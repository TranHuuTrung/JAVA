package com.huutrung.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

public class BorderUI extends JFrame {
	JTextField txtTen, txtMaSinhVien, txtTenSinhVien, txtDiem;
	JButton btnOK, btnTimKiem, btnXemTatCa, btnReset, btnThoat;
	JTextArea txtDiaChi;
	DefaultTableModel dftable;
	JTable jtableXemDanhSach;

	public BorderUI(String title) {
		super(title);
		addControls();
		addEvents();
	}

	public void addControls() {
		Container con = getContentPane();
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		con.add(pnMain);
		
		JPanel pnThongTin = new JPanel();
		pnMain.add(pnThongTin);
		
		pnThongTin.setLayout(new BoxLayout(pnThongTin, BoxLayout.Y_AXIS));
		JPanel pnTen = new JPanel();
		pnTen.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lbName = new JLabel("Nhap Ten");
		txtTen = new JTextField(20);
		
		pnTen.add(lbName);
		pnTen.add(txtTen);
		pnThongTin.add(pnTen);
		
		JPanel pnDiaChi = new JPanel();
		pnDiaChi.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lbDiaChi = new JLabel("Dia chi");
		txtDiaChi = new JTextArea(5, 19);
		txtDiaChi.setWrapStyleWord(true);
		txtDiaChi.setLineWrap(true);
		JScrollPane scrl = new JScrollPane(txtDiaChi, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		lbDiaChi.setPreferredSize(lbName.getPreferredSize());
		
		pnDiaChi.add(lbDiaChi);
		pnDiaChi.add(scrl);
		pnThongTin.add(pnDiaChi);
		
		Border borderThongtin = BorderFactory.createLineBorder(Color.BLUE);
		TitledBorder titleThongTin = new TitledBorder(borderThongtin, "Thong Tin");
		pnThongTin.setBorder(titleThongTin);
		titleThongTin.setTitleColor(Color.GREEN);
		
		JPanel pnThongTinVaHanhDong = new JPanel();
		pnMain.add(pnThongTinVaHanhDong);
		pnThongTinVaHanhDong.setLayout(new GridLayout(1, 3));
		
		JPanel pnThongTinTimKiem = new JPanel();
//		pnThongTinTimKiem.setPreferredSize(new Dimension(400, 0));
		pnThongTinTimKiem.setLayout(new BoxLayout(pnThongTinTimKiem ,BoxLayout.Y_AXIS));
		
		Border borderThongTinTimKiem = BorderFactory.createLineBorder(Color.BLUE);
		TitledBorder titleThongTinTimKiem = new TitledBorder(borderThongTinTimKiem, "Thong tin tim kiem");
		pnThongTinTimKiem.setBorder(titleThongTinTimKiem);
		titleThongTinTimKiem.setTitleColor(Color.RED);
		
		JLabel lbMaSinhVien = new JLabel("Ma Sinh Vien");
		txtMaSinhVien = new JTextField(20);
		JLabel lbTenSinhVien = new JLabel("Ten Sinh Vien");
		txtTenSinhVien = new JTextField(20);
		JLabel lbDiem = new JLabel("Diem");
		txtDiem = new JTextField(20);
		pnThongTinTimKiem.add(lbMaSinhVien);
		pnThongTinTimKiem.add(txtMaSinhVien);
		pnThongTinTimKiem.add(lbTenSinhVien);
		pnThongTinTimKiem.add(txtTenSinhVien);
		pnThongTinTimKiem.add(lbDiem);
		pnThongTinTimKiem.add(txtDiem);
		pnThongTinVaHanhDong.add(pnThongTinTimKiem);
		
		JPanel pnHanhDong = new JPanel();
		pnHanhDong.setLayout(new BoxLayout(pnHanhDong, BoxLayout.Y_AXIS));
		
		
		Border borderHanhDong = BorderFactory.createLineBorder(Color.BLUE);
		TitledBorder titleHanhDong = new TitledBorder(borderHanhDong, "Hanh Dong");
		pnHanhDong.setBorder(titleHanhDong);
		titleHanhDong.setTitleColor(Color.RED);
		pnThongTinVaHanhDong.add(pnHanhDong);
		
		btnTimKiem = new JButton("Tim Kiem");
		btnXemTatCa = new JButton("Xem tat ca");
		btnReset = new JButton("Reset");
		btnThoat = new JButton("Thoat");
		
		pnHanhDong.add(btnTimKiem);
		pnHanhDong.add(btnXemTatCa);
		pnHanhDong.add(btnReset);
		pnHanhDong.add(btnThoat);
		
		JPanel pnXemDanhSach = new JPanel();
		pnXemDanhSach.setLayout(new BorderLayout());
		pnMain.add(pnXemDanhSach);
		
		dftable = new DefaultTableModel();
		dftable.addColumn("Ma so");
		dftable.addColumn("Ten sinh vien");
		dftable.addColumn("Diem");
		//them data
		String data1[] = {"102150142", "Tran Huu Trung", "10"};
		dftable.addRow(data1);
		Vector<String> data2 = new Vector<String>();
		data2.add("102150142");
		data2.add("Tran Huu Hieu");
		data2.add("10");
		dftable.addRow(data2);
		
		jtableXemDanhSach = new JTable(dftable) {
			 public boolean isCellEditable(int rowIndex, int colIndex) {
				  return false; //Disallow the editing of any cell
			}
		};
		JScrollPane scTable = new JScrollPane(jtableXemDanhSach,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnXemDanhSach.add(scTable, BorderLayout.CENTER);
		pnXemDanhSach.setPreferredSize(new Dimension(0, 100));
		
		JPanel pnOK = new JPanel();
		pnOK.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnOK = new JButton("OK");
		pnOK.add(btnOK);
		pnMain.add(pnOK);
	}

	public void addEvents() {

	}

	public void showUI() {
		this.setSize(700, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
