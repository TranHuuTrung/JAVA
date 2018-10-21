package com.huutrung.giaodien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Client {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Client client = new Client();
			}
		});
	}
	
	public Client() {
		JFrame giaodien = new JFrame("Xem diem thi");
		giaodien.setSize(900, 650);
		giaodien.setLocationRelativeTo(null);
		giaodien.setResizable(false);
		giaodien.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		giaodien.setLayout(new BorderLayout());
		//Header
		JPanel jPanelHeader = new JPanel();
		jPanelHeader.setBackground(new Color(153, 255, 153));
		jPanelHeader.setBorder(new EmptyBorder(20, 10, 10, 10));
		
		JLabel lbHeader = new JLabel();
		lbHeader.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbHeader.setForeground(new Color(0, 102, 204));
		lbHeader.setIcon(new ImageIcon(getClass().getResource("/images/graduated-icon.png")));
		lbHeader.setText("XEM ĐIỂM THI SINH VIÊN");
		
		jPanelHeader.add(lbHeader);
		//Content
		JPanel jpContent = new JPanel();
//		Border titleBorder;
//		Border blueBorder = BorderFactory.createLineBorder(Color.BLUE);
//		titleBorder = BorderFactory.createTitledBorder(blueBorder, "Nhập thông tin Server");
//		jpContent.add(createPaneBorder(titleBorder, "title border with color blue"));
		jpContent.setLayout(new GridLayout(3,1));
		
		JPanel pnThongtinServer = new JPanel();
		JLabel lbThongtin = new JLabel("Thong tin Server ");
		JTextField txtThongTinIp = new JTextField();
		
		pnThongtinServer.add(lbThongtin);
		pnThongtinServer.add(txtThongTinIp);
		
		jpContent.add(pnThongtinServer);
		
		//Footer
		JPanel Jpfooter = new JPanel();
		Jpfooter.setBackground(new Color(153, 255, 153));
		Jpfooter.setBorder(new EmptyBorder(10, 10, 10, 10));
		JLabel lbtext = new JLabel("Copyright© by TranHuuTrung - 10/2018");
		lbtext.setForeground(new Color(0, 102, 204));
		Jpfooter.add(lbtext);
		
		//add all part into masster
		giaodien.add(jPanelHeader, BorderLayout.NORTH);
		giaodien.add(Jpfooter, BorderLayout.SOUTH);
		giaodien.add(jpContent, BorderLayout.CENTER);
		giaodien.setVisible(true);
	}

	private Component createPaneBorder(Border border, String content) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(border);
		JTextField tfNhap = new JTextField();
		JButton btnKetNoi = new JButton("Ket Noi");
		JLabel lb = new JLabel(content, JLabel.CENTER);
	    panel.add(lb);
	    panel.add(tfNhap);
	    panel.add(btnKetNoi);
	    return panel;
	}
}
