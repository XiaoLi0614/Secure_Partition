
import x10.compiler.*;

class IDEATestMain {

	public static def main(args: Array[String]) {
{
			var tmr: Timer = new Timer();
			var count: Int = 0;

				tmr.start(count);

				new IDEATest().run();
			tmr.stop(count);
			Console.OUT.println((("Wall-clock time for crypt: " + tmr.readTimer(count)) + " secs"));
		}	}

}

class IDEATest {
	public static val array_rows: Int = 32000;
	public var plain1: DistArray[Byte]{(self.rank == 1)};
	public var crypt1: DistArray[Byte]{(self.rank == 1)};
	public var plain2: DistArray[Byte]{(self.rank == 1)};
	public var userkey: DistArray[Int]{(self.rank == 1)};
	public var ZR: DistArray[Int]{(self.rank == 1)};
	public var DKR: DistArray[Int]{(self.rank == 1)};

	public def this() {
		buildTestData();
		val D <: Dist{(self.rank == 1)} = (Dist.makeBlock((0..(array_rows - 1))) as Dist{(self.rank == 1)});
		plain1 = (DistArray.make[Byte](D, (p:Point{(self.rank == 1)}) => {
			return (p(0) as Byte);
		}));
		crypt1 = (DistArray.make[Byte](D));
		plain2 = (DistArray.make[Byte](D));
	}


	public def run() {
		kernel();
		validate();
	}

	public def kernel() {
		Do();
	}

	public def validate() {
		val p1 <: DistArray[Byte]{(self.rank == 1)} = plain1;
		val c1 <: DistArray[Byte]{(self.rank == 1)} = crypt1;
		val p2 <: DistArray[Byte]{(self.rank == 1)} = plain2;
		for (pt: Point{(self.rank == 1)} in plain1.dist) 
			if ((p1(pt) != p2(pt))) {
{
					Console.OUT.println("Validation failed");
					Console.OUT.println(((("Original Byte " + pt) + " = ") + p1(pt)));
					Console.OUT.println(((("Encrypted Byte " + pt) + " = ") + c1(pt)));
					Console.OUT.println(((("Decrypted Byte " + pt) + " = ") + p2(pt)));
					throw new RuntimeException("Validation failed");
				}			}
	}

	public def Do() {
		val p1 <: DistArray[Byte]{(self.rank == 1)} = plain1;
		val c1 <: DistArray[Byte]{(self.rank == 1)} = crypt1;
		val p2 <: DistArray[Byte]{(self.rank == 1)} = plain2;
		val fzr <: DistArray[Int]{(self.rank == 1)} = ZR;
		val fdkr <: DistArray[Int]{(self.rank == 1)} = DKR;
		for (pt: Point{(self.rank == 1)} in Dist.makeUnique()) {
			cipher_idea(p1, c1, fzr);
			cipher_idea(c1, p2, fdkr);
		}
	}

	@NonEscaping final
	public def buildTestData() {
		val rndnum <: Random = new Random(136506717L);
		val rUserKey <: Region{(self.rank == 1)} = (0..7);
		val dUserKey <: Dist{(self.rank == 1)} = (rUserKey->here);
		val tUserKey <: DistArray[Int]{(self.rank == 1)} = (DistArray.make[Int](dUserKey));
		for (pt: Point{(self.rank == 1)} in rUserKey) 
			tUserKey(pt) = (rndnum.nextInt() as Short);
		val tempuserkey <: DistArray[Int]{(self.rank == 1)} = ((DistArray.make[Int](rUserKey-> here, (pt:Point{(self.rank == 1)}) => {
			return tUserKey(pt);
		})) as DistArray[Int]{(self.rank == 1)});
		userkey = tempuserkey;
		val tempZR <: DistArray[Int]{(self.rank == 1)} = calcEncryptKey(tempuserkey);
		ZR = tempZR;
		calcDecryptKey(tempZR);
	}

