package com.goldgov.origin.core.discovery.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.FactoryBean;

import com.goldgov.origin.core.discovery.rpc.pool.SocketProvider;

/**
 * RPC客户端服务执行代理类
 * @author LiuHG
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
public class RpcClientProxy<T extends TServiceClient> implements FactoryBean{

	private Class<T> clientClass;
	
	private Class<?> ifaceClass;
	
	private ReconnectingThriftClient<T> reconnectingThriftClient;

	private ServiceNameGenerator serviceNameGenerator;
	
	RpcClientProxy(Class<T> clientClass,ServiceProviderCenter serviceCenter,ServiceNameGenerator serviceNameGenerator){
		this.clientClass = clientClass;
		this.serviceNameGenerator = serviceNameGenerator;
//		this.serviceCenter = serviceCenter;
		Class<?>[] interfaces = clientClass.getInterfaces();
		for (Class<?> iface : interfaces) {
			if(iface.getSimpleName().equals("Iface")){
				ifaceClass =  iface;
				break;
			}
		}
		 reconnectingThriftClient = new ReconnectingThriftClient<T>(clientClass,serviceCenter,serviceNameGenerator);
	}
	
	@Override
	public Class getObjectType() {
		if(ifaceClass == null){
			throw new RuntimeException("is not thrift's Iface");
		}
		return ifaceClass;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
	
	public String getServiceName(){
		return serviceNameGenerator.generateServiceName(ifaceClass.getDeclaringClass());
	}

	@Override
	public Object getObject() throws Exception {
		return Proxy.newProxyInstance(clientClass.getClassLoader(),clientClass.getInterfaces(),reconnectingThriftClient);
//		return Proxy.newProxyInstance(clientClass.getClassLoader(),clientClass.getInterfaces(),new ReconnectingThriftClient<T>(clientClass,serviceCenter));
	}
	
	public static class ReconnectingThriftClient<T extends TServiceClient> implements InvocationHandler{
		
		private final Log logger = LogFactory.getLog(getClass());
		
		private ServiceProviderCenter serviceCenter;
		private Class<T> clientClass;

		private ServiceNameGenerator serviceNameGenerator;
		
		public ReconnectingThriftClient(Class<T> clientClass,ServiceProviderCenter serviceCenter, ServiceNameGenerator serviceNameGenerator){
			this.clientClass = clientClass;
			this.serviceCenter = serviceCenter;
			this.serviceNameGenerator = serviceNameGenerator;
		}
		
		@Override
		public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
			String serviceName = serviceNameGenerator.generateServiceName(clientClass.getDeclaringClass());
			SocketProvider socketProvider = serviceCenter.getSocketProvider(serviceName);

			if(logger.isTraceEnabled()){
				logger.trace("exec rpc service:" + serviceName +" on " + socketProvider);
			}
			
			TTransport socket = socketProvider.getSocket();
			
			/*
			 * 如果当前获取不到通讯socket（比如服务不可用或已经宕机），则将该服务器从注册列表中剔除，重新按照负载策略更换下一个socket，直到有可提供服务的RPC服务器。
			 * 如果所有的服务器均无法提供服务，则会抛出无服务的异常。
			*/
			while(socket == null){
				serviceCenter.deleteService(serviceName, socketProvider);
				socketProvider = serviceCenter.getSocketProvider(serviceName);
				socket = socketProvider.getSocket();
			}
			
			TProtocol protocol = new  TBinaryProtocol(socket);
		    TMultiplexedProtocol multiplexedProtocol = new TMultiplexedProtocol(protocol,serviceName);
		    T rpcClient = clientClass.getConstructor(TProtocol.class).newInstance(multiplexedProtocol);
			try {
				return arg1.invoke(rpcClient, arg2);
			}catch (Exception e) {
				//处理返回值为null的情况
				if(e instanceof InvocationTargetException){
					InvocationTargetException ex = (InvocationTargetException)e;
					Throwable targetException = ex.getTargetException();
					if(targetException instanceof TApplicationException && ((TApplicationException) targetException).getType() == TApplicationException.MISSING_RESULT){
						return null;
					}
				}
				
				logger.warn("The current service is not available:" + serviceName, e);
				//FIXME 如果指定的服务再调用几次后仍然失败，则在socketProvider中注销该服务，如果socketProvider中没有其他同服务的节点了，则清除socketProvider中的该服务支持标记及对象池
				int retry = 1;
				while(retry > 0){
					try{
						TTransport transport = rpcClient.getInputProtocol().getTransport();
						transport.close();
						transport.open();
						return arg1.invoke(rpcClient, arg2);
					}catch(TTransportException ex){
						retry--;
						//业务异常处理
					}catch(InvocationTargetException ex){
						Throwable targetException = ex.getTargetException();
						throw targetException;
					}
				}
				serviceCenter.deleteService(serviceName, socketProvider);
				return invoke(arg0, arg1,arg2);
			}finally{
				socketProvider.release(socket);
			}
		}
	}

}
