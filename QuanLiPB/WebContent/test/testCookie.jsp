<%@ page import="model.bean.User"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String cookieName = "username";
	Cookie cookies[] = request.getCookies();
	Cookie myCookie = null;
	if (cookies != null) {
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals(cookieName)) {
				myCookie = cookies[i];
				break;
			}
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


	<%
		if (myCookie == null) {
	%>
	No Cookie found with the name
	<%=cookieName%>
	<%
		} else {
	%>
	<p>
		Welcome:
		<%=myCookie.getValue()%>.
		<%
		}
	%>
	
</body>
</body>
</html>