package generics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HierarchyBounded {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HierarchyBounded h = new HierarchyBounded();

		List<Boolean> booleans = new ArrayList<Boolean>();
		booleans.add(Boolean.FALSE);
		booleans.add(Boolean.TRUE);

		h.print1(booleans);
		//h.print2(booleans); //- Error
		//h.print3(booleans); //- Error

		List<Number> numbers = new ArrayList<Number>();
		numbers.add(new Double(1.4));
		numbers.add(new Integer(2));
		numbers.add(new BigDecimal(3));

		h.print1(numbers);
		h.print2(numbers);
		//h.print3(numbers); //- Error

		List<Integer> ints = new  ArrayList<Integer>();
		ints.add(5);
		ints.add(6);
		ints.add(7);

		h.print1(ints);
		h.print2(ints);
		h.print3(ints);
	}


	private <T extends Integer> void print3(List<T> list) {
		System.out.println("Printing numbers in List<? extends Integer> list: ");
		for (Integer integer: list) {
			System.out.println(integer.intValue());
		}
	}

	private <T extends Number> void print2(List<T> list) {
		System.out.println("Printing numbers in List<? extends Number> list: ");
		for (Number number : list) {
			System.out.println(number.doubleValue());
		}
	}

	private <T> void print1(List<T> list) {
		System.out.println("Printing objects in List<?> list: ");
		for (Object o : list) {
			System.out.println(o.toString());
		}
	}
}
