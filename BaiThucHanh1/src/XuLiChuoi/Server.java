package XuLiChuoi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
	public static final String IP = "localhost";
	public static final int PORT = 7004;
	private ServerSocket serverSocket;

	public Server(String host, int port) {
		try {
			serverSocket = new ServerSocket(port);
			SocketThread socket = new SocketThread(serverSocket.accept());
			new Thread(socket).start();
		} catch (IOException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void main(String[] args) {
		new Server(IP, PORT);
	}
}

class SocketThread implements Runnable {
	private Socket s;

	public SocketThread(Socket s) {
		this.s = s;
	}

	@Override
	public void run() {
		try {
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			String serverReceive = dis.readUTF();
			String serverResponse = serverReceive.toUpperCase() + " " + serverReceive.toLowerCase() + " "
					+ countWords(serverReceive);

			dos.writeUTF(serverResponse);
		} catch (IOException ex) {
			Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private int countWords(String s) {
		int wordCount = 0;
		boolean insideWord = false;
		for (int i = 0; i < s.length(); i++) {
			if (Character.isLetter(s.charAt(i)) && i != s.length() - 1) {
				insideWord = true;
			} else if (!Character.isLetter(s.charAt(i)) && insideWord) {
				wordCount++;
				insideWord = false;
			} else if (Character.isLetter(s.charAt(i)) && i == s.length() - 1) {
				wordCount++;
			}
		}
		return wordCount;
	}
}
