package lesani.image.segmentation.deformable.parallel.mesh;

import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;


/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Mar 25, 2010
 * Time: 11:02:53 PM
 */
public abstract class NodesProcessor {

    public abstract void process(Node node, NodePointer first, NodePointer last);

    Option<Node> nextNode = None.instance();
    public void setNextNode(Node node) {
        nextNode = new Some<Node>(node);
    }

    public Option<Node> getNextNode() {
        Option<Node> ret = nextNode;
        nextNode = None.instance();
        return ret;
    }

}
