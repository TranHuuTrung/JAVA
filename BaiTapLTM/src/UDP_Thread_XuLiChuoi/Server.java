package UDP_Thread_XuLiChuoi;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		serverSocket = new DatagramSocket(9003);
		System.out.println("Start server!");
	}
	@Override
	public void run() {
		while (true) {
			in = new byte[1024];
			out = new byte[1024];
			try {
				DatagramPacket receivePacket = new DatagramPacket(in, in.length);
				serverSocket.receive(receivePacket);
				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();
				//xu li
				String DataNhan = new String(receivePacket.getData(), 0 , receivePacket.getLength());
				String chuoiHoa = myToLowerUppercase(DataNhan);
				String chuoiThuong = myToLowercase(DataNhan);
				String chuoiHoaThuong = myToLowerUppercase(DataNhan);
				String chuoiHoaDauTien = capitalWord(DataNhan);
				int soTu = countWords(DataNhan);
				int soVowel = countVowel(DataNhan);
				String chuoiSo = soTu+"#"+soVowel;
				String DataGui = (chuoiThuong + chuoiHoa + chuoiHoaThuong + chuoiHoaDauTien + chuoiSo);
				//send client
				out = DataGui.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(out, out.length, IPAddress, port);
				serverSocket.send(sendPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	// ham bien doi ki tu thanh ki tu hoa
		private static String myUpper(String s) {
			char c = s.charAt(0);
			if (c >= 'a' && c <= 'z') {
				c = (char) (c - 32);
			}
			return String.valueOf(c);
		}

		// ham bien doi ki tu thanh ki tu hoa
		private static String myLower(String s) {
			char c = s.charAt(0);
			if (c >= 'A' && c <= 'Z') {
				c = (char) (c + 32);
			}
			return String.valueOf(c);
		}

		// ham bien doi ki tu hoa->thuong, thuong->hoa
		private static String myLowerUpper(String s) {
			char c = s.charAt(0);
			if (c >= 'A' && c <= 'Z') {
				c = (char) (c + 32);
			} else if (c >= 'a' && c <= 'z') {
				c = (char) (c - 32);
			} else {
			}
			return String.valueOf(c);
		}

		// bien doi chuoi thanh chuoi hoa
		private static String myToUppercase(String s) {
			char[] arrChar = s.toCharArray();
			String result = "";
			for (int i = 0; i < arrChar.length; i++) {
				result += myUpper(String.valueOf(arrChar[i]));
			}
			return result;
		}

		// bien doi chuoi thanh chuoi thuong
		private static String myToLowercase(String s) {
			char[] arrChar = s.toCharArray();
			String result = "";
			for (int i = 0; i < arrChar.length; i++) {
				result += myLower(String.valueOf(arrChar[i]));
			}
			return result;
		}

		// bien doi chuoi thanh vua Hoa vua Thuong
		private static String myToLowerUppercase(String s) {
			char[] arrChar = s.toCharArray();
			String result = "";
			for (int i = 0; i < arrChar.length; i++) {
				result += myLowerUpper(String.valueOf(arrChar[i]));
			}
			return result;
		}

		// ham doi chuoi thanh chuoi co ki tu hoa dau tien
		private static String capitalWord(String s) {
			String words[] = s.split("\\s");
			String result = "";
			for (int i = 0; i < words.length; i++) {
				if (words[i].length() <= 0) {
					result += " ";
				} else {
					String first = words[i].substring(0, 1);
					String conlai = words[i].substring(1);
					result += myUpper(first) + conlai.toLowerCase() + " ";
				}
			}
			return result.trim();
		}

		// dem so tu
		private static int countWords(String s) {
			int soTu = 0;
			String[] arrS = s.split("[ ,.]");
			soTu = arrS.length;
			for (int i = 0; i < arrS.length; i++) {
				if (arrS[i].equals("") || arrS[i].equals(",") || arrS[i].equals(".")) {
					soTu--;
				}
			}
			return soTu;
		}

		// dem s nghuyen am
		private static int countVowel(String s) {
			int soVowel = 0;
			char[] arrS = s.toCharArray();
			List listConditionVowel = new ArrayList<>(Arrays.asList("e","a","u","o","i"));
			for (int i = 0; i < arrS.length; i++) {
				if(listConditionVowel.contains(myLower(String.valueOf(arrS[i])))) {
					soVowel++;
				}
			}
			return soVowel;
		}
	
}