package com.huutrung.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(8000);
			System.out.println("Server is running at port " + ss);
			while (true) {
				Socket socket = null;
				try {
					socket = ss.accept();
					DataInputStream dis = new DataInputStream(socket.getInputStream());
					DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
					Thread t = new ClientThreadHandler(socket, dis, dos);
					t.start();
				} catch (Exception e) {
					socket.close();
					e.printStackTrace();
				}
			}
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
}

class ClientThreadHandler extends Thread {
	final DataOutputStream dos;
	final DataInputStream dis;
	final Socket socket;
	private Connection conn;
	private Statement stm;
	private ResultSet rs;
	private String sql;

	// Constructors
	public ClientThreadHandler(Socket socket, DataInputStream dis, DataOutputStream dos) {
		this.socket = socket;
		this.dis = dis;
		this.dos = dos;
		System.out.println("Call Thread socket");
		System.out.println("Socket is connected: " + socket.isConnected());
		System.out.println("Socket address: " + socket.getInetAddress().getHostAddress());
		System.out.println("Socket port: " + socket.getPort());
	}

	@Override
	public void run() {
		try {
			String[] arr = null;
			String st = "";
			System.out.println("Xu li du lieu");
			String students = null;
			String result = "";
			st = dis.readUTF();
			System.out.println("Client gui: " + st);
			arr = st.split("#");
			try {
//				students = xemData();
				if("allNameFromServer".equals(arr[0])) {
					students = allNameStudents();
				} else {
					students = xemData();
				}
				
			} catch (Exception e) {
				result = "DBError";
				System.out.println("Loi connect database: "+e);
			}
			System.out.println("Trong ham run() :  students = "+ students);
			if ((null == students || students.isEmpty()) && !result.equals("DBError")) {
				result = "OK";
			} else if((null != students && !students.isEmpty()) && !result.equals("DBError")) {
				result = students;
			}

//			if ("showAll".equals(st)) {
//				students = xemData();
//				if ((null == students || students.isEmpty()) && !result.equals("DBError")) {
//					result = "OK";
//				} else if ((null != students && !students.isEmpty()) && !result.equals("DBError")) {
//					result = students;
//				}
//			}

			if (null != arr && arr.length == 4) {
				System.out.println("chuoi 1:" + arr[0]);
				System.out.println("chuoi 2:" + arr[1]);
				System.out.println("chuoi 3:" + arr[2]);
				System.out.println("chuoi 4:" + arr[3]);
			} else {
				System.out.println(arr != null ? arr.toString() : "Null");
			}
			if (null != arr && 4 == arr.length) {
				if (arr[3].equalsIgnoreCase("search")) // goi ham tim kiem trong csdl
				{
					result = searchData(arr[0], arr[1], arr[2]);
				}
			}
			System.out.print(result);
			Thread.sleep(1000);
			dos.writeUTF(result);// gui du lieu xuong sever
			dos.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Tao connect to database
	public void ConnectData() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DACSNM?useUnicode=true&characterEncoding=UTF-8", "root", "password");
			stm = conn.createStatement();
		} catch (Exception e) {
			System.out.println("Khong nap duoc Driver " + e);
		}
	}

	// get all name of students
	public String allNameStudents() throws Exception {
		ConnectData();
		sql = "SELECT * FROM `DiemThi` ";
		rs = stm.executeQuery(sql);
		StringBuilder students = new StringBuilder();
		while (rs.next()) {
			String TenSV = rs.getString("TenSV");
			if (TenSV == null) {
				TenSV = "";
			}

			String student = TenSV + ";";
			students.append(student);
		}
		System.out.println("allNameStudents() = " + students.toString());
		conn.close();
		return students.toString();
	}

	// Search Data
	public String searchData(String masv, String tensv, String diem) throws Exception {
		ConnectData();
		masv.trim();
		//tensv.trim().toString();
		tensv.trim();
		diem.trim();
		System.out.println(tensv);
		//sql = "SELECT * FROM `DiemThi` WHERE MaSV LIKE '%" + masv + "%' AND TenSV LIKE '%" + tensv + "%' AND Diem LIKE '%" + diem + "%'";
		sql = "SELECT * FROM `DiemThi` WHERE MaSV LIKE '%" + masv + "%' AND TenSV LIKE '%" + tensv + "%' AND Diem LIKE '%" + diem + "%';";
		
		System.out.println("SQL: " + sql);
		rs = stm.executeQuery(sql);
//		if(!rs.next()) {
//			System.out.println("rs null");
//		}
		StringBuilder students = new StringBuilder();
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
			String student = sCode.trim() + "," + sName.trim() + "," + sGioiTinh.trim() + "," + sDiem.trim() + ";";
			students.append(student);
		}
		System.out.println("SearchData() = " + students.toString());
		conn.close();
		return students.toString();
	}

	public String xemData() throws Exception {
		ConnectData();
		sql = "select * from DiemThi";
		rs = stm.executeQuery(sql);
		StringBuilder students = new StringBuilder();
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
			String student = sCode.trim() + "," + sName.trim() + "," + sGioiTinh.trim() + "," + sDiem.trim() + ";";
			students.append(student);
		}
		System.out.println("XemData() = " + students.toString());
		conn.close();
		return students.toString();
	}
}
