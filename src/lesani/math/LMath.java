package lesani.math;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Mar 25, 2010
 * Time: 7:32:34 PM
 */
public class LMath {
    public static int sign (double d) {
        if (Double.compare(d, 0) < 0)
            return -1;
        else
            return +1;
    }

    public static String plusOneOrdinal(int i) {
        i = i + 1;
        switch (i%10) {
            case 1:
                return i + "st";
            case 2:
                return i + "nd";
            case 3:
                return i + "rd";
            default:
                return i + "th";
        }
    }

}
