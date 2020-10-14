package frontend.plasmax10.ast.builder;

import frontend.plasmax10.ast.builder.nodes.core.Node;
import frontend.plasmax10.ast.builder.nodes.core.NodeList;
import frontend.plasmax10.ast.builder.nodes.core.optional.OptionalNode;
import frontend.plasmax10.ast.builder.nodes.core.optional.SomeNode;
import frontend.plasmax10.ast.builder.nodes.statement.caseparts.CaseGuard;
import frontend.plasmax10.ast.builder.nodes.statement.caseparts.DefaultGuard;
import frontend.plasmax10.ast.builder.nodes.statement.caseparts.SwitchGuard;
import ast.tree.declarations.*;
import ast.tree.expression.*;
import ast.tree.expression.consts.FirstPlace;
import ast.tree.expression.consts.IntegerSize;
import ast.tree.expression.consts.LastPlace;
import ast.tree.expression.consts.MaxPlaces;
import ast.tree.expression.literal.*;
import ast.tree.expression.op.math.*;
import ast.tree.expression.op.relational.*;
import ast.tree.statement.*;
import ast.tree.statement.caseparts.Case;
import ast.tree.statement.xtras.AddLocalOps;
import ast.tree.type.*;
import frontend.plasmax10.syntaxanalyser.syntaxtree.ArrayIndex;
import util.Pair;
import util.option.None;
import util.option.Option;
import util.option.Some;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class Xtras {

    /**
     * f0 -> MainClass()
     * f1 -> ProgramClass()
     * f2 -> ( TopLevelDeclaration() )*
     * f3 -> <EOF>
     */
    public ast.tree.Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.File file) {
        MainClass mainClass = (MainClass) visitDispatch(file.f0);
        ClassDecl programClass = (ClassDecl) visitDispatch(file.f1);
        NodeList<ClassDecl> classDecls = (NodeList<ClassDecl>) visitDispatch(file.f2);
        classDecls.add(programClass);
        ClassDecl[] classDeclArray = NodeList.classDeclArray(classDecls);
        return new File(mainClass, classDeclArray, protoMethods);
/*
        MainClass mainClass = (MainClass) visitDispatch(file.f0);
        ProgramClass programClass = (ProgramClass) visitDispatch(file.f1);
        NodeList<TopLevelDecl> nodeList = (NodeList<TopLevelDecl>) visitDispatch(file.f2);
        Iterator<TopLevelDecl> iterator = nodeList.iterator();
        NodeList<ClassDecl> classDecls = new NodeList<ClassDecl>();
        NodeList<ValueClassDecl> valueClassDecls = new NodeList<ValueClassDecl>();
        while (iterator.hasNext()) {
            TopLevelDecl topLevelDecl = iterator.next();
            if (topLevelDecl instanceof ClassDecl)
                classDecls.add((ClassDecl) topLevelDecl);
            else
                valueClassDecls.add((ValueClassDecl) topLevelDecl);
        }
        ClassDecl[] classDeclArray = NodeList.classDeclArray(classDecls);
        ValueClassDecl[] valueClassDeclArray = NodeList.valueDeclArray(valueClassDecls);
        return new File(mainClass, programClass, classDeclArray, valueClassDeclArray);
*/
    }

    /**
     * f0 -> ClassDeclaration()
     *       | ValueDeclaration()
     */
    public ast.tree.Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.TopLevelDeclaration n) {
        return visitDispatch(n.f0);
    }


    /**
     * f0 -> "class"
     * f1 -> Identifier()
     * f2 -> "{"
     * f3 -> ( InitializableConstantDeclaration() )*
     * f4 -> ( UpdatableFieldDeclaration() )*
     * f5 -> ( ConstructorDeclaration() )*
     * f6 -> "}"
     */
    public ast.tree.Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.ClassDeclaration n) {
        String name = ((ast.tree.expression.Id) visitDispatch(n.f1)).name;

        NodeList<FieldDecl> lateInitConstDeclNodeList = (NodeList<FieldDecl>) visitDispatch(n.f3);
        FieldDecl[] lateInitConstFieldDecls = NodeList.fieldDeclArray(lateInitConstDeclNodeList);

        NodeList<FieldDecl> fieldDeclNodeList = (NodeList<FieldDecl>) visitDispatch(n.f4);

        Iterator<FieldDecl> iterator = lateInitConstDeclNodeList.iterator();
        while (iterator.hasNext()) {
            FieldDecl fieldDecl = iterator.next();
            fieldDeclNodeList.add(fieldDecl);
        }
        FieldDecl[] fieldDecls = NodeList.fieldDeclArray(fieldDeclNodeList);

        NodeList<ConstructorDecl> constructorDeclNodeList = (NodeList<ConstructorDecl>) visitDispatch(n.f5);
        ConstructorDecl[] constructorDecls = NodeList.constructorDeclArray(constructorDeclNodeList);
        return new ClassDecl(name, fieldDecls, constructorDecls);
    }


    /**
     * f0 -> "value"
     * f1 -> Identifier()
     * f2 -> "{"
     * f3 -> ( InitializableConstantDeclaration() )*
     * f4 -> ( ConstructorDeclaration() )*
     * f5 -> "}"
     */
    public ast.tree.Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.ValueDeclaration n) {
        String name = ((ast.tree.expression.Id) visitDispatch(n.f1)).name;
        NodeList<FieldDecl> lateInitConstDeclNodeList = (NodeList<FieldDecl>) visitDispatch(n.f3);
        FieldDecl[] lateInitConstFieldDecls = NodeList.fieldDeclArray(lateInitConstDeclNodeList);

        NodeList<ConstructorDecl> constructorDeclNodeList = (NodeList<ConstructorDecl>) visitDispatch(n.f4);
        ConstructorDecl[] constructorDecls = NodeList.constructorDeclArray(constructorDeclNodeList);

        return new ClassDecl(true, name, lateInitConstFieldDecls, constructorDecls);
    }

    /**
     * f0 -> "class"
     * f1 -> Identifier()
     * f2 -> "{"
     * f3 -> ( ConstantDeclaration() )*
     * f4 -> ( MethodDeclaration() )*
     * f5 -> "}"
     */
    public ast.tree.Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.ProgramClass n) {
        String name = ((ast.tree.expression.Id) visitDispatch(n.f1)).name;
        NodeList<FieldDecl> constDeclList = (NodeList<FieldDecl>) visitDispatch(n.f3);
        FieldDecl[] constDecls = NodeList.fieldDeclArray(constDeclList);
        NodeList<MethodDecl> methodDeclList = (NodeList<MethodDecl>) visitDispatch(n.f4);
        MethodDecl[] methodDecls = NodeList.methodDeclArray(methodDeclList);
        return new ClassDecl(name, constDecls, methodDecls);
    }


    /**
     * f0 -> "public"
     * f1 -> "static"
     * f2 -> "final"
     * f3 -> Type()
     * f4 -> Identifier()
     * f5 -> "="
     * f6 -> MethodCall()
     * f7 -> ";"
     */
    public ast.tree.Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.ConstantDeclaration n) {
        NonVoidType type = (NonVoidType) visitDispatch(n.f3);
        String name = ((ast.tree.expression.Id) visitDispatch(n.f4)).name;
        ast.tree.expression.MethodCall methodCall =
                (ast.tree.expression.MethodCall) visitDispatch(n.f6);
        return new FieldDecl(true, true, type, name, methodCall);
    }



    /**
     * f0 -> "public"
     * f1 -> "final"
     * f2 -> Type()
     * f3 -> Identifier()
     * f4 -> ";"
     */
    public ast.tree.Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.InitializableConstantDeclaration n) {
        NonVoidType type = (NonVoidType) visitDispatch(n.f2);
        String name = ((ast.tree.expression.Id) visitDispatch(n.f3)).name;
        return new FieldDecl(true, type, name);
    }


    /**
     * f0 -> "public"
     * f1 -> Type()
     * f2 -> Identifier()
     * f3 -> ";"
     */
    public ast.tree.Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.UpdatableFieldDeclaration n) {
        NonVoidType type = (NonVoidType) visitDispatch(n.f1);
        String name = ((ast.tree.expression.Id) visitDispatch(n.f2)).name;
        return new FieldDecl(type, name);
    }
    /**
     * f0 -> "public"
     * f1 -> "static"
     * f2 -> ReturnType()
     * f3 -> Identifier()
     * f4 -> "("
     * f5 -> ( FormalParameterList() )?
     * f6 -> ")"
     * f7 -> Block()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.MethodDeclaration n) {
        Type type = (Type)visitDispatch(n.f2);
        String name = ((ast.tree.expression.Id) visitDispatch(n.f3)).name;


        OptionalNode optionalFormalParamList = (OptionalNode)visitDispatch(n.f5);
        FormalParam[] formalParams;
        if (optionalFormalParamList.isPresent()) {
            NodeList<FormalParam> formalParamList = ((SomeNode<NodeList<FormalParam>>)optionalFormalParamList).get();
            formalParams = NodeList.formalParamArray(formalParamList);
        } else
            formalParams = new FormalParam[0];


        Block block = (Block) visitDispatch(n.f7);
        return new MethodDecl(true, type, name, formalParams, block);
    }

    /**
     * f0 -> ","
     * f1 -> FinalFormalParameter()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.FormalParameterRest n) {
        return visitDispatch(n.f1);
    }

    /**
     * f0 -> NonArrayType()
     * f1 -> "["
     * f2 -> ":"
     * f3 -> RankEquation()
     * f4 -> "]"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.UpdatableArrayType n) {
        ScalarType scalarType = (ScalarType) visitDispatch(n.f0);
        return new ArrayType(scalarType);
    }

    /**
     * f0 -> NonArrayType()
     * f1 -> "value"
     * f2 -> "["
     * f3 -> ":"
     * f4 -> RankEquation()
     * f5 -> "]"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.ValueArrayType n) {
        ScalarType scalarType = (ScalarType) visitDispatch(n.f0);
        return new ArrayType(scalarType, true);
    }

    /**
     * f0 -> "rank==1"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.RankEquation n) {
        return null;
    }

    /**
     * f0 -> BooleanType()
     *       | ByteType()
     *       | ShortType()
     *       | IntegerType()
     *       | LongType()
     *       | DoubleType()
     *       | StringType()
     *       | PlaceType()
     *       | DistType()
     *       | RegionType()
     *       | PointType()
     *       | ClassNameType()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.NonArrayType n) {
        return visitDispatch(n.f0);
    }


    /**
     * f0 -> "boolean"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.BooleanType n) {
        return BooleanType.instance();
    }

    /**
     * f0 -> "byte"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.ByteType n) {
        return ByteType.instance();
    }

    /**
     * f0 -> "short"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.ShortType n) {
        return ShortType.instance();
    }

    /**
     * f0 -> "int"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.IntegerType n) {
        return IntType.instance();
    }

    /**
     * f0 -> "long"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.LongType n) {
        return LongType.instance();
    }

    /**
     * f0 -> "double"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.DoubleType n) {
        return DoubleType.instance();
    }

    /**
     * f0 -> "String"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.StringType n) {
        return StringType.instance();
    }

    /**
     * f0 -> "place"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.PlaceType n) {
        return PlaceType.instance();
    }

    /**
     * f0 -> "dist"
     * f1 -> "("
     * f2 -> ":"
     * f3 -> RankEquation()
     * f4 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.DistType n) {
        return new DistType();
    }

    /**
     * f0 -> "region"
     * f1 -> "("
     * f2 -> ":"
     * f3 -> RankEquation()
     * f4 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.RegionType n) {
        return new RegionType();
    }



    /**
     * f0 -> "point"
     * f1 -> "("
     * f2 -> ":"
     * f3 -> RankEquation()
     * f4 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.PointType n) {
        return new PointType();
    }
    /**
     * f0 -> AddLocalOpsStatement()
     *       | Assignment()
     *       | ArrayAssignment()
     *       | FieldAssignment()
     *       | ThisFieldAssignment()
     *       | AsyncStatement()
     *       | Block()
     *       | BreakStatement()
     *       | ContinueStatement()
     *       | DoStatement()
     *       | FinishStatement()
     *       | IfStatement()
     *       | LoopStatement()
     *       | MethodCallStatement()
     *       | MethodCallStatementInConstructor()
     *       | PrintlnStatement()
     *       | PrintStatement()
     *       | PrintErrorStatement()
     *       | ReturnStatement()
     *       | SwitchStatement()
     *       | ThrowStatement()
     *       | WhileStatement()
     *       | FinalVariableDeclaration()
     *       | UpdatableVariableDeclaration()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.Statement n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "="
     * f2 -> Expression()
     * f3 -> ";"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.Assignment n) {
        String leftValue = ((ast.tree.expression.Id) visitDispatch(n.f0)).name;
        Expression expression = (Expression) visitDispatch(n.f2);
        return new Assignment(leftValue, expression);
    }

    /**
     * f0 -> "x10.lang.perf.addLocalOps"
     * f1 -> "("
     * f2 -> Identifier()
     * f3 -> ")"
     * f4 -> ";"
     */

    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.AddLocalOpsStatement n) {
        String param = ((ast.tree.expression.Id) visitDispatch(n.f2)).name;
        return new AddLocalOps(param);
    }

    /**
     * f0 -> "async"
     * f1 -> "("
     * f2 -> Identifier()
     * f3 -> ")"
     * f4 -> Block()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.AsyncStatement n) {
        String placeName = ((ast.tree.expression.Id) visitDispatch(n.f2)).name;
        Block block = (Block) visitDispatch(n.f4);
        return new Async(placeName, block);
    }

    /**
     * f0 -> "{"
     * f1 -> ( Statement() )*
     * f2 -> "}"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.Block n) {
        NodeList<Statement> statementList = (NodeList<Statement>)visitDispatch(n.f1);
        Statement[] statements = NodeList.statementArray(statementList);
//        for (Statement statement : blockStatements) {
//            if (statement == null)
//                System.out.println("null in Block prep.");
//        }
        return new Block(statements);
    }

    /**
     * f0 -> "final"
     * f1 -> Type()
     * f2 -> Identifier()
     * f3 -> "="
     * f4 -> Expression()
     * f5 -> ";"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.FinalVariableDeclaration n) {
        NonVoidType type = (NonVoidType)visitDispatch(n.f1);
        String name = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        Expression expression = (Expression)visitDispatch(n.f4);
//        if (expression == null) {
//            System.out.println(n.f4);
//            System.out.println(n.f3.beginLine);
//        }
        return new VarDecl(true, type, name, expression);
    }
    /**
     * f0 -> Type()
     * f1 -> Identifier()
     * f2 -> "="
     * f3 -> Expression()
     * f4 -> ";"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.UpdatableVariableDeclaration n) {
        NonVoidType type = (NonVoidType)visitDispatch(n.f0);
        String name = ((ast.tree.expression.Id)visitDispatch(n.f1)).name;
        Expression expression = (Expression)visitDispatch(n.f3);
//        if (expression == null) {
//            System.out.println(n.f4);
//            System.out.println(n.f4.beginLine);
//        }
        return new VarDecl(type, name, expression);
    }

    /**
     * f0 -> "do"
     * f1 -> Block()
     * f2 -> "while"
     * f3 -> "("
     * f4 -> Identifier()
     * f5 -> ")"
     * f6 -> ";"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.DoStatement n) {
        Block block = (Block) visitDispatch(n.f1);
        String conditionName = ((ast.tree.expression.Id) visitDispatch(n.f4)).name;
        return new DoWhile(block, conditionName);
    }

    /**
     * f0 -> "finish"
     * f1 -> Block()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.FinishStatement n) {
        Block block = (Block) visitDispatch(n.f1);
        return new Finish(block);
    }


    /**
     * f0 -> "if"
     * f1 -> "("
     * f2 -> Identifier()
     * f3 -> ")"
     * f4 -> Block()
     * f5 -> [ ElseClause() ]
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.IfStatement n) {

        String conditionName = ((ast.tree.expression.Id) visitDispatch(n.f2)).name;
        Block ifBlock = (Block) visitDispatch(n.f4);

        OptionalNode optionalNode = (OptionalNode) visitDispatch(n.f5);
        if (optionalNode.isPresent()) {
            SomeNode<Block> someNode = (SomeNode<Block>) optionalNode;
            Block elseBlock = someNode.get();
            return new If(conditionName, ifBlock, elseBlock);
        }
        else
            return new If(conditionName, ifBlock);
    }


    /**
     * f0 -> Identifier()
     * f1 -> "["
     * f2 -> IdentifierList()
     * f3 -> "]"
     * f4 -> "="
     * f5 -> "("
     * f6 -> Identifier()
     * f7 -> ")"
     * f8 -> ";"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.ArrayAssignment n) {
        String arrayName = ((ast.tree.expression.Id) visitDispatch(n.f0)).name;
        NodeList<ast.tree.expression.Id> indicesNodeList = (NodeList<ast.tree.expression.Id>) visitDispatch(n.f2);
        String[] indexNames= new String[indicesNodeList.size()];
        Iterator<ast.tree.expression.Id> iterator = indicesNodeList.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            ast.tree.expression.Id id = iterator.next();
            indexNames[i] = id.name;
            i++;
        }
        String rightValueName = ((ast.tree.expression.Id) visitDispatch(n.f6)).name;
        return new ArrayAssignment(arrayName, indexNames, rightValueName);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "."
     * f2 -> Identifier()
     * f3 -> "="
     * f4 -> "("
     * f5 -> Identifier()
     * f6 -> ")"
     * f7 -> ";"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.FieldAssignment n) {
        String receiver = ((ast.tree.expression.Id) visitDispatch(n.f0)).name;
        String fieldName = ((ast.tree.expression.Id) visitDispatch(n.f2)).name;
        String rightValueName = ((ast.tree.expression.Id) visitDispatch(n.f5)).name;
        return new FieldAssignment(receiver, fieldName, rightValueName);
    }

    /**
     * f0 -> "this"
     * f1 -> "."
     * f2 -> Identifier()
     * f3 -> "="
     * f4 -> "("
     * f5 -> Identifier()
     * f6 -> ")"
     * f7 -> ";"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.ThisFieldAssignment n) {
        String fieldName = ((ast.tree.expression.Id) visitDispatch(n.f2)).name;
        String rightValueName = ((ast.tree.expression.Id) visitDispatch(n.f5)).name;
        return new ThisFieldAssignment(fieldName, rightValueName);
    }


    /**
     * f0 -> "for"
     * f1 -> "("
     * f2 -> PointType()
     * f3 -> ExplodedSpecification()
     * f4 -> ":"
     * f5 -> Identifier()
     * f6 -> ")"
     * f7 -> Block()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.LoopStatement n) {
        PointType pointType = (PointType) visitDispatch(n.f2);
        For.Formal formal = (For.Formal) visitDispatch(n.f3);
        String rangeName = ((ast.tree.expression.Id) visitDispatch(n.f5)).name;
        Block block = (Block) visitDispatch(n.f7);
        return new For(pointType, formal, rangeName, block);
    }



    /**
     * f0 -> PointName()
     *       | Coordinates()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.ExplodedSpecification n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.PointName n) {
        String pointName = ((ast.tree.expression.Id)visitDispatch(n.f0)).name;
        return new For.Id(pointName);
    }

    /**
     * f0 -> "["
     * f1 -> Identifier()
     * f2 -> "]"
     */
    // Similar to pattern matching.
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.Coordinates n) {
        String pointName = ((ast.tree.expression.Id)visitDispatch(n.f1)).name;
        return new For.Coordinates(pointName);
    }

    /**
     * f0 -> Identifier()
     * f1 -> ( IdentifierRest() )*
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.IdentifierList n) {
        ast.tree.expression.Id id0 = (ast.tree.expression.Id)visitDispatch(n.f0);
        NodeList<ast.tree.expression.Id> idNodeList = (NodeList<Id>) visitDispatch(n.f1);
        NodeList<ast.tree.expression.Id> allIdList = new NodeList<ast.tree.expression.Id>();
        allIdList.add(id0);
        Iterator<ast.tree.expression.Id> iterator = idNodeList.iterator();
        while (iterator.hasNext()) {
            ast.tree.expression.Id id = iterator.next();
            allIdList.add(id);
        }
        return allIdList;
    }

    /**
     * f0 -> ","
     * f1 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.IdentifierRest n) {
        return visitDispatch(n.f1);
    }

    /**
     * f0 -> MethodCall()
     * f1 -> ";"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.MethodCallStatement n) {
        return new ExpressionStatement(
                (ast.tree.expression.MethodCall)
                visitDispatch(n.f0)
        );
    }

    /**
     * f0 -> MethodCallInConstructor()
     * f1 -> ";"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.MethodCallStatementInConstructor n) {
        return new ExpressionStatement(
                (ast.tree.expression.MethodCall)
                visitDispatch(n.f0)
        );
    }

    /**
     * f0 -> "System.out.println"
     * f1 -> "("
     * f2 -> Identifier()
     * f3 -> ")"
     * f4 -> ";"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.PrintlnStatement n) {
        String argName = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        return new Println(argName);
    }

    /**
     * f0 -> "System.out.print"
     * f1 -> "("
     * f2 -> Identifier()
     * f3 -> ")"
     * f4 -> ";"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.PrintStatement n) {
        String argName = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        return new Print(argName);
    }

    /**
     * f0 -> "System.err.println"
     * f1 -> "("
     * f2 -> Identifier()
     * f3 -> ")"
     * f4 -> ";"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.PrintErrorStatement n) {
        String argName = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        return new PrintError(argName);
    }

    /**
     * f0 -> "return"
     * f1 -> [ Identifier() ]
     * f2 -> ";"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.ReturnStatement n) {
        OptionalNode optionalNode = (OptionalNode)visitDispatch(n.f1);
        if (optionalNode.isPresent()) {
            SomeNode<ast.tree.expression.Id> someNode = (SomeNode<ast.tree.expression.Id>) optionalNode;
            String argName = someNode.get().name;
            return new ValueReturn(argName);
        }
        else
            return VoidReturn.instance();
    }

    /**
     * f0 -> "switch"
     * f1 -> "("
     * f2 -> Identifier()
     * f3 -> ")"
     * f4 -> "{"
     * f5 -> ( SwitchEntry() )*
     * f6 -> "}"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.SwitchStatement n) {
        String varName = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;

        NodeList<frontend.plasmax10.ast.builder.nodes.statement.caseparts.SwitchEntry> switchEntryList = (NodeList<frontend.plasmax10.ast.builder.nodes.statement.caseparts.SwitchEntry>) visitDispatch(n.f5);
        frontend.plasmax10.ast.builder.nodes.statement.caseparts.SwitchEntry probableDefault = switchEntryList.elementAt(switchEntryList.size() - 1);
        Option<Block> defaultBlock;
        Case[] cases;
        if (probableDefault.switchGuard instanceof DefaultGuard) {
            cases = new Case[switchEntryList.size() - 1];
            defaultBlock = new Some<Block>(probableDefault.block);
        } else {
            cases = new Case[switchEntryList.size()];
            defaultBlock = None.instance();
        }
        Iterator<frontend.plasmax10.ast.builder.nodes.statement.caseparts.SwitchEntry> iterator = switchEntryList.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            frontend.plasmax10.ast.builder.nodes.statement.caseparts.SwitchEntry switchEntry = iterator.next();
            CaseGuard caseLabel = (CaseGuard) switchEntry.switchGuard;
            cases[i] = new Case(caseLabel.labelName, switchEntry.block);
            i++;
            if (i == cases.length)
                break;
        }
        return new Switch(varName, cases, defaultBlock);
    }

    /**
     * f0 -> SwitchLabel()
     * f1 -> ":"
     * f2 -> Block()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.SwitchEntry n) {
        SwitchGuard switchGuard = (SwitchGuard)visitDispatch(n.f0);
        Block block = (Block)visitDispatch(n.f2);
        return new frontend.plasmax10.ast.builder.nodes.statement.caseparts.SwitchEntry(switchGuard, block);
    }

    /**
     * f0 -> Case()
     *       | DefaultLabel()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.SwitchLabel n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> "case"
     * f1 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.Case n) {
        String labelName = ((ast.tree.expression.Id)visitDispatch(n.f1)).name;
        return new CaseGuard(labelName);
    }

    /**
     * f0 -> "default"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.Default n) {
        return DefaultGuard.instance();
    }

    /**
     * f0 -> "throw"
     * f1 -> "new"
     * f2 -> "RuntimeException"
     * f3 -> "("
     * f4 -> Identifier()
     * f5 -> ")"
     * f6 -> ";"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.ThrowStatement n) {
        String argName = ((ast.tree.expression.Id)visitDispatch(n.f4)).name;
        return new Throw(argName);
    }

    /**
     * f0 -> "while"
     * f1 -> "("
     * f2 -> Identifier()
     * f3 -> ")"
     * f4 -> Block()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.WhileStatement n) {
        String conditionName = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        Block block = (Block)visitDispatch(n.f4);
        return new While(conditionName, block);
    }



    /**
     * f0 -> ExpressionInParentheses()
     *       | ConditionalOrExpression()
     *       | DistributionExpression()
     *       | DistributionUnionExpression()
     *       | ConditionalAndExpression()
     *       | InclusiveOrExpression()
     *       | ExclusiveOrExpression()
     *       | AndExpression()
     *       | EqualsExpression()
     *       | NonEqualsExpression()
     *       | LessThanExpression()
     *       | GreaterThanExpression()
     *       | LessThanEqualExpression()
     *       | GreaterThanEqualExpression()
     *       | ShiftLeftExpression()
     *       | ShiftRightExpression()
     *       | ShiftRightUnsignedExpression()
     *       | PlusExpression()
     *       | MinusExpression()
     *       | TimesExpression()
     *       | DivideExpression()
     *       | ModulusExpression()
     *       | RegionConstant()
     *       | SinExpression()
     *       | CosExpression()
     *       | PowExpression()
     *       | ExpExpression()
     *       | SqrtExpression()
     *       | AbsExpression()
     *       | MinExpression()
     *       | MaxExpression()
     *       | LogExpression()
     *       | ComplimentExpression()
     *       | NotExpression()
     *       | CoercionToIntExpression()
     *       | CoercionToDoubleExpression()
     *       | CoercionToLongExpression()
     *       | CoercionToShortExpression()
     *       | CoercionToByteExpression()
     *       | TypeAnnotatedExpression()
     *       | Place()
     *       | CurrentTime()
     *       | ArrayAccess()
     *       | MethodCall()
     *       | MethodCallInConstructor()
     *       | DotId()
     *       | DotIdentifierDotGet()
     *       | DotNext()
     *       | DotPrev()
     *       | DotIsFirst()
     *       | DotIsLast()
     *       | DotIdentifierDotRegionDotSize()
     *       | DotIdentifier()
     *       | NewObject()
     *       | NewValueArray()
     *       | NewUpdatableArray()
     *       | IntegerLiteral()
     *       | LongLiteral()
     *       | HexLiteral()
     *       | FloatingPointLiteral()
     *       | StringLiteral()
     *       | True()
     *       | False()
     *       | HereLiteral()
     *       | PlaceFirstPlace()
     *       | PlaceLastPlace()
     *       | PlaceMaxPlaces()
     *       | JavaIntegerSize()
     *       | Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.Expression n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> "("
     * f1 -> Expression()
     * f2 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.ExpressionInParentheses n) {
        Node node = visitDispatch(n.f1);
//        if (node == null) {
//            System.out.println("null in ( )");
            //n.f1.
//        }
        return node;
    }


    /**
     * f0 -> Identifier()
     * f1 -> "||"
     * f2 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.ConditionalOrExpression n) {
        String operand1name = ((ast.tree.expression.Id)visitDispatch(n.f0)).name;
        String operand2name = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        return new Or(operand1name, operand2name);

    }

    /**
     * f0 -> Identifier()
     * f1 -> "&&"
     * f2 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.ConditionalAndExpression n) {
        String operand1name = ((ast.tree.expression.Id)visitDispatch(n.f0)).name;
        String operand2name = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        return new And(operand1name, operand2name);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "|"
     * f2 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.InclusiveOrExpression n) {
        String operand1name = ((ast.tree.expression.Id)visitDispatch(n.f0)).name;
        String operand2name = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        return new BitOr(operand1name, operand2name);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "^"
     * f2 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.ExclusiveOrExpression n) {
        String operand1name = ((ast.tree.expression.Id)visitDispatch(n.f0)).name;
        String operand2name = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        return new BitXOr(operand1name, operand2name);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "&"
     * f2 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.AndExpression n) {
        String operand1name = ((ast.tree.expression.Id)visitDispatch(n.f0)).name;
        String operand2name = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        return new BitAnd(operand1name, operand2name);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "=="
     * f2 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.EqualsExpression n) {
        String operand1name = ((ast.tree.expression.Id)visitDispatch(n.f0)).name;
        String operand2name = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        return new Equality(operand1name, operand2name);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "!="
     * f2 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.NonEqualsExpression n) {
        String operand1name = ((ast.tree.expression.Id)visitDispatch(n.f0)).name;
        String operand2name = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        return new NotEquality(operand1name, operand2name);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "<"
     * f2 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.LessThanExpression n) {
        String operand1name = ((ast.tree.expression.Id)visitDispatch(n.f0)).name;
        String operand2name = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        return new LessThan(operand1name, operand2name);
    }

    /**
     * f0 -> Identifier()
     * f1 -> ">"
     * f2 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.GreaterThanExpression n) {
        String operand1name = ((ast.tree.expression.Id)visitDispatch(n.f0)).name;
        String operand2name = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        return new GreaterThan(operand1name, operand2name);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "<="
     * f2 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.LessThanEqualExpression n) {
        String operand1name = ((ast.tree.expression.Id)visitDispatch(n.f0)).name;
        String operand2name = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        return new LessThanEqual(operand1name, operand2name);
    }

    /**
     * f0 -> Identifier()
     * f1 -> ">="
     * f2 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.GreaterThanEqualExpression n) {
        String operand1name = ((ast.tree.expression.Id)visitDispatch(n.f0)).name;
        String operand2name = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        return new GreaterThanEqual(operand1name, operand2name);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "<<"
     * f2 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.ShiftLeftExpression n) {
        String operand1name = ((ast.tree.expression.Id)visitDispatch(n.f0)).name;
        String operand2name = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        return new ShiftLeft(operand1name, operand2name);
    }

    /**
     * f0 -> Identifier()
     * f1 -> ">>"
     * f2 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.ShiftRightExpression n) {
        String operand1name = ((ast.tree.expression.Id)visitDispatch(n.f0)).name;
        String operand2name = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        return new ShiftRight(operand1name, operand2name);
    }

    /**
     * f0 -> Identifier()
     * f1 -> ">>>"
     * f2 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.ShiftRightUnsignedExpression n) {
        String operand1name = ((ast.tree.expression.Id)visitDispatch(n.f0)).name;
        String operand2name = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        return new UnsignedShiftRight(operand1name, operand2name);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "+"
     * f2 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.PlusExpression n) {
        String operand1name = ((ast.tree.expression.Id)visitDispatch(n.f0)).name;
        String operand2name = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        return new Plus(operand1name, operand2name);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "-"
     * f2 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.MinusExpression n) {
        String operand1name = ((ast.tree.expression.Id)visitDispatch(n.f0)).name;
        String operand2name = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        return new Minus(operand1name, operand2name);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "*"
     * f2 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.TimesExpression n) {
        String operand1name = ((ast.tree.expression.Id)visitDispatch(n.f0)).name;
        String operand2name = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        return new Times(operand1name, operand2name);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "/"
     * f2 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.DivideExpression n) {
        String operand1name = ((ast.tree.expression.Id)visitDispatch(n.f0)).name;
        String operand2name = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        return new Divide(operand1name, operand2name);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "%"
     * f2 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.ModulusExpression n) {
        String operand1name = ((ast.tree.expression.Id)visitDispatch(n.f0)).name;
        String operand2name = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        return new Divide(operand1name, operand2name);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "->"
     * f2 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.DistributionExpression n) {
        String regionName = ((ast.tree.expression.Id)visitDispatch(n.f0)).name;
        String placeName = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        return new Distribution(regionName, placeName);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "||"
     * f2 -> "("
     * f3 -> "["
     * f4 -> Identifier()
     * f5 -> ":"
     * f6 -> Identifier()
     * f7 -> "]"
     * f8 -> "->"
     * f9 -> Identifier()
     * f10 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.DistributionUnionExpression n) {
        String operand1Name = ((ast.tree.expression.Id)visitDispatch(n.f0)).name;
        String operand2rangeStartName = ((ast.tree.expression.Id)visitDispatch(n.f4)).name;
        String operand2rangeEndName = ((ast.tree.expression.Id)visitDispatch(n.f6)).name;
        String operand2placeName = ((ast.tree.expression.Id)visitDispatch(n.f9)).name;
        return new DistributionUnion(operand1Name,
                operand2rangeStartName, operand2rangeEndName, operand2placeName);
    }

    /**
     * f0 -> "["
     * f1 -> "0 :"
     * f2 -> Identifier()
     * f3 -> "]"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.RegionConstant n) {
        String endName = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        return new RegionConstructor(endName);
    }

    /**
     * f0 -> "Math"
     * f1 -> "."
     * f2 -> "sin"
     * f3 -> "("
     * f4 -> Identifier()
     * f5 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.SinExpression n) {
        String argName = ((ast.tree.expression.Id)visitDispatch(n.f4)).name;
        return new ast.tree.expression.MethodCall(
                new ast.tree.expression.Id("Math"), "sin", new String[]{argName});
    }

    /**
     * f0 -> "Math"
     * f1 -> "."
     * f2 -> "cos"
     * f3 -> "("
     * f4 -> Identifier()
     * f5 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.CosExpression n) {
        String argName = ((ast.tree.expression.Id)visitDispatch(n.f4)).name;
        return new ast.tree.expression.MethodCall(
                new ast.tree.expression.Id("Math"), "cos", new String[]{argName});
    }

    /**
     * f0 -> "Math"
     * f1 -> "."
     * f2 -> "pow"
     * f3 -> "("
     * f4 -> Identifier()
     * f5 -> ","
     * f6 -> Identifier()
     * f7 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.PowExpression n) {
        String operand1name = ((ast.tree.expression.Id)visitDispatch(n.f4)).name;
        String operand2name = ((ast.tree.expression.Id)visitDispatch(n.f6)).name;
        return new ast.tree.expression.MethodCall(
                new ast.tree.expression.Id("Math"), "pow", new String[]{operand1name, operand2name});
    }

    /**
     * f0 -> "Math"
     * f1 -> "."
     * f2 -> "exp"
     * f3 -> "("
     * f4 -> Identifier()
     * f5 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.ExpExpression n) {
        String operandName = ((ast.tree.expression.Id)visitDispatch(n.f4)).name;
        return new ast.tree.expression.MethodCall(
                new ast.tree.expression.Id("Math"), "exp", new String[]{operandName});
    }

    /**
     * f0 -> "Math"
     * f1 -> "."
     * f2 -> "sqrt"
     * f3 -> "("
     * f4 -> Identifier()
     * f5 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.SqrtExpression n) {
        String operandName = ((ast.tree.expression.Id)visitDispatch(n.f4)).name;
        return new ast.tree.expression.MethodCall(
                new ast.tree.expression.Id("Math"), "sqrt", new String[]{operandName});
    }

    /**
     * f0 -> "Math"
     * f1 -> "."
     * f2 -> "abs"
     * f3 -> "("
     * f4 -> Identifier()
     * f5 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.AbsExpression n) {
        String operandName = ((ast.tree.expression.Id)visitDispatch(n.f4)).name;
        return new ast.tree.expression.MethodCall(
                new ast.tree.expression.Id("Math"), "abs", new String[]{operandName});
    }

    /**
     * f0 -> "Math"
     * f1 -> "."
     * f2 -> "min"
     * f3 -> "("
     * f4 -> Identifier()
     * f5 -> ","
     * f6 -> Identifier()
     * f7 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.MinExpression n) {
        String operand1name = ((ast.tree.expression.Id)visitDispatch(n.f4)).name;
        String operand2name = ((ast.tree.expression.Id)visitDispatch(n.f6)).name;
        return new ast.tree.expression.MethodCall(
                new ast.tree.expression.Id("Math"), "min", new String[]{operand1name, operand2name});
    }

    /**
     * f0 -> "Math"
     * f1 -> "."
     * f2 -> "max"
     * f3 -> "("
     * f4 -> Identifier()
     * f5 -> ","
     * f6 -> Identifier()
     * f7 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.MaxExpression n) {
        String operand1name = ((ast.tree.expression.Id)visitDispatch(n.f4)).name;
        String operand2name = ((ast.tree.expression.Id)visitDispatch(n.f6)).name;
        return new ast.tree.expression.MethodCall(
                new ast.tree.expression.Id("Math"), "max", new String[]{operand1name, operand2name});
    }

    /**
     * f0 -> "Math"
     * f1 -> "."
     * f2 -> "log"
     * f3 -> "("
     * f4 -> Identifier()
     * f5 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.LogExpression n) {
        String operandName = ((ast.tree.expression.Id)visitDispatch(n.f4)).name;
        return new ast.tree.expression.MethodCall(
                new ast.tree.expression.Id("Math"), "log", new String[]{operandName});
    }

    /**
     * f0 -> "~"
     * f1 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.ComplimentExpression n) {
        String operandName = ((ast.tree.expression.Id)visitDispatch(n.f1)).name;
        return new Complement(operandName);
    }

    /**
     * f0 -> "!"
     * f1 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.NotExpression n) {
        String operandName = ((ast.tree.expression.Id)visitDispatch(n.f1)).name;
        return new Not(operandName);
    }

    /**
     * f0 -> "("
     * f1 -> "int"
     * f2 -> ")"
     * f3 -> Expression()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.CoercionToIntExpression n) {
        Expression operand = (Expression)visitDispatch(n.f3);
        return new Coercion(IntType.instance(), operand);
    }

    /**
     * f0 -> "("
     * f1 -> "double"
     * f2 -> ")"
     * f3 -> Expression()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.CoercionToDoubleExpression n) {
        Expression operand = (Expression)visitDispatch(n.f3);
        return new Coercion(DoubleType.instance(), operand);
    }

    /**
     * f0 -> "("
     * f1 -> "long"
     * f2 -> ")"
     * f3 -> Expression()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.CoercionToLongExpression n) {
        Expression operand = (Expression)visitDispatch(n.f3);
        return new Coercion(LongType.instance(), operand);
    }

    /**
     * f0 -> "("
     * f1 -> "short"
     * f2 -> ")"
     * f3 -> Expression()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.CoercionToShortExpression n) {
        Expression operand = (Expression)visitDispatch(n.f3);
        return new Coercion(ShortType.instance(), operand);
    }

    /**
     * f0 -> "("
     * f1 -> "byte"
     * f2 -> ")"
     * f3 -> Expression()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.CoercionToByteExpression n) {
        Expression operand = (Expression)visitDispatch(n.f3);
        return new Coercion(ByteType.instance(), operand);
    }

    /**
     * f0 -> "("
     * f1 -> TypeAnnotation()
     * f2 -> ")"
     * f3 -> "("
     * f4 -> Expression()
     * f5 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.TypeAnnotatedExpression n) {
        NonVoidType type = (NonVoidType)visitDispatch(n.f1);
        Expression operand = (Expression)visitDispatch(n.f4);
        return new Coercion(type, operand);
    }

    /**
     * f0 -> UpdatableArrayType()
     *       | ValueArrayType()
     *       | DistType()
     *       | RegionType()
     *       | PointType()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.TypeAnnotation n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> "System.currentTimeMillis"
     * f1 -> "("
     * f2 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.CurrentTime n) {
//        System.out.println("CurrentTime");
        return CurrentTime.instance();
    }


    /**
     * f0 -> Identifier()
     * f1 -> "["
     * f2 -> ArrayIndex()
     * f3 -> "]"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.ArrayAccess n) {
        String arrayName = ((ast.tree.expression.Id)visitDispatch(n.f0)).name;
        //String indexName = ((Id)visitDispatch(n.f2)).name;
        Expression index = (Expression)visitDispatch(n.f2);
        return new ArrayAccess(arrayName, index);
    }

    /**
     * f0 -> Identifier()
     *       | IntegerLiteral()
     */
    public Node visit(ArrayIndex n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "."
     * f2 -> Identifier()
     * f3 -> "("
     * f4 -> ( IdentifierList() )?
     * f5 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.MethodCall n) {
        Expression receiverName = (ast.tree.expression.Id)visitDispatch(n.f0);
        String methodName = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        NodeList<ast.tree.expression.Id> argumentIdList;
        OptionalNode optionalArgumentIdList = (OptionalNode)visitDispatch(n.f4);
        String[] argumentNames;
        if (optionalArgumentIdList.isPresent()) {
            argumentIdList = ((SomeNode<NodeList<ast.tree.expression.Id>>)optionalArgumentIdList).get();
            argumentNames = new String[argumentIdList.size()];
            Iterator<ast.tree.expression.Id> iterator = argumentIdList.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                ast.tree.expression.Id id = iterator.next();
                argumentNames[i] = id.name;
                i++;
            }
        } else
            argumentNames = new String[0];

        return new ast.tree.expression.MethodCall(
                receiverName, methodName, argumentNames);
    }

    Set protoMethods = new HashSet<Pair<String, String>>();

