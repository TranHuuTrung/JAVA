package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import library.DBConnect;
import model.bean.Wife;

public class CheckLoginDAO {
	Connection connection;
	public boolean isExistUser(String userName, String password) {
		//connect DB, write SQL statement
		connection = DBConnect.getConnect();
		String sql = "SELECT * FROM users WHERE userName=? AND password=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next() == false) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			System.out.println("Loi truy van CSDL: "+ e);
		}
		return false;
	}
	public ArrayList<Wife> getWifeList(String userName){
		ArrayList<Wife> result = new ArrayList<Wife>();
		//connect DB, truy van csdl
		Wife wife = new Wife();
		wife.setName("Tran Huu Trung");
		wife.setAddress("Da Nang");
		wife.setAlive(true);
		result.add(wife);
		return result;
	}
}
