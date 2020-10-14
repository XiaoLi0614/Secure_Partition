package lesani.image.segmentation.deformable.parallel.mesh;

import java.util.concurrent.Future;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Mar 26, 2010
 * Time: 12:00:11 AM
 */

public class DistModel {

    private ModelPart[] modelParts;

    public DistModel(ModelPart[] modelParts) {
        this.modelParts = modelParts;
    }

    public void process(final NodesProcessor processor) {
        Future[] futures = new Future[modelParts.length];
        for (int i = 0; i < modelParts.length; i++) {
            futures[i] = modelParts[i].process(processor);
//        }
//        for (int i = 0; i < futures.length; i++) {
            Future future = futures[i];
            try {
                future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ModelPart part(int i) {
        return modelParts[0];
    }
}
