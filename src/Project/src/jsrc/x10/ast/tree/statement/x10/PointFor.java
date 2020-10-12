package jsrc.x10.ast.tree.statement.x10;

import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.tree.expression.id.Id;
import jsrc.x10.ast.tree.statement.Statement;
import jsrc.x10.ast.tree.statement.x10.parts.PointFormalVar;
import jsrc.x10.ast.visitor.DFSVisitor;
import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;

/**
 * User: lesani, Date: Nov 3, 2009, Time: 11:09:25 AM
 */
public class PointFor extends X10Statement {
    public static enum Type {
        FOR, FOREACH, ATEACH;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
    public Type type;
    public Option<jsrc.x10.ast.tree.type.Type> elementType = None.instance();
    public PointFormalVar pointFormalVar;
    public Expression aggregate;
    public Statement statement;

    public PointFor(Type type, jsrc.x10.ast.tree.type.Type elementType, PointFormalVar pointFormalVar, Expression aggregate, Statement statement) {
        this.type = type;
        this.elementType = new Some<jsrc.x10.ast.tree.type.Type>(elementType);
        this.pointFormalVar = pointFormalVar;
        this.aggregate = aggregate;
        this.statement = statement;
    }

    public PointFor(Type type, PointFormalVar pointFormalVar, Expression aggregate, Statement statement) {
        this.type = type;
        this.pointFormalVar = pointFormalVar;
        this.aggregate = aggregate;
        this.statement = statement;
    }

    public PointFor(Type type, String id, Expression aggregate, Statement statement) {
        this.type = type;
        this.pointFormalVar = new jsrc.x10.ast.tree.statement.x10.parts.Id(new Id(id));
        this.aggregate = aggregate;
        this.statement = statement;
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.StatementVisitor.X10StatementVisitor x10StatementVisitor) {
        return x10StatementVisitor.visit(this);
    }
}

