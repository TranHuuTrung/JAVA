package XuLiChuoi;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PORT = 2004;
	private static final String HOST="127.0.0.1";
	
	JButton btnProcess;
	JButton btnExit;
	JTextField txtStringInput;
	JTextArea txtStringOutput;
	JTextField txtChuThuong;
	JTextField txtChuHoa;
	JTextField txtSoTu;
	
	private DatagramSocket datagramSocket;
	private InetAddress ipServer;
	public Client() {
		GUI();
		Init();
	}
	private void Init() {
		try {
			datagramSocket = new DatagramSocket();
			System.out.println("Start client!");
			ipServer = InetAddress.getByName(HOST);
		} catch (SocketException ske) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE,null, ske);
		} catch (UnknownHostException e) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			
		}
		
	}
	private void GUI() {
		this.setTitle("DoiChuoi_UDP - Trần Hữu Trung - 15T2");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400, 150, 500, 300);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		
		JLabel lbNhapChuoi = new JLabel("Nhập chuỗi:");
		lbNhapChuoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbNhapChuoi.setBounds(20, 25, 110, 24);
		this.getContentPane().add(lbNhapChuoi);
		
		txtStringInput = new JTextField();
		txtStringInput.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtStringInput.setBounds(135, 25, 345, 24);
		this.getContentPane().add(txtStringInput);
		txtStringInput.setColumns(10);
		
		btnProcess = new JButton("Xử lí");
		btnProcess.setBounds(220, 60, 80, 24);
		this.getContentPane().add(btnProcess);
		
		JLabel lbKetQua = new JLabel("Kết quả");
		lbKetQua.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbKetQua.setBounds(20, 95, 200, 24);
		this.getContentPane().add(lbKetQua);
		
		JLabel lbChuHoa = new JLabel("Chữ hoa:");
		lbChuHoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbChuHoa.setBounds(20, 125, 110, 24);
		this.getContentPane().add(lbChuHoa);
		
		txtChuHoa = new JTextField();
		txtChuHoa.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtChuHoa.setBounds(135, 125, 345, 24);
		this.getContentPane().add(txtChuHoa);
		
		JLabel lbChuThuong = new JLabel("Chữ thường:");
		lbChuThuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbChuThuong.setBounds(20, 155, 110, 24);
		this.getContentPane().add(lbChuThuong);
		
		txtChuThuong = new JTextField();
		txtChuThuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtChuThuong.setBounds(135, 155, 345, 24);
		this.getContentPane().add(txtChuThuong);
		
		JLabel lbCount = new JLabel("Số từ:");
		lbCount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbCount.setBounds(20, 185, 110, 24);
		this.getContentPane().add(lbCount);
		
		txtSoTu = new JTextField();
		txtSoTu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSoTu.setBounds(135, 185, 345, 24);
		this.getContentPane().add(txtSoTu);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(220, 220, 80, 24);
		btnExit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	int ret = JOptionPane.showConfirmDialog(null, 
						"Bạn có chắc chắn muốn thoát phần mềm không?",
						"Xác nhận thoát", JOptionPane.YES_NO_OPTION);
				if(ret == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
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
				stringSend = stringSend.trim();
				int len = stringSend.length();
				//doi chuoi ra mang byte
				byte[] sendData = stringSend.getBytes();
				//tao goi tin gui len server
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipServer, PORT);
				datagramSocket.send(sendPacket);
				//nhan du lieu tu server , qua trinh bi nghen cho den khi co du lieu
				byte[] receiveData = new byte[1024];
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				datagramSocket.receive(receivePacket);
				//hien thi ket qua
				String result = new String(receivePacket.getData(), 0, receivePacket.getLength());
				txtChuHoa.setText(result.substring(0, 1*len));
				txtChuThuong.setText(result.substring(1*len, 2*len));
				txtSoTu.setText(result.substring(2*len, receivePacket.getLength()));
			} catch (IOException e2) {
				Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e2);
				JOptionPane.showMessageDialog(this, "Can't send to server!", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
