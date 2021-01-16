package interviews.queue;

import java.util.NoSuchElementException;

import interviews.Iterator;
import interviews.list.IList;
import interviews.list.LinkedList;

public class Queue<T> implements IQueue<T> {

	private QueueItem<T> head = null;
	private QueueItem<T> tail = null;
	private int size = 0; 
	
	private static class QueueItem<T> {
		private T value;
		private QueueItem<T> next;
		
		public QueueItem(T value) {
			this.value = value;
		}
	}
	
	@Override
	public void add(T element) {		
		QueueItem<T> newItem = new QueueItem<T>(element); 
		
		if (head == null) {
			head = newItem;
			tail = newItem;
		} else {
			head.next = newItem;
			head = newItem;
		}
		size++;
	}

	@Override
	public T remove() {
		if (size == 0) {
			return null;
		}
		
		T result = null;				
		if (size == 1) {
			result = head.value;
			
			head = null;
			tail = null;			
			size = 0;
			
			return result;
		} else {
			result = tail.value;
			tail = tail.next;
			size--;
		}
		
		return result;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	private static class QueueIterator<T> implements Iterator<T> {

		private QueueItem<T> current; 
		
		public QueueIterator(QueueItem<T> current) {
			this.current = current;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (current == null) {
				throw new NoSuchElementException();
			}
			
			T result = current.value;
			current = current.next;
					
			return result;
		}		
	}
	
	@Override
	public Iterator<T> iterator() {
		return new QueueIterator<T>(tail);
	}
	
	@Override
	public void importAll(IList<? extends T> list) {
		Iterator<? extends T> i = list.iterator();
		
		while (i.hasNext()) {
			add(i.next());
		}
	}

	@Override
	public void exportAll(IList<? super T> list) {
		Iterator<? extends T> i = iterator();
		
		while (i.hasNext()) {
			list.add(i.next());
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("Queue size = " + size);
		
		Iterator<? extends T> i = iterator();		
		while (i.hasNext()) {
			sb.append("\n" + i.next());
		}
		
		return sb.toString() + "\n";
	}

	public static void main(String[] args) {
		IList<String> list = new LinkedList<String>();
		
		Queue<String> queue = new Queue<String>();
		
		queue.add("one");
		queue.add("two");
		queue.add("three");
		System.out.println(queue);
		queue.exportAll(list);		
		
		System.out.println("Removed: " + queue.remove());		
		System.out.println(queue);

		System.out.println("Removed: " + queue.remove());		
		System.out.println(queue);

		System.out.println("Removed: " + queue.remove());		
		System.out.println(queue);
		
		System.out.println(list);
		
		queue.importAll(list);
		System.out.println(queue);
	}
}
