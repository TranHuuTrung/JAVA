package com.huutrung.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ClientUI extends JFrame {
	JTextField txtNhapIP, txtNhapMaSV, txtNhapTenSV, txtNhapDiem;
	JButton btnKetNoiIP, btnTimKiem, btnXemTatCa, btnReset, btnThoat;
	DefaultTableModel defaultTable;
	JTable tableXemDanhSach;
	public ClientUI (String title) {
		super(title);
		addControls();
		addEvents();
	}

	private void addEvents() {
		btnThoat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	private void addControls() {
		Container con = getContentPane();
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());
		con.add(pnMain);
		//Phan Header
		JPanel pnHeader = new JPanel();
		pnMain.add(pnHeader, BorderLayout.NORTH);
		pnHeader.setBackground(new Color(153, 255, 153));
		pnHeader.setBorder(new EmptyBorder(20, 10, 10, 10));
		
		JLabel lbHeader = new JLabel();
		lbHeader.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbHeader.setForeground(new Color(0, 102, 204));
		lbHeader.setIcon(new ImageIcon(getClass().getResource("/images/graduated-icon.png")));
		lbHeader.setText("XEM ĐIỂM THI SINH VIÊN");
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
		
		Border borderThongTinKetNoi = BorderFactory.createLineBorder(Color.BLUE);
		TitledBorder titleThongTinKetNoi = new TitledBorder(borderThongTinKetNoi, "Nhập thông tin kết nối Server");
		pnKetNoiServer.setBorder(titleThongTinKetNoi);
		titleThongTinKetNoi.setTitleColor(Color.RED);
		
		/*Nhap thong tin va chon hanh dong de tim kiem*/
		JPanel pnThongTinTimKiemVaHanhDong = new JPanel();
		pnBody.add(pnThongTinTimKiemVaHanhDong);
		pnThongTinTimKiemVaHanhDong.setBorder(new EmptyBorder(10, 0, 10, 0));
		pnThongTinTimKiemVaHanhDong.setLayout(new BoxLayout(pnThongTinTimKiemVaHanhDong, BoxLayout.X_AXIS));
		
		JPanel pnThongTinTimKiem = new JPanel();
		pnThongTinTimKiemVaHanhDong.add(pnThongTinTimKiem);
		pnThongTinTimKiem.setLayout(new BoxLayout(pnThongTinTimKiem, BoxLayout.Y_AXIS));
		//Nhap ma sinh vien
		JPanel pnNhapMaSV = new JPanel();
		pnNhapMaSV.setBorder(new EmptyBorder(5, 0, 5, 0));
		pnThongTinTimKiem.add(pnNhapMaSV);
		pnNhapMaSV.setLayout(new BoxLayout(pnNhapMaSV, BoxLayout.X_AXIS));
		
		JLabel lbNhapMaSV = new JLabel("Nhập mã sinh viên");
		txtNhapMaSV = new JTextField(15);
		lbNhapMaSV.setIcon(new ImageIcon(getClass().getResource("/images/User-32x32.png")));
		pnNhapMaSV.add(lbNhapMaSV);
		pnNhapMaSV.add(txtNhapMaSV);
		//Nhap ten sinh vien
		JPanel pnNhapTenSV = new JPanel();
		pnNhapTenSV.setBorder(new EmptyBorder(5, 0, 5, 0));
		pnThongTinTimKiem.add(pnNhapTenSV);
		pnNhapTenSV.setLayout(new BoxLayout(pnNhapTenSV, BoxLayout.X_AXIS));
		
		JLabel lbNhapTenSV = new JLabel("Nhập tên sinh viên    ");
		lbNhapTenSV.setIcon(new ImageIcon(getClass().getResource("/images/sign-up-icon.png")));
		txtNhapTenSV = new JTextField(15);
		pnNhapTenSV.add(lbNhapTenSV);
		pnNhapTenSV.add(txtNhapTenSV);
		lbNhapMaSV.setPreferredSize(lbNhapTenSV.getPreferredSize());
		
		//Nhap diem 
		JPanel pnNhapDiem = new JPanel();
		pnNhapDiem.setBorder(new EmptyBorder(5, 0, 5, 0));
		pnThongTinTimKiem.add(pnNhapDiem);
		pnNhapDiem.setLayout(new BoxLayout(pnNhapDiem, BoxLayout.X_AXIS));
		
		JLabel lbNhapDiem = new JLabel("Nhập điểm");
		lbNhapDiem.setIcon(new ImageIcon(getClass().getResource("/images/calculate.png")));
		txtNhapTenSV = new JTextField(15);
		pnNhapDiem.add(lbNhapDiem);
		pnNhapDiem.add(txtNhapTenSV);
		lbNhapDiem.setPreferredSize(lbNhapTenSV.getPreferredSize());
		
		Border borderThongTinTimKiem = BorderFactory.createLineBorder(Color.BLUE);
		TitledBorder titleThongTinTimKiem = new TitledBorder(borderThongTinTimKiem, "Thông tin tìm kiếm");
		pnThongTinTimKiem.setBorder(titleThongTinTimKiem);
		titleThongTinTimKiem.setTitleColor(Color.RED);
		
//		JPanel pnSpace = new JPanel();
		pnThongTinTimKiemVaHanhDong.add(Box.createRigidArea(new Dimension(10,0)));
		
		JPanel pnHanhDong = new JPanel();
		pnThongTinTimKiemVaHanhDong.add(pnHanhDong);
		pnHanhDong.setLayout(new GridLayout(4,1, 5, 5));
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setIcon(new ImageIcon(getClass().getResource("/images/search2.png")));
		btnXemTatCa = new JButton("Xem tất cả");
		btnXemTatCa.setIcon(new ImageIcon(getClass().getResource("/images/Tasks-icon.png")));
		btnReset = new JButton("Reset");
		btnReset.setIcon(new ImageIcon(getClass().getResource("/images/reset.png")));
		btnThoat = new JButton("Thoát");
		btnThoat.setIcon(new ImageIcon(getClass().getResource("/images/system_log_out.png")));
		
		pnHanhDong.add(btnTimKiem);
		pnHanhDong.add(btnXemTatCa);
		pnHanhDong.add(btnReset);
		pnHanhDong.add(btnThoat);
		
		Border borderHanhDong = BorderFactory.createLineBorder(Color.BLUE);
		TitledBorder titleHanhDong = new TitledBorder(borderHanhDong, "Hành động");
		pnHanhDong.setBorder(titleHanhDong);
		titleHanhDong.setTitleColor(Color.RED);
		
		JPanel pnTableDanhSach = new JPanel();
		pnTableDanhSach.setLayout(new BorderLayout());
		pnBody.add(pnTableDanhSach);
		defaultTable = new DefaultTableModel();
		defaultTable.addColumn("Mã số sinh viên");
		defaultTable.addColumn("Tên sinh viên");
		defaultTable.addColumn("Điểm DACSNM");
		defaultTable.addColumn("Điểm LTM");
		defaultTable.addColumn("Điểm CTD");
		//them data
		String data1[] = {"102150142", "Trần Hữu Trung", "10", "10","10"};
		for (int i = 0; i < 50; i++) {
			defaultTable.addRow(data1);
		}
		Vector<String> data2 = new Vector<String>();
		data2.add("102150142");
		data2.add("Trần Hữu Hiếu");
		data2.add("10");
		data2.add("10");
		data2.add("10");
		defaultTable.addRow(data2);
		tableXemDanhSach = new JTable(defaultTable) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				  return false; //Disallow the editing of any cell
			}
		};
		JScrollPane scTable = new JScrollPane(tableXemDanhSach,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnTableDanhSach.add(scTable, BorderLayout.CENTER);
		pnTableDanhSach.setPreferredSize(new Dimension(0, 150));
		
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
