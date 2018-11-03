package GiaoDien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class ClientUI extends JFrame{
	
	private JTextField txtNhapThongtin;
	private JTextField txtTruyVan;
	private JButton btnOK, btnReset, btnExit;
	DefaultTableModel defaultTable;
	JTable tableKetQua;
	public ClientUI(String title) {
		super(title);
		addControls();
		addEvents();
	}

	private void addEvents() {
		btnExit.addActionListener(new ActionListener() {
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
				txtNhapThongtin.setText("");
				txtTruyVan.setText("");
			}
		});
		
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Thực hiện câu lệnh SQL nhé!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		tableKetQua.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				XuLiXemChiTiet();
			}
			private void XuLiXemChiTiet() {
				int rowSelected = tableKetQua.getSelectedRow();
				if (rowSelected == -1) return;
				String msg = "";
				String id = "ID: ";
					id+=tableKetQua.getValueAt(rowSelected, 0)+"\n";
					msg+=id;
				String tenkh = "Tên Khách Hàng: ";
					tenkh+=tableKetQua.getValueAt(rowSelected, 1)+"\n";
					msg+=tenkh;
				String gioitinh = "Giới tính: ";
					gioitinh+=tableKetQua.getValueAt(rowSelected, 2)+"\n";
					msg+=gioitinh;
				String diachi = "Địa chỉ: ";
					diachi+=tableKetQua.getValueAt(rowSelected, 3)+"\n";
					msg+=diachi;
				String luong = "Lương: ";
					luong+=tableKetQua.getValueAt(rowSelected, 4)+"\n";
					msg+=luong;
				if(tableKetQua.getValueAt(rowSelected, 0) == "Không tìm thấy kết quả!") {
					JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu!", "Thông tin chi tiết",JOptionPane.ERROR_MESSAGE);
				} else {
					ImageIcon icon;
					if(tableKetQua.getValueAt(rowSelected, 2).toString().trim().toLowerCase().equals("nam")) {
						icon = new ImageIcon(getClass().getResource("/images/man.png"));
					} else {
						icon = new ImageIcon(getClass().getResource("/images/woman.png"));
					}
					JOptionPane.showMessageDialog(null, msg, "Thông tin chi tiết",JOptionPane.INFORMATION_MESSAGE, icon);
				}
			}
		});
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
		lbHeader.setText("TRUY XUẤT CƠ SỞ DỮ LIỆU");
		pnHeader.add(lbHeader);
		//Phan Body
		JPanel pnBody = new JPanel();
		pnMain.add(pnBody, BorderLayout.CENTER);
		pnBody.setBorder(new EmptyBorder(15, 5, 0, 5));
		pnBody.setLayout(new BoxLayout(pnBody, BoxLayout.Y_AXIS));
		/*Nhap thong tin*/
		JPanel pnNhapThongTin = new JPanel();
		pnBody.add(pnNhapThongTin);
		pnNhapThongTin.setLayout(new BoxLayout(pnNhapThongTin, BoxLayout.X_AXIS));
		
		JLabel lbNhapThongTin = new JLabel("Nhập thông tin");
		lbNhapThongTin.setBorder(new EmptyBorder(0, 5, 0, 10));
		txtNhapThongtin = new JTextField(15);
		txtNhapThongtin.setHorizontalAlignment(SwingConstants.CENTER);
		pnNhapThongTin.add(lbNhapThongTin);
		pnNhapThongTin.add(txtNhapThongtin);
		pnNhapThongTin.setBorder(new EmptyBorder(5, 0, 5, 0));
		
//		Border borderThongTinKetNoi = BorderFactory.createLineBorder(Color.BLUE);
//		TitledBorder titleThongTinKetNoi = new TitledBorder(borderThongTinKetNoi, "Nhập thông tin kết nối Server");
//		pnNhapThongTin.setBorder(titleThongTinKetNoi);
//		titleThongTinKetNoi.setTitleColor(Color.RED);
		//nhap cau lenh truy van
		JPanel pnTruyVan = new JPanel();
		pnBody.add(pnTruyVan);
		pnTruyVan.setLayout(new BoxLayout(pnTruyVan, BoxLayout.X_AXIS));
		JLabel lbTruyVan = new JLabel("SQL");
		lbTruyVan.setBorder(new EmptyBorder(0, 5, 0, 10));
		txtTruyVan = new JTextField(15);
		txtTruyVan.setHorizontalAlignment(SwingConstants.CENTER);
		lbTruyVan.setPreferredSize(lbNhapThongTin.getPreferredSize());
		pnTruyVan.add(lbTruyVan);
		pnTruyVan.add(txtTruyVan);
		pnTruyVan.setBorder(new EmptyBorder(5, 0, 15, 0));
		//table hien thi ket qua truy van
		JPanel pnTableKetQua = new JPanel();
		pnTableKetQua.setLayout(new BorderLayout());
		pnBody.add(pnTableKetQua);
		defaultTable = new DefaultTableModel();
		defaultTable.addColumn("ID");
		defaultTable.addColumn("Tên Khách Hàng");
		defaultTable.addColumn("Giới tính");
		defaultTable.addColumn("Địa chỉ");
		defaultTable.addColumn("Lương");
		//them data
		String data3[] = {"KH01", "Trần Thị Thu","Nữ","Lien Chieu - Da Nang" ,"10"};
		defaultTable.addRow(data3);
//		Vector<String> data2 = new Vector<String>();
//		data2.add("102150142");
//		defaultTable.addRow(data2);
		tableKetQua = new JTable(defaultTable) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				  return false; //Disallow the editing of any cell
			}
		};
		JScrollPane scTable = new JScrollPane(tableKetQua,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnTableKetQua.add(scTable, BorderLayout.CENTER);
		pnTableKetQua.setPreferredSize(new Dimension(0, 250));
		//phan cac button
		JPanel pnBtnGroup = new JPanel();
		pnBody.add(pnBtnGroup);
		pnBtnGroup.setLayout(new FlowLayout());
		
		btnOK = new JButton("OK");
		btnOK.setIcon(new ImageIcon(getClass().getResource("/images/ok.png")));
		btnReset = new JButton("Reset");
		btnReset.setIcon(new ImageIcon(getClass().getResource("/images/reset.png")));
		btnExit = new JButton("Exit");
		btnExit.setIcon(new ImageIcon(getClass().getResource("/images/log_out.png")));
		
		pnBtnGroup.add(btnOK);
		pnBtnGroup.add(btnReset);
		pnBtnGroup.add(btnExit);
		//Phan Footer
		JPanel pnFooter = new JPanel();
		pnMain.add(pnFooter, BorderLayout.SOUTH);
		pnFooter.setBackground(new Color(153, 255, 153));
		pnFooter.setBorder(new EmptyBorder(10, 10, 10, 10));
		JLabel lbFooter = new JLabel("Copyright© by TranHuuTrung - 11/2018");
		lbFooter.setForeground(new Color(0, 102, 204));
	    pnFooter.add(lbFooter);
	}
	
	public void showUI() {
		this.setSize(700, 550);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
