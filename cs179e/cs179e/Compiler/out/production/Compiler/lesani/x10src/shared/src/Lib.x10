
// Beginning of Lib.x10
import x10.util.Random;
import x10.util.ArrayList;
import x10.io.File;
import x10.io.IOException;


public class Lib {

    public static def f() {
        return null;
    }


//    public static operator (x: Boolean) ^ (y: Boolean): Boolean {
//        return true;
//    }
//
//    public static operator (x: Boolean) * (y: Boolean): Boolean {
//        return true;
//    }


    public static def error(s: String): Void {
        Console.OUT.println(s);
        throw new RuntimeException();
    }


    //public static operator (x: IntMatrix) | (y: BooleanMatrix): BooleanMatrix {
    //    return null;
    //}

    //public static def max(x: IntMatrix) {
    //    return null;
    //}


    // We do not generate eq and neq from template for more performance.
    // == { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def eq(x: Boolean, y: Boolean): Boolean {
        return x == y;
    }

    // 2. Matrices of the same size
    public static def eq(x: BooleanMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) == y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def eq(x: Boolean, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array =
            new Array[Boolean](
                r,
                (
                    (p:Point(r.rank))=>
                    x == y.array(p(0), p(1))
                )
            );
        return new BooleanMatrix(array, n, m);
    }

    public static def eq(x: BooleanMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array =
            new Array[Boolean](
                r,
                (
                    (p:Point(r.rank))=>
                    x.array(p(0), p(1)) == y
                )
            );

        return new BooleanMatrix(array, n, m);
    }

    // == } ------------------------------------------------------------------------------
    // != { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def neq(x: Boolean, y: Boolean): Boolean {
        return x != y;
    }

    // 2. Matrices of the same size
    public static def neq(x: BooleanMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) != y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def neq(x: Boolean, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array =
            new Array[Boolean](
                r,
                (
                    (p:Point(r.rank))=>
                    x != y.array(p(0), p(1))
                )
            );
        return new BooleanMatrix(array, n, m);
    }

    public static def neq(x: BooleanMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array =
            new Array[Boolean](
                r,
                (
                    (p:Point(r.rank))=>
                    x.array(p(0), p(1)) != y
                )
            );

        return new BooleanMatrix(array, n, m);
    }

    // != } ------------------------------------------------------------------------------



    // not { ------------------------------------------------------------------------------
    public static def not(x: Boolean) : Boolean {
        return !x;
    }
    public static def not(x: BooleanMatrix) : BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](r, (
                (p:Point(r.rank)) =>
                !(x.array(p(0), p(1)))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    // not } ------------------------------------------------------------------------------

    // unary - { ------------------------------------------------------------------------------
    public static def minus(x: Boolean): Int {
        return (x?-1:0);
    }

    public static def minus(x: BooleanMatrix): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](r, (
                (p:Point(r.rank)) =>
                    (x.array(p(0), p(1))?-1:0)
            )
        );

        return new IntMatrix(array, n, m);
    }
    // unary - } ------------------------------------------------------------------------------

    // * { ------------------------------------------------------------------------------

    public static def times(a: BooleanMatrix, b: BooleanMatrix): IntMatrix {
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
                       sum = sum + ((a.array(i, k) && b.array(k, j))?1:0);

                    return sum;
                }
            )
        );
        return new IntMatrix(array, rowsA, columnsB);
    }
    // * } ------------------------------------------------------------------------------
    // / { ------------------------------------------------------------------------------

    public static def divide(a: BooleanMatrix, b: BooleanMatrix): DoubleMatrix {
        if (a.m != b.m)
            error("At matrix / op: Mismatch of Operand Dimensions.");

        val biArray = invert(b.array);
        val bi = new DoubleMatrix(biArray, 1);
        return times(a, bi);
    }

    public static def invert(a: Array[Boolean](2)): Array[Double](2) {
        val n = a.region.max(0)-a.region.min(0) + 1;
        val r = (0..n-1)*(0..n-1);
        val x = new Array[Double](r);
        val b = new Array[Double](r);

        val r2 = (0..n-1);
        val index = new Array[Int](r2);
        for (var i: Int=0; i<n; ++i) b(i, i) = 1;

//        val aa = new Array[Double](r, (
//                (p:Point(r.rank))=> {return a(p(0), p(1));}
//            )
//        );
        val aa = new Array[Double](
            r, (
                (p:Point(r.rank))=> {
                    return (a(p(0), p(1))?1:0) as Double;
                }
            )
        );

        // Transform the matrix into an upper triangle
        gaussian(aa, index);

        // Update the matrix b[i][j] with the ratios stored
        for (var i: Int=0; i<n-1; ++i)
            for (var j: Int=i+1; j<n; ++j)
                for (var k: Int=0; k<n; ++k)
                    b(index(j), k)
                        -= aa(index(j), i) * b(index(i), k);

        // Perform backward substitutions
        for (var i: Int=0; i<n; ++i) {
            x(n-1, i) = b(index(n-1), i) / aa(index(n-1), n-1);
            for (var j: Int=n-2; j>=0; --j) {
                x(j, i) = b(index(j), i);
                for (var k: Int=j+1; k<n; ++k)
                    x(j, i) -= aa(index(j), k) * x(k, i);
                x(j, i) /= aa(index(j), j);
            }
        }
        return x;
    }
    public static def invert(a: Array[Int](2)): Array[Double](2) {
        val n = a.region.max(0)-a.region.min(0) + 1;
        val r = (0..n-1)*(0..n-1);
        val x = new Array[Double](r);
        val b = new Array[Double](r);

        val r2 = (0..n-1);
        val index = new Array[Int](r2);
        for (var i: Int=0; i<n; ++i) b(i, i) = 1;

//        val aa = new Array[Double](r, (
//                (p:Point(r.rank))=> {return a(p(0), p(1));}
//            )
//        );
        val aa = new Array[Double](
            r, (
                (p:Point(r.rank))=> {
                    return a(p(0), p(1)) as Double;
                }
            )
        );

        // Transform the matrix into an upper triangle
        gaussian(aa, index);

        // Update the matrix b[i][j] with the ratios stored
        for (var i: Int=0; i<n-1; ++i)
            for (var j: Int=i+1; j<n; ++j)
                for (var k: Int=0; k<n; ++k)
                    b(index(j), k)
                        -= aa(index(j), i) * b(index(i), k);

        // Perform backward substitutions
        for (var i: Int=0; i<n; ++i) {
            x(n-1, i) = b(index(n-1), i) / aa(index(n-1), n-1);
            for (var j: Int=n-2; j>=0; --j) {
                x(j, i) = b(index(j), i);
                for (var k: Int=j+1; k<n; ++k)
                    x(j, i) -= aa(index(j), k) * x(k, i);
                x(j, i) /= aa(index(j), j);
            }
        }
        return x;
    }
    public static def invert(a: Array[Double](2)): Array[Double](2) {
        val n = a.region.max(0)-a.region.min(0) + 1;
        val r = (0..n-1)*(0..n-1);
        val x = new Array[Double](r);
        val b = new Array[Double](r);

        val r2 = (0..n-1);
        val index = new Array[Int](r2);
        for (var i: Int=0; i<n; ++i) b(i, i) = 1;

//        val aa = new Array[Double](r, (
//                (p:Point(r.rank))=> {return a(p(0), p(1));}
//            )
//        );
        val aa = a;

        // Transform the matrix into an upper triangle
        gaussian(aa, index);

        // Update the matrix b[i][j] with the ratios stored
        for (var i: Int=0; i<n-1; ++i)
            for (var j: Int=i+1; j<n; ++j)
                for (var k: Int=0; k<n; ++k)
                    b(index(j), k)
                        -= aa(index(j), i) * b(index(i), k);

        // Perform backward substitutions
        for (var i: Int=0; i<n; ++i) {
            x(n-1, i) = b(index(n-1), i) / aa(index(n-1), n-1);
            for (var j: Int=n-2; j>=0; --j) {
                x(j, i) = b(index(j), i);
                for (var k: Int=j+1; k<n; ++k)
                    x(j, i) -= aa(index(j), k) * x(k, i);
                x(j, i) /= aa(index(j), j);
            }
        }
        return x;
    }

    // Method to carry out the partial-pivoting Gaussian
    // elimination.  Here index[] stores pivoting order.

    public static def gaussian(a: Array[Double](2), index: Array[Int](1)): Void {
        val n = index.region.max(0)-index.region.min(0) + 1;

        val r2 = (0..n-1);
        val c = new Array[Double](r2);

        // Initialize the index
        for (var i: Int=0; i<n; ++i) index(i) = i;

        // Find the rescaling factors, one from each row
        for (var i:Int=0; i<n; ++i) {
            var c1: Double = 0;
            for (var j:Int=0; j<n; ++j) {
                var c0: Double = Math.abs(a(i, j));
                if (c0 > c1) c1 = c0;
            }
            c(i) = c1;
        }

        // Search the pivoting element from each column
        var k: Int = 0;
        for (var j:Int =0; j<n-1; ++j) {
            var pi1: Double = 0;
            for (var i: Int=j; i<n; ++i) {
                var pi0: Double = Math.abs(a(index(i), j));
                pi0 = pi0 / c(index(i));
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            // Interchange rows according to the pivoting order
            var itmp: Int = index(j);
            index(j) = index(k);
            index(k) = itmp;
            for (var i: Int=j+1; i<n; ++i) {
                var pj: Double = a(index(i), j) / a(index(j), j);

                // Record pivoting ratios below the diagonal
                a(index(i), j) = pj;

                // Modify other elements accordingly
                for (var l: Int =j+1; l<n; ++l)
                    a(index(i), l) = a(index(i), l) - pj * a(index(j), l);
            }
        }
    }
    // / } ------------------------------------------------------------------------------

    // ^ { ------------------------------------------------------------------------------
    public static def power(a: BooleanMatrix, n: Int): IntMatrix {
        val r = (0..a.n-1)*(0..a.m-1);
        var b: IntMatrix = new IntMatrix(
            new Array[Int](
                r, (
                    (p:Point(r.rank))=>
                    a.array(p(0), p(1))?1:0
                )
            ),
            a.n,
            a.m
        );
        for (var i: Int = 0; i < n-1; i++)
            b = times(b, a);
        return b;
    }
    // ^ } ------------------------------------------------------------------------------
    // sqrt { ------------------------------------------------------------------------------
    public static def sqrt(x: Double): Double {
        return Math.sqrt(x);
    }
    // sqrt } ------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------
    private static val random = new Random();
    private static class RandomHolder {
        var haveNextNextGaussian: Boolean = false;
        var nextNextGaussian: Double = 0.0;
    }
    private static val randomHolder = new RandomHolder();
    public static def nextGaussian(): Double {
        if (randomHolder.haveNextNextGaussian) {
            randomHolder.haveNextNextGaussian = false;
            return randomHolder.nextNextGaussian;
        } else {
            var v1: Double = 0.0;
            var v2: Double = 0.0;
            var s: Double = 0.0;
            do {
                v1 = 2 * random.nextDouble() - 1;   // between -1.0 and 1.0
                v2 = 2 * random.nextDouble() - 1;   // between -1.0 and 1.0
                s = v1 * v1 + v2 * v2;
            } while (s >= 1 || s == 0);
            val multiplier = Math.sqrt(-2 * Math.log(s)/s);
            randomHolder.nextNextGaussian = v2 * multiplier;
            randomHolder.haveNextNextGaussian = true;
            return v1 * multiplier;
        }
    }

    public static def randn(n: Int, m: Int): DoubleMatrix {
//        val random = new Random();
        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                nextGaussian()
            )
        );
        return new DoubleMatrix(array, n, m);
    }
    public static def randn(n: Int): DoubleMatrix {
        return randn(n, n);
    }
    public static def randn(size: IntMatrix): DoubleMatrix {
        if ((size.n != 1) || (size.m != 2))
            error("At randn: Input size should be of shape 1x2.");
        return randn(size(1, 1), size(1, 2));
    }


    public static def zeros(n: Int, m: Int): IntMatrix {
        val random = new Random();
        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                0
            )
        );
        return new IntMatrix(array, n, m);
    }
    public static def ones(n: Int, m: Int): IntMatrix {
        val random = new Random();
        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                1
            )
        );
        return new IntMatrix(array, n, m);
    }
    public static def dzeros(n: Int, m: Int): DoubleMatrix {
        val random = new Random();
        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                0.0
            )
        );
        return new DoubleMatrix(array, n, m);
    }
    public static def dones(n: Int, m: Int): DoubleMatrix {
        val random = new Random();
        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                1.0
            )
        );
        return new DoubleMatrix(array, n, m);
    }

    public static def randperm(n: Int): IntMatrix {
        val r = (0..0)*(0..n-1);
        val a = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                p(1) + 1
            )
        );
        val random = new Random();
        for (var i: Int = 0; i < n; i++) {
            val j = (random.nextDouble() * n) as Int;
	        val temp = a(0, j);
	        a(0, j) = a(0, i);
	        a(0, i) = temp;
        }
        return new IntMatrix(a, 1, n);
    }

    // ------------------------------------------------------------------------------

    // ------------------------------------------------------------------------------
    public static def disp(x: String): Void {
        Console.OUT.println(x);
    }
    // ------------------------------------------------------------------------------
    // needed for norm in NDTLib
    private static def hypot(a: Double, b: Double): Double {
        var r: Double = 0.0;
        if (Math.abs(a) > Math.abs(b)) {
            r = b/a;
            r = Math.abs(a)*Math.sqrt(1+r*r);
        } else if (b != 0) {
            r = a/b;
            r = Math.abs(b)*Math.sqrt(1+r*r);
        } else {
            r = 0.0;
        }
        return r;
    }

    // Image IO ------------------------------------------------------------------------------
    public static def readFormatImage(filePathName: String): IntMatrix {
        val input = new File(filePathName);
        val reader = input.openRead();

        val lHeight = reader.read();
        val hHeight = reader.read();
        val lWidth = reader.read();
        val hWidth = reader.read();

        val height = buildInt(lHeight, hHeight);
        val width = buildInt(lWidth, hWidth);

        val region = (0..(height-1)) * (0..(width-1));
        val data = new Array[Int](region);

        for (var j: Int = 0; j < width; j++) {
            for (var i: Int = 0; i < height ; i++) {
                data(i, j) = toUnsigned(reader.read()) as Int;
            }
        }

        reader.close();
        return new IntMatrix(data, height, width);
    }

    public static def writeFormatImage(
        image: IntMatrix,
        filePathName: String)
            /*throws IOException*/ {

        val output = new File(filePathName);
        val writer = output.openWrite();

        val height = image.n;
        val width = image.m;


        val lHeight = low(height);
        val hHeight = high(height);
        val lWidth = low(width);
        val hWidth = high(width);
        // We use little endian.
        writer.write(lHeight);
        writer.write(hHeight);
        writer.write(lWidth);
        writer.write(hWidth);

        var maxV: Double = 0.0;
        for (var j: Int = 0; j < width; j++) {
            for (var i: Int = 0; i < height; i++) {
                val v = image.array(i, j);
                if (v > maxV)
                    maxV = v;
            }
        }

        for (var j: Int = 0; j < width; j++) {
            for (var i: Int = 0; i < height ; i++) {
                val value = ((image.array(i, j) as Double) / maxV) * 255;
                val bValue = value as Byte;
                writer.write(bValue);
            }
        }
        writer.close();
    }
    public static def writeFormatImage(
        image: DoubleMatrix,
        filePathName: String)
            /*throws IOException*/ {

        val output = new File(filePathName);
        val writer = output.openWrite();

        val height = image.n;
        val width = image.m;


        val lHeight = low(height);
        val hHeight = high(height);
        val lWidth = low(width);
        val hWidth = high(width);
        // We use little endian.
        writer.write(lHeight);
        writer.write(hHeight);
        writer.write(lWidth);
        writer.write(hWidth);

        var maxV: Double = 0.0;

        for (var j: Int = 0; j < width; j++) {
            for (var i: Int = 0; i < height; i++) {
                val v = image.array(i, j);
                if (v > maxV)
                    maxV = v;
            }
        }

        for (var j: Int = 0; j < width; j++) {
            for (var i: Int = 0; i < height; i++) {
                val value = (image.array(i, j) * 255) / maxV ;
                val bValue = value as Byte;
                writer.write(bValue);
            }
        }
        writer.close();
    }

    private static def high(i: Int): Byte {
        return (i / 256) as Byte;
    }
    private static def low(i: Int): Byte {
        return (i % 256) as Byte;
    }

    private static def buildInt(low: Byte, high: Byte): Int {
        val uLow = toUnsigned(low);
        val uHigh = toUnsigned(high);
        return uHigh*256 + uLow;
    }

    private static def toUnsigned(theByte: Byte): Int {
        var intValue: Int = theByte;
        if (intValue < 0) {
            intValue = ((theByte & 0x7F) as Int) + 128;
        }
        return intValue;
    }

    // Image IO ------------------------------------------------------------------------------

    // Mat IO ------------------------------------------------------------------------------
    public static def readIntMatrix(filePathName: String): IntMatrix {
        val input = new File(filePathName);
        val reader = input.openRead();

//        val lHeight = reader.read();
//        val hHeight = reader.read();
//        val lWidth = reader.read();
//        val hWidth = reader.read();
//        val height = buildInt(lHeight, hHeight);
//        val width = buildInt(lWidth, hWidth);

        reader.readInt();
        val height = reader.readInt();
        val width = reader.readInt();

        val region = (0..(height-1)) * (0..(width-1));
        val data = new Array[Int](region);

        for (var j: Int = 0; j < width; j++)
            for (var i: Int = 0; i < height; i++) {
                val value = reader.readInt();
                data(i, j) = value;
            }

        reader.close();
        return new IntMatrix(data, height, width);
    }

    public static def readDoubleMatrix(filePathName: String): DoubleMatrix {
        val input = new File(filePathName);
        val reader = input.openRead();

//        val lHeight = reader.read();
//        val hHeight = reader.read();
//        val lWidth = reader.read();
//        val hWidth = reader.read();
//        val height = buildInt(lHeight, hHeight);
//        val width = buildInt(lWidth, hWidth);

        reader.readInt();
        val height = reader.readInt();
        val width = reader.readInt();

        val region = (0..(height-1)) * (0..(width-1));
        val data = new Array[Double](region);

        for (var j: Int = 0; j < width; j++)
            for (var i: Int = 0; i < height; i++) {
                val value = reader.readDouble();
                data(i, j) = value;
            }


        reader.close();
        return new DoubleMatrix(data, height, width);
    }

    public static def writeMatrix(
        mat: IntMatrix,
        filePathName: String)
            /*throws IOException*/ {

        val output = new File(filePathName);
        val writer = output.openWrite();

        val height = mat.n;
        val width = mat.m;

//        val lWidth = low(width);
//        val hWidth = high(width);
//        val lHeight = low(height);
//        val hHeight = high(height);
        // We use little endian.
//        writer.write(lWidth);
//        writer.write(hWidth);
//        writer.write(lHeight);
//        writer.write(hHeight);

        writer.writeInt(1);
        writer.writeInt(height);
        writer.writeInt(width);

        for (var j: Int = 0; j < width; j++)
            for (var i: Int = 0; i < height; i++)
                writer.writeInt(mat.array(i, j));

        writer.close();
    }

    public static def writeMatrix(
        mat: DoubleMatrix,
        filePathName: String)
            /*throws IOException*/ {

        val output = new File(filePathName);
        val writer = output.openWrite();

        val height = mat.n;
        val width = mat.m;


//        val lHeight = low(height);
//        val hHeight = high(height);
//        val lWidth = low(width);
//        val hWidth = high(width);
        // We use little endian.
//        writer.write(lWidth);
//        writer.write(hWidth);
//        writer.write(lHeight);
//        writer.write(hHeight);

        writer.writeInt(2);
        writer.writeInt(height);
        writer.writeInt(width);

        for (var j: Int = 0; j < width; j++)
            for (var i: Int = 0; i < height; i++)
                writer.writeDouble(mat.array(i, j));

        writer.close();
    }

    // Mat IO ------------------------------------------------------------------------------

    // tic, toc { ------------------------------------------------------------------------------
    private static class Holder {
        var startTime: Long = 0 as Long;
    }
    public static val holder = new Holder();

    public static def tic(): Void {
        holder.startTime = System.currentTimeMillis();
    }
    public static def toc(): Double {
        val finishTime = System.currentTimeMillis();
        return ((finishTime - holder.startTime) as Double)/1000;
    }
    // tic, toc } ------------------------------------------------------------------------------

    // find { --------------------------------------------------------------------------
    public static def find(x: BooleanMatrix): IntMatrix {
        val n = x.n;
        val m = x.m;

        val list = new ArrayList[Int]();

        var ind: Int = 0;
        for (var j: Int = 0; j < m; j++)
            for (var i: Int = 0; i < n; i++) {
                ind = ind + 1;
                val elem = x.array(i, j);
                if (elem)
                    list.add(ind);
            }

        val size = list.size();
        if (x.n == 1) {
            val r = (0..0)*(0..size-1);
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

// Closed curly bracket is added later.
//}


// End of Lib.x10




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


// Beginning of N-D-T3RelLib.x10

    // == { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def eq(x: IntMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) == y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def eq(x: Int, y: IntMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x == y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def eq(x: IntMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) == y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // == } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // != { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def neq(x: IntMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) != y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def neq(x: Int, y: IntMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x != y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def neq(x: IntMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) != y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // != } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // < { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def lt(x: IntMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) < y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def lt(x: Int, y: IntMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x < y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def lt(x: IntMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) < y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // < } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // <= { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def lteq(x: IntMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) <= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def lteq(x: Int, y: IntMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x <= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def lteq(x: IntMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) <= y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // <= } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // > { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def gt(x: IntMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) > y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def gt(x: Int, y: IntMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x > y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def gt(x: IntMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) > y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // > } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // >= { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def gteq(x: IntMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) >= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def gteq(x: Int, y: IntMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x >= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def gteq(x: IntMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) >= y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // >= } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // == { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def eq(x: IntMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) == y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def eq(x: Int, y: DoubleMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x == y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def eq(x: IntMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) == y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // == } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // != { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def neq(x: IntMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) != y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def neq(x: Int, y: DoubleMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x != y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def neq(x: IntMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) != y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // != } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // < { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def lt(x: IntMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) < y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def lt(x: Int, y: DoubleMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x < y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def lt(x: IntMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) < y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // < } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // <= { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def lteq(x: IntMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) <= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def lteq(x: Int, y: DoubleMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x <= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def lteq(x: IntMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) <= y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // <= } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // >= { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def gt(x: IntMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) >= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def gt(x: Int, y: DoubleMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x >= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def gt(x: IntMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) >= y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // >= } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // > { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def gteq(x: IntMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) > y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def gteq(x: Int, y: DoubleMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x > y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def gteq(x: IntMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) > y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // > } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // == { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def eq(x: DoubleMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) == y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def eq(x: Double, y: IntMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x == y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def eq(x: DoubleMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) == y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // == } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // != { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def neq(x: DoubleMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) != y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def neq(x: Double, y: IntMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x != y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def neq(x: DoubleMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) != y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // != } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // < { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def lt(x: DoubleMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) < y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def lt(x: Double, y: IntMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x < y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def lt(x: DoubleMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) < y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // < } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // <= { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def lteq(x: DoubleMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) <= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def lteq(x: Double, y: IntMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x <= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def lteq(x: DoubleMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) <= y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // <= } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // >= { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def gt(x: DoubleMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) >= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def gt(x: Double, y: IntMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x >= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def gt(x: DoubleMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) >= y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // >= } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // > { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def gteq(x: DoubleMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) > y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def gteq(x: Double, y: IntMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x > y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def gteq(x: DoubleMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) > y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // > } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // == { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def eq(x: DoubleMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) == y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def eq(x: Double, y: DoubleMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x == y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def eq(x: DoubleMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) == y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // == } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // != { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def neq(x: DoubleMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) != y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def neq(x: Double, y: DoubleMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x != y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def neq(x: DoubleMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) != y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // != } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // < { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def lt(x: DoubleMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) < y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def lt(x: Double, y: DoubleMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x < y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def lt(x: DoubleMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) < y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // < } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // <= { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def lteq(x: DoubleMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) <= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def lteq(x: Double, y: DoubleMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x <= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def lteq(x: DoubleMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) <= y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // <= } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // > { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def gt(x: DoubleMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) > y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def gt(x: Double, y: DoubleMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x > y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def gt(x: DoubleMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) > y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // > } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-T3RelLib.x10

    // >= { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.

    // 2. Matrices of the same size
    public static def gteq(x: DoubleMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) >= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def gteq(x: Double, y: DoubleMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x >= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def gteq(x: DoubleMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) >= y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // >= } ------------------------------------------------------------------------------

// End of N-D-T3RelLib.x10
// Beginning of N-D-TRelLib.x10

    // == { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def eq(x: Boolean, y: Int): Boolean {
        return (x?1:0) == y;
    }

    public static def eq(x: Int, y: Boolean): Boolean {
        return x == (y?1:0);
    }

    // 2. Matrices of the same size
    public static def eq(x: BooleanMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) == y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def eq(x: IntMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) == (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def eq(x: Boolean, y: IntMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x?1:0) == y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def eq(x: Int, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x == (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def eq(x: BooleanMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) == y
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def eq(x: IntMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) == (y?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // == } ------------------------------------------------------------------------------



// End of N-D-TRelLib.x10


// Beginning of N-D-TRelLib.x10

    // != { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def neq(x: Boolean, y: Int): Boolean {
        return (x?1:0) != y;
    }

    public static def neq(x: Int, y: Boolean): Boolean {
        return x != (y?1:0);
    }

    // 2. Matrices of the same size
    public static def neq(x: BooleanMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) != y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def neq(x: IntMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) != (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def neq(x: Boolean, y: IntMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x?1:0) != y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def neq(x: Int, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x != (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def neq(x: BooleanMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) != y
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def neq(x: IntMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) != (y?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // != } ------------------------------------------------------------------------------



// End of N-D-TRelLib.x10


// Beginning of N-D-TRelLib.x10

    // < { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def lt(x: Boolean, y: Int): Boolean {
        return (x?1:0) < y;
    }

    public static def lt(x: Int, y: Boolean): Boolean {
        return x < (y?1:0);
    }

    // 2. Matrices of the same size
    public static def lt(x: BooleanMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) < y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def lt(x: IntMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) < (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def lt(x: Boolean, y: IntMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x?1:0) < y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def lt(x: Int, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x < (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def lt(x: BooleanMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) < y
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def lt(x: IntMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) < (y?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // < } ------------------------------------------------------------------------------



// End of N-D-TRelLib.x10


// Beginning of N-D-TRelLib.x10

    // <= { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def lteq(x: Boolean, y: Int): Boolean {
        return (x?1:0) <= y;
    }

    public static def lteq(x: Int, y: Boolean): Boolean {
        return x <= (y?1:0);
    }

    // 2. Matrices of the same size
    public static def lteq(x: BooleanMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) <= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def lteq(x: IntMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) <= (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def lteq(x: Boolean, y: IntMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x?1:0) <= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def lteq(x: Int, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x <= (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def lteq(x: BooleanMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) <= y
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def lteq(x: IntMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) <= (y?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // <= } ------------------------------------------------------------------------------



// End of N-D-TRelLib.x10


// Beginning of N-D-TRelLib.x10

    // > { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def gt(x: Boolean, y: Int): Boolean {
        return (x?1:0) > y;
    }

    public static def gt(x: Int, y: Boolean): Boolean {
        return x > (y?1:0);
    }

    // 2. Matrices of the same size
    public static def gt(x: BooleanMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) > y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def gt(x: IntMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) > (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def gt(x: Boolean, y: IntMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x?1:0) > y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def gt(x: Int, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x > (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def gt(x: BooleanMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) > y
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def gt(x: IntMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) > (y?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // > } ------------------------------------------------------------------------------



// End of N-D-TRelLib.x10


// Beginning of N-D-TRelLib.x10

    // == { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def eq(x: Boolean, y: Double): Boolean {
        return (x?1:0) == y;
    }

    public static def eq(x: Double, y: Boolean): Boolean {
        return x == (y?1:0);
    }

    // 2. Matrices of the same size
    public static def eq(x: BooleanMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) == y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def eq(x: DoubleMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) == (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def eq(x: Boolean, y: DoubleMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x?1:0) == y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def eq(x: Double, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x == (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def eq(x: BooleanMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) == y
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def eq(x: DoubleMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) == (y?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // == } ------------------------------------------------------------------------------



// End of N-D-TRelLib.x10


// Beginning of N-D-TRelLib.x10

    // != { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def neq(x: Boolean, y: Double): Boolean {
        return (x?1:0) != y;
    }

    public static def neq(x: Double, y: Boolean): Boolean {
        return x != (y?1:0);
    }

    // 2. Matrices of the same size
    public static def neq(x: BooleanMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) != y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def neq(x: DoubleMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) != (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def neq(x: Boolean, y: DoubleMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x?1:0) != y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def neq(x: Double, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x != (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def neq(x: BooleanMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) != y
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def neq(x: DoubleMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) != (y?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // != } ------------------------------------------------------------------------------



// End of N-D-TRelLib.x10


// Beginning of N-D-TRelLib.x10

    // < { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def lt(x: Boolean, y: Double): Boolean {
        return (x?1:0) < y;
    }

    public static def lt(x: Double, y: Boolean): Boolean {
        return x < (y?1:0);
    }

    // 2. Matrices of the same size
    public static def lt(x: BooleanMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) < y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def lt(x: DoubleMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) < (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def lt(x: Boolean, y: DoubleMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x?1:0) < y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def lt(x: Double, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x < (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def lt(x: BooleanMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) < y
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def lt(x: DoubleMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) < (y?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // < } ------------------------------------------------------------------------------



// End of N-D-TRelLib.x10


// Beginning of N-D-TRelLib.x10

    // <= { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def lteq(x: Boolean, y: Double): Boolean {
        return (x?1:0) <= y;
    }

    public static def lteq(x: Double, y: Boolean): Boolean {
        return x <= (y?1:0);
    }

    // 2. Matrices of the same size
    public static def lteq(x: BooleanMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) <= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def lteq(x: DoubleMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) <= (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def lteq(x: Boolean, y: DoubleMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x?1:0) <= y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def lteq(x: Double, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x <= (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def lteq(x: BooleanMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) <= y
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def lteq(x: DoubleMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) <= (y?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // <= } ------------------------------------------------------------------------------



// End of N-D-TRelLib.x10


// Beginning of N-D-TRelLib.x10

    // > { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def gt(x: Boolean, y: Double): Boolean {
        return (x?1:0) > y;
    }

    public static def gt(x: Double, y: Boolean): Boolean {
        return x > (y?1:0);
    }

    // 2. Matrices of the same size
    public static def gt(x: BooleanMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) > y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def gt(x: DoubleMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) > (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def gt(x: Boolean, y: DoubleMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x?1:0) > y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def gt(x: Double, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x > (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def gt(x: BooleanMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) > y
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def gt(x: DoubleMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) > (y?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // > } ------------------------------------------------------------------------------



// End of N-D-TRelLib.x10

// Beginning of RelLib.x10

    // < { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def lt(x: Boolean, y: Boolean): Boolean {
        return (x?1:0) < (y?1:0);
    }

    // 2. Matrices of the same size
    public static def lt(x: BooleanMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) < (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def lt(x: Boolean, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array =
            new Array[Boolean](
                r,
                (
                    (p:Point(r.rank))=>
                    (x?1:0) < (y.array(p(0), p(1))?1:0)
                )
            );
        return new BooleanMatrix(array, n, m);
    }

    public static def lt(x: BooleanMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array =
            new Array[Boolean](
                r,
                (
                    (p:Point(r.rank))=>
                    (x.array(p(0), p(1))?1:0) < (y?1:0)
                )
            );

        return new BooleanMatrix(array, n, m);
    }

    // < } ------------------------------------------------------------------------------

// End of RelLib.x10// Beginning of RelLib.x10

    // <= { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def lteq(x: Boolean, y: Boolean): Boolean {
        return (x?1:0) <= (y?1:0);
    }

    // 2. Matrices of the same size
    public static def lteq(x: BooleanMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) <= (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def lteq(x: Boolean, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array =
            new Array[Boolean](
                r,
                (
                    (p:Point(r.rank))=>
                    (x?1:0) <= (y.array(p(0), p(1))?1:0)
                )
            );
        return new BooleanMatrix(array, n, m);
    }

    public static def lteq(x: BooleanMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array =
            new Array[Boolean](
                r,
                (
                    (p:Point(r.rank))=>
                    (x.array(p(0), p(1))?1:0) <= (y?1:0)
                )
            );

        return new BooleanMatrix(array, n, m);
    }

    // <= } ------------------------------------------------------------------------------

// End of RelLib.x10// Beginning of RelLib.x10

    // > { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def gt(x: Boolean, y: Boolean): Boolean {
        return (x?1:0) > (y?1:0);
    }

    // 2. Matrices of the same size
    public static def gt(x: BooleanMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) > (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def gt(x: Boolean, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array =
            new Array[Boolean](
                r,
                (
                    (p:Point(r.rank))=>
                    (x?1:0) > (y.array(p(0), p(1))?1:0)
                )
            );
        return new BooleanMatrix(array, n, m);
    }

    public static def gt(x: BooleanMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array =
            new Array[Boolean](
                r,
                (
                    (p:Point(r.rank))=>
                    (x.array(p(0), p(1))?1:0) > (y?1:0)
                )
            );

        return new BooleanMatrix(array, n, m);
    }

    // > } ------------------------------------------------------------------------------

// End of RelLib.x10// Beginning of RelLib.x10

    // >= { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def gteq(x: Boolean, y: Boolean): Boolean {
        return (x?1:0) >= (y?1:0);
    }

    // 2. Matrices of the same size
    public static def gteq(x: BooleanMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At == op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r,
            (
                (p:Point(r.rank))=>
                (x.array(p(0), p(1))?1:0) >= (y.array(p(0), p(1))?1:0)
            )
        );

        return new BooleanMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def gteq(x: Boolean, y: BooleanMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array =
            new Array[Boolean](
                r,
                (
                    (p:Point(r.rank))=>
                    (x?1:0) >= (y.array(p(0), p(1))?1:0)
                )
            );
        return new BooleanMatrix(array, n, m);
    }

    public static def gteq(x: BooleanMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array =
            new Array[Boolean](
                r,
                (
                    (p:Point(r.rank))=>
                    (x.array(p(0), p(1))?1:0) >= (y?1:0)
                )
            );

        return new BooleanMatrix(array, n, m);
    }

    // >= } ------------------------------------------------------------------------------

// End of RelLib.x10
// Beginning of N-D-T3LogicLib.x10
    // && { ------------------------------------------------------------------------------
    // 1. Primitive data types, Int and Double.
    public static def and(x: Int, y: Int): Boolean {
//        val b1 = x != 0 ? true : false;
//        val b2 = y != 0 ? true : false;
        var b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 =false;
        var b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        return b1 && b2;
    }

    // 2. Matrices of the same size
    public static def and(x: IntMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At & op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank)) => {
//                    val b1 = (x.array(p(0), p(1)) != 0) ? true : false;
//                    val b2 = (y.array(p(0), p(1)) != 0) ? true : false;
                    var b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                        b2 = true;
                    else
                        b2 = false;

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def and(x: Int, y: IntMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

//        val b1 = x != 0 ? true : false;
        val b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 = false;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
//                    val b2 = y.array(p(0), p(1)) != 0 ? true : false;
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                        b2 = true;
                    else
                        b2 = false;

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def and(x: IntMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

//        val b2 = y != 0 ? true : false;
        val b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
//                    val b1 = x.array(p(0), p(1)) != 0 ? true : false;
                    var b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // && } ------------------------------------------------------------------------------


// End of N-D-T3LogicLib.x10
// Beginning of N-D-T3LogicLib.x10
    // || { ------------------------------------------------------------------------------
    // 1. Primitive data types, Int and Double.
    public static def or(x: Int, y: Int): Boolean {
//        val b1 = x != 0 ? true : false;
//        val b2 = y != 0 ? true : false;
        var b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 =false;
        var b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        return b1 || b2;
    }

    // 2. Matrices of the same size
    public static def or(x: IntMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At & op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank)) => {
//                    val b1 = (x.array(p(0), p(1)) != 0) ? true : false;
//                    val b2 = (y.array(p(0), p(1)) != 0) ? true : false;
                    var b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                        b2 = true;
                    else
                        b2 = false;

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def or(x: Int, y: IntMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

//        val b1 = x != 0 ? true : false;
        val b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 = false;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
//                    val b2 = y.array(p(0), p(1)) != 0 ? true : false;
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                        b2 = true;
                    else
                        b2 = false;

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def or(x: IntMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

//        val b2 = y != 0 ? true : false;
        val b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
//                    val b1 = x.array(p(0), p(1)) != 0 ? true : false;
                    var b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // || } ------------------------------------------------------------------------------


// End of N-D-T3LogicLib.x10
// Beginning of N-D-T3LogicLib.x10
    // && { ------------------------------------------------------------------------------
    // 1. Primitive data types, Int and Double.
    public static def and(x: Int, y: Double): Boolean {
//        val b1 = x != 0 ? true : false;
//        val b2 = y != 0 ? true : false;
        var b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 =false;
        var b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        return b1 && b2;
    }

    // 2. Matrices of the same size
    public static def and(x: IntMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At & op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank)) => {
//                    val b1 = (x.array(p(0), p(1)) != 0) ? true : false;
//                    val b2 = (y.array(p(0), p(1)) != 0) ? true : false;
                    var b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                        b2 = true;
                    else
                        b2 = false;

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def and(x: Int, y: DoubleMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

//        val b1 = x != 0 ? true : false;
        val b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 = false;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
//                    val b2 = y.array(p(0), p(1)) != 0 ? true : false;
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                        b2 = true;
                    else
                        b2 = false;

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def and(x: IntMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

//        val b2 = y != 0 ? true : false;
        val b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
//                    val b1 = x.array(p(0), p(1)) != 0 ? true : false;
                    var b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // && } ------------------------------------------------------------------------------


// End of N-D-T3LogicLib.x10
// Beginning of N-D-T3LogicLib.x10
    // || { ------------------------------------------------------------------------------
    // 1. Primitive data types, Int and Double.
    public static def or(x: Int, y: Double): Boolean {
//        val b1 = x != 0 ? true : false;
//        val b2 = y != 0 ? true : false;
        var b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 =false;
        var b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        return b1 || b2;
    }

    // 2. Matrices of the same size
    public static def or(x: IntMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At & op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank)) => {
//                    val b1 = (x.array(p(0), p(1)) != 0) ? true : false;
//                    val b2 = (y.array(p(0), p(1)) != 0) ? true : false;
                    var b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                        b2 = true;
                    else
                        b2 = false;

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def or(x: Int, y: DoubleMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

//        val b1 = x != 0 ? true : false;
        val b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 = false;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
//                    val b2 = y.array(p(0), p(1)) != 0 ? true : false;
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                        b2 = true;
                    else
                        b2 = false;

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def or(x: IntMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

//        val b2 = y != 0 ? true : false;
        val b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
//                    val b1 = x.array(p(0), p(1)) != 0 ? true : false;
                    var b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // || } ------------------------------------------------------------------------------


// End of N-D-T3LogicLib.x10
// Beginning of N-D-T3LogicLib.x10
    // && { ------------------------------------------------------------------------------
    // 1. Primitive data types, Int and Double.
    public static def and(x: Double, y: Int): Boolean {
//        val b1 = x != 0 ? true : false;
//        val b2 = y != 0 ? true : false;
        var b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 =false;
        var b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        return b1 && b2;
    }

    // 2. Matrices of the same size
    public static def and(x: DoubleMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At & op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank)) => {
//                    val b1 = (x.array(p(0), p(1)) != 0) ? true : false;
//                    val b2 = (y.array(p(0), p(1)) != 0) ? true : false;
                    var b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                        b2 = true;
                    else
                        b2 = false;

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def and(x: Double, y: IntMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

//        val b1 = x != 0 ? true : false;
        val b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 = false;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
//                    val b2 = y.array(p(0), p(1)) != 0 ? true : false;
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                        b2 = true;
                    else
                        b2 = false;

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def and(x: DoubleMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

//        val b2 = y != 0 ? true : false;
        val b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
//                    val b1 = x.array(p(0), p(1)) != 0 ? true : false;
                    var b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // && } ------------------------------------------------------------------------------


// End of N-D-T3LogicLib.x10
// Beginning of N-D-T3LogicLib.x10
    // || { ------------------------------------------------------------------------------
    // 1. Primitive data types, Int and Double.
    public static def or(x: Double, y: Int): Boolean {
//        val b1 = x != 0 ? true : false;
//        val b2 = y != 0 ? true : false;
        var b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 =false;
        var b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        return b1 || b2;
    }

    // 2. Matrices of the same size
    public static def or(x: DoubleMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At & op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank)) => {
//                    val b1 = (x.array(p(0), p(1)) != 0) ? true : false;
//                    val b2 = (y.array(p(0), p(1)) != 0) ? true : false;
                    var b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                        b2 = true;
                    else
                        b2 = false;

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def or(x: Double, y: IntMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

//        val b1 = x != 0 ? true : false;
        val b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 = false;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
//                    val b2 = y.array(p(0), p(1)) != 0 ? true : false;
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                        b2 = true;
                    else
                        b2 = false;

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def or(x: DoubleMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

//        val b2 = y != 0 ? true : false;
        val b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
//                    val b1 = x.array(p(0), p(1)) != 0 ? true : false;
                    var b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // || } ------------------------------------------------------------------------------


// End of N-D-T3LogicLib.x10
// Beginning of N-D-T3LogicLib.x10
    // && { ------------------------------------------------------------------------------
    // 1. Primitive data types, Int and Double.
    public static def and(x: Double, y: Double): Boolean {
//        val b1 = x != 0 ? true : false;
//        val b2 = y != 0 ? true : false;
        var b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 =false;
        var b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        return b1 && b2;
    }

    // 2. Matrices of the same size
    public static def and(x: DoubleMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At & op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank)) => {
//                    val b1 = (x.array(p(0), p(1)) != 0) ? true : false;
//                    val b2 = (y.array(p(0), p(1)) != 0) ? true : false;
                    var b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                        b2 = true;
                    else
                        b2 = false;

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def and(x: Double, y: DoubleMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

//        val b1 = x != 0 ? true : false;
        val b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 = false;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
//                    val b2 = y.array(p(0), p(1)) != 0 ? true : false;
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                        b2 = true;
                    else
                        b2 = false;

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def and(x: DoubleMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

//        val b2 = y != 0 ? true : false;
        val b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
//                    val b1 = x.array(p(0), p(1)) != 0 ? true : false;
                    var b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // && } ------------------------------------------------------------------------------


// End of N-D-T3LogicLib.x10
// Beginning of N-D-T3LogicLib.x10
    // || { ------------------------------------------------------------------------------
    // 1. Primitive data types, Int and Double.
    public static def or(x: Double, y: Double): Boolean {
//        val b1 = x != 0 ? true : false;
//        val b2 = y != 0 ? true : false;
        var b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 =false;
        var b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        return b1 || b2;
    }

    // 2. Matrices of the same size
    public static def or(x: DoubleMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At & op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank)) => {
//                    val b1 = (x.array(p(0), p(1)) != 0) ? true : false;
//                    val b2 = (y.array(p(0), p(1)) != 0) ? true : false;
                    var b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                        b2 = true;
                    else
                        b2 = false;

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def or(x: Double, y: DoubleMatrix): BooleanMatrix {
        val n = y.n;
        val m = y.m;

//        val b1 = x != 0 ? true : false;
        val b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 = false;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
//                    val b2 = y.array(p(0), p(1)) != 0 ? true : false;
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                        b2 = true;
                    else
                        b2 = false;

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def or(x: DoubleMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

//        val b2 = y != 0 ? true : false;
        val b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
//                    val b1 = x.array(p(0), p(1)) != 0 ? true : false;
                    var b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // || } ------------------------------------------------------------------------------


// End of N-D-T3LogicLib.x10
// Beginning of N-D-TLogicLib.x10

    // && { ------------------------------------------------------------------------------
    // 1. Primitive data types, Int and Boolean
    public static def and(x: Int, y: Boolean): Boolean {
        var b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 = false;
        val b2: Boolean = y;

        return b1 && b2;
    }
    public static def and(x: Boolean, y: Int): Boolean {
        val b1: Boolean = x;
        var b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        return b1 && b2;
    }

    // 2. Matrices of the same size
    public static def and(x: IntMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At & op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank)) => {
                    var b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;
                    val b2: Boolean = y.array(p(0), p(1));

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def and(x: BooleanMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At & op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank)) => {
                    val b1: Boolean = x.array(p(0), p(1));
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                        b2 = true;
                    else
                        b2 = false;
                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

/*
    // Note: This part is commented now because the last release of x10 crashes on these.
    // 3. For a Matrix and a primitive
    public static def and(x: Int, y: BooleanMatrix): BooleanMatrix {
        val np = y.n;
        val mp = y.m;

        val b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 = false;

        val r = (0..np-1)*(0..mp-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
                    val b2: Boolean = y.array(p(0), p(1));
                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, np, mp);
    }

    public static def and(x: Boolean, y: IntMatrix): BooleanMatrix {
        val np = y.n;
        val mp = y.m;

        val b1: Boolean = x;


        val r = (0..np-1)*(0..mp-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                      b2 = true;
                    else
                      b2 = false;

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, np, mp);
    }

    public static def and(x: BooleanMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;


        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
                    val b1: Boolean = x.array(p(0), p(1));
                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def and(x: IntMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val b2: Boolean = y;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {


                    val b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }
*/

    // && } ------------------------------------------------------------------------------


// End of N-D-TLogicLib.x10


// Beginning of N-D-TLogicLib.x10

    // || { ------------------------------------------------------------------------------
    // 1. Primitive data types, Int and Boolean
    public static def or(x: Int, y: Boolean): Boolean {
        var b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 = false;
        val b2: Boolean = y;

        return b1 || b2;
    }
    public static def or(x: Boolean, y: Int): Boolean {
        val b1: Boolean = x;
        var b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        return b1 || b2;
    }

    // 2. Matrices of the same size
    public static def or(x: IntMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At & op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank)) => {
                    var b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;
                    val b2: Boolean = y.array(p(0), p(1));

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def or(x: BooleanMatrix, y: IntMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At & op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank)) => {
                    val b1: Boolean = x.array(p(0), p(1));
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                        b2 = true;
                    else
                        b2 = false;
                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

/*
    // Note: This part is commented now because the last release of x10 crashes on these.
    // 3. For a Matrix and a primitive
    public static def or(x: Int, y: BooleanMatrix): BooleanMatrix {
        val np = y.n;
        val mp = y.m;

        val b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 = false;

        val r = (0..np-1)*(0..mp-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
                    val b2: Boolean = y.array(p(0), p(1));
                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, np, mp);
    }

    public static def or(x: Boolean, y: IntMatrix): BooleanMatrix {
        val np = y.n;
        val mp = y.m;

        val b1: Boolean = x;


        val r = (0..np-1)*(0..mp-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                      b2 = true;
                    else
                      b2 = false;

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, np, mp);
    }

    public static def or(x: BooleanMatrix, y: Int): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;


        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
                    val b1: Boolean = x.array(p(0), p(1));
                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def or(x: IntMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val b2: Boolean = y;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {


                    val b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }
*/

    // || } ------------------------------------------------------------------------------


// End of N-D-TLogicLib.x10


// Beginning of N-D-TLogicLib.x10

    // && { ------------------------------------------------------------------------------
    // 1. Primitive data types, Double and Boolean
    public static def and(x: Double, y: Boolean): Boolean {
        var b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 = false;
        val b2: Boolean = y;

        return b1 && b2;
    }
    public static def and(x: Boolean, y: Double): Boolean {
        val b1: Boolean = x;
        var b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        return b1 && b2;
    }

    // 2. Matrices of the same size
    public static def and(x: DoubleMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At & op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank)) => {
                    var b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;
                    val b2: Boolean = y.array(p(0), p(1));

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def and(x: BooleanMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At & op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank)) => {
                    val b1: Boolean = x.array(p(0), p(1));
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                        b2 = true;
                    else
                        b2 = false;
                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

/*
    // Note: This part is commented now because the last release of x10 crashes on these.
    // 3. For a Matrix and a primitive
    public static def and(x: Double, y: BooleanMatrix): BooleanMatrix {
        val np = y.n;
        val mp = y.m;

        val b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 = false;

        val r = (0..np-1)*(0..mp-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
                    val b2: Boolean = y.array(p(0), p(1));
                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, np, mp);
    }

    public static def and(x: Boolean, y: DoubleMatrix): BooleanMatrix {
        val np = y.n;
        val mp = y.m;

        val b1: Boolean = x;


        val r = (0..np-1)*(0..mp-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                      b2 = true;
                    else
                      b2 = false;

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, np, mp);
    }

    public static def and(x: BooleanMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;


        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
                    val b1: Boolean = x.array(p(0), p(1));
                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def and(x: DoubleMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val b2: Boolean = y;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {


                    val b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;

                    return b1 && b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }
*/

    // && } ------------------------------------------------------------------------------


// End of N-D-TLogicLib.x10


// Beginning of N-D-TLogicLib.x10

    // || { ------------------------------------------------------------------------------
    // 1. Primitive data types, Double and Boolean
    public static def or(x: Double, y: Boolean): Boolean {
        var b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 = false;
        val b2: Boolean = y;

        return b1 || b2;
    }
    public static def or(x: Boolean, y: Double): Boolean {
        val b1: Boolean = x;
        var b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;

        return b1 || b2;
    }

    // 2. Matrices of the same size
    public static def or(x: DoubleMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At & op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank)) => {
                    var b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;
                    val b2: Boolean = y.array(p(0), p(1));

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def or(x: BooleanMatrix, y: DoubleMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At & op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank)) => {
                    val b1: Boolean = x.array(p(0), p(1));
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                        b2 = true;
                    else
                        b2 = false;
                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

/*
    // Note: This part is commented now because the last release of x10 crashes on these.
    // 3. For a Matrix and a primitive
    public static def or(x: Double, y: BooleanMatrix): BooleanMatrix {
        val np = y.n;
        val mp = y.m;

        val b1: Boolean;
        if (x != 0)
            b1 = true;
        else
            b1 = false;

        val r = (0..np-1)*(0..mp-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
                    val b2: Boolean = y.array(p(0), p(1));
                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, np, mp);
    }

    public static def or(x: Boolean, y: DoubleMatrix): BooleanMatrix {
        val np = y.n;
        val mp = y.m;

        val b1: Boolean = x;


        val r = (0..np-1)*(0..mp-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
                    var b2: Boolean;
                    if (y.array(p(0), p(1)) != 0)
                      b2 = true;
                    else
                      b2 = false;

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, np, mp);
    }

    public static def or(x: BooleanMatrix, y: Double): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val b2: Boolean;
        if (y != 0)
            b2 = true;
        else
            b2 = false;


        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {
                    val b1: Boolean = x.array(p(0), p(1));
                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    public static def or(x: DoubleMatrix, y: Boolean): BooleanMatrix {
        val n = x.n;
        val m = x.m;

        val b2: Boolean = y;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](
            r, (
                (p:Point(r.rank))=> {


                    val b1: Boolean;
                    if (x.array(p(0), p(1)) != 0)
                        b1 = true;
                    else
                        b1 = false;

                    return b1 || b2;
                }
            )
        );

        return new BooleanMatrix(array, n, m);
    }
*/

    // || } ------------------------------------------------------------------------------


// End of N-D-TLogicLib.x10


// Beginning of LogicLib

    // && { ------------------------------------------------------------------------------

    public static def and(x: Boolean, y: Boolean): Boolean {
        return x && y;
    }

    public static def and(x: BooleanMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At | op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](r, (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) && y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def and(x: Boolean, y: BooleanMatrix): BooleanMatrix {

        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](r, (
                (p:Point(r.rank))=>
                x && y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def and(x: BooleanMatrix, y: Boolean): BooleanMatrix {

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](r, (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) && y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // && } ------------------------------------------------------------------------------

// End of LogicLib

// Beginning of LogicLib

    // || { ------------------------------------------------------------------------------

    public static def or(x: Boolean, y: Boolean): Boolean {
        return x || y;
    }

    public static def or(x: BooleanMatrix, y: BooleanMatrix): BooleanMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At | op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](r, (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) || y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def or(x: Boolean, y: BooleanMatrix): BooleanMatrix {

        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](r, (
                (p:Point(r.rank))=>
                x || y.array(p(0), p(1))
            )
        );

        return new BooleanMatrix(array, n, m);
    }
    public static def or(x: BooleanMatrix, y: Boolean): BooleanMatrix {

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Boolean](r, (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) || y
            )
        );

        return new BooleanMatrix(array, n, m);
    }

    // || } ------------------------------------------------------------------------------

// End of LogicLib

// Beginning of N-D-T3MathLib.x10

    // + { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def plus(x: Int, y: Int): Int {
        return x + y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def plus(x: Int, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x + y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def plus(x: IntMatrix, y: Int): IntMatrix {
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

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def plus(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // + } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // + { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def plus(x: IntMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At plus op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) + y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }

    // + } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // - { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def minus(x: Int, y: Int): Int {
        return x - y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def minus(x: Int, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x - y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def minus(x: IntMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) - y
            )
        );

        return new IntMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def minus(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // - } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // - { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def minus(x: IntMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At minus op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) - y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }

    // - } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // * { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def dotTimes(x: Int, y: Int): Int {
        return x * y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def dotTimes(x: Int, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x * y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def dotTimes(x: IntMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) * y
            )
        );

        return new IntMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def dotTimes(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // * } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // * { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotTimes(x: IntMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotTimes op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) * y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // / { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def dotDivide(x: Int, y: Int): Int {
        return x / y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def dotDivide(x: Int, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x / y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def dotDivide(x: IntMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) / y
            )
        );

        return new IntMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def dotDivide(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // / } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // / { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotDivide(x: IntMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotDivide op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) / y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // * { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def times(x: Int, y: Int): Int {
        return x * y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def times(x: Int, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x * y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def times(x: IntMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) * y
            )
        );

        return new IntMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def times(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // * } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathLib.x10

    // as Double / { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def divide(x: Int, y: Int): Double {
        return x as Double / y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def divide(x: Int, y: IntMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x as Double / y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def divide(x: IntMatrix, y: Int): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) as Double / y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def divide(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // as Double / } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathLib.x10

    // + { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def plus(x: Int, y: Double): Double {
        return x + y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def plus(x: Int, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x + y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def plus(x: IntMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) + y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def plus(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // + } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // + { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def plus(x: IntMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At plus op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) + y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // + } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // - { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def minus(x: Int, y: Double): Double {
        return x - y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def minus(x: Int, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x - y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def minus(x: IntMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) - y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def minus(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // - } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // - { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def minus(x: IntMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At minus op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) - y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // - } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // * { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def dotTimes(x: Int, y: Double): Double {
        return x * y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def dotTimes(x: Int, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x * y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def dotTimes(x: IntMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) * y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def dotTimes(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // * } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // * { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotTimes(x: IntMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotTimes op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) * y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // / { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def dotDivide(x: Int, y: Double): Double {
        return x / y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def dotDivide(x: Int, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x / y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def dotDivide(x: IntMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) / y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def dotDivide(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // / } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // / { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotDivide(x: IntMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotDivide op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) / y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // * { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def times(x: Int, y: Double): Double {
        return x * y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def times(x: Int, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x * y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def times(x: IntMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) * y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def times(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // * } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathLib.x10

    // / { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def divide(x: Int, y: Double): Double {
        return x / y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def divide(x: Int, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x / y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def divide(x: IntMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) / y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def divide(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // / } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathLib.x10

    // + { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def plus(x: Double, y: Int): Double {
        return x + y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def plus(x: Double, y: IntMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x + y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def plus(x: DoubleMatrix, y: Int): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) + y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def plus(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // + } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // + { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def plus(x: DoubleMatrix, y: IntMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At plus op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) + y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // + } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // - { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def minus(x: Double, y: Int): Double {
        return x - y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def minus(x: Double, y: IntMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x - y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def minus(x: DoubleMatrix, y: Int): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) - y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def minus(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // - } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // - { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def minus(x: DoubleMatrix, y: IntMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At minus op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) - y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // - } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // * { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def dotTimes(x: Double, y: Int): Double {
        return x * y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def dotTimes(x: Double, y: IntMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x * y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def dotTimes(x: DoubleMatrix, y: Int): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) * y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def dotTimes(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // * } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // * { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotTimes(x: DoubleMatrix, y: IntMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotTimes op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) * y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // / { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def dotDivide(x: Double, y: Int): Double {
        return x / y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def dotDivide(x: Double, y: IntMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x / y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def dotDivide(x: DoubleMatrix, y: Int): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) / y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def dotDivide(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // / } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // / { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotDivide(x: DoubleMatrix, y: IntMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotDivide op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) / y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // * { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def times(x: Double, y: Int): Double {
        return x * y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def times(x: Double, y: IntMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x * y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def times(x: DoubleMatrix, y: Int): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) * y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def times(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // * } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathLib.x10

    // / { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def divide(x: Double, y: Int): Double {
        return x / y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def divide(x: Double, y: IntMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x / y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def divide(x: DoubleMatrix, y: Int): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) / y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def divide(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // / } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathLib.x10

    // + { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def plus(x: Double, y: Double): Double {
        return x + y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def plus(x: Double, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x + y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def plus(x: DoubleMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) + y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def plus(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // + } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // + { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def plus(x: DoubleMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At plus op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) + y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // + } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // - { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def minus(x: Double, y: Double): Double {
        return x - y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def minus(x: Double, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x - y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def minus(x: DoubleMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) - y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def minus(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // - } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // - { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def minus(x: DoubleMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At minus op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) - y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // - } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // * { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def dotTimes(x: Double, y: Double): Double {
        return x * y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def dotTimes(x: Double, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x * y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def dotTimes(x: DoubleMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) * y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def dotTimes(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // * } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // * { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotTimes(x: DoubleMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotTimes op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) * y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // / { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def dotDivide(x: Double, y: Double): Double {
        return x / y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def dotDivide(x: Double, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x / y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def dotDivide(x: DoubleMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) / y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def dotDivide(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // / } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathMMLib.x10

    // / { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotDivide(x: DoubleMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotDivide op: Mismatch of Operand Dimensions." +
                  "(" + x.n + ", " + x.m + ") and (" + y.n + ", " + y.m + ")");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) / y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------



// End of N-D-T3MathMMLib.x10
// Beginning of N-D-T3MathLib.x10

    // * { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def times(x: Double, y: Double): Double {
        return x * y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def times(x: Double, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x * y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def times(x: DoubleMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) * y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def times(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // * } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-T3MathLib.x10

    // / { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def divide(x: Double, y: Double): Double {
        return x / y;
    }

    // 2. Matrices of the same size
    // In N-D-T3MathMMLib

    // 3. For a Matrix and a primitive
    public static def divide(x: Double, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x / y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def divide(x: DoubleMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                x.array(p(0), p(1)) / y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def divide(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // / } ------------------------------------------------------------------------------



// End of N-D-T3MathLib.x10

// Beginning of N-D-TMathLib.x10

    // + { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def plus(x: Boolean, y: Int): Int {
        return (x?1:0) + y;
    }
    public static def plus(x: Int, y: Boolean): Int {
        return x + (y?1:0);
    }

    // 2. Matrices of the same size
    // In N-D-TMathMMLib

    // 3. For a Matrix and a primitive
    public static def plus(x: Boolean, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    xv + y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def plus(x: Int, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x + (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def plus(x: IntMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) + yv
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def plus(x: BooleanMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) + y
            )
        );

        return new IntMatrix(array, n, m);
    }

    // + } ------------------------------------------------------------------------------

// End of N-D-TMathLib.x10
// Beginning of N-D-TMathMMLib.x10

    // + { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def plus(x: BooleanMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At plus op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) +
                    y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def plus(x: IntMatrix, y: BooleanMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At plus op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) +
                    (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new IntMatrix(array, n, m);
    }

    // + } ------------------------------------------------------------------------------

// End of N-D-TMathMMLib.x10
// Beginning of N-D-TMathLib.x10

    // - { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def minus(x: Boolean, y: Int): Int {
        return (x?1:0) - y;
    }
    public static def minus(x: Int, y: Boolean): Int {
        return x - (y?1:0);
    }

    // 2. Matrices of the same size
    // In N-D-TMathMMLib

    // 3. For a Matrix and a primitive
    public static def minus(x: Boolean, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    xv - y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def minus(x: Int, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x - (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def minus(x: IntMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) - yv
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def minus(x: BooleanMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) - y
            )
        );

        return new IntMatrix(array, n, m);
    }

    // - } ------------------------------------------------------------------------------

// End of N-D-TMathLib.x10
// Beginning of N-D-TMathMMLib.x10

    // - { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def minus(x: BooleanMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At minus op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) -
                    y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def minus(x: IntMatrix, y: BooleanMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At minus op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) -
                    (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new IntMatrix(array, n, m);
    }

    // - } ------------------------------------------------------------------------------

// End of N-D-TMathMMLib.x10
// Beginning of N-D-TMathLib.x10

    // * { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def dotTimes(x: Boolean, y: Int): Int {
        return (x?1:0) * y;
    }
    public static def dotTimes(x: Int, y: Boolean): Int {
        return x * (y?1:0);
    }

    // 2. Matrices of the same size
    // In N-D-TMathMMLib

    // 3. For a Matrix and a primitive
    public static def dotTimes(x: Boolean, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    xv * y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def dotTimes(x: Int, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x * (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def dotTimes(x: IntMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) * yv
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def dotTimes(x: BooleanMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) * y
            )
        );

        return new IntMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------

// End of N-D-TMathLib.x10
// Beginning of N-D-TMathMMLib.x10

    // * { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotTimes(x: BooleanMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotTimes op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) *
                    y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def dotTimes(x: IntMatrix, y: BooleanMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotTimes op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) *
                    (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new IntMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------

// End of N-D-TMathMMLib.x10
// Beginning of N-D-TMathLib.x10

    // / { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def dotDivide(x: Boolean, y: Int): Int {
        return (x?1:0) / y;
    }
    public static def dotDivide(x: Int, y: Boolean): Int {
        return x / (y?1:0);
    }

    // 2. Matrices of the same size
    // In N-D-TMathMMLib

    // 3. For a Matrix and a primitive
    public static def dotDivide(x: Boolean, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    xv / y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def dotDivide(x: Int, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x / (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def dotDivide(x: IntMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) / yv
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def dotDivide(x: BooleanMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) / y
            )
        );

        return new IntMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------

// End of N-D-TMathLib.x10
// Beginning of N-D-TMathMMLib.x10

    // / { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotDivide(x: BooleanMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotDivide op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) /
                    y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def dotDivide(x: IntMatrix, y: BooleanMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotDivide op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) /
                    (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new IntMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------

// End of N-D-TMathMMLib.x10
// Beginning of N-D-TMathLib.x10

    // * { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def times(x: Boolean, y: Int): Int {
        return (x?1:0) * y;
    }
    public static def times(x: Int, y: Boolean): Int {
        return x * (y?1:0);
    }

    // 2. Matrices of the same size
    // In N-D-TMathMMLib

    // 3. For a Matrix and a primitive
    public static def times(x: Boolean, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    xv * y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def times(x: Int, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x * (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def times(x: IntMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) * yv
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def times(x: BooleanMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) * y
            )
        );

        return new IntMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------

// End of N-D-TMathLib.x10
// Beginning of N-D-TMathLib.x10

    // / { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def divide(x: Boolean, y: Int): Int {
        return (x?1:0) / y;
    }
    public static def divide(x: Int, y: Boolean): Int {
        return x / (y?1:0);
    }

    // 2. Matrices of the same size
    // In N-D-TMathMMLib

    // 3. For a Matrix and a primitive
    public static def divide(x: Boolean, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    xv / y.array(p(0), p(1))
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def divide(x: Int, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x / (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def divide(x: IntMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) / yv
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def divide(x: BooleanMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) / y
            )
        );

        return new IntMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------

// End of N-D-TMathLib.x10
// Beginning of N-D-TMathLib.x10

    // + { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def plus(x: Boolean, y: Double): Double {
        return (x?1:0) + y;
    }
    public static def plus(x: Double, y: Boolean): Double {
        return x + (y?1:0);
    }

    // 2. Matrices of the same size
    // In N-D-TMathMMLib

    // 3. For a Matrix and a primitive
    public static def plus(x: Boolean, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    xv + y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def plus(x: Double, y: BooleanMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x + (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def plus(x: DoubleMatrix, y: Boolean): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) + yv
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def plus(x: BooleanMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) + y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // + } ------------------------------------------------------------------------------

// End of N-D-TMathLib.x10
// Beginning of N-D-TMathMMLib.x10

    // + { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def plus(x: BooleanMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At plus op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) +
                    y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def plus(x: DoubleMatrix, y: BooleanMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At plus op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) +
                    (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // + } ------------------------------------------------------------------------------

// End of N-D-TMathMMLib.x10
// Beginning of N-D-TMathLib.x10

    // - { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def minus(x: Boolean, y: Double): Double {
        return (x?1:0) - y;
    }
    public static def minus(x: Double, y: Boolean): Double {
        return x - (y?1:0);
    }

    // 2. Matrices of the same size
    // In N-D-TMathMMLib

    // 3. For a Matrix and a primitive
    public static def minus(x: Boolean, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    xv - y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def minus(x: Double, y: BooleanMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x - (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def minus(x: DoubleMatrix, y: Boolean): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) - yv
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def minus(x: BooleanMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) - y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // - } ------------------------------------------------------------------------------

// End of N-D-TMathLib.x10
// Beginning of N-D-TMathMMLib.x10

    // - { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def minus(x: BooleanMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At minus op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) -
                    y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def minus(x: DoubleMatrix, y: BooleanMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At minus op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) -
                    (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // - } ------------------------------------------------------------------------------

// End of N-D-TMathMMLib.x10
// Beginning of N-D-TMathLib.x10

    // * { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def dotTimes(x: Boolean, y: Double): Double {
        return (x?1:0) * y;
    }
    public static def dotTimes(x: Double, y: Boolean): Double {
        return x * (y?1:0);
    }

    // 2. Matrices of the same size
    // In N-D-TMathMMLib

    // 3. For a Matrix and a primitive
    public static def dotTimes(x: Boolean, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    xv * y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def dotTimes(x: Double, y: BooleanMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x * (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def dotTimes(x: DoubleMatrix, y: Boolean): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) * yv
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def dotTimes(x: BooleanMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) * y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------

// End of N-D-TMathLib.x10
// Beginning of N-D-TMathMMLib.x10

    // * { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotTimes(x: BooleanMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotTimes op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) *
                    y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def dotTimes(x: DoubleMatrix, y: BooleanMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotTimes op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) *
                    (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------

// End of N-D-TMathMMLib.x10
// Beginning of N-D-TMathLib.x10

    // / { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def dotDivide(x: Boolean, y: Double): Double {
        return (x?1:0) / y;
    }
    public static def dotDivide(x: Double, y: Boolean): Double {
        return x / (y?1:0);
    }

    // 2. Matrices of the same size
    // In N-D-TMathMMLib

    // 3. For a Matrix and a primitive
    public static def dotDivide(x: Boolean, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    xv / y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def dotDivide(x: Double, y: BooleanMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x / (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def dotDivide(x: DoubleMatrix, y: Boolean): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) / yv
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def dotDivide(x: BooleanMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) / y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------

// End of N-D-TMathLib.x10
// Beginning of N-D-TMathMMLib.x10

    // / { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotDivide(x: BooleanMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotDivide op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) /
                    y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def dotDivide(x: DoubleMatrix, y: BooleanMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotDivide op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) /
                    (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------

// End of N-D-TMathMMLib.x10
// Beginning of N-D-TMathLib.x10

    // * { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def times(x: Boolean, y: Double): Double {
        return (x?1:0) * y;
    }
    public static def times(x: Double, y: Boolean): Double {
        return x * (y?1:0);
    }

    // 2. Matrices of the same size
    // In N-D-TMathMMLib

    // 3. For a Matrix and a primitive
    public static def times(x: Boolean, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    xv * y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def times(x: Double, y: BooleanMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x * (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def times(x: DoubleMatrix, y: Boolean): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) * yv
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def times(x: BooleanMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) * y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------

// End of N-D-TMathLib.x10
// Beginning of N-D-TMathLib.x10

    // / { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def divide(x: Boolean, y: Double): Double {
        return (x?1:0) / y;
    }
    public static def divide(x: Double, y: Boolean): Double {
        return x / (y?1:0);
    }

    // 2. Matrices of the same size
    // In N-D-TMathMMLib

    // 3. For a Matrix and a primitive
    public static def divide(x: Boolean, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    xv / y.array(p(0), p(1))
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def divide(x: Double, y: BooleanMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x / (y.array(p(0), p(1)) ? 1 : 0)
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def divide(x: DoubleMatrix, y: Boolean): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    x.array(p(0), p(1)) / yv
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def divide(x: BooleanMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    (x.array(p(0), p(1)) ? 1 : 0) / y
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------

// End of N-D-TMathLib.x10// Beginning of MathLib

    // + { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def plus(x: Boolean, y: Boolean): Int {
        val xv = x ? 1 : 0;
        val yv = x ? 1 : 0;
        return xv + yv;
    }

    // 2. Matrices of the same size
    // In MathMMLib

    // 3. For a Matrix and a primitive
    public static def plus(x: Boolean, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv = x ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return xv + yv;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def plus(x: BooleanMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv = y ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                    (p:Point(r.rank))=> {
                        val xv = (x.array(p(0), p(1))) ? 1 : 0;
                        return xv + yv;
                    }
            )
        );

        return new IntMatrix(array, n, m);
    }

    // + } ------------------------------------------------------------------------------

// End of MathLib// Beginning of MathMMLib

    // + { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def plus(x: BooleanMatrix, y: BooleanMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At plus op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val xv = (x.array(p(0), p(1))) ? 1 : 0;
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return xv + yv;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }

    // + } ------------------------------------------------------------------------------

// End of MathMMLib// Beginning of MathLib

    // - { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def minus(x: Boolean, y: Boolean): Int {
        val xv = x ? 1 : 0;
        val yv = x ? 1 : 0;
        return xv - yv;
    }

    // 2. Matrices of the same size
    // In MathMMLib

    // 3. For a Matrix and a primitive
    public static def minus(x: Boolean, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv = x ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return xv - yv;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def minus(x: BooleanMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv = y ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                    (p:Point(r.rank))=> {
                        val xv = (x.array(p(0), p(1))) ? 1 : 0;
                        return xv - yv;
                    }
            )
        );

        return new IntMatrix(array, n, m);
    }

    // - } ------------------------------------------------------------------------------

// End of MathLib// Beginning of MathMMLib

    // - { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def minus(x: BooleanMatrix, y: BooleanMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At minus op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val xv = (x.array(p(0), p(1))) ? 1 : 0;
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return xv - yv;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }

    // - } ------------------------------------------------------------------------------

// End of MathMMLib// Beginning of MathLib

    // * { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def dotTimes(x: Boolean, y: Boolean): Int {
        val xv = x ? 1 : 0;
        val yv = x ? 1 : 0;
        return xv * yv;
    }

    // 2. Matrices of the same size
    // In MathMMLib

    // 3. For a Matrix and a primitive
    public static def dotTimes(x: Boolean, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv = x ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return xv * yv;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def dotTimes(x: BooleanMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv = y ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                    (p:Point(r.rank))=> {
                        val xv = (x.array(p(0), p(1))) ? 1 : 0;
                        return xv * yv;
                    }
            )
        );

        return new IntMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------

// End of MathLib// Beginning of MathMMLib

    // * { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotTimes(x: BooleanMatrix, y: BooleanMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotTimes op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val xv = (x.array(p(0), p(1))) ? 1 : 0;
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return xv * yv;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------

// End of MathMMLib// Beginning of MathLib

    // / { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def dotDivide(x: Boolean, y: Boolean): Int {
        val xv = x ? 1 : 0;
        val yv = x ? 1 : 0;
        return xv / yv;
    }

    // 2. Matrices of the same size
    // In MathMMLib

    // 3. For a Matrix and a primitive
    public static def dotDivide(x: Boolean, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv = x ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return xv / yv;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def dotDivide(x: BooleanMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv = y ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                    (p:Point(r.rank))=> {
                        val xv = (x.array(p(0), p(1))) ? 1 : 0;
                        return xv / yv;
                    }
            )
        );

        return new IntMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------

// End of MathLib// Beginning of MathMMLib

    // / { ------------------------------------------------------------------------------

    // 2. Matrices of the same size
    public static def dotDivide(x: BooleanMatrix, y: BooleanMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotDivide op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val xv = (x.array(p(0), p(1))) ? 1 : 0;
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return xv / yv;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------

// End of MathMMLib// Beginning of MathLib

    // * { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def times(x: Boolean, y: Boolean): Int {
        val xv = x ? 1 : 0;
        val yv = x ? 1 : 0;
        return xv * yv;
    }

    // 2. Matrices of the same size
    // In MathMMLib

    // 3. For a Matrix and a primitive
    public static def times(x: Boolean, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv = x ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return xv * yv;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def times(x: BooleanMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv = y ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                    (p:Point(r.rank))=> {
                        val xv = (x.array(p(0), p(1))) ? 1 : 0;
                        return xv * yv;
                    }
            )
        );

        return new IntMatrix(array, n, m);
    }

    // * } ------------------------------------------------------------------------------

// End of MathLib// Beginning of MathLib

    // / { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def divide(x: Boolean, y: Boolean): Int {
        val xv = x ? 1 : 0;
        val yv = x ? 1 : 0;
        return xv / yv;
    }

    // 2. Matrices of the same size
    // In MathMMLib

    // 3. For a Matrix and a primitive
    public static def divide(x: Boolean, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv = x ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return xv / yv;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def divide(x: BooleanMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv = y ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                    (p:Point(r.rank))=> {
                        val xv = (x.array(p(0), p(1))) ? 1 : 0;
                        return xv / yv;
                    }
            )
        );

        return new IntMatrix(array, n, m);
    }

    // / } ------------------------------------------------------------------------------

// End of MathLib
// Beginning of N-D-T3MathFunLib.x10

    // Math.pow { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def dotPower(x: Int, y: Int): Int {
        return Math.pow(x, y) as Int;
    }


    // 2. Matrices of the same size
    public static def dotPower(x: IntMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                Math.pow(x.array(p(0), p(1)), y.array(p(0), p(1))) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def dotPower(x: Int, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                Math.pow(x, y.array(p(0), p(1))) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def dotPower(x: IntMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                Math.pow(x.array(p(0), p(1)), y) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def dotPower(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // Math.pow } ------------------------------------------------------------------------------



// End of N-D-T3MathFunLib.x10
// Beginning of N-D-T3MathFunLib.x10

    // Math.min { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def min(x: Int, y: Int): Int {
        return Math.min(x, y) as Int;
    }


    // 2. Matrices of the same size
    public static def min(x: IntMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                Math.min(x.array(p(0), p(1)), y.array(p(0), p(1))) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def min(x: Int, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                Math.min(x, y.array(p(0), p(1))) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def min(x: IntMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                Math.min(x.array(p(0), p(1)), y) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def min(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // Math.min } ------------------------------------------------------------------------------



// End of N-D-T3MathFunLib.x10
// Beginning of N-D-T3MathFunLib.x10

    // Math.max { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def max(x: Int, y: Int): Int {
        return Math.max(x, y) as Int;
    }


    // 2. Matrices of the same size
    public static def max(x: IntMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                Math.max(x.array(p(0), p(1)), y.array(p(0), p(1))) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def max(x: Int, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                Math.max(x, y.array(p(0), p(1))) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def max(x: IntMatrix, y: Int): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r,
            (
                (p:Point(r.rank))=>
                Math.max(x.array(p(0), p(1)), y) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def max(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // Math.max } ------------------------------------------------------------------------------



// End of N-D-T3MathFunLib.x10
// Beginning of N-D-T3MathFunLib.x10

    // Math.pow { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def dotPower(x: Int, y: Double): Double {
        return Math.pow(x, y) as Double;
    }


    // 2. Matrices of the same size
    public static def dotPower(x: IntMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.pow(x.array(p(0), p(1)), y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def dotPower(x: Int, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.pow(x, y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def dotPower(x: IntMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.pow(x.array(p(0), p(1)), y) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def dotPower(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // Math.pow } ------------------------------------------------------------------------------



// End of N-D-T3MathFunLib.x10
// Beginning of N-D-T3MathFunLib.x10

    // Math.min { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def min(x: Int, y: Double): Double {
        return Math.min(x, y) as Double;
    }


    // 2. Matrices of the same size
    public static def min(x: IntMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.min(x.array(p(0), p(1)), y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def min(x: Int, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.min(x, y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def min(x: IntMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.min(x.array(p(0), p(1)), y) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def min(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // Math.min } ------------------------------------------------------------------------------



// End of N-D-T3MathFunLib.x10
// Beginning of N-D-T3MathFunLib.x10

    // Math.max { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def max(x: Int, y: Double): Double {
        return Math.max(x, y) as Double;
    }


    // 2. Matrices of the same size
    public static def max(x: IntMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.max(x.array(p(0), p(1)), y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def max(x: Int, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.max(x, y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def max(x: IntMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.max(x.array(p(0), p(1)), y) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def max(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // Math.max } ------------------------------------------------------------------------------



// End of N-D-T3MathFunLib.x10
// Beginning of N-D-T3MathFunLib.x10

    // Math.pow { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def dotPower(x: Double, y: Int): Double {
        return Math.pow(x, y) as Double;
    }


    // 2. Matrices of the same size
    public static def dotPower(x: DoubleMatrix, y: IntMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.pow(x.array(p(0), p(1)), y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def dotPower(x: Double, y: IntMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.pow(x, y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def dotPower(x: DoubleMatrix, y: Int): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.pow(x.array(p(0), p(1)), y) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def dotPower(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // Math.pow } ------------------------------------------------------------------------------



// End of N-D-T3MathFunLib.x10
// Beginning of N-D-T3MathFunLib.x10

    // Math.min { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def min(x: Double, y: Int): Double {
        return Math.min(x, y) as Double;
    }


    // 2. Matrices of the same size
    public static def min(x: DoubleMatrix, y: IntMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.min(x.array(p(0), p(1)), y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def min(x: Double, y: IntMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.min(x, y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def min(x: DoubleMatrix, y: Int): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.min(x.array(p(0), p(1)), y) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def min(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // Math.min } ------------------------------------------------------------------------------



// End of N-D-T3MathFunLib.x10
// Beginning of N-D-T3MathFunLib.x10

    // Math.max { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def max(x: Double, y: Int): Double {
        return Math.max(x, y) as Double;
    }


    // 2. Matrices of the same size
    public static def max(x: DoubleMatrix, y: IntMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.max(x.array(p(0), p(1)), y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def max(x: Double, y: IntMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.max(x, y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def max(x: DoubleMatrix, y: Int): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.max(x.array(p(0), p(1)), y) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def max(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // Math.max } ------------------------------------------------------------------------------



// End of N-D-T3MathFunLib.x10
// Beginning of N-D-T3MathFunLib.x10

    // Math.pow { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def dotPower(x: Double, y: Double): Double {
        return Math.pow(x, y) as Double;
    }


    // 2. Matrices of the same size
    public static def dotPower(x: DoubleMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.pow(x.array(p(0), p(1)), y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def dotPower(x: Double, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.pow(x, y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def dotPower(x: DoubleMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.pow(x.array(p(0), p(1)), y) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def dotPower(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // Math.pow } ------------------------------------------------------------------------------



// End of N-D-T3MathFunLib.x10
// Beginning of N-D-T3MathFunLib.x10

    // Math.min { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def min(x: Double, y: Double): Double {
        return Math.min(x, y) as Double;
    }


    // 2. Matrices of the same size
    public static def min(x: DoubleMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.min(x.array(p(0), p(1)), y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def min(x: Double, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.min(x, y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def min(x: DoubleMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.min(x.array(p(0), p(1)), y) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def min(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // Math.min } ------------------------------------------------------------------------------



// End of N-D-T3MathFunLib.x10
// Beginning of N-D-T3MathFunLib.x10

    // Math.max { ------------------------------------------------------------------------------

    // 1. X10 has built-in support for primitive data types, Int and Double.
    public static def max(x: Double, y: Double): Double {
        return Math.max(x, y) as Double;
    }


    // 2. Matrices of the same size
    public static def max(x: DoubleMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At + op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.max(x.array(p(0), p(1)), y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // 3. For a Matrix and a primitive
    public static def max(x: Double, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.max(x, y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    public static def max(x: DoubleMatrix, y: Double): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r,
            (
                (p:Point(r.rank))=>
                Math.max(x.array(p(0), p(1)), y) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    /*
    // X We overload implicit type conversion in NDTMatrix.x10
    public static def max(c : NDT) : NDTMatrix {
        val r = (0..n-1)*(0..m-1);
        val array = new Array[NDT](r, (
                (p:Point(r.rank)) =>
                c
            )
        );

        return new NDTMatrix(array, n, m);
    }
    */

    // Math.max } ------------------------------------------------------------------------------



// End of N-D-T3MathFunLib.x10
// Beginning of N-D-TMathFunLib.x10

    // MATH { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def dotPower(x: Boolean, y: Int): Int {
        return Math.pow((x?1:0), y) as Int;
    }
    public static def dotPower(x: Int, y: Boolean): Int {
        return Math.pow(x, (y?1:0)) as Int;
    }

    // 2. Matrices of the same size
    public static def dotPower(x: BooleanMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotPower op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.pow(
                        (x.array(p(0), p(1)) ? 1 : 0),
                        y.array(p(0), p(1))
                    ) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def dotPower(x: IntMatrix, y: BooleanMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotPower op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.pow(
                        x.array(p(0), p(1)),
                        (y.array(p(0), p(1)) ? 1 : 0)
                    ) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def dotPower(x: Boolean, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.pow(xv, y.array(p(0), p(1))) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def dotPower(x: IntMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.pow(x.array(p(0), p(1)), yv) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }

    // MATH } ------------------------------------------------------------------------------

// End of N-D-TMathFunLib.x10

// Beginning of N-D-TMathFunLib.x10

    // MATH { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def min(x: Boolean, y: Int): Int {
        return Math.min((x?1:0), y) as Int;
    }
    public static def min(x: Int, y: Boolean): Int {
        return Math.min(x, (y?1:0)) as Int;
    }

    // 2. Matrices of the same size
    public static def min(x: BooleanMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At min op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.min(
                        (x.array(p(0), p(1)) ? 1 : 0),
                        y.array(p(0), p(1))
                    ) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def min(x: IntMatrix, y: BooleanMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At min op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.min(
                        x.array(p(0), p(1)),
                        (y.array(p(0), p(1)) ? 1 : 0)
                    ) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def min(x: Boolean, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.min(xv, y.array(p(0), p(1))) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def min(x: IntMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.min(x.array(p(0), p(1)), yv) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }

    // MATH } ------------------------------------------------------------------------------

// End of N-D-TMathFunLib.x10

// Beginning of N-D-TMathFunLib.x10

    // MATH { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def max(x: Boolean, y: Int): Int {
        return Math.max((x?1:0), y) as Int;
    }
    public static def max(x: Int, y: Boolean): Int {
        return Math.max(x, (y?1:0)) as Int;
    }

    // 2. Matrices of the same size
    public static def max(x: BooleanMatrix, y: IntMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At max op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.max(
                        (x.array(p(0), p(1)) ? 1 : 0),
                        y.array(p(0), p(1))
                    ) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def max(x: IntMatrix, y: BooleanMatrix): IntMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At max op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.max(
                        x.array(p(0), p(1)),
                        (y.array(p(0), p(1)) ? 1 : 0)
                    ) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def max(x: Boolean, y: IntMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.max(xv, y.array(p(0), p(1))) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }
    public static def max(x: IntMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.max(x.array(p(0), p(1)), yv) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }

    // MATH } ------------------------------------------------------------------------------

// End of N-D-TMathFunLib.x10

// Beginning of N-D-TMathFunLib.x10

    // MATH { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def dotPower(x: Boolean, y: Double): Double {
        return Math.pow((x?1:0), y) as Double;
    }
    public static def dotPower(x: Double, y: Boolean): Double {
        return Math.pow(x, (y?1:0)) as Double;
    }

    // 2. Matrices of the same size
    public static def dotPower(x: BooleanMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotPower op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.pow(
                        (x.array(p(0), p(1)) ? 1 : 0),
                        y.array(p(0), p(1))
                    ) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def dotPower(x: DoubleMatrix, y: BooleanMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At dotPower op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.pow(
                        x.array(p(0), p(1)),
                        (y.array(p(0), p(1)) ? 1 : 0)
                    ) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def dotPower(x: Boolean, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.pow(xv, y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def dotPower(x: DoubleMatrix, y: Boolean): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.pow(x.array(p(0), p(1)), yv) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // MATH } ------------------------------------------------------------------------------

// End of N-D-TMathFunLib.x10

// Beginning of N-D-TMathFunLib.x10

    // MATH { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def min(x: Boolean, y: Double): Double {
        return Math.min((x?1:0), y) as Double;
    }
    public static def min(x: Double, y: Boolean): Double {
        return Math.min(x, (y?1:0)) as Double;
    }

    // 2. Matrices of the same size
    public static def min(x: BooleanMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At min op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.min(
                        (x.array(p(0), p(1)) ? 1 : 0),
                        y.array(p(0), p(1))
                    ) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def min(x: DoubleMatrix, y: BooleanMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At min op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.min(
                        x.array(p(0), p(1)),
                        (y.array(p(0), p(1)) ? 1 : 0)
                    ) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def min(x: Boolean, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.min(xv, y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def min(x: DoubleMatrix, y: Boolean): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.min(x.array(p(0), p(1)), yv) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // MATH } ------------------------------------------------------------------------------

// End of N-D-TMathFunLib.x10

// Beginning of N-D-TMathFunLib.x10

    // MATH { ------------------------------------------------------------------------------

    // 1. Boolean with Int and Double.
    public static def max(x: Boolean, y: Double): Double {
        return Math.max((x?1:0), y) as Double;
    }
    public static def max(x: Double, y: Boolean): Double {
        return Math.max(x, (y?1:0)) as Double;
    }

    // 2. Matrices of the same size
    public static def max(x: BooleanMatrix, y: DoubleMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At max op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.max(
                        (x.array(p(0), p(1)) ? 1 : 0),
                        y.array(p(0), p(1))
                    ) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def max(x: DoubleMatrix, y: BooleanMatrix): DoubleMatrix {
        if ((x.n != y.n) || (x.m != y.m))
            error("At max op: Mismatch of Operand Dimensions.");

        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.max(
                        x.array(p(0), p(1)),
                        (y.array(p(0), p(1)) ? 1 : 0)
                    ) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def max(x: Boolean, y: DoubleMatrix): DoubleMatrix {
        val n = y.n;
        val m = y.m;

        val xv =  (x ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.max(xv, y.array(p(0), p(1))) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    public static def max(x: DoubleMatrix, y: Boolean): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val yv =  (y ? 1 : 0);

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.max(x.array(p(0), p(1)), yv) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }

    // MATH } ------------------------------------------------------------------------------

// End of N-D-TMathFunLib.x10
// Beginning of MathFunLib

    // Math.pow { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def dotPower(x: Boolean, y: Boolean): Int {
        val xv = x ? 1 : 0;
        val yv = x ? 1 : 0;
        return Math.pow(xv, yv) as Int;
    }

    // 2. Matrices of the same size
    public static def dotPower(x: BooleanMatrix, y: BooleanMatrix): IntMatrix {
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
                    return Math.pow(xv, yv) as Int;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def dotPower(x: Boolean, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv = x ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return Math.pow(xv, yv) as Int;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def dotPower(x: BooleanMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv = y ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                    (p:Point(r.rank))=> {
                        val xv = (x.array(p(0), p(1))) ? 1 : 0;
                        return Math.pow(xv, yv) as Int;
                    }
            )
        );

        return new IntMatrix(array, n, m);
    }

    // MATH } ------------------------------------------------------------------------------

// End of MathFunLib

// Beginning of MathFunLib

    // Math.min { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def min(x: Boolean, y: Boolean): Int {
        val xv = x ? 1 : 0;
        val yv = x ? 1 : 0;
        return Math.min(xv, yv) as Int;
    }

    // 2. Matrices of the same size
    public static def min(x: BooleanMatrix, y: BooleanMatrix): IntMatrix {
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
                    return Math.min(xv, yv) as Int;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def min(x: Boolean, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv = x ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return Math.min(xv, yv) as Int;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def min(x: BooleanMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv = y ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                    (p:Point(r.rank))=> {
                        val xv = (x.array(p(0), p(1))) ? 1 : 0;
                        return Math.min(xv, yv) as Int;
                    }
            )
        );

        return new IntMatrix(array, n, m);
    }

    // MATH } ------------------------------------------------------------------------------

// End of MathFunLib

// Beginning of MathFunLib

    // Math.max { ------------------------------------------------------------------------------

    // 1. Boolean and Boolean
    public static def max(x: Boolean, y: Boolean): Int {
        val xv = x ? 1 : 0;
        val yv = x ? 1 : 0;
        return Math.max(xv, yv) as Int;
    }

    // 2. Matrices of the same size
    public static def max(x: BooleanMatrix, y: BooleanMatrix): IntMatrix {
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
                    return Math.max(xv, yv) as Int;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }


    // 3. For a Matrix and a primitive
    public static def max(x: Boolean, y: BooleanMatrix): IntMatrix {
        val n = y.n;
        val m = y.m;

        val xv = x ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=> {
                    val yv = (y.array(p(0), p(1))) ? 1 : 0;
                    return Math.max(xv, yv) as Int;
                }
            )
        );

        return new IntMatrix(array, n, m);
    }

    public static def max(x: BooleanMatrix, y: Boolean): IntMatrix {
        val n = x.n;
        val m = x.m;

        val yv = y ? 1 : 0;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                    (p:Point(r.rank))=> {
                        val xv = (x.array(p(0), p(1))) ? 1 : 0;
                        return Math.max(xv, yv) as Int;
                    }
            )
        );

        return new IntMatrix(array, n, m);
    }

    // MATH } ------------------------------------------------------------------------------

// End of MathFunLib


// Beginning of N-D-TMathUFunLib.x10

    // MATH { ------------------------------------------------------------------------------

    public static def abs(x: Int): Int {
        return Math.abs(x) as Int;
    }

    // 2. Matrices of the same size
    public static def abs(x: IntMatrix): IntMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Int](
            r, (
                (p:Point(r.rank))=>
                    Math.abs(
                        x.array(p(0), p(1))
                    ) as Int
            )
        );

        return new IntMatrix(array, n, m);
    }
    // MATH } ------------------------------------------------------------------------------

// End of N-D-TMathUFunLib.x10

// Beginning of N-D-TMathUFunLib.x10

    // MATH { ------------------------------------------------------------------------------

    public static def abs(x: Double): Double {
        return Math.abs(x) as Double;
    }

    // 2. Matrices of the same size
    public static def abs(x: DoubleMatrix): DoubleMatrix {
        val n = x.n;
        val m = x.m;

        val r = (0..n-1)*(0..m-1);
        val array = new Array[Double](
            r, (
                (p:Point(r.rank))=>
                    Math.abs(
                        x.array(p(0), p(1))
                    ) as Double
            )
        );

        return new DoubleMatrix(array, n, m);
    }
    // MATH } ------------------------------------------------------------------------------

// End of N-D-TMathUFunLib.x10


}

