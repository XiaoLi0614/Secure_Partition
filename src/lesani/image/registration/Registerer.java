package lesani.image.registration;

import lesani.image.core.image.GSImage;
import lesani.image.util.ImageUtil;
import lesani.image.util.exception.NotAnImageFileException;
import lesani.collection.vector.Vector;

import static lesani.image.util.ImageUtil.readGSImage;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: May 23, 2010
 * Time: 4:14:57 PM
 */

public class Registerer {

    public static final int pixelPerCell = 10;

    public static Transformer register(GSImage referenceImage, GSImage floatingImage) {

        int width = referenceImage.getWidth();
        int height = referenceImage.getHeight();
        System.out.println("width = " + width);
        System.out.println("height = " + height);

        int widthCellCount = width / pixelPerCell;
        int heightCellCount = height / pixelPerCell;

        ControlPointGrid controlPointGrid =
                new ControlPointGrid(widthCellCount, heightCellCount, pixelPerCell);

        Optimizer optimizer = new Optimizer(referenceImage, floatingImage, controlPointGrid);

        return optimizer.optimize();
    }

    public static void main(String[] args) throws NotAnImageFileException {
        final String fileName1 =
                "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Lena.jpg";
//                "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Landscape1.jpg";
//                "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Landscape2.jpg";

        final String fileName2 =
                "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/ShiftedLena.jpg";
        GSImage referenceImage = readGSImage(fileName1);
        GSImage floatingImage = readGSImage(fileName2);
        Transformer transformer = register(referenceImage, floatingImage);
        // Do something with transformer
//        Vector input = new Vector(131,133);
//        Vector input = new Vector(79,32);
//        Vector output = transformer.apply(input);
//        System.out.println(input + " is mapped to " + output + ".");

        int referenceWidth = referenceImage.getWidth();
        int referenceHeight = referenceImage.getHeight();
        int widthCellCount = referenceWidth / pixelPerCell;
        int heightCellCount = referenceHeight / pixelPerCell;

        for (int i = 0; i < widthCellCount; i++)
            for (int j = 0; j < heightCellCount; j++) {
                int color = 255;
                if (i==18 && j==10)
                    color = 0;
                referenceImage.drawCircle(3, i*pixelPerCell, j*pixelPerCell, color);
            }

        ControlPointGrid c = (ControlPointGrid) transformer;
        Vector[][] points = c.points;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                Vector input = points[i][j];
                Vector output = transformer.apply(input);
                int color = 255;
                if (i==18 && j==10)
                    color = 0;
                floatingImage.drawCircle(3, output.x, output.y, color);
            }
        }

        ImageUtil.showImage(referenceImage);
        ImageUtil.showImage(floatingImage);
    }
}

