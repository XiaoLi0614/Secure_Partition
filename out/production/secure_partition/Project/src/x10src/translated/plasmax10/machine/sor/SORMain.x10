class SORMain {
	public static global def main(args: Rail[String]): Void {
{
			var tmr: Timer = new Timer();
			var count: Int = 0;
			tmr.start(count);
			new SOR().run();
			tmr.stop(count);
			Console.OUT.println((("Wall-clock time for sor: " + tmr.readTimer(count)) + " secs"));
		}	}

}

class SOR {
	private static global val JACOBI_NUM_ITER: Int = 100;
	private static global val RANDOM_SEED: Long = 10101010;
	public global var R: Random;

	public def this() {
		R = new Random(RANDOM_SEED);
	}


	public global def run(): Void {
		validate(kernel());
	}

	public global def kernel(): Double {
		var G: Array[Double]{(self.rank == 2)} = RandomMatrix(10, 10, R);
		return SORrun(1.25, G, JACOBI_NUM_ITER);
	}

	private global def RandomMatrix(val M: Int, val N: Int, val R: Random): Array[Double]{(self.rank == 2)} {
		val d: Dist{(self.rank == 2)} = blockStar(Region.make([(0..(M - 1))]), Region.make([(0..(N - 1))]));
		val t: Array[Double]{(self.rank == 2)} = (Array.make[Double](d));
		for (p: Point{(self.rank == 2)} in t.region) {
			write(t, p, (R.nextDouble() * 1e-6));
		}
		return t;
	}

	private global def write(val t: Array[Double]{(self.rank == 2)}, val p: Point{(self.rank == 2)}, val v: Double): Void {
		finish 		async (t.dist(p)) {
			t(p) = v;
		}

	}

	private global def blockStar(val r1: Region{(self.rank == 1)}, val r2: Region{(self.rank == 1)}): Dist{(self.rank == 2)} {
		val d1: Dist{(self.rank == 1)} = (Dist.makeBlock(r1) as Dist{(self.rank == 1)});
		return distTimesRegion(d1, r2);
	}

	private global def distTimesRegion(val d: Dist{(self.rank == 1)}, val r: Region{(self.rank == 1)}): Dist{(self.rank == 2)} {
		var d0: Dist{(self.rank == 2)} = (((Region.makeEmpty(2) as Region{(self.rank == 2)}))->here);
		for (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			var p: Place = Dist.makeUnique()(pl);
			d0 = (d0 || ((Region.make([(((d | p)).region as Region{(self.rank == 1)}), r])->p)));
		}
		return d0;
	}

	public global def validate(val gtotal: Double): Void {
		var dev: Double = Math.abs((gtotal - 4.5185971433257635E-5));
		if ((dev > 1.0e-12)) {
			Console.OUT.println("Validation failed");
			Console.OUT.println(((("gtotal = " + gtotal) + "  ") + dev));
			throw new RuntimeException("Validation failed");
		}
	}

	public global def read(val G: Array[Double]{(self.rank == 2)}, val pt: Point{(self.rank == 2)}): Double {
		val r: Region{(self.rank == 1)} = Region.make([(0..0)]);
		val d: Dist{(self.rank == 1)} = (r->here);
		val result: Array[Double]{(self.rank == 1)} = (Array.make[Double](d));
		val phere: Place = here;
		finish 		async (G.dist(pt)) {
			val val: Double = G(pt);
			async (phere) {
				result(0) = val;
			}
		}

		return result(0);
	}

	public global def SORrun(val omega: Double, val G: Array[Double]{(self.rank == 2)}, val num_iterations: Int): Double {
		val M: Int = G.dist.region.projection(0).size();
		val N: Int = G.dist.region.projection(1).size();
		val omega_over_four: Double = (omega * 0.25);
		val one_minus_omega: Double = (1.0 - omega);
		val Mm1: Int = (M - 1);
		val Nm1: Int = (N - 1);
		for (po: Point{(self.rank == 2)} in Region.make([(0..(num_iterations - 1)), (0..1)])) {
			finish 			foreach (pt: Point{(self.rank == 2)} in Region.make([(0..((((((Mm1 - 1)) - ((1 + po(1))))) / 2))), (1..(Nm1 - 1))])) {
				val ij: Point{(self.rank == 2)} = ((pt * Point.make([2, 1])) + Point.make([(1 + po(1)), 0]));
				finish 				async (G.dist(ij)) {
					G(ij) = ((omega_over_four * ((((read(G, (ij + Point.make([-1, 0]))) + read(G, (ij + Point.make([1, 0])))) + G((ij + Point.make([0, -1])))) + G((ij + Point.make([0, 1])))))) + (one_minus_omega * G(ij)));
				}

			}

		}
		return COMPILER_INSERTED_LIB.sum(G);
	}

}

class Random {
	private global var seed: Long;
	private static global val multiplier: Long = 0x5DEECE66DL;
	private static global val addend: Long = 0xBL;
	private static global val mask: Long = (((1L << 48)) - 1);

	public def this(val rseed: Long) {
		setSeed(rseed);
	}


	public proto global def setSeed(val rseed: Long): Void {
		seed = (((rseed ^ multiplier)) & mask);
	}

