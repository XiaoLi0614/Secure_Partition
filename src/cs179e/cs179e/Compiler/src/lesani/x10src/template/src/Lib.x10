
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

