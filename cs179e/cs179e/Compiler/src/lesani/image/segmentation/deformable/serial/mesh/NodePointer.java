package lesani.image.segmentation.deformable.serial.mesh;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Mar 23, 2010
 * Time: 12:54:14 PM
 */

public class NodePointer {
    public Node node;

    public NodePointer(Node node) {
        this.node = node;
    }

    public NodePointer() {
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
