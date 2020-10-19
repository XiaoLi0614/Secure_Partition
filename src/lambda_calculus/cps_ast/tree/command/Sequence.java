package lambda_calculus.cps_ast.tree.command;
import lambda_calculus.cps_ast.visitor.BetaReduction;
import lambda_calculus.cps_ast.visitor.CPSVisitor;
import lambda_calculus.source_ast.tree.expression.Expression;
import lambda_calculus.source_ast.visitor.CPSPrinter;

public class Sequence extends Command {
    public Command command1;
    public Command command2;

    public Sequence(Command command_1, Command command_2) {
        this.command1 = command_1;
        this.command2 = command_2;
    }

    public <R> R accept(CPSVisitor.CommandVisitor<R> sequence){
        return sequence.visit(this);
    }
}
