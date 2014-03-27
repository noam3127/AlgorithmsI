/**
 *
 * @author Noam Lustiger
 */
public class Percolation  {
  
   private boolean[][] openSites;
   private int N;  
   private int single;
   private WeightedQuickUnionUF quick;
   private WeightedQuickUnionUF fullWQUF;
   private int openCounter = 0;
  
   public Percolation(int N) {
       this.N = N;
       openSites = new boolean[N + 1][N + 1]; 
       quick = new WeightedQuickUnionUF((N + 1) * (N + 1));  
       fullWQUF = new WeightedQuickUnionUF((N + 1) * (N + 1));
       for (int i = 0; i <= N; i++) {
           for (int j = 0; j <= N; j++) {                    
               openSites[i][j] = false;            
             // System.out.println(i + ","+j+ " = " + xyTo1D(i,j));
           } 
       }       
       //connect all top sites "virtually"
       for (int i = N + 1; i < N * 2; i++) {
           quick.union(i, i + 1);
           fullWQUF.union(i, i + 1);
       }
       //connect all bottom sites "virtually"
       for (int i = N * N + N; i > (N * N) + 1; i--) {
           quick.union(i, i - 1);
       }  
   }
   
   private int xyTo1D(int i, int j) {
       if (i == 0 || j == 0) return 0;
       single = i * N + j;
       return single;
   }
   
   public void open(int i, int j) {
       if (i <= 0 || i > N) throw new IndexOutOfBoundsException("index i out of bounds");
       if (j <= 0 || j > N) throw new IndexOutOfBoundsException("index j out of bounds");             
       openSites[i][j] = true;
       openCounter++;
       
       int singleD = xyTo1D(i, j);
       if (j > 1 && openSites[i][j - 1]) {
           quick.union(singleD, xyTo1D(i, j - 1));
           fullWQUF.union(singleD, xyTo1D(i, j - 1));
       } 
       if (j < N && openSites[i][j + 1]) {
           quick.union(singleD, xyTo1D(i, j + 1));
           fullWQUF.union(singleD, xyTo1D(i, j + 1));
       } 
       if (i > 0 && openSites[i - 1][j]) {
           quick.union(singleD, xyTo1D(i - 1, j));
           fullWQUF.union(singleD, xyTo1D(i - 1, j));
       } 
       if (i < N  && openSites[i + 1][j]) {
           quick.union(singleD, xyTo1D(i + 1, j));
           fullWQUF.union(singleD, xyTo1D(i + 1, j));
       }   
   }
   
   public boolean isOpen(int i, int j) {  
       if (i <= 0 || i > N) throw new IndexOutOfBoundsException("index i out of bounds");
       if (j <= 0 || j > N) throw new IndexOutOfBoundsException("index j out of bounds");  
       return openSites[i][j];
   }
   
   public boolean isFull(int i, int j) {
       if (i <= 0 || i > N) throw new IndexOutOfBoundsException("index i out of bounds");
       if (j <= 0 || j > N) throw new IndexOutOfBoundsException("index j out of bounds");  
       int singleD = xyTo1D(i, j);      
       return openSites[i][j] && fullWQUF.connected(N + 1, singleD);
   }
   
   public boolean percolates() {
       if(N == 1 && openCounter == 1) return true; 
       return quick.connected(N + 1, (N * N) + N) && openCounter > 1;   
       
   }
  /* public static void main(String[] args){
       new Percolation(20);       
   }  */
}
