package lesani.image.registration;

import lesani.image.core.image.GSImage;
import lesani.image.util.exception.NotAnImageFileException;
import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;
import lesani.collection.vector.Vector;
import lesani.parallel.ProcessPower;

import static lesani.image.registration.ParallelSimilarityMeasurer.parallelMeasureSimilarity;
import static lesani.image.registration.SimilarityMeasurer.measureSimilarity;
import static lesani.image.util.ImageUtil.*;


/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: May 4, 2010
 * Time: 5:27:45 PM
 */

public class ParallelSimilarityMeasurerLauncher {

    public static void process(String fileName, Option<Integer> processorCount) throws NotAnImageFileException {

        if (processorCount instanceof Some) {
            int i = ((Some<Integer>) processorCount).get();
            ProcessPower.setNumThreads(i);
        }

        GSImage inputImage = readGSImage(fileName);

        Transformer identity = new Transformer() {
            public Vector apply(Vector v) {
                return v;
            }
        };

        double similarity;

        long t1;
        long t2;
        long totalTime;

        System.out.println("=======================");
        System.out.println("ThreadCount = " + ProcessPower.getNumThreads());        
        System.out.println("-----------------------");
        t1 = System.currentTimeMillis();
        similarity = measureSimilarity(inputImage, inputImage, identity);
        t2 = System.currentTimeMillis();

        t1 = System.currentTimeMillis();
        similarity = measureSimilarity(inputImage, inputImage, identity);
        t2 = System.currentTimeMillis();
        totalTime = t2 - t1;
        System.out.println("similarity = " + similarity);
        System.out.println("Serial time = " + totalTime);

        System.out.println("-----------------------");
        t1 = System.currentTimeMillis();
        similarity = parallelMeasureSimilarity(inputImage, inputImage, identity);
        t2 = System.currentTimeMillis();

        t1 = System.currentTimeMillis();
        similarity = parallelMeasureSimilarity(inputImage, inputImage, identity);
        t2 = System.currentTimeMillis();
        totalTime = t2 - t1;
        System.out.println("similarity = " + similarity);
        System.out.println("Parallel time = " + totalTime);
        System.out.println("-----------------------");
        System.out.println("=======================");
        
//        System.out.println(similarity);

    }

    public static void main(String[] args) throws NotAnImageFileException {
        final String fileName;
        Option<Integer> processorCount;
        if (args.length == 0) {
            fileName =
    //                "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Lena.jpg";
    //                "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/lena124.jpg";
    //                "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/lena256.jpg";
//                "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/lena512.jpg";

                    "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Landscape1.jpg";
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
}
