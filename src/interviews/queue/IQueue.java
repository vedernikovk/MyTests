package interviews.queue;

import interviews.Iterator;
import interviews.list.IList;

public interface IQueue<T> {
	void add(T element);
	T remove();
	int size();
	void clear();
	Iterator<T> iterator();
	void importAll(IList<? extends T> list);
	void exportAll(IList<? super T> list);
}
