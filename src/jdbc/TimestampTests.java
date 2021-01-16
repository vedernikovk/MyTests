package jdbc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class TimestampTests {

	public static void main(String[] args) {
		IntGenerator gen = new IntGenerator(0, 999);
		
		for (int i=0; i<= 10000; i++) {			
		    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()) + String.format("%03d", gen.nextInteger()));
		}
	}
}

class IntGenerator {
	
	private final AtomicInteger integer;
    private final int min;
	private final int max;

	public IntGenerator(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		this.min = min;
		this.max = max;		
		integer = new AtomicInteger(min);
	}

	public int nextInteger() 	{
		int current = integer.getAndIncrement();
		
		if (current == max) {
			integer.set(min);
		}
		
		return current;
	}
}

