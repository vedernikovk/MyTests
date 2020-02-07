package concurrent.semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class WORMDemoSemphore {
	
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
	
	private Semaphore readerGate = new Semaphore(1);
	private Semaphore writerGate = new Semaphore(1);

	private AtomicReference<MODE> mode = new AtomicReference<>();
	
	private AtomicInteger activeReaders = new AtomicInteger(0);	
	private AtomicInteger waitingReaders = new AtomicInteger(0);
	
	private AtomicReference<V> value = new AtomicReference<>();

	public WORM() {
		mode.set(MODE.READ);
	}
	
	public V read() {
		if (mode.get() == MODE.WRITE) {
			try {
				waitingReaders.incrementAndGet();
				writerGate.acquire();
				waitingReaders.decrementAndGet();
			} catch (InterruptedException e) {
				e.printStackTrace(System.err);
			}			
		}
		
		if (activeReaders.get() == 0) {
			try {
				readerGate.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace(System.err);
			}
		}
		activeReaders.incrementAndGet();
		
		String name = Thread.currentThread().getName();
		System.out.println("Reader " + name + " (" + activeReaders +"): Got value: " + value.get());
		
		activeReaders.decrementAndGet();
		if (activeReaders.get() == 0) {
			readerGate.release();
		}
		
		return value.get();
	}
	
	synchronized public void write(V newValue) {
		try {
			writerGate.acquire();
			mode.set(MODE.WRITE);
			
			readerGate.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace(System.err);
		}				
		
		value.set(newValue);
		String name = Thread.currentThread().getName();
		System.out.println("Writer " + name + ": Set new value: " + newValue);
		
		mode.set(MODE.READ);
		readerGate.release();
		
		writerGate.release(waitingReaders.get() + 1);
	}
}

enum MODE {
	READ, WRITE
}
