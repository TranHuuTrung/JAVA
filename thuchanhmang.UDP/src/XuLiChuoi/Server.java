package XuLiChuoi;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
	private static final int PORT = 2004;
	private DatagramSocket serverSocket;
	
	public Server(int portHost) {
		try {
			serverSocket = new DatagramSocket(portHost);
			System.out.println("Server is running ...");
			byte[] receiveData = new byte[1024];
			byte[] sendData;
			while (true) {
				//nhan du lieu
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
				
				//lay dia chi may nhan tu goi tin nhan
				InetAddress iPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();
				
				//lay du lieu
				String mess = new String(receivePacket.getData(), 0, receivePacket.getLength());
				String thuong = mess.toLowerCase();
				String hoa = mess.toUpperCase();
				int sotu = countWords(mess);
				//tao goi tin gui
				String stringSend = thuong + hoa + sotu;
				sendData = stringSend.getBytes();
				//tao 1 datagrampacket chua du lieu va dia chi may nhan
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, iPAddress, port);
				serverSocket.send(sendPacket);
			}
			
		} catch (IOException e) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE,null,e);
		}
	}
	
	public static void main(String[] args) {
		new Server(PORT);
	}
	
	private int countWords(String s) {
		int countWords = 0;
		String[] arrReceive = s.split("[ ,.]");
		countWords = arrReceive.length;
		for(int i = 0; i< arrReceive.length; i++) {
			if ((arrReceive[i].equals("")) || (arrReceive[i].equals(",")) || (arrReceive[i].equals("."))){ 
				countWords--; 
            }
		}
		return countWords;
	}
}
