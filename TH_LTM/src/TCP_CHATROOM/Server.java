package TCP_CHATROOM;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
	public final static int SERVER_PORT = 5200;
	public Vector<ServerThreadHandler> groupChat = new Vector<ServerThreadHandler>();
	
	public Server(){
		ServerSocket server;
		Socket connect;
		try {
			System.out.println("Khoi tao server port: "+ SERVER_PORT);
			server = new ServerSocket(SERVER_PORT);
			System.out.println("Server dang chay "+ server);
			System.out.println("Waiting client......");
			while (true) {
				connect = server.accept();
				System.out.println(connect + "ket noi den server");
				new ServerThreadHandler(this, connect).start();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) throws IOException {
		new Server();
	}
}
