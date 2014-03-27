
import java.util.Comparator;


public class Solver {
  /*  private MinPQ<SearchNode> pq = new MinPQ<SearchNode>(new Comparator<SearchNode>({
*/
    private MinPQ<SearchNode> pq = new MinPQ<SearchNode>(new Comparator<SearchNode>() {

        @Override
       public int compare(SearchNode o1, SearchNode o2) {
            if (o1.priority < o2.priority) return -1;
            else if (o1.priority > o2.priority) return 1;
            else if(o1.board.manhattan() < o2.board.manhattan())return -1;
            else return 0;
                    
        }
    });
  
      /*  @Override
        public int compare(SearchNode o1, SearchNode o2) {
            if (o1.priority < o2.priority) return -1;
            if (o1.priority > o2.priority) return 1;
            else return 0;
        }
    });*/
    private MinPQ<SearchNode> twinPq = new MinPQ<SearchNode>(new Comparator<SearchNode>() {

        @Override
       public int compare(SearchNode o1, SearchNode o2) {
            if (o1.priority < o2.priority) return -1;
            else if (o1.priority > o2.priority) return 1;
            else if(o1.board.manhattan() < o2.board.manhattan())return -1;
            else return 0;
                    
        }
    });
    private boolean solvable = true;
    private SearchNode tDequeued = null;
    private SearchNode dequeued = null;
    public Solver(Board initial) {  
      // MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
         Board twin = initial.twin();
         SearchNode twinNode = new SearchNode(twin, -1, null);
         twinPq.insert(twinNode);
         SearchNode node = new SearchNode(initial, -1, null);
         pq.insert(node);
         
         while (solvable) {  
            tryTwin();
            if (!solvable) break;
            //if (solvable) {
                dequeued = pq.delMin();
                Board db = dequeued.board;    
                if (db.isGoal()) {
                    solvable = true;
                  //  System.out.println("size = " + pq.size());
                    break;
                }  
               
                for (Board nb : db.neighbors()) {  
                   SearchNode nsn = new SearchNode(nb, dequeued.myMoves + 1, dequeued);
                   if (dequeued.previous == null) {
                        pq.insert(nsn);
                   }
                   else if (!nb.equals(dequeued.previous.board)) {
                        pq.insert(nsn);
                   } 
                
                }
            // } 
         }
    }
    private void tryTwin() {
        
        tDequeued = twinPq.delMin();
        Board db = tDequeued.board;
        if (db.isGoal()) {
            solvable = false;
           // System.out.println("size = " + twinPq.size());
            return;
        }
        for (Board nb : db.neighbors()) {
            SearchNode tsn = new SearchNode(nb, tDequeued.myMoves + 1, tDequeued);
             if (tDequeued.previous == null) twinPq.insert(tsn); 
             else if (!nb.equals(tDequeued.previous.board)) {
                twinPq.insert(tsn); 
            }              
        }         
    }
    public boolean isSolvable() {
        return solvable;
    }
      private class SearchNode {// implements Comparable<SearchNode> {
          private Board board;
          private SearchNode previous;
          private int myMoves;
          private int priority;
          
          private SearchNode(Board b, int myMoves, SearchNode previous) {            
              this.board = b;
              this.myMoves = myMoves;
              this.previous = previous;  
              this.priority = b.manhattan() + myMoves;
          }
         
    /*    @Override
        public int compareTo(SearchNode s) {
            return (board.manhattan() + myMoves) - (s.board.manhattan() + s.myMoves);
        }*/
      }
      
    public int moves() {
        if (!isSolvable()) return -1;
        return dequeued.myMoves + 1;
    }
    public Iterable<Board> solution() {
        if (!solvable) return null;
        Stack<Board> stack = new Stack<Board>();
        
        Board boardSol; 
        SearchNode sol = dequeued;
       // Board[] stack = new Board[sol.myMoves + 1];
        while (sol != null) { 
            boardSol = sol.board;
            stack.push(boardSol);
            sol = sol.previous;
                                 
         }
        return stack;
    }
    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
       // System.out.println(initial.toString());
     
        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }

        
    }
}
/*

public class Solver {
    public Solver(Board initial)            // find a solution to the initial board (using the A* algorithm)
    public boolean isSolvable()             // is the initial board solvable?
    public int moves()                      // min number of moves to solve initial board; -1 if no solution
    public Iterable<Board> solution()       // sequence of boards in a shortest solution; null if no solution
    public static void main(String[] args)  // solve a slider puzzle (given below)
}

*/