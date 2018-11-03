package UDP_XuLiChuoi;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.crypto.Data;

public class Server {
	public static void main(String[] args) throws Exception {
		//gan cong cho chuong trinh
		DatagramSocket serverSK = new DatagramSocket(9002);
		System.out.println("Server started!");
		//tao cac mang byte de nhan va gui du lieu
		byte[] sendData;
		byte[] receiveData = new byte[1024];
		while (true) {
			//tao goi tin rong de nhan du lieu tu client
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			//nhan du lieu tu client
			serverSK.receive(receivePacket);
			//lay dia chi IP cua client
			InetAddress IPAddress = receivePacket.getAddress();
			//lay port cua client
			int port = receivePacket.getPort();
			//XU li
			String DataNhan = new String(receivePacket.getData(), 0, receivePacket.getLength());
			String chuoiHoa = myToLowerUppercase(DataNhan);
			String chuoiThuong = myToLowercase(DataNhan);
			String chuoiHoaThuong = myToLowerUppercase(DataNhan);
			String chuoiHoaDauTien = capitalWord(DataNhan);
			int soTu = countWords(DataNhan);
			int soVowel = countVowel(DataNhan);
			String chuoiSo = soTu+"#"+soVowel;
			String DataGui = (chuoiThuong + chuoiHoa + chuoiHoaThuong + chuoiHoaDauTien + chuoiSo);
			sendData = DataGui.getBytes();
			//tao goi tin rong de gui data cho client
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			//gui cho client
			serverSK.send(sendPacket);
			sendData = new byte[1024];
			receiveData = new byte[1024];
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
