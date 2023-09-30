import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringQueueWithOnePointer<E> implements StringQueue<E>{
    private CircularlyLinkedList<E> list = new CircularlyLinkedList<E>(); // Άδεια κυκλική λίστα
    public StringQueueWithOnePointer(){}
    
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
	    list.addLast(e); // Last => tail node and newest element of queue
	}
	
	@Override
	public E get() throws NoSuchElementException {
		return list.removeFirst(); // First => "head" node and oldest element of queue
	}
	
	@Override
	public E peek() throws NoSuchElementException {
		return list.getFirst(); // First => "head" node and oldest element of queue
	}
	
    @Override
    public void printQueue(PrintStream stream) {
	    try {
			if (isEmpty()){ // if queue is empty do nothing and exit the method
				return;
			}
			
			E temp = peek(); // Extract the element at the front of the queue
			
			get(); // remove the first element
				
			stream.print(temp + " "); // Print the <<current>> first element of the queue
			
			printQueue(stream); // Recursively call the method (with a new first element
								// each time) and print the remaining queue
			   
			put(temp);         // Put the element back
				
		} catch (Exception e) {
			stream.close();
		}
	}  
}
