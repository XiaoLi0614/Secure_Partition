// Beginning of MathMMLib

    // MATH { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def NAME(x: BooleanMatrix, y: BooleanMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At NAME op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val xv = (x.array(p(0), p(1))) ? 1 : 0;
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return xv MATH yv;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }

    // MATH } ------------------------------------------------------------------------------

// End of MathMMLib