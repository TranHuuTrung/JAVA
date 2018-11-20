package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bo.CheckDangKiBO;


@WebServlet("/CheckDangKiServlet")
public class CheckDangKiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = null;
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
//		String repassWord = request.getParameter("repassWord");
		CheckDangKiBO checkDangKiBO = new CheckDangKiBO();
		if(checkDangKiBO.isDangKiSuccess(userName, passWord)) {
			request.setAttribute("dangkithanhcong", "Tạo người dùng thành công!");
			destination = "/register.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		} else {
			request.setAttribute("loi", "Người dùng đã tồn tại!");
			destination = "/register.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		}
	}

}
