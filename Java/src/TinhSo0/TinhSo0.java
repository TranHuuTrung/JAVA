package TinhSo0;

public class TinhSo0 {
	public static void main(String[] args) {
		long count = 0;
		System.out.println(Dem(111111111));
	}
	public static long Dem(long n) {
		if(n == 0) return 0;
		else return Dem(n/5) + n/5;
	}
}
