
// Beginning of N-D-TLib.x10

    // logical { ------------------------------------------------------------------------------
    public static def logical(x: Int): Boolean {
        return (x != 0);
    }

    public static def logical(x: IntMatrix): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](r, (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1)) != 0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    // logical } ------------------------------------------------------------------------------


    // not { ------------------------------------------------------------------------------
    public static def not(x: Int) : Boolean {
        return !(x != 0);
    }

    public static def not(x: IntMatrix) : BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](r, (
                (p:Point(r.rank)) =>
                !(x.array(p(0), p(1)) != 0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    // not } ------------------------------------------------------------------------------

    // unary - { ------------------------------------------------------------------------------
    public static def minus(x: Int): Int {
        return -x;
    }

    public static def minus(x: IntMatrix): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](r, (
                (p:Point(r.rank)) =>
                - x.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }
    // unary - } ------------------------------------------------------------------------------
    // * { ------------------------------------------------------------------------------

    public static def times(a: BooleanMatrix, b: IntMatrix): IntMatrix {
        val rowsA = a.n;
        val columnsA = a.m;
        val rowsB = b.n;
        val columnsB = b.m;

        if (columnsA != rowsB)
            error("At matrix multiplication: Dimension mismatch.");

        val r = (0..rowsA-1)*(0..columnsB-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val i = p(0);
                    val j = p(1);
                    var sum: Int = 0;
                    for (var k: Int = 0; k < columnsA; k++)
                       sum = sum + (a.array(i, k)?(b.array(k, j)):0);

                    return sum;
                }
            )
        );

        return new IntMatrix(array, rowsA, columnsB);
    }
    public static def times(a: IntMatrix, b: BooleanMatrix): IntMatrix {
        val rowsA = a.n;
        val columnsA = a.m;
        val rowsB = b.n;
        val columnsB = b.m;

        if (columnsA != rowsB)
            error("At matrix multiplication: Dimension mismatch.");

        val r = (0..rowsA-1)*(0..columnsB-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val i = p(0);
                    val j = p(1);
                    var sum: Int = 0;
                    for (var k: Int = 0; k < columnsA; k++)
                       sum = sum + (b.array(k, j)?(a.array(i, k)):0);

                    return sum;
                }
            )
        );

        return new IntMatrix(array, rowsA, columnsB);
    }
    // * } ------------------------------------------------------------------------------
    // / { ------------------------------------------------------------------------------

    public static def divide(a: IntMatrix, b: BooleanMatrix): DoubleMatrix {
        if (a.m != b.m)
            error("At matrix / op: Mismatch of Operand Dimensions.");

        val biArray = invert(b.array);
        val bi = new DoubleMatrix(biArray, 1);
        return times(a, bi);
    }
    public static def divide(a: BooleanMatrix, b: IntMatrix): DoubleMatrix {
        if (a.m != b.m)
            error("At matrix / op: Mismatch of Operand Dimensions.");

        val biArray = invert(b.array);
        val bi = new DoubleMatrix(biArray, 1);
        return times(a, bi);
    }

    // / } ------------------------------------------------------------------------------

    // ^ { ------------------------------------------------------------------------------

    public static def power(a: Int, n: Int): Int {
        return (Math.pow(a, n) as Int);
    }

    public static def power(a: IntMatrix, n: Int): IntMatrix {
        var b: IntMatrix = a;
        for (var i: Int = 0; i < n-1; i++)
            b = times(b, a);
        return b;
    }

    // ^ } ------------------------------------------------------------------------------

    public static def sqrt(x: IntMatrix): DoubleMatrix {
        val r = (0..x.n-1)*(0..x.m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank)) =>
                Math.sqrt(x.array(p(0), p(1)) as Double)
            )
        );
        return new DoubleMatrix(array, x.n, x.m);
    }
    // norm { ---------------------------------------------------------------------------

    //    When A is a vector:
    //        norm(A,p) returns sum(abs(A).^p)^(1/p), for any 1 <= p <= infinity.
    //        norm(A) returns norm(A,2).
    //        norm(A,inf) returns max(abs(A)).
    //        norm(A,-inf) returns min(abs(A)).


    public static def norm(a: IntMatrix): Double {
        val n = a.n;
        val m = a.m;
        if ((n != 1) && (m != 1)) {
//            error("At norm: To be implemented. norm of a non-vector matrix.");
            val s: Array[Double](1) = singularValueDecomposition(a)._3;
            return s(0);
        }
        var s: Int = 0;
        for (var i: Int = 0; i < n; i++)
            for (var j: Int = 0; j < m; j++) {
                val e = Math.abs(a.array(i, j));
                s += e*e;
            }
        return Math.sqrt(s as Double);
    }

    public static def norm(mat: IntMatrix, st: String): Double {
        val n = mat.n;
        val m = mat.m;

        if (!st.equals("inf"))
            error("At norm: An int or \'inf\' expected as the second parameter of norm.");
        if ((n != 1) && (m != 1))
            error("At norm: To be implemented. norm of a non-vector matrix.");

        var maxValue: Int = 0 as Int;
        for (var i: Int = 0; i < n; i++)
            for (var j: Int = 0; j < m; j++) {
                val value = Math.abs(mat.array(i, j));
                if (value > maxValue)
                    maxValue = value;
            }

        return maxValue;
    }

    public static def norm(a: IntMatrix, c: Int): Int {
        val n = a.n;
        val m = a.m;
        if (c == 1) {
            var f: Int = 0;
            for (var j: Int = 0; j < m; j++) {
                var s: Int = 0;
                for (var i: Int = 0; i < n; i++)
                    s += Math.abs(a.array(i, j));
                f = Math.max(f, s);
            }
            return f;
        } else
            error("At norm: To be implemented.");
        return 0;
    }

    // norm } ---------------------------------------------------------------------------

    // sort { ---------------------------------------------------------------------------
    private static def sortV(a: IntMatrix, lo0: Int, hi0: Int) {

        var lo: Int = lo0;
        var hi: Int = hi0;
        if (lo >= hi) {
            return;
        } else if (lo == hi - 1) {

            //sort a two element list by swapping if necessary
            if (a.array(lo, 0) > a.array(hi, 0)) {
                val temp = a.array(lo, 0);
                a.array(lo, 0) = a.array(hi, 0);
                a.array(hi, 0) = temp;
            }
            return;
        }



        //  Pick a pivot and move it out of the way
        val pivot = a.array((lo + hi) / 2, 0);
        a.array((lo + hi) / 2, 0) = a.array(hi, 0);
        a.array(hi, 0) = pivot;

        while (lo < hi) {

             //  Search forward from a[lo] until an element is found that
             //  is greater than the pivot or lo >= hi
             //
            while (a.array(lo, 0) <= pivot && lo < hi)
                lo++;

            //
            //  Search backward from a[hi] until element is found that
            //  is less than the pivot, or lo >= hi

            while (pivot <= a.array(hi, 0) && lo < hi)
                hi--;


            //  Swap elements a[lo] and a[hi]
            if (lo < hi) {
                val temp = a.array(lo, 0);
                a.array(lo, 0) = a.array(hi, 0);
                a.array(hi, 0) = temp;
            }
        }


        //  Put the median in the "center" of the list
        a.array(hi0, 0) = a.array(hi, 0);
        a.array(hi, 0) = pivot;


        //  Recursive calls, elements a[lo0] to a[lo-1] are less than or
        //  equal to pivot, elements a[hi+1] to a[hi0] are greater than
        //  pivot.
        sortV(a, lo0, lo-1);
        sortV(a, hi+1, hi0);
    }

    private static def sortIV(a: IntMatrix, index: IntMatrix, lo0: Int, hi0: Int) {

        var lo: Int = lo0;
        var hi: Int = hi0;
        if (lo >= hi) {
            return;
        } else if (lo == hi - 1) {
            /*
             *  sort a two element list by swapping if necessary
             */
            if (a.array(lo, 0) > a.array(hi, 0)) {
                val temp = a.array(lo, 0);
                a.array(lo, 0) = a.array(hi, 0);
                a.array(hi, 0) = temp;
                val temp2 = index.array(lo, 0);
                index.array(lo, 0) = index.array(hi, 0);
                index.array(hi, 0) = temp2;
            }
            return;
        }


        /*
         *  Pick a pivot and move it out of the way
         */
        val pivot = a.array((lo + hi) / 2, 0);
        a.array((lo + hi) / 2, 0) = a.array(hi, 0);
        a.array(hi, 0) = pivot;
        val i = index.array((lo + hi) / 2, 0);
        index.array((lo + hi) / 2, 0) = index.array(hi, 0);
        index.array(hi, 0) = i;

        while (lo < hi) {
            /*
             *  Search forward from a[lo] until an element is found that
             *  is greater than the pivot or lo >= hi
             */
            while (a.array(lo, 0) <= pivot && lo < hi)
                lo++;

            /*
             *  Search backward from a[hi] until element is found that
             *  is less than the pivot, or lo >= hi
             */
            while (pivot <= a.array(hi, 0) && lo < hi)
                hi--;

            /*
             *  Swap elements a[lo] and a[hi]
             */
            if (lo < hi) {
                val temp = a.array(lo, 0);
                a.array(lo, 0) = a.array(hi, 0);
                a.array(hi, 0) = temp;
                val temp2 = index.array(lo, 0);
                index.array(lo, 0) = index.array(hi, 0);
                index.array(hi, 0) = temp2;
            }
        }

        /*
         *  Put the median in the "center" of the list
         */
        a.array(hi0, 0) = a.array(hi, 0);
        a.array(hi, 0) = pivot;

        index.array(hi0, 0) = index.array(hi, 0);
        index.array(hi, 0) = i;

        /*
         *  Recursive calls, elements a[lo0] to a[lo-1] are less than or
         *  equal to pivot, elements a[hi+1] to a[hi0] are greater than
         *  pivot.
         */
        sortIV(a, index, lo0, lo-1);
        sortIV(a, index, hi+1, hi0);
    }
    private static def sortH(a: IntMatrix, lo0: Int, hi0: Int) {

        var lo: Int = lo0;
        var hi: Int = hi0;
        if (lo >= hi) {
            return;
        } else if (lo == hi - 1) {
            /*
             *  sort a two element list by swapping if necessary
             */
            if (a.array(0, lo) > a.array(0, hi)) {
                val temp = a.array(0, lo);
                a.array(0, lo) = a.array(0, hi);
                a.array(0, hi) = temp;
            }
            return;
        }


        /*
         *  Pick a pivot and move it out of the way
         */
        val pivot = a.array(0, (lo + hi) / 2);
        a.array(0, (lo + hi) / 2) = a.array(0, hi);
        a.array(0, hi) = pivot;

        while (lo < hi) {
            /*
             *  Search forward from a[lo] until an element is found that
             *  is greater than the pivot or lo >= hi
             */
            while (a.array(0, lo) <= pivot && lo < hi)
                lo++;

            /*
             *  Search backward from a[hi] until element is found that
             *  is less than the pivot, or lo >= hi
             */
            while (pivot <= a.array(0, hi) && lo < hi)
                hi--;

            /*
             *  Swap elements a[lo] and a[hi]
             */
            if (lo < hi) {
                val temp = a.array(0, lo);
                a.array(0, lo) = a.array(0, hi);
                a.array(0, hi) = temp;
            }
        }

        /*
         *  Put the median in the "center" of the list
         */
        a.array(0, hi0) = a.array(0, hi);
        a.array(0, hi) = pivot;

        /*
         *  Recursive calls, elements a[lo0] to a[lo-1] are less than or
         *  equal to pivot, elements a[hi+1] to a[hi0] are greater than
         *  pivot.
         */
        sortH(a, lo0, lo-1);
        sortH(a, hi+1, hi0);
    }

    private static def sortIH(a: IntMatrix, index: IntMatrix, lo0: Int, hi0: Int) {

        var lo: Int = lo0;
        var hi: Int = hi0;
        if (lo >= hi) {
            return;
        } else if (lo == hi - 1) {
            /*
             *  sort a two element list by swapping if necessary
             */
            if (a.array(0, lo) > a.array(0, hi)) {
                val temp = a.array(0, lo);
                a.array(0, lo) = a.array(0, hi);
                a.array(0, hi) = temp;
                val temp2 = index.array(0, lo);
                index.array(0, lo) = index.array(0, hi);
                index.array(0, hi) = temp2;
            }
            return;
        }


        /*
         *  Pick a pivot and move it out of the way
         */
        val pivot = a.array((lo + hi) / 2, 0);
        a.array(0, (lo + hi) / 2) = a.array(0, hi);
        a.array(0, hi) = pivot;
        val i = index.array(0, (lo + hi) / 2);
        index.array(0, (lo + hi) / 2) = index.array(0, hi);
        index.array(0, hi) = i;

        while (lo < hi) {
            /*
             *  Search forward from a[lo] until an element is found that
             *  is greater than the pivot or lo >= hi
             */
            while (a.array(0, lo) <= pivot && lo < hi)
                lo++;

            /*
             *  Search backward from a[hi] until element is found that
             *  is less than the pivot, or lo >= hi
             */
            while (pivot <= a.array(0, hi) && lo < hi)
                hi--;

            /*
             *  Swap elements a[lo] and a[hi]
             */
            if (lo < hi) {
                val temp = a.array(0, lo);
                a.array(0, lo) = a.array(0, hi);
                a.array(0, hi) = temp;
                val temp2 = index.array(0, lo);
                index.array(0, lo) = index.array(0, hi);
                index.array(0, hi) = temp2;
            }
        }

        /*
         *  Put the median in the "center" of the list
         */
        a.array(0, hi0) = a.array(0, hi);
        a.array(0, hi) = pivot;

        index.array(0, hi0) = index.array(0, hi);
        index.array(0, hi) = i;

        /*
         *  Recursive calls, elements a[lo0] to a[lo-1] are less than or
         *  equal to pivot, elements a[hi+1] to a[hi0] are greater than
         *  pivot.
         */
        sortIV(a, index, lo0, lo-1);
        sortIV(a, index, hi+1, hi0);
    }




    private static def sortDV(a: IntMatrix, lo0: Int, hi0: Int) {
        var lo: Int = lo0;
        var hi: Int = hi0;
        if (lo >= hi) {
            return;
        } else if (lo == hi - 1) {

            //sort a two element list by swapping if necessary
            if (a.array(lo, 0) < a.array(hi, 0)) {
                val temp = a.array(lo, 0);
                a.array(lo, 0) = a.array(hi, 0);
                a.array(hi, 0) = temp;
            }
            return;
        }


        //  Pick a pivot and move it out of the way
        val pivot = a.array((lo + hi) / 2, 0);
        a.array((lo + hi) / 2, 0) = a.array(hi, 0);
        a.array(hi, 0) = pivot;

        while (lo < hi) {

             //  Search forward from a[lo] until an element is found that
             //  is greater than the pivot or lo >= hi
             //
            while (a.array(lo, 0) >= pivot && lo < hi)
                lo++;

            //
            //  Search backward from a[hi] until element is found that
            //  is less than the pivot, or lo >= hi

            while (pivot <= a.array(hi, 0) && lo < hi)
                hi--;


            //  Swap elements a[lo] and a[hi]
            if (lo < hi) {
                val temp = a.array(lo, 0);
                a.array(lo, 0) = a.array(hi, 0);
                a.array(hi, 0) = temp;
            }
        }


        //  Put the median in the "center" of the list
        a.array(hi0, 0) = a.array(hi, 0);
        a.array(hi, 0) = pivot;


        //  Recursive calls, elements a[lo0] to a[lo-1] are less than or
        //  equal to pivot, elements a[hi+1] to a[hi0] are greater than
        //  pivot.
        sortV(a, lo0, lo-1);
        sortV(a, hi+1, hi0);
    }

    private static def sortDIV(a: IntMatrix, index: IntMatrix, lo0: Int, hi0: Int) {
        var lo: Int = lo0;
        var hi: Int = hi0;
        if (lo >= hi) {
            return;
        } else if (lo == hi - 1) {
            /*
             *  sort a two element list by swapping if necessary
             */
            if (a.array(lo, 0) < a.array(hi, 0)) {
                val temp = a.array(lo, 0);
                a.array(lo, 0) = a.array(hi, 0);
                a.array(hi, 0) = temp;
                val temp2 = index.array(lo, 0);
                index.array(lo, 0) = index.array(hi, 0);
                index.array(hi, 0) = temp2;
            }
            return;
        }


        /*
         *  Pick a pivot and move it out of the way
         */
        val pivot = a.array((lo + hi) / 2, 0);
        a.array((lo + hi) / 2, 0) = a.array(hi, 0);
        a.array(hi, 0) = pivot;
        val i = index.array((lo + hi) / 2, 0);
        index.array((lo + hi) / 2, 0) = index.array(hi, 0);
        index.array(hi, 0) = i;

        while (lo < hi) {
            /*
             *  Search forward from a[lo] until an element is found that
             *  is greater than the pivot or lo >= hi
             */
            while (a.array(lo, 0) >= pivot && lo < hi)
                lo++;

            /*
             *  Search backward from a[hi] until element is found that
             *  is less than the pivot, or lo >= hi
             */
            while (pivot >= a.array(hi, 0) && lo < hi)
                hi--;

            /*
             *  Swap elements a[lo] and a[hi]
             */
            if (lo < hi) {
                val temp = a.array(lo, 0);
                a.array(lo, 0) = a.array(hi, 0);
                a.array(hi, 0) = temp;
                val temp2 = index.array(lo, 0);
                index.array(lo, 0) = index.array(hi, 0);
                index.array(hi, 0) = temp2;
            }
//            Console.OUT.println(a.transpose());
        }

        /*
         *  Put the median in the "center" of the list
         */
        a.array(hi0, 0) = a.array(hi, 0);
        a.array(hi, 0) = pivot;

        index.array(hi0, 0) = index.array(hi, 0);
        index.array(hi, 0) = i;

//        Console.OUT.println(a.transpose());
        /*
         *  Recursive calls, elements a[lo0] to a[lo-1] are less than or
         *  equal to pivot, elements a[hi+1] to a[hi0] are greater than
         *  pivot.
         */
        sortDIV(a, index, lo0, lo-1);
        sortDIV(a, index, hi+1, hi0);
    }


    private static def sortDH(a: IntMatrix, lo0: Int, hi0: Int) {
        var lo: Int = lo0;
        var hi: Int = hi0;
        if (lo >= hi) {
            return;
        } else if (lo == hi - 1) {

            //sort a two element list by swapping if necessary
            if (a.array(0, lo) < a.array(0, hi)) {
                val temp = a.array(0, lo);
                a.array(0, lo) = a.array(0, hi);
                a.array(0, hi) = temp;
            }
            return;
        }


        //  Pick a pivot and move it out of the way
        val pivot = a.array(0, (lo + hi) / 2);
        a.array(0, (lo + hi) / 2) = a.array(0, hi);
        a.array(0, hi) = pivot;

        while (lo < hi) {

             //  Search forward from a[lo] until an element is found that
             //  is greater than the pivot or lo >= hi
             //
            while (a.array(0, lo) >= pivot && lo < hi)
                lo++;

            //
            //  Search backward from a[hi] until element is found that
            //  is less than the pivot, or lo >= hi

            while (pivot <= a.array(0, hi) && lo < hi)
                hi--;


            //  Swap elements a[lo] and a[hi]
            if (lo < hi) {
                val temp = a.array(0, lo);
                a.array(0, lo) = a.array(0, hi);
                a.array(0, hi) = temp;
            }
        }


        //  Put the median in the "center" of the list
        a.array(0, hi0) = a.array(0, hi);
        a.array(0, hi) = pivot;


        //  Recursive calls, elements a[lo0] to a[lo-1] are less than or
        //  equal to pivot, elements a[hi+1] to a[hi0] are greater than
        //  pivot.
        sortDH(a, lo0, lo-1);
        sortDH(a, hi+1, hi0);
    }
    private static def sortDIH(a: IntMatrix, index: IntMatrix, lo0: Int, hi0: Int) {

        var lo: Int = lo0;
        var hi: Int = hi0;
        if (lo >= hi) {
            return;
        } else if (lo == hi - 1) {
            /*
             *  sort a two element list by swapping if necessary
             */
            if (a.array(0, lo) < a.array(0, hi)) {
                val temp = a.array(0, lo);
                a.array(0, lo) = a.array(0, hi);
                a.array(0, hi) = temp;
                val temp2 = index.array(0, lo);
                index.array(0, lo) = index.array(0, hi);
                index.array(0, hi) = temp2;
            }
            return;
        }


        /*
         *  Pick a pivot and move it out of the way
         */
        val pivot = a.array(0, (lo + hi) / 2);
        a.array(0, (lo + hi) / 2) = a.array(0, hi);
        a.array(0, hi) = pivot;
        val i = index.array(0, (lo + hi) / 2);
        index.array(0, (lo + hi) / 2) = index.array(0, hi);
        index.array(0, hi) = i;

        while (lo < hi) {
            /*
             *  Search forward from a[lo] until an element is found that
             *  is greater than the pivot or lo >= hi
             */
            while (a.array(0, lo) >= pivot && lo < hi)
                lo++;

            /*
             *  Search backward from a[hi] until element is found that
             *  is less than the pivot, or lo >= hi
             */
            while (pivot >= a.array(0, hi) && lo < hi)
                hi--;

            /*
             *  Swap elements a[lo] and a[hi]
             */
            if (lo < hi) {
                val temp = a.array(0, lo);
                a.array(0, lo) = a.array(0, hi);
                a.array(0, hi) = temp;
                val temp2 = index.array(0, lo);
                index.array(0, lo) = index.array(0, hi);
                index.array(0, hi) = temp2;
            }
        }

        /*
         *  Put the median in the "center" of the list
         */
        a.array(0, hi0) = a.array(0, hi);
        a.array(0, hi) = pivot;

        index.array(0, hi0) = index.array(0, hi);
        index.array(0, hi) = i;

        /*
         *  Recursive calls, elements a[lo0] to a[lo-1] are less than or
         *  equal to pivot, elements a[hi+1] to a[hi0] are greater than
         *  pivot.
         */
        sortDIH(a, index, lo0, lo-1);
        sortDIH(a, index, hi+1, hi0);
    }



    // We have
    //  sort:   Ascending
    //  sortI:  Ascending with indices
    //  sortD:  Descending
    //  sortDI: Descending with indices

    public static def sort(mat: IntMatrix): IntMatrix {
        val a = mat.clone();
        if (a.n == 1)
            sortH(a, 0, a.m - 1);
        else if (mat.m == 1)
            sortV(a, 0, a.n - 1);
        else
//            sortM(a);
            error("At sort: To be implemented.");
        return a;
    }
    // I for with Index
    public static def sortI(mat: IntMatrix): Tuple2[IntMatrix, IntMatrix] {
        val a = mat.clone();
        var index: IntMatrix = null;
        if (a.n == 1) {
            error("At sort: To be implemented.");
//            sortIH(a, 0, a.m-1);
        }
        else if (mat.m == 1) {
            index = new IntMatrix(1, a.n).transpose();
            sortIV(a, index, 0, a.n - 1);
        } else
//            sortIM(a);
            error("At sort: To be implemented.");
        return new Tuple2[IntMatrix, IntMatrix](a, index);
    }

    // D for descending
    public static def sortD(mat: IntMatrix): IntMatrix {
        val a = mat.clone();
        if (a.n == 1)
//            sortDH(a, 0, a.m-1);
            error("At sort: To be implemented.");
        else if (mat.m == 1)
            sortDV(a, 0, a.n - 1);
        else
//            sortM(a);
            error("At sort: To be implemented.");
        return a;
    }

    public static def sortDI(mat: IntMatrix): Tuple2[IntMatrix, IntMatrix] {
        val a = mat.clone();
        var index: IntMatrix = null;
        if (a.n == 1) {
            index = new IntMatrix(1, a.m).transpose();
            sortDIH(a, index, 0, a.m - 1);
        }
        else if (a.m == 1) {
            index = new IntMatrix(1, a.n).transpose();
            sortDIV(a, index, 0, a.n - 1);
        } else {
//            sortDIM(a);
            error("At sort: To be implemented.");
        }
        return new Tuple2[IntMatrix, IntMatrix](a, index);
    }
    // sort } ---------------------------------------------------------------------------

    // union { --------------------------------------------------------------------------

    private static def printList(list: ArrayList[Int]): Void {
        val it = list.iterator();
        while (it.hasNext()) {
            val e = it.next();
            Console.OUT.print(e);
            Console.OUT.print("\t");
        }
        Console.OUT.println();
    }

    private static def addBefore(list: ArrayList[Int], index: int, v: Int): Void {
        // addBefore is terribly wrong in the standard library!
        /*
            val a = new ArrayList[Int]();
            a.add(1);
            a.add(2);
            a.add(3);
            a.add(4);
            Console.OUT.println(a.get(0));
            Console.OUT.println(a.get(1));
            Console.OUT.println(a.get(2));
            Console.OUT.println(a.get(3));
            a.addBefore(2, 10);

            Console.OUT.println();

            Console.OUT.println(a.get(0));
            Console.OUT.println(a.get(1));
            Console.OUT.println(a.get(2));
            Console.OUT.println(a.get(3));
            Console.OUT.println(a.get(4));

            1
            2
            3
            4

            1
            2
            10
            3
            3
        */
        val size = list.size();
        list.add(0);
        for (var i: Int = size; i >= index; i--)
            list.set(list.get(i), i+1);
        list.set(v, index);
    }

    private static def unionInsert(list: ArrayList[Int], v: Int): Void {
        val iter = list.iterator();
        var i: Int = -1;
        while (iter.hasNext()) {
            i = i + 1;
            val e = iter.next();
            if (e == v)
                return;
            if (e > v) {
                addBefore(list, i, v);
                return;
            }
        }
        list.add(v);
    }

    public static def unionV(mat1: IntMatrix, mat2: IntMatrix): IntMatrix {
        val list = new ArrayList[Int]();
        val n1 = mat1.n;
        val n2 = mat2.n;
        for (var i: Int = 0; i < n1; i++)
            unionInsert(list, mat1.array(i, 0));
        for (var i: Int = 0; i < n2; i++)
            unionInsert(list, mat2.array(i, 0));

        val size = list.size();
        val r = (0..size-1)*(0..1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val i = p(0);
                    return list.get(i);
                }
            )
        );

        return new IntMatrix(array, size, 1);
    }

    public static def unionH(mat1: IntMatrix, mat2: IntMatrix): IntMatrix {
        val list = new ArrayList[Int]();
        val m1 = mat1.m;
        val m2 = mat2.m;
        for (var i: Int = 0; i < m1; i++) {
            unionInsert(list, mat1.array(0, i));
            printList(list);
        }
        for (var i: Int = 0; i < m2; i++) {
            unionInsert(list, mat2.array(0, i));
            printList(list);
        }
        val size = list.size();
        val r = (0..1)*(0..size-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val i = p(1);
                    return list.get(i);
                }
            )
        );

        return new IntMatrix(array, 1, size);
    }
    public static def union(mat1: IntMatrix, mat2: IntMatrix): IntMatrix {
//        if (mat1.n == 0 || mat1.m == 0)
//            return mat2.clone();
//        if (mat2.n == 0 || mat2.m == 0)
//            return mat1.clone();
        if (mat1.n == 1) {
            if (mat2.n == 1 || mat2.m == 0)
                return unionH(mat1, mat2);
            else if (mat2.m == 1)
                return unionH(mat1, mat2.transpose());
            else {
                Console.OUT.println("mat1: " + mat1.n + "x" + mat1.m);
                Console.OUT.println("mat2: " + mat2.n + "x" + mat2.m);
                error("At union: Union on non-vector matrix.");
            }
        } else if (mat1.m == 1) {
            if (mat2.n == 1)
                return unionH(mat1.transpose(), mat2);
            else if (mat2.m == 1 || mat2.m == 0)
                return unionV(mat1, mat2);
            else {
                Console.OUT.println("mat1: " + mat1.n + "x" + mat1.m);
                Console.OUT.println("mat2: " + mat2.n + "x" + mat2.m);
                error("At union: Union on non-vector matrix.");
            }
        } else {
            Console.OUT.println("mat1: " + mat1.n + "x" + mat1.m);
            Console.OUT.println("mat2: " + mat2.n + "x" + mat2.m);
            error("At union: Union on non-vector matrix.");
        }
        return null;
    }

    // union } --------------------------------------------------------------------------

    // pinv { --------------------------------------------------------------------------

    // Wikipedia: http://en.wikipedia.org/wiki/Moore%E2%80%93Penrose_pseudoinverse#Vectors
    // pinv special cases: vector
    // The pseudoinverse of the null (all zero) vector is the transposed null vector.
    // The pseudoinverse of a non-null vector is the conjugate transposed vector divided
    // by its squared magnitude:

    // Wikipedia: http://en.wikipedia.org/wiki/Conjugate_transpose
    // In mathematics, the conjugate transpose, Hermitian transpose, Hermitian conjugate,
    // or adjoint matrix of an m-by-n matrix A with complex entries is the n-by-m matrix
    // A* obtained from A by taking the transpose and then taking the complex conjugate
    // of each entry (i.e., negating their imaginary parts but not their real parts).

    // We do not have imaginary numbers
    public static def singularValueDecomposition (Arg: IntMatrix):
            Tuple3[Array[Double], Array[Double], Array[Double]] {

        // Derived from JAMA code that is
        // Derived from LINPACK code.

        // Initialize.
        var m: Int = Arg.n; //different convention for n and m
        var n: Int = Arg.m;

        val r = (0..m-1)*(0..n-1);
        val A = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                Arg.array(p(0), p(1)) as Double
            )
        );



        /* Apparently the failing cases are only a proper subset of (m<n),
        so let's not throw error.  Correct fix to come later?
        if (m<n) {
        throw new IllegalArgumentException("Jama SVD only works for m >= n"); }
        */
        var nu: Int = Math.min(m, n);

        val sr = (0..Math.min(m+1, n)-1);
        val s/*: Array[Double]*/ = new Array[Double](
            sr, (
                (p:Point(sr.rank))=> {
                    return 0.0;
                }
            )
        );
        val Ur = (0..m-1)*(0..nu-1);
        val U/*: Array[Double]*/ = new Array[Double](
            Ur, (
                (p:Point(Ur.rank))=> {
                    return 0.0;
                }
            )
        );
        val Vr = (0..n-1)*(0..n-1);
        val V/*: Array[Double]*/ = new Array[Double](
            Vr, (
                (p:Point(Vr.rank))=> {
                    return 0.0;
                }
            )
        );


        val er = (0..n-1);
        val e/*: Array[Double] */= new Array[Double](
            er, (
                (p:Point(er.rank))=> {
                    return 0.0;
                }
            )
        );

        val workr = (0..m-1);
        val work/*: Array[Double]*/ = new Array[Double](
            workr, (
                (p:Point(workr.rank))=> {
                    return 0.0;
                }
            )
        );


        var wantu: Boolean = true;
        var wantv: Boolean = true;

        // Reduce A to bidiagonal form, storing the diagonal elements
        // in s and the super-diagonal elements in e.

        var nct: Int = Math.min(m-1, n);
        var nrt: Int = Math.max(0, Math.min(n-2, m));
        for (var k: Int = 0; k < Math.max(nct, nrt); k++) {
            if (k < nct) {

                // Compute the transformation for the k-th column and
                // place the k-th diagonal in s[k].
                // Compute 2-norm of k-th column without under/overflow.
                s(k) = 0.0;
                for (var i: Int = k; i < m; i++)
                    s(k) = hypot(s(k), A(i, k));

                if (s(k) != 0.0) {
                    if (A(k, k) < 0.0)
                        s(k) = -s(k);
                    for (var i: Int = k; i < m; i++)
                        A(i, k) /= s(k);
                    A(k, k) += 1.0;
                }
                s(k) = -s(k);
            }
            for (var j: Int = k+1; j < n; j++) {
                if ((k < nct) && (s(k) != 0.0))  {

                    // Apply the transformation.

                    var t: Double = 0.0;
                    for (var i: Int = k; i < m; i++)
                        t += A(i, k)*A(i, j);

                    t = -t/A(k, k);
                    for (var i: Int = k; i < m; i++)
                        A(i, j) += t * A(i, k);

                }

                // Place the k-th row of A into e for the
                // subsequent calculation of the row transformation.

                e(j) = A(k, j);
            }
            if (wantu && (k < nct)) {

                // Place the transformation in U for subsequent back
                // multiplication.

                for (var i: Int = k; i < m; i++)
                    U(i, k) = A(i, k);
            }
            if (k < nrt) {

                // Compute the k-th row transformation and place the
                // k-th super-diagonal in e[k].
                // Compute 2-norm without under/overflow.
                e(k) = 0;
                for (var i: Int = k+1; i < n; i++)
                    e(k) = hypot(e(k), e(i));

                if (e(k) != 0.0) {
                    if (e(k+1) < 0.0)
                        e(k) = -e(k);

                    for (var i: Int = k+1; i < n; i++)
                        e(i) /= e(k);

                    e(k+1) += 1.0;
                }
                e(k) = -e(k);
                if ((k+1 < m) && (e(k) != 0.0)) {

                    // Apply the transformation.

                    for (var i: Int = k+1; i < m; i++)
                        work(i) = 0.0;

                    for (var j: Int = k+1; j < n; j++)
                        for (var i: Int = k+1; i < m; i++)
                            work(i) += e(j)*A(i, j);

                    for (var j: Int = k+1; j < n; j++) {
                        var t: Double = -e(j)/e(k+1);
                        for (var i: Int = k+1; i < m; i++)
                            A(i, j) += t * work(i);

                    }
                }
                if (wantv) {

                    // Place the transformation in V for subsequent
                    // back multiplication.

                    for (var i: Int = k+1; i < n; i++)
                        V(i, k) = e(i);
                }
            }
        }

        // Set up the final bidiagonal matrix or order p.

        var p: Int = Math.min(n,m+1);
        if (nct < n)
            s(nct) = A(nct, nct);

        if (m < p)
            s(p-1) = 0.0;

        if (nrt+1 < p)
            e(nrt) = A(nrt, p-1);

        e(p-1) = 0.0;

        // If required, generate U.

        if (wantu) {
            for (var j: Int = nct; j < nu; j++) {
                for (var i: Int = 0; i < m; i++)
                    U(i, j) = 0.0;

                U(j, j) = 1.0;
            }
            for (var k: Int = nct-1; k >= 0; k--) {
                if (s(k) != 0.0) {
                    for (var j: Int = k+1; j < nu; j++) {
                        var t: Double = 0;
                        for (var i: Int = k; i < m; i++)
                            t += U(i, k) * U(i, j);

                        t = -t / U(k, k);
                        for (var i: Int = k; i < m; i++)
                            U(i, j) += t * U(i, k);
                    }
                    for (var i: Int = k; i < m; i++)
                        U(i, k) = -U(i, k);

                    U(k, k) = 1.0 + U(k, k);
                    for (var i: Int = 0; i < k-1; i++)
                        U(i, k) = 0.0;

                } else {
                    for (var i: Int = 0; i < m; i++)
                        U(i, k) = 0.0;

                    U(k, k) = 1.0;
                }
            }
        }

        // If required, generate V.

        if (wantv) {
            for (var k: Int = n-1; k >= 0; k--) {
                if ((k < nrt) & (e(k) != 0.0)) {
                    for (var j: Int = k+1; j < nu; j++) {
                        var t: Double = 0;
                        for (var i: Int = k+1; i < n; i++)
                            t += V(i, k) * V(i, j);

                        t = -t / V(k+1, k);
                        for (var i: Int = k+1; i < n; i++)
                            V(i, j) += t * V(i, k);
                    }
                }
                for (var i: Int = 0; i < n; i++)
                    V(i, k) = 0.0;

                V(k, k) = 1.0;
            }
        }

        // Main iteration loop for the singular values.

        var pp: Int = p-1;
        var iter: Int = 0;
        var eps: Double = Math.pow(2.0,-52.0);
        var tiny: Double = Math.pow(2.0,-966.0);
        while (p > 0) {
            var k: Int = 0;
            var kase: Int = 0;


            // Here is where a test for too many iterations would go.

            // This section of the program inspects for
            // negligible elements in the s and e arrays.  On
            // completion the variables kase and k are set as follows.

            // kase = 1     if s(p) and e[k-1] are negligible and k<p
            // kase = 2     if s(k) is negligible and k<p
            // kase = 3     if e[k-1] is negligible, k<p, and
            //              s(k), ..., s(p) are not negligible (qr step).
            // kase = 4     if e(p-1) is negligible (convergence).

            for (k = p-2; k >= -1; k--) {
                if (k == -1)
                    break;
                if (Math.abs(e(k)) <=
                    tiny + eps*(Math.abs(s(k)) + Math.abs(s(k+1)))) {
                    e(k) = 0.0;
                    break;
                }
            }
            if (k == p-2) {
                kase = 4;
            } else {
                var ks: Int = 0;
                for (ks = p-1; ks >= k; ks--) {
                    if (ks == k)
                        break;

                    var t: Double = (ks != p ? Math.abs(e(ks)) : 0.) +
                          (ks != k+1 ? Math.abs(e(ks-1)) : 0.);
                    if (Math.abs(s(ks)) <= tiny + eps*t)  {
                        s(ks) = 0.0;
                        break;
                    }
                }
                if (ks == k) {
                    kase = 3;
                } else if (ks == p-1) {
                    kase = 1;
                } else {
                    kase = 2;
                    k = ks;
                }
            }
            k++;

            // Perform the task indicated by kase.

            switch (kase) {

            // Deflate negligible s(p).

                case 1: {
                   var f: Double = e(p-2);
                   e(p-2) = 0.0;
                   for (var j: Int = p-2; j >= k; j--) {
                      var t: Double = hypot(s(j), f);
                      var cs: Double = s(j) / t;
                      var sn: Double = f / t;
                      s(j) = t;
                      if (j != k) {
                         f = -sn * e(j-1);
                         e(j-1) = cs * e(j-1);
                      }
                      if (wantv) {
                         for (var i: Int = 0; i < n; i++) {
                            t = cs * V(i, j) + sn * V(i, p-1);
                            V(i, p-1) = -sn * V(i, j) + cs * V(i, p-1);
                            V(i, j) = t;
                         }
                      }
                   }
                }
                break;

                // Split at negligible s(k).

                case 2: {
                    var f: Double = e(k-1);
                    e(k-1) = 0.0;
                    for (var j: Int = k; j < p; j++) {
                        var t: Double = hypot(s(j),f);
                        var cs: Double = s(j)/t;
                        var sn: Double = f/t;
                        s(j) = t;
                        f = -sn*e(j);
                        e(j) = cs*e(j);
                        if (wantu) {
                            for (var i: Int = 0; i < m; i++) {
                                t = cs*U(i, j) + sn*U(i, k-1);
                                U(i, k-1) = -sn*U(i, j) + cs*U(i, k-1);
                                U(i, j) = t;
                            }
                        }
                    }
                }
                break;

                // Perform one qr step.

                case 3: {

                    // Calculate the shift.

                    var scale: Double = Math.max(Math.max(Math.max(Math.max(
                           Math.abs(s(p-1)),Math.abs(s(p-2))),Math.abs(e(p-2))),
                           Math.abs(s(k))),Math.abs(e(k)));
                    var sp: Double = s(p-1)/scale;
                    var spm1: Double = s(p-2)/scale;
                    var epm1: Double = e(p-2)/scale;
                    var sk: Double = s(k)/scale;
                    var ek: Double = e(k)/scale;
                    var b: Double = ((spm1 + sp)*(spm1 - sp) + epm1*epm1)/2.0;
                    var c: Double = (sp*epm1)*(sp*epm1);
                    var shift: Double = 0.0;
                    if ((b != 0.0) | (c != 0.0)) {
                        shift = Math.sqrt(b*b + c);
                        if (b < 0.0)
                            shift = -shift;
                        shift = c/(b + shift);
                    }
                    var f: Double = (sk + sp)*(sk - sp) + shift;
                    var g: Double = sk*ek;

                    // Chase zeros.

                    for (var j: Int = k; j < p-1; j++) {
                        var t: Double = hypot(f,g);
                        var cs: Double = f/t;
                        var sn: Double = g/t;
                        if (j != k)
                            e(j-1) = t;

                        f = cs*s(j) + sn*e(j);
                        e(j) = cs*e(j) - sn*s(j);
                        g = sn * s(j+1);
                        s(j+1) = cs * s(j+1);
                        if (wantv) {
                            for (var i: Int = 0; i < n; i++) {
                                t = cs * V(i, j) + sn * V(i, j+1);
                                V(i, j+1) = -sn*V(i, j) + cs*V(i, j+1);
                                V(i, j) = t;
                            }
                        }
                        t = hypot(f,g);
                        cs = f/t;
                        sn = g/t;
                        s(j) = t;
                        f = cs*e(j) + sn*s(j+1);
                        s(j+1) = -sn*e(j) + cs*s(j+1);
                        g = sn*e(j+1);
                        e(j+1) = cs*e(j+1);
                        if (wantu && (j < m-1)) {
                            for (var i: Int = 0; i < m; i++) {
                                t = cs * U(i, j) + sn * U(i, j+1);
                                U(i, j+1) = -sn * U(i, j) + cs * U(i, j+1);
                                U(i, j) = t;
                            }
                        }
                   }
                   e(p-2) = f;
                   iter = iter + 1;
                }
                break;

                // Convergence.

                case 4: {

                    // Make the singular values positive.

                    if (s(k) <= 0.0) {
                        s(k) = (s(k) < 0.0 ? -s(k) : 0.0);
                        if (wantv) {
                            for (var i: Int = 0; i <= pp; i++)
                                V(i, k) = -V(i, k);
                        }
                    }

                    // Order the singular values.

                    while (k < pp) {
                        if (s(k) >= s(k+1))
                            break;

                        var t: Double = s(k);
                        s(k) = s(k+1);
                        s(k+1) = t;
                        if (wantv && (k < n-1)) {
                            for (var i: Int = 0; i < n; i++) {
                                t = V(i, k+1);
                                V(i, k+1) = V(i, k);
                                V(i, k) = t;
                            }
                        }

                        if (wantu && (k < m-1)) {
                            for (var i: Int = 0; i < m; i++) {
                                t = U(i, k+1);
                                U(i, k+1) = U(i, k);
                                U(i, k) = t;
                            }
                        }
                        k++;
                    }
                    iter = 0;
                    p--;
                }
                break;
            }
        }
        return new Tuple3[Array[Double], Array[Double], Array[Double]](U, V, s);
    }

    public static def pinv(mat: IntMatrix): DoubleMatrix {
        if ((mat.n == 1) || (mat.m == 1))
            return pinvVector(mat);
        else
            return pinvMat(mat);
//            error("At pinv: To be implemented. pinv of a non-vector matrix.");
    }

    public static def pinvVector(mat: IntMatrix): DoubleMatrix {
        val n = mat.n;
        val m = mat.m;
        //here
        var s: Double = 0;
        for (var i: Int = 0; i < n; i++)
            for (var j: Int = 0; j < m; j++) {
                val e = mat.array(i, j);
                s += e*e;
            }

        val ss = s;
        val r = (0..m-1)*(0..n-1);
        val a = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                (mat.array(p(1), p(0)) as Double) / ss
            )
        );

        return new DoubleMatrix(a, m, n);
    }

    public static def pinvMat(x: IntMatrix): DoubleMatrix {
      if (x.m > x.n)
          return pinv(x.transpose()).transpose();


        /**
          * The difference between 1 and the smallest exactly representable number
          * greater than one. Gives an upper bound on the relative error due to
          * rounding of floating point numbers.
          */
        val MACHEPS = 2e-16;

        /**
         * Updates MACHEPS for the executing machine.
         */
//        public static double MACHEPS = 2E-16;
//        public static void updateMacheps() {
//           MACHEPS = 1;
//           do
//               MACHEPS /= 2;
//           while (1 + MACHEPS / 2 != 1);
//        }

// SingularValueDecomposition svdX = new SingularValueDecomposition(x);
//  double[] singularValues = svdX.getSingularValues();
//  double tol = Math.max(x.getColumnDimension(), x.getRowDimension()) * singularValues[0] * MACHEPS;
//  double[] singularValueReciprocals = new double[singularValues.length];
//  for (int i = 0; i < singularValues.length; i++)
//   singularValueReciprocals[i] = Math.abs(singularValues[i]) < tol ? 0 : (1.0 / singularValues[i]);
//  double[][] u = svdX.getU().getArray();
//  double[][] v = svdX.getV().getArray();
//  int min = Math.min(x.getColumnDimension(), u[0].length);
//  double[][] inverse = new double[x.getColumnDimension()][x.getRowDimension()];
//  for (int i = 0; i < x.getColumnDimension(); i++)
//   for (int j = 0; j < u.length; j++)
//    for (int k = 0; k < min; k++)
//     inverse[i][j] += v[i][k] * singularValueReciprocals[k] * u[j][k];

        val svdX = singularValueDecomposition(x);
        val singularValues: Array[Double](1) = svdX._3;
        val tol = Math.max(x.m, x.n) * singularValues(0) * MACHEPS;

        val length = singularValues.region.max(0) + 1;
//        Console.OUT.println(singularValues.region.max(0) + 1);
//        Console.OUT.println(singularValues.region.max(1) + 1);
        val sr = (0..length-1);
        val singularValueReciprocals = new Array[Double](sr);
        for (var i: Int = 0; i < length; i++)
            singularValueReciprocals(i) = Math.abs((singularValues(i) < tol) ? 0 : (1.0 / singularValues(i)));

        val u: Array[Double](2) = svdX._1;
        val v: Array[Double](2) = svdX._2;
        val min = Math.min(x.m, u.region.max(1) + 1);
        val inverse/*: Array[Double](2)*/ = new Array[Double]((0..x.m-1)*(0..x.n-1));

        for (var i: Int = 0; i < x.m; i++)
            for (var j: Int = 0; j < u.region.max(0) + 1; j++)
                for (var k: Int = 0; k < min; k++)
                   inverse(i, j) += v(i, k) * singularValueReciprocals(k) * u(j, k);

        return new DoubleMatrix(inverse, x.m, x.n);

    }


    // pinv } --------------------------------------------------------------------------
    // double { --------------------------------------------------------------------------
    public static def double(v: Int): Double {
        return (v as Double);
    }

    public static def double(mat: IntMatrix): DoubleMatrix {
        val n = mat.n;
        val m = mat.m;

        val r = (0..n-1)*(0..m-1);
        val a = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                (mat.array(p(0), p(1)) as Double)
            )
        );
        return new DoubleMatrix(a, n, m);
    }
    // double } --------------------------------------------------------------------------

    // sum { --------------------------------------------------------------------------
    public static def sum(x: IntMatrix): IntMatrix {
        val n = x.n;
        val m = x.m;
        val r = (0..0)*(0..m-1);
        val a = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    var s: Int = 0 as Int;
                    val j = p(1);
                    for (var i: Int = 0; i < n; i++)
                        s += x.array(i, j);
                    return s;
                }
            )
        );

        return new IntMatrix(a, 1, m);
    }

    public static def sum(x: IntMatrix, dim: Int): IntMatrix {
        if (dim == 1)
            return sum(x);
        if (dim != 2)
            error("At sum: Dimension argument sould be either 1 or 2.");
        val n = x.n;
        val m = x.m;
        val r = (0..n-1)*(0..0);
        val a = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    var s: Int = 0 as Int;
                    val i = p(0);
                    for (var j: Int = 0; j < m; j++)
                        s += x.array(i, j);
                    return s;
                }
            )
        );

        return new IntMatrix(a, n, 1);
    }
    // sum } --------------------------------------------------------------------------
    // find { --------------------------------------------------------------------------
    public static def find(x: IntMatrix): IntMatrix {
        val n = x.n;
        val m = x.m;

        val list = new ArrayList[Int]();

        var ind: Int = 0;
        for (var j: Int = 0; j < m; j++)
            for (var i: Int = 0; i < n; i++) {
                ind = ind + 1;
                val elem = x.array(i, j);
                if (elem != 0)
                    list.add(ind);
            }

        val size = list.size();
        if (x.n == 1) {
            val r = (0..0)*(0..size - 1);
            val a = new Array[Int](
                r, (
                    (p:Point(r.rank))=>
                    list.get(p(1))
                )
            );
            return new IntMatrix(a, 1, size);
        } else {
            val r = (0..size-1)*(0..0);
            val a = new Array[Int](
                r, (
                    (p:Point(r.rank))=>
                    list.get(p(0))
                )
            );
            return new IntMatrix(a, size, 1);
        }
    }
    // find } --------------------------------------------------------------------------
    // min and max { --------------------------------------------------------------------------
    public static def max(x: IntMatrix): IntMatrix {
        val n = x.n;
        val m = x.m;
        val r = (0..0)*(0..m-1);
        val a = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val j = p(1);
                    var s: Int = x.array(0, j) as Int;
                    for (var i: Int = 0; i < n; i++) {
                        val elem = x.array(i, j);
                        if (elem > s)
                            s = elem;
                    }
                    return s;
                }
            )
        );

        return new IntMatrix(a, 1, m);
    }

    public static def min(x: IntMatrix): IntMatrix {
        val n = x.n;
        val m = x.m;
        val r = (0..0)*(0..m-1);
        val a = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val j = p(1);
                    var s: Int = x.array(0, j) as Int;
                    for (var i: Int = 0; i < n; i++) {
                        val elem = x.array(i, j);
                        if (elem < s)
                            s = elem;
                    }
                    return s;
                }
            )
        );

        return new IntMatrix(a, 1, m);
    }


    // min and max } --------------------------------------------------------------------------


