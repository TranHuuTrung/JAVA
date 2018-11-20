<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" flush="true"></jsp:include>
<div class="row menu">
  <ul>
     <li><a href="login.jsp"><span class="fa fa-sign-in"></span>&nbsp;Đăng nhập</a></li>
     <li><a  href="register.jsp"><span class="fa fa-sign-in"></span>&nbsp;Đăng kí</a></li>
   </ul>
</div>
<div class="row content">
<div class="col-md-6 col-md-push-3 content_login">
	<div>
		<form method="post" action="CheckLoginServlet" id="loginform">						
		  <h3 align="center">ĐĂNG NHẬP HỆ THỐNG QUẢN LÝ</h3>
		  <div style="text-align: center; color: red;">
		  	<span id="thongbao"></span>
		  	<br/>
		  	<%String loi = (request.getAttribute("loi") != null) ? (String) request.getAttribute("loi") : ""; %>
		  	<%= loi %>
		  </div>
		  <table border="0px" width= "50%" style="margin-left: 25%" >
			<tr>
				<th>Tài khoản<span style="color: red; margin-left: 5px;">*</span></th>
				<th><input type="text" name="userName" class="input_form"/><th>
			</tr>
			<tr>
				<th>Mật khẩu<span style="color: red; margin-left: 5px;">*</span></th>
				<th><input type="password" name="passWord" class="input_form"/></th>
			</tr>
			<tr>
				<th></th>
				<th>
					<input type="submit" name="dangNhap" value="Đăng nhập" class="input_form"/>
					<input type="reset" name="nhapLai" value="Nhập lại"/>
				</th>
			</tr>
		  </table>
		</form>
	</div>
</div>
</div>
<jsp:include page="footer.jsp" flush="true"></jsp:include>
