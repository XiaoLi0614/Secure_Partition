
// Beginning of N-D-T3Lib.x10

    // * { ------------------------------------------------------------------------------
    public static def times(a: NDT1Matrix, b: NDT2Matrix): NDT3Matrix {
        val rowsA = a.n;
        val columnsA = a.m;
        val rowsB = b.n;
        val columnsB = b.m;

        if (columnsA != rowsB)
            error("At matrix multiplication: Dimension mismatch." +
                  " (" + a.n + ", " + a.m + ") and (" + b.n + ", " + b.m + ")");

        val r = (0..rowsA-1)*(0..columnsB-1);
        val array = new Array[NDT3](
            r, (
                (p:Point(r.rank))=> {
                    val i = p(0);
                    val j = p(1);
                    var sum: NDT3 = 0;
                    for (var k: Int = 0; k < columnsA; k++)
                       sum = sum + a.array(i, k) * b.array(k, j);

                    return sum;
                }
            )
        );

        return new NDT3Matrix(array, rowsA, columnsB);
    }
    // * } ------------------------------------------------------------------------------

    // / { ------------------------------------------------------------------------------
    public static def backDivide(a: NDT1Matrix, b: NDT2Matrix): DoubleMatrix {
        return divide(a.transpose(), b.transpose()).transpose();
    }

    public static def divide(a: NDT1Matrix, b: NDT2Matrix): DoubleMatrix {
        if (a.m != b.m)
            error("At matrix / op: Mismatch of Operand Dimensions.");

        val biArray = invert(b.array);
        val bi = new DoubleMatrix(biArray, 1);
        return times(a, bi);
    }

    // / } ------------------------------------------------------------------------------

// End of N-D-T3Lib.x10

