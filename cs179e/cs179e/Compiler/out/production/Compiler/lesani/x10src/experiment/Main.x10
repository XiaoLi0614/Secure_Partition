
import x10.io.File;
//import Lib;
//import x10.util.ArrayList;

public class Main {


    public static def main(args: Array[String]) {
        val mat = new IntMatrix([1, 2, 3, 4, 5, 6, 7, 8, 9]);
        val matp = Lib.reshape(mat, 3, 3);
        Console.OUT.println(matp);
        Console.OUT.println(Lib.reshape(matp, 9, 1));

//        val doubleTest1 = "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/2.Projects/0.Project/0.Project/src/msrc/benchmarks/data/DoubleTest.format";
//        val doubleTest2 = "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/2.Projects/0.Project/0.Project/src/msrc/benchmarks/data/DoubleTest2.format";
//        val intTest1 = "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/2.Projects/0.Project/0.Project/src/msrc/benchmarks/data/IntTest.format";
//        val intTest2 = "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/2.Projects/0.Project/0.Project/src/msrc/benchmarks/data/IntTest2.format";
//
//        var mat: DoubleMatrix = Lib.readDoubleMatrix(doubleTest1);
//        Console.OUT.println(mat);
//
//        mat = new DoubleMatrix([[1.1, 2.1, 3.1], [4.1, 5.1, 6.1]]);
//        Console.OUT.println(mat);
//
//        Lib.writeMatrix(mat, doubleTest2);
//        mat = Lib.readDoubleMatrix(doubleTest2);
//        Console.OUT.println(mat);
//
//
//        var intMat: IntMatrix = Lib.readIntMatrix(intTest1);
//        Console.OUT.println(intMat);
//
//        intMat = new IntMatrix([[1, 2, 3], [4, 5, 6]]);
//        Console.OUT.println(intMat);
//
//        Lib.writeMatrix(intMat, intTest2);
//        intMat = Lib.readIntMatrix(intTest2);
//        Console.OUT.println(intMat);
//
//        Console.OUT.println(Lib.sum(intMat, 2));

        // -----------------------------------------------------------

//        val input = new File(fileName);
//        val reader = input.openRead();
//        Console.OUT.println("s");
//        val height = reader.readShort();
//        Console.OUT.println(height);
//        val width = reader.readShort();
//        Console.OUT.println(width);
//
//        val region = (0..(height-1)) * (0..(width-1));
//        val data = new Array[Double](
//            region, (
//                (p:Point(region.rank)) => {
//                    val i = p(0);
//                    val j = p(1);
//                    Console.OUT.println("4");
//                    val value = reader.readDouble();
//                    Console.OUT.println("5");
//                    return value as Double;
//                }
//            )
//        );
//
//        reader.close();
//
//        for (var i: Int = 0; i < height; i++)
//            for (var j: Int = 0; j < width; j++)
//                Console.OUT.println(data(i, j));



//        var myRegion: Region{(self.rank == 1)} = (1..10);
//        var myDist: Dist{(self.rank == 1)} = (myRegion->here);
//        for (p: Point{(self.rank == 1)} in myDist)
//            Console.OUT.println(p + 1);


    }
}


