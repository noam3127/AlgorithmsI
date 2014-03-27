
/**
 *
 * @author Noam Lustiger
 * 
 
 */
public class PercolationStats {
    private double mean;
    private int T;
    private double[]threshPercent;   
    private int thresholdCounter;
    
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException("Both numbers must be greater than 0."); 
        this.T = T;
        threshPercent = new double[T];
        
        for (int i = 0; i < T; i++) { 
            Percolation perc = new Percolation(N); 
            thresholdCounter = 0;
            int randI;
            int randJ; 
           // System.out.println(perc.percolates());
            while (!perc.percolates()) {
               randI = StdRandom.uniform(N) + 1;
               randJ = StdRandom.uniform(N) + 1;
               if (!perc.isOpen(randI, randJ)) {
                  perc.open(randI, randJ);
                  thresholdCounter++;
               } 
               //This can be used as a visualizer from the command-line
             /* 
               if(perc.percolates()){
                   
                for(int j = 1; j <= N; j++){
                    for(int n = 1; n <= N; n++){
                        char c;
                        if(perc.isFull(j, n)) c = 'O';
                        else if(perc.isOpen(j, n)) c = ' ';
                        else c = 'X';
                      System.out.print("[" + c + "]");
                     
                    }
                    System.out.println();
                }
                System.out.println("\n\n");               
            }*/      
            threshPercent[i] = (double) thresholdCounter / (N * N);           
            }  
        }
       /* System.out.println("MEAN: " + mean());
        System.out.println("high: " + confidenceHi());
        System.out.println("low: " + confidenceLo()); */
    }
    public double mean() {
        mean = StdStats.mean(threshPercent);
        return mean;
    }    
    public double stddev() {
        return StdStats.stddev(threshPercent);
    }
    public double confidenceLo() {
        double confidenceLo = mean() - ((1.96 * stddev() / Math.sqrt(T)));
        return confidenceLo;
    }
    public double confidenceHi() {
        double confidenceHi = mean() + ((1.96 * stddev() / Math.sqrt(T)));
        return confidenceHi;
    }
    
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        new PercolationStats(N, T);
    }
}
