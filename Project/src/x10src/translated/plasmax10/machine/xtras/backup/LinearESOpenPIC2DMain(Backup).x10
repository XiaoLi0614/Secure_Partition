class PoissonSolver {
	public static val PI4I: Double = (0.5 / 6.28318530717959);
	public static val SQT2I: Double = 0.707106781186548;
	public static val SQT2PI: Double = 0.797884560802865;
	public static val GREENS_IDX: Int = 0;
	public static val SHAPE_IDX: Int = 1;
	public static val ELECX_IDX: Int = 2;
	public static val ELECY_IDX: Int = 3;
	public static val AR: Double = 0.912871;
	public val x: Int;
	public val y: Int;
	public val xBits: Int;
	public val yBits: Int;
	public val ari: Double;
	public val affp: Double;
	public val fft: FourierTransform2D;
	public val ffg: Array[Double]{(self.rank == 3)};
	public val ffg2: Array[Double]{(self.rank == 2)};

	public def this(val xBits: Int, val yBits: Int, val affp: Double) {
		this.xBits = xBits;
		this.yBits = yBits;
		this.affp = affp;
		val x0: Int = (1 << xBits);
		val y0: Int = (1 << yBits);
		x = x0;
		y = y0;
		ari = 0;
		val fft0: FourierTransform2D{self.home==this.home} = (new FourierTransform2D((yBits + 1), (xBits + 1))) as FourierTransform2D{self.home==this.home};
		fft = fft0;
		val tables: Array[Double]{(self.rank == 3)} = initTables(fft0, x0, y0, affp);
		ffg = makeFFG(tables, x0, y0);
		ffg2 = makeFFG2(tables, x0, y0);
		Console.OUT.println("Poisson Solver initialized");
	}


	public proto def initTables(val fft: FourierTransform2D{self.home==this.home}, val x: Int, val y: Int, val affp: Double): Array[Double]{(self.rank == 3)} {
		val r1: Region{(self.rank == 1)} = Region.make([(0..(x - 1))]);
		val r2: Region{(self.rank == 1)} = Region.make([(0..y)]);
		val r3: Region{(self.rank == 1)} = Region.make([(0..3)]);
		val dTable1: Dist{(self.rank == 3)} = X10Util.extendDistRank3D(X10Util.distBlockStar2(r1, r2), r3);
		val first: Place = Place.FIRST_PLACE;
		val dTable2: Dist{(self.rank == 3)} = (((Region.make([(x..x), (0..y), (0..3)])->first)) as Dist{(self.rank == 3)});
		val dTable: Dist{(self.rank == 3)} = (dTable1 || dTable2);
		val table: Array[Double]{(self.rank == 3)} = (Array.make[Double](dTable));
		Console.OUT.println("Init: Green's function");
		initGreensFnTable(table, fft, x, y, affp);
		Console.OUT.println("Init: Shape factor");
		initShapeFactorTable(table, fft, x, y, affp);
		Console.OUT.println("Init: X Electric field");
		initElectricFieldXTable(table, fft, x, y, affp);
		Console.OUT.println("Init: Y Electric field");
		initElectricFieldYTable(table, fft, x, y, affp);
		return table;
	}

	public proto def makeFFG(val table: Array[Double]{(self.rank == 3)}, val x: Int, val y: Int): Array[Double]{(self.rank == 3)} {
		val r1: Region{(self.rank == 1)} = Region.make([(0..(x - 1))]);
		val r2: Region{(self.rank == 1)} = Region.make([(0..y)]);
		val r3: Region{(self.rank == 1)} = Region.make([(0..3)]);
		val dResult: Dist{(self.rank == 3)} = X10Util.extendDistRank3D(X10Util.distBlockStar2(r1, r2), r3);
		return (Array.make[Double](dResult, (pt:Point{(self.rank == 3)}) => {
			return table(pt);
		}));
	}

	public proto def makeFFG2(val table: Array[Double]{(self.rank == 3)}, val x: Int, val y: Int): Array[Double]{(self.rank == 2)} {
		val rResult: Region{(self.rank == 2)} = Region.make([(0..y), (0..3)]);
		return ((Array.make[Double](rResult, ((i, j):Point{(self.rank == 2)}) => {
			val pHere: Place = here;
			val dDst: Dist{(self.rank == 1)} = (Region.make([(0..0)])->here);
			val rcv: Array[Double]{(self.rank == 1)} = (Array.make[Double](dDst));
			finish 			async (Place.FIRST_PLACE) {
				val val: Double = table(x, i, j);
				finish 				async (pHere) {
					rcv(0) = val;
				}

			}

			return rcv(0);
		})) as Array[Double]{(self.rank == 2)});
	}

	public proto def initGreensFnTable(val table: Array[Double]{(self.rank == 3)}, val fft: FourierTransform2D{self.home==this.home}, val x: Int, val y: Int, val affp: Double): Void {
		val r1: Region{(self.rank == 1)} = Region.make([(0..((2 * y) - 1))]);
		val r2: Region{(self.rank == 1)} = Region.make([(0..((2 * x) - 1))]);
		val dGreen: Dist{(self.rank == 2)} = X10Util.distBlockStar2(r1, r2);
		val f: Array[Double]{(self.rank == 2)} = (Array.make[Double](dGreen));
		val invRadius: Double = ((AR > 0.0))?(1.0 / AR):0.0;
		val N: Double = ((4 * x) * y);
		finish 		ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			if ((pl(0) <= (2 * y))) {
				val rLocal: Region{(self.rank == 2)} = X10Util.get2DLRegion(dGreen);
				foreach (p: Point{(self.rank == 2)} in rLocal) {
					val x1: Double = Math.pow(((p(0) > y))?(p(0) - ((2 * y))):p(0), 2);
					val x2: Double = Math.pow(((p(1) > x))?(p(1) - ((2 * x))):p(1), 2);
					val r: Double = Math.sqrt((x1 + x2));
					f(p) = potentialField(r, invRadius, affp);
				}
			}
		}

		val ft: Array[Complex]{(self.rank == 2)} = (fft as FourierTransform2D{self.home==this.home}).inverseTransformD(f);
		genTable1(table, ft, GREENS_IDX, x, y);
	}


	public proto def initShapeFactorTable(val table: Array[Double]{(self.rank == 3)}, val fft: FourierTransform2D{self.home==this.home}, val x: Int, val y: Int, val affp: Double): Void {
		val r1: Region{(self.rank == 1)} = Region.make([(0..((2 * y) - 1))]);
		val r2: Region{(self.rank == 1)} = Region.make([(0..((2 * x) - 1))]);
		val dShape: Dist{(self.rank == 2)} = X10Util.distBlockStar2(r1, r2);
		val f: Array[Double]{(self.rank == 2)} = (Array.make[Double](dShape));
		val invRadius: Double = ((AR > 0.0))?(1.0 / AR):0.0;
		val N: Double = ((4 * x) * y);
		finish 		ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			if ((pl(0) <= (2 * y))) {
				val rLocal: Region{(self.rank == 2)} = X10Util.get2DLRegion(dShape);
				foreach (p: Point{(self.rank == 2)} in rLocal) {
					val x1: Double = Math.pow(((p(0) > y))?(p(0) - ((2 * y))):p(0), 2);
					val x2: Double = Math.pow(((p(1) > x))?(p(1) - ((2 * x))):p(1), 2);
					val r: Double = Math.sqrt((x1 + x2));
					f(p) = particleShape(r, invRadius, affp);
				}
			}
		}

		val ft: Array[Complex]{(self.rank == 2)} = (fft as FourierTransform2D{self.home==here}).inverseTransformD(f);
		genTable1(table, ft, SHAPE_IDX, x, y);
	}


	public proto def initElectricFieldXTable(val table: Array[Double]{(self.rank == 3)}, val fft: FourierTransform2D, val x: Int, val y: Int, val affp: Double): Void {
		val r1: Region{(self.rank == 1)} = Region.make([(0..((2 * y) - 1))]);
		val r2: Region{(self.rank == 1)} = Region.make([(0..((2 * x) - 1))]);
		val dElecX: Dist{(self.rank == 2)} = X10Util.distBlockStar2(r1, r2);
		val f: Array[Double]{(self.rank == 2)} = (Array.make[Double](dElecX));
		val invRadius: Double = ((AR > 0.0))?(1.0 / AR):0.0;
		val N: Double = ((4 * x) * y);
		finish 		ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			if ((pl(0) <= (2 * y))) {
				val rLocal: Region{(self.rank == 2)} = X10Util.get2DLRegion(dElecX);
				foreach (p: Point{(self.rank == 2)} in rLocal) {
					val x1: Double = ((p(0) > y))?(p(0) - ((2 * y))):p(0);
					val xs1: Double = Math.pow(x1, 2);
					val x2: Double = ((p(1) > x))?(p(1) - ((2 * x))):p(1);
					val xs2: Double = Math.pow(x2, 2);
					val r: Double = Math.sqrt((xs1 + xs2));
					val val: Double = radialElectricField(r, invRadius, affp);
					f(p) = ((r > 0.0))?(val * ((x2 / r))):val;
				}
			}
		}

		val ft: Array[Complex]{(self.rank == 2)} = fft.inverseTransformD(f);
		genTable2(table, ft, ELECX_IDX, x, y);
	}

	public proto def initElectricFieldYTable(val table: Array[Double]{(self.rank == 3)}, val fft: FourierTransform2D, val x: Int, val y: Int, val affp: Double): Void {
		val r1: Region{(self.rank == 1)} = Region.make([(0..((2 * y) - 1))]);
		val r2: Region{(self.rank == 1)} = Region.make([(0..((2 * x) - 1))]);
		val dElecX: Dist{(self.rank == 2)} = X10Util.distBlockStar2(r1, r2);
		val f: Array[Double]{(self.rank == 2)} = (Array.make[Double](dElecX));
		val invRadius: Double = ((AR > 0.0))?(1.0 / AR):0.0;
		val N: Double = ((4 * x) * y);
		finish 		ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			if ((pl(0) <= (2 * y))) {
				val rLocal: Region{(self.rank == 2)} = X10Util.get2DLRegion(dElecX);
				foreach (p: Point{(self.rank == 2)} in rLocal) {
					val y1: Double = ((p(0) > y))?(p(0) - ((2 * y))):p(0);
					val ys1: Double = Math.pow(y1, 2);
					val y2: Double = ((p(1) > x))?(p(1) - ((2 * x))):p(1);
					val ys2: Double = Math.pow(y2, 2);
					val r: Double = Math.sqrt((ys1 + ys2));
					val val: Double = radialElectricField(r, invRadius, affp);
					f(p) = ((r > 0.0))?(val * ((y1 / r))):val;
				}
			}
		}

		val ft: Array[Complex]{(self.rank == 2)} = fft.inverseTransformD(f);
		genTable3(table, ft, ELECY_IDX, x, y);
	}

	public proto def genTable1(val table: Array[Double]{(self.rank == 3)}, val ft: Array[Complex]{(self.rank == 2)}, val index: Int, val x: Int, val y: Int): Void {
		val N: Int = ((4 * x) * y);
		finish 		ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			if ((pl(0) <= x)) {
				val dFT: Dist{(self.rank == 2)} = ft.dist;
				val dTable: Dist{(self.rank == 3)} = table.dist;
				val rLocalFT1: Region{(self.rank == 1)} = X10Util.getLRank2D(dFT, 0);
				val rLocalT2: Region{(self.rank == 1)} = X10Util.getLRank3D(dTable, 1);
				foreach ((i, j): Point{(self.rank == 2)} in Region.make([rLocalFT1, rLocalT2])) {
					table(i, j, index) = (N * ft(i, j).getReal());
				}
				if (here.isFirst()) {
					foreach ((i): Point{(self.rank == 1)} in Region.make([(1..(y - 1))])) {
						table(x, i, index) = (N * ft(0, ((2 * y) - i)).getReal());
					}
					table(x, 0, index) = (N * ft(0, 0).getImag());
					table(x, y, index) = (N * ft(0, y).getImag());
				}
			}
		}

	}

	public proto def genTable2(val table: Array[Double]{(self.rank == 3)}, val ft: Array[Complex]{(self.rank == 2)}, val index: Int, val x: Int, val y: Int): Void {
		val N: Int = ((4 * x) * y);
		finish 		ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			if ((pl(0) <= x)) {
				val dFT: Dist{(self.rank == 2)} = ft.dist;
				val dTable: Dist{(self.rank == 3)} = table.dist;
				val rLocalFT1: Region{(self.rank == 1)} = X10Util.getLRank2D(dFT, 0);
				val rLocalT2: Region{(self.rank == 1)} = X10Util.getLRank3D(dTable, 1);
				foreach ((i): Point{(self.rank == 1)} in rLocalFT1) {
					if ((i > 0)) {
						foreach ((j): Point{(self.rank == 1)} in rLocalT2) {
							table(i, j, index) = (N * ft(i, j).getImag());
						}
					}
				}
				if (here.isFirst()) {
					foreach ((i1): Point{(self.rank == 1)} in Region.make([(1..(y - 1))])) {
						val i2: Int = ((2 * y) - i1);
						table(0, i1, index) = (N * ft(0, i1).getReal());
						table(x, i1, index) = (N * ft(0, i2).getReal());
					}
					table(0, 0, index) = (N * ft(0, 0).getReal());
					table(x, 0, index) = (N * ft(0, 0).getImag());
					table(0, y, index) = (N * ft(0, y).getReal());
					table(x, y, index) = (N * ft(0, y).getImag());
				}
			}
		}

	}

	public proto def genTable3(val table: Array[Double]{(self.rank == 3)}, val ft: Array[Complex]{(self.rank == 2)}, val index: Int, val x: Int, val y: Int): Void {
		val N: Int = ((4 * x) * y);
		finish 		ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			if ((pl(0) <= x)) {
				val dFT: Dist{(self.rank == 2)} = ft.dist;
				val dTable: Dist{(self.rank == 3)} = table.dist;
				val rLocalFT1: Region{(self.rank == 1)} = X10Util.getLRank2D(dFT, 0);
				val rLocalT2: Region{(self.rank == 1)} = X10Util.getLRank3D(dTable, 1);
				foreach ((i): Point{(self.rank == 1)} in rLocalFT1) {
					if ((i > 0)) {
						foreach ((j): Point{(self.rank == 1)} in Region.make([(1..(y - 1))])) {
							table(i, j, index) = (N * ft(i, j).getImag());
						}
						table(i, 0, index) = (N * ft(i, 0).getReal());
						table(i, y, index) = (N * ft(i, y).getReal());
					}
				}
				if (here.isFirst()) {
					foreach ((i1): Point{(self.rank == 1)} in Region.make([(1..(y - 1))])) {
						val i2: Int = ((2 * y) - i1);
						table(0, i1, index) = (N * ft(0, i1).getImag());
						table(x, i1, index) = (N * ft(0, i2).getImag());
					}
					table(0, 0, index) = (N * ft(0, 0).getReal());
					table(x, 0, index) = (N * ft(0, 0).getImag());
					table(0, y, index) = (N * ft(0, y).getReal());
					table(x, y, index) = (N * ft(0, y).getImag());
				}
			}
		}

	}

	public def makeForceArray(): Array[Complex]{(self.rank == 2)} {
		val dResult: Dist{(self.rank == 2)} = X10Util.distBlockStar1(Region.make([(0..((2 * y) - 1)), (0..(x - 1))]));
		return (Array.make[Complex](dResult));
	}

	public def getForceCharge(val q: Array[Complex]{(self.rank == 2)}, val forceX: Array[Complex]{(self.rank == 2)}, val forceY: Array[Complex]{(self.rank == 2)}): Double {
		val dForce: Dist{(self.rank == 2)} = X10Util.distBlockStar1(Region.make([(0..(x - 1)), (0..((2 * y) - 1))]));
		val fxfft: Array[Complex]{(self.rank == 2)} = (Array.make[Complex](dForce));
		val fyfft: Array[Complex]{(self.rank == 2)} = (Array.make[Complex](dForce));
		val qfft: Array[Complex]{(self.rank == 2)} = fft.inverseTransformC(q);
		val we: Double = solveForceCharge(qfft, fxfft, fyfft);
		val fx: Array[Complex]{(self.rank == 2)} = fft.transformC(fxfft);
		val fy: Array[Complex]{(self.rank == 2)} = fft.transformC(fyfft);
		finish 		ateach (pt: Point{(self.rank == 2)} in forceX) {
			forceX(pt) = fx(pt);
			forceY(pt) = fy(pt);
		}

		return we;
	}

	public def solveForceCharge(val q: Array[Complex]{(self.rank == 2)}, val forceX: Array[Complex]{(self.rank == 2)}, val forceY: Array[Complex]{(self.rank == 2)}): Double {
		val dUnique: Dist{(self.rank == 1)} = (Dist.makeUnique() as Dist{(self.rank == 1)});
		val energy: Array[Double]{(self.rank == 1)} = (Array.make[Double](dUnique, (pt:Point{(self.rank == 1)}) => {
			return 0.0;
		}));
		for (pl: Point{(self.rank == 1)} in Dist.makeUnique()) 		finish 		async (Dist.makeUnique()(pl)) {
			if ((pl(0) <= x)) {
				val dFFG: Dist{(self.rank == 3)} = ffg.dist;
				val rLocal: Region{(self.rank == 3)} = X10Util.get3DLRegion(dFFG);
				val rLocal1: Region{(self.rank == 1)} = X10Util.getRank3D(rLocal, 0);
				val rLocal2: Region{(self.rank == 1)} = X10Util.getRank3D(rLocal, 1);
				var tmp3: Double = -1.0;
				for ((i): Point{(self.rank == 1)} in rLocal1) {
					tmp3 = (((i % 2) == 0))?1.0:-1.0;
					if ((i > 0)) {
						var tmp2: Double = ffg(i, 0, ELECY_IDX);
						for ((j1): Point{(self.rank == 1)} in Region.make([(1..(y - 1))])) {
							val j2: Int = ((2 * y) - j1);
							val tmp0: Double = ((here.isFirst())?ffg(0, j1, ELECX_IDX):ffg2(j1, ELECX_IDX));
							val tmp1: Double = (tmp3 * tmp0);
							tmp2 = -tmp2;
							val cx: Complex = new Complex(tmp1, ffg(i, j1, ELECX_IDX));
							val cy: Complex = new Complex(tmp2, ffg(i, j1, ELECY_IDX));
							forceX(i, j1) = cx.mult(q(i, j1));
							forceX(i, j2) = cx.mult(q(i, j2));
							forceY(i, j1) = cy.mult(q(i, j1));
							forceY(i, j2) = (cy.getConjg()).mult(q(i, j2));
							energy(pl) = (energy(pl) + ((ffg(i, j1, GREENS_IDX) * ((q(i, j1).multConjg() + q(i, j2).multConjg())))));
						}
						var tmp0: Double = ((here.isFirst())?ffg(0, 0, ELECX_IDX):ffg2(0, ELECX_IDX));
						var tmp1: Double = (tmp3 * tmp0);
						val x0: Complex = new Complex(tmp1, ffg(i, 0, ELECX_IDX));
						forceX(i, 0) = x0.mult(q(i, 0));
						forceY(i, 0) = q(i, 0).multS(ffg(i, 0, ELECY_IDX));
						energy(pl) = (energy(pl) + ((ffg(i, 0, GREENS_IDX) * q(i, 0).multConjg())));
						tmp0 = ((here.isFirst())?ffg(0, y, ELECX_IDX):ffg2(y, ELECX_IDX));
						tmp1 = (tmp3 * tmp0);
						val xNY: Complex = new Complex(tmp1, ffg(i, y, ELECX_IDX));
						forceX(i, y) = xNY.mult(q(i, y));
						forceY(i, y) = q(i, y).multS(ffg(i, y, ELECY_IDX));
						energy(pl) = (energy(pl) + ((ffg(i, y, GREENS_IDX) * q(i, y).multConjg())));
					}
				}
				if (here.isFirst()) {
					tmp3 = ffg(0, 0, ELECY_IDX);
					for ((j): Point{(self.rank == 1)} in Region.make([(1..(y - 1))])) {
						tmp3 = -tmp3;
						val s: Complex = new Complex(tmp3, ffg(0, j, ELECY_IDX));
						forceX(0, j) = q(0, j).multS(ffg(0, j, ELECX_IDX));
						forceY(0, j) = s.mult(q(0, j));
						energy(pl) = (energy(pl) + ((ffg(0, j, GREENS_IDX) * q(0, j).multConjg())));
					}
					tmp3 = ffg2(0, ELECY_IDX);
					for ((j1): Point{(self.rank == 1)} in Region.make([(1..(y - 1))])) {
						tmp3 = -tmp3;
						val j2: Int = ((2 * y) - j1);
						val s: Complex = new Complex(tmp3, ffg2(j1, ELECY_IDX));
						forceX(0, j2) = q(0, j2).multS(ffg(0, j1, ELECX_IDX));
						forceY(0, j2) = s.mult(q(0, j2));
						energy(pl) = (energy(pl) + ((ffg2(j1, GREENS_IDX) * q(0, j2).multConjg())));
					}
					val q00R: Double = q(0, 0).getReal();
					val q00I: Double = q(0, 0).getImag();
					forceX(0, 0) = new Complex((ffg(0, 0, ELECX_IDX) * q00R), (ffg2(0, ELECX_IDX) * q00I));
					forceY(0, 0) = new Complex((ffg(0, 0, ELECY_IDX) * q00R), (ffg2(0, ELECY_IDX) * q00I));
					energy(pl) = (energy(pl) + (0.5 * ((((ffg(0, 0, GREENS_IDX) * Math.pow(q00R, 2))) + ((ffg2(0, GREENS_IDX) * Math.pow(q00I, 2)))))));
					val q0NYR: Double = q(0, y).getReal();
					val q0NYI: Double = q(0, y).getImag();
					forceX(0, y) = new Complex((ffg(0, y, ELECX_IDX) * q0NYR), (ffg2(y, ELECX_IDX) * q0NYI));
					forceY(0, y) = new Complex((ffg(0, y, ELECY_IDX) * q0NYR), (ffg2(y, ELECY_IDX) * q0NYI));
					energy(pl) = (energy(pl) + (0.5 * ((((ffg(0, y, GREENS_IDX) * Math.pow(q0NYR, 2))) + ((ffg2(y, GREENS_IDX) * Math.pow(q0NYI, 2)))))));
				}
			}
		}


		return (((4.0 * x) * y) * COMPILER_INSERTED_LIB.sum(energy));
	}

	public global proto def potentialField(val r: Double, val ari: Double, val affp: Double): Double {
		val anorm: Double = (affp * PI4I);
		if ((ari > 0)) {
			if ((r == 0.0)) {
				return ((anorm * SQT2PI) * ari);
			} else {
				return ((anorm * errorFn(((r * SQT2I) * ari))) / r);
			}
		} else {
			if ((r == 0.0)) {
				return 0.0;
			} else {
				return (anorm / r);
			}
		}
	}

	public proto def particleShape(val r: Double, val ari: Double, val affp: Double): Double {
		val anorm: Double = (affp * Math.pow(((0.5 * SQT2PI) * ari), 2));
		if ((ari > 0.0)) {
			if ((r == 0.0)) {
				return anorm;
			} else {
				val at1: Double = X10Util.minDouble(((r * SQT2I) * ari), 8.0);
				return (anorm * Math.exp(-((at1 * at1))));
			}
		} else {
			if ((r == 0.0)) {
				return affp;
			} else {
				return 0.0;
			}
		}
	}

	public proto def radialElectricField(val r: Double, val ari: Double, val affp: Double): Double {
		val anorm: Double = (affp * PI4I);
		if ((ari > 0.0)) {
			if ((r == 0.0)) {
				return 0.0;
			} else {
				val ri: Double = (1.0 / r);
				val at1: Double = X10Util.minDouble(((r * SQT2I) * ari), 8.0);
				return ((anorm * ri) * (((errorFn(at1) * ri) - ((SQT2PI * ari) * Math.exp(-((at1 * at1)))))));
			}
		} else {
			if ((r == 0.0)) {
				return 0.0;
			} else {
				return (anorm / ((r * r)));
			}
		}
	}

	public proto def errorFn(val x: Double): Double {
		val p: Double = 0.3275911;
		val a1: Double = 0.254829592;
		val a2: Double = -0.284496736;
		val a3: Double = 1.421413741;
		val a4: Double = -1.453152027;
		val a5: Double = 1.061405429;
		val f: Double = Math.abs(x);
		val t: Double = (1.0 / ((1.0 + (p * f))));
		var ret: Double = 0.0;
		if ((f <= 8.0)) {
			ret = (1.0 - ((t * ((a1 + (t * ((a2 + (t * ((a3 + (t * ((a4 + (t * a5))))))))))))) * Math.exp((-x * x))));
		} else {
			ret = 1.0;
		}
		if ((x < 0)) {
			ret = -ret;
		}
		return ret;
	}

	public def expIntNL(val x: Double): Double {
		val a0: Double = -0.57721566;
		val a1: Double = 0.99999193;
		val a2: Double = -0.24991055;
		val a3: Double = 0.05519968;
		val a4: Double = -0.00976004;
		val a5: Double = 0.00107857;
		val b1: Double = 8.5733287401;
		val b2: Double = 18.0590169730;
		val b3: Double = 8.6347608925;
		val b4: Double = 0.2677737343;
		val c1: Double = 9.5733223454;
		val c2: Double = 25.6329561486;
		val c3: Double = 21.0996530827;
		val c4: Double = 3.9584969228;
		if ((x < 1.0)) {
			return (a0 + (x * ((a1 + (x * ((a2 + (x * ((a3 + (x * ((a4 + (x * a5))))))))))))));
		} else 		if ((x < 50.0)) {
			return (Math.log((x as Double)) + ((((Math.exp(-x) / x)) * ((((b4 + (x * ((b3 + (x * ((b2 + (x * ((b1 + x))))))))))) / ((c4 + (x * ((c3 + (x * ((c2 + (x * ((c1 + x))))))))))))))));
		} else {
			return Math.log((x as Double));
		}

	}

	public safe def toString(): String {
		return ((("PoisonSolver: xbits = " + xBits) + " ybits = ") + yBits);
	}

}

