<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action='./login' method='POST'>
<table>
	<tr><td><@i18n code="label.loginName" suffix="colon"/></td><td><input type='text' name='username' value=''></td></tr>
	<tr><td><@i18n code="label.password" suffix="colon"/></td><td><input type='password' name='password'/></td></tr>
	<tr><td> <input type="checkbox" name="remember-me" value="true">记住我</td></tr>
	<tr><td colspan='2'><button type="submit" ><@i18n code="login"/></button></td></tr>
</table>
<input type="hidden" name="${Request['_csrf'].parameterName}" value="${Request['_csrf'].token}"/>
</form>
</body>
</html>