package jsrc.matlab.astbuild;

import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.tree.expression.literal.*;
import jsrc.matlab.ast.tree.expression.literal.StringLiteral;
import jsrc.matlab.ast.tree.expression.op.application.*;
import jsrc.matlab.ast.tree.expression.op.application.Call;
import jsrc.matlab.ast.tree.statement.xtras.Tic;
import jsrc.matlab.astbuild.intastnodes.ArrayMaker;
import jsrc.matlab.astbuild.intastnodes.expressions.*;
import jsrc.matlab.syntaxanalysis.syntaxtree.*;
import jsrc.matlab.syntaxanalysis.visitor.GJNoArguDepthFirst;
import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;
import lesani.compiler.ast.*;
import jsrc.matlab.ast.tree.declaration.*;
import jsrc.matlab.ast.tree.expression.*;
import jsrc.matlab.ast.tree.expression.op.constructors.MatrixConstructor;
import jsrc.matlab.ast.tree.expression.op.logical.*;
import jsrc.matlab.ast.tree.expression.op.math.*;
import jsrc.matlab.ast.tree.expression.op.constructors.RangeVectorConstructor;
import jsrc.matlab.ast.tree.expression.op.relational.*;
import jsrc.matlab.ast.tree.statement.*;
import jsrc.matlab.ast.tree.statement.ArrayAssignment;
import jsrc.matlab.ast.tree.statement.CallSt;
import jsrc.matlab.ast.tree.statement.Statement;
import jsrc.matlab.astbuild.intastnodes.statement.Else;
import jsrc.matlab.astbuild.intastnodes.declarations.FunOutput;
import jsrc.matlab.astbuild.intastnodes.declarations.FunHeader;
import jsrc.matlab.astbuild.intastnodes.expressions.OpIntExp;
import lesani.compiler.ast.Node;
import lesani.compiler.ast.NodeChoice;
import lesani.compiler.ast.NodeList;
import lesani.compiler.ast.NodeSequence;
import lesani.compiler.ast.optional.*;

import java.util.Enumeration;
import java.util.Iterator;

public class ASTBuilder extends GJNoArguDepthFirst<Node> {

	private jsrc.matlab.syntaxanalysis.syntaxtree.File file;

    public ASTBuilder(jsrc.matlab.syntaxanalysis.syntaxtree.File file) {
		this.file = file;
	}

    public CompilationUnit build() {
        return (CompilationUnit) this.file.accept(this);

    }

    private Node visitDispatch(jsrc.matlab.syntaxanalysis.syntaxtree.Node node) {
        return node.accept(this);
    }

    //
    //  Auto class visitors
    //
    private Node visitList(Enumeration<jsrc.matlab.syntaxanalysis.syntaxtree.Node> e) {
        //System.out.println("In Enumeration");
        NodeList nodeList = new NodeList();
        while (e.hasMoreElements()) {
            jsrc.matlab.syntaxanalysis.syntaxtree.Node parseNode = e.nextElement();
            Node node = visitDispatch(parseNode);
//            if (node == null)
//                System.out.println("null in Enumeration");
            nodeList.add(node);
        }
        return nodeList;
    }

    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.NodeList n) {
        //System.out.println("In NodeList");
        return visitList(n.elements());
    }
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.NodeListOptional n) {
        return visitList(n.elements());
    }

    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.NodeOptional n) {
        if (n.present()) {
            Node node = visitDispatch(n.node);
            return new SomeNode(node);
        }
        else
            return NoneNode.instance();
    }

    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.NodeSequence n) {
        int count = n.size();
        Node[] nodes = new Node[count];

        for (int i = 0; i < count; i++)
            nodes[i] = visitDispatch(n.elementAt(i));

        return new NodeSequence(nodes);
    }


    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.NodeChoice n) {
//        System.out.println("In visit(NodeChoice)");
        return new NodeChoice(visitDispatch(n.choice), n.which);
    }
    
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.NodeToken n) {
        return new Token(n);
    }

