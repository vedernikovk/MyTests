package interviews; 

public class StringReverse {

	
	public static void main(String[] args) throws Exception {
		reverse("abc");
	}

	public static void reverse(String s) {
		if (s.length() == 1) {
			System.out.print(s.charAt(0));
			return;
		} else {
			reverse (s.substring(1));
		}
	}	
}
