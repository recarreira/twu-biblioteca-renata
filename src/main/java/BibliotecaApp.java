import book.Book;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by rcarreira on 1/8/15.
 */
public class BibliotecaApp {



    public static ArrayList<Book> bookList = new ArrayList<Book>();
    public static ArrayList<Book> checkedOutBooks = new ArrayList<Book>();


    public static void main(String[] args) {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.createDefaultBookObjects();
        bibliotecaApp.welcomeMessage();
        bibliotecaApp.menu();
    }

    public void welcomeMessage(){
        System.out.println("Welcome!");
    }

    public void printBooKListDetails() {
        int length = bookListMaxLengthString(bookList);
        for (Book objBook : bookList){
            objBook.printBookDetails(length);
        }
    }

    public void createDefaultBookObjects(){
        bookList.add(new Book("Learning TDD", "Cool Girl", 2015));
        bookList.add(new Book("Awesome book", "author with huge name", 2014));
        bookList.add(new Book("Another awesome book", "myself", 2013));
    }

    public int bookListMaxLengthString(ArrayList<Book> arrayList){
        int max = 0;
        for(Book objBook:arrayList){
            if (objBook.getTitle().length() > max ){
                max = objBook.getTitle().length();
            }

            if (objBook.getAuthor().length() > max){
                max = objBook.getAuthor().length();
            }
        }
        return max;
    }


    public String readBookName(){
        Scanner text = new Scanner(System.in);
        System.out.println("Type book title: ");
        String bookTitle= text.nextLine();
        return bookTitle;
    }

    public void menuOptions(int option) {
        switch (option) {
            case 1:
                printBooKListDetails();
                break;
            case 2:
                checkout(readBookName());
                break;
            case 3:
                returnBook(readBookName());
                break;
            case 0:
                System.exit(1);
                break;
            default:
                System.out.println("Invalid Option!");
        }
    }

    public void printMenu(){
        System.out.println("Choose an option:");
        System.out.println("1 - Books List");
        System.out.println("2 - Checkout Book");
        System.out.println("3 - Return Book");
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
                System.out.println("Invalid Option!");
                menu();
            }

         }
    }

    public boolean checkout(String bookTitle) {
        int position = findObjectPosition(bookTitle, bookList);
        if (position != - 1) {

            checkedOutBooks.add(bookList.get(position));
            bookList.remove(position);

            System.out.println("Thank you! Enjoy the book");
            return true;
        }else {
            System.out.println("That book is not available");
            return false;
        }
    }

    public int findObjectPosition(String bookTitle, ArrayList<Book> listOfBooks){
        int i = 0;
        int position = -1;

        for (Book objBook:listOfBooks){
            if(objBook.getTitle().equals(bookTitle)){
                position = i;
                break;
            }
            i++;
        }
        return position;
    }

    public boolean returnBook(String bookTitle) {
        int position = findObjectPosition(bookTitle, checkedOutBooks);
        if (position != - 1) {

            bookList.add(checkedOutBooks.get(position));
            checkedOutBooks.remove(position);

            System.out.println("Thank you for returning the book");
            return true;
        }else {
            System.out.println("That is not a valid book to return");
            return false;
        }
    }
}