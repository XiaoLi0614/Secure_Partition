package parsing.ast.visitor;

import parsing.ast.tree.*;

/**
 * User: Mohsen's Desktop
 * Date: Aug 28, 2009
 */

public interface GIVisitor<A> {
	public void visit(CompilationUnit compilationUnit, A inheritedVar);

	public void visit(MainClass mainClass, A inheritedVar);
	public void visit(parsing.ast.tree.Class theClass, A inheritedVar);

	public void visit(VarDecl varDecl, A inheritedVar);
	public void visit(Method method, A inheritedVar);
	public void visit(Parameter parameter, A inheritedVar);

	public void visit(Block block, A inheritedVar);
	public void visit(Assign assign, A inheritedVar);
	public void visit(ArrayAssign arrayAssign, A inheritedVar);
	public void visit(If theIf, A inheritedVar);
	public void visit(While theWhile, A inheritedVar);
	public void visit(Return aReturn, A inheritedVar);
    public void visit(Print print, A inheritedVar);

	public void visit(And and, A inheritedVar);
	public void visit(LessThan lessThan, A inheritedVar);
	public void visit(Plus plus, A inheritedVar);
	public void visit(Minus minus, A inheritedVar);
	public void visit(Mult mult, A inheritedVar);
	public void visit(ArrayLookup arrayLookup, A inheritedVar);
	public void visit(ArrayLength arrayLength, A inheritedVar);
	public void visit(MethodCall methodCall, A inheritedVar);
	public void visit(IntLiteral intLiteral, A inheritedVar);
	public void visit(TrueLiteral trueLiteral, A inheritedVar);
	public void visit(FalseLiteral falseLiteral, A inheritedVar);
	public void visit(Id id, A inheritedVar);
	public void visit(This theThis, A inheritedVar);
	public void visit(ArrayAllocation arrayAllocation, A inheritedVar);
	public void visit(Allocation allocation, A inheritedVar);
	public void visit(Not not, A inheritedVar);

	public void visit(IntType intType, A inheritedVar);
	public void visit(BooleanType booleanType, A inheritedVar);
	public void visit(ArrayType arrayType, A inheritedVar);

	public void visit(Token token, A inheritedVar);

	public void visit(List list, A inheritedVar);

}