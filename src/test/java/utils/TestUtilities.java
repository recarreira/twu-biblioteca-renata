package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by rcarreira on 1/9/15.
 */
public class TestUtilities {

    public static String txtFileContentToString(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(path));
        String str = "";
        while (scanner.hasNext()){
            str += scanner.nextLine() + "\n";
        }
        return str;
    }
}
