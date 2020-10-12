package lesani.image.core.op.core.masking.parts;

import lesani.collection.func.concrete.IntBoolFun;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Mar 1, 2010
 * Time: 2:42:50 PM
 */
public class AndShortcut extends IntBoolFun {
    private static AndShortcut ourInstance = new AndShortcut();
    public static AndShortcut instance() {
        return ourInstance;
    }
    private AndShortcut() {
    }

    @Override
    public Boolean apply(Integer p) {
        return p == 0;
    }
}