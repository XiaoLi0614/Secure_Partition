package lesani.collection.func.concrete;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Feb 27, 2010
 * Time: 1:48:25 AM
 */

public class IntMaxFun extends IntBFun {
    private static IntMaxFun ourInstance = new IntMaxFun();

    public static IntMaxFun instance() {
        return ourInstance;
    }
    private IntMaxFun() {
    }

    @Override
    public Integer apply(Integer p1, Integer p2) {
        return Math.max(p1, p2);
    }
}