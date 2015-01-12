package utils;

import biblioteca.Biblioteca;
import book.Book;

/**
 * Created by rcarreira on 1/11/15.
 */
public class BibliotecaUtils {

    public static void createDefaultBookObjects(Biblioteca biblioteca){
        biblioteca.bookList.add(new Book("Learning TDD", "Cool Girl", 2015));
        biblioteca.bookList.add(new Book("Awesome book", "author with huge name", 2014));
        biblioteca.bookList.add(new Book("Another awesome book", "myself", 2013));
    }

}
