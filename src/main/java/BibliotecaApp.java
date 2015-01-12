import biblioteca.Biblioteca;
import biblioteca.BibliotecaCLI;
import book.Book;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by rcarreira on 1/8/15.
 */
public class BibliotecaApp {

    private static Biblioteca biblioteca;

    public static void main(String[] args) {
        biblioteca = new Biblioteca();
        populate();

        BibliotecaCLI bibliotecaCLI = new BibliotecaCLI(biblioteca);
        bibliotecaCLI.welcomeMessage();
        bibliotecaCLI.menu();
    }

    public static void populate(){
        biblioteca.bookList.add(new Book("Learning TDD", "Cool Girl", 2015));
        biblioteca.bookList.add(new Book("Awesome book", "author with huge name", 2014));
        biblioteca.bookList.add(new Book("Another awesome book", "myself", 2013));
    }

}