class LinearESOpenPIC2DMain {


	public static def main(args: Rail[String]): Void {
{
			var tmr: Timer = new Timer();
			var count: Int = 0;
			tmr.start(count);
			new LinearESOpenPIC2D().run();
			tmr.stop(count);
			Console.OUT.println((("Wall-clock time for plasma: " + tmr.readTimer(count)) + " secs"));
		}	}

}

class Complex {
	private val real: Double;
	private val imag: Double;

	public def this(val real: Double, val imag: Double) {
		this.real = real;
		this.imag = imag;
	}


	public def getReal(): Double {
		return real;
	}

	public def getImag(): Double {
		return imag;
	}

	public def add(val n: Complex): Complex {
		return new Complex((getReal() + n.getReal()), (getImag() + n.getImag()));
	}

	public def sub(val n: Complex): Complex {
		return new Complex((getReal() - n.getReal()), (getImag() - n.getImag()));
	}

	public def mult(val n: Complex): Complex {
		val a: Double = getReal();
		val b: Double = getImag();
		val c: Double = n.getReal();
		val d: Double = n.getImag();
		return new Complex(((a * c) - (b * d)), ((a * d) + (b * c)));
	}

	public def multS(val r: Double): Complex {
		return new Complex((r * getReal()), (r * getImag()));
	}

	public def getConjg(): Complex {
		return new Complex(getReal(), -getImag());
	}

	public def multConjg(): Double {
		val a: Double = getReal();
		val b: Double = getImag();
		return (((a * a) + (b * b)));
	}

	public def complexEquals(val c: Complex): Boolean {
		return ((((Math.abs((getReal() - c.getReal())) < X10Util.ERROR)) && ((Math.abs((getImag() - c.getImag())) < X10Util.ERROR))));
	}

	public safe def toString(): String {
		val r: Double = getReal();
		val i: Double = getImag();
		return (((("(" + r) + ",") + i) + ")");
	}

}

class Random {
	private var flag: Int;
	private var r0: Double;
	private var r1: Int;
	private var r2: Int;
	private var r4: Int;
	private var r5: Int;
	private var h1l: Double;
	private var h1u: Double;
	private var h2l: Double;

	public def this() {
		flag = 0;
		r0 = 0.0;
		r1 = 885098780;
		r2 = 1824280461;
		r4 = 1396483093;
		r5 = 55318673;
		h1l = 65531.0;
		h1u = 32767.0;
		h2l = 65525.0;
	}


	public def nextNormal(): Double {
		var ret: Double = 0.0;
		if ((flag != 0)) {
			ret = r0;
			r0 = 0.0;
			flag = 0;
		} else {
			var isc: Int = 65536;
			var asc: Double = isc;
			var bsc: Double = (asc * asc);
			var i1: Int = (r1 - (((r1 / isc)) * isc));
			var r3: Double = ((h1l * r1) + ((asc * h1u) * i1));
			i1 = (((r3 / bsc)) as Int);
			r3 = (r3 - ((i1 * bsc)));
			bsc = (0.5 * bsc);
			i1 = (r2 / isc);
			isc = (r2 - (i1 * isc));
			r0 = ((h1l * r2) + ((asc * h1u) * isc));
			asc = (1.0 / bsc);
			isc = (((r0 * asc)) as Int);
			r2 = (((r0 - (isc * bsc))) as Int);
			r3 = ((r3 + isc) + ((2.0 * h1u) * i1));
			isc = (((r3 * asc)) as Int);
			r1 = (((r3 - (isc * bsc))) as Int);
			var temp: Double = Math.sqrt((-2.0 * Math.log(((((r1 + (r2 * asc))) * asc) as Double))));
			isc = 65536;
			asc = isc;
			bsc = (asc * asc);
			i1 = (r4 - (((r4 / isc)) * isc));
			r3 = ((h2l * r4) + ((asc * h1u) * i1));
			i1 = (((r3 / bsc)) as Int);
			r3 = (r3 - ((i1 * bsc)));
			bsc = (0.5 * bsc);
			i1 = (r5 / isc);
			isc = (r5 - (i1 * isc));
			r0 = ((h2l * r5) + ((asc * h1u) * isc));
			asc = (1.0 / bsc);
			isc = (((r0 * asc)) as Int);
			r5 = (((r0 - (isc * bsc))) as Int);
			r3 = (r3 + ((isc + ((2.0 * h1u) * i1))));
			isc = (((r3 * asc)) as Int);
			r4 = (((r3 - (isc * bsc))) as Int);
			r0 = (6.28318530717959 * ((((r4 + (r5 * asc))) * asc)));
			ret = (temp * Math.sin(r0));
			r0 = (temp * Math.cos(r0));
			flag = 1;
		}
		return ret;
	}

}

class X10Util {
	public static val DEBUG: Boolean = true;
	public static val ENABLE_CHECKS: Boolean = true;
	public static val ERROR: Double = 1.0e-13;


	public static def maxDouble(val d1: Double, val d2: Double): Double {
		return ((d1 >= d2))?d1:d2;
	}

	public static def minDouble(val d1: Double, val d2: Double): Double {
		return ((d1 >= d2))?d2:d1;
	}

	public static def maxInt(val i1: Int, val i2: Int): Int {
		return ((i1 >= i2))?i1:i2;
	}

	public static def minInt(val i1: Int, val i2: Int): Int {
		return ((i1 >= i2))?i2:i1;
	}

	public static def prependRegionRank2D(val r1: Region{(self.rank == 1)}, val r2: Region{(self.rank == 1)}): Region{(self.rank == 2)} {
		return extendRegionRank2D(r1, r2);
	}

	public static def prependRegionRank3D(val r1: Region{(self.rank == 1)}, val r2: Region{(self.rank == 2)}): Region{(self.rank == 3)} {
		return Region.make([r1, getRank2D(r2, 0), getRank2D(r2, 1)]);
	}

	public static def extendRegionRank2D(val r1: Region{(self.rank == 1)}, val r2: Region{(self.rank == 1)}): Region{(self.rank == 2)} {
		return Region.make([r1, r2]);
	}

	public static def extendRegionRank3D(val r1: Region{(self.rank == 2)}, val r2: Region{(self.rank == 1)}): Region{(self.rank == 3)} {
		return Region.make([getRank2D(r1, 0), getRank2D(r1, 1), r2]);
	}

	public static def prependDistRank2D(val r1: Region{(self.rank == 1)}, val d2: Dist{(self.rank == 1)}): Dist{(self.rank == 2)} {
		var dResult: Dist{(self.rank == 2)} = (((Region.makeEmpty(2)->here)) as Dist{(self.rank == 2)});
		for (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			val p: Place = Dist.makeUnique()(pl);
			val rLocal: Region{(self.rank == 1)} = (((d2 | p)).region as Region{(self.rank == 1)});
			dResult = (dResult || ((prependRegionRank2D(r1, rLocal)->p)));
		}
		return dResult;
	}

	public static def prependDistRank3D(val r1: Region{(self.rank == 1)}, val d2: Dist{(self.rank == 2)}): Dist{(self.rank == 3)} {
		var dResult: Dist{(self.rank == 3)} = (((Region.makeEmpty(3)->here)) as Dist{(self.rank == 3)});
		for (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			val p: Place = Dist.makeUnique()(pl);
			val rLocal: Region{(self.rank == 2)} = (((d2 | p)).region as Region{(self.rank == 2)});
			dResult = (dResult || ((prependRegionRank3D(r1, rLocal)->p)));
		}
		return dResult;
	}

	public static def extendDistRank2D(val d1: Dist{(self.rank == 1)}, val r2: Region{(self.rank == 1)}): Dist{(self.rank == 2)} {
		var dResult: Dist{(self.rank == 2)} = (((Region.makeEmpty(2)->here)) as Dist{(self.rank == 2)});
		for (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			val p: Place = Dist.makeUnique()(pl);
			val rLocal: Region{(self.rank == 1)} = (((d1 | p)).region as Region{(self.rank == 1)});
			dResult = (dResult || ((extendRegionRank2D(rLocal, r2)->p)));
		}
		return dResult;
	}

	public static def extendDistRank3D(val d1: Dist{(self.rank == 2)}, val r2: Region{(self.rank == 1)}): Dist{(self.rank == 3)} {
		var dResult: Dist{(self.rank == 3)} = (((Region.makeEmpty(3)->here)) as Dist{(self.rank == 3)});
		for (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			val p: Place = Dist.makeUnique()(pl);
			val rLocal: Region{(self.rank == 2)} = (((d1 | p)).region as Region{(self.rank == 2)});
			dResult = (dResult || ((extendRegionRank3D(rLocal, r2)->p)));
		}
		return dResult;
	}

	public static def distBlock(val r: Region{(self.rank == 1)}): Dist{(self.rank == 1)} {
		if ((r.min(0) != 0)) {
			throw new RuntimeException("distBlock only applies to rails");
		}
		if ((r.max(0) != (r.size() - 1))) {
			throw new RuntimeException("distblock only applies to rails");
		}
		var blockSize: Int = (r.size() / Place.MAX_PLACES);
		var dResult: Dist{(self.rank == 1)} = (((Region.makeEmpty(1)->here)) as Dist{(self.rank == 1)});
		for (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			val p: Place = Dist.makeUnique()(pl);
			var ext: Dist{(self.rank == 1)} = (((Region.make([((pl(0) * blockSize)..(((((pl(0) + 1)) * blockSize)) - 1))])->p)) as Dist{(self.rank == 1)});
			dResult = (dResult || ext);
		}
		var diff: Int = (r.size() - (blockSize * Place.MAX_PLACES));
		if (((blockSize * Place.MAX_PLACES) != r.size())) {
			var l: Place = Place.places((Place.MAX_PLACES - 1));
			var ext: Dist{(self.rank == 1)} = (((Region.make([((r.size() - diff)..(r.size() - 1))])->l)) as Dist{(self.rank == 1)});
			dResult = (dResult || ext);
		}
		return dResult;
	}

	public static def distBlockStar1(val r: Region{(self.rank == 2)}): Dist{(self.rank == 2)} {
		val r1: Region{(self.rank == 1)} = getRank2D(r, 0);
		val d1: Dist{(self.rank == 1)} = distBlock(r1);
		return extendDistRank2D(d1, getRank2D(r, 1));
	}

	public static def distBlockStar2(val r1: Region{(self.rank == 1)}, val r2: Region{(self.rank == 1)}): Dist{(self.rank == 2)} {
		return distBlockStar1(Region.make([r1, r2]));
	}

	public static def distStarBlock1(val r: Region{(self.rank == 2)}): Dist{(self.rank == 2)} {
		val r2: Region{(self.rank == 1)} = getRank2D(r, 1);
		val d2: Dist{(self.rank == 1)} = distBlock(r2);
		return prependDistRank2D(getRank2D(r, 0), d2);
	}

	public static def distStarBlock2(val r1: Region{(self.rank == 1)}, val r2: Region{(self.rank == 1)}): Dist{(self.rank == 2)} {
		return distStarBlock1(Region.make([r1, r2]));
	}

	public static def isDistBlockStar(val d: Dist{(self.rank == 2)}): Boolean {
		if (ENABLE_CHECKS) {
			val rd: Region{(self.rank == 2)} = d.region;
			val r1: Region{(self.rank == 1)} = getRank2D(rd, 0);
			val r2: Region{(self.rank == 1)} = getRank2D(rd, 1);
			val dRef: Dist{(self.rank == 1)} = (Dist.makeBlock(r1) as Dist{(self.rank == 1)});
			for (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
				val p: Place = Dist.makeUnique()(pl);
				val rLocal: Region{(self.rank == 2)} = (((d | p)).region as Region{(self.rank == 2)});
				val rRefLocal: Region{(self.rank == 1)} = (((dRef | p)).region as Region{(self.rank == 1)});
				if (!(getRank2D(rLocal, 0).equals(rRefLocal))) {
					return false;
				} else 				if (!(getRank2D(rLocal, 1).equals(r2))) {
					if ((rRefLocal.size() > 0)) {
						return false;
					}
				}

			}
		}
		return true;
	}

