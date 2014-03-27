
/**
 *
 * @author noamlustiger
 * 
 
 */
public class PercolationStats {
    private double mean;
    private int T;
    private int N;
    private int[]thresholds;
    private double[]threshPercent;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;
    private int thresholdCounter;
    double percent;
    Percolation perc;
    
    public PercolationStats(int N, int T){
        if(N <= 0 || T <= 0) throw new IllegalArgumentException("Both numbers must be greater than 0."); 
        this.T = T;
        this.N = N;
        thresholds = new int[T];
        threshPercent = new double[T];
        for(int i = 0;i < T;i++){ 
            perc = new Percolation(N); 
            thresholdCounter = 0;
            int randI;
            int randJ; 
            while(!perc.percolates()){
               randI = StdRandom.uniform(N) + 1;
               randJ = StdRandom.uniform(N) + 1;
               if(!perc.isOpen(randI,randJ)){
                  perc.open(randI, randJ);
                  thresholdCounter++;
               }                
            }            
            thresholds[i] = thresholdCounter;
            threshPercent[i] = (double) thresholdCounter / (N * N);           
        }        
        System.out.println("MEAN: " + mean());
        System.out.println("high: " + confidenceHi());
        System.out.println("low: " + confidenceLo());
    }
    public double mean(){
        mean = StdStats.mean(threshPercent);
        return mean;
    }    
    public double stddev(){
        return StdStats.stddev(threshPercent);
    }
    public double confidenceLo(){
        confidenceLo = mean() - ((1.96 * stddev() / Math.sqrt(T)));
        return confidenceLo;
    }
    public double confidenceHi(){
        confidenceHi = mean() + ((1.96 * stddev() / Math.sqrt(T)));
        return confidenceHi;
    }
    
    public static void main(String[] args){
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        new PercolationStats(N, T);
    }
}
/* public class PercolationStats {
   public PercolationStats(int N, int T)    // perform T independent computational experiments on an N-by-N grid
   public double mean()                     // sample mean of percolation threshold
   public double stddev()                   // sample standard deviation of percolation threshold
   public double confidenceLo()             // returns lower bound of the 95% confidence interval
   public double confidenceHi()             // returns upper bound of the 95% confidence interval
   public static void main(String[] args)   // test client, described below
}*/