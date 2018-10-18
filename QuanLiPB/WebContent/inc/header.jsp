<%@page import ="model.bean.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/header.css">
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="About"><img src="img/profile.png"
				alt="" style="width: 25px" class="img-circle"></a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li><a href="Home">Home</a></li>
				<li><a href="About">About</a></li>
				<li><a href="#">Projects</a></li>
				<li><a href="#">Contact</a></li>
				<%
					User user = (User) session.getAttribute("user");
					if (user == null) {} else {
				%>	
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Chức năng<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="NhanVienServlet?page=0">Xem Nhân Viên</a></li>
						<li><a href="PhongBanServlet?page=0">Xem Phòng Ban</a></li>
						<li><a href="#p">Tìm Kiếm</a></li>
					</ul></li>
				<%
					}
				%>
				
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<%
					if (user == null) {						
				%>
				<li><a href="register.jsp" style="width: auto;"><span
						class="glyphicon glyphicon-log-in"></span> Register</a></li>
				<li><a href="login.jsp" style="width: auto;"><span
						class="glyphicon glyphicon-log-in"></span> Login</a></li>
				<%
					} else {
				%>
				<li><a href="profile.jsp" style="width: auto;"> <%=user.getEmail()%></a></li>
				<li><a href="logout.jsp" style="width: auto;"><span
						class="glyphicon glyphicon-log-in"></span> Logout</a></li>
				<%
					}
				%>
			</ul>
		</div>
	</div>
</nav>


