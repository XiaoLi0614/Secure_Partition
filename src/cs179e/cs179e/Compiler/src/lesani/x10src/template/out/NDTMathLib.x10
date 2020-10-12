
// Beginning of N-D-TMathLib.x10

    // + { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def plus(x: Boolean, y: Int): Int {
        return (x?1:0) + y;
    }
    public static def plus(x: Int, y: Boolean): Int {
        return x + (y?1:0);
    }

    // 2. Matrices of the same size
    // In N-D-TMathMMLib

    // 3. For a Matrix and a primitive
    public static def plus(x: Boolean, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    xv + y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def plus(x: Int, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x + (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def plus(x: IntMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) + yv
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def plus(x: BooleanMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) + y
            )
        );

        return new IntMatrix(array, n, m);
    }

    // + } ------------------------------------------------------------------------------

// End of N-D-TMathLib.x10
// Beginning of N-D-TMathMMLib.x10

    // + { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def plus(x: BooleanMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At plus op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) +
                    y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def plus(x: IntMatrix, y: BooleanMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At plus op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) +
                    (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new IntMatrix(array, n, m);
    }

    // + } ------------------------------------------------------------------------------

// End of N-D-TMathMMLib.x10
// Beginning of N-D-TMathLib.x10

    // - { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def minus(x: Boolean, y: Int): Int {
        return (x?1:0) - y;
    }
    public static def minus(x: Int, y: Boolean): Int {
        return x - (y?1:0);
    }

    // 2. Matrices of the same size
    // In N-D-TMathMMLib

    // 3. For a Matrix and a primitive
    public static def minus(x: Boolean, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    xv - y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def minus(x: Int, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x - (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def minus(x: IntMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) - yv
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def minus(x: BooleanMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) - y
            )
        );

        return new IntMatrix(array, n, m);
    }

    // - } ------------------------------------------------------------------------------

// End of N-D-TMathLib.x10
// Beginning of N-D-TMathMMLib.x10

    // - { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def minus(x: BooleanMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At minus op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) -
                    y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def minus(x: IntMatrix, y: BooleanMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At minus op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) -
                    (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new IntMatrix(array, n, m);
    }

    // - } ------------------------------------------------------------------------------

