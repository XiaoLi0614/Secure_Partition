
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


public class Test {
	public static def main(Rail[String]) {
		var testsPassed: Boolean = true;
		val r1: Region(2) = [1..3, 1..3];
		val r2: Region(2) = [3..5, 2..5];
		val r3 = r1.union(r2);
		val c = COMPILER_INSERTED_LIB.coord(r3, 10, 1);
		Console.OUT.println(c == 5);
		testsPassed = (c == 5);

		val i = COMPILER_INSERTED_LIB.ordinal(r3, [3,5]);
		Console.OUT.println(i == 10);
		testsPassed = (i == 10);

		val dist1 = [1..2] -> here;
		val dist2 = [4..5] -> here.next();
		val dist3 = dist1.union(dist2);
		val array = Array.make[Int](dist3, (point(i): Point) => i);
		val s = COMPILER_INSERTED_LIB.sum(array);
		//Console.OUT.println(s == 12);
		testsPassed = (s == 12);
		val m = COMPILER_INSERTED_LIB.max(array);
		Console.OUT.println(m == 5);
		testsPassed = (m == 5);
		Console.OUT.println("TestsPassed: " + testsPassed);
	}

}
