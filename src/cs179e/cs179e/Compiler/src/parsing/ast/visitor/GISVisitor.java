package parsing.ast.visitor;

import parsing.ast.tree.*;

/**
 * User: Mohsen's Desktop
 * Date: Aug 28, 2009
 */

public interface GISVisitor<R, A> {

	public R visit(CompilationUnit compilationUnit, A inheritedVar);

	public R visit(MainClass mainClass, A inheritedVar);
	public R visit(parsing.ast.tree.Class theClass, A inheritedVar);

	public R visit(VarDecl varDecl, A inheritedVar);
	public R visit(Method method, A inheritedVar);
	public R visit(Parameter parameter, A inheritedVar);

	public R visit(Block block, A inheritedVar);
	public R visit(Assign assign, A inheritedVar);
	public R visit(ArrayAssign arrayAssign, A inheritedVar);
	public R visit(If theIf, A inheritedVar);
	public R visit(While theWhile, A inheritedVar);
	public R visit(Return aReturn, A inheritedVar);
	public R visit(Print print, A inheritedVar);

	public R visit(And and, A inheritedVar);
	public R visit(LessThan lessThan, A inheritedVar);
	public R visit(Plus plus, A inheritedVar);
	public R visit(Minus minus, A inheritedVar);
	public R visit(Mult mult, A inheritedVar);
	public R visit(ArrayLookup arrayLookup, A inheritedVar);
	public R visit(ArrayLength arrayLength, A inheritedVar);
	public R visit(MethodCall methodCall, A inheritedVar);
	public R visit(IntLiteral intLiteral, A inheritedVar);
	public R visit(TrueLiteral trueLiteral, A inheritedVar);
	public R visit(FalseLiteral falseLiteral, A inheritedVar);
	public R visit(Id id, A inheritedVar);
	public R visit(This theThis, A inheritedVar);
	public R visit(ArrayAllocation arrayAllocation, A inheritedVar);
	public R visit(Allocation allocation, A inheritedVar);
	public R visit(Not not, A inheritedVar);

	public R visit(IntType intType, A inheritedVar);
	public R visit(BooleanType booleanType, A inheritedVar);
	public R visit(ArrayType arrayType, A inheritedVar);

	public R visit(Token token, A inheritedVar);

	public R visit(List list, A inheritedVar);

}
