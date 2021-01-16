package interviews.list;

import interviews.Iterator;

public interface IList<T> {	
	void add(T element);
	void delete(T element);
	void setAt(T element, int i);
	T getAt(int i);
	void deleteAt(int i);
	int size();
	void importAll(IList <? extends T> list);
	void exportAll(IList <? super T> list);
	T getFirst();
	T getLast();
	void clear();
	Iterator<T> iterator();
}
