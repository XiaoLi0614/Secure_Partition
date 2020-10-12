package lesani.image.core.parallel.xtras;

import lesani.image.core.image.GSImage;
import lesani.image.core.op.core.masking.Mask;
import lesani.image.core.parallel.xtras.ParallelMasker;

import java.util.concurrent.ExecutionException;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Feb 28, 2010
 * Time: 12:16:26 PM
 */

public class ParallelEdgeDetector {

    public GSImage compute(GSImage image)
            throws ExecutionException, InterruptedException {
        ParallelMasker masker = new ParallelMasker(
                image,
                Mask.Sharpening.FirstDerivative.rightDiagonalSobelMask
//                2
        );
        GSImage resultImage = masker.compute();
        return resultImage;
    }

    public static GSImage parallelEdgeDetect(GSImage image) {
        try {
            return new ParallelEdgeDetector().compute(image);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
