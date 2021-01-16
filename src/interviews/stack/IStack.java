package interviews.stack;

import interviews.Iterator;
import interviews.list.IList;

public interface IStack<T> {
	void push(T newItem);
	T pop();
	Iterator<T> iterator();
	T top();
	void clear();	
	int size();
	void importAll(IList<? extends T> list); 
	void exporAll(IList<? super T> list);
}
