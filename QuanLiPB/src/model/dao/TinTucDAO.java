//package model.dao;
//
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//
//
///**
// *
// * @author Tran Van Thanh
// */
//public class TinTucDao{
//
//    private DbConnectLibrary dbcn;
//    private Connection conn;
//    private Statement st;
//    private PreparedStatement pst;
//    private ResultSet rs;
//    
//    public TinTucDao(){
//        dbcn = new DbConnectLibrary();
//    }
//    
//    public ArrayList<TinTuc> getItems(){
//        ArrayList<TinTuc> alTinTuc = new ArrayList<>();
//        
//        conn = dbcn.getConectMySQL();
//        String sql = "SELECT * FROM tintuc";
//        try {
//            st = conn.createStatement();
//            rs = st.executeQuery(sql);
//            while (rs.next()){
//                int id = rs.getInt(1);
//                String tenTin = rs.getString(2);
//                String danhMuc = rs.getString(3);
//                String moTa = rs.getString(4);               
//                Timestamp time = rs.getTimestamp(5);
//                
//                TinTuc objTT = new TinTuc(id, tenTin, danhMuc, moTa, time);
//                alTinTuc.add(objTT);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(TinTucDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return alTinTuc;
//    }
//
//    public int addItem(TinTuc objTT) {
//        int result = 0;
//        String sql = "INSERT INTO tintuc(tentin, danhmuc, mota, ngaydang) VALUES (?, ?, ?, ?)";
//        conn = dbcn.getConectMySQL();
//        
//        try {
//            pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//            pst.setString(1, objTT.getTenTin());
//            pst.setString(2, objTT.getDanhMuc());
//            pst.setString(3, objTT.getMoTa());
//            pst.setTimestamp(4, new java.sql.Timestamp(objTT.getDate().getTime()));
//            pst.executeUpdate();
//            rs = pst.getGeneratedKeys();
//            if (rs.next()){
//                result = rs.getInt(1);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(TinTucDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return result;
//    }
//
//    public int deleItem(TinTuc objTT) {
//        int result = 0;
//        String sql = "DELETE FROM tintuc WHERE id = ?";
//        conn = dbcn.getConectMySQL();
//        try {
//            pst = conn.prepareStatement(sql);
//            pst.setInt(1, objTT.getId());
//            result = pst.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(TinTucDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return result;
//    }
//
//    public int editItem(TinTuc objTT) {
//        int result = 0;
//        String sql = "UPDATE tintuc SET tentin = ?, danhmuc = ?, mota = ?, ngaydang = ? WHERE id = ?";
//        conn = dbcn.getConectMySQL();
//        
//        try {
//            pst = conn.prepareStatement(sql);
//            pst.setString(1, objTT.getTenTin());
//            pst.setString(2, objTT.getDanhMuc());
//            pst.setString(3, objTT.getMoTa());
//            pst.setTimestamp(4, new java.sql.Timestamp(objTT.getDate().getTime()));
//            pst.setInt(5, objTT.getId());
//            result = pst.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(TinTucDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return result;
//    }
//}
