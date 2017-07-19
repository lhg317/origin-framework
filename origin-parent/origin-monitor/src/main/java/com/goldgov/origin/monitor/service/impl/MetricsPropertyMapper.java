package com.goldgov.origin.monitor.service.impl;


import com.goldgov.origin.monitor.service.MonitorInfo;
import com.goldgov.origin.monitor.service.MonitorInfo.SystemMetrics;

/**
 * 
 * @author LiuHG
 * @version 1.0
 */
public class MetricsPropertyMapper {

	private MonitorInfo monitorInfo;
	
	public MetricsPropertyMapper(){
		monitorInfo = new MonitorInfo();
	}
	
	public void putMetricsProperty(String key,int value){
		
		SystemMetrics systemMetrics = monitorInfo.getSystemMetrics();
		if(key.equals("mem")){
			systemMetrics.setMemTotal(value);
		}else if(key.equals("processors")){
			systemMetrics.setProcessors(value);
		}else if(key.equals("uptime")){
			systemMetrics.setUptime(value);
		}else if(key.equals("heap")){
			systemMetrics.setHeap(value);
		}else if(key.equals("nonheap")){
			systemMetrics.setNonHeap(value);
		}else if(key.equals("threads")){
			systemMetrics.setThreads(value);
		}else if(key.equals("classes")){
			systemMetrics.setClasses(value);
		}
		
//		if(MetricsType.SYSTEM.contain(key)){
//			String suffix = MetricsType.SYSTEM.getSuffix(key);
//			if("".equals(suffix)){
//				SystemMetrics systemMetrics = monitorInfo.getSystemMetrics();
//				if(key.equals("mem")){
//					systemMetrics.setMemTotal(value);
//				}else if(key.equals("processors")){
//					systemMetrics.setProcessors(value);
//				}else if(key.equals("uptime")){
//					systemMetrics.setUptime(value);
//				}else if(key.equals("heap")){
//					systemMetrics.setHeap(value);
//				}else if(key.equals("nonheap")){
//					systemMetrics.setNonHeap(value);
//				}else if(key.equals("threads")){
//					systemMetrics.setThreads(value);
//				}else if(key.equals("classes")){
//					systemMetrics.setClasses(value);
//				}
//			}else{
//				SystemMetrics systemMetrics = monitorInfo.getSystemMetrics();
//				if(key.equals("free")){
//					systemMetrics.setMemFree(value);
//				}else if(key.equals("uptime")){
//					systemMetrics.setInstanceUptime(value);
//				}else if(key.equals("average")){
//					systemMetrics.setSystemload(value);
//				}else if(key.equals("committed")){
//					systemMetrics.setHeap(value);
//				}else if(key.equals("nonheap")){
//					systemMetrics.setNonHeap(value);
//				}else if(key.equals("threads")){
//					systemMetrics.setThreads(value);
//				}else if(key.equals("classes")){
//					systemMetrics.setClasses(value);
//				}
//			}
//			
//		}else if(MetricsType.HTTP.contain(key)){
//			String suffix = MetricsType.SYSTEM.getSuffix(key);
//			if("".equals(suffix)){
//				
//			}else{
//				
//			}
//		}
	}
	
//	/**
//	 * 
//	 * @author LiuHG
//	 * @version 1.0
//	 */
//	public enum MetricsType{
//		SYSTEM(new String[]{"mem","processors","instance","uptime","systemload","heap","nonheap","threads","classes","gc"}),HTTP(new String[]{""});
//		
//		private final String[] prefixs;
//		
//		MetricsType(String[] prefixs){
//			this.prefixs = prefixs;
//		}
//
//		public String[] getPrefixs() {
//			return prefixs;
//		}
//		
//		public boolean contain(String key){
//			for (String prefix : prefixs) {
//				if(key.startsWith(prefix)){
//					return true;
//				}
//			}
//			return false;
//		}
//		
//		public String getSuffix(String key){
//			for (String prefix : prefixs) {
//				if(prefix.startsWith(prefix)){
//					if(prefix.length() == key.length()){
//						return "";
//					}
//					return key.substring(prefix.length() + 1);
//				}
//			}
//			return "";
//		}
//		
//	}
	
}
