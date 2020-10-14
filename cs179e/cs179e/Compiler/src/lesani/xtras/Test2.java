package lesani.xtras;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class A {}
class B extends A {}
class C extends A {}


public class Test2 {

   static ArrayList<String> method1(ArrayList<String> s) {
      return null;
   }

   static ArrayList method2(ArrayList s) {
      return null;
   }

//   <T> T badCast(T t, Object o) {
//      return (T) o;
//   }

   public static void main(String[] args) {
      @SuppressWarnings("unchecked")
      ArrayList a1 = method1(new ArrayList());

      @SuppressWarnings("unchecked")
      ArrayList<String> a2 = method2(new ArrayList<String>());

      Collection cs = new ArrayList<String>();
      // Illegal.
      if (cs instanceof Collection) {  }

      // Unchecked warning,
      @SuppressWarnings("unchecked")
      Collection<String> cstr = cs;

      B[] bs = new B[10];
      Object[] as = bs;

      as[1] = new B();

      B b = bs[1];

      @SuppressWarnings("unchecked")
      List<String>[] array = (List<String>[]) Array.newInstance(ArrayList.class, 10);


      class SourceCell<T extends A> {
         private T t;
         public SourceCell(T t) {
            this.t = t;
         }
         public T get() {
            return t;
         }
      }

      SourceCell<B> cellB = new SourceCell<B>(new B());
      //SourceCell<A> cellA = cellB;     // Step 1
      // The method set can be called.
      //cellA.set(new C());            // Step 2
      // But, the method get can be called.
      B bb = cellB.get();               // Step 3

   }
}




