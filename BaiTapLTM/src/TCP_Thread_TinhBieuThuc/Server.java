package TCP_Thread_TinhBieuThuc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(8888);
			System.out.println("Started Server!");
			while (true) {
				Socket sk = server.accept();
				Thread2 t = new Thread2(sk);
				t.start();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
