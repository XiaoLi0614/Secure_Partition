package lesani.collection.func.concrete;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Feb 27, 2010
 * Time: 1:48:25 AM
 */

public class IntReverseSubtractFun extends IntBFun {
    private static IntReverseSubtractFun ourInstance = new IntReverseSubtractFun();

    public static IntReverseSubtractFun instance() {
        return ourInstance;
    }
    private IntReverseSubtractFun() {
    }

    @Override
    public Integer apply(Integer p1, Integer p2) {
        return p2 - p1;
    }
}