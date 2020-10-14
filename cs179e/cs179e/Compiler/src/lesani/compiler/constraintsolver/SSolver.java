package lesani.compiler.constraintsolver;

import java.util.*;

public class SSolver<Var, T> {

    //private Set<ConstConstraint<Var, T>> constConstraints;
    //private Set<ConstConstraint<Var, T>> constConstraints;
    private Set<Constraint<Var>> constraints;

    public SSolver(Set<ConstConstraint<Var, T>> constConstraints, Set<SubsetConstraint<Var>> subsetConstraints) {
        for (ConstConstraint<Var, T> constConstraint : constConstraints)
            this.constraints.add(constConstraint);
        for (SubsetConstraint<Var> subsetConstraint : subsetConstraints)
            this.constraints.add(subsetConstraint);
    }
    public SSolver(Set<Constraint<Var>> constraints) {
        this.constraints = constraints;
    }

    HashMap<Var, Node<Var, T>> nodes = new HashMap<Var, Node<Var, T>>();
    private Node<Var, T> getNode(Var var) {
        Node<Var, T> node = nodes.get(var);
        if (node == null) {
            node = new Node<Var, T>(var);
            nodes.put(var, node);
        }
        return node;
    }

    private Map<Var, Set<T>> values() {
        Map<Var, Set<T>> values = new HashMap<Var, Set<T>>();
        for (Map.Entry<Var, Node<Var, T>> entry : nodes.entrySet())
            values.put(entry.getKey(), entry.getValue().values);
        return values;
    }

    public Map<Var, Set<T>> solve() {
        for (Constraint<Var> constraint : constraints)
            process(constraint);
        return values();
    }

    private Queue<Node<Var, T>> fringe = new LinkedList<Node<Var, T>>();
    public void process(Constraint<Var> constraint) {
        if (constraint instanceof ConstConstraint) {
            ConstConstraint<Var, T> cons = (ConstConstraint<Var, T>) constraint;
            Var var = cons.var;
            Set<T> values = cons.rightValue;
            Node<Var, T> initVarNode = getNode(var);
            initVarNode.addValues(values);
            fringe.add(initVarNode);
        } else if (constraint instanceof SubsetConstraint) {
            SubsetConstraint<Var> cons = (SubsetConstraint<Var>) constraint;
            Node<Var, T> initVarNode = getNode(cons.var);
            Node<Var, T> initRightVarNode = getNode(cons.rightVar);
            initRightVarNode.addNeighbor(initVarNode);
            boolean changed = initVarNode.addValues(initRightVarNode.values);
            if (changed)
                fringe.add(initVarNode);
        } else if (constraint instanceof SubtractConstraint) {
            SubtractConstraint<Var> cons = (SubtractConstraint<Var>) constraint;
            Node<Var, T> initVarNode = getNode(cons.var);
            Node<Var, T> initRightVarNode = getNode(cons.rightVar);
            initRightVarNode.addNeighbor(initVarNode);
            boolean changed = initVarNode.addValues(initRightVarNode.values);
            if (changed)
                fringe.add(initVarNode);
        }
        processFringe();
    }

    private void processFringe() {
        while (!fringe.isEmpty()) {
            Node<Var, T> rightVarNode = fringe.remove();
            for (Node<Var, T> varNode: rightVarNode.neighbors) {
                boolean changed = varNode.addValues(rightVarNode.values);
                if (changed)
                    fringe.add(varNode);
            }
        }
    }
}


