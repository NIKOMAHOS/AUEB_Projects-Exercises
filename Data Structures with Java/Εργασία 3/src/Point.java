public class Point {
    int x;
    int y;

    Point(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Invalid point. Negative coordinates are not allowed.");
        }
        if (x > 100 || y > 100) {
            throw new IllegalArgumentException("Invalid point. Max value of x and y is 100.");
        }
        
        this.x = x;
        this.y = y;
    }

    public int x() {
        return this.x;
    } // return the x-coordinate

    public int y() {
        return this.y;
    } // return the y-coordinate
    
    public double distanceTo(Point p) {
        int dx = Math.abs(this.x() - p.x()); // |x1 - x2|
        int dy = Math.abs(this.y() - p.y()); // |y1 - y2|
        return Math.sqrt((dx * dx) + (dy * dy)); // sqrt(dx^2 + dy^2)
    } // The Euclidean distance between two points
    
    public int distanceSquared(Point p) {
        int dx = Math.abs(this.x() - p.x()); // |x1 - x2|
        int dy = Math.abs(this.y() - p.y()); // |y1 - y2|
        return (dx * dx) + (dy * dy); // dx^2 + dy^2
    } // The square of the Euclidean distance between two points
    
    public boolean equals(Point p) {
        if (p == null) return false;
        if (p.getClass() != this.getClass()) return false;
        return (this.x == p.x) && (this.y == p.y);
    } // is this point equal to that point?

    public String toString() {
        return " (" + x + ", " + y + ") ";
    } // string representation: (x, y)
}
