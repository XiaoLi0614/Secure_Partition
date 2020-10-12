package lesani.collection.xtolook;

import lesani.collection.func.Fun;

public abstract class Union3<A, B, C> {
    private Union3() { }

    public A elem1() { return null; }
    public B elem2() { return null; }
    public C elem3() { return null; }

    public Elem1<A, B, C> newEither(A a) {
        return new Elem1<A, B, C>(a);
    }

    public Elem2<A, B, C> newEither1(B b) {
        return new Elem2<A, B, C>(b);
    }

    public Elem3<A, B, C> newEither2(C c) {
        return new Elem3<A, B, C>(c);
    }

    public final static class Elem1<A, B, C> extends Union3<A, B, C> {
        private A a;
        public Elem1(A a) { this.a = a; }
        public A elem1() { return a; }

        @Override
        public String toString() {
            return a.toString();
        }
    }

    public static class Elem2<A, B, C> extends Union3<A, B, C> {
        private B b;
        public Elem2(B b) { this.b = b; }
        public B elem2() { return b; }

        @Override
        public String toString() {
            return b.toString();
        }

    }
    public static class Elem3<A, B, C> extends Union3<A, B, C> {
        private C c;
        public Elem3(C c) { this.c = c; }
        public C elem3() { return c; }

        @Override
        public String toString() {
            return c.toString();
        }

    }

    public <D> D apply(Fun<A, D> f, Fun<B, D> g, Fun<C, D> h) {
        if (this instanceof Elem1)
            return f.apply(elem1());
        if (this instanceof Elem2)
            return g.apply(elem2());
        return h.apply(elem3());
    }
}

