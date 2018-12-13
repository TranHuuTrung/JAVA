package com.huutrung.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.server.Skeleton;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import TestAutocomplete.connectDB;

public class ClientUI extends JFrame {
	JTextField txtNhapIP, txtNhapMaSV, txtNhapTenSV, txtNhapDiem;
	JButton btnKetNoiIP, btnTimKiem, btnXemTatCa, btnReset, btnThoat;
	DefaultTableModel defaultTable;
	JTable tableXemDanhSach;
	ArrayList<String> name = new ArrayList<>();
	public ClientUI (String title) {
		super(title);
		//DBName();
		addControls();
		addEvents();
	}
	//Tao connect to server 
	private Socket connect() throws Exception {
		//Client tao socket ket noi den server cho phep ket noi o cong 8000
		String addressIP;
		addressIP = txtNhapIP.getText();
		Socket socket = null;
		socket = new Socket(addressIP, 8000);
		System.out.println(socket);
		System.out.println("Client created!");
		return socket;
	}
	
	private String connectToServer() throws Exception{
		Socket socket = connect();
		String ketqua = null;
		try {
			DataOutputStream dataGuiServer = new DataOutputStream(socket.getOutputStream());
			DataInputStream dataNhanServer = new DataInputStream(socket.getInputStream());
			dataGuiServer.writeUTF("showAll");
			System.out.println("ConnectToServer() .. gui data len Server");
			ketqua = dataNhanServer.readUTF();
			System.out.println("ConnectToServer() .. nhan data tu server, kq = "+ ketqua);
			socket.close();
		} catch (Exception e) {
			System.out.println("CoonectToServer().. Loi gui, nhan data tu client");
			socket.close();
		}
		return ketqua;
	}
	private String allNameFromServer() throws Exception{
		Socket socket = connect();
		String ketqua = null;
		try {
			DataOutputStream dataGuiServer = new DataOutputStream(socket.getOutputStream());
			DataInputStream dataNhanServer = new DataInputStream(socket.getInputStream());
			dataGuiServer.writeUTF("allNameFromServer");
			System.out.println("ConnectToServer() .. gui data len Server");
			ketqua = dataNhanServer.readUTF();
			System.out.println("ConnectToServer() .. nhan data tu server, kq = "+ ketqua);
			socket.close();
		} catch (Exception e) {
			System.out.println("CoonectToServer().. Loi gui, nhan data tu client");
			socket.close();
		}
		return ketqua;
	}	
	public boolean kiemTraThongTin() {
		Check check = new Check();
		if(!check.checkID(txtNhapMaSV.getText())) {
			String erorrĐinhdangID = "Nhập sai định dạng mã sinh viên \n Vui lòng nhập lại chính xác!";
			JOptionPane.showMessageDialog(this, erorrĐinhdangID, "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
			txtNhapMaSV.requestFocus();
			return false;
		} else if(!check.check_hoten(txtNhapTenSV.getText())) {
			String errorDinhDangTen = "Bạn nhập tên sinh viên chưa đúng định dạng...";
			JOptionPane.showMessageDialog(this, errorDinhDangTen , "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
	        txtNhapTenSV.requestFocus();
	        return false;
		}
		//them dieu kiem nhap diem
		return true;
	}

	private void addEvents() {
		btnThoat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int ret = JOptionPane.showConfirmDialog(null, 
						"Bạn có chắc chắn muốn thoát phần mềm không?",
						"Xác nhận thoát", JOptionPane.YES_NO_OPTION);
				if(ret == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ResetForm();
			}
		});
		
		btnTimKiem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!kiemTraThongTin()) { return;}
				//kiem tra thong tin sinh vien , masv khong duo rong
				if(!txtNhapMaSV.getText().trim().equals("") || !txtNhapTenSV.getText().trim().equals("") || !txtNhapDiem.getText().trim().equals("")) {
					try {
						acceptedClient(txtNhapMaSV.getText(), txtNhapTenSV.getText(), txtNhapDiem.getText());
					} catch (Exception e2) {
						Logger.getLogger(ClientUI.class.getName());
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnKetNoiIP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Check check = new Check();
				if (!check.check_IP(txtNhapIP.getText())) {
					JOptionPane.showMessageDialog(null, "Nhập địa chỉ IP bị sai...\n Yêu cầu nhập lại dạng xxx.xxx.x.x", "Lỗi định dạng địa chỉ IP", JOptionPane.ERROR_MESSAGE);
					txtNhapIP.setText("");
					txtNhapIP.requestFocus();
				} else {
					try {
//						String traloi = connectToServer();
						String traloi = allNameFromServer();
						String[] Allname = traloi.split(";");
						for (int i = 0; i < Allname.length; i++) {
							name.add(Allname[i]);
						}
//						System.out.println(traloi);
//						xuatDataRaBang(traloi);
						if (null != traloi && !traloi.equals("DBError")) {
							JOptionPane.showMessageDialog(null, "Chúc mừng bạn đã kết nối thành công!", "Kết nối thành công", JOptionPane.INFORMATION_MESSAGE);
							btnTimKiem.setEnabled(true);
							btnXemTatCa.setEnabled(true);
							txtNhapIP.setEditable(false);
							btnKetNoiIP.setEnabled(false);
							txtNhapTenSV.setEditable(true);
							txtNhapMaSV.setEditable(true);
							txtNhapDiem.setEditable(true);
						}
					} catch (Exception e2) {
						System.out.println("Ket noi that bai");
						JOptionPane.showMessageDialog(null, "Kết nối đến server thất bại", "Lỗi kết nối", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		btnXemTatCa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//xem tat ca danh sach
				String traloi = null;
				txtNhapMaSV.setText("");
				txtNhapTenSV.setText("");
				txtNhapDiem.setText("");
				try {
					traloi = connectToServer();
				} catch (Exception e1) {
					e1.printStackTrace();
					traloi = "Không thể xem tất cả";
					JOptionPane.showMessageDialog(null, "Không thể xem toàn bộ danh sách", "Lỗi xem dữ liệu", JOptionPane.ERROR_MESSAGE);
				}
				xuatDataRaBang(traloi);
			}
		});
		
		tableXemDanhSach.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				XuLiXemChiTiet();
			}
		});
		txtNhapTenSV.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_BACK_SPACE:
					break;
				case KeyEvent.VK_ENTER:
					txtNhapTenSV.setText(txtNhapTenSV.getText());
					break;
				default:
					EventQueue.invokeLater(new Runnable() {
						@Override
						public void run() {
							String txt = txtNhapTenSV.getText();
							autoComplete(txt);
						}
					});
				}
			}
		});
	}

	public void autoComplete(String txt) {
		String complete="";
		int start = txt.length();
		int last = txt.length();
		int a;
		for (a = 0; a < name.size(); a++) {
			if (name.get(a).toLowerCase().toString().startsWith(txt.toLowerCase())) {
				complete = name.get(a).toString();
				last = complete.length();
				break;
			}
		}
		if(last>start) {
			txtNhapTenSV.setText(complete);
			txtNhapTenSV.setCaretPosition(last);
			txtNhapTenSV.moveCaretPosition(start);
		}
	}
	public void ResetForm() {
		txtNhapMaSV.setText("");
		txtNhapTenSV.setText("");
		txtNhapDiem.setText("");
	}
	//tim kiem
	public void acceptedClient(String masv, String tensv, String diem) throws Exception {
		//1.client: tao socket ket noi den server cho phep ket noi o cong 8080
		Socket sk = connect();
        String search = "search";
        DataOutputStream dataGui = new DataOutputStream(sk.getOutputStream());
        DataInputStream dataNhan = new DataInputStream(sk.getInputStream());
        
        dataGui.writeUTF(masv + "#" + tensv + "#" + diem + "#" + search);
        System.out.println("AcceptedClient : "+masv+" vs "+tensv+" vs "+diem);
        String result = dataNhan.readUTF();
        System.out.println("Ket qua search = "+result);
        sk.close();
        xuatDataRaBang(result);
	}
	private void xuatDataRaBang(String result) {
		//xuat ket qua ra bang
		DefaultTableModel dm = (DefaultTableModel) tableXemDanhSach.getModel();
		dm.getDataVector().clear();
		if (null != result && !result.isEmpty() && !result.equals("DBError") && !result.equalsIgnoreCase("OK")) {
			String[] students = result.split(";");
			if(null != students && students.length != 0) {
				for (int i = 0; i < students.length; i++) {
					String student = students[i];
					if (null != student && !student.isEmpty()) {
						String[] parts = student.split(",");
						dm.addRow(new Object[] {parts[0], parts[1], parts[2], parts[3]});
					}
				}
			}
		} else {
			dm.addRow(new Object[] {"Không tìm thấy kết quả!", "", "", ""});
		}
	}

	private void XuLiXemChiTiet() {
		int rowSelected = tableXemDanhSach.getSelectedRow();
		if (rowSelected == -1) return;
		String msg = "";
		String masv = "Mã Sinh Viên: ";
			masv+=tableXemDanhSach.getValueAt(rowSelected, 0)+"\n";
			msg+=masv;
		String tensv = "Tên Sinh Viên: ";
			tensv+=tableXemDanhSach.getValueAt(rowSelected, 1)+"\n";
			msg+=tensv;
		String gioitinh = "Giới tính: ";
			gioitinh+=tableXemDanhSach.getValueAt(rowSelected, 2)+"\n";
			msg+=gioitinh;
		String diemdacsnm = "Điểm Đồ án CSNM: ";
			diemdacsnm+=tableXemDanhSach.getValueAt(rowSelected, 3)+"\n";
			msg+=diemdacsnm;
		if(tableXemDanhSach.getValueAt(rowSelected, 0) == "Không tìm thấy kết quả!") {
			JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu!", "Thông tin chi tiết",JOptionPane.ERROR_MESSAGE);
		} else {
			ImageIcon icon;
			if(tableXemDanhSach.getValueAt(rowSelected, 2).toString().trim().toLowerCase().equals("nam")) {
				icon = new ImageIcon(getClass().getResource("/images/man.png"));
			} else {
				icon = new ImageIcon(getClass().getResource("/images/woman.png"));
			}
			JOptionPane.showMessageDialog(null, msg, "Thông tin chi tiết",JOptionPane.INFORMATION_MESSAGE, icon);
		}
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
		txtNhapIP.setText("127.000.0.1");
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
		txtNhapMaSV.setEditable(false);
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
		txtNhapTenSV.setEditable(false);
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
		txtNhapDiem = new JTextField(15);
		txtNhapDiem.setEditable(false);
		pnNhapDiem.add(lbNhapDiem);
		pnNhapDiem.add(txtNhapDiem);
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
		btnTimKiem.setEnabled(false);
		btnXemTatCa = new JButton("Xem tất cả");
		btnXemTatCa.setIcon(new ImageIcon(getClass().getResource("/images/Tasks-icon.png")));
		btnXemTatCa.setEnabled(false);
		btnReset = new JButton("Reset");
		btnReset.setIcon(new ImageIcon(getClass().getResource("/images/reset.png")));
		btnThoat = new JButton("Thoát");
		btnThoat.setIcon(new ImageIcon(getClass().getResource("/images/log_out.png")));
		
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
		defaultTable.addColumn("Giới tính");
		defaultTable.addColumn("Điểm Đồ án CSNM");
		//them data
//		String data3[] = {"102170190", "Trần Thị Thu", "Nữ" ,"10"};
//		defaultTable.addRow(data3);
//		Vector<String> data2 = new Vector<String>();
//		data2.add("102150142");
//		defaultTable.addRow(data2);
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
