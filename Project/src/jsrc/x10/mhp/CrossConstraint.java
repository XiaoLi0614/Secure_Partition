package jsrc.x10.mhp;

import jsrc.x10.ast.tree.statement.Statement;
import lesani.compiler.constraintsolver.ConstConstraint;
import lesani.collection.Pair;

import java.util.HashSet;
import java.util.Set;

public class CrossConstraint {
    Var var;
    Var right1;
    Var right2;

    public CrossConstraint(Var var, Var right1, Var right2) {
        this.var = var;
        this.right1 = right1;
        this.right2 = right2;
    }

    public <T1, T2> ConstConstraint<Var, Pair<T1, T2>> resolve(Set<T1> set1, Set<T2> set2) {
        HashSet<Pair<T1, T2>> set = new HashSet<Pair<T1, T2>>();

        for (T1 t1 : set1)
            for (T2 t2 : set2)
                set.add(new Pair<T1, T2>(t1, t2));

/*
        if (set.size() != 0) {
            System.out.println();
            System.out.println("--------------");
            for (Pair<T1, T2> pair : set)
                System.out.println(pair);
            System.out.println("--------------");
            System.out.println();
        }
*/

        return new ConstConstraint<Var, Pair<T1, T2>>(var, set);
    }
}

