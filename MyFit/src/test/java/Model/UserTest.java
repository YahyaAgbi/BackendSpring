package Model;

import ma.FITTRACK.MyFit.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testSetAndGetUsername() {
        String username = "testUsername";
        user.setUsername(username);
        assertEquals(username, user.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        String email = "testEmail";
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        String password = "testPassword";
        user.setPassword(password);
        assertEquals(password, user.getPassword());
    }
}
