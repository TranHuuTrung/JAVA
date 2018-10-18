package model.bean;

import java.util.Date;

public class PhongBan {
	String ma;
	String ten;
	Date ngaytao;
	public String getMa() {
		return ma;
	}
	public void setMa(String ma) {
		this.ma = ma;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public Date getNgaytao() {
		return ngaytao;
	}
	public void setNgaytao(Date ngaytao) {
		this.ngaytao = ngaytao;
	}
	@Override
	public String toString() {
		return "PhongBan [ma=" + ma + ", ten=" + ten + ", ngaytao=" + ngaytao + "]";
	}
	public PhongBan(String ma, String ten, Date ngaytao) {
		super();
		this.ma = ma;
		this.ten = ten;
		this.ngaytao = ngaytao;
	}
	public PhongBan() {
		super();
	}
	
}
