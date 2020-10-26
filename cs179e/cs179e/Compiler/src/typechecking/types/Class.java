package typechecking.types;

import lesani.alg.graph.scc.Node;
import lesani.collection.func.Fun;
import lesani.collection.func.Fun0;
import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;
import lesani.compiler.typing.exception.TypeErrorException;
import lesani.compiler.typing.Symbol;
import vapor.core.ClassRecord;
import vapor.core.VTable;

import java.lang.*;
import java.lang.Boolean;
import java.util.*;



/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */


public class Class implements Type, Node {

	public Symbol nameSymbol;

    public Option<DefinedType> superDefinedType = None.instance();
    public Option<Class> superClassOpt = None.instance();

    public Map<Symbol, Type> fields;
	 public Map<Symbol, Method> methods;

    public List<parsing.ast.tree.Method> methodTrees;

    public ClassRecord classRecord;
    public VTable vTable;

	 public Class(
            String name,
            Option<DefinedType> superClassOpt,
            List<Class.Field> fields,
            List<Method> methods,
            List<parsing.ast.tree.Method> methodTrees) {

		this.nameSymbol = Symbol.symbol(name);
        this.superDefinedType = superClassOpt;
//		if (superClassSymbol != null)
//			this.superClass = new Some<Class>(new ToBeResolvedType(superClassSymbol));
		this.fields = new HashMap<Symbol, Type>();
        this.methods = new HashMap<Symbol, Method>();

        this.methodTrees = methodTrees;
        // These checks are done in the type checker but having them here does not hurt.
		for (Class.Field fieldsDecl : fields) {
			if (this.fields.put(fieldsDecl.symbol, fieldsDecl.type) != null)
				throw new TypeErrorException("Duplicate field definition.");
		}
		for (Method method : methods) {
			if (this.methods.put(method.nameSymbol, method) != null)
				throw new TypeErrorException("Duplicate method definition.");
		}
	}

	public Class(String name, HashMap<Symbol, Type> fields, HashMap<Symbol, Method> methods) {
		this.nameSymbol = Symbol.symbol(name);
		this.fields = fields;
		this.methods = methods;
        this.methodTrees = new LinkedList<parsing.ast.tree.Method>();
	}

	public Symbol nameSymbol() {
		return nameSymbol;
	}

	public String name() {
		return nameSymbol.toString();
	}

    // The methods denoted by "self" return information from the class itself and not the super class(es).

// Field ------------------------------------------------------------

	public boolean addField(String name, Type type) {
		Symbol symbol = Symbol.symbol(name);
		return fields.put(symbol, type) == null;
	}

    public Option<Type> getSelfFieldType(final String name) {
        final Type type = fields.get(Symbol.symbol(name));
        if (type != null)
            return new Some<Type>(type);
        return None.instance();
    }

	public Option<Type> getFieldType(final String name) {
		Type type = fields.get(Symbol.symbol(name));
        if (type != null)
            return new Some<Type>(type);
        else
            return superClassOpt.apply(
                new Fun0<Option<Type>>() {
                    public Option<Type> apply() {
                        return None.instance();
                    }
                },
                new Fun<Class, Option<Type>>() {
                    public Option<Type> apply(Class clazz) {
                        return clazz.getFieldType(name);
                    }
                }
            );
    }
// -------------------------------------------------------------------
// MethodDefinition ------------------------------------------------------------

	public Method[] selfMethods() {
        final Collection<Method> values = methods.values();
        return values.toArray(new Method[values.size()]);
	}

    public Option<Method> getSelfMethod(final String name) {
        final Method method = methods.get(Symbol.symbol(name));
        if (method != null)
            return new Some<Method>(method);
        return None.instance();
    }

	public Option<Method> getMethod(final String name) {
		final Method method = methods.get(Symbol.symbol(name));
        if (method != null)
            return new Some<Method>(method);
        else
            return superClassOpt.apply(
                new Fun0<Option<Method>>() {
                    public Option<Method> apply() {
                        return None.instance();
                    }
                },
                new Fun<Class, Option<Method>>() {
                    public Option<Method> apply(Class clazz) {
                        return clazz.getMethod(name);
                    }
                }
            );
	}

	public boolean addMethod(String name, Method method) {
		Symbol symbol = Symbol.symbol(name);
		return methods.put(symbol, method) == null;
	}

// -------------------------------------------------------------------

    public boolean isSelfSubclass(final Class clazz) {
        if (this == clazz)
            return true;
        return superClassOpt.apply(
            new Fun0<Boolean>() {
                public Boolean apply() {
                    return false;
                }
            },
            new Fun<Class, Boolean>() {
                public Boolean apply(Class superClass) {
                    return superClass == clazz;
                }
            }
        );
    }

    public boolean isSubclass(final Class clazz) {
        if (clazz == this)
            return true;
        else
            return superClassOpt.apply(
                new Fun0<java.lang.Boolean>() {
                    public Boolean apply() {
                        return false;
                    }
                },
                new Fun<Class, Boolean>() {
                    public Boolean apply(Class superClass) {
                        return superClass.isSubclass(clazz);
                    }
                }
            );
    }

    public static class Field {
        public Type type;
        public Symbol symbol;

        public Field(Type type, String name) {
            this.type = type;
            this.symbol = Symbol.symbol(name);
        }

        public String name() {
            return symbol.toString();
        }
    }

    @Override
    public String toString() {
        return name();
    }
}