	public static def isDistStarBlock(val d: Dist{(self.rank == 2)}): Boolean {
		if (ENABLE_CHECKS) {
			val rd: Region{(self.rank == 2)} = d.region;
			val r1: Region{(self.rank == 1)} = getRank2D(rd, 0);
			val r2: Region{(self.rank == 1)} = getRank2D(rd, 1);
			val dRef: Dist{(self.rank == 1)} = (Dist.makeBlock(r2) as Dist{(self.rank == 1)});
			for (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
				val p: Place = Dist.makeUnique()(pl);
				val rLocal: Region{(self.rank == 2)} = (((d | p)).region as Region{(self.rank == 2)});
				val rRefLocal: Region{(self.rank == 1)} = (((dRef | p)).region as Region{(self.rank == 1)});
				if (!((getRank2D(rLocal, 0).equals(r1) && getRank2D(rLocal, 1).equals(rRefLocal)))) {
					return false;
				}
			}
		}
		return true;
	}

	public static def get2DDistRank(val d: Dist{(self.rank == 2)}, val n: Int): Dist{(self.rank == 1)} {
		if (((n > 2) || (n < 0))) {
			throw new RuntimeException("Rank must within bounds");
		}
		var dResult: Dist{(self.rank == 1)} = (((Region.makeEmpty(1)->here)) as Dist{(self.rank == 1)});
		for (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			val p: Place = Dist.makeUnique()(pl);
			val rLocal: Region{(self.rank == 2)} = ((d | p)).region;
			val rLocalN: Region{(self.rank == 1)} = getRank2D(rLocal, n);
			dResult = (dResult || ((((rLocalN - dResult.region))->p)));
		}
		return dResult;
	}

	public static def transposeRegion(val r: Region{(self.rank == 2)}): Region{(self.rank == 2)} {
		return Region.make([getRank2D(r, 1), getRank2D(r, 0)]);
	}

	public static def transposeDist(val d: Dist{(self.rank == 2)}): Dist{(self.rank == 2)} {
		var dResult: Dist{(self.rank == 2)} = (((Region.makeEmpty(2)->here)) as Dist{(self.rank == 2)});
		for (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			val p: Place = Dist.makeUnique()(pl);
			val rLocal: Region{(self.rank == 2)} = (((d | p)).region as Region{(self.rank == 2)});
			dResult = (dResult || ((transposeRegion(rLocal)->p)));
		}
		return dResult;
	}

	public static def transposeBlockStar(val d: Dist{(self.rank == 2)}): Dist{(self.rank == 2)} {
		val rd: Region{(self.rank == 2)} = d.region;
		val r1: Region{(self.rank == 1)} = getRank2D(rd, 0);
		val r2: Region{(self.rank == 1)} = getRank2D(rd, 1);
		return extendDistRank2D(distBlock(r2), r1);
	}

	public static def pullBoolean(val b: Array[Boolean]{(self.rank == 1)}, val pt: Point{(self.rank == 1)}): Boolean {
		if (!b.region.contains(pt)) {
			println(((("pt must be contained in b.region: " + b.region) + " ") + pt));
			throw new RuntimeException(("pullBoolean Exception: " + pt));
		}
		val pHere: Place = here;
		val dDst: Dist{(self.rank == 1)} = (Region.make([(0..0)])->here);
		val result: Array[Boolean]{(self.rank == 1)} = (Array.make[Boolean](dDst));
		finish 		async (b.dist(pt)) {
			val val: Boolean = b(pt);
			async (pHere) {
				result(0) = val;
			}
		}

		return result(0);
	}

	public static def pullComplex(val c: Array[Complex]{(self.rank == 1)}, val pt: Point{(self.rank == 1)}): Complex {
		if (!c.region.contains(pt)) {
			println(((("pt must be contained in c.region: " + c.region) + " ") + pt));
			throw new RuntimeException(("Complex Exception: " + pt));
		}
		val pHere: Place = here;
		val dDst: Dist{(self.rank == 1)} = (Region.make([(0..0)])->here);
		val result: Array[Complex]{(self.rank == 1)} = (Array.make[Complex](dDst));
		finish 		async (c.dist(pt)) {
			val val: Complex = c(pt);
			async (pHere) {
				result(0) = val;
			}
		}

		return result(0);
	}

	public static def pullInt(val i: Array[Int]{(self.rank == 1)}, val pt: Point{(self.rank == 1)}): Int {
		if (!i.region.contains(pt)) {
			println(((("pt must be contained in i.region: " + i.region) + " ") + pt));
			throw new RuntimeException(("pullDouble Exception: " + pt));
		}
		val pHere: Place = here;
		val dDst: Dist{(self.rank == 1)} = (Region.make([(0..0)])->here);
		val result: Array[Int]{(self.rank == 1)} = (Array.make[Int](dDst));
		finish 		async (i.dist(pt)) {
			val val: Int = i(pt);
			async (pHere) {
				result(0) = val;
			}
		}

		return result(0);
	}

	public static def pullDouble(val d: Array[Double]{(self.rank == 1)}, val pt: Point{(self.rank == 1)}): Double {
		if (!d.region.contains(pt)) {
			println(((("pt must be contained in d.region: " + d.region) + " ") + pt));
			throw new RuntimeException(("pullDouble Exception: " + pt));
		}
		val pHere: Place = here;
		val dDst: Dist{(self.rank == 1)} = (Region.make([(0..0)])->here);
		val result: Array[Double]{(self.rank == 1)} = (Array.make[Double](dDst));
		finish 		async (d.dist(pt)) {
			val val: Double = d(pt);
			async (pHere) {
				result(0) = val;
			}
		}

		return result(0);
	}

	public static def getRank2D(val r: Region{(self.rank == 2)}, val n: Int): Region{(self.rank == 1)} {
		return (r.projection(n) as Region{(self.rank == 1)});
	}

	public static def getRank3D(val r: Region{(self.rank == 3)}, val n: Int): Region{(self.rank == 1)} {
		return (r.projection(n) as Region{(self.rank == 1)});
	}

	public static def getLRank2D(val d: Dist{(self.rank == 2)}, val n: Int): Region{(self.rank == 1)} {
		return getPRank2D(d, here, n);
	}

	public static def getLRank3D(val d: Dist{(self.rank == 3)}, val n: Int): Region{(self.rank == 1)} {
		return getPRank3D(d, here, n);
	}

	public static def getPRank2D(val d: Dist{(self.rank == 2)}, val p: Place, val n: Int): Region{(self.rank == 1)} {
		return getRank2D(((d | p)).region, n);
	}

	public static def getPRank3D(val d: Dist{(self.rank == 3)}, val p: Place, val n: Int): Region{(self.rank == 1)} {
		return getRank3D(((d | p)).region, n);
	}

	public static def get1DLRegion(val d: Dist{(self.rank == 1)}): Region{(self.rank == 1)} {
		return (((d | here)).region as Region{(self.rank == 1)});
	}

	public static def get1DPRegion(val d: Dist{(self.rank == 1)}, val p: Place): Region{(self.rank == 1)} {
		return (((d | p)).region as Region{(self.rank == 1)});
	}

	public static def get2DLRegion(val d: Dist{(self.rank == 2)}): Region{(self.rank == 2)} {
		return (((d | here)).region as Region{(self.rank == 2)});
	}

	public static def get2DPRegion(val d: Dist{(self.rank == 2)}, val p: Place): Region{(self.rank == 2)} {
		return (((d | p)).region as Region{(self.rank == 2)});
	}

	public static def get3DLRegion(val d: Dist{(self.rank == 3)}): Region{(self.rank == 3)} {
		return (((d | here)).region as Region{(self.rank == 3)});
	}

	public static def get3DPRegion(val d: Dist{(self.rank == 3)}, val p: Place): Region{(self.rank == 3)} {
		return (((d | p)).region as Region{(self.rank == 3)});
	}

	public static def print(val s: String): Void {
		if (DEBUG) {
			Console.OUT.print(s);
		}
	}

	public static def println(val s: String): Void {
		print(s);
		newline();
	}

	public static def newline(): Void {
		print("\n");
	}

}

class FourierTransform2D {
	public val maxBits: Int;
	public val nBits1: Int;
	public val nBits2: Int;
	public val length1: Int;
	public val length2: Int;
	public val invN: Double;
	public val tableBitReverse: Array[Int]{(self.rank == 1)};
	public val tableSineCosine: Array[Complex]{(self.rank == 1)};

	public def this(val nBits1: Int, val nBits2: Int) {
		this.nBits1 = nBits1;
		this.nBits2 = nBits2;
		maxBits = X10Util.maxInt(nBits1, (nBits2 - 1));
		val len1: Int = (1 << nBits1);
		val len2: Int = (1 << nBits2);
		length1 = len1;
		length2 = len2;
		invN = (1.0 / (((2 * len1) * len2)));
		tableBitReverse = initBitReverse(nBits1, nBits2);
		tableSineCosine = initSineCosine(len1, len2);
	}


	public def transformC(val f: Array[Complex]{(self.rank == 2)}): Array[Complex]{(self.rank == 2)} {
		scrambleY(f);
		bitReverse(f, 0);
		transformRank(f, 0, false);
		val g: Array[Complex]{(self.rank == 2)} = ArrayUtil.transpose(f);
		scrambleX(g);
		bitReverse(g, 1);
		transformRank(g, 1, false);
		return g;
	}

	public def transformD(val f: Array[Double]{(self.rank == 2)}): Array[Complex]{(self.rank == 2)} {
		return transformC(ArrayUtil.doubleToComplex(f));
	}

	public def inverseTransformC(val f: Array[Complex]{(self.rank == 2)}): Array[Complex]{(self.rank == 2)} {
		bitReverse(f, 1);
		transformRank(f, 1, true);
		unscrambleX(f);
		val g: Array[Complex]{(self.rank == 2)} = ArrayUtil.transpose(f);
		bitReverse(g, 0);
		transformRank(g, 0, true);
		unscrambleY(g);
		return g;
	}

	public def inverseTransformD(val f: Array[Double]{(self.rank == 2)}): Array[Complex]{(self.rank == 2)} {
		return inverseTransformC(ArrayUtil.doubleToComplex(f));
	}

	public proto def initBitReverse(val nBits1: Int, val nBits2: Int): Array[Int]{(self.rank == 1)} {
		val maxBits: Int = X10Util.maxInt((nBits2 - 1), nBits1);
		val r: Region{(self.rank == 1)} = Region.make([(0..(((1 << maxBits)) - 1))]);
		return ((Array.make[Int](r, (i:Point{(self.rank == 1)}) => {
			val i0: Int = i(0);
			val i1: Int = ((((((i0 & 0x0000FFFF)) << 16)) | ((((i0 >> 16)) & 0x0000FFFF))));
			val i2: Int = ((((((i1 & 0x00FF00FF)) << 8)) | ((((i1 >> 8)) & 0x00FF00FF))));
			val i3: Int = ((((((i2 & 0x0F0F0F0F)) << 4)) | ((((i2 >> 4)) & 0x0F0F0F0F))));
			val i4: Int = ((((((i3 & 0x33333333)) << 2)) | ((((i3 >> 2)) & 0x33333333))));
			val i5: Int = ((((((i4 & 0x55555555)) << 1)) | ((((i4 >> 1)) & 0x55555555))));
			return (i5 >>> ((32 - maxBits)));
		})) as Array[Int]{(self.rank == 1)});
	}

	public proto def initSineCosine(val length1: Int, val length2: Int): Array[Complex]{(self.rank == 1)} {
		val maxLength: Int = X10Util.maxInt(length1, length2);
		val PI2: Double = 6.28318530717959;
		val k: Double = (PI2 / maxLength);
		val r: Region{(self.rank == 1)} = Region.make([(0..(((maxLength / 2)) - 1))]);
		return ((Array.make[Complex](r, (i:Point{(self.rank == 1)}) => {
			val theta: Double = (k * i(0));
			return new Complex(Math.cos(theta), -Math.sin(theta));
		})) as Array[Complex]{(self.rank == 1)});
	}

	public def bitReverse(val f: Array[Complex]{(self.rank == 2)}, val rnk: Int): Void {
		val nBits: Int = ((rnk == 0))?nBits1:(nBits2 - 1);
		val r1Length: Int = ((rnk == 0))?length1:(length2 / 2);
		val nrx: Int = (((1 << nBits)) / r1Length);
		finish 		ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			val dF: Dist{(self.rank == 2)} = f.dist;
			val rLocal1: Region{(self.rank == 1)} = X10Util.getLRank2D(dF, 0);
			foreach ((j1): Point{(self.rank == 1)} in Region.make([(0..(r1Length - 1))])) {
				val j2: Int = (tableBitReverse(j1) >> ((maxBits - nBits)));
				if ((j1 < j2)) {
					finish 					foreach ((i): Point{(self.rank == 1)} in rLocal1) {
						val temp: Complex = f(i, j1);
						f(i, j1) = f(i, j2);
						f(i, j2) = temp;
					}

				}
			}
		}

	}

	public def scrambleX(val f: Array[Complex]{(self.rank == 2)}): Void {
		val nxh: Int = (length2 / 2);
		val nxhh: Int = (nxh / 2);
		val kmr: Int = (X10Util.maxInt(length1, length2) / length2);
		finish 		ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			val dF: Dist{(self.rank == 2)} = f.dist;
			val rLocal: Region{(self.rank == 2)} = X10Util.get2DLRegion(dF);
			val rLocal1: Region{(self.rank == 1)} = X10Util.getRank2D(rLocal, 0);
			foreach (pt: Point{(self.rank == 2)} in Region.make([rLocal1, (nxhh..nxhh)])) {
				f(pt) = (f(pt).getConjg()).multS(2);
				val pt0: Point{(self.rank == 2)} = (pt * Point.make([1, 0]));
				val t2: Complex = f(pt0);
				val t2Real: Double = t2.getReal();
				val t2Imag: Double = t2.getImag();
				f(pt0) = new Complex((t2Real + t2Imag), (t2Real - t2Imag));
			}
			for ((j1): Point{(self.rank == 1)} in Region.make([(1..(nxhh - 1))])) {
				val j2: Int = (nxh - j1);
				val t0: Complex = tableSineCosine((kmr * j1));
				val t1: Complex = new Complex(t0.getImag(), t0.getReal());
				foreach ((i): Point{(self.rank == 1)} in rLocal1) {
					val t: Complex = f(i, j2).getConjg();
					val s: Complex = f(i, j1).add(t);
					val t3: Complex = (f(i, j1).sub(t)).mult(t1);
					f(i, j1) = s.add(t3);
					f(i, j2) = (s.sub(t3)).getConjg();
				}
			}
		}

	}

	public def unscrambleX(val f: Array[Complex]{(self.rank == 2)}): Void {
		val nxh: Int = (length2 / 2);
		val nxhh: Int = (length2 / 4);
		val kmr: Int = (X10Util.maxInt(length1, length2) / length2);
		finish 		ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			val dF: Dist{(self.rank == 2)} = f.dist;
			val rLocal1: Region{(self.rank == 1)} = X10Util.getLRank2D(dF, 0);
			foreach ((j1): Point{(self.rank == 1)} in Region.make([(1..(nxhh - 1))])) {
				val j2: Int = (nxh - j1);
				val t0: Complex = tableSineCosine((kmr * j1));
				val t1: Complex = new Complex(t0.getImag(), -t0.getReal());
				foreach ((i): Point{(self.rank == 1)} in rLocal1) {
					val t: Complex = f(i, j2).getConjg();
					val s: Complex = f(i, j1).add(t);
					val t2: Complex = (f(i, j1).sub(t)).mult(t1);
					f(i, j1) = (s.add(t2)).multS(invN);
					f(i, j2) = ((s.sub(t2)).getConjg()).multS(invN);
				}
			}
			foreach (pt: Point{(self.rank == 2)} in Region.make([rLocal1, (nxhh..nxhh)])) {
				val pt0: Point{(self.rank == 2)} = (pt * Point.make([1, 0]));
				val t1: Complex = f(pt0);
				val t2: Complex = f(pt);
				val t1Real: Double = t1.getReal();
				val t1Imag: Double = t1.getImag();
				val t3: Complex = new Complex((t1Real + t1Imag), (t1Real - t1Imag));
				f(pt) = (t2.getConjg()).multS((2 * invN));
				f(pt0) = t3.multS((2 * invN));
			}
		}

	}

	public def scrambleY(val f: Array[Complex]{(self.rank == 2)}): Void {
		finish 		async (Place.FIRST_PLACE) {
			foreach ((j1): Point{(self.rank == 1)} in Region.make([(1..(((length1 / 2)) - 1))])) {
				val j2: Int = (length1 - j1);
				val t1: Complex = f(0, j2);
				val t2: Complex = f(0, j1);
				val s: Complex = new Complex(t1.getImag(), t1.getReal());
				f(0, j2) = (t2.sub(s)).getConjg();
				f(0, j1) = t2.add(s);
			}
		}

	}

	public def unscrambleY(val f: Array[Complex]{(self.rank == 2)}): Void {
		finish 		async (Place.FIRST_PLACE) {
			foreach ((j1): Point{(self.rank == 1)} in Region.make([(1..(((length1 / 2)) - 1))])) {
				val j2: Int = (length1 - j1);
				val s: Complex = f(0, j2);
				val t: Complex = f(0, j1);
				val t1: Complex = t.add(s);
				val t2: Complex = t.sub(s);
				f(0, j2) = (new Complex(t1.getImag(), t2.getReal())).multS(0.5);
				f(0, j1) = (new Complex(t1.getReal(), t2.getImag())).multS(0.5);
			}
		}

	}

	public def transformRank(val f: Array[Complex]{(self.rank == 2)}, val rnk: Int, val inverse: Boolean): Void {
		val nxy: Int = X10Util.maxInt(length1, length2);
		val nxh: Int = (length2 / 2);
		val nyh: Int = (length1 / 2);
		val nxhh: Int = (length2 / 4);
		val bits: Int = ((rnk == 0))?nBits1:(nBits2 - 1);
		val r: Int = (nxy / (((rnk == 0))?length1:nxh));
		for (exponent: Point{(self.rank == 1)} in Region.make([(0..(bits - 1))])) {
			val stride: Int = (1 << exponent(0));
			val width: Int = (2 * stride);
			val km: Int = ((((rnk == 0))?nyh:nxhh) / stride);
			val kmr: Int = (km * r);
			finish 			ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
				for ((k): Point{(self.rank == 1)} in Region.make([(0..(km - 1))])) {
					val lb: Int = (width * k);
					val mb: Int = (lb + stride);
					finish 					foreach ((j): Point{(self.rank == 1)} in Region.make([(0..(stride - 1))])) {
						val j1: Int = (j + lb);
						val j2: Int = (j + mb);
						val s: Complex = ((inverse)?tableSineCosine((kmr * j)):tableSineCosine((kmr * j)).getConjg());
						val dF: Dist{(self.rank == 2)} = f.dist;
						val rLocal1: Region{(self.rank == 1)} = X10Util.getLRank2D(dF, 0);
						foreach ((i): Point{(self.rank == 1)} in rLocal1) {
							val tmp1: Complex = f(i, j1);
							val tmp2: Complex = s.mult(f(i, j2));
							f(i, j2) = tmp1.sub(tmp2);
							f(i, j1) = tmp1.add(tmp2);
						}
					}

				}
			}

		}
	}

	public safe def toString(): String {
		return ((("FourierTransform: xbits = " + nBits1) + " ybits = ") + nBits2);
	}

}

