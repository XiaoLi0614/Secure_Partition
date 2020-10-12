package lesani.image.segmentation.deformable.serial;

import lesani.image.segmentation.deformable.serial.mesh.Node;
import lesani.image.segmentation.deformable.serial.mesh.NodePointer;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Mar 15, 2010
 * Time: 5:57:38 PM
 */

public class LengthUnifier /*extends MeshProcessor*/ {

    NodePointer first;

    private int minLength = 5;
    private int maxLength = 10;

    public LengthUnifier(/*GSImage image, */NodePointer first) {
//        super(image, first);
        this.first = first;
    }

    public LengthUnifier(/*GSImage image, */NodePointer first, int minLength, int maxLength) {
//        super(image, first);
        this.first = first;
        
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public void process() {
/*
        final int MIN_LENGTH = 3;
        final int MAX_LENGTH = 7;
*/
/*
        final int MIN_LENGTH = 40;
        final int MAX_LENGTH = 60;
*/
//        final int MIN_LENGTH = 20;
//        final int MAX_LENGTH = 40;

        Node node = first.node;
        do {
//            System.out.println("inside lengther");
//            System.out.println("Unifying length");
            int x1 = node.getX();
            int y1 = node.getY();
            Node next = node.next;
            int x2 = next.getX();
            int y2 = next.getY();

            Node previous = node.previous;
            Node newNode = new Node((x1+x2)/2, (y1+y2)/2);

            double length = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
//            if (Double.isInfinite(length) || Double.isNaN(length))
//                System.out.println("length: " + length);

            if (length < minLength) {
                // To not join points that are on boundaries.
//                if ((image.get(x1, y1) > 2) && (image.get(x2, y2) > 2)) {
//                    System.out.println("Merge");
//                     Merge
                    previous.next = newNode;
                    newNode.previous = previous;
                    Node nextNext = next.next;
                    newNode.next = nextNext;
                    nextNext.previous = newNode;

                    if (next == first.node) {
                        first.node = newNode;
                        break;
                    }

                    if (node == first.node)
                        first.node = newNode;
                    node = newNode.next;
//                } else
//                    node = node.next;
            } else if (length > maxLength) {
                // do not fork inside!
//                if ((image.get(x1, y1) > 2) && (image.get(x2, y2) > 2)) {
                // Split
//                System.out.println("Split");
                node.next = newNode;
                newNode.previous = node;
                newNode.next = next;
                next.previous = newNode;

                node = newNode.next;
//                } else
//                    node = node.next;

            } else {
                node = node.next;
            }
        } while (node != first.node);
    }
}
