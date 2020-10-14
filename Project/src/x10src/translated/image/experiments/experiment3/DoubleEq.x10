
public class DoubleEq {

	public static def doubleEq(mat1: DoubleMatrix, mat2: DoubleMatrix): Boolean {
		var b: Boolean;
		var xId0: Tuple2[Int, Int] = Lib.size(mat1);
		var n: Int = xId0._1;
		var m: Int = xId0._2;
		var tresh: Double = 0.000001;
		for (i in new IntMatrix(1, n).values()) 
			for (j in new IntMatrix(1, m).values()) {
				var defer: Double = (mat1(i, j) - mat2(i, j));
				if ((defer < 0)) 
					defer = -defer;
				if ((defer > tresh)) {
					b = false;
					return b;
				}
			}
		b = Lib.logical(1);
		return b;
	}

}

