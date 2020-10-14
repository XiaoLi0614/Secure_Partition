package lesani.image.core.parallel.xtras;

import lesani.image.core.image.GSImage;
import lesani.image.core.op.core.masking.Mask;
import lesani.image.core.op.core.masking.Masker;
import lesani.collection.func.Fun;
import lesani.collection.vector.Pair;
import lesani.parallel.ForkJoin;
import lesani.parallel.ProcessPower;

import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Feb 27, 2010
 * Time: 8:55:59 PM
 */

public class ParallelMasker {

    private GSImage image;
    private Mask mask;
    private int processCount;

                                 
    public ParallelMasker(GSImage image, Mask mask/*, int processCount*/) {
        this.image = image;
        this.mask = mask;
//        this.processCount = processCount;
        this.processCount = ProcessPower.getNumProcessors();
    }

    public GSImage compute() throws ExecutionException, InterruptedException {
        ForkJoin<GSImage, Pair<GSImage, Integer>, GSImage, GSImage> forkJoin =
            new ForkJoin<GSImage, Pair<GSImage, Integer>, GSImage, GSImage>(
                new ForkJoin.Forker<GSImage, Pair<GSImage, Integer>>() {
                    public Pair<GSImage, Integer>[] fork(GSImage grayScaleImage) {
//                        System.out.println("Original image");
//                        System.out.println("Width = " + grayScaleImage.getWidth());
//                        System.out.println("Height = " + grayScaleImage.getHeight());
                        final Pair<GSImage, Integer>[] pairs = ParallelMasker.this.split(grayScaleImage);

/*
                        for (int i = 0; i < pairs.length; i++) {
                            Pair<GSImage, Integer> pair = pairs[i];
                            GSImage image = pair.getElement1();
                            Integer n = pair.getElement2();
                            //ImageUtil.showImage("Subimage " + n, image);
                            System.out.println("Subimage " + n);
                            System.out.println("Width = " + image.getWidth());
                            System.out.println("Height = " + image.getHeight());
                        }
*/
                        return pairs;

                    }
                },

                new Fun<Pair<GSImage, Integer>, GSImage>() {
                    public GSImage apply(Pair<GSImage, Integer> pair) {
                        GSImage image = pair.getElement1();
                        int number = pair.getElement2();

                        int x1;
                        int x2;
                        int y1;
                        int y2;
                        if (number == 0) {
                            x1 = 0;
                            x2 = image.getWidth() - mask.getWidth() / 2;
                        } else if (number == processCount - 1) {
                            x1 = mask.getWidth() / 2;
                            x2 = image.getWidth();
                        } else {
                            x1 = mask.getWidth() / 2;
                            x2 = image.getWidth() - mask.getWidth() / 2;
                        }
                        y1 = 0;
                        y2 = image.getHeight();
//                        System.out.println("----");
//                        System.out.println("Subimage " + number);
//                        System.out.println(x1);
//                        System.out.println(x2);
//                        System.out.println("----");
                        Masker<GSImage> masker =
                            new Masker<GSImage>(mask, x1, x2, y1, y2);
//                        ImageUtil.showImage("Subimage " + number, image);
                        final GSImage maskedImage = masker.compute(image);
/*
                        ImageUtil.showImage("Subimage " + number,
                                Scaler.instance().compute(
                                Abs.instance().compute(
                                maskedImage)
                                ));
*/
                        return maskedImage;
                    }
                },

                new ForkJoin.Joiner<GSImage, GSImage>() {
                    public GSImage join(GSImage[] images) {
//                        for (int i = 0; i < images.length; i++) {
//                            GSImage image = images[i];
//                            ImageUtil.showImage(image);
//                            System.out.println("Masked subimage " + i);
//                            System.out.println("Width = " + image.getWidth());
//                            System.out.println("Height = " + image.getHeight());
//                        }
                        return ParallelMasker.this.merge(images);
                    }
                },
//                processCount,
                new GSImage[processCount]
            );
        return forkJoin.compute(image);
    }

    private Pair<GSImage, Integer>[] split(GSImage image) {
        int partWidth = image.getWidth() / processCount;
        Pair<GSImage, Integer>[] parts = new Pair[processCount];
        int high = 0;
        for (int i = 0; i < processCount - 1; i++) {
            int low = high;
            high = low + partWidth;
            final GSImage subImage = extractSubImage(image, low, high);
//            ImageUtil.showImage("Subimage " + i, subImage);
            parts[i] = new Pair<GSImage, Integer>(subImage, i);
        }
        final GSImage subImage = extractSubImage(image, high, image.getWidth());
        parts[parts.length - 1] = new Pair<GSImage, Integer>(
                subImage, processCount - 1);
//        ImageUtil.showImage("Subimage " + (processCount-1), subImage);
        return parts;
    }

    private GSImage extractSubImage(GSImage image, int low, int high) {
        int x1;
        int x2;
        if (low == 0) {
            x1 = 0;
            x2 = high + (mask.getWidth() / 2);
        }
        else if (high == image.getWidth()) {
            x1 = low - (mask.getWidth() / 2);
            x2 = high;            
        }
        else {
            x1 = low - (mask.getWidth() / 2);
            x2 = high + (mask.getWidth() / 2);
        }
        final int width = x2 - x1;
        final int height = image.getHeight();
        GSImage subImage = new GSImage(width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                subImage.set(i, j, image.get(x1 + i, j));
            }
        }
        return subImage;
    }

    private GSImage merge(GSImage[] images) {
        final int height = image.getHeight();
        final int width = image.getWidth();
        GSImage maskedImage = new GSImage(width, height);
//        System.out.println("w: " + width);
//        System.out.println("h: " + height);
        int x = 0;
        for (int i = 0; i < images.length; i++) {
            GSImage maskedSubImage = images[i];
            int subimageWidth = maskedSubImage.getWidth();
            int subimageHeight = maskedSubImage.getHeight();
//            System.out.println("w(s): " + subimageWidth);
//            System.out.println("h(s): " + subimageHeight);
            for (int j = 0; j < subimageWidth; j++) {
                for (int k = 0; k < height; k++) {
//                    System.out.println("--");
//                    System.out.println(x);
//                    System.out.println(k);
//                    System.out.println("--");
                    maskedImage.valueUncheckedSet(x, k, maskedSubImage.get(j, k));
                }
                x++;
            }
        }
        return maskedImage;
    }
}
