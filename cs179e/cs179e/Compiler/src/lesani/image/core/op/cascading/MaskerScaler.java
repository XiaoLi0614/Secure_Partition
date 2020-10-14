package lesani.image.core.op.cascading;

import lesani.image.core.image.GSImage;
import lesani.image.core.op.core.Scaler;
import lesani.image.core.op.core.base.UOp;
import lesani.image.core.op.core.masking.Mask;
import lesani.image.core.op.core.masking.Masker;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Feb 27, 2010
 * Time: 5:04:15 PM
 */

public class MaskerScaler extends UOp<GSImage> {
    private Masker<GSImage> masker;

    public MaskerScaler(Mask mask) {
        this.masker = new Masker<GSImage>(mask);
    }

/*
    public MaskerAdderScaler(Mask mask, int maskFactor, int imageFactor) {
        this.masker = new Masker<GSImage>(mask, maskFactor, imageFactor);
    }
*/

    @Override
    public GSImage compute(GSImage image) {
        GSImage masked = masker.compute(image);
        return Scaler.instance().compute(masked);
    }

    public static GSImage process(GSImage image, Mask mask) {
        return new MaskerScaler(mask).compute(image);
    }

}