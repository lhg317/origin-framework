package com.goldgov.origin.monitor.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.goldgov.origin.core.discovery.ServiceServer;

/**
 * 监控信息对象
 * @author LiuHG
 * @version 1.0
 */
public class MonitorInfo {

	private ServiceServer server;
	
	private SystemMetrics systemMetrics = new SystemMetrics();
	private HttpMetrics httpMetrics = new HttpMetrics();
	
	public ServiceServer getServer() {
		return server;
	}

	public void setServer(ServiceServer server) {
		this.server = server;
	}

	public SystemMetrics getSystemMetrics() {
		return systemMetrics;
	}

	public HttpMetrics getHttpMetrics() {
		return httpMetrics;
	}

	/**
	 * 系统指标对象
	 * @author LiuHG
	 * @version 1.0
	 */
	public class SystemMetrics{
		private int memTotal; //The total system memory in KB 
		private int memFree;//The amount of free memory in KB
		private int processors;//The number of processors
		private long instanceUptime;//The application context uptime in milliseconds
		private long uptime;//The system uptime in milliseconds
		private int systemload;//The average system load
		
		//Heap information in KB
		private int heap;
		private int committedHeap;
		private int initHeap;
		private int usedHeap;
		private int nonHeap;
		private int committednonHeap;
		private int initnonHeap;
		private int usednonHeap;
		
		//Thread information
		private int threads;
		private int peakThreads;
		private int daemonThreads;
		
		//Class load information
		private int classes;
		private int loadedClasses; 
		private int unloadedClasses; 
		
		//Garbage collection information
		private int gcCount;
		private int gcTime;
		private int gcMarksweepCount;
		private int gcMarksweepTime;
		
		public int getNonHeap() {
			return nonHeap;
		}
		public void setNonHeap(int nonHeap) {
			this.nonHeap = nonHeap;
		}
		public int getCommittednonHeap() {
			return committednonHeap;
		}
		public void setCommittednonHeap(int committednonHeap) {
			this.committednonHeap = committednonHeap;
		}
		public int getInitnonHeap() {
			return initnonHeap;
		}
		public void setInitnonHeap(int initnonHeap) {
			this.initnonHeap = initnonHeap;
		}
		public int getUsednonHeap() {
			return usednonHeap;
		}
		public void setUsednonHeap(int usednonHeap) {
			this.usednonHeap = usednonHeap;
		}
		public int getMemTotal() {
			return memTotal;
		}
		public void setMemTotal(int memTotal) {
			this.memTotal = memTotal;
		}
		public int getMemFree() {
			return memFree;
		}
		public void setMemFree(int memFree) {
			this.memFree = memFree;
		}
		public int getProcessors() {
			return processors;
		}
		public void setProcessors(int processors) {
			this.processors = processors;
		}
		public long getInstanceUptime() {
			return instanceUptime;
		}
		public void setInstanceUptime(long instanceUptime) {
			this.instanceUptime = instanceUptime;
		}
		public long getUptime() {
			return uptime;
		}
		public void setUptime(long uptime) {
			this.uptime = uptime;
		}
		public int getSystemload() {
			return systemload;
		}
		public void setSystemload(int systemload) {
			this.systemload = systemload;
		}
		public long getHeap() {
			return heap;
		}
		public void setHeap(int heap) {
			this.heap = heap;
		}
		public long getCommittedHeap() {
			return committedHeap;
		}
		public void setCommittedHeap(int committedHeap) {
			this.committedHeap = committedHeap;
		}
		public long getInitHeap() {
			return initHeap;
		}
		public void setInitHeap(int initHeap) {
			this.initHeap = initHeap;
		}
		public long getUsedHeap() {
			return usedHeap;
		}
		public void setUsedHeap(int usedHeap) {
			this.usedHeap = usedHeap;
		}
		public int getThreads() {
			return threads;
		}
		public void setThreads(int threads) {
			this.threads = threads;
		}
		public int getPeakThreads() {
			return peakThreads;
		}
		public void setPeakThreads(int peakThreads) {
			this.peakThreads = peakThreads;
		}
		public int getDaemonThreads() {
			return daemonThreads;
		}
		public void setDaemonThreads(int daemonThreads) {
			this.daemonThreads = daemonThreads;
		}
		public int getClasses() {
			return classes;
		}
		public void setClasses(int classes) {
			this.classes = classes;
		}
		public int getLoadedClasses() {
			return loadedClasses;
		}
		public void setLoadedClasses(int loadedClasses) {
			this.loadedClasses = loadedClasses;
		}
		public int getUnloadedClasses() {
			return unloadedClasses;
		}
		public void setUnloadedClasses(int unloadedClasses) {
			this.unloadedClasses = unloadedClasses;
		}
		public int getGcCount() {
			return gcCount;
		}
		public void setGcCount(int gcCount) {
			this.gcCount = gcCount;
		}
		public int getGcTime() {
			return gcTime;
		}
		public void setGcTime(int gcTime) {
			this.gcTime = gcTime;
		}
		public int getGcMarksweepCount() {
			return gcMarksweepCount;
		}
		public void setGcMarksweepCount(int gcMarksweepCount) {
			this.gcMarksweepCount = gcMarksweepCount;
		}
		public int getGcMarksweepTime() {
			return gcMarksweepTime;
		}
		public void setGcMarksweepTime(int gcMarksweepTime) {
			this.gcMarksweepTime = gcMarksweepTime;
		}
		
	}
	
	/**
	 * Http指标对象
	 * @author LiuHG
	 * @version 1.0
	 */
	public class HttpMetrics{
		private int maxHttpSessions;
		private int activeHttpSessions;
		
		private List<StatusCount> statusCounterList = new ArrayList<>();
		
		public int getMaxHttpSessions() {
			return maxHttpSessions;
		}

		public void setMaxHttpSessions(int maxHttpSessions) {
			this.maxHttpSessions = maxHttpSessions;
		}

		public int getActiveHttpSessions() {
			return activeHttpSessions;
		}

		public void setActiveHttpSessions(int activeHttpSessions) {
			this.activeHttpSessions = activeHttpSessions;
		}

		public List<StatusCount> getStatusCounterList() {
			return statusCounterList;
		}

		public void addStatusCount(int status,String uri,long count) {
			addStatusCount(status,uri,-1);
		}
		
		public void addStatusCount(int status,String uri,int count,int responseTime) {
			StatusCount statusCount = new StatusCount(status);
			statusCount.putCount(uri, count,responseTime);
			this.statusCounterList.add(statusCount);
		}

		/**
		 * Http请求、响应指标对象
		 * @author LiuHG
		 * @version 1.0
		 */
		public class StatusCount{
			private final int status;
			private Map<String,StatusMetrics> requestMap = new HashMap<>();
			
			private StatusCount(int status){
				this.status = status;
			}
			
			public void putCount(String uri,int count,int responseTime){
				requestMap.put(uri, new StatusMetrics(count,responseTime));
			}
			
			public long getTotalCount(){
				Collection<StatusMetrics> values = requestMap.values();
				long total = 0;
				for (StatusMetrics metrics : values) {
					total += metrics.getCount();
				}
				return total;
			}

			public int getStatus() {
				return status;
			}
			
		}
		
		public class StatusMetrics{
			private final int count;
			private final int responseTime;
			
			public StatusMetrics(int count, int responseTime) {
				super();
				this.count = count;
				this.responseTime = responseTime;
			}

			public int getCount() {
				return count;
			}

			public int getResponseTime() {
				return responseTime;
			}
		}
		
	}
	
}
