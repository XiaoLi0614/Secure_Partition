package lesani.compiler.typing.xtras;

import lesani.compiler.typing.exception.DuplicateDefExc;
import lesani.compiler.typing.exception.SymbolNotFoundException;

public interface SymbolTableInterface<T> {

    public void add(String name, T type) throws DuplicateDefExc;
    public T get(String name) throws SymbolNotFoundException;
    public boolean contains(String name);
    public void beginScope();
    public void finishScope();
}
