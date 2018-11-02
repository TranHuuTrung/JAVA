package GiaoDien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ClientUI extends JFrame{
	
	private JTextField txtNhapIP;
	private JButton btnKetNoiIP;

	public ClientUI(String title) {
		super(title);
		addControls();
		addEvents();
	}

	private void addEvents() {
		
	}

	private void addControls() {
		Container container = getContentPane();
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());
		container.add(pnMain);
		//Phan Header
		JPanel pnHeader = new JPanel();
		pnMain.add(pnHeader, BorderLayout.NORTH);
		pnHeader.setBackground(new Color(153, 255, 153));
		pnHeader.setBorder(new EmptyBorder(20, 10, 10, 10));
				
		JLabel lbHeader = new JLabel();
		lbHeader.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbHeader.setForeground(new Color(0, 102, 204));
//		lbHeader.setIcon(new ImageIcon(getClass().getResource("/images/graduated-icon.png")));
		lbHeader.setText("GIAO DIEN CHINH");
		pnHeader.add(lbHeader);
		//Phan Body
		JPanel pnBody = new JPanel();
		pnMain.add(pnBody, BorderLayout.CENTER);
		pnBody.setBorder(new EmptyBorder(15, 5, 0, 5));
		pnBody.setLayout(new BoxLayout(pnBody, BoxLayout.Y_AXIS));
		/*Nhap thong de ket noi*/
		JPanel pnKetNoiServer = new JPanel();
		pnBody.add(pnKetNoiServer);
		pnKetNoiServer.setLayout(new FlowLayout());
		
		JLabel lbNhapIp = new JLabel("Nhập địa chỉ IP");
		txtNhapIP = new JTextField(15);
		txtNhapIP.setHorizontalAlignment(SwingConstants.CENTER);
		btnKetNoiIP = new JButton("Kết nối");
		pnKetNoiServer.add(lbNhapIp);
		pnKetNoiServer.add(txtNhapIP);
		pnKetNoiServer.add(btnKetNoiIP);
		
		//Phan Footer
		JPanel pnFooter = new JPanel();
		pnMain.add(pnFooter, BorderLayout.SOUTH);
		pnFooter.setBackground(new Color(153, 255, 153));
		pnFooter.setBorder(new EmptyBorder(10, 10, 10, 10));
		JLabel lbFooter = new JLabel("Copyright© by TranHuuTrung - 10/2018");
		lbFooter.setForeground(new Color(0, 102, 204));
	    pnFooter.add(lbFooter);
	}
	
	public void showUI() {
		this.setSize(700, 600);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
