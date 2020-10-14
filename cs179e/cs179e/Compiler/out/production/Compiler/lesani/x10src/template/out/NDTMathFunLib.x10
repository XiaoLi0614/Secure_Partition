
// Beginning of N-D-TMathFunLib.x10

    // MATH { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def dotPower(x: Boolean, y: Int): Int {
        return Math.pow((x?1:0), y) as Int;
    }
    public static def dotPower(x: Int, y: Boolean): Int {
        return Math.pow(x, (y?1:0)) as Int;
    }

    // 2. Matrices of the same size
    public static def dotPower(x: BooleanMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotPower op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.pow(
                        (x.array(p(0), p(1)) ? 1 : 0),
                        y.array(p(0), p(1))
                    ) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def dotPower(x: IntMatrix, y: BooleanMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotPower op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.pow(
                        x.array(p(0), p(1)),
                        (y.array(p(0), p(1)) ? 1 : 0)
                    ) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def dotPower(x: Boolean, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.pow(xv, y.array(p(0), p(1))) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def dotPower(x: IntMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.pow(x.array(p(0), p(1)), yv) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }

    // MATH } ------------------------------------------------------------------------------

// End of N-D-TMathFunLib.x10

// Beginning of N-D-TMathFunLib.x10

    // MATH { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def min(x: Boolean, y: Int): Int {
        return Math.min((x?1:0), y) as Int;
    }
    public static def min(x: Int, y: Boolean): Int {
        return Math.min(x, (y?1:0)) as Int;
    }

    // 2. Matrices of the same size
    public static def min(x: BooleanMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At min op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.min(
                        (x.array(p(0), p(1)) ? 1 : 0),
                        y.array(p(0), p(1))
                    ) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def min(x: IntMatrix, y: BooleanMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At min op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.min(
                        x.array(p(0), p(1)),
                        (y.array(p(0), p(1)) ? 1 : 0)
                    ) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def min(x: Boolean, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.min(xv, y.array(p(0), p(1))) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def min(x: IntMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.min(x.array(p(0), p(1)), yv) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }

    // MATH } ------------------------------------------------------------------------------

// End of N-D-TMathFunLib.x10

// Beginning of N-D-TMathFunLib.x10

    // MATH { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def max(x: Boolean, y: Int): Int {
        return Math.max((x?1:0), y) as Int;
    }
    public static def max(x: Int, y: Boolean): Int {
        return Math.max(x, (y?1:0)) as Int;
    }

    // 2. Matrices of the same size
    public static def max(x: BooleanMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At max op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.max(
                        (x.array(p(0), p(1)) ? 1 : 0),
                        y.array(p(0), p(1))
                    ) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def max(x: IntMatrix, y: BooleanMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At max op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.max(
                        x.array(p(0), p(1)),
                        (y.array(p(0), p(1)) ? 1 : 0)
                    ) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def max(x: Boolean, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.max(xv, y.array(p(0), p(1))) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def max(x: IntMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.max(x.array(p(0), p(1)), yv) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }

    // MATH } ------------------------------------------------------------------------------

// End of N-D-TMathFunLib.x10

// Beginning of N-D-TMathFunLib.x10

    // MATH { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def dotPower(x: Boolean, y: Double): Double {
        return Math.pow((x?1:0), y) as Double;
    }
    public static def dotPower(x: Double, y: Boolean): Double {
        return Math.pow(x, (y?1:0)) as Double;
    }

    // 2. Matrices of the same size
    public static def dotPower(x: BooleanMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotPower op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.pow(
                        (x.array(p(0), p(1)) ? 1 : 0),
                        y.array(p(0), p(1))
                    ) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def dotPower(x: DoubleMatrix, y: BooleanMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotPower op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.pow(
                        x.array(p(0), p(1)),
                        (y.array(p(0), p(1)) ? 1 : 0)
                    ) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def dotPower(x: Boolean, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.pow(xv, y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def dotPower(x: DoubleMatrix, y: Boolean): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.pow(x.array(p(0), p(1)), yv) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // MATH } ------------------------------------------------------------------------------

// End of N-D-TMathFunLib.x10

// Beginning of N-D-TMathFunLib.x10

    // MATH { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def min(x: Boolean, y: Double): Double {
        return Math.min((x?1:0), y) as Double;
    }
    public static def min(x: Double, y: Boolean): Double {
        return Math.min(x, (y?1:0)) as Double;
    }

    // 2. Matrices of the same size
    public static def min(x: BooleanMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At min op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.min(
                        (x.array(p(0), p(1)) ? 1 : 0),
                        y.array(p(0), p(1))
                    ) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def min(x: DoubleMatrix, y: BooleanMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At min op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.min(
                        x.array(p(0), p(1)),
                        (y.array(p(0), p(1)) ? 1 : 0)
                    ) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def min(x: Boolean, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.min(xv, y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def min(x: DoubleMatrix, y: Boolean): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.min(x.array(p(0), p(1)), yv) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // MATH } ------------------------------------------------------------------------------

// End of N-D-TMathFunLib.x10

// Beginning of N-D-TMathFunLib.x10

    // MATH { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def max(x: Boolean, y: Double): Double {
        return Math.max((x?1:0), y) as Double;
    }
    public static def max(x: Double, y: Boolean): Double {
        return Math.max(x, (y?1:0)) as Double;
    }

    // 2. Matrices of the same size
    public static def max(x: BooleanMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At max op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.max(
                        (x.array(p(0), p(1)) ? 1 : 0),
                        y.array(p(0), p(1))
                    ) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def max(x: DoubleMatrix, y: BooleanMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At max op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.max(
                        x.array(p(0), p(1)),
                        (y.array(p(0), p(1)) ? 1 : 0)
                    ) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def max(x: Boolean, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.max(xv, y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def max(x: DoubleMatrix, y: Boolean): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.max(x.array(p(0), p(1)), yv) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // MATH } ------------------------------------------------------------------------------

// End of N-D-TMathFunLib.x10
