package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.PhongBan;
import model.bo.PhongBanBO;

@WebServlet("/EditPhongBanServlet")
public class EditPhongBanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = null;
		String id = request.getParameter("id");
		Long IdPB = Long.parseLong(id);
		PhongBanBO phongBanBO = new PhongBanBO();
		PhongBan phongBan = phongBanBO.getPhongBanById(IdPB);
		request.setAttribute("phongBan", phongBan);
		destination = "/editphongban.jsp";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String destination = null;
		String id = request.getParameter("id");
		Long IdPB = Long.parseLong(id);
		String TenPB = request.getParameter("TenPB");
		if(TenPB == null || TenPB == "") {
			request.setAttribute("loi", "Tên phòng ban không được trống!");
			destination = "/editphongban.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		} else {
		PhongBanBO phongBanBO = new PhongBanBO();
		if(phongBanBO.isEditPhongBanSuccess(IdPB,TenPB)) {
			PhongBanBO phongBanBO2 = new PhongBanBO();
			PhongBan phongBan = phongBanBO2.getPhongBanById(IdPB);
			request.setAttribute("phongBan", phongBan);
			request.setAttribute("editphongbanthanhcong", "Chỉnh sửa phòng ban thành công!");
			destination = "/editphongban.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		} else {
			request.setAttribute("loi", "Phòng ban đã tồn tại!");
			destination = "/editphongban.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		}
	  }
	}

}
