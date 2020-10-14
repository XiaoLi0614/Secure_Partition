package typechecking.types;

/**
 * User: Mohsen's Desktop
 * Date: Aug 28, 2009
 */

public class Void implements Type {
	private static Void ourInstance = new Void();

	public static Void instance() {
		return ourInstance;
	}

	private Void() {
	}

	public String name() {
		return "Void";
	}
}
