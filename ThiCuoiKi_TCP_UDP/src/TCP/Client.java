package TCP;

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
			Socket clientsk = new Socket("localhost", 9999);
			System.out.println("connect server port "+ clientsk);
			DataInputStream dis = new DataInputStream(clientsk.getInputStream());
			DataOutputStream dos = new DataOutputStream(clientsk.getOutputStream());
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader buff = new BufferedReader(isr);
			while(true) {
				System.out.println("Nhap chuoi : ");
				String msgInput = buff.readLine();
				String dataGui = msgInput.trim();
				int len = dataGui.length();
				//gui data
				dos.writeUTF(dataGui);
				//nhan result
				String kqua = dis.readUTF();
				System.out.println("Chuoi Hoa: "+ kqua.substring(0,  1*len));
				System.out.println("Chuoi Thuong: "+ kqua.substring(1*len, 2*len));
				System.out.println("Chuoi Hoa Thuong: "+ kqua.substring(2*len, 3*len));
				System.out.println("Chuoi Hoa Ki Tu dau tien: "+ kqua.substring(3*len, 4*len ));
				String chuoiso = kqua.substring(4*len, kqua.length());
				String []arrChuoiSo = chuoiso.split("#");
				System.out.println("So Tu: "+ arrChuoiSo[0]);
				System.out.println("so Nguyen Am : "+ arrChuoiSo[1]);
			}
		} catch (UnknownHostException unknownhost) {
			unknownhost.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
