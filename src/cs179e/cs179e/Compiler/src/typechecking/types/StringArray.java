package typechecking.types;

/**
 * User: Mohsen's Desktop
 * Date: Aug 28, 2009
 */

public class StringArray implements Type {
	private static StringArray ourInstance = new StringArray();

	public static StringArray instance() {
		return ourInstance;
	}

	private StringArray() {
	}

	public String name() {
		return "StringArray";
	}
}
