
import x10.compiler.*;

public class CoSaMP {

	public static def coSaMP(y: DoubleMatrix, phi: DoubleMatrix, k: Int, maxIter: Int, tolerance: Double): DoubleMatrix {
		var xHat: DoubleMatrix;
		var n: Int = Lib.size(phi.clone(), 2);
		var m: Int = Lib.size(phi.clone(), 1);
		xHat = Lib.dzeros(n, 1);
		var res: DoubleMatrix = y.clone();
		var i: Int = 1;
		var prevOmega: IntMatrix = new IntMatrix();
		while (((Lib.norm(res.clone()) > tolerance) && ((i - 1) < maxIter))) {
			i = (i + 1);
			var match: DoubleMatrix = Lib.times(phi.transpose(), res);
			var xId1: Tuple2[DoubleMatrix, IntMatrix] = Lib.sortDI(Lib.abs(match.clone()));
			var dummy: DoubleMatrix = xId1._1;
			var supp: IntMatrix = xId1._2;
			var omega: IntMatrix = supp(new IntMatrix(1, (2 * k)));
			var T: IntMatrix = Lib.union(omega.clone(), prevOmega.clone());
			var phi_T: DoubleMatrix = phi(new IntMatrix(1, phi.n), T);
			var a_T: DoubleMatrix = Lib.times(Lib.pinv(phi_T.clone()), y);
			xHat = Lib.dzeros(n, 1);
			xHat(T) = a_T;
			var xId2: Tuple2[DoubleMatrix, IntMatrix] = Lib.sortDI(Lib.abs(xHat.clone()));
			dummy = xId2._1;
			supp = xId2._2;
			xHat(supp(new IntMatrix((k + 1), (supp.n * supp.m)))) = 0.0;
			res = Lib.minus(y, Lib.times(phi, xHat));
			prevOmega = supp(new IntMatrix(1, k));
		}
		return xHat;
	}

}

