
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

class Linpack {
	private var setSize: Int;
	private static val r: Region{(self.rank == 1)} = (0..2);
	private var a: DistArray[Double]{(self.rank == 2)};
	private var b: DistArray[Double]{(self.rank == 2)};
	private var x: DistArray[Double]{(self.rank == 2)};
	private var ops: Double;
	private var total: Double;
	private var norma: Double;
	private var normx: Double;
	private var resid: Double;
	private var time: Double;
	private var kf: Double;
	private var n: Int;
	private var i: Int;
	private var ntimes: Int;
	private var info: Int;
	private var lda: Int;
	private var ldaa: Int;
	private var kflops: Int;
	private var ipvt: DistArray[Int]{(self.rank == 1)};
	private var infodgefa: Int;


	public def initdataSizesArray(val datasizes: DistArray[Int]{(self.rank == 1)}) {
		datasizes(0) = 150;
		datasizes(1) = 1000;
		datasizes(2) = 2000;
	}

	public def JGFsetsize(val setSize: Int) {
		this.setSize = setSize;
	}

	public def JGFinitialise(val vd: Dist{(self.rank == 1)}) {
		val datasizes <: DistArray[Int]{(self.rank == 1)} = (DistArray.make[Int](vd, (p0:Point{(self.rank == 1)}) => {
			return 0;
		}));
		initdataSizesArray(datasizes);
		n = datasizes(setSize);
		Console.OUT.println((("ATTENTION: Running with smaller size (" + n) + " instead of 500)"));
		ldaa = n;
		lda = (ldaa + 1);
		var vectorRegion: Region{(self.rank == 1)} = (0..ldaa);
		var vectorRegionMinus1: Region{(self.rank == 1)} = (0..(ldaa - 1));
		var vectorRegionMinus1Dist: Dist{(self.rank == 1)} = (vectorRegionMinus1->here);
		var rectangularRegion: Region{(self.rank == 2)} = (0..ldaa) * (0..lda);
		var slimRegion: Region{(self.rank == 2)} = (0..0) * (0..lda);
		var slimRegionDist: Dist{(self.rank == 2)} = (slimRegion->here);
		var rectangular_distribution: Dist{(self.rank == 2)} = (Dist.makeBlock(rectangularRegion) as Dist{(self.rank == 2)});
		a = (DistArray.make[Double](rectangular_distribution));
		b = (DistArray.make[Double](slimRegionDist));
		x = (DistArray.make[Double](slimRegionDist));
		ipvt = (DistArray.make[Int](vectorRegionMinus1Dist));
		var nl: Long = (n as Long);
		ops = ((((2.0 * (((nl * nl) * nl)))) / 3.0) + (2.0 * ((nl * nl))));
		norma = matgen(a, lda, n, b);
	}

	public def JGFkernel() {
		info = dgefa(a, lda, n, ipvt);
		dgesl(a, lda, n, ipvt, b, 0);
	}

	public def JGFvalidate(val vd: Dist{(self.rank == 1)}) {
		var eps: Double = 0.0;
		var residn: Double = 0.0;
		val ref <: DistArray[Double]{(self.rank == 1)} = (DistArray.make[Double](vd, (p0:Point{(self.rank == 1)}) => {
			return 0.0;
		}));
		ref(0) = 6.0;
		ref(1) = 12.0;
		ref(2) = 20.0;
		val tempR <: Region{(self.rank == 2)} = (0..0) * (0..n);
		val tempdist <: Dist{(self.rank == 2)} = (tempR->here);
		for (pt: Point{(self.rank == 2)} in tempdist) {
			val pt0 <: Point{(self.rank == 2)} = (pt * Point.make([0, 1]));
			x(pt0) = b(pt0);
		}
		norma = matgen(a, lda, n, b);
		for (pt: Point{(self.rank == 2)} in tempdist) {
			val pt0 <: Point{(self.rank == 2)} = (pt * Point.make([0, 1]));
			b(pt0) = -b(pt0);
		}
		dmxpy(n, b, n, lda, x, a);
		resid = 0.0;
		normx = 0.0;
		for (pt: Point{(self.rank == 2)} in tempdist) {
			val pt0 <: Point{(self.rank == 2)} = (pt * Point.make([0, 1]));
			resid = ((resid > myabs(b(pt0))))?resid:myabs(b(pt0));
			normx = ((normx > myabs(x(pt0))))?normx:myabs(x(pt0));
		}
		eps = epslon((1.0 as Double));
		residn = (resid / ((((n * norma) * normx) * eps)));
		if ((residn > ref(setSize))) {
{
				Console.OUT.println("Validation failed");
				Console.OUT.println(("Computed Norm Res = " + residn));
				Console.OUT.println(("Reference Norm Res = " + ref(setSize)));
				throw new RuntimeException("Validation failed");
			}		}
	}

