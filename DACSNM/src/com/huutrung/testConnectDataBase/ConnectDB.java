package com.huutrung.testConnectDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDB {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DACSNM", "root", "password");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from DiemThi");
			while (rs.next())
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) +" "+ rs.getString(4));
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
