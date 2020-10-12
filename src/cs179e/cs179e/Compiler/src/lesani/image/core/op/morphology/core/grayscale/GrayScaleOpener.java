package lesani.image.core.op.morphology.core.grayscale;

import lesani.image.core.image.GSImage;
import lesani.image.core.op.core.base.UOp;
import lesani.image.core.op.core.masking.Mask;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 * Date: Apr 15, 2005
 * Time: 5:49:00 PM
 */

public class GrayScaleOpener extends UOp<GSImage> {

    private Mask mask;

    public GrayScaleOpener(Mask mask) {
        this.mask = mask;
    }

    public GrayScaleOpener(int maskLength) {
        this.mask = Mask.newFull(maskLength, maskLength);
    }

    @Override
    public GSImage compute(GSImage image) {
        return
            GrayScaleDilator.compute(
                GrayScaleEroder.compute(
                    image,
                    mask),
                mask
            );
    }

    public static GSImage compute(GSImage image, Mask mask) {
        return new GrayScaleOpener(mask).compute(image);
    }

    public static GSImage compute(GSImage image, int maskLength) {
        return new GrayScaleOpener(maskLength).compute(image);
    }
}
