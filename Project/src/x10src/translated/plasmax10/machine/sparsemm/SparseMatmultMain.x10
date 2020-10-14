
import x10.compiler.*;

class Random {
	private var seed: Long;
	private static val multiplier: Long = 0x5DEECE66DL;
	private static val addend: Long = 0xBL;
	private static val mask: Long = (((1L << 48)) - 1);

	public def this(val rseed: Long) {
		setSeed(rseed);
	}


	@NonEscaping final
	public def setSeed(val rseed: Long) {
		seed = (((rseed ^ multiplier)) & mask);
	}

	private def nextbits(val bits: Int): Int {
		var oldseed: Long = seed;
		var nextseed: Long = ((((oldseed * multiplier) + addend)) & mask);
		seed = nextseed;
		return (((nextseed >>> ((48 - bits)))) as Int);
	}

	public def nextInt(): Int {
		return nextbits(32);
	}

	public def nextLong(): Long {
		return (((((nextbits(32)) as Long) << 32)) + nextbits(32));
	}

	public def nextBoolean(): Boolean {
		return (nextbits(1) != 0);
	}

	public def nextDouble(): Double {
		return (((((((nextbits(26)) as Long) << 27)) + nextbits(27))) / (((1L << 53)) as Double));
	}

}

class SparseMatmultMain {

	public static def main(args: Array[String]) {
{
			var tmr: Timer = new Timer();
			var count: Int = 0;

				tmr.start(count);

				new SparseMatmult().run(0);
			tmr.stop(count);
			Console.OUT.println((("Wall-clock time for sparsemm: " + tmr.readTimer(count)) + " secs"));
		}	}

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

class SparseMatmult {
	public var nthreads: Int;
	private var setSize: Int;
	private static val RANDOM_SEED: Long = 10101010;
	private static val SPARSE_NUM_ITER: Int = 200;
	private static val R: Random = new Random(RANDOM_SEED);
	private var x: DistArray[Double]{(self.rank == 1)};
	private var theValue: DistArray[Double]{(self.rank == 1)};
	private var col: DistArray[Int]{(self.rank == 1)};
	private var row: DistArray[Int]{(self.rank == 1)};
	private var lowsum: DistArray[Int]{(self.rank == 1)};
	private var highsum: DistArray[Int]{(self.rank == 1)};
	private var y: DistArray[Double]{(self.rank == 1)};
	public var ytotal: Double;

	public def this() {
		this.nthreads = Place.MAX_PLACES;
	}


	private def initDataSizes_M(val datasizesM: DistArray[Int]{(self.rank == 1)}) {
		datasizesM(0) = 100;
		datasizesM(1) = 100000;
		datasizesM(2) = 500000;
	}

	private def initDataSizes_N(val datasizesN: DistArray[Int]{(self.rank == 1)}) {
		datasizesN(0) = 100;
		datasizesN(1) = 100000;
		datasizesN(2) = 500000;
	}

	private def initDataSizes_NZ(val datasizesNZ: DistArray[Int]{(self.rank == 1)}) {
		datasizesNZ(0) = 500;
		datasizesNZ(1) = 500000;
		datasizesNZ(2) = 2500000;
	}

	public def JGFsetsize(val setSize: Int) {
		this.setSize = setSize;
	}

