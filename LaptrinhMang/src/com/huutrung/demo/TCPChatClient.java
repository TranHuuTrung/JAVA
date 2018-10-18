package com.huutrung.demo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPChatClient {
	public static void main(String[] args) {
		try {
			Socket client = new Socket("localhost", 6000);
			DataInputStream dis = new DataInputStream(client.getInputStream());
			DataOutputStream dos = new DataOutputStream(client.getOutputStream());
			Scanner kb = new Scanner(System.in);
			while (true) {
				System.out.print("Client: ");
				String msString = kb.nextLine();
				dos.writeUTF("Client: "+msString);
				dos.flush();
				//nhan data
				String dataComing = dis.readUTF();
				System.out.println(dataComing);
				kb =kb.reset();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
