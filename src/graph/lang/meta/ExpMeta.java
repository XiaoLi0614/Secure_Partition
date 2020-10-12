package graph.lang.meta;

import graph.lang.ast.Exp;
import graph.lang.type.Type;
import lesani.collection.Pair;

import java.util.List;


public interface ExpMeta {
   int arity();
   List<Pair<Type[], Type>> getType();
   Exp cons(Exp[] operands);
}
