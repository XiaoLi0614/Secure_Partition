package lesani.xtras.simpprog;

import java.util.HashSet;

public class Test2 {

   // Print all three matched parentheses.
   static HashSet<String> strings = new HashSet<String>();
   public static void makeStrings(int i, int j, String s) {
      if (i==3 && j==3)
         strings.add(s);
      if (i<3)
         makeStrings(i+1, j, s+"(");
      if (j<3 && i>j)
         makeStrings(i, j+1, s+")");
   }


   public static void main(String[] args) {
      makeStrings(0, 0, "");
      System.out.println(strings);
   }

}


