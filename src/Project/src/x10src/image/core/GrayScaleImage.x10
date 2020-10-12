import x10.io.*;

public class GrayScaleImage {
    private val data: Array[Int]{self.region.rank==2}/*{self.dist.region.rank==2}*/;
    public var n: Int = 0;
    public var m: Int = 0;

    public def this(data: Array[Int]{self.region.rank==2}/*{self.dist.region.rank==2}*/) {
        this.data = data;
        val region = data.region;
        this.n = region.max(0) + 1;
        this.m = region.max(1) + 1;
    }

    public def width(): Int {
        return n;
    }

    public def height(): Int  {
        return m;
    }

    public def get(i: Int, j: Int): Int {
//        val p = Point.make([i, j]);
//        return data(p);
        return data(i, j);
    }
}




/*
val output = new File(outputFileName);
val p = output.printer();
p.println(line);
*/