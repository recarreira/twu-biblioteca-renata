package biblioteca;

import book.Book;
import movie.Movie;
import user.User;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
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
        public static final String USER_NOT_FOUND = "User not found.";

    }
    public ArrayList<Book> bookList = new ArrayList<Book>();
    public ArrayList<Book> checkedOutBooks = new ArrayList<Book>();

    public ArrayList<Movie> movieList = new ArrayList<Movie>();

    public Map<String, User> users = new HashMap<String, User>();

    User user;

    public boolean checkoutBook(String bookTitle) {
        int position = findBookObjectPositionByName(bookTitle, bookList);
        if (position != - 1) {

            checkedOutBooks.add(bookList.get(position));
            bookList.remove(position);

            System.out.println(Messages.CHECKOUT_BOOK);
            return true;
        }else {
            System.out.println(Messages.UNSUCCESSFUL_BOOK_CHECKOUT);
            return false;
        }
    }

    public Boolean checkoutMovie(String movieName) {
        int position = findMovieObjectPositionByName(movieName, movieList);
        if (position != - 1) {
            movieList.remove(position);

            System.out.println(Messages.CHECKOUT_MOVIE);
            return true;
        } else {
            System.out.println(Messages.UNSUCCESSFUL_MOVIE_CHECKOUT);
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


    public int findMovieObjectPositionByName(String movieName, ArrayList<Movie> listOfMovies){

        int i = 0;
        int position = -1;

        for (Movie objMovie:listOfMovies){
            if(objMovie.getName().equals(movieName)){
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

    public boolean login(String libraryNumber, String password){
        if (users.containsKey(libraryNumber)){
            if (users.get(libraryNumber).passwordMatch(password)){
                user = users.get(libraryNumber);
                return true;
            }
        }
        return false;
    }

}
