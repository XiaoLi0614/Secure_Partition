
// Beginning of N-D-TMathUFunLib.x10

    // MATH { ------------------------------------------------------------------------------

    public static def NAME(x: NDT): NDT {
        return FUN(x) as NDT;
    }

    // 2. Matrices of the same size
    public static def NAME(x: NDTMatrix): NDTMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](
            r, (
                (p:Point(r.rank))=>
                    FUN(
                        x.array(p(0), p(1))
                    ) as NDT
            )
        );

        return new NDTMatrix(array, n, m);
    }
    // MATH } ------------------------------------------------------------------------------

// End of N-D-TMathUFunLib.x10
