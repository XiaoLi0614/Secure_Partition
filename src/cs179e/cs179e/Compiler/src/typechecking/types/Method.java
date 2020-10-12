package typechecking.types;

import lesani.compiler.typing.Symbol;
import lesani.compiler.typing.exception.SymbolNotFoundException;
import parsing.ast.tree.Statement;

import java.util.List;
import java.util.LinkedList;
import java.lang.*;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class Method implements Type {
    // This is basically a function.

    public static class Parameter {
        public Type type;
        public Symbol nameSymbol;

        public Parameter(String name, Type type) {
            this.type = type;
            this.nameSymbol = Symbol.symbol(name);
        }

        public Parameter(Symbol name, Type type) {
            this.type = type;
            this.nameSymbol = name;
        }


        public String name() {
            return nameSymbol.toString();
        }
    }

	public Symbol nameSymbol;
	public List<Parameter> parameters = new LinkedList<Parameter>();
	public Type returnType;

    public List<Statement> statements = new LinkedList<Statement>();

	//public HashMap<Symbol, Type> locals = new HashMap<Symbol, Type>();
	//public HashMap<symbol, Class> innerClasses;

	public Method(String name, List<Parameter> parameters, Type returnType, List<Statement> statements) {
		this.nameSymbol = Symbol.symbol(name);
		this.parameters = parameters;
		this.returnType = returnType;
        this.statements = statements;
	}

	public String name() {
		return nameSymbol.toString();
	}

// -------------------------------------------------------------------
// Parameter ---------------------------------------------------------

	public void updateParameterType(String name, Type type) throws SymbolNotFoundException {
		Symbol symbol = Symbol.symbol(name);
		for (Parameter parameter : parameters)
			if (symbol.equals(parameter.nameSymbol)) {
				parameter.type = type;
				return;
			}
		throw new SymbolNotFoundException("Parameter not found.");
	}

	public Type getParameterType(int parameterNumber) {
		return parameters.get(parameterNumber).type;
	}

	public Type getParameterType(String name) throws SymbolNotFoundException {
		Symbol symbol = Symbol.symbol(name);
		return getParameterType(symbol);
	}

	public Type getParameterType(Symbol symbol) throws SymbolNotFoundException {
		for (Parameter parameter : parameters)
			if (symbol.equals(parameter.nameSymbol))
				return parameter.type;
		throw new SymbolNotFoundException("Parameter not found.");
	}

	public Type[] getParamTypes() {
		Type[] types = new Type[parameters.size()];
		int i = 0;
		for (Parameter parameter : parameters) {
			types[i] = parameter.type;
			i++;
		}
		return types;
	}

// -------------------------------------------------------------------
// Local var ---------------------------------------------------------

//	public void addLocalVar(String name, Type type) throws DuplicateDefExc {
//		Symbol symbol = Symbol.symbol(name);
//		if (locals.put(symbol, type) != null)
//			throw new DuplicateDefExc("Duplicate local var.");
//	}
//
//	public Type getLocalVarType(String name) throws SymbolNotFoundException {
//		Symbol symbol = Symbol.symbol(name);
//		return getLocalVarType(symbol);
//	}
//
//	public Type getLocalVarType(Symbol symbol) throws SymbolNotFoundException {
//		Type type = locals.get(symbol);
//		if (type == null)
//			throw new SymbolNotFoundException("Local var not found.");
//		return type;
//
//	}

// -------------------------------------------------------------------
// Interface methods -------------------------------------------------

/*
	public void addVar(String name, Type type) throws DuplicateDefExc {
		addLocalVar(name, type);
	}

	public Type getVarType(String name) throws SymbolNotFoundException {
		Symbol symbol = Symbol.symbol(name);
		try {
			return getParameterType(symbol);
		} catch (SymbolNotFoundException e) {
			try {
				return getLocalVarType(symbol);
			} catch (SymbolNotFoundException e1) {
				throw new SymbolNotFoundException("Parameter or local var not found."); 
			}
		}
	}

	public void add(typechecking.types.Class theClass) throws DuplicateDefExc {
		//...
	}


	public Class get(String name) throws SymbolNotFoundException {
		throw new SymbolNotFoundException("");
	}
*/

    /**
     * User: Mohsen's Desktop
     * Date: Aug 28, 2009
     */

}


