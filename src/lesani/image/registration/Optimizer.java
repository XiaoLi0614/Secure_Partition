package lesani.image.registration;

import lesani.image.core.image.GSImage;
import lesani.collection.vector.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: May 23, 2010
 * Time: 3:25:12 PM
 */
public class Optimizer {

    private GSImage referenceImage;
    private GSImage floatingImage;

    private ControlPointGrid controlPointGrid;

    public Optimizer(
            GSImage referenceImage,
            GSImage floatingImage,
            ControlPointGrid controlPointGrid) {

        this.referenceImage = referenceImage;
        this.floatingImage = floatingImage;
        this.controlPointGrid = controlPointGrid;
    }

    public void step() {
        int width = referenceImage.getWidth();
        int height = referenceImage.getHeight();

        final int delta = 2;
        double currentSimilarity = similarity();
        Vector[][] points = controlPointGrid.points;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                Vector vector = points[i][j];

                double similarity;

                if (vector.x + delta < width) {
                    vector.x += delta;

                    similarity = similarity();
                    if (similarity < currentSimilarity)
                        vector.x -= delta;
                    else
                        currentSimilarity = similarity;
                }

                if (vector.x - delta >= 0) {
                    vector.x -= delta;
                    similarity = similarity();
                    if (similarity < currentSimilarity)
                        vector.x += delta;
                    else
                        currentSimilarity = similarity;
                }

                if (vector.y + delta < height) {
                    vector.y += delta;
                    similarity = similarity();
                    if (similarity < currentSimilarity)
                        vector.y -= delta;
                    else
                        currentSimilarity = similarity;
                }

                if (vector.y - delta >= 0) {
                    vector.y -= delta;
                    similarity = similarity();
                    if (similarity < currentSimilarity)
                        vector.y += delta;
                    else
                        currentSimilarity = similarity;
                }
            }
        }
    }

    public Transformer optimize() {
        int iter = 0;
        while (iter != 10) {
            step();
            iter++;
            System.out.println("Iteration = " + iter);
        }
        return controlPointGrid;
    }

    private double similarity() {
        return SimilarityMeasurer.measureSimilarity(
                referenceImage,
                floatingImage,
                controlPointGrid);
    }
}
