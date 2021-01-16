package generics.hashmap;

import java.util.ArrayList;
import java.util.List;

public class HashTable<Key extends HashKey, Value> {
	
	private ArrayList<StorageItem<Value, Key>> storage = new ArrayList<>(1000);
	
	public HashTable() {
		for (int i = 0; i <= 1000; i++) storage.add(null);
	}

	private static class StorageItem<Value, Key extends HashKey> {

		private Value value;
		private Key key;
		private StorageItem<Value, Key> next;
		
		public StorageItem(Key key, Value value) {
			this.value = value;
			this.key = key;
		}
	}

	
	public void exportValues(List<? super Value> list) {		
		for (StorageItem<Value, Key> storageItem : storage) {
			if (storageItem != null) {
				do {
					list.add(storageItem.value);
					
				} while ((storageItem = storageItem.next) != null);
			}
		}
	}
	
	public void exportKeys(List<? super Key> list) {
		for (StorageItem<Value, Key> storageItem : storage) {
			if (storageItem != null) {
				do {
					list.add(storageItem.key);
					
				} while ((storageItem = storageItem.next) != null);
			}
		}		
	}
	
	public void put(Key key, Value value) {
		int index = key.getHash();
		
		StorageItem<Value, Key> storageItem = storage.get(index);
		if (storageItem == null) {
			storage.set(index, new StorageItem<Value, Key>(key, value));
			
		} else {
			// Collision
			while (storageItem.next != null) {
				storageItem = storageItem.next;
			}
			
			storageItem.next = new StorageItem<Value, Key>(key, value);
		}
	}

	public Value get(Key key) {
		int index = key.getHash();
		
		StorageItem<Value, Key> item = storage.get(index);
		if (item == null) {
			return null;
			
		} else {
			do {
				if (item.key.isEqual(key)) {
					return item.value;
				}
				
			} while ((item = item.next) != null);
			
			return null;
		}		
	}
}
