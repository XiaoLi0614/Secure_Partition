package lesani.image.registration;

import lesani.image.core.image.GSImage;
import lesani.collection.func.Fun;
import lesani.collection.vector.Vector;
import static java.lang.Math.*;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: May 4, 2010
 * Time: 4:41:13 PM
 */

public class SimilarityMeasurer {

    private GSImage referenceImage;
    private GSImage floatingImage;
    private Fun<Vector, Vector> transformer;

    private int[][] bins;

    private double[][] jointPDist;
    private double[] refMarginalPDist;
    private double[] floatMarginalPDist;

    public SimilarityMeasurer(GSImage referenceImage, GSImage floatingImage, Transformer transformer) {
        this.referenceImage = referenceImage;
        this.floatingImage = floatingImage;
        this.transformer = transformer;
        this.bins = new int[256][256];
        this.jointPDist = new double[256][256];
        this.refMarginalPDist = new double[256];
        this.floatMarginalPDist = new double[256];
        // when created, arrays are automatically initialized with the default value of their type.
    }

    private void fillBins() {
        int width = referenceImage.getWidth();
        int height = referenceImage.getHeight();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                int referenceValue = referenceImage.get(i, j);

                Vector mappedVector = transformer.apply(new Vector(i, j));
                int newI = mappedVector.x;
                int newJ = mappedVector.y;

                if (newI < 0 || newI > floatingImage.getWidth() ||
                    newJ < 0 || newJ > floatingImage.getHeight()) {
                    System.out.println(i + ", " + j);
                    System.out.println(newI + ", " + newJ);
                }
                int floatingValue = floatingImage.get(newI, newJ);

                bins[referenceValue][floatingValue] += 1;

            }
        }
    }

    private void computeDists() {

        int total = 0;
        for (int i = 0; i < bins.length; i++) {
            int rowTotal = 0;
            for (int j = 0; j < bins[i].length; j++)
                rowTotal += bins[i][j];
            refMarginalPDist[i] = rowTotal;
            total += rowTotal;
        }

        for (int j = 0; j < bins[0].length; j++) {
            int columnTotal = 0;
            for (int i = 0; i < bins.length; i++)
                columnTotal += bins[i][j];
            floatMarginalPDist[j] = columnTotal;
        }

        if (total != 0) {
            for (int i = 0; i < refMarginalPDist.length; i++)
                refMarginalPDist[i] /= total;

            for (int i = 0; i < floatMarginalPDist.length; i++)
                floatMarginalPDist[i] /= total;

            for (int i = 0; i < jointPDist.length; i++) {
                for (int j = 0; j < jointPDist[i].length; j++) {
                    jointPDist[i][j] = (double)bins[i][j]/total;
//                    System.out.print(jointPDist[i][j] + "\t");
                }
//                System.out.println("");
            }

        }
    }

    private double computeMI() {
        double mutualInfo = 0;

        for (int i = 0; i < jointPDist.length; i++) {
            for (int j = 0; j < jointPDist[i].length; j++) {
                if (jointPDist[i][j] != 0) {
//                    System.out.println("In");
//                    System.out.println("i=" + i + " j=" + j);
//                    System.out.println(jointPDist[i][j]);
//                    System.out.println(refMarginalPDist[i]);
//                    System.out.println(floatMarginalPDist[i]);
//                    System.out.println(denum);
//                    final double v = jointPDist[i][j] / denum;
//                    System.out.println("v= " + v);
                    final double denum = refMarginalPDist[i] * floatMarginalPDist[j];
                    final double term = jointPDist[i][j] * log(jointPDist[i][j] / denum);
                    mutualInfo += term;
//                    System.out.println(term);
                }
//                System.out.println(mutualInfo);
            }
        }

        return mutualInfo;
    }


    public double compute() {
        fillBins();
        computeDists();
        return computeMI();
    }

    public static double measureSimilarity(GSImage referenceImage, GSImage floatingImage, Transformer transformer) {
        return new SimilarityMeasurer(referenceImage, floatingImage, transformer).compute();
    }
}

