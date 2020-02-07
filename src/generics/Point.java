	package generics;

import java.util.ArrayList;
import java.util.List;


public class Point {

	public Point(int i, int j) {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// OK
		Point[] a = new Point[10];
		Object[] b;
		b = a;
		b[0] = new Point(10,20);

		// java.lang.ArrayStoreException
		Point[] a1 = new Point[10];
		Object[] b1;
		b1 = a1;
		b1[0] = "hi there"; // java.lang.ArrayStoreException

		// Compilation error
		//Point[] a2 = new Point[10];
		//Object[] b2;
		//b2 = a2;
		//a2[0] = "hi there";

		List<Object> list = new ArrayList<Object>();
		list.add(new Point(1 ,2));
	}
}
