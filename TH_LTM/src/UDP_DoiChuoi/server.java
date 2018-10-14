package UDP_DoiChuoi;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class server {
	public final static int SERVER_PORT = 3000;
	public static byte[] SEND;
	public final static byte[] RECEIVED = new byte[1024];
	
	public static void main(String[] args) throws IOException {
		DatagramSocket dSocket = null;
		try {
			System.out.println("Khoi tao server port "+ SERVER_PORT);
			dSocket = new DatagramSocket(SERVER_PORT);
			System.out.println("Server running, waiting client .....");
			
			while (true) {
				//nhan dulieu
				DatagramPacket dataNhan = new DatagramPacket(RECEIVED, RECEIVED.length);
				dSocket.receive(dataNhan);
				
				//lay dulieu tu goi tin nhan
				String message = new String(dataNhan.getData(), 0, dataNhan.getLength());
				
				System.out.println("Chuoi nhan duoc tu client la : "+ message);
				
				String biendoiThuongMessage = message.toLowerCase();
				String biendoiHoaMessage = message.toUpperCase();
				int demsoTuMessage = message.length();
				
				//tao goi tin de gui
				SEND = (biendoiThuongMessage+biendoiHoaMessage+demsoTuMessage).getBytes();
				//phuong thuc tao 1 diagramPacket chua du lieu va dia chi may nhan
				DatagramPacket responseMessage = new DatagramPacket(SEND, SEND.length, dataNhan.getAddress(), dataNhan.getPort());
				
				dSocket.send(responseMessage);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dSocket != null) {
				dSocket.close();
			}
		}
	}
}
