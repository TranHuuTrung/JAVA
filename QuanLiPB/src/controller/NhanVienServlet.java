package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.NhanVien;
import model.dao.NhanVienDAO;

@WebServlet("/NhanVienServlet")
public class NhanVienServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	NhanVienDAO nhanvienDAO = new NhanVienDAO();
    public NhanVienServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		

		//get value 
		String key = (request.getParameter("key") ==null)?"": request.getParameter("key");
		int start = 0;
		int totals = nhanvienDAO.countTotal("key");
		try {
			 start = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
		}
	
		ArrayList<NhanVien> nhanviens = nhanvienDAO.getList(key,start*5,5);
		request.setAttribute("totals",String.valueOf(totals));
		request.setAttribute("nhanviens",nhanviens);
		request.getRequestDispatcher("xemnhanvien.jsp").forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
