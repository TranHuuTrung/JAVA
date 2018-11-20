<%@page import="model.bean.PhongBan"%>
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
	
	ArrayList<PhongBan> phongbanList = (ArrayList<PhongBan>) request.getAttribute("phongbanList");
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
       <h2>Xem phòng ban</h2> 
        <div class="widget">
       	  <a type="button" class="btn btn-success btn-sm" href="ThemPhongBanServlet"><i class="fa fa-plus"></i>&nbsp; Thêm mới</a>
       	  <div style="text-align: center; color: red;">
		  	<span id="thongbao"></span>
		  	<br/>
		  	<%
		  		String loixoaphongban = (request.getAttribute("loixoaphongban") != null) ? (String) request.getAttribute("loixoaphongban") : ""; 
		  		String xoaphongbanthanhcong = (request.getAttribute("xoaphongbanthanhcong") != null) ? (String) request.getAttribute("xoaphongbanthanhcong") : "";
		  	%>
		  	<%= loixoaphongban %>
		  	<span style="color: green"><%= xoaphongbanthanhcong %></span>
		  </div>
       	  <table class="table table-responsive table-bordered" id="nhanvien_table" style="margin-top: 10px;">
			<thead>
			   <tr>
				 <th class="text-center" colspan="1">Mã Phòng</th>
				 <th class="text-center" colspan="2">Tên phòng ban</th>
				 <th class="text-center" colspan="1">Hành Động</th>
				</tr>
			</thead>
			<tbody>
			<%
				for(PhongBan item:phongbanList){
			%>
				<tr>
					<td class="text-center" colspan="1"><%=item.getIdPB() %></td>
					<td class="text-center" colspan="2"><%=item.getTenPB() %></td>
					<td class="text-center" colspan="1">
						<div class="btn-group" id="HanhDongNhanVien">
							<a href="EditPhongBanServlet?id=<%=item.getIdPB() %>" class="btn btn-warning btn-xs"><i class="fa fa-edit"></i></a> 
							<button class="btn btn-danger btn-xs" onclick="myDeletePBFunction(<%=item.getIdPB()%>, '<%=item.getTenPB()%>')"><i class="fa fa-trash"></i></button>
						</div>
					</td>
				 </tr>
				<%} %>
			</tbody>
		 </table>     
       </div>           	   
    </div>
     <!-- Modal xoa-->
	<div class="modal fade" id="modalPBDelete" tabindex="-1" role="dialog" aria-labelledby="examplePBModalLabel" aria-hidden="true">
  		<div class="modal-dialog" role="document">
   		  <div class="modal-content">
      		<div class="modal-header" style="display: flex;">
        		<h5 class="modal-title" id="examplePBModalLabel" style="font-size: 20px;"><i style="color: red; margin-right: 5px; font-size: 30px" class="fa fa-question"></i> Xác nhận Xóa</h5>
     		</div>
      	   <div class="modal-body">
      			Bạn có chắc chắn muốn xóa nhân viên <span style="color: red;" id="TenPBXoa"></span> không?
      	   </div>
      	   <div class="modal-footer">
        		<button type="button" class="btn btn-secondary" data-dismiss="modal">Thoát</button>
        		<a id="xoaPBabc" href="#" class="btn btn-danger">Xóa</a>
      	  </div>
   		</div>
  	  </div>
	</div>
</div>
<script>
function myDeletePBFunction(id, TenPB) {
	$("#TenPBXoa").text(TenPB);
	var url = "DeletePhongBanServlet?id="+id;
	document.getElementById("xoaPBabc").href=url; 
	$('#modalPBDelete').modal('show');
}
</script>
<jsp:include page="footer.jsp" flush="true"></jsp:include>
	