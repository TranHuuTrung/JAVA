package huutrung.dacsnm.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.ConnectDB;

public class ThreadHandler extends Thread{
	final Socket socket;
	private Connection conn;
	private Statement stm;
	private ResultSet rs;
	private String sql;
	//Constructor
	public ThreadHandler(Socket socket) {
		this.socket = socket;
		System.out.println("Call Thread!");
		System.out.println("Socket address : "+ socket.getInetAddress().getHostAddress());
		System.out.println("Socket port: "+ socket.getPort());
	}
	
	@Override
	public void run() {
		try {
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			//Nhan yeu cau va xu li
			String response = null;
			
			String requestFromClient = null;
			requestFromClient = dis.readUTF();
			//lay connect den DB
			conn = ConnectDB.getConnect();
			if("requestConnectDB".equals(requestFromClient)) {
				if(conn != null) {
					response = "OK";
				}
			}
			
			if ("allData".equals(requestFromClient)) {
				//xem tat ca
				String students = null;
				students = allStudents(conn);
				//check empty
				if(!students.isEmpty()) {
					response = students;
				} else {
					response = "notDB";
				}
			}
			if("Search".equals(requestFromClient.split("#")[0])) {
				//search
				System.out.println(requestFromClient);
				String[] arrRequest = requestFromClient.split("#");
				for (int i = 0; i < arrRequest.length; i++) {
					System.out.println("arrRequest[ "+i+"] = "+ arrRequest[i]);
				}
				String maSV = arrRequest[1];
				String tenSV = arrRequest[2];
				String diem = arrRequest[3];
				String students = null;
				students = searchStudents(conn, maSV, tenSV, diem);
				//check empty
				if(!students.isEmpty()) {
					response = students;
				} else {
					response = "notHaveDataSearch";
				}
			}
			System.out.println(response);
			dos.writeUTF(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//get all students from DB
	public String allStudents(Connection conn){
		sql = "SELECT * FROM DiemThi";
		StringBuilder students = new StringBuilder();
		try {
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				String sCode = rs.getString("MaSV");
				String sName = rs.getString("TenSV");
				String sGioiTinh = rs.getString("GioiTinh");
				String sDiem = rs.getString("Diem");
				if (sCode == null) {
					sCode = "";
				}
				if (sName == null) {
					sName = "";
				}
				if (sGioiTinh == null) {
					sGioiTinh = "";
				}
				if (sDiem == null) {
					sDiem = "";
				}
				String student = sCode.trim()+","+sName.trim()+","+sGioiTinh.trim()+","+sDiem.trim()+";";
				students.append(student);
			}
			System.out.println("allStudents : "+ students.toString());
			conn.close();
		} catch (SQLException e) {
			students.append("notDB");
		}
		
		return students.toString();
	}
	
	//Search data
	public String searchStudents(Connection conn, String maSV, String tenSV, String diem) {
		sql = "SELECT * FROM `DiemThi` WHERE MaSV LIKE '%"+maSV+"%' AND TenSV LIKE '%"+tenSV+"%' AND Diem LIKE '%"+diem+"%';";
		System.out.println("SQL: "+ sql);
		StringBuilder students = new StringBuilder();
		try {
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
//			if(!rs.next()) 
//				return students.append("notHaveDataSearch").toString();
//			} else {
			while(rs.next()) {
				String sCode = rs.getString("MaSV");
				String sName = rs.getString("TenSV");
				String sGioiTinh = rs.getString("GioiTinh");
				String sDiem = rs.getString("Diem");
				if (sCode == null) {
					sCode = "";
				}
				if (sName == null) {
					sName = "";
				}
				if (sGioiTinh == null) {
					sGioiTinh = "";
				}
				if (sDiem == null) {
					sDiem = "";
				}
				String student = sCode.trim()+","+sName.trim()+","+sGioiTinh.trim()+","+sDiem.trim()+";";
				students.append(student);
			}
			System.out.println("search Students : "+ students.toString());
			conn.close();
		} catch (SQLException sqle) {
			students.append("notHaveDataSearch");
		}
		return students.toString();
	}
	
}
