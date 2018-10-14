package TCP_CHATROOM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginFrame {
	public JFrame frame;
	
	public LoginFrame(String ms) {
		frame = new JFrame("Login");
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		JLabel lbName = new JLabel("Name");
		lbName.setBounds(50, 50, 50, 30);
		frame.add(lbName);
		
		final JTextField Name = new JTextField("");
		Name.setBounds(120, 50, 200, 30);
		frame.add(Name);
		
		final JLabel msg = new JLabel(ms);
		msg.setBounds(120, 150, 200, 20);
		frame.add(msg);
		
		JButton OK = new JButton("Login");
		OK.setBounds(170, 100, 80, 25);
		OK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!Name.getText().equals("")) {
					new Client(Name.getText());
					frame.dispose();
				} else {
					msg.setText("Nhap ten cua ban vao!");
				}
			}
		});
		frame.add(OK);
		
		frame.setVisible(true);		
	}
	public static void main(String[] args) {
		new LoginFrame("");
	}
}

