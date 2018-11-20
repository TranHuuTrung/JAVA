package model.bo;

import model.dao.CheckDangKiDAO;

public class CheckDangKiBO {
	CheckDangKiDAO checkDangKiDAO = new CheckDangKiDAO();
	public boolean isDangKiSuccess(String userName, String passWord) {
		return checkDangKiDAO.isDangKiSuccess(userName, passWord);
	}
}
