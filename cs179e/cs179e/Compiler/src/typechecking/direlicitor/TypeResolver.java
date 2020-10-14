package typechecking.direlicitor;

import lesani.collection.func.Fun;
import lesani.collection.func.Fun0;
import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;
import lesani.compiler.typing.Symbol;
import typechecking.exceptions.TypeNotFoundException;
import typechecking.types.Class;
import typechecking.types.*;
import typechecking.types.Method;
import typechecking.types.Type;

import java.util.*;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class TypeResolver {

    public static void resolve(final ClassDir symbolTable) {

        Map<Symbol, Class> classes = symbolTable.classMap;
        for (Map.Entry<Symbol, Class> classEntry : classes.entrySet()) {
            Symbol classSymbol = classEntry.getKey();
            Class clazz = classEntry.getValue();

            clazz.superClassOpt = clazz.superDefinedType.apply(
                new Fun0<Option<Class>>() {
                    public Option<Class> apply() {
                        return None.instance();
                    }
                },
                    new Fun<DefinedType, Option<Class>>() {
                    public Option<Class> apply(DefinedType definedType) {
                        Type type = resolve(symbolTable, definedType);
                        Class superClass = (Class) type;
                        return new Some<Class>(superClass);
                    }
                }
            );

            Map<Symbol, Type> fields = clazz.fields;
            for (Map.Entry<Symbol, Type> fieldEntry : fields.entrySet()) {
                Symbol fieldSymbol = fieldEntry.getKey();
                Type fieldType = fieldEntry.getValue();
                fields.put(fieldSymbol, resolve(symbolTable, fieldType));
            }
            Map<Symbol, Method> methods = clazz.methods;
            for (Map.Entry<Symbol, Method> methodEntry : methods.entrySet()) {
                Symbol methodSymbol = methodEntry.getKey();
                Method method = methodEntry.getValue();
                List<Method.Parameter> params = method.parameters;
                List<Method.Parameter> resolvedParams = new LinkedList<Method.Parameter>();
                for (Method.Parameter parameter : params) {
                    Symbol paramSymbol = parameter.nameSymbol;
                    Type type = parameter.type;
                    resolvedParams.add(new Method.Parameter(paramSymbol, resolve(symbolTable, type)));
                }
                method.parameters = resolvedParams;
                method.returnType = resolve(symbolTable, method.returnType);
            }
        }
    }

    private static Type resolve(ClassDir symbolTable, Type type) {
        if (type instanceof DefinedType) {
            final DefinedType definedType = (DefinedType) type;

            return symbolTable.get(definedType.name).apply(
                new Fun0<Type>() {
                    public Type apply() {
                        throw new TypeNotFoundException("Unknown type " + definedType.name() + ".");
                    }
                },
                new Fun<Class, Type>() {
                    public Type apply(Class clazz) {
                        return clazz;
                    }
                }
            );
        } else
            return type;
    }

}



