package jsrc.x10.ast.tree.expression;

import jsrc.x10.ast.tree.expression.id.Id;
import jsrc.x10.ast.tree.type.NonVoidType;
import jsrc.x10.ast.visitor.CPSPrinter;
import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;

public class Var implements Expression{
    //public NonVoidType type;
    public Id name;

    public Var(String name) {
        //this.type = type;
        this.name = new Id(name);
    }
    public Object accept(CPSPrinter.FileVisitor.ClassVisitor.ExpressionVisitor varVisitor) {
        return varVisitor.visit(this);
    }
}
}
