package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxWildcard {

	public static <T extends Comparable<? super T>> T max(List<T> list, int begin, int end) {
		if (list == null || list.size() == 0) {
			return null;
		}

		T max = list.get(begin);
		for (int i = begin; i <= end; i++) {
			T t = list.get(i);
			if (t.compareTo(max) > 0) {
				max = t;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6);
		System.out.println(max(ints, 2, 4));

		List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3, 4.4, 5.5, 6.6);
		System.out.println(max(doubles, 2, 4));

		List<MyInteger> myints = new ArrayList<MyInteger>();
		System.out.println(max(myints, 2, 4));

		// No Error - MyNewInteger implements Comparable<MyInteger>
		List<MyNewInteger> mynewints = new ArrayList<MyNewInteger>();
		System.out.println(max(mynewints, 2, 4));

		// No Error - MyLatestInteger implements Comparable<MyInteger>
		List<MyLatestInteger> mylatestints = new ArrayList<MyLatestInteger>();
		System.out.println(max(mylatestints, 2, 4));

		// Error - Object does not implement Comparable
		//List <Object> objs = new ArrayList<Object>();
		//System.out.println(max(objs, 2, 4));
	}
}
