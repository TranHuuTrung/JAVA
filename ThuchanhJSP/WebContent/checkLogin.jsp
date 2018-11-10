<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CheckLogin</title>
</head>
<body>
	<%
		String userName = request.getParameter("userName");
	    String password = request.getParameter("password");
	    String address = "193 NLB";
	    if("ChiPheo".equals(userName) && !"".equals(password)){
	    	request.setAttribute("address", address);
	    	RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
	    	rd.forward(request, response);
	    } else {
	    	response.sendRedirect("login.jsp");
	    }
	%>
</body>
</html>