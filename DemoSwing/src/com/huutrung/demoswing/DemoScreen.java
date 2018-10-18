package com.huutrung.demoswing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class DemoScreen extends JFrame {

	private JPanel mainPanel;
	private JTextField txtThongTinIP;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DemoScreen frame = new DemoScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DemoScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
//		setSize(900, 650);
		setLocationRelativeTo(null);
//		setResizable(false);
		mainPanel = new JPanel();
//		mainPanel.setBorder(new EmptyBorder());
		setContentPane(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));

		//HEADER
		JPanel jPanelHeader = new JPanel();
		jPanelHeader.setBackground(new Color(153, 255, 153));
		jPanelHeader.setBorder(new EmptyBorder(20, 10, 10, 10));
		JLabel lbHeader = new JLabel();
		lbHeader.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbHeader.setForeground(new Color(0, 102, 204));
		lbHeader.setIcon(new ImageIcon(getClass().getResource("/images/graduated-icon.png")));
		lbHeader.setText("XEM ĐIỂM THI SINH VIÊN");
		jPanelHeader.add(lbHeader);
		mainPanel.add(jPanelHeader, BorderLayout.NORTH);
		//CONTENT 
		JPanel JpContent = new JPanel();
		jPanelHeader.setBackground(new Color(153, 255, 153));
		jPanelHeader.setBorder(new EmptyBorder(20, 10, 10, 10));

		
		mainPanel.add(JpContent, BorderLayout.CENTER);
		JpContent.setLayout(null);
		
		JPanel ThongTinIP = new JPanel();
		ThongTinIP.setBounds(0, 0, 600, 50);
		JpContent.add(ThongTinIP);
		ThongTinIP.setLayout(null);
		
		JLabel lblNhapThongTin = new JLabel("Nhập thông tin IP");
		lblNhapThongTin.setHorizontalAlignment(SwingConstants.CENTER);
		lblNhapThongTin.setBounds(12, 12, 123, 26);
		ThongTinIP.add(lblNhapThongTin);
		
		txtThongTinIP = new JTextField();
		txtThongTinIP.setText("127.0.0.1");
		txtThongTinIP.setHorizontalAlignment(SwingConstants.CENTER);
		txtThongTinIP.setBounds(221, 15, 197, 22);
		ThongTinIP.add(txtThongTinIP);
		txtThongTinIP.setColumns(10);
		
		JButton btnKetNoi = new JButton("Kết nối");
		btnKetNoi.setIcon(new ImageIcon(getClass().getResource("/images/search32.png")));
		btnKetNoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtThongTinIP.setText("Trung");
			}
		});
		btnKetNoi.setBounds(430, 13, 141, 25);
		ThongTinIP.add(btnKetNoi);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 49, 600, 62);
		JpContent.add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 110, 600, 127);
		JpContent.add(scrollPane);
		
		JLabel label = new JLabel("New label");
		scrollPane.setColumnHeaderView(label);
		
		//FOOTER
		JPanel Jpfooter = new JPanel();
		Jpfooter.setBackground(new Color(153, 255, 153));
		Jpfooter.setBorder(new EmptyBorder(10, 10, 10, 10));
		JLabel lbtext = new JLabel("Copyright© by TranHuuTrung - 10/2018");
		lbtext.setForeground(new Color(0, 102, 204));
		Jpfooter.add(lbtext);
		mainPanel.add(Jpfooter, BorderLayout.SOUTH);
	}
}
