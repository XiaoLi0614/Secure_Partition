package lesani.image.segmentation.deformable.parallel.base;

import lesani.image.core.image.GSImage;
import lesani.image.segmentation.deformable.parallel.mesh.NodePointer;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Mar 15, 2010
 * Time: 6:48:37 PM
 */
public abstract class MeshProcessor {

    protected GSImage image;
    protected NodePointer first;


    protected MeshProcessor() {
    }

    protected MeshProcessor(GSImage image, NodePointer first) {
        this.image = image;
        this.first = first;
    }

    public void setImage(GSImage image) {
        this.image = image;
    }

    public abstract void process();
}