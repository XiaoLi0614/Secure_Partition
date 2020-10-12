
// Beginning of N-D-TMathFunLib.x10

    // MATH { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def NAME(x: Boolean, y: NDT): NDT {
        return FUN((x?1:0), y) as NDT;
    }
    public static def NAME(x: NDT, y: Boolean): NDT {
        return FUN(x, (y?1:0)) as NDT;
    }

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
                    FUN(
                        (x.array(p(0), p(1)) ? 1 : 0),
                        y.array(p(0), p(1))
                    ) as NDT
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
                    FUN(
                        x.array(p(0), p(1)),
                        (y.array(p(0), p(1)) ? 1 : 0)
                    ) as NDT
            )
        );

        return new NDTMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def NAME(x: Boolean, y: NDTMatrix): NDTMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](
            r, (
                (p:Point(r.rank))=>
                    FUN(xv, y.array(p(0), p(1))) as NDT
            )
        );

        return new NDTMatrix(array, n, m);
    }
    public static def NAME(x: NDTMatrix, y: Boolean): NDTMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](
            r, (
                (p:Point(r.rank))=>
                    FUN(x.array(p(0), p(1)), yv) as NDT
            )
        );

        return new NDTMatrix(array, n, m);
    }

    // MATH } ------------------------------------------------------------------------------

// End of N-D-TMathFunLib.x10
