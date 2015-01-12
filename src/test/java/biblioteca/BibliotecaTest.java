package biblioteca;

import book.Book;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import utils.BibliotecaUtils;
import utils.TestUtilities;

import static org.junit.Assert.*;

public class BibliotecaTest {

    BibliotecaCLI bibliotecaCLI;
    Biblioteca biblioteca;

    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    @Before
    public void setUp() throws Exception {
        biblioteca = new Biblioteca();
        bibliotecaCLI = new BibliotecaCLI(biblioteca);
        BibliotecaUtils.createDefaultBookObjects(biblioteca);
    }

    @Test
    public void bibliotecaShouldFindThePositionForAValidBookName(){
        assertEquals(2, biblioteca.findBookObjectPositionByName("Another awesome book", biblioteca.bookList));
    }

    @Test
    public void bookShouldBeRemovedFromBookListAfterCheckout() {
        assertEquals(true, biblioteca.checkout("Another awesome book"));
    }

    @Test
    public void successMessageShouldBePrintedAfterSuccessfulCheckout(){
        biblioteca.checkout("Another awesome book");

        assertEquals("Thank you! Enjoy the book\n", log.getLog());
    }

    @Test
    public void unccessfulMessageShouldBePrintedAfterAnUnsuccessfulCheckout(){
        biblioteca.checkout("We dont have this book");

        assertEquals("That book is not available\n", log.getLog());
    }

    @Test
    public void bookShouldBeAddedToBookListAfterReturnBook(){
        Book book = new Book("Checked out book", "someone", 2010);
        biblioteca.checkedOutBooks.add(book);

        assertEquals(true, biblioteca.returnBook("Checked out book"));
    }

    @Test
    public void successMessageShouldBePrintedAfterSuccessfulReturnBook(){
        Book book = new Book("Checked out book", "someone", 2010);
        biblioteca.checkedOutBooks.add(book);
        biblioteca.returnBook("Checked out book");

        assertEquals("Thank you for returning the book\n", log.getLog());
    }

    @Test
    public void unccessfulMessageShouldBePrintedAfterAnUnsuccessfulReturn() {
        biblioteca.returnBook("We dont have this book");

        assertEquals("That is not a valid book to return\n", log.getLog());

    }
}