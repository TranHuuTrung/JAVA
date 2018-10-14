package TCP_CHATROOM;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThreadHandler extends Thread {
	Server server;
	public Socket incoming;
	public DataInputStream dataIS;
	public DataOutputStream dataOS;
	public String name;
	
	public ServerThreadHandler(Server sv, Socket i) {
		this.server = sv;
		this.incoming = i;
		try {
			this.dataIS = new DataInputStream(incoming.getInputStream());
			this.dataOS = new DataOutputStream(incoming.getOutputStream());
			
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void run() {
		String ch="";
		try {
			ch = dataIS.readUTF();
			String cmd = ch.substring(0, ch.indexOf(","));
			String msg = ch.substring(ch.indexOf(",")+1);
			if (!cmd.equals("ThamGia")) {
				incoming.close();
			}
			System.out.println("Hello "+ msg);
			this.name = msg;
			this.server.groupChat.add(this);
			System.out.println(this.server.groupChat);
			while (true) {
				ch = dataIS.readUTF();
				cmd = ch.substring(0, ch.indexOf(","));
				msg = ch.substring(ch.indexOf(",")+1);
				if (cmd.equals("Msg")) {
					for (int i = 0; i < this.server.groupChat.size(); i++) {
						ServerThreadHandler temp = this.server.groupChat.get(i);
						if (temp!= this) {
							temp.dataOS.writeUTF("Msg,"+this.name+" >> "+ msg);
						}
					}
				} else {
					incoming.close();
					this.server.groupChat.remove(this);
				}
			}
			
		} catch (IOException e1) {
			server.groupChat.remove(this);
		}
	}
}
