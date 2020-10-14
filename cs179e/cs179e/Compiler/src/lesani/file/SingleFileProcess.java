package lesani.file;

import lesani.collection.option.Option;
import lesani.compiler.texttree.Printable;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Nov 16, 2010
 * Time: 3:34:56 PM
 */

public abstract class SingleFileProcess implements Process {
    public abstract Option<Printable> process(java.io.File file, Logger logger) throws Exception;
    public abstract void exceptionHandler(Exception e);
}

