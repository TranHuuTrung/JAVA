package GiaoDien;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ThreadXuLi implements Runnable {
	final Socket socket;
	final DataInputStream dis;
	final DataOutputStream dos;
	private Connection conn;
	private Statement stm;
	private ResultSet rs;
	//Constructor
	public ThreadXuLi(Socket socket, DataInputStream dis, DataOutputStream dos) {
		this.socket = socket;
		this.dis = dis;
		this.dos = dos;
		System.out.println("Call threadXuLi()!");
		System.out.println("Socket address "+socket.getInetAddress().getHostAddress());
		System.out.println("Socket port "+ socket.getPort());
	}
	@Override
	public void run() {
		try {
			String dataGui = "";
			while(true) {
				// nhan data
				String dataNhan = dis.readUTF();
				//xuli
				String[] arrDataNhan = dataNhan.split("#");
				String thongtin = arrDataNhan[0];
				String sql = arrDataNhan[1];
				try {
					dataGui = sendRequestOK(thongtin, sql);
					
				} catch (Exception e) {
					System.out.println("Khong the ket noi DB");
					e.printStackTrace();
				}
				//gui ve lai cho client
				dos.writeUTF(dataGui);
			}
		} catch (IOException ioe) {
//			ioe.printStackTrace();
			System.out.println("Loi gui data");
		}
	}
	
	//xu li connect databse
	//Tao connect to database
	public void ConnectData(String thongtin) throws Exception{
	  try {
		Class.forName(thongtin);
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/LTM", "root", "password");
		stm = conn.createStatement();
	  } catch (Exception e) {
		System.out.println("Khong nap duoc Driver "+ e);
	  }
	}
	//select all
	public String allRecord(String thongtin) throws Exception {
		ConnectData(thongtin);
		try {
			rs = stm.executeQuery("select * from khachhang");
			StringBuilder khachhang = new StringBuilder();
			while (rs.next()) {
				String khID = rs.getString("id");
				String khName = rs.getString("Ten");
				String khDiaChi = rs.getString("DiaChi");
				String khGioiTinh = rs.getString("GioiTinh");
				String khLuong = rs.getString("Luong");
				if(khID == null) {
					khID = "";
				}
				if (khName == null) {
					khName ="";
				}
				if (khDiaChi == null) {
					khDiaChi = "";
				}
				if(khGioiTinh == null) {
					khGioiTinh = "";
				}
				if(khLuong == null) {
					khLuong = "";
				}
				String kh = khID.trim()+","+khName.trim()+","+khGioiTinh.trim()+","+khDiaChi.trim()+","+khLuong.trim()+";";
					khachhang.append(kh);
				}
				System.out.println("SearchData() = "+ khachhang.toString());
				conn.close();
				return khachhang.toString();
		    } catch (SQLException sqlerror) {
			    return "Loi";
		   }
	}
	//xu li cau lenh sql
	public String sendRequestOK(String thongtin, String sql) throws Exception{
		ConnectData(thongtin);
		System.out.println("SQL: "+sql);
		String ketqua="Loi";
		if(sql.trim().indexOf("select") == 0 || sql.trim().indexOf("Select") == 0) {
		 try {
			rs = stm.executeQuery(sql);
			StringBuilder khachhang = new StringBuilder();
			while (rs.next()) {
				String khID = rs.getString("id");
				String khName = rs.getString("Ten");
				String khDiaChi = rs.getString("DiaChi");
				String khGioiTinh = rs.getString("GioiTinh");
				String khLuong = rs.getString("Luong");
				if(khID == null) {
					khID = "";
				}
				if (khName == null) {
					khName ="";
				}
				if (khDiaChi == null) {
					khDiaChi = "";
				}
				if(khGioiTinh == null) {
					khGioiTinh = "";
				}
				if(khLuong == null) {
					khLuong = "";
				}
				String kh = khID.trim()+","+khName.trim()+","+khGioiTinh.trim()+","+khDiaChi.trim()+","+khLuong.trim()+";";
					khachhang.append(kh);
				}
				System.out.println("SearchData() = "+ khachhang.toString());
				conn.close();
				return khachhang.toString();
		    } catch (SQLException sqlerror) {
			    return "Loi";
		   }
		} else if(sql.trim().toLowerCase().indexOf("insert") == 0 ) {
			try {
				int n=stm.executeUpdate(sql);
				if (n < 1) {
					return "LoiAdd";
				}
				else  {
					String allRecord = allRecord(thongtin);
					return "ThemThanhCong#1#"+allRecord;
				}
			} catch (SQLException sqle) {
				return "Loi";
			}
		} else if(sql.trim().toLowerCase().indexOf("update") == 0 ){
			try {
				int n=stm.executeUpdate(sql);
				if (n < 1) {
					return "LoiUpdate";
				}
				else  {
					String allRecord = allRecord(thongtin);
					return "CapNhatThanhCong#"+n+"#"+allRecord;
				}
			} catch (SQLException sqle) {
				return "Loi";
			}
		}
		return ketqua;
	}	
}
