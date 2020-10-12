package lesani.file.others;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Converter {
   public static void main(String[] args) throws FileNotFoundException {
      String inputTextFilePath = "/media/MOHSENHD/AtHand/MyText.txt";
//      String inputTextFilePath = args[0];
      String outputTextFilePath = "/media/MOHSENHD/AtHand/MyText2.txt";
//      String outputTextFilePath = args[1];

      Scanner reader = new Scanner(new FileInputStream(inputTextFilePath));
      PrintWriter writer = new PrintWriter(new FileOutputStream(outputTextFilePath));

      while (reader.hasNext()) {
         String line = reader.nextLine();
         writer.println(line);
      }

      reader.close();
      writer.close();
   }
}
