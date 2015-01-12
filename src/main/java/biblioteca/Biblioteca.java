package biblioteca;

import book.Book;

import java.util.ArrayList;

/**
 * Created by rcarreira on 1/11/15.
 */
public class Biblioteca {
    public static class Messages {
        public static final String WELCOME = "Welcome!";
        public static final String UNSUCCESSFUL_CHECKOUT = "That book is not available";
        public static final String INVALID_OPTION = "Invalid Option!";
        public static final String SUCCESSFUL_RETURN = "Thank you for returning the book";
        public static final String SUCCESSFUL_CHECKOUT = "Thank you! Enjoy the book";
        public static final String UNSUCCESSFUL_RETURN = "That is not a valid book to return";

    }
    public ArrayList<Book> bookList = new ArrayList<Book>();
    public ArrayList<Book> checkedOutBooks = new ArrayList<Book>();

    public void printBooKListDetails() {
        int length = bookListMaxLengthString(bookList);
        for (Book objBook : bookList){
            objBook.printBookDetails(length);
        }
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

    public boolean checkout(String bookTitle) {
        int position = findBookObjectPositionByName(bookTitle, bookList);
        if (position != - 1) {

            checkedOutBooks.add(bookList.get(position));
            bookList.remove(position);

            System.out.println(Messages.SUCCESSFUL_CHECKOUT);
            return true;
        }else {
            System.out.println(Messages.UNSUCCESSFUL_CHECKOUT);
            return false;
        }
    }

    public int findBookObjectPositionByName(String bookTitle, ArrayList<Book> listOfBooks){
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
        int position = findBookObjectPositionByName(bookTitle, checkedOutBooks);
        if (position != - 1) {

            bookList.add(checkedOutBooks.get(position));
            checkedOutBooks.remove(position);

            System.out.println(Messages.SUCCESSFUL_RETURN);
            return true;
        }else {
            System.out.println(Messages.UNSUCCESSFUL_RETURN);
            return false;
        }
    }

}
