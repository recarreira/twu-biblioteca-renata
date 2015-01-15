package user;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;

import static org.junit.Assert.*;

public class UserTest {
    User user;
    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();


    @Before
    public void setUp() throws Exception {
        user = new User ("000-0001", "1234", "Somebody With a Name", "somebody@someemail.com", "8888-8888");
    }

    @Test
    public void successfulPasswordMatch(){
        assertTrue(user.passwordMatch("1234"));
    }

    @Test
    public void unsuccessfulPasswordMatch(){
        assertFalse(user.passwordMatch("wrong_password"));
    }

    @Test
    public void userInformation(){
        String userInformation =    "Name:         Somebody With a Name\n" +
                                    "Email:        somebody@someemail.com\n" +
                                    "Phone number: 8888-8888";
        assertEquals(userInformation, user.toString());
    }

    @Test
    public void printsUserInformation() {
        String userInformation =    "Name:         Somebody With a Name\n" +
                                    "Email:        somebody@someemail.com\n" +
                                    "Phone number: 8888-8888\n";
        user.userInformation();
        assertEquals(userInformation, log.getLog());
    }

}