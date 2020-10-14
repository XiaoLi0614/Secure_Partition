
import x10.compiler.*;

class MGOP {
	public static val PERF_On: Boolean = Util.PERF_OUTPUT;
	public static val PERF_Comp: Double = (1.0 / Util.FP_PERCLOCK);
	public static val PERF_CommL: Double = (Util.COMM_LATENCY * Util.CLOCK_SPEED);
	public static val PERF_CommB: Double = ((((Util.CLOCK_SPEED as Double) / (Util.COMM_BANDWIDTH as Double))) * 8.0);
	public static val PERF_InLoop: Boolean = Util.IN_LOOP;
	public static val PERF_CompOnly: Boolean = Util.COMP_ONLY;
	public static val PERF_CommOnly: Boolean = Util.COMM_ONLY;
	public static val OVERLAPPED: Boolean = Util.OVERLAP_COMMANDCOMP;
	public val EXCHANGE_After: Boolean;
	private static val reg: Region{(self.rank == 1)} = (0..3);
	public static val Ac0: Double = (-8.0 / 3.0);
	public static val Ac1: Double = 0.0;
	public static val Ac2: Double = (1.0 / 6.0);
	public static val Ac3: Double = (1.0 / 12.0);
	public static val Sac0: Double = (-3.0 / 8.0);
	public static val Sac1: Double = (1.0 / 32.0);
	public static val Sac2: Double = (-1.0 / 64.0);
	public static val Sac3: Double = 0.0;
	public static val Pc0: Double = (1.0 / 2.0);
	public static val Pc1: Double = (1.0 / 4.0);
	public static val Pc2: Double = (1.0 / 8.0);
	public static val Pc3: Double = (1.0 / 16.0);
	private static val P2SLEVEL: Int = (Util.P2SLEVEL - 1);
	private val m_size: Int;
	private val m_problemDomain: Region{(self.rank == 3)};
	private val m_levels: Int;
	private var m_r: DistArray[LevelData]{(self.rank == 1)};
	private var m_z: DistArray[LevelData]{(self.rank == 1)};
	private var m_tempLD: LevelData;

	public def this(val a_size: Int) {
		var temp_EXCHANGE_After: Boolean = false;
		if (Util.OVERLAP_COMMANDCOMP) {
			temp_EXCHANGE_After = false;
		}		else 		temp_EXCHANGE_After = Util.EXCHANGE_AFTER;
		EXCHANGE_After = temp_EXCHANGE_After;
		var i: Int = 0;
		var j: Int = 0;
		var k: Int = 0;
		m_size = a_size;
		m_problemDomain = (0..(a_size - 1)) * (0..(a_size - 1)) * (0..(a_size - 1));
		val log_a_size <: Int = Util.log2(a_size);
		m_levels = log_a_size;
		if ((P2SLEVEL > 0)) {
{
				i = Util.pow2(P2SLEVEL);
				j = ((i * i) * i);
			}		} else ;
		val d <: Dist{(self.rank == 1)} = ((0..log_a_size)->here);
		m_r = (DistArray.make[LevelData](d, (p:Point{(self.rank == 1)}) => {
			return new LevelData(Util.pow2((p(0) + 1)), (p(0) >= P2SLEVEL));
		}));
		m_z = (DistArray.make[LevelData](d, (p:Point{(self.rank == 1)}) => {
			return new LevelData(Util.pow2((p(0) + 1)), (p(0) >= P2SLEVEL));
		}));
		if ((P2SLEVEL > 0)) {
			m_tempLD = new LevelData(Util.pow2(P2SLEVEL), true);
		}		Console.OUT.println(((("Overlapping communication and computation? " + OVERLAPPED) + ". If not, doing exchange after computation? ") + temp_EXCHANGE_After));
	}


	public def computeResidual2(val RES: LevelData, val a_arg: LevelData) {
		for ([i]: Point{(self.rank == 1)} in RES.getPlaces()) {
			val res <: DistArray[Double]{(self.rank == 3)} = RES.getArray(i);
			val arg <: DistArray[Double]{(self.rank == 3)} = a_arg.getArray(i);
			var R: Region{(self.rank == 3)} = RES.getInnerRegion(i);
			for (p: Point{(self.rank == 3)} in R) {
				var d0: Double = arg(p);
				var d1: Double = 0;
				var d2: Double = Util.sumDIFF2(p, arg);
				var d3: Double = Util.sumDIFF3(p, arg);
				res(p) = (res(p) - ((((Ac0 * d0) + (Ac2 * d2)) + (Ac3 * d3))));
			}
		}
		if (EXCHANGE_After) {
			RES.exchange();
		}	}

	private def computeResidual3(val a_res: LevelData, val a_arg1: LevelData, val a_arg2: LevelData) {
		if (OVERLAPPED) {
			ComputeResidual_Overlapped(a_res, a_arg1, a_arg2);
		}		else 		ComputeResidual(a_res, a_arg1, a_arg2);
	}

	private def ComputeResidual(val a_res: LevelData, val a_arg1: LevelData, val a_arg2: LevelData) {
		if (!EXCHANGE_After) {
			a_arg2.exchange();
		}		for ([i]: Point{(self.rank == 1)} in a_res.getPlaces()) {
			val res <: DistArray[Double]{(self.rank == 3)} = a_res.getArray(i);
			val arg1 <: DistArray[Double]{(self.rank == 3)} = a_arg1.getArray(i);
			val arg2 <: DistArray[Double]{(self.rank == 3)} = a_arg2.getArray(i);
			var R: Region{(self.rank == 3)} = a_res.getInnerRegion(i);
			for (p: Point{(self.rank == 3)} in R) {
				var d0: Double = arg2(p);
				var d2: Double = Util.sumDIFF2(p, arg2);
				var d3: Double = Util.sumDIFF3(p, arg2);
				res(p) = (((arg1(p) - (Ac0 * d0)) - (Ac2 * d2)) - (Ac3 * d3));
			}
		}
		if (EXCHANGE_After) {
			a_res.exchange();
		}	}

	private def ComputeResidual_Overlapped1(val a_res: LevelData, val a_arg1: LevelData, val a_arg2: LevelData) {
{
			a_arg2.exchange();
			for ([i]: Point{(self.rank == 1)} in a_res.getPlaces()) {
				val res <: DistArray[Double]{(self.rank == 3)} = a_res.getArray(i);
				val arg1 <: DistArray[Double]{(self.rank == 3)} = a_arg1.getArray(i);
				val arg2 <: DistArray[Double]{(self.rank == 3)} = a_arg2.getArray(i);
				var R: Region{(self.rank == 3)} = a_res.getINNERRegion(i);
				for (p: Point{(self.rank == 3)} in R) {
					var d0: Double = arg2(p);
					var d2: Double = Util.sumDIFF2(p, arg2);
					var d3: Double = Util.sumDIFF3(p, arg2);
					res(p) = (((arg1(p) - (Ac0 * d0)) - (Ac2 * d2)) - (Ac3 * d3));
				}
			}
		}		for ([i]: Point{(self.rank == 1)} in a_res.getPlaces()) {
			val res <: DistArray[Double]{(self.rank == 3)} = a_res.getArray(i);
			val arg1 <: DistArray[Double]{(self.rank == 3)} = a_arg1.getArray(i);
			val arg2 <: DistArray[Double]{(self.rank == 3)} = a_arg2.getArray(i);
			var R: Region{(self.rank == 3)} = (a_res.getInnerRegion(i) - a_res.getINNERRegion(i));
			for (p: Point{(self.rank == 3)} in R) {
				var d0: Double = arg2(p);
				var d2: Double = Util.sumDIFF2(p, arg2);
				var d3: Double = Util.sumDIFF3(p, arg2);
				res(p) = (((arg1(p) - (Ac0 * d0)) - (Ac2 * d2)) - (Ac3 * d3));
			}
		}
	}

	private def ComputeResidual_Overlapped(val a_res: LevelData, val a_arg1: LevelData, val a_arg2: LevelData) {
{
			for ([i]: Point{(self.rank == 1)} in a_res.getPlaces()) {
				val res <: DistArray[Double]{(self.rank == 3)} = a_res.getArray(i);
				val arg1 <: DistArray[Double]{(self.rank == 3)} = a_arg1.getArray(i);
				val arg2 <: DistArray[Double]{(self.rank == 3)} = a_arg2.getArray(i);
				var R: Region{(self.rank == 3)} = a_res.getINNERRegion(i);
				for (p: Point{(self.rank == 3)} in R) {
					var d0: Double = arg2(p);
					var d2: Double = Util.sumDIFF2(p, arg2);
					var d3: Double = Util.sumDIFF3(p, arg2);
					res(p) = (((arg1(p) - (Ac0 * d0)) - (Ac2 * d2)) - (Ac3 * d3));
				}
			}
			a_arg2.exchange();
			for ([i]: Point{(self.rank == 1)} in a_res.getPlaces()) {
				val res <: DistArray[Double]{(self.rank == 3)} = a_res.getArray(i);
				val arg1 <: DistArray[Double]{(self.rank == 3)} = a_arg1.getArray(i);
				val arg2 <: DistArray[Double]{(self.rank == 3)} = a_arg2.getArray(i);
				var R: Region{(self.rank == 3)} = (a_res.getInnerRegion(i) - a_res.getINNERRegion(i));
				for (p: Point{(self.rank == 3)} in R) {
					var d0: Double = arg2(p);
					var d2: Double = Util.sumDIFF2(p, arg2);
					var d3: Double = Util.sumDIFF3(p, arg2);
					res(p) = (((arg1(p) - (Ac0 * d0)) - (Ac2 * d2)) - (Ac3 * d3));
				}
			}
		}	}

