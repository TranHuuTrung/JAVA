package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.DBConnect;
import model.bean.PhongBan;

public class PhongBanDAO {

	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
 
	public int countTotal(String key){
		int result = 0;
		conn = DBConnect.getConnection();
		String sql = "SELECT count(*) FROM phong_ban WHERE ten_phong_ban like ?";
		try {
			pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setString(1, "%" + key + "%");
			rs = pst.executeQuery();
		
			while (rs.next()) {
				result = rs.getInt(1);
			}
			rs.close();
			pst.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public ArrayList<PhongBan> getList() {
		ArrayList<PhongBan> result = new ArrayList<>();
		conn = DBConnect.getConnection();
		String sql = "SELECT * FROM nhan_vien";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				PhongBan temp = new PhongBan(
						rs.getString("ma_phong_ban"), 
						rs.getString("ten_phong_ban"),
						rs.getDate("ngay_tao"));
				result.add(temp);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public ArrayList<PhongBan> getList(String key, int start, int end) {
		ArrayList<PhongBan> result = new ArrayList<>();
		conn = DBConnect.getConnection();
		String sql = "SELECT * FROM  phong_ban WHERE ten_phong_ban like ? LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setString(1, "%" + key + "%");
			pst.setInt(2, start);
			pst.setInt(3, end);
			rs = pst.executeQuery();
			while (rs.next()) {
				PhongBan temp = new PhongBan(
						rs.getString("ma_phong_ban"), 
						rs.getString("ten_phong_ban"),
						rs.getDate("ngay_tao"));
				result.add(temp);
			}
			rs.close();
			pst.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {

				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public ArrayList<PhongBan> getList(String key) {
		ArrayList<PhongBan> result = new ArrayList<>();
		conn = DBConnect.getConnection();
		String sql = "SELECT * FROM phong_ban WHERE ten_phong_ban like ?";
		try {
			pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setString(1, "%" + key + "%");
			rs = pst.executeQuery();
			while (rs.next()) {
				PhongBan temp = new PhongBan(
						rs.getString("ma_phong_ban"), 
						rs.getString("ten_phong_ban"),
						rs.getDate("ngay_tao"));
				result.add(temp);
			}
			rs.close();
			pst.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}


	public PhongBan getItem(String id){
		PhongBan result =new PhongBan();
		conn = DBConnect.getConnection();
		String sql = "SELECT * FROM phong_ban WHERE ma_phong_ban = ?";
		try {
			pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setString(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				result = new PhongBan(
						rs.getString("ma_phong_ban"), 
						rs.getString("ten_phong_ban"),
						rs.getDate("ngay_tao"));
			}
			rs.close();
			pst.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
public int addItem(PhongBan item) {
		int result = 0;

		return result;
	}

	public int editItem(PhongBan item) {
		int result = 0;

		return result;
	}

	public int deleteItem(PhongBan item) {
		int result = 0;

		return result;
	}

	public static void main(String[] args) {
		PhongBanDAO tmp = new PhongBanDAO();
		System.out.println(tmp.countTotal(""));
		for (PhongBan item : tmp.getList("", 0, 5)) {
			System.out.println(item.toString());
		}
	}
}
