package model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import utils.DBConnect;

public class CheckDangKiDAO {
	Connection connection;
	public boolean isDangKiSuccess(String userName, String passWord) {
		//connect DB
		connection = DBConnect.getConnect();
		String sql = "INSERT INTO users(userName, passWord) VALUES(?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, passWord);
			int n = ps.executeUpdate();
			if(n == 1) return true;
		} catch (SQLException e) {
			System.out.println("Loi insert SQL: "+ e);
		}
		return false;
	}
//	public static void main(String[] args) {
//		CheckDangKiDAO aaaa = new CheckDangKiDAO();
//		System.out.println(aaaa.isDangKiSuccess("Huu Trung", "123245678"));
//	}
}
