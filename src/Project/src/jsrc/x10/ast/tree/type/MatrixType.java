package jsrc.x10.ast.tree.type;

import jsrc.x10.ast.visitor.DFSVisitor;
import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;


public class MatrixType extends NonVoidType {
    public ScalarType elemType;
    public Option<Integer> n = None.instance();
    public Option<Integer> m = None.instance();

    public MatrixType(ScalarType elemType, Option<Integer> n, Option<Integer> m) {
        this.elemType = elemType;
        this.n = n;
        this.m = m;
    }

    public MatrixType(ScalarType elemType, Integer n, Integer m) {
        this.elemType = elemType;
        this.n = new Some<Integer>(n);
        this.n = new Some<Integer>(m);
    }
    public MatrixType(ScalarType elemType, Integer n) {
        this.elemType = elemType;
        this.n = new Some<Integer>(n);
    }


    public void setN(Integer n) {
        this.n = new Some<Integer>(n);
    }

    public void setM(Integer m) {
        this.m = new Some<Integer>(m);
    }

    public Object accept(DFSVisitor.FileVisitor.ClassVisitor.Shared.TypeVisitor typeVisitor) {
        return typeVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "Matrix[" + elemType + "](" + n + ", " + m + ")";
    }
}


