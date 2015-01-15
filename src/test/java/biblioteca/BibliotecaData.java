package biblioteca;

import biblioteca.Biblioteca;
import book.Book;
import movie.Movie;
import user.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by rcarreira on 1/9/15.
 */
public class BibliotecaData {

    public static void populateWithBooks(Biblioteca biblioteca){
        biblioteca.bookList.add(new Book("Learning TDD", "Cool Girl", 2015));
        biblioteca.bookList.add(new Book("Awesome book", "author with huge name", 2014));
        biblioteca.bookList.add(new Book("Another awesome book", "myself", 2013));
    }

    public static void populateWithMovies(Biblioteca biblioteca){
        biblioteca.movieList.add(new Movie("Some movie", "Some Pretty Director", 2012, 10));
        biblioteca.movieList.add(new Movie("Another movie", "Just a Director", 2001, 0));
        biblioteca.movieList.add(new Movie("Just another movie", "Unknown Director", 2009, 3));
    }

    public static void createUsers(Biblioteca biblioteca){
        biblioteca.users.put("000-0001",
                new User("000-0001", "1234", "Somebody With a Name", "somebody@someemail.com", "8888-8888"));
        biblioteca.users.put("000-0002",
                new User("000-0002", "password", "User name", "user@user.com", ""));
        biblioteca.users.put("000-0003",
                new User("000-0003", "tomatoes", "Forgot my name", "name@user.com", "9720-8192"));
    }
}
