//
// Generated by JTB 1.3.2
//

package jsrc.px10.syntaxanalyser.visitor;
import jsrc.px10.syntaxanalyser.syntaxtree.*;
import java.util.*;

/**
 * All void visitors must implement this interface.
 */

public interface Visitor {

   //
   // void Auto class visitors
   //

   public void visit(NodeList n);
   public void visit(NodeListOptional n);
   public void visit(NodeOptional n);
   public void visit(NodeSequence n);
   public void visit(NodeToken n);

   //
   // User-generated visitor methods below
   //

   /**
    * f0 -> ( TopLevelDeclaration() )*
    * f1 -> <EOF>
    */
   public void visit(File n);

   /**
    * f0 -> MainClass()
    *       | ClassDeclaration()
    *       | ValueDeclaration()
    */
   public void visit(TopLevelDeclaration n);

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
   public void visit(MainClass n);

   /**
    * f0 -> [ Public() ]
    * f1 -> "class"
    * f2 -> Identifier()
    * f3 -> "{"
    * f4 -> ( ClassMember() )*
    * f5 -> "}"
    */
   public void visit(ClassDeclaration n);

   /**
    * f0 -> [ Public() ]
    * f1 -> "value"
    * f2 -> Identifier()
    * f3 -> "{"
    * f4 -> ( ValueMember() )*
    * f5 -> "}"
    */
   public void visit(ValueDeclaration n);

   /**
    * f0 -> ConstructorDeclaration()
    *       | MethodDeclaration()
    *       | ConstantDeclaration()
    *       | InitializableConstantDeclaration()
    *       | UpdatableFieldDeclaration()
    */
   public void visit(ClassMember n);

   /**
    * f0 -> ConstructorDeclaration()
    *       | MethodDeclaration()
    *       | ConstantDeclaration()
    *       | InitializableConstantDeclaration()
    */
   public void visit(ValueMember n);

   /**
    * f0 -> "public"
    * f1 -> Identifier()
    * f2 -> "("
    * f3 -> ( FormalParameterList() )?
    * f4 -> ")"
    * f5 -> Block()
    */
   public void visit(ConstructorDeclaration n);

   /**
    * f0 -> Public()
    *       | Private()
    */
   public void visit(Visibility n);

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
   public void visit(ConstantDeclaration n);

   /**
    * f0 -> Visibility()
    * f1 -> "final"
    * f2 -> Type()
    * f3 -> Identifier()
    * f4 -> ";"
    */
   public void visit(InitializableConstantDeclaration n);

   /**
    * f0 -> Visibility()
    * f1 -> Type()
    * f2 -> Identifier()
    * f3 -> ";"
    */
   public void visit(UpdatableFieldDeclaration n);

   /**
    * f0 -> "public"
    * f1 -> "static"
    */
   public void visit(PublicStatic n);

   /**
    * f0 -> "public"
    */
   public void visit(Public n);

   /**
    * f0 -> "private"
    */
   public void visit(Private n);

   /**
    * f0 -> MethodModifier()
    * f1 -> ReturnType()
    * f2 -> Identifier()
    * f3 -> "("
    * f4 -> ( FormalParameterList() )?
    * f5 -> ")"
    * f6 -> Block()
    */
   public void visit(MethodDeclaration n);

   /**
    * f0 -> PublicStatic()
    *       | Public()
    *       | Private()
    */
   public void visit(MethodModifier n);

   /**
    * f0 -> FormalParameter()
    * f1 -> ( FormalParameterRest() )*
    */
   public void visit(FormalParameterList n);

   /**
    * f0 -> FinalFormalParameter()
    */
   public void visit(FormalParameter n);

   /**
    * f0 -> ( "final" )?
    * f1 -> Type()
    * f2 -> Identifier()
    */
   public void visit(FinalFormalParameter n);

   /**
    * f0 -> ","
    * f1 -> FormalParameter()
    */
   public void visit(FormalParameterRest n);

   /**
    * f0 -> VoidType()
    *       | Type()
    */
   public void visit(ReturnType n);

   /**
    * f0 -> "void"
    */
   public void visit(VoidType n);

   /**
    * f0 -> UpdatableArrayType()
    *       | ValueArrayType()
    *       | NonArrayType()
    */
   public void visit(Type n);

