package lesani.image.core.op.core;

import lesani.image.core.image.GSImage;
import lesani.image.core.op.core.base.UOp;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen's Desktop
 * Date: May 4, 2007
 * Time: 12:36:15 PM
 */

public class Cutter extends UOp<GSImage> {
	private int lowThreshold;
	private int highThreshold;

    public Cutter(int lowThreshold, int highThreshold) {
		this.lowThreshold = lowThreshold;
		this.highThreshold = highThreshold;
	}

    public GSImage compute(GSImage image) {
		int width = image.getWidth();
		int height = image.getHeight();

		GSImage cutImage = new GSImage(width, height);

		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				if (image.get(i, j) > highThreshold)
						cutImage.set(i, j, highThreshold);
				else if (image.get(i, j) < lowThreshold)
                        cutImage.set(i, j, lowThreshold);
                else
						cutImage.set(i, j, image.get(i, j));
		return cutImage;
    }
}
