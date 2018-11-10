package GiaoDien;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(8000);
			System.out.println("Started server!");
			while (true) {
				Socket socket  = serverSocket.accept();
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				ThreadXuLi tr = new ThreadXuLi(socket, dis, dos);
				Thread thread = new Thread(tr);
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
