import x10.util.ArrayList;

// Beginning of S-D-TMatrix

class BooleanMatrix(n: Int, m: Int) {
//    static type HVector(i:Boolean) = BooleanMatrix{self.n==1 && self.m==i};
//    static type VVector(i:Boolean) = BooleanMatrix{self.n==i && self.m==1};

    public /*private*/ var array: Array[Boolean](2);

//    public def this(array: Array[Boolean](1)): BooleanMatrix{self.n==1, self.m==array.region.max(0) - array.region.min(0)} {

    public def this() {
        val length = 0;
        property(0, 0);
        this.array = null;
    }
    public def this(array: Array[Boolean](1)) {
//        n = 1;
//        m = array.region.max(0) - array.region.min(0);
        val length = array.region.max(0)-array.region.min(0) + 1;
        property(1, length);
        val r = (0..0)*(0..length - 1);
        this.array = new Array[Boolean](r, (
                (p:Point(r.rank))=>
                array(p(1))
            )
        );
    }

//    public def this(arrays: Array[Array[Boolean](1)](1)) {
    public def this(arrays: Array[Array[Boolean]](1)) {
        val nv = arrays.region.max(0) - arrays.region.min(0) + 1;
        val fArr: Array[Boolean](1) = arrays(0);
        val mv = fArr.region.max(0) - fArr.region.min(0) + 1;
        property(nv, mv);

//        Console.OUT.println(arrays.region.min(0));
//        Console.OUT.println(arrays.region.max(0));
//        Console.OUT.println(n);
//        Console.OUT.println(m);

        val r = (0..n-1)*(0..m-1);
        this.array = new Array[Boolean](r, (
                (p:Point(r.rank))=> {
                    val array1 = arrays(p(0)) as Array[Boolean](1);
                    array1(p(1))
                }
            )
        );
    }

//    public def this(array: Array[Boolean](2)) {
//        this(array, array.region.max(0), array.region.max(1));
//    }

    public def this(array: Array[Boolean](2), dummy: Int) {
        property(array.region.max(0) - array.region.min(0) + 1, array.region.max(1) - array.region.min(1) + 1);
        this.array = array;
    }

    public def this(array: Array[Boolean](2), nv: Int, mv: Int) {
        property(nv, mv);
        this.array = array;
    }



    public def apply(i: Int, j: Int): Boolean {
        return array(i - 1, j - 1);
    }


    public def apply(i: IntMatrix, j: IntMatrix): BooleanMatrix {

        if (i.n == 1)
            if (j.n == 1)
                return applyHH(i, j);
            else if (j.m == 1)
                return applyHV(i, j);
            else
                error("At matrix indexing: indices should be vectors.");
        else if (i.m == 1)
            if (j.n == 1)
                return applyVH(i, j);
            else if (j.m == 1)
                return applyVV(i, j);
            else
                error("At matrix indexing: indices should be vectors.");
        else
            error("At matrix indexing: indices should be vectors.");
        return null;
    }
    public def applyHH(i: IntMatrix, j: IntMatrix): BooleanMatrix {
        var rn: Int = i.m;
        var rm: Int = j.m;

        val r = (0..rn-1)*(0..rm-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=>
                this.array(
                    i.array(0, p(0)) - 1,
                    j.array(0, p(1)) - 1
                )
            )
        );

