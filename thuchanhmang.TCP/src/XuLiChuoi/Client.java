package XuLiChuoi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client {
	private static Socket socket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	public static final String IP = "localhost";
	public static final int PORT = 2000;
	public static void main(String[] args) {
		new Client();
	}
	
	public Client() {
		JFrame f = new JFrame("TCP-XuLiChuoi - Tran Huu Trung - 15T2");
		f.setSize(900, 600);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(null);
		
		JLabel lbTitle = new JLabel("XU LI CHUOI _ TCP");
		lbTitle.setBounds(400,20,200,50);
		f.add(lbTitle);
		
		JLabel lbString = new JLabel("Client request");
		lbString.setBounds(10, 80, 150, 50);
		f.add(lbString);
		JTextArea taClientSend = new JTextArea(); 
		taClientSend.setBounds(120,80,750,70);
		f.add(taClientSend);
		
		JButton btnSend = new JButton("SEND");
		btnSend.setBounds(410, 160, 100, 30);
		f.add(btnSend);

		JLabel lbResult = new JLabel("SERVER RESPONSE");
		lbResult.setBounds(400,190,200,50);
		f.add(lbResult);
		
		JLabel lbUppercase = new JLabel("Uppercase");
		lbUppercase.setBounds(10, 230, 100, 50);
		f.add(lbUppercase);
		JTextArea taResultUppercase = new JTextArea();
		taResultUppercase.setBounds(120, 230, 750, 100);
		f.add(taResultUppercase);
		
		JLabel lbLowercase = new JLabel("Lowercase");
		lbLowercase.setBounds(10, 370, 100, 50);
		f.add(lbLowercase);
		JTextArea taResultLowercase = new JTextArea();
		taResultLowercase.setBounds(120, 370, 750, 100);
		f.add(taResultLowercase);
		
		JLabel lbResultCount = new JLabel("Count words");
		lbResultCount.setBounds(10, 500, 100, 50);
		f.add(lbResultCount);
		JTextField tfResultCount = new JTextField();
		tfResultCount.setBounds(120, 500, 750, 50);
		f.add(tfResultCount);
		
		btnSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					socket = new Socket(IP, PORT);
					dataInputStream = new DataInputStream(socket.getInputStream());
					dataOutputStream = new DataOutputStream(socket.getOutputStream());
					String msg = taClientSend.getText();
					int n = msg.trim().length();
					dataOutputStream.writeUTF(msg);
					//((Flushable) dataInputStream).flush();
					taResultLowercase.setText("");
					taResultUppercase.setText("");
					String clientReceive = dataInputStream.readUTF();
					taResultUppercase.append(clientReceive.substring(0, 1*n));
					taResultLowercase.append(clientReceive.substring(1*n, 2*n));
					tfResultCount.setText(clientReceive.substring(2*n, clientReceive.length()));
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		});
		
		f.setVisible(true);
		
	}
}
