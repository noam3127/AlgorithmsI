
public class Subset {
    
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rd = new RandomizedQueue();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString(); 
            rd.enqueue(item);
        }       
        for (int i = 0; i < k; i++) {
            String str = rd.dequeue();
            StdOut.println(str);
        }
       
    }
}
