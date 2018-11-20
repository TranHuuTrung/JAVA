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
import model.bo.ThemNhanVienBO;

@WebServlet("/EditNhanVienServlet")
public class EditNhanVienServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<PhongBan> listPhongBan = null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idNV = request.getParameter("id");
		String destination= null;
		Long MaNV = Long.parseLong(idNV);
		ThemNhanVienBO themNhanVienBO = new ThemNhanVienBO();
		listPhongBan = themNhanVienBO.listphongBan();
		
		NhanVienBO nhanVienBO = new NhanVienBO();
		NhanVien nhanVien = nhanVienBO.getNhanVienById(MaNV);
		request.setAttribute("listPhongBan", listPhongBan);
		request.setAttribute("nhanVien", nhanVien);
		destination = "/editnhanvien.jsp";
		RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String destination = null;
		String idNV = request.getParameter("idNV");
		Long MaNV = Long.parseLong(idNV);
		String TenNV = request.getParameter("TenNV");
		String ChucVu = request.getParameter("ChucVu");
		String idPBString = request.getParameter("IdPB");
		Long IdPB = Long.parseLong(idPBString);
		String DiaChi = request.getParameter("DiaChi");
		String SDT = request.getParameter("SDT");
		NhanVienBO nhanVienBO = new NhanVienBO();
		if(nhanVienBO.isEditNhanVienSuccess(MaNV, TenNV, IdPB, ChucVu, DiaChi, SDT)) {
			NhanVienBO nhanVienBO1 = new NhanVienBO();
			NhanVien nhanVien = nhanVienBO1.getNhanVienById(MaNV);
			request.setAttribute("nhanVien", nhanVien);
			request.setAttribute("listPhongBan", listPhongBan);
			request.setAttribute("editnhanvienthanhcong", "Chỉnh sửa nhân viên thành công!");
			destination = "/editnhanvien.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		} else {
			NhanVien nhanVien = nhanVienBO.getNhanVienById(MaNV);
			request.setAttribute("nhanVien", nhanVien);
			request.setAttribute("listPhongBan", listPhongBan);
			request.setAttribute("loi", "Không thể chỉnh sửa nhân viên!");
			destination = "/editnhanvien.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		}
	}

}