        return new BooleanMatrix(array, rn, rm);
    }
    public def applyHV(i: IntMatrix, j: IntMatrix): BooleanMatrix {
        var rn: Int = i.m;
        var rm: Int = j.n;

        val r = (0..rn-1)*(0..rm-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=>
                this.array(
                    i.array(0, p(0)) - 1,
                    j.array(p(1), 0) - 1
                )
            )
        );

        return new BooleanMatrix(array, rn, rm);
    }
    public def applyVH(i: IntMatrix, j: IntMatrix): BooleanMatrix {
        var rn: Int = i.n;
        var rm: Int = j.m;

        val r = (0..rn-1)*(0..rm-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=>
                this.array(
                    i.array(p(0), 0) - 1,
                    j.array(0, p(1)) - 1
                )
            )
        );

        return new BooleanMatrix(array, rn, rm);
    }
    public def applyVV(i: IntMatrix, j: IntMatrix): BooleanMatrix {
        var rn: Int = i.n;
        var rm: Int = j.n;

        val r = (0..rn-1)*(0..rm-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=>
                this.array(
                    i.array(p(0), 0) - 1,
                    j.array(p(1), 0) - 1
                )
            )
        );

        return new BooleanMatrix(array, rn, rm);
    }

    public def apply(i: Int, j: IntMatrix): BooleanMatrix {
        var rm: Int = j.m;

        val r = (0..1)*(0..rm-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=>
                this.array(
                    i - 1,
                    j.array(0, p(1)) - 1
                )
            )
        );

        return new BooleanMatrix(array, 1, rm);
    }

    public def apply(i: IntMatrix, j: Int): BooleanMatrix {
        var rn: Int = i.m;

        val r = (0..rn-1)*(0..1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                this.array(
                    i.array(0, p(0)) - 1,
                    j - 1
                )
            )
        );

        return new BooleanMatrix(array, rn, 1);
    }

    public def apply(index: IntMatrix): BooleanMatrix {
        if (this.n == 1) {
            if (index.n == 1)
                return applyHH(index);
            else if (index.m == 1)
                return applyHV(index);
            else
                return applyHM(index);
        } else if (this.m == 1) {
            if (index.n == 1)
                return applyVH(index);
            else if (index.m == 1)
                return applyVV(index);
            else
                return applyVM(index);
        } else
            return applyMM(index);
    }

    public def applyHH(index: IntMatrix): BooleanMatrix {
        var rm: Int = index.m;

        val r = (0..1)*(0..rm-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=> {
                    val li = index.array(
                        0,
                        p(1)
                    );
                    val columnCount = this.m;
                    if ((li > columnCount) || (li < 1))
                        error("At linear indexing: Out of bounds linear index.");
                    return this.array(0, li - 1);
                }
            )
        );
        return new BooleanMatrix(array, 1, rm);
    }
    public def applyHV(index: IntMatrix): BooleanMatrix {
        var rm: Int = index.n;

        val r = (0..1)*(0..rm-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=> {
                    val li = index.array(
                        p(1),
                        0
                    );
                    val columnCount = this.m;
                    if ((li > columnCount) || (li < 1))
                        error("At linear indexing: Out of bounds linear index.");
                    return this.array(0, li - 1);
                }
            )
        );
        return new BooleanMatrix(array, 1, rm);
    }
    public def applyVH(index: IntMatrix): BooleanMatrix {
        var rn: Int = index.m;

        val r = (0..rn-1)*(0..1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=> {
                    val li = index.array(
                        0,
                        p(0)
                    );
                    val rowCount = this.n;
                    if ((li > rowCount) || (li < 1))
                        error("At linear indexing: Out of bounds linear index.");
                    return this.array(li - 1, 0);
                }
            )
        );
        return new BooleanMatrix(array, rn, 1);
    }
    public def applyVV(index: IntMatrix): BooleanMatrix {
        var rn: Int = index.n;

        val r = (0..rn-1)*(0..1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=> {
                    val li = index.array(
                        p(0),
                        0
                    );
                    val rowCount = this.n;
                    if ((li > rowCount) || (li < 1))
                        error("At linear indexing: Out of bounds linear index.");
                    return this.array(li - 1, 0);
                }
            )
        );
        return new BooleanMatrix(array, rn, 1);
    }
    public def applyHM(index: IntMatrix): BooleanMatrix {
        var rn: Int = index.n;
        var rm: Int = index.m;

        val r = (0..rn-1)*(0..rm-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=> {
                    val li = index.array(
                        p(0),
                        p(1)
                    );
                    val columnCount = this.m;
                    if ((li > columnCount) || (li < 1))
                        error("At linear indexing: Out of bounds linear index.");
                    return this.array(0, li - 1);
                }
            )
        );
        return new BooleanMatrix(array, rn, rm);
    }
    public def applyVM(index: IntMatrix): BooleanMatrix {
        var rn: Int = index.n;
        var rm: Int = index.m;

        val r = (0..rn-1)*(0..rm-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=> {
                    val li = index.array(
                        p(0),
                        p(1)
                    );
                    val rowCount = this.n;
                    if ((li > rowCount) || (li < 1))
                        error("At linear indexing: Out of bounds linear index.");
                    return this.array(li - 1, 0);
                }
            )
        );
        return new BooleanMatrix(array, rn, rm);
    }
    public def applyMM(index: IntMatrix): BooleanMatrix {
        var rn: Int = index.n;
        var rm: Int = index.m;

        val r = (0..rn-1)*(0..rm-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
                    val li = index.array(
                        p(0),
                        p(1)
                    );
                    val rowCount = this.n;
                    val columnCount = this.m;
                    if ((li > rowCount * columnCount) || (li < 1))
                        error("At linear indexing: Out of bounds linear index.");

                    val i = (li - 1) % this.n;
                    val j = (li - 1) / this.n;
                    return this.array(i, j);
                }
            )
        );
        return new BooleanMatrix(array, rn, rm);
    }

    public def apply(li: Int): Boolean {
        val rowCount = this.n;
        val columnCount = this.m;
        if ((li > rowCount * columnCount) || (li < 1))
            error("At linear indexing assignment: Out of bounds linear index.");
        val i = (li - 1) % this.n;
        val j = (li - 1) / this.n;
        return array(i, j);
    }

    // Logical Indexing
    public def apply(index: BooleanMatrix): BooleanMatrix {
        if ((n != index.n) || (m != index.m))
            error("At logical indexing: Mismatch of array and index dimensions.");

        val list = new ArrayList[Boolean]();
        for (var j: Int = 0; j < m; j++) {
            for (var i: Int = 0; i < n; i++) {
                if (index.array(i, j))
                    list.add(array(i, j));
            }
        }
        val size = list.size();
        val r = (0..size-1)*(0..1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
                    val i = p(0);
                    return list.get(i);
                }
            )
        );

        return new BooleanMatrix(array, size, 1);
    }


    public def singleColumn(): BooleanMatrix {
        val r = (0..n*m-1)*(0..1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
                    val li = p(0);

                    val i = li % this.n;
                    val j = li / this.n;
                    return this.array(i, j);
                }
            )
        );
        return new BooleanMatrix(array, n*m, 1);
    }

    // set beg ------------------------------
    // The method is not named update in X10.

    // Simple indexing
    public def set(v: Boolean, i: Int, j: Int): Void {
        array(i - 1, j - 1) = v;
    }

    // Multi indexing
    public def set(v: Boolean, index1: IntMatrix, index2: IntMatrix): Void {
        if ((index1.n != 1) || (index2.n != 1))
            error("At multi indexing assignment: Index arrays should be horizontal.");

        for (var i: Int = 0; i < index1.m; i++)
            for (var j: Int = 0; j < index2.m; j++) {
                array(index1.array(0, i)-1, index2.array(0, j)-1) = v;
            }
    }
    public def set(v: BooleanMatrix, index1: IntMatrix, index2: IntMatrix): Void {
        if ((index1.n != 1) || (index2.n != 1))
            error("At multi indexing assignment: Index arrays should be horizontal.");
        if ((v.n != index1.m) || (v.m != index2.m))
            error("At multi indexing assignment: Dimensions mismatch of indices and right hand side.");
        for (var i: Int = 0; i < index1.m; i++)
            for (var j: Int = 0; j < index2.m; j++) {
                array(index1.array(0, i)-1, index2.array(0, j)-1) = v.array(i, j);
            }
    }

    public def set(v: Boolean, index1: Int, index2: IntMatrix): Void {
        if (index2.n != 1)
            error("At multi indexing assignment: Index arrays should be horizontal.");
        for (var j: Int = 0; j < index2.m; j++)
            array(index1-1, index2.array(0, j)-1) = v;
    }
    public def set(v: BooleanMatrix, index1: Int, index2: IntMatrix): Void {
        if (index2.n != 1)
            error("At multi indexing assignment: Index arrays should be horizontal.");
        if ((v.n != 1) || (v.m != index2.m))
            error("At multi indexing assignment: Dimensions mismatch of indices and right hand side.");

        for (var j: Int = 0; j < index2.m; j++)
            array(index1-1, index2.array(0, j)-1) = v.array(0, j);
    }

    public def set(v: Boolean, index1: IntMatrix, index2: Int): Void {
        if (index1.n != 1)
            error("At multi indexing assignment: Index arrays should be horizontal.");
        for (var i: Int = 0; i < index1.m; i++)
            array(index1.array(0, i)-1, index2-1) = v;
    }
    public def set(v: BooleanMatrix, index1: IntMatrix, index2: Int): Void {
        if (index1.n != 1)
            error("At multi indexing assignment: Index arrays should be horizontal.");
        if ((v.n != index1.m) || (v.m != 1))
            error("At multi indexing assignment: Dimensions mismatch of indices and right hand side.");

        for (var i: Int = 0; i < index1.m; i++)
            array(index1.array(0, i)-1, index2-1) = v.array(i, 0);
    }


    // Linear indexing
    public def set(v: Boolean, index: IntMatrix): Void {
        for (var i: Int = 0; i < index.n; i++)
            for (var j: Int = 0; j < index.m; j++) {
                val li = index.array(i, j);
                val rowCount = this.n;
                val columnCount = this.m;
                if ((li > rowCount * columnCount) || (li < 1))
                    error("At linear indexing assignment: Out of bounds linear index.");
                val ii = (li - 1) % rowCount;
                val jj = (li - 1) / rowCount;
                this.array(ii, jj) = v;
            }
    }

    public def set(v: BooleanMatrix, index: IntMatrix): Void {
        if (v.n == 1 && v.m == 1) {
            set(v(1,1), index);
            return;
        }
        var swap: Boolean = false;
        if (
               ((index.n == 1) && (v.m == 1) && (index.m == v.n))
               ||
               ((index.m == 1) && (v.n == 1) && (index.n == v.m))
           )
            swap = true;

        if (!swap && ((index.n != v.n) || (index.m != v.m))) {
            Console.OUT.println("Index: " + index.n + "x" + index.m);
            Console.OUT.println("Right: " + v.n + "x" + v.m);
            error("At linear indexing assignment: Dimensions mismatch of index and right hand side.");
        }
        for (var i: Int = 0; i < index.n; i++) {
            for (var j: Int = 0; j < index.m; j++) {
                val li = index.array(i, j);
                val rowCount = this.n;
                val columnCount = this.m;
                if ((li > rowCount * columnCount) || (li < 1))
                    error("At linear indexing assignment: Out of bounds linear index.");
                val ii = (li - 1) % rowCount;
                val jj = (li - 1) / rowCount;
                val value = swap ? v.array(j, i) : v.array(i, j);
                this.array(ii, jj) = value;
            }
        }
    }

    // Logical indexing
    public def set(v: Boolean, index: BooleanMatrix): Void {
//        Console.OUT.println("At set logical indexing");
        if ((n != index.n) || (m != index.m))
            error("At logical indexing: Mismatch of array and index dimensions.");

        for (var i: Int = 0; i < n; i++)
            for (var j: Int = 0; j < m; j++)
                if (index.array(i, j)) {
//                    Console.OUT.println(i);
//                    Console.OUT.println(j);
//                    Console.OUT.println("");
                    this.array(i, j) = v;
                }

    }
    public def set(v: BooleanMatrix, index: BooleanMatrix): Void {
        if ((n != index.n) || (m != index.m))
            error("At logical indexing: Mismatch of array and index dimensions.");
        if (v.n != 1)
            error("At logical indexing: Right hand side should be a horizontal matrix.");
        var k: Int = 0;
        for (var j: Int = 0; j < m; j++)
            for (var i: Int = 0; i < n; i++)
                if (index.array(i, j)) {
                    this.array(i, j) = v.array(0, k);
                    k = k + 1;
                }
        if (k != index.m)
            error("At logical indexing: Mismatch between number of true values in the index and the number of columns of the right hand side.");
    }
    // set end ------------------------------

    public def toString(): String {
        var s: String = "";
        for (var i: Int = 0; i < n; i++) {
            for (var j: Int = 0; j < m; j++) {
                s += array(i,j).toString();
                s += "\t";
            }
            if (i != n - 1)
                s += "\n";
        }
        return s;
    }

