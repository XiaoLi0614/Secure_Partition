import x10.compiler.*;

class RayTracer {
	private var scene: Scene;
	private var lightCount: Int;
	private var objCount: Int;
	private var lights: DistArray[Light]{(self.rank == 1)};
	private var prim: DistArray[Sphere]{(self.rank == 1)};
	private var view: View;
	private static val alpha: Int = (255 << 24);
	private static val voidVec: Vec = new Vec();
	private var height: Int;
	private var width: Int;
	private var datasizes: DistArray[Int]{(self.rank == 1)};
	private var checksum: Long;
	private var checkSumArray: DistArray[Long]{(self.rank == 1)};
	private var sizet: Int;
	private var numobjects: Int;

	public def this() {
	}


	public def JGFsetsize(val sizet: Int) {
		checksum = 0;
		var d1: Dist{(self.rank == 1)} = ((0..1)->here);
		datasizes = (DistArray.make[Int](d1));
		datasizes(0) = 20;
		datasizes(1) = 500;
		this.sizet = sizet;
	}

	public def JGFinitialise() {
		width = datasizes(sizet);
		height = datasizes(sizet);
		scene = createScene();
		setScene(scene);
		numobjects = scene.getObjects();
	}

	public def JGFapplication() {
		var interval: Interval = new Interval(0, width, height, 0, height, 1);
		render(interval);
	}

	public def JGFvalidate() {
		var d1: Dist{(self.rank == 1)} = ((0..1)->here);
		var refval: DistArray[Long]{(self.rank == 1)} = (DistArray.make[Long](d1));
		refval(0) = 51428;
		refval(1) = 29827635;
		var dev: Long = (checksum - refval(sizet));
		if ((dev != 0)) {
{
				Console.OUT.println("Validation failed");
				Console.OUT.println(("Pixel checksum = " + checksum));
				Console.OUT.println(("Reference value = " + refval(sizet)));
				throw new RuntimeException("Validation failed");
			}		}
	}

	public def JGFtidyup() {
	}

	public def run() {
		JGFsetsize(0);
		JGFinitialise();
		JGFapplication();
		JGFvalidate();
		JGFtidyup();
	}

	private def createScene(): Scene {
		val x <: Int = 0;
		val y <: Int = 0;
		var scene: Scene = new Scene(new View(new Vec(x, 20, -30, false), new Vec(x, y, 0, false), new Vec(0, 1, 0, false), 1.0, ((35.0 * 3.14159265) / 180.0), 1.0));
		val nx <: Int = 4;
		val ny <: Int = 4;
		val nz <: Int = 4;
		val reg <: Region{(self.rank == 3)} = (0..(nx - 1)) * (0..(ny - 1)) * (0..(nz - 1));
		for (pp: Point{(self.rank == 3)} in reg) {
			var xx: Double = (((20.0 / ((nx - 1))) * pp(0)) - 10.0);
			var yy: Double = (((20.0 / ((ny - 1))) * pp(1)) - 10.0);
			var zz: Double = (((20.0 / ((nz - 1))) * pp(2)) - 10.0);
			var p: Sphere = new Sphere(new Vec(xx, yy, zz, false), 3, new Surface(15.0, (1.5 - 1.0), (1.5 - 1.0), new Vec(0, 0, (((pp(0) + pp(1))) / ((((nx + ny) - 2)) as Double)), false), false));
			scene.addObject(p);
		}
		scene.addLight(new Light(100, 100, -50, 1.0));
		scene.addLight(new Light(-100, 100, -50, 1.0));
		scene.addLight(new Light(100, -100, -50, 1.0));
		scene.addLight(new Light(-100, -100, -50, 1.0));
		scene.addLight(new Light(200, 200, 0, 1.0));
		return scene;
	}

