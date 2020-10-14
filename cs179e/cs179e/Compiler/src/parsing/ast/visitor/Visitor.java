package parsing.ast.visitor;

import parsing.ast.tree.*;


/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public interface Visitor {

	public void visit(CompilationUnit compilationUnit);

	public void visit(MainClass mainClass);
	public void visit(parsing.ast.tree.Class theClass);

	public void visit(VarDecl varDecl);
	public void visit(Method method);
	public void visit(Parameter parameter);

	public void visit(Block block);
	public void visit(Assign assign);
	public void visit(ArrayAssign arrayAssign);
	public void visit(If theIf);
	public void visit(While theWhile);
    public void visit(Return aReturn);
	public void visit(Print print);

	public void visit(And and);
	public void visit(LessThan lessThan);
	public void visit(Plus plus);
	public void visit(Minus minus);
	public void visit(Mult mult);
	public void visit(ArrayLookup arrayLookup);
	public void visit(ArrayLength arrayLength);
	public void visit(MethodCall methodCall);
	public void visit(IntLiteral intLiteral);
	public void visit(TrueLiteral trueLiteral);
	public void visit(FalseLiteral falseLiteral);
	public void visit(Id id);
	public void visit(This theThis);
	public void visit(ArrayAllocation arrayAllocation);
	public void visit(Allocation allocation);
	public void visit(Not not);

	public void visit(IntType intType);
	public void visit(BooleanType booleanType);
	public void visit(ArrayType arrayType);

	public void visit(Token token);

	public void visit(List list);


}

