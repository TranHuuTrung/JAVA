<%@page import="model.bean.NhanVien"%>
<%@page import="java.util.ArrayList"%>
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
	
	ArrayList<NhanVien> nhanvienList = (ArrayList<NhanVien>) request.getAttribute("nhanvienList");
	
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
       <h2>Xem nhân viên</h2>
       <div class="widget">
       	  <a type="button" class="btn btn-success btn-sm" href="ThemNhanVienServlet"><i class="fa fa-plus"></i>&nbsp; Thêm mới</a>
       	   <div style="text-align: center; color: red;">
		  	<span id="thongbao"></span>
		  	<br/>
		  	<%
		  		String loixoanhanvien = (request.getAttribute("loixoanhanvien") != null) ? (String) request.getAttribute("loixoanhanvien") : ""; 
		  		String xoanhanvienthanhcong = (request.getAttribute("xoanhanvienthanhcong") != null) ? (String) request.getAttribute("xoanhanvienthanhcong") : "";
		  	%>
		  	<%= loixoanhanvien %>
		  	<span style="color: green"><%= xoanhanvienthanhcong %></span>
		  </div>
       	  <table class="table table-responsive table-bordered" id="nhanvien_table" style="margin-top: 10px;">
			<thead>
			   <tr>
				 <th class="text-center" colspan="1">Mã Nhân Viên</th>
				 <th class="text-center" colspan="2">Tên Nhân Viên</th>
				 <th class="text-center" colspan="1">Chức vụ</th>
				 <th class="text-center" colspan="1">Phòng ban</th>
				 <th class="text-center" colspan="1">SĐT</th>
				 <th class="text-center" colspan="2">Địa Chỉ</th>
				 <th class="text-center" colspan="1">Hành Động</th>
				</tr>
			</thead>
			<tbody>
			<% 
				for(NhanVien item: nhanvienList){
			%>
				<tr>
					<td class="text-center" colspan="1" id="maNV"><%=item.getMaNV() %></td>
					<td class="text-center" colspan="2" id="tenNV"><%=item.getTenNV() %></td>
					<td class="text-center" colspan="1"><%=item.getChucVu() %></td>
					<td class="text-center" colspan="1"><%=item.getTenPB() %></td>
					<td class="text-center" colspan="1"><%=item.getSDT()%></td>
					<td class="text-center" colspan="2"><%=item.getDiaChi() %></td>
					<td class="text-center" colspan="1">
						<div class="btn-group" id="HanhDongNhanVien">
							<a href="EditNhanVienServlet?id=<%=item.getMaNV() %>" class="btn btn-warning btn-xs"><i class="fa fa-edit"></i></a> 
							<button class="btn btn-primary btn-xs" onclick="myDetailsFunction(<%=item.getMaNV()%>, '<%=item.getTenNV()%>', '<%=item.getChucVu()%>','<%=item.getTenPB()%>','<%=item.getSDT()%>','<%=item.getDiaChi()%>')"><i class="fa fa-eye"></i></button>
							<button class="btn btn-danger btn-xs" onclick="myFunction(<%=item.getMaNV()%>, '<%=item.getTenNV()%>')"><i class="fa fa-trash"></i></button>
						</div>
					</td>
				 </tr>
			<% } %>
			</tbody>
		 </table>     
       </div>    	   
    </div>
    <!-- Modal xoa-->
	<div class="modal fade" id="modalDelete" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  		<div class="modal-dialog" role="document">
   		  <div class="modal-content">
      		<div class="modal-header" style="display: flex;">
        		<h5 class="modal-title" id="exampleModalLabel" style="font-size: 20px;"><i style="color: red; margin-right: 5px; font-size: 30px" class="fa fa-question"></i> Xác nhận Xóa</h5>
     		</div>
      	   <div class="modal-body">
      			Bạn có chắc chắn muốn xóa nhân viên <span style="color: red;" id="TenNhanVienXoa"></span> không?
      	   </div>
      	   <div class="modal-footer">
        		<button type="button" class="btn btn-secondary" data-dismiss="modal">Thoát</button>
        		<a id="abc" href="#" class="btn btn-danger">Xóa</a>
      	  </div>
   		</div>
  	  </div>
	</div>
	<!-- Modal xem chi tiet-->
	<div class="modal fade" id="modalDetails" tabindex="-1" role="dialog" aria-labelledby="DetailsModalLabel" aria-hidden="true">
  		<div class="modal-dialog" role="document">
   		  <div class="modal-content">
      		<div class="modal-header" style="display: flex;">
        		<h5 class="modal-title" id="DetailsModalLabel" style="font-size: 20px;">
        			<i style="color: green; margin-right: 5px; font-size: 30px" class="fa fa-info"></i> 
        			Chi tiết nhân viên <i style="font-size: 15px;" class="detailTenNV"></i>
        		</h5> 
     		</div>
      	   <div class="modal-body">
      			<ul>
  					<li>Họ và tên Nhân Viên: <span class="detailTenNV" style="font-weight: bold;"></span></li>
  					<li>Phòng ban: <span class="detailTenPBNV" style="font-weight: bold;"></span></li>
  					<li>Chức vụ: <span class="detailChucVuNV" style="font-weight: bold;"></span></li>
  					<li>Số điện thoại: <span class="detailSDTNV" style="font-weight: bold;"></span></li>
  					<li>Địa chỉ: <span class="detailDiaChiNV" style="font-weight: bold;"></span></li>
				</ul>
      	   </div>
      	   <div class="modal-footer">
        		<button type="button" class="btn btn-primary" data-dismiss="modal">Thoát</button>
      	  </div>
   		</div>
  	  </div>
	</div>
</div>
<script>
function myFunction(id, TenNV) {
	$("#TenNhanVienXoa").text(TenNV);
	var url = "DeleteNhanVienServlet?id="+id;
	document.getElementById("abc").href=url; 
	$('#modalDelete').modal('show');
}

function myDetailsFunction(id, TenNV, ChucVu, PhongBan, SDT, DiaChi){
	$(".detailTenNV").text(TenNV);
	$(".detailTenPBNV").text(PhongBan);
	$(".detailChucVuNV").text(ChucVu);
	$(".detailSDTNV").text(SDT);
	$(".detailDiaChiNV").text(DiaChi);
	$('#modalDetails').modal('show');
}
</script>
<jsp:include page="footer.jsp" flush="true"></jsp:include>
	