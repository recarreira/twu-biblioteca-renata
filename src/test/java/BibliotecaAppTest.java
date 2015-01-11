import book.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import utils.TestUtilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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
        assertEquals(0, biblioteca.findObjectPosition("Learning TDD"));
    }

    @Test
    public void bookShouldBeRemovedFromBookListAfterCheckout() {
        ArrayList<Book> bookListClone = biblioteca.bookList;
        bookListClone.remove(2);
        biblioteca.checkout("Another awesome book");
        assertEquals(bookListClone, biblioteca.bookList);
    }
}