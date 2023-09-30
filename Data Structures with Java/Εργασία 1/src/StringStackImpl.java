import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringStackImpl<E> implements StringStack<E> {
    protected SinglyLinkedList<E> list = new SinglyLinkedList<>(); // Δημιουργία άδειας λίστας
    public StringStackImpl(){} // constructor
    
    // The element at the top of the stack is the first element of the LinkedList ( or the head ) so that we can
    // insert and delete elements in constant time, because a stack works according to the LIFO principle
            
    @Override
    public int size() { // Returns the size of the stack
        return list.size();
    }
    
    @Override
    public boolean isEmpty() { // Return true if the stack is empty
        return list.isEmpty();
    }
    
    @Override
    public void push(E e) {
        list.addFirst(e); // First => head node and newest element, hence top of the stack
    }
    
    @Override
    public E pop() throws NoSuchElementException {
        return list.removeFirst(); // First => head node and newest element of the stack, hence "top" of the stack
    }
    
    @Override
    public E peek() throws NoSuchElementException {
        return list.getFirst(); // First => head node and newest element of the stack, hence "top" of the stack
    }
    
    @Override
    public void printStack(PrintStream stream) {
        try{
            if (isEmpty()){ // if queue is empty do nothing and exit the method
                return;
            }
            
            E temp = peek(); // Extract the element at the top of the stack
            
            pop(); // pop the top element
                
            stream.println(temp); // Print the <<current>> top element of the stack
            
            printStack(stream); // Recursively call the method
                                // and print the remaining stack
               
            push(temp); // Put the element back
            
        } catch (Exception e) {
            e.getStackTrace();
            stream.close();
        }
    }
    
}
