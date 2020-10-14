package parsing.ast.visitor;

import lesani.collection.option.Option;
import lesani.collection.option.Some;
import parsing.ast.tree.*;

import java.util.Iterator;

/**
 * User: Mohsen's Desktop
 * Date: Aug 28, 2009
 */

public class GIDepthFirstSearchVisitor<A> implements GIVisitor<A> {

	protected Callable pre = new Callable() {
		public void run(Node node) {
		}
	};
	protected Callable post = new Callable() {
		public void run(Node node) {
		}
	};
	protected InCallable in = new InCallable() {
		public void run(Node node, int no) {
		}
	};

	public GIDepthFirstSearchVisitor() {
	}

	public Callable getPre() {
		return pre;
	}

	public void setPre(Callable pre) {
		this.pre = pre;
	}

	public Callable getPost() {
		return post;
	}

	public void setPost(Callable post) {
		this.post = post;
	}

	public InCallable getIn() {
		return in;
	}

	public void setIn(InCallable in) {
		this.in = in;
	}


	//------------------------------------------------------------
	// Visits

	public void visit(CompilationUnit compilationUnit, A inheritedVar) {
		pre.run(compilationUnit);
		in.run(compilationUnit, 0);
		compilationUnit.mainClass.accept(this, inheritedVar);
		in.run(compilationUnit, 1);
		compilationUnit.classes.accept(this, inheritedVar);
		post.run(compilationUnit);
		//visitList((List<Node>)compilationUnit.classes);
		/*
		List<Class> classes = compilationUnit.classes;
		Iterator<Class> iterator = classes.iterator();
		while (iterator.hasNext()) {
			Class theClass = iterator.next();
			theClass.accept(this, inheritedVar);
		}
		*/

	}

	public void visit(MainClass mainClass, A inheritedVar) {
		pre.run(mainClass);
		in.run(mainClass, 0);
		mainClass.name.accept(this, inheritedVar);
		in.run(mainClass, 1);
		mainClass.parameterId.accept(this, inheritedVar);
		in.run(mainClass, 2);
		mainClass.varDecls.accept(this, inheritedVar);
		in.run(mainClass, 3);
		mainClass.statements.accept(this, inheritedVar);
		post.run(mainClass);

	}

	public void visit(parsing.ast.tree.Class theClass, A inheritedVar) {
		pre.run(theClass);
		in.run(theClass, 0);
		theClass.name.accept(this, inheritedVar);

		Option<Id> o = theClass.superClassId;
		if (o instanceof Some) {
			in.run(theClass, 1);
			Id superClassName = ((Some<Id>)o).get();
			superClassName.accept(this, inheritedVar);
		}
		in.run(theClass, 2);
		theClass.fieldDecls.accept(this, inheritedVar);
		in.run(theClass, 3);
		theClass.methodDecls.accept(this, inheritedVar);
		post.run(theClass);

	}

	public void visit(VarDecl varDecl, A inheritedVar) {
		pre.run(varDecl);
		in.run(varDecl, 0);
		varDecl.type.accept(this, inheritedVar);
		in.run(varDecl, 1);
		varDecl.id.accept(this, inheritedVar);
		post.run(varDecl);

	}

	public void visit(Method method, A inheritedVar) {
		pre.run(method);
		in.run(method, 0);
		method.returnType.accept(this, inheritedVar);
		in.run(method, 1);
		method.name.accept(this, inheritedVar);
		in.run(method, 2);
		method.parameters.accept(this, inheritedVar);
		in.run(method, 3);
		method.varDecls.accept(this, inheritedVar);
		in.run(method, 4);
		method.statements.accept(this, inheritedVar);
//		in.run(method, 5);
//		method.returnExp.accept(this, inheritedVar);
		post.run(method);

	}

	public void visit(Parameter parameter, A inheritedVar) {
		pre.run(parameter);
		in.run(parameter, 0);
		parameter.type.accept(this, inheritedVar);
		in.run(parameter, 1);
		parameter.name.accept(this, inheritedVar);
		post.run(parameter);

	}

	public void visit(Block block, A inheritedVar) {
		pre.run(block);
		in.run(block, 0);
		block.statements.accept(this, inheritedVar);
		post.run(block);

	}

	public void visit(Assign assign, A inheritedVar) {
		pre.run(assign);
		in.run(assign, 0);
		assign.id.accept(this, inheritedVar);
		in.run(assign, 1);
		assign.right.accept(this, inheritedVar);
		post.run(assign);

	}

	public void visit(ArrayAssign arrayAssign, A inheritedVar) {
		pre.run(arrayAssign);
		in.run(arrayAssign, 0);
		arrayAssign.array.accept(this, inheritedVar);
		in.run(arrayAssign, 1);
		arrayAssign.index.accept(this, inheritedVar);
		in.run(arrayAssign, 2);
		arrayAssign.right.accept(this, inheritedVar);
		post.run(arrayAssign);

	}

	public void visit(If theIf, A inheritedVar) {
		pre.run(theIf);
		in.run(theIf, 0);
		theIf.condition.accept(this, inheritedVar);
		in.run(theIf, 1);
		theIf.ifStatement.accept(this, inheritedVar);
		in.run(theIf, 2);
		theIf.elseStatement.accept(this, inheritedVar);
		post.run(theIf);

	}

