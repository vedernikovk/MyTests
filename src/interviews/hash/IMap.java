package interviews.hash;

public interface IMap<K extends Key, V> {
	void put(K key, V val);
	V get(K key);
}
