
import x10.compiler.*;

public class IhtFun {

	public static def ihtFun(n: Int, m: Int, k: Int, iters: Int): Double {
		var result: Double;
		var x: DoubleMatrix = Lib.dzeros(n, 1);
		var q: IntMatrix = Lib.randperm(n);
		var randV: DoubleMatrix = Lib.randn(k, 1);
		x(q.applyHH(new IntMatrix(1, k))) = randV;
		var phi: DoubleMatrix = Lib.randn(m, n);
		phi = Lib.divide(phi, Lib.norm(phi.clone()));
		var y: DoubleMatrix = Lib.times(phi, x);
		var randy: DoubleMatrix = Lib.randn(Lib.tupleToVector(Lib.size(y.clone())));
		y = Lib.plus(y, Lib.times(0.00, randy));
		var xhat: DoubleMatrix = Iht.iht(y.clone(), phi.clone(), k, iters, x.clone());
		result = (Lib.norm(Lib.minus(x, xhat)) / Lib.norm(x.clone()));
		return result;
	}

}

