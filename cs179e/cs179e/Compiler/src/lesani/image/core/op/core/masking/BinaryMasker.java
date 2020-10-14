package lesani.image.core.op.core.masking;

import lesani.image.core.image.BImage;
import lesani.image.core.op.core.base.UOp;
import lesani.image.core.op.core.masking.parts.IntAndFun;
import lesani.image.core.op.core.masking.parts.IntOrFun;
import lesani.image.core.op.core.masking.parts.OrShortcut;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Mar 1, 2010
 * Time: 3:21:03 PM
 */

public class BinaryMasker extends UOp<BImage> {
    private Mask mask;

    public BinaryMasker(Mask mask) {
        this.mask = mask;
    }

    @Override
    public BImage compute(BImage image) {

        Masker<BImage> masker = new Masker<BImage>(
            mask,
            IntAndFun.instance(),
            IntOrFun.instance(),
            OrShortcut.instance()
        );

        return masker.compute(image);
    }
}

