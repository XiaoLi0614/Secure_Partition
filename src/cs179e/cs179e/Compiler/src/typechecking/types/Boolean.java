package typechecking.types;

/**
 * User: Mohsen's Desktop
 * Date: Aug 28, 2009
 */

public class Boolean implements Type {
	private static Boolean ourInstance = new Boolean();

	public static Boolean instance() {
		return ourInstance;
	}

	private Boolean() {
	}

	public String name() {
		return "Boolean";
	}
}

