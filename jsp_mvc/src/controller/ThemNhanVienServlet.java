package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.PhongBan;
import model.bo.ThemNhanVienBO;

@WebServlet("/ThemNhanVienServlet")
public class ThemNhanVienServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ArrayList<PhongBan> listPhongBan = null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = null;
		ThemNhanVienBO themNhanVienBO = new ThemNhanVienBO();
		listPhongBan = themNhanVienBO.listphongBan();
		request.setAttribute("listPhongBan", listPhongBan);
		destination = "/themnhanvien.jsp";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String destination = null;
		String TenNV = request.getParameter("TenNV");
		String ChucVu = request.getParameter("ChucVu");
		String idPBString = request.getParameter("IdPB");
		Long IdPB = Long.parseLong(idPBString);
		String DiaChi = request.getParameter("DiaChi");
		String SDT = request.getParameter("SDT");
		ThemNhanVienBO themNhanVienBO = new ThemNhanVienBO();
		if(themNhanVienBO.isThemNhanVienSuccess(TenNV, IdPB, ChucVu, DiaChi, SDT)) {
			request.setAttribute("listPhongBan", listPhongBan);
			request.setAttribute("themnhanvienthanhcong", "Thêm mới nhân viên thành công!");
			destination = "/themnhanvien.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		} else {
			request.setAttribute("listPhongBan", listPhongBan);
			request.setAttribute("loi", "Không thể thêm mới nhân viên!");
			destination = "/themnhanvien.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		}
		
	}

}