//====================================================================================

    /**
     * f0 -> ( FunctionHeader() )?
     * f1 -> ( Statement() )*
     * f2 -> <EOF>
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.File n) {

        NodeList<Statement> statementList = (NodeList<Statement>) visitDispatch(n.f1);
        Statement[] statements = ArrayMaker.statementArray(statementList);

        OptionalNode fileHeader = (OptionalNode)visitDispatch(n.f0);
        if (fileHeader.isPresent()) {
            FunHeader funHeader = (FunHeader)((SomeNode)fileHeader).get();
            Option<Id[]> outputParams = funHeader.outputParams;
            Id name = funHeader.name;
            Option<Id[]> inputParams = funHeader.inputParams;
            // funHeader.outputParams

            final Function function = new Function(outputParams, name, inputParams, statements);
            setLoc(function, name);
            return function;
        } else
            return new Script(statements);
    }

    /**
     * f0 -> "function"
     * f1 -> [ OutputClause() ]
     * f2 -> Identifier()
     * f3 -> "("
     * f4 -> ( IdentifierList() )?
     * f5 -> ")"
     * f6 -> ( ";" )?
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.FunctionHeader n) {
        OptionalNode optionalFunOutput = (OptionalNode)visitDispatch(n.f1);

        Option<Id[]> outputParams;
        if (optionalFunOutput.isPresent()) {
            FunOutput funOutput = (FunOutput)((SomeNode)optionalFunOutput).get();
            outputParams = new Some<Id[]>(funOutput.outputParams);
        } else
            outputParams = None.instance();

        Id funName = (Id)visitDispatch(n.f2);

        Option<Id[]> inputIds = null;
        OptionalNode optionalInputIds = (OptionalNode)visitDispatch(n.f4);
        if (optionalInputIds.isPresent()) {
            NodeList<Id> idList = (NodeList<Id>)((SomeNode)optionalInputIds).get();
            inputIds = new Some<Id[]>(ArrayMaker.idArray(idList));
        } else
            inputIds = None.instance();

        return new FunHeader(outputParams, funName, inputIds);
    }


    /**
     * f0 -> Output()
     * f1 -> "="
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.OutputClause n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> Identifier()
     *       | MultiOutput()
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.Output n) {
        Node node = visitDispatch(n.f0);
        if (node instanceof Id)
            return new FunOutput(new Id[] {(Id)node});
        else {
            NodeList<Id> idList = (NodeList<Id>)node;
            Id[] ids = ArrayMaker.idArray(idList);
//            OutputParam[] outputParams = new OutputParam[ids.length];
//            for (int i = 0; i < outputParams.length; i++) {
//                outputParams[i] = new OutputParam(ids[i]);
//            }
//            return new FunOutput(outputParams);
            return new FunOutput(ids);
        }
    }

    /**
     * f0 -> "["
     * f1 -> IdentifierList()
     * f2 -> "]"
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.MultiOutput n) {
        return visitDispatch(n.f1);
    }


    /**
     * f0 -> CommandList()
     *       | ArrayUpdate()
     *       | CallSt()
     *       | AssignmentSt()
     *       | CallAndMultipleAssignmentSt()
     *       | IfSt()
     *       | ForSt()
     *       | WhileSt()
     *       | SwitchSt()
     *       | BreakSt()
     *       | ReturnSt()
     *       | Tic()
     *       | PrintSt()
     *       | PrintlnSt()
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.Statement n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> "tic"
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.Tic n) {
        Id ticId = new Id("tic");
        Call ticCall = new Call(ticId, new Expression[]{});
        CallSt ticCallSt = new CallSt(ticCall);

        ticId.lineNo = ticCall.lineNo = ticCallSt.lineNo = ((NodeToken)n.f0).beginLine;
        ticId.columnNo = ticCall.columnNo = ticCallSt.columnNo = ((NodeToken)n.f0).beginColumn;

        return ticCallSt;
//        Tic tic =  new Tic();
//        setLoc(tic, (NodeToken)n.f0);
//        return tic;
    }
    /**
     *    Identifier f0;
     *    NodeOptional f1;
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.Command n) {
        Id name = (Id)visitDispatch(n.f0);

        Expression[] args;
        OptionalNode optionalId = (OptionalNode)visitDispatch(n.f1);
        if (optionalId.isPresent()) {
            StringLiteral argument = new StringLiteral(((Id)((SomeNode)optionalId).get()).name);
            args = new Expression[] {argument};
        } else
            args = new Expression[0];
        final jsrc.matlab.ast.tree.expression.op.application.Call call = new jsrc.matlab.ast.tree.expression.op.application.Call(name, args);
        setLoc(call, name);
        return call;
    }

    /**
     * f0 -> Call()
     * f1 -> [ ";" ]
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.CallSt n) {
        jsrc.matlab.ast.tree.expression.op.application.Call call = (jsrc.matlab.ast.tree.expression.op.application.Call)visitDispatch(n.f0);
        return new CallSt(call);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "="
     * f2 -> Expression()
     * f3 -> [ ";" ]
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.AssignmentSt n) {
        Id id = (Id)visitDispatch(n.f0);
        Expression exp = (Expression)visitDispatch(n.f2);
        final Assignment assignment = new Assignment(id, exp);
        setLoc(assignment, n.f1);
        return assignment;
    }

    /**
     * f0 -> "["
     * f1 -> IdentifierList()
     * f2 -> "]"
     * f3 -> "="
     * f4 -> Call()
     * f5 -> [ ";" ]
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.CallAndMultipleAssignmentSt n) {
        NodeList<Id> idsList = (NodeList<Id>)visitDispatch(n.f1);
        Id[] ids = ArrayMaker.idArray(idsList);
        jsrc.matlab.ast.tree.expression.op.application.Call call = (jsrc.matlab.ast.tree.expression.op.application.Call)visitDispatch(n.f4);
        final CallAndMultiAssignment assignment = new CallAndMultiAssignment(ids, call);
        setLoc(assignment, n.f3);
        return assignment;
    }

    /**
     * f0 -> IndexedArray()
     * f1 -> "="
     * f2 -> Expression()
     * f3 -> [ ";" ]
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.ArrayUpdate n) {
        CallOrArrayAccess callOrArrayAccess = (CallOrArrayAccess)visitDispatch(n.f0);
        Id id = callOrArrayAccess.id;
        Expression[] indices = callOrArrayAccess.args;

        Expression expression = (Expression)visitDispatch(n.f2);

        final ArrayAssignment arrayAssignment = new ArrayAssignment(id, indices, expression);
        setLoc(arrayAssignment, n.f1);
        return arrayAssignment;
    }

    /**
     * f0 -> "if"
     * f1 -> Expression()
     * f2 -> ( Statement() )*
     * f3 -> ( ElseIf() )*
     * f4 -> ( ElseClause() )?
     * f5 -> "end"
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.IfSt n) {
        Expression condition = (Expression) visitDispatch(n.f1);
        NodeList<Statement> statementNodeList = (NodeList<Statement>) visitDispatch(n.f2);
        Block ifBlock = new Block(ArrayMaker.statementArray(statementNodeList));

        NodeList<If.ElseIf> elseIfList = (NodeList<If.ElseIf>) visitDispatch(n.f3);
        If.ElseIf[] elseIfArray = ArrayMaker.elseIfArray(elseIfList);

        OptionalNode optionalElse = (OptionalNode)visitDispatch(n.f4);
        if (optionalElse.isPresent()) {
            Block elseBlock = new Block(((Else)((SomeNode) optionalElse).get()).statements);
            return new If(condition, ifBlock, elseIfArray, elseBlock);
        } else
            return new If(condition, ifBlock, elseIfArray);
    }

    /**
     * f0 -> "elseif"
     * f1 -> Expression()
     * f2 -> ( Statement() )*
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.ElseIf n) {
        Expression condition = (Expression) visitDispatch(n.f1);
        NodeList<Statement> statementNodeList = (NodeList<Statement>) visitDispatch(n.f2);
        Statement[] statements = ArrayMaker.statementArray(statementNodeList);

        return new If.ElseIf(condition, new Block(statements));
    }

    /**
     * f0 -> "else"
     * f1 -> ( Statement() )*
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.ElseClause n) {
        NodeList<Statement> statementNodeList = (NodeList<Statement>) visitDispatch(n.f1);
        Statement[] statements = ArrayMaker.statementArray(statementNodeList);

        return new Else(statements);
    }

    /**
     * f0 -> "for"
     * f1 -> Identifier()
     * f2 -> "="
     * f3 -> Expression()
     * f4 -> ( Statement() )*
     * f5 -> "end"
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.ForSt n) {
        Id id = (Id) visitDispatch(n.f1);
        Expression range = (Expression) visitDispatch(n.f3);

        NodeList<Statement> statementNodeList = (NodeList<Statement>) visitDispatch(n.f4);
        Statement[] statements = ArrayMaker.statementArray(statementNodeList);

        return new For(id, range, new Block(statements));
    }

    /**
     * f0 -> "while"
     * f1 -> Expression()
     * f2 -> ( Statement() )*
     * f3 -> "end"
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.WhileSt n) {
        Expression condition = (Expression) visitDispatch(n.f1);

        NodeList<Statement> statementNodeList = (NodeList<Statement>) visitDispatch(n.f2);
        Statement[] statements = ArrayMaker.statementArray(statementNodeList);

        return new While(condition, new Block(statements));
    }

    /**
     * f0 -> "switch"
     * f1 -> Expression()
     * f2 -> ( Case() )*
     * f3 -> "otherwise"
     * f4 -> ( Statement() )*
     * f5 -> "end"
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.SwitchSt n) {

        Expression selector = (Expression) visitDispatch(n.f1);

        NodeList<Switch.Case> casesNodeList = (NodeList<Switch.Case>) visitDispatch(n.f2);
        Switch.Case[] cases = ArrayMaker.caseArray(casesNodeList);

        NodeList<Statement> statementNodeList = (NodeList<Statement>) visitDispatch(n.f4);
        Option<Statement[]> defaultStatements =
                (statementNodeList.size() == 0) ?
                None.instance() :
                new Some<Statement[]>(ArrayMaker.statementArray(statementNodeList));

        return new Switch(selector, cases, defaultStatements);
    }

    /**
     * f0 -> "case"
     * f1 -> StringLiteral() | IntegerLiteral()
     * f2 -> ( Statement() )*
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.Case n) {
        Expression exp = (Expression) visitDispatch(n.f1);

        NodeList<Statement> statementNodeList = (NodeList<Statement>) visitDispatch(n.f2);
        Statement[] statements = ArrayMaker.statementArray(statementNodeList);

        return new Switch.Case(exp, statements);
    }

    /**
     * f0 -> "break"
     * f1 -> ";"
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.BreakSt n) {
        return Break.instance();
    }

    /**
     * f0 -> "return"
     * f1 -> ";"
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.ReturnSt n) {
        return Return.instance();
    }

    /**
     * f0 -> ShortCircuitOrExp()
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.Expression n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> ShortCircuitAndExp()
     * f1 -> ( ShortCircuitOrExpNodeRest() )*
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.ShortCircuitOrExp n) {
        Expression operand1 = (Expression)visitDispatch(n.f0);
        NodeList<IntExp> operands = (NodeList<IntExp>)visitDispatch(n.f1);
        Iterator<IntExp> iterator = operands.iterator();
        while (iterator.hasNext()) {
            IntExp intExp = iterator.next();
            Expression operand2 = intExp.expression;
            operand1 = new Or(operand1, operand2);
            setLoc(operand1, intExp);
        }
        return operand1;
    }

    /**
     * f0 -> "||"
     * f1 -> ShortCircuitAndExp()
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.ShortCircuitOrExpRest n) {
        final Expression expression = (Expression)visitDispatch(n.f1);
        IntExp intExp = new IntExp(expression);
        setLoc(intExp, n.f0);
        return intExp;
    }

    /**
     * f0 -> ElementWiseOrExp()
     * f1 -> ( ShortCircuitAndExpNodeRest() )*
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.ShortCircuitAndExp n) {
        Expression operand1 = (Expression)visitDispatch(n.f0);
        NodeList<IntExp> operands = (NodeList<IntExp>)visitDispatch(n.f1);
        Iterator<IntExp> iterator = operands.iterator();
        while (iterator.hasNext()) {
            IntExp intExp = iterator.next();
            Expression operand2 = intExp.expression;
            operand1 = new And(operand1, operand2);
            setLoc(operand1, intExp);
        }
        return operand1;
    }

    /**
     * f0 -> "&&"
     * f1 -> ElementWiseOrExp()
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.ShortCircuitAndExpRest n) {
        final Expression elementWiseOr = (Expression)visitDispatch(n.f1);
        IntExp intExp = new IntExp(elementWiseOr);
        setLoc(intExp, n.f0);
        return intExp;
    }

    /**
     * f0 -> ElementWiseAndExp()
     * f1 -> ( ElementWiseOrExpNodeRest() )*
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.ElementWiseOrExp n) {
        Expression operand1 = (Expression)visitDispatch(n.f0);
        NodeList<IntExp> operands = (NodeList<IntExp>)visitDispatch(n.f1);
        Iterator<IntExp> iterator = operands.iterator();
        while (iterator.hasNext()) {
            IntExp intExp = iterator.next();
            Expression operand2 = intExp.expression;
            operand1 = new ElementWiseOr(operand1, operand2);
            setLoc(operand1, intExp);
        }
        return operand1;
    }

    /**
     * f0 -> "|"
     * f1 -> ElementWiseAndExp()
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.ElementWiseOrExpRest n) {
        final Expression elementWiseAnd = (Expression)visitDispatch(n.f1);
        IntExp intExp = new IntExp(elementWiseAnd);
        setLoc(intExp, n.f0);
        return intExp;
    }

    /**
     * f0 -> RelationalExp()
     * f1 -> ( ElementWiseAndExpNodeRest() )*
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.ElementWiseAndExp n) {
        Expression operand1 = (Expression)visitDispatch(n.f0);
        NodeList<IntExp> operands = (NodeList<IntExp>)visitDispatch(n.f1);
        Iterator<IntExp> iterator = operands.iterator();
        while (iterator.hasNext()) {
            IntExp intExp = iterator.next();
            Expression operand2 = intExp.expression;
            operand1 = new ElementWiseAnd(operand1, operand2);
            setLoc(operand1, intExp);
        }
        return operand1;
    }

    /**
     * f0 -> "&"
     * f1 -> RelationalExp()
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.ElementWiseAndExpRest n) {
        final Expression relationalOp = (Expression)visitDispatch(n.f1);
        IntExp intExp = new IntExp(relationalOp);
        setLoc(intExp, n.f0);
        return intExp;

    }

    /**
     * f0 -> ColonVectorExp()
     * f1 -> ( RelationalExpNodeRest() )?
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.RelationalExp n) {
        Expression operand1 = (Expression)visitDispatch(n.f0);
        OptionalNode optionalExps = (OptionalNode) visitDispatch(n.f1);
        if (!optionalExps.isPresent())
            return operand1;
        OpIntExp opIntExp = (OpIntExp)((SomeNode)optionalExps).get();
        String lexeme = opIntExp.lexeme;
        Expression operand2 = opIntExp.operand;
        Expression res;
        if (lexeme.equals("<"))
            res = new LessThan(operand1, operand2);
        else if (lexeme.equals(">"))
            res = new GreaterThan(operand1, operand2);
        else if (lexeme.equals("<="))
            res = new LessThanEqual(operand1, operand2);
        else  if (lexeme.equals(">="))
            res = new GreaterThanEqual(operand1, operand2);
        else  if (lexeme.equals("=="))
            res = new Equality(operand1, operand2);
        else  //if (lexeme.equals("~="))
            res = new NotEquality(operand1, operand2);
        setLoc(res, opIntExp);
        return res;
    }

    /**
     * f0 -> ( "<" | ">" | "<=" | ">=" | "==" | "~=" )
     * f1 -> ColonVectorExp()
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.RelationalExpRest n) {
        String opLexeme = n.f0.choice.toString();
        Expression operand = (Expression)visitDispatch(n.f1);
        final OpIntExp exp = new OpIntExp(opLexeme, operand);
        setLoc(exp, (NodeToken)n.f0.choice);
        return exp;
    }

    /**
     * f0 -> Term()
     * f1 -> [ ":" Term() [ ":" Term() ] ]
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.ColonVectorExp n) {
        Expression firstExp = (Expression)visitDispatch(n.f0);
        OptionalNode optionalNodeSequence = (OptionalNode)visitDispatch(n.f1);
        if (!optionalNodeSequence.isPresent())
            return firstExp;
        
        NodeSequence nodeSequence = (NodeSequence)((SomeNode)optionalNodeSequence).get();
        Node[] nodes = nodeSequence.nodes;

//        NodeToken firstColon = (NodeToken)nodes[0];
        Expression secondExp = (Expression)nodes[1];


        OptionalNode lastOptionalNode = (OptionalNode)nodes[2];
        if (!lastOptionalNode.isPresent()) {
            //            setLoc(vectorConstructor, firstColon);
            return new RangeVectorConstructor(firstExp, secondExp);
        }
        NodeSequence lastNodeSequence = (NodeSequence)((SomeNode)lastOptionalNode).get();
        Node[] lastNodes = lastNodeSequence.nodes;
//        NodeToken secondColon = (NodeToken)lastNodes[0];
        Expression thirdExp = (Expression)lastNodes[1];

        return new RangeVectorConstructor(firstExp, secondExp, thirdExp);
    }

    /**
     * f0 -> Factor()
     * f1 -> ( TermNodeRest() )*
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.Term n) {
        Expression operand1 = (Expression)visitDispatch(n.f0);
        NodeList<OpIntExp> operands = (NodeList<OpIntExp>)visitDispatch(n.f1);
        Iterator<OpIntExp> iterator = operands.iterator();
        while (iterator.hasNext()) {
            OpIntExp opIntExp = iterator.next();
            String lexeme = opIntExp.lexeme;
            Expression operand2 = opIntExp.operand;
            if (lexeme.equals("+"))
                operand1 = new Plus(operand1, operand2);
            else if (lexeme.equals("-"))
                operand1 = new Minus(operand1, operand2);
            setLoc(operand1, opIntExp);
        }
        return operand1;
    }

    /**
     * f0 -> ( "+" | "-" )
     * f1 -> Factor()
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.TermRest n) {
        String opLexeme = n.f0.choice.toString();
        Expression operand = (Expression)visitDispatch(n.f1);
        final OpIntExp exp = new OpIntExp(opLexeme, operand);
        setLoc(exp, (NodeToken)n.f0.choice);
        return exp;
    }

    /**
     * f0 -> UnaryExp()
     * f1 -> ( FactorNodeRest() )*
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.Factor n) {
        Expression operand1 = (Expression)visitDispatch(n.f0);
        NodeList<OpIntExp> operands = (NodeList<OpIntExp>)visitDispatch(n.f1);
        Iterator<OpIntExp> iterator = operands.iterator();
        while (iterator.hasNext()) {
            OpIntExp opIntExp = (OpIntExp)iterator.next();
            String lexeme = opIntExp.lexeme;
            Expression operand2 = opIntExp.operand;
            if (lexeme.equals("*"))
                operand1 = new Times(operand1, operand2);
            else if (lexeme.equals("/"))
                operand1 = new Divide(operand1, operand2);
            else if (lexeme.equals(".*"))
                operand1 = new DotTimes(operand1, operand2);
            else if (lexeme.equals("./"))
                operand1 = new DotDivide(operand1, operand2);
            setLoc(operand1, opIntExp);
        }
        return operand1;
    }

    /**
     * f0 -> ( "*" | ".*" | "/" | "./" )
     * f1 -> UnaryExp()
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.FactorRest n) {
        String opLexeme = n.f0.choice.toString();
        Expression operand = (Expression)visitDispatch(n.f1);
        final OpIntExp exp = new OpIntExp(opLexeme, operand);
        setLoc(exp, (NodeToken)n.f0.choice);
        return exp;
    }

    /**
     * f0 -> [ "-" | "~" ]
     * f1 -> SuperScriptExp()
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.UnaryExp n) {
        Expression operand = (Expression)visitDispatch(n.f1);
        OptionalNode optionalToken =
                (OptionalNode)visitDispatch(n.f0);
        if (!optionalToken.isPresent())
            return operand;
        Token token = (Token)((SomeNode)optionalToken).get();
        String lexeme = token.toString();
        if (lexeme.equals("-")) {
            final UnaryMinus unaryMinus = new UnaryMinus(operand);
            setLoc(unaryMinus, (NodeToken)((jsrc.matlab.syntaxanalysis.syntaxtree.NodeChoice)n.f0.node).choice);
            return unaryMinus;
        }
        else //if (lexeme.equals("~"))
        {
            final Not not = new Not(operand);
            setLoc(not, (NodeToken)((jsrc.matlab.syntaxanalysis.syntaxtree.NodeChoice)n.f0.node).choice);
            return not;
        }
    }

    /**
     * f0 -> TransposeExp()
     *       | PowerExp()
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.SuperScriptExp n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> AtomExp()
     * f1 -> "'"
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.TransposeExp n) {
        Expression operand = (Expression)visitDispatch(n.f0);
        final Transpose transpose = new Transpose(operand);
        setLoc(transpose, n.f1);
        return transpose;
    }

    /**
     * f0 -> AtomExp()
     * f1 -> ( PowerExpNodeRest() )*
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.PowerExp n) {
        Expression operand1 = (Expression)visitDispatch(n.f0);
        NodeList<OpIntExp> operands = (NodeList<OpIntExp>)visitDispatch(n.f1);
        Iterator<OpIntExp> iterator = operands.iterator();
        while (iterator.hasNext()) {
            OpIntExp opIntExp = iterator.next();
            String lexeme = opIntExp.lexeme;
            Expression operand2 = opIntExp.operand;
            if (lexeme.equals("^"))
                operand1 = new Power(operand1, operand2);
            else if (lexeme.equals(".^"))
                operand1 = new DotPower(operand1, operand2);
            setLoc(operand1, opIntExp);
        }
        return operand1;
    }

    /**
     * f0 -> ( ".^" | "^" )
     * f1 -> AtomExp()
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.PowerExpRest n) {
        String opLexeme = n.f0.choice.toString();
        Expression operand = (Expression)visitDispatch(n.f1);
        final OpIntExp exp = new OpIntExp(opLexeme, operand);
        setLoc(exp, (NodeToken)n.f0.choice);
        return exp;
    }

    /**
     * f0 -> CallOrIndexedArray()
     *       | ParExp()
     *       | MatrixConstructor()
     *       | IntegerLiteral()
     *       | FloatingPointLiteral()
     *       | StringLiteral()
     *       | Identifier()
     *       | "toc"
     *       | ":"
     *       | "end"
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.AtomExp n) {
        Node exp = visitDispatch(n.f0);
        if (!(exp instanceof Token))
            return exp;
        if (exp.toString().equals(":")) {
            final Colon colon = new Colon();
            setLoc(colon, (NodeToken)n.f0.choice);
            return colon;
        } else if (exp.toString().equals("end")) {
            final End end = new End();
            setLoc(end, (NodeToken)n.f0.choice);
            return end;
        } else {//if (exp.toString().equals("toc")) {

            Id tocId = new Id("toc");
            jsrc.matlab.ast.tree.expression.op.application.Call tocCall = new jsrc.matlab.ast.tree.expression.op.application.Call(tocId, new Expression[]{});

            tocId.lineNo = tocCall.lineNo = ((NodeToken)n.f0.choice).beginLine;
            tocId.columnNo = tocCall.columnNo = ((NodeToken)n.f0.choice).beginColumn;

            return tocCall;
//            final Toc toc = new Toc();
//            setLoc(toc, (NodeToken)n.f0.choice);
//            return toc;
        }
//            return End.instance();
    }

    /**
     * f0 -> CallOrIndexedArray()
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.Call n) {
        CallOrArrayAccess node = (CallOrArrayAccess)visitDispatch(n.f0);
        Id id = node.id;
        Expression[] expressions = node.args;
        jsrc.matlab.ast.tree.expression.op.application.Call call = new jsrc.matlab.ast.tree.expression.op.application.Call(id, expressions);
        setLoc(call, id);
        return call;
    }

    /**
     * f0 -> CallOrIndexedArray()
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.IndexedArray n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "("
     * f2 -> ( ExpressionList() )?
     * f3 -> ")"
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.CallOrIndexedArray n) {
        Id id = (Id)visitDispatch(n.f0);

        Expression[] expressions;
        OptionalNode optionalExpressionList = (OptionalNode)visitDispatch(n.f2);
        if (optionalExpressionList.isPresent()) {
            NodeList<Expression> expressionList = (NodeList<Expression>)((SomeNode)optionalExpressionList).get();
            expressions = ArrayMaker.expressionArray(expressionList);
        } else
            expressions = new Expression[0];

        CallOrArrayAccess callOrArrayAccess = new CallOrArrayAccess(id, expressions);
        setLoc(callOrArrayAccess, id);
        return callOrArrayAccess;
    }

    /**
     * f0 -> "("
     * f1 -> Expression()
     * f2 -> ")"
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.ParExp n) {
        final Expression expression = (Expression)visitDispatch(n.f1);
        setLoc(expression, n.f0);
        return expression;
    }

    /**
     * f0 -> "["
     * f1 -> ExpressionSeqs()
     * f2 -> "]"
     */
    /**
     * f0 -> "["
     * f1 -> ( ExpressionSeqs() "]" | "]" )
     */

    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.MatrixConstructor n) {
        Node node = visitDispatch(n.f1);
        Expression[][] expressions;
        if (node instanceof Token)
            expressions = new Expression[0][0];
        else {
            Node seqs = ((NodeSequence)node).nodes[0];
            NodeList<NodeList<Expression>> expressionList =
                (NodeList<NodeList<Expression>>)seqs;

            expressions = ArrayMaker.expressionArrayArray(expressionList);
        }
        final MatrixConstructor matrixConstructor = new MatrixConstructor(expressions);
        setLoc(matrixConstructor, n.f0);
        return matrixConstructor;
    }

    /**
     * f0 -> <INTEGER_LITERAL>
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.IntegerLiteral n) {
        final IntLiteral intLiteral = new IntLiteral(n.f0.toString());
        setLoc(intLiteral, n.f0);
        return intLiteral;
    }

    /**
     * f0 -> <FLOATING_POINT_LITERAL>
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.FloatingPointLiteral n) {
        final DoubleLiteral doubleLiteral = new DoubleLiteral(n.f0.toString());
        setLoc(doubleLiteral, n.f0);
        return doubleLiteral;
    }

    /**
     * f0 -> <STRING_LITERAL>
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.StringLiteral n) {
        String lexeme = n.f0.toString();
        if (lexeme.length() <= 2)
            lexeme = "";
        else
            lexeme = lexeme.substring(1, lexeme.length() - 1);
        final StringLiteral stringLiteral = new StringLiteral(lexeme);
        setLoc(stringLiteral, n.f0);
        return stringLiteral;
    }

    /**
     * f0 -> <IDENTIFIER>
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.Identifier n) {
        final Id id = new Id(n.f0.toString());
        setLoc(id, n.f0);
        return id;
    }

    /**
     * f0 -> Identifier()
     * f1 -> ( IdentifierRest() )*
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.IdentifierList n) {
        Id id0 = (Id)visitDispatch(n.f0);
        NodeList<Id> idNodeList = (NodeList<Id>) visitDispatch(n.f1);
        NodeList<Id> allIdList = new NodeList<Id>();
        allIdList.add(id0);
        allIdList.add(idNodeList);
        return allIdList;

    }

    /**
     * f0 -> ","
     * f1 -> Identifier()
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.IdentifierRest n) {
        return visitDispatch(n.f1);
    }

    /**
     * f0 -> Command()
     * f1 -> ( CommandsNodeRest() )*
     * f2 -> [ ";" ]
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.CommandList n) {
        jsrc.matlab.ast.tree.expression.op.application.Call call0 = (jsrc.matlab.ast.tree.expression.op.application.Call)visitDispatch(n.f0);
        NodeList<jsrc.matlab.ast.tree.expression.op.application.Call> callNodeList = (NodeList<jsrc.matlab.ast.tree.expression.op.application.Call>) visitDispatch(n.f1);
        if (callNodeList.size() == 0)
            return new CallSt(call0);
        Statement[] statements = new Statement[callNodeList.size() + 1];
        statements[0] = new CallSt(call0);
        for (int i = 1; i < statements.length; i++)
            statements[i] = new CallSt(callNodeList.elementAt(i-1));

        return new Block(statements);
    }

    /**
     * f0 -> ","
     * f1 -> Command()
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.CommandsRest n) {
        return visitDispatch(n.f1);
    }

    /**
     * f0 -> Expression()
     * f1 -> ( ExpressionNodeRest() )*
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.ExpressionList n) {
        Expression exp0 = (Expression)visitDispatch(n.f0);
        NodeList<Expression> expNodeList = (NodeList<Expression>) visitDispatch(n.f1);
        NodeList<Expression> allExpList = new NodeList<Expression>();
        allExpList.add(exp0);
        allExpList.add(expNodeList);
        return allExpList;
    }

    /**
     * f0 -> ","
     * f1 -> Expression()
     */
    public Node visit(jsrc.matlab.syntaxanalysis.syntaxtree.ExpressionRest n) {
        return visitDispatch(n.f1);
    }

    /**
     * f0 -> Expression()
     * f1 -> ( ( "," )? Expression() )*
     */

    public Node visit(ExpressionSeq n) {
        Node firstExp = visitDispatch(n.f0);
        NodeList<Node> exps = new NodeList<Node>();
        exps.add(firstExp);
        NodeList<Node> expcommas = (NodeList<Node>) visitDispatch(n.f1);
        Iterator<Node> iterator = expcommas.iterator();
        while (iterator.hasNext()) {
            NodeSequence nodeSeq = (NodeSequence)iterator.next();
            exps.add(nodeSeq.nodes[1]);
        }
        return exps;
    }

    /**
     * f0 -> ExpressionSeq()
     * f1 -> ( ExpressionSeqsRest() )*
     */
    @Override
    public Node visit(ExpressionSeqs n) {
        NodeList<Expression> seq1 = (NodeList<Expression>) visitDispatch(n.f0);
        NodeList<NodeList<Expression>> restSeqs = (NodeList<NodeList<Expression>>) visitDispatch(n.f1);
        NodeList<NodeList<Expression>> result = new NodeList<NodeList<Expression>>();
        result.add(seq1);
        result.add(restSeqs);
        return result;
    }

    /**
     * f0 -> ","
     * f1 -> ExpressionSeq()
     */

    @Override
    public Node visit(ExpressionSeqsRest n) {
        return visitDispatch(n.f1);
    }

    /**
     * f0 -> "print("
     * f1 -> Expression()
     * f2 -> ")"
     */
    public Node visit(PrintSt n) {
        return new Print((Expression)visitDispatch(n.f1));
    }

    /**
     * f0 -> "println("
     * f1 -> Expression()
     * f2 -> ")"
     */
    public Node visit(PrintlnSt n) {
        return new Println((Expression)visitDispatch(n.f1));
    }



    private void setLoc(LocInfo locInfo, NodeToken token) {
        locInfo.lineNo = token.beginLine;
        locInfo.columnNo = token.beginColumn;
    }
    private void setLoc(LocInfo locInfo, LocInfo locInfo2) {
        locInfo.lineNo = locInfo2.lineNo;
        locInfo.columnNo = locInfo2.columnNo;
    }

}
    
    
//====================================================================================
//====================================================================================
