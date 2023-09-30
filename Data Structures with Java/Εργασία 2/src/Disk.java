
public class Disk implements Comparable<Disk> { // Represents an 1TB disk - D = Disk

    protected int id; // Disk ID - unique 
    private int size; // Disk size in MB (Every disk is 1TB = 1000000 MB)
    protected SinglyLinkedList<Integer> folders; // List of folders on the disk (Will be used as the value of the Disk in the priority queue)
    private static int counter = 0; // Disk counter
    
    //constructor
    public Disk() {
    this.size = 1000000;
    counter++;
    this.id = counter;
    folders = new SinglyLinkedList<>();
    }
    
    //methods
    
    protected void addFolder(int size) { // Adds a folder to the disk
        folders.addFirst(size);
    }
    
    public int getFreeSpace() { // Returns the free space on the disk in MB
        int allocated = 0;
        SinglyLinkedList.Node<Integer> current = folders != null ? folders.head : null;
        while (current != null) {
            allocated += current.element;
            current = current.getNext();
        }
        return this.size - allocated;
    }

    @Override
    public int compareTo(Disk d) {
        int free_space = this.getFreeSpace();
        int free_space2 = d.getFreeSpace();
        if (free_space > free_space2) {
            return 1;
        } else if (free_space < free_space2) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "ID : " + this.id + ", FREE SPACE = " + this.getFreeSpace() + " MB" + ", FOLDERS : " + this.folders.toString();
    }
}
