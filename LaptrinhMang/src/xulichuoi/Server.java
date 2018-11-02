package xulichuoi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
	public static final String IP = "localhost";
	public static final int PORT = 8001;
	private ServerSocket serverSocket;
	
	public Server(String host, int port) throws IOException {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server started!");
			while (true) {
				try {
					SocketThread socket = new SocketThread(serverSocket.accept());
					new Thread(socket).start();
				} catch (IOException e) {
					System.out.println("Ket noi that bai "+ e);
				}	
			}
		} catch (IOException ie) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ie);
		} 
	}
	
	public static void main(String[] args) throws IOException {
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
			String dataNhan = serverReceive.trim();
			String serverResponse = dataNhan.toUpperCase() + dataNhan.toLowerCase()+ countWords(dataNhan);
			dos.writeUTF(serverResponse);
		} catch (IOException e) {
			Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, e);
		}
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
