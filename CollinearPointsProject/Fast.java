
import java.util.Arrays;

public class Fast {
    
    public static void main(String[] args) {
       // System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
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
        } 
        Point p;
       // Arrays.sort(points);
        //Point[] sorted = points;
        
        for (int a = 0; a < N - 2; a++) { 
            
            Arrays.sort(points, a , N);
            p = points[a];    
            Arrays.sort(points, a, N, p.SLOPE_ORDER);   
            int lo = a + 1;
            int hi = a + 2;
            while (lo < N) { 
              
                double theSlope = p.slopeTo(points[lo]);
                if (hi < N && theSlope == p.slopeTo(points[hi])) {
                    hi++;
                    continue;
                }  
                else if (hi - lo > 2) { 
                    //check if subsegment
                   
                     if (a > 0 && points[a - 1].slopeTo(p) == theSlope) {
                        hi--;
                        lo = hi + 1;
                        hi = lo + 1;
                        continue;
                    }
                    
                    
                    StdOut.print(p.toString());
                    for (int j = lo; j < hi; j++) {
                         StdOut.print(" -> " + points[j].toString());
                    }  
                    StdOut.println();
                    p.drawTo(points[hi - 1]);
                    StdDraw.show();
                    
                } else hi--;
                lo = hi + 1;
                hi = lo + 1;       
            } 
        }
            //Arrays.sort(points, a, N);
               /* double tempSlope = p.slopeTo(points[i + 1]);
                int slopes = 1;
                int counter = 0;
                int j = i + 1;
                for (j = i; j < N; j++) {
                    if (p.slopeTo(points[i]) == p.slopeTo(points[j])) {
                       counter++; 
                    } else break;
                }  
                if (counter > 2) {      
                   
                    StdOut.print(p.toString());
                    for (int d = i; d < j; d++) {
                        StdOut.print(" -> " + points[d].toString());
                    }
                    StdOut.println();
                    p.drawTo(points[j - 1]);
                    StdDraw.show();
                    i += j;
                }
                 //Arrays.sort(points);
              
                
          
               // 
                
               // if (a > 0 && points[a - 1].slopeTo(p) == theSlope) break;
               /* if (i > a + 1) {   
                    if (p.slopeTo(points[i - 1]) == p.slopeTo(points[i]) ) {
                        counter++;
                        System.out.println(counter);
                    } else counter = 1;             
               // }
                   // Arrays.sort(points, i - counter, i + 1);
                    if (counter > 2) {
                        int index;                        
                                                  
                        for (index = i - counter; index <= i; index++) {
                             StdOut.print(" -> " + points[index].toString());         
                        }
                        StdOut.println("That had" + counter);
                        p.drawTo(points[i]);
                        StdDraw.show();
                     }
                            
                 } 
            }
                /*while (i + counter < N && p.slopeTo(points[i + counter]) == theSlope) {
                  //increment slopeCount to maximum amount of consecutive subsegments 
                    slopeCount++;
                    counter++;
                    System.out.println(slopeCount);
                }
                if (slopeCount > 2) {
                    Arrays.sort(points);
                    if (points[i - 1].slopeTo(p) == theSlope) continue;
                    StdOut.print(p.toString());
                    int d = 1;
                    for (d = 1; d < slopeCount; d++) {                      
                         StdOut.print(" -> " + points[i + d].toString());
                    }
                    StdOut.println();
                    p.drawTo(points[i + d]);
                    StdDraw.show(0);
                    
                }
                if (p.slopeTo(points[i]) != p.slopeTo(points[i + 1])) continue;
                if (p.slopeTo(points[i]) != p.slopeTo(points[i + 2])) continue;
                if (i < N - 3 && p.slopeTo(points[i]) == p.slopeTo(points[i + 3])) {
                    Point[] five = new Point[5];
                    five[0] = p;
                    five[1] = points[i];
                    five[2] = points[i + 1];
                    five[3] = points[i + 2];
                    five[4] = points[i + 3]; 
                    Arrays.sort(five);    
                    StdOut.println(five[0].toString() + " -> " + five[1].toString() + " -> " + five[2].toString() + " -> " + five[3].toString() + " -> " + five[4].toString()); 
                    five[0].drawTo(five[4]);
                    StdDraw.show(0);
                    
                    
                } else {          
                    Point[] four = new Point[4];
                    four[0] = p;
                    four[1] = points[i];
                    four[2] = points[i + 1];
                    four[3] = points[i + 2];
                    Arrays.sort(four);
                    StdOut.println(four[0].toString() + " -> " + four[1].toString() + " -> " + four[2].toString() + " -> " + four[3].toString()); 
                    four[0].drawTo(four[3]);
                    StdDraw.show(0);
                    break;
                }*/
                
              /*  int counter = 2;
                int j;              
                for (j = i  + 1; j < N; j++) {                  
                    if (j == a) continue;
                    if (p.slopeTo(points[j]) == theSlope) {
                        counter++;
                        System.out.println(counter);
                    } 
                    if (counter == 4 && j < N - 1 && p.slopeTo(points[j + 1]) != theSlope) {  
                        Point[] four = new Point[4];
                        four[0] = points[j];
                        four[1] = points[j - 1];
                        four[2] = points[j - 2];
                        four[3] = points[i];
                        Arrays.sort(four);
                        StdOut.println(four[0].toString() + " -> " + four[1].toString() + " -> " + four[2].toString() + " -> " + four[3].toString()); 
                        four[0].drawTo(four[3]);
                        StdDraw.show(0);
                    } 
                    if (counter == 5) {
                        Point[] five = new Point[5];
                        five[0] = points[j];
                        five[1] = points[j - 1];
                        five[2] = points[j - 2];
                        five[3] = points[j - 3];
                        five[4] = points[i]; 
                        Arrays.sort(five); 
                        StdOut.println(five[0].toString() + " -> " + five[1].toString() + " -> " + five[2].toString() + " -> " + five[3].toString() + " -> " + five[4].toString()); 
                        five[0].drawTo(five[4]);
                        StdDraw.show(0);
                    }   
                }
                            
             /*   if (theSlope == p.slopeTo(points[i + 1]) && theSlope == p.slopeTo(points[i + 2]) && theSlope != p.slopeTo(points[i + 3])) {
                    Point[] four = new Point [4];
                    four[0] = points[i];
                    four[1] = points[i + 1];
                   
                    StdOut.println(p.toString() + " -> " + points[i].toString() + " -> " + points[i + 1].toString() + " -> " + points[i + 2].toString());
                    
                    p.drawTo(points[i]);
                    points[i].drawTo(points[i + 1]);
                    points[i + 1].drawTo(points[i + 2]);
                } else if (theSlope == p.slopeTo(points[i + 1]) && theSlope == p.slopeTo(points[i + 2]) && theSlope == p.slopeTo(points[i + 3])) {
                    StdOut.println(p.toString() + " -> " + points[i].toString() + " -> " + points[i + 1].toString() + " -> " + points[i + 2].toString() + " -> " + points[i + 3].toString());
                    p.drawTo(points[i]);
                    points[i].drawTo(points[i + 1]);
                    points[i + 1].drawTo(points[i + 2]);
                    points[i + 2].drawTo(points[i + 3]);
                } 
                
            }
            
       */
    }
} 
       
    