	public def JGFtidyup() {
	}

	public def run() {
		JGFsetsize(0);
		val vd <: Dist{(self.rank == 1)} = (r->here);
		JGFinitialise(vd);
		JGFkernel();
		JGFvalidate(vd);
		JGFtidyup();
	}

	private def read(val a: DistArray[Double]{(self.rank == 2)}, val i: Int, val j: Int): Double {
		val curr_place <: Place = here;
		val tempr <: Region{(self.rank == 1)} = (0..0);
		val tempvd <: Dist{(self.rank == 1)} = (tempr->here);
		val tempref <: DistArray[Double]{(self.rank == 1)} = (DistArray.make[Double](tempvd, (p0:Point{(self.rank == 1)}) => {
			return 0.0;
		}));
{
			val temp <: Double = a(i, j);

				tempref(0) = temp;
		}		return tempref(0);
	}

	private def write(val a: DistArray[Double]{(self.rank == 2)}, val p: Point{(self.rank == 2)}, val theValue: Double) {

			a(p) = theValue;
	}

	private def plusWrite(val a: DistArray[Double]{(self.rank == 2)}, val i: Int, val j: Int, val theValue: Double) {

			a(i, j) = (a(i, j) + theValue);
	}

	private def myabs(val d: Double): Double {
		return ((d >= 0))?d:-d;
	}

	private def matgen(val a: DistArray[Double]{(self.rank == 2)}, val lda: Int, val n: Int, val b: DistArray[Double]{(self.rank == 2)}): Double {
		var init: Int = 1325;
		var norma: Double = 0.0;
		for (p: Point{(self.rank == 2)} in a) {
			init = ((3125 * init) % 65536);
			var theValue: Double = (((init - 32768.0)) / 16384.0);

				write(a, p, theValue);
			norma = (theValue > norma)?theValue:norma;
		}
		for (p: Point{(self.rank == 2)} in b) 
			b(p) = 0.0;
		for ([i, j]: Point{(self.rank == 2)} in (0..(n - 1)) * (0..(n - 1))) 

				plusWrite(b, 0, j, a(i, j));
		return norma;
	}

	private def dgefa(val a: DistArray[Double]{(self.rank == 2)}, val lda: Int, val n: Int, val ipvt: DistArray[Int]{(self.rank == 1)}): Int {
		infodgefa = 0;
		val nm1 <: Int = (n - 1);
		if ((nm1 >= 0)) {
{
				var tempr: Region{(self.rank == 1)} = (0..(nm1 - 1));
				var temprDist: Dist{(self.rank == 1)} = (tempr->here);
				for ([k]: Point{(self.rank == 1)} in temprDist) {
					val l <: Int = (idamax((n - k), a, k, k, 1) + k);
					ipvt(k) = l;
{
						val kp1 <: Int = (k + 1);
						if ((a(k, l) != 0)) {
{
								if ((l != k)) {

										if ((l != k)) {
{
												var t: Double = a(k, l);
												a(k, l) = a(k, k);
												a(k, k) = t;
											}										}
								}								val tx <: Double = (-1.0 / a(k, k));
								dscal((n - (kp1)), tx, a, k, kp1, 1);
{
									var temprkp1: Region{(self.rank == 1)} = (kp1..(n - 1));
									var temprkp1Dist: Dist{(self.rank == 1)} = (temprkp1->here);
									for ([j]: Point{(self.rank == 1)} in temprkp1Dist) 
{
											var t: Double = a(j, l);
											if ((l != k)) {
{
													a(j, l) = a(j, k);
													a(j, k) = t;
												}											}
											daxpy((n - (kp1)), t, a, k, kp1, 1, a, j, kp1, 1);
										}								}							}						} else 
							infodgefa = k;
					}				}
			}		}
		ipvt((n - 1)) = (n - 1);
		var theValue: Double = read(a, (n - 1), (n - 1));
		if ((theValue == 0)) {

				info = (n - 1);
		}		return infodgefa;
	}

