
import x10.compiler.*;

public class RicianDenoise {

	public static def ricianDenoise(inImage: DoubleMatrix, sigma: Double, lambda: Double, tolerance: Double): DoubleMatrix {
		var u: DoubleMatrix;
		var maxIter: Int = 100;
		var dt: Int = 5;
		var epsilon: Double = 1e-20;
		var xId1: Tuple2[Int, Int] = Lib.size(inImage.clone());
		var n: Int = xId1._1;
		var m: Int = xId1._2;
		var ir: IntMatrix = Lib.concat(new IntMatrix(2, m), m);
		var il: IntMatrix = Lib.concat(1, new IntMatrix(1, (m - 1)));
		var id: IntMatrix = Lib.concat(new IntMatrix(2, n), n);
		var iu: IntMatrix = Lib.concat(1, new IntMatrix(1, (n - 1)));
		var sigma2: Double = Lib.dotPower(sigma, 2);
		var gamma: Double = (lambda / sigma2);
		u = inImage.clone();
		for (Iter in new IntMatrix(1, maxIter).values()) {
			var ulast: DoubleMatrix = u.clone();
			var ur: DoubleMatrix = u(new IntMatrix(1, u.n), ir);
			var ul: DoubleMatrix = u(new IntMatrix(1, u.n), il);
			var ud: DoubleMatrix = u(id, new IntMatrix(1, u.m));
			var uu: DoubleMatrix = u(iu, new IntMatrix(1, u.m));
			var eur: DoubleMatrix = Lib.minus(ur, u);
			var eul: DoubleMatrix = Lib.minus(ul, u);
			var eud: DoubleMatrix = Lib.minus(ud, u);
			var euu: DoubleMatrix = Lib.minus(uu, u);
			var g: DoubleMatrix = Lib.dotDivide(1, Lib.sqrt(Lib.plus(Lib.plus(Lib.plus(Lib.plus(epsilon, Lib.dotTimes(eur, eur)), Lib.dotTimes(eul, eul)), Lib.dotTimes(eud, eud)), Lib.dotTimes(euu, euu))));
			var r: DoubleMatrix = Lib.divide(Lib.dotTimes(u, inImage), sigma2);
			r = Lib.dotDivide(Lib.dotTimes(r, Lib.plus(2.38944, Lib.dotTimes(r, Lib.plus(0.950037, r)))), Lib.plus(4.65314, Lib.dotTimes(r, Lib.plus(2.57541, Lib.dotTimes(r, Lib.plus(1.48937, r))))));
			var ug: DoubleMatrix = Lib.dotTimes(u, g);
			u = Lib.dotDivide(Lib.plus(u, Lib.times(dt, Lib.plus(Lib.plus(Lib.plus(Lib.plus(ug(new IntMatrix(1, ug.n), ir), ug(new IntMatrix(1, ug.n), il)), ug(id, new IntMatrix(1, ug.m))), ug(iu, new IntMatrix(1, ug.m))), Lib.dotTimes(Lib.times(gamma, inImage), r)))), Lib.plus(1, Lib.times(dt, Lib.plus(Lib.plus(Lib.plus(Lib.plus(g(new IntMatrix(1, g.n), ir), g(new IntMatrix(1, g.n), il)), g(id, new IntMatrix(1, g.m))), g(iu, new IntMatrix(1, g.m))), gamma))));
			if ((Lib.norm(Lib.minus(u.singleColumn(), ulast.singleColumn()), "inf") < tolerance)) {

					break;
			}		}
		return u;
	}

}

