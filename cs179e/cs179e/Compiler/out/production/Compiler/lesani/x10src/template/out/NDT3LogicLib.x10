
// Beginning of N-D-T3LogicLib.x10
    // && { ------------------------------------------------------------------------------
    // 1. Primitive data types, Int and Double.
    public static def and(x: Int, y: Int): Boolean {
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

        return b1 && b2;
    }

    // 2. Matrices of the same size
    public static def and(x: IntMatrix, y: IntMatrix): BooleanMatrix {
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

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def and(x: Int, y: IntMatrix): BooleanMatrix {
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

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def and(x: IntMatrix, y: Int): BooleanMatrix {
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

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // && } ------------------------------------------------------------------------------


// End of N-D-T3LogicLib.x10
// Beginning of N-D-T3LogicLib.x10
    // || { ------------------------------------------------------------------------------
    // 1. Primitive data types, Int and Double.
    public static def or(x: Int, y: Int): Boolean {
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

        return b1 || b2;
    }

    // 2. Matrices of the same size
    public static def or(x: IntMatrix, y: IntMatrix): BooleanMatrix {
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

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def or(x: Int, y: IntMatrix): BooleanMatrix {
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

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def or(x: IntMatrix, y: Int): BooleanMatrix {
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

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // || } ------------------------------------------------------------------------------


// End of N-D-T3LogicLib.x10
// Beginning of N-D-T3LogicLib.x10
    // && { ------------------------------------------------------------------------------
    // 1. Primitive data types, Int and Double.
    public static def and(x: Int, y: Double): Boolean {
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

        return b1 && b2;
    }

    // 2. Matrices of the same size
    public static def and(x: IntMatrix, y: DoubleMatrix): BooleanMatrix {
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

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def and(x: Int, y: DoubleMatrix): BooleanMatrix {
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

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def and(x: IntMatrix, y: Double): BooleanMatrix {
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

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // && } ------------------------------------------------------------------------------


// End of N-D-T3LogicLib.x10
// Beginning of N-D-T3LogicLib.x10
    // || { ------------------------------------------------------------------------------
    // 1. Primitive data types, Int and Double.
    public static def or(x: Int, y: Double): Boolean {
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

        return b1 || b2;
    }

    // 2. Matrices of the same size
    public static def or(x: IntMatrix, y: DoubleMatrix): BooleanMatrix {
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

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def or(x: Int, y: DoubleMatrix): BooleanMatrix {
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

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def or(x: IntMatrix, y: Double): BooleanMatrix {
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

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // || } ------------------------------------------------------------------------------


// End of N-D-T3LogicLib.x10
// Beginning of N-D-T3LogicLib.x10
    // && { ------------------------------------------------------------------------------
    // 1. Primitive data types, Int and Double.
    public static def and(x: Double, y: Int): Boolean {
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

        return b1 && b2;
    }

    // 2. Matrices of the same size
    public static def and(x: DoubleMatrix, y: IntMatrix): BooleanMatrix {
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

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def and(x: Double, y: IntMatrix): BooleanMatrix {
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

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def and(x: DoubleMatrix, y: Int): BooleanMatrix {
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

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // && } ------------------------------------------------------------------------------


// End of N-D-T3LogicLib.x10
// Beginning of N-D-T3LogicLib.x10
    // || { ------------------------------------------------------------------------------
    // 1. Primitive data types, Int and Double.
    public static def or(x: Double, y: Int): Boolean {
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

        return b1 || b2;
    }

    // 2. Matrices of the same size
    public static def or(x: DoubleMatrix, y: IntMatrix): BooleanMatrix {
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

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def or(x: Double, y: IntMatrix): BooleanMatrix {
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

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def or(x: DoubleMatrix, y: Int): BooleanMatrix {
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

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // || } ------------------------------------------------------------------------------


// End of N-D-T3LogicLib.x10
// Beginning of N-D-T3LogicLib.x10
    // && { ------------------------------------------------------------------------------
    // 1. Primitive data types, Int and Double.
    public static def and(x: Double, y: Double): Boolean {
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

        return b1 && b2;
    }

    // 2. Matrices of the same size
    public static def and(x: DoubleMatrix, y: DoubleMatrix): BooleanMatrix {
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

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def and(x: Double, y: DoubleMatrix): BooleanMatrix {
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

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def and(x: DoubleMatrix, y: Double): BooleanMatrix {
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

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // && } ------------------------------------------------------------------------------


// End of N-D-T3LogicLib.x10
// Beginning of N-D-T3LogicLib.x10
    // || { ------------------------------------------------------------------------------
    // 1. Primitive data types, Int and Double.
    public static def or(x: Double, y: Double): Boolean {
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

        return b1 || b2;
    }

    // 2. Matrices of the same size
    public static def or(x: DoubleMatrix, y: DoubleMatrix): BooleanMatrix {
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

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def or(x: Double, y: DoubleMatrix): BooleanMatrix {
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

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def or(x: DoubleMatrix, y: Double): BooleanMatrix {
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

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // || } ------------------------------------------------------------------------------


// End of N-D-T3LogicLib.x10