package com.goldgov.origin.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.goldgov.origin.core.discovery.IsWebGate;
import com.goldgov.origin.core.discovery.ServiceServer;
import com.goldgov.origin.core.discovery.ServiceServer.ServiceType;
import com.goldgov.origin.core.discovery.http.HttpRequestClient;
import com.goldgov.origin.core.discovery.http.Response;
import com.goldgov.origin.core.discovery.http.request.JsonRequest;
import com.goldgov.origin.core.discovery.rpc.RpcClientProxy;
import com.goldgov.origin.core.discovery.rpc.RpcServiceInstance;
import com.goldgov.origin.core.discovery.rpc.RpcServiceProxy;
import com.goldgov.origin.core.discovery.rpc.ThriftRpcServer;
import com.goldgov.origin.core.utils.ArrayUtils;
/**
 * 本地服务注册器，负责将本地含有的服务注册到服务中心。如果注册失败则默认等待5秒后继续尝试，直到成功为止。
 * 注册服务是以Http方式注册到服务中心，因此本注册服务保证了注册行为在容器启动之后执行。当本地服务注册成功后，
 * 则注册器的工作即结束，退出线程。
 * 该类必须在主线程中执行，否则可能导致获取网关服务类型错误。
 * @author LiuHG
 * @since 1.0
 */
public class LocalServiceRegister implements ApplicationListener<EmbeddedServletContainerInitializedEvent>{

	private final Log logger = LogFactory.getLog(getClass());
	
	private static final int RETRY_INTERVAL = 5000;//毫秒
	
	
	@Autowired
	private ThriftRpcServer rpcServer;
	
	@SuppressWarnings("rawtypes")
	@Autowired(required=false)
	private List<RpcClientProxy> rpcClientList;
	
	@Autowired
	private ClientConfig clientConfig;
	
	@Value("${server.port:80}")
	private int serverPort;
	
	@Value("${server.context-path:}")
	private String contextPath;
	
//	@Value("${discovery.client.update-path}")
//	private String updatePath;
//	
//	@Value("${discovery.client.health-path:}")
//	private String healthPath;
	
	@Value("${discovery.client.web-path:}")
	private String webPath;
	
	@Value("${application.name:Unspecified}")
	private String applicationName;
	
	@Value("${server.display-name:Unspecified}")
	private String displayName;
	
//	@Value("${discovery.config.config-path:}")
//	private String configPath;
	
	@Value("${server.ssl.enabled:false}")
	private boolean sslEnabled;
	
	private boolean started = false;
	
	private boolean isWebGate = false;
	
	public synchronized void register(){
		if(started){
			logger.info("Register thread already running.");
			return;
		}
		started = true;
		
		final String discoveryServer = clientConfig.getDiscoveryServer();
		Assert.hasText(discoveryServer,"discovery server is not specified.");
		
		final List<RpcServiceProxy> rpcServiceList = rpcServer.getRpcServiceList();
//		if(rpcServiceList.size() == 0){
//			logger.debug("当前应用不包含任何rpc服务，不发起服务注册请求");
//			return;
//		}
		final String[] optional = getOptionalModules();
		
		//判断是否为网关服务
		isWebGate = isWebGate();

		new Thread("ServiceRegister"){

			private int failTimes; 
			
			@SuppressWarnings("rawtypes")
			@Override
			public void run() {
				
				ServiceServer localService = new ServiceServer();
				if(rpcServer.isServing()){
					localService.setServerPort(rpcServer.getPort());
				}

				if(webPath == null || "".equals(webPath)){
					String protocol = sslEnabled ? "https":"http";
					webPath = protocol + "://{serverIP}:" + serverPort + contextPath;
				}
				localService.setServerID(UUID.randomUUID().toString());
				localService.setWebPath(webPath);
//				localService.setHealthPath(healthPath);
//				localService.setUpdatePath(updatePath);
//				localService.setConfigPath(configPath);
				
				for (RpcServiceProxy rpcServiceObject : rpcServiceList) {
					RpcServiceInstance registeredService = new RpcServiceInstance(rpcServiceObject.getServiceName(),localService);
					registeredService.setDisplayName(rpcServiceObject.getDisplayName());
					localService.addService(registeredService);
				}
				
				if(rpcClientList != null){
					for (RpcClientProxy rpcServiceProxy : rpcClientList) {
						if(!isServiceSelf(rpcServiceProxy.getServiceName())){
							if(ArrayUtils.contain(optional,rpcServiceProxy.getServiceName()) != -1){
								localService.addOptionalServerName(rpcServiceProxy.getServiceName());
							}else{
								localService.addRequiredServerName(rpcServiceProxy.getServiceName());
							}
						}
					}
				}

				localService.setServiceType(getServiceType());
				if(applicationName != null && !"".equals(applicationName)){
					localService.setApplicationName(applicationName);
				}
				if(displayName != null && !"".equals(displayName)){
					localService.setDisplayName(displayName);
				}
				
				
				boolean registerSuccess = false;
				
				while(!registerSuccess){
					HttpRequestClient requestClient = new HttpRequestClient();
					try {
						JsonRequest jsonRequest = new JsonRequest(localService,discoveryServer);
						Response response = requestClient.sendRequest(jsonRequest);
						if(response.getStatusCode() == 200){
							String result = response.toString();
							if(result.equals("SUCCESS")){
								logger.info("register success:" + discoveryServer + ",service num:" + rpcServiceList.size());
								registerSuccess = true;
								return;
							}else{
								logger.error("注册失败：" + localService + "，服务器返回了不可识别的信息：" + result + "，" + RETRY_INTERVAL + "后重试");
							}
						}else{
							logger.warn("注册失败：" + localService + "，错误明细：" + response.toString() + "，" + RETRY_INTERVAL + "后重试");
						}
					} catch (Exception e) {
						failTimes++;
						if(logger.isDebugEnabled()){
							logger.debug("registration server failed.fail times:" + failTimes + ",Retry after " + RETRY_INTERVAL + "ms", e);
						}
						logger.warn("registration server failed:"+e.getMessage()+".fail times:" + failTimes + ",Retry after " + RETRY_INTERVAL + "ms" + ",full stack in debug level");
					}finally{
						requestClient.close();
					}
					try {
						Thread.sleep(RETRY_INTERVAL);
					} catch (InterruptedException e) {}
				}
			}
			
		}.start();
		
	}

