package com.goldgov.origin.core.discovery.rpc.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class ClientSocketPooledObjectFactory extends BasePooledObjectFactory<TTransport>{

    private String serviceIP;
    private int servicePort;
    private int timeout;
    
	public ClientSocketPooledObjectFactory(String serviceIP, int servicePort, int timeout) {
		super();
		this.serviceIP = serviceIP;
		this.servicePort = servicePort;
		this.timeout = timeout;
	}

	@Override
	public TTransport create() throws Exception {
		try {
			TTransport transport = new TSocket(this.serviceIP, this.servicePort, this.timeout);
//            TTransport transport = new TFramedTransport(new TSocket(this.serviceIP, this.servicePort, this.timeout));
            transport.open();
            return transport;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public PooledObject<TTransport> wrap(TTransport transport) {
		return new DefaultPooledObject<TTransport>(transport);
	}

//	@Override
//	public void activateObject(PooledObject<TTransport> p) throws Exception {
//		super.activateObject(p);
//	}

	@Override
	public void destroyObject(PooledObject<TTransport> p) throws Exception {
		TTransport transport = p.getObject();
		if(transport.isOpen()){
			transport.close();
		}
		transport = null;
	}


}
