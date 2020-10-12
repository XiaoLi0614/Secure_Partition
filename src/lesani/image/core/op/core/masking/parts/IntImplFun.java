package lesani.image.core.op.core.masking.parts;

import lesani.collection.func.concrete.IntBFun;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Mar 1, 2010
 * Time: 2:42:50 PM
 */

public class IntImplFun extends IntBFun {
    private static IntImplFun ourInstance = new IntImplFun();

    public static IntImplFun instance() {
        return ourInstance;
    }
    private IntImplFun() {
    }

    @Override
    public Integer apply(Integer p1, Integer p2) {
        return ((!(p1 == 1)) || (p2 == 1)) ? 1 : 0;
    }
}
