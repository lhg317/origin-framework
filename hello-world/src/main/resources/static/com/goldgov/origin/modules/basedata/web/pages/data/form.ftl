<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<#if (!data??) || (data.dataID=="") >
<form method="post" action="./addData">
</#if>
<#if data?? && data.dataID!="">
<form method="post" action="./updateData">
<input type="hidden" name="dataID" value="${(data.dataID)!}">
</#if>

数据名：<input type="text" name="dataName" value="${(data.dataName)!}">
数据值：<input type="text" name="dataValue" value="${(data.dataValue)!}">
排序：<input type="text" name="orderNum" value="${(data.orderNum)!}">
<input type="hidden" name="dataLocale.localeID" value="1">
<input type="hidden" name="dataCategory.categoryID" value="1">
${csrfToken(true)}
${webToken(true)}
<br>
<input type="submit" value="<@i18n code="submit"/>"/> <input type="button" value="<@i18n code="back"/>" onclick="window.open('./listUser','_self')"/>

</form>

</body>
</html>