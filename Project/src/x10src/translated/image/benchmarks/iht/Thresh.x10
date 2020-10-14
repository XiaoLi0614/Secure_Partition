
import x10.compiler.*;

public class Thresh {

	public static def thresh(x: DoubleMatrix, k: Int): DoubleMatrix {
		var xt: DoubleMatrix;
		var xId2: Tuple2[DoubleMatrix, IntMatrix] = Lib.sortDI(Lib.abs(x.clone()));
		var trash: DoubleMatrix = xId2._1;
		var indices: IntMatrix = xId2._2;
		var kIndices: IntMatrix = indices(new IntMatrix(1, k));
		xt = Lib.times(0, x);
		xt(kIndices) = x(kIndices);
		return xt;
	}

}

