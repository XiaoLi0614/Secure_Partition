package typechecking.exceptions;

import lesani.compiler.typing.exception.TypeErrorException;

/**
 * User: Mohsen's Desktop
 * Date: Aug 30, 2009
 */

public class DuplicateTypeDefExc extends TypeErrorException {
	public DuplicateTypeDefExc(String s) {
		super(s);
	}
}