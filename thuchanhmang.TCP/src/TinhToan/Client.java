package TinhToan;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Client extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Socket socket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	public static final String IP = "127.0.0.1";
	public static final int PORT = 2001;
	
	JButton btnProcess;
	JTextField tfInputExpress;
	JTextField tfResult;
	public Client() {
		GUI();
		Connect();
	}
	
	public static void main(String[] args) {
		new Client();
	}

	private void GUI() {
		this.setTitle("TCP-TinhToan - Tran Huu Trung - 15T2");
        this.setResizable(false);
        this.setLocationRelativeTo(this);
        
        this.setBounds(320, 150, 720, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);
        
        JLabel lbNhapBieuThuc = new JLabel("NHẬP BIỂU THỨC:");
        lbNhapBieuThuc.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbNhapBieuThuc.setBounds(20, 20, 180, 30);
        this.getContentPane().add(lbNhapBieuThuc);
        
        tfInputExpress = new JTextField();
        tfInputExpress.setFont(new Font("Tahoma", Font.BOLD, 15));
        tfInputExpress.setBounds(200, 20, 400, 30);
        this.getContentPane().add(tfInputExpress);
        
        btnProcess = new JButton("TÍNH");
        btnProcess.setBounds(620, 20, 70, 30);
        this.getContentPane().add(btnProcess);
        
        JLabel lbKetQua = new JLabel("KẾT QUẢ:");
        lbKetQua.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbKetQua.setBounds(20, 80, 180, 25);
        this.getContentPane().add(lbKetQua);
        
        tfResult = new JTextField();
        tfResult.setFont(new Font("Tahoma", Font.BOLD, 15));
        tfResult.setBounds(200, 80, 400, 30);
        this.getContentPane().add(tfResult);
        
        this.add(tfInputExpress);
        this.add(tfResult);
        this.add(btnProcess);
        
        btnProcess.addActionListener(this);
        this.setVisible(true);
	}


	private void Connect() {
		try {
			socket = new Socket(IP, PORT);
			dataInputStream = new DataInputStream(socket.getInputStream());
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
		} catch (IOException io) {
			JOptionPane.showMessageDialog(this, "Cannot connect to server!", "Error", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if(btn == btnProcess) {
			try {
				String strSend = tfInputExpress.getText();
				dataOutputStream.writeUTF(strSend);
				
				String strReceive = dataInputStream.readUTF();
				
				tfResult.setText(strReceive);
			} catch (IOException e2) {
				Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, e2);
			}
		}
	}
}
