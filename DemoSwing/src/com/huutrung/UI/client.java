package com.huutrung.UI;

import com.huutrung.UI.Check;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HB
 */
public class client extends javax.swing.JFrame {

    //private DefaultTableModel dtm;
    public client() {
        initComponents();
        //showuser();
    }

    private Socket connect() throws Exception {
        //1.client: tao socket ket noi den server cho phep ket noi o cong 8080
        String addip;
        addip = ip.getText();
        Socket sk = null;
        sk = new Socket(addip, 9876);
        System.out.println("Client da duoc tao...");

        return sk;
    }

    private String connectToServer() throws Exception {
        Socket sk = connect();
        String ketqua = null;
        //3.3 tao luong ket noi den server
        try {
            DataOutputStream gui_server = new DataOutputStream(sk.getOutputStream());// tao luong gui di
            DataInputStream nhan_server = new DataInputStream(sk.getInputStream());// tao luong nhan vao
            gui_server.writeUTF("showAll");// gui du lieu len server
            System.out.println("socket.client.connectToServer()... send res \"showAll\"");

            ketqua = nhan_server.readUTF();
            System.out.println("Client nhan:" + ketqua);//doc tu sever
            sk.close();
        } catch (Exception e) {
            System.out.println("socket.client.connectToServer()...Loi gui, nhan Client");
            sk.close();
        }
        return ketqua;
    }

