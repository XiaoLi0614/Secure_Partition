


// Beginning of S-D-TLib.x10

    public static def size(x: IntMatrix): Tuple2[Int, Int] {
        return new Tuple2[Int, Int](x.n, x.m);
    }

    public static def size(x: IntMatrix, i: Int): Int {
        if (i==1)
            return x.n;
        else if (i==2)
            return x.m;
        else
            error("At size, Out of dimensions index.");
        return 0;
    }




    public static def disp(x: Int): Void {
        Console.OUT.println(x);
    }

    public static def disp(x: IntMatrix): Void {
        Console.OUT.println(x);
    }

    public static def tupleToVector(tuple: Tuple2[Int, Int]): IntMatrix {
        val array: Array[Int](1) = new Array[Int](2);
        array(0) = tuple._1;
        array(1) = tuple._2;
        return new IntMatrix(array);
    }
    public static def tupleToVector(tuple: Tuple3[Int, Int, Int]): IntMatrix {
        val array: Array[Int](1) = new Array[Int](3);
        array(0) = tuple._1;
        array(1) = tuple._2;
        array(2) = tuple._3;
        return new IntMatrix(array);
    }
    public static def tupleToVector(tuple: Tuple4[Int, Int, Int, Int]): IntMatrix {
        val array: Array[Int](1) = new Array[Int](4);
        array(0) = tuple._1;
        array(1) = tuple._2;
        array(2) = tuple._3;
        array(3) = tuple._4;
        return new IntMatrix(array);
    }
    public static def tupleToVector(tuple: Tuple5[Int, Int, Int, Int, Int]): IntMatrix {
        val array: Array[Int](1) = new Array[Int](5);
        array(0) = tuple._1;
        array(1) = tuple._2;
        array(2) = tuple._3;
        array(3) = tuple._4;
        array(4) = tuple._5;
        return new IntMatrix(array);
    }


    // ------------------------------------------------------------------------------------
    // length
    public static def length(mat: IntMatrix): Int {
        return Math.max(mat.n, mat.m);
    }

    // ------------------------------------------------------------------------------------
    // colonAssign
    public static def colonAssign(mat: IntMatrix, v: Int) {
        val n = mat.n;
        val m = mat.m;

        for (var i: Int = 0; i < n; i++)
            for (var j: Int = 0; j < m; j++)
                mat.array(i, j) = v;
    }
    public static def colonAssign(mat: IntMatrix, v: IntMatrix) {
        // Todo: accept vector on the right hand side.
        if (v.m == 1) {
            colonAssignV(mat, v);
            return;
        }
        if (v.n == 1) {
            colonAssignH(mat, v);
            return;
        }
        if (mat.n != v.n || mat.m != v.m) {
            Console.OUT.println("Array: " + mat.n + "x" + mat.m);
            Console.OUT.println("Right: " + v.n + "x" + v.m);
            error("At colon matrix assignment: Dimension mismatch of array and right hand side.");
        }
        val n = mat.n;
        val m = mat.m;

        for (var i: Int = 0; i < n; i++)
            for (var j: Int = 0; j < m; j++)
                mat.array(i, j) = v.array(i, j);
    }
    private static def colonAssignH(mat: IntMatrix, v: IntMatrix) {
        val n = mat.n;
        val m = mat.m;
        if (n*m != v.m)
            error("At colon matrix assignment: Element coutn mismatch of array and right hand side.");

        for (var j: Int = 0; j < m; j++)
            for (var i: Int = 0; i < n; i++)
                mat.array(i, j) = v.array(0, n*j+i);
    }
    private static def colonAssignV(mat: IntMatrix, v: IntMatrix) {
        val n = mat.n;
        val m = mat.m;

        if (n*m != v.n)
            error("At colon matrix assignment: Element coutn mismatch of array and right hand side.");

        for (var i: Int = 0; i < n; i++)
            for (var j: Int = 0; j < m; j++)
                mat.array(i, j) = v.array(n*j+i, 0);
    }
    // ------------------------------------------------------------------------------------
    // concat
    public static def concat(mat: IntMatrix, v: Int): IntMatrix {
        if (mat.n != 1)
            error("concatentation of matrices is supported on horizontal vectors.");

        var m: Int = mat.m;

        val r = (0..0)*(0..m-1+1);
        val array = new Array[Int](r);
        for (var i: Int = 0; i < m; i++)
            array(0, i) = mat.array(0, i);
        array(0, m) = v;
        return new IntMatrix(array, 1, m + 1);
    }
    public static def concat(v: Int, mat: IntMatrix): IntMatrix {
        if (mat.n != 1)
            error("concatentation of matrices is supported on horizontal vectors.");

        var m: Int = mat.m;

        val r = (0..0)*(0..m-1+1);
        val array = new Array[Int](r);
        array(0, 0) = v;
        for (var i: Int = 0; i < m; i++)
            array(0, i+1) = mat.array(0, i);

        return new IntMatrix(array, 1, m + 1);
    }

    public static def concat(mat1: IntMatrix, mat2: IntMatrix): IntMatrix {
        if ((mat1.n != 1) || (mat2.n != 1))
            error("concatentation of matrices is supported on horizontal vectors.");

        var m1: Int = mat1.m;
        var m2: Int = mat2.m;

        val r = (0..0)*(0..m1 + m2 -1);
        val array = new Array[Int](r);
        for (var i: Int = 0; i < m1; i++)
            array(0, i) = mat1.array(0, i);
        for (var i: Int = 0; i < m2; i++)
            array(0, m1 + i) = mat2.array(0, i);

        return new IntMatrix(array, 1, m1 + m2);
    }

    // ------------------------------------------------------------------------------------
    // reshape { ------------------------------------------------------------------------------------
    public static def reshape(mat: IntMatrix, np: Int, mp: Int): IntMatrix {
        val n = mat.n;
        val m = mat.m;

        if (n*m < np*mp)
            error("At reshape: The matrix does not have enough elements.");

        val r = (0..np-1)*(0..mp-1);
        val a = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val i = p(0);
                    val j = p(1);
                    val num = j*np + i;
                    return mat.array(num % n, num / n);
                }
            )
        );
        return new IntMatrix(a, np, mp);
    }

    // reshape } ------------------------------------------------------------------------------------

