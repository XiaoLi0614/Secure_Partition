package jsrc.x10.ast.tree.declaration;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.tree.expression.id.Id;
import jsrc.x10.ast.tree.type.NonVoidType;
import lesani.collection.option.*;


/*
 User: lesani, Date: Nov 2, 2009, Time: 10:54:52 AM
*/

public class Field implements ClassMember {
    public Visibility visibility = Visibility.PUBLIC;
    public boolean isStatic = false;
    public boolean isFinal = false;
    public NonVoidType type;
    public Id name;
    public Option<Expression> initExpression = None.instance();


    public Field(NonVoidType type, Id name) {
        this.type = type;
        this.name = name;
    }

    public Field(Visibility visibility, NonVoidType type, Id name) {
        this.visibility = visibility;
        this.type = type;
        this.name = name;
    }

    public Field(boolean isConstant, NonVoidType type, Id name) {
        this.type = type;
        this.name = name;
        this.isFinal = isConstant;
    }

    public Field(Visibility visibility, boolean isConstant, NonVoidType type, Id name) {
        this.visibility = visibility;
        this.type = type;
        this.name = name;
        this.isFinal = isConstant;
    }

    public Field(boolean isStatic, boolean isConstant, NonVoidType type, Id name, Expression initExpression) {
        this.isStatic = isStatic;
        this.isFinal = isConstant;
        this.type = type;
        this.name = name;
        this.initExpression = new Some<Expression>(initExpression);
    }

    public Field(Visibility visibility, boolean isStatic, boolean isConstant, NonVoidType type, Id name, Expression initExpression) {
        this.visibility = visibility;
        this.isStatic = isStatic;
        this.isFinal = isConstant;
        this.type = type;
        this.name = name;
        this.initExpression = new Some<Expression>(initExpression);
    }
}
