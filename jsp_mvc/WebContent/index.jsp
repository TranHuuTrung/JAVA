<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	response.sendRedirect("login.jsp");
	%>
<jsp:include page="header.jsp" flush="true"></jsp:include>
<div class="row menu">
  <ul>
     <li><a href="login.jsp"><span class="fa fa-sign-in"></span>&nbsp;Đăng nhập</a></li>
     <li><a  href="register.jsp"><span class="fa fa-sign-in"></span>&nbsp;Đăng kí</a></li>
   </ul>
</div>
<div class="row content">
Chào mừng bạn đến với website jsp/servlet.
</div>
<jsp:include page="footer.jsp" flush="true"></jsp:include>
	