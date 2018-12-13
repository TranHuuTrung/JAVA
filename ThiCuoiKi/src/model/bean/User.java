package model.bean;

public class User {
	private Long id;
	private String userName;
	private String passWord;
	
	public User() {
		super();
	}
	
	public User(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassWord() {
		return this.passWord;
	}
	
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
