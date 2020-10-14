package jsrc.px10.astbuild;


import jsrc.px10.astbuild.intastnodes.ArrayMaker;
import jsrc.px10.syntaxanalyser.syntaxtree.NodeToken;
import jsrc.x10.ast.tree.declaration.Method;
import jsrc.x10.ast.tree.expression.id.specialids.x10.classes.*;
import jsrc.x10.ast.tree.expression.op.logical.And;
import jsrc.x10.ast.tree.expression.op.logical.Not;
import jsrc.x10.ast.tree.expression.op.logical.Or;
import jsrc.x10.ast.tree.expression.id.specialids.general.IntegerSize;
import jsrc.x10.ast.tree.expression.x10.*;
import jsrc.x10.ast.tree.expression.x10.derived.ArrayAccess;
import jsrc.x10.ast.tree.expression.x10.derived.DistAccess;
import jsrc.x10.ast.tree.expression.xtras.Parenthesized;
import jsrc.x10.ast.tree.statement.derived.Print;
import jsrc.x10.ast.tree.statement.derived.PrintError;
import jsrc.x10.ast.tree.statement.derived.Println;
import jsrc.x10.ast.tree.statement.x10.Async;
import jsrc.x10.ast.tree.statement.x10.Finish;
import jsrc.x10.ast.tree.statement.x10.PointFor;
import lesani.collection.Pair;
import lesani.compiler.ast.HolderNode;
import lesani.compiler.ast.Node;
import lesani.compiler.ast.NodeList;
import jsrc.px10.astbuild.intastnodes.declarations.MethodModifier;
import jsrc.px10.astbuild.intastnodes.expression.*;
import jsrc.px10.astbuild.intastnodes.expression.ShiftLeftExp;
import jsrc.px10.astbuild.intastnodes.expression.ShiftRightExp;
import jsrc.px10.astbuild.intastnodes.statement.caseparts.*;
import jsrc.px10.astbuild.intastnodes.statement.caseparts.SwitchEntry;
import lesani.compiler.ast.optional.*;

import jsrc.x10.ast.tree.expression.MethodCall;
import jsrc.x10.ast.tree.expression.literal.This;
import jsrc.x10.ast.tree.expression.x10.sx10lib.Coord;
import jsrc.x10.ast.tree.expression.x10.sx10lib.Max;
import jsrc.x10.ast.tree.expression.x10.sx10lib.Ordinal;
import jsrc.x10.ast.tree.expression.x10.sx10lib.Sum;
import jsrc.x10.ast.tree.expression.id.specialids.x10.array.properties.Distribution;
import jsrc.x10.ast.tree.expression.id.specialids.x10.dist.methods.UniqueFactory;
import jsrc.x10.ast.tree.expression.id.specialids.x10.object.Equals;
import jsrc.x10.ast.tree.expression.id.specialids.x10.place.methods.*;
import jsrc.x10.ast.tree.expression.id.specialids.x10.dist.methods.BlockCyclicFactory;
import jsrc.x10.ast.tree.expression.id.specialids.x10.dist.methods.BlockFactory;
import jsrc.x10.ast.tree.expression.id.specialids.x10.place.properties.FirstPlace;
import jsrc.x10.ast.tree.expression.id.specialids.x10.place.properties.MaxPlaces;
import jsrc.x10.ast.tree.expression.id.specialids.x10.region.methods.*;
import jsrc.x10.ast.tree.expression.id.specialids.x10.system.methods.CurrentTime;
import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.tree.expression.literal.False;
import jsrc.x10.ast.tree.expression.literal.HexLiteral;
import jsrc.x10.ast.tree.expression.literal.LongLiteral;
import jsrc.x10.ast.tree.expression.literal.StringLiteral;
import jsrc.x10.ast.tree.expression.literal.True;

import jsrc.x10.ast.tree.declaration.File;
import jsrc.x10.ast.tree.declaration.MainClass;
import jsrc.x10.ast.tree.declaration.Visibility;

import jsrc.x10.ast.tree.statement.VarDeclOrSt;
import jsrc.x10.ast.tree.statement.Assignment;
import jsrc.x10.ast.tree.statement.Block;
import jsrc.x10.ast.tree.statement.Statement;
import jsrc.x10.ast.tree.statement.x10.parts.*;
import jsrc.x10.ast.tree.statement.x10.parts.Coordinates;
import jsrc.x10.ast.tree.type.BooleanType;
import jsrc.x10.ast.tree.type.ByteType;
import jsrc.x10.ast.tree.type.DistType;
import jsrc.x10.ast.tree.type.DoubleType;
import jsrc.x10.ast.tree.type.LongType;
import jsrc.x10.ast.tree.type.PlaceType;
import jsrc.x10.ast.tree.type.PointType;
import jsrc.x10.ast.tree.type.RegionType;
import jsrc.x10.ast.tree.type.ShortType;
import jsrc.x10.ast.tree.type.StringType;
import jsrc.x10.ast.tree.type.Type;
import jsrc.x10.ast.tree.type.VoidType;
import jsrc.x10.ast.tree.type.*;
import jsrc.x10.ast.tree.declaration.*;
import jsrc.x10.ast.tree.statement.*;
import jsrc.x10.ast.tree.expression.*;
import jsrc.x10.ast.tree.expression.op.math.*;
import jsrc.x10.ast.tree.expression.op.relational.*;
import jsrc.x10.ast.tree.expression.literal.*;
import jsrc.x10.ast.tree.type.constraint.Constraint;
import jsrc.x10.ast.tree.type.constraint.DistConstraint;
import jsrc.x10.ast.tree.type.constraint.RankConstraint;

import jsrc.px10.syntaxanalyser.visitor.GJNoArguDepthFirst;

import java.util.Enumeration;
import java.util.Iterator;

import jsrc.x10.srcgen.ProtoFinder;
import lesani.collection.Triple;
import lesani.collection.option.*;


public class ASTBuilder extends GJNoArguDepthFirst<Node> {

	private jsrc.px10.syntaxanalyser.syntaxtree.File file;

	public ASTBuilder(jsrc.px10.syntaxanalyser.syntaxtree.File file) {
		this.file = file;
	}

    public File build() {
        File file = (File)this.file.accept(this);
        ProtoFinder protoFinder = new ProtoFinder(file);
        protoFinder.findAndSet();
        return file;

    }

    private Node visitDispatch(jsrc.px10.syntaxanalyser.syntaxtree.Node node) {
        return node.accept(this);
    }