//    public static def newHVector(array: Array[Boolean](1)) : BooleanMatrix {
//        return new BooleanMatrix(array);
//    }

//    static def newBooleanMatrix(arrays: Array[Array[Boolean](1)](1)) : BooleanMatrix {

//    public static def newBooleanMatrix(arrays: Array[Array[Boolean]](1)) : BooleanMatrix {
//        return new BooleanMatrix(arrays);
//    }

    public def dim(i: Int): Int {
        if (i == 1)
            return n;
        else if (i == 2)
            return m;
        else
            return array.region.max(i-1)-array.region.min(i-1) + 1;
    }

    // For now, we assume that the iterator is used for horizontal (one row) matrices
    public def values(): Iterable[Boolean] {
        return new ValueIterable();
    }

    private class ValueIterable implements Iterable[Boolean] {
        public def iterator(): Iterator[Boolean] {
            return new ValueIterator();
        }
    }
    private class ValueIterator implements Iterator[Boolean] {
        private var index: Int = 0;

        public def hasNext(): Boolean {
            return (index != m);
        }
        public def next(): Boolean {
            var value: Boolean = array(0, index);
            index = index + 1;
            return value;
        }
    }

    // I wonder why we cannot overload for all mmax, vmax
    // I wonder why we cannot have "def mmax(x: BooleanMatrix(np, mp)): HVector(mp)".

    public def clone(): BooleanMatrix {
        val r = (0..n-1)*(0..m-1);
        val a = new Array[Boolean](
            r, (
                (p:Point(r.rank))=>
                array(p(0), p(1))
            )
        );
        return new BooleanMatrix(a, n, m);
    }


    public def transpose(): BooleanMatrix {

        val r = (0..m-1)*(0..n-1);
        val a = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                array(p(1), p(0))
            )
        );

        return new BooleanMatrix(a, m, n);
    }

    public static def error(s: String): Void {
        Console.OUT.println(s);
//        System.exit(1);
        throw new RuntimeException();
    }

// End of S-D-TMatrix

// Beginning of BooleanMatrix


}

// End of BooleanMatrix