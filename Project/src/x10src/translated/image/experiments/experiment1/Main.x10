
public class Main {

	public static def main(args: Array[String]): Void {
		var a: Int = 2;
		var b: Int = 4;
		var c: Int = MaxFun.maxFun(a, b);
		Console.OUT.println("Single return value");
		var asser1: Boolean = (c == 4);
		Check.check(asser1);
		Console.OUT.println("Multi return value: Test 1");
		var xId0: Tuple2[Int, Int] = MinAndMaxFun.minAndMaxFun(a, b);
		var r1: Int = xId0._1;
		var r2: Int = xId0._2;
		var asser2: Boolean = ((r1 == 2) && (r2 == 4));
		Check.check(asser2);
		Console.OUT.println("Multi return value: Test 2");
		var r: IntMatrix = Lib.tupleToVector(MinAndMaxFun.minAndMaxFun(a, b));
		var asser3: Boolean = AllTrue.allTrue(Lib.eq(r, new IntMatrix([2, 4])));
		Check.check(asser3);
		var asser: Boolean = ((asser1 && asser2) && asser3);
		if (asser) 
			Lib.disp("Success.");
		else 
			Lib.disp("Failure.");
	}

}