class LinearESOpenPIC2D {
	private static val NUMBER_OF_XBITS: Int = 2;
	private static val NUMBER_OF_YBITS: Int = 3;
	private static val XLENGTH: Int = (1 << NUMBER_OF_XBITS);
	private static val YLENGTH: Int = (1 << NUMBER_OF_YBITS);
	private static val PARTICLE_XDENSITY: Int = 4;
	private static val PARTICLE_YDENSITY: Int = 8;
	private static val NPARTICLES: Int = (PARTICLE_XDENSITY * PARTICLE_YDENSITY);
	private static val DELTA_TIME: Double = 0.2000000e+00;
	private static val ELECTRON_CHARGE: Double = -1.0;
	private static val THERMAL_XVELOCITY: Double = 1.0;
	private static val THERMAL_YVELOCITY: Double = 1.0;
	private static val BOUNDARY_CONDITION: Int = 2;
	private static val TIME_STEPS: Int = 325;
	private static val SORT_TIME: Int = 50;
	private static val IMBALANCE: Double = 0.08;
	private static val MOVE_FIELD_TO_DIST_MAX_ROWS: Int = ((4 * YLENGTH) / Place.MAX_PLACES);
	private static val MOVE_PARTICLES_MAX: Int = ((4 * NPARTICLES) / Place.MAX_PLACES);
	private static val MOVE_PARTICLES_MAX_BUFFER: Int = ((4 * NPARTICLES) / Place.MAX_PLACES);
	private val r: Random;
	private val solver: PoissonSolver;
	private var particles: Array[Particle]{(self.rank == 1)};
	private var backgroundCharge: Array[Double]{(self.rank == 2)};
	private var totalTime: Long;
	private var initTime: Long;
	private var depositTime: Long;
	private var redistTime: Long;
	private var solverTime: Long;
	private var pushTime: Long;
	private var moveTime: Long;

	public def this() {
		r = new Random();
		var affp: Double = (((((XLENGTH - 2.0)) * ((YLENGTH - 2.0)))) / NPARTICLES);
		solver = new PoissonSolver(NUMBER_OF_XBITS, NUMBER_OF_YBITS, affp);
		totalTime = 0;
		initTime = 0;
		depositTime = 0;
		redistTime = 0;
		pushTime = 0;
		moveTime = 0;
	}


	public def run(): Void {
		totalTime = System.currentTimeMillis();
		initTime = System.currentTimeMillis();
		initialize();
		initTime = (System.currentTimeMillis() - initTime);
		for (i: Point{(self.rank == 1)} in Region.make([(0..(TIME_STEPS - 1))])) {
			Console.OUT.println(("T = " + i(0)));
			val e: Array[Double]{(self.rank == 1)} = stepTime(backgroundCharge);
			if (((i(0) % SORT_TIME) == 0)) {
				var time: Long = System.currentTimeMillis();
				sortParticles(particles, backgroundCharge.dist);
				moveTime = (moveTime + ((System.currentTimeMillis() - time)));
			}
			Console.OUT.println(((((("field, kinetic, total energies = " + e(0)) + ",") + e(1)) + ",") + e(2)));
			if ((i(0) == (TIME_STEPS - 1))) {
				val dRef: Dist{(self.rank == 1)} = (Region.make([(0..2)])->here);
				val ref: Array[Double]{(self.rank == 1)} = (Array.make[Double](dRef));
				if (((((NUMBER_OF_XBITS == 2) && (NUMBER_OF_YBITS == 3)) && (PARTICLE_XDENSITY == 4)) && (PARTICLE_YDENSITY == 8))) {
					ref(0) = 0.009936711552799253;
					ref(1) = 35.7904959849017;
					ref(2) = 35.8004326964545;
					Console.OUT.println(("final field energy diff = " + (Math.abs((ref(0) - e(0))))));
					Console.OUT.println(("final kinetic energy diff = " + (Math.abs((ref(1) - e(1))))));
					Console.OUT.println(("final final energy diff = " + (Math.abs((ref(2) - e(2))))));
				} else 				if (((((NUMBER_OF_XBITS == 3) && (NUMBER_OF_YBITS == 4)) && (PARTICLE_XDENSITY == 8)) && (PARTICLE_YDENSITY == 16))) {
					ref(0) = 1.12911030199718;
					ref(1) = 138.01404855563126;
					ref(2) = 139.14315885762844;
					Console.OUT.println(("final field energy diff = " + (Math.abs((ref(0) - e(0))))));
					Console.OUT.println(("final kinetic energy diff = " + (Math.abs((ref(1) - e(1))))));
					Console.OUT.println(("final final energy diff = " + (Math.abs((ref(2) - e(2))))));
				} else {
					Console.OUT.println("No reference values availabile");
				}

			}
		}
		totalTime = (System.currentTimeMillis() - totalTime);
		Console.OUT.println(("Total time (ms): " + totalTime));
		Console.OUT.println(((("Initialization time (ms): " + initTime) + " ") + (((100.0 * initTime)) / totalTime)));
		Console.OUT.println(((("Particle Deposit time (ms): " + depositTime) + " ") + (((100.0 * depositTime)) / totalTime)));
		Console.OUT.println(((("Field Redistribution time (ms): " + redistTime) + " ") + (((100.0 * redistTime)) / totalTime)));
		Console.OUT.println(((("Solver time (ms): " + solverTime) + " ") + (((100.0 * solverTime)) / totalTime)));
		Console.OUT.println(((("Particle Push time (ms): " + pushTime) + " ") + (((100.0 * pushTime)) / totalTime)));
		Console.OUT.println(((("Particle move time (ms): " + moveTime) + " ") + (((100.0 * moveTime)) / totalTime)));
	}

	public def initialize(): Void {
		Console.OUT.println("Init: particle position");
		val position: Array[Double]{(self.rank == 2)} = genParticlePosition(NPARTICLES);
		Console.OUT.println("Init: particle velocity");
		val velocity: Array[Double]{(self.rank == 2)} = genParticleVelocity(NPARTICLES);
		Console.OUT.println("Init: particles");
		particles = genParticles(NPARTICLES, position, velocity);
		Console.OUT.println("Init: initial field distribution");
		val dField: Dist{(self.rank == 2)} = genInitialDist(particles);
		Console.OUT.println("Init: initial partical distribution");
		val info: Array[Int]{(self.rank == 1)} = makeInfoArray();
		particles = moveParticles(particles, dField, info);
		Console.OUT.println("Init: background charge");
		backgroundCharge = (Array.make[Double](dField));
		val backgroundGuards: Array[Double]{(self.rank == 2)} = makeGuards(dField);
		depositCharge(particles, backgroundCharge, backgroundGuards, Math.abs(ELECTRON_CHARGE));
		addGuards(backgroundCharge, backgroundGuards);
	}

	public def genParticlePosition(val np: Int): Array[Double]{(self.rank == 2)} {
		val X: Int = 0;
		val Y: Int = 1;
		val MAX_PLACES: Int = Place.MAX_PLACES;
		val MAX_ITER: Int = 20;
		val ERROR: Double = 0.0001;
		val BIG: Double = 0.5;
		val dResult: Dist{(self.rank == 2)} = X10Util.distBlockStar2(Region.make([(0..(np - 1))]), Region.make([(0..1)]));
		val result: Array[Double]{(self.rank == 2)} = (Array.make[Double](dResult));
		val particlesPerPlace: Int = (((PARTICLE_XDENSITY * PARTICLE_YDENSITY)) / MAX_PLACES);
		val totalParticles: Int = (particlesPerPlace * MAX_PLACES);
		if ((totalParticles != NPARTICLES)) {
			Console.ERR.println((("Particle distribution truncated, " + "total particles = ") + totalParticles));
			return result;
		}
		val edgelx: Double = (((BOUNDARY_CONDITION == 2) || (BOUNDARY_CONDITION == 3)))?1.0:0.0;
		val edgely: Double = ((BOUNDARY_CONDITION == 2))?1.0:0.0;
		var tx0: Double = linearDensityFnIntegral(edgelx, 0.0, 0.0, 0.0);
		var ty0: Double = linearDensityFnIntegral(edgely, 0.0, 0.0, 0.0);
		val anx: Double = (XLENGTH - edgelx);
		val any: Double = (YLENGTH - edgely);
		val bnx: Double = (PARTICLE_XDENSITY / ((linearDensityFnIntegral(anx, 0.0, 0.0, 0.0) - tx0)));
		val bny: Double = (PARTICLE_YDENSITY / ((linearDensityFnIntegral(any, 0.0, 0.0, 0.0) - ty0)));
		val x0: Double = ((bnx * tx0) - 0.5);
		val y0: Double = ((bny * ty0) - 0.5);
		finish 		ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			val rLocal: Region{(self.rank == 1)} = X10Util.getLRank2D(dResult, 0);
			val offset: Int = rLocal.min(0);
			val koff: Int = (particlesPerPlace * pl(0));
			val noff: Int = offset;
			var kc: Int = (koff / PARTICLE_XDENSITY);
			var yt0: Double = edgely;
			var yt: Double = (yt0 + (0.5 / ((bny * linearDensityFn(yt0, 0.0, 0.0, 0.0)))));
			for (k: Point{(self.rank == 1)} in Region.make([(0..(kc - 1))])) {
				var yn: Double = ((k(0) + y0) + 1);
				if ((k(0) > 0)) {
					yt = (yt + ((1.0 / ((bny * linearDensityFn(yt, 0.0, 0.0, 0.0))))));
				}
				yt = X10Util.maxDouble(edgely, X10Util.minDouble(yt, any));
				var f: Double = ((bny * linearDensityFnIntegral(yt, 0.0, 0.0, 0.0)) - yn);
				var absf: Double = Math.abs(f);
				var i: Int = 0;
				while ((absf >= ERROR)){
					if ((absf < BIG)) {
						var fp: Double = (bny * linearDensityFn(yt, 0.0, 0.0, 0.0));
						yt0 = yt;
						yt = X10Util.maxDouble(edgely, X10Util.minDouble((yt - (f / fp)), any));
					} else 					if ((f > 0.0)) {
						yt = (yt0 + ((0.5 * ((yt - yt0)))));
					} else {
						yt = ((yt + yt) - yt0);
					}

					if ((i >= MAX_ITER)) {
						Console.ERR.println("1: Newton iteration max exceeded");
						break;
					} else {
						f = ((bny * linearDensityFnIntegral(yt, 0.0, 0.0, 0.0)) - yn);
						absf = Math.abs(f);
					}
					i++;
				}
				yt0 = yt;
			}
			var jc: Int = (koff - (PARTICLE_XDENSITY * kc));
			var xt0: Double = edgelx;
			var xt: Double = (xt0 + (0.5 / ((bnx * linearDensityFn(xt0, 0.0, 0.0, 0.0)))));
			for (j: Point{(self.rank == 1)} in Region.make([(0..(jc - 1))])) {
				var xn: Double = ((j(0) + x0) + 1);
				if ((j(0) > 0)) {
					xt = (xt + ((1.0 / ((bnx * linearDensityFn(xt, 0.0, 0.0, 0.0))))));
				}
				xt = X10Util.maxDouble(edgelx, X10Util.minDouble(xt, anx));
				var f: Double = ((bnx * linearDensityFnIntegral(xt, 0.0, 0.0, 0.0)) - xn);
				var absf: Double = Math.abs(f);
				var i: Int = 0;
				while ((absf >= ERROR)){
					if ((absf < BIG)) {
						var fp: Double = (bnx * linearDensityFn(xt, 0.0, 0.0, 0.0));
						xt0 = xt;
						xt = X10Util.maxDouble(edgelx, X10Util.minDouble((xt - (f / fp)), anx));
					} else 					if ((f > 0.0)) {
						xt = (xt0 + (0.5 * ((xt - xt0))));
					} else {
						xt = ((xt + xt) - xt0);
					}

					if ((i >= MAX_ITER)) {
						Console.ERR.println("2: Newton iteration max exceeded");
						break;
					} else {
						f = ((bnx * linearDensityFnIntegral(xt, 0.0, 0.0, 0.0)) - xn);
						absf = Math.abs(f);
					}
					i++;
				}
				xt0 = xt;
			}
			kc++;
			for ((n): Point{(self.rank == 1)} in Region.make([(0..(X10Util.minInt(particlesPerPlace, PARTICLE_XDENSITY) - 1))])) {
				var nn: Int = (n + koff);
				var k: Int = (nn / PARTICLE_XDENSITY);
				var j: Int = (nn % PARTICLE_XDENSITY);
				var xn: Double = ((j + x0) + 1);
				if ((j == 0)) {
					xt0 = edgelx;
					xt = (xt0 + (0.5 / ((bnx * linearDensityFn(xt0, 0.0, 0.0, 0.0)))));
				} else {
					xt = (xt + ((1.0 / ((bnx * linearDensityFn(xt, 0.0, 0.0, 0.0))))));
				}
				xt = X10Util.maxDouble(edgelx, X10Util.minDouble(xt, anx));
				var f: Double = ((bnx * linearDensityFnIntegral(xt0, 0.0, 0.0, 0.0)) - xn);
				var absf: Double = Math.abs(f);
				var i: Int = 0;
				while ((absf >= ERROR)){
					if ((absf < BIG)) {
						var fp: Double = (bnx * linearDensityFn(xt, 0.0, 0.0, 0.0));
						xt0 = xt;
						xt = X10Util.maxDouble(edgelx, X10Util.minDouble((xt - (f / fp)), anx));
					} else 					if ((f > 0.0)) {
						xt = (xt0 + (0.5 * ((xt - xt0))));
					} else {
						xt = ((xt + xt) - xt0);
					}

					if ((i >= MAX_ITER)) {
						Console.ERR.println("3: Newton iteration max exceeded");
						break;
					} else {
						f = ((bnx * linearDensityFnIntegral(xt, 0.0, 0.0, 0.0)) - xn);
						absf = Math.abs(f);
					}
					i++;
				}
				result((n + noff), X) = xt;
			}
			for ((n): Point{(self.rank == 1)} in Region.make([(0..(particlesPerPlace - 1))])) {
				var nn: Int = (n + koff);
				var k: Int = ((nn / PARTICLE_XDENSITY) + 1);
				var j: Int = (nn - (PARTICLE_XDENSITY * k));
				nn = (n % PARTICLE_XDENSITY);
				if ((k == kc)) {
					var yn: Double = (k + y0);
					if ((k > 1)) {
						yt = (yt + ((1.0 / ((bny * linearDensityFn(yt, 0.0, 0.0, 0.0))))));
					}
					yt = X10Util.maxDouble(edgelx, X10Util.minDouble(yt, any));
					var f: Double = ((bny * linearDensityIntegral(yt, 0.0, 0.0, 0.0)) - yn);
					var absf: Double = Math.abs(f);
					var i: Int = 0;
					while ((absf >= ERROR)){
						if ((absf < BIG)) {
							var fp: Double = (bny * linearDensityFn(yt, 0.0, 0.0, 0.0));
							yt0 = yt;
							yt = X10Util.maxDouble(edgely, X10Util.minDouble((yt - (f / fp)), any));
						} else 						if ((f > 0.0)) {
							yt = (yt0 + (0.5 * ((yt - yt0))));
						} else {
							yt = ((yt + yt) - yt0);
						}

						if ((i >= MAX_ITER)) {
							Console.ERR.println("4: Newton iteration max exceeded");
							break;
						} else {
							f = ((bny * linearDensityFnIntegral(yt, 0.0, 0.0, 0.0)) - yn);
							absf = Math.abs(f);
						}
						i++;
					}
					kc++;
					yt0 = yt;
				}
				result((n + noff), X) = result((nn + noff), X);
				result((n + noff), Y) = yt;
			}
		}

