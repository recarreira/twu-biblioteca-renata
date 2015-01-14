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

    @Test
    public void printsBookDetails(){
        Movie movie = new Movie("Some movie", "Some Pretty Director", 2012, 10);
        String movieDetails = "Some movie           | Some Pretty Director | 2012 | 10\n";
        movie.printMovieDetails(20);
        assertEquals(movieDetails, log.getLog());
    }

    @Test
    public void printsBookListDetails(){
        String movieDetails =   "Some movie           | Some Pretty Director | 2012 | 10\n" +
                                "Another movie        | Just a Director      | 2001 | Unrated\n" +
                                "Just another movie   | Unknown Director     | 2009 | 3\n";
        biblioteca.printMovieListDetail();
        assertEquals(movieDetails, log.getLog());
    }

}