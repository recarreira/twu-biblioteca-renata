package biblioteca;

import book.Book;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import utils.TestUtilities;

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


    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    @Before
    public void setUp() throws Exception {
        biblioteca = new Biblioteca();
        bibliotecaCLI = new BibliotecaCLI(biblioteca);
        TestUtilities.populateBiblioteca(biblioteca);

    }

    @Test
    public void findsPositionForValidBookName(){
        assertEquals(2, biblioteca.findBookObjectPositionByName("Another awesome book", biblioteca.bookList));
    }

    @Test
    public void returnsTrueForSuccessfulCheckout() {
        assertThat(biblioteca.checkout("Another awesome book"), is(true));

    }

    @Test
    public void returnsFalseForUnsuccessfulCheckout(){
        assertThat(biblioteca.checkout("Unavailable book"), is(false));
    }

    @Test
    public void printsSuccessMessageAfterSuccessfulCheckout(){
        biblioteca.checkout("Another awesome book");

        assertEquals(Biblioteca.Messages.SUCCESSFUL_CHECKOUT + "\n", log.getLog());
    }

    @Test
    public void removesBookFromBookListAfterCheckout(){
        ArrayList<Book> books = new ArrayList<Book>(Arrays.asList(aBook, yetAnotherBook));
        biblioteca.checkout("Awesome book");
        assertEquals(books, biblioteca.bookList);
    }

    @Test
    public void addsBookToCheckedOutListAftercheckout(){
        ArrayList<Book> books = new ArrayList<Book>(Arrays.asList(aBook));
        biblioteca.checkout("Learning TDD");

        assertEquals(books, biblioteca.checkedOutBooks);
    }

    @Test
    public void printsUnsuccessfulMessageAfterUnsuccessfulCheckout(){
        biblioteca.checkout("We don't have this book");

        assertEquals(Biblioteca.Messages.UNSUCCESSFUL_CHECKOUT + "\n", log.getLog());
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
    public void printsUnsuccessfulMessageAfterUnsuccessfulReturn() {
        biblioteca.returnBook("We dont have this book");

        assertEquals(Biblioteca.Messages.UNSUCCESSFUL_RETURN + "\n", log.getLog());

    }
}