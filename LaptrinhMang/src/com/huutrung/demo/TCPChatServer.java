package com.huutrung.demo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPChatServer {
	public static void main(String[] args) {
		try {
			ServerSocket serverSk = new ServerSocket(6000);
			System.out.println("Server is running!");
			Socket socket = serverSk.accept();
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			//nhap 
			Scanner kb = new Scanner(System.in);
			while (true) {
				//nhan du lieu tu client
				String st = dis.readUTF();
				System.out.println(st);
				System.out.print("Server: ");
				String msg = kb.nextLine();
				dos.writeUTF("Server: "+msg);
				dos.flush();
				kb = kb.reset();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
