package TestAutocomplete;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TestAutoComplete extends JFrame{
	JTextField txtNhapTen;
	ArrayList<String> name = new ArrayList<>();
	public static void main(String[] args) {
		new TestAutoComplete();
	}
	
	public TestAutoComplete() {
		DBName();
		
		JFrame fr = new JFrame("AutoComplete");
		fr.setSize(500, 300);
		fr.setLocationRelativeTo(null);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setLayout(null);
		
		JLabel lbNhapTen = new JLabel("Nhap ten: ");
		lbNhapTen.setBounds(40, 40, 100, 30);
		fr.add(lbNhapTen);
		
		txtNhapTen = new JTextField();
		txtNhapTen.setBounds(150, 40, 300, 30);
		fr.add(txtNhapTen);

		
		txtNhapTen.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_BACK_SPACE:
					break;
				case KeyEvent.VK_ENTER:
					txtNhapTen.setText(txtNhapTen.getText());
					break;
				default:
					EventQueue.invokeLater(new Runnable() {
						@Override
						public void run() {
							String txt = txtNhapTen.getText();
							autoComplete(txt);
						}
					});
				}
			}
		});
		
		fr.setVisible(true);
	}
	
	public void DBName() {
		try {
			Connection connects = new connectDB().connDB();
			Statement stm = connects.createStatement();
			String sql = "select * from DiemThi";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String TenSV = rs.getString("TenSV");
				name.add(TenSV);
				System.out.println(TenSV);
			}
			rs.close();
			stm.close();
			connects.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			txtNhapTen.setText(complete);
			txtNhapTen.setCaretPosition(last);
			txtNhapTen.moveCaretPosition(start);
		}
	}
}
