package lesani.xtras;

public class Test {
    public static void main(String[] args) {
        class A {}
        class B extends A {}
        class C extends A {}
        class Ref<T> {
            private T t;

            public T get() {
                return t;
            }

            public void set(T t) {
                this.t = t;
            }
        }

        // --------------------------------------------

        // Ref<B> refB = new Ref<B>();
        // Ref<A> refA = refB;         // Going up the class hierarchy.
                                       // Upcasting Ref[B] tp Ref[A]
                                       // This is not allowed by the type system.
        // refA.set(new C());          // Using the upcasted reference to "write" a object of another subtype.
                                       // An object of another subtype C is passed to method set(A) of type Ref[A].
                                       // This is always allowed because of the covariance of function parameters.
        // B b = refB.get();           // Reusing The first reference to read the value.
                                       // Statically, it is a B but at runtime, it is a C!

        // That's why most of collection classes are not covariant.

        // --------------------------------------------

        // This does not work:

        // class Holder {
        //     public void method(Ref<A> ref) {
        //     }
        // }
        // Holder h = new Holder();

        // Ref<B> refB = new Ref<B>();
        // h.method(refB);                 // This is not allowed by the type system.

        // Then how to pass Ref[B] as Ref[A]?
        // We should either prevent writing at to upcasted reference or reading from the original reference.
        // The latter seems to be infeasible but the former can be done as follows:
        class Holder {
             public void method(Ref<? extends A> ref) {
                 // Now, the method declares that the parameter is a Ref of a subtype of A. It can be any subtype.
                 // Thus the following is disallowed. No object of type A or a subtype can be "written".
                 // The following two lines are prevented by the type system.
                 // ref.set(new C());
                 // ref.set(new A());
             }
        }
        Holder h = new Holder();

        Ref<B> refB = new Ref<B>();
        h.method(refB);
        B b = refB.get();
        // --------------------------------------------

    }




}
