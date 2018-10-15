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
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class CreateUserServelet
 */
@WebServlet("/readServlet")
public class ReadUserServelet extends HttpServlet {
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			Statement statement = (Statement) connection.createStatement();
			ResultSet resultSet = (ResultSet) statement.executeQuery("select * from user");
			PrintWriter out = response.getWriter();
			out.print("<table>");
			out.print("<tr>");
			out.print("<th>");
			out.println("First Name");
			out.print("</th>");
			out.print("<th>");
			out.println("Last Name");
			out.print("</th>");
			out.print("<th>");
			out.println("Email");
			out.print("</th>");
			out.print("</tr>");
			while (resultSet.next()) {
				out.print("<tr>");
				out.print("<td>");
				out.println(resultSet.getString(1));
				out.print("</td>");
				out.print("<td>");
				out.println(resultSet.getString(2));
				out.print("</td>");
				out.print("<td>");
				out.println(resultSet.getString(3));
				out.print("</td>");
				out.print("</tr>");
			}
			out.print("</table>");
			
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
