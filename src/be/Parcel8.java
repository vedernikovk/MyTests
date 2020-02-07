package be;

public class Parcel8 {
	  public Destination dest(String dest) {
		  
		  int k = 0;
		  return new Destination() {
			  private String label = dest;
			  public String readLabel() {
				  return label + k; 
			  }
	    };
	  }
	  public static void main(String[] args) {
	    Parcel8 p = new Parcel8();
	    Destination d = p.dest("Tanzania");
	    System.out.println(d.readLabel());
	  }
}