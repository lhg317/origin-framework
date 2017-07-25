package com.goldgov.origin.monitor.service.impl;


import java.util.Iterator;
import java.util.Map;

import com.goldgov.origin.monitor.service.MetricsPropertyMapper;
import com.goldgov.origin.monitor.service.MonitorInfo;
import com.goldgov.origin.monitor.service.MonitorInfo.HttpMetrics;
import com.goldgov.origin.monitor.service.MonitorInfo.SystemMetrics;
import com.goldgov.origin.monitor.service.MonitorInfo.HttpMetrics.RpcSocketPoolMetrics;

/**
 * 
 * @author LiuHG
 * @version 1.0
 */
public class DefaultPropertyMapperImpl implements MetricsPropertyMapper{

	public MonitorInfo getMonitorInfo(Map<String,Number> propValueMap){
		MonitorInfo monitorInfo = new MonitorInfo();
		SystemMetrics systemMetrics = monitorInfo.getSystemMetrics();
		HttpMetrics httpMetrics = monitorInfo.getHttpMetrics();
		
		Iterator<String> keyIterator = propValueMap.keySet().iterator();
		while(keyIterator.hasNext()){
			String propName = keyIterator.next();
			
			//在统计请求数量时对该值已经处理，因此不用单独处理
			if(propName.startsWith("gauge.response") || propName.startsWith("rpc.pool.max-total")){
				continue;
			}
			
			Number propValue = propValueMap.get(propName);
			if(propName.equals("mem")){
				systemMetrics.setMemTotal(propValue.intValue());
			}else if(propName.equals("mem.free")){
				systemMetrics.setMemFree(propValue.intValue());
			}else if(propName.equals("processors")){
				systemMetrics.setProcessors(propValue.intValue());
			}else if(propName.equals("instance.uptime")){
				systemMetrics.setInstanceUptime(propValue.intValue());
			}else if(propName.equals("uptime")){
				systemMetrics.setUptime(propValue.intValue());
			}else if(propName.equals("systemload.average")){
				systemMetrics.setSystemload(propValue.doubleValue());
			}else if(propName.equals("heap.committed")){
				systemMetrics.setCommittedHeap(propValue.intValue());
			}else if(propName.equals("heap.init")){
				systemMetrics.setInitHeap(propValue.intValue());
			}else if(propName.equals("heap.used")){
				systemMetrics.setUsedHeap(propValue.intValue());
			}else if(propName.equals("heap")){
				systemMetrics.setHeap(propValue.intValue());
			}else if(propName.equals("nonheap.committed")){
				systemMetrics.setCommittedNonHeap(propValue.intValue());
			}else if(propName.equals("nonheap.init")){
				systemMetrics.setInitNonHeap(propValue.intValue());
			}else if(propName.equals("nonheap.used")){
				systemMetrics.setUsedNonHeap(propValue.intValue());
			}else if(propName.equals("nonheap")){
				systemMetrics.setNonHeap(propValue.intValue());
			}else if(propName.equals("threads.peak")){
				systemMetrics.setPeakThreads(propValue.intValue());
			}else if(propName.equals("threads.daemon")){
				systemMetrics.setDaemonThreads(propValue.intValue());
			}else if(propName.equals("threads.totalStarted")){
				systemMetrics.setTotalStartedThreads(propValue.intValue());
			}else if(propName.equals("threads")){
				systemMetrics.setThreads(propValue.intValue());
			}else if(propName.equals("classes")){
				systemMetrics.setClasses(propValue.intValue());
			}else if(propName.equals("classes.loaded")){
				systemMetrics.setLoadedClasses(propValue.intValue());
			}else if(propName.equals("classes.unloaded")){
				systemMetrics.setUnloadedClasses(propValue.intValue());
			}else if(propName.equals("gc.ps_scavenge.count")){
				systemMetrics.setGcCount(propValue.intValue());
			}else if(propName.equals("gc.ps_scavenge.time")){
				systemMetrics.setGcTime(propValue.intValue());
			}else if(propName.equals("gc.ps_marksweep.count")){
				systemMetrics.setGcMarksweepCount(propValue.intValue());
			}else if(propName.equals("gc.ps_marksweep.time")){
				systemMetrics.setGcMarksweepTime(propValue.intValue());
			}else if(propName.equals("httpsessions.max")){
				httpMetrics.setMaxHttpSessions(propValue.intValue());
			}else if(propName.equals("httpsessions.active")){
				httpMetrics.setActiveHttpSessions(propValue.intValue());
			}else if(propName.equals("datasource.primary.active")){
				
			}else if(propName.equals("datasource.primary.usage")){
				
			}else if(propName.startsWith("rpc.pool.num-active")){
				String host = propName.substring(20);
				Number maxTotal = propValueMap.get("rpc.pool.max-total." + host);
				RpcSocketPoolMetrics rpcSocketPoolMetrics = new RpcSocketPoolMetrics(host,maxTotal.intValue(),propValue.intValue());
				monitorInfo.addRpcSocketPoolMetrics(rpcSocketPoolMetrics);
			}else if(propName.startsWith("counter.status")){
				String[] infoSplit = propName.split("[.]");
				int statusCode = Integer.parseInt(infoSplit[2]);
				
				StringBuilder uri = new StringBuilder();
				StringBuilder responseUri = new StringBuilder("gauge.response");
				for (int i = 3; i < infoSplit.length; i++) {
					uri.append("/" + infoSplit[i]);
					responseUri.append("." + infoSplit[i]);
				}
				Number responseTime = propValueMap.get(responseUri.toString());
				responseTime = responseTime == null ? -1:responseTime;
				httpMetrics.addStatusCount(statusCode,uri.toString(), propValue.intValue(), responseTime.doubleValue());
			}
		}
		return monitorInfo;
		
	}
	
}
