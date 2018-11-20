package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import model.bean.NhanVien;
import utils.DBConnect;

public class TimKiemDAO {
	Connection connection;
	Statement st;
	ResultSet rs;
	public ArrayList<NhanVien> getSearchNhanVienList(String searchQuery){
		ArrayList<NhanVien> result = new ArrayList<NhanVien>();
		//connect DB, get all
		connection = DBConnect.getConnect();
		String condition = "%"+searchQuery+"%";
		String sql ="SELECT * FROM nhanvien LEFT JOIN phongban "
				+ "ON nhanvien.IdPhongBan = phongban.Id "
				+ "WHERE nhanvien.TenNV LIKE '"+condition+"'";
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
	
	public ArrayList<NhanVien> getSearchPhongBanList(String searchType,String searchQuery){
		ArrayList<NhanVien> result = new ArrayList<NhanVien>();
		//connect DB, get all
		connection = DBConnect.getConnect();
		String condition = "%"+searchQuery+"%";
		String sql ="SELECT * FROM nhanvien LEFT JOIN phongban "
				+ "ON nhanvien.IdPhongBan = phongban.Id "
				+ "WHERE phongban.TenPB LIKE '"+condition+"'";
		try {
			st = connection.createStatement();
			rs = st.executeQuery(sql);
			System.out.println(rs);
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
}