    public boolean kiemtrathongtin() {
        Check c = new Check();
        if (!c.checkID(MaSV1.getText())) {
            JOptionPane.showMessageDialog(this, "Nhập mã SV sai định dạng...", "Hãy nhập lại", JOptionPane.ERROR_MESSAGE);
            MaSV1.setText("");
            MaSV1.requestFocus();
            return false;
        } else if (!c.check_hoten(TenSV1.getText())) {
            //else if (!c.checkSpace(TenSV1.getText()) || !c.check(TenSV1.getText())) {
            JOptionPane.showMessageDialog(this, "Bạn nhập tên sinh viên chưa đúng định dạng...", "Hãy nhập lại", JOptionPane.ERROR_MESSAGE);
            TenSV1.setText("");
            TenSV1.requestFocus();
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        MaSV1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TenSV1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        DIem1 = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        search = new javax.swing.JButton();
        xem = new javax.swing.JButton();
        lammoi = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        ip = new javax.swing.JTextField();
        ketnoi = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        thongbao = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ban = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Quan ly diem sv - HaBinh");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(300, 0));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinhanh/Computer-Network-icon.png"))); // NOI18N
        jLabel1.setText("XEM ĐIỂM SINH VIÊN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin"));
        jPanel2.setLayout(new java.awt.GridLayout(3, 2));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinhanh/system.png"))); // NOI18N
        jLabel2.setText("Mã Sinh Viên *");
        jPanel2.add(jLabel2);

        MaSV1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaSV1ActionPerformed(evt);
            }
        });
        jPanel2.add(MaSV1);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinhanh/graduated-icon (1).png"))); // NOI18N
        jLabel3.setText("Tên Sinh Viên");
        jPanel2.add(jLabel3);

        TenSV1.setToolTipText("");
        jPanel2.add(TenSV1);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinhanh/calculate.png"))); // NOI18N
        jLabel4.setText("Điểm Sinh Viên");
        jPanel2.add(jLabel4);

        DIem1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        DIem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DIem1ActionPerformed(evt);
            }
        });
        jPanel2.add(DIem1);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Chọn"));

        search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinhanh/search2.png"))); // NOI18N
        search.setText("Tìm kiếm");
        search.setEnabled(false);
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        xem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinhanh/distributor-report-icon.png"))); // NOI18N
        xem.setText("Xem tất cả");
        xem.setEnabled(false);
        xem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xemActionPerformed(evt);
            }
        });

        lammoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinhanh/reset.png"))); // NOI18N
        lammoi.setText("Reset");
        lammoi.setEnabled(false);
        lammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lammoiActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hinhanh/Button-Delete-icon.png"))); // NOI18N
        jButton4.setText("Thoát");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(xem, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                    .addComponent(lammoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(xem, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhập Thông Tin Server"));

        jLabel5.setText("Nhập IP Server :");

        ip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ipActionPerformed(evt);
            }
        });

        ketnoi.setText("Kết Nối");
        ketnoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ketnoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ip, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ketnoi)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ip, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ketnoi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 172, Short.MAX_VALUE)
        );

        thongbao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        thongbao.setForeground(new java.awt.Color(255, 51, 0));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(thongbao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(thongbao, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
        );

        ban.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Sinh Viên", "Tên Sinh Viên", "Điểm Sinh Viên"
            }
        ));
        ban.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                banMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ban);

        jPanel7.setLayout(new java.awt.GridLayout(0, 3));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MaSV1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaSV1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MaSV1ActionPerformed

    //SEARCH
    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        if (!kiemtrathongtin()) {
            return;
        }
        //kiem tra masv ten sv khong dc rong
        if (!MaSV1.getText().trim().equals("") || !TenSV1.getText().trim().equals("") || !DIem1.getSelectedItem().toString().equals("...")) {
            //DefaultTableModel model = (DefaultTableModel) ban.getModel();//xuat ra tabe
            //model.addRow(new Object[]{MaSV1.getText(), TenSV1.getText(), DIem1.getSelectedItem().toString()});//xuat ra tabe
            try {               
                accept_Client(MaSV1.getText(), TenSV1.getText(), DIem1.getSelectedItem().toString());
            } catch (Exception ex) {
                Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
            }
            thongbao.setText("Đã gửi yêu cầu, xem kết quả ở dưới...");
    }//GEN-LAST:event_searchActionPerformed
        else
            thongbao.setText("Xin mời bạn nhập thông tin tìm kiếm...");
    }
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton4ActionPerformed

    //Kết nối
    private void ketnoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ketnoiActionPerformed
        Check c = new Check();
        if (!c.check_IP(ip.getText())) {
            JOptionPane.showMessageDialog(this, "Nhập địa chỉ IP bị sai...yêu cầu nhập lại dạng xxx.xxx.x.x  ", "Hãy nhập lại", JOptionPane.ERROR_MESSAGE);
            ip.setText("");
            ip.requestFocus();
        } else {
            try {
                String traloi = connectToServer();
                handleReturnData(traloi);//result
                if (null != traloi && !traloi.equals("DBError")) {
                    JOptionPane.showMessageDialog(null, "Kết nối thành công... ...");
                    search.setEnabled(true);
                    xem.setEnabled(true);
                    lammoi.setEnabled(true);
                    ketnoi.setEnabled(false);
                } else if (null != traloi && traloi.equals("DBError")) {
                    JOptionPane.showMessageDialog(null, "Khong the ket noi den database... ...");
                }

            } catch (Exception e) {
                System.out.print("Kết nối thất bại... ..."+e);
                JOptionPane.showMessageDialog(null, "Kết nối thất bại... ...");
            }
        }
    }//GEN-LAST:event_ketnoiActionPerformed

    // Xem all
    private void xemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xemActionPerformed
        String traloi = null;
        this.resetForm();
        try {
            traloi = connectToServer();
        } catch (Exception ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
            traloi = "Có lỗi xảy ra.";
        }
        handleReturnData(traloi);
    }//GEN-LAST:event_xemActionPerformed

    private void lammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lammoiActionPerformed
        this.resetForm();
    }//GEN-LAST:event_lammoiActionPerformed

    private void DIem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DIem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DIem1ActionPerformed

    private void ipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ipActionPerformed

    private void banMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_banMouseClicked
        int row = ban.getSelectedRow();
        if (row != -1) {
            String id = ban.getValueAt(row, 0).toString();
            MaSV1.setText(id);
            TenSV1.setText(ban.getValueAt(row, 1).toString());
            DIem1.setSelectedItem(ban.getValueAt(row, 2).toString());
        }
    }//GEN-LAST:event_banMouseClicked

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new client().setVisible(true);
            }
        });
    }

    public void resetForm() {
        DIem1.getSelectedItem();
        MaSV1.setText("");
        TenSV1.setText("");
        thongbao.setText("");
        DIem1.setSelectedIndex(0);
    }

    // Search service
    public void accept_Client(String masv, String tensv, String diem) throws Exception {
        //1.client: tao socket ket noi den server cho phep ket noi o cong 8080
        Socket sk = connect();
        String search = "search";

        //3.3 tao luong ket noi den sever
        DataOutputStream gui_server = new DataOutputStream(sk.getOutputStream());// tao luon gui di
        DataInputStream nhan_server = new DataInputStream(sk.getInputStream());// tao luon nhan vao

        if (diem.equals("...")) {
            diem = "";
        }
        gui_server.writeUTF(masv + "#" + tensv + "#" + diem + "#" + search);// gui du lieu len server
        String result = nhan_server.readUTF();
        System.out.println("Ket qua search:" + result);//doc tu sever
        sk.close();
        handleReturnData(result);
    }

    //xuat ra table
    private void handleReturnData(String result) {
        DefaultTableModel model = (DefaultTableModel) ban.getModel();
        model.getDataVector().clear();
        if (null != result
                && !result.isEmpty()
                && !result.equalsIgnoreCase("OK")
                && !result.equals("DBError")) {
            String[] students = result.split(";");
            if (null != students && students.length != 0) {
                for (int i = 0; i < students.length; i++) {
                    String student = students[i];
                    if (null != student && !student.isEmpty()) {
                        String[] parts = student.split(",");
                        model.addRow(new Object[]{parts[0], parts[1], parts[2]});
                    }
                }
            }
        } else {
            model.addRow(new Object[]{"Không có kết quả"});
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox DIem1;
    private javax.swing.JTextField MaSV1;
    private javax.swing.JTextField TenSV1;
    private javax.swing.JTable ban;
    private javax.swing.JTextField ip;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton ketnoi;
    private javax.swing.JButton lammoi;
    private javax.swing.JButton search;
    private javax.swing.JLabel thongbao;
    private javax.swing.JButton xem;
    // End of variables declaration//GEN-END:variables

}
