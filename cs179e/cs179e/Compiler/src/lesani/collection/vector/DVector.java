package lesani.collection.vector;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Mar 7, 2010
 * Time: 1:31:21 AM
 */

public class DVector {
    public double x;
    public double y;

    public DVector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public DVector(Vector vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    public DVector invert() {
        return new DVector(-x, -y);
    }

    public DVector times(double factor) {
        return times(this, factor);
    }
    public static DVector times(DVector v, double factor) {
        return new DVector(factor * v.x, factor * v.y);
    }

    public DVector devide(int factor) {
        return new DVector(x/factor, y/factor);
    }

    public DVector add(DVector v) {
        return add(this, v);
    }
    public static DVector add(DVector v1, DVector v2) {
        return new DVector(v1.x + v2.x, v1.y + v2.y);
    }
    public static DVector add(Vector v1, DVector v2) {
        return new DVector(v1.x + v2.x, v1.y + v2.y);
    }
    public static DVector add(Vector v1, Vector v2) {
        return new DVector(v1.x + v2.x, v1.y + v2.y);
    }

    public DVector minus(DVector v) {
        return minus(this, v);
    }
    public static DVector minus(DVector v1, DVector v2) {
        return new DVector(v1.x - v2.x, v1.y - v2.y);
    }
    public static DVector minus(Vector v1, DVector v2) {
        return new DVector(v1.x - v2.x, v1.y - v2.y);
    }
    public static DVector minus(Vector v1, Vector v2) {
        return new DVector(v1.x - v2.x, v1.y - v2.y);
    }

    public Vector cast() {
        return new Vector((int)x, (int)y);
    }

    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    public double length() {
        return Math.sqrt(x*x + y*y);
    }
    public void normalize() {

        double d = Math.sqrt(x*x + y*y);

        //if (Math.abs(d) > 0.001) {

        double newX = x / d;
        double newY = y / d;

        if (Double.isInfinite(newX) || Double.isNaN(newX)) {
//            System.out.println("Double math error.");
            return;
        }
        if (Double.isInfinite(newY) || Double.isNaN(newY)) {
//            System.out.println("Double math error.");
            return;
        }

        x = newX;
        y = newY;

    }

}