   /**
    * f0 -> NonArrayType()
    * f1 -> "["
    * f2 -> ":"
    * f3 -> RankEquation()
    * f4 -> ( DistributionEquation() )?
    * f5 -> "]"
    */
   public void visit(UpdatableArrayType n);

   /**
    * f0 -> NonArrayType()
    * f1 -> "value"
    * f2 -> "["
    * f3 -> ":"
    * f4 -> RankEquation()
    * f5 -> "]"
    */
   public void visit(ValueArrayType n);

   /**
    * f0 -> "rank"
    * f1 -> "=="
    * f2 -> IntegerLiteral()
    */
   public void visit(RankEquation n);

   /**
    * f0 -> "&&"
    * f1 -> "distribution"
    * f2 -> "=="
    * f3 -> Identifier()
    */
   public void visit(DistributionEquation n);

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
   public void visit(NonArrayType n);

   /**
    * f0 -> "boolean"
    */
   public void visit(BooleanType n);

   /**
    * f0 -> "byte"
    */
   public void visit(ByteType n);

   /**
    * f0 -> "short"
    */
   public void visit(ShortType n);

   /**
    * f0 -> "int"
    */
   public void visit(IntegerType n);

   /**
    * f0 -> "long"
    */
   public void visit(LongType n);

   /**
    * f0 -> "double"
    */
   public void visit(DoubleType n);

   /**
    * f0 -> "String"
    */
   public void visit(StringType n);

   /**
    * f0 -> "place"
    */
   public void visit(PlaceType n);

   /**
    * f0 -> "dist"
    * f1 -> "("
    * f2 -> ":"
    * f3 -> RankEquation()
    * f4 -> ")"
    */
   public void visit(DistType n);

   /**
    * f0 -> "region"
    * f1 -> "("
    * f2 -> ":"
    * f3 -> RankEquation()
    * f4 -> ")"
    */
   public void visit(RegionType n);

   /**
    * f0 -> "point"
    * f1 -> "("
    * f2 -> ":"
    * f3 -> RankEquation()
    * f4 -> ")"
    */
   public void visit(PointType n);

   /**
    * f0 -> Identifier()
    */
   public void visit(ClassNameType n);

   /**
    * f0 -> <IDENTIFIER>
    */
   public void visit(Identifier n);

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
   public void visit(Statement n);

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "="
    * f2 -> Expression()
    * f3 -> ";"
    */
   public void visit(Assignment n);

   /**
    * f0 -> "async"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> Block()
    */
   public void visit(AsyncStatement n);

   /**
    * f0 -> "{"
    * f1 -> ( BlockStatement() )*
    * f2 -> "}"
    */
   public void visit(Block n);

   /**
    * f0 -> FinalVariableDeclaration()
    *       | UpdatableVariableDeclaration()
    *       | Statement()
    */
   public void visit(BlockStatement n);

   /**
    * f0 -> "final"
    * f1 -> Type()
    * f2 -> Identifier()
    * f3 -> "="
    * f4 -> Expression()
    * f5 -> ";"
    */
   public void visit(FinalVariableDeclaration n);

   /**
    * f0 -> Type()
    * f1 -> Identifier()
    * f2 -> "="
    * f3 -> Expression()
    * f4 -> ";"
    */
   public void visit(UpdatableVariableDeclaration n);

   /**
    * f0 -> "break"
    * f1 -> ";"
    */
   public void visit(BreakStatement n);

   /**
    * f0 -> "continue"
    * f1 -> ";"
    */
   public void visit(ContinueStatement n);

   /**
    * f0 -> "do"
    * f1 -> Statement()
    * f2 -> "while"
    * f3 -> "("
    * f4 -> Expression()
    * f5 -> ")"
    * f6 -> ";"
    */
   public void visit(DoStatement n);

   /**
    * f0 -> "finish"
    * f1 -> Statement()
    */
   public void visit(FinishStatement n);

   /**
    * f0 -> "if"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> Statement()
    * f5 -> [ ElseClause() ]
    */
   public void visit(IfStatement n);

   /**
    * f0 -> "else"
    * f1 -> Statement()
    */
   public void visit(ElseClause n);

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
   public void visit(LoopStatement n);

   /**
    * f0 -> Ateach()
    *       | For()
    *       | Foreach()
    */
   public void visit(LoopQualifier n);

