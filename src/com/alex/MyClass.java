package com.alex;

//import static com.alex.IOUtils.readString;
import static com.alex.IOUtils.readDouble;
//import static com.alex.IOUtils.readInt;
import static java.lang.Math.sqrt;

import java.io.IOException;

public class MyClass {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		System.out.println("Enter a:");
		double a = readDouble();
		
		System.out.println("Enter b:");
		double b = readDouble();
		
		System.out.println("Enter c:");
		double c = readDouble();
		
		double d = sqrt(b*b - 4*a*c);
		double x = (((-b + d) / (2 * a))+3);
		Thread.sleep(500);
		System.out.println("I'm thinking...");
		Thread.sleep(3000);
		
	    System.out.println("The answer is " + x);
	    Thread.sleep(5000);
	    System.out.println("Hmm...");
	    Thread.sleep(2000);
	    System.out.println("Or maybe not...");
	    Thread.sleep(2000);
	    System.out.println("You should do the math yourself");
	    Thread.sleep(6666);
	    x = x - 3;
	    
	    System.out.println("Well, if you're still here..." );
	    Thread.sleep(1000);
	    System.out.println("The REAL answer is " + x );
	}
}
