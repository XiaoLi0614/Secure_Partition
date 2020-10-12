package parsing.ast.visitor;

import lesani.collection.option.Option;
import lesani.collection.option.Some;
import parsing.ast.tree.*;

import java.util.Iterator;

/**
 * User: Mohsen's Desktop
 * Date: Aug 27, 2009
 */

public class DepthFirstSearchVisitor implements Visitor {

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

	public DepthFirstSearchVisitor() {
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

	public void visit(CompilationUnit compilationUnit) {
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
	}

	public void visit(MainClass mainClass) {
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
	}

	public void visit(parsing.ast.tree.Class theClass) {
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
	}

	public void visit(VarDecl varDecl) {
		pre.run(varDecl);
		in.run(varDecl, 0);
		varDecl.type.accept(this);
		in.run(varDecl, 1);
		varDecl.id.accept(this);
		post.run(varDecl);
	}

	public void visit(Method method) {
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
		//in.run(method, 5);
		//method.returnExp.accept(this);
		post.run(method);
	}

	public void visit(Parameter parameter) {
		pre.run(parameter);
		in.run(parameter, 0);
		parameter.type.accept(this);
		in.run(parameter, 1);
		parameter.name.accept(this);
		post.run(parameter);
	}

	public void visit(Block block) {
		pre.run(block);
		in.run(block, 0);
		block.statements.accept(this);
		post.run(block);
	}

	public void visit(Assign assign) {
		pre.run(assign);
		in.run(assign, 0);
		assign.id.accept(this);
		in.run(assign, 1);
		assign.right.accept(this);
		post.run(assign);
	}

	public void visit(ArrayAssign arrayAssign) {
		pre.run(arrayAssign);
		in.run(arrayAssign, 0);
		arrayAssign.array.accept(this);
		in.run(arrayAssign, 1);
		arrayAssign.index.accept(this);
		in.run(arrayAssign, 2);
		arrayAssign.right.accept(this);
		post.run(arrayAssign);
	}

	public void visit(If theIf) {
		pre.run(theIf);
		in.run(theIf, 0);
		theIf.condition.accept(this);
		in.run(theIf, 1);
		theIf.ifStatement.accept(this);
		in.run(theIf, 2);
		theIf.elseStatement.accept(this);
		post.run(theIf);
	}

	public void visit(While theWhile) {
		pre.run(theWhile);
		in.run(theWhile, 0);
		theWhile.condition.accept(this);
		in.run(theWhile, 1);
		theWhile.body.accept(this);
		post.run(theWhile);
	}

    public void visit(Return aReturn) {
        pre.run(aReturn);
        aReturn.expression.accept(this);
        post.run(aReturn);
    }

    public void visit(Print print) {
		pre.run(print);
		in.run(print, 0);
		print.argument.accept(this);
		post.run(print);
	}

	public void visit(And and) {
		pre.run(and);
		in.run(and, 0);
		and.operand1.accept(this);
		in.run(and, 1);
		and.operand2.accept(this);
		post.run(and);
	}

	public void visit(LessThan lessThan) {
		pre.run(lessThan);
		in.run(lessThan, 0);
		lessThan.operand1.accept(this);
		in.run(lessThan, 1);
		lessThan.operand2.accept(this);
		post.run(lessThan);
	}

	public void visit(Plus plus) {
		pre.run(plus);
		in.run(plus, 0);
		plus.operand1.accept(this);
		in.run(plus, 1);
		plus.operand2.accept(this);
		post.run(plus);
	}

	public void visit(Minus minus) {
		pre.run(minus);
		in.run(minus, 0);
		minus.operand1.accept(this);
		in.run(minus, 1);
		minus.operand2.accept(this);
		post.run(minus);
	}

	public void visit(Mult mult) {
		pre.run(mult);
		in.run(mult, 0);
		mult.operand1.accept(this);
		in.run(mult, 1);
		mult.operand2.accept(this);
		post.run(mult);
	}

	public void visit(ArrayLookup arrayLookup) {
		pre.run(arrayLookup);
		in.run(arrayLookup, 0);
		arrayLookup.array.accept(this);
		in.run(arrayLookup, 1);
		arrayLookup.index.accept(this);
		post.run(arrayLookup);
	}

	public void visit(ArrayLength arrayLength) {
		pre.run(arrayLength);
		in.run(arrayLength, 0);
		arrayLength.array.accept(this);
		post.run(arrayLength);
	}

	public void visit(MethodCall methodCall) {
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
	}

	public void visit(IntLiteral intLiteral) {
		pre.run(intLiteral);
		in.run(intLiteral, 0);
		intLiteral.token.accept(this);
		post.run(intLiteral);
	}

	public void visit(TrueLiteral trueLiteral) {
		pre.run(trueLiteral);
		post.run(trueLiteral);
	}

	public void visit(FalseLiteral falseLiteral) {
		pre.run(falseLiteral);
		post.run(falseLiteral);
	}

	public void visit(Id id) {
		pre.run(id);
		in.run(id, 0);
		id.token.accept(this);
		post.run(id);
	}

	public void visit(This theThis) {
		pre.run(theThis);
		post.run(theThis);
	}

	public void visit(ArrayAllocation arrayAllocation) {
		pre.run(arrayAllocation);
		in.run(arrayAllocation, 0);
		arrayAllocation.expression.accept(this);
		post.run(arrayAllocation);
	}

	public void visit(Allocation allocation) {
		pre.run(allocation);
		in.run(allocation, 0);
		allocation.className.accept(this);
		post.run(allocation);
	}

	public void visit(Not not) {
		pre.run(not);
		in.run(not, 0);
		not.operand.accept(this);
		post.run(not);
	}

	public void visit(IntType intType) {
		pre.run(intType);
		post.run(intType);
	}

	public void visit(BooleanType booleanType) {
		pre.run(booleanType);
		post.run(booleanType);
	}

	public void visit(ArrayType arrayType) {
		pre.run(arrayType);
		post.run(arrayType);
	}

	public void visit(Token token) {
		pre.run(token);
		post.run(token);
	}

	public void visit(List list) {
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
	}

	/*
	public <T extends Node> void visitList(List<Node> list) {
		Iterator<Node> iterator = list.iterator();
		while (iterator.hasNext()) {
			Node node = iterator.next();
			node.accept(this);
		}
	}
	*/
}
