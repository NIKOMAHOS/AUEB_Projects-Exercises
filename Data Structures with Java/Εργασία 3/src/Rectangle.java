public class Rectangle {
    int xmin; // bottom left x coordinate
    int xmax; // upper right x coordinate
    int ymin; // bottom left y coordinate
    int ymax; // upper right y coordinate

    Rectangle(int xmin, int xmax, int ymin, int ymax) {
    
        if (xmin > xmax || ymin > ymax) {
            throw new IllegalArgumentException("Invalid rectangle.\nCoordinates of bottom left point must be less than the coordinates of the upper right.");
        }
        
        if (xmin < 0 || xmax < 0 || ymin < 0 || ymax < 0) {
            throw new IllegalArgumentException("Invalid rectangle. Negative coordinates are not allowed.");
        }
        
        if (xmin > 100 || xmax > 100 || ymin > 100 || ymax > 100) {
            throw new IllegalArgumentException("Invalid rectangle. Max area is 100x100.");
        }
        
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
    }

    public int xmin() {
        return this.xmin;
    } // minimum x-coordinate of rectangle

    public int ymin() {
        return this.ymin;
    } // minimum y-coordinate of rectangle

    public int xmax() {
        return this.xmax;
    } // maximum x-coordinate of rectangle

    public int ymax() {
        return this.ymax;
    } // maximum y-coordinate of rectangle

    public boolean contains(Point p) {
        if (xmin <= p.x && xmax >= p.x && ymin <= p.y && ymax >= p.y) {
            return true;
        } else {
            return false;
        }
    } // does p belong to the rectangle?

    public boolean intersects(Rectangle that) {
        return (xmin <= that.xmax && that.xmin <= xmax && ymin <= that.ymax && that.ymin <= ymax);
    } // do the two rectangles intersect?

    public int distanceTo(Point p) {
        int dx = Math.max(Math.abs(p.x - this.xmin), Math.abs(p.x - this.xmax));
        int dy = Math.max(Math.abs(p.y - this.ymin), Math.abs(p.y - this.ymax));
        return (int)Math.sqrt(dx*dx + dy*dy);
    }// Euclidean distance from p to closest point in rectangle

    public int squareDistanceTo(Point p) {
        int dx = Math.max(Math.abs(p.x - this.xmin), Math.abs(p.x - this.xmax));
        int dy = Math.max(Math.abs(p.y - this.ymin), Math.abs(p.y - this.ymax));
        return (int) (Math.pow(dx, 2) + Math.pow(dy, 2));
    } // square of Euclidean distance from p to closest point in rectangle

    public String toString() {
        return " [" + xmin + ", " + xmax + "] " + "x" + " [" + ymin + ", " + ymax + "] ";
    } // string representation: [xmin, xmax] × [ymin, ymax]

}