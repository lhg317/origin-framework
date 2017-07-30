package com.goldgov.origin.server.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import com.goldgov.origin.core.discovery.ServiceServer;
import com.goldgov.origin.core.discovery.http.HttpRequestClient;
import com.goldgov.origin.core.discovery.http.Response;
import com.goldgov.origin.core.discovery.http.request.GetRequest;

/**
 * 服务端健康度检查器，每“5*服务器数量”秒检查一次各个服务器是否运行正常。
 * 如果检查到某些服务器不可用，则进行清除处理，同时通知各个服务端也进行清除。
 * @author LiuHG
 * @since 1.0
 */
@Component
public class ServiceHealthChecker extends Thread implements InitializingBean,DisposableBean{

	private final Log logger = LogFactory.getLog(getClass());
	
	private static final int RETRY_INTERVAL = 5000;//毫秒
	
	private boolean isRunning = true;
	
	@Autowired
	private DiscoveryServerService discoveryServerService;
	
	public ServiceHealthChecker(){
		super("HealthChecker");
	}
	
	private int checkInterval;
	
	private Date lastCheckDate;
	
	private List<CheckFailLog> failLogList = new ArrayList<>();
	
	private AtomicInteger failTotal = new AtomicInteger(0);
	
	@Override
	public void run() {
		while(isRunning){
			List<ServiceServer> allServices = discoveryServerService.getAllServiceServer();
			logger.debug("Health begin check...(" + allServices.size() + ")");
			for (int i = allServices.size() -1 ; i >= 0 ; i--) {
				ServiceServer serviceObject = allServices.get(i);
				if(!doCheck(serviceObject.getRpcServerAddress(),serviceObject.getHealthPath())){
					logger.debug("Prepare to clear all services under " + serviceObject.getRpcServerAddress());
//					discoveryServerService.deleteRequiredServiceName(serviceObject.getServerID());
//					discoveryServerService.deleteOptionalServiceName(serviceObject.getServerID());
					discoveryServerService.deleteServiceServer(serviceObject.getServerID());
				}
			}
			checkInterval = Math.max(RETRY_INTERVAL, RETRY_INTERVAL + (RETRY_INTERVAL/2)*(allServices.size() - 1));
			if(logger.isDebugEnabled()){
				logger.debug("Health check complete.Next time check in " + checkInterval +"ms");
			}
			
			lastCheckDate = new Date();
			
			try {
				Thread.sleep(checkInterval);
			} catch (InterruptedException e) {}
		}
		
	}
	
	public boolean doCheck(String host,String path){
		HttpRequestClient httpRequestClient = new HttpRequestClient();
		GetRequest request = new GetRequest(path);
		boolean result = false;
		
		String failReason = null;
		
		try {
			Response response = httpRequestClient.sendRequest(request);
			/*{"status":"UP",
			 * "rpcServer":{"status":"UP","port":6666},
			 * "diskSpace":{"status":"UP","total":196214018048,"free":51779588096,"threshold":10485760},
			 * "db":{"status":"UP","database":"MySQL","hello":1}}
			 */
			if(response.isSuccess()){
				HealthInfo healthInfo = response.toObject(HealthInfo.class);
				result = Status.UP.equals(new Status(healthInfo.getStatus()));
			}
		} catch (Exception e) {
			logger.warn("Health check fail:" + path,e);
			failReason = e.toString();
		}finally{
			httpRequestClient.close();
		}
		
		logger.debug("\t"+ path + " Health check :" + result);
		
		if(!result){
			failTotal.incrementAndGet();
			failLogList.add(new CheckFailLog(host,failReason));
		}
		
		return result;
	}

	public List<CheckFailLog> getFailLogList(){
		return failLogList;
	}
	
	public Integer getCheckFailTotal(){
		return failTotal.intValue();
	}
	
	public void clearCheckFailLog(){
		failLogList.clear();
		failTotal.set(0);
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		super.start();
	}

	public int getCheckInterval() {
		return checkInterval;
	}

	public Date getLastCheckDate() {
		return lastCheckDate;
	}
	
	@Override
	public void destroy(){
		isRunning = false;
		logger.info("停止服务器健康状态检查线程");
	}


	public static class CheckFailLog{
		private Date failDate;
		private String clientAddress;
		private String reason;
		
		public CheckFailLog(String clientAddress) {
			this(new Date(),clientAddress,null);
		}
		
		public CheckFailLog(Date failDate, String clientAddress) {
			this(failDate,clientAddress,null);
		}
		
		public CheckFailLog( String clientAddress, String reason) {
			this(new Date(),clientAddress,reason);
		}
		
		public CheckFailLog(Date failDate, String clientAddress, String reason) {
			this.failDate = failDate;
			this.clientAddress = clientAddress;
			this.reason = reason;
		}
		public Date getFailDate() {
			return failDate;
		}
		public void setFailDate(Date failDate) {
			this.failDate = failDate;
		}
		public String getClientAddress() {
			return clientAddress;
		}
		public void setClientAddress(String clientAddress) {
			this.clientAddress = clientAddress;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
		
		
	}
	
}
