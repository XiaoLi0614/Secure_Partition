package lambda_calculus.partition_package.tree.expression;


import lambda_calculus.partition_package.visitor.PartitionVisitor;

public class Conditional implements Expression {

    public Expression condition;
    public Expression ifExp;
    public Expression elseExp;

    public Conditional(Expression condition, Expression ifExp, Expression elseExp) {
        this.condition = condition;
        this.ifExp = ifExp;
        this.elseExp = elseExp;
    }

    public <R> R accept(PartitionVisitor.ExpressionVisitor<R> conditionalVisitor) {
        return conditionalVisitor.visit(this);
    }

    @Override
    public String toString(){
        return "If (" + condition + ") then (" + ifExp +") else (" + elseExp + ")";
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conditional that = (Conditional) o;

        if(!condition.equals(that.condition)) return false;
        else if (! ifExp.equals(that.ifExp)) return false;
        else return elseExp.equals(that.elseExp);
    }
}
