
// Beginning of N-D-TLogicLib.x10

    // LOGIC { ------------------------------------------------------------------------------
    // 1. Primitive data types, NDT and Boolean
    public static def NAME(x: NDT, y: Boolean): Boolean {
        var b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 = false;
        val b2: Boolean = y;

        return b1 LOGIC b2;
    }
    public static def NAME(x: Boolean, y: NDT): Boolean {
        val b1: Boolean = x;
        var b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        return b1 LOGIC b2;
    }

    // 2. Matrices of the same size
    public static def NAME(x: NDTMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At & op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank)) => {
                    var b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;
                    val b2: Boolean = y.array(p(0), p(1));

                    return b1 LOGIC b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def NAME(x: BooleanMatrix, y: NDTMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At & op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank)) => {
                    val b1: Boolean = x.array(p(0), p(1));
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                        b2 = true;
                    else
                        b2 = false;
                    return b1 LOGIC b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

/*
    // Note: This part is commented now because the last release of x10 crashes on these.
    // 3. For a Matrix and a primitive
    public static def NAME(x: NDT, y: BooleanMatrix): BooleanMatrix {
        val np = y.n;
        val mp = y.m;

        val b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 = false;

        val r = (0..np-1)*(0..mp-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
                    val b2: Boolean = y.array(p(0), p(1));
                    return b1 LOGIC b2;
                }
            )
        );

        return new BooleanMatrix(array, np, mp);
    }

    public static def NAME(x: Boolean, y: NDTMatrix): BooleanMatrix {
        val np = y.n;
        val mp = y.m;

        val b1: Boolean = x;


        val r = (0..np-1)*(0..mp-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                      b2 = true;
                    else
                      b2 = false;

                    return b1 LOGIC b2;
                }
            )
        );

        return new BooleanMatrix(array, np, mp);
    }

    public static def NAME(x: BooleanMatrix, y: NDT): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;


        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
                    val b1: Boolean = x.array(p(0), p(1));
                    return b1 LOGIC b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def NAME(x: NDTMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val b2: Boolean = y;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {


                    val b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;

                    return b1 LOGIC b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }
*/

    // LOGIC } ------------------------------------------------------------------------------


// End of N-D-TLogicLib.x10

