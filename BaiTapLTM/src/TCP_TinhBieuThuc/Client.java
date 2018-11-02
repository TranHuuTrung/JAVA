package TCP_TinhBieuThuc;

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
			Socket clientSk = new Socket("localhost", 8888);
			System.out.println("Client is running in "+ clientSk);
			DataInputStream dis = new DataInputStream(clientSk.getInputStream());
			DataOutputStream dos = new DataOutputStream(clientSk.getOutputStream());
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader buffer = new BufferedReader(isr);
			while (true) {
				System.out.println("Nhap bieu thuc tinh: ");
				String msgInput = buffer.readLine();
				String dataGui = msgInput.trim();
				// gui data
				dos.writeUTF(dataGui);
				//nhan data
				String kqua = dis.readUTF();
				String[] result = kqua.split("#");
				String chuoiChuanHoa = result[0];
				String chuoiHauTo = result[1];
				String ketquaTinh = result[2];
				String dsToanTu = result[3];
				System.out.println("Bieu thuc hau to: "+chuoiHauTo);
				System.out.println("Ds toan tu : "+dsToanTu);
				System.out.println("Ket qua : "+ketquaTinh);
			}
		} catch (UnknownHostException unkhost) {
			unkhost.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
}
