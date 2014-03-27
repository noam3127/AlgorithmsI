/**
 *
 * @author Noam Lustiger
 */
public class Percolation {
  
   private boolean[][] openSites;
   private int N;
   private int i;
   private int j;
   private int single;
   private int[] singleDim;
   private WeightedQuickUnionUF quick;
   private int counter = 0;

   public Percolation(int N){
       this.N = N;
       singleDim = new int[(N + 1) * (N + 1)];      
       openSites = new boolean[N + 1][N + 1]; 
       quick = new WeightedQuickUnionUF((N + 1) * (N + 1));    
       
       for(i = 0;i <= N;i++){
           for(j = 0; j <= N;j++){                    
               openSites[i][j] = false;            
             // System.out.println(i + ","+j+ " = " + xyTo1D(i,j));
           } 
       }       
       //connect all top sites "virtually"
       for(i = N + 1; i < N * 2; i++){
           quick.union(i, i + 1);
       }
       //connect all bottom sites "virtually"
       for(i = N * N + N; i > (N * N) + 1 ; i--){
           quick.union(i, i - 1);
       }    
   }
   
   private int xyTo1D(int i, int j){
       if(i == 0 || j == 0) return 0;
       single = i * N + j;
       return single;
   }
   
   public void open(int i, int j){
       if (i <= 0 || i > N) throw new IndexOutOfBoundsException("index i out of bounds");
       if (j <= 0 || j > N) throw new IndexOutOfBoundsException("index j out of bounds");             
       openSites[i][j] = true;        
       int singleD = xyTo1D(i,j);
      // System.out.println(singleD);
       if(j > 1 && openSites[i][j - 1])quick.union(singleD,xyTo1D(i,j - 1)); 
       if(j < N - 1 && openSites[i][j + 1])quick.union(singleD,xyTo1D(i,j + 1)); 
       if(i > 0 && openSites[i - 1][j])quick.union(singleD,xyTo1D(i - 1,j)); 
       if(i < N - 1 && openSites[i + 1][j])quick.union(singleD,xyTo1D(i + 1,j));
   }
   
   public boolean isOpen(int i, int j){      
       return openSites[i][j];
   }
   
   public boolean isFull(int i, int j){
       int singleD = xyTo1D(i,j);
       return quick.connected(singleD, 1);
   }
   
   public boolean percolates(){
       return quick.connected(N + 1, (N * N) + N);    
   }
  // public static void main(String[] args){
    //   new Percolation(20);       
   //}  
}