	private def smooth(val a_res: LevelData, val a_arg: LevelData, val a_isBottom: Boolean) {
		if (OVERLAPPED) {
			Smooth_Overlapped(a_res, a_arg, a_isBottom);
		}		else 		Smooth(a_res, a_arg, a_isBottom);
	}

	private def Smooth(val a_res: LevelData, val a_arg: LevelData, val a_isBottom: Boolean) {
		if (!EXCHANGE_After) {
			a_arg.exchange();
		}		for ([i]: Point{(self.rank == 1)} in a_res.getPlaces()) {
			val res <: DistArray[Double]{(self.rank == 3)} = a_res.getArray(i);
			val arg <: DistArray[Double]{(self.rank == 3)} = a_arg.getArray(i);
			var R: Region{(self.rank == 3)} = a_res.getInnerRegion(i);
			for (p: Point{(self.rank == 3)} in R) {
				var d0: Double = arg(p);
				var d1: Double = Util.sumDIFF1(p, arg);
				var d2: Double = Util.sumDIFF2(p, arg);
				res(p) = (((res(p) + (Sac0 * d0)) + (Sac1 * d1)) + (Sac2 * d2));
			}
		}
		if (EXCHANGE_After) {
			a_res.exchange();
		}	}

	private def Smooth_Overlapped1(val a_res: LevelData, val a_arg: LevelData, val a_isBottom: Boolean) {
{
			a_arg.exchange();
			for ([i]: Point{(self.rank == 1)} in a_res.getPlaces()) {
				val res <: DistArray[Double]{(self.rank == 3)} = a_res.getArray(i);
				val arg <: DistArray[Double]{(self.rank == 3)} = a_arg.getArray(i);
				var R: Region{(self.rank == 3)} = a_res.getINNERRegion(i);
				for (p: Point{(self.rank == 3)} in R) {
					var d0: Double = arg(p);
					var d1: Double = Util.sumDIFF1(p, arg);
					var d2: Double = Util.sumDIFF2(p, arg);
					res(p) = (((res(p) + (Sac0 * d0)) + (Sac1 * d1)) + (Sac2 * d2));
				}
			}
		}		for ([i]: Point{(self.rank == 1)} in a_res.getPlaces()) {
			val res <: DistArray[Double]{(self.rank == 3)} = a_res.getArray(i);
			val arg <: DistArray[Double]{(self.rank == 3)} = a_arg.getArray(i);
			var R: Region{(self.rank == 3)} = (a_res.getInnerRegion(i) - a_res.getINNERRegion(i));
			for (p: Point{(self.rank == 3)} in R) {
				var d0: Double = arg(p);
				var d1: Double = Util.sumDIFF1(p, arg);
				var d2: Double = Util.sumDIFF2(p, arg);
				res(p) = (((res(p) + (Sac0 * d0)) + (Sac1 * d1)) + (Sac2 * d2));
			}
		}
	}

	private def Smooth_Overlapped(val a_res: LevelData, val a_arg: LevelData, val a_isBottom: Boolean) {
{
			for ([i]: Point{(self.rank == 1)} in a_res.getPlaces()) {
				val res <: DistArray[Double]{(self.rank == 3)} = a_res.getArray(i);
				val arg <: DistArray[Double]{(self.rank == 3)} = a_arg.getArray(i);
				var R: Region{(self.rank == 3)} = a_res.getINNERRegion(i);
				for (p: Point{(self.rank == 3)} in R) {
					var d0: Double = arg(p);
					var d1: Double = Util.sumDIFF1(p, arg);
					var d2: Double = Util.sumDIFF2(p, arg);
					res(p) = (((res(p) + (Sac0 * d0)) + (Sac1 * d1)) + (Sac2 * d2));
				}
			}
			a_arg.exchange();
			for ([i]: Point{(self.rank == 1)} in a_res.getPlaces()) {
				val res <: DistArray[Double]{(self.rank == 3)} = a_res.getArray(i);
				val arg <: DistArray[Double]{(self.rank == 3)} = a_arg.getArray(i);
				var R: Region{(self.rank == 3)} = (a_res.getInnerRegion(i) - a_res.getINNERRegion(i));
				for (p: Point{(self.rank == 3)} in R) {
					var d0: Double = arg(p);
					var d1: Double = Util.sumDIFF1(p, arg);
					var d2: Double = Util.sumDIFF2(p, arg);
					res(p) = (((res(p) + (Sac0 * d0)) + (Sac1 * d1)) + (Sac2 * d2));
				}
			}
		}	}

	private def applyOpP3(val a_res: LevelData, val a_arg: LevelData, val a_level: Int) {
		if ((a_level == P2SLEVEL)) {
{
				applyOpP2(m_tempLD, a_arg);
				val res <: DistArray[Double]{(self.rank == 3)} = a_res.getArray(0);
				for ([i]: Point{(self.rank == 1)} in m_tempLD.getPlaces()) {
					var temp: DistArray[Double]{(self.rank == 3)} = m_tempLD.getArray(i);
					Util.arraycopy3(res, (m_tempLD.getInnerRegion(i) as Region{(self.rank == 3)}), temp);
				}
			}		} else 		applyOpP2(a_res, a_arg);
		if (EXCHANGE_After) {
			a_res.exchange();
		}	}

	private def applyOpP2(val a_res: LevelData, val a_arg: LevelData) {
		if (OVERLAPPED) {
			ApplyOpP_Overlapped(a_res, a_arg);
		}		else 		ApplyOpP(a_res, a_arg);
	}

	private def ApplyOpP(val a_res: LevelData, val a_arg: LevelData) {
		if (!EXCHANGE_After) {
			a_arg.exchange();
		}		for ([i]: Point{(self.rank == 1)} in a_res.getPlaces()) {
			val arg <: DistArray[Double]{(self.rank == 3)} = a_arg.getArray(i);
			val res <: DistArray[Double]{(self.rank == 3)} = a_res.getArray(i);
			var R: Region{(self.rank == 3)} = a_res.getInnerRegion(i);
			for (pp: Point{(self.rank == 3)} in R) {
				var p: Point{(self.rank == 3)} = ((pp * 2) + Point.make([1, 1, 1]));
				var d0: Double = arg(p);
				var d1: Double = Util.sumDIFF1(p, arg);
				var d2: Double = Util.sumDIFF2(p, arg);
				var d3: Double = Util.sumDIFF3(p, arg);
				res(pp) = ((((Pc0 * d0) + (Pc1 * d1)) + (Pc2 * d2)) + (Pc3 * d3));
			}
		}
	}

	private def ApplyOpP_Overlapped1(val a_res: LevelData, val a_arg: LevelData) {
{
			a_arg.exchange();
			for ([i]: Point{(self.rank == 1)} in a_res.getPlaces()) {
				val arg <: DistArray[Double]{(self.rank == 3)} = a_arg.getArray(i);
				val res <: DistArray[Double]{(self.rank == 3)} = a_res.getArray(i);
				var R: Region{(self.rank == 3)} = a_res.getINNERRegion(i);
				for (pp: Point{(self.rank == 3)} in R) {
					var p: Point{(self.rank == 3)} = ((pp * 2) + Point.make([1, 1, 1]));
					var d0: Double = arg(p);
					var d1: Double = Util.sumDIFF1(p, arg);
					var d2: Double = Util.sumDIFF2(p, arg);
					var d3: Double = Util.sumDIFF3(p, arg);
					res(pp) = ((((Pc0 * d0) + (Pc1 * d1)) + (Pc2 * d2)) + (Pc3 * d3));
				}
			}
		}		for ([i]: Point{(self.rank == 1)} in a_res.getPlaces()) {
			val arg <: DistArray[Double]{(self.rank == 3)} = a_arg.getArray(i);
			val res <: DistArray[Double]{(self.rank == 3)} = a_res.getArray(i);
			var R: Region{(self.rank == 3)} = (a_res.getInnerRegion(i) - a_res.getINNERRegion(i));
			for (pp: Point{(self.rank == 3)} in R) {
				var p: Point{(self.rank == 3)} = ((pp * 2) + Point.make([1, 1, 1]));
				var d0: Double = arg(p);
				var d1: Double = Util.sumDIFF1(p, arg);
				var d2: Double = Util.sumDIFF2(p, arg);
				var d3: Double = Util.sumDIFF3(p, arg);
				res(pp) = ((((Pc0 * d0) + (Pc1 * d1)) + (Pc2 * d2)) + (Pc3 * d3));
			}
		}
	}

	private def ApplyOpP_Overlapped(val a_res: LevelData, val a_arg: LevelData) {
{
			for ([i]: Point{(self.rank == 1)} in a_res.getPlaces()) {
				val arg <: DistArray[Double]{(self.rank == 3)} = a_arg.getArray(i);
				val res <: DistArray[Double]{(self.rank == 3)} = a_res.getArray(i);
				var R: Region{(self.rank == 3)} = a_res.getINNERRegion(i);
				for (pp: Point{(self.rank == 3)} in R) {
					var p: Point{(self.rank == 3)} = ((pp * 2) + Point.make([1, 1, 1]));
					var d0: Double = arg(p);
					var d1: Double = Util.sumDIFF1(p, arg);
					var d2: Double = Util.sumDIFF2(p, arg);
					var d3: Double = Util.sumDIFF3(p, arg);
					res(pp) = ((((Pc0 * d0) + (Pc1 * d1)) + (Pc2 * d2)) + (Pc3 * d3));
				}
			}
			a_arg.exchange();
			for ([i]: Point{(self.rank == 1)} in a_res.getPlaces()) {
				val arg <: DistArray[Double]{(self.rank == 3)} = a_arg.getArray(i);
				val res <: DistArray[Double]{(self.rank == 3)} = a_res.getArray(i);
				var R: Region{(self.rank == 3)} = (a_res.getInnerRegion(i) - a_res.getINNERRegion(i));
				for (pp: Point{(self.rank == 3)} in R) {
					var p: Point{(self.rank == 3)} = ((pp * 2) + Point.make([1, 1, 1]));
					var d0: Double = arg(p);
					var d1: Double = Util.sumDIFF1(p, arg);
					var d2: Double = Util.sumDIFF2(p, arg);
					var d3: Double = Util.sumDIFF3(p, arg);
					res(pp) = ((((Pc0 * d0) + (Pc1 * d1)) + (Pc2 * d2)) + (Pc3 * d3));
				}
			}
		}	}

