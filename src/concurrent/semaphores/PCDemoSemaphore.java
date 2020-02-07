package concurrent.semaphores;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class PCDemoSemaphore {

	public static void main(String[] args) {
		Buffer<Integer> buffer = new Buffer<>();
		
		 Runnable producer = new Runnable() {
			public void run() {
				for (int i = 0; i <= 1000; i++) {
					buffer.put(i);
				}
			};
		};
		
		 Runnable consumer = new Runnable() {
			public void run() {
				for (int i = 0; i <= 1000; i++) {
					buffer.get();
				}
			};
		};
		
	    ExecutorService[] producers = new ExecutorService[3];
	    for (int i = 0; i < producers.length; i++) {
	    	producers[i] = Executors.newSingleThreadExecutor();
	    	producers[i].execute(producer);
	    }
	    
	    ExecutorService[] consumers = new ExecutorService[3];
	    for (int i = 0; i < consumers.length; i++) {
	    	consumers[i] = Executors.newSingleThreadExecutor();
	    	consumers[i].execute(consumer);
	    }		

	    for (int i = 0; i < producers.length; i++) {
	    	producers[i].shutdown();
	    }
	    
	    for (int i = 0; i < consumers.length; i++) {
	    	consumers[i].shutdown();
	    }			    
	}
}

class Buffer<T> {
	
	public static int MAX = 5;
	
	private Semaphore empty = new Semaphore(MAX, true);
	private Semaphore full = new Semaphore(MAX, true);
	
	private Stack<T> storage = new Stack<T>();
	
	public Buffer() {
		try {
			empty.acquire(MAX);
		} catch (InterruptedException e) {
			e.printStackTrace(System.err);
		}		
	}

	public void put(T element) {
		try {
			full.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace(System.err);
		}
			
		synchronized (storage) {
			storage.push(element);
			System.out.println("Producer '" + Thread.currentThread().getName() + "': " + storage);
			
			empty.release();			
		}
	}
	
	public T get() {
		try {
			empty.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace(System.err);
			return null;
		}
		
		T result = null;
		synchronized (storage) {
			try {
				result = storage.pop();				
			} catch (EmptyStackException e) {
				e.printStackTrace(System.err);
			}
			
			System.out.println("Consumer: '" + Thread.currentThread().getName() + "': " + storage);
			
			full.release();
			
			return result;
		}			
	}
}