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
	ArrayList<NhanVien> resultSearch = null;
	resultSearch = (ArrayList<NhanVien>) request.getAttribute("resultSearch");
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
       <h2>Tìm kiếm</h2>
       <div class="row">
       <form method="POST" action="TimKiemServlet" class="form">
		 <div class="col-xs-2">
			<div class="form-group">
				<select name="searchType" class="form-control">
					<option value="staff">Nhân viên</option>
					<option value="dept" >Phòng ban</option>
				</select>
			 </div>
		 </div>
		 <div class="col-xs-5">
			<div class="input-group">
				<input type="text" name="searchQuery" value="" class="form-control">
				<span class="input-group-btn"><input type="submit" value="Tìm kiếm" class="btn btn-success"></span>
			</div>
		</div>
	    </form>    
       </div>  
        <div class="">
        <%
        	if(resultSearch != null){
        %>
       <div class="widget col-sm-12">
       	<% 
              if(!resultSearch.isEmpty()){
				for(NhanVien item: resultSearch){	
			%>
       	  <table class="table table-responsive table-bordered" id="nhanvien_table" style="margin-top: 10px;">
			<thead>
			   <tr>
				 <th class="text-center" colspan="2">Tên Nhân Viên</th>
				 <th class="text-center" colspan="1">Chức vụ</th>
				 <th class="text-center" colspan="1">Phòng ban</th>
				 <th class="text-center" colspan="1">SĐT</th>
				 <th class="text-center" colspan="2">Địa Chỉ</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="text-center" colspan="2" id="tenNV"><%=item.getTenNV() %></td>
					<td class="text-center" colspan="1"><%=item.getChucVu() %></td>
					<td class="text-center" colspan="1"><%=item.getTenPB() %></td>
					<td class="text-center" colspan="1"><%=item.getSDT()%></td>
					<td class="text-center" colspan="2"><%=item.getDiaChi() %></td>
				 </tr>
			</tbody>
		 </table><%}}else{ %>
			<p>Không có kết quả</p>
		<%} %> 
       </div>
  	  <%} %> 
    </div>
    </div>
</div>
<jsp:include page="footer.jsp" flush="true"></jsp:include>
	