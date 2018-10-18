package model.bean;

public class User {
	private Long id;
	private String pass;
	private String email;
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", pass=" + pass + ", email=" + email + "]";
	}
	public User(Long id, String pass, String email) {
		super();
		this.id = id;
		this.pass = pass;
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
