

public class ImageTest {

    public static def main(args: Array[String]) /*throws Exception*/ {
        val inputFileName: String =
//            "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/TestImages/BigHorse.format";
//            "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Medical/Brain.format";
              "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/2.Projects/0.Project/0.Project/src/msrc/benchmarks/rd/KneeMRI.format";
        val outFileName: String =
//            "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/TestImages/BigHorse2.format";
//            "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Medical/Brain2.format";
            "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/2.Projects/0.Project/0.Project/src/msrc/benchmarks/rd/KneeMRI2.format";


        val image = ImageUtil.readFile(inputFileName);
//        Console.OUT.println(image.width());
//        Console.OUT.println(image.height());

        ImageUtil.writeFile(image, outFileName);
    }

}
