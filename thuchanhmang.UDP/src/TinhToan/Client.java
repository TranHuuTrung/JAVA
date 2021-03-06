package TinhToan;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Client extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private static final int PORT = 6000;
	private static final String IP = "127.0.0.1";
	JButton btnProcess;
	JTextField txtStringInput;
	JTextField txtStringOutput;
	
	public InetAddress addressServer;
	public DatagramSocket datagramSocket;
	
	public Client() {
		GUI();
		Connect();
	}
	
	private void GUI() {
		this.setTitle("TinhToan_UDP - Trần Hữu Trung - 15T2");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setBounds(350, 150, 600, 250);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JLabel lbNhapBieuThuc = new JLabel("Nhập biểu thức:");
		lbNhapBieuThuc.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbNhapBieuThuc.setBounds(20, 20, 150, 30);
		this.getContentPane().add(lbNhapBieuThuc);
		
		txtStringInput = new JTextField();
		txtStringInput.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtStringInput.setBounds(180, 20, 400, 30);
		this.getContentPane().add(txtStringInput);
		
		JLabel lbKetQua = new JLabel("Kết quả:");
		lbKetQua.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbKetQua.setBounds(20, 100, 150, 30);
		this.getContentPane().add(lbKetQua);
		
		txtStringOutput = new JTextField();
		txtStringOutput.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtStringOutput.setBounds(180, 100, 400, 30);
		this.getContentPane().add(txtStringOutput);

		btnProcess = new JButton("Tính");
		btnProcess.setBounds(300, 150, 80, 30);
		this.getContentPane().add(btnProcess);
		
		JButton btnExit = new JButton("Thoát");
		btnExit.setBounds(390, 150, 80, 30);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		this.getContentPane().add(btnExit);
		
		btnProcess.addActionListener(this);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new Client();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if(btn == btnProcess) {
			try {
				String stringSend = txtStringInput.getText();
				//gui data cho Server
					byte[] dataSend = stringSend.getBytes();
					DatagramPacket sendPacket = new DatagramPacket(dataSend, dataSend.length, addressServer, PORT);
					System.out.println(addressServer);
					datagramSocket.send(sendPacket);
					
					System.out.println(sendPacket);
					//nhan response tu server
					byte[] dataReceive = new byte[1024];
					DatagramPacket receivePacket = new DatagramPacket(dataReceive, dataReceive.length);
					datagramSocket.receive(receivePacket);
					//hien thi ket qua
					String ketqua = new String(receivePacket.getData(), 0, receivePacket.getLength());
					txtStringOutput.setText(ketqua);
			} catch (IOException io) {
				Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, io);
				JOptionPane.showMessageDialog(this, "Can't send to server!", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	private void Connect() {
		try {
			datagramSocket = new DatagramSocket();	
			System.out.println("Client started ");
			addressServer = InetAddress.getByName(IP);
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(this, "Cann't connect to server!", "Error", JOptionPane.INFORMATION_MESSAGE);
		} 
	}
}
