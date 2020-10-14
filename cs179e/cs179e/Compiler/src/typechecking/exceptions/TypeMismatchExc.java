package typechecking.exceptions;

import lesani.compiler.typing.exception.InternalTypingException;

/**
 * User: Mohsen's Desktop
 * Date: Aug 30, 2009
 */

public class TypeMismatchExc extends InternalTypingException {
	public static final int NUMBER_OF_ARGUMENTS = -1;
	public int inequalArgument;

	public TypeMismatchExc() {
		super("");
	}

	public TypeMismatchExc(int inequalArgument) {
		super("");
		this.inequalArgument = inequalArgument;
	}
}
