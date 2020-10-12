package parsing.ast.visitor;

import lesani.collection.option.Option;
import lesani.collection.option.Some;
import parsing.ast.tree.*;
import parsing.ast.tree.Class;

import java.util.Iterator;

/**
 * User: Mohsen's Desktop
 * Date: Aug 28, 2009
 */

public class GSDepthFirstSearchVisitor<R> implements GSVisitor<R> {

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

	public GSDepthFirstSearchVisitor() {
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

	public R visit(CompilationUnit compilationUnit) {
		pre.run(compilationUnit);
		in.run(compilationUnit, 0);
		compilationUnit.mainClass.accept(this);
		in.run(compilationUnit, 1);
		compilationUnit.classes.accept(this);
		post.run(compilationUnit);
		//visitList((List<Node>)compilationUnit.classes);
		/*
		List<Class> classes = compilationUnit.classes;
		Iterator<Class> iterator = classes.iterator();
		while (iterator.hasNext()) {
			Class theClass = iterator.next();
			theClass.accept(this);
		}
		*/
		return null;
	}

	public R visit(MainClass mainClass) {
		pre.run(mainClass);
		in.run(mainClass, 0);
		mainClass.name.accept(this);
		in.run(mainClass, 1);
		mainClass.parameterId.accept(this);
		in.run(mainClass, 2);
		mainClass.varDecls.accept(this);
		in.run(mainClass, 3);
		mainClass.statements.accept(this);
		post.run(mainClass);
		return null;
	}

	public R visit(Class theClass) {
		pre.run(theClass);
		in.run(theClass, 0);
		theClass.name.accept(this);

		Option<Id> o = theClass.superClassId;
		if (o instanceof Some) {
			in.run(theClass, 1);
			Id superClassName = ((Some<Id>)o).get();
			superClassName.accept(this);
		}
		in.run(theClass, 2);
		theClass.fieldDecls.accept(this);
		in.run(theClass, 3);
		theClass.methodDecls.accept(this);
		post.run(theClass);
		return null;
	}

	public R visit(VarDecl varDecl) {
		pre.run(varDecl);
		in.run(varDecl, 0);
		varDecl.type.accept(this);
		in.run(varDecl, 1);
		varDecl.id.accept(this);
		post.run(varDecl);
		return null;
	}

	public R visit(Method method) {
		pre.run(method);
		in.run(method, 0);
		method.returnType.accept(this);
		in.run(method, 1);
		method.name.accept(this);
		in.run(method, 2);
		method.parameters.accept(this);
		in.run(method, 3);
		method.varDecls.accept(this);
		in.run(method, 4);
		method.statements.accept(this);
//		in.run(method, 5);
//		method.returnExp.accept(this);
		post.run(method);
		return null;
	}

	public R visit(Parameter parameter) {
		pre.run(parameter);
		in.run(parameter, 0);
		parameter.type.accept(this);
		in.run(parameter, 1);
		parameter.name.accept(this);
		post.run(parameter);
		return null;
	}

	public R visit(Block block) {
		pre.run(block);
		in.run(block, 0);
		block.statements.accept(this);
		post.run(block);
		return null;
	}

	public R visit(Assign assign) {
		pre.run(assign);
		in.run(assign, 0);
		assign.id.accept(this);
		in.run(assign, 1);
		assign.right.accept(this);
		post.run(assign);
		return null;
	}

	public R visit(ArrayAssign arrayAssign) {
		pre.run(arrayAssign);
		in.run(arrayAssign, 0);
		arrayAssign.array.accept(this);
		in.run(arrayAssign, 1);
		arrayAssign.index.accept(this);
		in.run(arrayAssign, 2);
		arrayAssign.right.accept(this);
		post.run(arrayAssign);
		return null;
	}

	public R visit(If theIf) {
		pre.run(theIf);
		in.run(theIf, 0);
		theIf.condition.accept(this);
		in.run(theIf, 1);
		theIf.ifStatement.accept(this);
		in.run(theIf, 2);
		theIf.elseStatement.accept(this);
		post.run(theIf);
		return null;
	}

	public R visit(While theWhile) {
		pre.run(theWhile);
		in.run(theWhile, 0);
		theWhile.condition.accept(this);
		in.run(theWhile, 1);
		theWhile.body.accept(this);
		post.run(theWhile);
		return null;
	}

    public R visit(Return aReturn) {
        pre.run(aReturn);
        aReturn.expression.accept(this);
        post.run(aReturn);
        return null;
    }

    public R visit(Print print) {
		pre.run(print);
		in.run(print, 0);
		print.argument.accept(this);
		post.run(print);
		return null;
	}

	public R visit(And and) {
		pre.run(and);
		in.run(and, 0);
		and.operand1.accept(this);
		in.run(and, 1);
		and.operand2.accept(this);
		post.run(and);
		return null;
	}

	public R visit(LessThan lessThan) {
		pre.run(lessThan);
		in.run(lessThan, 0);
		lessThan.operand1.accept(this);
		in.run(lessThan, 1);
		lessThan.operand2.accept(this);
		post.run(lessThan);
		return null;
	}

	public R visit(Plus plus) {
		pre.run(plus);
		in.run(plus, 0);
		plus.operand1.accept(this);
		in.run(plus, 1);
		plus.operand2.accept(this);
		post.run(plus);
		return null;
	}

	public R visit(Minus minus) {
		pre.run(minus);
		in.run(minus, 0);
		minus.operand1.accept(this);
		in.run(minus, 1);
		minus.operand2.accept(this);
		post.run(minus);
		return null;
	}

	public R visit(Mult mult) {
		pre.run(mult);
		in.run(mult, 0);
		mult.operand1.accept(this);
		in.run(mult, 1);
		mult.operand2.accept(this);
		post.run(mult);
		return null;
	}

	public R visit(ArrayLookup arrayLookup) {
		pre.run(arrayLookup);
		in.run(arrayLookup, 0);
		arrayLookup.array.accept(this);
		in.run(arrayLookup, 1);
		arrayLookup.index.accept(this);
		post.run(arrayLookup);
		return null;
	}

	public R visit(ArrayLength arrayLength) {
		pre.run(arrayLength);
		in.run(arrayLength, 0);
		arrayLength.array.accept(this);
		post.run(arrayLength);
		return null;
	}

	public R visit(MethodCall methodCall) {
		pre.run(methodCall);
		in.run(methodCall, 0);
		methodCall.receiver.accept(this);
		in.run(methodCall, 1);
		methodCall.methodName.accept(this);
//		System.out.println(methodCall.arguments);
//		Iterator<Expression> iterator = methodCall.arguments.iterator();
//		while (iterator.hasNext()) {
//			Expression expression = iterator.next();
//			System.out.println(expression);
//		}
		in.run(methodCall, 2);
		methodCall.arguments.accept(this);
		post.run(methodCall);
		return null;
	}

	public R visit(IntLiteral intLiteral) {
		pre.run(intLiteral);
		in.run(intLiteral, 0);
		intLiteral.token.accept(this);
		post.run(intLiteral);
		return null;
	}

	public R visit(TrueLiteral trueLiteral) {
		pre.run(trueLiteral);
		post.run(trueLiteral);
		return null;
	}

	public R visit(FalseLiteral falseLiteral) {
		pre.run(falseLiteral);
		post.run(falseLiteral);
		return null;
	}

	public R visit(Id id) {
		pre.run(id);
		in.run(id, 0);
		id.token.accept(this);
		post.run(id);
		return null;
	}

	public R visit(This theThis) {
		pre.run(theThis);
		post.run(theThis);
		return null;
	}

	public R visit(ArrayAllocation arrayAllocation) {
		pre.run(arrayAllocation);
		in.run(arrayAllocation, 0);
		arrayAllocation.expression.accept(this);
		post.run(arrayAllocation);
		return null;
	}

	public R visit(Allocation allocation) {
		pre.run(allocation);
		in.run(allocation, 0);
		allocation.className.accept(this);
		post.run(allocation);
		return null;
	}

	public R visit(Not not) {
		pre.run(not);
		in.run(not, 0);
		not.operand.accept(this);
		post.run(not);
		return null;
	}

	public R visit(IntType intType) {
		pre.run(intType);
		post.run(intType);
		return null;
	}

	public R visit(BooleanType booleanType) {
		pre.run(booleanType);
		post.run(booleanType);
		return null;
	}

	public R visit(ArrayType arrayType) {
		pre.run(arrayType);
		post.run(arrayType);
		return null;
	}

	public R visit(Token token) {
		pre.run(token);
		post.run(token);
		return null;
	}

	public R visit(List list) {
		pre.run(list);
		Iterator iterator = list.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			Node node = (Node)iterator.next();
			in.run(node, i);
			node.accept(this);
			i++;
		}
		post.run(list);
		return null;
	}

}