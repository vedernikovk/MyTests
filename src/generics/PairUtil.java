package generics;

import java.util.Arrays;
import java.util.List;

public class PairUtil {

	public static <T> Pair<T> swap(Pair<T> pair) {
		return pair.swap();
	}

	public static <T extends Comparable<T>> T max(List<T> list) {
		if (list == null || list.size() == 0) {
			return null;
		}

		T max = list.get(0);
		for (T t : list) {
			if (t.compareTo(max) > 0) {
				max = t;
			}
		}
		return max;
	}

	public static <T extends Comparable<T>> T min(List<T> list) {
		if (list == null || list.size() == 0) {
			return null;
		}

		T min = list.get(0);
		for (T t : list) {
			if (t.compareTo(min) < 0) {
				min = t;
			}
		}
		return min;
	}

	public static <T extends Comparable<T>> Pair<T> findMinMax(List<T> list) {
		T min = min(list);
		T max = max(list);

		return new Pair<T>(min, max);
	}

	public static void main(String[] args) {
		List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6);
		System.out.println(findMinMax(ints));

		List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3, 4.4, 5.5, 6.6);
		System.out.println(findMinMax(doubles));
	}
}

class Pair<T> {

	private T first;
	private T second;

	public Pair(T firstElement, T secondElement) {
		first = firstElement;
		second = secondElement;
	}

	public T getFirst() {
		return first;
	}

	public T getSecond() {
		return second;
	}

	public String toString() {
		return "Pair [first=" + first + ", second=" + second + "]";
	}

	public Pair<T> swap() {
		return new Pair<T>(second, first);
	}
}