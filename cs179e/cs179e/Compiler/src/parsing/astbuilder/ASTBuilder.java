package parsing.astbuilder;

import parsing.ast.tree.*;
import parsing.ast.tree.Block;
import parsing.ast.tree.BooleanType;
import parsing.ast.tree.Class;
import parsing.ast.tree.Expression;
import parsing.ast.tree.TrueLiteral;
import parsing.parser.syntaxtree.*;
import parsing.parser.visitor.DepthFirstVisitor;
import parsing.parser.syntaxtree.NotExpression;
import parsing.parser.syntaxtree.AllocationExpression;
import parsing.parser.syntaxtree.ArrayAllocationExpression;
import parsing.parser.syntaxtree.IntegerLiteral;
import parsing.parser.syntaxtree.MethodDeclaration;
import parsing.parser.syntaxtree.MessageSend;

import parsing.ast.tree.List;


import java.util.Enumeration;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class ASTBuilder extends DepthFirstVisitor {

	private Goal root;

	public ASTBuilder(Goal root) {
		this.root = root;
	}

	public CompilationUnit build() {
		root.accept(this);
		return root.outCompilationUnit;
	}

	/**
	 * f0 -> MainClass()
	 * f1 -> ( TypeDeclaration() )*
	 * f2 -> <EOF>
	 */
	public void visit(Goal n) {
		super.visit(n);

		List<Class> classes
				= new List<parsing.ast.tree.Class>();
		Enumeration<parsing.parser.syntaxtree.Node> e = n.f1.elements();
		while (e.hasMoreElements()) {
			TypeDeclaration typeDeclaration = (TypeDeclaration)e.nextElement();
			classes.add(typeDeclaration.outClass);
		}

		n.outCompilationUnit = new CompilationUnit(n.f0.outMainClass, classes);
	}

	/**
	 * f0 -> "class"
	 * f1 -> Identifier()
	 * f2 -> "{"
	 * f3 -> "public"
	 * f4 -> "static"
	 * f5 -> "void"
	 * f6 -> "main"
	 * f7 -> "("
	 * f8 -> "String"
	 * f9 -> "["
	 * f10 -> "]"
	 * f11 -> Identifier()
	 * f12 -> ")"
	 * f13 -> "{"
	 * f14 -> ( VarDeclaration() )*
	 * f15 -> ( Statement() )*
	 * f16 -> "}"
	 * f17 -> "}"
	 */
	public void visit(parsing.parser.syntaxtree.MainClass n) {
		super.visit(n);
		MethodBody b = parseMethod(n.f14, n.f15);
		n.outMainClass = new parsing.ast.tree.MainClass(
				n.f1.outId, n.f11.outId, b.varDeclarations, b.statements);
	}

	/**
	 * f0 -> ClassDeclaration()
	 * | ClassExtendsDeclaration()
	 */
	public void visit(TypeDeclaration n) {
		super.visit(n);
		switch (n.f0.which) {
			case 0:
				n.outClass = ((ClassDeclaration)(n.f0.choice)).outClass;
				break;
			case 1:
				n.outClass = ((ClassExtendsDeclaration)(n.f0.choice)).outClass;
				break;
		}
	}

	/**
	 * f0 -> "class"
	 * f1 -> Identifier()
	 * f2 -> "{"
	 * f3 -> ( VarDeclaration() )*
	 * f4 -> ( MethodDeclaration() )*
	 * f5 -> "}"
	 */
	public void visit(ClassDeclaration n) {
		super.visit(n);

		ClassBody b = parseClass(n.f3, n.f4);

		n.outClass = new Class(
				n.f1.outId,
				b.varDeclarations,
				b.methodDeclarations
		);
	}

	/**
	 * f0 -> "class"
	 * f1 -> Identifier()
	 * f2 -> "extends"
	 * f3 -> Identifier()
	 * f4 -> "{"
	 * f5 -> ( VarDeclaration() )*
	 * f6 -> ( MethodDeclaration() )*
	 * f7 -> "}"
	 */
	public void visit(ClassExtendsDeclaration n) {
		super.visit(n);

		ClassBody b = parseClass(n.f5, n.f6);

		n.outClass = new Class(
				n.f1.outId,
				n.f3.outId,
				b.varDeclarations,
				b.methodDeclarations
		);
	}

	private class ClassBody {
		List<VarDecl> varDeclarations;
		List<Method> methodDeclarations;
		private ClassBody(List<VarDecl> varDeclarations, List<Method> methodDeclarations) {
			this.varDeclarations = varDeclarations;
			this.methodDeclarations = methodDeclarations;
		}
	}
	private ClassBody parseClass(NodeListOptional nodeListOptional1, NodeListOptional nodeListOptional2) {
		List<VarDecl> varDeclarations
				= new List<VarDecl>();
		Enumeration<parsing.parser.syntaxtree.Node> e = nodeListOptional1.elements();
		while (e.hasMoreElements()) {
			parsing.parser.syntaxtree.VarDeclaration varDeclaration = (parsing.parser.syntaxtree.VarDeclaration)e.nextElement();
			varDeclarations.add(varDeclaration.outVarDecl);
		}

		List<Method> methodDeclarations
				= new List<Method>();
		Enumeration<parsing.parser.syntaxtree.Node> e2 = nodeListOptional2.elements();
		while (e2.hasMoreElements()) {
			MethodDeclaration methodDeclaration = (MethodDeclaration)e2.nextElement();
			methodDeclarations.add(methodDeclaration.outMethod);
		}
		return new ClassBody(varDeclarations, methodDeclarations);
	}


	/**
	 * f0 -> Type()
	 * f1 -> Identifier()
	 * f2 -> ";"
	 */
	public void visit(parsing.parser.syntaxtree.VarDeclaration n) {
		super.visit(n);
		n.outVarDecl = new VarDecl(n.f0.outType, n.f1.outId);
	}


	private class MethodBody {
		List<VarDecl> varDeclarations;
		List<parsing.ast.tree.Statement> statements;

		private MethodBody(List<VarDecl> varDeclarations, List<parsing.ast.tree.Statement> statements) {
			this.varDeclarations = varDeclarations;
			this.statements = statements;
		}
	}

	private MethodBody parseMethod(NodeListOptional nodeListOptional1, NodeListOptional nodeListOptional2) {
		List<VarDecl> varDeclarations
				= new List<VarDecl>();
		Enumeration<parsing.parser.syntaxtree.Node> e = nodeListOptional1.elements();
		while (e.hasMoreElements()) {
			parsing.parser.syntaxtree.VarDeclaration varDeclaration = (parsing.parser.syntaxtree.VarDeclaration)e.nextElement();
			varDeclarations.add(varDeclaration.outVarDecl);
		}

		List<parsing.ast.tree.Statement> statements
				= new List<parsing.ast.tree.Statement>();
		Enumeration<parsing.parser.syntaxtree.Node> e2 = nodeListOptional2.elements();
		while (e2.hasMoreElements()) {
			parsing.parser.syntaxtree.Statement statement = (parsing.parser.syntaxtree.Statement)e2.nextElement();
			statements.add(statement.outStatement);
		}
		return new MethodBody(varDeclarations, statements);
	}
	/**
	 * f0 -> "public"
	 * f1 -> Type()
	 * f2 -> Identifier()
	 * f3 -> "("
	 * f4 -> ( FormalParameterList() )?
	 * f5 -> ")"
	 * f6 -> "{"
	 * f7 -> ( VarDeclaration() )*
	 * f8 -> ( Statement() )*
	 * f9 -> "return"
	 * f10 -> Expression()
	 * f11 -> ";"
	 * f12 -> "}"
	 */
	public void visit(MethodDeclaration n) {
		super.visit(n);



		List<Parameter> parameters = null;
		if (n.f4.present())
			parameters = ((FormalParameterList)n.f4.node).outParameters;

		MethodBody b = parseMethod(n.f7, n.f8);
        b.statements.add(new Return(n.f10.outExpression));

		n.outMethod = new Method(
				n.f1.outType,
				n.f2.outId,
				parameters,
				b.varDeclarations,
                b.statements
        );
	}

	/**
	 * f0 -> FormalParameter()
	 * f1 -> ( FormalParameterRest() )*
	 */
	public void visit(FormalParameterList n) {
		super.visit(n);
		n.outParameters = new List<Parameter>();
		n.outParameters.add(n.f0.outParamater);
		Enumeration<parsing.parser.syntaxtree.Node> e = n.f1.elements();
		while (e.hasMoreElements()) {
			FormalParameterRest fpr = (FormalParameterRest)e.nextElement();
			n.outParameters.add(fpr.outParamater);
		}
	}

	/**
	 * f0 -> Type()
	 * f1 -> Identifier()
	 */
	public void visit(FormalParameter n) {
		super.visit(n);
		n.outParamater = new Parameter(n.f0.outType, n.f1.outId);
	}

	/**
	 * f0 -> ","
	 * f1 -> FormalParameter()
	 */
	public void visit(FormalParameterRest n) {
		super.visit(n);
		n.outParamater = n.f1.outParamater;
	}

	/**
	 * f0 -> ArrayType()
	 * | BooleanType()
	 * | IntegerType()
	 * | Identifier()
	 */
	public void visit(parsing.parser.syntaxtree.Type n) {
		super.visit(n);
		switch (n.f0.which) {
			case 0:
				n.outType = ((parsing.parser.syntaxtree.ArrayType)(n.f0.choice)).outArrayType;
				break;
			case 1:
				n.outType = ((parsing.parser.syntaxtree.BooleanType)(n.f0.choice)).outBooleanType;
				break;
			case 2:
				n.outType = ((IntegerType)(n.f0.choice)).outIntType;
				break;
			case 3:
				n.outType = ((parsing.parser.syntaxtree.Identifier)(n.f0.choice)).outId;
				break;
		}
	}

	/**
	 * f0 -> "int"
	 * f1 -> "["
	 * f2 -> "]"
	 */
	public void visit(parsing.parser.syntaxtree.ArrayType n) {
		super.visit(n);
		n.outArrayType = parsing.ast.tree.ArrayType.instance();
	}

	/**
	 * f0 -> "boolean"
	 */
	public void visit(parsing.parser.syntaxtree.BooleanType n) {
		super.visit(n);
		n.outBooleanType = BooleanType.instance();
	}

	/**
	 * f0 -> "int"
	 */
	public void visit(IntegerType n) {
		super.visit(n);
		n.outIntType = IntType.instance();
	}

	/**
	 * f0 -> Block()
	 * | AssignmentStatement()
	 * | ArrayAssignmentStatement()
	 * | IfStatement()
	 * | WhileStatement()
	 * | PrintStatement()
	 */
	public void visit(parsing.parser.syntaxtree.Statement n) {
		super.visit(n);
		switch (n.f0.which) {
			case 0:
				n.outStatement = ((parsing.parser.syntaxtree.Block)(n.f0.choice)).outBlock;
				break;
			case 1:
				n.outStatement = ((AssignmentStatement)(n.f0.choice)).outAssign;
				break;
			case 2:
				n.outStatement = ((ArrayAssignmentStatement)(n.f0.choice)).outArrayAssign;
				break;
			case 3:
				n.outStatement = ((IfStatement)(n.f0.choice)).outIf;
				break;
			case 4:
				n.outStatement = ((WhileStatement)(n.f0.choice)).outWhile;
				break;
			case 5:
				n.outStatement = ((PrintStatement)(n.f0.choice)).outPrint;
				break;
		}
	}

	/**
	 * f0 -> "{"
	 * f1 -> ( Statement() )*
	 * f2 -> "}"
	 */
	public void visit(parsing.parser.syntaxtree.Block n) {
		super.visit(n);
		List<parsing.ast.tree.Statement> statements = new List<parsing.ast.tree.Statement>();
		Enumeration<parsing.parser.syntaxtree.Node> e = n.f1.elements();
		while (e.hasMoreElements()) {
			parsing.parser.syntaxtree.Statement statement = (parsing.parser.syntaxtree.Statement)e.nextElement();
			statements.add(statement.outStatement);
		}
		n.outBlock = new Block(statements);
	}

	/**
	 * f0 -> Identifier()
	 * f1 -> "="
	 * f2 -> Expression()
	 * f3 -> ";"
	 */
	public void visit(AssignmentStatement n) {
		super.visit(n);
		n.outAssign = new Assign(n.f0.outId, n.f2.outExpression);
	}

	/**
	 * f0 -> Identifier()
	 * f1 -> "["
	 * f2 -> Expression()
	 * f3 -> "]"
	 * f4 -> "="
	 * f5 -> Expression()
	 * f6 -> ";"
	 */
	public void visit(ArrayAssignmentStatement n) {
		super.visit(n);
		n.outArrayAssign = new ArrayAssign(
				n.f0.outId, n.f2.outExpression, n.f5.outExpression);
	}

	/**
	 * f0 -> "if"
	 * f1 -> "("
	 * f2 -> Expression()
	 * f3 -> ")"
	 * f4 -> Statement()
	 * f5 -> "else"
	 * f6 -> Statement()
	 */
	public void visit(IfStatement n) {
		super.visit(n);
		n.outIf = new If(n.f2.outExpression, n.f4.outStatement, n.f6.outStatement);
	}

	/**
	 * f0 -> "while"
	 * f1 -> "("
	 * f2 -> Expression()
	 * f3 -> ")"
	 * f4 -> Statement()
	 */
	public void visit(WhileStatement n) {
		super.visit(n);
		n.outWhile = new While(n.f2.outExpression, n.f4.outStatement);
	}

	/**
	 * f0 -> "System.out.println"
	 * f1 -> "("
	 * f2 -> Expression()
	 * f3 -> ")"
	 * f4 -> ";"
	 */
	public void visit(PrintStatement n) {
		super.visit(n);
		n.outPrint = new Print(n.f2.outExpression);
	}

	/**
	 * f0 -> AndExpression()
	 * | CompareExpression()
	 * | PlusExpression()
	 * | MinusExpression()
	 * | TimesExpression()
	 * | ArrayLookup()
	 * | ArrayLength()
	 * | MessageSend()
	 * | PrimaryExpression()
	 */
	public void visit(parsing.parser.syntaxtree.Expression n) {
		super.visit(n);
		switch (n.f0.which) {
			case 0:
				n.outExpression = ((AndExpression)(n.f0.choice)).outAnd;
				break;
			case 1:
				n.outExpression = ((CompareExpression)(n.f0.choice)).outLessThan;
				break;
			case 2:
				n.outExpression = ((PlusExpression)(n.f0.choice)).outPlus;
				break;
			case 3:
				n.outExpression = ((MinusExpression)(n.f0.choice)).outMinus;
				break;
			case 4:
				n.outExpression = ((TimesExpression)(n.f0.choice)).outMult;
				break;
			case 5:
				n.outExpression = ((parsing.parser.syntaxtree.ArrayLookup)(n.f0.choice)).outArrayLoopup;
				break;
			case 6:
				n.outExpression = ((parsing.parser.syntaxtree.ArrayLength)(n.f0.choice)).outArrayLength;
				break;
			case 7:
				n.outExpression = ((MessageSend)(n.f0.choice)).outMethodCall;
				break;
			case 8:
				n.outExpression = ((PrimaryExpression)(n.f0.choice)).outExpression;
				break;
		}
	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "&&"
	 * f2 -> PrimaryExpression()
	 */
	public void visit(AndExpression n) {
		super.visit(n);
		n.outAnd = new And(n.f0.outExpression, n.f2.outExpression);
	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "<"
	 * f2 -> PrimaryExpression()
	 */
	public void visit(CompareExpression n) {
		super.visit(n);
		n.outLessThan = new LessThan(n.f0.outExpression, n.f2.outExpression);
	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "+"
	 * f2 -> PrimaryExpression()
	 */
	public void visit(PlusExpression n) {
		super.visit(n);
		n.outPlus = new Plus(n.f0.outExpression, n.f2.outExpression);
	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "-"
	 * f2 -> PrimaryExpression()
	 */
	public void visit(MinusExpression n) {
		super.visit(n);
		n.outMinus = new Minus(n.f0.outExpression, n.f2.outExpression);
	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "*"
	 * f2 -> PrimaryExpression()
	 */
	public void visit(TimesExpression n) {
		super.visit(n);
		n.outMult = new Mult(n.f0.outExpression, n.f2.outExpression);
	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "["
	 * f2 -> PrimaryExpression()
	 * f3 -> "]"
	 */
	public void visit(parsing.parser.syntaxtree.ArrayLookup n) {
		super.visit(n);
		n.outArrayLoopup = new parsing.ast.tree.ArrayLookup(n.f0.outExpression, n.f2.outExpression);
	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "."
	 * f2 -> "length"
	 */
	public void visit(parsing.parser.syntaxtree.ArrayLength n) {
		super.visit(n);
		n.outArrayLength = new parsing.ast.tree.ArrayLength(n.f0.outExpression);
	}

	/**
	 * f0 -> PrimaryExpression()
	 * f1 -> "."
	 * f2 -> Identifier()
	 * f3 -> "("
	 * f4 -> ( ExpressionList() )?
	 * f5 -> ")"
	 */
	public void visit(MessageSend n) {
		super.visit(n);
		NodeOptional no = n.f4;
		if (no.present()) {
			List<Expression> expressions =
					((ExpressionList)no.node).outExpressions;
//			System.out.println(elements);
			n.outMethodCall = new MethodCall(n.f0.outExpression, n.f2.outId, expressions);
			//System.out.println("Here");
		}
		else
			n.outMethodCall = new MethodCall(n.f0.outExpression, n.f2.outId);
	}

	/**
	 * f0 -> Expression()
	 * f1 -> ( ExpressionRest() )*
	 */
	public void visit(ExpressionList n) {
		super.visit(n);
		n.outExpressions = new List<Expression>();
//		System.out.println(n.operator.outExpression);
		n.outExpressions.add(n.f0.outExpression);
		Enumeration<parsing.parser.syntaxtree.Node> e = n.f1.elements();
		while (e.hasMoreElements()) {
			ExpressionRest node = (ExpressionRest)e.nextElement();
			n.outExpressions.add(node.outExpression);
		}
	}

	/**
	 * f0 -> ","
	 * f1 -> Expression()
	 */
	public void visit(ExpressionRest n) {
		super.visit(n);
		n.outExpression = n.f1.outExpression;		
	}

	/**
	 * f0 -> IntegerLiteral()
	 * | TrueLiteral()
	 * | FalseLiteral()
	 * | Identifier()
	 * | ThisExpression()
	 * | ArrayAllocationExpression()
	 * | AllocationExpression()
	 * | NotExpression()
	 * | BracketExpression()
	 */

	public void visit(PrimaryExpression n) {
		super.visit(n);
		switch (n.f0.which) {
			case 0:
				n.outExpression = ((IntegerLiteral)(n.f0.choice)).outIntLiteral;
				break;
			case 1:
				n.outExpression = ((parsing.parser.syntaxtree.TrueLiteral)(n.f0.choice)).outTrueLiteral;
				break;
			case 2:
				n.outExpression = ((parsing.parser.syntaxtree.FalseLiteral)(n.f0.choice)).outFalseLiteral;
				break;
			case 3:
				n.outExpression = ((parsing.parser.syntaxtree.Identifier)(n.f0.choice)).outId;
				break;
			case 4:
				n.outExpression = ((ThisExpression)(n.f0.choice)).outThis;
				break;
			case 5:
				n.outExpression = ((ArrayAllocationExpression)(n.f0.choice)).outArrayAllocation;
				break;
			case 6:
				n.outExpression = ((AllocationExpression)(n.f0.choice)).outAllocation;
				break;
			case 7:
				n.outExpression = ((NotExpression)(n.f0.choice)).outNot;
				break;
			case 8:
				n.outExpression = ((BracketExpression)(n.f0.choice)).outExpression;
				break;
		}
	}

	/**
	 * f0 -> <INTEGER_LITERAL>
	 */
	public void visit(IntegerLiteral n) {
		super.visit(n);
		n.outIntLiteral = new IntLiteral(new Token(n.f0));
	}

	/**
	 * f0 -> "true"
	 */
	public void visit(parsing.parser.syntaxtree.TrueLiteral n) {
		super.visit(n);
		n.outTrueLiteral = TrueLiteral.getInstance();
	}

	/**
	 * f0 -> "false"
	 */
	public void visit(parsing.parser.syntaxtree.FalseLiteral n) {
		super.visit(n);
		n.outFalseLiteral = parsing.ast.tree.FalseLiteral.getInstance();
	}

	/**
	 * f0 -> <IDENTIFIER>
	 */
	public void visit(parsing.parser.syntaxtree.Identifier n) {
		super.visit(n);
		n.outId = new Id(new Token(n.f0));
	}

	/**
	 * f0 -> "this"
	 */
	public void visit(ThisExpression n) {
		super.visit(n);
		n.outThis = This.getInstance();
	}

	/**
	 * f0 -> "new"
	 * f1 -> "int"
	 * f2 -> "["
	 * f3 -> Expression()
	 * f4 -> "]"
	 */
	public void visit(ArrayAllocationExpression n) {
		super.visit(n);
		n.outArrayAllocation = new ArrayAllocation(n.f3.outExpression);
	}

	/**
	 * f0 -> "new"
	 * f1 -> Identifier()
	 * f2 -> "("
	 * f3 -> ")"
	 */
	public void visit(AllocationExpression n) {
		super.visit(n);
		n.outAllocation = new Allocation(n.f1.outId);
	}

	/**
	 * f0 -> "!"
	 * f1 -> Expression()
	 */
	public void visit(NotExpression n) {
		super.visit(n);
		n.outNot = new Not(n.f1.outExpression);
	}

	/**
	 * f0 -> "("
	 * f1 -> Expression()
	 * f2 -> ")"
	 */
	public void visit(BracketExpression n) {
		super.visit(n);
		n.outExpression = n.f1.outExpression;
	}
}

