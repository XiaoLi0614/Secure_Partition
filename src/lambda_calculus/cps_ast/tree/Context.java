package lambda_calculus.cps_ast.tree;

import lambda_calculus.cps_ast.tree.command.Command;
import lambda_calculus.cps_ast.tree.expression.Expression;
import lambda_calculus.cps_ast.tree.expression.Var;

import java.io.Serializable;
import java.util.HashMap;

public class Context {
/*    public HashMap<Var, Expression> contextMap;
    public Command scope;
    //public Context parent;

    //initialization for the top level ast
    public Context(Command s){
        this.contextMap = new HashMap<>();
        this.scope = s;
        this.parent = null;
    }

    public Context(Context p){ this.parent = p; }

    public Context(Command s, Context p){
        //this.contextMap = cMap;
        this.contextMap = p.contextMap.clone();
        this.scope = s;
        this.parent = p;
    }

    public Context extend(){
        return new Context(this);
    }

    public Context ()

    @Override
    public Object clone() throws CloneNotSupportedException {
        Context c = (Context) super.clone();
        c.parent = (Context) parent.clone();
        c.scope = (Command) scope.clone();
        return c;
    }*/

    public HashMap<Var, Command> contextMap;
    //public Command scope;
    //public Context parent;

    //initialization for the top level ast
    public Context(){
        this.contextMap = new HashMap<>();
    }

    public Context(HashMap<Var, Command> cMap){
        this.contextMap = cMap;
    }

    public Command getValueInContext(Var v){
        if( this.contextMap.containsKey(v)){
            return this.contextMap.get(v);
        }
        else{
            throw new Error("Undefined variable " + v.name);
        }
    }

    public void bindValueToContext(Var v, Command e){
        //there is already a binding for the variable, throw an error
        if(this.contextMap.containsKey(v)){
            if(this.contextMap.get(v) != null){
                throw new Error("Can not bind to a variable already with a value");
            }
            else {
                this.contextMap.put(v, e);
            }
        }
        else {
            throw new Error("No variable in the context yet");
        }
    }

    public void addVariableToContext(Var v){
        this.contextMap.put(v, null);
    }

}
