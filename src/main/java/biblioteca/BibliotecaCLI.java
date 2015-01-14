package biblioteca;

import java.util.Scanner;

/**
 * Created by rcarreira on 1/11/15.
 */
public class BibliotecaCLI {


    Biblioteca biblioteca;

    public BibliotecaCLI(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void welcomeMessage(){
        System.out.println(Biblioteca.Messages.WELCOME);
    }

    public String readString(){
        Scanner text = new Scanner(System.in);
        System.out.println("Type the title: ");
        String bookTitle = text.nextLine();
        return bookTitle;
    }

    public void menuOptions(int option) {
        switch (option) {
            case 1:
                biblioteca.printBooKListDetails();
                break;
            case 2:
                biblioteca.checkoutBook(readString());
                break;
            case 3:
                biblioteca.returnBook(readString());
                break;
            case 4:
                biblioteca.printMovieListDetail();
                break;
            case 5:
                biblioteca.checkoutMovie(readString());
                break;
            case 0:
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
                System.out.println(Biblioteca.Messages.INVALID_OPTION);
                menu();
            }

        }
    }
}