	public def JGFinitialise() {
		val ar <: Region{(self.rank == 1)} = (0..2);
		val dr <: Dist{(self.rank == 1)} = (ar->here);
		val datasizes_M <: DistArray[Int]{(self.rank == 1)} = (DistArray.make[Int](dr, (p:Point{(self.rank == 1)}) => {
			return 0;
		}));
		val datasizes_N <: DistArray[Int]{(self.rank == 1)} = (DistArray.make[Int](dr, (p:Point{(self.rank == 1)}) => {
			return 0;
		}));
		val datasizes_nz <: DistArray[Int]{(self.rank == 1)} = (DistArray.make[Int](dr, (p:Point{(self.rank == 1)}) => {
			return 0;
		}));
		initDataSizes_M(datasizes_M);
		initDataSizes_N(datasizes_N);
		initDataSizes_NZ(datasizes_nz);
		val ds_N <: Int = datasizes_N(setSize);
		val ds_M <: Int = datasizes_M(setSize);
		val ds_nz <: Int = datasizes_nz(setSize);
		val r_nz <: Region{(self.rank == 1)} = (0..(ds_nz - 1));
		val r_nthreads <: Region{(self.rank == 1)} = (0..(nthreads - 1));
		val d_M <: Dist{(self.rank == 1)} = (Dist.makeBlock((0..(ds_M - 1))) as Dist{(self.rank == 1)});
		val r_nthreadsPlus1 <: Region{(self.rank == 1)} = (0..nthreads);
		val ds_NReg <: Region{(self.rank == 1)} = (0..(ds_N - 1));
		val ds_NRegDist <: Dist{(self.rank == 1)} = (ds_NReg->here);
		val xin <: DistArray[Double]{(self.rank == 1)} = (init(((DistArray.make[Double](ds_NRegDist)) as DistArray[Double]{(self.rank == 1)}), R) as DistArray[Double]{(self.rank == 1)});
		val xinreg <: Region{(self.rank == 1)} = (0..(xin.dist.region.size() - 1));
		val xinregDist <: Dist{(self.rank == 1)} = (xinreg->here);
		x = ((DistArray.make[Double](xinreg-> here, (p:Point{(self.rank == 1)}) => {
			return xin(p);
		})) as DistArray[Double]{(self.rank == 1)});
		y = (DistArray.make[Double](d_M));
		val r_nthreadsregDist <: Dist{(self.rank == 1)} = (r_nthreads->here);
		var ilow: DistArray[Int]{(self.rank == 1)} = ((DistArray.make[Int](r_nthreadsregDist)) as DistArray[Int]{(self.rank == 1)});
		var iup: DistArray[Int]{(self.rank == 1)} = ((DistArray.make[Int](r_nthreadsregDist)) as DistArray[Int]{(self.rank == 1)});
		val r_nthreadsplus1regDist <: Dist{(self.rank == 1)} = (r_nthreadsPlus1->here);
		val dsnzDist <: Dist{(self.rank == 1)} = (r_nz->here);
		val rowt <: DistArray[Int]{(self.rank == 1)} = (DistArray.make[Int](dsnzDist));
		val colt <: DistArray[Int]{(self.rank == 1)} = (DistArray.make[Int](dsnzDist));
		val valt <: DistArray[Double]{(self.rank == 1)} = (DistArray.make[Double](dsnzDist));
		var sumT: DistArray[Int]{(self.rank == 1)} = (DistArray.make[Int](r_nthreadsplus1regDist));
		var sect: Int = ((((ds_M + nthreads) - 1)) / nthreads);
		var rowin: DistArray[Int]{(self.rank == 1)} = (DistArray.make[Int](dsnzDist));
		var colin: DistArray[Int]{(self.rank == 1)} = (DistArray.make[Int](dsnzDist));
		var valin: DistArray[Double]{(self.rank == 1)} = (DistArray.make[Double](dsnzDist));
		val lowsumin <: DistArray[Int]{(self.rank == 1)} = ((DistArray.make[Int](r_nthreadsregDist)) as DistArray[Int]{(self.rank == 1)});
		val highsumin <: DistArray[Int]{(self.rank == 1)} = ((DistArray.make[Int](r_nthreadsregDist)) as DistArray[Int]{(self.rank == 1)});
		for (p: Point{(self.rank == 1)} in (0..(ds_nz - 1))) {
			rowin(p) = (Math.abs(R.nextInt()) % ds_M);
			colin(p) = (Math.abs(R.nextInt()) % ds_N);
			valin(p) = R.nextDouble();
		}
		for (i: Point{(self.rank == 1)} in r_nthreads) {
			ilow(i) = (i(0) * sect);
			iup(i) = (((((i(0) + 1)) * sect)) - 1);
			if ((iup(i) > ds_M)) {
				iup(i) = ds_M;
			}		}
		for (i: Point{(self.rank == 1)} in r_nz) 
			for (j: Point{(self.rank == 1)} in r_nthreads) 
				if ((((rowin(i) >= ilow(j))) && ((rowin(i) <= iup(j))))) {

						sumT((j + Point.make([1]))) = (sumT((j + Point.make([1]))) + 1);
				}		for ([j]: Point{(self.rank == 1)} in r_nthreads) 
			for ([i]: Point{(self.rank == 1)} in (0..j)) {
				lowsumin(j) = (lowsumin(j) + sumT((j - i)));
				highsumin(j) = (highsumin(j) + sumT((j - i)));
			}
		for (i1: Point{(self.rank == 1)} in r_nz) 
			for (j1: Point{(self.rank == 1)} in r_nthreads) 
				if ((((rowin(i1) >= ilow(j1))) && ((rowin(i1) <= iup(j1))))) {
{
						rowt(highsumin(j1)) = rowin(i1);
						colt(highsumin(j1)) = colin(i1);
						valt(highsumin(j1)) = valin(i1);
						highsumin(j1) = (highsumin(j1) + 1);
					}				}
		val rowtregion <: Region{(self.rank == 1)} = (0..(rowt.dist.region.size() - 1));
		val rowtdist <: Dist{(self.rank == 1)} = (rowtregion->here);
		row = ((DistArray.make[Int](rowtregion-> here, (i:Point{(self.rank == 1)}) => {
			return rowt(i);
		})) as DistArray[Int]{(self.rank == 1)});
		val coltregion <: Region{(self.rank == 1)} = (0..(colt.dist.region.size() - 1));
		val coltdist <: Dist{(self.rank == 1)} = (coltregion->here);
		col = ((DistArray.make[Int](coltregion-> here, (i:Point{(self.rank == 1)}) => {
			return colt(i);
		})) as DistArray[Int]{(self.rank == 1)});
		val valtregion <: Region{(self.rank == 1)} = (0..(valt.dist.region.size() - 1));
		val valtdist <: Dist{(self.rank == 1)} = (valtregion->here);
		theValue = ((DistArray.make[Double](valtregion-> here, (i:Point{(self.rank == 1)}) => {
			return valt(i);
		})) as DistArray[Double]{(self.rank == 1)});
		val lowsuminregion <: Region{(self.rank == 1)} = (0..(lowsumin.dist.region.size() - 1));
		val lowsumindist <: Dist{(self.rank == 1)} = (lowsuminregion->here);
		lowsum = ((DistArray.make[Int](lowsuminregion-> here, (i:Point{(self.rank == 1)}) => {
			return lowsumin(i);
		})) as DistArray[Int]{(self.rank == 1)});
		val highsuminregion <: Region{(self.rank == 1)} = (0..(highsumin.dist.region.size() - 1));
		val highsumindist <: Dist{(self.rank == 1)} = (highsuminregion->here);
		highsum = ((DistArray.make[Int](highsuminregion-> here, (i:Point{(self.rank == 1)}) => {
			return highsumin(i);
		})) as DistArray[Int]{(self.rank == 1)});
	}

