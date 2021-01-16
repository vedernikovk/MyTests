package interviews.hash;

public class HashMap<K extends Key, V> implements IMap<K, V> {
	
	private int SIZE = 1000; 

	public class HashMapEntry<K, V> {
		
		private K key;
		private V value;
		private HashMapEntry<K, V> next;
		
		public HashMapEntry(K key, V value) {
			this.value = value;
			this.key = key;
		}
	}
	
	private HashMapEntry<K, V>[] storage = new HashMapEntry[SIZE];
	
	@Override
	public void put(K key, V val) {
		int index = key.hashCode() % SIZE;
		HashMapEntry<K, V> entry = storage[index];
		
		if (entry == null) {
			storage[index] = new HashMapEntry<K, V>(key, val);
		} else {
			// Collision
			while (entry.next != null) {
				entry = entry.next;
			}
			
			entry.next = new HashMapEntry<K, V>(key, val);
		}
	}

	@Override
	public V get(K key) {		
		int index = key.hashCode() % SIZE;
		HashMapEntry<K, V> entry = storage[index];
		
		if (entry == null) {
			return null;
			
		} else {
			do {
				if (entry.key.equals(key)) {
					return entry.value;
				}				
				entry = entry.next;
				
			} while (entry != null);			
		}
		
		return null;
	}


	public static void main(String[] args) {
		IMap<StringKey, Integer> map = new HashMap<StringKey, Integer>();
		
		StringKey A = new StringKey("A");
		StringKey B = new StringKey("B");
		StringKey C = new StringKey("C");
		
		map.put(A, 1);
		map.put(B, 2);
		map.put(C, 3);
		
		System.out.println(map.get(A));
		System.out.println(map.get(B));
		System.out.println(map.get(C));
	}
}

class StringKey implements Key {
	
	private String val;

	public StringKey(String val) {
		this.val = val;
	}

	@Override
	public boolean equals(Key key) {	
		if (key instanceof StringKey) {
			return val.equals(((StringKey) key).val);
		} else {
			return false;
		}
	}
}