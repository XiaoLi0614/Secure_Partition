package jsrc.matlab.typeinference;


import jsrc.matlab.ast.tree.declaration.*;
import jsrc.matlab.typeinference.constraintelicitation.ConstraintElicitor;
import jsrc.matlab.typeinference.exceptions.TypeMismatches;
import jsrc.matlab.typeinference.type.Type;
import jsrc.matlab.typeinference.unification.Constraint;
import lesani.compiler.typing.SymbolTable;
import lesani.compiler.typing.substitution.Substitution;
import jsrc.matlab.typeinference.unification.Unifier;
import lesani.collection.Pair;

import java.util.List;


public class TypeInferencer {
    public static CompilationUnit[] infer(CompilationUnit[] compilationUnits) throws TypeMismatches {
//        return new TypeInferencer(compilationUnits).infer();
        return (new TypeInferencer(compilationUnits)).infer();
    }

    private CompilationUnit[] compilationUnits;

    public TypeInferencer(CompilationUnit[] compilationUnits) {
        this.compilationUnits = compilationUnits;
    }

    public CompilationUnit[] infer() throws TypeMismatches {
        ConstraintElicitor constraintElicitor = new ConstraintElicitor(compilationUnits);
        Pair<List<Constraint>, fj.data.List<List<List<Constraint>>>> constraints =
                constraintElicitor.elicit();
        compilationUnits = constraintElicitor.getProcessedCompilationUnits();

        Substitution substitution = Unifier.solve(constraints._1(), constraints._2());

//        System.out.println("Substitution");
//        System.out.println(substitution);

        TypeResolver.resolve(compilationUnits, substitution);

//        SymbolTable<Type> symbolTable = constraintElicitor.symbolTable;
//        TypeResolver.resolve(symbolTable, substitution);

//        return new TypedCompilationUnits(compilationUnits, symbolTable);
        return compilationUnits;
    }
}


