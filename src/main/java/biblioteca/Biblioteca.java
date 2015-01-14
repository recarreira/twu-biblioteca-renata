package biblioteca;

import book.Book;
import movie.Movie;

import java.util.ArrayList;

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


    }
    public ArrayList<Book> bookList = new ArrayList<Book>();
    public ArrayList<Book> checkedOutBooks = new ArrayList<Book>();

    public ArrayList<Movie> movieList = new ArrayList<Movie>();

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

    public void printMovieListDetail(){
        for (Movie objMovie:movieList){
            objMovie.printMovieDetails(20);
        }
    }

}