/*
    public Set getProtoMethods() {
        return protoMethods;
    }
*/

    /**
     * f0 -> Identifier()
     * f1 -> "."
     * f2 -> Identifier()
     * f3 -> "("
     * f4 -> "this"
     * f5 -> ( IdentifierRest() )*
     * f6 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.MethodCallInConstructor n) {
        ast.tree.expression.Id receiverId = (ast.tree.expression.Id)visitDispatch(n.f0);
        Expression receiverName = receiverId;
        String methodName = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        NodeList<ast.tree.expression.Id> argumentIdList = (NodeList<ast.tree.expression.Id>)visitDispatch(n.f5);
        String[] argumentNames = new String[argumentIdList.size() + 1];
        argumentNames[0] = "this";
        Iterator<ast.tree.expression.Id> iterator = argumentIdList.iterator();
        int i = 1;
        while (iterator.hasNext()) {
            ast.tree.expression.Id id = iterator.next();
            argumentNames[i] = id.name;
            i++;
        }
        protoMethods.add(new Pair<String, String>(receiverId.name, methodName));
        return new ast.tree.expression.MethodCall(
                receiverName, methodName, argumentNames);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "."
     * f2 -> "id"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.DotId n) {
        Expression objectName = (ast.tree.expression.Id)visitDispatch(n.f0);
        //String fieldName = ((Id)visitDispatch(n.f2)).name;
        return new FieldSelection(objectName, "id");
    }

    /**
     * f0 -> Identifier()
     * f1 -> "."
     * f2 -> Identifier()
     * f3 -> "."
     * f4 -> "get"
     * f5 -> "("
     * f6 -> IdentifierList()
     * f7 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.DotIdentifierDotGet n) {
        String objectName = ((ast.tree.expression.Id)visitDispatch(n.f0)).name;
        String fieldName = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        Expression receiver = new FieldSelection(new ast.tree.expression.Id(objectName), fieldName);

        //String methodName = "get";
        NodeList<ast.tree.expression.Id> argumentIdList = (NodeList<ast.tree.expression.Id>)visitDispatch(n.f6);

        String[] indexNames = new String[argumentIdList.size()];

        Iterator<ast.tree.expression.Id> iterator = argumentIdList.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            ast.tree.expression.Id id = iterator.next();
            indexNames[i] = id.name;
            i++;
        }
        For.Coordinates coordinates = new For.Coordinates(indexNames);

        return new DistAccess(receiver, coordinates);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "."
     * f2 -> "next"
     * f3 -> "("
     * f4 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.DotNext n) {
        Expression receiver = (ast.tree.expression.Id)visitDispatch(n.f0);
        String methodName = "next";
        return new ast.tree.expression.MethodCall(
                receiver, methodName, new String[0]);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "."
     * f2 -> "prev"
     * f3 -> "("
     * f4 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.DotPrev n) {
        Expression receiver = (ast.tree.expression.Id)visitDispatch(n.f0);
        String methodName = "prev";
        return new ast.tree.expression.MethodCall(
                receiver, methodName, new String[0]);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "."
     * f2 -> "isFirst"
     * f3 -> "("
     * f4 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.DotIsFirst n) {
        Expression receiver = (ast.tree.expression.Id)visitDispatch(n.f0);
        String methodName = "isFirst";
        return new ast.tree.expression.MethodCall(
                receiver, methodName, new String[0]);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "."
     * f2 -> "isLast"
     * f3 -> "("
     * f4 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.DotIsLast n) {
        Expression receiver = (ast.tree.expression.Id)visitDispatch(n.f0);
        String methodName = "isLast";
        return new ast.tree.expression.MethodCall(
                receiver, methodName, new String[0]);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "."
     * f2 -> Identifier()
     * f3 -> "."
     * f4 -> "region"
     * f5 -> "."
     * f6 -> "size"
     * f7 -> "("
     * f8 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.DotIdentifierDotRegionDotSize n) {
        Expression objectName = (ast.tree.expression.Id)visitDispatch(n.f0);
        String fieldName = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        Expression array = new FieldSelection(objectName, fieldName);
        Expression region = new RegionSelection(array);
        return new ast.tree.expression.MethodCall(
                region, "size", new String[0]);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "."
     * f2 -> Identifier()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.DotIdentifier n) {
        Expression objectName = (ast.tree.expression.Id)visitDispatch(n.f0);
        String fieldName = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;

        return new FieldSelection(objectName, fieldName);
    }

    /**
     * f0 -> "new"
     * f1 -> Identifier()
     * f2 -> "("
     * f3 -> [ IdentifierList() ]
     * f4 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.NewObject n) {
        String className = ((ast.tree.expression.Id)visitDispatch(n.f1)).name;

        NodeList<ast.tree.expression.Id> argumentIdList;
        OptionalNode optionalArgumentIdList = (OptionalNode)visitDispatch(n.f3);
        String[] argumentNames;
        if (optionalArgumentIdList.isPresent()) {
            argumentIdList = ((SomeNode<NodeList<ast.tree.expression.Id>>)optionalArgumentIdList).get();
            argumentNames = new String[argumentIdList.size()];
            Iterator<ast.tree.expression.Id> iterator = argumentIdList.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                ast.tree.expression.Id id = iterator.next();
                argumentNames[i] = id.name;
                i++;
            }
        } else
            argumentNames = new String[0];

        return new New(className, argumentNames);
    }

    /**
     * f0 -> "new"
     * f1 -> NonArrayType()
     * f2 -> "value"
     * f3 -> "["
     * f4 -> Identifier()
     * f5 -> "]"
     * f6 -> ArrayInitializer()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.NewValueArray n) {
        ScalarType elementType = (ScalarType) visitDispatch(n.f1);
        String regionName = ((ast.tree.expression.Id)visitDispatch(n.f4)).name;
        NewArray.ArrayInit arrayInit = (NewArray.ArrayInit) visitDispatch(n.f6);
        return new NewArray(true, elementType, regionName, arrayInit);
    }

    /**
     * f0 -> "new"
     * f1 -> NonArrayType()
     * f2 -> "["
     * f3 -> Identifier()
     * f4 -> "]"
     * f5 -> [ ArrayInitializer() ]
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.NewUpdatableArray n) {
        ScalarType elementType = (ScalarType) visitDispatch(n.f1);
        String regionName = ((ast.tree.expression.Id)visitDispatch(n.f3)).name;
        OptionalNode<NewArray.ArrayInit> optionalNode = (OptionalNode<NewArray.ArrayInit>) visitDispatch(n.f5);
        if (optionalNode.isPresent()) {
            NewArray.ArrayInit arrayInit = ((SomeNode<NewArray.ArrayInit>) optionalNode).get();
            return new NewArray(elementType, regionName, arrayInit);
        } else
            return new NewArray(elementType, regionName);

    }


    /**
     * f0 -> "("
     * f1 -> PointType()
     * f2 -> ExplodedSpecification()
     * f3 -> ")"
     * f4 -> Block()
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.ArrayInitializer n) {
        PointType pointType = (PointType) visitDispatch(n.f1);
        For.Formal formal = (For.Formal) visitDispatch(n.f2);
        Block block = (Block) visitDispatch(n.f4);
        return new NewArray.ArrayInit(pointType, formal, block);
    }


    /**
     * f0 -> <INTEGER_LITERAL>
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.IntegerLiteral n) {
        return new IntLiteral(n.f0.toString());
    }

    /**
     * f0 -> <LONG_LITERAL>
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.LongLiteral n) {
        return new LongLiteral(n.f0.toString());
    }

    /**
     * f0 -> <HEX_LITERAL>
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.HexLiteral n) {
        return new HexLiteral(n.f0.toString());
    }

    /**
     * f0 -> <FLOATING_POINT_LITERAL>
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.FloatingPointLiteral n) {
        return new FloatLiteral(n.f0.toString());
    }

    /**
     * f0 -> <STRING_LITERAL>
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.StringLiteral n) {
        return new StringLiteral(n.f0.toString());
    }

    /**
     * f0 -> "true"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.True n) {
        return True.instance();
    }

    /**
     * f0 -> "false"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.False n) {
        return False.instance();
    }

    /**
     * f0 -> "here"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.HereLiteral n) {
        return Here.instance();
    }

    /**
     * f0 -> "java.lang.Integer.SIZE"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.JavaIntegerSize n) {
        return IntegerSize.instance();
    }

    /**
     * f0 -> "place.FIRST_PLACE"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.PlaceFirstPlace n) {
        return FirstPlace.instance();
    }

    /**
     * f0 -> "place.LAST_PLACE"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.PlaceLastPlace n) {
        return LastPlace.instance();
    }

    /**
     * f0 -> "place.MAX_PLACES"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.PlaceMaxPlaces n) {
        return MaxPlaces.instance();
    }

    /**
     * f0 -> "place.places"
     * f1 -> "("
     * f2 -> Identifier()
     * f3 -> ")"
     */
    public Node visit(frontend.plasmax10.syntaxanalyser.syntaxtree.Place n) {
        String palceNumberName = ((ast.tree.expression.Id)visitDispatch(n.f2)).name;
        return new Place(palceNumberName);
    }

}
