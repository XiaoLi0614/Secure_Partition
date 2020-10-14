
// Beginning of LogicLib

    // && { ------------------------------------------------------------------------------

    public static def and(x: Boolean, y: Boolean): Boolean {
        return x && y;
    }

    public static def and(x: BooleanMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At | op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](r, (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) && y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def and(x: Boolean, y: BooleanMatrix): BooleanMatrix {

        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](r, (
                (p:Point(r.rank))=>
                x && y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def and(x: BooleanMatrix, y: Boolean): BooleanMatrix {

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](r, (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) && y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // && } ------------------------------------------------------------------------------

// End of LogicLib

// Beginning of LogicLib

    // || { ------------------------------------------------------------------------------

    public static def or(x: Boolean, y: Boolean): Boolean {
        return x || y;
    }

    public static def or(x: BooleanMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At | op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](r, (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) || y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def or(x: Boolean, y: BooleanMatrix): BooleanMatrix {

        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](r, (
                (p:Point(r.rank))=>
                x || y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def or(x: BooleanMatrix, y: Boolean): BooleanMatrix {

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](r, (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) || y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // || } ------------------------------------------------------------------------------

// End of LogicLib
