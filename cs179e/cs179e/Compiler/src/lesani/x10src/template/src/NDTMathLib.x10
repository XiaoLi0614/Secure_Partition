
// Beginning of N-D-TMathLib.x10

    // MATH { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def NAME(x: Boolean, y: NDT): NDT {
        return (x?1:0) MATH y;
    }
    public static def NAME(x: NDT, y: Boolean): NDT {
        return x MATH (y?1:0);
    }

    // 2. Matrices of the same size
    // In N-D-TMathMMLib

    // 3. For a Matrix and a primitive
    public static def NAME(x: Boolean, y: NDTMatrix): NDTMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](
            r, (
                (p:Point(r.rank))=>
                    xv MATH y.array(p(0), p(1))
            )
        );

        return new NDTMatrix(array, n, m);
    }
    public static def NAME(x: NDT, y: BooleanMatrix): NDTMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](
            r, (
                (p:Point(r.rank))=>
                    x MATH (y.array(p(0), p(1)) ? 1 : 0)
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
                    x.array(p(0), p(1)) MATH yv
            )
        );

        return new NDTMatrix(array, n, m);
    }
    public static def NAME(x: BooleanMatrix, y: NDT): NDTMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) MATH y
            )
        );

        return new NDTMatrix(array, n, m);
    }

    // MATH } ------------------------------------------------------------------------------

// End of N-D-TMathLib.x10