
public class MinAndMaxFun {

	public static def minAndMaxFun(x: Int, y: Int): Tuple2[Int, Int] {
		var m1: Int;
		var m2: Int;
		if ((x >= y)) {
			m1 = y;
			m2 = x;
			return new Tuple2[Int, Int](m1, m2);
		} else {
			m1 = x;
			m2 = y;
		}
		return new Tuple2[Int, Int](m1, m2);
	}

}

