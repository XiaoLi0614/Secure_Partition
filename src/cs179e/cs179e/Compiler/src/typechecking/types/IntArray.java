package typechecking.types;

/**
 * User: Mohsen's Desktop
 * Date: Aug 28, 2009
 */

public class IntArray implements Type {
	private static IntArray ourInstance = new IntArray();

	public static IntArray instance() {
		return ourInstance;
	}

	private IntArray() {
	}

	public String name() {
		return "IntArray";
	}
}
