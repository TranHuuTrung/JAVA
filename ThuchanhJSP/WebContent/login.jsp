<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<%
		String temp = "temp";
		session.setAttribute("temp", temp);
	%>
	<form action="checkLogin.jsp" method="POST">
		Username: <input type="text" name="userName" >
		Password: <input type="password" name="password">
		<button type="submit">OK</button>
		<button type="reset">Reset</button>
	</form>
</body>
</html>