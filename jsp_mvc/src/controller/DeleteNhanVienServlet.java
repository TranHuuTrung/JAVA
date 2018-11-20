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
import model.bo.NhanVienBO;


@WebServlet("/DeleteNhanVienServlet")
public class DeleteNhanVienServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maNV = request.getParameter("id");
		Long idNV = Long.parseLong(maNV);
		NhanVienBO nhanVienBO = new NhanVienBO();
		String destination = null;
		ArrayList<NhanVien> nhanViens = null;
		if(nhanVienBO.isDeleteNhanVienSuccess(idNV)) {
			nhanViens = nhanVienBO.getNhanVienList();
			request.setAttribute("nhanvienList", nhanViens);
			request.setAttribute("xoanhanvienthanhcong", "Xóa nhân viên thành công!");
			destination = "/nhanvien.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		} else {
			request.setAttribute("loixoanhanvien", "Không xóa được nhân viên!");
			destination = "/nhanvien.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
