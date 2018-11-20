package model.bean;

public class PhongBan {
	private Long IdPB;
	private String TenPB;
	
	public PhongBan() {
		super();
	}
	
	public PhongBan(Long IdPB, String TenPB) {
		super();
		this.IdPB = IdPB;
		this.TenPB = TenPB;
	}
	
	public Long getIdPB() {
		return this.IdPB;
	}
	
	public void setIdPB(Long IdPB) {
		this.IdPB = IdPB;
	}
	
	public String getTenPB() {
		return this.TenPB;
	}
	
	public void setTenPB(String TenPB) {
		this.TenPB = TenPB;
	}
}
