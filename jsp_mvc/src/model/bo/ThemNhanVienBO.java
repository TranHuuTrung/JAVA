package model.bo;

import java.util.ArrayList;

import model.bean.PhongBan;
import model.dao.ThemNhanVienDAO;

public class ThemNhanVienBO {
	ThemNhanVienDAO themNhanVienDAO = new ThemNhanVienDAO();
	public ArrayList<PhongBan> listphongBan(){
		return themNhanVienDAO.listPhongBan();
	}
	
	public boolean isThemNhanVienSuccess(String TenNV,Long IdPB, String ChucVu, String DiaChi, String SDT) {
		return themNhanVienDAO.isThemNhanVienSuccess(TenNV, IdPB, ChucVu, DiaChi, SDT);
	}
}