		return result;
	}

	public def genParticleVelocity(val n: Int): Array[Double]{(self.rank == 2)} {
		val X: Int = 0;
		val Y: Int = 1;
		val dResult: Dist{(self.rank == 2)} = X10Util.distBlockStar2(Region.make([(0..(n - 1))]), Region.make([(0..1)]));
		val result: Array[Double]{(self.rank == 2)} = (Array.make[Double](dResult));
		finish {
			for ((i): Point{(self.rank == 1)} in Region.make([(0..(PARTICLE_YDENSITY - 1))])) {
				val offset: Int = (PARTICLE_XDENSITY * i);
				for ((j): Point{(self.rank == 1)} in Region.make([(0..(PARTICLE_XDENSITY - 1))])) {
					val idx: Int = (j + offset);
					val vx: Double = (THERMAL_XVELOCITY * r.nextNormal());
					val vy: Double = (THERMAL_YVELOCITY * r.nextNormal());
					async (dResult(idx, 0)) {
						result(idx, X) = vx;
						result(idx, Y) = vy;
					}
				}
			}
		}
		Console.OUT.println("Init: correcting drift");
		val dUnique: Dist{(self.rank == 1)} = (Dist.makeUnique() as Dist{(self.rank == 1)});
		val numParticles: Array[Int]{(self.rank == 1)} = (Array.make[Int](dUnique, (pt:Point{(self.rank == 1)}) => {
			return 0;
		}));
		val sumX: Array[Double]{(self.rank == 1)} = (Array.make[Double](dUnique, (pt:Point{(self.rank == 1)}) => {
			return 0.0;
		}));
		val sumY: Array[Double]{(self.rank == 1)} = (Array.make[Double](dUnique, (pt:Point{(self.rank == 1)}) => {
			return 0.0;
		}));
		finish 		ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			val rLocal1: Region{(self.rank == 1)} = X10Util.getLRank2D(dResult, 0);
			for (pt: Point{(self.rank == 2)} in Region.make([rLocal1, (X..X)])) {
				sumX(pl) = (sumX(pl) + result(pt));
				numParticles(pl) = (numParticles(pl) + 1);
			}
			for (pt: Point{(self.rank == 2)} in Region.make([rLocal1, (Y..Y)])) {
				sumY(pl) = (sumY(pl) + result(pt));
			}
		}

		val totalParticles: Int = COMPILER_INSERTED_LIB.sum(numParticles);
		val driftX: Double = (COMPILER_INSERTED_LIB.sum(sumX) / totalParticles);
		val driftY: Double = (COMPILER_INSERTED_LIB.sum(sumY) / totalParticles);
		Console.OUT.println("Init: updating with drift");
		finish 		ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			val rLocal1: Region{(self.rank == 1)} = X10Util.getLRank2D(dResult, 0);
			foreach (pt: Point{(self.rank == 2)} in Region.make([rLocal1, (X..X)])) {
				result(pt) = (result(pt) - driftX);
			}
			foreach (pt: Point{(self.rank == 2)} in Region.make([rLocal1, (Y..Y)])) {
				result(pt) = (result(pt) - driftY);
			}
		}

		if ((totalParticles != NPARTICLES)) {
			Console.ERR.println((("Velocity distribution truncated, number of " + "particles = ") + totalParticles));
		}
		return result;
	}

	public def genParticles(val n: Int, val position: Array[Double]{(self.rank == 2)}, val velocity: Array[Double]{(self.rank == 2)}): Array[Particle]{(self.rank == 1)} {
		val dResult: Dist{(self.rank == 1)} = (Dist.makeBlock(Region.make([(0..(n - 1))])) as Dist{(self.rank == 1)});
		return (Array.make[Particle](dResult, ((i):Point{(self.rank == 1)}) => {
			return new Particle(position(i, 0), position(i, 1), velocity(i, 0), velocity(i, 1));
		}));
	}

	public def genInitialDist(val particles: Array[Particle]{(self.rank == 1)}): Dist{(self.rank == 2)} {
		val MAX_PLACES: Int = Place.MAX_PLACES;
		val edgely: Double = ((BOUNDARY_CONDITION == 2))?1.0:0.0;
		val y: Double = YLENGTH;
		val y0: Double = linearDensityFnIntegral(edgely, 0.0, 0.0, 0.0);
		val nParticlesAve: Double = (((linearDensityFnIntegral((y - edgely), 0.0, 0.0, 0.0) - y0)) / MAX_PLACES);
		val dUnique: Dist{(self.rank == 1)} = (Dist.makeUnique() as Dist{(self.rank == 1)});
		val yRegions: Array[Region{(self.rank == 1)}]{(self.rank == 1)} = ((Array.make[Region{(self.rank == 1)}](dUnique)) as Array[Region{(self.rank == 1)}]{(self.rank == 1)});
		finish 		ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			val anpl: Double = (pl(0) * nParticlesAve);
			val anpr: Double = (((pl(0) + 1)) * nParticlesAve);
			var total: Double = 0.0;
			var tmp: Double = 0.0;
			var y1: Double = edgely;
			do {
				tmp = total;
				total = (linearDensityFnIntegral(y1, 0.0, 0.0, 0.0) - y0);
				y1 = (y1 + 1.0);
			}			while (((total < anpl) && (y1 <= y)));
			var lb: Double = (((total > tmp))?((((y1 - 2.0)) + (((anpl - tmp)) / ((total - tmp))))):((y1 - 1.0)));
			if (here.isFirst()) {
				lb = 0.0;
			}
			do {
				tmp = total;
				total = (linearDensityFnIntegral(y1, 0.0, 0.0, 0.0) - y0);
				y1 = (y1 + 1.0);
			}			while (((total < anpr) && (y1 <= y)));
			var ub: Double = (((y1 - 2.0)) + (((anpr - tmp)) / ((total - tmp))));
			if (here.isLast()) {
				ub = y;
			}
			yRegions(pl) = Region.make([((((lb + 0.5)) as Int)..(((((ub + 0.5)) as Int) - 1)))]);
		}

		val pHere: Place = here;
		val dPlace: Dist{(self.rank == 1)} = (((Region.make([(0..(MAX_PLACES - 1))])->here)) as Dist{(self.rank == 1)});
		val rBuffer: Array[Region{(self.rank == 1)}]{(self.rank == 1)} = ((Array.make[Region{(self.rank == 1)}](dPlace)) as Array[Region{(self.rank == 1)}]{(self.rank == 1)});
		finish 		ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			val r: Region{(self.rank == 1)} = (yRegions(pl) as Region{(self.rank == 1)});
			async (pHere) {
				rBuffer(pl) = r;
			}
		}

		var dResult: Dist{(self.rank == 2)} = (((Region.makeEmpty(2)->here)) as Dist{(self.rank == 2)});
		for (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			val p: Place = Dist.makeUnique()(pl);
			val ry: Region{(self.rank == 1)} = (rBuffer(pl) as Region{(self.rank == 1)});
			val dLocal: Dist{(self.rank == 2)} = (((Region.make([ry, (0..XLENGTH)])->p)) as Dist{(self.rank == 2)});
			dResult = (dResult || dLocal);
		}
		return dResult;
	}

	public def linearDensityFn(val x: Double, val anlx: Double, val anxi: Double, val shift: Double): Double {
		val result: Double = (1.0 + (anlx * (((x * anxi) - shift))));
		if ((result < 0.0)) {
			Console.ERR.println(("Linear Density Error: result = " + result));
		}
		return result;
	}

	public def linearDensityFnIntegral(val x: Double, val anlx: Double, val anxi: Double, val shift: Double): Double {
		var result: Double = 0.0;
		if ((anxi == 0.0)) {
			result = x;
		} else {
			result = (x + (((0.5 * anlx) * x) * (((x * anxi) - (2.0 * shift)))));
		}
		if ((result < 0.0)) {
			Console.ERR.println((("Linear Density Integral Error: " + "result = ") + result));
		}
		return result;
	}

	public def stepTime(val ionBackground: Array[Double]{(self.rank == 2)}): Array[Double]{(self.rank == 1)} {
		var time1: Long = System.currentTimeMillis();
		var time2: Long = 0;
		val CHARGE: Double = ELECTRON_CHARGE;
		val dIonBG: Dist{(self.rank == 2)} = ionBackground.dist;
		val chargeDensityGuards: Array[Double]{(self.rank == 2)} = makeGuards(dIonBG);
		val chargeDensity: Array[Double]{(self.rank == 2)} = ((Array.make[Double](dIonBG, (pt:Point{(self.rank == 2)}) => {
			return 0.0;
		})) as Array[Double]{(self.rank == 2)});
		depositCharge(particles, chargeDensity, chargeDensityGuards, CHARGE);
		finish 		ateach (pt: Point{(self.rank == 2)} in dIonBG) {
			chargeDensity(pt) = (chargeDensity(pt) + ionBackground(pt));
		}

		addGuards(chargeDensity, chargeDensityGuards);
		time2 = System.currentTimeMillis();
		depositTime = (depositTime + ((time2 - time1)));
		val dOld: Dist{(self.rank == 2)} = chargeDensity.dist;
		val uniformChargeDensity: Array[Double]{(self.rank == 2)} = moveFieldToUniform(chargeDensity);
		val complexChargeDensity: Array[Complex]{(self.rank == 2)} = ArrayUtil.doubleToComplex(doubleDimensions(uniformChargeDensity));
		time1 = System.currentTimeMillis();
		redistTime = (redistTime + ((time1 - time2)));
		val complexForceX: Array[Complex]{(self.rank == 2)} = solver.makeForceArray();
		val complexForceY: Array[Complex]{(self.rank == 2)} = solver.makeForceArray();
		var we: Double = solver.getForceCharge(complexChargeDensity, complexForceX, complexForceY);
		time2 = System.currentTimeMillis();
		solverTime = (solverTime + ((time2 - time1)));
		val dChargeDensityGuards: Dist{(self.rank == 2)} = chargeDensityGuards.dist;
		val gx: Array[Double]{(self.rank == 2)} = (Array.make[Double](dChargeDensityGuards));
		val gy: Array[Double]{(self.rank == 2)} = (Array.make[Double](dChargeDensityGuards));
		val forceX: Array[Double]{(self.rank == 2)} = cropField(ArrayUtil.complexToDouble(complexForceX), gx);
		val forceY: Array[Double]{(self.rank == 2)} = cropField(ArrayUtil.complexToDouble(complexForceY), gy);
		val fcx: Array[Double]{(self.rank == 2)} = moveFieldToDist(forceX, dOld);
		val fcy: Array[Double]{(self.rank == 2)} = moveFieldToDist(forceY, dOld);
		loadGuards(fcx, gx);
		loadGuards(fcy, gy);
		time1 = System.currentTimeMillis();
		redistTime = (redistTime + ((time1 - time2)));
		var wk: Double = pushParticles(particles, fcx, gx, fcy, gy);
		time2 = System.currentTimeMillis();
		pushTime = (pushTime + ((time2 - time1)));
		val info: Array[Int]{(self.rank == 1)} = makeInfoArray();
		particles = moveParticles(particles, dOld, info);
		val particleAve: Double = (((info(6) as Double)) / Place.MAX_PLACES);
		val balance: Double = X10Util.maxDouble((info(1) - particleAve), (particleAve - ((info(2) / particleAve))));
		if ((balance > IMBALANCE)) {
			val count: Array[Int]{(self.rank == 1)} = countParticles(particles, dOld);
			val dNew: Dist{(self.rank == 2)} = repartition(count, dOld, particleAve);
			backgroundCharge = moveFieldToDist(backgroundCharge, dNew);
			particles = moveParticles(particles, dNew, info);
		}
		val dResult: Dist{(self.rank == 1)} = (((Region.make([(0..2)])->here)) as Dist{(self.rank == 1)});
		val result: Array[Double]{(self.rank == 1)} = (Array.make[Double](dResult));
		result(0) = we;
		result(1) = wk;
		result(2) = (we + wk);
		time1 = System.currentTimeMillis();
		moveTime = (moveTime + ((time1 - time2)));
		return result;
	}

	public def linearDensity(val x: Double, val anlx: Double, val anxi: Double, val shift: Double): Double {
		return (1.0 + (anlx * (((x * anxi) - shift))));
	}

	public def linearDensityIntegral(val x: Double, val anlx: Double, val anxi: Double, val shift: Double): Double {
		return ((anxi == 0))?x:(x + (((0.5 * anlx) * x) * (((x * anxi) - (2.0 * shift)))));
	}

	public def makeGuards(val dData: Dist{(self.rank == 2)}): Array[Double]{(self.rank == 2)} {
		var dResult: Dist{(self.rank == 2)} = (((Region.makeEmpty(2)->here)) as Dist{(self.rank == 2)});
		for (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			val p: Place = Dist.makeUnique()(pl);
			val rLocal1: Region{(self.rank == 1)} = X10Util.getPRank2D(dData, p, 0);
			val rLocal2: Region{(self.rank == 1)} = X10Util.getPRank2D(dData, p, 1);
			val ub: Int = rLocal1.max(0);
			dResult = (dResult || ((Region.make([((ub + 1)..(ub + 1)), rLocal2])->p)));
		}
		return (Array.make[Double](dResult, (pt:Point{(self.rank == 2)}) => {
			return 0.0;
		}));
	}

	public def depositCharge(val particles: Array[Particle]{(self.rank == 1)}, val fieldCharge: Array[Double]{(self.rank == 2)}, val guards: Array[Double]{(self.rank == 2)}, val charge: Double): Void {
		finish 		ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			val dFC: Dist{(self.rank == 2)} = fieldCharge.dist;
			val rLocalFC: Region{(self.rank == 2)} = X10Util.get2DLRegion(dFC);
			val dG: Dist{(self.rank == 2)} = guards.dist;
			val rLocalG: Region{(self.rank == 2)} = X10Util.get2DLRegion(dG);
			val dLocal: Dist{(self.rank == 2)} = (((((rLocalFC || rLocalG))->here)) as Dist{(self.rank == 2)});
			val localCharge: Array[Double]{(self.rank == 2)} = (Array.make[Double](dLocal, (pt:Point{(self.rank == 2)}) => {
				return 0.0;
			}));
			for (pt: Point{(self.rank == 1)} in (particles.dist | here)) {
				val p: Particle = particles(pt);
				val x: Int = (p.getX() as Int);
				val y: Int = (p.getY() as Int);
				val dx: Double = (p.getX() - x);
				val dy: Double = (p.getY() - y);
				localCharge((y + 1), (x + 1)) = (localCharge((y + 1), (x + 1)) + (((charge * dx) * dy)));
				localCharge((y + 1), x) = (localCharge((y + 1), x) + (((charge * ((1.0 - dx))) * dy)));
				localCharge(y, (x + 1)) = (localCharge(y, (x + 1)) + (((charge * dx) * ((1.0 - dy)))));
				localCharge(y, x) = (localCharge(y, x) + (((charge * ((1.0 - dx))) * ((1.0 - dy)))));
			}
			finish {
				foreach (pt: Point{(self.rank == 2)} in (fieldCharge.dist | here)) {
					fieldCharge(pt) = localCharge(pt);
				}
				foreach (pt: Point{(self.rank == 2)} in (guards.dist | here)) {
					guards(pt) = localCharge(pt);
				}
			}
		}

	}

	public def addGuards(val data: Array[Double]{(self.rank == 2)}, val guards: Array[Double]{(self.rank == 2)}): Void {
		val dGuard: Dist{(self.rank == 2)} = (guards.dist as Dist{(self.rank == 2)});
		finish 		ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			val dG: Dist{(self.rank == 2)} = guards.dist;
			val rLocalG: Region{(self.rank == 2)} = X10Util.get2DLRegion(dG);
			if (!here.isLast()) {
				val sBuffer: Array[Double]{(self.rank == 2)} = ((Array.make[Double](rLocalG, (pt:Point{(self.rank == 2)}) => {
					return guards(pt);
				})) as Array[Double]{(self.rank == 2)});
				finish 				async (here.next()) {
					val dData: Dist{(self.rank == 2)} = data.dist;
					val rLocalD: Region{(self.rank == 2)} = X10Util.get2DLRegion(dData);
					foreach (pt: Point{(self.rank == 2)} in ((rLocalD && rLocalG))) {
						data(pt) = (data(pt) + sBuffer(pt));
					}
				}

			}
			foreach (pt: Point{(self.rank == 2)} in rLocalG) {
				guards(pt) = 0.0;
			}
		}

	}

	public def doubleDimensions(val data: Array[Double]{(self.rank == 2)}): Array[Double]{(self.rank == 2)} {
		val rData: Region{(self.rank == 2)} = data.region;
		val rData1: Region{(self.rank == 1)} = X10Util.getRank2D(rData, 0);
		val rData2: Region{(self.rank == 1)} = X10Util.getRank2D(rData, 1);
		val dResult: Dist{(self.rank == 2)} = X10Util.distBlockStar2(Region.make([(0..((2 * rData1.size()) - 1))]), Region.make([(0..((2 * rData2.size()) - 1))]));
		val result: Array[Double]{(self.rank == 2)} = (Array.make[Double](dResult, (pt:Point{(self.rank == 2)}) => {
			return 0.0;
		}));
		finish 		ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			val pDst: Place = Place.places((pl(0) / 2));
			val dData: Dist{(self.rank == 2)} = data.dist;
			val rLocal: Region{(self.rank == 2)} = X10Util.get2DLRegion(dData);
			val sBuffer: Array[Double]{(self.rank == 2)} = ((Array.make[Double](rLocal, (pt:Point{(self.rank == 2)}) => {
				return data(pt);
			})) as Array[Double]{(self.rank == 2)});
			async (pDst) {
				foreach (pt: Point{(self.rank == 2)} in rLocal) {
					result(pt) = sBuffer(pt);
				}
			}
		}

		return result;
	}

	public def moveFieldToUniform(val data: Array[Double]{(self.rank == 2)}): Array[Double]{(self.rank == 2)} {
		val rData: Region{(self.rank == 2)} = data.region;
		return moveFieldToDist(data, X10Util.distBlockStar1(rData));
	}

	public def moveFieldToDist(val data: Array[Double]{(self.rank == 2)}, val dDst: Dist{(self.rank == 2)}): Array[Double]{(self.rank == 2)} {
		val MAX_ROWS: Int = MOVE_FIELD_TO_DIST_MAX_ROWS;
		val MAX_ITER: Int = 0;
		val dUnique: Dist{(self.rank == 1)} = (Dist.makeUnique() as Dist{(self.rank == 1)});
		val rData: Region{(self.rank == 2)} = data.region;
		val rRank1: Region{(self.rank == 1)} = X10Util.getRank2D(rData, 0);
		val rRank2t: Region{(self.rank == 1)} = X10Util.getRank2D(rData, 1);
		val rRank2: Region{(self.rank == 1)} = Region.make([(0..(rRank2t.size() - 1))]);
		val rRows: Region{(self.rank == 1)} = Region.make([(0..(MAX_ROWS - 1))]);
		val dBuffer: Dist{(self.rank == 3)} = X10Util.extendDistRank3D(X10Util.extendDistRank2D(dUnique, rRows), rRank2);
		val srcBuffer: Array[Double]{(self.rank == 3)} = (Array.make[Double](dBuffer, (pt:Point{(self.rank == 3)}) => {
			return 0.0;
		}));
		val dstBuffer: Array[Double]{(self.rank == 3)} = (Array.make[Double](dBuffer, (pt:Point{(self.rank == 3)}) => {
			return 0.0;
		}));
		val offsets: Array[Int]{(self.rank == 1)} = (Array.make[Int](dUnique, (pt:Point{(self.rank == 1)}) => {
			return 0;
		}));
		val sizes: Array[Int]{(self.rank == 1)} = (Array.make[Int](dUnique, (pt:Point{(self.rank == 1)}) => {
			return 0;
		}));
		val inLt: Array[Int]{(self.rank == 1)} = (Array.make[Int](dUnique, (pt:Point{(self.rank == 1)}) => {
			return 0;
		}));
		val inRt: Array[Int]{(self.rank == 1)} = (Array.make[Int](dUnique, (pt:Point{(self.rank == 1)}) => {
			return 0;
		}));
		val outLt: Array[Int]{(self.rank == 1)} = (Array.make[Int](dUnique, (pt:Point{(self.rank == 1)}) => {
			return 0;
		}));
		val outRt: Array[Int]{(self.rank == 1)} = (Array.make[Int](dUnique, (pt:Point{(self.rank == 1)}) => {
			return 0;
		}));
		finish 		ateach ((pl): Point{(self.rank == 1)} in Dist.makeUnique()) {
			val dData: Dist{(self.rank == 2)} = data.dist;
			val rLocal: Region{(self.rank == 2)} = X10Util.get2DLRegion(dData);
			val rLocal1: Region{(self.rank == 1)} = X10Util.getRank2D(rLocal, 0);
			if ((X10Util.ENABLE_CHECKS && (rLocal1.size() == 0))) {
				throw new RuntimeException("Region empty -- should not happen");
			}
			val offset: Int = rLocal1.min(0);
			foreach ((i, j): Point{(self.rank == 2)} in rLocal) {
				srcBuffer(pl, (i - offset), j) = data(i, j);
			}
			sizes(pl) = rLocal1.size();
			offsets(pl) = offset;
		}

		var mter: Int = MAX_ITER;
		var iter: Int = 0;
		var done: Boolean = false;
		do {
			finish 			ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
				val rLocalD: Region{(self.rank == 2)} = X10Util.get2DLRegion(dDst);
				val rLocalD1: Region{(self.rank == 1)} = X10Util.getRank2D(rLocalD, 0);
				if ((X10Util.ENABLE_CHECKS && (rLocalD1.size() == 0))) {
					throw new RuntimeException(("Region empty -- " + "cannot to move grids"));
				}
				outLt(pl) = 0;
				outRt(pl) = 0;
				val lBorder: Int = rLocalD1.min(0);
				val rBorder: Int = (rLocalD1.max(0) + 1);
				for ((i1): Point{(self.rank == 1)} in Region.make([(0..(sizes(pl) - 1))])) {
					val i2: Int = (i1 + offsets(pl));
					if ((i2 >= rBorder)) {
						outRt(pl) = (outRt(pl) + 1);
					} else 					if ((i2 < lBorder)) {
						outLt(pl) = (outLt(pl) + 1);
					}

				}
			}

			iter++;
			val pr: Array[Int]{(self.rank == 1)} = (Array.make[Int](dUnique, (pt:Point{(self.rank == 1)}) => {
				return 0;
			}));
			val ter: Array[Int]{(self.rank == 1)} = (Array.make[Int](dUnique, (pt:Point{(self.rank == 1)}) => {
				return 0;
			}));
			finish 			ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
				inLt(pl) = 0;
				inRt(pl) = 0;
			}

			finish 			ateach ((pl): Point{(self.rank == 1)} in Dist.makeUnique()) {
				if ((outRt(pl) > 0)) {
					val bsize: Int = outRt(pl);
					val rBuffer: Region{(self.rank == 2)} = Region.make([(0..(bsize - 1)), rRank2]);
					val sBuffer: Array[Double]{(self.rank == 2)} = ((Array.make[Double](rBuffer, ((i, j):Point{(self.rank == 2)}) => {
						return srcBuffer(pl, ((i + sizes(pl)) - outRt(pl)), j);
					})) as Array[Double]{(self.rank == 2)});
					async (here.next()) {
						foreach ((i, j): Point{(self.rank == 2)} in rBuffer) {
							dstBuffer(here.id, i, j) = sBuffer(i, j);
						}
						inLt(here.id) = bsize;
					}
				}
			}

			finish 			ateach ((pl): Point{(self.rank == 1)} in Dist.makeUnique()) {
				sizes(pl) = (sizes(pl) - outRt(pl));
				outRt(pl) = X10Util.maxInt(((sizes(pl) + inLt(pl)) - MAX_ROWS), 0);
				sizes(pl) = (sizes(pl) - outRt(pl));
				if ((outRt(pl) > 0)) {
					pr(pl) = X10Util.maxInt(pr(pl), outRt(pl));
					val rsize: Int = X10Util.minInt(outRt(pl), (MAX_ROWS - inLt(pl)));
					finish 					foreach ((i, j): Point{(self.rank == 2)} in Region.make([(0..(rsize - 1)), rRank2])) {
						dstBuffer(pl, ((MAX_ROWS - rsize) + i), j) = srcBuffer(pl, (sizes(pl) + i), j);
					}

				}
				if (((sizes(pl) > 0) && (inLt(pl) > 0))) {
					for ((i1): Point{(self.rank == 1)} in Region.make([(1..sizes(pl))])) {
						val i2: Int = (sizes(pl) - i1);
						finish 						foreach ((j): Point{(self.rank == 1)} in rRank2) {
							srcBuffer(pl, (i2 + inLt(pl)), j) = srcBuffer(pl, i2, j);
						}

					}
				}
				finish 				foreach (pt: Point{(self.rank == 3)} in Region.make([(pl..pl), (0..(inLt(pl) - 1)), rRank2])) {
					srcBuffer(pt) = dstBuffer(pt);
				}

				sizes(pl) = (sizes(pl) + inLt(pl));
				offsets(pl) = (offsets(pl) - inLt(pl));
			}

			finish 			ateach ((pl): Point{(self.rank == 1)} in Dist.makeUnique()) {
				if ((outLt(pl) > 0)) {
					val bsize: Int = outLt(pl);
					val rBuffer: Region{(self.rank == 2)} = Region.make([(0..(bsize - 1)), rRank2]);
					val sBuffer: Array[Double]{(self.rank == 2)} = ((Array.make[Double](rBuffer, ((i, j):Point{(self.rank == 2)}) => {
						return srcBuffer(pl, i, j);
					})) as Array[Double]{(self.rank == 2)});
					async (here.prev()) {
						foreach ((i, j): Point{(self.rank == 2)} in rBuffer) {
							dstBuffer(here.id, i, j) = sBuffer(i, j);
						}
						inRt(here.id) = bsize;
					}
				}
			}

			finish 			ateach ((pl): Point{(self.rank == 1)} in Dist.makeUnique()) {
				sizes(pl) = (sizes(pl) - outLt(pl));
				offsets(pl) = (offsets(pl) + outLt(pl));
				if (((sizes(pl) > 0) && (outLt(pl) > 0))) {
					for ((i): Point{(self.rank == 1)} in Region.make([(0..(sizes(pl) - 1))])) {
						finish 						foreach ((j): Point{(self.rank == 1)} in rRank2) {
							srcBuffer(pl, i, j) = srcBuffer(pl, (i + outLt(pl)), j);
						}

					}
				}
				outLt(pl) = X10Util.maxInt(((sizes(pl) + inRt(pl)) - MAX_ROWS), 0);
				if ((outLt(pl) > 0)) {
					pr(pl) = X10Util.maxInt(pr(pl), outLt(pl));
					inRt(pl) = (inRt(pl) - outLt(pl));
				} else 				if ((outRt(pl) > 0)) {
				}

				finish 				foreach ((i, j): Point{(self.rank == 2)} in Region.make([(0..(inRt(pl) - 1)), rRank2])) {
					srcBuffer(pl, (i + sizes(pl)), j) = dstBuffer(pl, i, j);
				}

				sizes(pl) = (sizes(pl) + inRt(pl));
				val rLocal1: Region{(self.rank == 1)} = X10Util.getLRank2D(dDst, 0);
				if ((X10Util.ENABLE_CHECKS && (rLocal1.size() == 0))) {
					throw new RuntimeException(("Destination distribution " + "is empty"));
				}
				val rsize: Int = rLocal1.size();
				val offset: Int = rLocal1.min(0);
				ter(pl) = ((Math.abs((sizes(pl) - rsize)) + Math.abs((offsets(pl) - offset))));
			}

			var ierr: Int = 0;
			if ((iter <= mter)) {
				if ((COMPILER_INSERTED_LIB.sum(pr) != 0)) {
					ierr = COMPILER_INSERTED_LIB.sum(pr);
					Console.ERR.println((("Local field overflow error, " + "ierr = ") + ierr));
					break;
				}
				if ((iter < mter)) {
					continue;
				} else {
					break;
				}
			}
			if ((iter > 10)) {
				throw new RuntimeException("Too many iterations");
			}
			val dFlags: Dist{(self.rank == 1)} = (Region.make([(0..1)])->here);
			val flags: Array[Int]{(self.rank == 1)} = (Array.make[Int](dFlags));
			flags(0) = COMPILER_INSERTED_LIB.sum(pr);
			flags(1) = COMPILER_INSERTED_LIB.sum(ter);
			if ((flags(0) != 0)) {
				ierr = flags(0);
				Console.ERR.println((("Global field overflow error, " + "ierr = ") + ierr));
				break;
			}
			if ((flags(1) != 0)) {
				Console.ERR.println((("Info: fields being passed " + "further = ") + flags(1)));
			} else {
				mter = iter;
				break;
			}
		}		while (!done);
		val result: Array[Double]{(self.rank == 2)} = (Array.make[Double](dDst));
		finish 		ateach ((pl): Point{(self.rank == 1)} in Dist.makeUnique()) {
			val rLocal1: Region{(self.rank == 1)} = X10Util.getLRank2D(dDst, 0);
			if (X10Util.ENABLE_CHECKS) {
				if ((sizes(pl) <= 0)) {
					throw new RuntimeException(("Cannot make non-existant " + "region"));
				}
				if (((sizes(pl) != rLocal1.size()) || (offsets(pl) != rLocal1.min(0)))) {
					throw new RuntimeException(("Regions don't match result " + "dist"));
				}
			}
			foreach ((i, j): Point{(self.rank == 2)} in Region.make([(0..(sizes(pl) - 1)), rRank2])) {
				result((offsets(pl) + i), j) = srcBuffer(pl, i, j);
			}
		}

		return result;
	}

	public def cropField(val data: Array[Double]{(self.rank == 2)}, val guards: Array[Double]{(self.rank == 2)}): Array[Double]{(self.rank == 2)} {
		val rRank1: Region{(self.rank == 1)} = Region.make([(0..(YLENGTH - 1))]);
		val rRank2: Region{(self.rank == 1)} = Region.make([(0..XLENGTH)]);
		val dGuard: Dist{(self.rank == 2)} = guards.dist;
		val rGuard: Region{(self.rank == 2)} = X10Util.get2DPRegion(dGuard, Place.places((Place.MAX_PLACES - 1)));
		val dResult: Dist{(self.rank == 2)} = X10Util.distBlockStar2(rRank1, rRank2);
		val result: Array[Double]{(self.rank == 2)} = (Array.make[Double](dResult));
		for (pl: Point{(self.rank == 1)} in Dist.makeUnique()) 		finish 		async (Dist.makeUnique()(pl)) {
			val dData: Dist{(self.rank == 2)} = data.dist;
			val rLocalS: Region{(self.rank == 2)} = X10Util.get2DLRegion(dData);
			val rLocalS1: Region{(self.rank == 1)} = X10Util.getRank2D(rLocalS, 0);
			if (((rLocalS.size() > 0) && (rLocalS1.min(0) < YLENGTH))) {
				val pDst1: Place = Place.places((2 * pl(0)));
				val pDst2: Place = Place.places(((2 * pl(0)) + 1));
				val sBuffer: Array[Double]{(self.rank == 2)} = ((Array.make[Double](rLocalS, (pt:Point{(self.rank == 2)}) => {
					return data(pt);
				})) as Array[Double]{(self.rank == 2)});
				async (pDst1) {
					val rLocalD: Region{(self.rank == 2)} = X10Util.get2DLRegion(dResult);
					foreach (pt: Point{(self.rank == 2)} in rLocalD) {
						result(pt) = sBuffer(pt);
					}
				}
				async (pDst2) {
					val rLocalD: Region{(self.rank == 2)} = X10Util.get2DLRegion(dResult);
					foreach (pt: Point{(self.rank == 2)} in rLocalD) {
						result(pt) = sBuffer(pt);
					}
				}
			}
			if (rLocalS1.contains(Point.make([YLENGTH]))) {
				val sBuffer: Array[Double]{(self.rank == 2)} = ((Array.make[Double](rGuard, (pt:Point{(self.rank == 2)}) => {
					return data(pt);
				})) as Array[Double]{(self.rank == 2)});
				async (Place.places((Place.MAX_PLACES - 1))) {
					foreach (pt: Point{(self.rank == 2)} in rGuard) {
						guards(pt) = sBuffer(pt);
					}
				}
			}
		}


		return result;
	}

	public def loadGuards(val data: Array[Double]{(self.rank == 2)}, val guards: Array[Double]{(self.rank == 2)}): Void {
		finish 		ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			if (!here.isFirst()) {
				val dGuard: Dist{(self.rank == 2)} = guards.dist;
				val rGuard: Region{(self.rank == 2)} = X10Util.get2DPRegion(dGuard, here.prev());
				val sBuffer: Array[Double]{(self.rank == 2)} = ((Array.make[Double](rGuard, (pt:Point{(self.rank == 2)}) => {
					return data(pt);
				})) as Array[Double]{(self.rank == 2)});
				async (here.prev()) {
					foreach (pt: Point{(self.rank == 2)} in rGuard) {
						guards(pt) = sBuffer(pt);
					}
				}
			}
		}

	}

	public def makeInfoArray(): Array[Int]{(self.rank == 1)} {
		val dInfo: Dist{(self.rank == 1)} = (((Region.make([(0..6)])->here)) as Dist{(self.rank == 1)});
		return (Array.make[Int](dInfo, (pt:Point{(self.rank == 1)}) => {
			return 0;
		}));
	}

	public def moveParticles(val particles: Array[Particle]{(self.rank == 1)}, val dFieldSpace: Dist{(self.rank == 2)}, val info: Array[Int]{(self.rank == 1)}): Array[Particle]{(self.rank == 1)} {
		val MAX: Int = MOVE_PARTICLES_MAX;
		val MAX_BUFFER: Int = MOVE_PARTICLES_MAX_BUFFER;
		val ITER_MAX: Int = 20;
		val rPart: Region{(self.rank == 1)} = Region.make([(0..(MAX - 1))]);
		val rBuffer: Region{(self.rank == 1)} = Region.make([(0..(MAX_BUFFER - 1))]);
		val dUnique: Dist{(self.rank == 1)} = (Dist.makeUnique() as Dist{(self.rank == 1)});
		val dPart: Dist{(self.rank == 2)} = X10Util.extendDistRank2D(dUnique, rPart);
		val dBuffer: Dist{(self.rank == 2)} = X10Util.extendDistRank2D(dUnique, rBuffer);
		val part: Array[Particle]{(self.rank == 2)} = (Array.make[Particle](dPart));
		val sBufferL: Array[Particle]{(self.rank == 2)} = (Array.make[Particle](dBuffer));
		val sBufferR: Array[Particle]{(self.rank == 2)} = (Array.make[Particle](dBuffer));
		val rBufferL: Array[Particle]{(self.rank == 2)} = (Array.make[Particle](dBuffer));
		val rBufferR: Array[Particle]{(self.rank == 2)} = (Array.make[Particle](dBuffer));
		val inLt: Array[Int]{(self.rank == 1) && (self.dist == dUnique)} = (Array.make[Int](dUnique, (pt:Point{(self.rank == 1)}) => {
			return 0;
		}));
		val inRt: Array[Int]{(self.rank == 1) && (self.dist == dUnique)} = (Array.make[Int](dUnique, (pt:Point{(self.rank == 1)}) => {
			return 0;
		}));
		val outLt: Array[Int]{(self.rank == 1) && (self.dist == dUnique)} = (Array.make[Int](dUnique, (pt:Point{(self.rank == 1)}) => {
			return 0;
		}));
		val outRt: Array[Int]{(self.rank == 1) && (self.dist == dUnique)} = (Array.make[Int](dUnique, (pt:Point{(self.rank == 1)}) => {
			return 0;
		}));
		val nPart: Array[Int]{(self.rank == 1) && (self.dist == dUnique)} = (Array.make[Int](dUnique, (pt:Point{(self.rank == 1)}) => {
			return 0;
		}));
		var nPartOld: Array[Int]{(self.rank == 1) && (self.dist == dUnique)} = (Array.make[Int](dUnique, (pt:Point{(self.rank == 1)}) => {
			return 0;
		}));
		val holes: Array[Int]{(self.rank == 2)} = (Array.make[Int](dPart, (pt:Point{(self.rank == 2)}) => {
			return 0;
		}));
		val dFlags: Dist{(self.rank == 1)} = (Region.make([(0..3)])->here);
		val nSent: Array[Int]{(self.rank == 1) && (self.dist == dUnique)} = (Array.make[Int](dUnique, (pt:Point{(self.rank == 1)}) => {
			return 0;
		}));
		val ibflg: Array[Int]{(self.rank == 1)} = (Array.make[Int](dFlags, (pt:Point{(self.rank == 1)}) => {
			return 0;
		}));
		finish 		ateach ((pl): Point{(self.rank == 1)} in Dist.makeUnique()) {
			val rLocal: Region{(self.rank == 1)} = (((particles.dist | here)).region as Region{(self.rank == 1)});
			if ((rLocal.size() > 0)) {
				val offset: Int = rLocal.min(0);
				foreach ((i): Point{(self.rank == 1)} in rLocal) {
					part(pl, (i - offset)) = particles(i);
				}
			}
			nPart(pl) = rLocal.size();
		}

		val rFieldSpace: Region{(self.rank == 2)} = dFieldSpace.region;
		val rsize: Double = X10Util.getRank2D(rFieldSpace, 1).size();
		var iter: Int = 2;
		var nter: Int = 0;
		finish 		foreach (pt: Point{(self.rank == 1)} in Region.make([(0..6)])) {
			info(pt) = 0;
		}

		nPartOld = (nPartOld + nPart);
		var doneInner: Boolean = false;
		var doneOuter: Boolean = false;
		do {
			var mter: Int = 0;
			val overflow: Array[Int]{(self.rank == 1) && (self.dist == dUnique)} = (Array.make[Int](dUnique, (pt:Point{(self.rank == 1)}) => {
				return 0;
			}));
			finish 			ateach ((pl): Point{(self.rank == 1)} in Dist.makeUnique()) {
				val rLocal: Region{(self.rank == 2)} = (((dFieldSpace | here)).region as Region{(self.rank == 2)});
				val rLocal1: Region{(self.rank == 1)} = X10Util.getRank2D(rLocal, 0);
				val ub: Double = (rLocal1.max(0) + 1);
				val lb: Double = rLocal1.min(0);
				outLt(pl) = 0;
				outRt(pl) = 0;
				for ((i): Point{(self.rank == 1)} in Region.make([(0..(nPart(pl) - 1))])) {
					val p: Particle = part(pl, i);
					var y: Double = p.getY();
					if ((y < lb)) {
						if ((outLt(pl) < rBuffer.size())) {
							if (here.isFirst()) {
								y = (y + rsize);
							}
							sBufferL(pl, outLt(pl)) = p.setY(y);
							holes(pl, (outLt(pl) + outRt(pl))) = i;
							outLt(pl)++;
						} else {
							overflow(pl) = 1;
							break;
						}
					} else 					if ((y >= ub)) {
						if ((outRt(pl) < rBuffer.size())) {
							if (here.isLast()) {
								y = (y - rsize);
							}
							sBufferR(pl, outRt(pl)) = p.setY(y);
							holes(pl, (outLt(pl) + outRt(pl))) = i;
							outRt(pl)++;
						} else {
							overflow(pl) = 1;
							break;
						}
					}

				}
				nSent(pl) = (outLt(pl) + outRt(pl));
			}

			ibflg(2) = COMPILER_INSERTED_LIB.sum(overflow);
			do {
				iter = (iter + 2);
				mter = (mter + 1);
				transferParticles(sBufferL, sBufferR, outLt, outRt, rBufferL, rBufferR, inLt, inRt);
				val outgoing: Array[Int]{(self.rank == 1)} = (Array.make[Int](dUnique));
				finish 				ateach ((pl): Point{(self.rank == 1)} in Dist.makeUnique()) {
					val rLocal: Region{(self.rank == 2)} = (((dFieldSpace | here)).region as Region{(self.rank == 2)});
					val rLocal1: Region{(self.rank == 1)} = X10Util.getRank2D(rLocal, 0);
					val ub: Double = (rLocal1.max(0) + 1);
					val lb: Double = rLocal1.min(0);
					outLt(pl) = 0;
					outRt(pl) = 0;
					var belowLeft: Int = 0;
					for ((i): Point{(self.rank == 1)} in Region.make([(0..(inRt(pl) - 1))])) {
						val p: Particle = rBufferR(pl, i);
						val y: Double = p.getY();
						if ((y < lb)) {
							outLt(pl)++;
						}
						if ((y >= ub)) {
							outRt(pl)++;
						}
					}
					if ((outRt(pl) != 0)) {
						Console.ERR.println("Info: particles returning up");
					}
					for ((i): Point{(self.rank == 1)} in Region.make([(0..(inLt(pl) - 1))])) {
						val p: Particle = rBufferL(pl, i);
						val y: Double = p.getY();
						if ((y >= ub)) {
							outRt(pl)++;
						}
						if ((y < lb)) {
							belowLeft++;
						}
					}
					if ((belowLeft != 0)) {
						Console.ERR.println("Info: particles returning down");
					}
					outLt(pl) = (outLt(pl) + belowLeft);
					outgoing(pl) = (outLt(pl) + outRt(pl));
				}

				ibflg(1) = COMPILER_INSERTED_LIB.sum(outgoing);
				if ((ibflg(1) != 0)) {
					finish 					ateach ((pl): Point{(self.rank == 1)} in Dist.makeUnique()) {
						val rLocal: Region{(self.rank == 2)} = (((dFieldSpace | here)).region as Region{(self.rank == 2)});
						val rLocal1: Region{(self.rank == 1)} = X10Util.getRank2D(rLocal, 0);
						val ub: Double = (rLocal1.max(0) + 1);
						val lb: Double = rLocal1.min(0);
						outLt(pl) = 0;
						outRt(pl) = 0;
						overflow(pl) = 0;
						var inRightStaying: Int = 0;
						for ((i): Point{(self.rank == 1)} in Region.make([(0..(inRt(pl) - 1))])) {
							val p: Particle = rBufferR(pl, i);
							var y: Double = p.getY();
							if ((y < lb)) {
								if (here.isFirst()) {
									y = (y + rsize);
								}
								sBufferL(pl, outLt(pl)) = p.setY(y);
								outLt(pl)++;
							} else 							if ((y >= ub)) {
								Console.ERR.println((("Particle returning up -- " + "should not happen: ") + p.toString()));
								if (here.isLast()) {
									y = (y - rsize);
								}
								sBufferR(pl, outRt(pl)) = p.setY(y);
								outRt(pl)++;
							} else {
								rBufferR(pl, inRightStaying) = p;
								inRightStaying++;
							}

						}
						inRt(pl) = inRightStaying;
						var inLeftStaying: Int = 0;
						for ((i): Point{(self.rank == 1)} in Region.make([(0..(inLt(pl) - 1))])) {
							val p: Particle = rBufferL(pl, i);
							var y: Double = p.getY();
							if ((y >= ub)) {
								if ((outRt(pl) < rBuffer.size())) {
									if (here.isLast()) {
										y = (y - rsize);
									}
									sBufferR(pl, outRt(pl)) = p.setY(y);
									outRt(pl)++;
								} else {
									overflow(pl) = (2 * rBuffer.size());
									break;
								}
							} else 							if ((y < lb)) {
								if ((outLt(pl) < rBuffer.size())) {
									if (here.isFirst()) {
										y = (y + rsize);
									}
									sBufferL(pl, outLt(pl)) = p.setY(y);
									outLt(pl)++;
								} else {
									overflow(pl) = (2 * rBuffer.size());
									break;
								}
							} else {
								rBufferL(pl, inLeftStaying) = p;
								inLeftStaying++;
							}

						}
						inLt(pl) = inLeftStaying;
					}

				}
				val rSize: Int = rPart.size();
				val tMaxSize: Array[Int]{(self.rank == 1) && (self.dist == dUnique)} = ((((nPart + inLt) + inRt) - nSent));
				val tMinSize: Array[Int]{(self.rank == 1) && (self.dist == dUnique)} = (((Array.make[Int](dUnique, (pt:Point{(self.rank == 1)}) => {
					return rSize;
				}))) - tMaxSize);
				info(1) = COMPILER_INSERTED_LIB.sum(tMaxSize);
				info(2) = (rSize - COMPILER_INSERTED_LIB.sum(tMinSize));
				var err: Int = (ibflg(1) - rPart.size());
				if ((err > 0)) {
					Console.ERR.println(("Particle overflow error, err = " + err));
					info(0) = err;
					return particles;
				}
				finish 				ateach ((pl): Point{(self.rank == 1)} in Dist.makeUnique()) {
					var holesTotal: Int = nSent(pl);
					var holesLeft: Int = nSent(pl);
					var nMove: Int = X10Util.minInt(holesLeft, inLt(pl));
					finish 					foreach ((i): Point{(self.rank == 1)} in Region.make([(0..(nMove - 1))])) {
						part(pl, holes(pl, i)) = rBufferL(pl, i);
					}

					holesLeft = (holesLeft - inLt(pl));
					if ((holesLeft > 0)) {
						nMove = X10Util.minInt(holesLeft, inRt(pl));
						for ((i): Point{(self.rank == 1)} in Region.make([(0..(nMove - 1))])) {
							part(pl, holes(pl, (i + inLt(pl)))) = rBufferR(pl, i);
						}
					} else {
						nMove = -holesLeft;
						for ((i): Point{(self.rank == 1)} in Region.make([(0..(nMove - 1))])) {
							part(pl, (i + nPart(pl))) = rBufferL(pl, (i + holesTotal));
						}
					}
					if ((holesLeft <= 0)) {
						nPart(pl) = (nPart(pl) - holesLeft);
						holesTotal = inLt(pl);
					}
					holesLeft = (holesTotal - ((inLt(pl) + inRt(pl))));
					if ((holesLeft > 0)) {
						holesTotal = (inLt(pl) + inRt(pl));
						inRt(pl) = holesLeft;
					} else {
						holesTotal = (holesTotal - inLt(pl));
						inRt(pl) = -holesLeft;
					}
					for ((i): Point{(self.rank == 1)} in Region.make([(0..(inRt(pl) - 1))])) {
						if ((holesLeft > 0)) {
							val i1: Int = ((nPart(pl) - i) - 1);
							val i2: Int = (((holesTotal + holesLeft) - i) - 1);
							if ((i1 > holes(pl, i2))) {
								part(pl, holes(pl, i2)) = part(pl, i1);
							}
						} else {
							part(pl, (i + nPart(pl))) = rBufferR(pl, (i + holesTotal));
						}
					}
					if ((holesLeft > 0)) {
						nPart(pl) = (nPart(pl) - inRt(pl));
					} else {
						nPart(pl) = (nPart(pl) + inRt(pl));
					}
					nSent(pl) = 0;
				}

				info(4) = X10Util.maxInt(info(4), mter);
				if ((ibflg(1) > 0)) {
					Console.ERR.println((("Info: particles being passed" + " further = ") + ibflg(1)));
					if ((ibflg(2) > 0)) {
						ibflg(2) = 1;
					}
					if ((iter >= ITER_MAX)) {
						err = -(((iter - (2 / 2))));
						Console.ERR.println((("Iteration overflow, " + "iter = ") + err));
						info(0) = err;
						doneInner = true;
						doneOuter = true;
					}
				} else {
					doneInner = true;
				}
			}			while (!doneInner);
			if ((!doneOuter && (ibflg(2) > 0))) {
				nter++;
				info(3) = nter;
			} else {
				doneOuter = true;
			}
		}		while (!doneOuter);
		info(5) = COMPILER_INSERTED_LIB.sum(nPart);
		info(6) = COMPILER_INSERTED_LIB.sum(nPartOld);
		if ((info(5) != info(6))) {
			Console.ERR.println(((("Particle number error, old/new = " + info(0)) + " ") + info(1)));
			info(0) = 1;
		}
		if ((nter > 0)) {
			Console.ERR.println((((("Info: " + nter) + " buffer overflows, ") + "nbmax = ") + rBuffer.size()));
		}
		return packParticleArray(part, nPart);
	}

	private def transferParticles(val leftOutBuffer: Array[Particle]{(self.rank == 2)}, val rightOutBuffer: Array[Particle]{(self.rank == 2)}, val outLeft: Array[Int]{(self.rank == 1)}, val outRight: Array[Int]{(self.rank == 1)}, val leftInBuffer: Array[Particle]{(self.rank == 2)}, val rightInBuffer: Array[Particle]{(self.rank == 2)}, val inLeft: Array[Int]{(self.rank == 1)}, val inRight: Array[Int]{(self.rank == 1)}): Void {
		finish 		ateach ((pl): Point{(self.rank == 1)} in Dist.makeUnique()) {
			val pLeft: Place = here.prev();
			val pRight: Place = here.next();
			val rLeft: Region{(self.rank == 1)} = Region.make([(0..(outLeft(pl) - 1))]);
			val rRight: Region{(self.rank == 1)} = Region.make([(0..(outRight(pl) - 1))]);
			val lBuffer: Array[Particle]{(self.rank == 1)} = ((Array.make[Particle](rLeft, ((i):Point{(self.rank == 1)}) => {
				return leftOutBuffer(pl, i);
			})) as Array[Particle]{(self.rank == 1)});
			val rBuffer: Array[Particle]{(self.rank == 1)} = ((Array.make[Particle](rRight, ((i):Point{(self.rank == 1)}) => {
				return rightOutBuffer(pl, i);
			})) as Array[Particle]{(self.rank == 1)});
			async (pLeft) {
				val pid: Int = here.id;
				inRight(pid) = lBuffer.region.size();
				foreach ((i): Point{(self.rank == 1)} in lBuffer) {
					rightInBuffer(pid, i) = lBuffer(i);
				}
			}
			async (pRight) {
				val pid: Int = here.id;
				inLeft(pid) = rBuffer.region.size();
				foreach ((i): Point{(self.rank == 1)} in rBuffer) {
					leftInBuffer(pid, i) = rBuffer(i);
				}
			}
		}

	}

	private def packParticleArray(val part: Array[Particle]{(self.rank == 2)}, val nParticles: Array[Int]{(self.rank == 1)}): Array[Particle]{(self.rank == 1)} {
		var lb: Int = 0;
		var dResult: Dist{(self.rank == 1)} = (((Region.makeEmpty(1)->here)) as Dist{(self.rank == 1)});
		for (pt: Point{(self.rank == 1)} in Dist.makeUnique()) {
			var rsize: Int = X10Util.pullInt(nParticles, pt);
			var p: Place = Place.places(pt(0));
			dResult = (dResult || ((Region.make([(lb..((lb + rsize) - 1))])->p)));
			lb = (lb + rsize);
		}
		val result: Array[Particle]{(self.rank == 1)} = (Array.make[Particle](dResult));
		finish 		ateach ((pl): Point{(self.rank == 1)} in Dist.makeUnique()) {
			val rLocal: Region{(self.rank == 1)} = (((result.dist | here)).region as Region{(self.rank == 1)});
			if ((rLocal.size() > 0)) {
				val offset: Int = rLocal.min(0);
				foreach ((i): Point{(self.rank == 1)} in rLocal) {
					result(i) = part(pl, (i - offset));
				}
			}
		}

		return result;
	}

	public def pushParticles(val particles: Array[Particle]{(self.rank == 1)}, val forceChargeX: Array[Double]{(self.rank == 2)}, val fcGuardX: Array[Double]{(self.rank == 2)}, val forceChargeY: Array[Double]{(self.rank == 2)}, val fcGuardY: Array[Double]{(self.rank == 2)}): Double {
		var lx: Double = 0.0;
		var ly: Double = 0.0;
		var rx: Double = 0.0;
		var ry: Double = 0.0;
		switch (BOUNDARY_CONDITION) {
			case 1:
				rx = XLENGTH;
				break;
			case 2:
				lx = 1.0;
				ly = 1.0;
				rx = (XLENGTH - 1.0);
				ry = (YLENGTH - 1.0);
				break;
			case 3:
				lx = 1.0;
				rx = (XLENGTH - 1.0);
				break;
}
		val edgelx: Double = lx;
		val edgely: Double = ly;
		val edgerx: Double = rx;
		val edgery: Double = ry;
		val quantum: Double = (ELECTRON_CHARGE * DELTA_TIME);
		val dEnergy: Dist{(self.rank == 1)} = particles.dist;
		val energy: Array[Double]{(self.rank == 1)} = (Array.make[Double](dEnergy));
		finish 		ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			val fcLocalX: Array[Double]{(self.rank == 2)} = makeLocalSpace(forceChargeX, fcGuardX);
			val fcLocalY: Array[Double]{(self.rank == 2)} = makeLocalSpace(forceChargeY, fcGuardY);
			foreach (pt: Point{(self.rank == 1)} in (particles.dist | here)) {
				pushParticle(particles, pt, fcLocalX, fcLocalY, quantum, edgelx, edgely, edgerx, edgery, energy);
			}
		}

		return ((0.125 * COMPILER_INSERTED_LIB.sum(energy)));
	}

	private def makeLocalSpace(val data: Array[Double]{(self.rank == 2)}, val guards: Array[Double]{(self.rank == 2)}): Array[Double]{(self.rank == 2)} {
		val dData: Dist{(self.rank == 2)} = data.dist;
		val dGuard: Dist{(self.rank == 2)} = guards.dist;
		val rLocalD: Region{(self.rank == 2)} = X10Util.get2DLRegion(dData);
		val rLocalG: Region{(self.rank == 2)} = X10Util.get2DLRegion(dGuard);
		val dResult: Dist{(self.rank == 2)} = (((((rLocalD || rLocalG))->here)) as Dist{(self.rank == 2)});
		val result: Array[Double]{(self.rank == 2)} = (Array.make[Double](dResult));
		finish {
			foreach (pt: Point{(self.rank == 2)} in (data.dist | here)) {
				result(pt) = data(pt);
			}
			foreach (pt: Point{(self.rank == 2)} in (guards.dist | here)) {
				result(pt) = guards(pt);
			}
		}
		return result;
	}

	private def pushParticle(val particles: Array[Particle]{(self.rank == 1)}, val pt: Point{(self.rank == 1)}, val forceChargeX: Array[Double]{(self.rank == 2)}, val forceChargeY: Array[Double]{(self.rank == 2)}, val quantum: Double, val edgelx: Double, val edgely: Double, val edgerx: Double, val edgery: Double, val energy: Array[Double]{(self.rank == 1)}): Void {
		val p: Particle = particles(pt);
		val x: Int = (p.getX() as Int);
		val y: Int = (p.getY() as Int);
		val dx: Double = (p.getX() - x);
		val dy: Double = (p.getY() - y);
		val mx: Double = (1.0 - dx);
		val my: Double = (1.0 - dy);
		val ax: Double = (((dy * (((dx * forceChargeX((y + 1), (x + 1))) + (mx * forceChargeX((y + 1), x))))) + (my * (((dx * forceChargeX(y, (x + 1))) + (mx * forceChargeX(y, x)))))));
		val ay: Double = (((dy * (((dx * forceChargeY((y + 1), (x + 1))) + (mx * forceChargeY((y + 1), x))))) + (my * (((dx * forceChargeY(y, (x + 1))) + (mx * forceChargeY(y, x)))))));
		var vx: Double = (p.getXVel() + (quantum * ax));
		var vy: Double = (p.getYVel() + (quantum * ay));
		var nx: Double = (p.getX() + (vx * DELTA_TIME));
		var ny: Double = (p.getY() + (vy * DELTA_TIME));
		energy(pt) = ((Math.pow((vx + p.getXVel()), 2) + Math.pow((vy + p.getYVel()), 2)));
		switch (BOUNDARY_CONDITION) {
			case 1:
				if ((nx < edgelx)) {
					nx = (nx + edgerx);
				}
				if ((nx >= edgerx)) {
					nx = (nx - edgerx);
				}
				break;
			case 2:
				if (((ny < edgely) || (ny >= edgery))) {
					ny = p.getY();
					vy = -vy;
				}
			case 3:
				if (((nx < edgelx) || (nx >= edgerx))) {
					nx = p.getX();
					vx = -vx;
				}
				break;
}
		particles(pt) = new Particle(nx, ny, vx, vy);
	}

	public def countParticles(val particles: Array[Particle]{(self.rank == 1)}, val dField: Dist{(self.rank == 2)}): Array[Int]{(self.rank == 1)} {
		val dCount: Dist{(self.rank == 1)} = X10Util.get2DDistRank(dField, 0);
		val count: Array[Int]{(self.rank == 1)} = (Array.make[Int](dCount, (pt:Point{(self.rank == 1)}) => {
			return 0;
		}));
		finish 		ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			for (pt: Point{(self.rank == 1)} in (particles.dist | here)) {
				count((particles(pt).getY() as Int))++;
			}
		}

		return count;
	}

	public def repartition(val particleCount: Array[Int]{(self.rank == 1)}, val dField: Dist{(self.rank == 2)}, val countAve: Double): Dist{(self.rank == 2)} {
		val MAX: Int = 32;
		val dUnique: Dist{(self.rank == 1)} = (Dist.makeUnique() as Dist{(self.rank == 1)});
		val dGrids: Dist{(self.rank == 1)} = X10Util.get2DDistRank(dField, 0);
		val rField: Region{(self.rank == 2)} = dField.region;
		val rRows: Region{(self.rank == 1)} = X10Util.getRank2D(rField, 1);
		val border: Array[Double]{(self.rank == 1)} = (Array.make[Double](dUnique, (pt:Point{(self.rank == 1)}) => {
			return 0.0;
		}));
		val particleTotals: Array[Int]{(self.rank == 1)} = (Array.make[Int](dUnique, (pt:Point{(self.rank == 1)}) => {
			return 0;
		}));
		val gridTotals: Array[Int]{(self.rank == 1)} = (Array.make[Int](dUnique, (pt:Point{(self.rank == 1)}) => {
			return 0;
		}));
		val dBuffer: Dist{(self.rank == 2)} = X10Util.extendDistRank2D((Dist.makeUnique() as Dist{(self.rank == 1)}), Region.make([(0..(MAX - 1))]));
		val sCountBuffer: Array[Int]{(self.rank == 2)} = (Array.make[Int](dBuffer));
		val rCountBuffer: Array[Int]{(self.rank == 2)} = (Array.make[Int](dBuffer));
		val sendCount: Array[Int]{(self.rank == 1)} = (Array.make[Int](dUnique));
		val sendGrids: Array[Int]{(self.rank == 1)} = (Array.make[Int](dUnique));
		val recvCount: Array[Int]{(self.rank == 1)} = (Array.make[Int](dUnique));
		val recvGrids: Array[Int]{(self.rank == 1)} = (Array.make[Int](dUnique));
		val finished: Array[Int]{(self.rank == 1)} = (Array.make[Int](dUnique, (pt:Point{(self.rank == 1)}) => {
			return 0;
		}));
		finish 		ateach ((pl): Point{(self.rank == 1)} in Dist.makeUnique()) {
			val dPC: Dist{(self.rank == 1)} = particleCount.dist;
			val rLocal: Region{(self.rank == 1)} = X10Util.get1DLRegion(dPC);
			val rsize: Int = rLocal.size();
			val offset: Int = ((rsize > 0))?rLocal.min(0):0;
			var total: Int = 0;
			for ((i): Point{(self.rank == 1)} in (particleCount.dist | here)) {
				val count: Int = particleCount(i);
				sCountBuffer(pl, (i - offset)) = count;
				total = (total + count);
			}
			particleTotals(pl) = total;
			gridTotals(pl) = rsize;
			sendCount(pl) = total;
			sendGrids(pl) = rsize;
			recvCount(pl) = 0;
			recvGrids(pl) = 0;
		}

		val runningPTotals: Array[Int]{(self.rank == 1)} = parallelPrefix(particleTotals);
		val runningGTotals: Array[Int]{(self.rank == 1)} = parallelPrefix(gridTotals);
		finish 		ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			particleTotals(pl) = runningPTotals(pl);
			gridTotals(pl) = runningGTotals(pl);
		}

		do {
			finish 			ateach ((pl): Point{(self.rank == 1)} in Dist.makeUnique()) {
				val expected: Int = ((((((pl + 1)) * countAve) + 0.5)) as Int);
				val surplus: Int = (runningPTotals(pl) - expected);
				if ((surplus > 0.0)) {
					val dSBuffer: Region{(self.rank == 1)} = Region.make([(0..(sendGrids(pl) - 1))]);
					val sBuffer: Array[Int]{(self.rank == 1)} = ((Array.make[Int](dSBuffer, ((i):Point{(self.rank == 1)}) => {
						return sCountBuffer(pl, i);
					})) as Array[Int]{(self.rank == 1)});
					val nCount: Int = sendCount(pl);
					val nGrids: Int = sendGrids(pl);
					finish 					async (here.next()) {
						recvCount(here.id) = nCount;
						recvGrids(here.id) = nGrids;
						finish 						foreach ((i): Point{(self.rank == 1)} in dSBuffer) {
							rCountBuffer(here.id, i) = sBuffer(i);
						}

					}

				}
			}

			finish 			ateach ((pl): Point{(self.rank == 1)} in Dist.makeUnique()) {
				finished(pl) = 0;
				val expected1: Int = ((((pl * countAve) + 0.5)) as Int);
				val expected2: Int = ((((((pl + 1)) * countAve) + 0.5)) as Int);
				val lDeficit: Int = ((expected1 - runningPTotals(pl)) + sendCount(pl));
				val rDeficit: Int = (runningPTotals(pl) - expected2);
				if ((lDeficit < 0.0)) {
					if (((lDeficit + recvCount(pl)) >= 0.0)) {
						var total: Int = 0;
						var oldTotal: Int = 0;
						var gsize: Int = recvGrids(pl);
						var i: Int = gsize;
						do {
							i--;
							oldTotal = total;
							total = (total - rCountBuffer(pl, i));
						}						while (((total > lDeficit) && (i >= 0)));
						var offset: Double = ((((gsize - i)) + ((((((lDeficit - oldTotal)) as Double)) / ((((total - oldTotal)) as Double))))));
						border(pl) = ((runningGTotals(pl) - sendGrids(pl)) - offset);
					} else {
						finished(pl)++;
					}
				} else 				if ((runningPTotals(pl) >= expected1)) {
					var gsize: Int = sendGrids(pl);
					var i: Int = 0;
					var total: Int = 0;
					var oldTotal: Int = 0;
					do {
						oldTotal = total;
						total = (total + sCountBuffer(pl, i));
						i++;
					}					while (((total < lDeficit) && (i < gsize)));
					var offset: Double = (i - 1);
					if ((total > oldTotal)) {
						offset = (offset + ((((((lDeficit - oldTotal)) as Double)) / ((((total - oldTotal)) as Double)))));
					}
					border(pl) = ((runningGTotals(pl) - sendGrids(pl)) + offset);
				}

				if ((rDeficit > sendCount(pl))) {
					finished(pl)++;
				}
				if (!here.isFirst()) {
					var gsize: Int = recvGrids(pl);
					runningPTotals(pl) = (runningPTotals(pl) - sendCount(pl));
					runningGTotals(pl) = (runningGTotals(pl) - sendGrids(pl));
					sendCount(pl) = recvCount(pl);
					sendGrids(pl) = recvGrids(pl);
					finish 					foreach (pt: Point{(self.rank == 2)} in Region.make([(pl..pl), (0..(gsize - 1))])) {
						sCountBuffer(pt) = rCountBuffer(pt);
					}

				}
			}

		}		while ((COMPILER_INSERTED_LIB.sum(finished) != 0));
		finish 		ateach ((pl): Point{(self.rank == 1)} in Dist.makeUnique()) {
			val dPC: Dist{(self.rank == 1)} = particleCount.dist;
			val rLocal: Region{(self.rank == 1)} = X10Util.get1DLRegion(dPC);
			val rsize: Int = rLocal.size();
			val offset: Int = ((rsize > 0))?rLocal.min(0):0;
			var total: Int = 0;
			for ((i): Point{(self.rank == 1)} in (particleCount.dist | here)) {
				val count: Int = particleCount(i);
				sCountBuffer(pl, (i - offset)) = count;
				total = (total + count);
			}
			runningPTotals(pl) = particleTotals(pl);
			runningGTotals(pl) = gridTotals(pl);
			sendCount(pl) = total;
			sendGrids(pl) = rsize;
			recvCount(pl) = 0;
			recvGrids(pl) = 0;
		}

		do {
			finish 			ateach ((pl): Point{(self.rank == 1)} in Dist.makeUnique()) {
				val expected: Int = ((((pl * countAve) + 0.5)) as Int);
				val lDeficit: Int = ((((((((((pl - 1)) * countAve) + 0.5)) as Int)) - runningPTotals(pl)) + sendCount(pl)));
				if ((lDeficit > 0)) {
					val dSBuffer: Region{(self.rank == 1)} = Region.make([(0..(sendGrids(pl) - 1))]);
					val sBuffer: Array[Int]{(self.rank == 1)} = ((Array.make[Int](dSBuffer, ((i):Point{(self.rank == 1)}) => {
						return sCountBuffer(pl, i);
					})) as Array[Int]{(self.rank == 1)});
					val nCount: Int = sendCount(pl);
					val nGrids: Int = sendGrids(pl);
					finish 					async (here.prev()) {
						recvCount(here.id) = nCount;
						recvGrids(here.id) = nGrids;
						foreach ((i): Point{(self.rank == 1)} in dSBuffer) {
							rCountBuffer(here.id, i) = sBuffer(i);
						}
					}

				}
			}

			finish 			ateach ((pl): Point{(self.rank == 1)} in Dist.makeUnique()) {
				finished(pl) = 0;
				val expected1: Int = ((((pl * countAve) + 0.5)) as Int);
				val expected2: Int = ((((((pl - 1)) * countAve) + 0.5)) as Int);
				val lDeficit: Int = ((expected2 - runningPTotals(pl)) + sendCount(pl));
				val rDeficit: Int = (runningPTotals(pl) - expected1);
				if ((runningPTotals(pl) < expected1)) {
					if (((runningPTotals(pl) + recvCount(pl)) >= expected1)) {
						val gsize: Int = recvGrids(pl);
						var oldTotal: Int = 0;
						var total: Int = 0;
						var i: Int = 0;
						do {
							oldTotal = total;
							total = (total + rCountBuffer(pl, i));
							i++;
						}						while (((total < -rDeficit) && (i < gsize)));
						val offset: Int = ((((i - 1) + ((((((-rDeficit - oldTotal)) as Double)) / ((((total - oldTotal)) as Double)))))) as Int);
						border(pl) = (runningGTotals(pl) + offset);
					} else {
						finished(pl)++;
					}
				}
				if ((lDeficit > sendCount(pl))) {
					finished(pl)++;
				}
				if (!here.isLast()) {
					val count: Int = recvCount(pl);
					val gsize: Int = recvGrids(pl);
					runningPTotals(pl) = (runningPTotals(pl) + count);
					runningGTotals(pl) = (runningGTotals(pl) + gsize);
					sendCount(pl) = count;
					sendGrids(pl) = gsize;
					foreach (pt: Point{(self.rank == 2)} in Region.make([(pl..pl), (0..(gsize - 1))])) {
						sCountBuffer(pt) = rCountBuffer(pt);
					}
				}
			}

		}		while ((COMPILER_INSERTED_LIB.sum(finished) != 0));
		val pHere: Place = here;
		val dBounds: Dist{(self.rank == 1)} = (Region.make([(0..Place.MAX_PLACES)])->here);
		val lowerbounds: Array[Int]{(self.rank == 1)} = (Array.make[Int](dBounds));
		val upperbounds: Array[Int]{(self.rank == 1)} = (Array.make[Int](dBounds));
		finish 		ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			val edge: Double = border(pl);
			async (pHere) {
				lowerbounds(pl) = (((edge + 0.5)) as Int);
			}
		}

		for (i: Point{(self.rank == 1)} in Region.make([(0..(Place.MAX_PLACES - 1))])) {
			val lb: Int = lowerbounds(i);
			for (j: Point{(self.rank == 1)} in Region.make([((i(0) + 1)..(Place.MAX_PLACES - 1))])) {
				if ((lowerbounds(j) <= lb)) {
					lowerbounds(j) = (lb + 1);
				}
			}
		}
		for (i: Point{(self.rank == 1)} in Region.make([(0..(Place.MAX_PLACES - 2))])) {
			upperbounds(i) = (lowerbounds((i + Point.make([1]))) - 1);
		}
		upperbounds((Place.MAX_PLACES - 1)) = (YLENGTH - 1);
		var dResult: Dist{(self.rank == 2)} = (((Region.makeEmpty(2)->here)) as Dist{(self.rank == 2)});
		for (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			val p: Place = Dist.makeUnique()(pl);
			dResult = (dResult || ((Region.make([(lowerbounds(pl)..upperbounds(pl)), rRows])->p)));
		}
		return dResult;
	}

	public def sortParticles(val particles: Array[Particle]{(self.rank == 1)}, val dField: Dist{(self.rank == 2)}): Array[Particle]{(self.rank == 1)} {
		val dParticles: Dist{(self.rank == 1)} = particles.dist;
		val result: Array[Particle]{(self.rank == 1)} = (Array.make[Particle](dParticles));
		val indices: Array[Int]{(self.rank == 1)} = (Array.make[Int](dParticles));
		val dCount: Dist{(self.rank == 1)} = X10Util.get2DDistRank(dField, 0);
		val count: Array[Int]{(self.rank == 1)} = (Array.make[Int](dCount, (pt:Point{(self.rank == 1)}) => {
			return 0;
		}));
		finish 		ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			val dP: Dist{(self.rank == 1)} = particles.dist;
			val rLocalP: Region{(self.rank == 1)} = X10Util.get1DLRegion(dP);
			val rLocalC: Region{(self.rank == 1)} = X10Util.get1DLRegion(dCount);
			for (pt: Point{(self.rank == 1)} in (particles.dist | here)) {
				val y: Int = (particles(pt).getY() as Int);
				count(y)++;
				indices(pt) = y;
			}
			val dLocalC: Dist{(self.rank == 1)} = (rLocalC->here);
			var offset: Int = ((rLocalP.size() > 0))?rLocalP.min(0):0;
			var offsets: Array[Int]{(self.rank == 1)} = (Array.make[Int](dLocalC));
			for (pt: Point{(self.rank == 1)} in rLocalC) {
				val n: Int = count(pt);
				offsets(pt) = offset;
				offset = (offset + n);
			}
			for (pt: Point{(self.rank == 1)} in rLocalP) {
				val index: Int = indices(pt);
				indices(pt) = offsets(index);
				offsets(index)++;
			}
			foreach (i: Point{(self.rank == 1)} in rLocalP) {
				result(indices(i)) = particles(i);
			}
		}

		return result;
	}

	public def parallelPrefix(val data: Array[Int]{(self.rank == 1)}): Array[Int]{(self.rank == 1)} {
		val dData: Dist{(self.rank == 1)} = data.dist;
		val result: Array[Int]{(self.rank == 1)} = (Array.make[Int](dData, (pt:Point{(self.rank == 1)}) => {
			return data(pt);
		}));
		val tmp1: Array[Int]{(self.rank == 1)} = (Array.make[Int](dData, (pt:Point{(self.rank == 1)}) => {
			return data(pt);
		}));
		val tmp2: Array[Int]{(self.rank == 1)} = (Array.make[Int](dData, (pt:Point{(self.rank == 1)}) => {
			return 0;
		}));
		var strides: Int = 1;
		while ((strides < Place.MAX_PLACES)){
			val stride: Int = strides;
			finish 			ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
				val dir: Int = (((pl(0) / stride)) % 2);
				val val: Int = tmp1(pl);
				val pDst: Place = Place.places((pl(0) + ((((dir == 0))?1:-1) * stride)));
				finish 				async (pDst) {
					if ((dir == 0)) {
						result(here.id) = (result(here.id) + val);
					}
					tmp2(here.id) = val;
				}

			}

			finish 			ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
				tmp1(pl) = (tmp1(pl) + tmp2(pl));
			}

			strides = (strides + strides);
		}
		return result;
	}

}

