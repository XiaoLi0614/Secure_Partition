package lesani.image.xdatahiding.runlength.core;

import lesani.image.core.image.BImage;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 * Date: Jan 25, 2005
 * Time: 6:21:11 PM
 */

public class RunPairIterator
{
	BImage bImage;
	int y, x;
	int previousValue;

	public RunPairIterator(BImage bImage)
	{
		this.bImage = bImage;

		// Pass the first white (0) run.
		x = 0;
		y = 0;
		sweepWhiteRun();
	}

	private void sweepWhiteRun()
	{
		int width = bImage.getWidth();
		int height = bImage.getHeight();

		for (; y < height; y++)
		{
			for (; x < width; x++)
			{
				int value = bImage.get(x, y);
                if (value == 1)
				{
					if (x == width)
					{
						x = 0;
						y++;
					}
					previousValue = 1;
					return;
				}
			}
			x = 0;
		}

	}

	public RunPair getNextRunPair() throws NoMoreRunPairException
	{
		int width = bImage.getWidth();
		int height = bImage.getHeight();

		if (y >= height)
			throw new NoMoreRunPairException("No other run pair");

		int blackRunLength = 0, whiteRunLength = 0;
		int boundaryX = 0, boundaryY = 0;

		for (; x < width; x++)
		{
			int value = bImage.get(x, y);

			if (previousValue == 0)
			{
				if (value == 1)
				{
					previousValue = 1;
					//System.out.println(blackRunLength + "\t" + whiteRunLength + "\t" + boundaryX + "\t" + boundaryY);
					return new RunPair(blackRunLength, whiteRunLength, boundaryX, boundaryY);
					// End of one black and white run starting another one.
					//blackRunLength = 1;
				}
				else //if (value == 0)
				{
					whiteRunLength++;
				}
			}
			else //if (previousValue == 1)
			{
				if (value == 1)
				{
					blackRunLength++;
				}
				else //if (value == 0)
				{
					whiteRunLength = 1;
					boundaryX = x;
					boundaryY = y;
				}
			}

			previousValue = value;
		}
		x = 0;
        y++;
        sweepWhiteRun();

		if (whiteRunLength == 0)
			return getNextRunPair();
		else
			return new RunPair(blackRunLength, whiteRunLength, boundaryX, boundaryY);

	}
}
