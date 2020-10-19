package lambda_calculus.cps_ast.tree.command;

import lambda_calculus.cps_ast.tree.expression.Var;
import lambda_calculus.cps_ast.visitor.BetaReduction;
import lambda_calculus.cps_ast.visitor.CPSVisitor;

public class Application extends Command {
    public Command function;
    public Command[] values;

    public Application(Command function, Command[] values) {
        this.function = function;
        this.values = values;
    }

    public <R> R accept(CPSVisitor.CommandVisitor<R> application){
        return application.visit(this);
    }
}
