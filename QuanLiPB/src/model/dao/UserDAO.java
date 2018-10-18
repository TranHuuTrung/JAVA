package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import library.DBConnect;
import model.bean.User;

public class UserDAO {
	public ArrayList<User> getList() {
		ArrayList<User> result = new ArrayList<>();
		Connection connection = DBConnect.getConnection();
		String sql = "SELECT * FROM user";
		PreparedStatement ps;
		try {
			ps = connection.prepareCall(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User item = new User();
				item.setId(rs.getLong("user_id"));
				item.setEmail(rs.getString("user_email"));
				item.setPass(rs.getString("user_pass"));
				result.add(item);
			}
			ps.close();
		} catch (SQLException ex) {
		}
		try {
			connection.close();
		} catch (SQLException e) {
		}
		return result;
	}

	public User getItem(long id) {
		Connection connection = DBConnect.getConnection();
		String sql = "SELECT * FROM user WHERE user_id=?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User item = new User();
				item.setId(rs.getLong("user_id"));
				item.setEmail(rs.getString("user_email"));
				item.setPass(rs.getString("user_pass"));
				try {
					ps.close();
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return item;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public ArrayList<User> getItemByKey(String email) {

		ArrayList<User> result = new ArrayList<>();
		Connection connection = DBConnect.getConnection();
		String sql = "SELECT * FROM user WHERE user_email like '%?%'";
		PreparedStatement ps;
		try {
			ps = connection.prepareCall(sql);
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User item = new User();
				item.setId(rs.getLong("user_id"));
				item.setEmail(rs.getString("user_email"));
				item.setPass(rs.getString("user_pass"));
				result.add(item);
			}
			ps.close();
		} catch (SQLException ex) {
		}
		try {
			connection.close();
		} catch (SQLException e) {
		}
		return result;
	}

	public int addItem(User item) {
		int result = 0;
		String sql = "INSERT INTO user(user_email,user_pass) VALUES (?, ?)";
		Connection connection = DBConnect.getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, item.getEmail());
			ps.setString(2, item.getPass());
			result = ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public int editItem(User item) {
		int result = 0;
		String sql = "UPDATE user SET user_pass = ? WHERE user_id = ?";
		Connection connection = DBConnect.getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setLong(2, item.getId());
			ps.setString(1, item.getPass());
			ps.executeUpdate();
			result = ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public int deleteItem(User item) {
		int result = 0;
		String sql = "DELETE FROM user WHERE user_id = ?";
		Connection connection = DBConnect.getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setLong(1, (new Date().getTime()));
			ps.executeUpdate();
			result = ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public User checkUser(String email, String pass){
		Connection connection = DBConnect.getConnection();
		String sql = "SELECT * FROM user WHERE user_email=? AND user_pass=?";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1,email);
			ps.setString(2,pass);
			ResultSet rs = ps.executeQuery();
			System.out.println(rs);
			while (rs.next()) {
				User item = new User();
				item.setId(rs.getLong("user_id"));
				item.setEmail(rs.getString("user_email"));
				item.setPass(rs.getString("user_pass"));
				try {
					ps.close();
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return item;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}
	public static void main(String[] args) {
		UserDAO tmp = new UserDAO();
		for(User item:tmp.getItemByKey("1")){
			System.out.println(item.toString());
		}
		System.out.println("...");
//		 System.out.println(tmp.addItem(
//				new User(
//						new Date().getTime(),
//						"1230",
//						"123")));
	}
}
