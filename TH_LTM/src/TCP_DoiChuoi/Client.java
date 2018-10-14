package TCP_DoiChuoi;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
	public final static String HOST = "127.0.0.1";
	public final static int SERVER_PORT = 2000;
	
	public static void main(String[] args) throws IOException {
		Socket socket = null;
		String KetQua = "";
		try {
			socket = new Socket(HOST, SERVER_PORT);
			System.out.println("Ket noi toi server "+ socket);
			while (true) {
				System.out.print("Nhap string: ");
				InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader buffer = new BufferedReader(isr);
				String chuoiGui = buffer.readLine();
				chuoiGui = chuoiGui.trim();
				int len = chuoiGui.length();
				DataInputStream dataInput = new DataInputStream(socket.getInputStream());
				DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());
				//gui
				dataOutput.writeUTF("BatDau,"+chuoiGui);
				//nhan result
				KetQua = dataInput.readUTF();
				String cmd = KetQua.substring(0, KetQua.indexOf(","));
				String msg = KetQua.substring(KetQua.indexOf(",")+1);
				if (cmd.equals("Response")) {
					System.out.println("Chuoi Thuong: "+ msg.substring(0, 1*len));
					System.out.println("Chuoi Hoa: "+ msg.substring(1*len, 2*len));
					System.out.println("So chu: "+ msg.substring(2*len, msg.length()));
				}
			}
		} catch (IOException e) {
			System.out.println("Khong the ket noi server");
		} finally {
			if (socket != null) {
				socket.close();
			}
		}
	}
}
