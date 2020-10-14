package lesani.image.core.op.morphology.core.binary.core;

import lesani.image.core.image.BImage;
import lesani.image.core.op.core.base.UOp;
import lesani.image.core.op.core.masking.Mask;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 * Date: Apr 15, 2005
 * Time: 12:02:17 PM
 */

public class Opener extends UOp<BImage> {
	private Mask mask;

    public Opener(Mask mask) {
        this.mask = mask;
    }

    public Opener(int maskLength) {
		this.mask = Mask.newFull(maskLength, maskLength);
	}

    @Override
    public BImage compute(BImage image) {
		return
            Dilator.compute(
                Eroder.compute(
                    image,
                    mask),
                mask
            );
	}

    public static BImage compute(BImage image, Mask mask) {
        return new Opener(mask).compute(image);
    }

    public static BImage compute(BImage image, int maskLength) {
        return new Opener(maskLength).compute(image);
    }
    
}
