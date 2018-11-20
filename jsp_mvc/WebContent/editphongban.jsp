<%@page import="model.bean.PhongBan"%>
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
	
	PhongBan phongBan = (PhongBan) request.getAttribute("phongBan");
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
       <h2>Chỉnh sửa thông tin phòng ban</h2>
       <div class="widget">
       <a href="PhongBanServlet"><i class="fa fa-arrow-left"></i>&nbsp; Trở lại danh sách</a>
       <form class="form form-horizontal" role="form" action="EditPhongBanServlet" method="post" id="EditPhongBanForm">
       <input type="text" hidden name="id" value="<%=phongBan.getIdPB() %>" class="input_form"/>
       <div style="text-align: center; color: red;">
		  	<span id="thongbao"></span>
		  	<br/>
		  	<%
		  		String loi = (request.getAttribute("loi") != null) ? (String) request.getAttribute("loi") : ""; 
		  		String themphongbanthanhcong = (request.getAttribute("themphongbanthanhcong") != null) ? (String) request.getAttribute("themphongbanthanhcong") : "";
		  		String editphongbanthanhcong = (request.getAttribute("editphongbanthanhcong") != null) ? (String) request.getAttribute("editphongbanthanhcong") : "";
		  	%>
		  	<%= loi %>
		  	<span style="color: green"><%= themphongbanthanhcong %></span>
		  	<span style="color: green"><%= editphongbanthanhcong %></span>
		  </div>
		<table border="0px" width= "60%" style="margin-left: 20%" >
			<tr>
				<th>Tên phòng ban <span style="color: red; margin-left: 5px;">*</span></th>
				<th><input type="text" name="TenPB" class="input_form" value="<%=phongBan.getTenPB() %>"/><th>
			</tr>
			<tr>
				<th></th>
				<th>
					<input type="reset" name="nhapLai" value="Nhập lại" class="input_form"/>
					<input type="submit" name="EditPB" value="Chỉnh sửa" />
				</th>
			</tr>
		  </table>
		</form> 
       </div>  
    </div>
</div>
<jsp:include page="footer.jsp" flush="true"></jsp:include>