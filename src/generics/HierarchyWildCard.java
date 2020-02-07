package generics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HierarchyWildCard {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HierarchyWildCard h = new HierarchyWildCard();

		List<Boolean> booleans = new ArrayList<Boolean>();
		booleans.add(Boolean.FALSE);
		booleans.add(Boolean.TRUE);

		//h.print0(booleans); - Error
		h.print1(booleans);
		//h.print2(booleans); - Error
		//h.print3(booleans); - Error
		//h.print4(booleans); - Error

		List<Number> numbers = new ArrayList<Number>();
		numbers.add(new Double(1.4));
		numbers.add(new Integer(2));
		numbers.add(new BigDecimal(3));

		// h.print0(numbers); - Error
		h.print1(numbers);
		h.print2(numbers);
		//h.print3(numbers); - Error
		h.print4(numbers);

		List<Integer> ints = new  ArrayList<Integer>();
		ints.add(5);
		ints.add(6);
		ints.add(7);

		//h.print0(ints); - Error
		h.print1(ints);
		h.print2(ints);
		h.print3(ints);
		//h.print4(ints); - Error
		
		List<Object> objects = new ArrayList<Object>();
		objects.add("String one");
		objects.add("String two");
		objects.add("String tree");
		
		h.print0(objects);
		h.print1(objects);
		//h.print2(objects); - Error
		//h.print3(objects); - Error
		h.print4(objects);
		
		h.print5(ints);
		h.print5(numbers);
		h.print5(objects);
	}

	private void print5(List<? super Integer> list) {
		list.add(12);
		
		System.out.println("Printing objects in List<? super Integer> list: ");
		for (Object object: list) {
			System.out.println(object);
		}	
	}
	
	private void print4(List<? super Number> list) {
		list.add(12);
		
		System.out.println("Printing objects in List<? super Number> list: ");
		for (Object object: list) {
			System.out.println(object);
		}	
	}
	
	private void print3(List<? extends Integer> list) {
		//list.add(new Integer(1)); // error
		
		System.out.println("Printing numbers in List<? extends Integer> list: ");
		for (Integer integer: list) {
			System.out.println(integer.intValue());
		}
	}

	private void print2(List<? extends Number> list) {
		//list.add(new Float(1.0)); // error
		
		System.out.println("Printing numbers in List<? extends Number> list: ");
		for (Number number : list) {
			System.out.println(number.doubleValue());
		}
	}

	private void print1(List<?> list) {
		// list.add(new Integer(1)); // error
		System.out.println("Printing objects in List<?> list: ");
		for (Object o : list) {
			System.out.println(o.toString());
		}
	}

	private void print0(List<Object> list) {
		System.out.println("Printing objects in List<?> list: ");
		for (Object o : list) {
			System.out.println(o.toString());
		}
	}
}
