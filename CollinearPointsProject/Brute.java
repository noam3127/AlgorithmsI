
import java.util.Arrays;


public class Brute {

    public static void main(String[] args) {
        String file = args[0];
        In in = new In(file);
        int N = in.readInt();
        Point[] points = new Point[N];
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        int x, y;
        for (int i = 0; i < N; i++) {
            x = in.readInt();
            y = in.readInt();
            points[i] = new Point(x, y);   
            points[i].draw();
        }         
        Point p, q, r, s;
        double seg1, seg2, seg3;
       // Arrays.sort(points);
        for (int a = 0; a < N; a++) {
            p = points[a];
            
            for (int b = 0; b < N; b++) {
                if (b == a) continue; 
                q = points[b];
                seg1 = p.slopeTo(q);
                
                for (int c = 0; c < N; c++) {
                    if (c == a || c == b) continue;
                    r = points[c];
                    seg2 = p.slopeTo(r);
                    
                    for (int d = 0; d < N; d++) {
                        if (d == a || d == b || d == c ) continue;
                        s = points[d];
                        seg3 = p.slopeTo(s);
                        
                        if (seg1 == seg2 && seg1 == seg3) {
                            if (p.compareTo(q) > -1 || q.compareTo(r) > -1 || r.compareTo(s) > -1) continue;
                            StdOut.println(p.toString() + " -> " + q.toString() + " -> " + r.toString() + " -> " + s.toString());
                            p.drawTo(s);
                            StdDraw.show();
                           // q.drawTo(r);
                            //r.drawTo(s);
                            
                        }
                    }
                }
            }           
        }
        StdDraw.show(0);
    }
           
}
    
/*

*/
