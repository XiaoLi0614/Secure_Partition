package lesani.image.core.op.morphology.core.binary.core;

import lesani.image.core.image.BImage;
import lesani.image.core.op.core.base.UOp;
import lesani.image.core.op.core.masking.Mask;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 * Date: Apr 15, 2005
 * Time: 12:03:18 PM
 */
public class Closer extends UOp<BImage> {

    private Mask mask;

    public Closer(Mask mask) {
        this.mask = mask;
    }

    public Closer(int maskLength) {
        this.mask = Mask.newFull(maskLength, maskLength);
    }

    @Override
    public BImage compute(BImage image) {
        return
            Eroder.compute(
                Dilator.compute(    
                    image,
                    mask),
                mask
            );
    }

    public static BImage compute(BImage image, Mask mask) {
        return new Closer(mask).compute(image);
    }

    public static BImage compute(BImage image, int maskLength) {
        return new Closer(maskLength).compute(image);
    }
}