    //
    //  Auto class visitors
    //
    private Node visitList(Enumeration<jsrc.px10.syntaxanalyser.syntaxtree.Node> e) {
        //System.out.println("In Enumeration");
        NodeList nodeList = new NodeList();
        while (e.hasMoreElements()) {
            jsrc.px10.syntaxanalyser.syntaxtree.Node parseNode = e.nextElement();
            Node node = visitDispatch(parseNode);
//            if (node == null)
//                System.out.println("null in Enumeration");
            nodeList.add(node);
        }
        return nodeList;
    }

    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.NodeList n) {
        //System.out.println("In NodeList");
        return visitList(n.elements());
    }
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.NodeListOptional n) {
        return visitList(n.elements());
    }

    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.NodeOptional n) {
        if (n.present()) {
            Node node = visitDispatch(n.node);
            return new SomeNode(node);
        }
        else
            return NoneNode.instance();
    }

    // It is not needed to override NodeSequence
    //public Node visit(frontend.plasmax10.syntaxanalysis.syntaxtree.NodeSequence n) { }


    //Is it used anywhere?
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.NodeToken n) {
        return new Token(n);
    }


    /**
     * f0 -> ( TopLevelDeclaration() )*
     * f1 -> <EOF>
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.File file) {
        NodeList<ClassDecl> classDecls = (NodeList<ClassDecl>) visitDispatch(file.f0);
        ClassDecl[] classDeclArray = ArrayMaker.classDeclArray(classDecls);
        return new File(classDeclArray);
    }

    /**
     * f0 -> MainClass()
     *       | ClassDeclaration()
     *       | ValueDeclaration()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.TopLevelDeclaration n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> "public"
     * f1 -> "class"
     * f2 -> Identifier()
     * f3 -> "{"
     * f4 -> "public"
     * f5 -> "static"
     * f6 -> "void"
     * f7 -> "main"
     * f8 -> "("
     * f9 -> "String"
     * f10 -> "["
     * f11 -> "]"
     * f12 -> Identifier()
     * f13 -> ")"
     * f14 -> "{"
     * f15 -> Statement()
     * f16 -> "}"
     * f17 -> "}"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.MainClass n) {
        jsrc.x10.ast.tree.expression.id.Id name = (jsrc.x10.ast.tree.expression.id.Id) visitDispatch(n.f2);
        jsrc.x10.ast.tree.expression.id.Id args = (jsrc.x10.ast.tree.expression.id.Id) visitDispatch(n.f12);
        Statement statement = ((Statement) visitDispatch(n.f15));
        Statement[] statements = new Statement[1];
        statements[0] = statement;
        Block block = new Block(statements);
        setLoc(block, n.f14);
        return new MainClass(name, args, block);
    }


    private Triple<Field[], Constructor[], Method[]>
        extractClassMembers(NodeList membersNodeList) {


        NodeList<Field> fieldDeclNodeList = new NodeList<Field>();
        NodeList<Method> methodDeclNodeList = new NodeList<Method>();
        NodeList<Constructor> constructorDeclNodeList = new NodeList<Constructor>();

        Iterator<Node> iterator = membersNodeList.iterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            if (node instanceof Field)
                fieldDeclNodeList.add((Field)node);
            else if (node instanceof Method)
                methodDeclNodeList.add((Method)node);
            else if (node instanceof Constructor)
                constructorDeclNodeList.add((Constructor)node);
        }
        Field[] fields = ArrayMaker.fieldDeclArray(fieldDeclNodeList);
        Method[] methods = ArrayMaker.methodDeclArray(methodDeclNodeList);
        Constructor[] constructors = ArrayMaker.constructorDeclArray(constructorDeclNodeList);

        return new Triple<Field[], Constructor[], Method[]>
                (fields, constructors, methods);
    }

    /**
     * f0 -> [ Public() ]
     * f1 -> "class"
     * f2 -> Identifier()
     * f3 -> "{"
     * f4 -> ( ClassMember() )*
     * f5 -> "}"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ClassDeclaration n) {
        jsrc.x10.ast.tree.expression.id.Id name = (jsrc.x10.ast.tree.expression.id.Id) visitDispatch(n.f2);
        NodeList classMemberNodeList = (NodeList) visitDispatch(n.f4);
        Triple<Field[], Constructor[], Method[]> classInfo =
                extractClassMembers(classMemberNodeList);
        return new ClassDecl(name, classInfo.ge1(), classInfo.get2(), classInfo.get3());
    }



    /**
     * f0 -> [ Public() ]
     * f1 -> "value"
     * f2 -> Identifier()
     * f3 -> "{"
     * f4 -> ( ValueMember() )*
     * f5 -> "}"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ValueDeclaration n) {
        jsrc.x10.ast.tree.expression.id.Id name = (jsrc.x10.ast.tree.expression.id.Id) visitDispatch(n.f2);
        NodeList valueMemberNodeList = (NodeList) visitDispatch(n.f4);
        Triple<Field[], Constructor[], Method[]> classInfo =
                extractClassMembers(valueMemberNodeList);
        return new ClassDecl(true, name, classInfo.ge1(), classInfo.get2(), classInfo.get3());
    }

   /**
    * f0 -> ConstructorDeclaration()
    *       | MethodDeclaration()
    *       | ConstantDeclaration()
    *       | InitializableConstantDeclaration()
    *       | UpdatableFieldDeclaration()
    */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ClassMember n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> ConstructorDeclaration()
     *       | MethodDeclaration()
     *       | ConstantDeclaration()
     *       | InitializableConstantDeclaration()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ValueMember n) {
        return visitDispatch(n.f0);
    }


    /**
     * f0 -> "public"
     * f1 -> Identifier()
     * f2 -> "("
     * f3 -> ( FormalParameterList() )?
     * f4 -> ")"
     * f5 -> Block()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ConstructorDeclaration n) {
        //We do not need to take the name.

        OptionalNode optionalFormalParamList = (OptionalNode)visitDispatch(n.f3);
        FormalParam[] formalParams;
        if (optionalFormalParamList.isPresent()) {
            NodeList<FormalParam> formalParamList = (NodeList<FormalParam>)((SomeNode)optionalFormalParamList).get();
            formalParams = ArrayMaker.formalParamArray(formalParamList);
        } else
            formalParams = new FormalParam[0];

        Block block = (Block) visitDispatch(n.f5);
        return new Constructor(formalParams, block);
    }

    /**
     * f0 -> Public()
     *       | Private()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.Visibility n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> Visibility()
     * f1 -> "static"
     * f2 -> "final"
     * f3 -> Type()
     * f4 -> Identifier()
     * f5 -> "="
     * f6 -> Expression()
     * f7 -> ";"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ConstantDeclaration n) {
        Visibility visibility = (Visibility) visitDispatch(n.f0);
        NonVoidType type = (NonVoidType) visitDispatch(n.f3);
        jsrc.x10.ast.tree.expression.id.Id name = (jsrc.x10.ast.tree.expression.id.Id) visitDispatch(n.f4);
        Expression expression = (Expression) visitDispatch(n.f6);
        return new Field(visibility, true, true, type, name, expression);
    }

    /**
     * f0 -> Visibility()
     * f1 -> "final"
     * f2 -> Type()
     * f3 -> Identifier()
     * f4 -> ";"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.InitializableConstantDeclaration n) {
        Visibility visibility = ((Visibility) visitDispatch(n.f0));
        NonVoidType type = (NonVoidType) visitDispatch(n.f2);
        jsrc.x10.ast.tree.expression.id.Id name = (jsrc.x10.ast.tree.expression.id.Id) visitDispatch(n.f3);
        return new Field(visibility, true, type, name);
    }

    /**
     * f0 -> Visibility()
     * f1 -> Type()
     * f2 -> Identifier()
     * f3 -> ";"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.UpdatableFieldDeclaration n) {
        Visibility visibility = ((Visibility) visitDispatch(n.f0));
        NonVoidType type = (NonVoidType) visitDispatch(n.f1);
        jsrc.x10.ast.tree.expression.id.Id name = (jsrc.x10.ast.tree.expression.id.Id) visitDispatch(n.f2);
        return new Field(visibility, type, name);
    }

    /**
     * f0 -> "public"
     * f1 -> "static"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.PublicStatic n) {
        return new MethodModifier(Visibility.PUBLIC, true);
    }

    /**
     * f0 -> "public"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.Public n) {
        return Visibility.PUBLIC;
    }

    /**
     * f0 -> "private"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.Private n) {
        return Visibility.PRIVATE;
    }

    /**
     * f0 -> PublicStatic()
     *       | Public()
     *       | Private()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.MethodModifier n) {
        Node node = visitDispatch(n.f0);
        if (node instanceof Visibility)
            return new MethodModifier((Visibility)node);
        else
            return node;
    }

    /**
     * f0 -> MethodModifier()
     * f1 -> ReturnType()
     * f2 -> Identifier()
     * f3 -> "("
     * f4 -> ( FormalParameterList() )?
     * f5 -> ")"
     * f6 -> Block()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.MethodDeclaration n) {
        MethodModifier methodModifier = (MethodModifier)visitDispatch(n.f0);
        Type type = (Type)visitDispatch(n.f1);
        jsrc.x10.ast.tree.expression.id.Id name = (jsrc.x10.ast.tree.expression.id.Id) visitDispatch(n.f2);

        OptionalNode optionalFormalParamList = (OptionalNode)visitDispatch(n.f4);
        FormalParam[] formalParams;
        if (optionalFormalParamList.isPresent()) {
            NodeList<FormalParam> formalParamList = (NodeList<FormalParam>)((SomeNode)optionalFormalParamList).get();
            formalParams = ArrayMaker.formalParamArray(formalParamList);
        } else
            formalParams = new FormalParam[0];


        Block block = (Block) visitDispatch(n.f6);
        return new Method(
                methodModifier.visibility,
                methodModifier.isStatic,
                methodModifier.isFinal,
                type, name, formalParams, block);
    }

    /**
     * f0 -> FormalParameter()
     * f1 -> ( FormalParameterRest() )*
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.FormalParameterList n) {
        FormalParam formalParam0 = (FormalParam)visitDispatch(n.f0);
        NodeList<FormalParam> allFormalParamNodeList = new NodeList<FormalParam>();
        allFormalParamNodeList.add(formalParam0);

        NodeList<FormalParam> restFormalParamNodeList = (NodeList<FormalParam>) visitDispatch(n.f1);
        allFormalParamNodeList.add(restFormalParamNodeList);
        /*
        Iterator<FormalParam> iterator = restFormalParamNodeList.iterator();
        while (iterator.hasNext()) {
            FormalParam formalParam = iterator.next();
            allFormalParamNodeList.add(formalParam);
        }
        */
        return allFormalParamNodeList;
    }

    /**
     * f0 -> FinalFormalParameter()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.FormalParameter n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> "final"
     * f1 -> Type()
     * f2 -> Identifier()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.FinalFormalParameter n) {
        NonVoidType type = (NonVoidType)visitDispatch(n.f1);
        jsrc.x10.ast.tree.expression.id.Id name = (jsrc.x10.ast.tree.expression.id.Id) visitDispatch(n.f2);
        if (n.f0.present())
            return new FormalParam(true, type, name);
        else
            return new FormalParam(false, type, name);
    }

    /**
     * f0 -> ","
     * f1 -> FormalParameter()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.FormalParameterRest n) {
        return visitDispatch(n.f1);
    }

    /**
     * f0 -> VoidType()
     *       | Type()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ReturnType n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> "void"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.VoidType n) {
        return VoidType.instance();
    }

    /**
     * f0 -> UpdatableArrayType()
     *       | ValueArrayType()
     *       | NonArrayType()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.Type n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> NonArrayType()
     * f1 -> "["
     * f2 -> ":"
     * f3 -> RankEquation()
     * f4 -> ( DistributionEquation() )?
     * f5 -> "]"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.UpdatableArrayType n) {
        Type type = (Type) visitDispatch(n.f0);
        RankConstraint rankConstraint =
                ((HolderNode<RankConstraint>) visitDispatch(n.f3)).get();
        OptionalNode optional = (OptionalNode)visitDispatch(n.f4);
        if (optional.isPresent()) {
            DistConstraint distConstraint
                    = ((HolderNode<DistConstraint>)((SomeNode)optional).get()).get();
            return new ArrayType(type, new Constraint[]{rankConstraint, distConstraint});
        }
        else
            return new ArrayType(type, new Constraint[]{rankConstraint});
    }

    /**
     * f0 -> NonArrayType()
     * f1 -> "value"
     * f2 -> "["
     * f3 -> ":"
     * f4 -> RankEquation()
     * f5 -> "]"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ValueArrayType n) {
        Type type = (Type) visitDispatch(n.f0);
        RankConstraint rankConstraint =
                ((HolderNode<RankConstraint>) visitDispatch(n.f4)).get();
        return new ArrayType(true, type, new Constraint[]{rankConstraint});
    }

    /**
     * f0 -> "&&"
     * f1 -> "distribution"
     * f2 -> "=="
     * f3 -> Identifier()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DistributionEquation n) {
        Expression expression = (Expression) visitDispatch(n.f3);
        return new HolderNode<DistConstraint>(new DistConstraint(expression));
    }

    /**
     * f0 -> "rank"
     * f1 -> "=="
     * f2 -> IntegerLiteral()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.RankEquation n) {
        Expression expression = (Expression) visitDispatch(n.f2);
        return new HolderNode<RankConstraint>(new RankConstraint(expression));
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
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.NonArrayType n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> "boolean"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.BooleanType n) {
        return BooleanType.instance();
    }

    /**
     * f0 -> "byte"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ByteType n) {
        return ByteType.instance();
    }

    /**
     * f0 -> "short"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ShortType n) {
        return ShortType.instance();
    }

    /**
     * f0 -> "int"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.IntegerType n) {
        return IntType.instance();
    }

    /**
     * f0 -> "long"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.LongType n) {
        return LongType.instance();
    }

    /**
     * f0 -> "double"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DoubleType n) {
        return DoubleType.instance();
    }

    /**
     * f0 -> "String"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.StringType n) {
        return StringType.instance();
    }

    /**
     * f0 -> "place"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.PlaceType n) {
        return PlaceType.instance();
    }

    /**
     * f0 -> "dist"
     * f1 -> "("
     * f2 -> ":"
     * f3 -> RankEquation()
     * f4 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DistType n) {
        RankConstraint rankConstraint =
                ((HolderNode<RankConstraint>) visitDispatch(n.f3)).get();
        return new DistType(rankConstraint);
    }


    /**
     * f0 -> "region"
     * f1 -> "("
     * f2 -> ":"
     * f3 -> RankEquation()
     * f4 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.RegionType n) {
        RankConstraint rankConstraint =
                ((HolderNode<RankConstraint>) visitDispatch(n.f3)).get();
        return new RegionType(rankConstraint);
    }

    /**
     * f0 -> "point"
     * f1 -> "("
     * f2 -> ":"
     * f3 -> RankEquation()
     * f4 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.PointType n) {
        RankConstraint rankConstraint =
                ((HolderNode<RankConstraint>) visitDispatch(n.f3)).get();
        return new PointType(rankConstraint);
    }

    /**
     * f0 -> Identifier()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ClassNameType n) {
        String name = ((jsrc.x10.ast.tree.expression.id.Id) visitDispatch(n.f0)).lexeme;
        return new DefinedType(name);
    }

    /**
     * f0 -> <IDENTIFIER>
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.Identifier n) {
        return new jsrc.x10.ast.tree.expression.id.Id(n.f0.toString());
    }


    /**
     * f0 -> Assignment()
     *       | AsyncStatement()
     *       | Block()
     *       | BreakStatement()
     *       | ContinueStatement()
     *       | DoStatement()
     *       | FinishStatement()
     *       | IfStatement()
     *       | LoopStatement()
     *       | PostfixStatement()
     *       | PrintlnStatement()
     *       | PrintStatement()
     *       | PrintErrorStatement()
     *       | ReturnStatement()
     *       | SwitchStatement()
     *       | ThrowStatement()
     *       | WhileStatement()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.Statement n) {
        return visitDispatch(n.f0);
    }

    /**
    * f0 -> PrimaryExpression()
    * f1 -> "="
    * f2 -> Expression()
    * f3 -> ";"
    */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.Assignment n) {
        Expression left = (Expression) visitDispatch(n.f0);
        Expression right = (Expression) visitDispatch(n.f2);
        Assignment assignment = new Assignment(left, right);
        setLoc(assignment, n.f1);
        return assignment;
    }

    /**
     * f0 -> "async"
     * f1 -> "("
     * f2 -> Expression()
     * f3 -> ")"
     * f4 -> Block()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.AsyncStatement n) {
        Expression place = (Expression) visitDispatch(n.f2);
        Block block = (Block) visitDispatch(n.f4);
        Async async = new Async(place, block);
        setLoc(async, n.f0);
        return async;
    }

    /**
     * f0 -> "{"
     * f1 -> ( BlockStatement() )*
     * f2 -> "}"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.Block n) {
        NodeList<VarDeclOrSt> statementList = (NodeList<VarDeclOrSt>)visitDispatch(n.f1);
        VarDeclOrSt[] varDeclOrSts = ArrayMaker.blockStatementArray(statementList);
//        for (Statement statement : blockStatements) {
//            if (statement == null)
//                System.out.println("null in Block prep.");
//        }
        Block block = new Block(varDeclOrSts);
        setLoc(block, n.f0);
        return block;
    }

    /**
     * f0 -> FinalVariableDeclaration()
     *       | UpdatableVariableDeclaration()
     *       | Statement()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.BlockStatement n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> "final"
     * f1 -> Type()
     * f2 -> Identifier()
     * f3 -> "="
     * f4 -> Expression()
     * f5 -> ";"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.FinalVariableDeclaration n) {
        NonVoidType type = (NonVoidType)visitDispatch(n.f1);
        jsrc.x10.ast.tree.expression.id.Id name = (jsrc.x10.ast.tree.expression.id.Id)visitDispatch(n.f2);
        Expression expression = (Expression)visitDispatch(n.f4);
//        if (expression == null) {
//            System.out.println(n.f4);
//            System.out.println(n.f3.beginLine);
//        }
        VarDecl varDecl = new VarDecl(true, type, name, expression);
        //Todo: Add loc for other nodes and change this to n.f0
        setLoc(varDecl, n.f3);
        return varDecl;
    }
        /**
     * f0 -> Type()
     * f1 -> Identifier()
     * f2 -> "="
     * f3 -> Expression()
     * f4 -> ";"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.UpdatableVariableDeclaration n) {
        NonVoidType type = (NonVoidType)visitDispatch(n.f0);
        jsrc.x10.ast.tree.expression.id.Id name = (jsrc.x10.ast.tree.expression.id.Id)visitDispatch(n.f1);
        Expression expression = (Expression)visitDispatch(n.f3);
//        if (expression == null) {
//            System.out.println(n.f4);
//            System.out.println(n.f4.beginLine);
//        }
        VarDecl varDecl = new VarDecl(type, name, expression);
        //Todo: Add loc for other nodes and change this to n.f0
        setLoc(varDecl, n.f2);
        return varDecl;
    }

    /**
     * f0 -> "break"
     * f1 -> ";"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.BreakStatement n) {
        Break theBreak = new Break();
        setLoc(theBreak, n.f0);
        return theBreak;
    }

    /**
     * f0 -> "continue"
     * f1 -> ";"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ContinueStatement n) {
        Continue theContinue = Continue.instance();
        setLoc(theContinue, n.f0);
        return theContinue;
    }

    /**
     * f0 -> "do"
     * f1 -> Statement()
     * f2 -> "while"
     * f3 -> "("
     * f4 -> Expression()
     * f5 -> ")"
     * f6 -> ";"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DoStatement n) {
        Statement statement = (Statement) visitDispatch(n.f1);
        Expression condition = (Expression) visitDispatch(n.f4);
        DoWhile doWhile = new DoWhile(statement, condition);
        setLoc(doWhile, n.f0);
        return doWhile;
    }

    /**
     * f0 -> "finish"
     * f1 -> Statement()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.FinishStatement n) {
        Statement statement = (Statement) visitDispatch(n.f1);
        Finish finish = new Finish(statement);
        setLoc(finish, n.f0);
        return finish;
    }


    /**
     * f0 -> "if"
     * f1 -> "("
     * f2 -> Expression()
     * f3 -> ")"
     * f4 -> Statement()
     * f5 -> [ ElseClause() ]
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.IfStatement n) {

        Expression condition = (Expression) visitDispatch(n.f2);
        Statement ifStatement = (Statement) visitDispatch(n.f4);

        OptionalNode optionalNode = (OptionalNode) visitDispatch(n.f5);
        if (optionalNode.isPresent()) {
            SomeNode someNode = (SomeNode) optionalNode;
            Statement elseStatement = (Statement) someNode.get();
            If anIf = new If(condition, ifStatement, elseStatement);
            setLoc(anIf, n.f0);
            return anIf;
        }
        else {
            If anIf = new If(condition, ifStatement);
            setLoc(anIf, n.f0);
            return anIf;
        }
    }

    /**
     * f0 -> "else"
     * f1 -> Statement()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ElseClause n) {
        return visitDispatch(n.f1);
    }

    /**
     * f0 -> LoopQualifier()
     * f1 -> "("
     * f2 -> PointType()
     * f3 -> ExplodedSpecification()
     * f4 -> ":"
     * f5 -> Expression()
     * f6 -> ")"
     * f7 -> Statement()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.LoopStatement n) {
        Pair<NodeToken, PointFor.Type> ret =
                ((HolderNode<Pair<NodeToken, PointFor.Type>>) visitDispatch(n.f0)).get();
        NodeToken nodeToken = ret._1();
        PointFor.Type forType = ret._2();
        PointType pointType = (PointType) visitDispatch(n.f2);
        PointFormalVar formal =
                ((HolderNode<PointFormalVar>) visitDispatch(n.f3)).get();
//        System.out.println(formal);
        Expression aggregate = (Expression) visitDispatch(n.f5);
        Statement statement = (Statement) visitDispatch(n.f7);
        PointFor pointFor = new PointFor(forType, pointType, formal, aggregate, statement);
        setLoc(pointFor, nodeToken);
        return pointFor;
    }



    /**
     * f0 -> Ateach()
     *       | For()
     *       | Foreach()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.LoopQualifier n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> "ateach"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.Ateach n) {
        return new HolderNode<Pair<NodeToken, PointFor.Type>>(new Pair<NodeToken, PointFor.Type>(n.f0, PointFor.Type.ATEACH));
    }

    /**
     * f0 -> "for"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.For n) {
        return new HolderNode<Pair<NodeToken, PointFor.Type>>(new Pair<NodeToken, PointFor.Type>(n.f0, PointFor.Type.FOR));
    }

    /**
     * f0 -> "foreach"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.Foreach n) {
        return new HolderNode<Pair<NodeToken, PointFor.Type>>(new Pair<NodeToken, PointFor.Type>(n.f0, PointFor.Type.FOREACH));
    }

    /**
     * f0 -> PointNameCoordinates()
     *       | PointName()
     *       | Coordinates()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ExplodedSpecification n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> PointName()
     * f1 -> Coordinates()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.PointNameCoordinates n) {
        jsrc.x10.ast.tree.statement.x10.parts.Id id =
                ((HolderNode<jsrc.x10.ast.tree.statement.x10.parts.Id>) visitDispatch(n.f0)).get();
        Coordinates coordinates = ((HolderNode<Coordinates>) visitDispatch(n.f1)).get();
        return new HolderNode<IdCoordinates>(new IdCoordinates(id, coordinates));
    }

    /**
     * f0 -> Identifier()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.PointName n) {
        jsrc.x10.ast.tree.expression.id.Id id = (jsrc.x10.ast.tree.expression.id.Id) visitDispatch(n.f0);
        return new HolderNode<jsrc.x10.ast.tree.statement.x10.parts.Id>(new jsrc.x10.ast.tree.statement.x10.parts.Id(id));
    }

    /**
     * f0 -> "["
     * f1 -> IdentifierList()
     * f2 -> "]"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.Coordinates n) {
        NodeList<jsrc.x10.ast.tree.expression.id.Id> idNodeList = (NodeList<jsrc.x10.ast.tree.expression.id.Id>) visitDispatch(n.f1);
        jsrc.x10.ast.tree.expression.id.Id[] ids = ArrayMaker.idArray(idNodeList);
        return new HolderNode<Coordinates>(new Coordinates(ids));
    }

    /**
     * f0 -> Identifier()
     * f1 -> ( IdentifierRest() )*
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.IdentifierList n) {
        jsrc.x10.ast.tree.expression.id.Id id0 = (jsrc.x10.ast.tree.expression.id.Id)visitDispatch(n.f0);
        NodeList<jsrc.x10.ast.tree.expression.id.Id> idNodeList = (NodeList<jsrc.x10.ast.tree.expression.id.Id>) visitDispatch(n.f1);
        NodeList<jsrc.x10.ast.tree.expression.id.Id> allIdList = new NodeList<jsrc.x10.ast.tree.expression.id.Id>();
        allIdList.add(id0);
        allIdList.add(idNodeList);
        /*
        Iterator<Id> iterator = idNodeList.iterator();
        while (iterator.hasNext()) {
            Id id = iterator.next();
            allIdList.add(id);
        }
        */
        return allIdList;
    }


    /**
     * f0 -> ","
     * f1 -> Identifier()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.IdentifierRest n) {
        return visitDispatch(n.f1);
    }


    /**
     * f0 -> PostfixExpression()
     * f1 -> ";"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.PostfixStatement n) {
        Expression expression = (Expression) visitDispatch(n.f0);
        ExpSt expSt = new ExpSt(expression);
        if (expression instanceof MethodCall) {
            //Todo: Can remove this when sourceloc is added to all nodes.
            MethodCall methodCall = (MethodCall) expression;
            expSt.setLoc(methodCall.sourceLoc);
        }
        return expSt;
    }

    /**
     * f0 -> "System.out.println"
     * f1 -> "("
     * f2 -> Expression()
     * f3 -> ")"
     * f4 -> ";"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.PrintlnStatement n) {
        Expression arg = (Expression)visitDispatch(n.f2);
        Println println = new Println(arg);
        setLoc(println, n.f0);
        return println;
    }

    /**
     * f0 -> "System.out.print"
     * f1 -> "("
     * f2 -> Expression()
     * f3 -> ")"
     * f4 -> ";"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.PrintStatement n) {
        Expression arg = (Expression)visitDispatch(n.f2);
        Print print = new Print(arg);
        setLoc(print, n.f0);
        return print;
    }

    /**
     * f0 -> "System.err.println"
     * f1 -> "("
     * f2 -> Expression()
     * f3 -> ")"
     * f4 -> ";"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.PrintErrorStatement n) {
        Expression arg = (Expression)visitDispatch(n.f2);
        PrintError printError = new PrintError(arg);
        setLoc(printError, n.f0);
        return printError;
    }

    /**
     * f0 -> "return"
     * f1 -> [ Expression() ]
     * f2 -> ";"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ReturnStatement n) {
        OptionalNode optionalNode = (OptionalNode)visitDispatch(n.f1);
        if (optionalNode.isPresent()) {
            SomeNode someNode = (SomeNode) optionalNode;
            Expression expression = (Expression)someNode.get();
            ValueReturn valueReturn = new ValueReturn(expression);
            setLoc(valueReturn, n.f0);
            return valueReturn;
        }
        else {
            VoidReturn voidReturn = new VoidReturn();
            setLoc(voidReturn, n.f0);
            return voidReturn;
        }
    }

    /**
     * f0 -> "switch"
     * f1 -> "("
     * f2 -> Expression()
     * f3 -> ")"
     * f4 -> "{"
     * f5 -> ( SwitchEntry() )*
     * f6 -> "}"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.SwitchStatement n) {
        Expression selector = (Expression)visitDispatch(n.f2);

        NodeList<SwitchEntry> switchEntryList = (NodeList<SwitchEntry>) visitDispatch(n.f5);
        SwitchEntry probableDefault = switchEntryList.elementAt(switchEntryList.size() - 1);
        Option<VarDeclOrSt[]> defaultBlockStatements;
        Switch.Case[] cases;
        if (probableDefault.switchGuard instanceof DefaultGuard) {
            cases = new Switch.Case[switchEntryList.size() - 1];
            defaultBlockStatements = new Some<VarDeclOrSt[]>(probableDefault.statements);
        } else {
            cases = new Switch.Case[switchEntryList.size()];
            defaultBlockStatements = None.instance();
        }
        Iterator<SwitchEntry> iterator = switchEntryList.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            SwitchEntry switchEntry = iterator.next();
            CaseGuard caseGuard = (CaseGuard) (switchEntry.switchGuard);
            cases[i] = new Switch.Case(caseGuard.guard, switchEntry.statements);
            i++;
            if (i == cases.length)
                break;
        }
        Switch aSwitch = new Switch(selector, cases, defaultBlockStatements);
        setLoc(aSwitch, n.f0);
        return aSwitch;
    }

    /**
     * f0 -> SwitchLabel()
     * f1 -> ":"
     * f2 -> ( BlockStatement() )*
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.SwitchEntry n) {
        SwitchGuard switchGuard = (SwitchGuard)visitDispatch(n.f0);
        NodeList<Statement> statementList =
                (NodeList<Statement>)visitDispatch(n.f2);
        Statement[] blockSts = ArrayMaker.statementArray(statementList);
        return new SwitchEntry(switchGuard, blockSts);
    }

    /**
     * f0 -> Case()
     *       | DefaultLabel()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.SwitchLabel n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> "case"
     * f1 -> Expression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.Case n) {
        Expression guard = (Expression)visitDispatch(n.f1);
        return new CaseGuard(guard);
    }

    /**
     * f0 -> "default"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.Default n) {
        return DefaultGuard.instance();
    }

    /**
     * f0 -> "throw"
     * f1 -> "new"
     * f2 -> "RuntimeException"
     * f3 -> "("
     * f4 -> Expression()
     * f5 -> ")"
     * f6 -> ";"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ThrowStatement n) {
        Expression arg = (Expression)visitDispatch(n.f4);
        Throw aThrow = new Throw(arg);
        setLoc(aThrow, n.f0);
        return aThrow;
    }

    /**
     * f0 -> "while"
     * f1 -> "("
     * f2 -> Expression()
     * f3 -> ")"
     * f4 -> Statement()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.WhileStatement n) {
        Expression condition = (Expression)visitDispatch(n.f2);
        Statement statement = (Statement)visitDispatch(n.f4);
        While aWhile = new While(condition, statement);
        setLoc(aWhile, n.f0);
        return aWhile;
    }

    /**
     * f0 -> ConditionalExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.Expression n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> ConditionalOrExpression()
     * f1 -> [ ConditionalExpressionRest() ]
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ConditionalExpression n) {
        Expression e = (Expression) visitDispatch(n.f0);
        OptionalNode optional = (OptionalNode)visitDispatch(n.f1);
        if (optional.isPresent()) {
            ConditionalRest conditionalRest
                    = (ConditionalRest)((SomeNode)optional).get();
            return new Conditional(e, conditionalRest.ifExp, conditionalRest.elseExp);
        }
        else
            return e;
    }

    /**
     * f0 -> "?"
     * f1 -> Expression()
     * f2 -> ":"
     * f3 -> Expression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ConditionalExpressionRest n) {
        Expression ifExp = (Expression) visitDispatch(n.f1);
        Expression elseExp = (Expression) visitDispatch(n.f3);
        return new ConditionalRest(ifExp, elseExp);
    }



    /**
     * f0 -> ConditionalAndExpression()
     * f1 -> ( ConditionalOrExpressionRest() )*
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ConditionalOrExpression n) {
        Expression operand1 = (Expression)visitDispatch(n.f0);
        NodeList<Expression> operands = (NodeList<Expression>)visitDispatch(n.f1);
        Iterator<Expression> iterator = operands.iterator();
        while (iterator.hasNext()) {
            Expression operand2 = iterator.next();
            operand1 = new Or(operand1, operand2);
        }
        return operand1;
    }

    /**
     * f0 -> "||"
     * f1 -> ConditionalAndExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ConditionalOrExpressionRest n) {
        return visitDispatch(n.f1);
    }


    /**
     * f0 -> InclusiveOrExpression()
     * f1 -> ( ConditionalAndExpressionRest() )*
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ConditionalAndExpression n) {
        Expression operand1 = (Expression)visitDispatch(n.f0);
        NodeList<Expression> operands = (NodeList<Expression>)visitDispatch(n.f1);
        Iterator<Expression> iterator = operands.iterator();
        while (iterator.hasNext()) {
            Expression operand2 = iterator.next();
            operand1 = new And(operand1, operand2);
        }
        return operand1;
    }

    /**
     * f0 -> "&&"
     * f1 -> InclusiveOrExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ConditionalAndExpressionRest n) {
        return visitDispatch(n.f1);
    }


    /**
     * f0 -> ExclusiveOrExpression()
     * f1 -> ( InclusiveOrExpressionRest() )*
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.InclusiveOrExpression n) {
        Expression operand1 = (Expression)visitDispatch(n.f0);
        NodeList<Expression> operands = (NodeList<Expression>)visitDispatch(n.f1);
        Iterator<Expression> iterator = operands.iterator();
        while (iterator.hasNext()) {
            Expression operand2 = iterator.next();
            operand1 = new BitOr(operand1, operand2);
        }
        return operand1;
    }

    /**
     * f0 -> "|"
     * f1 -> ExclusiveOrExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.InclusiveOrExpressionRest n) {
        return visitDispatch(n.f1);
    }


    /**
     * f0 -> AndExpression()
     * f1 -> ( ExclusiveOrExpressionRest() )*
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ExclusiveOrExpression n) {
        Expression operand1 = (Expression)visitDispatch(n.f0);
        NodeList<Expression> operands = (NodeList<Expression>)visitDispatch(n.f1);
        Iterator<Expression> iterator = operands.iterator();
        while (iterator.hasNext()) {
            Expression operand2 = iterator.next();
            operand1 = new BitXOr(operand1, operand2);
        }
        return operand1;
    }

    /**
     * f0 -> "^"
     * f1 -> AndExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ExclusiveOrExpressionRest n) {
        return visitDispatch(n.f1);
    }

    /**
     * f0 -> EqualityExpression()
     * f1 -> ( AndExpressionRest() )*
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.AndExpression n) {
        Expression operand1 = (Expression)visitDispatch(n.f0);
        NodeList<Expression> operands = (NodeList<Expression>)visitDispatch(n.f1);
        Iterator<Expression> iterator = operands.iterator();
        while (iterator.hasNext()) {
            Expression operand2 = iterator.next();
            operand1 = new BitAnd(operand1, operand2);
        }
        return operand1;
    }
    /**
     * f0 -> "&"
     * f1 -> EqualityExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.AndExpressionRest n) {
        return visitDispatch(n.f1);
    }

    /**
     * f0 -> RelationalExpression()
     * f1 -> ( EqualityExpressionRest() )?
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.EqualityExpression n) {
        Expression e1 = (Expression) visitDispatch(n.f0);
        OptionalNode optional = (OptionalNode)visitDispatch(n.f1);
        if (optional.isPresent()) {
            EqualityExp equalityExp = (EqualityExp)((SomeNode)optional).get();
            Expression e2 = equalityExp.e;
            if (equalityExp instanceof EqualsExp)
                e1 = new Equality(e1, e2);
            else
                e1 = new NotEquality(e1, e2);
        }
        return e1;
    }

    /**
     * f0 -> EqualsExpression()
     *       | NonEqualsExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.EqualityExpressionRest n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> "=="
     * f1 -> RelationalExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.EqualsExpression n) {
        Expression e = (Expression) visitDispatch(n.f1);
        return new EqualsExp(e);
    }

    /**
     * f0 -> "!="
     * f1 -> RelationalExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.NonEqualsExpression n) {
        Expression e = (Expression) visitDispatch(n.f1);
        return new NotEqualsExp(e);
    }


    /**
     * f0 -> ShiftExpression()
     * f1 -> [ RelationalExpressionRest() ]
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.RelationalExpression n) {
        Expression e1 = (Expression) visitDispatch(n.f0);
        OptionalNode optional = (OptionalNode)visitDispatch(n.f1);
        if (optional.isPresent()) {
            RelExp relExp = (RelExp)((SomeNode)optional).get();
            Expression e2 = relExp.e;
            if (relExp instanceof LessThanExp)
                e1 = new LessThan(e1, e2);
            else if (relExp instanceof GreaterThanExp)
                e1 = new GreaterThan(e1, e2);
            else if (relExp instanceof LessThanEqualExp)
                e1 = new LessThanEqual(e1, e2);
            else
                e1 = new GreaterThanEqual(e1, e2);
        }
        return e1;
    }

    /**
     * f0 -> LessThanExpression()
     *       | GreaterThanExpression()
     *       | LessThanEqualExpression()
     *       | GreaterThanEqualExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.RelationalExpressionRest n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> "<"
     * f1 -> ShiftExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.LessThanExpression n) {
        Expression e = (Expression) visitDispatch(n.f1);
        return new LessThanExp(e);
    }
    /**
     * f0 -> ">"
     * f1 -> ShiftExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.GreaterThanExpression n) {
        Expression e = (Expression) visitDispatch(n.f1);
        return new GreaterThanExp(e);
    }
    /**
     * f0 -> "<="
     * f1 -> ShiftExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.LessThanEqualExpression n) {
        Expression e = (Expression) visitDispatch(n.f1);
        return new LessThanEqualExp(e);
    }
    /**
     * f0 -> ">="
     * f1 -> ShiftExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.GreaterThanEqualExpression n) {
        Expression e = (Expression) visitDispatch(n.f1);
        return new GreaterThanEqualExp(e);
    }

    /**
     * f0 -> AdditiveExpression()
     * f1 -> ( ShiftExpressionRest() )*
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ShiftExpression n) {
        Expression e1 = (Expression) visitDispatch(n.f0);
        NodeList<ShiftExp> operands = (NodeList<ShiftExp>)visitDispatch(n.f1);
        Iterator<ShiftExp> iterator = operands.iterator();
        while (iterator.hasNext()) {
            ShiftExp shiftExp = iterator.next();
            Expression e2 = shiftExp.e;
            if (shiftExp instanceof ShiftLeftExp)
                e1 = new ShiftLeft(e1, e2);
            else if (shiftExp instanceof ShiftRightExp)
                e1 = new ShiftRight(e1, e2);
            else
                e1 = new UnsignedShiftRight(e1, e2);
        }
        return e1;
    }

    /**
     * f0 -> ShiftLeftExpression()
     *       | ShiftRightExpression()
     *       | ShiftRightUnsignedExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ShiftExpressionRest n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> "<<"
     * f1 -> AdditiveExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ShiftLeftExpression n) {
        Expression e = (Expression) visitDispatch(n.f1);
        return new ShiftLeftExp(e);
    }

    /**
     * f0 -> ">>"
     * f1 -> AdditiveExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ShiftRightExpression n) {
        Expression e = (Expression) visitDispatch(n.f1);
        return new ShiftRightExp(e);
    }

    /**
     * f0 -> ">>>"
     * f1 -> AdditiveExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ShiftRightUnsignedExpression n) {
        Expression e = (Expression) visitDispatch(n.f1);
        return new ShiftRightUnsignedExp(e);
    }

    /**
     * f0 -> MultiplicativeExpression()
     * f1 -> ( AdditiveExpressionRest() )*
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.AdditiveExpression n) {
        Expression e1 = (Expression) visitDispatch(n.f0);
        NodeList<AdditiveExp> operands = (NodeList<AdditiveExp>)visitDispatch(n.f1);
        Iterator<AdditiveExp> iterator = operands.iterator();
        while (iterator.hasNext()) {
            AdditiveExp additiveExp = iterator.next();
            Expression e2 = additiveExp.e;
            if (additiveExp instanceof PlusExp)
                e1 = new Plus(e1, e2);
            else
                e1 = new Minus(e1, e2);
        }
        return e1;
    }

    /**
     * f0 -> PlusOffset()
     *       | PlusExpression()
     *       | MinusExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.AdditiveExpressionRest n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> "+"
     * f1 -> "["
     * f2 -> ExpressionList()
     * f3 -> "]"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.PlusOffset n) {
        NodeList<Expression> expressionList = (NodeList<Expression>) visitDispatch(n.f2);
        Expression[] expressions = ArrayMaker.expressionArray(expressionList);
        return new PlusExp(new PointConstructor(expressions));
    }

    /**
     * f0 -> "+"
     * f1 -> MultiplicativeExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.PlusExpression n) {
        Expression e = (Expression) visitDispatch(n.f1);
        return new PlusExp(e);
    }

    /**
     * f0 -> "-"
     * f1 -> MultiplicativeExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.MinusExpression n) {
        Expression e = (Expression) visitDispatch(n.f1);
        return new MinusExp(e);
    }

    /**
     * f0 -> Expression()
     * f1 -> ( ArgumentRest() )*
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ExpressionList n) {
        NodeList<Expression> allExpressions = new NodeList<Expression>();
        Expression e0 = (Expression) visitDispatch(n.f0);
        allExpressions.add(e0);
        NodeList<Expression> restExpressions =
                (NodeList<Expression>) visitDispatch(n.f1);
        allExpressions.add(restExpressions);
        return allExpressions;
    }
    /**
     * f0 -> ","
     * f1 -> Expression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ArgumentRest n) {
        return visitDispatch(n.f1);
    }


    /**
     * f0 -> MapExpression()
     * f1 -> ( MultiplicativeExpressionRest() )*
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.MultiplicativeExpression n) {
        Expression e1 = (Expression) visitDispatch(n.f0);
        NodeList<MultiplicativeExp> operands = (NodeList<MultiplicativeExp>)visitDispatch(n.f1);
        Iterator<MultiplicativeExp> iterator = operands.iterator();
        while (iterator.hasNext()) {
            MultiplicativeExp multiplicativeExp = iterator.next();
            Expression e2 = multiplicativeExp.e;
            if (multiplicativeExp instanceof TimesExp)
                e1 = new Times(e1, e2);
            else if (multiplicativeExp instanceof DivideExp)
                e1 = new Divide(e1, e2);
            else
                e1 = new Modulus(e1, e2);
        }
        return e1;
    }

    /**
     * f0 -> TimesOffset()
     *       | TimesExpression()
     *       | DivideOffset()
     *       | DivideExpression()
     *       | ModulusExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.MultiplicativeExpressionRest n) {
        return visitDispatch(n.f0);
    }
    /**
     * f0 -> "*"
     * f1 -> "["
     * f2 -> ExpressionList()
     * f3 -> "]"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.TimesOffset n) {
        NodeList<Expression> expressionList = (NodeList<Expression>) visitDispatch(n.f2);
        Expression[] expressions = ArrayMaker.expressionArray(expressionList);
        return new TimesExp(new PointConstructor(expressions));
    }
    /**
     * f0 -> "*"
     * f1 -> MapExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.TimesExpression n) {
        Expression e = (Expression) visitDispatch(n.f1);
        return new TimesExp(e);
    }
    /**
     * f0 -> "/"
     * f1 -> "["
     * f2 -> ExpressionList()
     * f3 -> "]"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DivideOffset n) {
        NodeList<Expression> expressionList = (NodeList<Expression>) visitDispatch(n.f2);
        Expression[] expressions = ArrayMaker.expressionArray(expressionList);
        return new DivideExp(new PointConstructor(expressions));
    }
    /**
     * f0 -> "/"
     * f1 -> MapExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DivideExpression n) {
        Expression e = (Expression) visitDispatch(n.f1);
        return new DivideExp(e);
    }
    /**
     * f0 -> "%"
     * f1 -> MapExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ModulusExpression n) {
        Expression e = (Expression) visitDispatch(n.f1);
        return new ModulusExp(e);
    }

    /**
     * f0 -> RegionExpression()
     * f1 -> [ MapExpressionRest() ]
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.MapExpression n) {
        Expression e1 = (Expression) visitDispatch(n.f0);
        OptionalNode optional = (OptionalNode)visitDispatch(n.f1);
        if (optional.isPresent()) {
            Expression e2 = (Expression)((SomeNode)optional).get();
            e1 = new DistConstructor(e1, e2);
        }
        return e1;
    }

    /**
     * f0 -> "->"
     * f1 -> UnaryExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.MapExpressionRest n) {
        return visitDispatch(n.f1);
    }

    /**
     * f0 -> RegionConstant()
     *       | UnaryExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.RegionExpression n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> "["
     * f1 -> ColonExpression()
     * f2 -> ( ColonRest() )*
     * f3 -> "]"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.RegionConstant n) {
        // Hairy!
        HolderNode<RegionConstructor.Dimension> firstHolderNode =
                (HolderNode<RegionConstructor.Dimension>) visitDispatch(n.f1);
        NodeList<HolderNode<RegionConstructor.Dimension>> restNodeList =
                (NodeList<HolderNode<RegionConstructor.Dimension>>) visitDispatch(n.f2);
        HolderNode<RegionConstructor.Dimension>[] restDimsHolderNodes =
                ArrayMaker.holderNodeArray(restNodeList);
        RegionConstructor.Dimension[] allDims =
                new RegionConstructor.Dimension[restDimsHolderNodes.length + 1];
        allDims[0] = firstHolderNode.get();
        for (int i = 1; i < allDims.length; i++) {
            allDims[i] = restDimsHolderNodes[i - 1].get();

        }
        return new RegionConstructor(allDims);
    }

    /**
     * f0 -> ","
     * f1 -> ColonExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ColonRest n) {
        return visitDispatch(n.f1);
    }

    /**
     * f0 -> ColonPair()
     *       | Expression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ColonExpression n) {
        Node node = visitDispatch(n.f0);
        if (!(node instanceof HolderNode)) {
            Expression e = (Expression) node;
            return new HolderNode<RegionConstructor.Dimension>(new RegionConstructor.Dimension(e));
        }
        return node;
    }

    /**
     * f0 -> Expression()
     * f1 -> ":"
     * f2 -> Expression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ColonPair n) {
        Expression min = (Expression) visitDispatch(n.f0);
        Expression max = (Expression) visitDispatch(n.f2);
        return new HolderNode<RegionConstructor.Dimension>(new RegionConstructor.Dimension(min, max));
    }

    /**
     * f0 -> SinExpression()
     *       | CosExpression()
     *       | PowExpression()
     *       | ExpExpression()
     *       | SqrtExpression()
     *       | AbsExpression()
     *       | MinExpression()
     *       | MaxExpression()
     *       | LogExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.MathExpression n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> "Math"
     * f1 -> "."
     * f2 -> "sin"
     * f3 -> "("
     * f4 -> Expression()
     * f5 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.SinExpression n) {
        Expression arg = (Expression)visitDispatch(n.f4);
        MethodCall methodCall = new MethodCall(
                new jsrc.x10.ast.tree.expression.id.Id("Math"), new jsrc.x10.ast.tree.expression.id.Id("sin"), new Expression[]{arg}
        );
        setLoc(methodCall, n.f3);
        return methodCall;
    }

    /**
     * f0 -> "Math"
     * f1 -> "."
     * f2 -> "cos"
     * f3 -> "("
     * f4 -> Expression()
     * f5 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.CosExpression n) {
        Expression arg = (Expression)visitDispatch(n.f4);
        MethodCall methodCall = new MethodCall(
                new jsrc.x10.ast.tree.expression.id.Id("Math"), new jsrc.x10.ast.tree.expression.id.Id("cos"), new Expression[]{arg}
        );
        setLoc(methodCall, n.f3);
        return methodCall;
    }

    /**
     * f0 -> "Math"
     * f1 -> "."
     * f2 -> "pow"
     * f3 -> "("
     * f4 -> Expression()
     * f5 -> ","
     * f6 -> Expression()
     * f7 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.PowExpression n) {
        Expression arg1 = (Expression)visitDispatch(n.f4);
        Expression arg2 = (Expression)visitDispatch(n.f6);
        MethodCall methodCall = new MethodCall(
                new jsrc.x10.ast.tree.expression.id.Id("Math"), new jsrc.x10.ast.tree.expression.id.Id("pow"), new Expression[]{arg1, arg2}
        );
        setLoc(methodCall, n.f3);
        return methodCall;

    }

    /**
     * f0 -> "Math"
     * f1 -> "."
     * f2 -> "exp"
     * f3 -> "("
     * f4 -> Expression()
     * f5 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ExpExpression n) {
        Expression arg = (Expression)visitDispatch(n.f4);
        MethodCall methodCall = new MethodCall(
                new jsrc.x10.ast.tree.expression.id.Id("Math"), new jsrc.x10.ast.tree.expression.id.Id("exp"), new Expression[]{arg}
        );
        setLoc(methodCall, n.f3);
        return methodCall;
    }

    /**
     * f0 -> "Math"
     * f1 -> "."
     * f2 -> "sqrt"
     * f3 -> "("
     * f4 -> Expression()
     * f5 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.SqrtExpression n) {
        Expression arg = (Expression)visitDispatch(n.f4);
        MethodCall methodCall = new MethodCall(
                new jsrc.x10.ast.tree.expression.id.Id("Math"), new jsrc.x10.ast.tree.expression.id.Id("sqrt"), new Expression[]{arg}
        );
        setLoc(methodCall, n.f3);
        return methodCall;
    }

    /**
     * f0 -> "Math"
     * f1 -> "."
     * f2 -> "abs"
     * f3 -> "("
     * f4 -> Expression()
     * f5 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.AbsExpression n) {
        Expression arg = (Expression)visitDispatch(n.f4);
        MethodCall methodCall = new MethodCall(
                new jsrc.x10.ast.tree.expression.id.Id("Math"), new jsrc.x10.ast.tree.expression.id.Id("abs"), new Expression[]{arg}
        );
        setLoc(methodCall, n.f3);
        return methodCall;
    }

    /**
     * f0 -> "Math"
     * f1 -> "."
     * f2 -> "min"
     * f3 -> "("
     * f4 -> Expression()
     * f5 -> ","
     * f6 -> Expression()
     * f7 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.MinExpression n) {
        Expression arg1 = (Expression)visitDispatch(n.f4);
        Expression arg2 = (Expression)visitDispatch(n.f6);
        MethodCall methodCall = new MethodCall(
                new jsrc.x10.ast.tree.expression.id.Id("Math"), new jsrc.x10.ast.tree.expression.id.Id("min"), new Expression[]{arg1, arg2}
        );
        setLoc(methodCall, n.f3);
        return methodCall;
    }

    /**
     * f0 -> "Math"
     * f1 -> "."
     * f2 -> "max"
     * f3 -> "("
     * f4 -> Expression()
     * f5 -> ","
     * f6 -> Expression()
     * f7 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.MaxExpression n) {
        Expression arg1 = (Expression)visitDispatch(n.f4);
        Expression arg2 = (Expression)visitDispatch(n.f6);
        MethodCall methodCall = new MethodCall(
                new jsrc.x10.ast.tree.expression.id.Id("Math"), new jsrc.x10.ast.tree.expression.id.Id("max"), new Expression[]{arg1, arg2}
        );
        setLoc(methodCall, n.f3);
        return methodCall;
    }

    /**
     * f0 -> "Math"
     * f1 -> "."
     * f2 -> "log"
     * f3 -> "("
     * f4 -> Expression()
     * f5 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.LogExpression n) {
        Expression arg = (Expression)visitDispatch(n.f4);
        MethodCall methodCall = new MethodCall(
                new jsrc.x10.ast.tree.expression.id.Id("Math"), new jsrc.x10.ast.tree.expression.id.Id("log"), new Expression[]{arg}
        );
        setLoc(methodCall, n.f3);
        return methodCall;
    }

    /**
     * f0 -> UnaryPlusExpression()
     *       | UnaryMinusExpression()
     *       | PreIncrementExpression()
     *       | PreDecrementExpression()
     *       | ComplimentExpression()
     *       | NotExpression()
     *       | CoercionToIntExpression()
     *       | CoercionToDoubleExpression()
     *       | CoercionToLongExpression()
     *       | CoercionToShortExpression()
     *       | CoercionToByteExpression()
     *       | TypeAnnotatedExpression()
     *       | PostfixExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.UnaryExpression n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> "+"
     * f1 -> PrimaryExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.UnaryPlusExpression n) {
        Expression expression = (Expression)visitDispatch(n.f1);
        return new UnaryPlus(expression);
    }

    /**
     * f0 -> "-"
     * f1 -> PrimaryExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.UnaryMinusExpression n) {
        Expression expression = (Expression)visitDispatch(n.f1);
        return new UnaryMinus(expression);
    }

    /**
     * f0 -> "++"
     * f1 -> PrimaryExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.PreIncrementExpression n) {
        Expression expression = (Expression)visitDispatch(n.f1);
        return new PreIncrement(expression);
    }

    /**
     * f0 -> "--"
     * f1 -> PrimaryExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.PreDecrementExpression n) {
        Expression expression = (Expression)visitDispatch(n.f1);
        return new PreDecrement(expression);
    }

    /**
     * f0 -> "~"
     * f1 -> UnaryExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ComplimentExpression n) {
        Expression expression = (Expression)visitDispatch(n.f1);
        return new Complement(expression);
    }

    /**
     * f0 -> "!"
     * f1 -> UnaryExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.NotExpression n) {
        Expression expression = (Expression)visitDispatch(n.f1);
        return new Not(expression);
    }


    /**
     * f0 -> "("
     * f1 -> "int"
     * f2 -> ")"
     * f3 -> UnaryExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.CoercionToIntExpression n) {
        Expression operand = (Expression)visitDispatch(n.f3);
        return new Cast(IntType.instance(), operand);
    }

    /**
     * f0 -> "("
     * f1 -> "double"
     * f2 -> ")"
     * f3 -> UnaryExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.CoercionToDoubleExpression n) {
        Expression operand = (Expression)visitDispatch(n.f3);
        return new Cast(DoubleType.instance(), operand);
    }

    /**
     * f0 -> "("
     * f1 -> "long"
     * f2 -> ")"
     * f3 -> UnaryExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.CoercionToLongExpression n) {
        Expression operand = (Expression)visitDispatch(n.f3);
        return new Cast(LongType.instance(), operand);
    }

    /**
     * f0 -> "("
     * f1 -> "short"
     * f2 -> ")"
     * f3 -> UnaryExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.CoercionToShortExpression n) {
        Expression operand = (Expression)visitDispatch(n.f3);
        return new Cast(ShortType.instance(), operand);
    }

    /**
     * f0 -> "("
     * f1 -> "byte"
     * f2 -> ")"
     * f3 -> UnaryExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.CoercionToByteExpression n) {
        Expression operand = (Expression)visitDispatch(n.f3);
        return new Cast(ByteType.instance(), operand);
    }

    /**
     * f0 -> "("
     * f1 -> TypeAnnotation()
     * f2 -> ")"
     * f3 -> UnaryExpression()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.TypeAnnotatedExpression n) {
        NonVoidType type = (NonVoidType)visitDispatch(n.f1);
        Expression operand = (Expression)visitDispatch(n.f3);
        return new Cast(type, operand);
    }

    /**
     * f0 -> UpdatableArrayType()
     *       | ValueArrayType()
     *       | DistType()
     *       | RegionType()
     *       | PointType()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.TypeAnnotation n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> PostIncrementExpression()
     *       | PostDecrementExpression()
     *       | PrimaryExpression()
     *       | Place()
     *       | FactoryBlock()
     *       | FactoryBlockCyclic()
     *       | FactoryEmptyRegion()
     *       | CurrentTime()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.PostfixExpression n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> "++"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.PostIncrementExpression n) {
        Expression e = (Expression) visitDispatch(n.f0);
        return new PostIncrement(e);
    }

    /**
     * f0 -> PrimaryExpression()
     * f1 -> "--"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.PostDecrementExpression n) {
        Expression e = (Expression) visitDispatch(n.f0);
        return new PostDecrement(e);
    }





    /**
     * f0 -> PrimaryPrefix()
     * f1 -> ( PrimarySuffix() )*
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.PrimaryExpression n) {
        Expression e1 = (Expression) visitDispatch(n.f0);
        NodeList<Node> operands = (NodeList<Node>)visitDispatch(n.f1);
        Iterator<Node> iterator = operands.iterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            if (node instanceof ArrayAccessExp) {
                ArrayAccessExp h = (ArrayAccessExp)node;
                e1 = new ArrayAccess(e1, h.expressions);
            }
            else if (node instanceof MethodCallExp) {
                MethodCallExp h = (MethodCallExp)node;
                MethodCall methodCall = new MethodCall(e1, h.methodName, h.args);
                methodCall.setLoc(h.sourceLoc);
                e1 = methodCall;
            }
            else if (node instanceof DistPropertyExp) {
                e1 = new FieldSelection(e1, Distribution.instance());
            }
            else if (node instanceof RegionPropertyExp) {
                e1 = new FieldSelection(e1, jsrc.x10.ast.tree.expression.id.specialids.x10.region.properties.Region.instance());
            }
            else if (node instanceof IdPropertyExp) {
                e1 = new FieldSelection(e1, jsrc.x10.ast.tree.expression.id.specialids.x10.place.properties.Id.instance());
            }
            else if (node instanceof DistAccessExp) {
                DistAccessExp h = (DistAccessExp)node;
                e1 = new DistAccess(e1, h.expressions);
            }
            else if (node instanceof ContainsExp) {
                ContainsExp h = (ContainsExp)node;
                e1 = new MethodCall(e1, Contains.instance(), new Expression[]{h.expression});
            }
            else if (node instanceof EqualsMethodExp) {
                EqualsMethodExp h = (EqualsMethodExp)node;
                e1 = new MethodCall(e1, Equals.instance(), new Expression[]{h.expression});
            }
            else if (node instanceof HighExp) {
                e1 = new MethodCall(e1, High.instance(), new Expression[] {new IntLiteral("0")});
            }
            else if (node instanceof LowExp) {
                e1 = new MethodCall(e1, Low.instance(), new Expression[] {new IntLiteral("0")});
            }
            else if (node instanceof ProjectionExp) {
                ProjectionExp h = (ProjectionExp)node;
                e1 = new MethodCall(e1, Projection.instance(), new Expression[]{h.expression});
            }
            else if (node instanceof NextExp) {
                e1 = new MethodCall(e1, Next.instance(), new Expression[0]);
            }
            else if (node instanceof PrevExp) {
                e1 = new MethodCall(e1, Prev.instance(), new Expression[0]);
            }
            else if (node instanceof IsFirstExp) {
                 IsFirstExp isFirstExp = (IsFirstExp) node;
                MethodCall methodCall = new MethodCall(e1, IsFirst.instance(), new Expression[0]);
                methodCall.setLoc(isFirstExp.sourceLoc);
                e1 = methodCall;
            }
            else if (node instanceof IsLastExp) {
                e1 = new MethodCall(e1, IsLast.instance(), new Expression[0]);
            }
            else if (node instanceof CoordExp) {
                CoordExp h = (CoordExp)node;
                e1 = new Coord(e1, h.expression, h.intLiteral);
            }
            else if (node instanceof OrdinalExp) {
                OrdinalExp h = (OrdinalExp)node;
                e1 = new Ordinal(e1, h.expression);
            }
            else if (node instanceof MaxExp) {
                return new Max(e1);
            }
            else if (node instanceof SumExp) {
                return new Sum(e1);
            }
            else if (node instanceof SizeExp) {
                e1 = new MethodCall(e1, Size.instance(), new Expression[0]);
            }
            else if (node instanceof FieldSelectionExp) {
                FieldSelectionExp h = (FieldSelectionExp)node;
                e1 = new FieldSelection(e1, h.fieldName);
            }
        }
        return e1;
    }

    /**
     * f0 -> Literal()
     *       | MathExpression()
     *       | This()
     *       | ExpressionInParentheses()
     *       | AllocationExpression()
     *       | MethodCall()
     *       | Identifier()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.PrimaryPrefix n) {
        Node node = visitDispatch(n.f0);
        if (node instanceof MethodCallExp) {
            MethodCallExp m = (MethodCallExp)node;
            MethodCall methodCall = new MethodCall(m.methodName, m.args);
            methodCall.setLoc(m.sourceLoc);
            return methodCall;
        }
        return node;
    }

    /**
     * f0 -> "this"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.This n) {
        return This.instance();
    }

    /**
     * f0 -> "("
     * f1 -> Expression()
     * f2 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ExpressionInParentheses n) {
        Expression e = (Expression) visitDispatch(n.f1);
        return new Parenthesized(e);
//        return e;
    }

    /**
     * f0 -> "place.places"
     * f1 -> "("
     * f2 -> Expression()
     * f3 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.Place n) {
        Expression e = (Expression) visitDispatch(n.f2);
        return new jsrc.x10.ast.tree.expression.MethodCall(
                Place.instance(), Places.instance(), new Expression[]{e});
    }

    /**
     * f0 -> "dist.factory.block"
     * f1 -> "("
     * f2 -> Expression()
     * f3 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.FactoryBlock n) {
        Expression e = (Expression) visitDispatch(n.f2);
        return new jsrc.x10.ast.tree.expression.MethodCall(
                Dist.instance(), BlockFactory.instance(), new Expression[]{e});
    }

    /**
     * f0 -> "dist.factory.blockCyclic"
     * f1 -> "("
     * f2 -> Expression()
     * f3 -> ","
     * f4 -> Expression()
     * f5 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.FactoryBlockCyclic n) {
        Expression e1 = (Expression) visitDispatch(n.f2);
        Expression e2 = (Expression) visitDispatch(n.f4);
        return new jsrc.x10.ast.tree.expression.MethodCall(
                Dist.instance(), BlockCyclicFactory.instance(), new Expression[]{e1, e2});
    }

    /**
     * f0 -> "region.factory.emptyRegion"
     * f1 -> "("
     * f2 -> IntegerLiteral()
     * f3 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.FactoryEmptyRegion n) {
        Expression e = (Expression) visitDispatch(n.f2);
        return new jsrc.x10.ast.tree.expression.MethodCall(
                jsrc.x10.ast.tree.expression.id.specialids.x10.classes.Region.instance(), EmptyFactory.instance(), new Expression[]{e});
    }

    /**
     * f0 -> "System.currentTimeMillis"
     * f1 -> "("
     * f2 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.CurrentTime n) {
        MethodCall methodCall = new MethodCall(
                jsrc.x10.ast.tree.expression.id.specialids.x10.classes.System.instance(),
                CurrentTime.instance(),
                new Expression[0]);
        setLoc(methodCall, n.f1);
        return methodCall;
    }

    /**
     * f0 -> ArrayAccess()
     *       | DotDistribution()
     *       | DotRegion()
     *       | DotId()
     *       | DotGet()
     *       | DotContainsPoint()
     *       | DotContains()
     *       | DotEquals()
     *       | DotHigh()
     *       | DotLow()
     *       | DotRank()
     *       | DotNext()
     *       | DotPrev()
     *       | DotIsFirst()
     *       | DotIsLast()
     *       | DotCoord()
     *       | DotOrdinalPoint()
     *       | DotOrdinal()
     *       | DotMax()
     *       | DotSum()
     *       | DotSize()
     *       | DotMethodCall()
     *       | DotIdentifier()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.PrimarySuffix n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> "["
     * f1 -> ExpressionList()
     * f2 -> "]"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ArrayAccess n) {
        NodeList<Expression> expressionNodeList =
                (NodeList<Expression>) visitDispatch(n.f1);
        Expression[] expressions = ArrayMaker.expressionArray(expressionNodeList);
        return new ArrayAccessExp(expressions);
    }

    /**
     * f0 -> "."
     * f1 -> MethodCall()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DotMethodCall n) {
        return visitDispatch(n.f1);
    }

    /**
     * f0 -> Identifier()
     * f1 -> "("
     * f2 -> ( ExpressionList() )?
     * f3 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.MethodCall n) {
        jsrc.x10.ast.tree.expression.id.Id methodName = (jsrc.x10.ast.tree.expression.id.Id) visitDispatch(n.f0);
        OptionalNode optional = (OptionalNode)visitDispatch(n.f2);
        Expression[] args;
        if (optional.isPresent()) {
            NodeList<Expression> expressionNodeList =
                    (NodeList<Expression>)((SomeNode)optional).get();
            args = ArrayMaker.expressionArray(expressionNodeList);
        } else {
            args = new Expression[0];
        }
        MethodCallExp methodCallExp = new MethodCallExp(methodName, args);
        methodCallExp.setLoc(n.f1);
        return methodCallExp;
    }

    /**
     * f0 -> "."
     * f1 -> "distribution"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DotDistribution n) {
        return DistPropertyExp.instance();
    }

    /**
     * f0 -> "."
     * f1 -> "region"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DotRegion n) {
        return RegionPropertyExp.instance();
    }

    /**
     * f0 -> "."
     * f1 -> "id"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DotId n) {
        return IdPropertyExp.instance();
    }

    /**
     * f0 -> "."
     * f1 -> "get"
     * f2 -> "("
     * f3 -> ExpressionList()
     * f4 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DotGet n) {
        NodeList<Expression> expressionNodeList =
                (NodeList<Expression>) visitDispatch(n.f3);
        Expression[] expressions = ArrayMaker.expressionArray(expressionNodeList);
        return new DistAccessExp(expressions);
    }

    /**
     * f0 -> "."
     * f1 -> "contains"
     * f2 -> "("
     * f3 -> "["
     * f4 -> ExpressionList()
     * f5 -> "]"
     * f6 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DotContainsPoint n) {
        NodeList<Expression> expressionList = (NodeList<Expression>) visitDispatch(n.f4);
        Expression[] expressions = ArrayMaker.expressionArray(expressionList);
        return new ContainsExp(new PointConstructor(expressions));
    }

    /**
     * f0 -> "."
     * f1 -> "contains"
     * f2 -> "("
     * f3 -> Expression()
     * f4 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DotContains n) {
        Expression e = (Expression) visitDispatch(n.f3);
        return new ContainsExp(e);
    }

    /**
     * f0 -> "."
     * f1 -> "equals"
     * f2 -> "("
     * f3 -> Expression()
     * f4 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DotEquals n) {
        Expression e = (Expression) visitDispatch(n.f3);
        return new EqualsMethodExp(e);
    }

    /**
     * f0 -> "."
     * f1 -> "high"
     * f2 -> "("
     * f3 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DotHigh n) {
        return HighExp.instance();
    }

    /**
     * f0 -> "."
     * f1 -> "low"
     * f2 -> "("
     * f3 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DotLow n) {
        return LowExp.instance();
    }

    /**
     * f0 -> "."
     * f1 -> "rank"
     * f2 -> "("
     * f3 -> Expression()
     * f4 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DotRank n) {
        Expression e = (Expression) visitDispatch(n.f3);
        return new ProjectionExp(e);
    }

    /**
     * f0 -> "."
     * f1 -> "next"
     * f2 -> "("
     * f3 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DotNext n) {
        return NextExp.instance();
    }

    /**
     * f0 -> "."
     * f1 -> "prev"
     * f2 -> "("
     * f3 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DotPrev n) {
        return PrevExp.instance();
    }

    /**
     * f0 -> "."
     * f1 -> "isFirst"
     * f2 -> "("
     * f3 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DotIsFirst n) {
        IsFirstExp isFirstExp = new IsFirstExp();
        isFirstExp.setLoc(n.f0);
        return isFirstExp;
    }

    /**
     * f0 -> "."
     * f1 -> "isLast"
     * f2 -> "("
     * f3 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DotIsLast n) {
        return IsLastExp.instance();
    }

    /**
     * f0 -> "."
     * f1 -> "coord"
     * f2 -> "("
     * f3 -> Expression()
     * f4 -> ")"
     * f5 -> "["
     * f6 -> IntegerLiteral()
     * f7 -> "]"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DotCoord n) {
        Expression expression = (Expression) visitDispatch(n.f3);
        IntLiteral intLiteral = (IntLiteral) visitDispatch(n.f6);
        return new CoordExp(expression, intLiteral);
    }

    /**
     * f0 -> "."
     * f1 -> "ordinal"
     * f2 -> "("
     * f3 -> "["
     * f4 -> ExpressionList()
     * f5 -> "]"
     * f6 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DotOrdinalPoint n) {
        NodeList<Expression> expressionList = (NodeList<Expression>) visitDispatch(n.f4);
        Expression[] expressions = ArrayMaker.expressionArray(expressionList);
        return new OrdinalExp(new PointConstructor(expressions));
    }

    /**
     * f0 -> "."
     * f1 -> "ordinal"
     * f2 -> "("
     * f3 -> Expression()
     * f4 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DotOrdinal n) {
        Expression expression = (Expression) visitDispatch(n.f3);
        return new OrdinalExp(expression);
    }

    /**
     * f0 -> "."
     * f1 -> "max"
     * f2 -> "("
     * f3 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DotMax n) {
        return MaxExp.instance();

    }

    /**
     * f0 -> "."
     * f1 -> "sum"
     * f2 -> "("
     * f3 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DotSum n) {
        return MaxExp.instance();
    }

    /**
     * f0 -> "."
     * f1 -> "size"
     * f2 -> "("
     * f3 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DotSize n) {
        return SizeExp.instance();
    }

    /**
     * f0 -> "."
     * f1 -> Identifier()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DotIdentifier n) {
        jsrc.x10.ast.tree.expression.id.Id id = (jsrc.x10.ast.tree.expression.id.Id) visitDispatch(n.f1);
        return new FieldSelectionExp(id);
    }

    /**
     * f0 -> NewObject()
     *       | NewValueArray()
     *       | NewUpdatableArray()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.AllocationExpression n) {
        return visitDispatch(n.f0);
    }

    /**
     * f0 -> "new"
     * f1 -> Identifier()
     * f2 -> "("
     * f3 -> [ ExpressionList() ]
     * f4 -> ")"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.NewObject n) {
        jsrc.x10.ast.tree.expression.id.Id className = (jsrc.x10.ast.tree.expression.id.Id)visitDispatch(n.f1);

        NodeList<Expression> argExpresionList;
        OptionalNode optionalArgExpressionList = (OptionalNode)visitDispatch(n.f3);
        Expression[] argExpressions;
        if (optionalArgExpressionList.isPresent()) {
            argExpresionList = (NodeList<Expression>)((SomeNode)optionalArgExpressionList).get();
            argExpressions = ArrayMaker.expressionArray(argExpresionList);
        } else
            argExpressions = new Expression[0];

        return new New(new DefinedType(className.lexeme), argExpressions);
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
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.NewValueArray n) {
        ScalarType elementType = (ScalarType) visitDispatch(n.f1);
        jsrc.x10.ast.tree.expression.id.Id regionName = (jsrc.x10.ast.tree.expression.id.Id)visitDispatch(n.f4);
        NewDistArray.ArrayInit arrayInit = (NewDistArray.ArrayInit) visitDispatch(n.f6);
        return new NewDistArray(true, elementType, regionName, arrayInit);
    }

    /**
     * f0 -> "new"
     * f1 -> NonArrayType()
     * f2 -> "["
     * f3 -> Identifier()
     * f4 -> "]"
     * f5 -> [ ArrayInitializer() ]
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.NewUpdatableArray n) {
        ScalarType elementType = (ScalarType) visitDispatch(n.f1);
        jsrc.x10.ast.tree.expression.id.Id regionName = (jsrc.x10.ast.tree.expression.id.Id)visitDispatch(n.f3);
        OptionalNode optionalNode = (OptionalNode) visitDispatch(n.f5);
        if (optionalNode.isPresent()) {
            NewDistArray.ArrayInit arrayInit = (NewDistArray.ArrayInit)((SomeNode) optionalNode).get();
            return new NewDistArray(elementType, regionName, arrayInit);
        } else
            return new NewDistArray(elementType, regionName);

    }

    /**
     * f0 -> "("
     * f1 -> PointType()
     * f2 -> ExplodedSpecification()
     * f3 -> ")"
     * f4 -> Block()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.ArrayInitializer n) {
        PointType pointType = (PointType) visitDispatch(n.f1);
        PointFormalVar formal = ((HolderNode<PointFormalVar>) visitDispatch(n.f2)).get();
        Block block = (Block) visitDispatch(n.f4);
        return new NewDistArray.ArrayInit(pointType, formal, block);
    }

    /**
     * f0 -> IntegerLiteral()
     *       | LongLiteral()
     *       | HexLiteral()
     *       | FloatingPointLiteral()
     *       | StringLiteral()
     *       | True()
     *       | False()
     *       | HereLiteral()
     *       | PlaceFirstPlace()
     *       | PlaceMaxPlaces()
     *       | DistUnique()
     *       | JavaIntegerSize()
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.Literal n) {
        return visitDispatch(n.f0);
    }


    /**
     * f0 -> <INTEGER_LITERAL>
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.IntegerLiteral n) {
        return new IntLiteral(n.f0.toString());
    }

    /**
     * f0 -> <LONG_LITERAL>
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.LongLiteral n) {
        return new LongLiteral(n.f0.toString());
    }

    /**
     * f0 -> <HEX_LITERAL>
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.HexLiteral n) {
        return new HexLiteral(n.f0.toString());
    }

    /**
     * f0 -> <FLOATING_POINT_LITERAL>
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.FloatingPointLiteral n) {
        return new FloatLiteral(n.f0.toString());
    }

    /**
     * f0 -> <STRING_LITERAL>
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.StringLiteral n) {
        return new StringLiteral(n.f0.toString());
    }

    /**
     * f0 -> "true"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.True n) {
        return True.instance();
    }

    /**
     * f0 -> "false"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.False n) {
        return False.instance();
    }

    /**
     * f0 -> "here"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.HereLiteral n) {
        return Here.instance();
    }

    /**
     * f0 -> "java.lang.Integer.SIZE"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.JavaIntegerSize n) {
        return IntegerSize.instance();
    }

    /**
     * f0 -> "place.FIRST_PLACE"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.PlaceFirstPlace n) {
        return new FieldSelection(Place.instance(), FirstPlace.instance());
    }

    /**
     * f0 -> "place.MAX_PLACES"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.PlaceMaxPlaces n) {
        return new FieldSelection(Place.instance(), MaxPlaces.instance());
    }

    /**
     * f0 -> "dist.UNIQUE"
     */
    public Node visit(jsrc.px10.syntaxanalyser.syntaxtree.DistUnique n) {
        return new jsrc.x10.ast.tree.expression.MethodCall(
                Dist.instance(), UniqueFactory.instance(), new Expression[0]);
    }

