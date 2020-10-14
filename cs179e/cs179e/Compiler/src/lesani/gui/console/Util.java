package lesani.gui.console;

import java.util.Scanner;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: mohsen
 * Date: Feb 27, 2010
 * Time: 9:04:50 PM
 */

public class Util {
    public static String[] readLines() {
        Scanner consoleScanner = new Scanner(System.in);
        System.out.println("Please enter items one at a line and 'Q' at the end.");

        Vector<String> items = new Vector<String>();

        String input = consoleScanner.nextLine();
        while(!input.equalsIgnoreCase("Q")) {
            items.add(input);
            input = consoleScanner.nextLine();
        }
        String[] itemsArray = new String[items.size()];
        return items.toArray(itemsArray);
    }
}
