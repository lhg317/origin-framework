package com.goldgov.origin.server.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class HealthInfo {

	private String status;
	private RpcServer rpcServer;
	private DiskSpace diskSpace;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public RpcServer getRpcServer() {
		return rpcServer;
	}

	public void setRpcServer(RpcServer rpcServer) {
		this.rpcServer = rpcServer;
	}

	public DiskSpace getDiskSpace() {
		return diskSpace;
	}

	public void setDiskSpace(DiskSpace diskSpace) {
		this.diskSpace = diskSpace;
	}
	
	public static class RpcServer{
		private String status;
		private int port;
		private int serviceNum;
		private String serverStatus;
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public int getPort() {
			return port;
		}
		public void setPort(int port) {
			this.port = port;
		}
		public int getServiceNum() {
			return serviceNum;
		}
		public void setServiceNum(int serviceNum) {
			this.serviceNum = serviceNum;
		}
		public String getServerStatus() {
			return serverStatus;
		}
		public void setServerStatus(String serverStatus) {
			this.serverStatus = serverStatus;
		}
		
	}
	
	public static class DiskSpace{
		private String status;
		private long total;
		private long free;
		private long threshold;
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public long getTotal() {
			return total;
		}
		public void setTotal(long total) {
			this.total = total;
		}
		public long getFree() {
			return free;
		}
		public void setFree(long free) {
			this.free = free;
		}
		public long getThreshold() {
			return threshold;
		}
		public void setThreshold(long threshold) {
			this.threshold = threshold;
		}
		
	}
}
