package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import model.bean.NhanVien;
import model.bean.PhongBan;
import utils.DBConnect;

public class PhongBanDAO {
	Connection connection;
	Statement st;
	ResultSet rs;
	
	public ArrayList<PhongBan> getPhongBanList(){
		//connect DB, get all
		ArrayList<PhongBan> result = new ArrayList<PhongBan>();
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
	
	public boolean isDeletePhongBanSuccess(Long IdPB) {
		//connectDB, delete
		connection = DBConnect.getConnect();
		String sql = "DELETE FROM phongban WHERE Id=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setLong(1, IdPB);
			int n = ps.executeUpdate();
			if(n==1) return true;
		} catch (SQLException sqe) {
			System.out.println("Loi SQL Delete nhanvien : "+sqe);
		}
		return false;
	}
	
	public boolean isThemPhongBanSuccess(String TenPB) {
		//connect DB
		connection = DBConnect.getConnect();
		String sql = "INSERT INTO phongban(TenPB) VALUES(?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, TenPB);
			int n = ps.executeUpdate();
			if(n == 1) return true;
		} catch (SQLException e) {
			System.out.println("Loi insert SQL: "+ e);
		}
		return false;
	}
	
	public PhongBan getPhongBanById(Long IdPB) {
		PhongBan result = null;
		//connect Db, get phongban by Id
		connection = DBConnect.getConnect();
		String sql = "SELECT * FROM phongban WHERE Id=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setLong(1, IdPB);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Long Id = rs.getLong("Id");
				String TenPB = rs.getString("TenPB");
				result = new PhongBan(Id, TenPB);
			}
		} catch (SQLException sle) {
			System.out.println("Loi SQL getphongbanByID: "+ sle);
		}
		return result;
	}
	
	public boolean isEditPhongBanSuccess(Long IdPB, String TenPB) {
		//connectDB, edit
		connection = DBConnect.getConnect();
		String sql = "UPDATE phongban SET TenPB=? WHERE Id =?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, TenPB);
			ps.setLong(2, IdPB);
			int n = ps.executeUpdate();
			if(n==1) return true;
		} catch (SQLException e1) {
			System.out.println("Loi SQL edit nhanvien: "+e1);
		}
		return false;
	}
}
