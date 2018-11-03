package UDP_Thread_TinhBieuThuc;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.util.Date;

public class Server {
	public static void main(String[] args) throws SocketException {
		new Thread(new ServerThread()).start();
	}
}

class ServerThread implements Runnable{
	private DatagramSocket serverSocket;
	private byte[] in;
	private byte[] out;
	public ServerThread() throws SocketException {
		serverSocket = new DatagramSocket(9005);
		System.out.println("Server started!");
	}
	@Override
	public void run() {
		while (true) {
			try {
				in = new byte[1024];
				out = new byte[1024];
				DatagramPacket receivePacket = new DatagramPacket(in, in.length);
				serverSocket.receive(receivePacket);
				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();
				//xu li
				String dataNhan = new String(receivePacket.getData(), 0, receivePacket.getLength());
				String chuoiChuanHoa = XuLiTinhToan.refine(dataNhan);
				String bieuThucHauTo = XuLiTinhToan.toPostFix(chuoiChuanHoa);
				float ketqua = XuLiTinhToan.caculate(dataNhan);
				new DecimalFormat("%.###").format(ketqua);
				String dsToanTu = XuLiTinhToan.cacToanTu(dataNhan);
				String result = chuoiChuanHoa+"#"+bieuThucHauTo+"#"+ketqua+"#"+dsToanTu;
				out = result.getBytes();
				//send
				DatagramPacket sendPacket = new DatagramPacket(out, out.length, IPAddress, port);
				serverSocket.send(sendPacket);
			} catch (IOException ie) {
				ie.printStackTrace();
			}
		}
	}
	
}
