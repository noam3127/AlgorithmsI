
import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private int N;
    private Item[] q;
  
    public RandomizedQueue() {
        q = (Item[]) new Object[10];
        N = 0;
    } 
    public boolean isEmpty() {
        return N == 0;
    }
    public int size() {
         return N; 
    } 
    private void resize(int cap) {
        //assert capacity >= N;
              
       Item[] temp = (Item[]) new Object[cap];
       for (int i = 0; i < N; i++) {
           temp[i] = q[i];         
       }
       q = temp;  
       
    }
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException("Can't enqueue a null item");
        N++;   
        if (N == q.length) {       
            resize(N * 2);
        }
        q[N - 1] = item;           
        //System.out.println(N);
        
    }
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("No such element!");
        int rand = StdRandom.uniform(N);
        Item it = q[rand];
        q[rand] = q[N - 1];
        q[N - 1] = null;
        N--;
        if (N > 0 && q.length >= 10 && N <= q.length / 4) resize(N * 2);
        return it;
    } 
   
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("No such element!");
        int rand = StdRandom.uniform(N);
        return q[rand];
    }
    public Iterator<Item> iterator() {
        return new TheIterator();       
    } 
    
    private class TheIterator implements Iterator<Item> {
        private int i;
        private Item[] shuf;

        public TheIterator() {
            i = 0;
            shuf = (Item[]) new Object[N];
            for (int n = 0; n < N; n++) {
                shuf[n] = q[n];
            }
            StdRandom.shuffle(shuf);         
        }

        public boolean hasNext() {
            return i < N;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("There is no next element!");
            return shuf[i++];
        }
    }
   /* public static void main(String[] args) {
       RandomizedQueue rq = new RandomizedQueue();
       for (int i = 0; i < 100; i++){
           rq.enqueue(i);       
           System.out.println(i + ",");
       }
       int counter = 0;
       Iterator<Integer> it = rq.iterator();
       while (it.hasNext()){
           int s = it.next();
           //StdOut.println(s);
          counter++;
       }
        System.out.println(counter);
       for (int i = 0; i < 60; i++){
           System.out.println(i + ": " + rq.dequeue());
       }
        for (int i = 0; i < 50; i++){
           rq.enqueue(i);
           System.out.print(i + ",");
       }
       for(int i = 0; i < 35; i++){
           System.out.println(i + ": " + rq.dequeue());
           
       }      
       Iterator<Integer> it = rq.iterator();
       while (it.hasNext()){
           int s = it.next();
           StdOut.println(s);
       }
    }   */   
}

/*

public class RandomizedQueue<Item> implements Iterable<Item> {
   public RandomizedQueue()                 // construct an empty randomized queue
   public boolean isEmpty()                 // is the queue empty?
   public int size()                        // return the number of items on the queue
   public void enqueue(Item item)           // add the item
   public Item dequeue()                    // delete and return a random item
   public Item sample()                     // return (but do not delete) a random item
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   public static void main(String[] args)   // unit testing
}
*/