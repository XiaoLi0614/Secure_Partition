
public class AllTrue {

	public static def allTrue(mat: BooleanMatrix): Boolean {
		var b: Boolean;
		var xId0: Tuple2[Int, Int] = Lib.size(mat);
		var n: Int = xId0._1;
		var m: Int = xId0._2;
		b = Lib.logical(1);
		for (i in new IntMatrix(1, n).values()) 
			for (j in new IntMatrix(1, m).values()) 
				b = (b && mat(i, j));
		return b;
	}

}

