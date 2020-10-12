
class Timer {
  public const max_counters: Int = 64;
  private const d: Dist(1) = [0..max_counters]->Place.FIRST_PLACE;

  private const start_time: Rail[Double]! = Rail.makeVar[Double](max_counters);
  private const elapsed_time: Rail[Double]! = Rail.makeVar[Double](max_counters);
  private const total_time: Rail[Double]! = Rail.makeVar[Double](max_counters);
  //private const start_time: Rail[Double]! = Rail.makeVar[Double](max_counters, (Int) => 0.0);

  public def this() {
    var i: Int = 0;
    while (i < max_counters) {
      start_time(i) = 0;
      elapsed_time(i) = 0;
      total_time(i) = 0;
      i++;
    }
  }

  public def start(n: Int): Void {
    start_time(n) = System.currentTimeMillis();
  }

  public def stop(n: Int): Void {
    elapsed_time(n) = System.currentTimeMillis() - start_time(n);
    elapsed_time(n) /= 1000;
    total_time(n) += elapsed_time(n);
  }

  public def readTimer(n: Int): Double {
    return total_time(n);
  }

  public def resetTimer(n: Int): Void {
    total_time(n) = 0;
    start_time(n) = 0;
    elapsed_time(n) = 0;
  }

  public def resetAllTimers(): Void {
    var i: Int = 0;
    while (i < max_counters) {
      resetTimer(i);
      i++;
    }
  }
}




class MR {
  public const r = 0..300;
  public const d = Dist.makeBlock(r);

  public var a: Array[Int](1);
  public var total: Int;

  public def this() {
     a = Array.makeVar[Int](d, ((i):Point) => i);
     total = 0;
  }

  public def run(): Void {
    map(); reduce(); Console.OUT.println(total);
  }

  public def map(): Void {
    val b = a;
    finish ateach (p in b) {
      b(p) = f(b(p));
      //Console.OUT.println(b(p));
    }
  }

  public def f(x: Int): Int { return x*x; }

  public def reduce(): Void {
    val h = here;
    val reg = a.region;
    val dis = reg -> here;
    val result = Array.makeVar[Int](dis);
    // Create a final array having a "slot" reserved
    // for the result of each iteration
    finish {
    foreach (p in a) {
      //finish {
        val c = a;
        async(a.dist(p)) {
          val v = c(p as Point(1));
          async(h) {
            result(p(0)) = v; // Update the corresponding slot in the result array
          }
        }
      //}
    }
    }
    finish {
    foreach (p in result) {
       //Console.OUT.println(result(p));
       total += result(p);
       // This is a totally local access, if object is here,
       // we can update the field, no need to pass
       // the non-final total field inside the async
    }
    }
  }
}


public class MapReduce {
    public static def main(Rail[String])  {
        val tmr = new Timer();
		val count = 0;
	    tmr.start(count);
		new MR().run();
	    tmr.stop(count);
		Console.OUT.println("Wall-clock time for mapreduce: " + tmr.readTimer(count) + " secs");
	}
}


