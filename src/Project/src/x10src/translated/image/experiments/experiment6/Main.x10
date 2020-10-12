
public class Main {

	public static def main(args: Array[String]): Void {
		var a: Int = F.f();
		Lib.disp(a);
		var m1: IntMatrix = new IntMatrix([1, 2, 3]);
		var m2: IntMatrix = new IntMatrix([4, 5, 6]);
		var m3: IntMatrix = Lib.concat(0, m1);
		Lib.disp(m3.clone());
		var m4: IntMatrix = Lib.concat(m1, 4);
		Lib.disp(m4.clone());
		var m5: IntMatrix = Lib.concat(m1, m2);
		Lib.disp(m5.clone());
	}

}

