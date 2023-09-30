import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringQueueImpl<E> implements StringQueue<E>{
    private SinglyLinkedList<E> list = new SinglyLinkedList<E>(); // Δημιουργία άδειας λίστας
    public StringQueueImpl(){}
    
    // The element at the front of the queue is the first element of the LinkedList ( or the head ) so that we can remove 
    // the first element of the queue in constant time [ O(1) ], while the (last) element at the back of the list (or the tail) is the last element of the LinkedList
    // so that we can add an element from the back of the queue again in constant time [ O(1) ], because a queue works according to the FIFO principle
    
    @Override
	public int size() {
	    return list.size(); // Returns the size of the queue
	}
	
    @Override
    public boolean isEmpty() { // Return true if the queue is empty
        return list.isEmpty();
    }
    
    @Override
	public void put(E e) {
	    list.addLast(e); // Last => tail node and newest element of queue, hence "back" of the queue
	}
	
	@Override
	public E get() throws NoSuchElementException {
		return list.removeFirst(); // First => head node and oldest element of queue, hence "front" of the queue
	}
	
	@Override
	public E peek() throws NoSuchElementException {
		return list.getFirst(); // First => head node and oldest element of queue, hence "front" of the queue
	}

    @Override
    public void printQueue(PrintStream stream) {
        try{
            if (isEmpty()){ // if queue is empty do nothing and exit the method
                return;
            }
            E temp = peek(); // Extract the element at the front of the queue
            
            get(); // remove the first element
                
            stream.print(temp + " "); // Print the <<current>> first element of the queue
            
            printQueue(stream); // Recursively call the method
                                // and print the remaining queue
               
            put(temp); // Put the element back
                
        } catch (Exception e) {
            stream.close();
        }
    }
}

