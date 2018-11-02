<%@page import="model.bean.PhongBan"%>
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
		ArrayList<PhongBan> items = (ArrayList<PhongBan>) request.getAttribute("items");
		int start = 0;
		int totals = 8;
		if (request.getAttribute("totals") != null)
			totals = Integer.parseInt((String) request.getAttribute("totals"));

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
				<strong>Xem phòng ban</strong>
			</div>
			<div class="panel-body">
				<form method="get" action="" class="form">
					<div class="col-xs-3">
						<div class="form-group">
							<select name="searchType" class="form-control">
								<option value="staff">Phòng ban</option>
							</select>
						</div>
					</div>
					<div class="col-xs-9">
						<div class="input-group">
							<input type="text" name="key" value="<%=key%>"
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
						<div class="table-responsive">
							<table id="mytable" class="table table-bordred table-striped">
								<thead>
									<th  class="text-center" colspan="1">Mã phòng ban</th>
									<th class="text-center" colspan="2">Tên phòng ban</th>
									<th class="text-center" colspan="1">Ngày tạo</th>
									<th class="text-center" colspan="1">Sửa</th>
									<th class="text-center" colspan="1">Xóa</th>
								</thead>
								<tbody>
									<%
										for (PhongBan item : items) {
									%>
									<tr>
										<td colspan="1"><%=item.getMa()%></td>
										<td colspan="2" ><%=item.getTen()%></td>
										<td colspan="1"><%=item.getNgaytao()%></td>
										<td colspan="1"><p data-placement="top" data-toggle="tooltip"
												title="Edit">
												<button class="btn btn-primary btn-xs" data-title="Edit"
													data-toggle="modal" data-target="#edit">
													<span class="fa fa-pencil"></span>
												</button>
											</p></td>
										<td colspan="1"><p data-placement="top" data-toggle="tooltip"
												title="Delete">
												<button class="btn btn-danger btn-xs" data-title="Delete"
													data-toggle="modal" data-target="#delete">
													<span class="fa fa-trash"></span>
												</button>
											</p></td>
									</tr>
									<%
										}
									%>							
								</tbody>
							</table>
							<div class="clearfix"></div>
							<div class="box-footer clearfix">
								<div class="pagination-sm no-margin pull-right">
									<ul class="pagination">										
										<li><a href="PhongBanServlet?key=<%=key%>&page=0">«</a></li>
										<li><a href="PhongBanServlet?key=<%=key%>&page=<%=start-1%>"><%=start - 1%></a></li>
										<li class="active"><span><%=start%></span></li>
										<li><a href="PhongBanServlet?key=<%=key%>&page=<%=start+1%>"><%=start + 1%></a></li>
										<li><a href="PhongBanServlet?key=<%=key%>&page=<%=totals/5 %>">»</a></li>										
									</ul>
								</div>
							</div>
						</div>
					</div>
					<h1 class="pull-left">
						<a class="btn btn-primary pull-right"
							style="margin-top: -10px; margin-bottom: 5px"
							href="addphongban.php">Add New</a>
					</h1>
				</div>
				</section>
			</div>
		</div>
		<!-- advertisement -->
		<div class="modal fade" id="edit" tabindex="-1" role="dialog"
			aria-labelledby="edit" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							<span class="fa fa-remove" aria-hidden="true"></span>
						</button>
						<h4 class="modal-title custom_align" id="Heading">Edit Your
							Detail</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<input class="form-control " type="text" placeholder="Mohsin">
						</div>
						<div class="form-group">

							<input class="form-control " type="text" placeholder="Irshad">
						</div>
						<div class="form-group">
							<textarea rows="2" class="form-control"
								placeholder="CB 106/107 Street # 11 Wah Cantt Islamabad Pakistan"></textarea>
						</div>
					</div>
					<div class="modal-footer ">
						<button type="button" class="btn btn-warning btn-lg"
							style="width: 100%;">
							<span class="fa fa-check"></span> Update
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<div class="modal fade" id="delete" tabindex="-1" role="dialog"
			aria-labelledby="edit" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							<span class="fa fa-remove" aria-hidden="true"></span>
						</button>
						<h4 class="modal-title custom_align" id="Heading">Delete this
							</h4>
					</div>
					<div class="modal-body">
						<div class="alert alert-danger">
							<span class="fa fa-warning"></span> Are you
							sure you want to delete this Record?
						</div>
					</div>
					<div class="modal-footer ">
						<button type="button" class="btn btn-success">
							<span class="fa fa-check"></span> Yes
						</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="fa fa-remove"></span> No
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
		</div>
		<!-- /.modal-dialog -->
		<!-- end advertisement -->
	</div>
	<!-- end container -->
	<!-- footer -->
	<%-- <jsp:include page="inc/footer.jsp"></jsp:include> --%>
	<!-- end footer -->
</body>
</html>