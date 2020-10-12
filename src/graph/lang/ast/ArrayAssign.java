package graph.lang.ast;
import graph.lang.type.ArrayType;
import graph.lang.visitor.CVCIPrinter;
import graph.lang.visitor.Visitor;
import java.lang.reflect.*;

import java.util.Arrays;
import java.util.HashMap;

public class ArrayAssign extends Exp{
    //(state.employees WITH [e.0] := (TRUE, e.1, e.2))
    public Exp originalArray;
    public Exp index;
    public Exp assignedValue;


    //public String funName;
    //public Fun fun;
    //public Exp[] args;
    //public HashMap<String, String> argsMap = new HashMap<>();

    public ArrayAssign(Exp oArray, Exp id, Exp aValue) {
        this.originalArray = oArray;
        this.index = id;
        this.assignedValue = aValue;
        /*int i = 0;
        for (TDecl tDecl : fun.sig.pars)
        {
            argsMap.put(tDecl.name, CVCIPrinter.print(args[i]));
            i++;
        }*/
    }

    public <R> R accept(Visitor.ExpVisitor<R> v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        return originalArray + "[" + index + "]:=" + assignedValue; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrayAssign aA = (ArrayAssign) o;

        if (!index.equals(aA.index)) return false;
        return assignedValue.equals(aA.assignedValue);
    }

    @Override
    public int hashCode() {
        int result = originalArray.hashCode();
        result = 31 * result + assignedValue.hashCode();
        return result;
    }
}


