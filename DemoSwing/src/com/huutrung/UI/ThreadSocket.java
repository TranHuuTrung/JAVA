package com.huutrung.UI;

import java.net.Socket;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author HB
 */
public class ThreadSocket extends Thread {

    Socket socket = null;
    private Connection con;
    private Statement stm;
    private ResultSet rs;
    private String sql;

    public ThreadSocket(Socket socket) {

        System.out.println("Call to thread socket. ");
        System.out.println("Socket is connected: " + socket.isConnected());
        System.out.println("Socket address: " + socket.getInetAddress().getHostAddress());
        System.out.println("Socket port: " + socket.getPort());
        this.socket = socket;
    }

    public void run() {
        try {
            // tao luong gui du lieu toi client
            String[] arr = {null};
            String st = "";
            //tao luong nhan du lieu tu client
            //BufferedReader din= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataInputStream din = new DataInputStream(socket.getInputStream());
            // tao luong gui du lieu toi client
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            System.out.println("Xu ly du lieu. ");
            String students = null;
            String result = "";
            st = din.readUTF();
            System.out.println("Client gui:" + st);
            arr = st.split("#");
            
            try {
                students = xemData();// gan ham xemdata cho bien sv
            } catch (Exception e) {
                result = "DBError";
                System.out.println("Loi ket noi database: " + e);
            }

            if ((null == students || students.isEmpty())
                    && !result.equals("DBError")) {
                result = "OK";
            } else if ((null != students && !students.isEmpty())
                    && !result.equals("DBError")) {
                result = students;
            }

            if (null != arr && arr.length == 4) {
                System.out.println("chuoi 1:" + arr[0]);
                System.out.println("chuoi 2:" + arr[1]);
                System.out.println("chuoi 3:" + arr[2]);
                System.out.println("chuoi 4:" + arr[3]);
            } else {
                System.out.println(arr != null ? arr.toString() : "Null");
            }

            if (null != arr && 4 == arr.length) {
                if (arr[3].equalsIgnoreCase("search")) //goi ham tim kiem trong csdl
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

    //tao conet den csdl 
    public void ConnectData() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String URL = "jdbc:mysql://localhost/QuanLyDiemSV2?useUnicode=true&characterEncoding=UTF-8";
            String user = "root";
            String Password = "";
            con = DriverManager.getConnection(URL, user, Password);
            stm = con.createStatement();
        } catch (Exception e) {
            System.out.println("khong nap duoc driver" + e);
        }
    }

    public String searchData(String masv, String tensv, String diem) throws Exception {
        ConnectData();
        sql = "SELECT * FROM `BangDiem` WHERE MaSV LIKE '%" + masv + "%' AND TenSV LIKE '%" + tensv
                + "%' AND dltm LIKE '%" + diem + "%'";
        System.out.println("SQL: "+sql);
        rs = stm.executeQuery(sql);
        StringBuilder students = new StringBuilder();
//        if (!rs.next()) {
//            return "";
//        }
        while (rs.next()) {
            String sCode = rs.getString("MaSV");
            String sName = rs.getString("TenSV");
            String sScore = rs.getString("dltm");

            if (sCode == null) {
                sCode = "";
            }
            if (sName == null) {
                sName = "";
            }
            if (sScore == null) {
                sCode = "";
            }
            String student = sCode.trim() + "," + sName.trim() + "," + sScore.trim() + ";";
            students.append(student);
        }
        System.out.println("searchData() = " + students.toString());
        con.close();
        return students.toString();
    }

    public String xemData() throws Exception {
        ConnectData();
        sql = "select * from BangDiem";
        rs = stm.executeQuery(sql);
        StringBuilder students = new StringBuilder();
//        if (!rs.next()) {
//            return "";
//        }
        while (rs.next()) {
            String sCode = rs.getString("MaSV");
            String sName = rs.getString("TenSV");
            String sScore = rs.getString("dltm");

            if (sCode == null) {
                sCode = "";
            }
            if (sName == null) {
                sName = "";
            }
            if (sScore == null) {
                sCode = "";
            }
            String student = sCode.trim() + "," + sName.trim() + "," + sScore.trim() + ";";
            students.append(student);
        }
        System.out.println("xemData() = " + students.toString());
        con.close();
        return students.toString();
    }
}
