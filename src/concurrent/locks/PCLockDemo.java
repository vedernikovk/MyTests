package concurrent.locks;

import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PCLockDemo {

	public static void main(String[] args) {
		BBuffer<Integer> buffer = new BBuffer<>();
		
		Runnable producer = new Runnable() {
			public void run() {
				String name = Thread.currentThread().getName();
				for (int i = 0; i <= 1000; i++) {
					System.out.println("Producer " + name + ": about to put " + i);					
					buffer.put(i);
				}
				System.out.println("Producer " + name + ": is about to exit");	
			};
		};
		
		Runnable consumer = new Runnable() {
			public void run() {
				String name = Thread.currentThread().getName();
				
				Integer result = null;
				for (int i = 0; i <= 1000; i++) {
					result = buffer.get();
					System.out.println("Consumer " + name + ": got " + result);
				}
				
				System.out.println("Consumer " + name + ": is about to exit");	
			};
		};
		
	    ExecutorService[] producers = new ExecutorService[3];
	    for (int i = 0; i < producers.length; i++) {
	    	producers[i] = Executors.newSingleThreadExecutor();
	    	producers[i].execute(producer);
	    	producers[i].shutdown();
	    }
	    
	    ExecutorService[] consumers = new ExecutorService[3];
	    for (int i = 0; i < consumers.length; i++) {
	    	consumers[i] = Executors.newSingleThreadExecutor();
	    	consumers[i].execute(consumer);
	    	consumers[i].shutdown();
	    }		
	}
}

class BBuffer<T> {
	
	public static int MAX = 5;

	private Lock guard = new ReentrantLock();

	private Condition fullSignal = guard.newCondition();
	private Condition emptySignal = guard.newCondition();
	
	private Stack<T> storage = new Stack<T>();
	
	public void put(T element) {		
		String name = Thread.currentThread().getName();
		System.out.println("Producer " + name + "; before: " + storage);

		guard.lock();
		try {
			while (storage.size() == MAX) {
				try {
					fullSignal.await();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
			
			storage.push(element);
			assert (storage.size() <= MAX);
			System.out.println("Producer " + name + "; after: " + storage);
			
			emptySignal.signal();
			
		} finally {
			guard.unlock();
		}
	}
	
	public T get() {
		String name = Thread.currentThread().getName();
		System.out.println("Consumer " + name + "; before: " + storage);
		
		T result = null;
		guard.lock();
		try {			
			while (storage.isEmpty()) {
				try {
					emptySignal.await();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
			
			result = storage.remove(storage.size()-1);
			fullSignal.signal();
			
			System.out.println("Consumer " + name + "; after: " + storage);
		
			return result;		
			
		} finally {
			guard.unlock();
		}				
	}
}