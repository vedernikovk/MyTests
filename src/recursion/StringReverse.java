package recursion;

public class StringReverse {

	public static void main(String[] args) {
		reverse("qwe");
	}
	
	public static void reverse(String s) {
		if (s.length() == 0) {
			return;
		} else {
			reverse(s);
			System.out.print(s.charAt(s.length() -1));
		}
	}
}
