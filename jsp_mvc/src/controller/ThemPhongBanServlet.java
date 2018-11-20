package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bo.PhongBanBO;


@WebServlet("/ThemPhongBanServlet")
public class ThemPhongBanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = "/themphongban.jsp";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String destination = null;
		String TenPB = request.getParameter("TenPB");
		if(TenPB == null || TenPB == "") {
			request.setAttribute("loi", "Tên phòng ban không được trống!");
			destination = "/themphongban.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		} else {
		PhongBanBO phongBanBO = new PhongBanBO();
		if(phongBanBO.isThemPhongBanSuccess(TenPB)) {
			request.setAttribute("themphongbanthanhcong", "Thêm phòng ban mới thành công!");
			destination = "/themphongban.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		} else {
			request.setAttribute("loi", "Phòng ban đã tồn tại!");
			destination = "/themphongban.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		}
	  }
	}

}