   /**
    * f0 -> "ateach"
    */
   public void visit(Ateach n);

   /**
    * f0 -> "for"
    */
   public void visit(For n);

   /**
    * f0 -> "foreach"
    */
   public void visit(Foreach n);

   /**
    * f0 -> PointNameCoordinates()
    *       | PointName()
    *       | Coordinates()
    */
   public void visit(ExplodedSpecification n);

   /**
    * f0 -> PointName()
    * f1 -> Coordinates()
    */
   public void visit(PointNameCoordinates n);

   /**
    * f0 -> Identifier()
    */
   public void visit(PointName n);

   /**
    * f0 -> "["
    * f1 -> IdentifierList()
    * f2 -> "]"
    */
   public void visit(Coordinates n);

   /**
    * f0 -> Identifier()
    * f1 -> ( IdentifierRest() )*
    */
   public void visit(IdentifierList n);

   /**
    * f0 -> ","
    * f1 -> Identifier()
    */
   public void visit(IdentifierRest n);

   /**
    * f0 -> PostfixExpression()
    * f1 -> ";"
    */
   public void visit(PostfixStatement n);

   /**
    * f0 -> "System.out.println"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> ";"
    */
   public void visit(PrintlnStatement n);

   /**
    * f0 -> "System.out.print"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> ";"
    */
   public void visit(PrintStatement n);

   /**
    * f0 -> "System.err.println"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> ";"
    */
   public void visit(PrintErrorStatement n);

   /**
    * f0 -> "return"
    * f1 -> [ Expression() ]
    * f2 -> ";"
    */
   public void visit(ReturnStatement n);

   /**
    * f0 -> "switch"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> "{"
    * f5 -> ( SwitchEntry() )*
    * f6 -> "}"
    */
   public void visit(SwitchStatement n);

   /**
    * f0 -> SwitchLabel()
    * f1 -> ":"
    * f2 -> ( BlockStatement() )*
    */
   public void visit(SwitchEntry n);

   /**
    * f0 -> Case()
    *       | Default()
    */
   public void visit(SwitchLabel n);

   /**
    * f0 -> "case"
    * f1 -> Expression()
    */
   public void visit(Case n);

   /**
    * f0 -> "default"
    */
   public void visit(Default n);

   /**
    * f0 -> "throw"
    * f1 -> "new"
    * f2 -> "RuntimeException"
    * f3 -> "("
    * f4 -> Expression()
    * f5 -> ")"
    * f6 -> ";"
    */
   public void visit(ThrowStatement n);

   /**
    * f0 -> "while"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> Statement()
    */
   public void visit(WhileStatement n);

   /**
    * f0 -> ConditionalExpression()
    */
   public void visit(Expression n);

   /**
    * f0 -> ConditionalOrExpression()
    * f1 -> [ ConditionalExpressionRest() ]
    */
   public void visit(ConditionalExpression n);

   /**
    * f0 -> "?"
    * f1 -> Expression()
    * f2 -> ":"
    * f3 -> Expression()
    */
   public void visit(ConditionalExpressionRest n);

   /**
    * f0 -> ConditionalAndExpression()
    * f1 -> ( ConditionalOrExpressionRest() )*
    */
   public void visit(ConditionalOrExpression n);

   /**
    * f0 -> "||"
    * f1 -> ConditionalAndExpression()
    */
   public void visit(ConditionalOrExpressionRest n);

   /**
    * f0 -> InclusiveOrExpression()
    * f1 -> ( ConditionalAndExpressionRest() )*
    */
   public void visit(ConditionalAndExpression n);

   /**
    * f0 -> "&&"
    * f1 -> InclusiveOrExpression()
    */
   public void visit(ConditionalAndExpressionRest n);

   /**
    * f0 -> ExclusiveOrExpression()
    * f1 -> ( InclusiveOrExpressionRest() )*
    */
   public void visit(InclusiveOrExpression n);

   /**
    * f0 -> "|"
    * f1 -> ExclusiveOrExpression()
    */
   public void visit(InclusiveOrExpressionRest n);

   /**
    * f0 -> AndExpression()
    * f1 -> ( ExclusiveOrExpressionRest() )*
    */
   public void visit(ExclusiveOrExpression n);

   /**
    * f0 -> "^"
    * f1 -> AndExpression()
    */
   public void visit(ExclusiveOrExpressionRest n);

