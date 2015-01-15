package biblioteca;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import user.User;

import static org.junit.Assert.assertEquals;

public class BibliotecaCLITest {

    Biblioteca biblioteca;
    BibliotecaCLI bibliotecaCLI;
    String bookDetails;
    String movieDetails;

    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    @Before
    public void setUp() throws Exception {
        biblioteca = new Biblioteca();
        BibliotecaData.populateWithBooks(biblioteca);
        BibliotecaData.populateWithMovies(biblioteca);
        bibliotecaCLI = new BibliotecaCLI(biblioteca);
        bookDetails =   "TITLE                          | AUTHOR                         | YEAR \n" +
                        "Learning TDD                   | Cool Girl                      | 2015\n" +
                        "Awesome book                   | author with huge name          | 2014\n" +
                        "Another awesome book           | myself                         | 2013\n";
        movieDetails =   "NAME                           | DIRECTOR                       | YEAR | RATE\n" +
                "Some movie                     | Some Pretty Director           | 2012 | 10\n" +
                "Another movie                  | Just a Director                | 2001 | Unrated\n" +
                "Just another movie             | Unknown Director               | 2009 | 3\n";
    }

    @Test
    public void printsWelcomeMessage() {
        bibliotecaCLI.welcomeMessage();

        assertEquals(Biblioteca.Messages.WELCOME + "\n", log.getLog());
    }

    @Test
    public void printsBookListDetailsAsColumns(){
        bibliotecaCLI.printBooKList();

        assertEquals(bookDetails, log.getLog());
    }

    @Test
    public void printsBookListByChoosingMenuOption1(){
        bibliotecaCLI.menuOptions(1);

        assertEquals(bookDetails, log.getLog());
    }

    @Test
    public void printsErrorForInvalidMenuOption(){
        bibliotecaCLI.menuOptions(10);

        assertEquals(Biblioteca.Messages.INVALID_OPTION + "\n", log.getLog());
    }

    @Test
    public void printsMenu(){
        String menu =   "Choose an option:\n" +
                        "1 - Books List\n" +
                        "2 - Checkout Book\n" +
                        "3 - Return Book\n" +
                        "4 - Movies List\n" +
                        "5 - Checkout Movie\n" +
                        "6 - Login\n" +
                        "7 - User Information\n" +
                        "0 - Quit\n";
        bibliotecaCLI.printMenu();

        assertEquals(menu, log.getLog());
    }


    @Test
    public void printsMovieList(){

        bibliotecaCLI.printMovieList();
        assertEquals(movieDetails, log.getLog());
    }


    @Test
    public void printsMovieListByChoosingMenuOption4(){
        bibliotecaCLI.menuOptions(4);
        assertEquals(movieDetails, log.getLog());
    }

}