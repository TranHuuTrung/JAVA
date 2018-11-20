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
import model.bo.NhanVienBO;
import model.bo.PhongBanBO;

@WebServlet("/DeletePhongBanServlet")
public class DeletePhongBanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Long IdPB = Long.parseLong(id);
		String destination = null;
		PhongBanBO phongBanBO = new PhongBanBO();
		ArrayList<PhongBan> phongBans = null;
		if(phongBanBO.isDeletePhongBanSuccess(IdPB)) {
			phongBans = phongBanBO.getPhongBanList();
			request.setAttribute("phongbanList", phongBans);
			request.setAttribute("xoaphongbanthanhcong", "Xóa phòng ban thành công!");
			destination = "/phongban.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		} else {
			request.setAttribute("loixoaphongban", "Không xóa được phòng ban này!");
			destination = "/phongban.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
