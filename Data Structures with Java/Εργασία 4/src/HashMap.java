public interface HashMap<K, V> {
    void add(HashMapEntry<K, V> entry);
	void remove(K key);
	public V get(K key);
	boolean contains(K key);
    int size();
}
