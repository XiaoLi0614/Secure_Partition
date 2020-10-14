/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Feb 27, 2010
 * Time: 4:39:50 PM
 * To change this template use File | Settings | File Templates.
 */
package lesani.image.core.op.cascading;

import lesani.image.core.image.GSImage;
import lesani.image.core.op.core.Adder;
import lesani.image.core.op.core.Scaler;
import lesani.image.core.op.core.base.MOp;
import lesani.image.core.op.core.base.UOp;

public class AdderScaler extends MUCascader<GSImage> {
    private static AdderScaler ourInstance = new AdderScaler();

    public static AdderScaler instance() {
        return ourInstance;
    }

    private AdderScaler() {
        super(
                null,
                //new UOp<GSImage>[] {Scaler.instance()}
                new UOp[] {Scaler.instance()}
        );
        MOp<GSImage> mOp = Adder.instance();
        setMOperation(mOp);
    }
}


