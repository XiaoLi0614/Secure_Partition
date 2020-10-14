
import x10.compiler.*;

public class Em {

	public static def em(A: DoubleMatrix, b: DoubleMatrix, x0: DoubleMatrix, maxIter: Int): DoubleMatrix {
		var x: DoubleMatrix;
		x = x0.clone();
		var done: Int = 0;
		var iter: Int = 0;
		var sumA: DoubleMatrix = Lib.sum(A.clone()).transpose();
		while (Lib.not(done)) {
			var Ax: DoubleMatrix = Lib.times(A, x);
			Ax = Lib.max(Ax.clone(), 1e-8);
			var bAx: DoubleMatrix = Lib.dotDivide(b, Ax);
			var AtbAx: DoubleMatrix = Lib.times(A.transpose(), bAx);
			x = Lib.dotDivide(Lib.dotTimes(x, AtbAx), sumA);
			iter = (iter + 1);
			if ((iter == maxIter)) {

					done = 1;
			}		}
		return x;
	}

}