	public def setScene(val scene: Scene) {
		lightCount = scene.getLights();
		objCount = scene.getObjects();
		val sc <: Scene = scene;
		val U <: Dist{(self.rank == 1)} = (Dist.makeUnique() as Dist{(self.rank == 1)});
		val mylightCount <: Int = lightCount;
		val myobjCount <: Int = objCount;
		finish 		ateach (pl: Point{(self.rank == 1)} in U) {
			val pHere <: Place = here;
			var d1: Region{(self.rank == 1)} = (0..(mylightCount - 1));
			var d2: Region{(self.rank == 1)} = (0..(myobjCount - 1));
			val templights <: DistArray[Light]{(self.rank == 1)} = ((DistArray.make[Light](d1-> here, (l:Point{(self.rank == 1)}) => {
				val dTemp <: Dist{(self.rank == 1)} = ((0..0)->pHere);
				val temp <: DistArray[Light]{(self.rank == 1)} = (DistArray.make[Light](dTemp));
				val temp_l <: Point{(self.rank == 1)} = l;
				finish 				at (Place.FIRST_PLACE) async {
					val tempLight <: Light = sc.getLight(temp_l);
					at (pHere) async 
						temp(0) = tempLight;

				}

				return temp(0);
			})) as DistArray[Light]{(self.rank == 1)});
			if ((here == Place.FIRST_PLACE)) {

					lights = templights;
			}			val tempprim <: DistArray[Sphere]{(self.rank == 1)} = ((DistArray.make[Sphere](d2-> here, (o:Point{(self.rank == 1)}) => {
				val dTemp1 <: Dist{(self.rank == 1)} = ((0..0)->pHere);
				val temp1 <: DistArray[Sphere]{(self.rank == 1)} = (DistArray.make[Sphere](dTemp1));
				val temp_o <: Point{(self.rank == 1)} = o;
				finish 				at (Place.FIRST_PLACE) async {
					val tempObject <: Sphere = sc.getObject(temp_o);
					at (pHere) async 
						temp1(0) = tempObject;

				}

				return temp1(0);
			})) as DistArray[Sphere]{(self.rank == 1)});
			if ((here == Place.FIRST_PLACE)) {

					prim = tempprim;
			}		}

		view = scene.getView();
	}

	public def render(val interval: Interval) {
		val R <: Region{(self.rank == 1)} = (0..(((interval.width * ((interval.yto - interval.yfrom)))) - 1));
		val DBlock <: Dist{(self.rank == 1)} = (Dist.makeBlock(R) as Dist{(self.rank == 1)});
		val U <: Dist{(self.rank == 1)} = (Dist.makeUnique() as Dist{(self.rank == 1)});
		val row <: DistArray[Int]{(self.rank == 1)} = (DistArray.make[Int](DBlock));
		val chkSumReg <: Region{(self.rank == 1)} = U.region;
		val pHere <: Place = here;
		val fp <: Place = Place.FIRST_PLACE;
		val chkSumDist <: Dist{(self.rank == 1)} = (chkSumReg->fp);
		checkSumArray = (DistArray.make[Long](chkSumDist));
		val myview <: View = view;
		finish 		ateach (pl: Point{(self.rank == 1)} in U) {
			val my_dist <: Dist{(self.rank == 1)} = (DBlock | here);
			var checksum1: Long = 0;
			var frustrumwidth: Double = (myview.distance * ((Math.sin(myview.angle) / Math.cos(myview.angle))));
			var viewVec: Vec = Vec.sub(myview.at, myview.from).normalized();
			var tmpVec: Vec = new Vec(viewVec, false).scale(Vec.dot(myview.up, viewVec));
			var upVec: Vec = Vec.sub(myview.up, tmpVec).normalized().scale(-frustrumwidth);
			var leftVec: Vec = Vec.cross(myview.up, viewVec).normalized().scale((myview.aspect * frustrumwidth));
			var r: Ray = new Ray(myview.from, voidVec);
			for (pixCounter: Point{(self.rank == 1)} in my_dist.region) {
				var y: Int = (pixCounter(0) / interval.width);
				var x: Int = (pixCounter(0) % interval.width);
				var ylen: Double = (((((2.0 * y)) as Double) / (interval.width as Double)) - 1.0);
				var xlen: Double = (((((2.0 * x)) as Double) / (interval.width as Double)) - 1.0);
				r = r.d(Vec.comb(xlen, leftVec, ylen, upVec).added(viewVec).normalized());
				var col: Vec = trace(0, 1.0, r, new Isect(), new Ray());
				var red: Int = (((col.x * 255.0)) as Int);
				if ((red > 255)) {
					red = 255;
				}				var green: Int = (((col.y * 255.0)) as Int);
				if ((green > 255)) {
					green = 255;
				}				var blue: Int = (((col.z * 255.0)) as Int);
				if ((blue > 255)) {
					blue = 255;
				}				checksum1 = (((checksum1 + red) + green) + blue);
			}
			val checksumx <: Long = checksum1;
			finish 			at (fp) async 
				checkSumArray(pl) = checksumx;


		}

		val regSum <: Region{(self.rank == 1)} = checkSumArray.dist.region;
		for (i: Point{(self.rank == 1)} in chkSumReg) 
			checksum = (checksum + checkSumArray(i));
	}

