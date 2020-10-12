package parsing.ast.visitor;

import parsing.ast.tree.*;

/**
 * User: Mohsen's Desktop
 * Date: Aug 28, 2009
 */

public interface GSVisitor<R> {

	public R visit(CompilationUnit compilationUnit);

	public R visit(MainClass mainClass);
	public R visit(parsing.ast.tree.Class theClass);

	public R visit(VarDecl varDecl);
	public R visit(Method method);
	public R visit(Parameter parameter);

	public R visit(Block block);
	public R visit(Assign assign);
	public R visit(ArrayAssign arrayAssign);
	public R visit(If theIf);
	public R visit(While theWhile);
	public R visit(Return aReturn);
	public R visit(Print print);

	public R visit(And and);
	public R visit(LessThan lessThan);
	public R visit(Plus plus);
	public R visit(Minus minus);
	public R visit(Mult mult);
	public R visit(ArrayLookup arrayLookup);
	public R visit(ArrayLength arrayLength);
	public R visit(MethodCall methodCall);
	public R visit(IntLiteral intLiteral);
	public R visit(TrueLiteral trueLiteral);
	public R visit(FalseLiteral falseLiteral);
	public R visit(Id id);
	public R visit(This theThis);
	public R visit(ArrayAllocation arrayAllocation);
	public R visit(Allocation allocation);
	public R visit(Not not);

	public R visit(IntType intType);
	public R visit(BooleanType booleanType);
	public R visit(ArrayType arrayType);

	public R visit(Token token);

	public R visit(List list);

}
