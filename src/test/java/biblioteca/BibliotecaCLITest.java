package biblioteca;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import utils.BibliotecaUtils;
import utils.TestUtilities;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class BibliotecaCLITest {

    Biblioteca biblioteca;
    BibliotecaCLI bibliotecaCLI;
    TestUtilities testUtilities;

    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    @Before
    public void setUp() throws Exception {
        biblioteca = new Biblioteca();
        bibliotecaCLI = new BibliotecaCLI(biblioteca);
        testUtilities = new TestUtilities();
        BibliotecaUtils.createDefaultBookObjects(biblioteca);
    }

    @Test
    public void UserShouldSeeAWelcomeMessage() {
        bibliotecaCLI.welcomeMessage();

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
        String bookDetails = TestUtilities.txtFileContentToString("src/test/data/bookListWithDetails.txt");
        bibliotecaCLI.menuOptions(1);

        assertEquals(bookDetails, log.getLog());
    }

    @Test
    public void anErrorShouldBeDisplayedForInvalidOptionOnMenu(){
        bibliotecaCLI.menuOptions(10);

        assertEquals("Invalid Option!\n", log.getLog());
    }

    @Test
    public void menuShouldBePrinted() throws FileNotFoundException {
        String menu = TestUtilities.txtFileContentToString("src/test/data/menu.txt");
        bibliotecaCLI.printMenu();

        assertEquals(menu, log.getLog());
    }

}