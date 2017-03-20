<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form id="listForm" method="post" action="./add">

<button type="submit" ><@i18n code="add"/></button>
<button type="button" onclick="window.open('../exercise/findList','_self')"><@i18n code="back"/></button><p>

<@i18n code="label.paramValues" suffix="colon"/><input type="text" name="paramValues">
<@i18n code="label.paramValues" suffix="colon"/><input type="text" name="returnValue">
<table border=1>
<tr>
	<td><@i18n code="label.paramValues" /></td>
	<td><@i18n code="label.returnValue"/></td>
	<td><@i18n code="operate" /></td>
</tr>

<#list testCaseList as data>

<#escape data as data?html>
<tr>
	<td>${data.paramValues}</td>
	<td>${data.returnValue}</td>
	<td><a href="./delete?ids=${data.testCaseID}&exerciseID=${RequestParameters['exerciseID']}">【<@i18n code="delete"/>】</a>&nbsp;<a href="../user/findUserSelectList?exerciseID=${data.testCaseID}" target="_blank">【<@i18n code="i18n:select+label.user"/>】</td>
</tr>
</#escape>

</#list>
</table>
<input type="hidden" name="${Request['_csrf'].parameterName}" value="${Request['_csrf'].token}"/>
<input type="hidden" name="exerciseID" value="${RequestParameters['exerciseID']}"/>
${webToken(true)}
</form>

</body>
</html>