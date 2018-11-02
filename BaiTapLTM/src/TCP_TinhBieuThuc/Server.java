package TCP_TinhBieuThuc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import TCP_TinhBieuThuc.XuLiTinhToan;
public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(8888);
			System.out.println("Started Server!");
			while (true) {
				Socket sk = server.accept();
				DataInputStream dis = new DataInputStream(sk.getInputStream());
				DataOutputStream dos = new DataOutputStream(sk.getOutputStream());
				while (true) {
					//nhan data
					String chuoiTinh = dis.readUTF();
					//Xu li
					String chuoiChuanHoa = XuLiTinhToan.refine(chuoiTinh);
					String bieuThucHauTo = XuLiTinhToan.toPostFix(chuoiChuanHoa);
					float ketqua = XuLiTinhToan.caculate(chuoiTinh);
					String dsToanTu = XuLiTinhToan.cacToanTu(chuoiTinh);
					String result = chuoiChuanHoa+"#"+bieuThucHauTo+"#"+ketqua+"#"+dsToanTu;
					//gui cho database
					dos.writeUTF(result);
				}
				
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	//ham chuan hoa chuoi
}
