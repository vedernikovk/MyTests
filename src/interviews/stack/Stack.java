package interviews.stack;

import java.util.NoSuchElementException;

import interviews.Iterator;
import interviews.list.LinkedList;
import interviews.list.IList;

public class Stack<T> implements IStack<T> {
	
	private StackItem<T> top = null;
	private int size = 0; 
	
	private static class StackItem<T> {		
		private T value;
		private StackItem<T> next = null;
		
		public StackItem(T value) {
			this.value = value;
		}		
	}

	@Override
	public void push(T value) {
		StackItem<T> newItem = new StackItem<T>(value);
		
		if (top == null) {
			top = newItem;
		} else {
			newItem.next = top;
			top = newItem; 
		}
		
		size++;
	}

	@Override
	public T pop() {
		if (top == null) {
			return null;
		} else {
			T result = top.value;
			top = top.next;
			size--;
			
			return result;
		}	
	}

	private static class StackIterator<T> implements Iterator<T> {

		private StackItem<T> currentItem = null;
		
		public StackIterator(StackItem<T> current) {
			this.currentItem = current;
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
			currentItem = currentItem.next;
			
			return result;
		}		
	}
	
	@Override
	public Iterator<T> iterator() {
		return new StackIterator<T>(top);
	}

	@Override
	public T top() {
		return top == null ? null : top.value;
	}

	@Override
	public void clear() {
		top = null;
		size = 0;		
	}

	@Override
	public int size() {
		return size;
	}
	
	@Override
	public void importAll(IList<? extends T> list) {
		Iterator <? extends T> i  = list.iterator();
		while (i.hasNext()) {
			push(i.next());
		}
	}

	@Override
	public void exporAll(IList<? super T> list) {
		Iterator <T> i = iterator();
		while (i.hasNext()) {
			list.add(i.next());
		}		
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("MyStack size = " + size);
		
		Iterator<T> i = iterator();		
		while (i.hasNext()) {
			sb.append("\n" + i.next());
		}
		
		return sb.toString() + "\n";
	}
	
	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		stack.push("one");
		System.out.println(stack);
		
		stack.push("two");
		System.out.println(stack);	

		stack.push("three");
		System.out.println(stack);	

		stack.pop();
		System.out.println(stack.top());	
		System.out.println(stack);	

		stack.pop();
		System.out.println(stack.top());	
		System.out.println(stack);	
			
		stack.clear();
		System.out.println(stack);
		
		IList<String> list2 = new LinkedList<String>();
		list2.add("One");
		list2.add("Two");
		list2.add("Three");
		
		stack.importAll(list2);
		System.out.println(stack);
		
		IList<String> list1 = new LinkedList<String>();
		stack.exporAll(list1);
		System.out.println(list1);
	}
}