// ---------------------------------------------------------------------------------

    private void setLoc(VarDeclOrSt statement, NodeToken token) {
        statement.setLoc(token.beginLine, token.beginColumn);
    }

    private void setLoc(MethodCall methodCall, NodeToken token) {
        methodCall.setLoc(token.beginLine, token.beginColumn);
    }

    private void setLoc(VarDeclOrSt statement1, VarDeclOrSt statement2) {
        statement1.setLoc(statement2.sourceLoc.lineNo, statement2.sourceLoc.columnNo);
    }

    private void setLoc(MethodCall methodCall1, MethodCall methodCall2) {
        methodCall1.setLoc(methodCall2.sourceLoc.lineNo, methodCall2.sourceLoc.columnNo);
    }

// ---------------------------------------------------------------------------------  

/*
//     * f0 -> Identifier()
//     * f1 -> "||"
//     * f2 -> "("
//     * f3 -> "["
//     * f4 -> Identifier()
//     * f5 -> ":"
//     * f6 -> Identifier()
//     * f7 -> "]"
//     * f8 -> "->"
//     * f9 -> Identifier()
//     * f10 -> ")"

    public Node visit(frontend.plasmax10.syntaxanalysis.syntaxtree.DistributionUnionExpression n) {
        String operand1Name = ((Id)visitDispatch(n.f0)).name;
        String operand2rangeStartName = ((Id)visitDispatch(n.f4)).name;
        String operand2rangeEndName = ((Id)visitDispatch(n.f6)).name;
        String operand2placeName = ((Id)visitDispatch(n.f9)).name;
        return new DistributionUnion(operand1Name,
                operand2rangeStartName, operand2rangeEndName, operand2placeName);
    }
*/

}






