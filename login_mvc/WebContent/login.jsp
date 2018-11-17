<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login forward MVC</title>
</head>
<body>
 	<% Object loi = request.getAttribute("loi"); %>
 	<%= loi %>
	<form action="CheckLoginServlet" method="post">
		<h2>Login</h2>
		<table>
			<tr>
				<th>userName</th>
				<td><input type="text" name="userName" /></td>
			</tr>
			<tr>
				<th>Password</th>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<th></th>
				<td><button>Login</button></td>
			</tr>
		</table>
	</form>
</body>
</html>