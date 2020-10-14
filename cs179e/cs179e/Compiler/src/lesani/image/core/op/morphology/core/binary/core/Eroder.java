package lesani.image.core.op.morphology.core.binary.core;

import lesani.image.core.image.BImage;
import lesani.image.core.op.core.base.UOp;
import lesani.image.core.op.core.masking.Mask;
import lesani.image.core.op.core.masking.Masker;
import lesani.image.core.op.core.masking.parts.*;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 * Date: Apr 14, 2005
 * Time: 4:43:02 PM
 */

public class Eroder extends UOp<BImage>
{
    private Mask mask;

    public Eroder(Mask mask) {
        this.mask = mask;
    }

    @Override
    public BImage compute(BImage image) {

        Masker<BImage> masker = new Masker<BImage>(
            mask,
            IntImplFun.instance(),
            IntAndFun.instance(),
            AndShortcut.instance()
        );
        return masker.compute(image);
    }

    public static BImage compute(BImage image, Mask mask) {
        return new Eroder(mask).compute(image);        
    }
}
