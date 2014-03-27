import java.util.Comparator;

/**
 *
 * @author noamlustiger
 */
public class Point implements Comparable<Point> {
    private int x;
    private int y;
    public final Comparator<Point> SLOPE_ORDER;// = new SLOPE_ORDER(); 
    
    public Point(int x, int y) {       
        this.x = x;
        this.y = y;
        SLOPE_ORDER = new SLOPE_ORDER(); 
    }
    public void draw() {
         StdDraw.point(x, y);
    }
    public void drawTo(Point that) {
     StdDraw.line(this.x, this.y, that.x, that.y);
    }
    public String toString() {
        String xStr = "" + x;
        String yStr = "" + y;
        String xAndY = "(" + xStr + ", " + yStr + ")";
        return xAndY;
       
    }
    @Override
    public int compareTo(Point that) { 
        if (this.y < that.y) return -1;
        else if (this.y > that.y) return 1;
        else { //if y1 and y2 are equal
            assert (this.y == that.y);
            if (this.x < that.x) return -1;
            else if (this.x > that.x) return 1;
            else return 0;
        }
       
    }    
    public double slopeTo(Point that) {
        double denom = that.x - this.x;  
        //make horizontal slope a 'positive' zero
        if (that.y == this.y && denom < 0) denom = -denom;
        
        double slope = (that.y - this.y) / denom;  
       // if (that.y == this.y && denom < 0) slope = -slope;

        if (that.x - this.x == 0) slope = Double.POSITIVE_INFINITY;
        if (that.y == this.y && that.x == this.x) slope = Double.NEGATIVE_INFINITY;
        return slope;
        
    }
    private class SLOPE_ORDER implements Comparator<Point> {
        public int compare ;

        @Override
        public int compare(Point p1, Point p2) {
            double denom1 = p1.x - x;
            double denom2 = p2.x - x;
            //double numer1 = p1.y - y;
            //double numer2 = p2.y - y;        
            //'positive' zero   
            if (p1.y - y == 0 && p1.x - x < 0) denom1 = -denom1;
            if (p2.y - y == 0 && p2.x - x < 0) denom2 = -denom2;
            double slope1;// = (p1.y - y) / p1.x - x;
            double slope2;// = (p2.y - y) / p2.x - x;
            slope1 = (p1.y - y) / denom1;
            slope2 = (p2.y - y) / denom2;
            if (p1.y - y != 0 && p1.x - x == 0) slope1 = Double.POSITIVE_INFINITY;
            if (p2.y - y != 0 && p2.x - x == 0) slope2 = Double.POSITIVE_INFINITY;
           
            //degenerate points
            if (p1.y - y == 0 && p1.x - x == 0) slope1 = Double.NEGATIVE_INFINITY;
            if (p2.y - y == 0 && p2.x - x == 0) slope2 = Double.NEGATIVE_INFINITY;
       
           // if ((p1.y - y == 0 && p1.x - x < 0) && (p2.y - y == 0 && p2.x - x > 0) )return 1;
            //else if ((p1.y - y == 0 && p1.x - x > 0) && (p2.y - y == 0 && p2.x - x < 0) )return -1;  
            if (slope1 == slope2) return 0;
            else if (slope1 < slope2) return -1;
            else return 1;
        }
    }    
    /* public static void main(String[] args) {
        Point p = new Point(0, 9); 
        Point q = new Point(2, 8);
        Point r = new Point(8, 5);
       // System.out.println(p.Slop);
    }*/
}
/*
public class Point implements Comparable<Point> {
   public final Comparator<Point> SLOPE_ORDER;        // compare points by slope to this point

   public Point(int x, int y)                         // construct the point (x, y)

   public   void draw()                               // draw this point
   public   void drawTo(Point that)                   // draw the line segment from this point to that point
   public String toString()                           // string representation

   public    int compareTo(Point that)                // is this point lexicographically smaller than that point?
   public double slopeTo(Point that)                  // the slope between this point and that point
}


/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER;       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY 
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY 
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY 
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE 
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE 
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY 
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE 
    }
}

*/