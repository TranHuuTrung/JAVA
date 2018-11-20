package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import model.bean.NhanVien;
import utils.DBConnect;

public class NhanVienDAO {
	Connection connection;
	Statement st;
	ResultSet rs;
	public ArrayList<NhanVien> getNhanVienList(){
		ArrayList<NhanVien> result = new ArrayList<NhanVien>();
		//connect DB, get all
		connection = DBConnect.getConnect();
		String sql ="SELECT * FROM nhanvien LEFT JOIN phongban ON nhanvien.IdPhongBan = phongban.Id";
		try {
			st = connection.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Long MaNV = rs.getLong("MaNV");
				String TenNV = rs.getString("TenNV");
				String ChucVu = rs.getString("ChucVu");
				String DiaChi = rs.getString("DiaChi");
				String SDT = rs.getString("SDT");
				String TenPB = rs.getString("TenPB");
				Long IdPhongBan = rs.getLong("IdPhongBan");
				NhanVien temp = new NhanVien(MaNV, TenNV, ChucVu, DiaChi, SDT, IdPhongBan, TenPB);
				result.add(temp);
			}
		} catch (SQLException e) {
			System.out.println("Loi truy van CSDL get all nhanvien : "+e);
		}
		return result;
	}
	
	public boolean isDeleteNhanVienSuccess(Long id) {
		//connect Db, delete
		connection = DBConnect.getConnect();
		String sql = "DELETE FROM nhanvien WHERE MaNV=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setLong(1, id);
			int n = ps.executeUpdate();
			if(n==1) return true;
		} catch (SQLException se) {
			System.out.println("Loi SQL Delete nhanvien : "+se);
		}
		return false;
	}
	
	public NhanVien getNhanVienById(Long MaNV) {
		NhanVien result = null;
		//connect DB, get nhanvien by ID
		connection = DBConnect.getConnect();
		String sql = "SELECT * FROM nhanvien WHERE MaNV=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setLong(1, MaNV);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Long IdNV = rs.getLong("MaNV");
				String TenNV = rs.getString("TenNV");
				String ChucVu = rs.getString("ChucVu");
				String DiaChi = rs.getString("DiaChi");
				String SDT = rs.getString("SDT");
				Long IdPhongBan = rs.getLong("IdPhongBan");
				result = new NhanVien(IdNV, TenNV, ChucVu, DiaChi, SDT, IdPhongBan);
			}
		} catch (SQLException sqle) {
			System.out.println("Loi SQL getnhanvienByID: "+ sqle);
		}
		return result;
	}
	
	public boolean isEditNhanVienSuccess(Long MaNV, String TenNV,Long IdPB, String ChucVu, String DiaChi, String SDT) {
		//connect DB, edit 
		connection = DBConnect.getConnect();
		String sql = "UPDATE nhanvien SET TenNV =?, ChucVu=?, DiaChi=?, SDT=?, IdPhongBan=? WHERE MaNV =?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, TenNV);
			ps.setString(2, ChucVu);
			ps.setString(3, DiaChi);
			ps.setString(4, SDT);
			ps.setLong(5, IdPB);
			ps.setLong(6, MaNV);
			int n = ps.executeUpdate();
			if(n==1) return true;
		} catch (SQLException e1) {
			System.out.println("Loi SQL edit nhanvien: "+e1);
		}
		return false;
	}
}