class Particle {
	private val x: Double;
	private val y: Double;
	private val xVel: Double;
	private val yVel: Double;
	private val pid: Int;

	public def this() {
		x = 0.0;
		y = 0.0;
		xVel = 0.0;
		yVel = 0.0;
		pid = -1;
	}

	public def this(val x: Double, val y: Double, val xVel: Double, val yVel: Double) {
		this.x = x;
		this.y = y;
		this.xVel = xVel;
		this.yVel = yVel;
		pid = -1;
	}

	public def this(val x: Double, val y: Double, val xVel: Double, val yVel: Double, val pid: Int) {
		this.x = x;
		this.y = y;
		this.xVel = xVel;
		this.yVel = yVel;
		this.pid = pid;
	}


	public def getX(): Double {
		return x;
	}

	public def getY(): Double {
		return y;
	}

	public def getXVel(): Double {
		return xVel;
	}

	public def getYVel(): Double {
		return yVel;
	}

	public def setX(val x: Double): Particle {
		return new Particle(x, y, xVel, yVel, pid);
	}

	public def setY(val y: Double): Particle {
		return new Particle(x, y, xVel, yVel, pid);
	}

	public def setXVel(val xVel: Double): Particle {
		return new Particle(x, y, xVel, yVel, pid);
	}

