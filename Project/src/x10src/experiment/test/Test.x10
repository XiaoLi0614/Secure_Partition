
public class Test {
	public static def main(args: Array[String]): Void {
    }

    public static def divide(a: DoubleMatrix, b: DoubleMatrix): DoubleMatrix {
        if (a.m != b.m)
            error("At matrix / op: Mismatch of Operand Dimensions.");

        val biArray = invert(a.array);
        val bi = new DoubleMatrix(biArray);
        return times(a, bi);
    }

    public static def invert(a: Array[Double](2)): Array[Double](2) {
        val n = a.region.max(0)-a.region.min(0) + 1;
        val r = (0..n-1)*(0..n-1);
        val x = new Array[Double](r);
        val b = new Array[Double](r);

        val r2 = (0..n-1);
        val index = new Array[Int](r2);
        for (var i: Int=0; i<n; ++i) b(i, i) = 1;

        // Transform the matrix into an upper triangle
        gaussian(a, index);

        // Update the matrix b[i][j] with the ratios stored
        for (var i: Int=0; i<n-1; ++i)
            for (var j: Int=i+1; j<n; ++j)
                for (var k: Int=0; k<n; ++k)
                    b(index(j), k)
                        -= a(index(j), i) * b(index(i), k);

        // Perform backward substitutions
        for (var i: Int=0; i<n; ++i) {
            x(n-1, i) = b(index(n-1), i) / a(index(n-1), n-1);
            for (var j: Int=n-2; j>=0; --j) {
                x(j, i) = b(index(j), i);
                for (var k: Int=j+1; k<n; ++k)
                    x(j, i) -= a(index(j), k) * x(k, i);
                x(j, i) /= a(index(j), j);
            }
        }
        return x;
    }

    // Method to carry out the partial-pivoting Gaussian
    // elimination.  Here index[] stores pivoting order.

    public static def gaussian(a: Array[Double](2), index: Array[Double](1)): Void {
        val n = index.region.max(0)-index.region.min(0) + 1;

        val r2 = (0..n-1)
        val c = new Array[Double](r2);

        // Initialize the index
        for (var i: Int=0; i<n; ++i) index(i) = i;

        // Find the rescaling factors, one from each row
        for (var i:Int=0; i<n; ++i) {
            var c1: Double = 0;
            for (var j:Int=0; j<n; ++j) {
                var c0: Double = Math.abs(a(i, j));
                if (c0 > c1) c1 = c0;
            }
            c(i) = c1;
        }

        // Search the pivoting element from each column
        var k: Int = 0;
        for (var j:Int =0; j<n-1; ++j) {
            var pi1: Double = 0;
            for (var i: Int=j; i<n; ++i) {
                var pi0: Double = Math.abs(a(index(i), j));
                pi0 = pi0 / c(index(i));
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            // Interchange rows according to the pivoting order
            var itmp: Int = index(j);
            index(j) = index(k);
            index(k) = itmp;
            for (var i: Int=j+1; i<n; ++i) {
                var pj: Double = a(index(i), j) / a(index(j), j);

                // Record pivoting ratios below the diagonal
                a(index(i), j) = pj;

                // Modify other elements accordingly
                for (var l: Int =j+1; l<n; ++l)
                    a(index(i), l) = a(index(i), l) - pj * a(index(j), l);
            }
        }
    }
}





//        val a: A = at(here.next()) new A();
//        async (here.next()) {
//            (Transfer.transfer(a)).f();
//        }
//        Console.OUT.println(2 & 5);
//        Console.OUT.println(2.1 & 5);
//        Console.OUT.println(!2);




//class Transfer {
//    static def transfer[T](t: T): T{self.home==here} = {
//        return (t as T{self.home==here});
//    }
//}
//
//class A {
//    public def f(): Void {
//    }
//
//}


/*
class A {
    public proto def f(val b: B{self.home==this.home}): Void {
        val i = 2;
        b.f(i);
    }
}

class B {
    public def f(i: Int): Void {
    }
}



public class Test {
	public static def main(args: Rail[String]): Void {
	    val a = new A();
	}
}
*/