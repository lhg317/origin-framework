package com.goldgov.origin.core.discovery.rpc.pool;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class ClientSocketPooledObjectFactory extends BasePooledObjectFactory<TTransport>{

	private ResourceBundle config = ResourceBundle.getBundle("application");
	
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
			TTransport transport = null;
			try {
				String secPwd = config.getString("rpc.security.client.password");
				TSSLTransportParameters params = new TSSLTransportParameters();
				params.setTrustStore("META-INF/.truststore", secPwd, "SunX509", "JKS");
				transport = TSSLTransportFactory.getClientSocket(serviceIP, servicePort, timeout, params);
//				transport = new TFramedTransport(transport);
			} catch (MissingResourceException e) {
				transport = new TSocket(this.serviceIP, this.servicePort, this.timeout);
				transport.open();
//				transport = new TFramedTransport(new TSocket(this.serviceIP, this.servicePort, this.timeout));
			}
			
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
