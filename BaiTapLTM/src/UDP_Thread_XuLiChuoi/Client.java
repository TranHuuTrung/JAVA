package UDP_Thread_XuLiChuoi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
	public static void main(String[] args) throws IOException {
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		DatagramSocket clientSK = new DatagramSocket();
		System.out.println("Client address: "+ clientSK.getLocalAddress());
		System.out.println("client port: "+clientSK.getLocalPort());
		InetAddress IPAddress = InetAddress.getByName("localhost");
		//tao mang byte de gui va nhan du lieu
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		
		while (true) {
			System.out.print("Nhap chuoi: ");
			String st = inFromUser.readLine();
			String message = st.trim();
			int len = message.length();
			sendData = message.getBytes();
			//tao goi tin de gui
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9003);
			clientSK.send(sendPacket);
			//tao goi tin nhan data
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSK.receive(receivePacket);
			String result = new String(receivePacket.getData());
			System.out.println("Chuoi Thuong: "+result.substring(0,  1*len));
			System.out.println("Chuoi Hoa: "+result.substring(1*len, 2*len));
			System.out.println("Chuoi Hoa Thuong: "+result.substring(2*len, 3*len));
			System.out.println("Chuoi Capital: "+result.substring(3*len, 4*len));
			String chuoiso = result.substring(4*len, result.length());
			String [] arrChuoi = chuoiso.split("#");
			System.out.println("so Tu: "+ arrChuoi[0]);
			System.out.println("so nguyen am: "+ arrChuoi[1]);
			sendData = new byte[1024];
			receiveData = new byte[1024];
		}
	}
}
