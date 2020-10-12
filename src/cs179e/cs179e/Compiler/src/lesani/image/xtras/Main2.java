package lesani.image.xtras;

import lesani.image.core.image.GSImage;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen's Desktop
 * Date: Apr 26, 2007
 * Time: 5:27:11 PM
 */
public class Main2
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
				  	gsImage.set(i, j, 0);
			}
		}
        gsImage.drawCircle(10, 100, 100, 255);

//		ImageUtil.showImage("Sample Image", gsImage.getBufferedImage());

	}
}