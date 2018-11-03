package UDP_Thread_TinhBieuThuc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) {
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		try {
			DatagramSocket clientSk = new DatagramSocket();
			InetAddress IPAddress = InetAddress.getByName("localhost");
			//tao mang byte de nhan va gui data
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			//nhap chuoi bieu thuc tinh
			System.out.print("Nhap bieu thuc: ");
			String strBieuThuc = inFromUser.readLine();
			sendData = strBieuThuc.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9005);
			clientSk.send(sendPacket);
			//nhan data from server
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSk.receive(receivePacket);
			String result = new String(receivePacket.getData());
			String[] res = result.split("#");
			String chuoiChuanHoa = res[0];
			String chuoiHauTo = res[1];
			String ketquaTinh = res[2];
			String dsToanTu = res[3];
			System.out.println("Bieu thuc hau to: "+chuoiHauTo);
			System.out.println("Ds toan tu : "+dsToanTu);
			System.out.println("Ket qua : "+ketquaTinh);
 		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e1) {
			e1.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
