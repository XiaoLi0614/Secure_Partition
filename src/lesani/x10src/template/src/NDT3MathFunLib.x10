
// Beginning of N-D-T3MathFunLib.x10

    // FUN { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def NAME(x: NDT1, y: NDT2): NDT3 {
        return FUN(x, y) as NDT3;
    }


    // 2. Matrices of the same size
    public static def NAME(x: NDT1Matrix, y: NDT2Matrix): NDT3Matrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT3](
            r,
            (
                (p:Point(r.rank))=>
                FUN(x.array(p(0), p(1)), y.array(p(0), p(1))) as NDT3
            )
        );

        return new NDT3Matrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def NAME(x: NDT1, y: NDT2Matrix): NDT3Matrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT3](
            r,
            (
                (p:Point(r.rank))=>
                FUN(x, y.array(p(0), p(1))) as NDT3
            )
        );

        return new NDT3Matrix(array, n, m);
    }

    public static def NAME(x: NDT1Matrix, y: NDT2): NDT3Matrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT3](
            r,
            (
                (p:Point(r.rank))=>
                FUN(x.array(p(0), p(1)), y) as NDT3
            )
        );

        return new NDT3Matrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def NAME(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // FUN } ------------------------------------------------------------------------------



// End of N-D-T3MathFunLib.x10