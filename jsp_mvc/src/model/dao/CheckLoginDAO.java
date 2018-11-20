package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import model.bean.User;
import utils.DBConnect;

public class CheckLoginDAO {
	Connection connection;
	public User CheckExistUser(String userName, String passWord) {
		//connect DB, write SQL statement
		connection = DBConnect.getConnect();
		String sql = "SELECT * FROM users WHERE userName=? AND passWord=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, passWord);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getLong("id"));
				user.setUserName(rs.getString("userName"));
				user.setPassWord(rs.getString("passWord"));
				return user;
			}
		} catch (SQLException e) {
			System.out.println("Loi truy van CSDL: "+ e);
		}
		return null;
	}
//	public static void main(String[] args) {
//		CheckLoginDAO aa = new CheckLoginDAO();
//		User abb = aa.CheckExistUser("Huu Trung", "12345678");
//		System.out.println(abb.getId());
//	}
}
