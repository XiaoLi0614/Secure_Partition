package lesani.image.core.parallel;

import lesani.image.core.image.GSImage;
import lesani.image.core.op.core.masking.EdgeDetector;
import lesani.image.util.exception.NotAnImageFileException;
import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;
import lesani.parallel.ProcessPower;

import static lesani.image.util.ImageUtil.readGSImage;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: May 26, 2010
 * Time: 11:05:30 PM
 */

public class ParallelEdgeDetectorLauncher {

    public static void process(String fileName, Option<Integer> processorCount) throws NotAnImageFileException {

        if (processorCount instanceof Some) {
            int i = ((Some<Integer>) processorCount).get();
            ProcessPower.setNumThreads(i);
        }

        GSImage inputImage = readGSImage(fileName);
        GSImage outputImage;
        long t1;
        long t2;

        System.out.println("=======================");
        System.out.println("ThreadCount = " + ProcessPower.getNumThreads());
        System.out.println("-----------------------");
        t1 = System.currentTimeMillis();
//        outputImage = EdgeDetector.Scaled.Sobel.RightDiagonal.process(inputImage);
        outputImage = EdgeDetector.Sobel.RightDiagonal.process(inputImage);
        t2 = System.currentTimeMillis();

        t1 = System.currentTimeMillis();
//        outputImage = EdgeDetector.Scaled.Sobel.RightDiagonal.process(inputImage);
        outputImage = EdgeDetector.Sobel.RightDiagonal.process(inputImage);
        t2 = System.currentTimeMillis();

        System.out.println("Sequential Time = " + (t2-t1));
        System.out.println("-----------------------");
/*
        t1 = System.currentTimeMillis();
        outputImage = ParallelEdgeDetector.parallelEdgeDetect(inputImage);
        t2 = System.currentTimeMillis();

        t1 = System.currentTimeMillis();
        outputImage = ParallelEdgeDetector.parallelEdgeDetect(inputImage);
        t2 = System.currentTimeMillis();

        System.out.println("Parallel/ForkJoin Time = " + (t2-t1));
*/

        t1 = System.currentTimeMillis();
//        outputImage = EdgeDetector.Scaled.Sobel.RightDiagonal.Parallel.process(inputImage);
        outputImage = EdgeDetector.Sobel.RightDiagonal.Parallel.process(inputImage);
        t2 = System.currentTimeMillis();

        t1 = System.currentTimeMillis();
//        outputImage = EdgeDetector.Scaled.Sobel.RightDiagonal.Parallel.process(inputImage);
        outputImage = EdgeDetector.Sobel.RightDiagonal.Parallel.process(inputImage);
        t2 = System.currentTimeMillis();

        System.out.println("Parallel/ParFor Time = " + (t2-t1));
        System.out.println("-----------------------");
        System.out.println("=======================");        
//        ImageUtil.showImage(inputImage);
//        ImageUtil.showImage(outputImage);
    }

    public static void main(String[] args) throws NotAnImageFileException {
        final String fileName;
        Option<Integer> processorCount;
        if (args.length == 0) {
            fileName =
    //                "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Lena.jpg";
                    "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/lena124.jpg";
    //                "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/lena256.jpg";
//                "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/lena512.jpg";

    //                "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Landscape1.jpg";
    //                "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Landscape2.jpg";

            processorCount = None.instance();
        }
        else {
            fileName = args[0];
            int i = Integer.parseInt(args[1]);
            processorCount = new Some<Integer>(i);
        }
        process(fileName, processorCount);
        ProcessPower.turnOff();
    }

/*
    Results:
        lena124:
            Sequential Time = 73
            Parallel/ParFor Time = 56
            -----
            Sequential Time = 93
            Parallel/ParFor Time = 47
        lena256:
            Sequential Time = 145
            Parallel/ParFor Time = 99
            -----
            Sequential Time = 132
            Parallel/ParFor Time = 78
        lena512:
            Sequential Time = 359
            Parallel/ParFor Time = 244
            -----
            Sequential Time = 360
            Parallel/ParFor Time = 266            
*/

}
