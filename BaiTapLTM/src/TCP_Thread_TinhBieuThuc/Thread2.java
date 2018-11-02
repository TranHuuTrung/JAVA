package TCP_Thread_TinhBieuThuc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import TCP_Thread_TinhBieuThuc.XuLiTinhToan;
public class Thread2 extends Thread{
	final Socket socket;
	//Constructor
	public Thread2(Socket socket) {
		this.socket = socket;
		System.out.println("Call thread!");
		System.out.println("Socket address "+socket.getInetAddress().getHostAddress());
		System.out.println("Socket port "+ socket.getPort());
	}
	@Override
	public void run() {
		try {
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			while (true) {
				//nhan data
				String chuoiTinh = dis.readUTF();
				//Xu li
				String chuoiChuanHoa = XuLiTinhToan.refine(chuoiTinh);
				String bieuThucHauTo = XuLiTinhToan.toPostFix(chuoiChuanHoa);
				float ketqua = XuLiTinhToan.caculate(chuoiTinh);
				String dsToanTu = XuLiTinhToan.cacToanTu(chuoiTinh);
				String result = chuoiChuanHoa+"#"+bieuThucHauTo+"#"+ketqua+"#"+dsToanTu;
				//gui cho client
				dos.writeUTF(result);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
