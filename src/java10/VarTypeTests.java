package java10;

public class VarTypeTests {

	
	public static void main(String[] args) {
		
		// 1. Type is Shape
		var shape = new Shape();

		if (shape instanceof Shape) {
			System.out.println("1. It is Shape");
		} 

		if (shape instanceof Circle) {
			System.out.println("1. It is Circle");
		} 

		if (shape instanceof Rectangle) {
			System.out.println("1. It is Rectangle");
		}
		
		// 2. Type is Circle
		var circle = new Circle();

		if (circle instanceof Shape) {
			System.out.println("2. It is Shape");
		} 

		if (circle instanceof Circle) {
			System.out.println("2. It is Circle");
		} 

		// Compilation error
		//if (circle instanceof Rectangle) { 
		//	System.out.println("2. It is Rectangle");
		//} 

		// 3. Type is Shape
		var shape2 = new Shape();

		if (shape2 instanceof Shape) {
			System.out.println("3. It is Shape");
		}
		
		if (shape2 instanceof Circle) {
			System.out.println("3. It is Circle");
		} 
		
		shape2 = new Circle();
		if (shape2 instanceof Shape) {
			System.out.println("3. It is Shape");
		} 

		
		if (shape2 instanceof Circle) {
			System.out.println("3. It is Circle");
		} 
	}

}

class Shape {
	
}

class Circle extends Shape {
	
}

class Rectangle extends Shape {
	
}
