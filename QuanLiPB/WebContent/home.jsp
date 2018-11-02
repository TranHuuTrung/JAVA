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
		}
	%>
	<!-- Header -->
	<jsp:include page="inc/header.jsp"></jsp:include>
	<!-- end header -->
	<!-- Container -->
	<div class="container text-center" style="margin-top: 6px;">
		<!-- Container -->
		<div class="panel panel-info">
			<div class="panel-heading">
				<strong>Quảng cáo</strong>
				
			</div>
			<div class="panel-body">
			<form method="get" action="#" class="form">
					<div class="col-xs-3">
						<div class="form-group">
							<select name="searchType" class="form-control">
								<option value="staff">Nhân viên</option>
								<option value="dept" >Phòng ban</option>
							</select>
						</div>
					</div>
					<div class="col-xs-9">
						<div class="input-group">
							<input type="text" name="searchQuery" value="" class="form-control">
							<span class="input-group-btn"> <input type="submit"
								value="Tìm kiếm" class="btn btn-success">
							</span>
						</div>
					</div>
				</form>
			<section class="content">
						<div class="clearfix"></div>
						<div class="box box-primary">
							<div class="box-body table-responsive">
								<table class="table table-responsive table-bordered" id="tours-table">
									<thead>
										<tr>
											<th  class="text-center">ID</th>
											<th class="text-center">Tên phòng</th>
											<th class="text-center">Mô tả</th>
											<th class="text-center" colspan="3">Hành động</th>
										</tr>
									</thead>
									<tbody>
										
										<tr>
											<td>aaaaa</td>
											<td>bbbbbbb</td>
											<td>ccccccccc</td>
											<td class="text-center">
												
												<div class="btn-group">
													<a href="details.php?id=<?php echo $arrayPhong['id'] ?>" class="btn btn-default btn-xs"><i class="fa fa-eye"></i></a>
													<a href="edit.php?id=<?php echo $arrayPhong['id'];?>" class="btn btn-default btn-xs"><i class="fa fa-pencil"></i></a>
													<a href="delete.php?id=<?php echo $arrayPhong['id']; ?>" class="btn btn-danger btn-xs" onclick="return confirm('Are you sure?')"><i class="fa fa-trash"></i></a>
												</div>
												
											</td>
										</tr>
										
									</tbody>
								</table>
							</div>
							<div class="box-footer clearfix">
								<div class="pagination-sm no-margin pull-right">
									<ul class="pagination">
										<li class="disabled"><span>«</span></li>
										<li class="active"><span>1</span></li>
										<li><a href="#" rel="next">»</a></li>
									</ul>
								</div>
							</div>
						</div>
					</section>
			</div>
		</div>
		Chào mừng bạn đến với Quản Lý Phòng Ban
		<!-- advertisement -->

		<!-- end advertisement -->
	</div>
	<!-- end container -->
	<!-- footer -->
	<%-- <jsp:include page="inc/footer.jsp"></jsp:include> --%>
	<!-- end footer -->
</body>
</html>