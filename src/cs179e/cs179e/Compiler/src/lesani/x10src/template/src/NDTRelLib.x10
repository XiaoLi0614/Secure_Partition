
// Beginning of N-D-TRelLib.x10

    // REL { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def NAME(x: Boolean, y: NDT): Boolean {
        return (x?1:0) REL y;
    }

    public static def NAME(x: NDT, y: Boolean): Boolean {
        return x REL (y?1:0);
    }

    // 2. Matrices of the same size
    public static def NAME(x: BooleanMatrix, y: NDTMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) REL y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def NAME(x: NDTMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) REL (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def NAME(x: Boolean, y: NDTMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x?1:0) REL y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def NAME(x: NDT, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x REL (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def NAME(x: BooleanMatrix, y: NDT): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) REL y
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def NAME(x: NDTMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) REL (y?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // REL } ------------------------------------------------------------------------------



// End of N-D-TRelLib.x10

