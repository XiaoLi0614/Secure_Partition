package lesani.collection.func;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Feb 27, 2010
 * Time: 1:43:52 AM
 */

public interface Fun<T1, T2> {
    public abstract T2 apply(T1 input);
}