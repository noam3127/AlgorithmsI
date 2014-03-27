
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
       
    private int N;
    private Node first = null;
    private Node last = null;
    
    public Deque() {
       N = 0;
    }
    
    private class Node {
        private Item item;
        private Node next;
        private Node previous;
               
    }
    public boolean isEmpty() {       
        return first == null;
    }  
    public int size() {
        return N;
    }
  
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException("Can't add a null item to the beginning");
        N++;
        Node newFirst = new Node();
        if (!isEmpty()) {
            
            Node oldFirst = first;
            newFirst.next = oldFirst;
            oldFirst.previous = newFirst;          
        } 
        first = newFirst;        
        first.item = item; 
        first.previous = null;
        if (N == 1) last = first;
    }
    public void addLast(Item item) { 
        if (item == null) throw new NullPointerException("Can't add a null item to the end");
        N++;
        Node newLast = new Node();
        if (!isEmpty()) {
            Node oldLast = last;
            newLast.previous = oldLast;
            oldLast.next = newLast;
        } 
        last = newLast;           
        last.item = item; 
        last.next = null;
        if (N == 1) first = last;
    }
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("There's no first item");
        N--;
        Item oldFirst = first.item;
        if (N != 0) {
            first = first.next;
            first.previous = null;
            if (N == 1) last = first;
        } else {
            first = null;
            last = null;
        }
        return oldFirst;
    } 
    
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("There's no last item");
        N--;
        Item oldLast = last.item;
        if (N != 0) {
            last = last.previous;
            last.next = null;
            if (N == 1) first = last;
        } else {
            first = null;
            last = null;
        }
 
        return oldLast;
    } 

    public Iterator<Item> iterator() {
        return new TheIterator();
    }
    
    private class TheIterator implements Iterator<Item> {
  
        private Node current = first;
        
        public TheIterator() {
           
        }
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("No more items in iteration."); 
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); 
        }
    }
    
   /* public static void main (String[] args) {
        Deque<String> d = new Deque();
        for(int i = 0; i < 100; i++){
            d.addLast("#" + i);
            
        }
        
      String f1 = d.removeFirst();
        System.out.println("first: " + f1);
        String f2 = d.removeFirst();
        System.out.println("first: " + f2);
     
        String l1 = d.removeLast();
        System.out.println("last: " + l1);
        String l2 = d.removeLast();
        System.out.println("last: " + l2);
        System.out.println("size: " + d.size());
        int n = d.size();
        for(int i = 0; i < n; i++){
            System.out.print(d.size() + " - " );
            String st = d.removeFirst();
            System.out.println(st);
           
        }
 
    }*/

    
    
}
/*public class Deque<Item> implements Iterable<Item> {
   public Deque()                           // construct an empty deque
   public boolean isEmpty()                 // is the deque empty?
   public int size()                        // return the number of items on the deque
   public void addFirst(Item item)          // insert the item at the front
   public void addLast(Item item)           // insert the item at the end
   public Item removeFirst()                // delete and return the item at the front
   public Item removeLast()                 // delete and return the item at the end
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   public static void main(String[] args)   // unit testing
}
*/