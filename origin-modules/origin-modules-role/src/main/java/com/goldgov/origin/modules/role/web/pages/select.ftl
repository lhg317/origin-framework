<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form id="listForm" method="post" action="./saveRoleObject">
<table border=1>
<tr>
	<td><@i18n code="select" /></td>
	<td><@i18n code="label.loginName" /></td>
	<td><@i18n code="label.userName" /></td>
	<td><@i18n code="label.email" /></td>
</tr>

<#list query.resultList as user>

<#escape user as user?html>
<tr>
	<td><input type="checkbox" name="loginName" value="${user.loginName}"></td>
	<td>${user.userName}</td>
	<td>${user.loginName}</td>
	<td>${user.email!}</td>
</tr>
</#escape>

</#list>
</table>
<input type="hidden" name="${Request['_csrf'].parameterName}" value="${Request['_csrf'].token}"/>
<input type="hidden" name="roleID" value="${RequestParameters['roleID']}"/>
<input type="hidden" name="${Request['_csrf'].parameterName}" value="${Request['_csrf'].token}"/>

<button type="submit"><@i18n code="select"/></button>
</form>

</body>
</html>