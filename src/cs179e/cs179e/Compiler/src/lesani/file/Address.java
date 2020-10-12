package lesani.file;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: lesani
 * Date: Mar 2, 2010
 * Time: 2:10:28 PM
 */

public class Address {
    String value;

    public Address(String value) {
        this.value = value;
    }

    //-----------------------------------------------

    private static final String SEPARATOR = "/";

    public static String getNameNExtension(File file) {
        return file.getName();
    }
    public static String getNameNExtension(String filePathName) {
        return new File(filePathName).getName();
    }

    public static String getPath(File file) {
        return file.getParent();
    }
    public static String getPath(String filePathName) {
        return new File(filePathName).getParent();
    }

    public static String getRelPath(String fileRelPathName) {
        return null;
    }


    public static String getName(String fileNameExtension) {
        int index = 0;
        while (true)
        {
            //println(fileNameExtension.substring(index));

            int thisIndex = fileNameExtension.substring(index).indexOf(".");
            if (thisIndex == -1)
                break;
            index += thisIndex + 1;

        }
        if (index != 0)
            return fileNameExtension.substring(0, index - 1);
        else
            return fileNameExtension;

    }

    public static String getExtension(String fileNameExtension) {


        int i = fileNameExtension.lastIndexOf('.');

        String ext = null;
        if (i > 0 && i < fileNameExtension.length() - 1)
            ext = fileNameExtension.substring(i+1).toLowerCase();

        if ((ext == null) || (ext.contains("/")))
            return "";
        return ext;
    }


    public static String getPathNameNewExt(String filePathName, String newExtension) {
        File file = new File(filePathName);
        String fileName = getNameNExtension(file);
        String bareName = getName(fileName);
        return file.getParent() + SEPARATOR + bareName + "." + newExtension;
    }

    public static String getBareName(String fileName) {
        int index = 0;
        while (true)
        {
            //println(fileName.substring(index));

            int thisIndex = fileName.substring(index).indexOf(".");
            if (thisIndex == -1)
                break;
            index += thisIndex + 1;

        }
        if (index != 0)
            return fileName.substring(0, index - 1);
        else
            return fileName;

    }


    public static String capName(String name) {
        String firstChar = name.substring(0, 1).toUpperCase();
        String capName = firstChar + name.substring(1);
        return capName;
    }

    public static void main(String[] args) {
        String name = "proj/proj1/file.txt";
        String name2 = "proj/proj1";
        System.out.println(getPath(name));
        System.out.println(getPath(name2));
        System.out.println(getBareName(name));
        System.out.println(getBareName(getName(name)));
    }

}

