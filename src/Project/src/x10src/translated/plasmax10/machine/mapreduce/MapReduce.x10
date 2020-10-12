
import x10.compiler.*;

class Timer {
	public static val max_counters: Int = 64;
	private var start_time: DistArray[Double]{(self.rank == 1)};
	private var elapsed_time: DistArray[Double]{(self.rank == 1)};
	private var total_time: DistArray[Double]{(self.rank == 1)};

	public def this() {
		val d <: Dist{(self.rank == 1)} = ((0..max_counters)->Place.FIRST_PLACE);
		start_time = (DistArray.make[Double](d, (p:Point{(self.rank == 1)}) => {
			return 0.0;
		}));
		elapsed_time = (DistArray.make[Double](d, (p:Point{(self.rank == 1)}) => {
			return 0.0;
		}));
		total_time = (DistArray.make[Double](d, (p:Point{(self.rank == 1)}) => {
			return 0.0;
		}));
	}


	public def start(val n: Int) {
		start_time(n) = System.currentTimeMillis();
	}

	public def stop(val n: Int) {
		elapsed_time(n) = (System.currentTimeMillis() - start_time(n));
		elapsed_time(n) = (elapsed_time(n) / 1000);
		total_time(n) = (total_time(n) + elapsed_time(n));
	}

	public def readTimer(val n: Int): Double {
		return total_time(n);
	}

	public def resetTimer(val n: Int) {
		total_time(n) = 0;
		start_time(n) = 0;
		elapsed_time(n) = 0;
	}

	public def resetAllTimers() {
		var i: Int = 0;
		while ((i < max_counters)) {
			resetTimer(i);
			i++;
		}
	}

}

class MapReduce {

	public static def main(args: Array[String]) {
{
			var tmr: Timer = new Timer();
			var count: Int = 0;

				tmr.start(count);

				new MR().run();
			tmr.stop(count);
			Console.OUT.println((("Wall-clock time for mapreduce: " + tmr.readTimer(count)) + " secs"));
		}	}

}

class MR {
	public static val r: Region{(self.rank == 1)} = (0..300000);
	public var a: DistArray[Int]{(self.rank == 1)};
	public var total: Int;

	public def this() {
		val d <: Dist{(self.rank == 1)} = (Dist.makeBlock(r) as Dist{(self.rank == 1)});
		a = (DistArray.make[Int](d, (p:Point{(self.rank == 1)}) => {
			return p(0);
		}));
		total = 0;
	}


	public def run() {
		map();
		reduce();
		Console.OUT.println(total);
	}

	public def map() {
		val b <: DistArray[Int]{(self.rank == 1)} = a;
		for (p: Point{(self.rank == 1)} in b) 
			b(p) = f(b(p));
	}

	public def f(val x: Int): Int {
		return (x * x);
	}

	public def reduce() {
		val h <: Place = here;
		val reg <: Region{(self.rank == 1)} = a.region;
		val dis <: Dist{(self.rank == 1)} = (reg->here);
		val result <: DistArray[Int]{(self.rank == 1)} = (DistArray.make[Int](dis));
		for (p: Point{(self.rank == 1)} in a) 
{
				val c <: DistArray[Int]{(self.rank == 1)} = a;
{
					val v <: Int = c(p);

						result(p) = v;
				}			}		for (p: Point{(self.rank == 1)} in result) 
			total = (total + result(p));
	}

}