   /**
    * f0 -> EqualityExpression()
    * f1 -> ( AndExpressionRest() )*
    */
   public void visit(AndExpression n);

   /**
    * f0 -> "&"
    * f1 -> EqualityExpression()
    */
   public void visit(AndExpressionRest n);

   /**
    * f0 -> RelationalExpression()
    * f1 -> ( EqualityExpressionRest() )?
    */
   public void visit(EqualityExpression n);

   /**
    * f0 -> EqualsExpression()
    *       | NonEqualsExpression()
    */
   public void visit(EqualityExpressionRest n);

   /**
    * f0 -> "=="
    * f1 -> RelationalExpression()
    */
   public void visit(EqualsExpression n);

   /**
    * f0 -> "!="
    * f1 -> RelationalExpression()
    */
   public void visit(NonEqualsExpression n);

   /**
    * f0 -> ShiftExpression()
    * f1 -> [ RelationalExpressionRest() ]
    */
   public void visit(RelationalExpression n);

   /**
    * f0 -> LessThanExpression()
    *       | GreaterThanExpression()
    *       | LessThanEqualExpression()
    *       | GreaterThanEqualExpression()
    */
   public void visit(RelationalExpressionRest n);

   /**
    * f0 -> "<"
    * f1 -> ShiftExpression()
    */
   public void visit(LessThanExpression n);

   /**
    * f0 -> ">"
    * f1 -> ShiftExpression()
    */
   public void visit(GreaterThanExpression n);

   /**
    * f0 -> "<="
    * f1 -> ShiftExpression()
    */
   public void visit(LessThanEqualExpression n);

   /**
    * f0 -> ">="
    * f1 -> ShiftExpression()
    */
   public void visit(GreaterThanEqualExpression n);

   /**
    * f0 -> AdditiveExpression()
    * f1 -> ( ShiftExpressionRest() )*
    */
   public void visit(ShiftExpression n);

   /**
    * f0 -> ShiftLeftExpression()
    *       | ShiftRightExpression()
    *       | ShiftRightUnsignedExpression()
    */
   public void visit(ShiftExpressionRest n);

   /**
    * f0 -> "<<"
    * f1 -> AdditiveExpression()
    */
   public void visit(ShiftLeftExpression n);

   /**
    * f0 -> ">>"
    * f1 -> AdditiveExpression()
    */
   public void visit(ShiftRightExpression n);

   /**
    * f0 -> ">>>"
    * f1 -> AdditiveExpression()
    */
   public void visit(ShiftRightUnsignedExpression n);

   /**
    * f0 -> MultiplicativeExpression()
    * f1 -> ( AdditiveExpressionRest() )*
    */
   public void visit(AdditiveExpression n);

   /**
    * f0 -> PlusOffset()
    *       | PlusExpression()
    *       | MinusExpression()
    */
   public void visit(AdditiveExpressionRest n);

   /**
    * f0 -> "+"
    * f1 -> "["
    * f2 -> ExpressionList()
    * f3 -> "]"
    */
   public void visit(PlusOffset n);

   /**
    * f0 -> "+"
    * f1 -> MultiplicativeExpression()
    */
   public void visit(PlusExpression n);

   /**
    * f0 -> "-"
    * f1 -> MultiplicativeExpression()
    */
   public void visit(MinusExpression n);

   /**
    * f0 -> MapExpression()
    * f1 -> ( MultiplicativeExpressionRest() )*
    */
   public void visit(MultiplicativeExpression n);

   /**
    * f0 -> TimesOffset()
    *       | TimesExpression()
    *       | DivideOffset()
    *       | DivideExpression()
    *       | ModulusExpression()
    */
   public void visit(MultiplicativeExpressionRest n);

   /**
    * f0 -> "*"
    * f1 -> "["
    * f2 -> ExpressionList()
    * f3 -> "]"
    */
   public void visit(TimesOffset n);

   /**
    * f0 -> "*"
    * f1 -> MapExpression()
    */
   public void visit(TimesExpression n);

   /**
    * f0 -> "/"
    * f1 -> "["
    * f2 -> ExpressionList()
    * f3 -> "]"
    */
   public void visit(DivideOffset n);

   /**
    * f0 -> "/"
    * f1 -> MapExpression()
    */
   public void visit(DivideExpression n);

