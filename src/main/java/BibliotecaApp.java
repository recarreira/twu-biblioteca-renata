import book.Book;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by rcarreira on 1/8/15.
 */
public class BibliotecaApp {



   public ArrayList<Book> bookList = new ArrayList<Book>();


    public static void main(String[] args) {
        welcomeMessage();
    }

    public static void welcomeMessage(){
        System.out.println("Welcome!");
    }

    public void printBooKListDetails() {
        int length = bookListMaxLengthString(bookList);
        for (Book objBook : bookList){
            objBook.printBookDetails(length);
        }
    }

    public void createDefaultBookObjects(){
        bookList.add(new Book("Learning TDD", "Cool Girl", 2015));
        bookList.add(new Book("Awesome book", "author with huge name", 2014));
        bookList.add(new Book("Another awesome book", "myself", 2013));
    }

    public int bookListMaxLengthString(ArrayList<Book> arrayList){
        int max = 0;
        for(Book objBook:arrayList){
            if (objBook.getTitle().length() > max ){
                max = objBook.getTitle().length();
            }

            if (objBook.getAuthor().length() > max){
                max = objBook.getAuthor().length();
            }
        }
        return max;
    }


}
