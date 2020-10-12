
// Beginning of N-D-T3Lib.x10

    // * { ------------------------------------------------------------------------------
    public static def times(a: IntMatrix, b: IntMatrix): IntMatrix {
        val rowsA = a.n;
        val columnsA = a.m;
        val rowsB = b.n;
        val columnsB = b.m;

        if (columnsA != rowsB)
            error("At matrix multiplication: Dimension mismatch." +
                  " (" + a.n + ", " + a.m + ") and (" + b.n + ", " + b.m + ")");

        val r = (0..rowsA-1)*(0..columnsB-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val i = p(0);
                    val j = p(1);
                    var sum: Int = 0;
                    for (var k: Int = 0; k < columnsA; k++)
                       sum = sum + a.array(i, k) * b.array(k, j);

                    return sum;
                }
            )
        );

        return new IntMatrix(array, rowsA, columnsB);
    }
    // * } ------------------------------------------------------------------------------

    // / { ------------------------------------------------------------------------------
    public static def backDivide(a: IntMatrix, b: IntMatrix): DoubleMatrix {
        return divide(a.transpose(), b.transpose()).transpose();
    }

    public static def divide(a: IntMatrix, b: IntMatrix): DoubleMatrix {
        if (a.m != b.m)
            error("At matrix / op: Mismatch of Operand Dimensions.");

        val biArray = invert(b.array);
        val bi = new DoubleMatrix(biArray, 1);
        return times(a, bi);
    }

    // / } ------------------------------------------------------------------------------

// End of N-D-T3Lib.x10


// Beginning of N-D-T3Lib.x10

    // * { ------------------------------------------------------------------------------
    public static def times(a: IntMatrix, b: DoubleMatrix): DoubleMatrix {
        val rowsA = a.n;
        val columnsA = a.m;
        val rowsB = b.n;
        val columnsB = b.m;

        if (columnsA != rowsB)
            error("At matrix multiplication: Dimension mismatch." +
                  " (" + a.n + ", " + a.m + ") and (" + b.n + ", " + b.m + ")");

        val r = (0..rowsA-1)*(0..columnsB-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=> {
                    val i = p(0);
                    val j = p(1);
                    var sum: Double = 0;
                    for (var k: Int = 0; k < columnsA; k++)
                       sum = sum + a.array(i, k) * b.array(k, j);

                    return sum;
                }
            )
        );

        return new DoubleMatrix(array, rowsA, columnsB);
    }
    // * } ------------------------------------------------------------------------------

    // / { ------------------------------------------------------------------------------
    public static def backDivide(a: IntMatrix, b: DoubleMatrix): DoubleMatrix {
        return divide(a.transpose(), b.transpose()).transpose();
    }

    public static def divide(a: IntMatrix, b: DoubleMatrix): DoubleMatrix {
        if (a.m != b.m)
            error("At matrix / op: Mismatch of Operand Dimensions.");

        val biArray = invert(b.array);
        val bi = new DoubleMatrix(biArray, 1);
        return times(a, bi);
    }

    // / } ------------------------------------------------------------------------------

// End of N-D-T3Lib.x10


// Beginning of N-D-T3Lib.x10

    // * { ------------------------------------------------------------------------------
    public static def times(a: DoubleMatrix, b: IntMatrix): DoubleMatrix {
        val rowsA = a.n;
        val columnsA = a.m;
        val rowsB = b.n;
        val columnsB = b.m;

        if (columnsA != rowsB)
            error("At matrix multiplication: Dimension mismatch." +
                  " (" + a.n + ", " + a.m + ") and (" + b.n + ", " + b.m + ")");

        val r = (0..rowsA-1)*(0..columnsB-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=> {
                    val i = p(0);
                    val j = p(1);
                    var sum: Double = 0;
                    for (var k: Int = 0; k < columnsA; k++)
                       sum = sum + a.array(i, k) * b.array(k, j);

                    return sum;
                }
            )
        );

        return new DoubleMatrix(array, rowsA, columnsB);
    }
    // * } ------------------------------------------------------------------------------

    // / { ------------------------------------------------------------------------------
    public static def backDivide(a: DoubleMatrix, b: IntMatrix): DoubleMatrix {
        return divide(a.transpose(), b.transpose()).transpose();
    }

    public static def divide(a: DoubleMatrix, b: IntMatrix): DoubleMatrix {
        if (a.m != b.m)
            error("At matrix / op: Mismatch of Operand Dimensions.");

        val biArray = invert(b.array);
        val bi = new DoubleMatrix(biArray, 1);
        return times(a, bi);
    }

    // / } ------------------------------------------------------------------------------

// End of N-D-T3Lib.x10


// Beginning of N-D-T3Lib.x10

    // * { ------------------------------------------------------------------------------
    public static def times(a: DoubleMatrix, b: DoubleMatrix): DoubleMatrix {
        val rowsA = a.n;
        val columnsA = a.m;
        val rowsB = b.n;
        val columnsB = b.m;

        if (columnsA != rowsB)
            error("At matrix multiplication: Dimension mismatch." +
                  " (" + a.n + ", " + a.m + ") and (" + b.n + ", " + b.m + ")");

        val r = (0..rowsA-1)*(0..columnsB-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=> {
                    val i = p(0);
                    val j = p(1);
                    var sum: Double = 0;
                    for (var k: Int = 0; k < columnsA; k++)
                       sum = sum + a.array(i, k) * b.array(k, j);

                    return sum;
                }
            )
        );

        return new DoubleMatrix(array, rowsA, columnsB);
    }
    // * } ------------------------------------------------------------------------------

    // / { ------------------------------------------------------------------------------
    public static def backDivide(a: DoubleMatrix, b: DoubleMatrix): DoubleMatrix {
        return divide(a.transpose(), b.transpose()).transpose();
    }

    public static def divide(a: DoubleMatrix, b: DoubleMatrix): DoubleMatrix {
        if (a.m != b.m)
            error("At matrix / op: Mismatch of Operand Dimensions.");

        val biArray = invert(b.array);
        val bi = new DoubleMatrix(biArray, 1);
        return times(a, bi);
    }

    // / } ------------------------------------------------------------------------------

// End of N-D-T3Lib.x10

