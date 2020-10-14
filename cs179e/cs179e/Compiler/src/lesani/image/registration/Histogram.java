package lesani.image.registration;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: May 4, 2010
 * Time: 6:48:06 PM
 */
public class Histogram {
    int[][] bins;
    int total;

    public Histogram() {
        bins = new int[256][256];
    }
}