	private def applyOpQ3(val a_res: LevelData, val a_arg: LevelData, val a_level: Int) {
		if ((a_level == (P2SLEVEL - 1))) {
{
				val arg <: DistArray[Double]{(self.rank == 3)} = a_arg.getArray(0);
				for ([i]: Point{(self.rank == 1)} in m_tempLD.getPlaces()) {
					var temp: DistArray[Double]{(self.rank == 3)} = m_tempLD.getArray(i);
					Util.arraycopy3(temp, (temp.region as Region{(self.rank == 3)}), arg);
				}
				applyOpQ2(a_res, m_tempLD);
			}		} else 
			applyOpQ2(a_res, a_arg);
	}

	private def applyOpQ2(val a_res: LevelData, val a_arg: LevelData) {
		if (OVERLAPPED) {
			ApplyOpQ_Overlapped(a_res, a_arg);
		}		else 		ApplyOpQ(a_res, a_arg);
	}

	private def ApplyOpQ(val a_res: LevelData, val a_arg: LevelData) {
		if (!EXCHANGE_After) {
			a_arg.exchange();
		}		for ([i]: Point{(self.rank == 1)} in a_res.getPlaces()) {
			val arg <: DistArray[Double]{(self.rank == 3)} = a_arg.getArray(i);
			val res <: DistArray[Double]{(self.rank == 3)} = a_res.getArray(i);
			var R: Region{(self.rank == 3)} = a_arg.getShrinkedRegion(i);
			for (p: Point{(self.rank == 3)} in R) {
				var d: Double = 0;
				var pp: Point{(self.rank == 3)} = ((p * 2) + Point.make([1, 1, 1]));
				for (o: Point{(self.rank == 3)} in Util.UNIT_CUBE) {
					var i2: Int = o(0);
					var j2: Int = o(1);
					var k2: Int = o(2);
{
						d = 0;
						for ([i1, j1, k1]: Point{(self.rank == 3)} in Util.QREGIONS(o)) {
							val temp <: Point{(self.rank == 3)} = (p + Point.make([i1, j1, k1]));
							d = (d + arg(temp));
						}
						res((pp + Point.make([i2, j2, k2]))) = (d / Util.QREGIONS(o).size());
					}				}			}
		}
	}

	private def ApplyOpQ_Overlapped1(val a_res: LevelData, val a_arg: LevelData) {
{
			a_arg.exchange();
			for ([i]: Point{(self.rank == 1)} in a_res.getPlaces()) {
				val arg <: DistArray[Double]{(self.rank == 3)} = a_arg.getArray(i);
				val res <: DistArray[Double]{(self.rank == 3)} = a_res.getArray(i);
				var R: Region{(self.rank == 3)} = a_arg.getINNERRegion(i);
				for (p: Point{(self.rank == 3)} in R) {
					var d: Double = 0;
					var pp: Point{(self.rank == 3)} = ((p * 2) + Point.make([1, 1, 1]));
					for (o: Point{(self.rank == 3)} in Util.UNIT_CUBE) {
						var i2: Int = o(0);
						var j2: Int = o(1);
						var k2: Int = o(2);
{
							d = 0;
							for ([i1, j1, k1]: Point{(self.rank == 3)} in Util.QREGIONS(o)) {
								val temp <: Point{(self.rank == 3)} = (p + Point.make([i1, j1, k1]));
								d = (d + arg(temp));
							}
							res((pp + Point.make([i2, j2, k2]))) = (d / Util.QREGIONS(o).size());
						}					}				}
			}
		}		for ([i]: Point{(self.rank == 1)} in a_res.getPlaces()) {
			val arg <: DistArray[Double]{(self.rank == 3)} = a_arg.getArray(i);
			val res <: DistArray[Double]{(self.rank == 3)} = a_res.getArray(i);
			var R: Region{(self.rank == 3)} = (a_arg.getShrinkedRegion(i) - a_arg.getINNERRegion(i));
			for (p: Point{(self.rank == 3)} in R) {
				var d: Double = 0;
				var pp: Point{(self.rank == 3)} = ((p * 2) + Point.make([1, 1, 1]));
				for (o: Point{(self.rank == 3)} in Util.UNIT_CUBE) {
					var i2: Int = o(0);
					var j2: Int = o(1);
					var k2: Int = o(2);
{
						d = 0;
						for ([i1, j1, k1]: Point{(self.rank == 3)} in Util.QREGIONS(o)) {
							val temp <: Point{(self.rank == 3)} = (p + Point.make([i1, j1, k1]));
							d = (d + arg(temp));
						}
						res((pp + Point.make([i2, j2, k2]))) = (d / Util.QREGIONS(o).size());
					}				}			}
		}
	}

	private def ApplyOpQ_Overlapped(val a_res: LevelData, val a_arg: LevelData) {
{
			for ([i]: Point{(self.rank == 1)} in a_res.getPlaces()) {
				val arg <: DistArray[Double]{(self.rank == 3)} = a_arg.getArray(i);
				val res <: DistArray[Double]{(self.rank == 3)} = a_res.getArray(i);
				var R: Region{(self.rank == 3)} = a_arg.getINNERRegion(i);
				for (p: Point{(self.rank == 3)} in R) {
					var d: Double = 0;
					var pp: Point{(self.rank == 3)} = ((p * 2) + Point.make([1, 1, 1]));
					for (o: Point{(self.rank == 3)} in Util.UNIT_CUBE) {
						var i2: Int = o(0);
						var j2: Int = o(1);
						var k2: Int = o(2);
{
							d = 0;
							for ([i1, j1, k1]: Point{(self.rank == 3)} in Util.QREGIONS(o)) {
								val temp <: Point{(self.rank == 3)} = (p + Point.make([i1, j1, k1]));
								d = (d + arg(temp));
							}
							res((pp + Point.make([i2, j2, k2]))) = (d / Util.QREGIONS(o).size());
						}					}				}
			}
			a_arg.exchange();
			for ([i]: Point{(self.rank == 1)} in a_res.getPlaces()) {
				val arg <: DistArray[Double]{(self.rank == 3)} = a_arg.getArray(i);
				val res <: DistArray[Double]{(self.rank == 3)} = a_res.getArray(i);
				var R: Region{(self.rank == 3)} = (a_arg.getShrinkedRegion(i) - a_arg.getINNERRegion(i));
				for (p: Point{(self.rank == 3)} in R) {
					var d: Double = 0;
					var pp: Point{(self.rank == 3)} = ((p * 2) + Point.make([1, 1, 1]));
					for (o: Point{(self.rank == 3)} in Util.UNIT_CUBE) {
						var i2: Int = o(0);
						var j2: Int = o(1);
						var k2: Int = o(2);
{
							d = 0;
							for ([i1, j1, k1]: Point{(self.rank == 3)} in Util.QREGIONS(o)) {
								val temp <: Point{(self.rank == 3)} = (p + Point.make([i1, j1, k1]));
								d = (d + arg(temp));
							}
							res((pp + Point.make([i2, j2, k2]))) = (d / Util.QREGIONS(o).size());
						}					}				}
			}
		}	}

	public def MGSolve(val a_u: LevelData, val a_v: LevelData, val a_its: Int): Double {
		var l: Int = (m_levels - 1);
		m_r(l).setLevelData(a_v);
		Console.OUT.println(("The init residual norm is " + m_r(l).norm2()));
		var res: Double = 0.0;
		var i: Int = 0;
		while ((i < a_its)) {
			Console.OUT.println(("Iteration " + i));
			MG();
			a_u.add(m_z(l));
			computeResidual3(m_r(l), a_v, a_u);
			res = m_r(l).norm2();
			Console.OUT.println(("The residual norm is " + res));
			i++;
		}
		return res;
	}

	private def MG() {
		var i2: Int = (m_levels - 1);
		while ((i2 > 0)) {
			applyOpP3(m_r((i2 - 1)), m_r(i2), i2);
			i2--;
		}
		m_z(0).set(0);
		smooth(m_z(0), m_r(0), true);
		var i3: Int = 1;
		while ((i3 < m_levels)) {
			applyOpQ3(m_z(i3), m_z((i3 - 1)), (i3 - 1));
			computeResidual2(m_r(i3), m_z(i3));
			smooth(m_z(i3), m_r(i3), false);
			i3++;
		}
	}

	private def residualNorm(): Double {
		var l: Int = (m_levels - 1);
		return m_r(l).norm2();
	}

}

class MGMain {

	public static def main(args: Array[String]) {
		new MGDriver().driver();
	}

}

