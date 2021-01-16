package generics;

public class CovarianceTest {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		Generic<Number> numberContainer = new Generic<Number>();
		numberContainer.write(new Integer(1));
		numberContainer.write(new Double(1));
		Number aNumber = numberContainer.read();
		
		/*********************** Covariance **********************************************/
		
		Generic<? extends Number> covariantRef;

		// 1. Base
		covariantRef = numberContainer;

		// 2. Integer child
		Generic<Integer> integerContainer = new Generic<Integer>();
		integerContainer.write(new Integer(2));
		Integer i = integerContainer.read();
		
		covariantRef = integerContainer;
				
		// 3. Double child
		Generic<Double> doubleContainer = new Generic<Double>();   
		doubleContainer.write(new Double(1));
		Double d = doubleContainer.read();
		
		covariantRef = doubleContainer;
		
		// covariantRef = new Generic<Object>(); // Error
		
		Number number  = covariantRef.read(); // PEcs 	
		Object object  = covariantRef.read();	
		Double doubl; // = covariantRef.read(); // Error - what if it's integerContainer?
		Integer integer; //  = covariantRef.read(); // Error - what if it's doubleContainer?
		
		covariantRef.write(null);
		// covariantRef.write(object); // Error
		// covariantRef.write(number);  // Error - what if number is a double and covariantRef is integerContainer ?
		// covariantRef.write(integer);	// Error - what if covariantRef is doubleContainer ?
		
		
	    /**************************** Contrvariance ***********************************/
		
		Generic<Number> numberContainer2 = new Generic<Number>();
		numberContainer2.write(new Integer(1));
		numberContainer2.write(new Double(2));
		Number bNumber = numberContainer2.read();		
		
		Generic<? super Integer> contravariantRef;

		// 1. Leaf
		Generic<Integer> integerContainer2 =  new Generic<Integer>();
		integerContainer2.write(new Integer(3));
		
		contravariantRef = integerContainer2;
		
		// 2. parent
		contravariantRef = numberContainer2;
		
		// 3. Root
		Generic<Object> objectContainer = new Generic<Object>();
		objectContainer.write(new Object());
		
		contravariantRef = objectContainer;

		contravariantRef.write(new Integer(0));  // peCS
		// contravariantRef.write(number);       // Error what if contravariantRef is integerContainer2 and number is a Double?
		// contravariantRef.write(new Object()); // Error what if the object is a....?

		// integer = contravariantRef.read(); // Error - what if contravariantRef is numberContainer2?
		                                      // what if the numberContainer contains Double?
		
		// number = contravariantRef.read();  // Error - what if contravariantRef is objectContainer?
		                                      // what if the objectContainer contains ...?
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
	private T data;
	
	void write(T data) { 
		this.data = data; 
	} // T is input parameter type
	
	T read() { 
		return data; 
	} 
} 
