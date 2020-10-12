package xold.intcodegen.codegenerator;

import parsing.ast.visitor.GSVisitor;


import lesani.collection.option.Option;
import lesani.collection.option.Some;
import parsing.ast.tree.*;

import java.util.Iterator;



public class CodeGenerator implements GSVisitor<Object> {

	public Node visit(CompilationUnit compilationUnit) {
		compilationUnit.mainClass.accept(this);
		compilationUnit.classes.accept(this);
		return null;
	}

	public Node visit(MainClass mainClass) {
		mainClass.name.accept(this);
		mainClass.parameterId.accept(this);
		mainClass.varDecls.accept(this);
		mainClass.statements.accept(this);
		return null;
	}

	public Node visit(parsing.ast.tree.Class theClass) {
		theClass.name.accept(this);

		Option<Id> o = theClass.superClassId;
		if (o instanceof Some) {
			Id superClassName = ((Some<Id>)o).get();
			superClassName.accept(this);
		}
		theClass.fieldDecls.accept(this);
		theClass.methodDecls.accept(this);
		return null;
	}

	public Node visit(VarDecl varDecl) {
		varDecl.type.accept(this);
		varDecl.id.accept(this);
		return null;
	}

	public Node visit(Method method) {
		method.returnType.accept(this);
		method.name.accept(this);
		method.parameters.accept(this);
		method.varDecls.accept(this);
		method.statements.accept(this);
		//method.returnExp.accept(this);
		return null;
	}

	public Node visit(Parameter parameter) {
		parameter.type.accept(this);
		parameter.name.accept(this);
		return null;
	}

	public Node visit(Block block) {
		block.statements.accept(this);
		return null;
	}

	public Node visit(Assign assign) {
		assign.id.accept(this);
		assign.right.accept(this);
		return null;
	}

	public Node visit(ArrayAssign arrayAssign) {
		arrayAssign.array.accept(this);
		arrayAssign.index.accept(this);
		arrayAssign.right.accept(this);
		return null;
	}

	public Node visit(If theIf) {
		theIf.condition.accept(this);
		theIf.ifStatement.accept(this);
		theIf.elseStatement.accept(this);
		return null;
	}

	public Node visit(While theWhile) {
		theWhile.condition.accept(this);
		theWhile.body.accept(this);
		return null;
	}

    public Object visit(Return aReturn) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Node visit(Print print) {
		print.argument.accept(this);
		return null;
	}

	public Node visit(And and) {
		and.operand1.accept(this);
		and.operand2.accept(this);
		return null;
	}

	public Node visit(LessThan lessThan) {
		lessThan.operand1.accept(this);
		lessThan.operand2.accept(this);
		return null;
	}

	public Node visit(Plus plus) {
		plus.operand1.accept(this);
		plus.operand2.accept(this);
		return null;
	}

	public Node visit(Minus minus) {
		minus.operand1.accept(this);
		minus.operand2.accept(this);
		return null;
	}

	public Node visit(Mult mult) {
		mult.operand1.accept(this);
		mult.operand2.accept(this);
		return null;
	}

	public Node visit(ArrayLookup arrayLookup) {
		arrayLookup.array.accept(this);
		arrayLookup.index.accept(this);
		return null;
	}

	public Node visit(ArrayLength arrayLength) {
		arrayLength.array.accept(this);
		return null;
	}

	public Node visit(MethodCall methodCall) {
		methodCall.receiver.accept(this);
		methodCall.methodName.accept(this);
		methodCall.arguments.accept(this);
		return null;
	}

	public Node visit(IntLiteral intLiteral) {
		intLiteral.token.accept(this);
		return null;
	}

	public Node visit(TrueLiteral trueLiteral) {
		return null;
	}

	public Node visit(FalseLiteral falseLiteral) {
		return null;
	}

	public Node visit(Id id) {
		id.token.accept(this);
		return null;
	}

	public Node visit(This theThis) {
		return null;
	}

	public Node visit(ArrayAllocation arrayAllocation) {
		arrayAllocation.expression.accept(this);
		return null;
	}

	public Node visit(Allocation allocation) {
		allocation.className.accept(this);
		return null;
	}

	public Node visit(Not not) {
		not.operand.accept(this);
		return null;
	}

	public Node visit(IntType intType) {
		return null;
	}

	public Node visit(BooleanType booleanType) {
		return null;
	}

	public Node visit(ArrayType arrayType) {
		return null;
	}

	public Node visit(Token token) {
		return null;
	}

	public Node visit(List list) {
		Iterator iterator = list.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			Node node = (Node)iterator.next();
			node.accept(this);
			i++;
		}
		return null;
	}
}

