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
        <br>
         	<h1>Trợ giúp chương trình</h1>
		<ul>
			<li>
				Chọn menu 1 khác bất kì để đăng nhập vào hệ thống và sử dụng các chức năng khác
			</li>
			<li>
				Sau khi đăng nhập: 
			</li>
			<li>
				Chọn menu "Trang chủ" để xem trang chủ của hệ thống 
			</li>
			<li>
				Chọn menu "Xem nhân viên" để xem danh sách nhân viên 
			</li>
			<li>
				Chọn menu "Xem phòng ban" để xem danh sách phòng ban
				<ul>
					<li>
						Chỉnh sửa phòng ban.  
					</li>
					<li>
						Chức năng tìm kiếm phòng ban.  
					</li>
					<li>
						Xóa 1 phòng ban.  
					</li>
					<li>
						Xóa tất cả phòng ban  
					</li>
				</ul> 
			</li>
		</ul>          	   
    </div>
</div>
<jsp:include page="footer.jsp" flush="true"></jsp:include>