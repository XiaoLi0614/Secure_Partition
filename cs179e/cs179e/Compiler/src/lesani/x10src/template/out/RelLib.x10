// Beginning of RelLib.x10

    // < { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def lt(x: Boolean, y: Boolean): Boolean {
        return (x?1:0) < (y?1:0);
    }

    // 2. Matrices of the same size
    public static def lt(x: BooleanMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) < (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def lt(x: Boolean, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array =
            new Array[Boolean](
                r,
                (
                    (p:Point(r.rank))=>
                    (x?1:0) < (y.array(p(0), p(1))?1:0)
                )
            );
        return new BooleanMatrix(array, n, m);
    }

    public static def lt(x: BooleanMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array =
            new Array[Boolean](
                r,
                (
                    (p:Point(r.rank))=>
                    (x.array(p(0), p(1))?1:0) < (y?1:0)
                )
            );

        return new BooleanMatrix(array, n, m);
    }

    // < } ------------------------------------------------------------------------------

// End of RelLib.x10// Beginning of RelLib.x10

    // <= { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def lteq(x: Boolean, y: Boolean): Boolean {
        return (x?1:0) <= (y?1:0);
    }

    // 2. Matrices of the same size
    public static def lteq(x: BooleanMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) <= (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def lteq(x: Boolean, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array =
            new Array[Boolean](
                r,
                (
                    (p:Point(r.rank))=>
                    (x?1:0) <= (y.array(p(0), p(1))?1:0)
                )
            );
        return new BooleanMatrix(array, n, m);
    }

    public static def lteq(x: BooleanMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array =
            new Array[Boolean](
                r,
                (
                    (p:Point(r.rank))=>
                    (x.array(p(0), p(1))?1:0) <= (y?1:0)
                )
            );

        return new BooleanMatrix(array, n, m);
    }

    // <= } ------------------------------------------------------------------------------

// End of RelLib.x10// Beginning of RelLib.x10

    // > { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def gt(x: Boolean, y: Boolean): Boolean {
        return (x?1:0) > (y?1:0);
    }

    // 2. Matrices of the same size
    public static def gt(x: BooleanMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) > (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def gt(x: Boolean, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array =
            new Array[Boolean](
                r,
                (
                    (p:Point(r.rank))=>
                    (x?1:0) > (y.array(p(0), p(1))?1:0)
                )
            );
        return new BooleanMatrix(array, n, m);
    }

    public static def gt(x: BooleanMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array =
            new Array[Boolean](
                r,
                (
                    (p:Point(r.rank))=>
                    (x.array(p(0), p(1))?1:0) > (y?1:0)
                )
            );

        return new BooleanMatrix(array, n, m);
    }

    // > } ------------------------------------------------------------------------------

// End of RelLib.x10// Beginning of RelLib.x10

    // >= { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def gteq(x: Boolean, y: Boolean): Boolean {
        return (x?1:0) >= (y?1:0);
    }

    // 2. Matrices of the same size
    public static def gteq(x: BooleanMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) >= (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def gteq(x: Boolean, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array =
            new Array[Boolean](
                r,
                (
                    (p:Point(r.rank))=>
                    (x?1:0) >= (y.array(p(0), p(1))?1:0)
                )
            );
        return new BooleanMatrix(array, n, m);
    }

    public static def gteq(x: BooleanMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array =
            new Array[Boolean](
                r,
                (
                    (p:Point(r.rank))=>
                    (x.array(p(0), p(1))?1:0) >= (y?1:0)
                )
            );

        return new BooleanMatrix(array, n, m);
    }

    // >= } ------------------------------------------------------------------------------

// End of RelLib.x10