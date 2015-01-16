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
    private boolean logged = false;

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

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public boolean isLogged() {
        return logged;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof User){
            User c = (User) o;
            if (this.name.equals(c.name) && this.password.equals(c.password) && this.email.equals(c.email) &&
                    this.phone.equals(c.phone) && this.libraryNumber.equals(libraryNumber)){
                return true;
            }
        }
        return false;
    }
}
