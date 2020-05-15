package com.apress.moduleD;

import java.lang.reflect.InvocationTargetException;

import com.apress.moduleA.Main;
import com.apress.moduleC.ClassC;
import com.apress.moduleE.ClassE;

public class ClassD {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException {
		// Everything on class path is available for the unnamed module
		Main classA = new Main();
		Main.main(new String[0]);
		System.out.println(classA);
		
		ClassC classC = new ClassC(); 
		System.out.println(classC);
		
		ClassE classE = new ClassE();
		System.out.println(classE);
	}
}
