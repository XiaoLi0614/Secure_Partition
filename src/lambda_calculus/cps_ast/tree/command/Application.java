package lambda_calculus.cps_ast.tree.command;

import lambda_calculus.cps_ast.tree.expression.Var;
import lambda_calculus.cps_ast.visitor.BetaReduction;

public class Application extends Command {
    public Command function;
    public Command[] values;

    public Application(Command function, Command[] values) {
        this.function = function;
        this.values = values;
    }

    public Object accept(BetaReduction betaVisitor) {
        return betaVisitor.visit(this);
    }
}