	public def JGFkernel() {
		test(y, theValue, row, col, x, SPARSE_NUM_ITER, lowsum, highsum);
	}

	public def initRefVal(val refval: DistArray[Double]{(self.rank == 1)}) {
		refval(0) = 0.1436496372119012;
		refval(1) = 150.0130719633895;
		refval(2) = 749.5245870753752;
	}

	public def JGFvalidate() {
		var rtemp: Region{(self.rank == 1)} = (0..2);
		var dtemp: Dist{(self.rank == 1)} = (rtemp->here);
		var refval: DistArray[Double]{(self.rank == 1)} = (DistArray.make[Double](dtemp));
		initRefVal(refval);
		var dev: Double = Math.abs((ytotal - refval(setSize)));
		if ((dev > 1.0e-10)) {
{
				Console.OUT.println("Validation failed");
				Console.OUT.println(((((("ytotal = " + ytotal) + "  ") + dev) + "  ") + setSize));
				throw new RuntimeException("Validation failed");
			}		}
	}

	public def JGFtidyup() {
	}

	public def run(val setSize: Int) {
		JGFsetsize(setSize);
		JGFinitialise();
		JGFkernel();
		JGFvalidate();
		JGFtidyup();
	}

	public static def init(val a: DistArray[Double]{(self.rank == 1)}, val R: Random): DistArray[Double]{(self.rank == 1)} {
		val areg <: Region{(self.rank == 1)} = (0..(a.dist.region.size() - 1));
		val adist <: Dist{(self.rank == 1)} = (areg->here);
		for (i: Point{(self.rank == 1)} in areg) 
			a(i) = (R.nextDouble() * 1e-6);
		return a;
	}

	public def test(val y: DistArray[Double]{(self.rank == 1)}, val theValue: DistArray[Double]{(self.rank == 1)}, val row: DistArray[Int]{(self.rank == 1)}, val col: DistArray[Int]{(self.rank == 1)}, val x: DistArray[Double]{(self.rank == 1)}, val NUM_ITERATIONS: Int, val lowsum: DistArray[Int]{(self.rank == 1)}, val highsum: DistArray[Int]{(self.rank == 1)}) {
		val nz <: Int = theValue.region.size();
		val dDistU <: Dist{(self.rank == 1)} = (Dist.makeUnique() as Dist{(self.rank == 1)});
		ytotal = 0.0;
		for (id1: Point{(self.rank == 1)} in dDistU) 		for (reps: Point{(self.rank == 1)} in (0..(NUM_ITERATIONS - 1))) 		for (i: Point{(self.rank == 1)} in (lowsum(id1)..(highsum(id1) - 1))) 
			y(row(i)) = (y(row(i)) + (x(col(i)) * theValue(i)));
		val curr_place <: Place = here;
		val tempr <: Region{(self.rank == 1)} = (0..0);
		val tempvd <: Dist{(self.rank == 1)} = (tempr->here);
		val tempref <: DistArray[Double]{(self.rank == 1)} = (DistArray.make[Double](tempvd, (p0:Point{(self.rank == 1)}) => {
			return 0.0;
		}));
		for (i: Point{(self.rank == 1)} in (0..(nz - 1))) {
{
				val temp <: Double = y(row(i));

					tempref(0) = temp;
			}			ytotal = (ytotal + tempref(0));
		}
	}

}