// End of N-D-TLib.x10


// Beginning of N-D-TLib.x10

    // logical { ------------------------------------------------------------------------------
    public static def logical(x: Double): Boolean {
        return (x != 0);
    }

    public static def logical(x: DoubleMatrix): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](r, (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1)) != 0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    // logical } ------------------------------------------------------------------------------


    // not { ------------------------------------------------------------------------------
    public static def not(x: Double) : Boolean {
        return !(x != 0);
    }

    public static def not(x: DoubleMatrix) : BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](r, (
                (p:Point(r.rank)) =>
                !(x.array(p(0), p(1)) != 0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    // not } ------------------------------------------------------------------------------

    // unary - { ------------------------------------------------------------------------------
    public static def minus(x: Double): Double {
        return -x;
    }

    public static def minus(x: DoubleMatrix): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](r, (
                (p:Point(r.rank)) =>
                - x.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    // unary - } ------------------------------------------------------------------------------
    // * { ------------------------------------------------------------------------------

    public static def times(a: BooleanMatrix, b: DoubleMatrix): DoubleMatrix {
        val rowsA = a.n;
        val columnsA = a.m;
        val rowsB = b.n;
        val columnsB = b.m;

        if (columnsA != rowsB)
            error("At matrix multiplication: Dimension mismatch.");

        val r = (0..rowsA-1)*(0..columnsB-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=> {
                    val i = p(0);
                    val j = p(1);
                    var sum: Double = 0;
                    for (var k: Int = 0; k < columnsA; k++)
                       sum = sum + (a.array(i, k)?(b.array(k, j)):0);

                    return sum;
                }
            )
        );

        return new DoubleMatrix(array, rowsA, columnsB);
    }
    public static def times(a: DoubleMatrix, b: BooleanMatrix): DoubleMatrix {
        val rowsA = a.n;
        val columnsA = a.m;
        val rowsB = b.n;
        val columnsB = b.m;

        if (columnsA != rowsB)
            error("At matrix multiplication: Dimension mismatch.");

        val r = (0..rowsA-1)*(0..columnsB-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=> {
                    val i = p(0);
                    val j = p(1);
                    var sum: Double = 0;
                    for (var k: Int = 0; k < columnsA; k++)
                       sum = sum + (b.array(k, j)?(a.array(i, k)):0);

                    return sum;
                }
            )
        );

        return new DoubleMatrix(array, rowsA, columnsB);
    }
    // * } ------------------------------------------------------------------------------
    // / { ------------------------------------------------------------------------------

    public static def divide(a: DoubleMatrix, b: BooleanMatrix): DoubleMatrix {
        if (a.m != b.m)
            error("At matrix / op: Mismatch of Operand Dimensions.");

        val biArray = invert(b.array);
        val bi = new DoubleMatrix(biArray, 1);
        return times(a, bi);
    }
    public static def divide(a: BooleanMatrix, b: DoubleMatrix): DoubleMatrix {
        if (a.m != b.m)
            error("At matrix / op: Mismatch of Operand Dimensions.");

        val biArray = invert(b.array);
        val bi = new DoubleMatrix(biArray, 1);
        return times(a, bi);
    }

    // / } ------------------------------------------------------------------------------

    // ^ { ------------------------------------------------------------------------------

    public static def power(a: Double, n: Int): Double {
        return (Math.pow(a, n) as Double);
    }

    public static def power(a: DoubleMatrix, n: Int): DoubleMatrix {
        var b: DoubleMatrix = a;
        for (var i: Int = 0; i < n-1; i++)
            b = times(b, a);
        return b;
    }

    // ^ } ------------------------------------------------------------------------------

    public static def sqrt(x: DoubleMatrix): DoubleMatrix {
        val r = (0..x.n-1)*(0..x.m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank)) =>
                Math.sqrt(x.array(p(0), p(1)) as Double)
            )
        );
        return new DoubleMatrix(array, x.n, x.m);
    }
    // norm { ---------------------------------------------------------------------------

    //    When A is a vector:
    //        norm(A,p) returns sum(abs(A).^p)^(1/p), for any 1 <= p <= infinity.
    //        norm(A) returns norm(A,2).
    //        norm(A,inf) returns max(abs(A)).
    //        norm(A,-inf) returns min(abs(A)).


    public static def norm(a: DoubleMatrix): Double {
        val n = a.n;
        val m = a.m;
        if ((n != 1) && (m != 1)) {
//            error("At norm: To be implemented. norm of a non-vector matrix.");
            val s: Array[Double](1) = singularValueDecomposition(a)._3;
            return s(0);
        }
        var s: Double = 0;
        for (var i: Int = 0; i < n; i++)
            for (var j: Int = 0; j < m; j++) {
                val e = Math.abs(a.array(i, j));
                s += e*e;
            }
        return Math.sqrt(s as Double);
    }

    public static def norm(mat: DoubleMatrix, st: String): Double {
        val n = mat.n;
        val m = mat.m;

        if (!st.equals("inf"))
            error("At norm: An int or \'inf\' expected as the second parameter of norm.");
        if ((n != 1) && (m != 1))
            error("At norm: To be implemented. norm of a non-vector matrix.");

        var maxValue: Double = 0 as Double;
        for (var i: Int = 0; i < n; i++)
            for (var j: Int = 0; j < m; j++) {
                val value = Math.abs(mat.array(i, j));
                if (value > maxValue)
                    maxValue = value;
            }

        return maxValue;
    }

    public static def norm(a: DoubleMatrix, c: Int): Double {
        val n = a.n;
        val m = a.m;
        if (c == 1) {
            var f: Double = 0;
            for (var j: Int = 0; j < m; j++) {
                var s: Double = 0;
                for (var i: Int = 0; i < n; i++)
                    s += Math.abs(a.array(i, j));
                f = Math.max(f, s);
            }
            return f;
        } else
            error("At norm: To be implemented.");
        return 0;
    }

    // norm } ---------------------------------------------------------------------------

    // sort { ---------------------------------------------------------------------------
    private static def sortV(a: DoubleMatrix, lo0: Int, hi0: Int) {

        var lo: Int = lo0;
        var hi: Int = hi0;
        if (lo >= hi) {
            return;
        } else if (lo == hi - 1) {

            //sort a two element list by swapping if necessary
            if (a.array(lo, 0) > a.array(hi, 0)) {
                val temp = a.array(lo, 0);
                a.array(lo, 0) = a.array(hi, 0);
                a.array(hi, 0) = temp;
            }
            return;
        }



        //  Pick a pivot and move it out of the way
        val pivot = a.array((lo + hi) / 2, 0);
        a.array((lo + hi) / 2, 0) = a.array(hi, 0);
        a.array(hi, 0) = pivot;

        while (lo < hi) {

             //  Search forward from a[lo] until an element is found that
             //  is greater than the pivot or lo >= hi
             //
            while (a.array(lo, 0) <= pivot && lo < hi)
                lo++;

            //
            //  Search backward from a[hi] until element is found that
            //  is less than the pivot, or lo >= hi

            while (pivot <= a.array(hi, 0) && lo < hi)
                hi--;


            //  Swap elements a[lo] and a[hi]
            if (lo < hi) {
                val temp = a.array(lo, 0);
                a.array(lo, 0) = a.array(hi, 0);
                a.array(hi, 0) = temp;
            }
        }


        //  Put the median in the "center" of the list
        a.array(hi0, 0) = a.array(hi, 0);
        a.array(hi, 0) = pivot;


        //  Recursive calls, elements a[lo0] to a[lo-1] are less than or
        //  equal to pivot, elements a[hi+1] to a[hi0] are greater than
        //  pivot.
        sortV(a, lo0, lo-1);
        sortV(a, hi+1, hi0);
    }

    private static def sortIV(a: DoubleMatrix, index: IntMatrix, lo0: Int, hi0: Int) {

        var lo: Int = lo0;
        var hi: Int = hi0;
        if (lo >= hi) {
            return;
        } else if (lo == hi - 1) {
            /*
             *  sort a two element list by swapping if necessary
             */
            if (a.array(lo, 0) > a.array(hi, 0)) {
                val temp = a.array(lo, 0);
                a.array(lo, 0) = a.array(hi, 0);
                a.array(hi, 0) = temp;
                val temp2 = index.array(lo, 0);
                index.array(lo, 0) = index.array(hi, 0);
                index.array(hi, 0) = temp2;
            }
            return;
        }


        /*
         *  Pick a pivot and move it out of the way
         */
        val pivot = a.array((lo + hi) / 2, 0);
        a.array((lo + hi) / 2, 0) = a.array(hi, 0);
        a.array(hi, 0) = pivot;
        val i = index.array((lo + hi) / 2, 0);
        index.array((lo + hi) / 2, 0) = index.array(hi, 0);
        index.array(hi, 0) = i;

        while (lo < hi) {
            /*
             *  Search forward from a[lo] until an element is found that
             *  is greater than the pivot or lo >= hi
             */
            while (a.array(lo, 0) <= pivot && lo < hi)
                lo++;

            /*
             *  Search backward from a[hi] until element is found that
             *  is less than the pivot, or lo >= hi
             */
            while (pivot <= a.array(hi, 0) && lo < hi)
                hi--;

            /*
             *  Swap elements a[lo] and a[hi]
             */
            if (lo < hi) {
                val temp = a.array(lo, 0);
                a.array(lo, 0) = a.array(hi, 0);
                a.array(hi, 0) = temp;
                val temp2 = index.array(lo, 0);
                index.array(lo, 0) = index.array(hi, 0);
                index.array(hi, 0) = temp2;
            }
        }

        /*
         *  Put the median in the "center" of the list
         */
        a.array(hi0, 0) = a.array(hi, 0);
        a.array(hi, 0) = pivot;

        index.array(hi0, 0) = index.array(hi, 0);
        index.array(hi, 0) = i;

        /*
         *  Recursive calls, elements a[lo0] to a[lo-1] are less than or
         *  equal to pivot, elements a[hi+1] to a[hi0] are greater than
         *  pivot.
         */
        sortIV(a, index, lo0, lo-1);
        sortIV(a, index, hi+1, hi0);
    }
    private static def sortH(a: DoubleMatrix, lo0: Int, hi0: Int) {

        var lo: Int = lo0;
        var hi: Int = hi0;
        if (lo >= hi) {
            return;
        } else if (lo == hi - 1) {
            /*
             *  sort a two element list by swapping if necessary
             */
            if (a.array(0, lo) > a.array(0, hi)) {
                val temp = a.array(0, lo);
                a.array(0, lo) = a.array(0, hi);
                a.array(0, hi) = temp;
            }
            return;
        }


        /*
         *  Pick a pivot and move it out of the way
         */
        val pivot = a.array(0, (lo + hi) / 2);
        a.array(0, (lo + hi) / 2) = a.array(0, hi);
        a.array(0, hi) = pivot;

        while (lo < hi) {
            /*
             *  Search forward from a[lo] until an element is found that
             *  is greater than the pivot or lo >= hi
             */
            while (a.array(0, lo) <= pivot && lo < hi)
                lo++;

            /*
             *  Search backward from a[hi] until element is found that
             *  is less than the pivot, or lo >= hi
             */
            while (pivot <= a.array(0, hi) && lo < hi)
                hi--;

            /*
             *  Swap elements a[lo] and a[hi]
             */
            if (lo < hi) {
                val temp = a.array(0, lo);
                a.array(0, lo) = a.array(0, hi);
                a.array(0, hi) = temp;
            }
        }

        /*
         *  Put the median in the "center" of the list
         */
        a.array(0, hi0) = a.array(0, hi);
        a.array(0, hi) = pivot;

        /*
         *  Recursive calls, elements a[lo0] to a[lo-1] are less than or
         *  equal to pivot, elements a[hi+1] to a[hi0] are greater than
         *  pivot.
         */
        sortH(a, lo0, lo-1);
        sortH(a, hi+1, hi0);
    }

    private static def sortIH(a: DoubleMatrix, index: IntMatrix, lo0: Int, hi0: Int) {

        var lo: Int = lo0;
        var hi: Int = hi0;
        if (lo >= hi) {
            return;
        } else if (lo == hi - 1) {
            /*
             *  sort a two element list by swapping if necessary
             */
            if (a.array(0, lo) > a.array(0, hi)) {
                val temp = a.array(0, lo);
                a.array(0, lo) = a.array(0, hi);
                a.array(0, hi) = temp;
                val temp2 = index.array(0, lo);
                index.array(0, lo) = index.array(0, hi);
                index.array(0, hi) = temp2;
            }
            return;
        }


        /*
         *  Pick a pivot and move it out of the way
         */
        val pivot = a.array((lo + hi) / 2, 0);
        a.array(0, (lo + hi) / 2) = a.array(0, hi);
        a.array(0, hi) = pivot;
        val i = index.array(0, (lo + hi) / 2);
        index.array(0, (lo + hi) / 2) = index.array(0, hi);
        index.array(0, hi) = i;

        while (lo < hi) {
            /*
             *  Search forward from a[lo] until an element is found that
             *  is greater than the pivot or lo >= hi
             */
            while (a.array(0, lo) <= pivot && lo < hi)
                lo++;

            /*
             *  Search backward from a[hi] until element is found that
             *  is less than the pivot, or lo >= hi
             */
            while (pivot <= a.array(0, hi) && lo < hi)
                hi--;

            /*
             *  Swap elements a[lo] and a[hi]
             */
            if (lo < hi) {
                val temp = a.array(0, lo);
                a.array(0, lo) = a.array(0, hi);
                a.array(0, hi) = temp;
                val temp2 = index.array(0, lo);
                index.array(0, lo) = index.array(0, hi);
                index.array(0, hi) = temp2;
            }
        }

        /*
         *  Put the median in the "center" of the list
         */
        a.array(0, hi0) = a.array(0, hi);
        a.array(0, hi) = pivot;

        index.array(0, hi0) = index.array(0, hi);
        index.array(0, hi) = i;

        /*
         *  Recursive calls, elements a[lo0] to a[lo-1] are less than or
         *  equal to pivot, elements a[hi+1] to a[hi0] are greater than
         *  pivot.
         */
        sortIV(a, index, lo0, lo-1);
        sortIV(a, index, hi+1, hi0);
    }




    private static def sortDV(a: DoubleMatrix, lo0: Int, hi0: Int) {
        var lo: Int = lo0;
        var hi: Int = hi0;
        if (lo >= hi) {
            return;
        } else if (lo == hi - 1) {

            //sort a two element list by swapping if necessary
            if (a.array(lo, 0) < a.array(hi, 0)) {
                val temp = a.array(lo, 0);
                a.array(lo, 0) = a.array(hi, 0);
                a.array(hi, 0) = temp;
            }
            return;
        }


        //  Pick a pivot and move it out of the way
        val pivot = a.array((lo + hi) / 2, 0);
        a.array((lo + hi) / 2, 0) = a.array(hi, 0);
        a.array(hi, 0) = pivot;

        while (lo < hi) {

             //  Search forward from a[lo] until an element is found that
             //  is greater than the pivot or lo >= hi
             //
            while (a.array(lo, 0) >= pivot && lo < hi)
                lo++;

            //
            //  Search backward from a[hi] until element is found that
            //  is less than the pivot, or lo >= hi

            while (pivot <= a.array(hi, 0) && lo < hi)
                hi--;


            //  Swap elements a[lo] and a[hi]
            if (lo < hi) {
                val temp = a.array(lo, 0);
                a.array(lo, 0) = a.array(hi, 0);
                a.array(hi, 0) = temp;
            }
        }


        //  Put the median in the "center" of the list
        a.array(hi0, 0) = a.array(hi, 0);
        a.array(hi, 0) = pivot;


        //  Recursive calls, elements a[lo0] to a[lo-1] are less than or
        //  equal to pivot, elements a[hi+1] to a[hi0] are greater than
        //  pivot.
        sortV(a, lo0, lo-1);
        sortV(a, hi+1, hi0);
    }

    private static def sortDIV(a: DoubleMatrix, index: IntMatrix, lo0: Int, hi0: Int) {
        var lo: Int = lo0;
        var hi: Int = hi0;
        if (lo >= hi) {
            return;
        } else if (lo == hi - 1) {
            /*
             *  sort a two element list by swapping if necessary
             */
            if (a.array(lo, 0) < a.array(hi, 0)) {
                val temp = a.array(lo, 0);
                a.array(lo, 0) = a.array(hi, 0);
                a.array(hi, 0) = temp;
                val temp2 = index.array(lo, 0);
                index.array(lo, 0) = index.array(hi, 0);
                index.array(hi, 0) = temp2;
            }
            return;
        }


        /*
         *  Pick a pivot and move it out of the way
         */
        val pivot = a.array((lo + hi) / 2, 0);
        a.array((lo + hi) / 2, 0) = a.array(hi, 0);
        a.array(hi, 0) = pivot;
        val i = index.array((lo + hi) / 2, 0);
        index.array((lo + hi) / 2, 0) = index.array(hi, 0);
        index.array(hi, 0) = i;

        while (lo < hi) {
            /*
             *  Search forward from a[lo] until an element is found that
             *  is greater than the pivot or lo >= hi
             */
            while (a.array(lo, 0) >= pivot && lo < hi)
                lo++;

            /*
             *  Search backward from a[hi] until element is found that
             *  is less than the pivot, or lo >= hi
             */
            while (pivot >= a.array(hi, 0) && lo < hi)
                hi--;

            /*
             *  Swap elements a[lo] and a[hi]
             */
            if (lo < hi) {
                val temp = a.array(lo, 0);
                a.array(lo, 0) = a.array(hi, 0);
                a.array(hi, 0) = temp;
                val temp2 = index.array(lo, 0);
                index.array(lo, 0) = index.array(hi, 0);
                index.array(hi, 0) = temp2;
            }
//            Console.OUT.println(a.transpose());
        }

        /*
         *  Put the median in the "center" of the list
         */
        a.array(hi0, 0) = a.array(hi, 0);
        a.array(hi, 0) = pivot;

        index.array(hi0, 0) = index.array(hi, 0);
        index.array(hi, 0) = i;

//        Console.OUT.println(a.transpose());
        /*
         *  Recursive calls, elements a[lo0] to a[lo-1] are less than or
         *  equal to pivot, elements a[hi+1] to a[hi0] are greater than
         *  pivot.
         */
        sortDIV(a, index, lo0, lo-1);
        sortDIV(a, index, hi+1, hi0);
    }


    private static def sortDH(a: DoubleMatrix, lo0: Int, hi0: Int) {
        var lo: Int = lo0;
        var hi: Int = hi0;
        if (lo >= hi) {
            return;
        } else if (lo == hi - 1) {

            //sort a two element list by swapping if necessary
            if (a.array(0, lo) < a.array(0, hi)) {
                val temp = a.array(0, lo);
                a.array(0, lo) = a.array(0, hi);
                a.array(0, hi) = temp;
            }
            return;
        }


        //  Pick a pivot and move it out of the way
        val pivot = a.array(0, (lo + hi) / 2);
        a.array(0, (lo + hi) / 2) = a.array(0, hi);
        a.array(0, hi) = pivot;

        while (lo < hi) {

             //  Search forward from a[lo] until an element is found that
             //  is greater than the pivot or lo >= hi
             //
            while (a.array(0, lo) >= pivot && lo < hi)
                lo++;

            //
            //  Search backward from a[hi] until element is found that
            //  is less than the pivot, or lo >= hi

            while (pivot <= a.array(0, hi) && lo < hi)
                hi--;


            //  Swap elements a[lo] and a[hi]
            if (lo < hi) {
                val temp = a.array(0, lo);
                a.array(0, lo) = a.array(0, hi);
                a.array(0, hi) = temp;
            }
        }


        //  Put the median in the "center" of the list
        a.array(0, hi0) = a.array(0, hi);
        a.array(0, hi) = pivot;


        //  Recursive calls, elements a[lo0] to a[lo-1] are less than or
        //  equal to pivot, elements a[hi+1] to a[hi0] are greater than
        //  pivot.
        sortDH(a, lo0, lo-1);
        sortDH(a, hi+1, hi0);
    }
    private static def sortDIH(a: DoubleMatrix, index: IntMatrix, lo0: Int, hi0: Int) {

        var lo: Int = lo0;
        var hi: Int = hi0;
        if (lo >= hi) {
            return;
        } else if (lo == hi - 1) {
            /*
             *  sort a two element list by swapping if necessary
             */
            if (a.array(0, lo) < a.array(0, hi)) {
                val temp = a.array(0, lo);
                a.array(0, lo) = a.array(0, hi);
                a.array(0, hi) = temp;
                val temp2 = index.array(0, lo);
                index.array(0, lo) = index.array(0, hi);
                index.array(0, hi) = temp2;
            }
            return;
        }


        /*
         *  Pick a pivot and move it out of the way
         */
        val pivot = a.array(0, (lo + hi) / 2);
        a.array(0, (lo + hi) / 2) = a.array(0, hi);
        a.array(0, hi) = pivot;
        val i = index.array(0, (lo + hi) / 2);
        index.array(0, (lo + hi) / 2) = index.array(0, hi);
        index.array(0, hi) = i;

        while (lo < hi) {
            /*
             *  Search forward from a[lo] until an element is found that
             *  is greater than the pivot or lo >= hi
             */
            while (a.array(0, lo) >= pivot && lo < hi)
                lo++;

            /*
             *  Search backward from a[hi] until element is found that
             *  is less than the pivot, or lo >= hi
             */
            while (pivot >= a.array(0, hi) && lo < hi)
                hi--;

            /*
             *  Swap elements a[lo] and a[hi]
             */
            if (lo < hi) {
                val temp = a.array(0, lo);
                a.array(0, lo) = a.array(0, hi);
                a.array(0, hi) = temp;
                val temp2 = index.array(0, lo);
                index.array(0, lo) = index.array(0, hi);
                index.array(0, hi) = temp2;
            }
        }

        /*
         *  Put the median in the "center" of the list
         */
        a.array(0, hi0) = a.array(0, hi);
        a.array(0, hi) = pivot;

        index.array(0, hi0) = index.array(0, hi);
        index.array(0, hi) = i;

        /*
         *  Recursive calls, elements a[lo0] to a[lo-1] are less than or
         *  equal to pivot, elements a[hi+1] to a[hi0] are greater than
         *  pivot.
         */
        sortDIH(a, index, lo0, lo-1);
        sortDIH(a, index, hi+1, hi0);
    }



    // We have
    //  sort:   Ascending
    //  sortI:  Ascending with indices
    //  sortD:  Descending
    //  sortDI: Descending with indices

    public static def sort(mat: DoubleMatrix): DoubleMatrix {
        val a = mat.clone();
        if (a.n == 1)
            sortH(a, 0, a.m - 1);
        else if (mat.m == 1)
            sortV(a, 0, a.n - 1);
        else
//            sortM(a);
            error("At sort: To be implemented.");
        return a;
    }
    // I for with Index
    public static def sortI(mat: DoubleMatrix): Tuple2[DoubleMatrix, IntMatrix] {
        val a = mat.clone();
        var index: IntMatrix = null;
        if (a.n == 1) {
            error("At sort: To be implemented.");
//            sortIH(a, 0, a.m-1);
        }
        else if (mat.m == 1) {
            index = new IntMatrix(1, a.n).transpose();
            sortIV(a, index, 0, a.n - 1);
        } else
//            sortIM(a);
            error("At sort: To be implemented.");
        return new Tuple2[DoubleMatrix, IntMatrix](a, index);
    }

    // D for descending
    public static def sortD(mat: DoubleMatrix): DoubleMatrix {
        val a = mat.clone();
        if (a.n == 1)
//            sortDH(a, 0, a.m-1);
            error("At sort: To be implemented.");
        else if (mat.m == 1)
            sortDV(a, 0, a.n - 1);
        else
//            sortM(a);
            error("At sort: To be implemented.");
        return a;
    }

    public static def sortDI(mat: DoubleMatrix): Tuple2[DoubleMatrix, IntMatrix] {
        val a = mat.clone();
        var index: IntMatrix = null;
        if (a.n == 1) {
            index = new IntMatrix(1, a.m).transpose();
            sortDIH(a, index, 0, a.m - 1);
        }
        else if (a.m == 1) {
            index = new IntMatrix(1, a.n).transpose();
            sortDIV(a, index, 0, a.n - 1);
        } else {
//            sortDIM(a);
            error("At sort: To be implemented.");
        }
        return new Tuple2[DoubleMatrix, IntMatrix](a, index);
    }
    // sort } ---------------------------------------------------------------------------

    // union { --------------------------------------------------------------------------

    private static def printList(list: ArrayList[Double]): Void {
        val it = list.iterator();
        while (it.hasNext()) {
            val e = it.next();
            Console.OUT.print(e);
            Console.OUT.print("\t");
        }
        Console.OUT.println();
    }

    private static def addBefore(list: ArrayList[Double], index: int, v: Double): Void {
        // addBefore is terribly wrong in the standard library!
        /*
            val a = new ArrayList[Int]();
            a.add(1);
            a.add(2);
            a.add(3);
            a.add(4);
            Console.OUT.println(a.get(0));
            Console.OUT.println(a.get(1));
            Console.OUT.println(a.get(2));
            Console.OUT.println(a.get(3));
            a.addBefore(2, 10);

            Console.OUT.println();

            Console.OUT.println(a.get(0));
            Console.OUT.println(a.get(1));
            Console.OUT.println(a.get(2));
            Console.OUT.println(a.get(3));
            Console.OUT.println(a.get(4));

            1
            2
            3
            4

            1
            2
            10
            3
            3
        */
        val size = list.size();
        list.add(0);
        for (var i: Int = size; i >= index; i--)
            list.set(list.get(i), i+1);
        list.set(v, index);
    }

    private static def unionInsert(list: ArrayList[Double], v: Double): Void {
        val iter = list.iterator();
        var i: Int = -1;
        while (iter.hasNext()) {
            i = i + 1;
            val e = iter.next();
            if (e == v)
                return;
            if (e > v) {
                addBefore(list, i, v);
                return;
            }
        }
        list.add(v);
    }

    public static def unionV(mat1: DoubleMatrix, mat2: DoubleMatrix): DoubleMatrix {
        val list = new ArrayList[Double]();
        val n1 = mat1.n;
        val n2 = mat2.n;
        for (var i: Int = 0; i < n1; i++)
            unionInsert(list, mat1.array(i, 0));
        for (var i: Int = 0; i < n2; i++)
            unionInsert(list, mat2.array(i, 0));

        val size = list.size();
        val r = (0..size-1)*(0..1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=> {
                    val i = p(0);
                    return list.get(i);
                }
            )
        );

        return new DoubleMatrix(array, size, 1);
    }

    public static def unionH(mat1: DoubleMatrix, mat2: DoubleMatrix): DoubleMatrix {
        val list = new ArrayList[Double]();
        val m1 = mat1.m;
        val m2 = mat2.m;
        for (var i: Int = 0; i < m1; i++) {
            unionInsert(list, mat1.array(0, i));
            printList(list);
        }
        for (var i: Int = 0; i < m2; i++) {
            unionInsert(list, mat2.array(0, i));
            printList(list);
        }
        val size = list.size();
        val r = (0..1)*(0..size-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=> {
                    val i = p(1);
                    return list.get(i);
                }
            )
        );

        return new DoubleMatrix(array, 1, size);
    }
    public static def union(mat1: DoubleMatrix, mat2: DoubleMatrix): DoubleMatrix {
//        if (mat1.n == 0 || mat1.m == 0)
//            return mat2.clone();
//        if (mat2.n == 0 || mat2.m == 0)
//            return mat1.clone();
        if (mat1.n == 1) {
            if (mat2.n == 1 || mat2.m == 0)
                return unionH(mat1, mat2);
            else if (mat2.m == 1)
                return unionH(mat1, mat2.transpose());
            else {
                Console.OUT.println("mat1: " + mat1.n + "x" + mat1.m);
                Console.OUT.println("mat2: " + mat2.n + "x" + mat2.m);
                error("At union: Union on non-vector matrix.");
            }
        } else if (mat1.m == 1) {
            if (mat2.n == 1)
                return unionH(mat1.transpose(), mat2);
            else if (mat2.m == 1 || mat2.m == 0)
                return unionV(mat1, mat2);
            else {
                Console.OUT.println("mat1: " + mat1.n + "x" + mat1.m);
                Console.OUT.println("mat2: " + mat2.n + "x" + mat2.m);
                error("At union: Union on non-vector matrix.");
            }
        } else {
            Console.OUT.println("mat1: " + mat1.n + "x" + mat1.m);
            Console.OUT.println("mat2: " + mat2.n + "x" + mat2.m);
            error("At union: Union on non-vector matrix.");
        }
        return null;
    }

    // union } --------------------------------------------------------------------------

    // pinv { --------------------------------------------------------------------------

    // Wikipedia: http://en.wikipedia.org/wiki/Moore%E2%80%93Penrose_pseudoinverse#Vectors
    // pinv special cases: vector
    // The pseudoinverse of the null (all zero) vector is the transposed null vector.
    // The pseudoinverse of a non-null vector is the conjugate transposed vector divided
    // by its squared magnitude:

    // Wikipedia: http://en.wikipedia.org/wiki/Conjugate_transpose
    // In mathematics, the conjugate transpose, Hermitian transpose, Hermitian conjugate,
    // or adjoint matrix of an m-by-n matrix A with complex entries is the n-by-m matrix
    // A* obtained from A by taking the transpose and then taking the complex conjugate
    // of each entry (i.e., negating their imaginary parts but not their real parts).

    // We do not have imaginary numbers
    public static def singularValueDecomposition (Arg: DoubleMatrix):
            Tuple3[Array[Double], Array[Double], Array[Double]] {

        // Derived from JAMA code that is
        // Derived from LINPACK code.

        // Initialize.
        var m: Int = Arg.n; //different convention for n and m
        var n: Int = Arg.m;

        val r = (0..m-1)*(0..n-1);
        val A = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                Arg.array(p(0), p(1)) as Double
            )
        );



        /* Apparently the failing cases are only a proper subset of (m<n),
        so let's not throw error.  Correct fix to come later?
        if (m<n) {
        throw new IllegalArgumentException("Jama SVD only works for m >= n"); }
        */
        var nu: Int = Math.min(m, n);

        val sr = (0..Math.min(m+1, n)-1);
        val s/*: Array[Double]*/ = new Array[Double](
            sr, (
                (p:Point(sr.rank))=> {
                    return 0.0;
                }
            )
        );
        val Ur = (0..m-1)*(0..nu-1);
        val U/*: Array[Double]*/ = new Array[Double](
            Ur, (
                (p:Point(Ur.rank))=> {
                    return 0.0;
                }
            )
        );
        val Vr = (0..n-1)*(0..n-1);
        val V/*: Array[Double]*/ = new Array[Double](
            Vr, (
                (p:Point(Vr.rank))=> {
                    return 0.0;
                }
            )
        );


        val er = (0..n-1);
        val e/*: Array[Double] */= new Array[Double](
            er, (
                (p:Point(er.rank))=> {
                    return 0.0;
                }
            )
        );

        val workr = (0..m-1);
        val work/*: Array[Double]*/ = new Array[Double](
            workr, (
                (p:Point(workr.rank))=> {
                    return 0.0;
                }
            )
        );


        var wantu: Boolean = true;
        var wantv: Boolean = true;

        // Reduce A to bidiagonal form, storing the diagonal elements
        // in s and the super-diagonal elements in e.

        var nct: Int = Math.min(m-1, n);
        var nrt: Int = Math.max(0, Math.min(n-2, m));
        for (var k: Int = 0; k < Math.max(nct, nrt); k++) {
            if (k < nct) {

                // Compute the transformation for the k-th column and
                // place the k-th diagonal in s[k].
                // Compute 2-norm of k-th column without under/overflow.
                s(k) = 0.0;
                for (var i: Int = k; i < m; i++)
                    s(k) = hypot(s(k), A(i, k));

                if (s(k) != 0.0) {
                    if (A(k, k) < 0.0)
                        s(k) = -s(k);
                    for (var i: Int = k; i < m; i++)
                        A(i, k) /= s(k);
                    A(k, k) += 1.0;
                }
                s(k) = -s(k);
            }
            for (var j: Int = k+1; j < n; j++) {
                if ((k < nct) && (s(k) != 0.0))  {

                    // Apply the transformation.

                    var t: Double = 0.0;
                    for (var i: Int = k; i < m; i++)
                        t += A(i, k)*A(i, j);

                    t = -t/A(k, k);
                    for (var i: Int = k; i < m; i++)
                        A(i, j) += t * A(i, k);

                }

                // Place the k-th row of A into e for the
                // subsequent calculation of the row transformation.

                e(j) = A(k, j);
            }
            if (wantu && (k < nct)) {

                // Place the transformation in U for subsequent back
                // multiplication.

                for (var i: Int = k; i < m; i++)
                    U(i, k) = A(i, k);
            }
            if (k < nrt) {

                // Compute the k-th row transformation and place the
                // k-th super-diagonal in e[k].
                // Compute 2-norm without under/overflow.
                e(k) = 0;
                for (var i: Int = k+1; i < n; i++)
                    e(k) = hypot(e(k), e(i));

                if (e(k) != 0.0) {
                    if (e(k+1) < 0.0)
                        e(k) = -e(k);

                    for (var i: Int = k+1; i < n; i++)
                        e(i) /= e(k);

                    e(k+1) += 1.0;
                }
                e(k) = -e(k);
                if ((k+1 < m) && (e(k) != 0.0)) {

                    // Apply the transformation.

                    for (var i: Int = k+1; i < m; i++)
                        work(i) = 0.0;

                    for (var j: Int = k+1; j < n; j++)
                        for (var i: Int = k+1; i < m; i++)
                            work(i) += e(j)*A(i, j);

                    for (var j: Int = k+1; j < n; j++) {
                        var t: Double = -e(j)/e(k+1);
                        for (var i: Int = k+1; i < m; i++)
                            A(i, j) += t * work(i);

                    }
                }
                if (wantv) {

                    // Place the transformation in V for subsequent
                    // back multiplication.

                    for (var i: Int = k+1; i < n; i++)
                        V(i, k) = e(i);
                }
            }
        }

        // Set up the final bidiagonal matrix or order p.

        var p: Int = Math.min(n,m+1);
        if (nct < n)
            s(nct) = A(nct, nct);

        if (m < p)
            s(p-1) = 0.0;

        if (nrt+1 < p)
            e(nrt) = A(nrt, p-1);

        e(p-1) = 0.0;

        // If required, generate U.

        if (wantu) {
            for (var j: Int = nct; j < nu; j++) {
                for (var i: Int = 0; i < m; i++)
                    U(i, j) = 0.0;

                U(j, j) = 1.0;
            }
            for (var k: Int = nct-1; k >= 0; k--) {
                if (s(k) != 0.0) {
                    for (var j: Int = k+1; j < nu; j++) {
                        var t: Double = 0;
                        for (var i: Int = k; i < m; i++)
                            t += U(i, k) * U(i, j);

                        t = -t / U(k, k);
                        for (var i: Int = k; i < m; i++)
                            U(i, j) += t * U(i, k);
                    }
                    for (var i: Int = k; i < m; i++)
                        U(i, k) = -U(i, k);

                    U(k, k) = 1.0 + U(k, k);
                    for (var i: Int = 0; i < k-1; i++)
                        U(i, k) = 0.0;

                } else {
                    for (var i: Int = 0; i < m; i++)
                        U(i, k) = 0.0;

                    U(k, k) = 1.0;
                }
            }
        }

        // If required, generate V.

        if (wantv) {
            for (var k: Int = n-1; k >= 0; k--) {
                if ((k < nrt) & (e(k) != 0.0)) {
                    for (var j: Int = k+1; j < nu; j++) {
                        var t: Double = 0;
                        for (var i: Int = k+1; i < n; i++)
                            t += V(i, k) * V(i, j);

                        t = -t / V(k+1, k);
                        for (var i: Int = k+1; i < n; i++)
                            V(i, j) += t * V(i, k);
                    }
                }
                for (var i: Int = 0; i < n; i++)
                    V(i, k) = 0.0;

                V(k, k) = 1.0;
            }
        }

        // Main iteration loop for the singular values.

        var pp: Int = p-1;
        var iter: Int = 0;
        var eps: Double = Math.pow(2.0,-52.0);
        var tiny: Double = Math.pow(2.0,-966.0);
        while (p > 0) {
            var k: Int = 0;
            var kase: Int = 0;


            // Here is where a test for too many iterations would go.

            // This section of the program inspects for
            // negligible elements in the s and e arrays.  On
            // completion the variables kase and k are set as follows.

            // kase = 1     if s(p) and e[k-1] are negligible and k<p
            // kase = 2     if s(k) is negligible and k<p
            // kase = 3     if e[k-1] is negligible, k<p, and
            //              s(k), ..., s(p) are not negligible (qr step).
            // kase = 4     if e(p-1) is negligible (convergence).

            for (k = p-2; k >= -1; k--) {
                if (k == -1)
                    break;
                if (Math.abs(e(k)) <=
                    tiny + eps*(Math.abs(s(k)) + Math.abs(s(k+1)))) {
                    e(k) = 0.0;
                    break;
                }
            }
            if (k == p-2) {
                kase = 4;
            } else {
                var ks: Int = 0;
                for (ks = p-1; ks >= k; ks--) {
                    if (ks == k)
                        break;

                    var t: Double = (ks != p ? Math.abs(e(ks)) : 0.) +
                          (ks != k+1 ? Math.abs(e(ks-1)) : 0.);
                    if (Math.abs(s(ks)) <= tiny + eps*t)  {
                        s(ks) = 0.0;
                        break;
                    }
                }
                if (ks == k) {
                    kase = 3;
                } else if (ks == p-1) {
                    kase = 1;
                } else {
                    kase = 2;
                    k = ks;
                }
            }
            k++;

            // Perform the task indicated by kase.

            switch (kase) {

            // Deflate negligible s(p).

                case 1: {
                   var f: Double = e(p-2);
                   e(p-2) = 0.0;
                   for (var j: Int = p-2; j >= k; j--) {
                      var t: Double = hypot(s(j), f);
                      var cs: Double = s(j) / t;
                      var sn: Double = f / t;
                      s(j) = t;
                      if (j != k) {
                         f = -sn * e(j-1);
                         e(j-1) = cs * e(j-1);
                      }
                      if (wantv) {
                         for (var i: Int = 0; i < n; i++) {
                            t = cs * V(i, j) + sn * V(i, p-1);
                            V(i, p-1) = -sn * V(i, j) + cs * V(i, p-1);
                            V(i, j) = t;
                         }
                      }
                   }
                }
                break;

                // Split at negligible s(k).

                case 2: {
                    var f: Double = e(k-1);
                    e(k-1) = 0.0;
                    for (var j: Int = k; j < p; j++) {
                        var t: Double = hypot(s(j),f);
                        var cs: Double = s(j)/t;
                        var sn: Double = f/t;
                        s(j) = t;
                        f = -sn*e(j);
                        e(j) = cs*e(j);
                        if (wantu) {
                            for (var i: Int = 0; i < m; i++) {
                                t = cs*U(i, j) + sn*U(i, k-1);
                                U(i, k-1) = -sn*U(i, j) + cs*U(i, k-1);
                                U(i, j) = t;
                            }
                        }
                    }
                }
                break;

                // Perform one qr step.

                case 3: {

                    // Calculate the shift.

                    var scale: Double = Math.max(Math.max(Math.max(Math.max(
                           Math.abs(s(p-1)),Math.abs(s(p-2))),Math.abs(e(p-2))),
                           Math.abs(s(k))),Math.abs(e(k)));
                    var sp: Double = s(p-1)/scale;
                    var spm1: Double = s(p-2)/scale;
                    var epm1: Double = e(p-2)/scale;
                    var sk: Double = s(k)/scale;
                    var ek: Double = e(k)/scale;
                    var b: Double = ((spm1 + sp)*(spm1 - sp) + epm1*epm1)/2.0;
                    var c: Double = (sp*epm1)*(sp*epm1);
                    var shift: Double = 0.0;
                    if ((b != 0.0) | (c != 0.0)) {
                        shift = Math.sqrt(b*b + c);
                        if (b < 0.0)
                            shift = -shift;
                        shift = c/(b + shift);
                    }
                    var f: Double = (sk + sp)*(sk - sp) + shift;
                    var g: Double = sk*ek;

                    // Chase zeros.

                    for (var j: Int = k; j < p-1; j++) {
                        var t: Double = hypot(f,g);
                        var cs: Double = f/t;
                        var sn: Double = g/t;
                        if (j != k)
                            e(j-1) = t;

                        f = cs*s(j) + sn*e(j);
                        e(j) = cs*e(j) - sn*s(j);
                        g = sn * s(j+1);
                        s(j+1) = cs * s(j+1);
                        if (wantv) {
                            for (var i: Int = 0; i < n; i++) {
                                t = cs * V(i, j) + sn * V(i, j+1);
                                V(i, j+1) = -sn*V(i, j) + cs*V(i, j+1);
                                V(i, j) = t;
                            }
                        }
                        t = hypot(f,g);
                        cs = f/t;
                        sn = g/t;
                        s(j) = t;
                        f = cs*e(j) + sn*s(j+1);
                        s(j+1) = -sn*e(j) + cs*s(j+1);
                        g = sn*e(j+1);
                        e(j+1) = cs*e(j+1);
                        if (wantu && (j < m-1)) {
                            for (var i: Int = 0; i < m; i++) {
                                t = cs * U(i, j) + sn * U(i, j+1);
                                U(i, j+1) = -sn * U(i, j) + cs * U(i, j+1);
                                U(i, j) = t;
                            }
                        }
                   }
                   e(p-2) = f;
                   iter = iter + 1;
                }
                break;

                // Convergence.

                case 4: {

                    // Make the singular values positive.

                    if (s(k) <= 0.0) {
                        s(k) = (s(k) < 0.0 ? -s(k) : 0.0);
                        if (wantv) {
                            for (var i: Int = 0; i <= pp; i++)
                                V(i, k) = -V(i, k);
                        }
                    }

                    // Order the singular values.

                    while (k < pp) {
                        if (s(k) >= s(k+1))
                            break;

                        var t: Double = s(k);
                        s(k) = s(k+1);
                        s(k+1) = t;
                        if (wantv && (k < n-1)) {
                            for (var i: Int = 0; i < n; i++) {
                                t = V(i, k+1);
                                V(i, k+1) = V(i, k);
                                V(i, k) = t;
                            }
                        }

                        if (wantu && (k < m-1)) {
                            for (var i: Int = 0; i < m; i++) {
                                t = U(i, k+1);
                                U(i, k+1) = U(i, k);
                                U(i, k) = t;
                            }
                        }
                        k++;
                    }
                    iter = 0;
                    p--;
                }
                break;
            }
        }
        return new Tuple3[Array[Double], Array[Double], Array[Double]](U, V, s);
    }

    public static def pinv(mat: DoubleMatrix): DoubleMatrix {
        if ((mat.n == 1) || (mat.m == 1))
            return pinvVector(mat);
        else
            return pinvMat(mat);
//            error("At pinv: To be implemented. pinv of a non-vector matrix.");
    }

    public static def pinvVector(mat: DoubleMatrix): DoubleMatrix {
        val n = mat.n;
        val m = mat.m;
        //here
        var s: Double = 0;
        for (var i: Int = 0; i < n; i++)
            for (var j: Int = 0; j < m; j++) {
                val e = mat.array(i, j);
                s += e*e;
            }

        val ss = s;
        val r = (0..m-1)*(0..n-1);
        val a = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                (mat.array(p(1), p(0)) as Double) / ss
            )
        );

        return new DoubleMatrix(a, m, n);
    }

    public static def pinvMat(x: DoubleMatrix): DoubleMatrix {
      if (x.m > x.n)
          return pinv(x.transpose()).transpose();


        /**
          * The difference between 1 and the smallest exactly representable number
          * greater than one. Gives an upper bound on the relative error due to
          * rounding of floating point numbers.
          */
        val MACHEPS = 2e-16;

        /**
         * Updates MACHEPS for the executing machine.
         */
//        public static double MACHEPS = 2E-16;
//        public static void updateMacheps() {
//           MACHEPS = 1;
//           do
//               MACHEPS /= 2;
//           while (1 + MACHEPS / 2 != 1);
//        }

// SingularValueDecomposition svdX = new SingularValueDecomposition(x);
//  double[] singularValues = svdX.getSingularValues();
//  double tol = Math.max(x.getColumnDimension(), x.getRowDimension()) * singularValues[0] * MACHEPS;
//  double[] singularValueReciprocals = new double[singularValues.length];
//  for (int i = 0; i < singularValues.length; i++)
//   singularValueReciprocals[i] = Math.abs(singularValues[i]) < tol ? 0 : (1.0 / singularValues[i]);
//  double[][] u = svdX.getU().getArray();
//  double[][] v = svdX.getV().getArray();
//  int min = Math.min(x.getColumnDimension(), u[0].length);
//  double[][] inverse = new double[x.getColumnDimension()][x.getRowDimension()];
//  for (int i = 0; i < x.getColumnDimension(); i++)
//   for (int j = 0; j < u.length; j++)
//    for (int k = 0; k < min; k++)
//     inverse[i][j] += v[i][k] * singularValueReciprocals[k] * u[j][k];

        val svdX = singularValueDecomposition(x);
        val singularValues: Array[Double](1) = svdX._3;
        val tol = Math.max(x.m, x.n) * singularValues(0) * MACHEPS;

        val length = singularValues.region.max(0) + 1;
//        Console.OUT.println(singularValues.region.max(0) + 1);
//        Console.OUT.println(singularValues.region.max(1) + 1);
        val sr = (0..length-1);
        val singularValueReciprocals = new Array[Double](sr);
        for (var i: Int = 0; i < length; i++)
            singularValueReciprocals(i) = Math.abs((singularValues(i) < tol) ? 0 : (1.0 / singularValues(i)));

        val u: Array[Double](2) = svdX._1;
        val v: Array[Double](2) = svdX._2;
        val min = Math.min(x.m, u.region.max(1) + 1);
        val inverse/*: Array[Double](2)*/ = new Array[Double]((0..x.m-1)*(0..x.n-1));

        for (var i: Int = 0; i < x.m; i++)
            for (var j: Int = 0; j < u.region.max(0) + 1; j++)
                for (var k: Int = 0; k < min; k++)
                   inverse(i, j) += v(i, k) * singularValueReciprocals(k) * u(j, k);

        return new DoubleMatrix(inverse, x.m, x.n);

    }


    // pinv } --------------------------------------------------------------------------
    // double { --------------------------------------------------------------------------
    public static def double(v: Double): Double {
        return (v as Double);
    }

    public static def double(mat: DoubleMatrix): DoubleMatrix {
        val n = mat.n;
        val m = mat.m;

        val r = (0..n-1)*(0..m-1);
        val a = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                (mat.array(p(0), p(1)) as Double)
            )
        );
        return new DoubleMatrix(a, n, m);
    }
    // double } --------------------------------------------------------------------------

    // sum { --------------------------------------------------------------------------
    public static def sum(x: DoubleMatrix): DoubleMatrix {
        val n = x.n;
        val m = x.m;
        val r = (0..0)*(0..m-1);
        val a = new Array[Double](
            r, (
                (p:Point(r.rank))=> {
                    var s: Double = 0 as Double;
                    val j = p(1);
                    for (var i: Int = 0; i < n; i++)
                        s += x.array(i, j);
                    return s;
                }
            )
        );

        return new DoubleMatrix(a, 1, m);
    }

    public static def sum(x: DoubleMatrix, dim: Int): DoubleMatrix {
        if (dim == 1)
            return sum(x);
        if (dim != 2)
            error("At sum: Dimension argument sould be either 1 or 2.");
        val n = x.n;
        val m = x.m;
        val r = (0..n-1)*(0..0);
        val a = new Array[Double](
            r, (
                (p:Point(r.rank))=> {
                    var s: Double = 0 as Double;
                    val i = p(0);
                    for (var j: Int = 0; j < m; j++)
                        s += x.array(i, j);
                    return s;
                }
            )
        );

        return new DoubleMatrix(a, n, 1);
    }
    // sum } --------------------------------------------------------------------------
    // find { --------------------------------------------------------------------------
    public static def find(x: DoubleMatrix): IntMatrix {
        val n = x.n;
        val m = x.m;

        val list = new ArrayList[Int]();

        var ind: Int = 0;
        for (var j: Int = 0; j < m; j++)
            for (var i: Int = 0; i < n; i++) {
                ind = ind + 1;
                val elem = x.array(i, j);
                if (elem != 0)
                    list.add(ind);
            }

        val size = list.size();
        if (x.n == 1) {
            val r = (0..0)*(0..size - 1);
            val a = new Array[Int](
                r, (
                    (p:Point(r.rank))=>
                    list.get(p(1))
                )
            );
            return new IntMatrix(a, 1, size);
        } else {
            val r = (0..size-1)*(0..0);
            val a = new Array[Int](
                r, (
                    (p:Point(r.rank))=>
                    list.get(p(0))
                )
            );
            return new IntMatrix(a, size, 1);
        }
    }
    // find } --------------------------------------------------------------------------
    // min and max { --------------------------------------------------------------------------
    public static def max(x: DoubleMatrix): DoubleMatrix {
        val n = x.n;
        val m = x.m;
        val r = (0..0)*(0..m-1);
        val a = new Array[Double](
            r, (
                (p:Point(r.rank))=> {
                    val j = p(1);
                    var s: Double = x.array(0, j) as Double;
                    for (var i: Int = 0; i < n; i++) {
                        val elem = x.array(i, j);
                        if (elem > s)
                            s = elem;
                    }
                    return s;
                }
            )
        );

        return new DoubleMatrix(a, 1, m);
    }

    public static def min(x: DoubleMatrix): DoubleMatrix {
        val n = x.n;
        val m = x.m;
        val r = (0..0)*(0..m-1);
        val a = new Array[Double](
            r, (
                (p:Point(r.rank))=> {
                    val j = p(1);
                    var s: Double = x.array(0, j) as Double;
                    for (var i: Int = 0; i < n; i++) {
                        val elem = x.array(i, j);
                        if (elem < s)
                            s = elem;
                    }
                    return s;
                }
            )
        );

        return new DoubleMatrix(a, 1, m);
    }


    // min and max } --------------------------------------------------------------------------


// End of N-D-TLib.x10

