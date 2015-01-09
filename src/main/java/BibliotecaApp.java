import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by rcarreira on 1/8/15.
 */
public class BibliotecaApp {

    ArrayList<String> bookList = new ArrayList<String>(Arrays.asList("Book1", "Book2","Book3","Book4"));


    public static void main(String[] args) {
        welcomeMessage();
    }

    public static void welcomeMessage(){
        System.out.print("Welcome!");
    }

    public void printBookList() {
        for(String objBook:bookList){
            System.out.println(objBook);
        }
    }


    public void printBooKDetailsAsColumn() {
    }
}
