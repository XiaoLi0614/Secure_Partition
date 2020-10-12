package jsrc.x10.ast.tree.statement;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.tree.expression.id.Id;
import jsrc.x10.ast.tree.type.NonVoidType;
import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 1:01:30 PM
 */

public class VarDecl extends /*Statement {*/ VarDeclOrSt {

    public boolean isConstant = false;
    public NonVoidType type;
    public Id name;
    public Option<Expression> expression = None.instance();


    public VarDecl(NonVoidType type, Id name, Expression expression) {
        this.type = type;
        this.name = name;
        this.expression = new Some<Expression>(expression);
    }

    public VarDecl(NonVoidType type, String name, Expression expression) {
        this.type = type;
        this.name = new Id(name);
        this.expression = new Some<Expression>(expression);
    }

    public VarDecl(NonVoidType type, String name) {
        this.type = type;
        this.name = new Id(name);
    }

    public VarDecl(boolean isConstant, NonVoidType type, Id name, Expression expression) {
        this.isConstant = isConstant;
        this.type = type;
        this.name = name;
        this.expression = new Some<Expression>(expression);
    }

}


