import java.util.NoSuchElementException; 

public class DoubleLinkedListImpl<K, V> implements DoubleLinkedList<K, V> {
    DoublyNode<K, V> head;
    DoublyNode<K, V> tail;
    int size;

    DoubleLinkedListImpl(){
        head = null;
        tail = null;
        size = 0;
    }
    
    @Override
    public void insertAtFront(HashMapEntry<K, V> item) {
        DoublyNode<K, V> node = new DoublyNode<>(item);
        if(isEmpty()){
            head = tail = node;
            head.prev = null;
            tail.next = null;
        }else{
            head.prev = node;
            node.next = head;
            node.prev = null;
            head = node;
        }
        size++;
    }

    @Override
    public void insertAtBack(HashMapEntry<K, V> item) {
        DoublyNode<K, V> node = new DoublyNode<>(item);
        if (tail == null) { // if the list is empty, set the new node as both head and tail
            head = tail = node;
        } else { // otherwise, insert the new node after the tail and update the tail
            tail.next = node;
            node.prev = tail;
            tail = node;
            if (head == null) { // if the list had only one node before, update the head to point to it
                head = node.prev;
            }
        }
        this.size++;
    }

    @Override
    public HashMapEntry<K, V> peek() throws NoSuchElementException {                 
        if(!isEmpty()){                                             
            System.out.println("The first item is: " + head.data);
            return head.data;
        }else{                                                     
            throw new NoSuchElementException(); 
        }
    }

    @Override
    public HashMapEntry<K, V> last() throws NoSuchElementException {                 
        if(!isEmpty()){                                             
            System.out.println("The last item is: " + tail.data);
            return tail.data;
        }else{                                                     
            throw new NoSuchElementException(); 
        }
    }

    @Override
    public HashMapEntry<K, V> removeFromFront() throws NoSuchElementException {
        if (isEmpty()){     
            System.out.println("List is empty!!!");                                        
            return null;
        }else {
            size--;
            if(head != tail) {  
                head = head.next;   
                head.prev = null;  
            }  
            else {  
                head = tail = null;  
            }
            return head.data;
        }

    }

    @Override
    public DoublyNode<K, V> removeNode(DoublyNode<K , V> node) {
        if (node == null) {
            return null;
        }
        DoublyNode<K, V> prev = node.prev;
        DoublyNode<K, V> next = node.next;
        if (prev != null) {
            prev.next = next;
        } else {
            head = next;
        }
        if (next != null) {
            next.prev = prev;
        } else {
            tail = prev;
        }
        node.prev = null;
        node.next = null;
        size--;
        return node;
    }
    
    @Override
    public DoublyNode<K, V> getNode(K key) {
        if (this.isEmpty()) {
            return null;
        }
        DoublyNode<K, V> current = head;
        while (current!= null) {
            if (current.data.getKey().equals(key)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
    
    @Override
    public void update(DoublyNode<K, V> node) {
        if(node == null){ throw new NullPointerException();}
        removeNode(node);
        insertAtBack(node.data);
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return size;
    }

}
