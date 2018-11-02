<%@ page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản Lý Phòng Ban</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css"></link>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>


</head>
<body>
	<%
		User user = (User) session.getAttribute("user");
		if (user == null) {
		response.sendRedirect("login.jsp");
		return;
	}
		String message = (request.getAttribute("message") != null) ? (String) request.getAttribute("message") : "";
	%>

	<!-- Header -->
	<jsp:include page="inc/header.jsp"></jsp:include>
	<!-- end header -->
	<!-- Container -->
	<div class="container " style="margin-top: 6px;">

		<div class="container">
			<h3>
				Chào
				<%=user.getEmail()%></h3>
			<hr>
			<h4>Đổi mật khẩu</h4>
			<form class="form form-horizontal" role="form" action="UserServlet"
				method="post">
				<input type="hidden" name="command" value="changePassword">
				<div class="form-group">
					<label class="control-label col-xs-4">Mật khẩu cũ</label>
					<div class="col-xs-6">
						<input name="oldPassword" type="Password" class="form-control"
							placeholder="Enter password">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-4">Mật khẩu mới</label>
					<div class="col-xs-6">
						<input name="newPassword" type="password" class="form-control"
							placeholder="Enter new password">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-4">Xác nhận mật khẩu</label>
					<div class="col-xs-6">
						<input name="newPassword2" type="password" class="form-control"
							placeholder="Enter  Re-new password">
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-offset-4 col-xs-4">
						 <span id="message" class="label label-danger"><%=message%></span>
					</div>
					<div class="col-xs-offset-4 col-xs-4">
						<input name="" type="submit" class="btn btn-success btn-block"
							value="Đổi mật khẩu">
					</div>
				</div>
			</form>
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