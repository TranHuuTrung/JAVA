package UDP_TinhBieuThuc;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
	public static void main(String[] args) throws IOException {
		try {
			//gan cong cho chuong trinh
			DatagramSocket serverSK = new DatagramSocket(9004);
			System.out.println("Server Started!");
			// tao cac mang byte de nhan va gui data
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			while (true) {
				//tao goi rong de nhan du lieu
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSK.receive(receivePacket);
				//lay IP
				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();
				//Xu li 
				String dataNhan = new String(receivePacket.getData(), 0, receivePacket.getLength());
				String chuoiChuanHoa = XuLiTinhToan.refine(dataNhan);
				String bieuThucHauTo = XuLiTinhToan.toPostFix(chuoiChuanHoa);
				float ketqua = XuLiTinhToan.caculate(dataNhan);
				String dsToanTu = XuLiTinhToan.cacToanTu(dataNhan);
				String result = chuoiChuanHoa+"#"+bieuThucHauTo+"#"+ketqua+"#"+dsToanTu;
				sendData = result.getBytes();
				//gui ve cho client
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
				serverSK.send(sendPacket);
			}
			
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
	}
}
