package lesani.image.segmentation.deformable.parallel;


import lesani.image.segmentation.deformable.parallel.mesh.Node;
import lesani.image.segmentation.deformable.parallel.mesh.NodePointer;
import lesani.image.segmentation.deformable.parallel.mesh.NodesProcessor;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Mar 15, 2010
 * Time: 5:57:38 PM
 */

public class LengthUnifier extends NodesProcessor {

    private int minLength = 5;
    private int maxLength = 10;

    public LengthUnifier() {
    }

    public LengthUnifier(int minLength, int maxLength) {
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

    @Override
    public void process(Node current, NodePointer first, NodePointer last) {

        int x1 = current.getX();
        int y1 = current.getY();
        Node next = current.getNext();
        int x2 = next.getX();
        int y2 = next.getY();

        Node previous = current.getPrevious();

        Node newNode = new Node((x1+x2)/2, (y1+y2)/2);

        double length = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));


        if (length < minLength) {
//            Merge
            if (last.node == current) {
                return;
//                last.node = newNode;
                //
            }

            previous.setNext(newNode);
            newNode.setPrevious(previous);
            Node nextNext = next.getNext();
            newNode.setNext(nextNext);
            nextNext.setPrevious(newNode);

            if (first.node == current) {// This condition does not happen: (first.node == next)
                first.node = newNode;
            }

            // ...

            if (last.node == next) {
                last.node = newNode;
            }

            setNextNode(nextNext); // see LengthUnifier

        } else if (length > maxLength) {
//            Split
            current.setNext(newNode);
            newNode.setPrevious(current);
            newNode.setNext(next);
            next.setPrevious(newNode);

            if (current == last.node) {
                last.node = newNode;
            }

            setNextNode(next);
        }
    }
}

