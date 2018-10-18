package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.DBConnect;
import model.bean.NhanVien;

public class NhanVienDAO {

	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
 
	public int countTotal(String key){
		int result = 0;
		conn = DBConnect.getConnection();
		String sql = "SELECT count(*) FROM nhan_vien WHERE ten_nhan_vien like ?";
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
	
	public ArrayList<NhanVien> getList() {
		ArrayList<NhanVien> result = new ArrayList<>();
		conn = DBConnect.getConnection();
		String sql = "SELECT * FROM nhan_vien";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				NhanVien temp = new NhanVien(rs.getString("ma_nhan_vien"), rs.getString("ma_chuc_vu"),
						rs.getString("ma_phong_ban"), rs.getString("mat_khau"), rs.getString("ten_nhan_vien"),
						rs.getString("so_dien_thoai"), rs.getString("dia_chi"));
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

	public ArrayList<NhanVien> getList(String key, int start, int end) {
		ArrayList<NhanVien> result = new ArrayList<>();
		conn = DBConnect.getConnection();
		String sql = "SELECT nhan_vien.*,phong_ban.ten_phong_ban FROM nhan_vien LEFT JOIN phong_ban ON nhan_vien.ma_phong_ban = phong_ban.ma_phong_ban WHERE ten_nhan_vien like ? LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setString(1, "%" + key + "%");
			pst.setInt(2, start);
			pst.setInt(3, end);
			rs = pst.executeQuery();
			while (rs.next()) {
				NhanVien temp = new NhanVien(rs.getString("ma_nhan_vien"), rs.getString("ma_chuc_vu"),
						rs.getString("ma_phong_ban"), rs.getString("mat_khau"), rs.getString("ten_nhan_vien"),
						rs.getString("so_dien_thoai"), rs.getString("dia_chi"));
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

	public ArrayList<NhanVien> getList(String key) {
		ArrayList<NhanVien> result = new ArrayList<>();
		conn = DBConnect.getConnection();
		String sql = "SELECT * FROM nhan_vien WHERE ten_nhan_vien like ?";
		try {
			pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setString(1, "%" + key + "%");
			rs = pst.executeQuery();
			while (rs.next()) {
				NhanVien temp = new NhanVien(rs.getString("ma_nhan_vien"), rs.getString("ma_chuc_vu"),
						rs.getString("ma_phong_ban"), rs.getString("mat_khau"), rs.getString("ten_nhan_vien"),
						rs.getString("so_dien_thoai"), rs.getString("dia_chi"));
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


	public NhanVien getItem(String id){
		NhanVien result =new NhanVien();
		conn = DBConnect.getConnection();
		String sql = "SELECT * FROM nhan_vien WHERE ma_nhan_vien = ?";
		try {
			pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setString(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				result = new NhanVien(
						rs.getString("ma_nhan_vien"),
						rs.getString("ma_chuc_vu"),
						rs.getString("ma_phong_ban"),
						rs.getString("mat_khau"), 
						rs.getString("ten_nhan_vien"),
						rs.getString("so_dien_thoai"),
						rs.getString("dia_chi"));
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
public int addItem(NhanVien item) {
		int result = 0;

		return result;
	}

	public int editItem(NhanVien item) {
		int result = 0;

		return result;
	}

	public int deleteItem(NhanVien item) {
		int result = 0;

		return result;
	}

	public static void main(String[] args) {
		NhanVienDAO tmp = new NhanVienDAO();
		System.out.println(tmp.countTotal(""));
		for (NhanVien item : tmp.getList("", 5, 5)) {
			System.out.println(item.toString());
		}
	}
}
