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

public class Client extends JFrame implements ActionListener {
	public static final int PORT = 2003;
	private static final long serialVersionUID = 1L;
	
	JButton btnProcess;
    JTextArea txtStringOutput;
    JTextField txtStringInput;
    JTextField txtChuThuong;
    JTextField txtChuHoa;
    JTextField txtSoTu;
    
    private InetAddress inetAdress;
    private DatagramSocket datagramSocket;


    public Client() {
        GUI();
        Init();
    }

    private void GUI() {
    	this.setTitle("Bài TH1 - Tran Huu Trung- 15T2");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 250);
		this.getContentPane().setLayout(null);
		
		JLabel lblNhpChui = new JLabel("NHẬP CHUỖI:");
		lblNhpChui.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNhpChui.setBounds(21, 25, 111, 24);
		this.getContentPane().add(lblNhpChui);
		
		txtStringInput = new JTextField();
		txtStringInput.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtStringInput.setBounds(129, 25, 205, 24);
		this.getContentPane().add(txtStringInput);
		txtStringInput.setColumns(10);
		
		btnProcess = new JButton("Xử lý");
		btnProcess.setBounds(344, 25, 80, 24);
		this.getContentPane().add(btnProcess);
		
		JLabel lblKtQu = new JLabel("KẾT QUẢ:");
		lblKtQu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblKtQu.setBounds(21, 60, 99, 24);
		this.getContentPane().add(lblKtQu);
		
		txtChuHoa = new JTextField();
		txtChuHoa.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtChuHoa.setColumns(10);
		txtChuHoa.setBounds(129, 95, 205, 24);
		this.getContentPane().add(txtChuHoa);
		
		txtChuThuong = new JTextField();
		txtChuThuong.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtChuThuong.setColumns(10);
		txtChuThuong.setBounds(129, 130, 205, 24);
		this.getContentPane().add(txtChuThuong);
		
		txtSoTu = new JTextField();
		txtSoTu.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtSoTu.setColumns(10);
		txtSoTu.setBounds(129, 165, 205, 24);
		this.getContentPane().add(txtSoTu);
		
		JLabel lblChHoa = new JLabel("Chữ hoa:");
		lblChHoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChHoa.setBounds(31, 95, 99, 24);
		this.getContentPane().add(lblChHoa);
		
		JLabel lblChThng = new JLabel("Chữ thường:");
		lblChThng.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChThng.setBounds(31, 130, 223, 24);
		this.getContentPane().add(lblChThng);
		
		JLabel lblST = new JLabel("Số từ:");
		lblST.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblST.setBounds(31, 165, 99, 24);
		this.getContentPane().add(lblST);
    	
        this.setResizable(false);
        this.setLocationRelativeTo(this);

        btnProcess.addActionListener(this);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Client();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if (btn == btnProcess) {
            try {
                String sSend = txtStringInput.getText();
                
                byte[] sendData= sSend.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, inetAdress, PORT);
                datagramSocket.send(sendPacket);
                
                byte[] receiveData = new byte[ 4096];	
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				datagramSocket.receive(receivePacket);
				String sReceive = new String(receivePacket.getData());
				
				String fixedData = "";
				for (int i = 0; i < sReceive.length(); i++)
				{
					if (sReceive.charAt(i) != 0) fixedData += sReceive.charAt(i);
				}
				
				System.out.println("fixed data: " + fixedData);

		        String[] split = fixedData.split(" ");
		        System.out.println("length: " + split.length);
		        
		        txtChuHoa.setText(split[0]);
		        txtChuThuong.setText(split[1]);
		        txtSoTu.setText(split[2]);
                
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Can't send to Server", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void Init() {
        try {
            datagramSocket = new DatagramSocket();
            inetAdress = InetAddress.getByName("localhost");
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
