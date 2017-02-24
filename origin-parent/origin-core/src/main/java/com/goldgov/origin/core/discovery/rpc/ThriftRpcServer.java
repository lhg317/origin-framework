package com.goldgov.origin.core.discovery.rpc;

import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class ThriftRpcServer implements InitializingBean,DisposableBean {

	private final Log logger = LogFactory.getLog(getClass());
	
	@Value("${rpc.server.port:7777}")
	private int port;
	
	@Autowired(required = false)
	private List<RpcServiceProxy> rpcServiceList;
	
	private TServer server;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		if(rpcServiceList == null || rpcServiceList.size() == 0){
			logger.debug("当前系统中不存在RPC服务，RPC服务器终止启动。");
			return;
		}
		
		Thread rpcServer = new Thread("Thrift Server"){
			@Override
			public void run() {
				try {
		            TServerTransport serverTransport = new TServerSocket(port);
//					TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(port);
		            TMultiplexedProcessor processor = new TMultiplexedProcessor();
		            for (RpcServiceProxy rpcService : rpcServiceList) {
		            	Class<?>[] interfaces = rpcService.getObjectType().getInterfaces();
		            	for (Class<?> clazz : interfaces) {
		            		String className = clazz.getName();
							if(className.endsWith("$Iface")){
								Class<?> declaringClass = clazz.getDeclaringClass();
								Class<?> processorClass = Class.forName(declaringClass.getName() + "$Processor");
								TProcessor subProcessor = (TProcessor)processorClass.getConstructor(clazz).newInstance(rpcService.getObject());
								processor.registerProcessor(rpcService.getServiceName(), subProcessor);
							}
						}
					}
		            server = new TThreadPoolServer(new org.apache.thrift.server.TThreadPoolServer.Args(serverTransport).processor(processor));
//		            server = new TNonblockingServer(new org.apache.thrift.server.TNonblockingServer.Args(serverTransport).processor(processor));
		            logger.info("Rpc server startup on port:" + port + ",service num:" + rpcServiceList.size());
		            server.serve();
		        } catch (Exception e) {
		        	//FIXME
		            e.printStackTrace();
		            logger.error("RPC服务启动失败", e);
		        }
			}
		};
		rpcServer.start();
	}

	
	public synchronized void stop(){
		if(isServing()){
			server.stop();
		}
	}
	
	public boolean isServing(){
		return server != null && server.isServing();
	}
	
	public int getPort(){
		return port;
	}

	/**
	 * 
	 * @return 永远不返回null
	 */
	public List<RpcServiceProxy> getRpcServiceList() {
		if(rpcServiceList == null || rpcServiceList.size() == 0){
			return Collections.emptyList();
		}
		return Collections.unmodifiableList(rpcServiceList);
	}

	@Override
	public void destroy() throws Exception {
		stop();
		logger.debug("RPC服务已经停止");
	}
}
