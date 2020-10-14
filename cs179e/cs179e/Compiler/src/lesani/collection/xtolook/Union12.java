package lesani.collection.xtolook;

import lesani.collection.func.Fun;

public abstract class Union12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> {
    private Union12() { }

    public T1 elem1() { return null; }
    public T2 elem2() { return null; }
    public T3 elem3() { return null; }
    public T4 elem4() { return null; }
    public T5 elem5() { return null; }
    public T6 elem6() { return null; }
    public T7 elem7() { return null; }
    public T8 elem8() { return null; }
    public T9 elem9() { return null; }
    public T10 elem10() { return null; }
    public T11 elem11() { return null; }
    public T12 elem12() { return null; }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> Elem1<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> newUnion1(T1 a) {
        return new Elem1<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>(a);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> Elem2<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> newUnion2(T2 b) {
        return new Elem2<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>(b);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> Elem3<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> newUnion3(T3 c) {
        return new Elem3<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>(c);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> Elem4<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> newUnion4(T4 d) {
        return new Elem4<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>(d);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> Elem5<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> newUnion5(T5 e) {
        return new Elem5<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>(e);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> Elem6<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> newUnion6(T6 f) {
        return new Elem6<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>(f);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> Elem7<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> newUnion7(T7 o) {
        return new Elem7<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>(o);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> Elem8<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> newUnion8(T8 o) {
        return new Elem8<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>(o);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> Elem9<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> newUnion9(T9 o) {
        return new Elem9<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>(o);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> Elem10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> newUnion10(T10 o) {
        return new Elem10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>(o);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> Elem11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> newUnion11(T11 o) {
        return new Elem11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>(o);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> Elem12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> newUnion12(T12 o) {
        return new Elem12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>(o);
    }

    public final static class Elem1<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> extends Union12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> {
        private T1 a;
        public Elem1(T1 a) { this.a = a; }
        public T1 elem1() { return a; }

        @Override
        public String toString() {
            return a.toString();
        }
    }

    public static class Elem2<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> extends Union12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> {
        private T2 b;
        public Elem2(T2 b) { this.b = b; }
        public T2 elem2() { return b; }

        @Override
        public String toString() {
            return b.toString();
        }

    }
    public static class Elem3<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> extends Union12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> {
        private T3 c;
        public Elem3(T3 c) { this.c = c; }
        public T3 elem3() { return c; }

        @Override
        public String toString() {
            return c.toString();
        }

    }
    public static class Elem4<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> extends Union12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> {
        private T4 d;
        public Elem4(T4 d) { this.d = d; }
        public T4 elem4() { return d; }

        @Override
        public String toString() {
            return d.toString();
        }

    }
    public static class Elem5<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> extends Union12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> {
        private T5 e;
        public Elem5(T5 e) { this.e = e; }
        public T5 elem5() { return e; }

        @Override
        public String toString() {
            return e.toString();
        }

    }
    public static class Elem6<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> extends Union12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> {
        private T6 f;
        public Elem6(T6 f) { this.f = f; }
        public T6 elem6() { return f; }

        @Override
        public String toString() {
            return f.toString();
        }

    }
    public static class Elem7<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> extends Union12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> {
        private T7 f;
        public Elem7(T7 f) { this.f = f; }
        public T7 elem7() { return f; }

        @Override
        public String toString() {
            return f.toString();
        }

    }
    public static class Elem8<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> extends Union12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> {
        private T8 f;
        public Elem8(T8 f) { this.f = f; }
        public T8 elem8() { return f; }

        @Override
        public String toString() {
            return f.toString();
        }

    }
    public static class Elem9<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> extends Union12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> {
        private T9 f;
        public Elem9(T9 f) { this.f = f; }
        public T9 elem9() { return f; }

        @Override
        public String toString() {
            return f.toString();
        }

    }
    public static class Elem10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> extends Union12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> {
        private T10 f;
        public Elem10(T10 f) { this.f = f; }
        public T10 elem10() { return f; }

        @Override
        public String toString() {
            return f.toString();
        }

    }
    public static class Elem11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> extends Union12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> {
        private T11 f;
        public Elem11(T11 f) { this.f = f; }
        public T11 elem11() { return f; }

        @Override
        public String toString() {
            return f.toString();
        }

    }
    public static class Elem12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> extends Union12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> {
        private T12 f;
        public Elem12(T12 f) { this.f = f; }
        public T12 elem12() { return f; }

        @Override
        public String toString() {
            return f.toString();
        }

    }

    public <T> T apply(
            Fun<T1, T> f1,
            Fun<T2, T> f2,
            Fun<T3, T> f3,
            Fun<T4, T> f4,
            Fun<T5, T> f5,
            Fun<T6, T> f6,
            Fun<T7, T> f7,
            Fun<T8, T> f8,
            Fun<T9, T> f9,
            Fun<T10, T> f10,
            Fun<T11, T> f11,
            Fun<T12, T> f12) {

        if (this instanceof Elem1)
            return f1.apply(elem1());
        if (this instanceof Elem2)
            return f2.apply(elem2());
        if (this instanceof Elem3)
            return f3.apply(elem3());
        if (this instanceof Elem4)
            return f4.apply(elem4());
        if (this instanceof Elem5)
            return f5.apply(elem5());
        if (this instanceof Elem6)
            return f6.apply(elem6());
        if (this instanceof Elem7)
            return f7.apply(elem7());
        if (this instanceof Elem8)
            return f8.apply(elem8());
        if (this instanceof Elem9)
            return f9.apply(elem9());
        if (this instanceof Elem10)
            return f10.apply(elem10());
        if (this instanceof Elem11)
            return f11.apply(elem11());
        return f12.apply(elem12());
    }


}

