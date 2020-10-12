package lesani.image.registration;

import lesani.image.core.image.GSImage;
import lesani.collection.func.Fun;
import lesani.collection.vector.Pair;
import lesani.collection.vector.Vector;
import lesani.parallel.ForkJoin;

import static java.lang.Math.log;
import static lesani.parallel.ForkJoin.*;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: May 4, 2010
 * Time: 5:27:09 PM
 */

public class ParallelSimilarityMeasurer {

    private final GSImage referenceImage;
    private final GSImage floatingImage;
    private final Fun<Vector, Vector> transformation;

    private double[][] jointPDist;
    private double[] refMarginalPDist;
    private double[] floatMarginalPDist;

    public static final int THREAD_COUNT = 2;
    public static final int SPLIT_COUNT = THREAD_COUNT;

    private ForkJoin<Integer, Pair<Integer, Integer>, Histogram, Histogram> histogramParallelBuilder;

    public ParallelSimilarityMeasurer(final GSImage referenceImage, final GSImage floatingImage, final Fun<Vector, Vector> transformation) {
        this.referenceImage = referenceImage;
        this.floatingImage = floatingImage;
        this.transformation = transformation;

        this.jointPDist = new double[256][256];
        this.refMarginalPDist = new double[256];
        this.floatMarginalPDist = new double[256];

        histogramParallelBuilder =
            new ForkJoin<Integer, Pair<Integer, Integer>, Histogram, Histogram>(
                new Forker<Integer, Pair<Integer, Integer>>() {
                    public Pair<Integer, Integer>[] fork(Integer height) {
                        Pair<Integer, Integer>[] pairs = new Pair[SPLIT_COUNT];

                        int share = height / SPLIT_COUNT;

                        int lastEndRow = -1;
                        for (int i = 0; i < pairs.length; i++) {
                            int startRow = lastEndRow + 1;
                            int endRow;
                            if (i != pairs.length - 1)
                                endRow = lastEndRow + share;
                            else
                                endRow = height - 1;
                            pairs[i] = new Pair<Integer, Integer>(startRow, endRow);
                            lastEndRow = endRow;
                        }
                        return pairs;
                    }
                },
                new Fun<Pair<Integer, Integer>, Histogram>() {
                    @Override
                    public Histogram apply(Pair<Integer, Integer> pair) {
                        Histogram histogram = new Histogram();

                        int width = referenceImage.getWidth();
                        int row1 = pair.getElement1();
                        int row2 = pair.getElement2();

                        for (int i = 0; i < width; i++) {
                            for (int j = row1; j < row2; j++) {

                                int referenceValue = referenceImage.get(i, j);

                                Vector mappedVector = transformation.apply(new Vector(i, j));
                                int newI = mappedVector.x;
                                int newJ = mappedVector.y;

                                int floatingValue = floatingImage.get(newI, newJ);

                                histogram.bins[referenceValue][floatingValue] += 1;
                            }
                        }
                        return histogram;
                    }
                },
                new Joiner<Histogram, Histogram>() {
                    public Histogram join(Histogram[] histograms) {
                        Histogram completeHistogram = new Histogram();
                        int total = 0;
                        for (int i = 0; i < histograms.length; i++) {
                            Histogram histogram = histograms[i];
                            for (int j = 0; j < histogram.bins.length; j++) {
                                for (int k = 0; k < histogram.bins[j].length; k++) {
                                    int value = histogram.bins[j][k];
                                    completeHistogram.bins[j][k] += value;
                                    total += value;
                                }
                            }
                        }
                        completeHistogram.total = total;
                        return completeHistogram;
                    }
                },
//              THREAD_COUNT,
                new Histogram[SPLIT_COUNT]
            );
    }

    private void computeDists(int[][] bins) {

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
                    final double denum = refMarginalPDist[i] * floatMarginalPDist[j];
                    final double term = jointPDist[i][j] * log(jointPDist[i][j] / denum);
                    mutualInfo += term;
                }
            }
        }

        return mutualInfo;
    }

    public double compute() {
        int height = referenceImage.getHeight();
        Histogram histogram = histogramParallelBuilder.compute(height);
        computeDists(histogram.bins);
        return computeMI();
    }

    public static double parallelMeasureSimilarity(GSImage referenceImage, GSImage floatingImage, Fun<Vector, Vector> transformation) {
        return new ParallelSimilarityMeasurer(referenceImage, floatingImage, transformation).compute();
    }
}


