<%@ page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản Lý Phòng Ban</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="css/setbottom.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<!-- Header -->
	<jsp:include page="inc/header.jsp"></jsp:include>
	<!-- end header -->
	<!-- Container -->
	<div class="container " style="margin-top: 6px;">

		<div class="container" style="width: 500px; margin: 5% auto; border: 1px solid #c7b198">
			<h2 style="text-align: center; color: #219897; margin-bottom: 15px">Register</h2>
			<form class="form-horizontal" id = "register-form" action="UserServlet" method="post">
				<input type="hidden" name="command" value="register">
				<div class="form-group">
					<label class="control-label col-sm-3 " style="text-align: left" for="email">Email:</label>
					<div class="col-sm-9">
						<input type="email" class="form-control" id="email"
							placeholder="Enter email" name="email">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" style="text-align: left" for="pwd">Password:</label>
					<div class="col-sm-9">
						<input type="password" class="form-control" id="pwd"
							placeholder="Enter password" name="password">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" style="text-align: left" for="pwd">Re-password:</label>
					<div class="col-sm-9">
						<input type="password" class="form-control" id="pwd"
							placeholder="Enter password" name="password2">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-9">
						<div style="color: #3b2c85"><span id = "message"></span></div>
					</div>
					<div class="col-sm-offset-3 col-sm-9">
						<button type="submit" class="btn btn-success">Submit</button>
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
	<jsp:include page="inc/footer.jsp"></jsp:include> 
	<!-- end footer -->
</body>
</html>