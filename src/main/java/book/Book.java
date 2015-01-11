package book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by rcarreira on 1/9/15.
 */
public class Book {

    private String title;
    private String author;
    private int year;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public void printBookDetails(int lengthColumn) {

        String whitespaceTitle = new String(new char[lengthColumn - this.title.length()]).replace("\0", " ");
        String whitespaceAuthor = new String(new char[lengthColumn - this.author.length()]).replace("\0", " ");

        System.out.println(this.title + whitespaceTitle +" | " + this.author + whitespaceAuthor + " | " + this.year);
    }


}
