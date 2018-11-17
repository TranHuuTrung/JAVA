<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String userName = request.getParameter("userName");
		String address = (String) request.getAttribute("address");
		String temp = (String) session.getAttribute("temp");
		
	%>
	Welcome <%=userName%>!<br/>
	You are living at <%=address%><br/>
	Session temp <%=temp%><br/>
	this is blog <%= config.getInitParameter("blog") %>
</body>
</html>