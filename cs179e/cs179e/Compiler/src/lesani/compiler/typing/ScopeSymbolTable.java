package lesani.compiler.typing;

import lesani.compiler.typing.exception.DuplicateDefExc;
import lesani.compiler.typing.exception.SymbolNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class ScopeSymbolTable<T> {
    /*private */Map<Symbol, T> map = new HashMap<Symbol, T>();

    public void put(String name, T type) throws DuplicateDefExc {
        Symbol symbol = Symbol.symbol(name);
        if (map.put(symbol, type) != null)
            throw new DuplicateDefExc("Duplicate typing of symbol \"" + name +"\".");
    }
    public void put(Symbol symbol, T type) throws DuplicateDefExc {
        if (map.put(symbol, type) != null)
            throw new DuplicateDefExc("Duplicate typing of symbol \"" + symbol +"\".");
    }

    public T get(String name) throws SymbolNotFoundException {
        T type = map.get(Symbol.symbol(name));
        if (type == null)
            throw new SymbolNotFoundException("symbol not found.");
        return type;
    }

    public T get(Symbol name) throws SymbolNotFoundException {
        T type = map.get(name);
        if (type == null)
            throw new SymbolNotFoundException("symbol not found.");
        return type;
    }

    public boolean contains(String name) {
        return map.containsKey(Symbol.symbol(name));
    }
}
