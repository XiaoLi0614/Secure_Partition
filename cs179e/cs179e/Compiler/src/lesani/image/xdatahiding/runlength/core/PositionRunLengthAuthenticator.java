package lesani.image.xdatahiding.runlength.core;

import lesani.image.core.image.BImage;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 * Date: Jun 11, 2005
 * Time: 2:48:53 PM
 */

public class PositionRunLengthAuthenticator
{
	private int minRunLength = PositionRunLengthDataHider.MIN_RUN_LENGTH;

	public static final int VERTICAL_LINE_COUNT = 5;
	public static final int HORIZONTAL_LINE_COUNT = 5;

	private BImage embeddingImage, logoImage;
	private int windowWidth;
	private int windowHeight;

	private BImage markedImage = null;
	private boolean authenticated = false;

	public PositionRunLengthAuthenticator(BImage originalImage, BImage logoImage, int windowWidth, int windowHeight)
	{
		//TODO: make use of DataStreams to embed data of any form
		this.embeddingImage = originalImage;
		this.logoImage = logoImage;
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
	}

	public BImage getMarkedImage()
	{
		if (markedImage == null)
			authenticate();
		return markedImage;
	}

	public boolean isAuthenticated()
	{
		if (markedImage == null)
			authenticate();
		return authenticated;
	}

	private void authenticate()
	{
		int width = embeddingImage.getWidth();
		int height = embeddingImage.getHeight();

		markedImage = new BImage(width, height);

		int x = 0;
		int y = 0;

		authenticated = true;

		while (y < height)
		{
			BImage blockImage = embeddingImage.getBlock(x, y, windowWidth, windowHeight);

			RunLengthDataExtracter dataExtracter = new RunLengthDataExtracter(blockImage);
			dataExtracter.setNumberLenght(0);
			dataExtracter.setMinRunLength(minRunLength);

			BImage markedBlockImage;
			if (dataExtracter.isAuthenticated(logoImage))
			    markedBlockImage = blockImage;
			else
			{
				markedBlockImage = getMarkedBlock(blockImage);
				authenticated = false;
			}

			markedImage.setBlock(x, y, markedBlockImage);

			x += windowWidth;
			if (x >= width)
			{
				x = 0;
				y += windowHeight;
			}
		}

	}

	private BImage getMarkedBlock(BImage blockImage)
	{
		BImage markedBlock = (BImage) blockImage.clone();

		for (int i = 0; i < markedBlock.getHeight(); i += markedBlock.getHeight() / HORIZONTAL_LINE_COUNT)
			for (int j = 0; j < markedBlock.getWidth(); j++)
				markedBlock.set(j, i, BImage.BLACK_BIT);
		for (int j = 0; j < markedBlock.getWidth(); j++)
			markedBlock.set(j, markedBlock.getHeight() - 1, BImage.BLACK_BIT);

		for (int i = 0; i < markedBlock.getWidth(); i += markedBlock.getWidth() / VERTICAL_LINE_COUNT)
			for (int j = 0; j < markedBlock.getHeight(); j++)
				markedBlock.set(i, j, BImage.BLACK_BIT);
		for (int j = 0; j < markedBlock.getHeight(); j++)
			markedBlock.set(markedBlock.getWidth() - 1, j, BImage.BLACK_BIT);

		return markedBlock;

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
