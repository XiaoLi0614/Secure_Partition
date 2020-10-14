
// Beginning of N-D-T3LogicLib.x10
    // LOGIC { ------------------------------------------------------------------------------
    // 1. Primitive data types, Int and Double.
    public static def NAME(x: NDT1, y: NDT2): Boolean {
//        val b1 = x != 0 ? true : false;
//        val b2 = y != 0 ? true : false;
        var b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 =false;
        var b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        return b1 LOGIC b2;
    }

    // 2. Matrices of the same size
    public static def NAME(x: NDT1Matrix, y: NDT2Matrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At & op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank)) => {
//                    val b1 = (x.array(p(0), p(1)) != 0) ? true : false;
//                    val b2 = (y.array(p(0), p(1)) != 0) ? true : false;
                    var b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;
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

    // 3. For a Matrix and a primitive
    public static def NAME(x: NDT1, y: NDT2Matrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

//        val b1 = x != 0 ? true : false;
        val b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 = false;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
//                    val b2 = y.array(p(0), p(1)) != 0 ? true : false;
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

    public static def NAME(x: NDT1Matrix, y: NDT2): BooleanMatrix {
        val n = x.n;
        val m = x.m;

//        val b2 = y != 0 ? true : false;
        val b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
//                    val b1 = x.array(p(0), p(1)) != 0 ? true : false;
                    var b1: Boolean;
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

    // LOGIC } ------------------------------------------------------------------------------


// End of N-D-T3LogicLib.x10