package model.bean;

public class NhanVien {
	String ma,chucVu,phongBan,matKhau,ten,sdt,diaChi;

	public String getMa() {
		return ma;
	}

	public void setMa(String ma) {
		this.ma = ma;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public String getPhongBan() {
		return phongBan;
	}

	public void setPhongBan(String phongBan) {
		this.phongBan = phongBan;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	@Override
	public String toString() {
		return "NhanVien [ma=" + ma + ", chucVu=" + chucVu + ", phongBan=" + phongBan + ", matKhau=" + matKhau
				+ ", ten=" + ten + ", sdt=" + sdt + ", diaChi=" + diaChi + "]";
	}

	public NhanVien(String ma, String chucVu, String phongBan, String matKhau, String ten, String sdt, String diaChi) {
		super();
		this.ma = ma;
		this.chucVu = chucVu;
		this.phongBan = phongBan;
		this.matKhau = matKhau;
		this.ten = ten;
		this.sdt = sdt;
		this.diaChi = diaChi;
	}

	public NhanVien() {
		super();
	}
	
}