class Util {
	public static val PERF_OUTPUT: Boolean = true;
	public static val CLOCK_SPEED: Double = 375;
	public static val FP_PERCLOCK: Double = 4;
	public static val COMM_LATENCY: Double = 1;
	public static val COMM_BANDWIDTH: Double = 2000;
	public static val IN_LOOP: Boolean = false;
	public static val COMP_ONLY: Boolean = false;
	public static val COMM_ONLY: Boolean = false;
	public static val THREADS_PERFOREACH: Int = 1;
	public static val OVERLAP_COMMANDCOMP: Boolean = false;
	public static val EXCHANGE_AFTER: Boolean = false;
	public static val P2SLEVEL: Int = 1;
	public static val N_PLACES: Int = Place.MAX_PLACES;
	public static val UNIT_CUBE: Region{(self.rank == 3)} = (0..1) * (0..1) * (0..1);
	public static val QREGIONS: DistArray[Region{(self.rank == 3)}]{(self.rank == 3)} = ((DistArray.make[Region{(self.rank == 3)}](UNIT_CUBE-> here, (p:Point{(self.rank == 3)}) => {
		var a: Int = p(0);
		var b: Int = p(1);
		var c: Int = p(2);

			return (0..a) * (0..b) * (0..c);
	})) as DistArray[Region{(self.rank == 3)}]{(self.rank == 3)});
	public static val LOW: Int = 0;
	public static val HIGH: Int = 1;

	public def this() {
	}


	public static def sumDIFF1(val p: Point{(self.rank == 3)}, val arg: DistArray[Double]{(self.rank == 3)}): Double {
		var d1: Double = 0;
		d1 = (d1 + arg((p + Point.make([1, 0, 0]))));
		d1 = (d1 + arg((p + Point.make([-1, 0, 0]))));
		d1 = (d1 + arg((p + Point.make([0, 1, 0]))));
		d1 = (d1 + arg((p + Point.make([0, -1, 0]))));
		d1 = (d1 + arg((p + Point.make([0, 0, 1]))));
		d1 = (d1 + arg((p + Point.make([0, 0, -1]))));
		return d1;
	}

	public static def sumDIFF2(val p: Point{(self.rank == 3)}, val arg: DistArray[Double]{(self.rank == 3)}): Double {
		var d1: Double = 0;
		d1 = (d1 + arg((p + Point.make([1, 1, 0]))));
		d1 = (d1 + arg((p + Point.make([1, -1, 0]))));
		d1 = (d1 + arg((p + Point.make([-1, 1, 0]))));
		d1 = (d1 + arg((p + Point.make([-1, -1, 0]))));
		d1 = (d1 + arg((p + Point.make([1, 0, 1]))));
		d1 = (d1 + arg((p + Point.make([1, 0, -1]))));
		d1 = (d1 + arg((p + Point.make([-1, 0, 1]))));
		d1 = (d1 + arg((p + Point.make([-1, 0, -1]))));
		d1 = (d1 + arg((p + Point.make([0, 1, 1]))));
		d1 = (d1 + arg((p + Point.make([0, 1, -1]))));
		d1 = (d1 + arg((p + Point.make([0, -1, 1]))));
		d1 = (d1 + arg((p + Point.make([0, -1, -1]))));
		return d1;
	}

	public static def sumDIFF3(val p: Point{(self.rank == 3)}, val arg: DistArray[Double]{(self.rank == 3)}): Double {
		var d1: Double = 0;
		d1 = (d1 + arg((p + Point.make([1, 1, 1]))));
		d1 = (d1 + arg((p + Point.make([1, 1, -1]))));
		d1 = (d1 + arg((p + Point.make([1, -1, 1]))));
		d1 = (d1 + arg((p + Point.make([1, -1, -1]))));
		d1 = (d1 + arg((p + Point.make([-1, 1, 1]))));
		d1 = (d1 + arg((p + Point.make([-1, 1, -1]))));
		d1 = (d1 + arg((p + Point.make([-1, -1, 1]))));
		d1 = (d1 + arg((p + Point.make([-1, -1, -1]))));
		return d1;
	}

	public static def powerOf2(val a_int: Int): Boolean {
		var i: Int = (Math.abs(a_int) as Int);
		if ((i == 0)) {
			return false;
		}		else 
			if ((i != (pow2(log2(i))))) {
				return false;
			}		return true;
	}

	public static def log2(val a_int: Int): Int {
		return (((Math.log((a_int as Double)) / Math.log(((2 as Double) as Double)))) as Int);
	}

	public static def pow2(val a_int: Int): Int {
		return (Math.pow(2, a_int) as Int);
	}

	public static def boundary(val a_R: Region{(self.rank == 3)}, val a_direction: Int, val a_padSize0: Int, val a_padSize1: Int, val a_padSize2: Int): Region{(self.rank == 3)} {
		var i: Int = (Math.abs(a_direction) as Int);
		var IsHigh: Boolean = ((a_direction > 0));
		var low0: Int = a_R.projection(0).min(0);
		var low1: Int = a_R.projection(1).min(0);
		var low2: Int = a_R.projection(2).min(0);
		var high0: Int = a_R.projection(0).max(0);
		var high1: Int = a_R.projection(1).max(0);
		var high2: Int = a_R.projection(2).max(0);
		var r: Int = 3;
		if (((i <= r) && (r == 3))) {

				if (IsHigh) {

						return ((((i == 1)?(high0 + 1):low0) - a_padSize0)..(((i == 1)?(high0 + 1):high0) + a_padSize0)) * ((((i == 2)?(high1 + 1):low1) - a_padSize1)..(((i == 2)?(high1 + 1):high1) + a_padSize1)) * ((((i == 3)?(high2 + 1):low2) - a_padSize2)..(((i == 3)?(high2 + 1):high2) + a_padSize2));
				}				else 
					return ((((i == 1)?(low0 - 1):low0) - a_padSize0)..(((i == 1)?(low0 - 1):high0) + a_padSize0)) * ((((i == 2)?(low1 - 1):low1) - a_padSize1)..(((i == 2)?(low1 - 1):high1) + a_padSize1)) * ((((i == 3)?(low2 - 1):low2) - a_padSize2)..(((i == 3)?(low2 - 1):high2) + a_padSize2));
		}		else {
			Console.OUT.println("MG3TongValue1.boundary():Warning! invalid inputs!");
			return (0..-1) * (0..-1) * (0..-1);
		}
	}

	public static def arraycopy3(val a_dest: DistArray[Double]{(self.rank == 3)}, val a_destR: Region{(self.rank == 3)}, val a_src: DistArray[Double]{(self.rank == 3)}) {
		for (p: Point{(self.rank == 3)} in a_destR) {
			val h <: Place = here;
{
				val d <: Double = a_src(p);

					a_dest(p) = d;
			}		}
	}

	public static def arraycopy4(val a_dest: DistArray[Double]{(self.rank == 3)}, val a_destR: Region{(self.rank == 3)}, val a_src: DistArray[Double]{(self.rank == 3)}, val a_trans0: Int, val a_trans1: Int, val a_trans2: Int) {
		for (p: Point{(self.rank == 3)} in a_destR) {
			val h <: Place = here;
{
				val d <: Double = a_src((p + Point.make([a_trans0, a_trans1, a_trans2])));

					a_dest(p) = d;
			}		}
	}

}

class MGDriver {

	public static def driver() {
		var tmr: Timer = new Timer();
		var count: Int = 0;

			tmr.start(count);
{
			var CLASS: Int = 1;
			val isDistributed <: Boolean = true;
			var problemSize: Int = 0;
			var numberIterations: Int = 0;
			var reference2Norm: Double = 0;
			switch (CLASS) {
				case 1:
					problemSize = 32;
					numberIterations = 4;
					reference2Norm = 5.30770700573E-5;
					break;
				case 2:
					problemSize = 64;
					numberIterations = 40;
					reference2Norm = 2.50391406439E-18;
					break;
				case 3:
					problemSize = 256;
					numberIterations = 4;
					reference2Norm = 2.433365309e-6;
					break;
				case 4:
					problemSize = 256;
					numberIterations = 20;
					reference2Norm = 1.80056440132e-6;
					break;
				case 5:
					problemSize = 512;
					numberIterations = 20;
					reference2Norm = 5.70674826298e-7;
					break;
				case 6:
					problemSize = 1024;
					numberIterations = 50;
					reference2Norm = 1.58327506043e-10;
					break;
				default: 
					Console.ERR.println("Class must be one of {S,W,A,B,C,D}!");
			}
			var u: LevelData = new LevelData(problemSize, isDistributed);
			var v: LevelData = new LevelData(problemSize, isDistributed);
			v.initialize(CLASS);
			var MG: MGOP = new MGOP(problemSize);
			Console.OUT.println(((("There are " + Util.N_PLACES) + " places. Running problem of class ") + CLASS));
			var res: Double = 0;
			res = MG.MGSolve(u, v, numberIterations);
			Console.OUT.println(((("The reference 2norm is " + reference2Norm) + ". The difference is ") + ((res - reference2Norm))));
		}		tmr.stop(count);
		Console.OUT.println((("Wall-clock time for MGSolve: " + tmr.readTimer(count)) + "secs"));
	}

}

class Wrapper {
	public val m_array: DistArray[Double]{(self.rank == 3)};

	public def this(val a_array: DistArray[Double]{(self.rank == 3)}) {
		m_array = a_array;
	}


	public def toString(): String {
		return "";
	}

}

