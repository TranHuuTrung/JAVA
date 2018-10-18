<%@page import="java.awt.event.ItemEvent"%>
<%@page import="model.bean.NhanVien"%>
<%@ page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Quản Lý Phòng Ban</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


</head>
<body>
	<%
		if (request.getAttribute("item") == null) {
			response.sendRedirect("home.jsp");
			return;
		} 
		NhanVien item =(NhanVien) request.getAttribute("item");
			
		
	%>

	<!-- Header -->
	<jsp:include page="inc/header.jsp"></jsp:include>
	<!-- end header -->
	<!-- Container -->
	<div class="container " style="margin-top: 6px;">

		<div class="container">
			<h2>Edit Nhan Vien</h2>

			<form class="form-horizontal" id="register-form" action="UserServlet"
				method="post">
				<input type="hidden" name="command" value="register">
				<div class="form-group">
					<label class="control-label col-sm-2" for="email">Mã:</label>
					<div class="col-sm-10">
						<input type="email" class="form-control" id="email"
							placeholder="Enter email" name="email" value="<%=item.getMa() %>" disabled>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="pwd">Chức vụ:</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="chucvu"
							placeholder="Enter password" name="chucvu">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="pwd">Phòng ban:</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="pwd"
							placeholder="Enter password" name="password2">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="email">Mật khẩu:</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="pwd"
							placeholder="Mật khẩu" name="password" value="<%=item.getMatKhau()%>" >
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="pwd">Tên nhân viên:</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="tennhanvien"
							placeholder="Tên nhân viên" name="tennhanvien" value="<%=item.getTen()%>" >
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="pwd">Số điện thoại:</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="sdt"
							placeholder="Số điện thoại" name="sdt" value="<%=item.getSdt()%>" >
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="pwd">Địa chỉ:</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="diachi"
							placeholder="Địa chỉ" name="diachi" value="<%=item.getDiaChi()%>" >
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default">Submit</button>
						<span id="message"></span>
					</div>
				</div>

			</form>
			<script type="text/javascript" src="js/register.js"></script>
		</div>
		<!-- advertisement -->

		<!-- end advertisement -->
	</div>
	<!-- end container -->
	<!-- footer -->
	<%-- <jsp:include page="inc/footer.jsp"></jsp:include> --%>
	<!-- end footer -->
</body>
</html>