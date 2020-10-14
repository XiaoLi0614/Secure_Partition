package lesani.image.core.op.core.masking.parts;

import lesani.collection.func.concrete.IntBFun;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Mar 1, 2010
 * Time: 2:42:50 PM
 */
public class IntAndFun extends IntBFun {
    private static IntAndFun ourInstance = new IntAndFun();

    public static IntAndFun instance() {
        return ourInstance;
    }
    private IntAndFun() {
    }

    @Override
    public Integer apply(Integer p1, Integer p2) {
        if ((p1 == 1) && (p2 == 1))
            return 1;
        return 0;
    }
}
