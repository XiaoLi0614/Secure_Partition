
// Beginning of N-D-TMathMMLib.x10

    // MATH { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def NAME(x: BooleanMatrix, y: NDTMatrix): NDTMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At NAME op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) MATH
                    y.array(p(0), p(1))
            )
        );

        return new NDTMatrix(array, n, m);
    }
    public static def NAME(x: NDTMatrix, y: BooleanMatrix): NDTMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At NAME op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) MATH
                    (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new NDTMatrix(array, n, m);
    }

    // MATH } ------------------------------------------------------------------------------

// End of N-D-TMathMMLib.x10