package XuLiChuoi;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static final String IP = "127.0.0.1";
    public static final int PORT = 2808;
    private DatagramSocket severSocket;

    public Server(int portHost) {
        try {
            severSocket = new DatagramSocket(portHost);
            System.out.println("Server is running ...");
            byte[] receiveData = new byte[1024];
            byte[] senData;
            while (true) {
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				severSocket.receive(receivePacket);
				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();
				String request = new String(receivePacket.getData());
				String fixedData = "";
				for (int i = 0; i < request.length(); i++)
				{
					if (request.charAt(i) != 0) fixedData += request.charAt(i);
				}
				
				String sSend = fixedData.toLowerCase()+" "+fixedData.toUpperCase()
	                                +" "+countWords(fixedData);
	                        senData = sSend.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(senData, senData.length,IPAddress,port);
				severSocket.send(sendPacket);
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new Server( PORT);
    }

    private int countWords(String s) {
        int wordCount = 0;
        
        boolean insideWord = false;

        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i)) && i != s.length() - 1) {
                insideWord = true;
            }
            else if (!Character.isLetter(s.charAt(i)) && insideWord) {
                wordCount++;
                insideWord = false;
            }
            else if (Character.isLetter(s.charAt(i)) && i == s.length() - 1) {
                wordCount++;
            }
        }
        return wordCount;
    }
}