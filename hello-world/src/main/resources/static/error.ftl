<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<#if (status!0) == 404>
		您访问的页面不存在:${path}<p>
	<#elseif (exception!"") == "com.goldgov.origin.core.web.token.TokenValidException">
		令牌验证失败！请不要重复提交<p>
	<#elseif (exception!"") == "org.springframework.web.bind.MissingServletRequestParameterException">
		请求错误，缺乏请求参数或参数不合法<br>
	<#else>
	
	错误:<p>状态：${status!0}<br> 错误：${error!}<br> 异常：${exception!}<br> 错误信息：${message!}<br> 路径：${path!}<br>日期：${(timestamp?string("yyyy-MM-dd HH:mm:ss"))!}
	<p>
	堆栈：<br>
	${trace!}
	</#if>
</body>
</html>