	private def intersect(val r: Ray, val maxt: Double, val inter: Isect): Boolean {
		inter.t = 1e9;
		val h <: Place = here;
		val dTemp <: Dist{(self.rank == 1)} = ((0..0)->here);
		val temp <: DistArray[Int]{(self.rank == 1)} = (DistArray.make[Int](dTemp));
		finish 		at (Place.FIRST_PLACE) async {
			val tempobjCount <: Int = objCount;
			val tempprim <: DistArray[Sphere]{(self.rank == 1)} = prim;
			at (h) async {
				var nhits: Int = 0;
				for (i: Point{(self.rank == 1)} in (0..(tempobjCount - 1))) {
					var tp: Isect = tempprim(i).intersect(r);
					if ((!tp.isNull && (tp.t < inter.t))) {
{
							inter.t = tp.t;
							inter.prim = tp.prim;
							inter.surf = tp.surf;
							inter.enter = tp.enter;
							nhits++;
						}					}
				}
				temp(0) = nhits;
			}
		}

		return (temp(0) > 0)?true:false;
	}

	private def Shadow(val r: Ray, val tmax: Double, val inter: Isect): Int {
		if (intersect(r, tmax, inter)) {
			return 0;
		}		return 1;
	}

	private def SpecularDirection(val I: Vec, val N: Vec): Vec {
		return Vec.comb((1.0 / Math.abs(Vec.dot(I, N))), I, 2.0, N).normalized();
	}

	private def TransDir(val m1: Surface, val m2: Surface, val I: Vec, val N: Vec): Vec {
		var n1: Double = m1.isNull?1.0:m1.ior;
		var n2: Double = m2.isNull?1.0:m2.ior;
		var eta: Double = (n1 / n2);
		var c1: Double = -Vec.dot(I, N);
		var cs2: Double = (1.0 - ((eta * eta) * ((1.0 - (c1 * c1)))));
		if ((cs2 < 0.0)) {
			return new Vec(0.0, 0.0, 0.0, true);
		}		return Vec.comb(eta, I, ((eta * c1) - Math.sqrt(cs2)), N).normalized();
	}