// End of N-D-TMathMMLib.x10
// Beginning of N-D-TMathLib.x10

    // * { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def dotTimes(x: Boolean, y: Int): Int {
        return (x?1:0) * y;
    }
    public static def dotTimes(x: Int, y: Boolean): Int {
        return x * (y?1:0);
    }

    // 2. Matrices of the same size
    // In N-D-TMathMMLib

    // 3. For a Matrix and a primitive
    public static def dotTimes(x: Boolean, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    xv * y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def dotTimes(x: Int, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x * (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def dotTimes(x: IntMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) * yv
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def dotTimes(x: BooleanMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) * y
            )
        );

        return new IntMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------

// End of N-D-TMathLib.x10
// Beginning of N-D-TMathMMLib.x10

    // * { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotTimes(x: BooleanMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotTimes op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) *
                    y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def dotTimes(x: IntMatrix, y: BooleanMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotTimes op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) *
                    (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new IntMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------

// End of N-D-TMathMMLib.x10
// Beginning of N-D-TMathLib.x10

    // / { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def dotDivide(x: Boolean, y: Int): Int {
        return (x?1:0) / y;
    }
    public static def dotDivide(x: Int, y: Boolean): Int {
        return x / (y?1:0);
    }

    // 2. Matrices of the same size
    // In N-D-TMathMMLib

    // 3. For a Matrix and a primitive
    public static def dotDivide(x: Boolean, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    xv / y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def dotDivide(x: Int, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x / (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def dotDivide(x: IntMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) / yv
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def dotDivide(x: BooleanMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) / y
            )
        );

        return new IntMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------

// End of N-D-TMathLib.x10
// Beginning of N-D-TMathMMLib.x10

    // / { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotDivide(x: BooleanMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotDivide op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) /
                    y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def dotDivide(x: IntMatrix, y: BooleanMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotDivide op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) /
                    (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new IntMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------

// End of N-D-TMathMMLib.x10
// Beginning of N-D-TMathLib.x10

    // * { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def times(x: Boolean, y: Int): Int {
        return (x?1:0) * y;
    }
    public static def times(x: Int, y: Boolean): Int {
        return x * (y?1:0);
    }

    // 2. Matrices of the same size
    // In N-D-TMathMMLib

    // 3. For a Matrix and a primitive
    public static def times(x: Boolean, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    xv * y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def times(x: Int, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x * (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def times(x: IntMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) * yv
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def times(x: BooleanMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) * y
            )
        );

        return new IntMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------

// End of N-D-TMathLib.x10
// Beginning of N-D-TMathLib.x10

    // / { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def divide(x: Boolean, y: Int): Int {
        return (x?1:0) / y;
    }
    public static def divide(x: Int, y: Boolean): Int {
        return x / (y?1:0);
    }

    // 2. Matrices of the same size
    // In N-D-TMathMMLib

    // 3. For a Matrix and a primitive
    public static def divide(x: Boolean, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    xv / y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def divide(x: Int, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x / (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def divide(x: IntMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) / yv
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def divide(x: BooleanMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) / y
            )
        );

        return new IntMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------

// End of N-D-TMathLib.x10
// Beginning of N-D-TMathLib.x10

    // + { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def plus(x: Boolean, y: Double): Double {
        return (x?1:0) + y;
    }
    public static def plus(x: Double, y: Boolean): Double {
        return x + (y?1:0);
    }

    // 2. Matrices of the same size
    // In N-D-TMathMMLib

    // 3. For a Matrix and a primitive
    public static def plus(x: Boolean, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    xv + y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def plus(x: Double, y: BooleanMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x + (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def plus(x: DoubleMatrix, y: Boolean): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) + yv
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def plus(x: BooleanMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) + y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // + } ------------------------------------------------------------------------------

// End of N-D-TMathLib.x10
// Beginning of N-D-TMathMMLib.x10

    // + { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def plus(x: BooleanMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At plus op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) +
                    y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def plus(x: DoubleMatrix, y: BooleanMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At plus op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) +
                    (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // + } ------------------------------------------------------------------------------

// End of N-D-TMathMMLib.x10
// Beginning of N-D-TMathLib.x10

    // - { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def minus(x: Boolean, y: Double): Double {
        return (x?1:0) - y;
    }
    public static def minus(x: Double, y: Boolean): Double {
        return x - (y?1:0);
    }

    // 2. Matrices of the same size
    // In N-D-TMathMMLib

    // 3. For a Matrix and a primitive
    public static def minus(x: Boolean, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    xv - y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def minus(x: Double, y: BooleanMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x - (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def minus(x: DoubleMatrix, y: Boolean): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) - yv
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def minus(x: BooleanMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) - y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // - } ------------------------------------------------------------------------------

// End of N-D-TMathLib.x10
// Beginning of N-D-TMathMMLib.x10

    // - { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def minus(x: BooleanMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At minus op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) -
                    y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def minus(x: DoubleMatrix, y: BooleanMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At minus op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) -
                    (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // - } ------------------------------------------------------------------------------

// End of N-D-TMathMMLib.x10
// Beginning of N-D-TMathLib.x10

    // * { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def dotTimes(x: Boolean, y: Double): Double {
        return (x?1:0) * y;
    }
    public static def dotTimes(x: Double, y: Boolean): Double {
        return x * (y?1:0);
    }

    // 2. Matrices of the same size
    // In N-D-TMathMMLib

    // 3. For a Matrix and a primitive
    public static def dotTimes(x: Boolean, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    xv * y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def dotTimes(x: Double, y: BooleanMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x * (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def dotTimes(x: DoubleMatrix, y: Boolean): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) * yv
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def dotTimes(x: BooleanMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) * y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------

// End of N-D-TMathLib.x10
// Beginning of N-D-TMathMMLib.x10

    // * { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotTimes(x: BooleanMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotTimes op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) *
                    y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def dotTimes(x: DoubleMatrix, y: BooleanMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotTimes op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) *
                    (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------

// End of N-D-TMathMMLib.x10
// Beginning of N-D-TMathLib.x10

    // / { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def dotDivide(x: Boolean, y: Double): Double {
        return (x?1:0) / y;
    }
    public static def dotDivide(x: Double, y: Boolean): Double {
        return x / (y?1:0);
    }

    // 2. Matrices of the same size
    // In N-D-TMathMMLib

    // 3. For a Matrix and a primitive
    public static def dotDivide(x: Boolean, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    xv / y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def dotDivide(x: Double, y: BooleanMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x / (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def dotDivide(x: DoubleMatrix, y: Boolean): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) / yv
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def dotDivide(x: BooleanMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) / y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------

// End of N-D-TMathLib.x10
// Beginning of N-D-TMathMMLib.x10

    // / { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotDivide(x: BooleanMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotDivide op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) /
                    y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def dotDivide(x: DoubleMatrix, y: BooleanMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotDivide op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) /
                    (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------

// End of N-D-TMathMMLib.x10
// Beginning of N-D-TMathLib.x10

    // * { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def times(x: Boolean, y: Double): Double {
        return (x?1:0) * y;
    }
    public static def times(x: Double, y: Boolean): Double {
        return x * (y?1:0);
    }

    // 2. Matrices of the same size
    // In N-D-TMathMMLib

    // 3. For a Matrix and a primitive
    public static def times(x: Boolean, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    xv * y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def times(x: Double, y: BooleanMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x * (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def times(x: DoubleMatrix, y: Boolean): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) * yv
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def times(x: BooleanMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) * y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------

// End of N-D-TMathLib.x10
// Beginning of N-D-TMathLib.x10

    // / { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def divide(x: Boolean, y: Double): Double {
        return (x?1:0) / y;
    }
    public static def divide(x: Double, y: Boolean): Double {
        return x / (y?1:0);
    }

    // 2. Matrices of the same size
    // In N-D-TMathMMLib

    // 3. For a Matrix and a primitive
    public static def divide(x: Boolean, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    xv / y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def divide(x: Double, y: BooleanMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x / (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def divide(x: DoubleMatrix, y: Boolean): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) / yv
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def divide(x: BooleanMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) / y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------

// End of N-D-TMathLib.x10