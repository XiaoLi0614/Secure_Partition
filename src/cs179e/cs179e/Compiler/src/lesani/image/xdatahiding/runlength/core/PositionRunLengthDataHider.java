package lesani.image.xdatahiding.runlength.core;

import lesani.image.core.image.BImage;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 * Date: Jun 11, 2005
 * Time: 11:20:32 AM
 */

public class PositionRunLengthDataHider
{
	public static int MIN_RUN_LENGTH = 4;
	private int minRunLength = MIN_RUN_LENGTH;

	private BImage originalImage, logoImage;
	private int windowWidth;
	private int windowHeight;

	public PositionRunLengthDataHider(BImage originalImage, BImage logoImage, int windowWidth, int windowHeight)
	{
		//TODO: make use of DataStreams to embed data of any form
		this.originalImage = originalImage;
		this.logoImage = logoImage;
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
	}

	public BImage getEmbeddingImage()
	{
		int width = originalImage.getWidth();
		int height = originalImage.getHeight();
		BImage embeddingImage = new BImage(width, height);

		int x = 0;
		int y = 0;

		while (y < height)
		{
			BImage blockImage = originalImage.getBlock(x, y, windowWidth, windowHeight);

			RunLengthDataHider dataHider = new RunLengthDataHider(blockImage, logoImage);
			dataHider.setNumberLenght(0);
			dataHider.setMinRunLength(minRunLength);
			BImage embeddingBlockImage = dataHider.getEmbeddingImage();

			embeddingImage.setBlock(x, y, embeddingBlockImage);

			x += windowWidth;
			if (x >= width)
			{
				x = 0;
				y += windowHeight;
			}
		}

		return embeddingImage;
	}

	public int getMinRunLength()
	{
		return minRunLength;
	}

	public void setMinRunLength(int minRunLength)
	{
		this.minRunLength = minRunLength;
	}
}
