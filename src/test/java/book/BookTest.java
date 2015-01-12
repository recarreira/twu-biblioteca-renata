package book;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import utils.TestUtilities;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class BookTest {

    Book aBook;

    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    @Before
    public void setUp() throws Exception {
        aBook = new Book("Same name", "Same Author", 2010);

    }

    @Test
    public void printsNameYearAndAuthorOnColumns() throws FileNotFoundException {
        String bookDetail = "Learning TDD | Cool Girl    | 2015\n";
        Book book = new Book("Learning TDD", "Cool Girl", 2015);
        book.printBookDetails(12);
        assertEquals(bookDetail, log.getLog());
    }

    @Test
    public void equalsIfTitleAuthorAndYearMatch(){
        Book anotherBook = new Book("Same name", "Same Author", 2010);

        Assert.assertEquals(true, aBook.equals(anotherBook));
    }

    @Test
    public void notEqualsIfTitleDoesNotMatch(){
        Book anotherBook = new Book("Different name", "Same Author", 2010);

        Assert.assertEquals(false, aBook.equals(anotherBook));
    }

    @Test
    public void notEqualsIAuthorDoesNotMatch(){
        Book anotherBook = new Book("Same name", "Different Author", 2010);

        Assert.assertEquals(false, aBook.equals(anotherBook));
    }

    @Test
    public void notEqualsIYearDoesNotMatch(){
        Book anotherBook = new Book("Same name", "Same Author", 2012);

        Assert.assertEquals(false, aBook.equals(anotherBook));
    }


}