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
	<tr><td> <input id="remember-me" type="checkbox" name="remember-me" value="true"><label for="remember-me">记住我</label></td></tr>
	<tr><td colspan='2'><button type="submit" ><@i18n code="login"/></button></td></tr>
</table>
${csrfToken(true)}
</form>
</body>
</html>