	@NonEscaping final
	private def calcEncryptKey(val auserkey: DistArray[Int]{(self.rank == 1)}): DistArray[Int]{(self.rank == 1)} {
		var j: Int = 0;
		val rZ <: Region{(self.rank == 1)} = (0..51);
		val dZ <: Dist{(self.rank == 1)} = (rZ->here);
		val Z <: DistArray[Int]{(self.rank == 1)} = (DistArray.make[Int](dZ));
		for (i: Point{(self.rank == 1)} in (0..7)) 
			Z(i) = (auserkey(i) & 0xffff);
		for (i: Point{(self.rank == 1)} in (8..51)) {
			j = (i(0) % 8);
			if ((j < 6)) {
{
					Z(i) = (((((Z((i + Point.make([-7]))) >>> 9)) | ((Z((i + Point.make([-6]))) << 7)))) & 0xFFFF);
					continue;
				}			}
			if ((j == 6)) {
{
					Z(i) = (((((Z((i + Point.make([-7]))) >>> 9)) | ((Z((i + Point.make([-14]))) << 7)))) & 0xFFFF);
					continue;
				}			}
			Z(i) = (((((Z((i + Point.make([-15]))) >>> 9)) | ((Z((i + Point.make([-14]))) << 7)))) & 0xFFFF);
		}
		return ((DistArray.make[Int](rZ-> here, (pt:Point{(self.rank == 1)}) => {
			return Z(pt);
		})) as DistArray[Int]{(self.rank == 1)});
	}

	@NonEscaping final
	private def calcDecryptKey(val aZR: DistArray[Int]{(self.rank == 1)}) {
		var j: Int = 0;
		var k: Int = 0;
		var t1: Int = 0;
		var t2: Int = 0;
		var t3: Int = 0;
		val rDK <: Region{(self.rank == 1)} = (0..51);
		val dDK <: Dist{(self.rank == 1)} = (rDK->here);
		val DK <: DistArray[Int]{(self.rank == 1)} = (DistArray.make[Int](dDK));
		val Z <: DistArray[Int]{(self.rank == 1)} = aZR;
		t1 = inv(Z(0));
		t2 = (-Z(1) & 0xffff);
		t3 = (-Z(2) & 0xffff);
		DK(51) = inv(Z(3));
		DK(50) = t3;
		DK(49) = t2;
		DK(48) = t1;
		j = 47;
		k = 4;
		for (i: Point{(self.rank == 1)} in (0..6)) {
			t1 = Z(k++);
			DK(j--) = Z(k++);
			DK(j--) = t1;
			t1 = inv(Z(k++));
			t2 = (-Z(k++) & 0xffff);
			t3 = (-Z(k++) & 0xffff);
			DK(j--) = inv(Z(k++));
			DK(j--) = t2;
			DK(j--) = t3;
			DK(j--) = t1;
		}
		t1 = Z(k++);
		DK(j--) = Z(k++);
		DK(j--) = t1;
		t1 = inv(Z(k++));
		t2 = (-Z(k++) & 0xffff);
		t3 = (-Z(k++) & 0xffff);
		DK(j--) = inv(Z(k++));
		DK(j--) = t3;
		DK(j--) = t2;
		DK(j--) = t1;
		DKR = ((DistArray.make[Int](rDK-> here, (i:Point{(self.rank == 1)}) => {
			return DK(i);
		})) as DistArray[Int]{(self.rank == 1)});
	}

