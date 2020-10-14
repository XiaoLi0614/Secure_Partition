/**
 * User: Mohsen's Desktop
 * Date: Aug 27, 2009
 */

package lesani.collection.option;

import lesani.collection.func.Fun;
import lesani.collection.func.Fun0;

// None Should be defined as:
// public None extends Option<Nothing>
// And Option<T> should be covariant for T (An Option[Sub] is an Option[Super]).
// Nothing is Subclass of anything.
// This None is an Option[T] for any T.
// But there is no Nothing in Java.


public class None<T1> extends Option<T1> {
	private static None theInstance = new None();

    public static None instance() {
		return theInstance;
	}

	private None() {
	}

	public boolean isPresent() {
		return false;
	}

    @Override
    public <T2> T2 apply(Fun0<T2> fNone, Fun<T1, T2> fSome) {
        return fNone.apply();
    }

    @Override
    public <T2> Option<T2> apply(Fun<T1, T2> fSome) {
        return None.instance();
    }

    @Override
    public T1 value() {
        throw new RuntimeException("None contains no value.");
    }

    @Override
    public String toString() {
        return "None";
    }
}
