
public class Main {

	public static def main(args: Array[String]): Void {
		Lib.disp("Check 1");
		var n11: Int = 2;
		var n12: Double = 3.1;
		var n13: Double = (n11 + n12);
		var asser1: Boolean = (n13 == 5.1);
		Check.check(asser1);
		Lib.disp("Check 2");
		var a21: IntMatrix = new IntMatrix([1, 2, 3]);
		var a22: DoubleMatrix = new DoubleMatrix([1.1, 2.2, 3.3]);
		var a23: DoubleMatrix = Lib.plus(a21, a22);
		var asser2: Boolean = AllTrue.allTrue(Lib.eq(a23, new DoubleMatrix([2.1, 4.2, 6.3])));
		Check.check(asser2);
		Lib.disp("Check 3");
		var a31: BooleanMatrix = Lib.logical(new IntMatrix([1, 0, 3]));
		var a32: IntMatrix = Lib.dotTimes(a31, 5);
		var asser3: Boolean = AllTrue.allTrue(Lib.eq(a32, new IntMatrix([5, 0, 5])));
		Check.check(asser3);
		Lib.disp("Check 4");
		var a41: BooleanMatrix = Lib.logical(new IntMatrix([1, 2, 0]));
		var a42: IntMatrix = Lib.dotTimes(3, a41);
		var asser4: Boolean = AllTrue.allTrue(Lib.eq(a42, new IntMatrix([3, 3, 0])));
		Check.check(asser4);
		Lib.disp("Check 5");
		var a51: Int = 4;
		var a52: Int = -a51;
		var asser5: Boolean = (a52 == -4);
		Check.check(asser5);
		Lib.disp("Check 6");
		var a61: IntMatrix = new IntMatrix([1, 2, 3]);
		var a62: IntMatrix = Lib.minus(a61);
		var asser6: Boolean = AllTrue.allTrue(Lib.eq(a62, new IntMatrix([-1, -2, -3])));
		Check.check(asser6);
		Lib.disp("Check 7");
		var a71: IntMatrix = new IntMatrix([1, 2, 3]);
		var a72: IntMatrix = new IntMatrix([2, 3, 2]);
		var a73: IntMatrix = Lib.dotPower(a71, a72);
		var asser7: Boolean = AllTrue.allTrue(Lib.eq(a73, new IntMatrix([1, 8, 9])));
		Check.check(asser7);
		Lib.disp("Check 8");
		var a81: IntMatrix = new IntMatrix([[1, 2, 3], [2, 3, 4]]);
		var a82: IntMatrix = new IntMatrix([[2, 3], [1, 2], [3, 2]]);
		var a83: IntMatrix = Lib.times(a81, a82);
		var asser8: Boolean = AllTrue.allTrue(Lib.eq(a83, new IntMatrix([[13, 13], [19, 20]])));
		Check.check(asser8);
		Lib.disp("Check 9");
		var a91: IntMatrix = new IntMatrix([[1, 2], [2, 3]]);
		var a92: IntMatrix = Lib.times(a91, 2);
		var asser9: Boolean = AllTrue.allTrue(Lib.eq(a92, new IntMatrix([[2, 4], [4, 6]])));
		Check.check(asser9);
		Lib.disp("Check 10");
		var a101: DoubleMatrix = new DoubleMatrix([[1.0, 2.0, 3.0], [4.0, 5.0, 6.0], [7.0, 8.0, 9.0]]);
		var a102: DoubleMatrix = new DoubleMatrix([[2.0, 3.0, 4.0], [1.0, 2.0, 3.0], [6.0, 7.0, 4.0]]);
		var a103: DoubleMatrix = Lib.divide(a101, a102);
		var asser10: Boolean = DoubleEq.doubleEq(a103, new DoubleMatrix([[0.0, 1.0, 0.0], [3.0, -2.0, 0.0], [6.0, -5.0, 0.0]]));
		Check.check(asser10);
		Lib.disp("Check 11");
		var a111: IntMatrix = new IntMatrix([[8, 2, 6], [7, 5, 2]]);
		var a112: DoubleMatrix = Lib.divide(a111, 2);
		Lib.disp(a112);
		var asser11: Boolean = DoubleEq.doubleEq(a112, new DoubleMatrix([[4.0, 1.0, 3.0], [3.5, 2.5, 1.0]]));
		Check.check(asser11);
		Lib.disp("Check 12");
		var a121: IntMatrix = new IntMatrix([[1, 2, 3], [4, 5, 6]]);
		var a122: IntMatrix = a121.transpose();
		var asser12: Boolean = AllTrue.allTrue(Lib.eq(a122, new IntMatrix([[1, 4], [2, 5], [3, 6]])));
		Check.check(asser12);
		Lib.disp("Check 13");
		var a131: IntMatrix = new IntMatrix([[1, 2, 3], [4, 5, 6]]);
		var a132: IntMatrix = Lib.dotPower(a131, 2);
		var asser13: Boolean = AllTrue.allTrue(Lib.eq(a132, new IntMatrix([[1, 4, 9], [16, 25, 36]])));
		Check.check(asser13);
		Lib.disp("Check 14");
		var a141: IntMatrix = new IntMatrix([[2, 3, 4], [1, 2, 3], [6, 7, 4]]);
		var a142: IntMatrix = Lib.power(a141, 3);
		var asser14: Boolean = AllTrue.allTrue(Lib.eq(a142, new IntMatrix([[300, 404, 376], [204, 276, 260], [512, 676, 596]])));
		Check.check(asser14);
		var asser: Boolean = (((((((((((((asser1 && asser2) && asser3) && asser4) && asser5) && asser6) && asser7) && asser8) && asser9) && asser10) && asser11) && asser12) && asser13) && asser14);
		if (asser) 
			Lib.disp("Success.");
		else 
			Lib.disp("Failure.");
	}

}

