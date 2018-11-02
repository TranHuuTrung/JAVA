package UDP_Thread_getNgayHeThong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client {
	public static void main(String[] args) throws IOException {
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		try {
			DatagramSocket clientSk = new DatagramSocket();
			System.out.println("Connected to server!");
			InetAddress IPAddress = InetAddress.getByName("localhost");
			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];
			
			System.out.println("Nhap yeu cau: ");
			String st = inFromUser.readLine();
			sendData = st.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 10000);
			clientSk.send(sendPacket);
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSk.receive(receivePacket);
			String dataNhan = new String(receivePacket.getData());
			System.out.println("Server response: "+dataNhan);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
	}
}
