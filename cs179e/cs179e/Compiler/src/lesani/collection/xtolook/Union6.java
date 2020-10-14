package lesani.collection.xtolook;

import lesani.collection.func.Fun;

public abstract class Union6<A, B, C, D, E, F> {
    private Union6() { }

    public A elem1() { return null; }
    public B elem2() { return null; }
    public C elem3() { return null; }
    public D elem4() { return null; }
    public E elem5() { return null; }
    public F elem6() { return null; }

    public static <A, B, C, D, E, F> Elem1<A, B, C, D, E, F> newUnion1(A a) {
        return new Elem1<A, B, C, D, E, F>(a);
    }

    public static <A, B, C, D, E, F> Elem2<A, B, C, D, E, F> newUnion2(B b) {
        return new Elem2<A, B, C, D, E, F>(b);
    }

    public static <A, B, C, D, E, F> Elem3<A, B, C, D, E, F> newUnion3(C c) {
        return new Elem3<A, B, C, D, E, F>(c);
    }

    public static <A, B, C, D, E, F> Elem4<A, B, C, D, E, F> newUnion4(D d) {
        return new Elem4<A, B, C, D, E, F>(d);
    }

    public static <A, B, C, D, E, F> Elem5<A, B, C, D, E, F> newUnion5(E e) {
        return new Elem5<A, B, C, D, E, F>(e);
    }

    public static <A, B, C, D, E, F> Elem6<A, B, C, D, E, F> newUnion6(F f) {
        return new Elem6<A, B, C, D, E, F>(f);
    }

    public final static class Elem1<A, B, C, D, E, F> extends Union6<A, B, C, D, E, F> {
        private A a;
        public Elem1(A a) { this.a = a; }
        public A elem1() { return a; }

        @Override
        public String toString() {
            return a.toString();
        }
    }

    public static class Elem2<A, B, C, D, E, F> extends Union6<A, B, C, D, E, F> {
        private B b;
        public Elem2(B b) { this.b = b; }
        public B elem2() { return b; }

        @Override
        public String toString() {
            return b.toString();
        }

    }
    public static class Elem3<A, B, C, D, E, F> extends Union6<A, B, C, D, E, F> {
        private C c;
        public Elem3(C c) { this.c = c; }
        public C elem3() { return c; }

        @Override
        public String toString() {
            return c.toString();
        }

    }
    public static class Elem4<A, B, C, D, E, F> extends Union6<A, B, C, D, E, F> {
        private D d;
        public Elem4(D d) { this.d = d; }
        public D elem4() { return d; }

        @Override
        public String toString() {
            return d.toString();
        }

    }
    public static class Elem5<A, B, C, D, E, F> extends Union6<A, B, C, D, E, F> {
        private E e;
        public Elem5(E e) { this.e = e; }
        public E elem5() { return e; }

        @Override
        public String toString() {
            return e.toString();
        }

    }
    public static class Elem6<A, B, C, D, E, F> extends Union6<A, B, C, D, E, F> {
        private F f;
        public Elem6(F f) { this.f = f; }
        public F elem6() { return f; }

        @Override
        public String toString() {
            return f.toString();
        }

    }

    public <G> G apply(Fun<A, G> f, Fun<B, G> g, Fun<C, G> h, Fun<D, G> i, Fun<E, G> j, Fun<F, G> k) {
        if (this instanceof Elem1)
            return f.apply(elem1());
        if (this instanceof Elem2)
            return g.apply(elem2());
        if (this instanceof Elem3)
            return h.apply(elem3());
        if (this instanceof Elem4)
            return i.apply(elem4());
        if (this instanceof Elem5)
            return j.apply(elem5());
        return k.apply(elem6());
    }


}

