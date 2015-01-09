package book;

/**
 * Created by rcarreira on 1/9/15.
 */
public class Book {

    public String title;
    public String author;
    public int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public void printBookDetails() {
        System.out.println(this.title + " | " + this.author + " | " + this.year);
    }
}
