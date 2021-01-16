package interviews.recursion; 

public class StringReverse {

	
	public static void main(String[] args) throws Exception {
		reverse("abcde");
	}

	public static void reverse(String s) {
		if (s.length() > 1) {
			reverse (s.substring(1));			
			System.out.print(s.charAt(0));
		} else {
			System.out.print(s);
		}
	}	
}
