package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.bo.CheckLoginBO;
@WebServlet("/CheckLoginServlet")
public class CheckLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = null;
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		CheckLoginBO checkLoginBO = new CheckLoginBO();
		User user = checkLoginBO.checkValidUser(userName, passWord);
		if (user == null) {
			request.setAttribute("loi", "UserName or Password incorrect!");
			destination = "/login.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		} else {
//			request.setAttribute("userName", userName);
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			destination = "/home.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);
		}
		
	}

}
