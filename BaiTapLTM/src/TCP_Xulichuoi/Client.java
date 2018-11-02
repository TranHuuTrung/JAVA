package TCP_Xulichuoi;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 2000);
			System.out.println("connected to server, port "+ socket);
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			InputStreamReader isr = new InputStreamReader(System.in); //nhap
			BufferedReader buff = new BufferedReader(isr);// ki tu
			while (true) {
				System.out.println("Nhap chuoi: ");
				String msgInput = buff.readLine();
				String DataGui = msgInput.trim();
				int len = DataGui.length();
				//gui data
				dos.writeUTF(DataGui);
				//nhan data
				String kqua = dis.readUTF();
				System.out.println("chuoi Hoa: "+kqua.substring(0, 1*len));
				System.out.println("chuoi Thuong: "+ kqua.substring(1*len, 2*len));
				System.out.println("chuoi Hoa Thuong: "+ kqua.substring(2*len, 3*len));
				System.out.println("chuoi Hoa ki tu dau tien: "+ kqua.substring(3*len, 4*len));
				String chuoiso = kqua.substring(4*len, kqua.length());
				String [] arrChuoi = chuoiso.split("#");
				System.out.println("so Tu: "+ arrChuoi[0]);
				System.out.println("so nguyen am: "+ arrChuoi[1]);
			}
		} catch (UnknownHostException unkhost) {
			unkhost.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
