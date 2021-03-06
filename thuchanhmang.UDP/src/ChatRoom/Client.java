package ChatRoom;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Client extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	static String userName ="";
	static boolean readyToSend = false;
	private boolean connected;
	private Label label;
	
	static JTextField tf;
	static JTextField tfServer, tfPort;
	static JButton login, logout, sendMessage;
	static JTextArea ta;
	
	public Client() {
		JPanel northPanel = new JPanel(new GridLayout(3, 1));
		JPanel serverAndPort = new JPanel(new GridLayout(1, 5, 1, 3));
		tfServer = new JTextField("127.0.0.1");
		tfPort = new JTextField("2007");
		tfPort.setHorizontalAlignment(SwingConstants.RIGHT);
		serverAndPort.add(new JLabel("Server address: "));
		serverAndPort.add(tfServer);
		serverAndPort.add(new JLabel("Port number: "));
		serverAndPort.add(tfPort);
		serverAndPort.add(new JLabel(""));
		northPanel.add(serverAndPort);
		
		label = new Label("Nhập tên và bấm login để vào phòng chat!", SwingConstants.CENTER);
		northPanel.add(label);
		tf = new JTextField("Guest");
		tf.setBackground(Color.WHITE);
		northPanel.add(tf);
		add(northPanel, BorderLayout.NORTH);
		
		ta = new JTextArea("Welcome to chatroom!\n", 80, 80);
		JPanel centerPanel = new JPanel(new GridLayout(1, 1));
		centerPanel.add(new JScrollPane(ta));
		ta.setEditable(false);
		add(centerPanel, BorderLayout.CENTER);
		
		login = new JButton("Login");
		login.addActionListener(this);
		logout = new JButton("Logout");
		logout.addActionListener(this);
		logout.setEnabled(false);
		sendMessage = new JButton("Send");
		sendMessage.addActionListener(this);
		sendMessage.setEnabled(false);
		
		JPanel southPanel = new JPanel();
		southPanel.add(login);
		southPanel.add(logout);
		southPanel.add(sendMessage);
		add(southPanel, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		tf.requestFocus();
	}
	
	static boolean ready = false;
	public static boolean readyToSendMessage() {
		if (readyToSend == true) {
			readyToSend = false;
			return true;
		}else return false;
	}
	void connectionFailed() {
		login.setEnabled(true);
		logout.setEnabled(false);
		sendMessage.setEnabled(false);
		label.setText("Nhập tên và bấm login để vào phòng chat!");
		tf.setText("Guest");
		
		tfPort.setText("2007");
		tfServer.setText("127.0.0.1");
		
		tfServer.setEditable(false);
		tfPort.setEditable(false);
		
		ta.setText("");
		tf.removeActionListener(this);
		connected = false;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object ob = arg0.getSource();
		if(ob == logout) {
			connectionFailed();
			return;
		}
		if(ob == sendMessage) {
			readyToSend = true;
			return;
		}
		if(connected) {
			readyToSend = true;
			return;
		}
		if(ob == login) {
			userName = tf.getText().trim();
			if(userName.length() == 0) return;
			
			String server = tfServer.getText().trim();
			if(server.length() == 0) return;
			
			String portNumber = tfPort.getText().trim();
			if (portNumber.length() == 0) return;
			
			int port = 0;
			try {
				port = Integer.parseInt(portNumber);
			} catch (Exception e) {
				System.out.println(e);
				return ;
			}
			
			if(connected == false) {
				try {
					DatagramSocket socket = new DatagramSocket();
					MessageReceiver r = new MessageReceiver(socket);
					MessageSender s = new MessageSender(socket, server , port);
					Thread rThread = new Thread(r);
					Thread sThread = new Thread(s);
					rThread.start();
					sThread.start();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			tf.setText("");
			label.setText("Nhập tin nhắn và bấm send");
			connected = true;
			
			login.setEnabled(false);
			logout.setEnabled(true);
			sendMessage.setEnabled(true);
			tfServer.setEditable(false);
			tfPort.setEditable(false);
			tf.addActionListener(this);
		}
	}
	
	public static void main(String[] args) {
		new Client();
	}
	public static void append(String s) {
		ta.append(s);
		ta.setCaretPosition(ta.getText().length()-1);
	}
}

class MessageSender extends Thread {
    private int port = 2808;
    private DatagramSocket sock;
    private String hostname;
    
    MessageSender(DatagramSocket s, String h, int p) {
    	System.out.println("create sender");
        sock = s;
        hostname = h;
        port = p;
    }
    
    private void sendMessage(String s) throws Exception {
        byte buf[] = s.getBytes();
        InetAddress address = InetAddress.getByName(hostname);
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
        sock.send(packet);
    }
    
    public void run() {
        boolean connected = false;
        do {
            try {
                connected = true;
            } catch (Exception e) {
                
            }
        } while (!connected);
        
        while (true) {
            try {
                while (!Client.readyToSendMessage()) {
                    Thread.sleep(100);
                }
                sendMessage(">> " + Client.userName + ": " + Client.tf.getText());
                Client.tf.setText("");
            }
            catch(Exception e) {
                System.err.println(e);
            }
        }
    }
}


class MessageReceiver extends Thread {
    DatagramSocket sock;
    byte buf[];
    
    MessageReceiver(DatagramSocket s) {
        sock = s;
        buf = new byte[1024];
    }
    
    public void run() {
        while (true) {
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                sock.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                String s = "";
                for (int i = 0; i < received.length(); i++) {
                	if ((int)received.charAt(i) != 0) s += received.charAt(i);
                }
                s += "\n";
                Client.append(s);
                
            } catch(Exception e) {
                System.err.println(e);
            }
        }
    }
}
