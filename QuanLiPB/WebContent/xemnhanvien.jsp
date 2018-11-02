<%@page import="model.bean.NhanVien"%>
<%@page import="java.util.ArrayList"%>
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
		//check login
		User user = (User) session.getAttribute("user");
		if (user == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		// set table
		ArrayList<NhanVien> nhanviens = (ArrayList<NhanVien>) request.getAttribute("nhanviens");
		int start = 0;
		int totals = 8;
		if (request.getAttribute("totals") != null)
			totals = Integer.parseInt((String)request.getAttribute("totals"));
		
		try {
			start = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		// check search
		String key = request.getParameter("key") != null ? request.getParameter("key") : "";
	%>
	<!-- Header -->
	<jsp:include page="inc/header.jsp"></jsp:include>
	<!-- end header -->
	<!-- Container -->
	<div class="container text-center" style="margin-top: 6px;">
		<!-- Container -->
		<div class="panel panel-info">
			<div class="panel-heading">
				<strong>Xem Nhân Viên</strong>

			</div>

			<div class="panel-body">
				<form method="get" action="" class="form">
					<div class="col-xs-3">
						<div class="form-group">
							<select name="searchType" class="form-control">
								<option value="staff">Nhân viên</option>
							</select>
						</div>
					</div>
					<div class="col-xs-9">
						<div class="input-group">
							<input type="text" name="key" value="<%=key %>"
								class="form-control" value="<%=key%>>"> <span
								class="input-group-btn"> <input type="submit"
								value="Tìm kiếm" class="btn btn-success">
							</span>
						</div>
					</div>
				</form>
				<section class="content">
				<div class="clearfix"></div>
				<div class="box box-primary">
					<div class="box-body table-responsive">
						<table class="table table-responsive table-bordered"
							id="tours-table">
							<thead>
								<tr>
									<th class="text-center" colspan="1">Mã</th>
									<th class="text-center" colspan="2">Tên Nhân Viên</th>
									<th class="text-center" colspan="1">Mã Phòng</th>
									<th class="text-center" colspan="1">SDT</th>
									<th class="text-center" colspan="2">Địa Chỉ</th>
									<th class="text-center" colspan="1">Hành Động</th>
								</tr>
							</thead>
							<tbody>
								<%
									for (NhanVien item : nhanviens) {
								%>
								<tr>
									<td class="text-center" colspan="1"><%=item.getMa()%></td>
									<td class="text-center" colspan="2"><%=item.getTen()%></td>
									<td class="text-center" colspan="1"><%=item.getPhongBan()%></td>
									<td class="text-center" colspan="1"><%=item.getSdt()%></td>
									<td class="text-center" colspan="2"><%=item.getDiaChi()%></td>
									<td class="text-center" colspan="1">
										<div class="btn-group">
											<a href="detail.php?id=<?php echo $arrayPhong['id'] ?>"
												class="btn btn-default btn-xs"><i
												class="fa fa-eye"></i></a> <a
												href="EditServlet?type=NhanVien&id=<%=item.getMa() %>"
												class="btn btn-default btn-xs"><i
												class="fa fa-edit"></i></a> <a
												href="delete.php?id=<?php echo $arrayPhong['id']; ?>"
												class="btn btn-danger btn-xs"
												onclick="return confirm('Are you sure?')"><i
												class="fa fa-trash"></i></a>
										</div>

									</td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>
					<h1 class="pull-left">
						<a class="btn btn-primary pull-right"
							style="margin-top: -10px; margin-bottom: 5px"
							href="addphongban.php">Add New</a>
					</h1>
					<div class="box-footer clearfix">
						<div class="pagination-sm no-margin pull-right">
							<ul class="pagination">
								<%
									//chi co 1 trang
									if (totals <= 5 && start == 0) {
								%>
								<li class="active"><span>1</span></li>
								<%
									} else if (start * 5 >= totals - 5) {
										//trang cuoi cung
								%>
								<li><a href="NhanVienServlet?key=<%=key%>&page=0">«</a></li>
								<li><a
									href="NhanVienServlet?key=<%=key%>&page=<%=start-1%>"><%=start-1%></a></li>
								<li class="active"><span><%=start%></span></li>
								<%
									} else if (start == 0 && totals >= 5) {
										//la trang dau tien
								%>
								<li class="active"><span><%=start %></span></li>
								<li><a
									href="NhanVienServlet?key=<%=key%>&page=<%=start + 1%>"><%=start + 1%></a></li>
								<li><a
									href="NhanVienServlet?key=<%=key%>&page=<%=(totals)/5 -1%>">»</a></li>
								<%
									} else if (start >= 0 && totals >= 5) {
										// trang o giua
								%>
								<li><a href="NhanVienServlet?key=<%=key%>&page=0>">«</a></li>
								<li><a href="http://danang2.local/admin/tours?page=2"><%=start- 1%></a></li>
								<li class="active"><span><%=start %></span></li>
								<li><a href="NhanVienServlet?key=<%=key%>&page=0>"><%=start + 1%></a></li>
								<li><a href="NhanVienServlet?key=<%=key%>&page=0>">»</a></li>
								<%
									}
								%>

								<!-- 
								<li class="disabled"><span>«</span></li>
								<li class="active"><span>1</span></li>
								<li><a href="http://danang2.local/admin/tours?page=2">2</a></li>
								<li><a href="http://danang2.local/admin/tours?page=3">3</a></li>
								<li><a href="http://danang2.local/admin/tours?page=2"
									rel="next">»</a></li> -->
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