import book.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import utils.TestUtilities;
import java.io.FileNotFoundException;


import static org.junit.Assert.*;

public class BibliotecaAppTest {

    BibliotecaApp biblioteca;
    TestUtilities testUtilities;

    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    @Before
    public void setUp() throws Exception {
        biblioteca = new BibliotecaApp();
        testUtilities = new TestUtilities();
        biblioteca.createDefaultBookObjects();
    }

    @Test
    public void UserShouldSeeAWelcomeMessage() {
        biblioteca.welcomeMessage();

        assertEquals("Welcome!\n", log.getLog());
    }

    @Test
    public void bookListDetailsShouldPrintedAsColumns() throws FileNotFoundException {
        String bookDetails = testUtilities.txtFileContentToString("src/test/data/bookListWithDetails.txt");

        biblioteca.printBooKListDetails();

        assertEquals(bookDetails, log.getLog());
    }

    @Test
    public void bookListDetailsShouldBePrintedByChoose1OnMenu() throws FileNotFoundException {
        log.clear();
        String bookDetails = testUtilities.txtFileContentToString("src/test/data/bookListWithDetails.txt");
        biblioteca.menuOptions(1);

        assertEquals(bookDetails, log.getLog());
    }

    @Test
    public void anErrorShouldBeDisplayedForInvalidOptionOnMenu(){
        biblioteca.menuOptions(10);

        assertEquals("Invalid Option!\n", log.getLog());
    }

    @Test
    public void menuShouldBePrinted() throws FileNotFoundException {
        String menu = testUtilities.txtFileContentToString("src/test/data/menu.txt");
        biblioteca.printMenu();

        assertEquals(menu, log.getLog());
    }

    @Test
    public void bibliotecaShouldFindThePositionForAValidBookName(){
        assertEquals(2, biblioteca.findObjectPosition("Another awesome book", biblioteca.bookList));
    }

    @Test
    public  void bookShouldBeRemovedFromBookListAfterCheckout() {
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
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.checkedOutBooks.add(book);

        assertEquals(true, bibliotecaApp.returnBook("Checked out book"));
    }

    @Test
    public void successMessageShouldBePrintedAfterSuccessfulReturnBook(){
        Book book = new Book("Checked out book", "someone", 2010);
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.checkedOutBooks.add(book);
        bibliotecaApp.returnBook("Checked out book");

        assertEquals("Thank you for returning the book\n", log.getLog());
    }

    @Test
    public void unccessfulMessageShouldBePrintedAfterAnUnsuccessfulReturn(){
        biblioteca.returnBook("We dont have this book");

        assertEquals("That is not a valid book to return\n", log.getLog());
    }
}