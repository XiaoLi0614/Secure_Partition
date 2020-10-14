
import x10.compiler.*;

class md {
	public static val ITERS: Int = 100;
	public static val LENGTH: Double = 50e-10;
	public static val m: Double = 4.0026;
	public static val mu: Double = 1.66056e-27;
	public static val kb: Double = 1.38066e-23;
	public static val TSIM: Double = 50;
	public static val deltat: Double = 5e-16;
	public var one: DistArray[Particle]{(self.rank == 1)};
	public var epot: Double;
	public var vir: Double;
	public var interactions: Int;
	public var count: Double;
	private var mdsize: Int;
	private var l: Double;
	private var rcoff: Double;
	private var rcoffs: Double;
	private var side: Double;
	private var sideh: Double;
	private var hsq: Double;
	private var hsq2: Double;
	private var a: Double;
	private var tscale: Double;
	private var sc: Double;
	private var ekin: Double;
	private var ek: Double;
	public static val den: Double = 0.83134;
	public static val tref: Double = 0.722;
	public static val h: Double = 0.064;
	private var vaver: Double;
	private var vaverh: Double;
	private var etot: Double;
	private var temp: Double;
	private var pres: Double;
	private var rp: Double;
	private var npartm: Int;
	public static val irep: Int = 10;
	public static val istop: Int = 19;
	public static val iprint: Int = 10;
	public static val movemx: Int = 50;
	private var rnk: Int;
	private var nprocess: Int;


	public static def exec() {
		val D <: Dist{(self.rank == 1)} = (Dist.makeUnique() as Dist{(self.rank == 1)});
		val P <: DistArray[md]{(self.rank == 1)} = (DistArray.make[md](D, (pt:Point{(self.rank == 1)}) => {
			return new md();
		}));
		init(D, P);
		run(D, P);
		validate(D, P);
	}

	public static def init(val D: Dist{(self.rank == 1)}, val P: DistArray[md]{(self.rank == 1)}) {
		for (j: Point{(self.rank == 1)} in D) 
			(P(j)).initialise(j(0), Place.MAX_PLACES);
	}

	public static def run(val D: Dist{(self.rank == 1)}, val P: DistArray[md]{(self.rank == 1)}) {
		var n: Int = 0;
		for (move: Point{(self.rank == 1)} in (0..(movemx - 1))) {
			for (j: Point{(self.rank == 1)} in D) 
				P(j).runiters1();
			for (j: Point{(self.rank == 1)} in D) 
				P(j).runiters2(P);
			for (j: Point{(self.rank == 1)} in D) 
				P(j).runiters3(move(0));
		}
	}

	public static def validate(val D: Dist{(self.rank == 1)}, val P: DistArray[md]{(self.rank == 1)}) {
		for (j: Point{(self.rank == 1)} in D) {
			var ref: Double = 275.97175611773514;
			var dev: Double = Math.abs((P(j).ek - ref));
			if ((dev > 1.0e-10)) {
{
					Console.OUT.println(("Validation failed at place: " + j(0)));
					Console.OUT.println(((((("Kinetic energy = " + P(j).ek) + " Difference:") + dev) + " Reference: ") + ref));
					throw new RuntimeException("Validation failed");
				}			}
		}
	}

