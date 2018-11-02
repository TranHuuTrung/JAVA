package TCP_Thread_XuLiChuoi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.UnknownHostException;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(9000);
			System.out.println("Started Server!");
			while (true) {
				Socket sk = serverSocket.accept();
				Thread1 t1 = new Thread1(sk);
				Thread t = new Thread(t1);
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
