package typechecking.direlicitor;

import lesani.collection.func.Fun;
import lesani.collection.func.Fun0;
import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;
import lesani.compiler.typing.Symbol;
import parsing.ast.tree.*;
import parsing.ast.visitor.GSVisitor;
import typechecking.exceptions.DuplicateDeclExc;
import typechecking.exceptions.DuplicateTypeDefExc;
import typechecking.types.Boolean;
import typechecking.types.Class;
import typechecking.types.*;
import typechecking.types.MainClass;
import typechecking.types.Method;
import typechecking.types.Type;
import typechecking.types.Void;
import java.util.*;
import java.util.List;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class SignatureReader implements GSVisitor<Object> {

    public static ClassDir read(CompilationUnit compilationUnit) {
        return new SignatureReader(compilationUnit).read();
    }

	private CompilationUnit compilationUnit;

	public SignatureReader(CompilationUnit compilationUnit) {
		this.compilationUnit = compilationUnit;
	}

	public ClassDir read() {
		return (ClassDir)compilationUnit.accept(this);
	}

	private Type checkTypeForId(parsing.ast.tree.Type treeType) {
		Type type;
		if (treeType instanceof Id) {
			Id id = (Id) treeType;
			type = new DefinedType(id.token.toString());
		} else
			type = (Type)treeType.accept(this);
		return type;
	}

	// ------------------------------------------------------
	// Visits

	public Object visit(CompilationUnit compilationUnit) {
		// Note: Here, we do not have inner classes. So, we get all classes in this method
		// and add them to the initial symbol table.
		// Generally, we should maintain a stack of symbol tables.
        // A new symbol table should be inserted on entry to each scope.
		// Nested classes are new scopes ...

		ClassDir classDir = new ClassDir();

		MainClass mainClass = (MainClass) compilationUnit.mainClass.accept(this);
        classDir.setMainClass(mainClass);
        //throw new DuplicateTypeDefExc("Duplicate definition for class \"" + mainClass.name() + "\".");
        // This is the first class that we add, it cannot be a duplicate.

		List<Class> classes = (List<Class>)compilationUnit.classes.accept(this);
		for (Class theClass : classes)
            if (! classDir.add(theClass))
                throw new DuplicateTypeDefExc("Duplicate definition(s) for class \"" + theClass.name() + "\".");

		return classDir;
	}

	public Object visit(parsing.ast.tree.MainClass mainClass) {
		String name = (String)mainClass.name.accept(this);
		String parameterName = (String)mainClass.parameterId.accept(this);
		//// We do not need to traverse
		////mainClass.fieldDecls.accept(this);
		////mainClass.block.accept(this);
		List<Method.Parameter> parameters = new LinkedList<Method.Parameter>();
		parameters.add(new Method.Parameter(parameterName, StringArray.instance()));
		Method mainMethod = new Method("main", parameters, Void.instance(), mainClass.statements);

        final HashMap<Symbol, Method> methods = new HashMap<Symbol, Method>();
        methods.put(Symbol.symbol("main"), mainMethod);

        return new MainClass(
				name,
				new HashMap<Symbol, Type>(),
                methods,
				mainMethod,
                mainMethod.statements);
	}

	public Object visit(parsing.ast.tree.Class theClass) {
		String name = (String)theClass.name.accept(this);

		Option<DefinedType> superClass = theClass.superClassId.apply(
            new Fun0<Option<DefinedType>>() {
                public Option<DefinedType> apply() {
                    return None.instance();
                }
            },
            new Fun<Id, Option<DefinedType>>() {
                public Option<DefinedType> apply(Id superClassId) {
                    String superClassName = (String) superClassId.accept(SignatureReader.this);
                    return new Some<DefinedType>(new DefinedType(superClassName));
                }
            }
        );

		List<Class.Field> fields = (List<Class.Field>)theClass.fieldDecls.accept(this);
        Set<String> fieldNames = new HashSet<String>();
        for (Class.Field varDecl : fields) {
            final String fieldName = varDecl.name();
            if (!fieldNames.add(fieldName))
                throw new DuplicateDeclExc("Duplicate declaration of field " + fieldName + ".");
        }

		List<Method> methods = (List<Method>)theClass.methodDecls.accept(this);
        Set<String> methodNames = new HashSet<String>();
        for (Method method : methods) {
            final String methodName = method.name();
            if (! methodNames.add(methodName))
                throw new DuplicateDeclExc("Duplicate declaration of method " + methodName + ".");
        }

        return new Class(name, superClass, fields, methods, theClass.methodDecls);
	}

	public Object visit(parsing.ast.tree.VarDecl varDecl) {
		Type type = checkTypeForId(varDecl.type);
		String name = (String) varDecl.id.accept(this);
		return new Class.Field(type, name);
	}

	public Object visit(parsing.ast.tree.Method method) {
		Type returnType = checkTypeForId(method.returnType);
		String name = (String) method.name.accept(this);
		List<Method.Parameter> parameters = (List<Method.Parameter>) method.parameters.accept(this);

        Set<String> paramNames = new HashSet<String>();
        for (Method.Parameter p : parameters)
            if (! paramNames.add(p.name()))
                throw new DuplicateDeclExc("Duplicate declaration of parameter " + p.name() + ".");

		////method.fieldDecls.accept(this);
		////method.block.accept(this);
		////method.returnExp.accept(this);
        List<Statement> statements = method.statements;


        return new Method(name, parameters, returnType, statements);
        // local vars should not be added to the symbol table here because
        // forward references are typechecked then.
	}

    public Object visit(Parameter parameter) {
		Type type = checkTypeForId(parameter.type);
		String name = (String)parameter.name.accept(this);
		return new Method.Parameter(name, type);
	}

	public Object visit(Id id) {
		return id.token.toString();
	}

	public Object visit(IntType intType) {
		return Int.instance();
	}

	public Object visit(BooleanType booleanType) {
		return Boolean.instance();
	}

	public Object visit(ArrayType arrayType) {
		return IntArray.instance();
	}

	public Object visit(parsing.ast.tree.List list) {
		List<Object> resultList = new LinkedList<Object>();
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			Node node = (Node)iterator.next();
			Object object = node.accept(this);
			resultList.add(object);
		}
		return resultList;
	}

