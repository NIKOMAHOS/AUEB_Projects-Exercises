public class SinglyLinkedList <E> { 

    protected static class Node <E> {
        protected E element;        
        protected Node <E> next;    
        
        public Node (E e, Node<E> n) { 
            element = e; 
            next = n;    
        }                
        
        public E getElement() {return element;}    
        public Node <E> getNext() {return next;}   
        public void setNext(Node<E> n) {next = n;} 
    }   
   
    protected Node<E> head = null; 
    protected Node<E> tail = null; 
    protected int size = 0; 
    
    public SinglyLinkedList() { 
        
    }

    public int size() {return size;} 
    
    public boolean isEmpty() {return size == 0;} 
    
    public E getFirst() { 
        if (isEmpty()) {return null;}
        return head.getElement();
    }
    
    public E getLast() { 
        if (isEmpty()) {return null;}
        return tail.getElement();
    }
    
    public void addFirst(E e) { 
        head = new Node<E>(e, head); 
        if (isEmpty()) {
            tail = head;}           
    }
    
    public void addLast(E e) { 
        Node<E> newest = new Node<E>(e, null); 
        if (isEmpty()) {
            head = newest;
        }else {tail.setNext(newest);}  
        tail = newest;                                         
    }
    
    public E removeFirst() { 
        if (isEmpty()) {return null;} 
        E deleted = head.getElement();
        head = head.getNext();               
        if (size == 0) {       
            tail = null;       
        }
        return deleted;             
    }
}