package jsrc.matlab.astbuild.intastnodes;

import jsrc.matlab.ast.tree.expression.Expression;
import jsrc.matlab.ast.tree.statement.If;
import jsrc.matlab.ast.tree.statement.Switch;
import jsrc.matlab.ast.tree.statement.Statement;
import lesani.compiler.ast.Node;
import lesani.compiler.ast.NodeList;

import java.util.Iterator;

public class ArrayMaker {

    public static jsrc.matlab.ast.tree.expression.Expression[] expressionArray(NodeList<jsrc.matlab.ast.tree.expression.Expression> list) {
        jsrc.matlab.ast.tree.expression.Expression[] nodes = new jsrc.matlab.ast.tree.expression.Expression[list.size()];
        int i = 0;
        Iterator<Expression> iterator = list.iterator();
        while (iterator.hasNext()) {
            jsrc.matlab.ast.tree.expression.Expression node = iterator.next();
            nodes[i] = node;
            i++;
        }
        return nodes;
    }


    public static jsrc.matlab.ast.tree.expression.Id[] idArray(NodeList<jsrc.matlab.ast.tree.expression.Id> list) {
        jsrc.matlab.ast.tree.expression.Id[] nodes = new jsrc.matlab.ast.tree.expression.Id[list.size()];
        int i = 0;
        Iterator<jsrc.matlab.ast.tree.expression.Id> iterator = list.iterator();
        while (iterator.hasNext()) {
            jsrc.matlab.ast.tree.expression.Id node = iterator.next();
            nodes[i] = node;
            i++;
        }
        return nodes;
    }

    public static If.ElseIf[] elseIfArray(NodeList<If.ElseIf> list) {
        If.ElseIf[] nodes = new If.ElseIf[list.size()];
        int i = 0;
        Iterator<If.ElseIf> iterator = list.iterator();
        while (iterator.hasNext()) {
            If.ElseIf node = iterator.next();
            nodes[i] = node;
            i++;
        }
        return nodes;
    }

    public static Switch.Case[] caseArray(NodeList<Switch.Case> list) {
        Switch.Case[] nodes = new Switch.Case[list.size()];
        int i = 0;
        Iterator<Switch.Case> iterator = list.iterator();
        while (iterator.hasNext()) {
            Switch.Case node = iterator.next();
            nodes[i] = node;
            i++;
        }
        return nodes;
    }

    public static Statement[] statementArray(NodeList<Statement> list) {
        Statement[] nodes = new Statement[list.size()];
        int i = 0;
        Iterator<Statement> iterator = list.iterator();
        while (iterator.hasNext()) {
            Node n = iterator.next();
            if (n instanceof NodeList) {
                NodeList nl = (NodeList)n;
                Iterator it = nl.iterator();
//                while (it.hasNext()) {
//                    Object o = it.next();
//                    System.out.println(o);
//                }
            }
            Statement node = (Statement)n;
            nodes[i] = node;
            i++;
        }
        return nodes;
    }

    public static Expression[][] expressionArrayArray(NodeList<NodeList<Expression>> listList) {
        Expression[][] expArrayArray = new Expression[listList.size()][];
        int i = 0;
        Iterator<NodeList<Expression>> iterator = listList.iterator();
        while (iterator.hasNext()) {
            NodeList<Expression> list = iterator.next();

            Expression[] expArray = new Expression[list.size()];
            Iterator<Expression> it = list.iterator();
            int j = 0;
            while (it.hasNext()) {
                Expression expression = it.next();
                expArray[j] = expression;
                j++;
            }
            expArrayArray[i] = expArray;
            i++;
        }
        return expArrayArray;
    }
}
