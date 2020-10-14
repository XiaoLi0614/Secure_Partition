package jsrc.px10.astbuild.intastnodes;

import jsrc.matlab.ast.tree.declaration.InputParam;
import jsrc.x10.ast.tree.declaration.*;
import jsrc.x10.ast.tree.expression.id.Id;
import jsrc.x10.ast.tree.expression.x10.RegionConstructor;
import jsrc.x10.ast.tree.statement.VarDeclOrSt;
import jsrc.x10.ast.tree.expression.Expression;
import jsrc.x10.ast.tree.statement.Statement;
import jsrc.x10.ast.tree.xtras.ValueClassDecl;
import lesani.compiler.ast.HolderNode;
import lesani.compiler.ast.NodeList;

import java.util.Iterator;

public class ArrayMaker {
    public static HolderNode<RegionConstructor.Dimension>[] holderNodeArray(NodeList<HolderNode<RegionConstructor.Dimension>> list) {
        HolderNode[] nodes = new HolderNode[list.size()];
        int i = 0;
        Iterator<HolderNode<RegionConstructor.Dimension>> iterator = list.iterator();
        while (iterator.hasNext()) {
            HolderNode<RegionConstructor.Dimension> node = iterator.next();
            nodes[i] = node;
            i++;
        }
        return nodes;
    }

    public static Expression[] expressionArray(NodeList<Expression> list) {
        Expression[] nodes = new Expression[list.size()];
        int i = 0;
        Iterator<Expression> iterator = list.iterator();
        while (iterator.hasNext()) {
            Expression node = iterator.next();
            nodes[i] = node;
            i++;
        }
        return nodes;
    }

    public static Id[] idArray(NodeList<Id> list) {
        Id[] nodes = new Id[list.size()];
        int i = 0;
        Iterator<Id> iterator = list.iterator();
        while (iterator.hasNext()) {
            Id node = iterator.next();
            nodes[i] = node;
            i++;
        }
        return nodes;
    }
    public static Field[] fieldDeclArray(NodeList<Field> list) {
        Field[] nodes = new Field[list.size()];
        int i = 0;
        Iterator<Field> iterator = list.iterator();
        while (iterator.hasNext()) {
            Field node = iterator.next();
            nodes[i] = node;
            i++;
        }
        return nodes;
    }

    public static Constructor[] constructorDeclArray(NodeList<Constructor> list) {
        Constructor[] nodes = new Constructor[list.size()];
        int i = 0;
        Iterator<Constructor> iterator = list.iterator();
        while (iterator.hasNext()) {
            Constructor node = iterator.next();
            nodes[i] = node;
            i++;
        }
        return nodes;
    }

    public static ClassDecl[] classDeclArray(NodeList<ClassDecl> list) {
        ClassDecl[] nodes = new ClassDecl[list.size()];
        int i = 0;
        Iterator<ClassDecl> iterator = list.iterator();
        while (iterator.hasNext()) {
            ClassDecl node = iterator.next();
            nodes[i] = node;
            i++;
        }
        return nodes;
    }

    public static ValueClassDecl[] valueDeclArray(NodeList<ValueClassDecl> list) {
        ValueClassDecl[] nodes = new ValueClassDecl[list.size()];
        int i = 0;
        Iterator<ValueClassDecl> iterator = list.iterator();
        while (iterator.hasNext()) {
            ValueClassDecl node = iterator.next();
            nodes[i] = node;
            i++;
        }
        return nodes;
    }

    public static Method[] methodDeclArray(NodeList<Method> list) {
        Method[] nodes = new Method[list.size()];
        int i = 0;
        Iterator<Method> iterator = list.iterator();
        while (iterator.hasNext()) {
            Method node = iterator.next();
            nodes[i] = node;
            i++;
        }
        return nodes;
    }

    public static FormalParam[] formalParamArray(NodeList<FormalParam> list) {
        FormalParam[] nodes = new FormalParam[list.size()];
        int i = 0;
        Iterator<FormalParam> iterator = list.iterator();
        while (iterator.hasNext()) {
            FormalParam node = iterator.next();
            nodes[i] = node;
            i++;
        }
        return nodes;
    }

//    public static InputParam[] formalParamArray(NodeList<InputParam> list) {
//        InputParam[] nodes = new InputParam[list.size()];
//        int i = 0;
//        Iterator<InputParam> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            InputParam node = iterator.next();
//            nodes[i] = node;
//            i++;
//        }
//        return nodes;
//    }

    public static VarDeclOrSt[] blockStatementArray(NodeList<VarDeclOrSt> list) {
        VarDeclOrSt[] nodes = new VarDeclOrSt[list.size()];
        int i = 0;
        Iterator<VarDeclOrSt> iterator = list.iterator();
        while (iterator.hasNext()) {
            VarDeclOrSt node = iterator.next();
            nodes[i] = node;
//            if (node==null) {
//                System.out.println("null in statementArray");
//            }
            i++;
        }
        return nodes;
    }

/*
    public static ProgramClassFieldDecl[] constDeclArray(NodeList<ProgramClassFieldDecl> list) {
        ProgramClassFieldDecl[] nodes = new ProgramClassFieldDecl[list.size()];
        int i = 0;
        Iterator<ProgramClassFieldDecl> iterator = list.iterator();
        while (iterator.hasNext()) {
            ProgramClassFieldDecl result = iterator.next();
            nodes[i] = result;
            i++;
        }
        return nodes;
    }
*/

    public static Statement[] statementArray(NodeList<Statement> list) {
        Statement[] nodes = new Statement[list.size()];
        int i = 0;
        Iterator<Statement> iterator = list.iterator();
        while (iterator.hasNext()) {
            Statement node = iterator.next();
            nodes[i] = node;
            i++;
        }
        return nodes;

    }
}
