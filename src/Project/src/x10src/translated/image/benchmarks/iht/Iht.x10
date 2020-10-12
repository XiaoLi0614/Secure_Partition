
import x10.compiler.*;

public class Iht {

	public static def iht(y: DoubleMatrix, phi: DoubleMatrix, k: Int, iterCount: Int, x: DoubleMatrix): DoubleMatrix {
		var xHat: DoubleMatrix;
		var xId1: Tuple2[Int, Int] = Lib.size(phi.clone());
		var m: Int = xId1._1;
		var n: Int = xId1._2;
		xHat = Lib.dzeros(n, 1);
		for (i in new IntMatrix(1, iterCount).values()) {
			var xHatNew: DoubleMatrix = Thresh.thresh(Lib.plus(xHat, Lib.times(phi.transpose(), Lib.minus(y, Lib.times(phi, xHat)))), k);
			if ((Lib.norm(Lib.minus(xHat, xHatNew)) < (1e-6 * Lib.norm(xHat.clone())))) {

					break;
			}			xHat = xHatNew.clone();
		}
		return xHat;
	}

}

