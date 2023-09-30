public class CircularlyLinkedList <E> { // E = element
    private static class Node <E> {
        private E element;          // Αναφορά στο στοιχείο στο οποίο δείχνει ο συγκεκριμένος κόμβος
        private Node <E> next;      // Αναφορά στον αμέσως επόμενο κόμβο της λίστας
        
        public Node (E e, Node<E> n) { // constructor
            element = e;
            next = n;
        }
        
        public E getElement() {return element;}    // Επιστρέφει το στοιχείο στο οποίο δείχνει ο συγκεκριμένος κόμβος
        public Node<E> getNext() {return next;}    // Επιστρέφει τον επόμενο κόμβο από τον συγκεκριμένο
        public void setNext(Node<E> n) {next = n;} // Αλλάζει τον αμέσως επόμενο κόμβο από τον συγκεκριμένο
    }
    
    // Μεταβλητές αρχικοποίησης της CircularlyLinkedList
        
        // Στην συγκεκριμένη λίστα, δεν είναι αναγκαία η ύπαρξη αναφοράς στο head της λίστας
        // παρα μόνο στο tail καθώς μπορούμε ανά πάσα στιγμή να έχουμε πρόσβαση στο head με τον εξής τρόπο:
        // _head_ = tail.getNext(); [Το head είναι δηλαδή το tail.getNext()]        
        protected Node<E> tail = null; // Κόμβος tail της λίστας (null αν είναι κενή)
        protected int size = 0;        // Πλήθος κόμβων της λίστας, αρχικά 0
       
       public CircularlyLinkedList() { // constructor
       // Δημιουργεί μία αρχικά κενή κυκλική λίστα
       // με tail (και head) ίσο με null και size = 0
       }
       
    // Μέθοδοι
    
    public int size() {return size;} // Επιστρέφει το μέγεθος της λίστας, δηλαδή το πλήθος των κόμβων της
    
    public boolean isEmpty() {return size == 0;} // Αν η λίστα είναι άδεια, θα έχει και 0 κόμβους. Οπότε θα επιστραφεί true.
    
    public E getFirst() { // Επιστρέφει τo πρώτο στοιχείο της λίστας, χωρίς να το αφαιρέσει από αυτήν
        if (isEmpty()){ return null;}
        return tail.getNext().getElement(); // Επιστρέφει το στοιχείο στο οποίο δείχνει ο κόμβος "head" => tail.getNext()
    }
    
    public E getLast() { // Επιστρέφει τo τελευταίο στοιχείο της λίστας, χωρίς να το αφαιρέσει από αυτήν
        if (isEmpty()) {return null;}
        return tail.getElement(); // Επιστρέφει το στοιχείο στο οποίο δείχνει ο κόμβος tail
    }
    
    public void rotate() {           // Πηγαίνει το πρώτο στοιχείο στο τέλος της λίστας
        if (tail != null) {          // Αν δεν είναι άδεια
            tail = tail.getNext();}  // το παλιό "head" γίνεται το νέο tail
    }
    
    public void addFirst(E e) { // Προσθέτει το στοιχείο e στην αρχή της λίστας
        if (isEmpty()) {
            tail = new Node<E>(e, null);
            tail.setNext(tail); // Κυκλική σύνδεση με τον εαυτό του.
        }else {
            Node<E> newest = new Node<E> (e, tail.getNext());
            tail.setNext(newest); // Αλλαγή του "head" της λίστας.
        }
        size++; // Αύξηση κατά 1 του πλήθους των κόμβων του πίνακα  
    }
    
    public void addLast(E e) { // Προσθέτει το στοιχείο e στο τέλος της λίστας.
        addFirst(e);           // Τοποθετεί το στοιχείο στην αρχή της λίστας.
        rotate();              // Ο κόμβος που δείχνει στο στοιχείο e γίνεται τώρα ο tail
    }                          //  (Οπότε το e "πάει" στο τέλος της λίστας) 
    
    public E removeFirst() {   // Αφαιρεί από την λίστα το πρώτο στοιχείο και το επιστρέφει                 
        if (isEmpty()) {return null;}           // Άδεια λίστα, οπότε δεν έχει να επιστρέψει κάτι
        
        Node<E> head = tail.getNext();          // Δημιουργία προσωρινής μεταβλητής head
        if (head == tail) {
            tail =null;                          // Πρέπει να είναι ο μοναδικός κόμβος στη λίστα.
        }else {tail.setNext(head.getNext());  // Αφαιρούμε το " head " από τη λίστα
        }               
        size--;                                  // Μείωση κατά 1 του πλήθους των κόμβων του πίνακα
        return head.getElement();                // Επιστροφή του αφαιρούμενου στοιχείου
    }
}


