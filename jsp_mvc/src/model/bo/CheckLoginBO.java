package model.bo;

import model.bean.User;
import model.dao.CheckLoginDAO;

public class CheckLoginBO {
	CheckLoginDAO checkLoginDAO = new CheckLoginDAO();
	public User checkValidUser(String userName, String passWord) {
		return checkLoginDAO.CheckExistUser(userName, passWord);
	}
}
