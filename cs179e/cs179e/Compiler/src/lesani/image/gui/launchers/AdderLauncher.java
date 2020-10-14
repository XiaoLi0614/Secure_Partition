package lesani.image.gui.launchers;

import lesani.image.core.image.GSImage;
import lesani.image.util.ImageUtil;
import lesani.image.util.exception.NotAnImageFileException;

import java.util.Scanner;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Feb 27, 2010
 * Time: 3:43:34 PM
 */

public class AdderLauncher {
    public static void main(String[] args)
    {
        Scanner consoleScanner = new Scanner(System.in);
        System.out.println("Adder\nEnter Path\\name for images one at a line or 'Q' to Quit.");
        Vector<String> pathNameVector = new Vector<String>();
        String input = consoleScanner.nextLine();
        while(!input.equalsIgnoreCase("Q"))
        {
            pathNameVector.add(input);
            input = consoleScanner.nextLine();
        }

        if (pathNameVector.size() == 0)
            System.exit(1);

        String[] pathNames = new String[pathNameVector.size()];
        pathNameVector.toArray(pathNames);

        GSImage[] images = new GSImage[pathNames.length];
        try
        {
            for (int i = 0; i < images.length; i++)
            {
                images[i] = ImageUtil.getGrayScaleImage(pathNames[i]);
            }
        }
        catch (NotAnImageFileException e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        GSImage resultImage = ImageUtil.getAddedAndScaled(images);
//        ImageUtil.showImage("Added", resultImage.getBufferedImage());
    }
    
}
