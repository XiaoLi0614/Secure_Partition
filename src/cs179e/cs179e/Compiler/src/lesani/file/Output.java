package lesani.file;

import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;
import lesani.compiler.texttree.Printable;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Nov 16, 2010
 * Time: 3:31:38 PM
 */

public class Output {
    Option<String> name = None.instance();
    Printable printable;

    public Output(String name, Printable printable) {
        this.name = new Some<String>(name);
        this.printable = printable;
    }

    public Output(Printable printable) {
        this.printable = printable;
    }
}
