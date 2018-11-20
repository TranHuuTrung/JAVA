package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.NhanVien;
import model.bean.PhongBan;
import model.bo.TimKiemBO;

@WebServlet("/TimKiemServlet")
public class TimKiemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = "/timkiem.jsp";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String searchType = request.getParameter("searchType");
		String searchQuery = request.getParameter("searchQuery");
		TimKiemBO timKiemBO = new TimKiemBO();
		String destination = null;
		if("staff".equals(searchType)) {
			ArrayList<NhanVien> resultNhanVienList = null;
			resultNhanVienList = timKiemBO.getSearchNhanVienList(searchQuery);
			request.setAttribute("resultSearch", resultNhanVienList);
			destination = "/timkiem.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		}
		if("dept".equals(searchType)) {
			ArrayList<NhanVien> resultPhongBanList = null;
			resultPhongBanList = timKiemBO.getSearchPhongBanList(searchType,searchQuery);
			request.setAttribute("resultSearch", resultPhongBanList);
			destination = "/timkiem.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		}
	}

}
