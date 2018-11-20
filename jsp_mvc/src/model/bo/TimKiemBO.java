package model.bo;

import java.util.ArrayList;

import model.bean.NhanVien;
import model.bean.PhongBan;
import model.dao.TimKiemDAO;

public class TimKiemBO {
	TimKiemDAO timKiemDAO = new TimKiemDAO();
	public ArrayList<NhanVien> getSearchNhanVienList(String searchQuery){
		return timKiemDAO.getSearchNhanVienList(searchQuery);
	}
	
	public ArrayList<NhanVien> getSearchPhongBanList(String searchType,String searchQuery) {
		return timKiemDAO.getSearchPhongBanList(searchType,searchQuery);
	}
}
