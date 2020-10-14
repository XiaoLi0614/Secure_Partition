package lesani.collection.func.concrete;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Feb 27, 2010
 * Time: 1:48:25 AM
 */

public class IntMinFun extends IntBFun {
    private static IntMinFun ourInstance = new IntMinFun();

    public static IntMinFun instance() {
        return ourInstance;
    }
    private IntMinFun() {
    }

    @Override
    public Integer apply(Integer p1, Integer p2) {
        return Math.min(p1, p2);
    }
}