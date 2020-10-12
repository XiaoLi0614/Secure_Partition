package typechecking.types;

/**
 * User: Mohsen's Desktop
 * Date: Aug 25, 2009
 */


public class Int implements Type {
	private static Int ourInstance = new Int();

	public static Int instance() {
		return ourInstance;
	}

	private Int() {
	}

	public String name() {
		return "Int";
	}
}