class LevelData {
	public static val PERF_On: Boolean = Util.PERF_OUTPUT;
	public static val PERF_Comp: Double = (1.0 / Util.FP_PERCLOCK);
	public static val PERF_CommL: Double = (Util.COMM_LATENCY * Util.CLOCK_SPEED);
	public static val PERF_CommB: Double = ((((Util.CLOCK_SPEED as Double) / (Util.COMM_BANDWIDTH as Double))) * 8.0);
	public static val PERF_InLoop: Boolean = Util.IN_LOOP;
	public static val PERF_CompOnly: Boolean = Util.COMP_ONLY;
	public static val PERF_CommOnly: Boolean = Util.COMM_ONLY;
	public val EXCHANGE_After: Boolean;
	public static val N_PLACES: Int = Util.N_PLACES;
	public static val HIGH: Int = Util.HIGH;
	public static val LOW: Int = Util.LOW;
	private val SIZE: Int;
	private val P_DOMAIN: Region{(self.rank == 3)};
	private val ISPARALLEL: Boolean;
	private val m_regions: DistArray[Region{(self.rank == 3)}]{(self.rank == 1)};
	private val m_REGIONs: DistArray[Region{(self.rank == 3)}]{(self.rank == 1)};
	private val m_boundaries: DistArray[Region{(self.rank == 3)}]{(self.rank == 2)};
	private val m_places: Dist{(self.rank == 1)};
	private val m_numPlaces: Int;
	private val m_placeGrid: Region{(self.rank == 3)};
	private val m_size0: Int;
	private val m_size1: Int;
	private val m_size2: Int;
	private val m_block0: Int;
	private val m_block1: Int;
	private val m_block2: Int;
	private val m_dist: DistArray[Dist{(self.rank == 3)}]{(self.rank == 1)};
	private val m_DIST: DistArray[Dist{(self.rank == 3)}]{(self.rank == 1)};
	private val m_u: DistArray[Wrapper]{(self.rank == 1)};

	public def this(val a_problemDomainSize: Int, val a_isParallel: Boolean) {
		val ALLPLACES <: Dist{(self.rank == 1)} = (Dist.makeUnique() as Dist{(self.rank == 1)});
		if (Util.OVERLAP_COMMANDCOMP) {

				EXCHANGE_After = false;
		}		else 
			EXCHANGE_After = Util.EXCHANGE_AFTER;
		SIZE = a_problemDomainSize;
		ISPARALLEL = a_isParallel;
		val temp_P_DOMAIN <: Region{(self.rank == 3)} = (0..(a_problemDomainSize - 1)) * (0..(a_problemDomainSize - 1)) * (0..(a_problemDomainSize - 1));
		P_DOMAIN = temp_P_DOMAIN;
		var numCuts: Int = 0;
		var temp_m_numPlaces: Int = 0;
		if (a_isParallel) {
{
				m_places = ALLPLACES;
				temp_m_numPlaces = N_PLACES;
				numCuts = Util.log2(N_PLACES);
			}		} else {
			m_places = ((0..0)->ALLPLACES(0));
			temp_m_numPlaces = 1;
			numCuts = 0;
		}
		m_numPlaces = temp_m_numPlaces;
		var d: Dist{(self.rank == 1)} = ((0..temp_m_numPlaces)->here);
		var di: Dist{(self.rank == 2)} = ((0..5) * (0..(temp_m_numPlaces - 1))->here);
		val temp_m_dist <: DistArray[Dist{(self.rank == 3)}]{(self.rank == 1)} = (getDist3Array(d) as DistArray[Dist{(self.rank == 3)}]{(self.rank == 1)});
		m_dist = temp_m_dist;
		val temp_m_DIST <: DistArray[Dist{(self.rank == 3)}]{(self.rank == 1)} = (getDist3Array(d) as DistArray[Dist{(self.rank == 3)}]{(self.rank == 1)});
		m_DIST = temp_m_DIST;
		val m_low0 <: Int = temp_P_DOMAIN.projection(0).min(0);
		val m_low1 <: Int = temp_P_DOMAIN.projection(1).min(0);
		val m_low2 <: Int = temp_P_DOMAIN.projection(2).min(0);
		val m_hi0 <: Int = temp_P_DOMAIN.projection(0).max(0);
		val m_hi1 <: Int = temp_P_DOMAIN.projection(1).max(0);
		val m_hi2 <: Int = temp_P_DOMAIN.projection(2).max(0);
		val temp_m_size0 <: Int = ((m_hi0 - m_low0) + 1);
		val temp_m_size1 <: Int = ((m_hi1 - m_low1) + 1);
		val temp_m_size2 <: Int = ((m_hi2 - m_low2) + 1);
		m_size0 = temp_m_size0;
		m_size1 = temp_m_size1;
		m_size2 = temp_m_size2;
		var a: Int = (numCuts / 3);
		var b: Int = (numCuts % 3);
		val m_cut0 <: Int = (a + ((b > 0)?1:0));
		val m_cut1 <: Int = (a + ((b > 1)?1:0));
		val m_cut2 <: Int = a;
		val pow2_m_cut0 <: Int = Util.pow2(m_cut0);
		val pow2_m_cut1 <: Int = Util.pow2(m_cut1);
		val pow2_m_cut2 <: Int = Util.pow2(m_cut2);
		m_block0 = pow2_m_cut0;
		m_block1 = pow2_m_cut1;
		m_block2 = pow2_m_cut2;
		val m_blockSize0 <: Int = (temp_m_size0 / pow2_m_cut0);
		val m_blockSize1 <: Int = (temp_m_size1 / pow2_m_cut1);
		val m_blockSize2 <: Int = (temp_m_size2 / pow2_m_cut2);
		val temp_m_placeGrid <: Region{(self.rank == 3)} = (0..(pow2_m_cut0 - 1)) * (0..(pow2_m_cut1 - 1)) * (0..(pow2_m_cut2 - 1));
		m_placeGrid = temp_m_placeGrid;
		var i: Int = 0;
		var m_reg: DistArray[Region{(self.rank == 3)}]{(self.rank == 1)} = (getRegion3Array1(d) as DistArray[Region{(self.rank == 3)}]{(self.rank == 1)});
		var m_REG: DistArray[Region{(self.rank == 3)}]{(self.rank == 1)} = (getRegion3Array1(d) as DistArray[Region{(self.rank == 3)}]{(self.rank == 1)});
		for ([x, y, z]: Point{(self.rank == 3)} in temp_m_placeGrid) {
			val mrl0 <: Int = ((m_blockSize0 * x) + m_low0);
			val mrl1 <: Int = ((m_blockSize1 * y) + m_low1);
			val mrl2 <: Int = ((m_blockSize2 * z) + m_low2);
			val mrh0 <: Int = ((m_blockSize0 * ((x + 1))) + m_low0);
			val mrh1 <: Int = ((m_blockSize1 * ((y + 1))) + m_low1);
			val mrh2 <: Int = ((m_blockSize2 * ((z + 1))) + m_low2);
			m_reg(i) = (mrl0..(mrh0 - 1)) * (mrl1..(mrh1 - 1)) * (mrl2..(mrh2 - 1));
			m_REG(i) = ((mrl0 - 1)..mrh0) * ((mrl1 - 1)..mrh1) * ((mrl2 - 1)..mrh2);
			temp_m_dist(i) = ((mrl0..(mrh0 - 1)) * (mrl1..(mrh1 - 1)) * (mrl2..(mrh2 - 1))->Place.place(i));
			temp_m_DIST(i) = (((mrl0 - 1)..mrh0) * ((mrl1 - 1)..mrh1) * ((mrl2 - 1)..mrh2)->Place.place(i));
			i++;
		}
		m_regions = m_reg;
		m_REGIONs = m_REG;
		var temp_m_boundaries: DistArray[Region{(self.rank == 3)}]{(self.rank == 2)} = (getRegion3Array2(di) as DistArray[Region{(self.rank == 3)}]{(self.rank == 2)});
		for ([j, ii]: Point{(self.rank == 2)} in di) {
			if ((j == 0)) {

					temp_m_boundaries(j, ii) = Util.boundary((m_reg(ii) as Region{(self.rank == 3)}), -((0 + 1)), ((0 > 0)?1:0), ((0 > 1)?1:0), 0);
			}			if ((j == 2)) {

					temp_m_boundaries(j, ii) = Util.boundary((m_reg(ii) as Region{(self.rank == 3)}), -((1 + 1)), ((1 > 0)?1:0), ((1 > 1)?1:0), 0);
			}			if ((j == 4)) {

					temp_m_boundaries(j, ii) = Util.boundary((m_reg(ii) as Region{(self.rank == 3)}), -((2 + 1)), ((2 > 0)?1:0), ((2 > 1)?1:0), 0);
			}			if ((j == 1)) {

					temp_m_boundaries(j, ii) = Util.boundary((m_reg(ii) as Region{(self.rank == 3)}), ((0 + 1)), ((0 > 0)?1:0), ((0 > 1)?1:0), 0);
			}			if ((j == 3)) {

					temp_m_boundaries(j, ii) = Util.boundary((m_reg(ii) as Region{(self.rank == 3)}), ((1 + 1)), ((1 > 0)?1:0), ((1 > 1)?1:0), 0);
			}			if ((j == 5)) {

					temp_m_boundaries(j, ii) = Util.boundary((m_reg(ii) as Region{(self.rank == 3)}), ((2 + 1)), ((2 > 0)?1:0), ((2 > 1)?1:0), 0);
			}		}
		m_boundaries = temp_m_boundaries;
		val temp_m_u <: DistArray[Wrapper]{(self.rank == 1)} = getWrapperArray(ALLPLACES);
		m_u = temp_m_u;
		for ([k]: Point{(self.rank == 1)} in (0..(N_PLACES - 1))) {
			val disi <: Dist{(self.rank == 3)} = (temp_m_DIST(k) as Dist{(self.rank == 3)});

				temp_m_u(k) = new Wrapper(getDoubleArray(disi));
		}
	}


	public def toString(): String {
		return "";
	}

	public def getPlaces(): Dist{(self.rank == 1)} {
		return m_places;
	}

	public def getArray(val a_idx: Int): DistArray[Double]{(self.rank == 3)} {
		return ((m_u(a_idx).m_array) as DistArray[Double]{(self.rank == 3)});
	}

