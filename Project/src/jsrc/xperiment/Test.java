package jsrc.xperiment;

import jsrc.matlab.ast.tree.declaration.type.IntType;
import jsrc.matlab.ast.tree.declaration.type.MatrixType;
import jsrc.matlab.typeinference.type.TypeVar;
import lesani.compiler.typing.TypeScheme;

public class Test {
//    public static void main(String[] args) {
//        Pair<Expression, Statement[]>[] elseIfs = (Pair<Expression, Statement[]>)
//                Array.newInstance(Pair<Expression, Statement[]>.class, 10);
//
//        elseIfs[0] =
//    }

    public static void main(String[] args) {

        int i = 2;
        if (i==3)
            if (true)
                System.out.println("Hello");
            else
                System.out.println("world");




/*
        final TypeVar v1 = TypeVar.fresh();
        final TypeVar v2 = TypeVar.fresh();
        MatrixType matrixType = new MatrixType(
                IntType.instance(),
                v1,
                v2);

        TypeScheme typeScheme = new TypeScheme(v1, v2, matrixType);
        System.out.println("matrixType = " + matrixType);
        MatrixType instance1 = (MatrixType)typeScheme.instantiate();
        System.out.println("instance1 = " + instance1);
        MatrixType instance2 = (MatrixType)typeScheme.instantiate();
        System.out.println("instance2 = " + instance2);
*/
    }

}
