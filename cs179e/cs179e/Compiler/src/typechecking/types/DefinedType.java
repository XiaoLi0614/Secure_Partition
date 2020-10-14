package typechecking.types;

/**
 * User: Mohsen's Desktop
 * Date: Aug 29, 2009
 */

public class DefinedType implements Type {
	public String name;

	public DefinedType(String name) {
		this.name = name;
	}

	public String name() {
		return name;
	}
}
