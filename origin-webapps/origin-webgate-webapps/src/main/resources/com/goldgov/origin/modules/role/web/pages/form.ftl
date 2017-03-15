<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<#if (!role??) || (!role.roleID??) >
<form method="post" action="./addRole">
</#if>
<#if role?? && (role.roleID??)>
<form method="post" action="./updateRole">
<input type="hidden" name="roleID" value="${(role.roleID)!}">
</#if>

<@i18n code="label.roleName" suffix="colon"/><input type="text" name="roleName" value="${(role.roleName)!}">
<@i18n code="label.roleCode" suffix="colon"/><input type="text" name="roleCode" value="${(role.roleCode)!}">
<@i18n code="label.description" suffix="colon"/><input type="text" name="description" value="${(role.description)!}">
<input type="hidden" name="${Request['_csrf'].parameterName}" value="${Request['_csrf'].token}"/>
${webToken(true)}
<br>
<input type="submit" value="<@i18n code="submit"/>"/> <input type="button" value="<@i18n code="back"/>" onclick="window.open('./findRoles')"/>

</form>

</body>
</html>