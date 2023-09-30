public class SinglyLinkedList <E> { // E = element

    protected static class Node <E> {
        protected E element;          // Αναφορά στο στοιχείο στο οποίο δείχνει ο συγκεκριμένος κόμβος
        protected Node <E> next;      // Αναφορά στον αμέσως επόμενο κόμβο της λίστας
        
        public Node (E e, Node<E> n) { // constructor
            element = e; // Το στοιχείο της λίστας στον συγκεκριμένο κόμβο (ή θέση)
            next = n;    // Η σύνδεση μεταξύ των κόμβων της λίστας, δηλαδή η αναφορά σε ένα αντικείμενο Node.
        }                // Συγκεκριμένα στο ακριβώς επόμενο από το συγκεκριμένο
        
        public E getElement() {return element;}    // Επιστρέφει το στοιχείο στο οποίο δείχνει ο συγκεκριμένος κόμβος
        public Node <E> getNext() {return next;}   // Επιστρέφει τον επόμενο κόμβο από τον συγκεκριμένο
        public void setNext(Node<E> n) {next = n;} // Αλλάζει τον αμέσως επόμενο κόμβο από τον συγκεκριμένο
    }   
    
    // Μεταβλητές αρχικοποίησης της SinglyLinkedList
    
    protected Node<E> head = null; // Κόμβος head της λίστας (null αν είναι κενή)
    protected Node<E> tail = null; // Κόμβος tail της λίστας (null αν είναι κενή)
    protected int size = 0; // Πλήθος κόμβων της λίστας, αρχικά 0
    
    public SinglyLinkedList() { // constructor
    // Δημιουργεί μία αρχικά κενή λίστα
    // με  head και tail ίσα με null και size = 0
    }
    
    // Μέθοδοι
    
    public int size() {return size;} // Επιστρέφει το μέγεθος της λίστας, δηλαδή το πλήθος των κόμβων της
    
    public boolean isEmpty() {return size == 0;} // Αν η λίστα είναι άδεια, θα έχει και 0 κόμβους. Οπότε θα επιστραφεί true
    
    public E getFirst() { // Επιστρέφει τo πρώτο στοιχείο της λίστας, χωρίς να το αφαιρέσει από αυτήν
        if (isEmpty()) {return null;}
        return head.getElement();
    }
    
    public E getLast() { // Επιστρέφει τo τελευταίο στοιχείο της λίστας, χωρίς να το αφαιρέσει από αυτήν
        if (isEmpty()) {return null;}
        return tail.getElement();
    }
    
    public void addFirst(E e) { // Προσθέτει το στοιχείο e στην αρχή της λίστας
        head = new Node<E>(e, head); // Δημιορυργία νέο κόμβου και σύνδεσή του με την πρώτη θέση (head) της λίστας
        if (isEmpty()) {
            tail = head;} // Αν η λίστα είναι άδεια ο νέος κόμβος γίνεται και tail
        size++;           // Αύξηση κατά 1 του πλήθους των κόμβων του πίνακα  
    }
    
    public void addLast(E e) { // Προσθέτει το στοιχείο e στο τέλος της λίστας
        Node<E> newest = new Node<E>(e, null); // Δημιουργία νέο κόμβου με τιμή null διότι θα καταλήξει ως το tail της λίστας
        if (isEmpty()) {
            head = newest;
        }else {tail.setNext(newest);}  // Νέος κόμβος μετά το tail
        tail = newest;                 // Ο νέος αυτός κόμβος γίνεται το tail 
        size++;                        // Αύξηση κατά 1 του πλήθους των κόμβων του πίνακα  
    }
    
    public E removeFirst() { // Αφαιρεί από την λίστα το πρώτο στοιχείο και το επιστρέφει
        if (isEmpty()) {return null;} // Άδεια λίστα, οπότε δεν έχει να επιστρέψει κάτι
        E a = head.getElement();
        head = head.getNext(); // Αν η λίστα είχε μόνο 1 κόμβο θα γινόταν null
        size--;                // Μείωση κατά 1 του πλήθους των κόμβων του πίνακα
        if (size == 0) {       
            tail = null;       // Αν άδειασε η λίστα, πρέπει το tail να γίνει null
        }
        return a;              // Επιστροφή του αφαιρούμενου στοιχείου
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        SinglyLinkedList.Node<E> current = head;
        while (current != null) {
            sb.append(current.element);
            sb.append(", ");
            current = current.next;
        }
        sb.append(" ");
        return sb.toString();
    }
}
