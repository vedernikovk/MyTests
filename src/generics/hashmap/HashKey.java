package generics.hashmap;

public interface HashKey {
	int getHash();
	boolean isEqual(HashKey key);
}
