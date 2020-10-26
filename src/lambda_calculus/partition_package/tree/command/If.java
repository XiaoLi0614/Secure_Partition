package lambda_calculus.partition_package.tree.command;

import lambda_calculus.partition_package.tree.expression.Expression;
import lambda_calculus.partition_package.visitor.PartitionVisitor;

public class If extends Command {
    public Expression condition;
    public Command command1;
    public Command command2;

    public If(Expression e, Command c1, Command c2) {
        this.condition = e;
        this.command1 = c1;
        this.command2 = c2;
    }

    public <R> R accept(PartitionVisitor.CommandVisitor<R> iF){
        return iF.visit(this);
    }

    @Override
    public String toString(){
        return "If (" + condition + ") then (" + command1 +") else (" + command2 + ")";
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        If that = (If) o;

        if(!condition.equals(that.condition)) return false;
        else if (! command1.equals(that.command1)) return false;
        else return command2.equals(that.command2);
    }
}
