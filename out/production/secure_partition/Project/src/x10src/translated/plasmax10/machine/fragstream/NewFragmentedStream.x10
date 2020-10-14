
import x10.compiler.*;

class NewFragmentedStream {

	public static def main(args: Array[String]) {
{
			var tmr: Timer = new Timer();
			var count: Int = 0;

				tmr.start(count);

				new FragmentedStream().run();
			tmr.stop(count);
			Console.OUT.println((("Wall-clock time for stream: " + tmr.readTimer(count)) + " secs"));
		}	}

}

class FragmentedStreamData {
	public val verified: DistArray[Boolean]{(self.rank == 1)};
	public val D: Dist{(self.rank == 1)};
	public val times: DistArray[Double]{(self.rank == 1)};
	public val a: DistArray[Double]{(self.rank == 1)};
	public val b: DistArray[Double]{(self.rank == 1)};
	public val c: DistArray[Double]{(self.rank == 1)};

	public def this(val N: Long, val NUM_TIMES: Int) {
		val dverified <: Dist{(self.rank == 1)} = ((0..0)->here);
		verified = initVerified(dverified);
		val tempD <: Dist{(self.rank == 1)} = (Dist.makeBlock((0..(((N - 1)) as Int))) as Dist{(self.rank == 1)});
		D = tempD;
		times = initArray(((0..(((NUM_TIMES - 1)) as Int))->here));
		a = initArray(tempD);
		b = initArray(tempD);
		c = initArray(tempD);
	}


	@NonEscaping final
	public def initVerified(val d: Dist{(self.rank == 1)}): DistArray[Boolean]{(self.rank == 1)} {
		return ((DistArray.make[Boolean](d, (p:Point{(self.rank == 1)}) => {
			return true;
		})) as DistArray[Boolean]{(self.rank == 1)});
	}

	@NonEscaping final
	public def initArray(val d: Dist{(self.rank == 1)}): DistArray[Double]{(self.rank == 1)} {
		return ((DistArray.make[Double](d, (p:Point{(self.rank == 1)}) => {
			return 0.0D;
		})) as DistArray[Double]{(self.rank == 1)});
	}

}

class FragmentedStream {
	public static val MEG: Int = (50 * 50);
	public static val alpha: Double = 3.0D;
	public static val NUM_TIMES: Int = 10;
	public static val N0: Long = (2 * MEG);
	public static val N: Long = (N0 * Place.MAX_PLACES);
	public static val LocalSize: Int = (N0 as Int);
	public val sd: FragmentedStreamData;

	public def this() {
		sd = new FragmentedStreamData(N, NUM_TIMES);
	}


	public def run() {
		Console.OUT.println(("LocalSize=" + LocalSize));
		val tempSd <: FragmentedStreamData = this.sd;
{
			var r: Region{(self.rank == 1)} = (0..(((NUM_TIMES - 1)) as Int));
			for (p1: Point{(self.rank == 1)} in r) {
				for (p6: Point{(self.rank == 1)} in Dist.makeUnique()) {
					for (i: Point{(self.rank == 1)} in ((tempSd.D) | here)) {
						tempSd.b(i) = (1.5 * (((here.id * LocalSize) + i(0))));
						tempSd.c(i) = (2.5 * (((here.id * LocalSize) + i(0))));
					}
					if ((Dist.makeUnique()(p6) == Place.FIRST_PLACE)) {
						tempSd.times(p1) = -mySecond();
					}					for (p4: Point{(self.rank == 1)} in ((tempSd.D) | here)) 					tempSd.a(p4) = (tempSd.b(p4) + (alpha * tempSd.c(p4)));
				}
				for (p5: Point{(self.rank == 1)} in Dist.makeUnique()) 
					if ((Dist.makeUnique()(p5) == Place.FIRST_PLACE)) {
						tempSd.times(p1) = (tempSd.times(p1) + mySecond());
					}			}
			for (p2: Point{(self.rank == 1)} in Dist.makeUnique()) 
				for (p3: Point{(self.rank == 1)} in ((tempSd.D) | here)) 				if ((tempSd.a(p3) != (tempSd.b(p3) + (alpha * tempSd.c(p3))))) {

						tempSd.verified(0) = false;
				}		}		var mini: Double = 10000000L;
		var r: Region{(self.rank == 1)} = (0..(((NUM_TIMES - 1)) as Int));
		for (p1: Point{(self.rank == 1)} in r) 		if ((tempSd.times(p1) < mini)) {
			mini = tempSd.times(p1);
		}		printStats(N, mini, tempSd.verified(0));
	}

	public static def mySecond(): Double {
		return (((((((System.currentTimeMillis() * 1.e6) / 1000)) as Double) * 1.e-6)) as Double);
	}

	public static def printStats(val N: Long, val time: Double, val verified: Boolean) {
		Console.OUT.println(("Number of places=" + Place.MAX_PLACES));
		var size1: Long = ((((3 * 8) * N) / MEG));
		var rate: Double = ((((3 * 8) * N)) / ((1.0E9 * time)));
		Console.OUT.println((((("Size of arrays: " + size1) + " MB (total)") + (size1 / Place.MAX_PLACES)) + " MB (per place)"));
		Console.OUT.println((((("Min time: " + time) + " rate=") + rate) + " GB/s"));
		Console.OUT.println(("Result is " + (verified?"verified.":"NOT verified.")));
	}

}

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

