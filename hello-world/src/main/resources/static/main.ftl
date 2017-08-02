<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<@res tag="script" src="/sockjs.js"/>
<@res tag="script" src="/webjars/jquery/3.2.1/jquery.min.js"/>
<body>
	<h2>登录成功</h2><p>
	<@authorize >
		欢迎，${userToken.userName}<p>
	</@authorize>
	<a href="./user/listUser">用户管理</a><br>
	<a href="./role/listRole">角色管理</a><br>
	<hr>
<br>
<form method="post"  enctype="multipart/form-data" action="./file/uploadFile">
	<input type="file" name="testFile" />
	<input type="file" name="testFile2" />
	<button type="submit" >上传</button>
	${csrfToken(true)}
</form>
</body>
</html>