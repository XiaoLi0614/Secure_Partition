package lesani.collection.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PIterator<E> implements Iterator<E> {
    private Iterator<E> iterator;
    private E peek;

    public PIterator(Iterator<E> iterator) {
        // initialize any member here.
        this.iterator  = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public E peek() {
        if (peek == null) peek = iterator.next();
        return peek;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    public E next() {
        if (peek != null) {
            E res = peek;
            peek = null;
            return res;
        } else return iterator.next();
    }

   public void remove() {
      iterator.remove();
   }

    public boolean hasNext() {
        if (peek != null) return true;
        else return iterator.hasNext();
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c"));
        PIterator<String> it = new PIterator<String>(list.iterator());
        System.out.println(it.peek());
    }
}