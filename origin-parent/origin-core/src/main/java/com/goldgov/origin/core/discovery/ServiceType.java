package com.goldgov.origin.core.discovery;

/**
 * 支持的服务节点类型枚举对象
 * @author LiuHG
 * @version 1.0
 */
public enum ServiceType {

		ProducerService,//生产者服务
		ConsumerService,//消费者服务
		ConfigurationService,//配置服务
		MonitorService,//监控服务
		LoggingService,//日志服务
		CertificationService,//认证服务
		DiscoveryService,//发现服务
		WebGateService,//网关服务
		TimeService,//时间服务
		NoService;//没有任何标准服务
}
