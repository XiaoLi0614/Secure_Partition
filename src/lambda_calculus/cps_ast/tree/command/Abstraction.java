package lambda_calculus.cps_ast.tree.command;

import lambda_calculus.cps_ast.tree.expression.Var;
import lambda_calculus.cps_ast.visitor.BetaReduction;
import lambda_calculus.cps_ast.visitor.CPSVisitor;
import lambda_calculus.cps_ast.tree.expression.Expression;
import sun.nio.ch.AbstractPollArrayWrapper;

public class Abstraction extends Command {
    public Var[] lambdas;
    public Command body;
    //todo: we need to add new variable for lambda here

    public Abstraction(Var[] lambdas, Command body) {
        this.lambdas = lambdas;
        this.body = body;
    }

    public <R> R accept(CPSVisitor.CommandVisitor<R> abstraction){
        return abstraction.visit(this);
    }

    @Override
    public String toString(){
        StringBuilder returnString = new StringBuilder();
        returnString.append("(lambda ");
        if(lambdas == null || lambdas.length == 0){ }
        else {
            for(Var lambda: lambdas){
                returnString.append(lambda + ", ");
            }
            //remove the last,
            returnString.deleteCharAt(returnString.lastIndexOf(", "));
        }
        returnString.append(". " + body + ")");
        return returnString.toString();
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Abstraction that = (Abstraction) o;

        if(!lambdas.equals(that.lambdas)) return false;
        else return body.equals(that.body);
    }

    @Override
    public Command substitute(Var originalVar, Expression replacer){
        Var[] resultVar = new Var[lambdas.length];
        for(int i = 0; i < lambdas.length; i++){
            resultVar[i] = (Var)lambdas[i].substitute(originalVar, replacer);
        }
        return new Abstraction(resultVar, body.substitute(originalVar, replacer));
    }
}
