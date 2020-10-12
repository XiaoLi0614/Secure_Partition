package jsrc.x10.ast.tree.declaration;

import jsrc.x10.ast.tree.Node;

public enum Visibility implements Node {
    PUBLIC, PRIVATE, PROTECTED, PACKAGE;

    public String toString() {
        if (this==PACKAGE)
            return "";
        return super.toString().toLowerCase();
    }


    public static void main(String[] args) {
        System.out.println(PUBLIC);
    }
}
