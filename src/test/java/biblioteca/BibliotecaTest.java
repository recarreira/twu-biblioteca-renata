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
    public void findsPositionForValidBookTitle(){
        assertEquals(2, biblioteca.findBookObjectPositionByName("Another awesome book", biblioteca.bookList));
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
        ArrayList<Book> books = new ArrayList<Book>(Arrays.asList(aBook, yetAnotherBook));
        biblioteca.checkoutBook("Awesome book");
        assertEquals(books, biblioteca.bookList);
    }

    @Test
    public void addsBookToCheckedOutListAftercheckoutBook(){
        ArrayList<Book> books = new ArrayList<Book>(Arrays.asList(aBook));
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
        biblioteca.checkedOutBooks.add(newBook);
        assertThat(biblioteca.returnBook("New book"),is(true));
    }

    @Test
    public void returnsFalseIfReturnBookFail(){
        assertThat(biblioteca.returnBook("Never checked out book"), is(false));
    }

    @Test
    public void addsBookToBookListAfterReturnBook(){
        ArrayList<Book> books = new ArrayList<Book>(Arrays.asList(aBook, anotherBook, yetAnotherBook, newBook));

        biblioteca.checkedOutBooks.add(newBook);

        biblioteca.returnBook("New book");

        assertEquals(books, biblioteca.bookList);
    }


    @Test
    public void printsSuccessMessageAfterSuccessfulReturnBook(){
        biblioteca.checkedOutBooks.add(newBook);
        biblioteca.returnBook("New book");

        assertEquals(Biblioteca.Messages.SUCCESSFUL_RETURN +"\n", log.getLog());
    }

    @Test
    public void removesBookFromCheckedOutListAfterReturnBook() {
        biblioteca.checkedOutBooks.add(newBook);
        biblioteca.returnBook("New book");
        ArrayList<Book> books = new ArrayList<Book>();

        assertEquals(books, biblioteca.checkedOutBooks);
    }

    @Test
    public void printsUnsuccessfulMessageAfterUnsuccessfulReturnBook() {
        biblioteca.returnBook("We dont have this book");

        assertEquals(Biblioteca.Messages.UNSUCCESSFUL_RETURN + "\n", log.getLog());

    }

    @Test
    public void findsPositionForValidMovieName(){
        assertEquals(2, biblioteca.findMovieObjectPositionByName("Just another movie", biblioteca.movieList));
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
        ArrayList<Movie> movies = new ArrayList<Movie>(Arrays.asList(aMovie, yetAnotherMovie));
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


}