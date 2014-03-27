
public class Board {
     private int N; 
     private final int[][] blocks;
     private short manhattanNumber = 0;
     private short row;
     private short col;
     
     public Board(int[][] blocks){
         N = blocks.length;
         this.blocks = new int[N][N];
         for (int i = 0; i < N; i++){
             System.arraycopy(blocks[i], 0, this.blocks[i], 0, N);
         }       
          for (int i = 0; i < N; i++) {
              for (int j = 0; j < N; j++) {
                  int theBlock = blocks[i][j];
                  if (theBlock == 0) {
                      row = (short)i;
                      col = (short)j;
                  }
                  else if (theBlock != to1D(i, j)) { 
                     int dif = Math.abs(theBlock - to1D(i, j));
                     
                     int targetX = (theBlock - 1) / N; // expected x-coordinate (row)
                     int targetY = (theBlock - 1) % N;
                     int dist = Math.abs(i - targetX) + Math.abs(j - targetY);
                     manhattanNumber += dist;
                  }
              }
          }
       //   System.out.println(this.toString() + "man num for above: " + manhattanNumber + "\n");
     }
      public int dimension() { 
          return N;
      }   
      public int hamming() {
          short hammingNumber = 0;
           for (int i = 0; i < N; i++) {
              for (int j = 0; j < N; j++) {
                  if (blocks[i][j] == 0) continue;
                  if (blocks[i][j] != i * N + (j + 1)) {
                      hammingNumber++;
                  }
              }
          }
          return hammingNumber;
      }
      private int to1D(int i, int j) {
          return i * N + (j + 1);
      }
      public int manhattan() { 
          return manhattanNumber;
      }
     
      public boolean isGoal() {
         // System.out.println("ham num = " + hamming());
          return manhattanNumber == 0;
      }
      public Board twin() {
          //int[][] twin = blocks;
          int[][] twin = new int[N][N];
              for (int i = 0; i < N; i++) {
                   System.arraycopy(blocks[i], 0, twin[i], 0, N);
              }
          if (twin[0][0] != 0 && twin[0][1] != 0) {
              int temp = twin[0][0];
              twin[0][0] = twin[0][1];
              twin[0][1] = temp;
          } else {
              int temp = twin[1][0];
              twin[1][0] = twin[1][1];
              twin[1][1] = temp;
          }
          Board twinBoard = new Board(twin);
          return twinBoard;
      }
   
     @Override
      public boolean equals(Object y) {
          Board x = null;
          if (!(y instanceof Board)) return false;
          else {
              x = (Board) y;
          }
          if (N != x.N) return false;
          if (row != x.row || col != x.col) return false;
          for (int i = 0; i < N; i++) {
              for (int j = 0; j < N; j++) {
                  if (x.blocks[i][j] != this.blocks[i][j]) return false;
              }
           }
         
          return true;//(this.row == x.row && this.col == x.col);
      }
      public Iterable<Board> neighbors() {
          Stack<Board> stack = new Stack<Board>();
          
          if (row > 0) {
            
              int[][] neighbor = new int[N][N];
              for (int i = 0; i < N; i++) {
                   System.arraycopy(blocks[i], 0, neighbor[i], 0, N);
              }
              neighbor[row][col] = neighbor[row - 1][col];
              neighbor[row - 1][col] = 0;
              Board nBoard = new Board(neighbor);
              stack.push(nBoard);
               
          }
          if (row < N - 1) {
              int[][] neighbor = new int[N][N];
              for (int i = 0; i < N; i++) {
                   System.arraycopy(blocks[i], 0, neighbor[i], 0, N);
              }
           
             neighbor[row][col] = neighbor[row + 1][col];
             neighbor[row + 1][col] = 0; 
             Board rBoard = new Board(neighbor);
            
             stack.push(rBoard);
                // System.out.println("\tthis swap\n\t" + rBoard.toString());
          }
          if (col > 0) {
             int[][] neighbor = new int[N][N];
             for (int i = 0; i < N; i++) {
                   System.arraycopy(blocks[i], 0, neighbor[i], 0, N);
              }
             neighbor[row][col] = neighbor[row][col - 1];
             neighbor[row][col - 1] = 0;
             Board nBoard = new Board(neighbor);
             
             stack.push(nBoard); 
                // System.out.println("\t" + nBoard.toString());
          }
          if (col < N - 1) {
             int[][] neighbor = new int[N][N];
             for (int i = 0; i < N; i++) {
                   System.arraycopy(blocks[i], 0, neighbor[i], 0, N);
              }
             neighbor[row][col] = neighbor[row][col + 1];
             neighbor[row][col + 1] = 0;  
             Board nBoard = new Board(neighbor);
             
             stack.push(nBoard); 
                 //System.out.println("\t" + nBoard.toString());
          }
          return stack;
      }
      
      public String toString() {
         StringBuilder s = new StringBuilder();
         s.append(N + "\n");
         for (int i = 0; i < N; i++) {
             for (int j = 0; j < N; j++) {
                 s.append(String.format("%2d ", blocks[i][j]));
             }
             s.append("\n");
         }
        return s.toString();
      }
    
      
}
/*
public class Board {
    public Board(int[][] blocks)           // construct a board from an N-by-N array of blocks
                                           // (where blocks[i][j] = block in row i, column j)
    public int dimension()                 // board dimension N
    public int hamming()                   // number of blocks out of place
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    public boolean isGoal()                // is this board the goal board?
    public Board twin()                    // a board obtained by exchanging two adjacent blocks in the same row
    public boolean equals(Object y)        // does this board equal y?
    public Iterable<Board> neighbors()     // all neighboring boards
    public String toString()               // string representation of the board (in the output format specified below)
}
*/