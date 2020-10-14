
import x10.compiler.*;

public class Main {

	public static def main(args: Array[String]) {
		var n: Int = 16;
		var rays: DoubleMatrix = Lib.readDoubleMatrix("/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/2.Projects/0.Project/0.Project/src/msrc/benchmarks/data/Rays16.format");
		var sino: DoubleMatrix = Lib.readDoubleMatrix("/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/2.Projects/0.Project/0.Project/src/msrc/benchmarks/data/Sino16.format");
		var rowSum: DoubleMatrix = Lib.sum(rays.clone(), 2);
		var index: IntMatrix = Lib.find(Lib.gt(rowSum, 0));
		var A: DoubleMatrix = rays(index, new IntMatrix(1, rays.m));
		var b: DoubleMatrix = sino(index);
		var V: DoubleMatrix = Lib.reshape(Lib.sum(A.clone()), n, n);
		var alpha: Int = 5;
		var x: DoubleMatrix = Lib.dones((n * n), 1);
		for (i in new IntMatrix(1, 300).values()) {
			x = Em.em(A.clone(), b.clone(), x.clone(), 3);
			x = Lib.reshape(x.clone(), n, n);
			x(1, new IntMatrix(1, x.m)) = 0.0;
			x(n, new IntMatrix(1, x.m)) = 0.0;
			x(new IntMatrix(1, x.n), 1) = 0.0;
			x(new IntMatrix(1, x.n), n) = 0.0;
			x = Tv.tv(x.clone(), V.clone(), alpha, 6);
			x = Lib.reshape(x.clone(), (n * n), 1);
			x = Lib.max(x.clone(), 0);
			x = Lib.min(x.clone(), 1);
		}
		var image: DoubleMatrix = Lib.reshape(x.clone(), n, n);
		Lib.writeFormatImage(image.clone(), "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/2.Projects/0.Project/0.Project/src/msrc/benchmarks/emptv/EMpTVOut.format");
	}

}

