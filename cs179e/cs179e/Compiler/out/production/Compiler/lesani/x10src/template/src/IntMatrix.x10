


// Beginning of IntMatrix

    // Range Vector Creation { ---------------------------------------------------------------------------
    public def this(i1: Int, s: Int, i2: Int) {
        val length = (i2 - i1) / s + 1;
        property(1, length);
        val r = (0..0)*(0..length - 1);
        this.array = new Array[Int](r, (
                (p:Point(r.rank))=>
                i1 + s*p(1)
            )
        );
    }

    public def this(i1: Int, i2: Int) {
        val length = i2 - i1 + 1;
        property(1, length);
        val r = (0..0)*(0..length - 1);
        this.array = new Array[Int](r, (
                (p:Point(r.rank))=>
                p(1) + i1
            )
        );
    }
    // Range Vector Creation } ---------------------------------------------------------------------------
/*
    // Equality of n and m is checked
    public static operator (x: IntMatrix) + (y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](r, (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) + y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }
*/

/*
    // Equality of n and m is checked
    public static operator (x: NDTMatrix) + (y: NDTMatrix): NDTMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) + y.array(p(0), p(1))
            )
        );

        return new NDTMatrix(array, n, m);
    }

    // no checks
    // s for static checks
    public static def splus(x: NDTMatrix, y: NDTMatrix): NDTMatrix {
        return null;
    }


    // checks are done on n and m to determine which of the following max methods should be called.
    public static def max(x: NDTMatrix): NDTMatrix {

        return null;
        // What do we do to return a scalar value?
        // We set array to null and use the num field.
    }


    public static def mMax(x: NDTMatrix): NDTMatrix {
        return null;
    }

    public static def hvMax(x: NDTMatrix): NDT {
        return 1;
    }

    public static def vvMax(x: NDTMatrix): NDT {
        return 1;
    }
*/

// % { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static operator (x: IntMatrix) % (y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At % op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) % y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static operator (x: Int) % (y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x % y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static operator (x: IntMatrix) % (y: Int): IntMatrix {
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

// % } ------------------------------------------------------------------------------
}

// End of IntMatrix