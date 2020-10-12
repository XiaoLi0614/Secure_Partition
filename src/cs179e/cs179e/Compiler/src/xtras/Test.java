package xtras;

public class Test {
    static class User {
        //static class C1 {}
        public void g() {
            String V = "a";
            V += "b";
            String C1 = "a";
            String c1 = "a";
            new V().f(new C1());
        }
    }

    static class V {
        public C2 f(C1 c1) {
            return null;
        }
    }

    static class C1 {}
    static class C2 {}

    static class B extends A {}
    static class A {}

    public static void main(String[] args) {

    }
}