	public void visit(While theWhile, A inheritedVar) {
		pre.run(theWhile);
		in.run(theWhile, 0);
		theWhile.condition.accept(this, inheritedVar);
		in.run(theWhile, 1);
		theWhile.body.accept(this, inheritedVar);
		post.run(theWhile);

	}

    public void visit(Return aReturn, A inheritedVar) {
        pre.run(aReturn);
        aReturn.expression.accept(this, inheritedVar);
        post.run(aReturn);
    }

    public void visit(Print print, A inheritedVar) {
		pre.run(print);
		in.run(print, 0);
		print.argument.accept(this, inheritedVar);
		post.run(print);

	}

	public void visit(And and, A inheritedVar) {
		pre.run(and);
		in.run(and, 0);
		and.operand1.accept(this, inheritedVar);
		in.run(and, 1);
		and.operand2.accept(this, inheritedVar);
		post.run(and);

	}

	public void visit(LessThan lessThan, A inheritedVar) {
		pre.run(lessThan);
		in.run(lessThan, 0);
		lessThan.operand1.accept(this, inheritedVar);
		in.run(lessThan, 1);
		lessThan.operand2.accept(this, inheritedVar);
		post.run(lessThan);

	}

	public void visit(Plus plus, A inheritedVar) {
		pre.run(plus);
		in.run(plus, 0);
		plus.operand1.accept(this, inheritedVar);
		in.run(plus, 1);
		plus.operand2.accept(this, inheritedVar);
		post.run(plus);

	}

	public void visit(Minus minus, A inheritedVar) {
		pre.run(minus);
		in.run(minus, 0);
		minus.operand1.accept(this, inheritedVar);
		in.run(minus, 1);
		minus.operand2.accept(this, inheritedVar);
		post.run(minus);

	}

	public void visit(Mult mult, A inheritedVar) {
		pre.run(mult);
		in.run(mult, 0);
		mult.operand1.accept(this, inheritedVar);
		in.run(mult, 1);
		mult.operand2.accept(this, inheritedVar);
		post.run(mult);

	}

	public void visit(ArrayLookup arrayLookup, A inheritedVar) {
		pre.run(arrayLookup);
		in.run(arrayLookup, 0);
		arrayLookup.array.accept(this, inheritedVar);
		in.run(arrayLookup, 1);
		arrayLookup.index.accept(this, inheritedVar);
		post.run(arrayLookup);

	}

	public void visit(ArrayLength arrayLength, A inheritedVar) {
		pre.run(arrayLength);
		in.run(arrayLength, 0);
		arrayLength.array.accept(this, inheritedVar);
		post.run(arrayLength);

	}

	public void visit(MethodCall methodCall, A inheritedVar) {
		pre.run(methodCall);
		in.run(methodCall, 0);
		methodCall.receiver.accept(this, inheritedVar);
		in.run(methodCall, 1);
		methodCall.methodName.accept(this, inheritedVar);
//		System.out.println(methodCall.arguments);
//		Iterator<Expression> iterator = methodCall.arguments.iterator();
//		while (iterator.hasNext()) {
//			Expression expression = iterator.next();
//			System.out.println(expression);
//		}
		in.run(methodCall, 2);
		methodCall.arguments.accept(this, inheritedVar);
		post.run(methodCall);

	}

	public void visit(IntLiteral intLiteral, A inheritedVar) {
		pre.run(intLiteral);
		in.run(intLiteral, 0);
		intLiteral.token.accept(this, inheritedVar);
		post.run(intLiteral);

	}

	public void visit(TrueLiteral trueLiteral, A inheritedVar) {
		pre.run(trueLiteral);
		post.run(trueLiteral);

	}

	public void visit(FalseLiteral falseLiteral, A inheritedVar) {
		pre.run(falseLiteral);
		post.run(falseLiteral);

	}

	public void visit(Id id, A inheritedVar) {
		pre.run(id);
		in.run(id, 0);
		id.token.accept(this, inheritedVar);
		post.run(id);

	}

	public void visit(This theThis, A inheritedVar) {
		pre.run(theThis);
		post.run(theThis);

	}

	public void visit(ArrayAllocation arrayAllocation, A inheritedVar) {
		pre.run(arrayAllocation);
		in.run(arrayAllocation, 0);
		arrayAllocation.expression.accept(this, inheritedVar);
		post.run(arrayAllocation);

	}

	public void visit(Allocation allocation, A inheritedVar) {
		pre.run(allocation);
		in.run(allocation, 0);
		allocation.className.accept(this, inheritedVar);
		post.run(allocation);

	}

	public void visit(Not not, A inheritedVar) {
		pre.run(not);
		in.run(not, 0);
		not.operand.accept(this, inheritedVar);
		post.run(not);

	}

	public void visit(IntType intType, A inheritedVar) {
		pre.run(intType);
		post.run(intType);

	}

	public void visit(BooleanType booleanType, A inheritedVar) {
		pre.run(booleanType);
		post.run(booleanType);

	}

	public void visit(ArrayType arrayType, A inheritedVar) {
		pre.run(arrayType);
		post.run(arrayType);

	}

	public void visit(Token token, A inheritedVar) {
		pre.run(token);
		post.run(token);

	}

	public void visit(List list, A inheritedVar) {
		pre.run(list);
		Iterator iterator = list.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			Node node = (Node)iterator.next();
			in.run(node, i);
			node.accept(this, inheritedVar);
			i++;
		}
		post.run(list);

	}
}