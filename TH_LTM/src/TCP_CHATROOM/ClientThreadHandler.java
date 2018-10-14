package TCP_CHATROOM;

import java.io.IOException;

public class ClientThreadHandler extends Thread {
	Client client;
	public ClientThreadHandler(Client client) {
		this.client = client;
	}
	
	public void run() {
		String ch ="";
		try {
			while (true) {
				ch = client.dataInS.readUTF();
				String cmd = ch.substring(0, ch.indexOf(","));
				String msg = ch.substring(ch.indexOf(",")+1);
				if (cmd.equals("Msg")) {
					this.client.Room.setText(msg+"\n"+client.Room.getText());
				} else {
					this.client.socket.close();
				}
			}
		} catch (IOException e) {
			client.frame.dispose();
			new LoginFrame("");
		}
	}

}
