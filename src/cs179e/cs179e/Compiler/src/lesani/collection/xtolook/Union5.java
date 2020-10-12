package lesani.collection.xtolook;

import lesani.collection.func.Fun;

public abstract class Union5<A, B, C, D, E> {
    private Union5() { }

    public A elem1() { return null; }
    public B elem2() { return null; }
    public C elem3() { return null; }
    public D elem4() { return null; }
    public E elem5() { return null; }

    public static <A, B, C, D, E> Elem1<A, B, C, D, E> newUnion1(A a) {
        return new Elem1<A, B, C, D, E>(a);
    }

    public static <A, B, C, D, E> Elem2<A, B, C, D, E> newUnion2(B b) {
        return new Elem2<A, B, C, D, E>(b);
    }

    public static <A, B, C, D, E> Elem3<A, B, C, D, E> newUnion3(C c) {
        return new Elem3<A, B, C, D, E>(c);
    }

    public static <A, B, C, D, E> Elem4<A, B, C, D, E> newUnion4(D d) {
        return new Elem4<A, B, C, D, E>(d);
    }

    public static <A, B, C, D, E> Elem5<A, B, C, D, E> newUnion5(E e) {
        return new Elem5<A, B, C, D, E>(e);
    }

    public final static class Elem1<A, B, C, D, E> extends Union5<A, B, C, D, E> {
        private A a;
        public Elem1(A a) { this.a = a; }
        public A elem1() { return a; }

        @Override
        public String toString() {
            return a.toString();
        }
    }

    public static class Elem2<A, B, C, D, E> extends Union5<A, B, C, D, E> {
        private B b;
        public Elem2(B b) { this.b = b; }
        public B elem2() { return b; }

        @Override
        public String toString() {
            return b.toString();
        }

    }
    public static class Elem3<A, B, C, D, E> extends Union5<A, B, C, D, E> {
        private C c;
        public Elem3(C c) { this.c = c; }
        public C elem3() { return c; }

        @Override
        public String toString() {
            return c.toString();
        }

    }
    public static class Elem4<A, B, C, D, E> extends Union5<A, B, C, D, E> {
        private D d;
        public Elem4(D d) { this.d = d; }
        public D elem4() { return d; }

        @Override
        public String toString() {
            return d.toString();
        }

    }
    public static class Elem5<A, B, C, D, E> extends Union5<A, B, C, D, E> {
        private E e;
        public Elem5(E e) { this.e = e; }
        public E elem5() { return e; }

        @Override
        public String toString() {
            return e.toString();
        }

    }

    public <F> F apply(Fun<A, F> f, Fun<B, F> g, Fun<C, F> h, Fun<D, F> i, Fun<E, F> j) {
        if (this instanceof Elem1)
            return f.apply(elem1());
        if (this instanceof Elem2)
            return g.apply(elem2());
        if (this instanceof Elem3)
            return h.apply(elem3());
        if (this instanceof Elem4)
            return i.apply(elem4());
        return j.apply(elem5());
    }


}

