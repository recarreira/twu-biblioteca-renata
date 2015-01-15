package movie;

import biblioteca.Biblioteca;
import biblioteca.BibliotecaData;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;

import static org.junit.Assert.*;

public class MovieTest {

    Biblioteca biblioteca;

    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    @Before
    public void setUp() throws Exception {

        biblioteca = new Biblioteca();
        BibliotecaData.populateWithMovies(biblioteca);
    }

}