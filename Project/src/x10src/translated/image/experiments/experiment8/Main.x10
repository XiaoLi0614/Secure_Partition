
public class Main {

	public static def main(args: Array[String]): Void {
		var a: IntMatrix = new IntMatrix([[1, 0, 3], [4, 5, 0]]);
		var s: IntMatrix = Lib.sum(a.clone(), 2);
		Lib.disp(s.clone());
		var nzero: IntMatrix = Lib.find(a.clone());
		Lib.disp(nzero.clone());
		var r: IntMatrix = Lib.reshape(a.clone(), 3, 2);
		Lib.disp(r.clone());
		Lib.disp(Lib.max(a.clone()));
		Lib.disp(Lib.min(a.clone()));
	}

}

