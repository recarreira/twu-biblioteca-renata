import biblioteca.Biblioteca;
import biblioteca.BibliotecaCLI;
import book.Book;
import utils.BibliotecaUtils;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by rcarreira on 1/8/15.
 */
public class BibliotecaApp {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        BibliotecaUtils.createDefaultBookObjects(biblioteca);

        BibliotecaCLI bibliotecaCLI = new BibliotecaCLI(biblioteca);
        bibliotecaCLI.welcomeMessage();
        bibliotecaCLI.menu();
    }

}