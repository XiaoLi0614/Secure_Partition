package graph.lang.ast;

import graph.lang.type.ArrayType;
import graph.lang.visitor.Visitor;

import java.util.HashMap;

public class ArrayTypeConstructor extends UOp{
    public Exp i;

    public ArrayTypeConstructor(Exp i)
    {
        super(i);
    }

    public <R> R accept(Visitor.ExpVisitor.UOpVisitor<R> v) { return v.visit(this); }

    @Override
    String opName() {
        return "ARRAY()";
    }

    @Override
    public String toString() {
        return "" + i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingeltonTuple that = (SingeltonTuple) o;

        return i == that.i;
    }

    @Override
    public int hashCode() {
        return i.hashCode();
    }

}






