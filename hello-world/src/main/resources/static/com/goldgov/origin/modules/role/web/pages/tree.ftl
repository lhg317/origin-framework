<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form id="listForm" method="post" action="../role/saveRoleResource">
<table border=1>
<tr>
	<td><@i18n code="label.roleName" /></td>
	<td><@i18n code="label.roleCode" /></td>
</tr>

<#list allResources as resource>

<#escape resource as resource?html>
<tr>
	<td><@i18n code="${resource.resourceName}" /></td>
	<td>
		<#list resource.resourceOperateList as operate>
			<#assign resourceOperate="${resource.resourceCode}_${operate.operateCode}"/>
			<input id="${resourceOperate}" type="checkbox" name="resourceOperate" value="${resourceOperate}" ><label for="${resourceOperate}"><@i18n code="${operate.operateName}" /></label><br>
		</#list>
	</td>
</tr>
</#escape>

</#list>
</table>
<input type="hidden" name="${Request['_csrf'].parameterName}" value="${Request['_csrf'].token}"/>
<input type="hidden" name="roleID" value="${RequestParameters['roleID']}"/>
<button type="submit"><@i18n code="select"/></button> <input type="button" value="<@i18n code="back"/>" onclick="window.open('./listRole','_self')"/>
</form>

</body>
</html>