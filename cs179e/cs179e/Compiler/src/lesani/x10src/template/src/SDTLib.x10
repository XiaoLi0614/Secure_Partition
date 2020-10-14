


// Beginning of S-D-TLib.x10

    public static def size(x: SDTMatrix): Tuple2[Int, Int] {
        return new Tuple2[Int, Int](x.n, x.m);
    }

    public static def size(x: SDTMatrix, i: Int): Int {
        if (i==1)
            return x.n;
        else if (i==2)
            return x.m;
        else
            error("At size, Out of dimensions index.");
        return 0;
    }




    public static def disp(x: SDT): Void {
        Console.OUT.println(x);
    }

    public static def disp(x: SDTMatrix): Void {
        Console.OUT.println(x);
    }

    public static def tupleToVector(tuple: Tuple2[SDT, SDT]): SDTMatrix {
        val array: Array[SDT](1) = new Array[SDT](2);
        array(0) = tuple._1;
        array(1) = tuple._2;
        return new SDTMatrix(array);
    }
    public static def tupleToVector(tuple: Tuple3[SDT, SDT, SDT]): SDTMatrix {
        val array: Array[SDT](1) = new Array[SDT](3);
        array(0) = tuple._1;
        array(1) = tuple._2;
        array(2) = tuple._3;
        return new SDTMatrix(array);
    }
    public static def tupleToVector(tuple: Tuple4[SDT, SDT, SDT, SDT]): SDTMatrix {
        val array: Array[SDT](1) = new Array[SDT](4);
        array(0) = tuple._1;
        array(1) = tuple._2;
        array(2) = tuple._3;
        array(3) = tuple._4;
        return new SDTMatrix(array);
    }
    public static def tupleToVector(tuple: Tuple5[SDT, SDT, SDT, SDT, SDT]): SDTMatrix {
        val array: Array[SDT](1) = new Array[SDT](5);
        array(0) = tuple._1;
        array(1) = tuple._2;
        array(2) = tuple._3;
        array(3) = tuple._4;
        array(4) = tuple._5;
        return new SDTMatrix(array);
    }


    // ------------------------------------------------------------------------------------
    // length
    public static def length(mat: SDTMatrix): Int {
        return Math.max(mat.n, mat.m);
    }

    // ------------------------------------------------------------------------------------
    // colonAssign
    public static def colonAssign(mat: SDTMatrix, v: SDT) {
        val n = mat.n;
        val m = mat.m;

        for (var i: Int = 0; i < n; i++)
            for (var j: Int = 0; j < m; j++)
                mat.array(i, j) = v;
    }
    public static def colonAssign(mat: SDTMatrix, v: SDTMatrix) {
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
    private static def colonAssignH(mat: SDTMatrix, v: SDTMatrix) {
        val n = mat.n;
        val m = mat.m;
        if (n*m != v.m)
            error("At colon matrix assignment: Element coutn mismatch of array and right hand side.");

        for (var j: Int = 0; j < m; j++)
            for (var i: Int = 0; i < n; i++)
                mat.array(i, j) = v.array(0, n*j+i);
    }
    private static def colonAssignV(mat: SDTMatrix, v: SDTMatrix) {
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
    public static def concat(mat: SDTMatrix, v: SDT): SDTMatrix {
        if (mat.n != 1)
            error("concatentation of matrices is supported on horizontal vectors.");

        var m: Int = mat.m;

        val r = (0..0)*(0..m-1+1);
        val array = new Array[SDT](r);
        for (var i: Int = 0; i < m; i++)
            array(0, i) = mat.array(0, i);
        array(0, m) = v;
        return new SDTMatrix(array, 1, m + 1);
    }
    public static def concat(v: SDT, mat: SDTMatrix): SDTMatrix {
        if (mat.n != 1)
            error("concatentation of matrices is supported on horizontal vectors.");

        var m: Int = mat.m;

        val r = (0..0)*(0..m-1+1);
        val array = new Array[SDT](r);
        array(0, 0) = v;
        for (var i: Int = 0; i < m; i++)
            array(0, i+1) = mat.array(0, i);

        return new SDTMatrix(array, 1, m + 1);
    }

    public static def concat(mat1: SDTMatrix, mat2: SDTMatrix): SDTMatrix {
        if ((mat1.n != 1) || (mat2.n != 1))
            error("concatentation of matrices is supported on horizontal vectors.");

        var m1: Int = mat1.m;
        var m2: Int = mat2.m;

        val r = (0..0)*(0..m1 + m2 -1);
        val array = new Array[SDT](r);
        for (var i: Int = 0; i < m1; i++)
            array(0, i) = mat1.array(0, i);
        for (var i: Int = 0; i < m2; i++)
            array(0, m1 + i) = mat2.array(0, i);

        return new SDTMatrix(array, 1, m1 + m2);
    }

    // ------------------------------------------------------------------------------------
    // reshape { ------------------------------------------------------------------------------------
    public static def reshape(mat: SDTMatrix, np: Int, mp: Int): SDTMatrix {
        val n = mat.n;
        val m = mat.m;

        if (n*m < np*mp)
            error("At reshape: The matrix does not have enough elements.");

        val r = (0..np-1)*(0..mp-1);
        val a = new Array[SDT](
            r, (
                (p:Point(r.rank))=> {
                    val i = p(0);
                    val j = p(1);
                    val num = j*np + i;
                    return mat.array(num % n, num / n);
                }
            )
        );
        return new SDTMatrix(a, np, mp);
    }

    // reshape } ------------------------------------------------------------------------------------

// End of S-D-TLib.x10

