package biblioteca;

import book.Book;
import movie.Movie;
import user.User;

import java.util.Scanner;

/**
 * Created by rcarreira on 1/11/15.
 */
public class BibliotecaCLI {

    Biblioteca biblioteca;
    User user;


    public BibliotecaCLI(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void welcomeMessage(){
        System.out.println(Biblioteca.Messages.WELCOME);
    }

    public String readString(String message){
        Scanner text = new Scanner(System.in);
        System.out.println(message);
        return text.nextLine();
    }

    public void menuOptions(int option) {
        switch (option) {
            case 1:
                printBooKList();
                break;
            case 2:
                System.out.println(Biblioteca.Messages.LOGIN_TO_PROCEED);
                biblioteca.checkoutBook(readString("Type book title: "));
                break;
            case 3:
                //userLogin(Biblioteca.Messages.LOGIN_TO_PROCEED);
                biblioteca.returnBook(readString("Type book title: "));
                break;
            case 4:
                printMovieList();
                break;
            case 5:
                biblioteca.checkoutMovie(readString("Type movie name: "));
                break;
            case 6:
                //userLogin("");
                break;
            case 7:
                //userLogin(Biblioteca.Messages.LOGIN_TO_PROCEED);
                if(user.isLogged()){
                    user.userInformation();
                }
                break;
            case 0:
                user.setLogged(false);
                System.exit(1);
                break;
            default:
                System.out.println(Biblioteca.Messages.INVALID_OPTION);
        }
    }

    public void printMenu(){
        System.out.println("Choose an option:");
        System.out.println("1 - Books List");
        System.out.println("2 - Checkout Book");
        System.out.println("3 - Return Book");
        System.out.println("4 - Movies List");
        System.out.println("5 - Checkout Movie");
        System.out.println("6 - Login");
        System.out.println("7 - User Information");
        System.out.println("0 - Quit");
    }

    public void menu(){
        Scanner in = new Scanner(System.in);
        int option = 0;
        printMenu();

        while (true){
            try {
                option = in.nextInt();
                menuOptions(option);
                System.out.println();
                printMenu();
            }catch (Exception e){
                System.out.println("Message: " + e.getMessage());
                System.out.println("Class: " + e.getClass());
                System.out.println(Biblioteca.Messages.INVALID_OPTION);
                menu();
            }

        }
    }

    public void printBooKList() {
        System.out.printf("%-30s | %-30s | %-5s%n", "TITLE", "AUTHOR", "YEAR");
        for (Book objBook : biblioteca.bookList){
            System.out.printf("%-30s | %-30s | %d%n", objBook.getTitle(), objBook.getAuthor(), objBook.getYear());
        }
    }

    public void printMovieList() {
        System.out.printf("%-30s | %-30s | %-4s | %s%n", "NAME", "DIRECTOR", "YEAR", "RATE");
        for (Movie objMovie : biblioteca.movieList){
            if (objMovie.getRate() == 0){
                System.out.printf("%-30s | %-30s | %d | %s%n", objMovie.getName(), objMovie.getDirector(),
                        objMovie.getYear(), "Unrated");
            } else {
                System.out.printf("%-30s | %-30s | %d | %d%n", objMovie.getName(), objMovie.getDirector(),
                        objMovie.getYear(), objMovie.getRate());
            }
        }
    }

    public  void userLogin(String message) {
        System.out.println(message);
        String libraryNumber = readString("Library number: ");
        String password = readString("Password: ");

        if (biblioteca.login(libraryNumber, password)){
            System.out.println(Biblioteca.Messages.SUCCESSFUL_LOGIN);
        }else{
            System.out.println(Biblioteca.Messages.UNSUCCESSFUL_LOGIN);
        }
    }

}
