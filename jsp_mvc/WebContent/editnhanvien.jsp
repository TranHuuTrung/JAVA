<%@page import="model.bean.PhongBan"%>
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
	
	ArrayList<PhongBan> listPhongBan = (ArrayList<PhongBan>) request.getAttribute("listPhongBan");
	NhanVien nhanvien = (NhanVien) request.getAttribute("nhanVien");
	
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
       <h2>Chỉnh sửa nhân viên</h2>
       <div class="widget">
       <a href="NhanVienServlet"><i class="fa fa-arrow-left"></i>&nbsp; Trở lại danh sách</a>
       <form class="form form-horizontal" role="form" action="EditNhanVienServlet" method="post" id="ThemNhanVienForm">
       <div style="text-align: center; color: red;">
		  	<span id="thongbao"></span>
		  	<br/>
		  	<%
		  		String loi = (request.getAttribute("loi") != null) ? (String) request.getAttribute("loi") : ""; 
		  		String editnhanvienthanhcong = (request.getAttribute("editnhanvienthanhcong") != null) ? (String) request.getAttribute("editnhanvienthanhcong") : "";
		  	%>
		  	<%= loi %>
		  	<span style="color: green"><%= editnhanvienthanhcong %></span>
		  </div>
		  <input type="text" name="idNV" hidden class="input_form" value="<%=nhanvien.getMaNV()%>"/>
		<table border="0px" width= "60%" style="margin-left: 20%" >
			<tr>
				<th>Tên nhân viên <span style="color: red; margin-left: 5px;">*</span></th>
				<th><input type="text" name="TenNV" class="input_form" value="<%=nhanvien.getTenNV()%>"/><th>
			</tr>
			<tr>
				<th>Phòng ban<span style="color: red; margin-left: 5px;">*</span></th>
				<th>
					<select class="selectPB" name="IdPB">
					<%
						for(PhongBan item:listPhongBan){
					%>
  						<option value="<%= item.getIdPB()%>" 
  							<%
  								if(item.getIdPB() == nhanvien.getIdPhongBan()) {
  							%>
  							selected
  							<% 
  								}
  							%>
  						><%=item.getTenPB() %></option>
  					<%} %>
					</select>
				</th>
			</tr>
			<tr>
				<th>Chức vụ<span style="color: red; margin-left: 5px;">*</span></th>
				<th><input type="text" name="ChucVu" class="input_form" value="<%=nhanvien.getChucVu()%>"/></th>
			</tr>
			<tr>
				<th>Địa chỉ<span style="color: red; margin-left: 5px;">*</span></th>
				<th><input type="text" name="DiaChi" class="input_form" value="<%=nhanvien.getDiaChi()%>"/></th>
			</tr>
			<tr>
				<th>Số điện thoại<span style="color: red; margin-left: 5px;">*</span></th>
				<th><input type="text" name="SDT" class="input_form" value="<%=nhanvien.getSDT()%>"/></th>
			</tr>
			<tr>
				<th></th>
				<th>
					<input type="reset" name="nhapLai" value="Nhập lại" class="input_form"/>
					<input type="submit" name="themNV" value="Chỉnh sửa" />
				</th>
			</tr>
		  </table>
		</form> 
       </div>  
    </div>
</div>
<jsp:include page="footer.jsp" flush="true"></jsp:include>
	