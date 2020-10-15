package lambda_calculus.cps_ast.tree;

import lambda_calculus.cps_ast.tree.command.Command;
import lambda_calculus.cps_ast.tree.expression.Expression;
import lambda_calculus.cps_ast.tree.expression.Var;

import java.util.HashMap;

public class Context {
    public HashMap<Var, Expression> contextMap;
    public Command scope;
    public Context parent;

    //initialization for the top level ast
    public Context(Command s){
        this.contextMap = new HashMap<>();
        this.scope = s;
        this.parent = null;
    }

    public Context(Context p){ this.parent = p; }

    public Context(HashMap<Var, Expression> cMap, Command s, Context p){
        this.contextMap = cMap;
        this.scope = s;
        this.parent = p;
    }

    public Context extend(){
        return new Context(this);
    }

    public Context ()


}
