package lesani.collection.vector;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Mar 7, 2010
 * Time: 12:03:25 AM
 */

public class Vector<T> implements Cloneable {
    public int x;
    public int y;

    public Vector() {
    }

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector invert() {
        return new Vector(-x, -y);
    }

    public Vector times(int factor) {
        return times(this, factor);
    }
    public static Vector times(Vector v, int factor) {
        return new Vector(factor * v.x, factor * v.y);
    }

    public Vector devide(int mass) {
        return new Vector(x/mass, y/mass);
    }

    public Vector add(Vector v) {
        return add(this, v);
    }
    public static Vector add(Vector v1, Vector v2) {
        return new Vector(v1.x + v2.x, v1.y + v2.y);
    }
    public Vector minus(Vector v) {
        return minus(this, v);
    }
    public static Vector minus(Vector v1, Vector v2) {
        return new Vector(v1.x - v2.x, v1.y - v2.y);
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector))
               return false;
           Vector v = (Vector)obj;
           return v.x == x &&
                  v.y == y;        
    }

    @Override
    public Vector clone() {
        try {
            return (Vector)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
