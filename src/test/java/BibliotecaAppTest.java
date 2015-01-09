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
    Book book1, book2, book3;

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


}