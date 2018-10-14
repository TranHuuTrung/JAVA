package TCP_CHATROOM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SendActionListener implements ActionListener {
	Client client;
	public SendActionListener(Client client) {
		this.client = client;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!client.msg.getText().equals("")) {
			client.Room.setText(client.nickName+" >> "+ client.msg.getText()+"\n"+ client.Room.getText());
			try {
				this.client.dataOuS.writeUTF("Msg," + client.msg.getText());
			} catch (IOException e2) {
				client.frame.dispose();
				new LoginFrame("");
			}
			client.msg.setText("");
			
		}

	}

}
