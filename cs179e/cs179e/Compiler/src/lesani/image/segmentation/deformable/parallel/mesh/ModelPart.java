package lesani.image.segmentation.deformable.parallel.mesh;

import lesani.image.segmentation.deformable.parallel.mesh.Node;
import lesani.image.segmentation.deformable.parallel.mesh.NodePointer;
import lesani.image.segmentation.deformable.parallel.mesh.NodesProcessor;
import lesani.image.segmentation.deformable.parallel.mesh.Place;
import lesani.collection.option.None;
import lesani.collection.option.Option;
import lesani.collection.option.Some;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Mar 25, 2010
 * Time: 10:16:43 PM
 */

public class ModelPart {

    private Place here;

    private NodePointer first = new NodePointer();
    private NodePointer last = new NodePointer();


    public ModelPart(Place here) {
        this.here = here;
    }

    public Future process(final NodesProcessor processor) {
         return here.at(new Callable<None>() {

            public None call() throws Exception {
                Node node = first.node;
                do {
                    processor.process(node, first, last);
                    Option<Node> nextNode = processor.getNextNode();
                    if (nextNode instanceof None)
                        node = node.getNext();
                    else {
                        Some<Node> someNextNode = (Some<Node>) nextNode;
                        node = someNextNode.get();
                    }
                } while (node != last.node.getNext());
                return None.instance();
            }
        });
    }

    public void setFirst(Node node) {
        first.node = node;
    }

    public void setLast(Node node) {
        last.node = node;
    }

    public NodePointer getFirst() {
        return first;
    }

    public NodePointer getLast() {
        return last;
    }
}
