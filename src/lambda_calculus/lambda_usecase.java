//package lambda_calculus;
//
////import lesani.collection.Pair;
////import graph.lang.ast.*;
////import graph.lang.type.RecordType;
////import graph.lang.type.Type;
////import graph.lang.visitor.CVCIPrinter;
//
//import jsrc.x10.ast.tree.expression.Expression;
//import jsrc.x10.ast.tree.expression.id.Id;
//import jsrc.x10.ast.tree.xtras.methodcall.Exp;
//import jsrc.x10.ast.visitor.CPSPrinter;
//import jsrc.x10.ast.*;
//import jsrc.x10.ast.tree.type.*;
//
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class lambda_usecase {
//    public String name; //name of the user declared method
//    public Type returnType; // the return value of the method
//    protected Expression methodBody; //body of the method
//    public ArrayList<Type> arguments = new ArrayList<>(); //input arguments of method
//    public Id methodID = new Id(); // the object which this method belongs to
//
//    public lambda_usecase() { }
//
//    public lambda_usecase(String methodName, Type rType)
//    {
//        this.name = methodName;
//        this.returnType = rType;
//    }
//
//    public Expression getMethodBody() {return methodBody;}
//    public void setMethodBody(Expression mb) {
//        this.methodBody = mb;
//        return;
//    }
//
//    public String cpsTransformation(lambda_usecase oftMethod){
//        StringBuilder builder = new StringBuilder();
//        builder.append(CPSPrinter.print(oftMethod));
//        return builder.toString();
//    }
//}