// --------------------------------------------------------------
// Visits that don't need to be changed.
// Can also be empty. Never Called here!

	public Object visit(Block block) {
		block.statements.accept(this);
		return null;
	}

	public Object visit(Assign assign) {
		assign.id.accept(this);
		assign.right.accept(this);
		return null;
	}

	public Object visit(ArrayAssign arrayAssign) {
		arrayAssign.array.accept(this);
		arrayAssign.index.accept(this);
		arrayAssign.right.accept(this);
		return null;
	}

	public Object visit(If theIf) {
		theIf.condition.accept(this);
		theIf.ifStatement.accept(this);
		theIf.elseStatement.accept(this);
		return null;
	}

	public Object visit(While theWhile) {
		theWhile.condition.accept(this);
		theWhile.body.accept(this);
		return null;
	}

    public Object visit(Return aReturn) {
        aReturn.expression.accept(this);
        return null;
    }

    public Object visit(Print print) {
		print.argument.accept(this);
		return null;
	}

	public Object visit(And and) {
		and.operand1.accept(this);
		and.operand2.accept(this);
		return null;
	}

	public Object visit(LessThan lessThan) {
		lessThan.operand1.accept(this);
		lessThan.operand2.accept(this);
		return null;
	}

	public Object visit(Plus plus) {
		plus.operand1.accept(this);
		plus.operand2.accept(this);
		return null;
	}

	public Object visit(Minus minus) {
		minus.operand1.accept(this);
		minus.operand2.accept(this);
		return null;
	}

	public Object visit(Mult mult) {
		mult.operand1.accept(this);
		mult.operand2.accept(this);
		return null;
	}

	public Object visit(ArrayLookup arrayLookup) {
		arrayLookup.array.accept(this);
		arrayLookup.index.accept(this);
		return null;
	}

	public Object visit(ArrayLength arrayLength) {
		arrayLength.array.accept(this);
		return null;
	}

	public Object visit(MethodCall methodCall) {
		methodCall.receiver.accept(this);
		methodCall.methodName.accept(this);
		methodCall.arguments.accept(this);
		return null;
	}

	public Object visit(IntLiteral intLiteral) {
		intLiteral.token.accept(this);
		return null;
	}

	public Object visit(TrueLiteral trueLiteral) {
		return null;
	}

	public Object visit(FalseLiteral falseLiteral) {
		return null;
	}

	public Object visit(This theThis) {
		return null;
	}

	public Object visit(ArrayAllocation arrayAllocation) {
		arrayAllocation.expression.accept(this);
		return null;
	}

	public Object visit(Allocation allocation) {
		allocation.className.accept(this);
		return null;
	}

	public Object visit(Not not) {
		not.operand.accept(this);
		return null;
	}

	public Object visit(Token token) {
		return null;
	}

}