	private boolean isWebGate() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement stackTraceElement = stackTrace[stackTrace.length - 1];
		if ("main".equals(stackTraceElement.getMethodName())) {
			try {
				Class<?>[] interfaces = Class.forName(stackTraceElement.getClassName()).getInterfaces();
				for (Class<?> interfaceClass : interfaces) {
					if(interfaceClass == IsWebGate.class){
						return true;
					}
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
		}else if("run".equals(stackTraceElement.getMethodName())){
			throw new RuntimeException("获取网关类型失败，注册器被非主线程调用。");
		}
		return false;
	}

	private String[] getOptionalModules() {
		InputStream resourceStream = this.getClass().getResourceAsStream("/META-INF/rpc");
		if(resourceStream != null){
			Properties properties = new Properties();
			try {
				properties.load(resourceStream);
				String property = properties.getProperty("optional.modules");
				return StringUtils.tokenizeToStringArray(property, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
			} catch (IOException e) {
				throw new RuntimeException("获取可选模块时发生IO错误",e);
			}finally{
				try {
					resourceStream.close();
				} catch (IOException e) {}
			}

		}else{
			return new String[0];
		}
	}
	
	private boolean isServiceSelf(String serviceName){
		List<RpcServiceProxy> rpcServiceList = rpcServer.getRpcServiceList();
		boolean hasService = false;
		for (RpcServiceProxy providedService : rpcServiceList) {
			if(providedService.getServiceName().equals(serviceName)){
				hasService = true;
				break;
			}
		}
		return hasService;
	}
	
	@SuppressWarnings("rawtypes")
	public ServiceType[] getServiceType(){
		List<ServiceType> serviceTypeList = new ArrayList<>();
		
		//判断是否为生产者服务
		List<RpcServiceProxy> rpcServiceList = rpcServer.getRpcServiceList();
		if(rpcServiceList.size() > 0){
			serviceTypeList.add(ServiceType.ProducerService);
		}
		
		//判断是否为消费者服务
		if(rpcClientList != null && rpcClientList.size() > 0){
			for (RpcClientProxy rpcServiceProxy : rpcClientList) {
				if(!isServiceSelf(rpcServiceProxy.getServiceName())){
					serviceTypeList.add(ServiceType.ConsumerService);
					break;
				}
			}
		}
		
		//判断是否为网关服务
		if(isWebGate){
			serviceTypeList.add(ServiceType.WebGateService);
		}
		
		//判断是否为注册发现服务
		try {
			Class.forName("com.goldgov.origin.server.service.DiscoveryServerService");
			serviceTypeList.add(ServiceType.DiscoveryService);
		} catch (ClassNotFoundException e) {}
		
		//判断是否为配置服务
		try {
			Class.forName("com.goldgov.origin.config.ConfigConfiguration");
			serviceTypeList.add(ServiceType.ConfigurationService);
		} catch (ClassNotFoundException e) {}
				
		//判断是否为注册发现服务
		try {
			Class.forName("com.goldgov.origin.monitor.MonitorConfiguration");
			serviceTypeList.add(ServiceType.MonitorService);
		} catch (ClassNotFoundException e) {}
		
		//TODO 更多服务的判断方式
		
		if(serviceTypeList.isEmpty()){
			serviceTypeList.add(ServiceType.NoService);
		}
		
		return serviceTypeList.toArray(new ServiceType[0]);
	}
	

	@Override
	public void onApplicationEvent(EmbeddedServletContainerInitializedEvent arg0) {
		register();
	}
}
