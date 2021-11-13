package Reader;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
  public static ArrayList<String> readStrings(String path) {
    ArrayList<String> stringList = new ArrayList<String>();
    try {
      Scanner f = new Scanner(new File(path));
      while (f.hasNextLine()) {
        stringList.add(f.nextLine());
      }
      f.close();
    } catch (Exception e) {
      System.out.println(e);
    }
    return stringList;
  }
}
