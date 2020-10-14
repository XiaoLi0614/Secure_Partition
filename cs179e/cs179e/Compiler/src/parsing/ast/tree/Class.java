package parsing.ast.tree;

import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;
import parsing.ast.visitor.Visitor;
import parsing.ast.visitor.GISVisitor;
import parsing.ast.visitor.GIVisitor;
import parsing.ast.visitor.GSVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class Class implements Node {
	public Id name;
	public Option<Id> superClassId;
	public List<VarDecl> fieldDecls;
	public List<Method> methodDecls;

	public Class(Id name, Id superClassId, List<VarDecl> fieldDecls, List<Method> methodDecls) {
		init(name, fieldDecls, methodDecls);
		this.superClassId = new Some<Id>(superClassId);
	}

	public Class(Id name, List<VarDecl> fieldDecls, List<Method> methodDecls) {
		init(name, fieldDecls, methodDecls);
		this.superClassId = None.instance();
	}

	private void init(Id name, List<VarDecl> varDeclarations, List<Method> methodDeclarations) {
		this.name = name;
		this.fieldDecls = varDeclarations;
		if (varDeclarations == null)
			this.fieldDecls = new List<VarDecl>();
		this.methodDecls = methodDeclarations;
		if (methodDeclarations == null)
			this.methodDecls = new List<Method>();
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public <R, A> R accept(GISVisitor<R, A> visitor, A inheritedVar) {
		return visitor.visit(this, inheritedVar);
	}

	public <A> void accept(GIVisitor<A> visitor, A inheritedVar) {
		visitor.visit(this, inheritedVar);
	}

	public <R> R accept(GSVisitor<R> visitor) {
		return visitor.visit(this);
	}
}


