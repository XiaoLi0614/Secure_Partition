package lesani.file;

import lesani.collection.option.Option;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Nov 16, 2010
 * Time: 3:35:09 PM
 */
public abstract class MultipleFileProcess implements Process {
    public abstract Option<Output[]> process(java.io.File[] files, Logger logger) throws Exception;
    public abstract void exceptionHandler(Exception exception);
}
