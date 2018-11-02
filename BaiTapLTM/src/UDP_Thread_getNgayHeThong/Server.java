package UDP_Thread_getNgayHeThong;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

public class Server {
	public static void main(String[] args) throws SocketException {
		new Thread(new ServerThread()).start();
	}
}

class ServerThread implements Runnable{
	private DatagramSocket serverSocket;
	private byte[] in;
	private byte[] out;
	public ServerThread() throws SocketException {
		serverSocket = new DatagramSocket(10000);
	}
	@Override
	public void run() {
		while (true) {
			try {
				in = new byte[1024];
				out = new byte[1024];
				DatagramPacket receivePacket = new DatagramPacket(in, in.length);
				serverSocket.receive(receivePacket);
				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();
				System.out.println(IPAddress+"  "+port);
				//send
				//lay ngay gio cua he thong de gui cho client
				String request = new String(receivePacket.getData());
				if(request.trim().equals("getDate")) {
					out = new Date().toString().getBytes();
				} else {
					out = "Server not know what you want!".getBytes();
				}
				DatagramPacket sendPacket = new DatagramPacket(out, out.length, IPAddress, port);
				serverSocket.send(sendPacket);
			} catch (IOException ie) {
				ie.printStackTrace();
			}
		}
	}
	
}
