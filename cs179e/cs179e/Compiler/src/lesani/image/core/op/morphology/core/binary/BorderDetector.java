package lesani.image.core.op.morphology.core.binary;

import lesani.image.core.image.BImage;
import lesani.image.util.ImageUtil;
import lesani.image.core.op.morphology.exception.NotSameSizeImageException;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 * Date: Apr 15, 2005
 * Time: 4:05:58 PM
 */

public class BorderDetector
{
	private BImage image;
	private int maskLength;

	public BorderDetector(BImage image, int maskLength)
	{
		this.image = image;
		this.maskLength = maskLength;
	}

	public BImage getBorder()
	{
		int[][] mask = new int[maskLength][maskLength];
		for (int i = 0; i < mask.length; i++)
			for (int j = 0; j < mask.length; j++)
	            mask[i][j] = 1;
		try
		{
			return ImageUtil.differentiate(image, ImageUtil.erode(image, mask));
		}
		catch (NotSameSizeImageException e)
		{
			// Never happens.
			e.printStackTrace();
			return null;
		}
	}

	public BImage getImage()
	{
		return image;
	}

	public void setImage(BImage image)
	{
		this.image = image;
	}

	public int getMaskLength()
	{
		return maskLength;
	}

	public void setMaskLength(int maskLength)
	{
		this.maskLength = maskLength;
	}
}
