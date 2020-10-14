package lesani.collection.vector;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Feb 28, 2010
 * Time: 12:54:50 AM
 */
public class Pair<T1, T2> {
    T1 element1;
    T2 element2;

    public Pair(T1 element1, T2 element2) {
        this.element1 = element1;
        this.element2 = element2;
    }

    public T1 getElement1() {
        return element1;
    }

    public void setElement1(T1 element1) {
        this.element1 = element1;
    }

    public T2 getElement2() {
        return element2;
    }

    public void setElement2(T2 element2) {
        this.element2 = element2;
    }
}
