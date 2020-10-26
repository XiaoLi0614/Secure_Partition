package typechecking;

import lesani.collection.func.Fun;
import lesani.collection.func.Fun0;
import lesani.compiler.typing.Symbol;
import lesani.compiler.typing.SymbolTable;

import parsing.ast.tree.Allocation;
import parsing.ast.tree.And;
import parsing.ast.tree.ArrayAllocation;
import parsing.ast.tree.ArrayAssign;
import parsing.ast.tree.ArrayLength;
import parsing.ast.tree.ArrayLookup;
import parsing.ast.tree.ArrayType;
import parsing.ast.tree.Assign;
import parsing.ast.tree.Block;
import parsing.ast.tree.BooleanType;
import parsing.ast.tree.CompilationUnit;
import parsing.ast.tree.FalseLiteral;
import parsing.ast.tree.Id;
import parsing.ast.tree.If;
import parsing.ast.tree.IntLiteral;
import parsing.ast.tree.IntType;
import parsing.ast.tree.LessThan;
import parsing.ast.tree.List;
import parsing.ast.tree.MethodCall;
import parsing.ast.tree.Minus;
import parsing.ast.tree.Mult;
import parsing.ast.tree.Node;
import parsing.ast.tree.Not;
import parsing.ast.tree.Parameter;
import parsing.ast.tree.Plus;
import parsing.ast.tree.Print;
import parsing.ast.tree.Return;
import parsing.ast.tree.This;
import parsing.ast.tree.Token;
import parsing.ast.tree.TrueLiteral;
import parsing.ast.tree.VarDecl;
import parsing.ast.tree.While;
import typechecking.exceptions.TypeMismatchExc;
import typechecking.exceptions.*;
import typechecking.direlicitor.ClassDir;
import typechecking.direlicitor.ClassDirElicitor;
import typechecking.types.Boolean;
import typechecking.types.Method;
import typechecking.types.Type;
import typechecking.types.Class;


import parsing.ast.visitor.GSVisitor;
import lesani.compiler.typing.exception.DuplicateDefExc;
import lesani.compiler.typing.exception.SymbolNotFoundException;
import lesani.compiler.typing.exception.TypeErrorException;
import lesani.compiler.typing.exception.TypeMismatchException;
import typechecking.types.*;

import java.util.*;


