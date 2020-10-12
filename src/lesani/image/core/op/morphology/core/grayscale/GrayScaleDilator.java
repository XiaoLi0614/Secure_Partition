package lesani.image.core.op.morphology.core.grayscale;

import lesani.image.core.image.GSImage;
import lesani.image.core.op.core.base.UOp;
import lesani.image.core.op.core.masking.Mask;
import lesani.image.core.op.core.masking.Masker;
import lesani.collection.func.concrete.IntAddFun;
import lesani.collection.func.concrete.IntMaxFun;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 * Date: Apr 15, 2005
 * Time: 4:35:17 PM
 */

public class GrayScaleDilator extends UOp<GSImage> {
	private Mask mask;

	public GrayScaleDilator(Mask mask) {
		this.mask = mask;
	}

    @Override
    public GSImage compute(GSImage image) {

        Mask reflectedMask = mask.reflection();
        Masker<GSImage> masker = new Masker<GSImage>(
            reflectedMask,
            IntAddFun.instance(),
            IntMaxFun.instance()
        );

        return masker.compute(image);
	}

    public static GSImage compute(GSImage image, Mask mask) {
        return new GrayScaleDilator(mask).compute(image);
    }    
}
