package concurrent.executors;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class MultiThreadExecutor {

	public static void main(String[] args) {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
		BlockingQueue<Runnable> queue = executor.getQueue();
		System.out.println("Elements in queue: " + queue.size());
		
		executor.execute(new TheTask2("WorkOrder"));
		queue = executor.getQueue();
		System.out.println("Elements in queue: " + queue.size());
		
		executor.execute(new TheTask2("Equipment"));
		queue = executor.getQueue();
		System.out.println("Elements in queue: " + queue.size());
		
		executor.execute(new TheTask2("WorkRequest"));
		queue = executor.getQueue();		
		System.out.println("Elements in queue: " + queue.size());
		
		executor.execute(new TheTask2("OperatingUnits"));
		queue = executor.getQueue();
		System.out.println("Elements in queue: " + queue.size());
		
		Runnable task = new TheTask2("OperatingQQQ");
		Future<?> f = executor.submit(task);		
		f.cancel(false) ;
		
		executor.shutdown();
	}
}

class TheTask2 implements Runnable {

	private String name;
	
	public TheTask2(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for (int i = 0; i <= 10; i++) {
			try {
				System.out.println("Thread " + Thread.currentThread().getName() + " is running for " + name + ": " + i);
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace(System.err);
			}
		}		
	}	
}