	public def getInnerRegion(val a_idx: Int): Region{(self.rank == 3)} {
		return (get_m_regions(a_idx) as Region{(self.rank == 3)});
	}

	public def getINNERRegion(val a_idx: Int): Region{(self.rank == 3)} {
		var R: Region{(self.rank == 3)} = (m_regions(a_idx) as Region{(self.rank == 3)});
		return ((((R.projection(0).min(0) + 1)..(R.projection(0).max(0) - 1)) * ((R.projection(1).min(0) + 1)..(R.projection(1).max(0) - 1)) * ((R.projection(2).min(0) + 1)..(R.projection(2).max(0) - 1))) as Region{(self.rank == 3)});
	}

	public def getShrinkedRegion(val a_idx: Int): Region{(self.rank == 3)} {
		var R: Region{(self.rank == 3)} = (get_m_REGIONs(a_idx) as Region{(self.rank == 3)});
		return (((R.projection(0).min(0)..(R.projection(0).max(0) - 1)) * (R.projection(1).min(0)..(R.projection(1).max(0) - 1)) * (R.projection(2).min(0)..(R.projection(2).max(0) - 1))) as Region{(self.rank == 3)});
	}

	public def getRegion(val a_idx: Int): Region{(self.rank == 3)} {
		return (m_REGIONs(a_idx) as Region{(self.rank == 3)});
	}

	private def print() {
		for ([i]: Point{(self.rank == 1)} in m_places) {
			var res: Double = 0;
			var temp: DistArray[Double]{(self.rank == 3)} = (m_u(i).m_array as DistArray[Double]{(self.rank == 3)});
			for (p: Point{(self.rank == 3)} in m_REGIONs(i)) 
				res = (res + Math.abs(temp(p)));
			Console.OUT.println(((((("block at place " + i) + " : ") + temp.region) + " sum of m_u=") + res));
		}
	}

	private def PRINT() {
		for ([i]: Point{(self.rank == 1)} in m_places) {
			Console.OUT.println((("block at place " + i) + " : "));
			var temp: DistArray[Double]{(self.rank == 3)} = (m_u(i).m_array as DistArray[Double]{(self.rank == 3)});
			var counter: Int = 0;
			for (p: Point{(self.rank == 3)} in m_regions(i)) {
				Console.OUT.print((((" [" + p) + "]= ") + temp(p)));
				counter++;
				if ((counter == 4)) {
{
						Console.OUT.print("\n");
						counter = 0;
					}				}
			}
			Console.OUT.println("");
		}
	}

	public def norm2(): Double {
		val results <: DistArray[Double]{(self.rank == 1)} = (DistArray.make[Double](m_places));
		for ([i]: Point{(self.rank == 1)} in m_places) {
			var res: Double = 0;
			val temp <: DistArray[Double]{(self.rank == 3)} = (m_u(i).m_array as DistArray[Double]{(self.rank == 3)});
			for (p: Point{(self.rank == 3)} in get_m_regions(i)) {
				val h <: Place = here;
				val dRecv <: Dist{(self.rank == 1)} = ((((0..0)->here)) as Dist{(self.rank == 1)});
				val aRecv <: DistArray[Double]{(self.rank == 1)} = (DistArray.make[Double](dRecv));
{
					val temp3 <: Double = (temp(p) * temp(p));

						aRecv(0) = temp3;
				}				res = (res + aRecv(0));
			}
			results(i) = res;
		}
		var temp: Double = (COMPILER_INSERTED_LIB.sum(results) / (((SIZE * SIZE) * SIZE)));
		return Math.sqrt(temp);
	}

	public def initialize(val a_CLASS: Int) {
		for ([i]: Point{(self.rank == 1)} in m_places) {
			val temp <: DistArray[Double]{(self.rank == 3)} = (m_u(i).m_array as DistArray[Double]{(self.rank == 3)});
			val R <: Region{(self.rank == 3)} = get_m_regions(i);
			for (p: Point{(self.rank == 3)} in temp) {
				var x: Int = p(0);
				var y: Int = p(1);
				var z: Int = p(2);

					switch (a_CLASS) {
						case 1:
							if (((((((((((((((((x == 0)) && ((y == 11))) && ((z == 2)))) || (((((x == 13)) && ((y == 8))) && ((z == 17))))) || (((((x == 5)) && ((y == 14))) && ((z == 0))))) || (((((x == 4)) && ((y == 28))) && ((z == 15))))) || (((((x == 12)) && ((y == 2))) && ((z == 1))))) || (((((x == 5)) && ((y == 17))) && ((z == 8))))) || (((((x == 20)) && ((y == 19))) && ((z == 11))))) || (((((x == 26)) && ((y == 15))) && ((z == 31))))) || (((((x == 8)) && ((y == 25))) && ((z == 22))))) || (((((x == 7)) && ((y == 14))) && ((z == 26)))))) && R.contains(p))) {

									temp(p) = -1;
							}							if (((((((((((((((((x == 7)) && ((y == 1))) && ((z == 20)))) || (((((x == 19)) && ((y == 29))) && ((z == 31))))) || (((((x == 2)) && ((y == 0))) && ((z == 3))))) || (((((x == 4)) && ((y == 22))) && ((z == 3))))) || (((((x == 1)) && ((y == 16))) && ((z == 21))))) || (((((x == 21)) && ((y == 31))) && ((z == 6))))) || (((((x == 12)) && ((y == 15))) && ((z == 12))))) || (((((x == 30)) && ((y == 4))) && ((z == 25))))) || (((((x == 28)) && ((y == 0))) && ((z == 28))))) || (((((x == 17)) && ((y == 26))) && ((z == 17)))))) && R.contains(p))) {

									temp(p) = 1;
							}							break;
						case 2:
							if (((((((((((((((((x == 38)) && ((y == 60))) && ((z == 51)))) || (((((x == 50)) && ((y == 15))) && ((z == 23))))) || (((((x == 18)) && ((y == 45))) && ((z == 36))))) || (((((x == 25)) && ((y == 14))) && ((z == 36))))) || (((((x == 26)) && ((y == 25))) && ((z == 25))))) || (((((x == 32)) && ((y == 37))) && ((z == 0))))) || (((((x == 29)) && ((y == 62))) && ((z == 54))))) || (((((x == 39)) && ((y == 49))) && ((z == 57))))) || (((((x == 12)) && ((y == 29))) && ((z == 28))))) || (((((x == 63)) && ((y == 46))) && ((z == 25)))))) && R.contains(p))) {

									temp(p) = -1;
							}							if (((((((((((((((((x == 27)) && ((y == 32))) && ((z == 45)))) || (((((x == 39)) && ((y == 0))) && ((z == 5))))) || (((((x == 45)) && ((y == 23))) && ((z == 49))))) || (((((x == 20)) && ((y == 32))) && ((z == 58))))) || (((((x == 23)) && ((y == 47))) && ((z == 57))))) || (((((x == 17)) && ((y == 43))) && ((z == 53))))) || (((((x == 8)) && ((y == 16))) && ((z == 48))))) || (((((x == 51)) && ((y == 46))) && ((z == 26))))) || (((((x == 58)) && ((y == 19))) && ((z == 62))))) || (((((x == 58)) && ((y == 15))) && ((z == 54)))))) && R.contains(p))) {

									temp(p) = 1;
							}							break;
						case 3:
						case 4:
							if (((((((((((((((((x == 221)) && ((y == 40))) && ((z == 238)))) || (((((x == 152)) && ((y == 160))) && ((z == 34))))) || (((((x == 80)) && ((y == 182))) && ((z == 253))))) || (((((x == 248)) && ((y == 168))) && ((z == 155))))) || (((((x == 197)) && ((y == 5))) && ((z == 201))))) || (((((x == 90)) && ((y == 61))) && ((z == 203))))) || (((((x == 15)) && ((y == 203))) && ((z == 30))))) || (((((x == 99)) && ((y == 154))) && ((z == 57))))) || (((((x == 100)) && ((y == 136))) && ((z == 110))))) || (((((x == 209)) && ((y == 152))) && ((z == 96)))))) && R.contains(p))) {

									temp(p) = -1;
							}							if (((((((((((((((((x == 52)) && ((y == 207))) && ((z == 38)))) || (((((x == 241)) && ((y == 170))) && ((z == 12))))) || (((((x == 201)) && ((y == 16))) && ((z == 196))))) || (((((x == 200)) && ((y == 81))) && ((z == 207))))) || (((((x == 113)) && ((y == 121))) && ((z == 205))))) || (((((x == 210)) && ((y == 5))) && ((z == 246))))) || (((((x == 43)) && ((y == 192))) && ((z == 232))))) || (((((x == 174)) && ((y == 244))) && ((z == 162))))) || (((((x == 3)) && ((y == 116))) && ((z == 173))))) || (((((x == 55)) && ((y == 118))) && ((z == 165)))))) && R.contains(p))) {

									temp(p) = 1;
							}							break;
						case 5:
							if (((((((((((((((((x == 397)) && ((y == 310))) && ((z == 198)))) || (((((x == 94)) && ((y == 399))) && ((z == 236))))) || (((((x == 221)) && ((y == 276))) && ((z == 59))))) || (((((x == 342)) && ((y == 137))) && ((z == 166))))) || (((((x == 381)) && ((y == 72))) && ((z == 281))))) || (((((x == 350)) && ((y == 192))) && ((z == 416))))) || (((((x == 16)) && ((y == 19))) && ((z == 455))))) || (((((x == 152)) && ((y == 336))) && ((z == 8))))) || (((((x == 400)) && ((y == 502))) && ((z == 447))))) || (((((x == 72)) && ((y == 0))) && ((z == 105)))))) && R.contains(p))) {

									temp(p) = -1;
							}							if (((((((((((((((((x == 308)) && ((y == 359))) && ((z == 9)))) || (((((x == 9)) && ((y == 491))) && ((z == 116))))) || (((((x == 449)) && ((y == 268))) && ((z == 441))))) || (((((x == 147)) && ((y == 115))) && ((z == 197))))) || (((((x == 241)) && ((y == 85))) && ((z == 3))))) || (((((x == 507)) && ((y == 41))) && ((z == 125))))) || (((((x == 161)) && ((y == 278))) && ((z == 73))))) || (((((x == 144)) && ((y == 91))) && ((z == 310))))) || (((((x == 201)) && ((y == 8))) && ((z == 49))))) || (((((x == 149)) && ((y == 399))) && ((z == 329)))))) && R.contains(p))) {

									temp(p) = 1;
							}							break;
						case 6:
							if (((((((((((((((((x == 186)) && ((y == 374))) && ((z == 694)))) || (((((x == 773)) && ((y == 345))) && ((z == 474))))) || (((((x == 478)) && ((y == 874))) && ((z == 804))))) || (((((x == 306)) && ((y == 75))) && ((z == 624))))) || (((((x == 397)) && ((y == 667))) && ((z == 49))))) || (((((x == 606)) && ((y == 199))) && ((z == 59))))) || (((((x == 892)) && ((y == 70))) && ((z == 361))))) || (((((x == 844)) && ((y == 261))) && ((z == 252))))) || (((((x == 221)) && ((y == 906))) && ((z == 14))))) || (((((x == 85)) && ((y == 327))) && ((z == 232)))))) && R.contains(p))) {

									temp(p) = -1;
							}							if (((((((((((((((((x == 739)) && ((y == 879))) && ((z == 781)))) || (((((x == 742)) && ((y == 641))) && ((z == 147))))) || (((((x == 335)) && ((y == 295))) && ((z == 600))))) || (((((x == 982)) && ((y == 944))) && ((z == 696))))) || (((((x == 622)) && ((y == 881))) && ((z == 180))))) || (((((x == 956)) && ((y == 217))) && ((z == 952))))) || (((((x == 777)) && ((y == 453))) && ((z == 706))))) || (((((x == 258)) && ((y == 730))) && ((z == 482))))) || (((((x == 271)) && ((y == 75))) && ((z == 815))))) || (((((x == 78)) && ((y == 276))) && ((z == 250)))))) && R.contains(p))) {

									temp(p) = 1;
							}							break;
					}
			}		}
		if (EXCHANGE_After) {
			exchange();
		}	}

