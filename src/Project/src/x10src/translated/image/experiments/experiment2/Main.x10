
public class Main {

	public static def main(args: Array[String]): Void {
		var d1: Double = 1.1;
		var d2: Double = 2.2;
		var da1: DoubleMatrix = new DoubleMatrix([[1.1, 2.2, 3.3], [4.4, 5.5, 6.6]]);
		var da2: DoubleMatrix = new DoubleMatrix([[1.2, 2.2, 3.4], [4.4, 5.6, 6.6]]);
		Lib.disp("Relational - Primitives");
		var asser1: Boolean = (d2 >= d1);
		Check.check(asser1);
		Lib.disp("Relational - Arrays");
		var ba2: BooleanMatrix = Lib.gt(da2, da1);
		var asser2: Boolean = AllTrue.allTrue(Lib.eq(ba2, Lib.logical(new IntMatrix([[1, 0, 1], [0, 1, 0]]))));
		Check.check(asser2);
		Lib.disp("Relational - Array and primitive - Test 1");
		var ba3: BooleanMatrix = Lib.gt(da2, 2);
		var asser3: Boolean = AllTrue.allTrue(Lib.eq(ba3, Lib.logical(new IntMatrix([[0, 1, 1], [1, 1, 1]]))));
		Check.check(asser3);
		Lib.disp("Relational - Array and primitive - Test 2");
		var ba4: BooleanMatrix = Lib.gt(2, da2);
		var asser4: Boolean = AllTrue.allTrue(Lib.eq(ba4, Lib.logical(new IntMatrix([[1, 0, 0], [0, 0, 0]]))));
		Check.check(asser4);
		Lib.disp("Short circuit and, or and not");
		var l51: Boolean = !(Lib.logical(0) && Lib.logical(0));
		var l52: Boolean = (Lib.logical(0) || Lib.logical(1));
		var l53: Boolean = !(Lib.logical(1) && Lib.logical(0));
		var l54: Boolean = (Lib.logical(1) || Lib.logical(1));
		var asser5: Boolean = (((l51 && l52) && l53) && l54);
		Check.check(asser5);
		Lib.disp("Elementwise and, or and not - primitives");
		var l61: Boolean = Lib.or(0, 5);
		var l62: Boolean = Lib.or(0, 5);
		var l63: Boolean = Lib.or(6, 5);
		var l64: Boolean = Lib.and(6, 5);
		var l65: Boolean = !Lib.and(0, 5);
		var asser6: Boolean = (((l61 && l62) && l63) && l64);
		Check.check(asser6);
		Lib.disp("Elementwise and, or and not - arrays");
		var m71: IntMatrix = new IntMatrix([1, 2, 0, 0]);
		var m72: IntMatrix = new IntMatrix([1, 0, 1, 0]);
		var l71: Boolean = AllTrue.allTrue(Lib.eq(Lib.and(m71, m72), Lib.logical(new IntMatrix([1, 0, 0, 0]))));
		var l72: Boolean = AllTrue.allTrue(Lib.eq(Lib.or(m71, m72), Lib.logical(new IntMatrix([1, 1, 1, 0]))));
		var asser7: Boolean = (l71 && l72);
		Check.check(asser7);
		Lib.disp("Elementwise and, or and not - primitive and array 1");
		var m81: IntMatrix = new IntMatrix([1, 2, 0, 0]);
		var l81: Boolean = AllTrue.allTrue(Lib.eq(Lib.and(m81, 5), Lib.logical(new IntMatrix([1, 1, 0, 0]))));
		var l82: Boolean = AllTrue.allTrue(Lib.eq(Lib.not(Lib.or(m81, 5)), Lib.logical(new IntMatrix([0, 0, 0, 0]))));
		var asser8: Boolean = (l81 && l82);
		Check.check(asser8);
		Lib.disp("Elementwise and, or and not - primitive and array 2");
		var m91: IntMatrix = new IntMatrix([1, 2, 0, 0]);
		var l91: Boolean = AllTrue.allTrue(Lib.eq(Lib.and(0, m91), Lib.logical(new IntMatrix([0, 0, 0, 0]))));
		var l92: Boolean = AllTrue.allTrue(Lib.eq(Lib.not(Lib.or(5, m91)), Lib.logical(new IntMatrix([0, 0, 0, 0]))));
		var asser9: Boolean = (l91 && l92);
		Check.check(asser9);
		var asser: Boolean = ((((((((asser1 && asser2) && asser3) && asser4) && asser5) && asser6) && asser7) && asser8) && asser9);
		if (asser) 
			Lib.disp("Success.");
		else 
			Lib.disp("Failure.");
	}

}

