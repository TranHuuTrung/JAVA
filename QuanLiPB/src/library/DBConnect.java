package library;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ql_phongban?useUnicode=true&CharacterEncoding=UTF-8", "root", "password");
			System.out.println("Ket noi thanh cong");
		} catch (Exception e) {
			System.out.println("Khong the ket noi voi databse");
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		System.out.println(DBConnect.getConnection());
	}

}
