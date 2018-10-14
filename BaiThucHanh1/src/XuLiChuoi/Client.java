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

	public Client() {
		JFrame f = new JFrame("TCP-XuLiChuoi - Tran Huu Trung - 15T2");
		f.setSize(1000, 600);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(null);
		JLabel jlbEnterString = new JLabel("Client request");
		jlbEnterString.setBounds(10, 10, 100, 50);
		f.add(jlbEnterString);
		JTextArea jtaClientSend = new JTextArea();
		jtaClientSend.setBounds(120, 10, 750, 100);
		f.add(jtaClientSend);
		JButton btnSend = new JButton("SEND");
		btnSend.setBounds(900, 10, 80, 50);
		f.add(btnSend);

		JLabel jlbResult = new JLabel("SERVER RESPONSE");
		jlbResult.setBounds(450, 100, 200, 50);
		f.add(jlbResult);

		JLabel jlbUppercase = new JLabel("Uppercase");
		jlbUppercase.setBounds(10, 150, 100, 50);
		f.add(jlbUppercase);
		JTextArea jtaResultUppercase = new JTextArea();
		jtaResultUppercase.setBounds(120, 150, 750, 100);
		f.add(jtaResultUppercase);

		JLabel jlbLowercase = new JLabel("Lowercase");
		jlbLowercase.setBounds(10, 300, 100, 50);
		f.add(jlbLowercase);
		JTextArea jtaResultLowercase = new JTextArea();
		jtaResultLowercase.setBounds(120, 300, 750, 100);
		f.add(jtaResultLowercase);
		
		JLabel jlbNumberString = new JLabel("Number");
		jlbNumberString.setBounds(10, 450, 100, 50);
		f.add(jlbNumberString);
		JTextField jtfResultNumbberString = new JTextField();
		jtfResultNumbberString.setBounds(120, 450, 750, 50);
		f.add(jtfResultNumbberString);
		
		btnSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					socket = new Socket("localhost",7004);
					dataInputStream = new DataInputStream(socket.getInputStream());
					dataOutputStream  = new DataOutputStream(socket.getOutputStream());
					String msg = jtaClientSend.getText();
					dataOutputStream.writeUTF(msg);
					dataOutputStream.flush();
					
					String clientReceive = dataInputStream.readUTF();
					String[] arrReceive = clientReceive.split(" ");
					int n = Integer.parseInt(arrReceive[arrReceive.length - 1]);
					for (int i = 0; i < n; i++) {
						jtaResultUppercase.append(arrReceive[i] +" ");
					}
					for (int i = n; i < arrReceive.length - 1; i++) {
						jtaResultLowercase.append(arrReceive[i]+" ");
					}
					jtfResultNumbberString.setText(arrReceive[arrReceive.length - 1]);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		f.setVisible(true);
	}


	public static void main(String[] args) {
		new Client();
	}

}
