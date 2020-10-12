public class MR1 {
  public static final region(:rank==1) r = [0:300];
  public static final dist(:rank==1) d = (dist(:rank==1))dist.factory.block(r);

  public int[:rank==1] a;
  public int total;

  public MR() { 
     a = new int[d](point(:rank==1) [i]) { return i; };
     total = 0;
  }

  public void run() {
    map(); reduce(); System.out.println(total);
  }
  public void map() {
    final int[:rank==1] b = a;
    finish ateach (point(:rank==1) p: b) { 
      b[p]=f(b[p]);
    }
  }
  public int f(int x) { return x*x; }
  public void reduce() {
    final place h = here;
    final region(:rank==1) reg = a.region;
    final dist(:rank==1) dis = reg->here;
    final int[:rank==1] result = new int[dis]; 
              // Create an final array having a "slot" reserved 
              // for the result of each iteration
    for (point(:rank==1) p: a) {
      finish {
        final int[:rank==1] c = a;
        async(a.distribution.get(p)) { 
          final int v = c[p];
          async(h) { 
            result[p]=v; // Update the corresponding slot in the result array
          }
        }
      }
    }
	
    for(point(:rank==1) p:result) {
       total += result[p]; 
       // This is a totally local access, if object is here, 
       // we can update the field, no need to pass 
       // the non-final total field inside the async
    }  
  }
}
