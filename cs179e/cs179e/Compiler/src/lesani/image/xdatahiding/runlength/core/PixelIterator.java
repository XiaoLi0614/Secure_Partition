package lesani.image.xdatahiding.runlength.core;

import lesani.image.core.image.BImage;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 * Date: Jun 11, 2005
 * Time: 11:12:53 AM
 */
public class PixelIterator
{
	private BImage image;
	private int pixelX = 0;
	private int pixelY = 0;

	public PixelIterator(BImage image, int pixelX, int pixelY)
	{
		this.image = image;
		this.pixelX = pixelX;
		this.pixelY = pixelY;
	}

	public PixelIterator(BImage image)
	{
		this.image = image;
	}

	public int getNext()
	{
		int width = image.getWidth();
		int height = image.getHeight();

		int value = image.get(pixelX, pixelY);
		if (pixelX == width - 1)
		{
			if (pixelY == height - 1)
			{
				pixelX = 0;
				pixelY = 0;
				return value;
			}

			pixelX = 0;
			pixelY++;
		}
		else
			pixelX++;

		return value;
	}

	public boolean hasNext()
	{
        return ((pixelX + 1) * (pixelY) + pixelY + 1 < (image.getWidth() * image.getHeight()));
	}

	public void reset()
	{
		pixelX = 0;
		pixelY = 0;
	}
}
