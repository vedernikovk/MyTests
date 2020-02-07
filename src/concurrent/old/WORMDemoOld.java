package concurrent.old;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WORMDemoOld {
	
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
	    }	    
	    for (int i = 0; i < writers.length; i++) {
	    	writers[i].execute(writer);
	    }  
	    
	    for (int i = 0; i < readers.length; i++) {
	    	readers[i].shutdown();
		}	    
	    for (int i = 0; i < writers.length; i++) {
	    	writers[i].shutdown();
		}    
	}	
}

class WORM<V> {
	
	private MODE mode = MODE.READ;
	private int readers = 0;	
	private V value = null;
	
	public V read() {	
		synchronized(this) {
			while (mode == MODE.WRITE) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace(System.err);
				}				
			}						
			readers++;
		}
		
		System.out.println("Readers: " + readers +"; Got value: " + value);
		
		synchronized(this) {
			readers--;
			this.notifyAll();
		}
		
		return value;
	}
	
	public void write(V newValue) {		
		synchronized(this) {
			mode = MODE.WRITE;

			while (readers > 0) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace(System.err);
				}				
			}
			
			value = newValue;
			System.out.println("Set new value: " + newValue);
			
			mode = MODE.READ;
			this.notifyAll();
		}		
	}
}

enum MODE {
	READ, WRITE
}
