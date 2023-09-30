public class CacheImpl<K, V> implements  Cache<K, V>{
    private final int capacity;
    private int lookups = 0;
    private int hits = 0;
    DoubleLinkedListImpl<K, V> list;
    HashMapImpl<K, V> map;

    CacheImpl(int capacity){
        this.capacity = capacity;
        this.list = new DoubleLinkedListImpl<>();
        this.map = new HashMapImpl<>(capacity);
    }

    @Override
    public V lookUp(K key) {
        lookups++;
        if (map.contains(key)) {
            DoublyNode<K, V> temp = list.getNode(key);
            list.removeNode(temp);
            list.insertAtBack(temp.data);
            //System.out.println("Hit !"); // for debugging purposes
            hits++;
            return map.get(key); // returns value V
        }
        return null;
    }

    @Override
    public void store(K key, V value) {
        if (map.size() == capacity && list.size() == capacity) { // if map is full
            // remove oldest (head) entry from list
            DoublyNode<K, V> lastNode = list.removeNode(list.head);
            // remove same entry from map
            map.remove(lastNode.data.getKey());
        }
        HashMapEntry<K, V> entry = new HashMapEntry<>(key, value);
        list.insertAtBack(entry); // back=tail -> most recent
        map.add(entry); // add entry to map
    }

    @Override
    public double getHitRatio() {
        return hits / (double) lookups;
    }

    @Override
    public long getHits() {
        return hits;
    }

    @Override
    public long getMisses() {
        return lookups - hits;
    }

    @Override
    public long getNumberOfLookUps() {
        return lookups;
    }
    
}
