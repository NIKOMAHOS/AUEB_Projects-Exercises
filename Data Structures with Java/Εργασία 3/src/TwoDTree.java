import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TwoDTree {

    private class TreeNode {
    
        Point item; // an object of the class Point
        TreeNode left; // pointer to left subtree
        TreeNode right; // pointer to right subtree
        Rectangle rect; // an object of the class Rectangle
        int level; // level of the node in the tree

        public TreeNode(Point p) {
            this.item = p;
            this.left = null;
            this.right = null;
            this.rect = null;
        } // constructor

        public Rectangle getRect() {
            return this.rect;
        }
    }

    private TreeNode root; // root of the tree
    private int size; // number of valid Nodes/points in the tree

    public TwoDTree() {
        this.root = new TreeNode(null);
        this.size = 0;
    } // construct an empty tree

    public TreeNode getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return (root.item == null);
    } // is the tree empty?

    public int level(TreeNode node) {
        if (this.search(node.item)) {
            return node.level;
        } else {
            return -1;
        }

    } // return the level of the tree

    public int size() {
        if (isEmpty()) {
            return 0; // returns 0 if it's empty
        } else {
            return this.size;
        }
    } // return number of valid points in the tree
    
    public int compare(Point p_1, Point p_2, int level) {
        if (level % 2 == 0) { // if the level is even or 0
            return p_1.x - p_2.x; // compare the x coordinates
        } else {
            return p_1.y - p_2.y; // compare the y coordinates
        }
    } // compare two points based on the level they are in

    private TreeNode insertNode(TreeNode node, Point point, int level) {
        TreeNode newNode;
        if (node == null) {
            newNode = new TreeNode(point);
            System.out.println("The point " + newNode.item.toString() + " was successfully inserted into the tree.");
            newNode.level = level; // set the level of the new node
            return newNode;
            // if the node is null (meaning that it's going to be inserted as a leaf),
            // create a new Node that contains the point p and return it
        }

        int comp = compare(point, node.item, level);
        // if comp < 0, the now checking coordinate of point p is less than the specific coordinate of the point
        //in the current node so it should be inserted its left subtree
        // else the now checking coordinate of point p is more or equal than the specific coordinate of the point
        // in the current node so it should be inserted its left subtree
        if (comp <= 0) { // Insert to the left subtree
            newNode = insertNode(node.left, point, level + 1);  // node is parent of the newly inserted node
            // call the recursive method from the left subtree and on the next level
            if ((newNode.level) % 2 == 0) { // if the level where the new node/point is going to be inserted at is even or 0
                newNode.rect = new Rectangle(node.rect.xmin, node.rect.xmax, node.rect.ymin, node.item.y);
            } else { // if the level where the new node/point is going to be inserted at is odd
                newNode.rect = new Rectangle(node.rect.xmin, node.item.x, node.rect.ymin, node.rect.ymax);
            }
            node.left = newNode; // set the left child of the parent node to the newly inserted node
            return node;
        } else { // Insert to the right subtrees
            newNode = insertNode(node.right, point, level + 1); // node is parent of the newly inserted node
            // call the recursive method from the right subtree and on the next level
            if ((newNode.level) % 2 == 0) { // if the level where the new node/point is going to be inserted at is even or 0
                newNode.rect = new Rectangle(node.rect.xmin , node.rect.xmax, node.item.y, node.rect.ymax);
            } else { // if the level where the new node/point is going to be inserted at is odd
                newNode.rect = new Rectangle(node.item.x , node.rect.xmax, node.rect.ymin, node.rect.ymax);
            }
            node.right = newNode; // set the right child of the parent node to the newly inserted node
            return node;
        }

    } // inserts a Node that contains the point p to the tree
    
    public void insert(Point p) {
        if (isEmpty()) { // if the tree is empty
            root = new TreeNode(p); // insert the point p to the root Node
            root.rect = new Rectangle(0, 100, 0, 100);
            root.level = 0; // set the level of the root Node to 0
            System.out.println("The point " + root.item.toString() + " was successfully inserted into the tree as the root.");
            size++; // increment the size of the tree
            return;
        }
        else if(this.search(p)){
            System.out.println("The point " + p.toString() + " you are trying to insert is already in the tree.");
            return; // if the point p is already in the tree, print a message and return
        }
        else {
            insertNode(root, p, 0);
            // Calling the recursive method from the root Node and on the first level ( level = 0 )
            size++; // increment the size of the tree
            return;
        }
    
    } // inserts the point p to the tree

    private boolean searchNode(TreeNode node, Point point , int level){
        if (node == null) {
            return false; // TreeNode Not Found. So return false
        }
        
        if (point.equals(node.item)){ // if found the TreeNode that contains the point p
            System.out.println("The point " + point.toString() + " was found in the tree.");
            return true; // Found TreeNode. So return true.    
        }
        
        int comp = compare(point, node.item, level); 
        // Compare given point with the point that the current TreeNode contains
        
        if (comp <= 0) {
            return searchNode(node.left, point, level + 1); 
            // call the recursive method from the right subtree and on the next level
        }
        else {
            return searchNode(node.right, point, level + 1);
            // call the recursive method from the right subtree and on the next level
        }
    }// does the tree have a Node that contains the point p?
    
    public boolean search(Point p) {
        if (isEmpty()) { // if the tree is empty
            System.out.println("The tree is empty.");
            return false; 
        }
        else {
            return searchNode(root, p, 0);
            // Calling the recursive method from the root Node and on the first level ( level = 0 )
        }
        
    } // does the tree contain p? 

    private void rangeSearchHelper(TreeNode node, Rectangle userRectangle, SinglyLinkedList<Point> resultList) {
        if (node == null) {
            return;
        }
        
        if (node.getRect().intersects(userRectangle)) { // if the rectangle of the current node intersects with the user's rectangle
            if (userRectangle.contains(node.item)) { // if the user's rectangle contains the point of the current node
                resultList.addLast(node.item); // add the point of the current node to the list
            }     
        }
        rangeSearchHelper(node.left, userRectangle, resultList);  // call the recursive method to search to the left subtree
        rangeSearchHelper(node.right, userRectangle, resultList); // call the recursive method to search to the right subtree
    }
    
    public SinglyLinkedList<Point> rangeSearch(TreeNode node, Rectangle userRectangle) { // node must be the root of the tree
        SinglyLinkedList<Point> resultList = new SinglyLinkedList<>();
        rangeSearchHelper(node, userRectangle, resultList); // call the recursive method from the root Node
        
        return resultList;
    }// returns a list of all the points in the tree that are inside the rectangle given by the user
    
    public Point nearestNeighbor(Point p) {
    
        if (isEmpty()) { // if the tree is empty
            System.out.println("The tree is empty.");
            return null;
        }
        if (search(p)) { // if the point p is already in the tree, print a message and return
            System.out.println("So the nearest point to " + p.toString() + " is the point itself ( distance = 0 ). ");
            return p;
        }
        
        TreeNode nearest = new TreeNode(root.item); // initialize the nearest variable as the root Node to traverse through all the nodes in tree
        SinglyLinkedList<Point> list = new SinglyLinkedList<Point>(); // initialize the list that will contain all the points in the tree
        list = rangeSearch(root, new Rectangle(0, 100, 0, 100)); // call the rangeSearch method to fill the list with all the points in the tree
        // Start the loop that will find the nearest point to p
        while (list.size() != 0 ) {
            Point current = list.removeFirst();
            if (current.distanceSquared(p) < nearest.item.distanceSquared(p)) { // if found new nearest point to p
                nearest = new TreeNode(current); // update the nearest variable
            }
        }
        
        return nearest.item;
        
    } // returns the point in the tree that is closest to p    
 

    // Main method
    public static void main(String[] args) {
        TwoDTree tree = new TwoDTree();
        //File file = new File(args[0]);
        File file = new File("example.txt");
        try {
            Scanner sc = new Scanner(file);
            String firstLine = sc.nextLine();
            int n = Integer.parseInt(firstLine);
            System.out.println("The 2D-Tree file " + file.getName() + " contains: " + n + " nodes");
            tree = new TwoDTree();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] coordinates = line.split(" ");
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                Point point = new Point(x, y);
                tree.insert(point);
            }
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file);
        } catch (NumberFormatException e) {
        } catch (Exception e) {
            System.out.println("Invalid Arguments given: " + e.getMessage());
        }
        Scanner sc1 = new Scanner(System.in);
        int choice = 0;
        while (choice != 7) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Compute the size of the tree");
            System.out.println("2. Insert a new point");
            System.out.println("3. Search if a given point exists in the tree");
            System.out.println("4. Provide a query rectangle");
            System.out.println("5. Provide a query point");
            System.out.println("6. Print the tree");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc1.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Size of the tree: " + tree.size());
                    break;
                case 2:
                    System.out.print("Enter x coordinate: ");
                    int x = sc1.nextInt();
                    System.out.print("Enter y coordinate: ");
                    int y = sc1.nextInt();
                    Point temp1 = new Point(x, y);
                    tree.insert(temp1);
                    break;
                case 3:
                    System.out.print("Enter x coordinate: ");
                    x = sc1.nextInt();
                    System.out.print("Enter y coordinate: ");
                    y = sc1.nextInt();
                    Point temp2 = new Point(x, y);
                    if (tree.search(temp2)) {
                        System.out.println("Point exists in the tree!");
                    } else {
                        System.out.println("Point does not exist in the tree!");
                    }
                    break;
                case 4:
                    System.out.print("Enter x coordinate of bottom-left point: ");
                    int xmin = sc1.nextInt();
                    System.out.print("Enter y coordinate of bottom-left point: ");
                    int ymin = sc1.nextInt();
                    System.out.print("Enter x coordinate of top-right point: ");
                    int xmax = sc1.nextInt();
                    System.out.print("Enter y coordinate of top-right point: ");
                    int ymax = sc1.nextInt();
                    Rectangle rec = new Rectangle(xmin, xmax, ymin, ymax);
                    SinglyLinkedList<Point> pointsInRect = tree.rangeSearch(tree.getRoot(), rec);
                    System.out.println("Points inside the given rectangle " + rec.toString() + " : ");
                    System.out.print(pointsInRect.toString());
                    break;
                case 5:
                    System.out.print("Enter x coordinate of point: ");
                    int xp = sc1.nextInt();
                    System.out.print("Enter y coordinate of point: ");
                    int yp = sc1.nextInt();
                    Point temp3 = new Point(xp, yp);
                    Point nearest = tree.nearestNeighbor(temp3);
                    if (nearest != null && nearest.distanceTo(temp3) != 0) { // else the appropriate printing happens inside the method
                        System.out.println("The nearest point to " + temp3.toString() + " is " + nearest.toString() + " with a distance of " + nearest.distanceTo(temp3) + ".");
                    }
                    break;
                case 6:
                    System.out.println("Here are all the nodes in the tree ( here we use pre-order traversal ): "); // προδιατεταγμένη διάσχιση
                    SinglyLinkedList<Point> points = tree.rangeSearch(tree.getRoot(), new Rectangle(0, 100, 0, 100));
                    System.out.print(points.toString());
                break;
            }
        }
        sc1.close();
    }

}
