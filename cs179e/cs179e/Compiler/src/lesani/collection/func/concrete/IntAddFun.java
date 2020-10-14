package lesani.collection.func.concrete;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Feb 27, 2010
 * Time: 1:48:25 AM
 */

public class IntAddFun extends IntBFun {
    private static IntAddFun ourInstance = new IntAddFun();

    public static IntAddFun instance() {
        return ourInstance;
    }
    private IntAddFun() {
    }

    @Override
    public Integer apply(Integer p1, Integer p2) {
        return p1 + p2;
    }
}
