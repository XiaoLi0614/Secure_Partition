/*
package graph.lang.ast;


import graph.lang.type.RecordType;
import graph.lang.visitor.Visitor;

import java.util.HashMap;

public class Record extends Exp{
    final String s = "record";
    public Exp[] exps;
    public Record(Exp... args)
    {
        this.exps = args;
    }
    public <R> R accept(Visitor.ExpVisitor<R> v) { return v.visit(this); }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Record minus = (Record) o;

        return s.equals(minus.s);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + s.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "record";
    }
}
*/
