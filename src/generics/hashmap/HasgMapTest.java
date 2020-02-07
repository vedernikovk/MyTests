package generics.hashmap;

import java.util.ArrayList;
import java.util.List;

public class HasgMapTest {

	private static class StringKey implements HashKey {
		
		private String val;

		public StringKey(String val) {
			this.val = val;
		}

		@Override
		public int getHash() {
			return val.hashCode();
		}

		@Override
		public boolean isEqual(HashKey key) {
			StringKey sk = (StringKey) key;
			return val.equals(sk.val);
		}
		
		@Override
		public String toString() {
			return val;
		}
	}

	private static class IntegerKey implements HashKey {
		
		private Integer val;

		public IntegerKey(Integer val) {
			this.val = val;
		}

		@Override
		public int getHash() {
			return val.hashCode();
		}

		@Override
		public boolean isEqual(HashKey key) {
			IntegerKey sk = (IntegerKey) key;
			return val.intValue() == sk.val.intValue();
		}		
		
		@Override
		public String toString() {
			return val.toString();
		}
	}
	
	public static void main(String[] args) {	
		HashMap<StringKey, Integer> theMap1 = new HashMap<>();
		
		theMap1.put(new StringKey("a"), new Integer(1));
		theMap1.put(new StringKey("b"), new Integer(2));
		theMap1.put(new StringKey("c"), new Integer(3));
		
		List<Number> numbers = new ArrayList<>(); 
		theMap1.exportValues(numbers);		
		System.out.println(numbers);
		
		
		HashMap<IntegerKey, String> theMap = new HashMap<>();
		
		theMap.put(new IntegerKey(1), "a");
		theMap.put(new IntegerKey(2), "b");
		theMap.put(new IntegerKey(3), "c");
		
		List<IntegerKey> keys = new ArrayList<>(); 
		theMap.exportKeys(keys);
		
		System.out.println(keys);
	}
}
