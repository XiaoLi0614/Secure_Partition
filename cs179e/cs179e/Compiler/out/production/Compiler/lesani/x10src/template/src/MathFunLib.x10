// Beginning of MathFunLib

    // FUN { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def NAME(x: Boolean, y: Boolean): Int {
        val xv = x ? 1 : 0;
        val yv = x ? 1 : 0;
        return FUN(xv, yv) as Int;
    }

    // 2. Matrices of the same size
    public static def NAME(x: BooleanMatrix, y: BooleanMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val xv = (x.array(p(0), p(1))) ? 1 : 0;
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return FUN(xv, yv) as Int;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def NAME(x: Boolean, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv = x ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return FUN(xv, yv) as Int;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def NAME(x: BooleanMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv = y ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                    (p:Point(r.rank))=> {
                        val xv = (x.array(p(0), p(1))) ? 1 : 0;
                        return FUN(xv, yv) as Int;
                    }
            )
        );

        return new IntMatrix(array, n, m);
    }

    // MATH } ------------------------------------------------------------------------------

// End of MathFunLib

