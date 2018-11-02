package TCP_Thread_XuLiChuoi;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.rmi.UnknownHostException;

public class Client {
	public static void main(String[] args) {
		try {
			Socket client = new Socket("localhost", 9000);
			System.out.println("Client is connected to server in "+ client);
			DataInputStream dis = new DataInputStream(client.getInputStream());
			DataOutputStream dos = new DataOutputStream(client.getOutputStream());
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader buff = new BufferedReader(isr);
			while (true) {
				// gui data len server
				System.out.println("Nhap chuoi : ");
				String msg = buff.readLine();
				String dataGui = msg.trim();
				int len = dataGui.length();
				dos.writeUTF(dataGui);
				// nhan data
				String kqua = dis.readUTF();
				System.out.println("Server response: ");
				String chuoiThuong = kqua.substring(0, 1*len);
				String chuoiHoa = kqua.substring(1*len, 2*len);
				String chuoiHoaThuong = kqua.substring(2*len, 3*len);
				String chuoiCapital = kqua.substring(3*len, 4*len);
				String chuoiSo = kqua.substring(4*len, kqua.length());
				String[] tachSo = chuoiSo.split("#");
				String soTu = tachSo[0];
				String soNguyenAm = tachSo[1];
				
				System.out.println("Chuoi Thuong: "+ chuoiThuong);
				System.out.println("Chuoi Hoa: "+ chuoiHoa);
				System.out.println("Chuoi Hoa Thuong: "+ chuoiHoaThuong);
				System.out.println("Chuoi Hoa dau tien: "+ chuoiCapital);
				System.out.println("So tu: "+ soTu);
				System.out.println("So nguyen am: "+ soNguyenAm);
				
//				System.out.println(kqua);
			}
			
		} catch (UnknownHostException unkHost) {
			unkHost.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
