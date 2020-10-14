package typechecking.types;

import java.lang.*;

import lesani.compiler.typing.Symbol;
import parsing.ast.tree.Statement;

import java.util.HashMap;
import java.util.List;

/**
 * User: Mohsen's Desktop
 * Date: Aug 28, 2009
 */

public class MainClass extends Class {

	// Because main method is static and we do not support static method call, we separate it.
	public Method mainMethod;
    public List<Statement> statements;

	public MainClass(String name,
                     HashMap<Symbol, Type> fields,
                     HashMap<Symbol, Method> methods,
                     Method mainMethod,
                     List<Statement> statements) {

		super(name, fields, methods);
        //addMethod(mainMethod.name(), mainMethod);
        this.mainMethod = mainMethod;
        this.statements = statements;
	}
}
