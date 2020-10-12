package lesani.image.core.op.morphology.core.grayscale;

import lesani.image.core.image.GSImage;
import lesani.image.core.op.core.base.UOp;
import lesani.image.core.op.core.masking.Mask;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 * Date: Apr 15, 2005
 * Time: 5:49:13 PM
 */

public class GrayScaleCloser extends UOp<GSImage> {

    private Mask mask;

    public GrayScaleCloser(Mask mask) {
        this.mask = mask;
    }

    public GrayScaleCloser(int maskLength) {
        this.mask = Mask.newFull(maskLength, maskLength);
    }

    @Override
    public GSImage compute(GSImage image) {
        return
            GrayScaleEroder.compute(
                GrayScaleDilator.compute(
                    image,
                    mask),
                mask
            );
    }

    public static GSImage compute(GSImage image, Mask mask) {
        return new GrayScaleCloser(mask).compute(image);
    }

    public static GSImage compute(GSImage image, int maskLength) {
        return new GrayScaleCloser(maskLength).compute(image);
    }
}