	public def setYVel(val yVel: Double): Particle {
		return new Particle(x, y, xVel, yVel, pid);
	}

	public def setPos(val x: Double, val y: Double): Particle {
		return new Particle(x, y, xVel, yVel, pid);
	}

	public def setVel(val xVel: Double, val yVel: Double): Particle {
		return new Particle(x, y, xVel, yVel, pid);
	}

	public safe def toString(): String {
		var result: String = ((pid >= 0))?("p" + pid):"";
		result = (((((((((result + "(") + x) + ",") + y) + ",") + xVel) + ",") + yVel) + ")");
		return result;
	}

	public def particleEquals(val p: Particle): Boolean {
		val error: Double = X10Util.ERROR;
		if (((pid >= 0) && (pid != p.pid))) {
			return false;
		}
		return ((((((Math.abs((getX() - p.getX())) < error)) && ((Math.abs((getY() - p.getY())) < error))) && ((Math.abs((getXVel() - p.getXVel())) < error))) && ((Math.abs((getYVel() - p.getYVel())) < error))));
	}

}

class ArrayUtil {


	public static def doubleToComplex(val src: Array[Double]{(self.rank == 2)}): Array[Complex]{(self.rank == 2)} {
		val rSrc: Region{(self.rank == 2)} = src.region;
		val rS2: Region{(self.rank == 1)} = X10Util.getRank2D(rSrc, 1);
		if (((rS2.size() % 2) != 0)) {
			Console.ERR.println(("Error: doubleToComplex requires the second " + "dimension to be of even size"));
			val dResult: Dist{(self.rank == 2)} = (((Region.makeEmpty(2)->here)) as Dist{(self.rank == 2)});
			return (Array.make[Complex](dResult));
		}
		val rD2: Region{(self.rank == 1)} = Region.make([(0..((rS2.size() / 2) - 1))]);
		val dSrc: Dist{(self.rank == 2)} = src.dist;
		val dResult: Dist{(self.rank == 2)} = X10Util.extendDistRank2D(X10Util.get2DDistRank(dSrc, 0), rD2);
		val result: Array[Complex]{(self.rank == 2)} = (Array.make[Complex](dResult));
		finish 		ateach (pt: Point{(self.rank == 2)} in dResult) {
			result(pt) = new Complex(src((pt * Point.make([1, 2]))), src(((pt * Point.make([1, 2])) + Point.make([0, 1]))));
		}

		return result;
	}

