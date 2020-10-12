package lesani.image.xtras;

import lesani.image.core.image.GSImage;
import lesani.image.util.ImageUtil;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen's Desktop
 * Date: Apr 26, 2007
 * Time: 5:27:11 PM
 */
public class Main
{
	public static void main(String[] args)
	{

		final int WIDTH = 500;
		final int HEIGHT = 500;

		GSImage gsImage = new GSImage(WIDTH, HEIGHT);
		for (int i = 0; i < WIDTH; i++)
		{
			for (int j = 0; j < HEIGHT; j++)
			{
				if (j < HEIGHT / 2)
				  	gsImage.set(i, j, 0);
				else
					gsImage.set(i, j, 1);
			}
		}

/*
		for (int j = 0; j < HEIGHT; j++)
		{
			for (int i = 0; i < WIDTH; i++)
				System.out.print(gsImage.get(i, j) + "\t");
			System.out.print("\n");
		}
		System.out.println("");
*/

//		ImageUtil.showImage("Sample Image", gsImage.getBufferedImage());

		GSImage sharpened = ImageUtil.getScaledLaplacian(gsImage);

/*
		for (int j = 0; j < HEIGHT; j++)
		{
			for (int i = 0; i < WIDTH; i++)
				System.out.print(sharpened.get(i, j) + "\t");
			System.out.print("\n");
		}
*/

//		ImageUtil.showImage("Sharpened Image", sharpened.getBufferedImage());

/*
		GSImage grayScaleImage2 = new GSImage(WIDTH, HEIGHT);
		for (int i = 0; i < WIDTH; i++)
		{
			for (int j = 0; j < HEIGHT; j++)
			{
					grayScaleImage2.set(i, j, 300);
			}
		}
		System.out.println(grayScaleImage2.get(2, 3));
		ImageUtil.showImage("Sample Image", grayScaleImage2);
*/


	}
}