   /**
    * f0 -> "%"
    * f1 -> MapExpression()
    */
   public void visit(ModulusExpression n);

   /**
    * f0 -> RegionExpression()
    * f1 -> [ MapExpressionRest() ]
    */
   public void visit(MapExpression n);

   /**
    * f0 -> "->"
    * f1 -> UnaryExpression()
    */
   public void visit(MapExpressionRest n);

   /**
    * f0 -> RegionConstant()
    *       | UnaryExpression()
    */
   public void visit(RegionExpression n);

   /**
    * f0 -> "["
    * f1 -> ColonExpression()
    * f2 -> ( ColonRest() )*
    * f3 -> "]"
    */
   public void visit(RegionConstant n);

   /**
    * f0 -> ","
    * f1 -> ColonExpression()
    */
   public void visit(ColonRest n);

   /**
    * f0 -> ColonPair()
    *       | Expression()
    */
   public void visit(ColonExpression n);

   /**
    * f0 -> Expression()
    * f1 -> ":"
    * f2 -> Expression()
    */
   public void visit(ColonPair n);

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
   public void visit(MathExpression n);

   /**
    * f0 -> "Math"
    * f1 -> "."
    * f2 -> "sin"
    * f3 -> "("
    * f4 -> Expression()
    * f5 -> ")"
    */
   public void visit(SinExpression n);

   /**
    * f0 -> "Math"
    * f1 -> "."
    * f2 -> "cos"
    * f3 -> "("
    * f4 -> Expression()
    * f5 -> ")"
    */
   public void visit(CosExpression n);

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
   public void visit(PowExpression n);

   /**
    * f0 -> "Math"
    * f1 -> "."
    * f2 -> "exp"
    * f3 -> "("
    * f4 -> Expression()
    * f5 -> ")"
    */
   public void visit(ExpExpression n);

   /**
    * f0 -> "Math"
    * f1 -> "."
    * f2 -> "sqrt"
    * f3 -> "("
    * f4 -> Expression()
    * f5 -> ")"
    */
   public void visit(SqrtExpression n);

   /**
    * f0 -> "Math"
    * f1 -> "."
    * f2 -> "abs"
    * f3 -> "("
    * f4 -> Expression()
    * f5 -> ")"
    */
   public void visit(AbsExpression n);

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
   public void visit(MinExpression n);

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
   public void visit(MaxExpression n);

   /**
    * f0 -> "Math"
    * f1 -> "."
    * f2 -> "log"
    * f3 -> "("
    * f4 -> Expression()
    * f5 -> ")"
    */
   public void visit(LogExpression n);

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
   public void visit(UnaryExpression n);

   /**
    * f0 -> "+"
    * f1 -> PrimaryExpression()
    */
   public void visit(UnaryPlusExpression n);

   /**
    * f0 -> "-"
    * f1 -> PrimaryExpression()
    */
   public void visit(UnaryMinusExpression n);

   /**
    * f0 -> "++"
    * f1 -> PrimaryExpression()
    */
   public void visit(PreIncrementExpression n);

   /**
    * f0 -> "--"
    * f1 -> PrimaryExpression()
    */
   public void visit(PreDecrementExpression n);

   /**
    * f0 -> "~"
    * f1 -> UnaryExpression()
    */
   public void visit(ComplimentExpression n);

   /**
    * f0 -> "!"
    * f1 -> UnaryExpression()
    */
   public void visit(NotExpression n);

   /**
    * f0 -> "("
    * f1 -> "int"
    * f2 -> ")"
    * f3 -> UnaryExpression()
    */
   public void visit(CoercionToIntExpression n);

   /**
    * f0 -> "("
    * f1 -> "double"
    * f2 -> ")"
    * f3 -> UnaryExpression()
    */
   public void visit(CoercionToDoubleExpression n);

   /**
    * f0 -> "("
    * f1 -> "long"
    * f2 -> ")"
    * f3 -> UnaryExpression()
    */
   public void visit(CoercionToLongExpression n);

   /**
    * f0 -> "("
    * f1 -> "short"
    * f2 -> ")"
    * f3 -> UnaryExpression()
    */
   public void visit(CoercionToShortExpression n);

   /**
    * f0 -> "("
    * f1 -> "byte"
    * f2 -> ")"
    * f3 -> UnaryExpression()
    */
   public void visit(CoercionToByteExpression n);

