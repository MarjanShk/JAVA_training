package tests;

import org.testng.annotations.Test;

/**
 * Created by Admin on 21.04.2016.
 */
public class RegistrationTest extends TestBase {
    @Test
    public void testRegistration(){
        app.registration().start("user", "user@mail.com");
    }
}
