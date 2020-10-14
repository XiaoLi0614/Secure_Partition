
import x10.compiler.*;

class SeriesMain {

	public static def main(args: Array[String]) {
{
			var tmr: Timer = new Timer();
			var count: Int = 0;
			finish 
				tmr.start(count);

			finish 
				new Series().run();

			tmr.stop(count);
			Console.OUT.println((("Wall-clock time for series: " + tmr.readTimer(count)) + " secs"));
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

class Series {
	private var setsize: Int;
	public var array_rows: Int;
	public var testArray: DistArray[Double]{(self.rank == 2)};

	public def this() {
	}


	public def setSize(val setsize: Int) {
		this.setsize = setsize;
	}

	public def initialise(val n: Int) {
		array_rows = n;
		buildTestData();
	}

	public def kernel() {
		Do();
	}

	public def initRefArray(val ref: DistArray[Double]{(self.rank == 2)}) {
		ref(0, 0) = 2.8729524964837996;
		ref(0, 1) = 0.0;
		ref(1, 0) = 1.1161046676147888;
		ref(1, 1) = -1.8819691893398025;
		ref(2, 0) = 0.34429060398168704;
		ref(2, 1) = -1.1645642623320958;
		ref(3, 0) = 0.15238898702519288;
		ref(3, 1) = -0.8143461113044298;
	}

	public def validate() {
		val r <: Region{(self.rank == 2)} = (0..3) * (0..1);
		val vd <: Dist{(self.rank == 2)} = (r->here);
		val ref <: DistArray[Double]{(self.rank == 2)} = (DistArray.make[Double](vd, (p0:Point{(self.rank == 2)}) => {
			return 0.0;
		}));
		initRefArray(ref);
		for ([i, j]: Point{(self.rank == 2)} in vd) {
			val ii <: Int = i;
			val jj <: Int = j;
			var cval: Double = 0;
			val curr_place <: Place = here;
			val tempr <: Region{(self.rank == 1)} = (0..0);
			val tempvd <: Dist{(self.rank == 1)} = (tempr->here);
			val tempref <: DistArray[Double]{(self.rank == 1)} = (DistArray.make[Double](tempvd, (p0:Point{(self.rank == 1)}) => {
				return 0.0;
			}));
			val tA <: DistArray[Double]{(self.rank == 2)} = testArray;
			finish 			at (testArray.dist(j, i)) async {
				val temp <: Double = tA(jj, ii);
				at (curr_place) async 
					tempref(0) = temp;

			}

			cval = tempref(0);
			var error: Double = Math.abs((cval - ref(i, j)));
			if ((error > 1.0e-12)) {
{
					Console.OUT.println(((("Validation failed for coefficient " + j) + ",") + i));
					Console.OUT.println(("Computed value = " + cval));
					Console.OUT.println(("Reference value = " + ref(i, j)));
					throw new RuntimeException("Validation failed");
				}			}
		}
	}

	public def tidyup() {
		freeTestData();
	}

	public def run() {
		val tempregion <: Region{(self.rank == 1)} = (0..0);
		val tempdist <: Dist{(self.rank == 1)} = (tempregion->here);
		var j: Int = 10000;
		for (i: Point{(self.rank == 1)} in tempdist) {
			setSize(0);
			initialise((((j * Math.pow(100, 0))) as Int));
			kernel();
			validate();
		}
		tidyup();
	}

	public def buildTestData() {
		val R <: Region{(self.rank == 2)} = (0..1) * (0..(array_rows - 1));
		val d <: Dist{(self.rank == 2)} = (Dist.makeBlock(R) as Dist{(self.rank == 2)});
		testArray = ((DistArray.make[Double](d)) as DistArray[Double]{(self.rank == 2)});
	}

	public def Do() {
		testArray(0, 0) = (TrapezoidIntegrate((0.0 as Double), (2.0 as Double), 1000, (0.0 as Double), 0) / (2.0 as Double));
		val omega <: Double = (3.1415926535897932 as Double);
		val U <: Dist{(self.rank == 1)} = (Dist.makeUnique() as Dist{(self.rank == 1)});
		val tA <: DistArray[Double]{(self.rank == 2)} = testArray;
		finish 
			ateach (p: Point{(self.rank == 1)} in U) {
				var ilow: Int = 0;
				if (U(p).isFirst()) {

						ilow = 1;
				}				else 
					ilow = 0;
				for (pp: Point{(self.rank == 2)} in (tA.dist | here)) 
					if ((pp(1) >= ilow)) {

							if ((pp(0) == 0)) {

									tA(pp) = TrapezoidIntegrate((0.0 as Double), (2.0 as Double), 1000, (omega * (pp(1) as Double)), 1);
							}							else 
								tA(pp) = TrapezoidIntegrate((0.0 as Double), (2.0 as Double), 1000, (omega * (pp(1) as Double)), 2);
					}			}

	}

	private def TrapezoidIntegrate(val x0: Double, val x1: Double, val a_nsteps: Int, val omegan: Double, val select: Int): Double {
		var x: Double = 0.0;
		var dx: Double = 0.0;
		var rvalue: Double = 0.0;
		var nsteps: Int = a_nsteps;
		x = x0;
		dx = (((x1 - x0)) / (nsteps as Double));
		rvalue = (thefunction(x0, omegan, select) / (2.0 as Double));
		if ((nsteps != 1)) {
{
				nsteps--;
				nsteps--;
				while ((nsteps > 0)) {
					x = (x + dx);
					rvalue = (rvalue + thefunction(x, omegan, select));
					nsteps--;
				}
			}		}
		rvalue = (((rvalue + (thefunction(x1, omegan, select) / (2.0 as Double)))) * dx);
		return (rvalue);
	}

	private def thefunction(val x: Double, val omegan: Double, val select: Int): Double {
		switch (select) {
			case 0:
				return (Math.pow((x + (1.0 as Double)), x));
			case 1:
				return ((Math.pow((x + (1.0 as Double)), x) * Math.cos((omegan * x))));
			case 2:
				return ((Math.pow((x + (1.0 as Double)), x) * Math.sin((omegan * x))));
		}
		return (0.0);
	}

	public def freeTestData() {
	}

}

class Y {
	public val x: Int;

	public def this() {
		this.x = 10;
	}

}

class X {
	public val y: Y;

	public def this() {
		this.y = new Y();
	}

}