// End of S-D-TLib.x10




// Beginning of S-D-TLib.x10

    public static def size(x: DoubleMatrix): Tuple2[Int, Int] {
        return new Tuple2[Int, Int](x.n, x.m);
    }

    public static def size(x: DoubleMatrix, i: Int): Int {
        if (i==1)
            return x.n;
        else if (i==2)
            return x.m;
        else
            error("At size, Out of dimensions index.");
        return 0;
    }




    public static def disp(x: Double): Void {
        Console.OUT.println(x);
    }

    public static def disp(x: DoubleMatrix): Void {
        Console.OUT.println(x);
    }

    public static def tupleToVector(tuple: Tuple2[Double, Double]): DoubleMatrix {
        val array: Array[Double](1) = new Array[Double](2);
        array(0) = tuple._1;
        array(1) = tuple._2;
        return new DoubleMatrix(array);
    }
    public static def tupleToVector(tuple: Tuple3[Double, Double, Double]): DoubleMatrix {
        val array: Array[Double](1) = new Array[Double](3);
        array(0) = tuple._1;
        array(1) = tuple._2;
        array(2) = tuple._3;
        return new DoubleMatrix(array);
    }
    public static def tupleToVector(tuple: Tuple4[Double, Double, Double, Double]): DoubleMatrix {
        val array: Array[Double](1) = new Array[Double](4);
        array(0) = tuple._1;
        array(1) = tuple._2;
        array(2) = tuple._3;
        array(3) = tuple._4;
        return new DoubleMatrix(array);
    }
    public static def tupleToVector(tuple: Tuple5[Double, Double, Double, Double, Double]): DoubleMatrix {
        val array: Array[Double](1) = new Array[Double](5);
        array(0) = tuple._1;
        array(1) = tuple._2;
        array(2) = tuple._3;
        array(3) = tuple._4;
        array(4) = tuple._5;
        return new DoubleMatrix(array);
    }


    // ------------------------------------------------------------------------------------
    // length
    public static def length(mat: DoubleMatrix): Int {
        return Math.max(mat.n, mat.m);
    }

    // ------------------------------------------------------------------------------------
    // colonAssign
    public static def colonAssign(mat: DoubleMatrix, v: Double) {
        val n = mat.n;
        val m = mat.m;

        for (var i: Int = 0; i < n; i++)
            for (var j: Int = 0; j < m; j++)
                mat.array(i, j) = v;
    }
    public static def colonAssign(mat: DoubleMatrix, v: DoubleMatrix) {
        // Todo: accept vector on the right hand side.
        if (v.m == 1) {
            colonAssignV(mat, v);
            return;
        }
        if (v.n == 1) {
            colonAssignH(mat, v);
            return;
        }
        if (mat.n != v.n || mat.m != v.m) {
            Console.OUT.println("Array: " + mat.n + "x" + mat.m);
            Console.OUT.println("Right: " + v.n + "x" + v.m);
            error("At colon matrix assignment: Dimension mismatch of array and right hand side.");
        }
        val n = mat.n;
        val m = mat.m;

        for (var i: Int = 0; i < n; i++)
            for (var j: Int = 0; j < m; j++)
                mat.array(i, j) = v.array(i, j);
    }
    private static def colonAssignH(mat: DoubleMatrix, v: DoubleMatrix) {
        val n = mat.n;
        val m = mat.m;
        if (n*m != v.m)
            error("At colon matrix assignment: Element coutn mismatch of array and right hand side.");

        for (var j: Int = 0; j < m; j++)
            for (var i: Int = 0; i < n; i++)
                mat.array(i, j) = v.array(0, n*j+i);
    }
    private static def colonAssignV(mat: DoubleMatrix, v: DoubleMatrix) {
        val n = mat.n;
        val m = mat.m;

        if (n*m != v.n)
            error("At colon matrix assignment: Element coutn mismatch of array and right hand side.");

        for (var i: Int = 0; i < n; i++)
            for (var j: Int = 0; j < m; j++)
                mat.array(i, j) = v.array(n*j+i, 0);
    }
    // ------------------------------------------------------------------------------------
    // concat
    public static def concat(mat: DoubleMatrix, v: Double): DoubleMatrix {
        if (mat.n != 1)
            error("concatentation of matrices is supported on horizontal vectors.");

        var m: Int = mat.m;

        val r = (0..0)*(0..m-1+1);
        val array = new Array[Double](r);
        for (var i: Int = 0; i < m; i++)
            array(0, i) = mat.array(0, i);
        array(0, m) = v;
        return new DoubleMatrix(array, 1, m + 1);
    }
    public static def concat(v: Double, mat: DoubleMatrix): DoubleMatrix {
        if (mat.n != 1)
            error("concatentation of matrices is supported on horizontal vectors.");

        var m: Int = mat.m;

        val r = (0..0)*(0..m-1+1);
        val array = new Array[Double](r);
        array(0, 0) = v;
        for (var i: Int = 0; i < m; i++)
            array(0, i+1) = mat.array(0, i);

        return new DoubleMatrix(array, 1, m + 1);
    }

    public static def concat(mat1: DoubleMatrix, mat2: DoubleMatrix): DoubleMatrix {
        if ((mat1.n != 1) || (mat2.n != 1))
            error("concatentation of matrices is supported on horizontal vectors.");

        var m1: Int = mat1.m;
        var m2: Int = mat2.m;

        val r = (0..0)*(0..m1 + m2 -1);
        val array = new Array[Double](r);
        for (var i: Int = 0; i < m1; i++)
            array(0, i) = mat1.array(0, i);
        for (var i: Int = 0; i < m2; i++)
            array(0, m1 + i) = mat2.array(0, i);

        return new DoubleMatrix(array, 1, m1 + m2);
    }

    // ------------------------------------------------------------------------------------
    // reshape { ------------------------------------------------------------------------------------
    public static def reshape(mat: DoubleMatrix, np: Int, mp: Int): DoubleMatrix {
        val n = mat.n;
        val m = mat.m;

        if (n*m < np*mp)
            error("At reshape: The matrix does not have enough elements.");

        val r = (0..np-1)*(0..mp-1);
        val a = new Array[Double](
            r, (
                (p:Point(r.rank))=> {
                    val i = p(0);
                    val j = p(1);
                    val num = j*np + i;
                    return mat.array(num % n, num / n);
                }
            )
        );
        return new DoubleMatrix(a, np, mp);
    }

    // reshape } ------------------------------------------------------------------------------------

