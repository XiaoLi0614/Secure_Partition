
// Beginning of N-D-T3RelLib.x10

    // REL { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def NAME(x: NDT1Matrix, y: NDT2Matrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) REL y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def NAME(x: NDT1, y: NDT2Matrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x REL y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def NAME(x: NDT1Matrix, y: NDT2): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) REL y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // REL } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10