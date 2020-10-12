package lesani.image.segmentation.deformable.parallel;


import lesani.image.core.image.GSImage;
import lesani.image.core.op.core.masking.Mask;
import lesani.image.core.op.core.masking.Masker;
import lesani.image.segmentation.deformable.parallel.mesh.DistModel;
import lesani.image.segmentation.deformable.parallel.mesh.NodePointer;
import lesani.image.segmentation.deformable.parallel.base.MultiStepMeshProcessor;
import lesani.collection.vector.DVector;
import lesani.image.segmentation.deformable.parallel.mesh.Node;


/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Mar 15, 2010
 * Time: 5:54:29 PM
*/

public class LevelProcessor extends MultiStepMeshProcessor {

    private DistModel distModel;
    private Forcer forcer;
    private LengthUnifier lengthUnifier;

    private boolean[][] footPrint;


    public LevelProcessor(
            GSImage image,
            DistModel distModel,
            int minEdgeLength,
            int maxEdgeLength) {
        super(image, distModel.part(0).getFirst());
        this.distModel = distModel;
        DVector[][] gradient = computeGradients(image);

        forcer = new Forcer(image, gradient);
        lengthUnifier = new LengthUnifier(minEdgeLength, maxEdgeLength);

        footPrint = new boolean[image.getWidth()][image.getHeight()];
        for (int i = 0; i < footPrint.length; i++) {
            for (int j = 0; j < footPrint[i].length; j++) {
                footPrint[i][j] = false;
            }
        }
    }

    public void step() {
//        System.out.println("step");
        mark();
        distModel.process(forcer);
        distModel.process(lengthUnifier);

        //DistFootPrint
        //distFoorPrint.mark(distMesh);
        //distFoorPrint.isStable();
    }

    private void mark() {
        final NodePointer first = distModel.part(0).getFirst();
        Node node = first.node;
        do {
            footPrint[node.getX()][node.getY()] = true;
            node = node.getNext();
        } while (node != first.node);
    }

    //    boolean finished = false;
    public boolean isFinished() {
//        if (isStable())
//            ImageUtil.showImage(current());
        return isStable();
    }

    private boolean isStable() {
        int pos = 0;
        int neg = 0;
        final NodePointer first = distModel.part(0).getFirst();
        Node node = first.node;
        do {
            if (node.isNotMoving() || (footPrint[node.getX()][node.getY()]))
                pos++;
            else
                neg++;
            node = node.getNext();
        } while (node != first.node);

        final double stablePercent = pos * 100 / ((double) pos + neg);

        final int THRESHOLD = 100;//95;
        final boolean binaryIsStable = stablePercent >= THRESHOLD;
        return binaryIsStable;
    }

    private DVector[][] computeGradients(GSImage image) {
        // Should be the reverse. Vertical means vertical edge that means horizontal gradient.
        // x goes to the right
        // y goes down
        GSImage horizontal =
                Masker.process(image, Mask.Sharpening.FirstDerivative.verticalSobelMask);
        // Computes increase to right
//        System.out.println("Horizontal gradient");
//        ImageUtil.showImage(horizontal);
//        ImageUtil.showImage(Horizontal.process(image));
//        horizontal.print();
        GSImage vertical =
                Masker.process(image, Mask.Sharpening.FirstDerivative.horizontalSobelMask);
        // Computes increase to down
//        System.out.println("Vertical gradient");
//        vertical.print();

        final int width = horizontal.getWidth();
        final int height = vertical.getHeight();
        DVector[][] vectors = new DVector[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                DVector vector = new DVector(vertical.get(i, j), horizontal.get(i, j));
                vector.normalize();
                vectors[i][j] = vector;
//                if ((vector.x > 0) || (vector.y > 0))
//                    System.out.println("Ya");
//                System.out.print(vector + "\t");
            }
//            System.out.println("");
        }
        return vectors;
    }


    // ---------------------------------------
    // Other termination checks
/*
    private boolean onSnakeMove(Node node) {
        Vector position = node.getPosition();
        int x = position.x;
        int y = position.y;

        double vx;
        double vy;
        Vector prevPos = node.prevPos;
        if (prevPos != null) {
            vx = x - prevPos.x;
            vy = y - prevPos.y;
        } else {
            DVector v = node.v;
            vx = v.x;
            vy = v.y;
        }

        Vector nextNodePos = node.next.getPosition();
        int nextX = nextNodePos.x;
        int nextY = nextNodePos.y;

        int nextVectorX = nextX - x;
        int nextVectorY = nextY - y;


        Vector prevNodePos = node.previous.getPosition();
        int prevX = prevNodePos.x;
        int prevY = prevNodePos.y;

        int prevVectorX = x - prevX;
        int prevVectorY = y - prevY;


        return (along(vx, vy, nextVectorX, nextVectorY) ||
                along(vx, vy, prevVectorX, prevVectorY));

    }

    private boolean along(double x1, double y1, double x2, double y2) {
        final double inner = x1 * x2 + y1 * y2;
        final double l1 = Math.sqrt(x1*x1 + y1*y1);
        final double l2 = Math.sqrt(x2*x2 + y2*y2);
        final double cosAlpha = Math.abs(inner / (l1 * l2));
//        System.out.println("cosAlpha: " + cosAlpha);
        return (cosAlpha > 0.7*/
/*7*//*
 - 0.001) */
/*&& (cosAlpha < 1)*//*
;
    }


    private boolean ridgeMove(Node node) {
        Vector position = node.getPosition();
        int x = position.x;
        int y = position.y;

        DVector v = node.v;
        double vx = v.x;
        double vy = v.y;

        double pvx = vy;
        double pvy = (-vx);
        double ipvx = -pvx;
        double ipvy = -pvy;

        int side1Value = readNeighborValue(x, y, pvx, pvy);
        int side2Value = readNeighborValue(x, y, ipvx, ipvy);
        int currentValue = image.get(x, y);

        return (side1Value <= currentValue) || (side2Value <= currentValue);
    }


    private int readNeighborValue(int x, int y, double xDir, double yDir) {
        if (Math.abs(Math.abs(xDir) - Math.abs(xDir)) < 00.1)
            return image.get(x + LMath.sign(x), y + LMath.sign(y));
        else if (Math.abs(xDir) > Math.abs(yDir))
            return image.get(x + LMath.sign(x), y);
        else //if (Math.abs(yDir) > Math.abs(xDir))
            return image.get(x, y + LMath.sign(y));
    }
*/

    // End of termination checks
    // ---------------------------------------

}

