package lambda_calculus.cps_ast.tree.command;

import lambda_calculus.cps_ast.tree.expression.Expression;
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

    @Override
    public String toString(){
        if(function.toString() == "null"){
            return "";
        }
        else {
            StringBuilder returnString = new StringBuilder();
            returnString.append("(" + function + " ");
            if(values == null || values.length == 0 ){}
            else {
                for(Command v: values){
                    returnString.append(v + " ");
                }
                returnString.deleteCharAt(returnString.lastIndexOf(" "));
            }
            returnString.append(")");
            return returnString.toString();
        }
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Application that = (Application) o;

        if(!function.equals(that.function)) return false;
        else return values.equals(that.values);
    }

    @Override
    public Command substitute(Var originalVar, Expression replacer){
        Command[] resultValues = new Command[values.length];
        for(int i = 0; i < values.length; i++){
            resultValues[i] = values[i].substitute(originalVar, replacer);
        }
        return new Application(function.substitute(originalVar, replacer), resultValues);
    }
}
