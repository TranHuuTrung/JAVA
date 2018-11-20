<%@page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	User user = (User) session.getAttribute("user");
    String userName ="";
	if (user == null) {
		response.sendRedirect("login.jsp");
	}else {
		userName = user.getUserName();
	}
%>
<jsp:include page="header.jsp" flush="true"></jsp:include>
<div class="row menu">
  <ul>
     <li><a href="profile.jsp"><span class="fa fa-user"></span>&nbsp;Chào <%= userName %>!</a></li>
     <li><a href="logout.jsp"><span class="fa fa-sign-out"></span>&nbsp;Đăng xuất</a></li>
   </ul>
</div>
<div class="row content">
	<div class="col-md-2 col-sm-3 col-xs-3 borderLeft" style="text-align: center;">
        <jsp:include page="sidebar.jsp"></jsp:include>                
    </div>
	<div class="col-md-10 col-sm-9 col-xs-9 borderCenter">
      <h3>Chào Trung!</h3>
	  <hr>
      <h4 style="text-align: center; font-weight: bold;">Thay đổi mật khẩu</h4>
	  <form class="form form-horizontal" role="form" action="UserServlet" method="post">
		<table border="0px" width= "60%" style="margin-left: 20%" >
			<tr>
				<th>Mật khẩu cũ <span style="color: red; margin-left: 5px;">*</span></th>
				<th><input type="text" name="userName" class="input_form"/><th>
			</tr>
			<tr>
				<th>Mật khẩu mới<span style="color: red; margin-left: 5px;">*</span></th>
				<th><input type="password" name="passWord" class="input_form"/></th>
			</tr>
			<tr>
				<th>Xác nhận mật khẩu<span style="color: red; margin-left: 5px;">*</span></th>
				<th><input type="password" name="repassWord" class="input_form"/></th>
			</tr>
			<tr>
				<th></th>
				<th>
					<input type="submit" name="doiMatKhau" value="Đổi mật khẩu" class="input_form"/>
					<input type="reset" name="nhapLai" value="Nhập lại"/>
				</th>
			</tr>
		  </table>
		</form>         	   
    </div>
</div>
<jsp:include page="footer.jsp" flush="true"></jsp:include>
	