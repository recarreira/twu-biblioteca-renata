package book;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import utils.TestUtilities;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class BookTest {

    TestUtilities testUtilities;

    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    @Before
    public void setUp() throws Exception {
        testUtilities = new TestUtilities();
    }

    @Test
    public void printBookDetailsShouldPrintNameYearAndAuthorOnColumns() throws FileNotFoundException {
        String bookDetail = testUtilities.txtFileContentToString("src/test/data/bookDetails.txt");
        Book book = new Book("Learning TDD", "Cool Girl", 2015);
        book.printBookDetails();
        assertEquals(bookDetail, log.getLog());
    }


}