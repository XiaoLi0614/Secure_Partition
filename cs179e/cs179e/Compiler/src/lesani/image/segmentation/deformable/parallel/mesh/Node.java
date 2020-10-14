package lesani.image.segmentation.deformable.parallel.mesh;

import lesani.collection.vector.DVector;
import lesani.collection.vector.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Mar 6, 2010
 * Time: 8:52:14 PM
 */

public class Node {

    private Vector position;
    public Vector prevPos;
    public Vector prePrevPos;

    private Node next;
    private Node previous;
    public DVector v = new DVector(0, 0);


    public static final int MASS = 1;
    public boolean metEdge = false;

    public Node(int x, int y) {
        position = new Vector(x, y);
    }

    public int getX() {
        return position.x;
    }

    public void setX(int x) {
        position.x = x;
    }

    public int getY() {
        return position.y;
    }

    public void setY(int y) {
        position.y = y;
    }

    synchronized public Node getNext() {
        return next;
    }

    synchronized public void setNext(Node next) {
        this.next = next;
    }

    synchronized public Node getPrevious() {
        return previous;
    }

    synchronized public void setPrevious(Node previous) {
        this.previous = previous;
    }

    synchronized public Vector getPosition() {
        return position;
    }

    synchronized public void setPosition(Vector position) {
        prePrevPos = prevPos;
        prevPos = this.position;
        this.position = position;
    }


    @Override
    public String toString() {
        return position.toString();
    }

    public boolean isNotMoving() {
        return (position.equals(prePrevPos));
//                || (prevPos == null);
    }


}