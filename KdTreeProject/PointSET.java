
public class PointSET {
    private SET<Point2D> set;
   
    public PointSET() {
        set = new SET();
    }
    public boolean isEmpty() {
        return set.isEmpty();
    }
    public int size() {
        return set.size();
    }
    public void insert(Point2D p) {
        if (!set.contains(p)) set.add(p);
    }
    public boolean contains(Point2D p) {
        return set.contains(p);
    }
    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        for (Point2D p : set) {
            p.draw();
        }
    }
    public Iterable<Point2D> range(RectHV rect) {
        Stack<Point2D> stack = new Stack<Point2D>();
        for (Point2D p : set) {
            if (rect.contains(p)) stack.push(p);
        }
        return stack;
    }
    public Point2D nearest(Point2D p) {
       double dist = -1;
       Point2D nearest = null;
       if (set.isEmpty()) return null;
       for (Point2D point : set) {
           if (dist < 0) {
               dist = p.distanceTo(point);
               nearest = point;
           }
           else if (p.distanceTo(point) < dist) {
             dist = p.distanceTo(point);
             nearest = point;
           }     
       }
       return nearest;
    }
}

/*
  public PointSET()                               // construct an empty set of points
   public boolean isEmpty()                        // is the set empty?
   public int size()                               // number of points in the set
   public void insert(Point2D p)                   // add the point p to the set (if it is not already in the set)
   public boolean contains(Point2D p)              // does the set contain the point p?
   public void draw()                              // draw all of the points to standard draw
   public Iterable<Point2D> range(RectHV rect)     // all points in the set that are inside the rectangle
   public Point2D nearest(Point2D p)               // a nearest neighbor in the set to p; null if set is empty
}

*/