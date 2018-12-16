package huutrung.dacsnm.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(8000);
			System.out.println("Started Server! ");
			while(true) {
				Socket sk = serverSocket.accept();
				Thread thread = new ThreadHandler(sk);
				thread.start();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
