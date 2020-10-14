// Beginning of RelLib.x10

    // REL { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def NAME(x: Boolean, y: Boolean): Boolean {
        return (x?1:0) REL (y?1:0);
    }

    // 2. Matrices of the same size
    public static def NAME(x: BooleanMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) REL (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def NAME(x: Boolean, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array =
            new Array[Boolean](
                r,
                (
                    (p:Point(r.rank))=>
                    (x?1:0) REL (y.array(p(0), p(1))?1:0)
                )
            );
        return new BooleanMatrix(array, n, m);
    }

    public static def NAME(x: BooleanMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array =
            new Array[Boolean](
                r,
                (
                    (p:Point(r.rank))=>
                    (x.array(p(0), p(1))?1:0) REL (y?1:0)
                )
            );

        return new BooleanMatrix(array, n, m);
    }

    // REL } ------------------------------------------------------------------------------

// End of RelLib.x10