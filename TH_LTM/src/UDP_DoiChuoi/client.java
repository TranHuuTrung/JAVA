package UDP_DoiChuoi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class client {
	public final static String SERVER_IP = "127.0.0.1";
	public final static int SERVER_PORT = 3000;
	public static byte[] SEND;
	public static byte[] RECEIVED = new byte[1024];
	
	public static void main(String[] args) throws IOException {
		DatagramSocket dSocket = null;
		try {
			dSocket = new DatagramSocket();
			System.out.println("Client started: ");
			
			InetAddress Ipserver = InetAddress.getByName(SERVER_IP);
			while (true) {
				System.out.println("Enter your string: ");
				InputStreamReader isr = new InputStreamReader(System.in); //nhap
				BufferedReader bReader = new BufferedReader(isr); //mot chuoi
				String chuoi = bReader.readLine();//tu ban phim
				chuoi = chuoi.trim();
				int len = chuoi.length();
				byte[] data = chuoi.getBytes(); //doi chuoi ra mang byte
				
				//tao gui goi tin len server
				DatagramPacket dataGui = new DatagramPacket(data, data.length, Ipserver, SERVER_PORT);
				dSocket.send(dataGui);
				
				//nhan du lieu, qua tring bi nghen cho den khi co du lieu
				DatagramPacket dataNhan = new DatagramPacket(RECEIVED, RECEIVED.length);
				dSocket.receive(dataNhan);
				
				//hien thi ra man hinh
				String ketqua = new String(dataNhan.getData(), 0, dataNhan.getLength());
				System.out.println("Received: ");
				System.out.println("Chuoi Thuong: "+ ketqua.substring(0,1*len));
				System.out.println("Chuoi Hoa: "+ ketqua.substring(1*len,2*len));
				System.out.println("Do Dai: "+ ketqua.substring(2*len,ketqua.length()));
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(dSocket != null) {
				dSocket.close();
			}
		}
	}
}
