package com.goldgov.origin.core.discovery.rpc.pool;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.DisposableBean;

import com.goldgov.origin.core.discovery.rpc.RpcServiceInstance;

public class SocketProviderImpl implements SocketProvider, DisposableBean{

	private final Log logger = LogFactory.getLog(getClass());
	
    private String serviceIP;
    private int servicePort;
    private int timeout;
    
    private int maxTotal = GenericObjectPoolConfig.DEFAULT_MAX_TOTAL;
    private int maxIdle = GenericObjectPoolConfig.DEFAULT_MAX_IDLE;
    private int minIdle = GenericObjectPoolConfig.DEFAULT_MIN_IDLE;
    private long maxWait = GenericObjectPoolConfig.DEFAULT_MAX_WAIT_MILLIS;
    
    private boolean testOnBorrow = GenericObjectPoolConfig.DEFAULT_TEST_ON_BORROW;
    private boolean testOnReturn = GenericObjectPoolConfig.DEFAULT_TEST_ON_RETURN;
    private boolean testWhileIdle = GenericObjectPoolConfig.DEFAULT_TEST_WHILE_IDLE;

    private GenericObjectPool<TTransport> objectPool = null;
    
	public SocketProviderImpl(String serviceIP, int servicePort, int timeout) {
		this.serviceIP = serviceIP;
		this.servicePort = servicePort;
		this.timeout = timeout;
	}
	
	public SocketProviderImpl(RpcServiceInstance service, int timeout) {
		this.serviceIP = service.getServiceServer().getServerIP();
		this.servicePort = service.getServiceServer().getServerPort();
		this.timeout = timeout;
		
		init();
	}
	
	private void init() {
		ClientSocketPooledObjectFactory poolableObjectFactory = new ClientSocketPooledObjectFactory(serviceIP, servicePort, timeout);  
		objectPool = new GenericObjectPool<TTransport>(poolableObjectFactory);
		
		objectPool.setMaxTotal(maxTotal);  
        objectPool.setMaxIdle(maxIdle);  
        objectPool.setMinIdle(minIdle);  
        objectPool.setMaxWaitMillis(maxWait);  
        objectPool.setTestOnBorrow(testOnBorrow);  
        objectPool.setTestOnReturn(testOnReturn);  
        objectPool.setTestWhileIdle(testWhileIdle);  
        objectPool.setBlockWhenExhausted(GenericObjectPoolConfig.DEFAULT_BLOCK_WHEN_EXHAUSTED); 
	}

	@Override
	public void destroy() {
		try {  
            objectPool.close();
        } catch (Exception e) {  
            throw new RuntimeException("erorr destroy()", e);  
        }
	}
	
	@Override
	public TTransport getSocket() {
		try {  
			TTransport socket = (TTransport) objectPool.borrowObject();  
            logger.debug("[getSocket] numActive : " + objectPool.getNumActive() + "/" + objectPool.getMaxTotal());
            return socket;
        } catch (Exception e) {
        	logger.error("获取TSocket时发生错误，请确认RPC服务器连接参数是否正确："+serviceIP+":"+servicePort, e);
//            throw new RuntimeException(e);//此处不抛出异常，此链接失败返回null后尝试其他服务端
        	return null;
        }  
	}
	@Override
	public void release(TTransport socket) {
		try {
            objectPool.returnObject(socket);
//            logger.debug("[releaseSocket] numActive : " + objectPool.getNumActive() + "/" + objectPool.getMaxTotal());
        } catch (Exception e) {
            throw new RuntimeException("error returnCon()", e);
        }
	}

}
