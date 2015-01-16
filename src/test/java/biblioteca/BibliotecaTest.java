package biblioteca;

import book.Book;
import movie.Movie;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BibliotecaTest {

    BibliotecaCLI bibliotecaCLI;
    Biblioteca biblioteca;

    Book aBook = new Book("Learning TDD", "Cool Girl", 2015);
    Book anotherBook = new Book("Awesome book", "author with huge name", 2014);
    Book yetAnotherBook = new Book("Another awesome book", "myself", 2013);
    Book newBook = new Book("New book", "someone", 2010);

    Movie aMovie = new Movie("Some movie", "Some Pretty Director", 2012, 10);
    Movie yetAnotherMovie = new Movie("Just another movie", "Unknown Director", 2009, 3);


    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    @Before
    public void setUp() throws Exception {
        biblioteca = new Biblioteca();
        bibliotecaCLI = new BibliotecaCLI(biblioteca);
        BibliotecaData.populateWithBooks(biblioteca);
        BibliotecaData.populateWithMovies(biblioteca);

    }

    @Test
    public void returnsTrueForSuccessfulCheckoutBook() {
        assertThat(biblioteca.checkoutBook("Another awesome book"), is(true));
    }

    @Test
    public void returnsFalseForUnsuccessfulCheckoutBook(){
        assertThat(biblioteca.checkoutBook("Unavailable book"), is(false));
    }

    @Test
    public void printsSuccessMessageAfterSuccessfulBookCheckout(){
        biblioteca.checkoutBook("Another awesome book");
        assertEquals(Biblioteca.Messages.CHECKOUT_BOOK + "\n", log.getLog());
    }

    @Test
    public void removesBookFromBookListAfterCheckoutBook(){
        Map<String, Book> books = new HashMap<String, Book>();
        books.put(aBook.getTitle(), aBook);
        books.put(yetAnotherBook.getTitle(), yetAnotherBook);
        biblioteca.checkoutBook("Awesome book");
        assertEquals(books, biblioteca.bookList);
    }

    @Test
    public void addsBookToCheckedOutListAftercheckoutBook(){
        Map<String, Book> books = new HashMap<String, Book>();
        books.put(aBook.getTitle(), aBook);
        biblioteca.checkoutBook("Learning TDD");
        assertEquals(books, biblioteca.checkedOutBooks);
    }

    @Test
    public void printsUnsuccessfulMessageAfterUnsuccessfulCheckoutBook(){
        biblioteca.checkoutBook("We don't have this book");
        assertEquals(Biblioteca.Messages.UNSUCCESSFUL_BOOK_CHECKOUT + "\n", log.getLog());
    }

    @Test
    public void returnsTrueForSuccessfulReturnBook(){
        biblioteca.checkedOutBooks.put(newBook.getTitle(), newBook);
        assertThat(biblioteca.returnBook("New book"),is(true));
    }

    @Test
    public void returnsFalseIfReturnBookFail(){
        assertThat(biblioteca.returnBook("Never checked out book"), is(false));
    }

    @Test
    public void addsBookToBookListAfterReturnBook(){
        Map<String, Book> books = new HashMap<String, Book>();
        books.put(aBook.getTitle(), aBook);
        books.put(anotherBook.getTitle(),anotherBook);
        books.put(yetAnotherBook.getTitle(), yetAnotherBook);
        books.put(newBook.getTitle(), newBook);

        biblioteca.checkedOutBooks.put(newBook.getTitle(), newBook);

        biblioteca.returnBook("New book");

        assertEquals(books, biblioteca.bookList);
    }


    @Test
    public void printsSuccessMessageAfterSuccessfulReturnBook(){
        biblioteca.checkedOutBooks.put(newBook.getTitle(), newBook);
        biblioteca.returnBook("New book");

        assertEquals(Biblioteca.Messages.SUCCESSFUL_RETURN +"\n", log.getLog());
    }

    @Test
    public void removesBookFromCheckedOutListAfterReturnBook() {
        biblioteca.checkedOutBooks.put(newBook.getTitle(), newBook);
        biblioteca.returnBook("New book");
        Map<String, Book> books = new HashMap<String, Book>();

        assertEquals(books, biblioteca.checkedOutBooks);
    }

    @Test
    public void printsUnsuccessfulMessageAfterUnsuccessfulReturnBook() {
        biblioteca.returnBook("We dont have this book");

        assertEquals(Biblioteca.Messages.UNSUCCESSFUL_RETURN + "\n", log.getLog());
    }

    @Test
    public void returnsTrueForSuccessfulCheckoutMovie(){
        assertThat(biblioteca.checkoutMovie("Another movie"), is(true));
    }

    @Test
    public void returnsFalseIfCheckoutMovieFail(){
        assertThat(biblioteca.checkoutMovie("Not a movie"), is(false));
    }

    @Test
    public void removesMovieFromMovieListAfterCheckoutMovie(){
        Map<String, Movie> movies = new HashMap<String, Movie>();
        movies.put(aMovie.getName(), aMovie);
        movies.put(yetAnotherMovie.getName(), yetAnotherMovie);

        biblioteca.checkoutMovie("Another movie");
        assertEquals(movies, biblioteca.movieList);
    }

    @Test
    public void printsSuccessMessageAfterSuccessfulMovieCheckout(){
        biblioteca.checkoutMovie("Just another movie");

        assertEquals(Biblioteca.Messages.CHECKOUT_MOVIE + "\n", log.getLog());
    }

    @Test
    public void printsUnsuccessfulMessageAfterUnsuccessfulCheckoutMovie(){
        biblioteca.checkoutMovie("Not a movie");

        assertEquals(Biblioteca.Messages.UNSUCCESSFUL_MOVIE_CHECKOUT + "\n", log.getLog());
    }

    @Test
    public void loginSuccessful(){
        BibliotecaData.createUsers(biblioteca);
        assertTrue(biblioteca.login("000-0001", "1234"));
    }

    @Test
    public void wrongPasswordLogin(){
        assertFalse(biblioteca.login("000-0001", "wrong_password"));
    }

    @Test
    public void wrongUserLogin(){
        assertFalse(biblioteca.login("000-0009", "password"));
    }

    @Test
    public void whoCheckedOutMovie(){
        User user = new User("000-0009", "passwd", "y Name", "name@name.com", "9999-9999");
        biblioteca.user = user;
        biblioteca.checkoutMovie("Some movie");
        Map<String, String> testMovieCheckedOutBy = new HashMap<String, String>();
        testMovieCheckedOutBy.put("Some movie", user.getLibraryNumber());
        assertEquals(testMovieCheckedOutBy, biblioteca.movieCheckedOutBy);
    }

    @Test
    public void whoCheckedOutMovieWhencheckOutFails(){
        User user = new User("000-0009", "passwd", "y Name", "name@name.com", "9999-9999");
        biblioteca.user = user;
        biblioteca.checkoutMovie("Some movie");
        Map<String, String> testMovieCheckedOutBy = new HashMap<String, String>();
        testMovieCheckedOutBy.put("Some movie", user.getLibraryNumber());
        assertEquals(testMovieCheckedOutBy, biblioteca.movieCheckedOutBy);
    }



}