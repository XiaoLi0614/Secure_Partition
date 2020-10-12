package jsrc.matlab.astbuild.intastnodes.declarations;

import lesani.collection.option.Option;
import lesani.collection.option.Some;
import lesani.compiler.ast.Node;
import jsrc.matlab.ast.tree.declaration.InputParam;
import jsrc.matlab.ast.tree.declaration.OutputParam;
import jsrc.matlab.ast.tree.expression.Id;

public class FunHeader implements Node {
    public Option<Id[]> outputParams;
    public Id name;
    public Option<Id[]> inputParams;

    public FunHeader(Id[] outputParams, Id name, Id[] inputParams) {
        this.outputParams = new Some<Id[]>(outputParams);
        this.name = name;
        this.inputParams = new Some<Id[]>(inputParams);
    }

    public FunHeader(Option<Id[]> outputParams, Id name, Option<Id[]> inputParams) {
        this.outputParams = outputParams;
        this.name = name;
        this.inputParams = inputParams;
    }
}
