package model.bo;

import java.util.ArrayList;

import model.bean.PhongBan;
import model.dao.PhongBanDAO;

public class PhongBanBO {
	PhongBanDAO phongBanDAO = new PhongBanDAO();
	public ArrayList<PhongBan> getPhongBanList(){
		return phongBanDAO.getPhongBanList();
	}
	
	public boolean isDeletePhongBanSuccess(Long IdPB) {
		return phongBanDAO.isDeletePhongBanSuccess(IdPB);
	}
	
	public boolean isThemPhongBanSuccess(String TenPB) {
		return phongBanDAO.isThemPhongBanSuccess(TenPB);
	}
	
	public PhongBan getPhongBanById(Long IdPB) {
		return phongBanDAO.getPhongBanById(IdPB);
	}
	
	public boolean isEditPhongBanSuccess(Long IdPB, String TenPB) {
		return phongBanDAO.isEditPhongBanSuccess(IdPB, TenPB);
	}
}