	private global def nextbits(val bits: Int): Int {
		var oldseed: Long = seed;
		var nextseed: Long = ((((oldseed * multiplier) + addend)) & mask);
		seed = nextseed;
		return (((nextseed >>> ((48 - bits)))) as Int);
	}

	public global def nextInt(): Int {
		return nextbits(32);
	}

	public global def nextLong(): Long {
		return (((((nextbits(32)) as Long) << 32)) + nextbits(32));
	}

	public global def nextBoolean(): Boolean {
		return (nextbits(1) != 0);
	}

	public global def nextDouble(): Double {
		return (((((((nextbits(26)) as Long) << 27)) + nextbits(27))) / (((1L << 53)) as Double));
	}

}

class Timer {
	public static global val max_counters: Int = 64;
	private global var start_time: Array[Double]{(self.rank == 1)};
	private global var elapsed_time: Array[Double]{(self.rank == 1)};
	private global var total_time: Array[Double]{(self.rank == 1)};

	public def this() {
		val d: Dist{(self.rank == 1)} = (Region.make([(0..max_counters)])->Place.FIRST_PLACE);
		start_time = (Array.make[Double](d, (p:Point{(self.rank == 1)}) => {
			return 0.0;
		}));
		elapsed_time = (Array.make[Double](d, (p:Point{(self.rank == 1)}) => {
			return 0.0;
		}));
		total_time = (Array.make[Double](d, (p:Point{(self.rank == 1)}) => {
			return 0.0;
		}));
	}


	public global def start(val n: Int): Void {
		start_time(n) = System.currentTimeMillis();
	}

	public global def stop(val n: Int): Void {
		elapsed_time(n) = (System.currentTimeMillis() - start_time(n));
		elapsed_time(n) = (elapsed_time(n) / 1000);
		total_time(n) = (total_time(n) + elapsed_time(n));
	}

	public global def readTimer(val n: Int): Double {
		return total_time(n);
	}

	public global def resetTimer(val n: Int): Void {
		total_time(n) = 0;
		start_time(n) = 0;
		elapsed_time(n) = 0;
	}

	public global def resetAllTimers(): Void {
		var i: Int = 0;
		while ((i < max_counters)){
			resetTimer(i);
			i++;
		}
	}

}


class COMPILER_INSERTED_LIB {

	public static def coord(region: Region, pointIndex: Int, coordIndex: Int): Int {
		val iterator = (region.iterator() as (Iterator[Point]!));
		var i: Int = 0;
		while (iterator.hasNext()) {
			val point = iterator.next();
			if (i == pointIndex)
				return point(coordIndex);
			i = i + 1;
		}
		return -1;
	}

	public static def ordinal(region: Region, point: Point): Int {
		val iterator = (region.iterator() as (Iterator[Point]!));
		var i: Int = 0;
		while (iterator.hasNext()) {
			val p = iterator.next();
			if (point.equals(p))
				return i;
			i = i + 1;
		}
		return -1;
	}

	private static def traverseAll(array: Array[Int], fun: (Int, Int) => (Int)): Int {
		val places = array.dist.places();
		var region: Region(1) = Region.makeEmpty(1);
		for (place in places) {
			//region = region + [place.id];
			//region = region + Point.make(place.id);
			// "Region +" does not work in this implementation!
			region = region.union(Region.make(place.id, place.id));
		}
		val centralArray = Array.make[Int](region, (p:Point(1)) => 0);
		val center = here;
		finish {
			for (place in places) {
				async (place) {
					val localPart = array | here;
					var result: Int = 0;
					for (point in array.region)
						result = fun(result, array(point));
					val finalResult = result;
					async (center) {
						centralArray(Point.make(here.id)) = finalResult;
					}
				}
			}
		}
		var result: Int = 0;
		for (point in centralArray.region)
			result += centralArray(point);
		return result;
	}
	private static def traverseAll(array: Array[Double], fun: (Double, Double) => (Double)): Double {
		val places = array.dist.places();
		var region: Region(1) = Region.makeEmpty(1);
		for (place in places) {
			//region = region + [place.id];
			//region = region + Point.make(place.id);
			// "Region +" does not work in this implementation!
			region = region.union(Region.make(place.id, place.id));
		}
		val centralArray = Array.make[Double](region, (p:Point(1)) => 0.0);
		val center = here;
		finish {
			for (place in places) {
				async (place) {
					val localPart = array | here;
					var result: Double = 0;
					for (point in array.region)
						result = fun(result, array(point));
					val finalResult = result;
					async (center) {
						centralArray(Point.make(here.id)) = finalResult;
					}
				}
			}
		}
		var result: Double = 0;
		for (point in centralArray.region)
			result += centralArray(point);
		return result;
	}

	public static def sum(array: Array[Int]): Int {
		return traverseAll(array, (i1: Int, i2: Int) => i1 + i2);
	}
	public static def sum(array: Array[Double]): Double {
		return traverseAll(array, (i1: Double, i2: Double) => i1 + i2);
	}
	public static def max(array: Array[Int]): Int {
		return traverseAll(array, (i1: Int, i2: Int) => Math.max(i1,i2));
	}
	public static def max(array: Array[Double]): Double {
		return traverseAll(array, (i1: Double, i2: Double) => Math.max(i1,i2));
	}
}