package jsrc.x10.ast.visitor;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.tree.statement.*;
import jsrc.x10.ast.tree.statement.x10.X10Statement;
import lesani.collection.func.Fun;
import lesani.collection.func.Fun0;
import lesani.collection.option.Option;
import lesani.collection.option.Some;

public interface StatementVisitor {
    public Object visit(Assignment assignment);

    public Object visit(ExpSt expSt);
    public Object visit(Throw theThrow);
    public Object visit(ValueReturn valueReturn);
    public Object visit(VoidReturn voidReturn);

    public Object visit(If theIf);

    public Object visit(While theWhile);

    public Object visit(DoWhile doWhile);

    public Object visit(Break theBreak);

    public Object visit(Continue theContinue);

    public Object visit(Switch theSwitch);

    public Object visit(Block block);

    public Object visit(X10Statement x10Statement);
}
