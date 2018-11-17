<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC Project</title>
<link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="assets/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="assets/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="container-fluid">
		<div class="row header">
			<div class="col-md-2 col-sm-3 col-xs-3" style="padding-left: 0px;">
                <img src="assets/images/anh1.jpg" class="img-responsive">
            </div>
            <div class="col-md-8 col-sm-6 col-xs-6 content_header"> 
            	<div style="text-align: center;"><h3>LẬP TRÌNH MẠNG - LẬP TRÌNH JSP/SERVLET</h3>  </div>             
                <div>
                	<marquee><span style="font-size: 35px; font-style: bold; font-family: Times ; color: red;">
                	Chào mừng các bạn đến với website của tui!
					</span></marquee> 
                </div>              
            </div> 
            <div class="col-md-2 col-sm-3 col-xs-3" style="padding-right: 0px;">
                <img src="assets/images/coffee_book.jpeg" class="img-responsive">
            </div>
		</div>
		<div class="row menu">
		  <ul>
            <li><a href="login.jsp">Đăng nhập</a></li>
            <li><a  href="register.jsp">Đăng kí</a></li>
          </ul>
		</div>
		<div class="row content">
			<div class="col-md-6 col-md-push-3">
			  <div>
				<form method="post" action="checklogin.jsp" name="f1" onsubmit="return checkLogin();">						
				   <h3 align="center">ĐĂNG NHẬP HỆ THỐNG QUẢN LÝ</h3>
				   <table border="0px" width= "50%" style="margin-left: 25%" >
					 <tr>
						<th>Tài khoản: </th>
						<th><input type="text" name="userName" class="input_form"/><th>
					 </tr>
					 <tr>
						<th>Mật khẩu: </th>
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
		<div class="row footer">
			<div class="col-md-12 copyright">
			© Copyright 2018 GDT - by TranHuuTrung
			</div>
		</div>
	</div>
	<script src="assets/js/jquery.min.js" ></script>
	<script src="assets/js/bootstrap.min.js" ></script>
	<script src="assets/js/myjs.js" ></script>
</body>
</html>