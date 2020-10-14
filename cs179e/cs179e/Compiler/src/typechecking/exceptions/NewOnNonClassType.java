package typechecking.exceptions;

import lesani.compiler.typing.exception.TypeErrorException;

/**
 * User: Mohsen's Desktop
 * Date: Aug 30, 2009
 */

public class NewOnNonClassType extends TypeErrorException {
	public NewOnNonClassType(String s) {
		super(s);
	}
}
