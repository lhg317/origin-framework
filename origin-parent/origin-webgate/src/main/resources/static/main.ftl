<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	登录成功
<br>
<@security.authorize access="hasRole('ROLE_ADMIN')">
	1 ROLE_ADMIN
</@security.authorize>
<br>
<@security.authorize access="hasRole('ROLE_USER')">
	2 ROLE_USER
</@security.authorize>
<br>
<@security.authorize access="hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')">
	3 ROLE_USER AND ROLE_ADMIN
</@security.authorize>
<br>
<@security.authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
 	4 ROLE_USER OR ROLE_ADMIN
</@security.authorize>
<br>
<@security.authorize access="!hasAnyRole('ROLE_USER')">
 	5 ROLE_USER OR ROLE_ADMIN
</@security.authorize>
<br>
</body>
</html>