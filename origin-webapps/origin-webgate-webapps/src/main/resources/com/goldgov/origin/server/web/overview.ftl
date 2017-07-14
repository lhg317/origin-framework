<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Demo</title>

<body>

检查间隔：${(checkInterval)!''}秒，最近一次检查时间：${(lastCheckDate?string("yyyy-MM-dd HH:mm:ss"))!''}，检查失败主机次数：${checkFailTotal} <a href="#">[明细]</a><button name="clearCheckFailLog">清除记录</button>

<h2>服务提供状态 (${allServices?size})</h2>
<table border="1" width="80%">
	<tr>
		<td style="text-align:center"><b>服务</b></td>
		<td colspan="2" style="text-align:center"><b>提供主机</b></td>
	</tr>
	
	<#list allServices?keys as key>
	<tr>
		<td rowspan="${(allServices[key]?size > 0)?string(allServices[key]?size,1)}">
			${key} (${allServices[key]?size})
		</td>
		<#list allServices[key] as item>
		<#if item_index &gt; 0>
			<tr>
		</#if>
		<td style="text-align:center">
			${item.serviceServer.rpcServerAddress}
		</td>
		<#if item_index &gt; 0>
			</tr>
		</#if>
		</#list>
		<#if (allServices[key]?size) == 0>
			<td colspan="3">
				无提供服务
			<td>
		</#if>
	</tr>
	</#list>
</table>
<p>

<h2>客户端运行状态  (${clientHealth.serviceHealthMap?size})</h2>
<table border="1" width="80%">
	<tr>
		<td colspan="2" style="text-align:center"><b>主机</b></td>
		<td colspan="2" style="text-align:center"><b>依赖服务</b></td>
	</tr>
	
	<#list clientHealth.clientHealthMap?keys as key>
	<tr>
		<td rowspan="${(clientHealth.serviceHealthMap[key]?size > 0)?string(clientHealth.serviceHealthMap[key]?size,1)}">
			${key}（${clientHealth.server.displayName!}：${clientHealth.server.applicationName!}）<br> 服务类型： <#list clientHealth.serviceServerMap[key].serviceType as serviceType>${serviceType};</#list>
		</td>
		<td rowspan="${(clientHealth.serviceHealthMap[key]?size > 0)?string(clientHealth.serviceHealthMap[key]?size,1)}" style="text-align:center">
			${clientHealth.clientHealthMap[key]}
		</td>
		<#list clientHealth.serviceHealthMap[key] as item>
		<#if item_index &gt; 0>
			<tr>
		</#if>
		<td>
			${item.serviceName}
		</td>
		<td style="text-align:center">
			${item.state}
		</td>
		<#if item_index &gt; 0>
			</tr>
		</#if>
		</#list>
		<#if (clientHealth.serviceHealthMap[key]?size) == 0>
			<td>
				无依赖服务
			</td>
		</#if>
	</tr>
	</#list>
</table>

负载策略
</body>
</html>