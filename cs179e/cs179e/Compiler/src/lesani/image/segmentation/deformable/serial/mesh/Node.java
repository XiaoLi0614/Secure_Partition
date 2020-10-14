package lesani.image.segmentation.deformable.serial.mesh;

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

    public Node next;
    public Node previous;
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

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
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

