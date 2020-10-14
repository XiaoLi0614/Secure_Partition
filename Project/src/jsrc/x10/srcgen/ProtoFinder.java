package jsrc.x10.srcgen;

import jsrc.x10.ast.tree.declaration.ClassDecl;
import jsrc.x10.ast.tree.declaration.Constructor;
import jsrc.x10.ast.tree.declaration.File;
import jsrc.x10.ast.tree.declaration.Method;
import jsrc.x10.ast.tree.expression.MethodCall;
import jsrc.x10.ast.tree.expression.id.Id;
import jsrc.x10.ast.tree.statement.*;
import jsrc.x10.ast.visitor.DFSVisitor;

import java.util.HashSet;
import java.util.Set;


public class ProtoFinder {
    private File file;

    public ProtoFinder(File file) {
        this.file = file;
    }

    public void findAndSet() {
        ClassDecl[] classDecls = file.classDecls;
        for (ClassDecl classDecl: classDecls)
            findAndSet(classDecl);
    }


    private void findAndSet(ClassDecl classDecl) {
        Method[] methods = classDecl.methods;
        CallGraph callGraph = new CallGraph(methods);
        Constructor[] constructors = classDecl.constructors;
        for (Constructor constructor : constructors) {
            Set<String> calledMethods = CalledMethodsFinder.find(constructor.block);
            Set<Integer> reachableMethodIndices =
                    callGraph.getReachableMethodIndicesFrom(calledMethods);
            for (Integer reachableMethodIndex : reachableMethodIndices) {
                methods[reachableMethodIndex].isProto = true;
            }
        }
    }


    private static class CallGraph {
        Method[] methods;
        private Boolean[][] adj;
        CallGraph(Method[] methods) {
            this.methods = methods;
            adj = new Boolean[methods.length][methods.length];
            for (int i = 0; i < adj.length; i++) {
                for (int j = 0; j < adj[i].length; j++) {
                    adj[i][j] = false;
                }
            }
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                Set<String> calledMethods =
                        CalledMethodsFinder.find(method.block);
                for (String calledMethod : calledMethods) {
                    Integer methodIndex = getMethodIndex(calledMethod);
                    adj[i][methodIndex] = true;
                }
            }
        }

        public Set<Integer> getReachableMethodIndicesFrom(Set<String> methodNames) {
            HashSet<Integer> fringe = new HashSet<Integer>();
            for (String methodName : methodNames) {
                fringe.add(getMethodIndex(methodName));
            }
            return graphTraverse(fringe);
        }

        private Set<Integer> getAdjecents(int i) {
            HashSet<Integer> adjacents = new HashSet<Integer>();
            for (int j = 0; j < adj[i].length; j++) {
                if (adj[i][j])
                    adjacents.add(j);
            }
            return adjacents;
        }
        private Set<Integer> graphTraverse(HashSet<Integer> fringe) {
            HashSet<Integer> closed = new HashSet<Integer>();
            while (!fringe.isEmpty()) {
                Integer current = fringe.iterator().next();
                Set<Integer> adjacents = getAdjecents(current);
                fringe.remove(current);
                closed.add(current);
                for (Integer adjacent : adjacents) {
                    if (!closed.contains(adjacent))
                        fringe.add(adjacent);
                }
            }
            return closed;
        }

        private Integer getMethodIndex(String methodName) {
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                if (method.name.lexeme.equals(methodName))
                    return i;
            }
            return -1;
        }
    }


    private static class CalledMethodsFinder extends DFSVisitor {
        Block block;
        HashSet<String> methodNames;

        private CalledMethodsFinder(Block block) {
            this.block = block;
        }

        public HashSet<String> getMethodNames() {
            methodNames = new HashSet<String>();
            fileVisitor.classVisitor.shared.statementVisitor.visitDispatch(block);
            return methodNames;
        }

        public static HashSet<String> find(Block block) {
            return (new CalledMethodsFinder(block)).getMethodNames();
        }

        {fileVisitor = new FileVisitor() {
            {classVisitor = new ClassVisitor() {
                {shared = new Shared() {
                    {expressionVisitor = new ExpressionVisitor() {
                        public Object visit(MethodCall methodCall) {
                            if (!methodCall.receiver.isPresent()) {
                                if (methodCall.methodName instanceof Id) //Not special ids
                                    methodNames.add(methodCall.methodName.lexeme);
                            }
                            return super.visit(methodCall);
                        }
                    };}
                };}
            };}
        };}
    }
}
