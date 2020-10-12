package lesani.image.registration;

import lesani.collection.vector.DVector;
import lesani.collection.vector.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: May 23, 2010
 * Time: 3:10:51 PM
 */

public class ControlPointGrid extends Transformer {
    Vector[][] points;
    int pixelPerCell;

    public ControlPointGrid(int width, int height, int pixelPerCell) {
        points = new Vector[width][height];
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                points[i][j] = new Vector(i*pixelPerCell, j*pixelPerCell);
        this.pixelPerCell = pixelPerCell;
    }

    public int getWidth() {
        return points.length;
    }
    public int getHeight() {
        return points[0].length; 
    }

    public void setPoint(int x, int y, Vector vector) {
        points[x][y] = vector;
    }
    public void setPoint(Vector coord, Vector vector) {
        points[coord.x][coord.y] = vector;
    }

    public Vector getPoint(int x, int y) {
        return points[x][y];
    }
    public Vector getPoint(Vector coord) {
        return points[coord.x][coord.y];
    }

    public Vector apply(Vector input) {
        //input is the input pixel location.
//        System.out.println(input);
        final int pixelX = input.x;
        final int pixelY = input.y;
        int cxcoord = pixelX / pixelPerCell;
        // cell x coordinate
        int cycoord = pixelY / pixelPerCell;
        // cell y coordinate

        if ((cxcoord != points.length) && (cycoord != points[0].length) &&
            (cxcoord != points.length - 1) && (cycoord != points[0].length - 1)) {
            int incellPixelX = pixelX - cxcoord * pixelPerCell;
            int incellPixelY = pixelY - cycoord * pixelPerCell;

            double xFraction = ((double) incellPixelX) / pixelPerCell;
            double yFraction = ((double) incellPixelY) / pixelPerCell;

//            System.out.println(points.length);
//            System.out.println(points[0].length);
//            System.out.println(cxcoord);
//            System.out.println(cycoord);
//            System.out.println("");

            Vector orig = points[cxcoord][cycoord];
            Vector xNeighbor = points[cxcoord + 1][cycoord];
            Vector yNeighbor = points[cxcoord][cycoord + 1];

            DVector xVector = new DVector(Vector.minus(xNeighbor, orig));
            DVector yVector = new DVector(Vector.minus(yNeighbor, orig));

            DVector result = DVector.add(orig,
                    DVector.add(xVector.times(xFraction), yVector.times(yFraction)));
            final Vector vector = result.cast();
//            if (pixelX==5 && pixelY==0) {
//                System.out.println(incellPixelY);
//                System.out.println(yFraction);
//                System.out.println(yNeighbor);
//                System.out.println(yVector);
//                System.out.println(result);
//                System.out.println(vector);
//            }

//            System.out.println(vector);
//            System.out.println("");
            return vector;
        }
        return input;
    }
}



