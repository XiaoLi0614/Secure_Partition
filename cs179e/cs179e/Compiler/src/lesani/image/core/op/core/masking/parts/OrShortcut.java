package lesani.image.core.op.core.masking.parts;

import lesani.collection.func.concrete.IntBoolFun;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Mar 1, 2010
 * Time: 2:42:50 PM
 */
public class OrShortcut extends IntBoolFun {
    private static OrShortcut ourInstance = new OrShortcut();
    public static OrShortcut instance() {
        return ourInstance;
    }
    private OrShortcut() {
    }

    @Override
    public Boolean apply(Integer p1) {
        return p1 == 1;
    }
}