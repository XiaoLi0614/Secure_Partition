package lesani.compiler.typing;

import lesani.collection.xtolook.Either;
import lesani.collection.Stack;
import lesani.collection.func.Fun;
import lesani.compiler.typing.exception.DuplicateDefExc;
import lesani.compiler.typing.exception.SymbolNotFoundException;

import java.util.Iterator;
import java.util.Map;

public class SymbolTable<T> /*implements SymbolTableInterface<T>*/ {

    private Stack<ScopeSymbolTable<Either<T, TypeScheme>>> symbolTableStack = new Stack<ScopeSymbolTable<Either<T, TypeScheme>>>();

//    private Stack<ScopeSymbolTable<TypeScheme>> typeSchemeStack = new Stack<ScopeSymbolTable<TypeScheme>>();

    private ScopeSymbolTable<Either<T, TypeScheme>> currentSymbolTable() {
        return symbolTableStack.peek();
    }

    public void put(String name, T type) throws DuplicateDefExc {
        currentSymbolTable().put(name, new Either.Left<T, TypeScheme>(type));
    }

    public void put(Symbol name, T type) throws DuplicateDefExc {
        currentSymbolTable().put(name, new Either.Left<T, TypeScheme>(type));
    }

    public void put(String name, TypeScheme typeScheme) throws DuplicateDefExc {
        currentSymbolTable().put(name, new Either.Right<T, TypeScheme>(typeScheme));
    }

    public T get(final String name) throws SymbolNotFoundException {
        try {
            return searchSymbolTableStack(new Operation<ScopeSymbolTable<Either<T, TypeScheme>>, T>() {
                public T run(ScopeSymbolTable<Either<T, TypeScheme>> symbolTable) throws UnsuccessfulException {
                    try {
                        return symbolTable.get(name).apply(
                            new Fun<T, T>() {
                                public T apply(T input) {
                                    return input;
                                }
                            },
                            new Fun<TypeScheme, T>() {
                                public T apply(TypeScheme typeScheme) {
                                    // The Cast: Works for our uses.
                                    return (T)typeScheme.instantiate();
                                }
                            }
                        );
                    } catch (SymbolNotFoundException e) {
                        throw new UnsuccessfulException();
                    }
                }
            });
        } catch (UnsuccessfulException e) {
            throw new SymbolNotFoundException("Symbol: " + name);
        }
    }
    public T get(final Symbol name) throws SymbolNotFoundException {
        try {
            return searchSymbolTableStack(new Operation<ScopeSymbolTable<Either<T, TypeScheme>>, T>() {
                public T run(ScopeSymbolTable<Either<T, TypeScheme>> symbolTable) throws UnsuccessfulException {
                    try {
                        return symbolTable.get(name).apply(
                            new Fun<T, T>() {
                                public T apply(T input) {
                                    return input;
                                }
                            },
                            new Fun<TypeScheme, T>() {
                                public T apply(TypeScheme typeScheme) {
                                    // The Cast: Works for our uses.
                                    return (T)typeScheme.instantiate();
                                }
                            }
                        );
                    } catch (SymbolNotFoundException e) {
                        throw new UnsuccessfulException();
                    }
                }
            });
        } catch (UnsuccessfulException e) {
            throw new SymbolNotFoundException("");
        }
    }

    public boolean contains(String name) {
        try {
            get(name);
        } catch (SymbolNotFoundException e) {
            return false;
        }
        return true;
    }

    public void startScope() {
        symbolTableStack.push(new ScopeSymbolTable<Either<T, TypeScheme>>());
    }
    public void startScope(Map<Symbol, ? extends T> map) {
        final ScopeSymbolTable<Either<T, TypeScheme>> scopeSymbolTable = new ScopeSymbolTable<Either<T, TypeScheme>>();
        symbolTableStack.push(scopeSymbolTable);
        for (Map.Entry<Symbol, ? extends T> symbolTEntry : map.entrySet()) {
            try {
                scopeSymbolTable.put(symbolTEntry.getKey(), new Either.Left<T, TypeScheme>(symbolTEntry.getValue()));
            } catch (DuplicateDefExc duplicateDefExc) {
                duplicateDefExc.printStackTrace();
            }
        }
    }

    public void endScope() {
        symbolTableStack.pop();
    }


    static class UnsuccessfulException extends Exception {}
    static interface Operation<T, U> {
        public U run(T t) throws UnsuccessfulException;
    }

    private <U> U searchSymbolTableStack(Operation<ScopeSymbolTable<Either<T, TypeScheme>>, U> operation)
            throws UnsuccessfulException {
        Iterator<ScopeSymbolTable<Either<T, TypeScheme>>> iterator = symbolTableStack.topDownIterator();
        while (iterator.hasNext()) {
            ScopeSymbolTable<Either<T, TypeScheme>> symbolTable = iterator.next();
            try {
                return operation.run(symbolTable);
            } catch (UnsuccessfulException e) {
            }
        }
        throw new UnsuccessfulException();
    }

    public Iterator<Symbol> iterator() {
        return currentSymbolTable().map.keySet().iterator();
    }
}
