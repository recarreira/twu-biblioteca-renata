package utils;

import biblioteca.Biblioteca;
import book.Book;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by rcarreira on 1/9/15.
 */
public class TestUtilities {

    public static void populateBiblioteca(Biblioteca biblioteca){
        biblioteca.bookList.add(new Book("Learning TDD", "Cool Girl", 2015));
        biblioteca.bookList.add(new Book("Awesome book", "author with huge name", 2014));
        biblioteca.bookList.add(new Book("Another awesome book", "myself", 2013));

    }
}
