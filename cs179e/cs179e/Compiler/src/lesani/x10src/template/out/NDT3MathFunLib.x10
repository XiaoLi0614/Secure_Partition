
// Beginning of N-D-T3MathFunLib.x10

    // Math.pow { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def dotPower(x: Int, y: Int): Int {
        return Math.pow(x, y) as Int;
    }


    // 2. Matrices of the same size
    public static def dotPower(x: IntMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                Math.pow(x.array(p(0), p(1)), y.array(p(0), p(1))) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def dotPower(x: Int, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                Math.pow(x, y.array(p(0), p(1))) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def dotPower(x: IntMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                Math.pow(x.array(p(0), p(1)), y) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def dotPower(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // Math.pow } ------------------------------------------------------------------------------



// End of N-D-T3MathFunLib.x10
// Beginning of N-D-T3MathFunLib.x10

    // Math.min { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def min(x: Int, y: Int): Int {
        return Math.min(x, y) as Int;
    }


    // 2. Matrices of the same size
    public static def min(x: IntMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                Math.min(x.array(p(0), p(1)), y.array(p(0), p(1))) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def min(x: Int, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                Math.min(x, y.array(p(0), p(1))) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def min(x: IntMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                Math.min(x.array(p(0), p(1)), y) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def min(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // Math.min } ------------------------------------------------------------------------------



// End of N-D-T3MathFunLib.x10
// Beginning of N-D-T3MathFunLib.x10

    // Math.max { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def max(x: Int, y: Int): Int {
        return Math.max(x, y) as Int;
    }


    // 2. Matrices of the same size
    public static def max(x: IntMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                Math.max(x.array(p(0), p(1)), y.array(p(0), p(1))) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def max(x: Int, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                Math.max(x, y.array(p(0), p(1))) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def max(x: IntMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                Math.max(x.array(p(0), p(1)), y) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def max(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // Math.max } ------------------------------------------------------------------------------



// End of N-D-T3MathFunLib.x10
// Beginning of N-D-T3MathFunLib.x10

    // Math.pow { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def dotPower(x: Int, y: Double): Double {
        return Math.pow(x, y) as Double;
    }


    // 2. Matrices of the same size
    public static def dotPower(x: IntMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.pow(x.array(p(0), p(1)), y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def dotPower(x: Int, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.pow(x, y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def dotPower(x: IntMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.pow(x.array(p(0), p(1)), y) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def dotPower(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // Math.pow } ------------------------------------------------------------------------------



// End of N-D-T3MathFunLib.x10
// Beginning of N-D-T3MathFunLib.x10

    // Math.min { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def min(x: Int, y: Double): Double {
        return Math.min(x, y) as Double;
    }


    // 2. Matrices of the same size
    public static def min(x: IntMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.min(x.array(p(0), p(1)), y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def min(x: Int, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.min(x, y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def min(x: IntMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.min(x.array(p(0), p(1)), y) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def min(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // Math.min } ------------------------------------------------------------------------------



// End of N-D-T3MathFunLib.x10
// Beginning of N-D-T3MathFunLib.x10

    // Math.max { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def max(x: Int, y: Double): Double {
        return Math.max(x, y) as Double;
    }


    // 2. Matrices of the same size
    public static def max(x: IntMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.max(x.array(p(0), p(1)), y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def max(x: Int, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.max(x, y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def max(x: IntMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.max(x.array(p(0), p(1)), y) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def max(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // Math.max } ------------------------------------------------------------------------------



// End of N-D-T3MathFunLib.x10
// Beginning of N-D-T3MathFunLib.x10

    // Math.pow { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def dotPower(x: Double, y: Int): Double {
        return Math.pow(x, y) as Double;
    }


    // 2. Matrices of the same size
    public static def dotPower(x: DoubleMatrix, y: IntMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.pow(x.array(p(0), p(1)), y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def dotPower(x: Double, y: IntMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.pow(x, y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def dotPower(x: DoubleMatrix, y: Int): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.pow(x.array(p(0), p(1)), y) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def dotPower(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // Math.pow } ------------------------------------------------------------------------------



// End of N-D-T3MathFunLib.x10
// Beginning of N-D-T3MathFunLib.x10

    // Math.min { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def min(x: Double, y: Int): Double {
        return Math.min(x, y) as Double;
    }


    // 2. Matrices of the same size
    public static def min(x: DoubleMatrix, y: IntMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.min(x.array(p(0), p(1)), y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def min(x: Double, y: IntMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.min(x, y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def min(x: DoubleMatrix, y: Int): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.min(x.array(p(0), p(1)), y) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def min(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // Math.min } ------------------------------------------------------------------------------



// End of N-D-T3MathFunLib.x10
// Beginning of N-D-T3MathFunLib.x10

    // Math.max { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def max(x: Double, y: Int): Double {
        return Math.max(x, y) as Double;
    }


    // 2. Matrices of the same size
    public static def max(x: DoubleMatrix, y: IntMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.max(x.array(p(0), p(1)), y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def max(x: Double, y: IntMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.max(x, y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def max(x: DoubleMatrix, y: Int): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.max(x.array(p(0), p(1)), y) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def max(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // Math.max } ------------------------------------------------------------------------------



// End of N-D-T3MathFunLib.x10
// Beginning of N-D-T3MathFunLib.x10

    // Math.pow { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def dotPower(x: Double, y: Double): Double {
        return Math.pow(x, y) as Double;
    }


    // 2. Matrices of the same size
    public static def dotPower(x: DoubleMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.pow(x.array(p(0), p(1)), y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def dotPower(x: Double, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.pow(x, y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def dotPower(x: DoubleMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.pow(x.array(p(0), p(1)), y) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def dotPower(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // Math.pow } ------------------------------------------------------------------------------



// End of N-D-T3MathFunLib.x10
// Beginning of N-D-T3MathFunLib.x10

    // Math.min { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def min(x: Double, y: Double): Double {
        return Math.min(x, y) as Double;
    }


    // 2. Matrices of the same size
    public static def min(x: DoubleMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.min(x.array(p(0), p(1)), y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def min(x: Double, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.min(x, y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def min(x: DoubleMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.min(x.array(p(0), p(1)), y) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def min(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // Math.min } ------------------------------------------------------------------------------



// End of N-D-T3MathFunLib.x10
// Beginning of N-D-T3MathFunLib.x10

    // Math.max { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def max(x: Double, y: Double): Double {
        return Math.max(x, y) as Double;
    }


    // 2. Matrices of the same size
    public static def max(x: DoubleMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.max(x.array(p(0), p(1)), y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def max(x: Double, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.max(x, y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def max(x: DoubleMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.max(x.array(p(0), p(1)), y) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def max(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // Math.max } ------------------------------------------------------------------------------



// End of N-D-T3MathFunLib.x10