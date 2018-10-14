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

	private static final long serialVersionUID = 1L;
	JButton btnProcess;
    JTextField txtStringOutput;
    JTextField txtStringInput;
    
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public Client() {
        GUI();
        Connect();
    }

    private void GUI() {
        this.setTitle("TCP-TinhToan - Tran Huu Trung - 15T2");
        this.setResizable(false);
        this.setLocationRelativeTo(this);
        
		this.setBounds(100, 100, 533, 160);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JLabel lblNhpChui = new JLabel("NHẬP BIỂU THỨC:");
		lblNhpChui.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNhpChui.setBounds(21, 25, 147, 24);
		this.getContentPane().add(lblNhpChui);
		
		txtStringInput = new JTextField();
		txtStringInput.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtStringInput.setBounds(178, 25, 215, 24);
		this.getContentPane().add(txtStringInput);
		txtStringInput.setColumns(10);
		
		btnProcess = new JButton("Xử lý");
		btnProcess.setBounds(403, 27, 80, 24);
		this.getContentPane().add(btnProcess);
		
		JLabel lblKtQu = new JLabel("KẾT QUẢ:");
		lblKtQu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblKtQu.setBounds(21, 72, 99, 24);
		this.getContentPane().add(lblKtQu);
		
		txtStringOutput = new JTextField();
		txtStringOutput.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtStringOutput.setColumns(10);
		txtStringOutput.setBounds(178, 72, 215, 24);
		this.getContentPane().add(txtStringOutput);
        
        this.add(txtStringInput);
        this.add(btnProcess);
        this.add(txtStringOutput);

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
                dataOutputStream.writeUTF(sSend);
               
                String sReceive = dataInputStream.readUTF();

                txtStringOutput.setText(sReceive);
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Can't send to Server", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            
        }
    }

    private void Connect() {
        try {
            socket = new Socket(Server.IP, Server.PORT);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Server didn't open", "Error", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

}