   /**
    * f0 -> "("
    * f1 -> TypeAnnotation()
    * f2 -> ")"
    * f3 -> UnaryExpression()
    */
   public void visit(TypeAnnotatedExpression n);

   /**
    * f0 -> UpdatableArrayType()
    *       | ValueArrayType()
    *       | DistType()
    *       | RegionType()
    *       | PointType()
    */
   public void visit(TypeAnnotation n);

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
   public void visit(PostfixExpression n);

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "++"
    */
   public void visit(PostIncrementExpression n);

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "--"
    */
   public void visit(PostDecrementExpression n);

   /**
    * f0 -> PrimaryPrefix()
    * f1 -> ( PrimarySuffix() )*
    */
   public void visit(PrimaryExpression n);

   /**
    * f0 -> Literal()
    *       | MathExpression()
    *       | This()
    *       | ExpressionInParentheses()
    *       | AllocationExpression()
    *       | MethodCall()
    *       | Identifier()
    */
   public void visit(PrimaryPrefix n);

   /**
    * f0 -> "this"
    */
   public void visit(This n);

   /**
    * f0 -> "("
    * f1 -> Expression()
    * f2 -> ")"
    */
   public void visit(ExpressionInParentheses n);

   /**
    * f0 -> "place.places"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    */
   public void visit(Place n);

   /**
    * f0 -> "dist.factory.block"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    */
   public void visit(FactoryBlock n);

   /**
    * f0 -> "dist.factory.blockCyclic"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ","
    * f4 -> Expression()
    * f5 -> ")"
    */
   public void visit(FactoryBlockCyclic n);

   /**
    * f0 -> "region.factory.emptyRegion"
    * f1 -> "("
    * f2 -> IntegerLiteral()
    * f3 -> ")"
    */
   public void visit(FactoryEmptyRegion n);

   /**
    * f0 -> "System.currentTimeMillis"
    * f1 -> "("
    * f2 -> ")"
    */
   public void visit(CurrentTime n);

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
   public void visit(PrimarySuffix n);

   /**
    * f0 -> "["
    * f1 -> ExpressionList()
    * f2 -> "]"
    */
   public void visit(ArrayAccess n);

   /**
    * f0 -> "."
    * f1 -> MethodCall()
    */
   public void visit(DotMethodCall n);

   /**
    * f0 -> Identifier()
    * f1 -> "("
    * f2 -> ( ExpressionList() )?
    * f3 -> ")"
    */
   public void visit(MethodCall n);

   /**
    * f0 -> "."
    * f1 -> "distribution"
    */
   public void visit(DotDistribution n);

   /**
    * f0 -> "."
    * f1 -> "region"
    */
   public void visit(DotRegion n);

   /**
    * f0 -> "."
    * f1 -> "id"
    */
   public void visit(DotId n);

   /**
    * f0 -> "."
    * f1 -> "get"
    * f2 -> "("
    * f3 -> ExpressionList()
    * f4 -> ")"
    */
   public void visit(DotGet n);

   /**
    * f0 -> "."
    * f1 -> "contains"
    * f2 -> "("
    * f3 -> "["
    * f4 -> ExpressionList()
    * f5 -> "]"
    * f6 -> ")"
    */
   public void visit(DotContainsPoint n);

   /**
    * f0 -> "."
    * f1 -> "contains"
    * f2 -> "("
    * f3 -> Expression()
    * f4 -> ")"
    */
   public void visit(DotContains n);

   /**
    * f0 -> "."
    * f1 -> "equals"
    * f2 -> "("
    * f3 -> Expression()
    * f4 -> ")"
    */
   public void visit(DotEquals n);

   /**
    * f0 -> "."
    * f1 -> "high"
    * f2 -> "("
    * f3 -> ")"
    */
   public void visit(DotHigh n);

   /**
    * f0 -> "."
    * f1 -> "low"
    * f2 -> "("
    * f3 -> ")"
    */
   public void visit(DotLow n);

   /**
    * f0 -> "."
    * f1 -> "rank"
    * f2 -> "("
    * f3 -> Expression()
    * f4 -> ")"
    */
   public void visit(DotRank n);

   /**
    * f0 -> "."
    * f1 -> "next"
    * f2 -> "("
    * f3 -> ")"
    */
   public void visit(DotNext n);

