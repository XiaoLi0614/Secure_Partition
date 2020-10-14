// Beginning of MathLib

    // + { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def plus(x: Boolean, y: Boolean): Int {
        val xv = x ? 1 : 0;
        val yv = x ? 1 : 0;
        return xv + yv;
    }

    // 2. Matrices of the same size
    // In MathMMLib

    // 3. For a Matrix and a primitive
    public static def plus(x: Boolean, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv = x ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return xv + yv;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def plus(x: BooleanMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv = y ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                    (p:Point(r.rank))=> {
                        val xv = (x.array(p(0), p(1))) ? 1 : 0;
                        return xv + yv;
                    }
            )
        );

        return new IntMatrix(array, n, m);
    }

    // + } ------------------------------------------------------------------------------

// End of MathLib// Beginning of MathMMLib

    // + { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def plus(x: BooleanMatrix, y: BooleanMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At plus op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val xv = (x.array(p(0), p(1))) ? 1 : 0;
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return xv + yv;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }

    // + } ------------------------------------------------------------------------------

// End of MathMMLib// Beginning of MathLib

    // - { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def minus(x: Boolean, y: Boolean): Int {
        val xv = x ? 1 : 0;
        val yv = x ? 1 : 0;
        return xv - yv;
    }

    // 2. Matrices of the same size
    // In MathMMLib

    // 3. For a Matrix and a primitive
    public static def minus(x: Boolean, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv = x ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return xv - yv;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def minus(x: BooleanMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv = y ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                    (p:Point(r.rank))=> {
                        val xv = (x.array(p(0), p(1))) ? 1 : 0;
                        return xv - yv;
                    }
            )
        );

        return new IntMatrix(array, n, m);
    }

    // - } ------------------------------------------------------------------------------

// End of MathLib// Beginning of MathMMLib

    // - { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def minus(x: BooleanMatrix, y: BooleanMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At minus op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val xv = (x.array(p(0), p(1))) ? 1 : 0;
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return xv - yv;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }

    // - } ------------------------------------------------------------------------------

// End of MathMMLib// Beginning of MathLib

    // * { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def dotTimes(x: Boolean, y: Boolean): Int {
        val xv = x ? 1 : 0;
        val yv = x ? 1 : 0;
        return xv * yv;
    }

    // 2. Matrices of the same size
    // In MathMMLib

    // 3. For a Matrix and a primitive
    public static def dotTimes(x: Boolean, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv = x ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return xv * yv;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def dotTimes(x: BooleanMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv = y ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                    (p:Point(r.rank))=> {
                        val xv = (x.array(p(0), p(1))) ? 1 : 0;
                        return xv * yv;
                    }
            )
        );

        return new IntMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------

// End of MathLib// Beginning of MathMMLib

    // * { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotTimes(x: BooleanMatrix, y: BooleanMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotTimes op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val xv = (x.array(p(0), p(1))) ? 1 : 0;
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return xv * yv;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------

// End of MathMMLib// Beginning of MathLib

    // / { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def dotDivide(x: Boolean, y: Boolean): Int {
        val xv = x ? 1 : 0;
        val yv = x ? 1 : 0;
        return xv / yv;
    }

    // 2. Matrices of the same size
    // In MathMMLib

    // 3. For a Matrix and a primitive
    public static def dotDivide(x: Boolean, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv = x ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return xv / yv;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def dotDivide(x: BooleanMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv = y ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                    (p:Point(r.rank))=> {
                        val xv = (x.array(p(0), p(1))) ? 1 : 0;
                        return xv / yv;
                    }
            )
        );

        return new IntMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------

// End of MathLib// Beginning of MathMMLib

    // / { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotDivide(x: BooleanMatrix, y: BooleanMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotDivide op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val xv = (x.array(p(0), p(1))) ? 1 : 0;
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return xv / yv;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------

// End of MathMMLib// Beginning of MathLib

    // * { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def times(x: Boolean, y: Boolean): Int {
        val xv = x ? 1 : 0;
        val yv = x ? 1 : 0;
        return xv * yv;
    }

    // 2. Matrices of the same size
    // In MathMMLib

    // 3. For a Matrix and a primitive
    public static def times(x: Boolean, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv = x ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return xv * yv;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def times(x: BooleanMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv = y ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                    (p:Point(r.rank))=> {
                        val xv = (x.array(p(0), p(1))) ? 1 : 0;
                        return xv * yv;
                    }
            )
        );

        return new IntMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------

// End of MathLib// Beginning of MathLib

    // / { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def divide(x: Boolean, y: Boolean): Int {
        val xv = x ? 1 : 0;
        val yv = x ? 1 : 0;
        return xv / yv;
    }

    // 2. Matrices of the same size
    // In MathMMLib

    // 3. For a Matrix and a primitive
    public static def divide(x: Boolean, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv = x ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return xv / yv;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def divide(x: BooleanMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv = y ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                    (p:Point(r.rank))=> {
                        val xv = (x.array(p(0), p(1))) ? 1 : 0;
                        return xv / yv;
                    }
            )
        );

        return new IntMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------

// End of MathLib