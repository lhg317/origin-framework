<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form id="listForm" method="post" action="./listOrg">

<input type="button" value="<@i18n code="add"/>" onclick="javascript:window.open('./preAddOrg<#if query.searchParentID??>?parentID=${query.searchParentID}</#if>','_self')"/>

<input type="button" value="<@i18n code="logout"/>" onclick="javascript:window.open('/logout','_self')"/>

<table border=1>
<tr>
	<td>机构名称</td>
	<td>机构编码</td>
	<td>缩写</td>
	<td><@i18n code="operate"/></td>
</tr>

<#list query.resultList as org>

<#escape org as org?html>
<tr>
	<td>${org.orgName}</td>
	<td>${org.orgCode}</td>
	<td>${org.abbreviation!}</td>
	<td>
	<a href="./listOrg?searchParentID=${org.orgID}">【子机构】</a>
	<a href="./getOrg?orgID=${org.orgID}">【<@i18n code="edit"/>】</a>
	<a href="./listObject?orgID=${org.orgID}">【机构用户】</a>
	<a href="./deleteOrg?orgID=${org.orgID}">【<@i18n code="delete"/>】</a>
	</td>
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