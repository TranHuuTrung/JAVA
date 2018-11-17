<%@page import="Model.Bean.Wife"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome MVC</title>
</head>
<body>
	<table>
	<%
		ArrayList<Wife> wifeArray = (ArrayList<Wife>) request.getAttribute("wifeArray");
	 	for(int i =0; i < wifeArray.size(); i++){
	%>
  		<tr>
    		<td><%= wifeArray.get(i).getName() %></td>
    		<td><%= wifeArray.get(i).getAddress() %></td>
    		<td><%= wifeArray.get(i).isAlive() %></td>
  		</tr>
  		<% } %>
	</table>

</body>
</html>