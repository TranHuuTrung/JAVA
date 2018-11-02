package UDP_Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
	public static void main(String[] args) throws IOException {
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(System.in));
		// gan cong cho chuong trinh
		DatagramSocket serverSK = new DatagramSocket(9999);
		//tao cac mang byte de gui va nhan du lieu
		System.out.println("Started server!");
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		while (true) {
			//tao goi rong de nhan du lieu tu clinet
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			//nhan du lieu tu client
			serverSK.receive(receivePacket);
			//Lay dia chi cua client
			InetAddress IPAddress = receivePacket.getAddress();
			//lay port cua client 
			int port = receivePacket.getPort();
			//Xu li 
			receiveData = receivePacket.getData();
			System.out.println("Client: "+new String(receiveData));
			String message = inFromServer.readLine();
			sendData = message.getBytes();
			//tao ;mang cac byte trong de gui data
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			//gui data
			serverSK.send(sendPacket);
			sendData = new byte[1024];
			receiveData = new byte[1024];
		}
	}
}
