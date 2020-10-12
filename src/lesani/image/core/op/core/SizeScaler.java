package lesani.image.core.op.core;

import lesani.image.core.image.GSImage;
import lesani.image.core.image.Image;
import lesani.image.core.op.core.base.UOp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 * Date: Apr 15, 2005
 * Time: 12:16:44 PM
 */

public class SizeScaler<T extends Image> extends UOp<T>
{
    private int factor;

    public SizeScaler(int factor) {
        this.factor = factor;
    }

	public T compute(T image) {
        T scaledImage = Image.createImageOfType(image, image.getWidth()/factor, image.getHeight()/factor);

		for (int i = 0; i < scaledImage.getWidth(); i++)
			for (int j = 0; j < scaledImage.getHeight(); j++) {
                int sum = 0;
                for (int k = 0; k < factor; k++)
                    for (int l = 0; l < factor; l++)
                        sum += image.get(i*factor + k, j*factor + l);
                scaledImage.set(i, j, sum/(factor*factor));
            }
    	return scaledImage;
	}

    public static <T extends Image> T process(T image, int factor) {
        return (T) (new SizeScaler(factor).compute(image));
    }

    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new File("/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/Medical/isolate_B.gif"));
        GSImage gsImage = new GSImage(image);
//        ImageUtil.showImage(gsImage);
        SizeScaler<GSImage> sizeScaler = new SizeScaler<GSImage>(2);
        GSImage scaledImage = sizeScaler.compute(gsImage);
//        ImageUtil.showImage(scaledImage);
    }

}

