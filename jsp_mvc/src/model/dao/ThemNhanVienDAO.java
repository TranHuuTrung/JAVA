package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import model.bean.PhongBan;
import utils.DBConnect;

public class ThemNhanVienDAO {
	Connection connection;
	Statement st;
	ResultSet rs;
	public ArrayList<PhongBan> listPhongBan(){
		ArrayList<PhongBan> result = new ArrayList<PhongBan>();
		//connectDB, get all phong ban
		connection = DBConnect.getConnect();
		String sql ="SELECT * FROM phongban";
		try {
			st = connection.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Long IdPB = rs.getLong("Id");
				String TenPB = rs.getString("TenPB");
				PhongBan temp = new PhongBan(IdPB, TenPB);
				result.add(temp);
			}
		} catch (SQLException e) {
			System.out.println("Loi truy van CSDL get all phongban : "+e);
		}
		return result;
	}
	
	public boolean isThemNhanVienSuccess(String TenNV,Long IdPB, String ChucVu, String DiaChi, String SDT) {
		//connect DB
		connection = DBConnect.getConnect();
		String sql = "INSERT INTO nhanvien(TenNV, ChucVu, DiaChi, SDT, IdPhongBan) VALUES(?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, TenNV);
			ps.setString(2, ChucVu);
			ps.setString(3, DiaChi);
			ps.setString(4, SDT);
			ps.setLong(5, IdPB);
			int n = ps.executeUpdate();
			if(n == 1) return true;
		} catch (SQLException e) {
			System.out.println("Loi insert SQL: "+ e);
		}
		return false;
	}
}
