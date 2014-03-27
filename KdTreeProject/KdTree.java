
public class KdTree {
    private int size;
    private Node root;
    private Point2D closest;
    private double distance;
    
    public KdTree() { }
    
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void insert(Point2D p) {
            
        if (root == null) {
            Node node = new Node();
            root = node;
            root.point = p;
            root.vertical = true;
            root.rect = new RectHV(0, 0, 1, 1);
            size++;
            return;
        } 
        Node current = root;
        Node node = new Node();
        node.point = p;
        while (true) {
            if (p.equals(current.point)) break;
            if (current.vertical) {
                
               if (p.x() <= current.point.x()) {
                   if (current.lb != null) {
                       current = current.lb;
                   } else {
                       
                       node.vertical = false;
                       RectHV rect = current.rect;
                       node.rect = new RectHV(rect.xmin(), rect.ymin(), current.point.x(), rect.ymax());
                       size++;
                       current.lb = node;
                      // System.out.println(node.point.x() + "," + node.point.y() + " left of node " + current.point.x() + "," + current.point.y());
                       return;
                   }
               } else if (p.x() > current.point.x()) {
                    if (current.rt != null) {
                       current = current.rt;
                    } else {
                       node.vertical = false;
                       RectHV rect = current.rect;
                       node.rect = new RectHV(current.point.x(), rect.ymin(), rect.xmax(), rect.ymax());
                       size++;
                       current.rt = node;
                       return;
                   }
               }
            } else { //horizontal divider
                
                if (p.y() <= current.point.y()) {
                    if (current.lb != null) {
                        current = current.lb;
                   } else {
                        node.vertical = true;
                       // System.out.println(node.point.x() + "," + node.point.y() + " under node " + current.point.x() + "," + current.point.y() + node.vertical);
                        RectHV rect = current.rect;
                        node.rect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), current.point.y());
                        size++;
                        current.lb = node;
                        return;
                   } 
                } else if (p.y() > current.point.y()) {
                      if (current.rt != null) {
                         current = current.rt;
                     } else {
                        node.vertical = true;
                        RectHV rect = current.rect;
                        node.rect = new RectHV(rect.xmin(), current.point.y(), rect.xmax(), rect.ymax());
                    //    node.divider++;
                        size++;
                        current.rt = node;
                        return;
                   }
                }
            } 
        }
    }
  
    private static class Node {
        private Point2D point;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree 
        private boolean vertical;
        
        private void drawPoint() {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.01);
            point.draw();
            
        }
        private void drawLine() {
            Point2D min;
            Point2D max;
            if (vertical) {
                StdDraw.setPenColor(StdDraw.RED);
                min = new Point2D(point.x(), rect.ymin());
                max = new Point2D(point.x(), rect.ymax());
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
                min = new Point2D(rect.xmin(), point.y());
                max = new Point2D(rect.xmax(), point.y()); 
            }
            StdDraw.setPenRadius();
            min.drawTo(max);
        }
    }
    //helper
    private Node goLeft(Node n) {
        if (n.lb == null) {
            return null;
        }
        return n.lb;
    }
    //helper
    private Node goRight(Node n) {
        if (n.rt == null) {
            return null;
        }
        return n.rt;
    }
    public boolean contains(Point2D p) {
        if (size == 0) return false;
        Node current = root;
        boolean found = false;
        
        while (!found) {
             if (current.point.equals(p)) {
                  found = true;
                  break;
             }
             if (current.vertical && p.x() <= current.point.x()) {
                 if (current.lb == null) break;
                 else current = current.lb;  
                
             } else if (current.vertical && p.x() > current.point.x()) {
                  if (current.rt == null) break;
                  else current = current.rt;  
                 
             } else if (!current.vertical && p.y() <= current.point.y()) {
                  if (current.lb == null) break;
                  else current = current.lb;  
                 
             } else if (!current.vertical && p.y() > current.point.y()) {
                  if (current.rt == null) break;
                  else current = current.rt;  
             }
        }
        return found;
    }
    
    public void draw() {
        draw(root);   
    }
    //helper
    private void draw(Node n) {
        if (n.lb != null) draw(n.lb);
        if (n.rt != null) draw(n.rt);
        n.drawPoint();
        n.drawLine();
        StdDraw.show();
    }

    public Iterable<Point2D> range(RectHV rect) {
        Stack<Point2D> stack = new Stack<Point2D>();
        if (root == null) return stack;
        range(root, rect, stack);
        return stack; 
    }
    //helper
    private Iterable<Point2D> range(Node n, RectHV rect, Stack<Point2D> stack) {
        if (n.rect.intersects(rect)) {
            if (rect.contains(n.point)) stack.push(n.point);
            if (n.lb != null) range(n.lb, rect, stack);
            if (n.rt != null) range(n.rt, rect, stack);
        }
        return stack;
    }
    
    public Point2D nearest(Point2D p) {
        if (root == null) return null;
       // double dist = p.distanceSquaredTo(root.point);
        closest = root.point;
        distance = p.distanceSquaredTo(root.point);
        nearest(root, p);
        return closest;  
    }
    
    private void nearest(Node n, Point2D p) {
        
        if (n == null) return;
        if (n.rect.distanceSquaredTo(p) > distance) return;
        if (p.distanceSquaredTo(n.point) <= distance) {
            closest = n.point;
            distance = p.distanceSquaredTo(closest);     
        } 
        
        if ((n.vertical && p.x() <= n.point.x()) || (!n.vertical && p.y() <= n.point.y())) {
           nearest(n.lb, p);
           nearest(n.rt, p);           
        } else { //if ((n.vertical && p.x() > n.point.x()) || (!n.vertical && p.y() > n.point.y())){
           nearest(n.rt, p);
           nearest(n.lb, p);
        }
       
    }
    public static void main(String[] args) {
        StdDraw.show(0);
        KdTree kdtree = new KdTree();
        //Point2D point = new Point2D(0.5, 0.4);
        while (true) {
            if (StdDraw.mousePressed()) {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                System.out.printf("%8.6f %8.6f\n", x, y);
                Point2D p = new Point2D(x, y);
                kdtree.insert(p);
                StdDraw.clear();
                kdtree.draw();
                //System.out.println(kdtree.size());
            }
            StdDraw.show(50);
        }
        

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