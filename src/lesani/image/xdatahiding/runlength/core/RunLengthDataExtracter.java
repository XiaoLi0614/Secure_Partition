package lesani.image.xdatahiding.runlength.core;

import lesani.image.core.image.BImage;
import lesani.image.core.image.GSImage;
import lesani.image.util.ImageUtil;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 * Date: Jan 26, 2005
 * Time: 2:36:39 PM
 */

public class RunLengthDataExtracter
{
	private int minRunLength = RunLengthDataHider.MIN_RUN_LENGTH;
	private int numberLenght = RunLengthDataHider.NUMBER_LENGTH;
	private int bitCount;

	private int hiddenPixelX;
	private int hiddenPixelY;
	private int hiddenImageWidth;
	private int hiddenImageHeight;

    private BImage embeddingImage;
	private BImage hiddenImage = null;
	private BImage logoImage;
	private int logoPixelX;
	private int logoPixelY;


	public RunLengthDataExtracter(BImage bImage)
	{
		this.embeddingImage = bImage;
	}

	public BImage getHiddenImage()
	{
		if (hiddenImage != null)
			return hiddenImage;

		hiddenImageWidth = 0;
		hiddenImageHeight = 0;
		bitCount = 0;

		RunPairIterator runPairIterator = new RunPairIterator(embeddingImage);
		while (true)
		{
			try
			{
				RunPair runPair = runPairIterator.getNextRunPair();
				int blackRunLength = runPair.getBlackRunLength();
				int whiteRunLength = runPair.getWhiteRunLength();
				int minLength = Math.min(blackRunLength, whiteRunLength);
				if (
						((blackRunLength == whiteRunLength) && (blackRunLength > minRunLength)) ||
						((blackRunLength != whiteRunLength) && (minLength >= minRunLength))
				)
				{
					int bit = (blackRunLength % 2);
					addExtractedBitToImage(bit);
				}
				else
					;

			}
			catch (NoMoreRunPairException e)
			{
				return hiddenImage;
			}
		}
	}

	private void addExtractedBitToImage(int bit)
	{
		if (bitCount == 2 * numberLenght) // set pixels
		{
			hiddenImage.set(hiddenPixelX, hiddenPixelY, bit);
			if (hiddenPixelX == hiddenImage.getWidth() - 1)
			{
				if (hiddenPixelY == hiddenImage.getHeight() - 1)
				{
					hiddenImage = ImageUtil.addOneRow(hiddenImage);
					hiddenPixelY = hiddenImage.getHeight() - 1;
					hiddenPixelX = 0;
					return;
				}

				hiddenPixelX = 0;
				hiddenPixelY++;
			}
			else
				hiddenPixelX++;
			return;
		}

		// make width and height
		int bitPlace;
		if (bitCount < numberLenght)
		{
			bitPlace = bitCount;
			hiddenImageWidth = (bit << bitPlace) | (hiddenImageWidth);
		}
		else
		{
			bitPlace = bitCount - numberLenght;
			hiddenImageHeight = (bit << bitPlace) | (hiddenImageHeight);
		}

		bitCount++;

		if (bitCount == 2 * numberLenght)
			hiddenImage = new BImage(hiddenImageWidth, hiddenImageHeight);
	}

	public GSImage getMarkedImage(BImage logoImage)
	{
		this.logoImage = logoImage;
		logoPixelX = 0;
		logoPixelY = 0;
		hiddenImageWidth = 0;
		hiddenImageHeight = 0;
		bitCount = 0;

		GSImage clutteredImage = new GSImage(embeddingImage);


		RunPairIterator runPairIterator = new RunPairIterator(embeddingImage);
		while (true)
		{
			try
			{
				RunPair runPair = runPairIterator.getNextRunPair();
				int blackRunLength = runPair.getBlackRunLength();
				int whiteRunLength = runPair.getWhiteRunLength();

				int minLength = Math.min(blackRunLength, whiteRunLength);
				if (
						((blackRunLength == whiteRunLength) && (blackRunLength > minRunLength)) ||
						((blackRunLength != whiteRunLength) && (minLength >= minRunLength))
				)
				{
					int bit = (blackRunLength % 2);
					if (!isValidBit(bit))
						markRun(clutteredImage, runPair);
				}
				else
					;

			}
			catch (Exception e)
			{
				return clutteredImage;
			}
		}
	}

	public boolean isAuthenticated(BImage logoImage)
	{
		this.logoImage = logoImage;
		logoPixelX = 0;
		logoPixelY = 0;
		hiddenImageWidth = 0;
		hiddenImageHeight = 0;
		bitCount = 0;

		RunPairIterator runPairIterator = new RunPairIterator(embeddingImage);
		while (true)
		{
			try
			{
				RunPair runPair = runPairIterator.getNextRunPair();
				int blackRunLength = runPair.getBlackRunLength();
				int whiteRunLength = runPair.getWhiteRunLength();

				int minLength = Math.min(blackRunLength, whiteRunLength);
				if (
						((blackRunLength == whiteRunLength) && (blackRunLength > minRunLength)) ||
						((blackRunLength != whiteRunLength) && (minLength >= minRunLength))
				)
				{
					int bit = (blackRunLength % 2);
					if (!isValidBit(bit))
						return false;
				}
				else
					;

			}
			catch (Exception e)
			{
				return true;
			}
		}
	}
	private void markRun(GSImage clutteredImage, RunPair runPair)
	{
		int grayValue = 255 / 2;

		int x = runPair.getBoundaryX();
		int y = runPair.getBoundaryY();

		for (int i = 0; i < runPair.blackRunLength; i++)
		{
			x--;
			if (x == -1)
			{
				x = clutteredImage.getWidth() - 1;
				y--;
			}
			clutteredImage.set(x, y, grayValue);
		}

		x = runPair.getBoundaryX();
		y = runPair.getBoundaryY();
		for (int i = 0; i < runPair.whiteRunLength; i++)
		{
			clutteredImage.set(x, y, grayValue);
			x++;
			if (x == clutteredImage.getWidth())
			{
				x = 0;
				y++;
			}
		}
	}

	private boolean isValidBit(int bit)
	{
		int hiddenBit;
        if (bitCount == 2 * numberLenght) // pixels
		{
			hiddenBit = logoImage.get(logoPixelX, logoPixelY);
			if (logoPixelX == logoImage.getWidth() - 1)
			{
				if (logoPixelY == logoImage.getHeight() - 1)
				{
					logoPixelX = 0;
					logoPixelY = 0;
				}
				else
				{
					logoPixelX = 0;
					logoPixelY++;
				}
			}
			else
				logoPixelX++;
		}
		else
		{
			if (bitCount < numberLenght)
			{
				int bitPlace = bitCount;
				int originalWidth = logoImage.getWidth();

				if (((1 << bitPlace) & originalWidth) == 0)
					hiddenBit = 0;
				else
					hiddenBit = 1;
			}
			else
			{
				int bitPlace = bitCount - numberLenght;
				int originalHeight = logoImage.getHeight();

				if (((1 << bitPlace) & originalHeight) == 0)
					hiddenBit = 0;
				else
					hiddenBit = 1;
			}
			bitCount++;

		}
		return (hiddenBit == bit);
	}

	public int getMinRunLength()
	{
		return minRunLength;
	}

	public void setMinRunLength(int minRunLength)
	{
		this.minRunLength = minRunLength;
	}

	public int getNumberLenght()
	{
		return numberLenght;
	}

	public void setNumberLenght(int numberLenght)
	{
		this.numberLenght = numberLenght;
	}
}
