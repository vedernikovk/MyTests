package interviews.list;

import java.util.NoSuchElementException;

import interviews.Iterator;

public class LinkedList<T> implements IList<T> {

	private static class ListItem<T> {
		private T value;
		private ListItem<T> nextItem = null;
		
		public ListItem(T value) {
			this.value = value;			
		}
	}
	
	private ListItem<T> head = null;
	private int size = 0;
	
	private ListItem<T> getLastItem() {
		ListItem<T> lastItem = null;
		
		if (head != null) {
			lastItem = head;
			while (lastItem.nextItem != null) {
				lastItem = lastItem.nextItem;
			}
		}
		
		return lastItem;
	}
	
	@Override
	public void add(T element) {
		ListItem<T> lastItem = getLastItem();
		if (lastItem == null) {
			head = new ListItem<T>(element);
		} else {
			lastItem.nextItem = new ListItem<T>(element);
		}
		size++;
	}

	@Override
	public void delete(T element) {
		
		if (head != null) {
			
			ListItem<T> previousItem = null;
			ListItem<T> currentItem = head;
			
			do {				
				if (currentItem.value.equals(element)) {
					
					if (previousItem != null) {
						previousItem.nextItem = currentItem.nextItem;
						size--;
					} else {
						head = null;
						size = 0;
					}
					
					return;
				}
				
				previousItem = currentItem;
				currentItem = currentItem.nextItem;
				
			} while (currentItem != null);	
		}		
	}

	@Override
	public void setAt(T element, int i) {	
		if (i < 0 || i > size - 1) {
			throw new IndexOutOfBoundsException(i);
		}
		
		if (head == null) {
			throw new IndexOutOfBoundsException(i);
			
		} else {			
			int currentIndex = 0;
			ListItem<T> previousItem = null;
			ListItem<T> currentItem = head;
			
			do {
				if (currentIndex == i) {
					ListItem<T> newItem = new ListItem<T>(element);
					newItem.nextItem = currentItem.nextItem;
					
					if (previousItem == null) {
						head = newItem;
					} else {
						previousItem.nextItem = newItem;						
					}
					
					return;
				}
				
				previousItem = currentItem;
				currentItem = currentItem.nextItem; 
				currentIndex++;
				
			} while (currentItem != null);			
		}		
	}

	@Override
	public T getAt(int i) {
		
		if (head == null) {
			throw new IndexOutOfBoundsException(i);
			
		} else {
			int currentIndex = 0;		
			ListItem<T> currentItem = head;
			
			do {
				if (currentIndex == i) {
					return currentItem.value;
				}
				
				currentItem = currentItem.nextItem; 
				currentIndex++;
				
			} while (currentItem != null);			
		}
		
		throw new IndexOutOfBoundsException(i);
	}

	@Override
	public void importAll(IList<? extends T> list) {
		Iterator<? extends T> iterator = list.iterator();
		while (iterator.hasNext()) {
			add(iterator.next());
		}
	}

	@Override
	public void exportAll(IList<? super T> list) {
		Iterator<? extends T> iterator = iterator();
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}	
	}

	@Override
	public void deleteAt(int i) {
		if (i < 0 || i > size - 1) {
			throw new IndexOutOfBoundsException(i);
		}
		
		if (head == null) {
			throw new IndexOutOfBoundsException(i);
			
		} else {			
			int currentIndex = 0;
			ListItem<T> previousItem = null;
			ListItem<T> currentItem = head;
			
			do {
				if (currentIndex == i) {
					
					if (previousItem == null) {
						head = null;
						size = 0;
					} else {
						previousItem.nextItem = currentItem.nextItem;
						size--;
					}
					
					return;
				}
				
				previousItem = currentItem;
				currentItem = currentItem.nextItem; 
				currentIndex++;
				
			} while (currentItem != null);			
		}		
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public T getFirst() {
		return head == null ? null : head.value;
	}

	@Override
	public T getLast() {
		ListItem<T> lastItem = getLastItem();
		return lastItem == null ? null : lastItem.value;
	}

	@Override
	public void clear() {
		head = null;
		size = 0;			
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("LinkedList size = " + size);
		
		Iterator<T> iterator = iterator();
		while (iterator.hasNext()) {
			sb.append("\n" + iterator.next().toString());
		}
		
		return sb.toString() + "\n";
	}

	private static class LinkedListIterator<T> implements Iterator<T> {

		private ListItem<T> currentItem = null;
		
		public LinkedListIterator(ListItem<T> currentItem) {
			this.currentItem = currentItem;
		}
		
		@Override
		public boolean hasNext() {
			return currentItem != null;
		}

		@Override
		public T next() {
			if (currentItem == null) {
				throw new NoSuchElementException();
			}
			
			T result = currentItem.value;
			currentItem = currentItem.nextItem;
			
			return result;
		}
		
	}
	
	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator<T>(head);
	}
	
	public static void main(String[] args) {
		IList<String> list = new LinkedList<String>();
		
		list.add("First");
		System.out.println(list);
		
		list.add("Second");
		System.out.println(list);		

		list.setAt("Updated first", 0);
		System.out.println(list);
		
		list.setAt("Updated second", 1);
		System.out.println(list);
		
		try {
			list.setAt("Updated third", 2);
			System.out.println(list);			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		try {
			list.deleteAt(2);
			System.out.println(list);			
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		
		list.delete("Updated second");
		System.out.println(list);

		list.delete("Updated first");
		System.out.println(list);	
		
		IList<String> list2 = new LinkedList<String>();
		list2.add("One");
		list2.add("Two");
		list2.add("Three");
		
		list.importAll(list2);
		System.out.println(list);
		
		list2.clear();
		System.out.println(list2);
		
		list.exportAll(list2);
		System.out.println(list2);
	}
}
