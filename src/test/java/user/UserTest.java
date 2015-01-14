package user;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    User user;

    @Before
    public void setUp() throws Exception {
        user = new User ("Somebody", "1234");
    }

    @Test
    public void loginSuccessful(){
        assertTrue(user.login("Somebody", "1234"));
    }

    @Test
    public void loginUnsuccessful(){
        assertFalse(user.login("Nobody", "1234"));
    }
}