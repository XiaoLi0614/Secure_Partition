
import x10.compiler.*;

public class Main {

	public static def main(args: Array[String]) {
		var n: Int = 512;
		var m: Int = 230;
		var k: Int = 13;
		var iters: Int = 1000;
		Lib.tic();
		var result: Double = IhtFun.ihtFun(n, m, k, iters);
		var time: Double = Lib.toc();
		Lib.disp("IHT error value:");
		Lib.disp(result);
		Lib.disp("Time in seconds:");
		Lib.disp(time);
	}

}

