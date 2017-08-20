<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<#if (!org??) || (org.orgID=="") >
<form method="post" action="./addOrg">
</#if>
<#if org?? && org.orgID!="">
<form method="post" action="./updateOrg">
<input type="hidden" name="orgID" value="${(org.orgID)!}">
</#if>

机构名称：<input type="text" name="orgName" value="${(org.orgName)!}">
机构编码：<input type="text" name="orgCode" value="${(org.orgCode)!}">
缩写：<input type="text" name="abbreviation" value="${(org.abbreviation)!}">

<input type="hidden" name="parentOrganization.orgID" value="${RequestParameters['parentID']!}">
<input type="hidden" name="searchParentID" value="${RequestParameters['parentID']!}">
${csrfToken(true)}
${webToken(true)}
<br>
<input type="submit" value="<@i18n code="submit"/>"/> <input type="button" value="<@i18n code="back"/>" onclick="window.open('./listOrg<#if RequestParameters['parentID']??>?searchParentID=${RequestParameters['parentID']}</#if>','_self')"/>

</form>

</body>
</html>