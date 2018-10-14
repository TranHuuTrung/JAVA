package TCP_CHATROOM;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client {
	public final static int SERVER_PORT = 5200;
	public final static String HOST = "127.0.0.1";
	
	public JFrame frame;
	public JTextArea Room;
	public JTextField msg;
	public JTextArea Joiners;
	public String nickName;
	
	public Socket socket;
	public DataInputStream dataInS;
	public DataOutputStream dataOuS;
	
	public Client(String nickName) {
		this.nickName = nickName;
		
		this.frame = new JFrame("Chat Room");
		this.frame.setSize(480, 400);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLayout(null);
		
		JLabel lb = new JLabel("Welcome to Chat room, "+ this.nickName);
		lb.setBounds(20, 10, 300, 50);
		this.frame.add(lb);
		
		this.Room = new JTextArea("");
		this.Room.setBounds(20, 50, 300, 250);
		this.Room.setEditable(false);
		this.frame.add(Room);
		
		JLabel lbSend = new JLabel("Send");
		lbSend.setBounds(20, 325, 50, 25);
		this.frame.add(lbSend);
		
		this.msg = new JTextField("");
		this.msg.setBounds(100, 325, 200, 25);
		msg.addActionListener(new SendActionListener(this));
		this.frame.add(msg);
		
		JButton OK = new JButton("Send");
		OK.setBounds(320, 325, 80, 25);
		OK.addActionListener(new SendActionListener(this));
		this.frame.add(OK);
		
		JLabel lbjoiners = new JLabel("Joiners");
		lbjoiners.setBounds(360, 10, 50, 50);
		this.frame.add(lbjoiners);
		
		this.Joiners = new JTextArea("");
		this.Joiners.setBounds(330, 50, 120, 250);
		this.Joiners.setEditable(false);
		this.frame.add(Joiners);
		
		frame.setVisible(true);
		
		try {
			socket = new Socket(HOST, SERVER_PORT);
			this.dataInS = new DataInputStream(socket.getInputStream());
			this.dataOuS = new DataOutputStream(socket.getOutputStream());
			new ClientThreadHandler(this).start();
			this.dataOuS.writeUTF("ThamGia,"+ this.nickName);
		} catch (IOException ie) {
			this.frame.dispose();
		}
		
	}
}
