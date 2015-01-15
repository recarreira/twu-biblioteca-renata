package movie;

/**
 * Created by rcarreira on 1/13/15.
 */
public class Movie {
    private String name;
    private String director;
    private int year;
    private int rate;

    public String getDirector() {
        return director;
    }

    public int getYear() {
        return year;
    }

    public int getRate() {
        return rate;
    }

    public Movie(String name, String director, int year, int rate) {
        this.name = name;
        this.director = director;
        this.year = year;

        this.rate = rate;
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
