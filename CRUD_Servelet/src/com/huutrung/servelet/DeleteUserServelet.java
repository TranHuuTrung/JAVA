package com.huutrung.servelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class CreateUserServelet
 */
@WebServlet(urlPatterns="/deleteServlet", initParams= {
		@WebInitParam(name="dbUrl", value="jdbc:mysql://localhost:3306/jsp"),
		@WebInitParam(name="dbUser", value="root"),
		@WebInitParam(name="dbPassword", value="password")
})
public class DeleteUserServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	public void init(ServletConfig config) {
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			connection = (Connection) DriverManager.getConnection(config.getInitParameter("dbUrl"), config.getInitParameter("dbUser"), config.getInitParameter("dbPassword"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		
		try {
			Statement statement = (Statement) connection.createStatement();
			int result = statement.executeUpdate("delete from user where email='"+email+"'");
			PrintWriter out = response.getWriter();
			if(result > 0) {
				out.println("<h1>delete user</h1>");
			} else {
				out.print("<h1>Cann't delete user because it didn't exist in database</h1>");
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
