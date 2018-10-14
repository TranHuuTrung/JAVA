package ChatRoom;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Server extends Thread {
	public final static int PORT = 2808;
    private final static int BUFFER = 1024;
    
    private DatagramSocket socket;
    private ArrayList<InetAddress> clientAddresses;
    private ArrayList<Integer> clientPorts;
    private HashSet<String> existingClients;

	public static void main(String[] args) throws IOException {
		Server s = new Server();
		s.start();
	}
	
	public Server() throws IOException {
		socket = new DatagramSocket(PORT);
        clientAddresses = new ArrayList<InetAddress>();
        clientPorts = new ArrayList<Integer>();
        existingClients = new HashSet<String>();
	}
	
	public void run() {
		byte[] buf = new byte[BUFFER];
        while (true) {
            try {
                Arrays.fill(buf, (byte)0);
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                
                @SuppressWarnings("deprecation")
				String content = new String(buf, buf.length);
                
                String fixedContent = "";
                for (int i = 0; i < content.length(); i++) {
                	if ((int)content.charAt(i) != 0) fixedContent += content.charAt(i);
                }
                
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();
                
                String id = clientAddress.toString() + "," + clientPort;
                if (!existingClients.contains(id)) {
                    existingClients.add( id );
                    clientPorts.add(clientPort);
                    clientAddresses.add(clientAddress);
                }
                
                System.out.println(id + " : " + fixedContent);
                
                byte[] data = (fixedContent).getBytes();
                
                for (int i=0; i < clientAddresses.size(); i++) {
                    InetAddress cl = clientAddresses.get(i);
                    int cp = clientPorts.get(i);
                    packet = new DatagramPacket(data, data.length, cl, cp);
                    socket.send(packet);
                }
            } catch(Exception e) {
                System.err.println(e);
            }
        }
	}
	
}

class Process extends Thread {
	Socket soc;
	
	public Process(Socket soc) {
		this.soc = soc;
	}
	
	public void run() {
		
	}
}