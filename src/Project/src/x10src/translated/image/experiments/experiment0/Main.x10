
public class Main {

	public static def main(args: Array[String]): Void {
		Lib.disp("if and for statements");
		var count: Int = 0;
		for (i in new IntMatrix(1, 10).values()) 
			if (((i % 2) == 1)) 
				count = (count + 1);
		var asser1: Boolean = (count == 5);
		Check.check(asser1);
		Lib.disp("while and switch statements");
		count = 0;
		var i: Int = 3;
		while ((i != 0)) {
			switch (i) {
				case 1:
					count = (count + 1);
					break;
				case 2:
					count = (count + 1);
					break;
				default: 
					count = (count + 1);
			}
			i = (i - 1);
			if ((i == 1)) 
				break;
		}
		var asser2: Boolean = (count == 2);
		Check.check(asser2);
		Lib.disp("Range vector creator");
		var a3: IntMatrix = new IntMatrix(3, 6);
		var asser3: Boolean = AllTrue.allTrue(Lib.eq(a3, new IntMatrix([3, 4, 5, 6])));
		Check.check(asser3);
		Lib.disp("Range vector creator - with step");
		var a4: IntMatrix = new IntMatrix(3, 2, 12);
		var asser4: Boolean = AllTrue.allTrue(Lib.eq(a4, new IntMatrix([3, 5, 7, 9, 11])));
		Check.check(asser4);
		var asser: Boolean = (((asser1 && asser2) && asser3) && asser4);
		if (asser) 
			Lib.disp("Success.");
		else 
			Lib.disp("Failure.");
	}

}

