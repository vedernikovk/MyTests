package concurrent.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(new TheTask("WorkOrder"));
		executor.execute(new TheTask("Equipment"));
		executor.shutdown();
	}
}

class TheTask implements Runnable {

	private String name;
	
	public TheTask(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for (int i = 0; i <= 10; i++) {
			try {
				System.out.println("Thread " + Thread.currentThread().getName() + " is running for " + name + ": " + i);
				
				// If this single thread terminates due to a failure during execution prior to 
				// shutdown, a new one will take its place if needed to execute subsequent tasks
				if (i == 5) {
					throw new IllegalStateException("Shit happened!");
				}
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace(System.err);
			}
		}		
	}	
}