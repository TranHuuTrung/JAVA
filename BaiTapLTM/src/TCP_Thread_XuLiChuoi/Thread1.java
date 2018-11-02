package TCP_Thread_XuLiChuoi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Thread1 implements Runnable {
	final Socket socket;
	//Constructor
	public Thread1(Socket socket) {
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
			while(true) {
				// nhan data
				String dataNhan = dis.readUTF();
				//xuli
				String chuoiThuong = myToLowercase(dataNhan);
				String chuoiHoa = myToUppercase(dataNhan);
				String chuoiHoaThuong = myToLowerUppercase(dataNhan);
				String chuoiCapital = capitalWord(dataNhan);
				int soTu = countWords(dataNhan);
				int soNguyenAm = countVowel(dataNhan);
				String chuoiSo = soTu+"#"+soNguyenAm;
				String dataGui = chuoiThuong+chuoiHoa+chuoiHoaThuong+chuoiCapital+chuoiSo;
				//gui data
				dos.writeUTF(dataGui);
			}
		} catch (IOException ie) {
			ie.printStackTrace();
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
			return result;
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

		// dem s nguyen am
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
