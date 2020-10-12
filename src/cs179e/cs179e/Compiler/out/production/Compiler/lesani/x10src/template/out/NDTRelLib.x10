
// Beginning of N-D-TRelLib.x10

    // == { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def eq(x: Boolean, y: Int): Boolean {
        return (x?1:0) == y;
    }

    public static def eq(x: Int, y: Boolean): Boolean {
        return x == (y?1:0);
    }

    // 2. Matrices of the same size
    public static def eq(x: BooleanMatrix, y: IntMatrix): BooleanMatrix {
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
    public static def eq(x: IntMatrix, y: BooleanMatrix): BooleanMatrix {
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
    public static def eq(x: Boolean, y: IntMatrix): BooleanMatrix {
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
    public static def eq(x: Int, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x == (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def eq(x: BooleanMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) == y
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def eq(x: IntMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) == (y?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // == } ------------------------------------------------------------------------------



// End of N-D-TRelLib.x10


// Beginning of N-D-TRelLib.x10

    // != { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def neq(x: Boolean, y: Int): Boolean {
        return (x?1:0) != y;
    }

    public static def neq(x: Int, y: Boolean): Boolean {
        return x != (y?1:0);
    }

    // 2. Matrices of the same size
    public static def neq(x: BooleanMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) != y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def neq(x: IntMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) != (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def neq(x: Boolean, y: IntMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x?1:0) != y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def neq(x: Int, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x != (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def neq(x: BooleanMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) != y
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def neq(x: IntMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) != (y?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // != } ------------------------------------------------------------------------------



// End of N-D-TRelLib.x10


// Beginning of N-D-TRelLib.x10

    // < { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def lt(x: Boolean, y: Int): Boolean {
        return (x?1:0) < y;
    }

    public static def lt(x: Int, y: Boolean): Boolean {
        return x < (y?1:0);
    }

    // 2. Matrices of the same size
    public static def lt(x: BooleanMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) < y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def lt(x: IntMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) < (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def lt(x: Boolean, y: IntMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x?1:0) < y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def lt(x: Int, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x < (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def lt(x: BooleanMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) < y
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def lt(x: IntMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) < (y?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // < } ------------------------------------------------------------------------------



// End of N-D-TRelLib.x10


// Beginning of N-D-TRelLib.x10

    // <= { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def lteq(x: Boolean, y: Int): Boolean {
        return (x?1:0) <= y;
    }

    public static def lteq(x: Int, y: Boolean): Boolean {
        return x <= (y?1:0);
    }

    // 2. Matrices of the same size
    public static def lteq(x: BooleanMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) <= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def lteq(x: IntMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) <= (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def lteq(x: Boolean, y: IntMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x?1:0) <= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def lteq(x: Int, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x <= (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def lteq(x: BooleanMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) <= y
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def lteq(x: IntMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) <= (y?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // <= } ------------------------------------------------------------------------------



// End of N-D-TRelLib.x10


// Beginning of N-D-TRelLib.x10

    // > { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def gt(x: Boolean, y: Int): Boolean {
        return (x?1:0) > y;
    }

    public static def gt(x: Int, y: Boolean): Boolean {
        return x > (y?1:0);
    }

    // 2. Matrices of the same size
    public static def gt(x: BooleanMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) > y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def gt(x: IntMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) > (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def gt(x: Boolean, y: IntMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x?1:0) > y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def gt(x: Int, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x > (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def gt(x: BooleanMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) > y
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def gt(x: IntMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) > (y?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // > } ------------------------------------------------------------------------------



// End of N-D-TRelLib.x10


// Beginning of N-D-TRelLib.x10

    // == { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def eq(x: Boolean, y: Double): Boolean {
        return (x?1:0) == y;
    }

    public static def eq(x: Double, y: Boolean): Boolean {
        return x == (y?1:0);
    }

    // 2. Matrices of the same size
    public static def eq(x: BooleanMatrix, y: DoubleMatrix): BooleanMatrix {
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
    public static def eq(x: DoubleMatrix, y: BooleanMatrix): BooleanMatrix {
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
    public static def eq(x: Boolean, y: DoubleMatrix): BooleanMatrix {
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
    public static def eq(x: Double, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x == (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def eq(x: BooleanMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) == y
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def eq(x: DoubleMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) == (y?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // == } ------------------------------------------------------------------------------



// End of N-D-TRelLib.x10


// Beginning of N-D-TRelLib.x10

    // != { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def neq(x: Boolean, y: Double): Boolean {
        return (x?1:0) != y;
    }

    public static def neq(x: Double, y: Boolean): Boolean {
        return x != (y?1:0);
    }

    // 2. Matrices of the same size
    public static def neq(x: BooleanMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) != y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def neq(x: DoubleMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) != (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def neq(x: Boolean, y: DoubleMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x?1:0) != y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def neq(x: Double, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x != (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def neq(x: BooleanMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) != y
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def neq(x: DoubleMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) != (y?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // != } ------------------------------------------------------------------------------



// End of N-D-TRelLib.x10


// Beginning of N-D-TRelLib.x10

    // < { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def lt(x: Boolean, y: Double): Boolean {
        return (x?1:0) < y;
    }

    public static def lt(x: Double, y: Boolean): Boolean {
        return x < (y?1:0);
    }

    // 2. Matrices of the same size
    public static def lt(x: BooleanMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) < y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def lt(x: DoubleMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) < (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def lt(x: Boolean, y: DoubleMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x?1:0) < y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def lt(x: Double, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x < (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def lt(x: BooleanMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) < y
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def lt(x: DoubleMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) < (y?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // < } ------------------------------------------------------------------------------



// End of N-D-TRelLib.x10


// Beginning of N-D-TRelLib.x10

    // <= { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def lteq(x: Boolean, y: Double): Boolean {
        return (x?1:0) <= y;
    }

    public static def lteq(x: Double, y: Boolean): Boolean {
        return x <= (y?1:0);
    }

    // 2. Matrices of the same size
    public static def lteq(x: BooleanMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) <= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def lteq(x: DoubleMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) <= (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def lteq(x: Boolean, y: DoubleMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x?1:0) <= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def lteq(x: Double, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x <= (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def lteq(x: BooleanMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) <= y
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def lteq(x: DoubleMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) <= (y?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // <= } ------------------------------------------------------------------------------



// End of N-D-TRelLib.x10


// Beginning of N-D-TRelLib.x10

    // > { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def gt(x: Boolean, y: Double): Boolean {
        return (x?1:0) > y;
    }

    public static def gt(x: Double, y: Boolean): Boolean {
        return x > (y?1:0);
    }

    // 2. Matrices of the same size
    public static def gt(x: BooleanMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) > y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def gt(x: DoubleMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) > (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def gt(x: Boolean, y: DoubleMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x?1:0) > y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def gt(x: Double, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x > (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def gt(x: BooleanMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) > y
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def gt(x: DoubleMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) > (y?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // > } ------------------------------------------------------------------------------



// End of N-D-TRelLib.x10

