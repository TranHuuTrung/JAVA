package UDP_getNgayHeThong;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

public class Server {
	public static void main(String[] args) throws IOException {
		//gan cong cho chuong trinh
		DatagramSocket serverSocket = new DatagramSocket(9000);
		System.out.println("Server is started!");
		//tao cac mang byte de chua du lieu gui va nhan
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		while (true) {
			//tao goi rong de nhan du lieu tu server
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			// Nhan du lieu tu client
			serverSocket.receive(receivePacket);
			//Lay dia chi Ip cua may client
			InetAddress IPAddress = receivePacket.getAddress();
			//lay port cua chuong trinh client
			int port = receivePacket.getPort();
			//lay ngay gio cua he thong de gui cho client
			String request = new String(receivePacket.getData());
			System.out.println(request);
			if(request.trim().equals("getDate")) {
				sendData = new Date().toString().getBytes();
			} else {
				sendData = "Server not know what you want!".getBytes();
			}
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			// gui du lieu cho client 
			serverSocket.send(sendPacket);
		}
	}
}
