
class Timer {
  public static final int max_counters=64;
  // private static final dist(:rank==1) d = [0:max_counters]->here;
  private static final dist(:rank==1) d = [0:max_counters]->place.FIRST_PLACE;
  private static final double[:rank==1] start_time   = new double[d];
  private static final double[:rank==1] elapsed_time = new double[d];
  private static final double[:rank==1] total_time   = new double[d];

  public Timer(){
    int i=0;
    while (i<max_counters) {
      start_time[i]=0;
      elapsed_time[i]=0;
      total_time[i]=0;
      i++;
    }
  }

  public void start(int n) {
    start_time[n]=System.currentTimeMillis();
  }

  public void stop(int n) {
    elapsed_time[n]=System.currentTimeMillis()-start_time[n];
    elapsed_time[n]/=1000;
    total_time[n] += elapsed_time[n];
  }

  public double readTimer(int n){
    return total_time[n];
  }

  public void resetTimer(int n){
    total_time[n]=0;
    start_time[n]=0;
    elapsed_time[n]=0;
  }

  public void resetAllTimers(){
    int i=0;
    while (i<max_counters) {
      resetTimer(i);
      i++;
    }
  }
}

class MR {
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
      //System.out.println(b[p]);
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
    finish {
    for (point(:rank==1) p: a) {
      //finish {
        final int[:rank==1] c = a;
        async(a.distribution.get(p)) {
          final int v = c[p];
          async(h) {
            result[p]=v; // Update the corresponding slot in the result array
          }
        }
      //}
    }
    }
    //finish {
    for(point(:rank==1) p:result) {
       //System.out.println(result[p]);
       total += result[p];
       // This is a totally local access, if object is here,
       // we can update the field, no need to pass
       // the non-final total field inside the async
    }
    //}
  }
}


public class MapReduce {
    public static void main(String[] args) { 
	{	
		Timer tmr = new Timer();
		int count = 0;
	    tmr.start(count);
		new MR().run(); 
	    tmr.stop(count);
		System.out.println("Wall-clock time for mapreduce: " + tmr.readTimer(count) + " secs");
	}
	}
}

