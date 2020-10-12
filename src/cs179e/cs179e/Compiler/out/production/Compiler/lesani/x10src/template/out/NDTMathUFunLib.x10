
// Beginning of N-D-TMathUFunLib.x10

    // MATH { ------------------------------------------------------------------------------

    public static def abs(x: Int): Int {
        return Math.abs(x) as Int;
    }

    // 2. Matrices of the same size
    public static def abs(x: IntMatrix): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.abs(
                        x.array(p(0), p(1))
                    ) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }
    // MATH } ------------------------------------------------------------------------------

// End of N-D-TMathUFunLib.x10

// Beginning of N-D-TMathUFunLib.x10

    // MATH { ------------------------------------------------------------------------------

    public static def abs(x: Double): Double {
        return Math.abs(x) as Double;
    }

    // 2. Matrices of the same size
    public static def abs(x: DoubleMatrix): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.abs(
                        x.array(p(0), p(1))
                    ) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    // MATH } ------------------------------------------------------------------------------

// End of N-D-TMathUFunLib.x10
