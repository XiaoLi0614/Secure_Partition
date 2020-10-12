package lesani.image.core.op.core.masking.parts;

import lesani.collection.func.concrete.IntBFun;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Mar 1, 2010
 * Time: 2:42:50 PM
 */
public class IntOrFun extends IntBFun {

    private static IntOrFun ourInstance = new IntOrFun();

    public static IntOrFun instance() {
        return ourInstance;
    }
    private IntOrFun() {
    }
    
    @Override
    public Integer apply(Integer p1, Integer p2) {
        if ((p1 == 1) || (p2 == 1))
            return 1;
        return 0;
    }
}