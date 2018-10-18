package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.NhanVien;
import model.dao.NhanVienDAO;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	NhanVienDAO nhanvienDAO = new NhanVienDAO();
	
	public EditServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		nhanvienDAO = new NhanVienDAO();
		if(type.equals("NhanVien")){
			NhanVien item = nhanvienDAO.getItem(id);
			request.setAttribute("item", item);
			request.getRequestDispatcher("editnhanvien.jsp").forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