	public def set(val a_db: Double) {
		for ([i]: Point{(self.rank == 1)} in m_places) {
			val temp <: DistArray[Double]{(self.rank == 3)} = (get_m_u(i).m_array as DistArray[Double]{(self.rank == 3)});
			for (p: Point{(self.rank == 3)} in get_m_regions(i)) 			temp(p) = a_db;
		}
		if (EXCHANGE_After) {
			exchange();
		}	}

	public def setLevelData(val a_LD: LevelData) {
		for ([i]: Point{(self.rank == 1)} in m_places) {
			val u <: DistArray[Double]{(self.rank == 3)} = (m_u(i).m_array as DistArray[Double]{(self.rank == 3)});
			val temp <: DistArray[Double]{(self.rank == 3)} = a_LD.getArray(i);
			val mri <: Region{(self.rank == 3)} = get_m_regions(i);
			for (p: Point{(self.rank == 3)} in mri) {
				val tp <: Double = temp(p);

					u(p) = tp;
			}
		}
		if (EXCHANGE_After) {
			exchange();
		}	}

	public def add(val a_LD: LevelData) {
		for ([i]: Point{(self.rank == 1)} in m_places) {
			val u <: DistArray[Double]{(self.rank == 3)} = (get_m_u(i).m_array as DistArray[Double]{(self.rank == 3)});
			val temp <: DistArray[Double]{(self.rank == 3)} = a_LD.getArray(i);
			for (p: Point{(self.rank == 3)} in get_m_regions(i)) 
				u(p) = (u(p) + temp(p));
		}
		if (EXCHANGE_After) {
			exchange();
		}	}

	private def Exchange() {
		for ([j]: Point{(self.rank == 1)} in (0..2)) {
			val jj <: Int = j;
			for ([i]: Point{(self.rank == 1)} in m_places) {
				val dest0 <: Int = COMPILER_INSERTED_LIB.coord(m_placeGrid,i,0);
				val dest1 <: Int = COMPILER_INSERTED_LIB.coord(m_placeGrid,i,1);
				val dest2 <: Int = COMPILER_INSERTED_LIB.coord(m_placeGrid,i,2);
				val ii <: Int = i;
				for ([p]: Point{(self.rank == 1)} in (LOW..HIGH)) {
					var disp0: Int = 0;
					var disp1: Int = 0;
					var disp2: Int = 0;
					var source0: Int = 0;
					var source1: Int = 0;
					var source2: Int = 0;
					var trans0: Int = 0;
					var trans1: Int = 0;
					var trans2: Int = 0;
					var sourceID: Int = 0;
					var k: Int = 0;
					if ((p == LOW)) {
{
							k = (jj * 2);
							disp0 = ((jj == 0)?1:0);
							disp1 = ((jj == 1)?1:0);
							disp2 = ((jj == 2)?1:0);
						}					} else {
						k = ((jj * 2) + 1);
						disp0 = ((jj == 0)?-1:0);
						disp1 = ((jj == 1)?-1:0);
						disp2 = ((jj == 2)?-1:0);
					}
					source0 = (dest0 - disp0);
					source1 = (dest1 - disp1);
					source2 = (dest2 - disp2);
					if (m_placeGrid.contains(Point.make([source0, source1, source2]))) {
{
							sourceID = COMPILER_INSERTED_LIB.ordinal(m_placeGrid,Point.make([source0, source1, source2]));
							Util.arraycopy3((m_u(ii).m_array as DistArray[Double]{(self.rank == 3)}), (get_m_boundaries(k, ii) as Region{(self.rank == 3)}), (m_u(sourceID).m_array as DistArray[Double]{(self.rank == 3)}));
						}					} else {
						if ((jj == 0)) {
{
								source0 = (dest0 + (disp0 * ((m_block0 - 1))));
								source1 = (dest1 + (disp1 * ((m_block0 - 1))));
								source2 = (dest2 + (disp2 * ((m_block0 - 1))));
							}						} else 						if ((jj == 1)) {
{
								source0 = (dest0 + (disp0 * ((m_block1 - 1))));
								source1 = (dest1 + (disp1 * ((m_block1 - 1))));
								source2 = (dest2 + (disp2 * ((m_block1 - 1))));
							}						} else {
							source0 = (dest0 + (disp0 * ((m_block2 - 1))));
							source1 = (dest1 + (disp1 * ((m_block2 - 1))));
							source2 = (dest2 + (disp2 * ((m_block2 - 1))));
						}
						sourceID = COMPILER_INSERTED_LIB.ordinal(m_placeGrid,Point.make([source0, source1, source2]));
						if ((jj == 0)) {
{
								trans0 = (disp0 * m_size0);
								trans1 = (disp1 * m_size0);
								trans2 = (disp2 * m_size0);
							}						} else 						if ((jj == 1)) {
{
								trans0 = (disp0 * m_size1);
								trans1 = (disp1 * m_size1);
								trans2 = (disp2 * m_size1);
							}						} else {
							trans0 = (disp0 * m_size2);
							trans1 = (disp1 * m_size2);
							trans2 = (disp2 * m_size2);
						}
						Util.arraycopy4((m_u(ii).m_array as DistArray[Double]{(self.rank == 3)}), (m_boundaries(k, ii) as Region{(self.rank == 3)}), (m_u(sourceID).m_array as DistArray[Double]{(self.rank == 3)}), trans0, trans1, trans2);
					}
				}
			}
		}
	}

