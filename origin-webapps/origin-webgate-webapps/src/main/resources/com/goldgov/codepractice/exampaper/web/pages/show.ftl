<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<#if (!examPaper??) || (examPaper.examPaperID == 0) >
<form method="post" action="./add">
</#if>
<#if examPaper?? && (examPaper.examPaperID != 0)>
<form method="post" action="./update">
<input type="hidden" name="examPaperID" value="${(examPaper.examPaperID)!}">
</#if>


${(examPaper.exercise.title)!}<br>
${(examPaper.exercise.statement)!}<br>
${(examPaper.exercise.example)!}<br>
<@i18n code="label.title" suffix="colon"/><input type="text" name="title" value="${(examPaper.codeContent)!}"><br>

<input type="hidden" name="${Request['_csrf'].parameterName}" value="${Request['_csrf'].token}"/>
${webToken(true)}
<br>
<input type="submit" value="<@i18n code="submit"/>"/> <input type="button" value="<@i18n code="back"/>" onclick="window.open('./findList','_self')"/>

</form>

</body>
</html>