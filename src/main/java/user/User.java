package user;

/**
 * Created by rcarreira on 1/14/15.
 */
public class User {

    String libraryNumber;
    String password;

    public User(String libraryNumber, String password) {
        this.libraryNumber = libraryNumber;
        this.password = password;
    }

    public Boolean login(String libraryNumber, String password) {

        if (this.libraryNumber.equals(libraryNumber) && this.password.equals(password)){
            return true;
        }

        return false;
    }

}
