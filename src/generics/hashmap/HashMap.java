package generics.hashmap;

import java.util.ArrayList;
import java.util.List;

public class HashMap<Key extends HashKey, Value> {
	
	private ArrayList<StorageItem<Value, Key>> storage = new ArrayList<>(1000);
	

	public HashMap() {
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
		
		public Value getValue() {
			return value;
		}
		
		public Key getKey() {
			return key;
		}
		
		public StorageItem<Value, Key> getNext() {
			return next;
		}
		
		public void setNext(StorageItem<Value, Key> next) {
			this.next = next;
		}
	}

	
	public void exportValues(List<? super Value> list) {		
		for (StorageItem<Value, Key> storageItem : storage) {
			if (storageItem != null) {
				do {
					list.add(storageItem.getValue());
					
				} while ((storageItem = storageItem.getNext()) != null);
			}
		}
	}
	
	public void exportKeys(List<? super Key> list) {
		for (StorageItem<Value, Key> storageItem : storage) {
			if (storageItem != null) {
				do {
					list.add(storageItem.getKey());
					
				} while ((storageItem = storageItem.getNext()) != null);
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
			while (storageItem.getNext() != null) {
				storageItem = storageItem.getNext();
			}
			
			storageItem.setNext(new StorageItem<Value, Key>(key, value));
		}
	}

	public Value get(Key key) {
		int index = key.getHash();
		
		StorageItem<Value, Key> item = storage.get(index);
		if (item == null) {
			return null;
			
		} else {
			do {
				if (item.getKey().isEqual(key)) {
					return item.getValue();
				}
				
			} while ((item = item.getNext()) != null);
			
			return null;
		}		
	}
}
