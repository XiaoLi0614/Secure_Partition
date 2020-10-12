
// Beginning of N-D-T3MathLib.x10

    // + { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def plus(x: Int, y: Int): Int {
        return x + y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def plus(x: Int, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x + y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def plus(x: IntMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) + y
            )
        );

        return new IntMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def plus(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // + } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // + { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def plus(x: IntMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At plus op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) + y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }

    // + } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // - { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def minus(x: Int, y: Int): Int {
        return x - y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def minus(x: Int, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x - y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def minus(x: IntMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) - y
            )
        );

        return new IntMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def minus(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // - } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // - { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def minus(x: IntMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At minus op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) - y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }

    // - } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // * { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def dotTimes(x: Int, y: Int): Int {
        return x * y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def dotTimes(x: Int, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x * y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def dotTimes(x: IntMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) * y
            )
        );

        return new IntMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def dotTimes(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // * } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // * { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotTimes(x: IntMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotTimes op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) * y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // / { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def dotDivide(x: Int, y: Int): Int {
        return x / y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def dotDivide(x: Int, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x / y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def dotDivide(x: IntMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) / y
            )
        );

        return new IntMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def dotDivide(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // / } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // / { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotDivide(x: IntMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotDivide op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) / y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // * { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def times(x: Int, y: Int): Int {
        return x * y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def times(x: Int, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x * y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def times(x: IntMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) * y
            )
        );

        return new IntMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def times(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // * } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathLib.x10

    // as Double / { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def divide(x: Int, y: Int): Double {
        return x as Double / y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def divide(x: Int, y: IntMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x as Double / y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def divide(x: IntMatrix, y: Int): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) as Double / y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def divide(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // as Double / } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathLib.x10

    // + { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def plus(x: Int, y: Double): Double {
        return x + y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def plus(x: Int, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x + y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def plus(x: IntMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) + y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def plus(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // + } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // + { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def plus(x: IntMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At plus op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) + y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // + } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // - { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def minus(x: Int, y: Double): Double {
        return x - y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def minus(x: Int, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x - y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def minus(x: IntMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) - y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def minus(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // - } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // - { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def minus(x: IntMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At minus op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) - y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // - } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // * { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def dotTimes(x: Int, y: Double): Double {
        return x * y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def dotTimes(x: Int, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x * y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def dotTimes(x: IntMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) * y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def dotTimes(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // * } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // * { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotTimes(x: IntMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotTimes op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) * y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // / { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def dotDivide(x: Int, y: Double): Double {
        return x / y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def dotDivide(x: Int, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x / y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def dotDivide(x: IntMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) / y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def dotDivide(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // / } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // / { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotDivide(x: IntMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotDivide op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) / y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // * { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def times(x: Int, y: Double): Double {
        return x * y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def times(x: Int, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x * y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def times(x: IntMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) * y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def times(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // * } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathLib.x10

    // / { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def divide(x: Int, y: Double): Double {
        return x / y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def divide(x: Int, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x / y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def divide(x: IntMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) / y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def divide(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // / } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathLib.x10

    // + { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def plus(x: Double, y: Int): Double {
        return x + y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def plus(x: Double, y: IntMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x + y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def plus(x: DoubleMatrix, y: Int): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) + y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def plus(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // + } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // + { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def plus(x: DoubleMatrix, y: IntMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At plus op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) + y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // + } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // - { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def minus(x: Double, y: Int): Double {
        return x - y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def minus(x: Double, y: IntMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x - y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def minus(x: DoubleMatrix, y: Int): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) - y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def minus(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // - } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // - { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def minus(x: DoubleMatrix, y: IntMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At minus op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) - y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // - } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // * { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def dotTimes(x: Double, y: Int): Double {
        return x * y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def dotTimes(x: Double, y: IntMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x * y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def dotTimes(x: DoubleMatrix, y: Int): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) * y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def dotTimes(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // * } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // * { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotTimes(x: DoubleMatrix, y: IntMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotTimes op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) * y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // / { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def dotDivide(x: Double, y: Int): Double {
        return x / y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def dotDivide(x: Double, y: IntMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x / y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def dotDivide(x: DoubleMatrix, y: Int): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) / y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def dotDivide(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // / } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // / { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotDivide(x: DoubleMatrix, y: IntMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotDivide op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) / y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // * { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def times(x: Double, y: Int): Double {
        return x * y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def times(x: Double, y: IntMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x * y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def times(x: DoubleMatrix, y: Int): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) * y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def times(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // * } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathLib.x10

    // / { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def divide(x: Double, y: Int): Double {
        return x / y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def divide(x: Double, y: IntMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x / y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def divide(x: DoubleMatrix, y: Int): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) / y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def divide(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // / } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathLib.x10

    // + { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def plus(x: Double, y: Double): Double {
        return x + y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def plus(x: Double, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x + y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def plus(x: DoubleMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) + y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def plus(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // + } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // + { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def plus(x: DoubleMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At plus op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) + y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // + } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // - { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def minus(x: Double, y: Double): Double {
        return x - y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def minus(x: Double, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x - y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def minus(x: DoubleMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) - y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def minus(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // - } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // - { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def minus(x: DoubleMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At minus op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) - y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // - } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // * { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def dotTimes(x: Double, y: Double): Double {
        return x * y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def dotTimes(x: Double, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x * y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def dotTimes(x: DoubleMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) * y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def dotTimes(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // * } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // * { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotTimes(x: DoubleMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotTimes op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) * y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // / { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def dotDivide(x: Double, y: Double): Double {
        return x / y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def dotDivide(x: Double, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x / y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def dotDivide(x: DoubleMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) / y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def dotDivide(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // / } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // / { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotDivide(x: DoubleMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotDivide op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) / y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // * { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def times(x: Double, y: Double): Double {
        return x * y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def times(x: Double, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x * y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def times(x: DoubleMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) * y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def times(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // * } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathLib.x10

    // / { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def divide(x: Double, y: Double): Double {
        return x / y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def divide(x: Double, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x / y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def divide(x: DoubleMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) / y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def divide(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // / } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10
