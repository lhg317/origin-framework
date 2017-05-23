<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<#if (!user??) || (user.userID=="") >
<form method="post" action="./addUser">
</#if>
<#if user?? && user.userID!="">
<form method="post" action="./updateUser">
<input type="hidden" name="userID" value="${(user.userID)!}">
</#if>

<@i18n code="label.userName" suffix="colon"/><input type="text" name="userName" value="${(user.userName)!}">
<@i18n code="label.loginName" suffix="colon"/><input type="text" name="loginName" value="${(user.loginName)!}">
<@i18n code="label.password" suffix="colon"/><input type="password" name="password" value="${(user.passwrd)!}">
<@i18n code="label.email" suffix="colon"/><input type="text" name="email" value="${(user.email)!}">
${csrfToken(true)}
${webToken(true)}
<br>
<input type="submit" value="<@i18n code="submit"/>"/> <input type="button" value="<@i18n code="back"/>" onclick="window.open('./listUser','_self')"/>

</form>

</body>
</html>