
import x10.compiler.*;

public class Main {

	public static def main(args: Array[String]) {
		var n: Int = 1024;
		var m: Int = 200;
		var k: Int = 40;
		var maxIter: Int = 500;
		var tolerance: Double = 1e-4;
		var epsilon: Double = 0.1;
		var randomMat: DoubleMatrix = Lib.randn(m, n);
		var phi: DoubleMatrix = Lib.times((1 / Lib.sqrt(m)), randomMat);
		var alpha: DoubleMatrix = Lib.dzeros(n, 1);
		var rp: IntMatrix = Lib.randperm(n);
		alpha(rp.applyHH(new IntMatrix(1, k))) = Lib.randn(k, 1);
		var x: DoubleMatrix = alpha.clone();
		x = Lib.divide(x, Lib.norm(x.clone()));
		var y: DoubleMatrix = Lib.times(phi, x);
		Lib.tic();
		var xHat: DoubleMatrix = CoSaMP.coSaMP(y.clone(), phi.clone(), k, maxIter, tolerance);
		var time: Double = Lib.toc();
		var error1: Double = (Lib.norm(Lib.minus(x, xHat)) / Lib.norm(x.clone()));
		Lib.disp("CoSaMP error value:");
		Lib.disp(error1);
		Lib.disp("Time is seconds:");
		Lib.disp(time);
	}

}

