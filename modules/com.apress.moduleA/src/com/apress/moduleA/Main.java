package com.apress.moduleA;

import java.lang.reflect.InvocationTargetException;

import com.apress.moduleB.Employee;
import com.apress.moduleB.Employer;
import com.apress.moduleC.ClassC;
import com.apress.moduleE.ClassE;
import com.apress.service.IService;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException {
		
		// TODO Auto-generated method stub
		Employee employee = new Employee("John", "Albert");
		System.out.println("First name is : " + employee.getFirstName());
		System.out.println("Last name is : " + employee.getLastName());
		
		// 1. Public class is accessible using reflection
		Employer employer = (Employer) Class.forName("com.apress.moduleB.Employer").getConstructors()[0].newInstance(new Object[0]);
		employer.setFirstName("ABB");
		System.out.println(employer.getFirstName());

		// 2. Package class is NOT accessible using reflection
		try {
			Object o = Class.forName("com.apress.moduleB.ClassB").getConstructors()[0].newInstance(new Object[] { "Kos" });
			System.out.println(o);		
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
		// 3. Implied readability - module B requires transitive the types from module C.
		ClassC player = new ClassC();
		System.out.println(player);
		
		// 4. Open modules make all the packages inside of the module available for deep reflection
		IService service = (IService) Class.forName("com.apress.serviceimpl.ServiceImpl").getConstructors()[0].newInstance(new Object[0]);
		service.perform();
		
		// 5. com.apress.moduleE.ClassE is automatic module (unnamed placed on module path) 
		ClassE classE = new ClassE();
		System.out.println(classE);		
	}
}
