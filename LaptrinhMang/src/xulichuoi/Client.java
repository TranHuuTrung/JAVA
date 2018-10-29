package xulichuoi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static final String IP = "localhost";
	public static final int PORT = 8001;
	public static void main(String[] args) throws IOException {
		Socket socket = null;
		String KetQua = "";
		try {
			socket = new Socket(IP, PORT);
			System.out.println("Ket noi toi server "+ socket);
			Scanner kb = new Scanner(System.in);
			DataInputStream dataInput = new DataInputStream(socket.getInputStream());
			DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());
			while (true) {
				System.out.print("Nhap string: ");
				String chuoiGui = kb.nextLine();
				String dataGui = chuoiGui.trim();
				int len = dataGui.length();
				//gui
				dataOutput.writeUTF(dataGui);
				//nhan result
				KetQua = dataInput.readUTF();
				System.out.println("Chuoi Thuong: "+ KetQua.substring(0, 1*len));
				System.out.println("Chuoi Hoa: "+ KetQua.substring(1*len, 2*len));
				System.out.println("So chu: "+ KetQua.substring(2*len, KetQua.length()));
				kb = kb.reset();
			}
		} catch (IOException e) {
			System.out.println("Khong the ket noi server");
		}
	}
}
