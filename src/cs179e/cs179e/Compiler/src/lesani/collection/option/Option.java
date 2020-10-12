package lesani.collection.option;

import lesani.collection.func.Fun;
import lesani.collection.func.Fun0;

/**
 * User: Mohsen's Desktop
 * Date: Aug 27, 2009
 */

public abstract class Option<T1> {

	public abstract boolean isPresent();

    public abstract T1 value();

    public abstract <T2> T2 apply(Fun0<T2> fNone, Fun<T1, T2> fSome);

    public abstract <T2> Option<T2> apply(Fun<T1, T2> fSome);
}
