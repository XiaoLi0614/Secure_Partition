
package lesani.collection.iterator;

import java.util.*;


public class CartProdIterator<E> implements Iterator<List<E>> {
   private ArrayList<Set<E>> ls;
   private ArrayList<PIterator<E>> iters;
   private ArrayList<E> next = null;


   public CartProdIterator(List<Set<E>> ls) {
      this.ls = new ArrayList<Set<E>>(ls);
      iters = new ArrayList<PIterator<E>>(ls.size());

      for (int i = 0; i < ls.size(); i++)
          iters.add(new PIterator<E>(ls.get(i).iterator()));

      peekNext();
   }

   private boolean moveNext() {
      int i = ls.size() - 1;
      while (i >= 0) {
         PIterator<E> it = iters.get(i);
         if (it.hasNext())
            it.next();
         else
            return false;

         if (it.hasNext()) {
            break;
         } else {
            iters.set(i, new PIterator<E>(ls.get(i).iterator()));
            i -= 1;
         }
      }
      return i != -1;
   }

   private void peekNext() {
      next = new ArrayList<E>(ls.size());
      for (int j = 0; j < ls.size(); j++) {
         PIterator<E> it = iters.get(j);
         if (!it.hasNext()) {
            next = null;
            return;
         }
         next.add(it.peek());
      }
   }

   private void prepareNext() {
      if (next == null) {
         if (!moveNext())
            return;
         peekNext();
      }
   }

   public boolean hasNext() {
      prepareNext();
      return (next != null);

//      for (int i = iters.size() - 1; i >= 0 ; i--) {
//          if (iters.get(i).hasNext())
//             return true;
//      }
   }



   public List<E> next() {
      prepareNext();
      if (next == null)
         throw new NoSuchElementException();

      List<E> l = next;
      next = null;

      return l;
   }


   public void remove() {
      throw new UnsupportedOperationException();
   }


   public static <E> Iterator<List<E>> iterator(List<Set<E>> ls) {
      return new CartProdIterator<E>(ls);
   }

   public static void main(String[] args) {
      HashSet<String> s1 = new HashSet<String>(Arrays.asList("a", "b", "c"));
      HashSet<String> s2 = new HashSet<String>(Arrays.asList("1", "2", "3", "4"));

//      HashSet<String> s1 = new HashSet<String>(Arrays.asList("a"));
//      HashSet<String> s2 = new HashSet<String>(Arrays.asList("1", "2", "3", "4"));

//      HashSet<String> s1 = new HashSet<String>(Arrays.asList("a", "b", "c"));
//      HashSet<String> s2 = new HashSet<String>(Arrays.asList("1"));

//      HashSet<String> s1 = new HashSet<String>(Arrays.asList("a", "b", "c"));
//      HashSet<String> s2 = new HashSet<String>();

//      HashSet<String> s1 = new HashSet<String>();
//      HashSet<String> s2 = new HashSet<String>(Arrays.asList("1", "2", "3", "4"));

      ArrayList<Set<String>> a = new ArrayList<Set<String>>(Arrays.asList(s1, s2));
      CartProdIterator<String> it = new CartProdIterator<String>(a);
      while (it.hasNext()) {
         List<String> next = it.next();
         System.out.println(next);
      }
   }


}



