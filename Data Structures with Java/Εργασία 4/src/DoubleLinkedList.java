public interface DoubleLinkedList<K, V> {
    public void insertAtFront(HashMapEntry<K, V> item);
    public void insertAtBack(HashMapEntry<K, V> item);
    public HashMapEntry<K, V> removeFromFront();
    public DoublyNode<K, V> removeNode(DoublyNode<K, V> node);
    public DoublyNode<K, V> getNode(K key);
    public HashMapEntry<K, V> peek();
    public HashMapEntry<K, V> last();
    public void update(DoublyNode<K, V> node);
    public int size();
    public boolean isEmpty();
}