// End of S-D-TLib.x10




// Beginning of S-D-TLib.x10

    public static def size(x: BooleanMatrix): Tuple2[Int, Int] {
        return new Tuple2[Int, Int](x.n, x.m);
    }

    public static def size(x: BooleanMatrix, i: Int): Int {
        if (i==1)
            return x.n;
        else if (i==2)
            return x.m;
        else
            error("At size, Out of dimensions index.");
        return 0;
    }




    public static def disp(x: Boolean): Void {
        Console.OUT.println(x);
    }

    public static def disp(x: BooleanMatrix): Void {
        Console.OUT.println(x);
    }

    public static def tupleToVector(tuple: Tuple2[Boolean, Boolean]): BooleanMatrix {
        val array: Array[Boolean](1) = new Array[Boolean](2);
        array(0) = tuple._1;
        array(1) = tuple._2;
        return new BooleanMatrix(array);
    }
    public static def tupleToVector(tuple: Tuple3[Boolean, Boolean, Boolean]): BooleanMatrix {
        val array: Array[Boolean](1) = new Array[Boolean](3);
        array(0) = tuple._1;
        array(1) = tuple._2;
        array(2) = tuple._3;
        return new BooleanMatrix(array);
    }
    public static def tupleToVector(tuple: Tuple4[Boolean, Boolean, Boolean, Boolean]): BooleanMatrix {
        val array: Array[Boolean](1) = new Array[Boolean](4);
        array(0) = tuple._1;
        array(1) = tuple._2;
        array(2) = tuple._3;
        array(3) = tuple._4;
        return new BooleanMatrix(array);
    }
    public static def tupleToVector(tuple: Tuple5[Boolean, Boolean, Boolean, Boolean, Boolean]): BooleanMatrix {
        val array: Array[Boolean](1) = new Array[Boolean](5);
        array(0) = tuple._1;
        array(1) = tuple._2;
        array(2) = tuple._3;
        array(3) = tuple._4;
        array(4) = tuple._5;
        return new BooleanMatrix(array);
    }


    // ------------------------------------------------------------------------------------
    // length
    public static def length(mat: BooleanMatrix): Int {
        return Math.max(mat.n, mat.m);
    }

    // ------------------------------------------------------------------------------------
    // colonAssign
    public static def colonAssign(mat: BooleanMatrix, v: Boolean) {
        val n = mat.n;
        val m = mat.m;

        for (var i: Int = 0; i < n; i++)
            for (var j: Int = 0; j < m; j++)
                mat.array(i, j) = v;
    }
    public static def colonAssign(mat: BooleanMatrix, v: BooleanMatrix) {
        // Todo: accept vector on the right hand side.
        if (v.m == 1) {
            colonAssignV(mat, v);
            return;
        }
        if (v.n == 1) {
            colonAssignH(mat, v);
            return;
        }
        if (mat.n != v.n || mat.m != v.m) {
            Console.OUT.println("Array: " + mat.n + "x" + mat.m);
            Console.OUT.println("Right: " + v.n + "x" + v.m);
            error("At colon matrix assignment: Dimension mismatch of array and right hand side.");
        }
        val n = mat.n;
        val m = mat.m;

        for (var i: Int = 0; i < n; i++)
            for (var j: Int = 0; j < m; j++)
                mat.array(i, j) = v.array(i, j);
    }
    private static def colonAssignH(mat: BooleanMatrix, v: BooleanMatrix) {
        val n = mat.n;
        val m = mat.m;
        if (n*m != v.m)
            error("At colon matrix assignment: Element coutn mismatch of array and right hand side.");

        for (var j: Int = 0; j < m; j++)
            for (var i: Int = 0; i < n; i++)
                mat.array(i, j) = v.array(0, n*j+i);
    }
    private static def colonAssignV(mat: BooleanMatrix, v: BooleanMatrix) {
        val n = mat.n;
        val m = mat.m;

        if (n*m != v.n)
            error("At colon matrix assignment: Element coutn mismatch of array and right hand side.");

        for (var i: Int = 0; i < n; i++)
            for (var j: Int = 0; j < m; j++)
                mat.array(i, j) = v.array(n*j+i, 0);
    }
    // ------------------------------------------------------------------------------------
    // concat
    public static def concat(mat: BooleanMatrix, v: Boolean): BooleanMatrix {
        if (mat.n != 1)
            error("concatentation of matrices is supported on horizontal vectors.");

        var m: Int = mat.m;

        val r = (0..0)*(0..m-1+1);
        val array = new Array[Boolean](r);
        for (var i: Int = 0; i < m; i++)
            array(0, i) = mat.array(0, i);
        array(0, m) = v;
        return new BooleanMatrix(array, 1, m + 1);
    }
    public static def concat(v: Boolean, mat: BooleanMatrix): BooleanMatrix {
        if (mat.n != 1)
            error("concatentation of matrices is supported on horizontal vectors.");

        var m: Int = mat.m;

        val r = (0..0)*(0..m-1+1);
        val array = new Array[Boolean](r);
        array(0, 0) = v;
        for (var i: Int = 0; i < m; i++)
            array(0, i+1) = mat.array(0, i);

        return new BooleanMatrix(array, 1, m + 1);
    }

    public static def concat(mat1: BooleanMatrix, mat2: BooleanMatrix): BooleanMatrix {
        if ((mat1.n != 1) || (mat2.n != 1))
            error("concatentation of matrices is supported on horizontal vectors.");

        var m1: Int = mat1.m;
        var m2: Int = mat2.m;

        val r = (0..0)*(0..m1 + m2 -1);
        val array = new Array[Boolean](r);
        for (var i: Int = 0; i < m1; i++)
            array(0, i) = mat1.array(0, i);
        for (var i: Int = 0; i < m2; i++)
            array(0, m1 + i) = mat2.array(0, i);

        return new BooleanMatrix(array, 1, m1 + m2);
    }

    // ------------------------------------------------------------------------------------
    // reshape { ------------------------------------------------------------------------------------
    public static def reshape(mat: BooleanMatrix, np: Int, mp: Int): BooleanMatrix {
        val n = mat.n;
        val m = mat.m;

        if (n*m < np*mp)
            error("At reshape: The matrix does not have enough elements.");

        val r = (0..np-1)*(0..mp-1);
        val a = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
                    val i = p(0);
                    val j = p(1);
                    val num = j*np + i;
                    return mat.array(num % n, num / n);
                }
            )
        );
        return new BooleanMatrix(a, np, mp);
    }

    // reshape } ------------------------------------------------------------------------------------

// End of S-D-TLib.x10

