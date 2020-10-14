
// Beginning of N-D-T3MathMMLib.x10

    // MATH { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def NAME(x: NDT1Matrix, y: NDT2Matrix): NDT3Matrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At NAME op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT3](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) MATH y.array(p(0), p(1))
            )
        );

        return new NDT3Matrix(array, n, m);
    }

    // MATH } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10