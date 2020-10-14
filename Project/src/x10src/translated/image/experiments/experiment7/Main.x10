
public class Main {

	public static def main(args: Array[String]): Void {
		var n: Int = 5;
		var y: DoubleMatrix = Lib.dzeros(n, 1);
		y = Lib.randn(Lib.tupleToVector(Lib.size(y)));
		Lib.disp(y);
	}

}

