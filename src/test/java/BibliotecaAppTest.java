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
    }

    @Test
    public void UserShouldSeeAWelcomeMessage() {
        biblioteca.welcomeMessage();

        assertEquals("Welcome!", log.getLog());
    }

    @Test
    public void booksShouldBePrintedOnTheScreen() throws FileNotFoundException {
        String listOfBooks = testUtilities.txtFileContentToString("src/test/data/bookList.txt");
        biblioteca.printBookList();

        assertEquals(listOfBooks, log.getLog());
    }

    public void bookListDetailsShouldPrintedAsColumns() throws FileNotFoundException {
        String bookDetails = testUtilities.txtFileContentToString("src/test/data/booksListWithDetails.txt");

        biblioteca.printBooKDetailsAsColumn();

        assertEquals(bookDetails, log.getLog());
    }


}