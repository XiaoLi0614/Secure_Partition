
import x10.compiler.*;

public class AddRicianNoise {

	public static def addRicianNoise(nu: DoubleMatrix, sigma: Double): DoubleMatrix {
		var result: DoubleMatrix;
		result = nu.clone();
		var r: DoubleMatrix = Lib.divide(result.singleColumn().transpose(), Lib.sqrt(2));
		var twoRowR: DoubleMatrix = r(new IntMatrix([1, 1]), new IntMatrix(1, r.m));
		var twoRowRandom: DoubleMatrix = Lib.randn(2, Lib.length(r.clone()));
		var rp: DoubleMatrix = Lib.dotPower(Lib.plus(twoRowR, Lib.times(twoRowRandom, sigma)), 2);
		var rpSum: DoubleMatrix = Lib.sum(rp.clone());
		Lib.colonAssign(result, Lib.sqrt(rpSum.clone()));
		return result;
	}

}

