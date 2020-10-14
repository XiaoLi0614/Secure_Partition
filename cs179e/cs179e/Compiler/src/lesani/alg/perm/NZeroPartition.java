package lesani.alg.perm;


import java.util.Arrays;
import java.util.Iterator;


public class NZeroPartition implements Iterator<int[]> {
   private Partition par;

   public NZeroPartition(int sum, int size) {
//      if (sum < size)
//         throw new RuntimeException("Sum should be larger than size.");
      par = new Partition(sum - size, size);
   }


   public boolean hasNext() {
      return par.hasNext();
   }

   public int[] next() {
      int[] r = par.next().clone();
      for (int i = 0; i < r.length; i++)
         r[i] = r[i] + 1;
      return r;
   }

   public void remove() {
      throw new UnsupportedOperationException();
   }
}



