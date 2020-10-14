package jsrc.matlab.typeinference.exceptions;

import jsrc.matlab.ast.tree.expression.op.Op;
import lesani.compiler.ast.LocInfo;

public class SemanticErrorException extends RuntimeException {
    public LocInfo locInfo;
    public int compilationUnitNo;

    public SemanticErrorException(int compilationUnitNo, LocInfo locInfo, String message) {
        super(message);
        this.locInfo = locInfo;
        this.compilationUnitNo = compilationUnitNo;
    }
}
