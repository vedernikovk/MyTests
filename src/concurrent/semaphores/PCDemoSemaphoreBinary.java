package concurrent.semaphores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class PCDemoSemaphoreBinary {

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

	private Semaphore producerGuard = new Semaphore(1);
	private Semaphore conumerGuard = new Semaphore(1);

	private Semaphore producerSignal = new Semaphore(1);
	private Semaphore conumerSignal = new Semaphore(1);

	
	private List<T> storage = Collections.synchronizedList(new ArrayList<T>(MAX));
	
	public void put(T element) {		
		String name = Thread.currentThread().getName();
		System.out.println("Producer " + name + "; before: " + storage);

		try {
			producerGuard.acquire();

			while (storage.size() == MAX) {
				producerSignal.acquire();
			}
			
			storage.add(element);
			assert (storage.size() <= MAX);
			conumerSignal.release();
				
			System.out.println("Producer " + name + "; after: " + storage);
			
			if (storage.size() == MAX) {
				producerSignal.acquire();
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace(System.err);
			
		} finally {
			producerGuard.release();
		}
	}
	
	public T get() {
		String name = Thread.currentThread().getName();
		System.out.println("Consumer " + name + "; before: " + storage);
		
		T result = null;
		try {
			conumerGuard.acquire();
			
			while (storage.isEmpty()) {
				conumerSignal.acquire();
			}
			
			result = storage.remove(storage.size()-1);
			producerSignal.release();
			
			System.out.println("Consumer " + name + "; after: " + storage);
			if (storage.isEmpty()) {
				conumerSignal.acquire();
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace(System.err);
			return null;
			
		} finally {
			conumerGuard.release();
		}
				
		return result;		
	}
}