	private def shade(val level: Int, val weight: Double, val P: Vec, val N: Vec, val I: Vec, val hit: Isect, val tRay: Ray): Vec {
		val surf <: Surface = hit.surf;
		var bigr1: Vec = new Vec();
		if ((surf.shine > 1e-6)) {

				bigr1 = SpecularDirection(I, N);
		}		val bigr <: Vec = bigr1;
		val h <: Place = here;
		val dTemp <: Dist{(self.rank == 1)} = ((0..0)->here);
		val temp <: DistArray[Vec]{(self.rank == 1)} = (DistArray.make[Vec](dTemp));
		finish 		at (Place.FIRST_PLACE) async {
			val templightCount <: Int = lightCount;
			val templights <: DistArray[Light]{(self.rank == 1)} = lights;
			at (h) async {
				var col: Vec = new Vec();
				for (l: Point{(self.rank == 1)} in (0..(templightCount - 1))) {
					var L: Vec = Vec.sub(templights(l).pos, P);
					if ((Vec.dot(N, L) >= 0.0)) {
{
							L = L.normalized();
							var t: Double = L.length();
							tRay.p = P;
							tRay.d = L;
							if ((Shadow(tRay, t, hit) > 0)) {
{
									var diff: Double = ((Vec.dot(N, L) * surf.kd) * templights(l).brightness);
									col = col.adds2(diff, surf.color);
									if ((surf.shine > 1e-6)) {
{
											var spec: Double = Vec.dot(bigr, L);
											if ((spec > 1e-6)) {
{
													spec = Math.pow(spec, surf.shine);
													col = col.added(new Vec(spec, spec, spec, false));
												}											}
										}									}
								}							}
						}					}
				}
				temp(0) = col;
			}
		}

		var col: Vec = temp(0);
		tRay.p = P;
		if (((surf.ks * weight) > 1e-3)) {
{
				tRay.d = SpecularDirection(I, N);
				var tcol: Vec = trace((level + 1), (surf.ks * weight), tRay, hit, tRay);
				col = col.adds2(surf.ks, tcol);
			}		}
		if (((surf.kt * weight) > 1e-3)) {
{
				if ((hit.enter > 0)) {
					tRay.d = TransDir(new Surface(0, 0, 0, new Vec(), true), surf, I, N);
				}				else 
					tRay.d = TransDir(surf, new Surface(0, 0, 0, new Vec(), true), I, N);
				var tcol: Vec = trace((level + 1), (surf.kt * weight), tRay, hit, tRay);
				col = col.adds2(surf.kt, tcol);
			}		}
		return col;
	}

	private def trace(val level: Int, val weight: Double, val r: Ray, val inter: Isect, val tRay: Ray): Vec {
		if ((level > 6)) {

				return new Vec();
		}		var hit: Boolean = intersect(r, 1e6, inter);
		if (hit) {
{
				var P: Vec = r.rayPoint(inter.t);
				var N: Vec = inter.prim.normal(P);
				if ((Vec.dot(r.d, N) >= 0.0)) {

						N = N.negate();
				}				return shade(level, weight, P, N, r.d, inter, tRay);
			}		}
		return voidVec;
	}

}

class Ray {
	public var p: Vec;
	public var d: Vec;

	public def this(val pnt: Vec, val dir: Vec) {
		p = new Vec(pnt.x, pnt.y, pnt.z, false);
		d = new Vec(dir.x, dir.y, dir.z, false).normalized();
	}

	public def this(val pnt: Vec, val dir: Vec, val normalize: Boolean) {
		if (normalize) {
{
				p = new Vec(pnt.x, pnt.y, pnt.z, false);
				d = new Vec(dir.x, dir.y, dir.z, false).normalized();
			}		} else {
			p = pnt;
			d = dir;
		}
	}

	public def this() {
		p = new Vec();
		d = new Vec();
	}


	public def d(val d_: Vec): Ray {
		return new Ray(p, d_, false);
	}

	public def rayPoint(val t: Double): Vec {
		return new Vec((p.x + (d.x * t)), (p.y + (d.y * t)), (p.z + (d.z * t)), false);
	}

	public def toString(): String {
		return ((((" { " + p.toString()) + "->") + d.toString()) + " } ");
	}

}

class RayTracerMain {

	public static def main(args: Array[String]) {
{
			var tmr: Timer = new Timer();
			var count: Int = 0;
			finish 
				tmr.start(count);

			finish 
				new RayTracer().run();

			tmr.stop(count);
			Console.OUT.println((("Wall-clock time for raytracer: " + tmr.readTimer(count)) + " secs"));
		}	}

}

class Vec {
	public val x: Double;
	public val y: Double;
	public val z: Double;
	public val isNull: Boolean;

	public def this(val a: Double, val b: Double, val c: Double, val isNull_: Boolean) {
		x = a;
		y = b;
		z = c;
		isNull = isNull_;
	}

	public def this(val a: Vec, val isNull_: Boolean) {
		x = a.x;
		y = a.y;
		z = a.z;
		isNull = isNull_;
	}

	public def this() {
		x = 0.0;
		y = 0.0;
		z = 0.0;
		isNull = false;
	}


	public def added(val a: Vec): Vec {
		return new Vec((x + a.x), (y + a.y), (z + a.z), false);
	}

