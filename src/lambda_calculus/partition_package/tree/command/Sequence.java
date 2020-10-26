package lambda_calculus.partition_package.tree.command;

import lambda_calculus.partition_package.visitor.PartitionVisitor;

public class Sequence extends Command {
    public Command command1;
    public Command command2;

    public Sequence(Command command_1, Command command_2) {
        this.command1 = command_1;
        this.command2 = command_2;
    }

    public <R> R accept(PartitionVisitor.CommandVisitor<R> sequence){
        return sequence.visit(this);
    }

    @Override
    public String toString(){
        return command1 +"; " + command2;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sequence that = (Sequence) o;

        if (! command1.equals(that.command1)) return false;
        else return command2.equals(that.command2);
    }
}