package biblioteca;

import book.Book;
import movie.Movie;
import user.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rcarreira on 1/11/15.
 */
public class Biblioteca {

    public static class Messages {
        public static final String WELCOME = "Welcome!";
        public static final String INVALID_OPTION = "Invalid Option!";
        public static final String CHECKOUT_BOOK = "Thank you! Enjoy the book";
        public static final String UNSUCCESSFUL_BOOK_CHECKOUT = "That book is not available";
        public static final String SUCCESSFUL_RETURN = "Thank you for returning the book";
        public static final String UNSUCCESSFUL_RETURN = "That is not a valid book to return";
        public static final String CHECKOUT_MOVIE = "Thank you! Enjoy the movie";
        public static final String UNSUCCESSFUL_MOVIE_CHECKOUT = "That movie is not available";
        public static final String SUCCESSFUL_LOGIN ="Login successful";
        public static final String UNSUCCESSFUL_LOGIN = "Incorrect library number or password.";
        public static final String LOGIN_TO_PROCEED = "You must login to proceed";
    }
    public Map<String, Book> bookList = new HashMap<String, Book>();
    public Map<String, Book> checkedOutBooks = new HashMap<String, Book>();

    public Map<String, Movie> movieList = new HashMap<String, Movie>();

    public Map<String, User> users = new HashMap<String, User>();

    public Map<String, String> bookCheckedOutBy = new HashMap<String, String>();
    public Map<String, String> movieCheckedOutBy = new HashMap<String, String>();

    User user;

    public boolean checkoutBook(String bookTitle) {
        if (bookList.containsKey(bookTitle)) {

            checkedOutBooks.put(bookTitle, bookList.get(bookTitle));
            bookList.remove(bookTitle);
            bookCheckedOutBy.put(bookTitle, user.getLibraryNumber());

            System.out.println(Messages.CHECKOUT_BOOK);
            return true;
        }else {
            System.out.println(Messages.UNSUCCESSFUL_BOOK_CHECKOUT);
            return false;
        }
    }

    public Boolean checkoutMovie(String movieName) {
        if (movieList.containsKey(movieName)) {
            movieList.remove(movieName);
            movieCheckedOutBy.put(movieName, user.getLibraryNumber());
            System.out.println(Messages.CHECKOUT_MOVIE);
            return true;
        } else {
            System.out.println(Messages.UNSUCCESSFUL_MOVIE_CHECKOUT);
            return false;
        }
    }


    public boolean returnBook(String bookTitle) {
        if (checkedOutBooks.containsKey(bookTitle)) {

            bookList.put(bookTitle, checkedOutBooks.get(bookTitle));
            checkedOutBooks.remove(bookTitle);

            System.out.println(Messages.SUCCESSFUL_RETURN);
            return true;
        }else {
            System.out.println(Messages.UNSUCCESSFUL_RETURN);
            return false;
        }
    }

    public boolean login(String libraryNumber, String password){
            if (users.containsKey(libraryNumber)){if (users.get(libraryNumber).passwordMatch(password)){
                user = users.get(libraryNumber);
                return true;
            }
        }
        return false;
    }
}