	private def dgesl(val a: DistArray[Double]{(self.rank == 2)}, val lda: Int, val n: Int, val ipvt: DistArray[Int]{(self.rank == 1)}, val b: DistArray[Double]{(self.rank == 2)}, val job: Int) {
		val nm1 <: Int = (n - 1);
		if ((job == 0)) {
{
				if ((nm1 >= 1)) {
{
						var tempr: Region{(self.rank == 1)} = (0..(nm1 - 1));
						var temprDist: Dist{(self.rank == 1)} = (tempr->here);
						for ([k]: Point{(self.rank == 1)} in temprDist) {
							val l <: Int = ipvt(k);
							var t: Double = b(0, l);
							if ((l != k)) {
{
									b(0, l) = b(0, k);
									b(0, k) = t;
								}							}
							val kp1 <: Int = (k + 1);
							daxpy((n - (kp1)), t, a, k, kp1, 1, b, 0, kp1, 1);
						}
					}				}
				var tempr: Region{(self.rank == 1)} = (0..(n - 1));
				var temprDist: Dist{(self.rank == 1)} = (tempr->here);
				for ([kb]: Point{(self.rank == 1)} in temprDist) {
					val k <: Int = (n - ((kb + 1)));
					b(0, k) = (b(0, k) / read(a, k, k));
					var t: Double = -b(0, k);
					daxpy(k, t, a, k, 0, 1, b, 0, 0, 1);
				}
			}		} else {
			var tempr: Region{(self.rank == 1)} = (0..(n - 1));
			var temprDist: Dist{(self.rank == 1)} = (tempr->here);
			for ([k]: Point{(self.rank == 1)} in temprDist) {
				var t: Double = ddot(k, a, k, 0, 1, b, 0, 0, 1);
				b(0, k) = (((b(0, k) - t)) / a(k, k));
			}
			if ((nm1 >= 1)) {
{
					var temprnm1: Region{(self.rank == 1)} = (1..(nm1 - 1));
					var temprnm1Dist: Dist{(self.rank == 1)} = (temprnm1->here);
					for ([kb]: Point{(self.rank == 1)} in temprnm1Dist) {
						val k <: Int = (n - ((kb + 1)));
						val kp1 <: Int = (k + 1);
						b(0, k) = (b(0, k) + ddot((n - (kp1)), a, k, kp1, 1, b, 0, kp1, 1));
						val l <: Int = ipvt(k);
						if ((l != k)) {
{
								var t: Double = b(0, l);
								b(0, l) = b(0, k);
								b(0, k) = t;
							}						}
					}
				}			}
		}
	}

	private def daxpy(val n: Int, val da: Double, val dx: DistArray[Double]{(self.rank == 2)}, val dxCol: Int, val dx_off: Int, val incx: Int, val dy: DistArray[Double]{(self.rank == 2)}, val dyCol: Int, val dy_off: Int, val incy: Int) {
		if ((((n > 0)) && ((da != 0)))) {
{
				if (((incx != 1) || (incy != 1))) {
{
						var ix: Int = 0;
						var iy: Int = 0;
						if ((incx < 0)) {
							ix = (((-n + 1)) * incx);
						}						if ((incy < 0)) {
							iy = (((-n + 1)) * incy);
						}						var tempr: Region{(self.rank == 1)} = (0..(n - 1));
						var temprDist: Dist{(self.rank == 1)} = (tempr->here);
						for (i: Point{(self.rank == 1)} in temprDist) {
							dy(dyCol, (iy + dy_off)) = (dy(dyCol, (iy + dy_off)) + ((da * read(dx, dxCol, (ix + dx_off)))));
							ix = (ix + incx);
							iy = (iy + incy);
						}
						return;
					}				}
				var tempr: Region{(self.rank == 1)} = (0..(n - 1));
				var temprDist: Dist{(self.rank == 1)} = (tempr->here);
				for ([i]: Point{(self.rank == 1)} in temprDist) 				dy(dyCol, (i + dy_off)) = (dy(dyCol, (i + dy_off)) + ((da * read(dx, dxCol, (i + dx_off)))));
			}		}
	}

