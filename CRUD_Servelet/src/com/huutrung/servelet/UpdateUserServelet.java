package com.huutrung.servelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class CreateUserServelet
 */
@WebServlet("/updateServlet")
public class UpdateUserServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	public void init() {
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp", "root", "password");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			Statement statement = (Statement) connection.createStatement();
			int result = statement.executeUpdate("update user set password='"+password+"' where email='"+email+"'");
			PrintWriter out = response.getWriter();
			if(result > 0) {
				out.println("<h1>updated user</h1>");
			} else {
				out.print("<h1>Cann't update user</h1>");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
	}
	
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
