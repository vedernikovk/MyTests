package concurrent.old;

import java.util.Stack;

public class PCDemoOld {

	public static void main(String[] args) {
		Buffer<Integer> buffer = new Buffer<>();
		
		new Thread() {
			public void run() {
				for (int i = 0; i <= 1000; i++) {
					buffer.put(i);
				}
			};
		}.start();
		
		new Thread() {
			public void run() {
				for (int i = 0; i <= 1000; i++) {
					buffer.get();
				}
			};
		}.start();
		
	}
}

class Buffer<T> {
	
	public static int MAX = 5;
	
	private Stack<T> storage = new Stack<T>();
	
	public void put(T element) {
		synchronized (storage) {
			while (storage.size() == MAX) {
				try {
					storage.wait();
				} catch (InterruptedException e) {
					e.printStackTrace(System.err);
				}
			}
			
			storage.push(element);
			storage.notifyAll();
			
			System.out.println("Storage: " + storage);
		}
	}
	
	public T get() {
		synchronized (storage) {
			while (storage.isEmpty()) {
				try {
					storage.wait();
				} catch (InterruptedException e) {
					e.printStackTrace(System.err);
					return null;
				}
			}
			
			T result = storage.pop();
			storage.notifyAll();
			
			System.out.println("Storage: " + storage);
			
			return result;
		}
	}
}