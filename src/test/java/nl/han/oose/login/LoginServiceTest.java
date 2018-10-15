package nl.han.oose.login;

import nl.han.oose.service.login.LoginService;
import nl.han.oose.service.login.LoginServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class LoginServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private LoginService sut;

    @Before
    public void setUp() throws Exception {
        sut = new LoginServiceImpl();
    }

//    @Test
//    public void testSuccesfulLogin() throws LoginException {
//        Account account = new Account("rickrietveld", "password");
//        UserToken login = sut.login(account);
//        assertEquals("Rick Rietveld", login.getUser());
//        assertEquals("1234-1234-1234", login.getToken());
//    }
//
//    @Test
//    public void testFailedLogin() throws LoginException {
//        thrown.expect(LoginException.class);
//        thrown.expectMessage("Wrong credentials");
//        Account account = new Account("Rick", "WrongPassword");
//        sut.login(account);
//    }


}