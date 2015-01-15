package user;

/**
 * Created by rcarreira on 1/14/15.
 */
public class User {



    String libraryNumber;
    String password;
    String name;
    String email;
    String phone;
    boolean logged = false;

    public User(String libraryNumber, String password, String name, String email, String phone) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Boolean passwordMatch(String password) {
        if (this.password.equals(password)){
            return true;
        }
        return false;
    }

    public void userInformation(){
        System.out.println(this.toString());
    }

    @Override
    public String toString(){
        return  "Name:         " + this.name +  "\n" +
                "Email:        " + this.email + "\n" +
                "Phone number: " + this.phone;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public boolean isLogged() {
        return logged;
    }
}
