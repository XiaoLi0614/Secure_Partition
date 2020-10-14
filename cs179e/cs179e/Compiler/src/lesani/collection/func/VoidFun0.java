package lesani.collection.func;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Feb 27, 2010
 * Time: 1:43:52 AM
 */

public class VoidFun0<T1> implements Fun0 {
    private static VoidFun0 theInstance = new VoidFun0();

    public T1 apply() {
        return null;
    }

    public static VoidFun0 instance() {
        return theInstance;
    }
}