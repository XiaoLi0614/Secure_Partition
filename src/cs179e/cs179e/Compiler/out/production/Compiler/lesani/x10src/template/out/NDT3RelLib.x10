
// Beginning of N-D-T3RelLib.x10

    // == { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def eq(x: IntMatrix, y: IntMatrix): BooleanMatrix {
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
    public static def eq(x: Int, y: IntMatrix): BooleanMatrix {
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

    public static def eq(x: IntMatrix, y: Int): BooleanMatrix {
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

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // != { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def neq(x: IntMatrix, y: IntMatrix): BooleanMatrix {
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
    public static def neq(x: Int, y: IntMatrix): BooleanMatrix {
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

    public static def neq(x: IntMatrix, y: Int): BooleanMatrix {
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

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // < { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def lt(x: IntMatrix, y: IntMatrix): BooleanMatrix {
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
    public static def lt(x: Int, y: IntMatrix): BooleanMatrix {
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

    public static def lt(x: IntMatrix, y: Int): BooleanMatrix {
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

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // <= { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def lteq(x: IntMatrix, y: IntMatrix): BooleanMatrix {
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
    public static def lteq(x: Int, y: IntMatrix): BooleanMatrix {
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

    public static def lteq(x: IntMatrix, y: Int): BooleanMatrix {
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

    // <= } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // > { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def gt(x: IntMatrix, y: IntMatrix): BooleanMatrix {
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
    public static def gt(x: Int, y: IntMatrix): BooleanMatrix {
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

    public static def gt(x: IntMatrix, y: Int): BooleanMatrix {
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

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // >= { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def gteq(x: IntMatrix, y: IntMatrix): BooleanMatrix {
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
    public static def gteq(x: Int, y: IntMatrix): BooleanMatrix {
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

    public static def gteq(x: IntMatrix, y: Int): BooleanMatrix {
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

    // >= } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // == { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def eq(x: IntMatrix, y: DoubleMatrix): BooleanMatrix {
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
    public static def eq(x: Int, y: DoubleMatrix): BooleanMatrix {
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

    public static def eq(x: IntMatrix, y: Double): BooleanMatrix {
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

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // != { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def neq(x: IntMatrix, y: DoubleMatrix): BooleanMatrix {
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
    public static def neq(x: Int, y: DoubleMatrix): BooleanMatrix {
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

    public static def neq(x: IntMatrix, y: Double): BooleanMatrix {
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

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // < { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def lt(x: IntMatrix, y: DoubleMatrix): BooleanMatrix {
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
    public static def lt(x: Int, y: DoubleMatrix): BooleanMatrix {
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

    public static def lt(x: IntMatrix, y: Double): BooleanMatrix {
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

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // <= { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def lteq(x: IntMatrix, y: DoubleMatrix): BooleanMatrix {
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
    public static def lteq(x: Int, y: DoubleMatrix): BooleanMatrix {
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

    public static def lteq(x: IntMatrix, y: Double): BooleanMatrix {
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

    // <= } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // >= { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def gt(x: IntMatrix, y: DoubleMatrix): BooleanMatrix {
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
    public static def gt(x: Int, y: DoubleMatrix): BooleanMatrix {
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

    public static def gt(x: IntMatrix, y: Double): BooleanMatrix {
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

    // >= } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // > { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def gteq(x: IntMatrix, y: DoubleMatrix): BooleanMatrix {
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
    public static def gteq(x: Int, y: DoubleMatrix): BooleanMatrix {
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

    public static def gteq(x: IntMatrix, y: Double): BooleanMatrix {
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

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // == { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def eq(x: DoubleMatrix, y: IntMatrix): BooleanMatrix {
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
    public static def eq(x: Double, y: IntMatrix): BooleanMatrix {
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

    public static def eq(x: DoubleMatrix, y: Int): BooleanMatrix {
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

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // != { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def neq(x: DoubleMatrix, y: IntMatrix): BooleanMatrix {
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
    public static def neq(x: Double, y: IntMatrix): BooleanMatrix {
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

    public static def neq(x: DoubleMatrix, y: Int): BooleanMatrix {
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

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // < { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def lt(x: DoubleMatrix, y: IntMatrix): BooleanMatrix {
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
    public static def lt(x: Double, y: IntMatrix): BooleanMatrix {
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

    public static def lt(x: DoubleMatrix, y: Int): BooleanMatrix {
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

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // <= { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def lteq(x: DoubleMatrix, y: IntMatrix): BooleanMatrix {
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
    public static def lteq(x: Double, y: IntMatrix): BooleanMatrix {
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

    public static def lteq(x: DoubleMatrix, y: Int): BooleanMatrix {
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

    // <= } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // >= { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def gt(x: DoubleMatrix, y: IntMatrix): BooleanMatrix {
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
    public static def gt(x: Double, y: IntMatrix): BooleanMatrix {
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

    public static def gt(x: DoubleMatrix, y: Int): BooleanMatrix {
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

    // >= } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // > { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def gteq(x: DoubleMatrix, y: IntMatrix): BooleanMatrix {
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
    public static def gteq(x: Double, y: IntMatrix): BooleanMatrix {
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

    public static def gteq(x: DoubleMatrix, y: Int): BooleanMatrix {
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

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // == { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def eq(x: DoubleMatrix, y: DoubleMatrix): BooleanMatrix {
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
    public static def eq(x: Double, y: DoubleMatrix): BooleanMatrix {
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

    public static def eq(x: DoubleMatrix, y: Double): BooleanMatrix {
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

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // != { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def neq(x: DoubleMatrix, y: DoubleMatrix): BooleanMatrix {
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
    public static def neq(x: Double, y: DoubleMatrix): BooleanMatrix {
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

    public static def neq(x: DoubleMatrix, y: Double): BooleanMatrix {
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

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // < { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def lt(x: DoubleMatrix, y: DoubleMatrix): BooleanMatrix {
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
    public static def lt(x: Double, y: DoubleMatrix): BooleanMatrix {
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

    public static def lt(x: DoubleMatrix, y: Double): BooleanMatrix {
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

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // <= { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def lteq(x: DoubleMatrix, y: DoubleMatrix): BooleanMatrix {
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
    public static def lteq(x: Double, y: DoubleMatrix): BooleanMatrix {
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

    public static def lteq(x: DoubleMatrix, y: Double): BooleanMatrix {
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

    // <= } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // > { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def gt(x: DoubleMatrix, y: DoubleMatrix): BooleanMatrix {
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
    public static def gt(x: Double, y: DoubleMatrix): BooleanMatrix {
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

    public static def gt(x: DoubleMatrix, y: Double): BooleanMatrix {
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

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // >= { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def gteq(x: DoubleMatrix, y: DoubleMatrix): BooleanMatrix {
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
    public static def gteq(x: Double, y: DoubleMatrix): BooleanMatrix {
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

    public static def gteq(x: DoubleMatrix, y: Double): BooleanMatrix {
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

    // >= } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10