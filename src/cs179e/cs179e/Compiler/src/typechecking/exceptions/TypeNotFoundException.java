package typechecking.exceptions;

import lesani.compiler.typing.exception.TypeErrorException;

/**
 * User: Mohsen's Desktop
 * Date: Aug 30, 2009
 */

public class TypeNotFoundException extends TypeErrorException {
	public TypeNotFoundException(String s) {
		super(s);
	}
}
