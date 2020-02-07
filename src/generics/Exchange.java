package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exchange {

	public static <T> void exchange(List<T> array, int i, int j) {
		T temp = array.get(i);
		array.set(i, array.get(j));
		array.set(j, temp);
	}

	public static <T> void finePrint(Iterable<T> array) {
		for (T item : array) {
			System.out.print(item + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		List<String> strings = new ArrayList<String>(Arrays.asList(new String[] { "A", "B", "C", "D", "E"}));
		exchange(strings, 2, 3);
		finePrint(strings);

		List<Integer> integers = new ArrayList<Integer>(Arrays.asList(new Integer[] { 1, 2, 3, 4, 5 }));
		exchange(integers, 0, 4);
		finePrint(integers);
	}
}
