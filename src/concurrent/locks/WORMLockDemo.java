package concurrent.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WORMLockDemo {

	public static void main(String[] args) {
		WORM<Integer> memory = new WORM<>();
		
		Runnable reader = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <= 1000; i++) {
					memory.read();			
				}
			}		
		};
		
		Runnable writer = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <= 1000; i++) {
					memory.write(i);			
				}
			}		
		};
		
	    ExecutorService[] readers = new ExecutorService[3];
	    for (int i = 0; i < readers.length; i++) {
	    	readers[i] = Executors.newSingleThreadExecutor();
		}

	    ExecutorService[] writers = new ExecutorService[3];
	    for (int i = 0; i < writers.length; i++) {
	    	writers[i] = Executors.newSingleThreadExecutor();
		}
	    
	    for (int i = 0; i < readers.length; i++) {
	       readers[i].execute(reader);
	       readers[i].shutdown();
	    }	    
	    for (int i = 0; i < writers.length; i++) {
	    	writers[i].execute(writer);
	    	writers[i].shutdown();
	    }  
	}
}

class WORM<V> {
	
	private Lock guard = new ReentrantLock();
	private Condition signal = guard.newCondition();
	
	private AtomicReference<MODE> mode = new AtomicReference<>();	
	private AtomicInteger readers = new AtomicInteger(0);
	
	private AtomicReference<V> value = new AtomicReference<>();
	
	public WORM() {
		mode.set(MODE.READ);
	}
	
	public V read() {	
		guard.lock();		
		try {
			while (mode.get() == MODE.WRITE) {
				try {
					signal.await();
				} catch (InterruptedException e) {
					e.printStackTrace(System.err);
				}				
			}
		} finally {
			guard.unlock();
		}
			
		V result = value.get();
		readers.incrementAndGet();		
		System.out.println("Readers: " + readers +"; Got value: " + result);		
		readers.decrementAndGet();
			
		guard.lock();		
		try {
			signal.signal();
		} finally {
			guard.unlock();
		}
			
		return result;			
	}
	
	public void write(V newValue) {		
		guard.lock();		
		try {
			mode.set(MODE.WRITE);

			while (readers.get() > 0) {
				try {
					signal.await();
				} catch (InterruptedException e) {
					e.printStackTrace(System.err);
				}				
			}
			
			value.set(newValue);
			System.out.println("Set new value: " + newValue);
			
			mode.set(MODE.READ);
			signal.signal();
			
		} finally {
			guard.unlock();
		}
	}
}

enum MODE {
	READ, WRITE
}
