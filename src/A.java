
public class A {

	public static void aa() {
		System.out.println("in A.aa()");
	}
	
	public static void main(String[] args) throws Exception {
		new A().a();
	}
	
	public void a () {
		System.out.println(A.this);		
	}
	
	public String toString() {
		return "The A";
	}
}
