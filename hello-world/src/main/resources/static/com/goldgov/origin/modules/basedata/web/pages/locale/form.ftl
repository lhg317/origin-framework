<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<#if (!locale??) || (locale.localeID=="") >
<form method="post" action="./addLocale">
</#if>
<#if locale?? && locale.localeID!="">
<form method="post" action="./updateLocale">
<input type="hidden" name="localeID" value="${(locale.localeID)!}">
</#if>

语言编码：<input type="text" name="localeCode" value="${(locale.localeCode)!}">
语言名称：<!-- <input type="text" name="localeName" value="${(locale.localeName)!}"> -->
${csrfToken(true)}
${webToken(true)}
<br>
<input type="submit" value="<@i18n code="submit"/>"/> <input type="button" value="<@i18n code="back"/>" onclick="window.open('./listUser','_self')"/>

</form>

</body>
</html>