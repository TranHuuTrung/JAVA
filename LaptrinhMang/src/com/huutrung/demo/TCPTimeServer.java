package com.huutrung.demo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TCPTimeServer {
	public static void main(String[] args) {
		ServerSocket severSk;
		try {
			severSk = new ServerSocket(5000);
			System.out.println("Server is running ");
			while(true) {
				Socket socket = severSk.accept();
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				String time = new Date().toString();
				dos.writeUTF("Ngay gio cua he thong la: "+ time);
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
