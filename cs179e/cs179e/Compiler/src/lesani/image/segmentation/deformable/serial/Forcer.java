package lesani.image.segmentation.deformable.serial;

import lesani.image.core.image.GSImage;
import lesani.image.segmentation.deformable.serial.mesh.Node;
import lesani.image.segmentation.deformable.serial.mesh.NodePointer;
import lesani.math.LMath;
import lesani.collection.vector.DVector;
import lesani.collection.vector.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Mar 24, 2010
 * Time: 1:33:58 AM
 */

public class Forcer {

    private GSImage image;
    private DVector[][] gradient;
    private NodePointer first;

    private static final int EXTERNAL_FORCE_FACTOR = 1;
    private static final int INTERNAL_FORCE_FACTOR = 0;//3;

    private static final int MAX_INTENSITY_AS_BLACK = 10;


    public Forcer(GSImage image, DVector[][] gradient, NodePointer first) {
        this.image = image;
        this.gradient = gradient;
        this.first = first;
    }

    public void process() {
        Node node = first.node;
        do {

            DVector externalForce = computeExternalForce(node).times(EXTERNAL_FORCE_FACTOR);
            DVector internalForce = computeInternalForce(node).times(INTERNAL_FORCE_FACTOR);

            DVector totalForce = DVector.add(externalForce, internalForce);

            totalForce.normalize();

            applyForce(node, totalForce);

            node = node.next;
        } while (node != first.node);
    }

    private boolean onBlack(int x, int y) {
        return image.get(x, y) < MAX_INTENSITY_AS_BLACK;
    }
    private DVector computeExternalForce(Node node) {
        int x = node.getX();
        int y = node.getY();

        if (onBlack(x, y))
            return externalDeflateForce(node);
        else {
            node.metEdge = true;
            return externalEdgeForce(node);
        }
    }
    
    private DVector externalEdgeForce(Node node) {

        int x = node.getX();
        int y = node.getY();

        DVector force = gradient[x][y];

        return force;
    }

    private DVector externalDeflateForce(Node node) {
        DVector normal = normal(node);
        DVector invertedNormal = normal.invert();
        DVector externalForce = invertedNormal;

        return externalForce;
    }

    private DVector computeInternalForce(Node node) {
        Vector position = node.getPosition();
        int x = position.x;
        int y = position.y;

        Vector nextNodePos = node.next.getPosition();
        int nextX = nextNodePos.x;
        int nextY = nextNodePos.y;

        DVector nextVector = new DVector(nextX - x, nextY - y);
        nextVector.normalize();


        Vector prevNodePos = node.previous.getPosition();
        int prevX = prevNodePos.x;
        int prevY = prevNodePos.y;

        DVector prevVector = new DVector(prevX - x, prevY - y);
        prevVector.normalize();

        DVector internalForce = DVector.add(nextVector, prevVector);
        internalForce.normalize();
        
        return internalForce;
    }

    public void applyForce(Node node, DVector force) {
        Vector position = node.getPosition();
        Vector newPosition = position.clone();

        if (Math.abs(force.x) - Math.abs(force.y) < 0.05) {
            newPosition.x = newPosition.x + LMath.sign(force.x);
            newPosition.y = newPosition.y + LMath.sign(force.y);
        } else if (Math.abs(force.x) > Math.abs(force.y))
            newPosition.x = newPosition.x + LMath.sign(force.x);
        else //if (Math.abs(force.x) < Math.abs(force.y))
            newPosition.y = newPosition.y + LMath.sign(force.y);

        if (!onBlack(position.x, position.y) && onBlack(newPosition.x, newPosition.y))
            applyForce(node, force.invert());
        else
            node.setPosition(newPosition);

/*
        int deltaT = 1;
        DVector a = force.devide(Node.MASS);
        final DVector deltaV = a.times(deltaT);
//        DVector newV = DVector.add(v, deltaV);
        DVector newV = deltaV;
        node.v = newV;
        final DVector deltaPos = DVector.times(newV, deltaT);
        DVector newPosition = DVector.add(node.getPosition(), deltaPos);

        final Vector newIntPosition = newPosition.cast();
        if ((node.getPosition().x == newIntPosition.x) &&
                (node.getPosition().y == newIntPosition.y)) {
            if (Math.abs(deltaPos.x) > Math.abs(deltaPos.y))
                newIntPosition.x = newIntPosition.x + LMath.sign(deltaPos.x);
            else if (Math.abs(deltaPos.x) < Math.abs(deltaPos.y))
                newIntPosition.y = newIntPosition.y + LMath.sign(deltaPos.y);
            else if (Math.abs(deltaPos.x) - Math.abs(deltaPos.y) < 0.001)
                if (Math.abs(deltaPos.x) != 0) {
                    newIntPosition.x = newIntPosition.x + LMath.sign(deltaPos.x);
                    newIntPosition.y = newIntPosition.y + LMath.sign(deltaPos.y);
                }
        }
        node.setPosition(newIntPosition);
*/

    }

    public static DVector normal(Node node1, Node node2) {
        double x = node2.getX() - node1.getX();
        double y = node2.getY() - node1.getY();
//        System.out.println("x: " + x);
//        System.out.println("y: " + y);
        double d = Math.sqrt(x*x + y*y);

        double newX = x / d;
        double newY = y / d;

        if (Double.isInfinite(newX) || Double.isInfinite(newY) ||
            Double.isNaN(newX) || Double.isNaN(newY)) {
//            System.out.println("Here");
            return normal(node1, node2.next);
        }


//        System.out.println("newX: " + newX);
//        System.out.println("newY: " + newY);
        return new DVector(newX, newY);
    }

    public DVector normal(Node node) {
//        System.out.println("P: " + previous);
//        System.out.println("N: " + next);
        DVector n1 = normal(node.previous, node);
//        System.out.println("n1: " + n1);
        DVector n2 = normal(node, node.next);
//        System.out.println("n2: " + n2);
        double x = (n1.x + n2.x)/2;
        double y = (n1.y + n2.y)/2;

        double newX = y;
        double newY = (-x);

        return new DVector(newX, newY);
    }


}