	public def exchange() {

			for ([j]: Point{(self.rank == 1)} in (0..2)) 
				for ([i]: Point{(self.rank == 1)} in m_places) {
					val jj <: Int = j;
					val dest0 <: Int = COMPILER_INSERTED_LIB.coord(m_placeGrid,i,0);
					val dest1 <: Int = COMPILER_INSERTED_LIB.coord(m_placeGrid,i,1);
					val dest2 <: Int = COMPILER_INSERTED_LIB.coord(m_placeGrid,i,2);
					val ii <: Int = i;
					for ([p]: Point{(self.rank == 1)} in (LOW..HIGH)) {
						var disp0: Int = 0;
						var disp1: Int = 0;
						var disp2: Int = 0;
						var trans0: Int = 0;
						var trans1: Int = 0;
						var trans2: Int = 0;
						var sourceID: Int = 0;
						var k: Int = 0;
						if ((p == LOW)) {
{
								k = (jj * 2);
								disp0 = ((jj == 0)?1:0);
								disp1 = ((jj == 1)?1:0);
								disp2 = ((jj == 2)?1:0);
							}						} else {
							k = ((jj * 2) + 1);
							disp0 = ((jj == 0)?-1:0);
							disp1 = ((jj == 1)?-1:0);
							disp2 = ((jj == 2)?-1:0);
						}
						var source0: Int = (dest0 - disp0);
						var source1: Int = (dest1 - disp1);
						var source2: Int = (dest2 - disp2);
						if (m_placeGrid.contains(Point.make([source0, source1, source2]))) {
{
								sourceID = COMPILER_INSERTED_LIB.ordinal(m_placeGrid,Point.make([source0, source1, source2]));
								var ma: DistArray[Double]{(self.rank == 3)} = (get_m_u(ii).m_array as DistArray[Double]{(self.rank == 3)});
								Util.arraycopy3((get_m_u(ii).m_array as DistArray[Double]{(self.rank == 3)}), (get_m_boundaries(k, ii) as Region{(self.rank == 3)}), (get_m_u(sourceID).m_array as DistArray[Double]{(self.rank == 3)}));
							}						} else {
							if ((jj == 0)) {
{
									source0 = (dest0 + (disp0 * ((m_block0 - 1))));
									source1 = (dest1 + (disp1 * ((m_block0 - 1))));
									source2 = (dest2 + (disp2 * ((m_block0 - 1))));
								}							} else 							if ((jj == 1)) {
{
									source0 = (dest0 + (disp0 * ((m_block1 - 1))));
									source1 = (dest1 + (disp1 * ((m_block1 - 1))));
									source2 = (dest2 + (disp2 * ((m_block1 - 1))));
								}							} else {
								source0 = (dest0 + (disp0 * ((m_block2 - 1))));
								source1 = (dest1 + (disp1 * ((m_block2 - 1))));
								source2 = (dest2 + (disp2 * ((m_block2 - 1))));
							}
							sourceID = COMPILER_INSERTED_LIB.ordinal(m_placeGrid,Point.make([source0, source1, source2]));
							if ((jj == 0)) {
{
									trans0 = (disp0 * m_size0);
									trans1 = (disp1 * m_size0);
									trans2 = (disp2 * m_size0);
								}							} else 							if ((jj == 1)) {
{
									trans0 = (disp0 * m_size1);
									trans1 = (disp1 * m_size1);
									trans2 = (disp2 * m_size1);
								}							} else {
								trans0 = (disp0 * m_size2);
								trans1 = (disp1 * m_size2);
								trans2 = (disp2 * m_size2);
							}
							Util.arraycopy4((get_m_u(ii).m_array as DistArray[Double]{(self.rank == 3)}), (get_m_boundaries(k, ii) as Region{(self.rank == 3)}), (get_m_u(sourceID).m_array as DistArray[Double]{(self.rank == 3)}), trans0, trans1, trans2);
						}
					}
				}
	}

	@NonEscaping final
	public def getDist3Array(val d: Dist{(self.rank == 1)}): DistArray[Dist{(self.rank == 3)}]{(self.rank == 1)} {
		return ((DistArray.make[Dist{(self.rank == 3)}](d)) as DistArray[Dist{(self.rank == 3)}]{(self.rank == 1)});
	}

	@NonEscaping final
	public def getRegion3Array1(val d: Dist{(self.rank == 1)}): DistArray[Region{(self.rank == 3)}]{(self.rank == 1)} {
		return ((DistArray.make[Region{(self.rank == 3)}](d)) as DistArray[Region{(self.rank == 3)}]{(self.rank == 1)});
	}

	@NonEscaping final
	public def getRegion3Array2(val d: Dist{(self.rank == 2)}): DistArray[Region{(self.rank == 3)}]{(self.rank == 2)} {
		return ((DistArray.make[Region{(self.rank == 3)}](d)) as DistArray[Region{(self.rank == 3)}]{(self.rank == 2)});
	}

	@NonEscaping final
	private def getWrapperArray(val d: Dist{(self.rank == 1)}): DistArray[Wrapper]{(self.rank == 1)} {
		return (DistArray.make[Wrapper](d));
	}

	@NonEscaping final
	private def getDoubleArray(val d: Dist{(self.rank == 3)}): DistArray[Double]{(self.rank == 3)} {
		return (DistArray.make[Double](d, (pp:Point{(self.rank == 3)}) => {
			return 0.0;
		}));
	}

	private def get_m_regions(val i: Int): Region{(self.rank == 3)} {
		val h <: Place = here;
		val dRecv <: Dist{(self.rank == 1)} = ((((0..0)->here)) as Dist{(self.rank == 1)});
		val aRecv <: DistArray[Region{(self.rank == 3)}]{(self.rank == 1)} = ((DistArray.make[Region{(self.rank == 3)}](dRecv)) as DistArray[Region{(self.rank == 3)}]{(self.rank == 1)});
{
			val temp3 <: Region{(self.rank == 3)} = (m_regions(i) as Region{(self.rank == 3)});

				aRecv(0) = temp3;
		}		val mri <: Region{(self.rank == 3)} = (aRecv(0) as Region{(self.rank == 3)});
		return mri;
	}

	private def get_m_REGIONs(val i: Int): Region{(self.rank == 3)} {
		val h <: Place = here;
		val dRecv <: Dist{(self.rank == 1)} = ((((0..0)->here)) as Dist{(self.rank == 1)});
		val aRecv <: DistArray[Region{(self.rank == 3)}]{(self.rank == 1)} = ((DistArray.make[Region{(self.rank == 3)}](dRecv)) as DistArray[Region{(self.rank == 3)}]{(self.rank == 1)});
{
			val temp3 <: Region{(self.rank == 3)} = (m_REGIONs(i) as Region{(self.rank == 3)});

				aRecv(0) = temp3;
		}		val mri <: Region{(self.rank == 3)} = (aRecv(0) as Region{(self.rank == 3)});
		return mri;
	}

	private def get_m_boundaries(val i: Int, val j: Int): Region{(self.rank == 3)} {
		val h <: Place = here;
		val dRecv <: Dist{(self.rank == 1)} = ((((0..0)->here)) as Dist{(self.rank == 1)});
		val aRecv <: DistArray[Region{(self.rank == 3)}]{(self.rank == 1)} = ((DistArray.make[Region{(self.rank == 3)}](dRecv)) as DistArray[Region{(self.rank == 3)}]{(self.rank == 1)});
{
			val temp4 <: Region{(self.rank == 3)} = (m_boundaries(i, j) as Region{(self.rank == 3)});

				aRecv(0) = temp4;
		}		val mri <: Region{(self.rank == 3)} = (aRecv(0) as Region{(self.rank == 3)});
		return mri;
	}

	private def get_m_u(val i: Int): Wrapper {
		val h <: Place = here;
		val dRecv <: Dist{(self.rank == 1)} = ((((0..0)->here)) as Dist{(self.rank == 1)});
		val aRecv <: DistArray[Wrapper]{(self.rank == 1)} = ((DistArray.make[Wrapper](dRecv)) as DistArray[Wrapper]{(self.rank == 1)});
{
			val temp4 <: Wrapper = m_u(i);

				aRecv(0) = temp4;
		}		val mri <: Wrapper = aRecv(0);
		return mri;
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


class COMPILER_INSERTED_LIB {

	public static def coord(region: Region, pointIndex: Int, coordIndex: Int): Int {
		val iterator = (region.iterator() as (Iterator[Point]));
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
		val iterator = (region.iterator() as (Iterator[Point]));
		var i: Int = 0;
		while (iterator.hasNext()) {
			val p = iterator.next();
			if (point.equals(p))
				return i;
			i = i + 1;
		}
		return -1;
	}

	private static def traverseAll(array: DistArray[Int], fun: (Int, Int) => (Int)): Int {
		val places = array.dist.places();
		//var region: Region(1) = Region.makeEmpty(1);
		//for (place in places) {
			//region = region + [place.id];
			//region = region + Point.make(place.id);
			// "Region +" does not work in this implementation!
			//region = region.union(Region.make(place.id, place.id));
		//}
		val region = 0..(places.size()-1);
		val centralArray = DistArray.make[Int](region->here, (p:Point(1)) => 0);
		val center = here;
		finish {
			var i: Int = -1;
			for (place in places) {
			i = i + 1;
			val j = i;
				async at (place) {
					val localPart = array | here;
					var result: Int = 0;
					for (point in localPart)
						result = fun(result, array(point));
					val finalResult = result;
					async at (center) {
						centralArray(Point.make(j)) = finalResult;
					}
				}
			}
		}
		var result: Int = 0;
		for (point in centralArray.region)
			result += centralArray(point);
		return result;
	}
	private static def traverseAll(array: DistArray[Double], fun: (Double, Double) => (Double)): Double {
		val places = array.dist.places();
		//var region: Region(1) = Region.makeEmpty(1);
		//for (place in places) {
			//region = region + [place.id];
			//region = region + Point.make(place.id);
			// "Region +" does not work in this implementation!
			//region = region.union(Region.make(place.id, place.id));
		//}
		val region = 0..(places.size()-1);
		val centralArray = DistArray.make[Double](region->here, (p:Point(1)) => 0.0);
		val center = here;
		finish {
			var i: Int = -1;
			for (place in places) {
			i = i + 1;
			val j = i;
				async at (place) {
					val localPart = array | here;
					var result: Double = 0;
					for (point in localPart)
						result = fun(result, array(point));
					val finalResult = result;
					async at (center) {
						centralArray(Point.make(j)) = finalResult;
					}
				}
			}
		}
		var result: Double = 0;
		for (point:Point(1) in centralArray.region)
			result += centralArray(point);
		return result;
	}

	public static def sum(array: DistArray[Int]): Int {
		return traverseAll(array, (i1: Int, i2: Int) => i1 + i2);
	}
	public static def sum(array: DistArray[Double]): Double {
		return traverseAll(array, (i1: Double, i2: Double) => i1 + i2);
	}
	public static def max(array: DistArray[Int]): Int {
		return traverseAll(array, (i1: Int, i2: Int) => Math.max(i1,i2));
	}
	public static def max(array: DistArray[Double]): Double {
		return traverseAll(array, (i1: Double, i2: Double) => Math.max(i1,i2));
	}
}