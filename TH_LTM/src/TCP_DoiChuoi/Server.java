package TCP_DoiChuoi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public final static int SERVER_PORT = 2000;
	public static void main(String[] args) throws IOException {
		ServerSocket server = null;
		try {
			System.out.println("Khoi tao server "+ SERVER_PORT);
			server = new ServerSocket(SERVER_PORT);
			System.out.println("Server dang chay o port "+ server);
			System.out.println("Wating a client...");
			while (true) {
				try {
					Socket client = server.accept();
					DataInputStream dataIS = new DataInputStream(client.getInputStream());
					DataOutputStream dataOS = new DataOutputStream(client.getOutputStream());
					String chuoi = "";
					String chuoiHoa = "";
					String chuoiThuong  = "";
					String result = "";
					int count = 0;
					while(true) {
						chuoi = dataIS.readUTF(); //doc chuoi ma hoa dang UTF8
						String cmd = chuoi.substring(0, chuoi.indexOf(","));
						String msg = chuoi.substring(chuoi.indexOf(",")+1);
						if(cmd.equals("BatDau")) {
							chuoiThuong = msg.toLowerCase();
							chuoiHoa = msg.toUpperCase();
							count = msg.split(" ").length;
							result = (chuoiThuong + chuoiHoa + count);
							//gui
							dataOS.writeUTF("Response,"+result);
						} else {
							client.close();
						}
						
					}
				} catch (IOException e) {
					System.out.println("Ket noi that bai "+ e);
				}
			}
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (server != null) {
				server.close();
			}
		}
	}
}
