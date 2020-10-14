package lesani.image.xdatahiding.runlength.core;

import lesani.image.core.image.BImage;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 * Date: Jan 25, 2005
 * Time: 6:18:43 PM
 */
public class RunLengthDataHider
{
	public static int MIN_RUN_LENGTH = 4;
	private int minRunLength = MIN_RUN_LENGTH;
	public static int NUMBER_LENGTH = 8;
	private int numberLenght = NUMBER_LENGTH;
	// Number of bits to embed from the right hand side of int values of width and height of the logo
	private int bitCount = 0;
	// Number of bits embedded from the int values of width and height of the logo until now.

	private BImage originalImage, logoImage;
	private PixelIterator pixelIterator;

	public RunLengthDataHider(BImage originalImage, BImage logoImage)
	{
		//TODO: make use of DataStreams to embed data of any form
		this.originalImage = originalImage;
		this.logoImage = logoImage;
	}

	public BImage getEmbeddingImage()
	{
		int width = originalImage.getWidth();
		int height = originalImage.getHeight();
		BImage embeddingImage = new BImage(width, height);

		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				embeddingImage.set(x, y, originalImage.get(x, y));

		RunPairIterator runPairIterator = new RunPairIterator(originalImage);

		pixelIterator = new PixelIterator(logoImage);


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
                    int bit = getNextHiddenBit();
					if (bit == 0)
					{
						if ((blackRunLength % 2) == 1)
							RunPair.shrinkBiggerRun(embeddingImage, runPair);
					}
					else //if (bit == 1)
					{
						if ((blackRunLength % 2) == 0)
							RunPair.shrinkBiggerRun(embeddingImage, runPair);
					}
				}

			}

			catch (NoMoreRunPairException e)
			{
				//e.printStackTrace();
					return embeddingImage;
			}
		}
	}

	private int getNextHiddenBit()
	{
		int width = logoImage.getWidth();
		int height = logoImage.getHeight();

		if (bitCount == 2 * numberLenght) // Embed pixels
		{
			return pixelIterator.getNext();
		}

		// Embed width and height
		int numberToHide;
		int bitPlace;
		if (bitCount < numberLenght)
		{
			numberToHide = width;
			bitPlace = bitCount;
		}
		else
		{
			numberToHide = height;
			bitPlace = bitCount - numberLenght;
		}
		bitCount++;

		if (((1 << bitPlace) &  numberToHide) == 0)
			return 0;
		else
			return 1;

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

/*
	if (
		((blackRunLength > whiteRunLenght) && (whiteRunLenght >= minLength)) ||
		((whiteRunLenght > blackRunLength) && (blackRunLength >= minLength)) ||
		((blackRunLength == whiteRunLenght) && (whiteRunLenght > minLength))
	)
	{

	}
*/
