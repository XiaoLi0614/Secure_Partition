package typechecking.direlicitor;

import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;
import typechecking.types.Class;
import lesani.compiler.typing.Symbol;
import lesani.compiler.typing.exception.SymbolNotFoundException;
import typechecking.types.MainClass;

import java.util.Map;
import java.util.HashMap;

import scala.collection.immutable.List;


/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class ClassDir {

 	 public Symbol mainClassSymbol;

 	 public Map<Symbol, Class> classMap = new HashMap<Symbol, Class>();
    public List<Class> classes;

    public boolean setMainClass(MainClass mainClass) {
        mainClassSymbol = mainClass.nameSymbol;
        return add(mainClass);
    }

	public Class getMainClass() throws SymbolNotFoundException {
		return get(mainClassSymbol).value();
	}

    public boolean add(Class theClass) {
        Symbol nameSymbol = theClass.nameSymbol();
        return classMap.put(nameSymbol, theClass) == null;
    }

    public Option<Class> get(Symbol symbol) {
        Class theClass = classMap.get(symbol);
        if (theClass == null)
            return None.instance();
        return new Some<Class>(theClass);
    }

	public Option<Class> get(String name) {
		Symbol symbol = Symbol.symbol(name);
		return get(symbol);
	}
}


