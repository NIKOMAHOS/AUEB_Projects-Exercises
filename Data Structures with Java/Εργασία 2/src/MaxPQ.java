
public class MaxPQ<Key extends Comparable<Key>> {
    private HeapPQ<Key> heapPQ;

    public MaxPQ(int capacity) {
        heapPQ = new HeapPQ<>(capacity);
    }

    public MaxPQ() {
        this(1);
    }

    public void insert(Key key) {
        heapPQ.insert(key);
    }
    
    public Key peek() {
        return heapPQ.peek();
    }
    
    public Key getMax() {
        return heapPQ.getMax();
    }

    public Key remove() {
        return heapPQ.remove();
    }

    public boolean isEmpty() {
        return heapPQ.isEmpty();
    }

    public int size() {
        return heapPQ.size();
    }
}
