package model.bean;

public class NhanVien {
	private Long MaNV;
	private String TenNV;
	private String ChucVu;
	private String DiaChi;
	private String SDT;
	private Long IdPhongBan;
	private String TenPB;
	public NhanVien() {
		super();
	}
	public NhanVien(Long MaNV, String TenNV, String ChucVu, String DiaChi, String SDT, Long IdPhongBan, String TenPB) {
		super();
		this.MaNV = MaNV;
		this.TenNV = TenNV;
		this.ChucVu = ChucVu;
		this.SDT = SDT;
		this.DiaChi = DiaChi;
		this.IdPhongBan = IdPhongBan;
		this.TenPB = TenPB;
	}
	
	public NhanVien(Long MaNV, String TenNV, String ChucVu, String DiaChi, String SDT, Long IdPhongBan) {
		super();
		this.MaNV = MaNV;
		this.TenNV = TenNV;
		this.ChucVu = ChucVu;
		this.SDT = SDT;
		this.DiaChi = DiaChi;
		this.IdPhongBan = IdPhongBan;
	}
	
	public NhanVien(String TenNV, String ChucVu, String DiaChi, String SDT, Long IdPhongBan) {
		super();
		this.TenNV = TenNV;
		this.ChucVu = ChucVu;
		this.SDT = SDT;
		this.DiaChi = DiaChi;
		this.IdPhongBan = IdPhongBan;
	}

	
	public Long getMaNV() {
		return this.MaNV;
	}
	
	public void setMaNV(Long MaNV) {
		this.MaNV = MaNV;
	}
	
	public String getTenNV() {
		return this.TenNV;
	}
	
	public void setTenNV(String TenNV) {
		this.TenNV = TenNV;
	}
	
	public String getChucVu() {
		return this.ChucVu;
	}
	
	public void setChucVu(String ChucVu) {
		this.ChucVu = ChucVu;
	}
	
	public String getDiaChi() {
		return this.DiaChi;
	}
	
	public void setDiaChi(String DiaChi) {
		this.DiaChi = DiaChi;
	}
	public String getSDT() {
		return this.SDT;
	}
	
	public void setSDT(String SDT) {
		this.SDT = SDT;
	}
	
	public String getTenPB() {
		return this.TenPB;
	}
	
	public void setTenPB(String TenPB) {
		this.TenPB = TenPB;
	}
	
	public Long getIdPhongBan() {
		return this.IdPhongBan;
	}
	
	public void setIdPhongBan(Long IdPhongBan) {
		this.IdPhongBan = IdPhongBan;
	}
}
