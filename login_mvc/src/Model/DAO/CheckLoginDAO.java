package Model.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import Model.Bean.Wife;

public class CheckLoginDAO {
	Connection connection;
	public boolean isExistUser(String userName, String password) {
		//connect DB, write SQL statement
		return true;
	}
	public ArrayList<Wife> getWifeList(String userName){
		ArrayList<Wife> result = new ArrayList<Wife>();
		//connect DB, truy van csdl
		Wife wife = new Wife();
		wife.setName("Tran Huu Trung");
		wife.setAddress("Da Nang");
		wife.setAlive(true);
		result.add(wife);
		return result;
	}
}
