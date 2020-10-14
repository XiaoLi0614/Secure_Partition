package lesani.image.core.op.morphology.core.binary.core;

import lesani.image.core.image.BImage;
import lesani.image.core.op.core.base.UOp;
import lesani.image.core.op.core.masking.BinaryMasker;
import lesani.image.core.op.core.masking.Mask;


/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 * Date: Apr 14, 2005
 * Time: 3:21:39 PM
 */

public class Dilator extends UOp<BImage>
{
	private Mask mask;

    public Dilator(Mask mask) {
        this.mask = mask;
    }

    @Override
    public BImage compute(BImage image) {
        Mask reflectedMask = mask.reflection();
        BinaryMasker binaryMasker = new BinaryMasker(reflectedMask);
        return binaryMasker.compute(image);
    }

    public static BImage compute(BImage image, Mask mask) {
        return new Dilator(mask).compute(image);
    }
}

