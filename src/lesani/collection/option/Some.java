package lesani.collection.option;

import lesani.collection.func.Fun;
import lesani.collection.func.Fun0;

import java.io.Serializable;

/**
 * User: Mohsen's Desktop
 * Date: Aug 27, 2009
 */

public class Some<T> extends Option<T> implements Serializable {
	T object;

	public Some(T object) {
		this.object = object;
	}

	public boolean isPresent() {
		return (object != null);
        // Interesting!

	}

    @Override
    public <T2> T2 apply(Fun0<T2> fNone, Fun<T, T2> fSome) {
        return fSome.apply(object);
    }

    @Override
    public <T2> Option<T2> apply(Fun<T, T2> fSome) {
        return new Some<T2>(fSome.apply(object));
    }

    @Override
    public T value() {
        return object;
    }

    public T get() {
		return object;
	}

	public void set(T object) {
		this.object = object;
	}

    @Override
    public String toString() {
        return object.toString();
    }
}
