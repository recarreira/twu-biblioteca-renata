import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class BibliotecaAppTest {

    BibliotecaApp biblioteca;

    private String txtFileContentToString(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(path));
        String str = "";
        while (scanner.hasNext()){
            str += scanner.next() + "\n";
        }
        return str;
    }

    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    @Before
    public void setUp() throws Exception {
        biblioteca = new BibliotecaApp();
    }

    @Test
    public void UserShouldSeeAWelcomeMessage() {
        biblioteca.welcomeMessage();
        assertEquals("Welcome!", log.getLog());
    }

    @Test
    public void booksShouldBePrintedOnTheScreen() throws FileNotFoundException {
        String listOfBooks = txtFileContentToString("src/test/data/bookList.txt");
        biblioteca.printBookList();
        assertEquals(listOfBooks, log.getLog());

    }
}