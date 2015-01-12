package biblioteca;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import utils.TestUtilities;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class BibliotecaCLITest {

    Biblioteca biblioteca;
    BibliotecaCLI bibliotecaCLI;
    String bookDetails;

    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    @Before
    public void setUp() throws Exception {
        biblioteca = new Biblioteca();
        TestUtilities.populateBiblioteca(biblioteca);
        bibliotecaCLI = new BibliotecaCLI(biblioteca);
        bookDetails =   "Learning TDD          | Cool Girl             | 2015\n" +
                        "Awesome book          | author with huge name | 2014\n" +
                        "Another awesome book  | myself                | 2013\n";
    }

    @Test
    public void printsWelcomeMessage() {
        bibliotecaCLI.welcomeMessage();

        assertEquals(Biblioteca.Messages.WELCOME + "\n", log.getLog());
    }

    @Test
    public void printsBookListDetailsAsColumns(){
        biblioteca.printBooKListDetails();

        assertEquals(bookDetails, log.getLog());
    }

    @Test
    public void printsBookListDetailsByChoosingMenuOption1(){
        bibliotecaCLI.menuOptions(1);

        assertEquals(bookDetails, log.getLog());
    }

    @Test
    public void printsErrorForInvalidMenuOption(){
        bibliotecaCLI.menuOptions(10);

        assertEquals(Biblioteca.Messages.INVALID_OPTION + "\n", log.getLog());
    }

    @Test
    public void printsMenu() throws FileNotFoundException {
        String menu =   "Choose an option:\n" +
                        "1 - Books List\n" +
                        "2 - Checkout Book\n" +
                        "3 - Return Book\n" +
                        "0 - Quit\n";
        bibliotecaCLI.printMenu();

        assertEquals(menu, log.getLog());
    }

}