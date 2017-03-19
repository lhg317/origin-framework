<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form id="listForm" method="post" action="./findList">

<input type="button" value="<@i18n code="add"/>" onclick="javascript:window.open('./preAdd','_self')"/>

<table border=1>
<tr>
	<td><@i18n code="label.title" /></td>
	<td><@i18n code="label.category"/></td>
	<td><@i18n code="label.className" /></td>
	<td><@i18n code="label.difficulty" /></td>
	<td><@i18n code="label.publishDate" /></td>
	<td><@i18n code="label.timeLimit" /></td>
	<td><@i18n code="label.scorePoint" /></td>
	<td><@i18n code="operate" /></td>
</tr>

<#list query.resultList as data>

<#escape data as data?html>
<tr>
	<td>${data.title}</td>
	<td>${data.category}</td>
	<td>${data.definitionClass}</td>
	<td>${data.difficulty}</td>
	<td>${data.publishDate}</td>
	<td>${data.timeLimit}</td>
	<td>${data.scorePoint}</td>
	<td><a href="./find?id=${data.exerciseID}">【<@i18n code="edit"/>】</a>&nbsp;<a href="./delete?ids=${data.exerciseID}">【<@i18n code="delete"/>】</a>&nbsp;<a href="../user/findUserSelectList?exerciseID=${data.exerciseID}" target="_blank">【<@i18n code="label.testCase"/>】</td>
</tr>
</#escape>

</#list>
</table>
${query.count}
<input type="hidden" name="${Request['_csrf'].parameterName}" value="${Request['_csrf'].token}"/>

</form>

</body>
</html>