	public static def adds3(val s: Double, val a: Vec, val b: Vec): Vec {
		return new Vec(((s * a.x) + b.x), ((s * a.y) + b.y), ((s * a.z) + b.z), false);
	}

	public def adds2(val s: Double, val b: Vec): Vec {
		return new Vec((x + (s * b.x)), (y + (s * b.y)), (z + (s * b.z)), false);
	}

	public static def sub(val a: Vec, val b: Vec): Vec {
		return new Vec((a.x - b.x), (a.y - b.y), (a.z - b.z), false);
	}

	public static def mult(val a: Vec, val b: Vec): Vec {
		return new Vec((a.x * b.x), (a.y * b.y), (a.z * b.z), false);
	}

	public static def cross(val a: Vec, val b: Vec): Vec {
		return new Vec(((a.y * b.z) - (a.z * b.y)), ((a.z * b.x) - (a.x * b.z)), ((a.x * b.y) - (a.y * b.x)), false);
	}

	public static def dot(val a: Vec, val b: Vec): Double {
		return (((a.x * b.x) + (a.y * b.y)) + (a.z * b.z));
	}

	public static def comb(val a: Double, val A: Vec, val b: Double, val B: Vec): Vec {
		return new Vec(((a * A.x) + (b * B.x)), ((a * A.y) + (b * B.y)), ((a * A.z) + (b * B.z)), false);
	}

	public def scale(val t: Double): Vec {
		return new Vec((x * t), (y * t), (z * t), false);
	}

	public def negate(): Vec {
		return new Vec(-x, -y, -z, false);
	}

	public def normalized(): Vec {
		var len: Double = Math.sqrt((((x * x) + (y * y)) + (z * z)));
		return ((len > 0.0))?new Vec((x / len), (y / len), (z / len), false):this;
	}

	public def length(): Double {
		return Math.sqrt((((x * x) + (y * y)) + (z * z)));
	}

	public def toString(): String {
		return (((((("<" + x) + ",") + y) + ",") + z) + ">");
	}

}

class Light {
	public val pos: Vec;
	public val brightness: Double;

	public def this(val x: Double, val y: Double, val z: Double, val b: Double) {
		pos = new Vec(x, y, z, false);
		brightness = b;
	}


	public def toString(): String {
		return ((pos + " ") + brightness);
	}

}

class Sphere {
	private val c: Vec;
	private val r: Double;
	private val r2: Double;
	public val surf: Surface;

	public def this(val center: Vec, val radius: Double, val s: Surface) {
		surf = s;
		c = center;
		r = radius;
		r2 = (radius * radius);
	}

	public def this(val center: Vec, val radius: Double) {
		c = center;
		r = radius;
		r2 = (radius * radius);
		surf = new Surface();
	}


	public def intersect(val ry: Ray): Isect {
		var v: Vec = Vec.sub(c, ry.p);
		var b: Double = Vec.dot(v, ry.d);
		var disc: Double = (((b * b) - Vec.dot(v, v)) + r2);
		if ((disc < 0.0)) {

				return new Isect(0.0, 0, true, this, surf);
		}		disc = Math.sqrt(disc);
		var t: Double = (((b - disc) < 1e-6))?(b + disc):(b - disc);
		if ((t < 1e-6)) {

				return new Isect(0.0, 0, true, this, surf);
		}		var ip: Isect = new Isect(t, (Vec.dot(v, v) > (r2 + 1e-6))?1:0, false, this, surf);
		return ip;
	}

	public def normal(val p: Vec): Vec {
		return Vec.sub(p, c).normalized();
	}

	public def toString(): String {
		return (((("Sphere {" + c.toString()) + ",") + r) + "}");
	}

	public def getCenter(): Vec {
		return c;
	}

}

class Scene {
	public static val maxObjects: Int = 64;
	public static val maxLights: Int = 5;
	public val lights: DistArray[Light]{(self.rank == 1)};
	public val objects: DistArray[Sphere]{(self.rank == 1)};
	private val view: View;
	private var lightCount: Int;
	private var objectCount: Int;