	public def initialise(val rank0: Int, val nprocess0: Int) {
		rnk = rank0;
		nprocess = nprocess0;
		vir = 0.0;
		epot = 0.0;
		interactions = 0;
		var mm: Int = 4;
		var partsize: Int = (((mm * mm) * mm) * 4);
		mdsize = partsize;
		var dOne: Dist{(self.rank == 1)} = ((0..(mdsize - 1))->here);
		one = (DistArray.make[Particle](dOne));
		l = LENGTH;
		side = Math.pow(((mdsize / den)), 0.3333333);
		rcoff = (mm / 4.0);
		a = (side / mm);
		sideh = (side * 0.5);
		hsq = (h * h);
		hsq2 = (hsq * 0.5);
		npartm = (mdsize - 1);
		rcoffs = (rcoff * rcoff);
		tscale = (16.0 / (((1.0 * mdsize) - 1.0)));
		vaver = (1.13 * Math.sqrt((tref / 24.0)));
		vaverh = (vaver * h);
		for (base: Point{(self.rank == 1)} in (0..0)) {
			var ijk: Point{(self.rank == 1)} = base;
			for (p: Point{(self.rank == 4)} in (0..1) * (0..(mm - 1)) * (0..(mm - 1)) * (0..(mm - 1))) {
				one(ijk) = new Particle((((p(1) * a) + ((p(0) * a) * 0.5))), (((p(2) * a) + ((p(0) * a) * 0.5))), ((p(3) * a)), 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
				ijk = (ijk + Point.make([1]));
			}
			for (p: Point{(self.rank == 4)} in (1..2) * (0..(mm - 1)) * (0..(mm - 1)) * (0..(mm - 1))) {
				one(ijk) = new Particle((((p(1) * a) + ((((2 - p(0))) * a) * 0.5))), (((p(2) * a) + ((((p(0) - 1)) * a) * 0.5))), (((p(3) * a) + (a * 0.5))), 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
				ijk = (ijk + Point.make([1]));
			}
		}
		var iseed: Int = 0;
		var v1: Double = 0.0;
		var v2: Double = 0.0;
		var randnum: Random = new Random(iseed, v1, v2);
		var r: Double = 0.0;
		var k: Int = 0;
		while ((k < mdsize)) {
			r = randnum.seed();
			one(k).xvelocity = (r * randnum.v1);
			one((k + 1)).xvelocity = (r * randnum.v2);
			k = (k + 2);
		}
		k = 0;
		while ((k < mdsize)) {
			r = randnum.seed();
			one(k).yvelocity = (r * randnum.v1);
			one((k + 1)).yvelocity = (r * randnum.v2);
			k = (k + 2);
		}
		k = 0;
		while ((k < mdsize)) {
			r = randnum.seed();
			one(k).zvelocity = (r * randnum.v1);
			one((k + 1)).zvelocity = (r * randnum.v2);
			k = (k + 2);
		}
		ekin = 0.0;
		var sp: Double = 0.0;
		for (i: Point{(self.rank == 1)} in (0..(mdsize - 1))) 
			sp = (sp + one(i).xvelocity);
		sp = (sp / mdsize);
		for (i: Point{(self.rank == 1)} in (0..(mdsize - 1))) {
			one(i).xvelocity = (one(i).xvelocity - sp);
			ekin = (ekin + (one(i).xvelocity * one(i).xvelocity));
		}
		sp = 0.0;
		for (i: Point{(self.rank == 1)} in (0..(mdsize - 1))) 
			sp = (sp + one(i).yvelocity);
		sp = (sp / mdsize);
		for (i: Point{(self.rank == 1)} in (0..(mdsize - 1))) {
			one(i).yvelocity = (one(i).yvelocity - sp);
			ekin = (ekin + (one(i).yvelocity * one(i).yvelocity));
		}
		sp = 0.0;
		for (i: Point{(self.rank == 1)} in (0..(mdsize - 1))) 
			sp = (sp + one(i).zvelocity);
		sp = (sp / mdsize);
		for (i: Point{(self.rank == 1)} in (0..(mdsize - 1))) {
			one(i).zvelocity = (one(i).zvelocity - sp);
			ekin = (ekin + (one(i).zvelocity * one(i).zvelocity));
		}
		var ts: Double = (tscale * ekin);
		sc = (h * Math.sqrt((tref / ts)));
		for (i: Point{(self.rank == 1)} in (0..(mdsize - 1))) {
			one(i).xvelocity = (one(i).xvelocity * sc);
			one(i).yvelocity = (one(i).yvelocity * sc);
			one(i).zvelocity = (one(i).zvelocity * sc);
		}
	}

	public def runiters1() {
		for (i: Point{(self.rank == 1)} in (0..(mdsize - 1))) 
			one(i).domove(side);
		epot = 0.0;
		vir = 0.0;
		var i: Int = (0 + rnk);
		while ((i < mdsize)) {
			one(i).force(side, rcoff, mdsize, i, this);
			i = (i + nprocess);
		}
	}

	public def runiters2(val P: DistArray[md]{(self.rank == 1)}) {
		allreduce(P);
	}

	public def runiters3(val move: Int) {
		var summation: Double = 0.0;
		for (i: Point{(self.rank == 1)} in (0..(mdsize - 1))) 
			summation = (summation + one(i).mkekin(hsq2));
		ekin = (summation / hsq);
		var vel: Double = 0.0;
		count = 0.0;
		for (i: Point{(self.rank == 1)} in (0..(mdsize - 1))) 
			vel = (vel + one(i).velavg(vaverh, h, this));
		vel = (vel / h);
		if ((((move < istop)) && ((((((move + 1)) % irep)) == 0)))) {
{
				sc = Math.sqrt((tref / ((tscale * ekin))));
				for (i: Point{(self.rank == 1)} in (0..(mdsize - 1))) 
					one(i).dscal(sc, 1);
				ekin = (tref / tscale);
			}		}
		if ((((((move + 1)) % iprint)) == 0)) {
{
				ek = (24.0 * ekin);
				epot = (4.0 * epot);
				etot = (ek + epot);
				temp = (tscale * ekin);
				pres = (((den * 16.0) * ((ekin - vir))) / mdsize);
				vel = (vel / mdsize);
				rp = (((count / mdsize)) * 100.0);
			}		}
	}

	public def allreduce(val P: DistArray[md]{(self.rank == 1)}) {
		if ((rnk != 0)) {
			return;
		}		val t <: md = new md();
		t.mdsize = mdsize;
		val dOne <: Dist{(self.rank == 1)} = ((0..(mdsize - 1))->here);
		t.one = (DistArray.make[Particle](dOne));
		val tHere <: Place = here;
		for (k: Point{(self.rank == 1)} in (0..((mdsize - 1)))) 
			t.one(k) = new Particle(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		for (j: Point{(self.rank == 1)} in P) {
			val dD <: Dist{(self.rank == 1)} = ((0..2)->here);
			val dI <: Dist{(self.rank == 1)} = ((0..0)->here);
			val dataD <: DistArray[Double]{(self.rank == 1)} = (DistArray.make[Double](dD));
			val dataI <: DistArray[Double]{(self.rank == 1)} = (DistArray.make[Double](dI));
			for (k: Point{(self.rank == 1)} in (0..((mdsize - 1)))) {
{
					val fx <: Double = P(j).one(k).xforce;
					val fy <: Double = P(j).one(k).yforce;
					val fz <: Double = P(j).one(k).zforce;
{
						dataD(0) = fx;
						dataD(1) = fy;
						dataD(2) = fz;
					}				}				t.one(k).xforce = (t.one(k).xforce + dataD(0));
				t.one(k).yforce = (t.one(k).yforce + dataD(1));
				t.one(k).zforce = (t.one(k).zforce + dataD(2));
			}
{
				val jvir <: Double = P(j).vir;
				val jepot <: Double = P(j).epot;
				val jinter <: Int = P(j).interactions;
{
					dataD(0) = jvir;
					dataD(1) = jepot;
					dataI(0) = jinter;
				}			}			t.vir = (t.vir + dataD(0));
			t.epot = (t.epot + dataD(1));
			t.interactions = (t.interactions + (dataI(0) as Int));
		}
		val mymdsize <: Int = mdsize;
		for (j: Point{(self.rank == 1)} in P.dist) {
			val pHere <: Place = here;
			val dD <: Dist{(self.rank == 1)} = ((0..2)->here);
			val dataD <: DistArray[Double]{(self.rank == 1)} = (DistArray.make[Double](dD));
			for (k: Point{(self.rank == 1)} in (0..((mymdsize - 1)))) {
{
					val xf <: Double = t.one(k).xforce;
					val yf <: Double = t.one(k).yforce;
					val zf <: Double = t.one(k).zforce;
{
						dataD(0) = xf;
						dataD(1) = yf;
						dataD(2) = zf;
					}				}				P(j).one(k).xforce = dataD(0);
				P(j).one(k).yforce = dataD(1);
				P(j).one(k).zforce = dataD(2);
			}
			val dI <: Dist{(self.rank == 1)} = ((0..0)->here);
			val dataI <: DistArray[Int]{(self.rank == 1)} = (DistArray.make[Int](dI));
{
				val tvir <: Double = t.vir;
				val tepot <: Double = t.epot;
				val tinter <: Int = t.interactions;
{
					dataD(0) = tvir;
					dataD(1) = tepot;
					dataI(0) = tinter;
				}			}			P(j).vir = dataD(0);
			P(j).epot = dataD(1);
			P(j).interactions = dataI(0);
		}
	}

}

class mdMain {

	public static def main(args: Array[String]) {
{
			var tmr: Timer = new Timer();
			var count: Int = 0;

				tmr.start(count);

				md.exec();
			tmr.stop(count);
			Console.OUT.println((("Wall-clock time for moldyn: " + tmr.readTimer(count)) + " secs"));
		}	}

}

class Random {
	public var iseed: Int;
	public var v1: Double;
	public var v2: Double;

	public def this(val iseed0: Int, val v10: Double, val v20: Double) {
		iseed = iseed0;
		v1 = v10;
		v2 = v20;
	}


	public def update(): Double {
		var rand: Double = 0.0;
		var scale: Double = 4.656612875e-10;
		var is1: Int = 0;
		var is2: Int = 0;
		var iss2: Int = 0;
		var imult: Int = 16807;
		var imod: Int = 2147483647;
		if ((iseed <= 0)) {

				iseed = 1;
		}		is2 = (iseed % 32768);
		is1 = (((iseed - is2)) / 32768);
		iss2 = (is2 * imult);
		is2 = (iss2 % 32768);
		is1 = ((((is1 * imult) + (((iss2 - is2)) / 32768))) % (65536));
		iseed = ((((is1 * 32768) + is2)) % imod);
		rand = (scale * iseed);
		return rand;
	}

	public def seed(): Double {
		var s: Double = 0.0;
		var u1: Double = 0.0;
		var u2: Double = 0.0;
		var r: Double = 0.0;
		s = 1.0;
		do {
			u1 = update();
			u2 = update();
			v1 = ((2.0 * u1) - 1.0);
			v2 = ((2.0 * u2) - 1.0);
			s = ((v1 * v1) + (v2 * v2));
		}		while ((s >= 1.0));
		r = Math.sqrt(((-2.0 * Math.log(((s as Double) as Double))) / s));
		return r;
	}

}

class Particle {
	public var xcoord: Double;
	public var ycoord: Double;
	public var zcoord: Double;
	public var xvelocity: Double;
	public var yvelocity: Double;
	public var zvelocity: Double;
	public var xforce: Double;
	public var yforce: Double;
	public var zforce: Double;

	public def this(val xcoord0: Double, val ycoord0: Double, val zcoord0: Double, val xvelocity0: Double, val yvelocity0: Double, val zvelocity0: Double, val xforce0: Double, val yforce0: Double, val zforce0: Double) {
		xcoord = xcoord0;
		ycoord = ycoord0;
		zcoord = zcoord0;
		xvelocity = xvelocity0;
		yvelocity = yvelocity0;
		zvelocity = zvelocity0;
		xforce = xforce0;
		yforce = yforce0;
		zforce = zforce0;
	}


	public def domove(val side: Double) {
		xcoord = ((xcoord + xvelocity) + xforce);
		ycoord = ((ycoord + yvelocity) + yforce);
		zcoord = ((zcoord + zvelocity) + zforce);
		if ((xcoord < 0)) {

				xcoord = (xcoord + side);
		}		if ((xcoord > side)) {

				xcoord = (xcoord - side);
		}		if ((ycoord < 0)) {

				ycoord = (ycoord + side);
		}		if ((ycoord > side)) {

				ycoord = (ycoord - side);
		}		if ((zcoord < 0)) {

				zcoord = (zcoord + side);
		}		if ((zcoord > side)) {

				zcoord = (zcoord - side);
		}		xvelocity = (xvelocity + xforce);
		yvelocity = (yvelocity + yforce);
		zvelocity = (zvelocity + zforce);
		xforce = 0.0;
		yforce = 0.0;
		zforce = 0.0;
	}

	public def force(val side: Double, val rcoff: Double, val mdsize: Int, val x: Int, val md1: md) {
		var sideh: Double = 0.0;
		var rcoffs: Double = 0.0;
		var xx: Double = 0.0;
		var yy: Double = 0.0;
		var zz: Double = 0.0;
		var xi: Double = 0.0;
		var yi: Double = 0.0;
		var zi: Double = 0.0;
		var fxi: Double = 0.0;
		var fyi: Double = 0.0;
		var fzi: Double = 0.0;
		var rd: Double = 0.0;
		var rrd: Double = 0.0;
		var rrd2: Double = 0.0;
		var rrd3: Double = 0.0;
		var rrd4: Double = 0.0;
		var rrd6: Double = 0.0;
		var rrd7: Double = 0.0;
		var r148: Double = 0.0;
		var forcex: Double = 0.0;
		var forcey: Double = 0.0;
		var forcez: Double = 0.0;
		sideh = (0.5 * side);
		rcoffs = (rcoff * rcoff);
		xi = xcoord;
		yi = ycoord;
		zi = zcoord;
		fxi = 0.0;
		fyi = 0.0;
		fzi = 0.0;
		for (i: Point{(self.rank == 1)} in ((x + 1)..(mdsize - 1))) {
			xx = (xi - md1.one(i).xcoord);
			yy = (yi - md1.one(i).ycoord);
			zz = (zi - md1.one(i).zcoord);
			if ((xx < (-sideh))) {

					xx = (xx + side);
			}			if ((xx > (sideh))) {

					xx = (xx - side);
			}			if ((yy < (-sideh))) {

					yy = (yy + side);
			}			if ((yy > (sideh))) {

					yy = (yy - side);
			}			if ((zz < (-sideh))) {

					zz = (zz + side);
			}			if ((zz > (sideh))) {

					zz = (zz - side);
			}			rd = (((xx * xx) + (yy * yy)) + (zz * zz));
			if ((rd <= rcoffs)) {
{
					rrd = (1.0 / rd);
					rrd2 = (rrd * rrd);
					rrd3 = (rrd2 * rrd);
					rrd4 = (rrd2 * rrd2);
					rrd6 = (rrd2 * rrd4);
					rrd7 = (rrd6 * rrd);
					md1.epot = (md1.epot + ((rrd6 - rrd3)));
					r148 = (rrd7 - (0.5 * rrd4));
					md1.vir = (md1.vir - (rd * r148));
					forcex = (xx * r148);
					fxi = (fxi + forcex);
					md1.one(i).xforce = (md1.one(i).xforce - forcex);
					forcey = (yy * r148);
					fyi = (fyi + forcey);
					md1.one(i).yforce = (md1.one(i).yforce - forcey);
					forcez = (zz * r148);
					fzi = (fzi + forcez);
					md1.one(i).zforce = (md1.one(i).zforce - forcez);
					md1.interactions++;
				}			}
		}
		xforce = (xforce + fxi);
		yforce = (yforce + fyi);
		zforce = (zforce + fzi);
	}

	public def mkekin(val hsq2: Double): Double {
		var sumt: Double = 0.0;
		xforce = (xforce * hsq2);
		yforce = (yforce * hsq2);
		zforce = (zforce * hsq2);
		xvelocity = (xvelocity + xforce);
		yvelocity = (yvelocity + yforce);
		zvelocity = (zvelocity + zforce);
		sumt = ((((xvelocity * xvelocity)) + ((yvelocity * yvelocity))) + ((zvelocity * zvelocity)));
		return sumt;
	}

	public def velavg(val vaverh: Double, val h: Double, val md1: md): Double {
		var velt: Double = 0.0;
		var sq: Double = 0.0;
		sq = Math.sqrt((((xvelocity * xvelocity) + (yvelocity * yvelocity)) + (zvelocity * zvelocity)));
		if ((sq > vaverh)) {

				md1.count = (md1.count + 1.0);
		}		velt = sq;
		return velt;
	}

	public def dscal(val sc: Double, val incx: Int) {
		xvelocity = (xvelocity * sc);
		yvelocity = (yvelocity * sc);
		zvelocity = (zvelocity * sc);
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

