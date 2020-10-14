package lesani.image.segmentation.deformable.serial.base;

import lesani.image.core.image.GSImage;
import lesani.image.core.op.core.Imposer;
import lesani.image.gui.environment.MultiStepProcessor;
import lesani.image.segmentation.deformable.serial.Segmenter;
import lesani.image.segmentation.deformable.serial.mesh.Node;
import lesani.image.segmentation.deformable.serial.mesh.NodePointer;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Mar 15, 2010
 * Time: 6:48:37 PM
 */

public abstract class MultiStepMeshProcessor extends MeshProcessor implements MultiStepProcessor {

    private static final int POINT_RADIUS = Segmenter.POINT_RADIUS;

    protected MultiStepMeshProcessor() {
    }

    protected MultiStepMeshProcessor(GSImage image, NodePointer first) {
        super(image, first);
    }

    @Override
    public void setImage(GSImage image) {
        super.setImage(image);
    }

    public GSImage current() {
        final int width = image.getWidth();
        final int height = image.getHeight();
        GSImage modelImage = new GSImage(width, height);
        Node node = first.node;
        do {
//            System.out.println("Drawing");
            modelImage.drawCircle(
                    POINT_RADIUS,
                    node.getX(), node.getY(),
                    255);
            modelImage.drawLine(
                    node.getX(), node.next.getX(),
                    node.getY(), node.next.getY(),
                    255);

            node = node.next;
        } while (node != first.node);
/*
        if (!forced)
            return AdderScaler.instance().compute(image, modelImage);
        else
            return modelImage;
*/
//        return AdderScaler.instance().compute(image, modelImage);
        return Imposer.process(image, modelImage);
    }

    @Override
    public void process() {
        step();
    }
}

