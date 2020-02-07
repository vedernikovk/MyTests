package generics;

public class CovarianceTest {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		Generic<Number> numberContainer = new Generic<Number>();
		numberContainer.write(new Integer(1));
		numberContainer.write(new Double(1));
		Number aNumber = numberContainer.read();
		
		/* Covariance */
		
		Generic<? extends Number> covariantRef;
		
		covariantRef = new Generic<Number>();
		covariantRef = new Generic<Integer>();
		covariantRef = new Generic<Double>();
		// covariantRef = new Generic<Object>(); // Error
		
		Number number  = covariantRef.read(); // PEcs 	
		Object object  = covariantRef.read();	
		Double doubl; // = covariantRef.read(); // Error
		Integer integer; //  = covariantRef.read(); // Error
		
		covariantRef.write(null);
		// covariantRef.write(object); // Error
		// covariantRef.write(number); // Error
		// covariantRef.write(integer);	// Error
		
		
	    /* Contrvariance */
		Generic<Number> numberContainer2 = new Generic<Number>();
		numberContainer2.write(new Integer(1));
		numberContainer2.write(new Double(2));
		Number bNumber = numberContainer2.read();		
		
		Generic<? super Integer> contravariantRef;
		
		contravariantRef = new Generic<Integer>();
		contravariantRef = new Generic<Number>();
		contravariantRef = new Generic<Object>();

		contravariantRef.write(new Integer(0)); // peCS
		// contravariantRef.write(number); // Error
		// contravariantRef.write(new Object()); // Error

		// integer = contravariantRef.read(); // Error
		// number = contravariantRef.read(); // Error
		object = contravariantRef.read();
		
	    /* Contrvariance 2*/
		
		Generic<? super Number> contravariantRef2;
		
		// contravariantRef2 = new Generic<Integer>(); // Error
		contravariantRef2 = new Generic<Number>();
		contravariantRef2 = new Generic<Object>();

		
		contravariantRef2.write(new Integer(0));
		contravariantRef2.write(number);
		//contravariantRef2.write(new Object());  // Error

		// integer = contravariantRef.read(); // Error - what if it is child of integer?
		// number = contravariantRef.read(); // Error - what if it is child of number?
		object = contravariantRef2.read();			

	}
}

class Generic<T> {
	T data;
	
	void write(T data) { 
		this.data = data; 
	} // T is input parameter type
	
	T read() { 
		return data; 
	} 
} 
