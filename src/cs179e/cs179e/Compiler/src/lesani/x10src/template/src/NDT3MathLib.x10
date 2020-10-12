
// Beginning of N-D-T3MathLib.x10

    // MATH { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def NAME(x: NDT1, y: NDT2): NDT3 {
        return x MATH y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def NAME(x: NDT1, y: NDT2Matrix): NDT3Matrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT3](
            r,
            (
                (p:Point(r.rank))=>
                x MATH y.array(p(0), p(1))
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
                x.array(p(0), p(1)) MATH y
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

    // MATH } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10
