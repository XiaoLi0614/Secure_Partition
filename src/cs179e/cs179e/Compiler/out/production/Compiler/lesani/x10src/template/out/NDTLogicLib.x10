
// Beginning of N-D-TLogicLib.x10

    // && { ------------------------------------------------------------------------------
    // 1. Primitive data types, Int and Boolean
    public static def and(x: Int, y: Boolean): Boolean {
        var b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 = false;
        val b2: Boolean = y;

        return b1 && b2;
    }
    public static def and(x: Boolean, y: Int): Boolean {
        val b1: Boolean = x;
        var b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        return b1 && b2;
    }

    // 2. Matrices of the same size
    public static def and(x: IntMatrix, y: BooleanMatrix): BooleanMatrix {
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

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def and(x: BooleanMatrix, y: IntMatrix): BooleanMatrix {
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
                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

/*
    // Note: This part is commented now because the last release of x10 crashes on these.
    // 3. For a Matrix and a primitive
    public static def and(x: Int, y: BooleanMatrix): BooleanMatrix {
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
                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, np, mp);
    }

    public static def and(x: Boolean, y: IntMatrix): BooleanMatrix {
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

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, np, mp);
    }

    public static def and(x: BooleanMatrix, y: Int): BooleanMatrix {
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
                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def and(x: IntMatrix, y: Boolean): BooleanMatrix {
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

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }
*/

    // && } ------------------------------------------------------------------------------


// End of N-D-TLogicLib.x10


// Beginning of N-D-TLogicLib.x10

    // || { ------------------------------------------------------------------------------
    // 1. Primitive data types, Int and Boolean
    public static def or(x: Int, y: Boolean): Boolean {
        var b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 = false;
        val b2: Boolean = y;

        return b1 || b2;
    }
    public static def or(x: Boolean, y: Int): Boolean {
        val b1: Boolean = x;
        var b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        return b1 || b2;
    }

    // 2. Matrices of the same size
    public static def or(x: IntMatrix, y: BooleanMatrix): BooleanMatrix {
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

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def or(x: BooleanMatrix, y: IntMatrix): BooleanMatrix {
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
                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

/*
    // Note: This part is commented now because the last release of x10 crashes on these.
    // 3. For a Matrix and a primitive
    public static def or(x: Int, y: BooleanMatrix): BooleanMatrix {
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
                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, np, mp);
    }

    public static def or(x: Boolean, y: IntMatrix): BooleanMatrix {
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

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, np, mp);
    }

    public static def or(x: BooleanMatrix, y: Int): BooleanMatrix {
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
                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def or(x: IntMatrix, y: Boolean): BooleanMatrix {
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

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }
*/

    // || } ------------------------------------------------------------------------------


// End of N-D-TLogicLib.x10


// Beginning of N-D-TLogicLib.x10

    // && { ------------------------------------------------------------------------------
    // 1. Primitive data types, Double and Boolean
    public static def and(x: Double, y: Boolean): Boolean {
        var b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 = false;
        val b2: Boolean = y;

        return b1 && b2;
    }
    public static def and(x: Boolean, y: Double): Boolean {
        val b1: Boolean = x;
        var b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        return b1 && b2;
    }

    // 2. Matrices of the same size
    public static def and(x: DoubleMatrix, y: BooleanMatrix): BooleanMatrix {
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

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def and(x: BooleanMatrix, y: DoubleMatrix): BooleanMatrix {
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
                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

/*
    // Note: This part is commented now because the last release of x10 crashes on these.
    // 3. For a Matrix and a primitive
    public static def and(x: Double, y: BooleanMatrix): BooleanMatrix {
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
                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, np, mp);
    }

    public static def and(x: Boolean, y: DoubleMatrix): BooleanMatrix {
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

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, np, mp);
    }

    public static def and(x: BooleanMatrix, y: Double): BooleanMatrix {
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
                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def and(x: DoubleMatrix, y: Boolean): BooleanMatrix {
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

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }
*/

    // && } ------------------------------------------------------------------------------


// End of N-D-TLogicLib.x10


// Beginning of N-D-TLogicLib.x10

    // || { ------------------------------------------------------------------------------
    // 1. Primitive data types, Double and Boolean
    public static def or(x: Double, y: Boolean): Boolean {
        var b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 = false;
        val b2: Boolean = y;

        return b1 || b2;
    }
    public static def or(x: Boolean, y: Double): Boolean {
        val b1: Boolean = x;
        var b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        return b1 || b2;
    }

    // 2. Matrices of the same size
    public static def or(x: DoubleMatrix, y: BooleanMatrix): BooleanMatrix {
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

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def or(x: BooleanMatrix, y: DoubleMatrix): BooleanMatrix {
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
                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

/*
    // Note: This part is commented now because the last release of x10 crashes on these.
    // 3. For a Matrix and a primitive
    public static def or(x: Double, y: BooleanMatrix): BooleanMatrix {
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
                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, np, mp);
    }

    public static def or(x: Boolean, y: DoubleMatrix): BooleanMatrix {
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

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, np, mp);
    }

    public static def or(x: BooleanMatrix, y: Double): BooleanMatrix {
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
                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def or(x: DoubleMatrix, y: Boolean): BooleanMatrix {
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

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }
*/

    // || } ------------------------------------------------------------------------------


// End of N-D-TLogicLib.x10

