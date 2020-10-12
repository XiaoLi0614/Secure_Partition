package jsrc.matlab.typeinference;

import jsrc.matlab.ast.tree.declaration.CompilationUnit;
import jsrc.matlab.typeinference.type.Type;
import lesani.compiler.typing.SymbolTable;

public class TypedCompilationUnits {
    public CompilationUnit[] compilationUnits;
    public SymbolTable<Type> symbolTable;
    public TypedCompilationUnits(CompilationUnit[] compilationUnits, SymbolTable<Type> symbolTable) {
        this.compilationUnits = compilationUnits;
        this.symbolTable = symbolTable;
    }
}
