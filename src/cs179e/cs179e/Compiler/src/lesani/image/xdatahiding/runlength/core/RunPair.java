package lesani.image.xdatahiding.runlength.core;

import lesani.image.core.image.BImage;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 * Date: Jan 25, 2005
 * Time: 6:23:10 PM
 */

public class RunPair
{
	int blackRunLength, whiteRunLength, boundaryX, boundaryY;
	// black/white pairs with boundary in white run

	public RunPair(int blackRunLength, int whiteRunLength, int boundaryX, int boundaryY)
	{
		this.blackRunLength = blackRunLength;
		this.whiteRunLength = whiteRunLength;
		this.boundaryX = boundaryX;
		this.boundaryY = boundaryY;
	}

	public String toString()
	{
		return "B:" + blackRunLength + " W:" + whiteRunLength + " X:" + boundaryY + " Y:" + boundaryX; 
	}

	public int getBlackRunLength()
	{
		return blackRunLength;
	}

	public void setBlackRunLength(int blackRunLength)
	{
		this.blackRunLength = blackRunLength;
	}

	public int getWhiteRunLength()
	{
		return whiteRunLength;
	}

	public void setWhiteRunLength(int whiteRunLength)
	{
		this.whiteRunLength = whiteRunLength;
	}

	public int getBoundaryX()
	{
		return boundaryX;
	}

	public void setBoundaryX(int boundaryX)
	{
		this.boundaryX = boundaryX;
	}

	public int getBoundaryY()
	{
		return boundaryY;
	}

	public void setBoundaryY(int boundaryY)
	{
		this.boundaryY = boundaryY;
	}

	public static void shrinkBiggerRun(BImage embeddingImage, RunPair runPair)
	{
		int blackRunLength = runPair.getBlackRunLength();
		int whiteRunLenght = runPair.getWhiteRunLength();
		int boundaryX = runPair.getBoundaryX();
		int boundaryY = runPair.getBoundaryY();

		if (blackRunLength >= whiteRunLenght)
		{
			if (boundaryX == 0)
			{
				boundaryX = embeddingImage.getWidth() - 1;
				boundaryY -= 1;
			}
			else
				boundaryX -= 1;
			embeddingImage.set(boundaryX, boundaryY, 0);
		}
		else // if (whiteRunLenght > blackRunLength)
			embeddingImage.set(boundaryX, boundaryY, 1);
	}
}