   /**
    * f0 -> "."
    * f1 -> "prev"
    * f2 -> "("
    * f3 -> ")"
    */
   public void visit(DotPrev n);

   /**
    * f0 -> "."
    * f1 -> "isFirst"
    * f2 -> "("
    * f3 -> ")"
    */
   public void visit(DotIsFirst n);

   /**
    * f0 -> "."
    * f1 -> "isLast"
    * f2 -> "("
    * f3 -> ")"
    */
   public void visit(DotIsLast n);

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
   public void visit(DotCoord n);

   /**
    * f0 -> "."
    * f1 -> "ordinal"
    * f2 -> "("
    * f3 -> "["
    * f4 -> ExpressionList()
    * f5 -> "]"
    * f6 -> ")"
    */
   public void visit(DotOrdinalPoint n);

   /**
    * f0 -> "."
    * f1 -> "ordinal"
    * f2 -> "("
    * f3 -> Expression()
    * f4 -> ")"
    */
   public void visit(DotOrdinal n);

   /**
    * f0 -> "."
    * f1 -> "max"
    * f2 -> "("
    * f3 -> ")"
    */
   public void visit(DotMax n);

   /**
    * f0 -> "."
    * f1 -> "sum"
    * f2 -> "("
    * f3 -> ")"
    */
   public void visit(DotSum n);

   /**
    * f0 -> "."
    * f1 -> "size"
    * f2 -> "("
    * f3 -> ")"
    */
   public void visit(DotSize n);

   /**
    * f0 -> "."
    * f1 -> Identifier()
    */
   public void visit(DotIdentifier n);

   /**
    * f0 -> NewObject()
    *       | NewValueArray()
    *       | NewUpdatableArray()
    */
   public void visit(AllocationExpression n);

   /**
    * f0 -> "new"
    * f1 -> Identifier()
    * f2 -> "("
    * f3 -> [ ExpressionList() ]
    * f4 -> ")"
    */
   public void visit(NewObject n);

   /**
    * f0 -> "new"
    * f1 -> NonArrayType()
    * f2 -> "value"
    * f3 -> "["
    * f4 -> Identifier()
    * f5 -> "]"
    * f6 -> ArrayInitializer()
    */
   public void visit(NewValueArray n);

   /**
    * f0 -> "new"
    * f1 -> NonArrayType()
    * f2 -> "["
    * f3 -> Identifier()
    * f4 -> "]"
    * f5 -> [ ArrayInitializer() ]
    */
   public void visit(NewUpdatableArray n);

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
   public void visit(Literal n);

   /**
    * f0 -> <INTEGER_LITERAL>
    */
   public void visit(IntegerLiteral n);

   /**
    * f0 -> <LONG_LITERAL>
    */
   public void visit(LongLiteral n);

   /**
    * f0 -> <HEX_LITERAL>
    */
   public void visit(HexLiteral n);

   /**
    * f0 -> <FLOATING_POINT_LITERAL>
    */
   public void visit(FloatingPointLiteral n);

   /**
    * f0 -> <STRING_LITERAL>
    */
   public void visit(StringLiteral n);

   /**
    * f0 -> "true"
    */
   public void visit(True n);

   /**
    * f0 -> "false"
    */
   public void visit(False n);

   /**
    * f0 -> "here"
    */
   public void visit(HereLiteral n);

   /**
    * f0 -> "place.FIRST_PLACE"
    */
   public void visit(PlaceFirstPlace n);

   /**
    * f0 -> "place.MAX_PLACES"
    */
   public void visit(PlaceMaxPlaces n);

   /**
    * f0 -> "dist.UNIQUE"
    */
   public void visit(DistUnique n);

   /**
    * f0 -> "java.lang.Integer.SIZE"
    */
   public void visit(JavaIntegerSize n);

   /**
    * f0 -> Expression()
    * f1 -> ( ArgumentRest() )*
    */
   public void visit(ExpressionList n);

   /**
    * f0 -> ","
    * f1 -> Expression()
    */
   public void visit(ArgumentRest n);

   /**
    * f0 -> "("
    * f1 -> PointType()
    * f2 -> ExplodedSpecification()
    * f3 -> ")"
    * f4 -> Block()
    */
   public void visit(ArrayInitializer n);

}