	public static def complexToDouble(val src: Array[Complex]{(self.rank == 2)}): Array[Double]{(self.rank == 2)} {
		val rSrc: Region{(self.rank == 2)} = src.region;
		val rS2: Region{(self.rank == 1)} = X10Util.getRank2D(rSrc, 1);
		val rD2: Region{(self.rank == 1)} = Region.make([(0..((2 * rS2.size()) - 1))]);
		val dSrc: Dist{(self.rank == 2)} = src.dist;
		val dResult: Dist{(self.rank == 2)} = X10Util.extendDistRank2D(X10Util.get2DDistRank(dSrc, 0), rD2);
		val result: Array[Double]{(self.rank == 2)} = (Array.make[Double](dResult));
		finish 		ateach (pt: Point{(self.rank == 2)} in dResult) {
			val ptd2: Point{(self.rank == 2)} = (pt / Point.make([1, 2]));
			if (((pt(1) % 2) == 0)) {
				result(pt) = src(ptd2).getReal();
			} else {
				result(pt) = src(ptd2).getImag();
			}
		}

		return result;
	}

	public static def transpose(val data: Array[Complex]{(self.rank == 2)}): Array[Complex]{(self.rank == 2)} {
		val dData: Dist{(self.rank == 2)} = (data.dist as Dist{(self.rank == 2)});
		if (!X10Util.isDistBlockStar(dData)) {
			X10Util.println(("Transpose only works on star block" + "distributed arrays"));
			throw new RuntimeException(("transpose: " + data.dist));
		}
		val dResult: Dist{(self.rank == 2)} = X10Util.transposeBlockStar(dData);
		val result: Array[Complex]{(self.rank == 2)} = (Array.make[Complex](dResult));
		finish 		ateach (pl: Point{(self.rank == 1)} in Dist.makeUnique()) {
			val rLocal: Region{(self.rank == 2)} = X10Util.get2DLRegion(dData);
			val rTtmp: Region{(self.rank == 2)} = X10Util.transposeRegion(rLocal);
			if ((rLocal.size() > 0)) {
				val rTtmp1: Region{(self.rank == 1)} = X10Util.getRank2D(rTtmp, 0);
				val rTtmp2: Region{(self.rank == 1)} = X10Util.getRank2D(rTtmp, 1);
				val rLocalT: Region{(self.rank == 2)} = Region.make([(rTtmp1.min(0)..rTtmp1.max(0)), (rTtmp2.min(0)..rTtmp2.max(0))]);
				val rRSBuffer: Region{(self.rank == 1)} = (Dist.makeUnique().region as Region{(self.rank == 1)});
				val rSBuffer: Array[Region{(self.rank == 2)}]{(self.rank == 1)} = ((Array.make[Region{(self.rank == 2)}](rRSBuffer, (p:Point{(self.rank == 1)}) => {
					val rTLocal: Region{(self.rank == 2)} = (((dResult | Dist.makeUnique()(p))).region as Region{(self.rank == 2)});
					return (rLocalT && rTLocal);
				})) as Array[Region{(self.rank == 2)}]{(self.rank == 1)});
				val sBuffer: Array[Complex]{(self.rank == 2)} = ((Array.make[Complex](rLocalT, ((i, j):Point{(self.rank == 2)}) => {
					return data(j, i);
				})) as Array[Complex]{(self.rank == 2)});
				foreach (i: Point{(self.rank == 1)} in rSBuffer) {
					ateach (pt: Point{(self.rank == 2)} in (rSBuffer(i)->Dist.makeUnique()(i))) {
						result(pt) = sBuffer(pt);
					}
				}
			}
		}

		return result;
	}

}

class Timer {
	public static val max_counters: Int = 64;
	private var start_time: Array[Double]{(self.rank == 1)};
	private var elapsed_time: Array[Double]{(self.rank == 1)};
	private var total_time: Array[Double]{(self.rank == 1)};

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


	public def start(val n: Int): Void {
		start_time(n) = System.currentTimeMillis();
	}

	public def stop(val n: Int): Void {
		elapsed_time(n) = (System.currentTimeMillis() - start_time(n));
		elapsed_time(n) = (elapsed_time(n) / 1000);
		total_time(n) = (total_time(n) + elapsed_time(n));
	}

	public def readTimer(val n: Int): Double {
		return total_time(n);
	}

	public def resetTimer(val n: Int): Void {
		total_time(n) = 0;
		start_time(n) = 0;
		elapsed_time(n) = 0;
	}

	public def resetAllTimers(): Void {
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

