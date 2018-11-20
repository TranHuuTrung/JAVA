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

@WebServlet("/NhanVienServlet")
public class NhanVienServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NhanVienBO nhanVienBO = new NhanVienBO();
		String destination = null;
		ArrayList<NhanVien> nhanViens = null;
		nhanViens = nhanVienBO.getNhanVienList();
		request.setAttribute("nhanvienList", nhanViens);
		destination = "/nhanvien.jsp";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
