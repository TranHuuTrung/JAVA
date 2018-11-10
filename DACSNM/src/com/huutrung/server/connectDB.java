package com.huutrung.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectDB {
	public Connection connDB() throws SQLException{
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DACSNM", "root", "password");
			
		} catch (ClassNotFoundException classexp) {
			classexp.printStackTrace();
		}
		return con;
	}
}
