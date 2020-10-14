package lesani.collection.xtolook;

import lesani.collection.func.Fun;

public abstract class Either<A, B> {
    private Either() { }

    public A left() { return null; }
    public B right() { return null; }

    public Left<A, B> newEither1(A a) {
        return new Left<A, B>(a);
    }

    public Right<A, B> newEither2(B b) {
        return new Right<A, B>(b);
    }

    public final static class Left<A, B> extends Either<A, B> {
        private A a;
        public Left(A a) { this.a = a; }
        public A left() { return a; }

        @Override
        public String toString() {
            return a.toString();
        }
    }

    public static class Right<A, B> extends Either<A, B> {
        private B b;
        public Right(B b) { this.b = b; }
        public B right() { return b; }

        @Override
        public String toString() {
            return b.toString();
        }

    }

    public <C> C apply(Fun<A, C> f, Fun<B, C> g) {
        if (this instanceof Left)
            return f.apply(left());
        return g.apply(right());
    }


}

