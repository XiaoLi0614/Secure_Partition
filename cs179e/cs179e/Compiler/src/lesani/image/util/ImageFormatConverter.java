package lesani.image.util;

import lesani.file.Address;
import lesani.image.core.image.GSImage;
import lesani.image.core.image.Image;
import lesani.image.util.exception.NotAnImageFileException;
import lesani.file.Util;

import javax.imageio.ImageIO;
import java.io.*;


public class ImageFormatConverter {

    public static void main(String[] args) throws IOException, NotAnImageFileException {

        final String jpgInputPathName =
//                "/home/lesani/Prog/Shortcuts/4.Research.msc/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/TestImages/Horse.jpg";
                "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/TestImages/BigHorse.jpg";
        final String formatExtension = "format";
        toFormat(jpgInputPathName, formatExtension);
        
        final String formatInputPathName =
//                "/home/lesani/Prog/Shortcuts/4.Research.msc/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/TestImages/Horse.format";
//                "/home/lesani/Prog/Shortcuts/4.Research.msc/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/TestImages/Horse2.format";
                "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/TestImages/BigHorse2.format";
        final String jpgOutPathName =
//                "/home/lesani/Prog/Shortcuts/4.Research.msc/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/TestImages/Horse2.jpg";
                "/media/MOHSENHD/1.Works/3.Research/0.Research/1.CDSC/1.ImageProcessing/2.Projects/Project/Images/TestImages/BigHorse2.jpg";

        Image image = fromFormat(formatInputPathName);
        ImageIO.write(image.getBufferedImage(), "JPG", new File(jpgOutPathName));

    }

    public static void toFormat(String filePathName, String newExtension)
            throws NotAnImageFileException, IOException {
        
        GSImage gsImage = ImageUtil.getGrayScaleImage(filePathName);
        FileOutputStream fileOutputStream =
                new FileOutputStream(Address.getPathNameNewExt(filePathName, newExtension));

        int width = gsImage.getWidth();
        int height = gsImage.getHeight();
        int lWidth = low(width);
        int hWidth = high(width);
        int lHeight = low(height);
        int hHeight = high(height);
        // We use little endian.
        fileOutputStream.write(lWidth);
        fileOutputStream.write(hWidth);
        fileOutputStream.write(lHeight);
        fileOutputStream.write(hHeight);
//        System.out.println("Image 1");
//        System.out.println("width:" + width);
//        System.out.println("height:" + height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int value = gsImage.get(i, j);
                byte bValue = (byte) value;
//                int value = gsImage.get(i, j);
//                System.out.print(bValue + ":" + value + " ");
                fileOutputStream.write(bValue);
            }
//            System.out.println("");
        }
        fileOutputStream.close();
    }

    public static GSImage fromFormat(String filePathName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePathName);

        byte lWidth = (byte)fileInputStream.read();
        byte hWidth = (byte)fileInputStream.read();
        byte lHeight = (byte)fileInputStream.read();
        byte hHeight = (byte)fileInputStream.read();
        int width = buildInt(lWidth, hWidth);
        int height = buildInt(lHeight, hHeight);
        
        GSImage gsImage = new GSImage(width, height);

//        System.out.println("Image 2");
//        System.out.println("width:" + width);
//        System.out.println("height:" + height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int value = fileInputStream.read();
//                System.out.print(value + " ");
                // should be int
                // otherwise we can get a negative byte
                gsImage.set(i, j, value);
            }
//            System.out.println("");
        }
        return gsImage;
    }

    public static byte high(int i) {
        return (byte)(i / 256);
    }
    public static byte low(int i) {
        return (byte)(i % 256);
    }

    public static int buildInt(byte low, byte high) {
        int uLow = toUnsigned(low);
        int uHigh = toUnsigned(high);
        return uHigh*256 + uLow;
    }

    public static int toUnsigned(byte theByte) {
        int intValue = theByte;
        if (intValue < 0) {
            intValue = (int)(theByte & 0x7F) + 128;
        }
        return intValue;
    }
}
