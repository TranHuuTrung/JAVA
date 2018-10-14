package UDP_DoiChuoi;

public class XuLi {
	public String Hoa(String s) {
		s = s.trim();
		String tam = "";
		for(int i = 0; i< s.length(); i++) {
			char c = s.charAt(i);
			if(c>='a' && c<='z') {
				c = (char) (c-32);
				tam += c;
			}else {
				tam += c;
			}
		}
		return tam;
	}
	
	public String Thuong(String s) {
		s = s.trim();
		String tam = "";
		for (int i=0; i< s.length(); i++) {
			char c = s.charAt(i);
			if(c>= 'A' && c <= 'Z') {
				c = (char) (c+32);
				tam += c;
			} else {
				tam += c;
			}
		}
		return tam;
	}
	
//	public int DemSoTu(String s) {
//		int count = 0;
//		try {
//			for (int i = 0; i < s.length(); i++) {
//				if(s.charAt(i)=='' && s.charAt(i+1)!= '' && s.charAt(i+1)!= '\0') {
//					count++;
//				}
//				
//			}
//			if (s.charAt(0) != '') {
//				count++;
//			}
//			return count;
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}
	
	public static void main(String[] args) {
		XuLi xLi = new XuLi();
		System.out.println(xLi.Hoa("TRAN HUU trung"));
	}
}
