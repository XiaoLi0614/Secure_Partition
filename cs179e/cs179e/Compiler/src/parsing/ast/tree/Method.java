package parsing.ast.tree;

import parsing.ast.visitor.GIVisitor;
import parsing.ast.visitor.Visitor;
import parsing.ast.visitor.GISVisitor;
import parsing.ast.visitor.GSVisitor;

/**
 * User: Mohsen's Desktop
 * Date: Aug 26, 2009
 */

public class Method implements Node {
	public Type returnType;
	public Id name;
	public List<Parameter> parameters;
	public List<VarDecl> varDecls;

	public List<Statement> statements;

    // In minijava, only one return comes at the end.
	//public Expression returnExp;

	public Method(Type returnType, Id name, List<Parameter> parameters, List<VarDecl> varDecls, List<Statement> statements/*, Expression returnExp*/) {
		this.returnType = returnType;
		this.name = name;
		this.parameters = parameters;
		if (parameters == null)
			this.parameters = new List<Parameter>();
		this.varDecls = varDecls;
		if (varDecls == null)
			this.varDecls = new List<VarDecl>();
		this.statements = statements;
		if (statements == null)
			this.statements = new List<Statement>();
		//this.returnExp = returnExp;
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

