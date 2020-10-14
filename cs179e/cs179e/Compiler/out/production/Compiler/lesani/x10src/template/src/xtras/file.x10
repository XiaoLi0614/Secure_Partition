    // == { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def eq(x: BooleanMatrix, y: NDTMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) == y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def eq(x: NDTMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) == (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def eq(x: Boolean, y: NDTMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x?1:0) == y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def eq(x: NDT1Matrix, y: NDT2): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) == y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // == } ------------------------------------------------------------------------------
    // != { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def neq(x: NDT1Matrix, y: NDT2Matrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) != y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def neq(x: NDT1, y: NDT2Matrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x != y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def neq(x: NDT1Matrix, y: NDT2): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) != y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // != } ------------------------------------------------------------------------------
    // > { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def gt(x: NDT1Matrix, y: NDT2Matrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) > y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def gt(x: NDT1, y: NDT2Matrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x > y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def gt(x: NDT1Matrix, y: NDT2): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) > y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // > } ------------------------------------------------------------------------------
    // < { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def lt(x: NDT1Matrix, y: NDT2Matrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) < y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def lt(x: NDT1, y: NDT2Matrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x < y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def lt(x: NDT1Matrix, y: NDT2): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) < y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // < } ------------------------------------------------------------------------------
    // >= { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def gteq(x: NDT1Matrix, y: NDT2Matrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) >= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def gteq(x: NDT1, y: NDT2Matrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x >= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def gteq(x: NDT1Matrix, y: NDT2): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) >= y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // > } ------------------------------------------------------------------------------
    // <= { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def lteq(x: NDT1Matrix, y: NDT2Matrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) <= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def lteq(x: NDT1, y: NDT2Matrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x <= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def lteq(x: NDT1Matrix, y: NDT2): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) <= y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // < } ------------------------------------------------------------------------------
    // == { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def eq(x: NDT1Matrix, y: NDT2Matrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) == y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def eq(x: NDT1, y: NDT2Matrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x == y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def eq(x: NDT1Matrix, y: NDT2): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) == y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // == } ------------------------------------------------------------------------------
    // != { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def neq(x: NDT1Matrix, y: NDT2Matrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) != y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def neq(x: NDT1, y: NDT2Matrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x != y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def neq(x: NDT1Matrix, y: NDT2): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) != y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // != } ------------------------------------------------------------------------------
    // > { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def gt(x: NDT1Matrix, y: NDT2Matrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) > y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def gt(x: NDT1, y: NDT2Matrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x > y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def gt(x: NDT1Matrix, y: NDT2): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) > y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // > } ------------------------------------------------------------------------------
    // < { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def lt(x: NDT1Matrix, y: NDT2Matrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) < y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def lt(x: NDT1, y: NDT2Matrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x < y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def lt(x: NDT1Matrix, y: NDT2): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) < y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // < } ------------------------------------------------------------------------------
    // >= { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def gteq(x: NDT1Matrix, y: NDT2Matrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) >= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def gteq(x: NDT1, y: NDT2Matrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x >= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def gteq(x: NDT1Matrix, y: NDT2): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) >= y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // > } ------------------------------------------------------------------------------
    // <= { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def lteq(x: NDT1Matrix, y: NDT2Matrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) <= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def lteq(x: NDT1, y: NDT2Matrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x <= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def lteq(x: NDT1Matrix, y: NDT2): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) <= y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // < } ------------------------------------------------------------------------------
    // & { ------------------------------------------------------------------------------
    // 1. Primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static operator (x: NDTMatrix) & (y: BooleanMatrix): BooleanMatrix {
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

    public static operator (x: BooleanMatrix) & (y: NDTMatrix): BooleanMatrix {
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
    public static operator (x: NDT) & (y: BooleanMatrix): BooleanMatrix {
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

    public static operator (x: Boolean) & (y: NDTMatrix): BooleanMatrix {
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

    public static operator (x: BooleanMatrix) & (y: NDT): BooleanMatrix {
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

    public static operator (x: NDTMatrix) & (y: Boolean): BooleanMatrix {
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

    // & } ------------------------------------------------------------------------------

    // | { ------------------------------------------------------------------------------

    // 1. Primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static operator (x: NDTMatrix) | (y: BooleanMatrix): BooleanMatrix {
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

    public static operator (x: BooleanMatrix) | (y: NDTMatrix): BooleanMatrix {
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
    public static operator (x: NDT) | (y: BooleanMatrix): BooleanMatrix {
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

    public static operator (x: Boolean) | (y: NDTMatrix): BooleanMatrix {
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

    public static operator (x: BooleanMatrix) | (y: NDT): BooleanMatrix {
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

    public static operator (x: NDTMatrix) | (y: Boolean): BooleanMatrix {
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

    // & } ------------------------------------------------------------------------------

    // & { ------------------------------------------------------------------------------
/*
    public static operator (x: Boolean) & (y: Boolean): Boolean {
        return x && y;
    }
*/

    public static operator (x: BooleanMatrix) & (y: BooleanMatrix): BooleanMatrix {
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
    public static operator (x: Boolean) & (y: BooleanMatrix): BooleanMatrix {

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
    public static operator (x: BooleanMatrix) & (y: Boolean): BooleanMatrix {

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

    // & } ------------------------------------------------------------------------------
    // | { ------------------------------------------------------------------------------
/*
    public static operator (x: Boolean) | (y: Boolean): Boolean {
        return x || y;
    }
*/

    public static operator (x: BooleanMatrix) | (y: BooleanMatrix): BooleanMatrix {
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
    public static operator (x: Boolean) | (y: BooleanMatrix): BooleanMatrix {

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
    public static operator (x: BooleanMatrix) | (y: Boolean): BooleanMatrix {

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

    // | } ------------------------------------------------------------------------------
