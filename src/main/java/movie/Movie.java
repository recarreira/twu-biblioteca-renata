package movie;

/**
 * Created by rcarreira on 1/13/15.
 */
public class Movie {
    private String name;
    private String director;
    private int year;
    private int rate;

    public Movie(String name, String director, int year, int rate) {
        this.name = name;
        this.director = director;
        this.year = year;
        this.rate = rate;
    }

    public void printMovieDetails(int columnLength) {

        String whitespaceName = new String(new char[columnLength - this.getName().length()]).replace("\0", " ");
        String whitespaceDirector = new String(new char[columnLength - this.director.length()]).replace("\0", " ");

        String details = "";
        if (rate == 0){
            details =   this.getName() + whitespaceName +" | " + this.director + whitespaceDirector +" | " + this.year +
                        " | Unrated";
        }else {
            details = this.getName() + whitespaceName +" | " + this.director + whitespaceDirector + " | " +
                      this.year + " | " + this.rate;
        }

        System.out.println(details);

    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Movie){
            Movie c = (Movie) o;
            if (this.name.equals(c.name) && this.director.equals(c.director) && this.rate == c.rate &&
                    this.year == c.year){
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }
}
