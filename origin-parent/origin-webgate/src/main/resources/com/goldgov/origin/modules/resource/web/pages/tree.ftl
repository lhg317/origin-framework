<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form id="listForm" method="post" action="../role/saveRoleResource">
<select name="locale" onchange="javascript:listForm.submit()">
	<option value="zh_CN" <#if (RequestParameters['locale']??) && (RequestParameters['locale']=="zh_CN")>selected</#if>>中</option>
	<option value="en_US" <#if (RequestParameters['locale']??) && (RequestParameters['locale']=="en_US")>selected</#if>>英</option>
</select>

<input type="button" value="<@i18n code="logout"/>" onclick="javascript:window.open('/logout','_self')"/>

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
			<input id="${resource.resourceCode}_${operate.operateCode}" type="checkbox" name="operateCode" value="${resource.resourceCode}_${operate.operateCode}"><label for="${resource.resourceCode}_${operate.operateCode}"><@i18n code="${operate.operateName}" /></label><br>
		</#list>
	</td>
</tr>
</#escape>

</#list>
</table>
<input type="hidden" name="${Request['_csrf'].parameterName}" value="${Request['_csrf'].token}"/>
<input type="hidden" name="roleID" value="${RequestParameters['roleID']}"/>
<button type="submit"><@i18n code="select"/></button>
</form>

</body>
</html>