	private def cipher_idea(val text1: DistArray[Byte]{(self.rank == 1)}, val text2: DistArray[Byte]{(self.rank == 1)}, val key: DistArray[Int]{(self.rank == 1)}) {
		if (((text1.region.size() % ((8 * Place.MAX_PLACES))) != 0)) {

				throw new RuntimeException("Invalid number of places for the distribution size");
		}		for (i: Point{(self.rank == 1)} in (text1.dist | here)) 
			if (((i(0) % 8) == 0)) {
{
					var i1: Point{(self.rank == 1)} = i;
					var i2: Point{(self.rank == 1)} = i;
					var ik: Int = 0;
					var x1: Int = 0;
					var x2: Int = 0;
					var x3: Int = 0;
					var x4: Int = 0;
					var t1: Int = 0;
					var t2: Int = 0;
					var r: Int = 0;
					ik = 0;
					r = 8;
					x1 = (text1(i1) & 0xff);
					i1 = (i1 + Point.make([1]));
					x1 = (x1 | (((text1(i1) & 0xff)) << 8));
					i1 = (i1 + Point.make([1]));
					x2 = (text1(i1) & 0xff);
					i1 = (i1 + Point.make([1]));
					x2 = (x2 | (((text1(i1) & 0xff)) << 8));
					i1 = (i1 + Point.make([1]));
					x3 = (text1(i1) & 0xff);
					i1 = (i1 + Point.make([1]));
					x3 = (x3 | (((text1(i1) & 0xff)) << 8));
					i1 = (i1 + Point.make([1]));
					x4 = (text1(i1) & 0xff);
					i1 = (i1 + Point.make([1]));
					x4 = (x4 | (((text1(i1) & 0xff)) << 8));
					i1 = (i1 + Point.make([1]));
					do {
						x1 = ((((((x1 as Long) * key(ik++)) % 0x10001L) & 0xffff)) as Int);
						x2 = ((x2 + key(ik++)) & 0xffff);
						x3 = ((x3 + key(ik++)) & 0xffff);
						x4 = ((((((x4 as Long) * key(ik++)) % 0x10001L) & 0xffff)) as Int);
						t2 = (x1 ^ x3);
						t2 = ((((((t2 as Long) * key(ik++)) % 0x10001L) & 0xffff)) as Int);
						t1 = ((t2 + ((x2 ^ x4))) & 0xffff);
						t1 = ((((((t1 as Long) * key(ik++)) % 0x10001L) & 0xffff)) as Int);
						t2 = ((t1 + t2) & 0xffff);
						x1 = (x1 ^ t1);
						x4 = (x4 ^ t2);
						t2 = (t2 ^ x2);
						x2 = (x3 ^ t1);
						x3 = t2;
					}					while ((--r != 0));
					x1 = ((((((x1 as Long) * key(ik++)) % 0x10001L) & 0xffff)) as Int);
					x3 = ((x3 + key(ik++)) & 0xffff);
					x2 = ((x2 + key(ik++)) & 0xffff);
					x4 = ((((((x4 as Long) * key(ik++)) % 0x10001L) & 0xffff)) as Int);
					text2(i2) = (x1 as Byte);
					i2 = (i2 + Point.make([1]));
					text2(i2) = (((x1 >>> 8)) as Byte);
					i2 = (i2 + Point.make([1]));
					text2(i2) = (x3 as Byte);
					i2 = (i2 + Point.make([1]));
					text2(i2) = (((x3 >>> 8)) as Byte);
					i2 = (i2 + Point.make([1]));
					text2(i2) = (x2 as Byte);
					i2 = (i2 + Point.make([1]));
					text2(i2) = (((x2 >>> 8)) as Byte);
					i2 = (i2 + Point.make([1]));
					text2(i2) = (x4 as Byte);
					i2 = (i2 + Point.make([1]));
					text2(i2) = (((x4 >>> 8)) as Byte);
					i2 = (i2 + Point.make([1]));
				}			}
	}

	private def mul(val aa: Int, val ab: Int): Int {
		var a: Int = aa;
		var b: Int = ab;
		var p: Long = 0L;
		if ((a != 0)) {

				if ((b != 0)) {
{
						p = ((a as Long) * b);
						b = ((p as Int) & 0xFFFF);
						a = ((p as Int) >>> 16);
						return ((((b - a) + ((b < a)?1:0)) & 0xFFFF));
					}				} else 
					return ((((1 - a)) & 0xFFFF));
		}		else 
			return ((((1 - b)) & 0xFFFF));
	}

	@NonEscaping final
	private def inv(val ax: Int): Int {
		var t0: Int = 0;
		var t1: Int = 0;
		var q: Int = 0;
		var y: Int = 0;
		var x: Int = ax;
		if ((x <= 1)) {

				return (x);
		}		t1 = (0x10001 / x);
		y = (0x10001 % x);
		if ((y == 1)) {

				return ((((1 - t1)) & 0xFFFF));
		}		t0 = 1;
		do {
			q = (x / y);
			x = (x % y);
			t0 = (t0 + (q * t1));
			if ((x == 1)) {
				return (t0);
			}			q = (y / x);
			y = (y % x);
			t1 = (t1 + (q * t0));
		}		while ((y != 1));
		return ((((1 - t1)) & 0xFFFF));
	}

}

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

