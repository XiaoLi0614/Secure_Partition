package lesani.collection.func.concrete;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Feb 27, 2010
 * Time: 1:48:25 AM
 */

public class IntSubtractFun extends IntBFun {
    private static IntSubtractFun ourInstance = new IntSubtractFun();

    public static IntSubtractFun instance() {
        return ourInstance;
    }
    private IntSubtractFun() {
    }

    @Override
    public Integer apply(Integer p1, Integer p2) {
        return p1 - p2;
    }
}