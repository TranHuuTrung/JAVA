package com.huutrung.demo;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPTimeClient {
	public static void main(String[] args) {
		Socket clientSk;
		try {
			clientSk = new Socket("localhost", 5000);
			DataInputStream dis = new DataInputStream(clientSk.getInputStream());
			String time = dis.readUTF();
			System.out.println(time);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
