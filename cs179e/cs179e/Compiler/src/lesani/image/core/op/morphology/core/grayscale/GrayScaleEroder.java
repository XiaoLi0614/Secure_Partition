package lesani.image.core.op.morphology.core.grayscale;

import lesani.image.core.image.GSImage;
import lesani.image.core.op.core.base.UOp;
import lesani.image.core.op.core.masking.Mask;
import lesani.image.core.op.core.masking.Masker;
import lesani.collection.func.concrete.*;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 * Date: Apr 15, 2005
 * Time: 4:35:30 PM
 */
public class GrayScaleEroder extends UOp<GSImage> {
	private Mask mask;

	public GrayScaleEroder(Mask mask) {
		this.mask = mask;
	}

    @Override
    public GSImage compute(GSImage image) {

        Mask reflectedMask = mask.reflection();
        Masker<GSImage> masker = new Masker<GSImage>(
            reflectedMask,
            IntReverseSubtractFun.instance(),
            IntMinFun.instance()
        );

        return masker.compute(image);
	}

    public static GSImage compute(GSImage image, Mask mask) {
        return new GrayScaleEroder(mask).compute(image);
    }

}
