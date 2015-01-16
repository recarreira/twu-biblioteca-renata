package biblioteca;

import book.Book;
import movie.Movie;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import user.User;

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

    User user;
    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    @Before
    public void setUp() throws Exception {
        biblioteca = new Biblioteca();
        bibliotecaCLI = new BibliotecaCLI(biblioteca);
        BibliotecaData.populateWithBooks(biblioteca);
        BibliotecaData.populateWithMovies(biblioteca);
        user = new User("000-0001", "1234", "Somebody With a Name", "somebody@someemail.com", "8888-8888");

    }

    @Test
    public void returnsTrueForSuccessfulCheckoutBook() {
        assertThat(biblioteca.checkoutBook("Another awesome book", user), is(true));
    }

    @Test
    public void returnsFalseForUnsuccessfulCheckoutBook(){
        assertThat(biblioteca.checkoutBook("Unavailable book", user), is(false));
    }

    @Test
    public void printsSuccessMessageAfterSuccessfulBookCheckout(){
        biblioteca.checkoutBook("Another awesome book", user);
        assertEquals(Biblioteca.Messages.CHECKOUT_BOOK + "\n", log.getLog());
    }

    @Test
    public void removesBookFromBookListAfterCheckoutBook(){
        Map<String, Book> books = new HashMap<String, Book>();
        books.put(aBook.getTitle(), aBook);
        books.put(yetAnotherBook.getTitle(), yetAnotherBook);
        biblioteca.checkoutBook("Awesome book", user);
        assertEquals(books, biblioteca.bookList);
    }

    @Test
    public void addsBookToCheckedOutListAftercheckoutBook(){
        Map<String, Book> books = new HashMap<String, Book>();
        books.put(aBook.getTitle(), aBook);
        biblioteca.checkoutBook("Learning TDD", user);
        assertEquals(books, biblioteca.checkedOutBooks);
    }

    @Test
    public void printsUnsuccessfulMessageAfterUnsuccessfulCheckoutBook(){
        biblioteca.checkoutBook("We don't have this book", user);
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
        assertThat(biblioteca.checkoutMovie("Another movie", user), is(true));
    }

    @Test
    public void returnsFalseIfCheckoutMovieFail(){
        assertThat(biblioteca.checkoutMovie("Not a movie", user), is(false));
    }

    @Test
    public void removesMovieFromMovieListAfterCheckoutMovie(){
        Map<String, Movie> movies = new HashMap<String, Movie>();
        movies.put(aMovie.getName(), aMovie);
        movies.put(yetAnotherMovie.getName(), yetAnotherMovie);

        biblioteca.checkoutMovie("Another movie", user);
        assertEquals(movies, biblioteca.movieList);
    }

    @Test
    public void printsSuccessMessageAfterSuccessfulMovieCheckout(){
        biblioteca.checkoutMovie("Just another movie", user);

        assertEquals(Biblioteca.Messages.CHECKOUT_MOVIE + "\n", log.getLog());
    }

    @Test
    public void printsUnsuccessfulMessageAfterUnsuccessfulCheckoutMovie(){
        biblioteca.checkoutMovie("Not a movie", user);

        assertEquals(Biblioteca.Messages.UNSUCCESSFUL_MOVIE_CHECKOUT + "\n", log.getLog());
    }

    @Test
    public void loginSuccessful(){
        BibliotecaData.createUsers(biblioteca);
        assertEquals(user, biblioteca.login("000-0001", "1234"));
    }

    @Test
    public void wrongPasswordLogin(){
        assertNull(biblioteca.login("000-0001", "wrong password"));
    }

    @Test
    public void wrongUserLogin(){
        assertNull(biblioteca.login("000-000x", "password"));
    }

    @Test
    public void movieCheckedOutByListPopulatedWithUserAndBookMovieForSuccessfulCheckout(){
        biblioteca.checkoutMovie("Some movie", user);
        Map<String, String> testMovieCheckedOutBy = new HashMap<String, String>();
        testMovieCheckedOutBy.put("Some movie", user.getLibraryNumber());
        assertEquals(testMovieCheckedOutBy, biblioteca.movieCheckedOutBy);
    }

    @Test
    public void movieCheckedOutByListNotPopulatedWithUserAndMovieNameForUnsuccessfulCheckout(){
        biblioteca.checkoutMovie("Unavailable movie", user);
        Map<String, String> testMovieCheckedOutBy = new HashMap<String, String>();
        assertEquals(testMovieCheckedOutBy, biblioteca.movieCheckedOutBy);
    }

    @Test
    public void bookCheckedOutByListPopulatedWithUserAndBookNameForSuccessfulCheckout(){
        biblioteca.checkoutBook("Awesome book", user);
        Map<String, String> checkedOutBooks = new HashMap<String, String>();
        checkedOutBooks.put("Awesome book", user.getLibraryNumber());
        assertEquals(checkedOutBooks, biblioteca.bookCheckedOutBy);
    }

    @Test
    public void bookCheckedOutByListNotPopulatedWithUserAndBookNameForUnsuccessfulCheckout(){
        biblioteca.checkoutBook("Unavailable book", user);
        Map<String, String> checkedOutBooks = new HashMap<String, String>();
        assertEquals(checkedOutBooks, biblioteca.bookCheckedOutBy);
    }



}