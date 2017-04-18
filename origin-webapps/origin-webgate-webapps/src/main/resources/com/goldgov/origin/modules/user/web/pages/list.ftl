<#assign authorize = "com.goldgov.origin.security.freemarker.model.AuthorizeTemplateModel"?new()>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form id="listForm" method="post" action="./listUser">
<select name="locale" onchange="javascript:listForm.submit()">
	<option value="zh_CN" <#if (RequestParameters['locale']??) && (RequestParameters['locale']=="zh_CN")>selected</#if>>中</option>
	<option value="en_US" <#if (RequestParameters['locale']??) && (RequestParameters['locale']=="en_US")>selected</#if>>英</option>
</select>

<input type="button" value="<@i18n code="add"/>" onclick="javascript:window.open('./preAdd','_self')"/>

<input type="button" value="<@i18n code="logout"/>" onclick="javascript:window.open('/logout','_self')"/>

<table border=1>
<tr>
	<td><@i18n code="label.loginName" /></td>
	<td><@i18n code="label.userName" /></td>
	<td><@i18n code="label.email" /></td>
	<td><@i18n code="operate"/></td>
</tr>

<#list query.resultList as user>

<#escape user as user?html>
<tr>
	<td>${user.loginName}</td>
	<td>${user.userName}</td>
	<td>${user.email!}</td>
	<td><a href="./getUser?userID=${user.userID}">【<@i18n code="edit"/>】</a>&nbsp;
	<@authorize code="user_deleteUsers" role="ROLE_ADMIN">
	<a href="./deleteUser?userID=${user.userID}">【<@i18n code="delete"/>】</a>
	</@authorize>
	</td>
</tr>
</#escape>

</#list>
</table>
${query.count}
<input type="hidden" name="${Request['_csrf'].parameterName}" value="${Request['_csrf'].token}"/>

</form>

</body>
</html>