
@SuppressWarnings("unchecked")
public class HeapPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int size;

    public HeapPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
        size = 0;
    }

    public HeapPQ() {
        this(1);
    }
    
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(Key key) {
        if (size == pq.length - 1) {
            resize(2 * pq.length);
        }

        pq[++size] = key;
        swim(size);
    }

    public Key peek() {
        if (isEmpty()) {
            return null;
        }
        return pq[1];
    }    
    
    public Key getMax() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        Key max = pq[1];
        swap(1, size--);
        sink(1);
        pq[size + 1] = null;

        if ((size > 0) && (size == (pq.length - 1) / 4)) {
            resize(pq.length / 2);
        }

        return max;
    }

    public Key remove() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        Key key = pq[1];
        swap(1, size--);
        sink(1);
        pq[size + 1] = null;

        if ((size > 0) && (size == (pq.length - 1) / 4)) {
            resize(pq.length / 2);
        }

        return key;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;

            if (j < size && less(j, j + 1)) {
                j++;
            }

            if (!less(k, j)) {
                break;
            }

            swap(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void swap(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private void resize(int newSize) {
        Key[] temp = (Key[]) new Comparable[newSize];

        for (int i = 1; i <= size; i++) {
            temp[i] = pq[i];
        }

        pq = temp;
    }
}