	private def ddot(val n: Int, val dx: DistArray[Double]{(self.rank == 2)}, val dxCol: Int, val dx_off: Int, val incx: Int, val dy: DistArray[Double]{(self.rank == 2)}, val dyCol: Int, val dy_off: Int, val incy: Int): Double {
		var dtemp: Double = 0;
		if ((n > 0)) {

				if (((incx != 1) || (incy != 1))) {
{
						var ix: Int = 0;
						var iy: Int = 0;
						if ((incx < 0)) {
							ix = (((-n + 1)) * incx);
						}						if ((incy < 0)) {
							iy = (((-n + 1)) * incy);
						}						var tempr: Region{(self.rank == 1)} = (0..(n - 1));
						var temprDist: Dist{(self.rank == 1)} = (tempr->here);
						for (i: Point{(self.rank == 1)} in temprDist) {
							dtemp = (dtemp + ((dx(dxCol, (ix + dx_off)) * dy(dyCol, (iy + dy_off)))));
							ix = (ix + incx);
							iy = (iy + incy);
						}
					}				} else {
					var tempr: Region{(self.rank == 1)} = (0..(n - 1));
					var temprDist: Dist{(self.rank == 1)} = (tempr->here);
					for ([i]: Point{(self.rank == 1)} in temprDist) 					dtemp = (dtemp + (dx(dxCol, (i + dx_off)) * dy(dyCol, (i + dy_off))));
				}
		}		return dtemp;
	}

	private def dscal(val n: Int, val da: Double, val dx: DistArray[Double]{(self.rank == 2)}, val currentCol: Int, val dx_off: Int, val incx: Int) {
		if ((n > 0)) {

				if ((incx != 1)) {
{
						val nincx <: Int = (n * incx);
						var i: Int = 0;
						while ((i < nincx)) {
							dx(currentCol, (i + dx_off)) = (dx(currentCol, (i + dx_off)) * da);
							i = (i + incx);
						}
					}				} else {
					var tempr: Region{(self.rank == 1)} = (0..(n - 1));
					var temprDist: Dist{(self.rank == 1)} = (tempr->here);
					for ([i]: Point{(self.rank == 1)} in temprDist) 					dx(currentCol, (i + dx_off)) = (dx(currentCol, (i + dx_off)) * da);
				}
		}	}

	private def idamax(val n: Int, val dx: DistArray[Double]{(self.rank == 2)}, val dxk: Int, val dx_off: Int, val incx: Int): Int {
		if ((n < 1)) {
			return -1;
		}		if ((n == 1)) {
			return 0;
		}		if ((incx != 1)) {
{
				var itemp: Int = 0;
				var dmax: Double = myabs(read(dx, dxk, (0 + dx_off)));
				var ix: Int = (1 + incx);
				for (i: Point{(self.rank == 1)} in (1..(n - 1))) {
					var dtemp: Double = myabs(read(dx, dxk, (ix + dx_off)));
					if ((dtemp > dmax)) {
{
							itemp = i(0);
							dmax = dtemp;
						}					}
					ix = (i(0) + incx);
				}
				return itemp;
			}		}
		var itemp: Int = 0;
		var dmax: Double = myabs(read(dx, dxk, (0 + dx_off)));
		for (i: Point{(self.rank == 1)} in (1..(n - 1))) {
			var dtemp: Double = myabs(read(dx, dxk, (i(0) + dx_off)));
			if ((dtemp > dmax)) {
{
					itemp = i(0);
					dmax = dtemp;
				}			}
		}
		return itemp;
	}

	private def epslon(val x: Double): Double {
		val a <: Double = (4.0e0 / 3.0e0);
		var eps: Double = 0;
		while ((eps == 0)) {
			var b: Double = (a - 1.0);
			var c: Double = ((b + b) + b);
			eps = myabs((c - 1.0));
		}
		return ((eps * myabs(x)));
	}

	private def dmxpy(val n1: Int, val y: DistArray[Double]{(self.rank == 2)}, val n2: Int, val ldm: Int, val x: DistArray[Double]{(self.rank == 2)}, val m: DistArray[Double]{(self.rank == 2)}) {
		for ([j, i]: Point{(self.rank == 2)} in (0..(n2 - 1)) * (0..(n1 - 1))) 		y(0, i) = (y(0, i) + ((x(0, j) * read(m, j, i))));
	}

}

class LinpackMain {

	public static def main(args: Array[String]) {
{
			var tmr: Timer = new Timer();
			var count: Int = 0;

				tmr.start(count);

				new Linpack().run();
			tmr.stop(count);
			Console.OUT.println((("Wall-clock time for linpack: " + tmr.readTimer(count)) + " secs"));
		}	}

}

