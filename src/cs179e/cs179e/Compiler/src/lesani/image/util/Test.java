package lesani.image.util;

import lesani.image.core.image.BImage;

/**
 * Created by IntelliJ IDEA.
 * User: Mohsen Lesani
 * Date: Apr 13, 2005
 * Time: 5:59:48 PM
 */
public class Test
{

	public static void main(String[] args)
	{
		BImage bImage = new BImage(200, 200);
		for (int i = 0; i < 200; i++)
		{
			for (int j = 0; j < 200; j++)
			{
				bImage.set(i, j, 1);
			}
		}
//		ImageUtil.showImage(bImage);
	}

}
