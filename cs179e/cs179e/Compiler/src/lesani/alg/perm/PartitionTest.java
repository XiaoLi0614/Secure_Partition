package lesani.alg.perm;

import java.util.Arrays;


public class PartitionTest {
   public static void main(String[] args) {
      Partition par;
      System.out.println("------");
      par = new Partition(3, 3);
      while (par.hasNext()) {
         int[] next = par.next();
         System.out.println(Arrays.toString(next));
      }

      System.out.println("------");
      par = new Partition(4, 3);
      while (par.hasNext()) {
         int[] next = par.next();
         System.out.println(Arrays.toString(next));
      }

      System.out.println("------");
      par = new Partition(2, 4);
      while (par.hasNext()) {
         int[] next = par.next();
         System.out.println(Arrays.toString(next));
      }

      System.out.println("------");
      par = new Partition(0, 4);
      while (par.hasNext()) {
         int[] next = par.next();
         System.out.println(Arrays.toString(next));
      }

      System.out.println("------");
      par = new Partition(1, 4);
      while (par.hasNext()) {
         int[] next = par.next();
         System.out.println(Arrays.toString(next));
      }

      System.out.println("------");
      par = new Partition(5, 1);
      while (par.hasNext()) {
         int[] next = par.next();
         System.out.println(Arrays.toString(next));
      }

      NZeroPartition npar;
      System.out.println("------");
      npar = new NZeroPartition(3, 3);
      while (npar.hasNext()) {
         int[] next = npar.next();
         System.out.println(Arrays.toString(next));
      }

      System.out.println("------");
      npar = new NZeroPartition(4, 3);
      while (npar.hasNext()) {
         int[] next = npar.next();
         System.out.println(Arrays.toString(next));
      }

   }
}


