package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	public static Connection getConnect() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/thicuoiki?useUnicode=true&characterEncoding=UTF-8", "root", "password");
			System.out.println("Connect successfully!");
		} catch (Exception e) {
			System.out.println("Cannot connect to DB");
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args) {
		System.out.println(DBConnect.getConnect());
	}
}
