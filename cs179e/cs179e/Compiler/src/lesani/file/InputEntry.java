package lesani.file;

import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Mar 2, 2010
 * Time: 2:05:59 PM
 */

public class InputEntry {

    String relDirOrFileRelPathName;
    Option<String> relOutDirOrOutFileRelPathName = None.instance();
    // relOut
    //      when only the out dir is specified for the entry.
    //      Output files have the same names as input files.
    // relPathName
    //      when the input of the entry is a file.
    //      The name of the output file can be specified in this field.

    public InputEntry(String relDirOrFileRelPathName) {
        this.relDirOrFileRelPathName = relDirOrFileRelPathName;
    }

    public InputEntry(String relDirOrFileRelPathName, String outAddress) {
        this.relDirOrFileRelPathName = relDirOrFileRelPathName;
        this.relOutDirOrOutFileRelPathName = new Some<String>(outAddress);
    }

}


