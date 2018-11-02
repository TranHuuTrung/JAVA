package UDP_Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) throws IOException {
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		//connect server
		DatagramSocket clientSK = new DatagramSocket();
		//lay dia chi Ip
		InetAddress IPAddress = InetAddress.getByName("localhost");
		//tao cac byte de gui va nhan du lieu
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		//vong lap de gui va nhan den server
		System.out.println("Start!");
		while(true) {
//			System.out.print("Client: ");
			String message = inFromUser.readLine();
			sendData = message.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,9999);
			clientSK.send(sendPacket);
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSK.receive(receivePacket);
			receiveData = receivePacket.getData();
			System.out.println("Server: "+ new String(receiveData));
			sendData = new byte[1024];
			receiveData = new byte[1024];
		}
	}
}
