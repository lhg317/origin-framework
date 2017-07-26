<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form id="listForm" method="post" action="./listRole">
<select name="locale" onchange="javascript:listForm.submit()">
	<option value="zh_CN" <#if (RequestParameters['locale']??) && (RequestParameters['locale']=="zh_CN")>selected</#if>>中</option>
	<option value="en_US" <#if (RequestParameters['locale']??) && (RequestParameters['locale']=="en_US")>selected</#if>>英</option>
</select>

<input type="button" value="<@i18n code="add"/>" onclick="javascript:window.open('./preAdd','_self')"/>

<input type="button" value="<@i18n code="logout"/>" onclick="javascript:window.open('/logout','_self')"/>

<table border=1>
<tr>
	<td><@i18n code="label.roleName" /></td>
	<td><@i18n code="label.roleCode" /></td>
	<td><@i18n code="operate"/></td>
</tr>

<#list query.resultList as role>

<#escape role as role?html>
<tr>
	<td>${role.roleName}</td>
	<td>${role.roleCode}</td>
	<td><a href="./getRole?roleID=${role.roleID}">【<@i18n code="edit"/>】</a>&nbsp;<a href="./deleteRole?roleID=${role.roleID}">【<@i18n code="delete"/>】</a>&nbsp;<a href="./listObject?roleID=${role.roleID}" target="_blank">【<@i18n code="i18n:select+label.user"/>】&nbsp;<a href="./listResource?roleID=${role.roleID}" target="_self">【<@i18n code="i18n:select+label.resource"/>】</a></td>
</tr>
</#escape>

</#list>
</table>
${query.pagingInfo.count}
${csrfToken(true)}

<input type="text" name="pagingInfo.currentPage" value="${query.pagingInfo.currentPage}"/>

</form>

</body>
</html>