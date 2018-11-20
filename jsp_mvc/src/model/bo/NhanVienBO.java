package model.bo;

import java.util.ArrayList;

import model.bean.NhanVien;
import model.dao.NhanVienDAO;

public class NhanVienBO {
		NhanVienDAO nhanVienDAO = new NhanVienDAO();
		public ArrayList<NhanVien> getNhanVienList(){
			return nhanVienDAO.getNhanVienList();
		}
		
		public boolean isDeleteNhanVienSuccess(Long id) {
			return nhanVienDAO.isDeleteNhanVienSuccess(id);
		}
		
		public NhanVien getNhanVienById(Long MaNV) {
			return nhanVienDAO.getNhanVienById(MaNV);
		}
		
		public boolean isEditNhanVienSuccess(Long MaNV, String TenNV,Long IdPB, String ChucVu, String DiaChi, String SDT) {
			return nhanVienDAO.isEditNhanVienSuccess(MaNV, TenNV, IdPB, ChucVu, DiaChi, SDT);
		}
}