	public def this(val v: View) {
		var d1: Dist{(self.rank == 1)} = ((0..(maxLights - 1))->here);
		lights = getLightArray(d1);
		var d2: Dist{(self.rank == 1)} = ((0..(maxObjects - 1))->here);
		objects = getSphereArray(d2);
		view = v;
		lightCount = 0;
		objectCount = 0;
	}


	@NonEscaping final
	public def getLightArray(val d: Dist{(self.rank == 1)}): DistArray[Light]{(self.rank == 1)} {
		return (DistArray.make[Light](d));
	}

	@NonEscaping final
	public def getSphereArray(val d: Dist{(self.rank == 1)}): DistArray[Sphere]{(self.rank == 1)} {
		return (DistArray.make[Sphere](d));
	}

	public def addLight(val l: Light) {
		if ((lightCount > (maxLights - 1))) {

				throw new RuntimeException((("Only " + maxLights) + " lights allowed"));
		}		this.lights(lightCount) = l;
		lightCount = (lightCount + 1);
	}

	public def addObject(val object: Sphere) {
		if ((objectCount > (maxObjects - 1))) {

				throw new RuntimeException((("Only " + maxObjects) + " objects allowed"));
		}		this.objects(objectCount) = object;
		objectCount = (objectCount + 1);
	}

	public def getView(): View {
		return this.view;
	}

	public def getLight(val number: Point{(self.rank == 1)}): Light {
		return this.lights(number);
	}

	public def getObject(val number: Point{(self.rank == 1)}): Sphere {
		return this.objects(number);
	}

	public def getLights(): Int {
		return this.lightCount;
	}

	public def getObjects(): Int {
		return this.objectCount;
	}

}

class Interval {
	public val number: Int;
	public val width: Int;
	public val height: Int;
	public val yfrom: Int;
	public val yto: Int;
	public val total: Int;

	public def this(val number_: Int, val width_: Int, val height_: Int, val yfrom_: Int, val yto_: Int, val total_: Int) {
		number = number_;
		width = width_;
		height = height_;
		yfrom = yfrom_;
		yto = yto_;
		total = total_;
	}


	public def toString(): String {
		return ((((((((((number + " ") + width) + " ") + height) + " ") + yfrom) + " ") + yto) + " ") + total);
	}

}

class Surface {
	public val color: Vec;
	public val kd: Double;
	public val ks: Double;
	public val shine: Double;
	public val kt: Double;
	public val ior: Double;
	public val isNull: Boolean;

	public def this() {
		color = new Vec(1, 0, 0, false);
		kd = 1.0;
		ks = 0.0;
		shine = 0.0;
		kt = 0.0;
		ior = 1.0;
		isNull = false;
	}

	public def this(val shine_: Double, val ks_: Double, val kt_: Double, val c_: Vec, val isNull_: Boolean) {
		kd = 1.0;
		ks = ks_;
		shine = shine_;
		kt = kt_;
		ior = 1.0;
		color = c_;
		isNull = isNull_;
	}


	public def toString(): String {
		return (("Surface { color = " + color) + " }");
	}

}

class Isect {
	public var t: Double;
	public var enter: Int;
	public var prim: Sphere;
	public var surf: Surface;
	public var isNull: Boolean;

	public def this() {
		t = 0.0;
		enter = 0;
		isNull = false;
	}

	public def this(val t_: Double, val enter_: Int, val isNull_: Boolean, val prim_: Sphere, val surf_: Surface) {
		t = t_;
		isNull = isNull_;
		enter = enter_;
		prim = prim_;
		surf = surf_;
	}

}

class View {
	public val from: Vec;
	public val at: Vec;
	public val up: Vec;
	public val distance: Double;
	public val angle: Double;
	public val aspect: Double;

	public def this(val from_: Vec, val at_: Vec, val up_: Vec, val dist_: Double, val angle_: Double, val aspect_: Double) {
		from = from_;
		at = at_;
		up = up_;
		distance = dist_;
		angle = angle_;
		aspect = aspect_;
	}


	public def toString(): String {
		return (((((((((((("View { from = " + from) + " at = ") + at) + " up = ") + up) + " distance = ") + distance) + " angle = ") + angle) + " aspect = ") + aspect) + "}");
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

