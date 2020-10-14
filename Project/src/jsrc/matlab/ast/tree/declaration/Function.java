package jsrc.matlab.ast.tree.declaration;

import jsrc.matlab.ast.tree.expression.Id;
import jsrc.matlab.ast.tree.statement.Statement;
import jsrc.matlab.ast.visitor.SVisitor;
import lesani.collection.option.Option;
import lesani.collection.option.Some;
import lesani.compiler.ast.LocInfo;

public class Function extends LocInfo implements CompilationUnit {
    public Option<Id[]> outputParams;
    public Id name;
    public Option<Id[]> inputParams;
    public Statement[] statements;


    public Function(Option<Id[]> outputParams, Id name, Option<Id[]> inputParams, Statement[] statements) {
        this.outputParams = outputParams;
        this.name = name;
        this.inputParams = inputParams;
        this.statements = statements;
    }

    public Function(Id[] outputParams, Id name, Id[] inputParams, Statement[] statements) {
        this.outputParams = new Some<Id[]>(outputParams);
        this.name = name;
        this.inputParams = new Some<Id[]>(inputParams);
        this.statements = statements;
    }


    public <R> R accept(SVisitor<R> v) {
        return v.visit(this);
    }
}
