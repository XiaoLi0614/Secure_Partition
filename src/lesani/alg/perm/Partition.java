package lesani.alg.perm;


import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Partition implements Iterator<int[]> {
   private int[] array;
   private int[] sent;

   private boolean hn = true;

   public Partition(int sum, int size) {
      this.array = new int[size];
      this.sent = new int[size];
      for (int i = 0; i < array.length; i++) {
         array[i] = 0;
         sent[i] = 0;
      }
      array[0] = sum;
      if (sum < 0)
         hn = false;
   }


   public boolean hasNext() {
      return hn;
   }

   public int[] next() {
      if (!hn)
         throw new NoSuchElementException();
      int[] r = array.clone();
      int i;
      for (i = r.length - 2; i >= 0 && array[i] == 0; i--);
      if (i == -1)
         hn = false;
      else {
//         System.out.println(i);
//         System.out.println("B " + Arrays.toString(array));
         array[i] = array[i] - 1;
//         System.out.println("N1 " + Arrays.toString(array));
         sent[i] = sent[i] + 1;
         array[i+1] = sent[i]; // incremented.
//         System.out.println("N2 " + Arrays.toString(array));
         sent[i+1] = 0;
         for (int j = i + 2; j < array.length; j++) {
            array[j] = 0;
            sent[j] = 0;
         }
//         System.out.println("A " + Arrays.toString(array));
      }

      return r;
   }


   public void remove() {
      throw new UnsupportedOperationException();
   }
}