/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class TypeChecker implements GSVisitor<Type> {

	private CompilationUnit compilationUnit;
    private SymbolTable<Type> symbolTable = new SymbolTable<Type>();
    private ClassDir classDir;

	public TypeChecker(CompilationUnit compilationUnit) {
		this.compilationUnit = compilationUnit;
        classDir = ClassDirElicitor.apply(compilationUnit);
        symbolTable.startScope(classDir.classMap);
	}

	public ClassDir check() throws TypeErrorException {
		visit(compilationUnit);
        return classDir;
	}

	public static class OK implements Type {
		private static OK instance = new OK();

		public static OK instance() {
			return instance;
		}

		private OK() {
		}

		public String name() {
			return "OK";
		}
	}

// -------------------------------------------------------------------
    private void addSymbolsOf(Class clazz) {

        clazz.superClassOpt.apply(new Fun<Class, Object>() {
            public Object apply(Class superClass) {
                addSymbolsOf(superClass);
                return null;
            }
        });

        Map<Symbol, Type> fields = clazz.fields;
        for (Map.Entry<Symbol, Type> symbolType : fields.entrySet()) {
            final Symbol fieldSymbol = symbolType.getKey();
            try {
                final Type type = symbolType.getValue();
                symbolTable.put(fieldSymbol, type);
            } catch (DuplicateDefExc duplicateDefExc) {
                // It is assumed there is no inner class, so classes are top-level.
                // So there is no clash of field declarations with variables of enclosing contexts.
                // But there can be clashes with field declarations of the super class.
                // But hiding fields is ok!
            }
        }

        Map<Symbol, Method> methods = clazz.methods;
        for (Map.Entry<Symbol, Method> symbolMethod : methods.entrySet())
            try {
                final Symbol methodSymbol = symbolMethod.getKey();
                final Method method = symbolMethod.getValue();
                symbolTable.put(methodSymbol, method);
            } catch (DuplicateDefExc duplicateDefExc) {
                // There may be overriding.
                // So we may get this exception but that's fine. We do want to overwrite the definition of super.
            }
    }

    private void addSymbolsOf(Method method) {

        for (Method.Parameter p : method.parameters)
            try {
                symbolTable.put(p.name(), p.type);
            } catch (DuplicateDefExc exc) {
                exc.printStackTrace();
                // The local variables can hide fields.
            }
        // This is needed only if we had multiple return statements.
        // In minijava, we only have one return statement at the end.
        try {
            symbolTable.put("$ret", method.returnType);
        } catch (DuplicateDefExc exc) {
            exc.printStackTrace();
            // $ret is only defined once in the scope of each method.
        }
    }

    private Type resolve(Type type) {
        if (type instanceof DefinedType) {
            DefinedType definedType = (DefinedType) type;
            try {
                return symbolTable.get(definedType.name);
            } catch (SymbolNotFoundException e) {
                throw new TypeNotFoundException("No type def found for the id " + definedType.name());
            }
        } else
            return type;
    }

    private boolean matchParamsArgs(Type[] params, Type[] args) throws TypeMismatchExc {
        if (params.length != args.length)
            throw new TypeMismatchExc(TypeMismatchExc.NUMBER_OF_ARGUMENTS);
        for (int i = 0; i < params.length; i++) {
            if (!isEqualOrSubClass(args[i], params[i]))
                throw new TypeMismatchExc(i);
        }
        return true;
    }

    private boolean isEqualOrSubClass(Type subType, Type superType) {
        return (subType == superType) || (isSubClass(subType, superType));
    }

    private boolean isSubClass(Type subType, Type superType) {
        if (!(subType instanceof Class) || !(superType instanceof Class))
            return false;
        Class subClass = (Class) subType;
        Class superClass = (Class) superType;

        return subClass.isSubclass(superClass);
    }

// ----------------------------------------------------------------------
// Visits ---------------------------------------------------------------

	public Type visit(CompilationUnit compilationUnit) {
        symbolTable.startScope();
		compilationUnit.mainClass.accept(this);
		compilationUnit.classes.accept(this);
        symbolTable.endScope();
        System.err.println(compilationUnit.toString());
		return OK.instance; // Return OK just to return something.
	}

	public Type visit(parsing.ast.tree.MainClass mainClass) {
        try {
            String className = mainClass.name.token.toString();
            Class clazz = (Class)symbolTable.get(className);

            symbolTable.startScope();
            addSymbolsOf(clazz);
            // In the main, we only have a single static main method.
            //symbolTable.put("this", clazz);

            symbolTable.startScope();

            symbolTable.put(mainClass.parameterId.toString(), StringArray.instance());

            mainClass.varDecls.accept(this);
            mainClass.statements.accept(this);

            symbolTable.endScope();
            symbolTable.endScope();

        } catch (SymbolNotFoundException e) {
            e.printStackTrace();
            // The name of the main class is in the initial symbol table.
            // So it is found in the symbol table.
        } catch (DuplicateDefExc e) {
            e.printStackTrace();
            // The name of the parameter of main method cannot be the same as field of the main class.
            // But in the grammar, the main class has no fields. So this cannot happen.
        }
        return OK.instance;
	}

    public Type visit(parsing.ast.tree.Class theClass) {
        try {
            String className = theClass.name.token.toString();
            Class clazz = (Class)symbolTable.get(className);

            symbolTable.startScope();

            addSymbolsOf(clazz);
            symbolTable.put("this", clazz);

            // We do not need to visit fields. They are added by addSymbolsOf.
            theClass.methodDecls.accept(this);

            symbolTable.endScope();

        } catch (SymbolNotFoundException e) {
            e.printStackTrace();
            // The name of classes are in the initial symbol table.
            // So it is found in the symbol table.

        } catch (DuplicateDefExc exc) {
            // "this" is inserted once and cannot be a duplicate.
        }
        return OK.instance;
	}

	public Type visit(parsing.ast.tree.Method method) {
        try {
            String name = method.name.token.toString();
            Method methodInfo = (Method)symbolTable.get(name);

            symbolTable.startScope();

            addSymbolsOf(methodInfo);

            // Check that there are no more than one declaration for a local variable name.
            Set<String> paramNames = new HashSet<String>();
            for (Method.Parameter parameter : methodInfo.parameters)
                paramNames.add(parameter.name());

            Set<String> varNames = new HashSet<String>();
            List<VarDecl> varDecls = method.varDecls;
            Iterator<VarDecl> iter = varDecls.iterator();
            while (iter.hasNext()) {
                VarDecl varDecl = iter.next();
                final String varName = varDecl.id.token.toString();
                if ((paramNames.contains(varName)) || (! varNames.add(varName)))
                    throw new DuplicateDeclExc("Duplicate declaration of variable " + varName + ".");
            }

            method.varDecls.accept(this);
            method.statements.accept(this);

            symbolTable.endScope();

        } catch (SymbolNotFoundException e) {
            e.printStackTrace();
            // At the beginning of the scope of a class, the methods are added to the symbol table.
            // So methods are found here.
        }
		System.err.println(method.toString());
		return OK.instance;
	}

	public Type visit(VarDecl varDecl) {

		String name = varDecl.id.token.toString();
        Type type = varDecl.type.accept(this);
        type = resolve(type);
        try {
            symbolTable.put(name, type);
        } catch (DuplicateDefExc exc) {
            // A local variable Declaration may hide a field declaration or a class definition.
            // What about double local vars? That is checked in the visitor of MethodDefinition.
        }
		System.err.println(varDecl.toString());
		return OK.instance;
	}

	public Type visit(Block block) {
		// Here, blocks cannot contain variable declarations. All variable declarations are done at
		// the beginning of methods. Therefore, we do not push a new symbol table (ScopeSymbolTable) to
		// the symbol table stack here.
		// If blocks could have variable declarations inside, a new symbol table should be pushed
		// to the stack and popped it in this method.
		block.statements.accept(this);
		System.err.println(block.toString());
		return OK.instance;
	}

	public Type visit(Id id) {
		// Identifier
        //   In definitions
        //     To define defined Types
		//       1. Class name
		//          Class: class Id ...
        //     To define variables
		//       2. Var name in a var declaration.
		//          int id;
		//       3. Name of a Parameter.
		//          MethodDefinition: public int methodName(int id)
        //     To define method names (function names)
		//       4. MethodDefinition name
		//          MethodDefinition: public int id(int paramName)
        //   Referring to definitions
        //     Referring to defined types
		//       5. The type to new
		//          Allocation: new Id()
		//       6. Superclass names in a Class.
		//          Class: class AClass extends Id
		//       7. Type of a var declaration.
		//          VarDeclarartion: Id a;
		//       8. Type of Parameter.
		//          Parameter: int method(Id param)
		//       9. Return type of a method.
		//          MethodDefinition: Id method(...)
        //     Referring to variables
		//       10. Var access in an expression.
		//           reading and writing a variable: id1 = id2
		//           ArrayAssign: id[1] = ...
        //     Referring to methods (function names)
		//       11. MethodDefinition name in a method call
		//           MethodCall: a.id(...)

		//    We call visit on an identifier only for var access. Its type should be computed and returned.

		String name = id.token.toString();
		System.err.println(name);
        try {
            return symbolTable.get(name);
        } catch (SymbolNotFoundException e) {
            throw new UndefinedIdentifierException("Identifier \"" + name + "\" not defined.");
        }
	}

	public Type visit(Assign assign) {
		Type idType = assign.id.accept(this);
		Type rightType = assign.right.accept(this);
        if (!(isEqualOrSubClass(rightType, idType)))
			throw new TypeMismatchException("Assigning \"" + rightType + "\" to \"" + idType + "\"");
		System.err.println(assign.toString());
        return idType;
	}

	public Type visit(ArrayAssign arrayAssign) {
		Type arrayType = arrayAssign.array.accept(this);
		if (! (arrayType == IntArray.instance()))
			throw new TypeMismatchException("Indexing non-array type.");
		Type indexType = arrayAssign.index.accept(this);
        if (!(indexType == Int.instance()))
			throw new TypeMismatchException("Non-integer array index.");
		Type rightType = arrayAssign.right.accept(this);
		if (!(rightType == Int.instance()))
            throw new TypeMismatchException("Assigning \"" + rightType + "\" to \"Int Array\" elements.");
		return Int.instance();
	}

	public Type visit(If theIf) {
		Type conditionType = theIf.condition.accept(this);
		if (!(conditionType == Boolean.instance()))
			throw new TypeMismatchException("Non-boolean \"if\" condition.");
		theIf.ifStatement.accept(this);
		theIf.elseStatement.accept(this);
		return OK.instance;
	}

	public Type visit(While theWhile) {
		Type conditionType = theWhile.condition.accept(this);
		if (!(conditionType == Boolean.instance()))
			throw new TypeMismatchException("Non-boolean \"while\" condition.");
		theWhile.body.accept(this);
		return OK.instance();
	}

    public Type visit(Return theReturn) {
        try {
            Type returnExpType = theReturn.expression.accept(this);
            Type declReturnType = symbolTable.get("$ret");

            if (!(returnExpType == declReturnType))
                throw new TypeMismatchException(
                        "Type mismatch between the declared return type " +
                        "and the type of the returned expression.");
            return  returnExpType;

        } catch (SymbolNotFoundException e) {
            // "$ret" is always found.
            return null;
        }
    }

    public Type visit(Print print) {
		Type argumentType = print.argument.accept(this);
        if (!(argumentType == Int.instance()))
			throw new TypeMismatchException("Non-integer input to \"print\".");
		return OK.instance;
	}

	public Type visit(And and) {
		Type type1 = and.operand1.accept(this);
		if (!(type1 == Boolean.instance()))
			throw new TypeMismatchException("Non-boolean left operand for \"&&\".");
		Type type2 = and.operand2.accept(this);
        if (!(type2 == Boolean.instance()))
			throw new TypeMismatchException("Non-boolean right operand for \"&&\".");
		return Boolean.instance();
	}

	public Type visit(LessThan lessThan) {
		Type type1 = lessThan.operand1.accept(this);
		if (!(type1 == Int.instance()))
			throw new TypeMismatchException("Non-integer left operand for \"<\".");
		Type type2 = lessThan.operand2.accept(this);
        if (!(type2 == Int.instance()))
			throw new TypeMismatchException("Non-integer right operand for \"<\".");
		return Boolean.instance();
	}

	public Type visit(Plus plus) {
		Type type1 = plus.operand1.accept(this);
		if (!(type1 == Int.instance()))
			throw new TypeMismatchException("Non-integer left operand for \"+\".");
		Type type2 = plus.operand2.accept(this);
        if (!(type2 == Int.instance()))
			throw new TypeMismatchException("Non-integer right operand for \"+\".");
		return Int.instance();
	}

	public Type visit(Minus minus) {
		Type type1 = minus.operand1.accept(this);
		if (!(type1 == Int.instance()))
			throw new TypeMismatchException("Non-integer left operand for \"-\".");
		Type type2 = minus.operand2.accept(this);
        if (!(type2 == Int.instance()))
			throw new TypeMismatchException("Non-integer right operand for \"-\".");
		return Int.instance();
	}

	public Type visit(Mult mult) {
		Type type1 = mult.operand1.accept(this);
		if (!(type1 == Int.instance()))
			throw new TypeMismatchException("Non-integer left operand for \"*\".");
		Type type2 = mult.operand2.accept(this);
        if (!(type2 == Int.instance()))
			throw new TypeMismatchException("Non-integer right operand for \"*\".");
		return Int.instance();
	}

	public Type visit(Not not) {
		Type type = not.operand.accept(this);
		if (!(type == Boolean.instance()))
			throw new TypeMismatchException("Non-boolean operand for \"!\".");
		return Boolean.instance();
	}

	public Type visit(ArrayLookup arrayLookup) {
		Type array = arrayLookup.array.accept(this);
        if (!(array == IntArray.instance()))
			throw new TypeMismatchException("Indexing non-array type.");
		Type indexType = arrayLookup.index.accept(this);
		if (!(indexType == Int.instance()))
			throw new TypeMismatchException("Non-integer array index.");
		return Int.instance();
	}

	public Type visit(ArrayLength arrayLength) {
		Type arrayType = arrayLength.array.accept(this);
		if (!(arrayType == IntArray.instance()))
			throw new TypeMismatchException("Applying length to a non-array type.");
		return Int.instance();
	}


	static class TypeList implements Type {
		Type[] types;
		TypeList(Type[] types) { this.types = types; }

		public String name() { return "TypeList"; }
	}

	public Type visit(List list) {
		Iterator iterator = list.iterator();
		Type[] types = new Type[list.size()];
		int i = 0;
		while (iterator.hasNext()) {
			Node node = (Node)iterator.next();
			types[i] = node.accept(this);
			i++;
		}
		return new TypeList(types);
	}

	public Type visit(MethodCall methodCall) {
		Type receiverType = methodCall.receiver.accept(this);
		if (!(receiverType instanceof Class))
			throw new MethodCallOnPrimitiveTypeException("MethodDefinition call on primitive type \"" + receiverType + "\".");
		final Class theClass = (Class) receiverType;
		final String methodName = methodCall.methodName.token.toString();
		Method method = theClass.getMethod(methodName).apply(
            new Fun0<Method>() {
                public Method apply() {
                    throw new MethodNotFoundException("Class \"" + theClass.name() + "\" does not have method \"" + methodName + "\".");
                }
            },
            new Fun<Method, Method>() {
                public Method apply(Method method) {
                    return method;
                }
            }
        );

		Type[] formalTypes = method.getParamTypes();

        TypeList typeList = (TypeList)methodCall.arguments.accept(this);
		Type[] argumentTypes = typeList.types;

        try {
			matchParamsArgs(formalTypes, argumentTypes);
		} catch (TypeMismatchExc e) {
			if (e.inequalArgument == TypeMismatchExc.NUMBER_OF_ARGUMENTS)
				throw new TypeMismatchException("Number of arguments not equal to number of parameters. "
						+ argumentTypes.length + " arguments passed for " + formalTypes.length + " parameters.");
			else
				throw new TypeMismatchException("Argument of type " + argumentTypes[e.inequalArgument].name() +
						" passed to parameter of type " + formalTypes[e.inequalArgument].name() + ".");
		}

        return method.returnType;
	}

	public Type visit(ArrayAllocation arrayAllocation) {
		Type sizeType = arrayAllocation.expression.accept(this);
        if (!(sizeType == Int.instance()))
			throw new TypeMismatchException("Non-integer size in array creation");
		return IntArray.instance();
	}

	public Type visit(Allocation allocation) {
		String className = allocation.className.token.toString();
        try {
            Type type = symbolTable.get(className);
            if (! (type instanceof Class))
                throw new NewOnNonClassType("New operator on non-class type " + type);
            return (Class) type;
        } catch (SymbolNotFoundException e) {
            throw new TypeNotFoundException("Unknown Type \"" + className + "\" at instance creation.");
        }
	}

	public Type visit(This theThis) {
        try {
            return symbolTable.get("this");
        } catch (SymbolNotFoundException e) {
            // "this" is inserted before.
            return null;
        }
    }

	public Type visit(IntLiteral intLiteral) {
		return Int.instance();
	}

	public Type visit(TrueLiteral trueLiteral) {
		return Boolean.instance();
	}

	public Type visit(FalseLiteral falseLiteral) {
		return Boolean.instance();
	}

	public Type visit(IntType intType) {
		return Int.instance();
	}

	public Type visit(BooleanType booleanType) {
		return Boolean.instance();
	}

	public Type visit(ArrayType arrayType) {
		return IntArray.instance();
	}
	
// --------------------------------------------------------------
// Visits that don't need to be changed.
// Can also be empty. Never Called here!
    public Type visit(Parameter parameter) {
        return null;
    }

	public Type visit(Token token) {
		return null;
	}
}

