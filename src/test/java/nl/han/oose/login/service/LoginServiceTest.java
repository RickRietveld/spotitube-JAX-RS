package nl.han.oose.login.service;

import nl.han.oose.entity.account.Account;
import nl.han.oose.entity.account.UserToken;
import nl.han.oose.persistence.account.TokenDAO;
import nl.han.oose.persistence.account.UserDAO;
import nl.han.oose.service.login.LoginServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    private final UserToken USER_TOKEN = new UserToken("9999-9999-9999", "rickrietveld");
    private List<Account> accounts;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private UserDAO userDAOMock;

    @Mock
    private TokenDAO tokenDAOMock;

    @InjectMocks
    private LoginServiceImpl sut;

    @Before
    public void setUp() {
        accounts = new ArrayList<>();
        Account account = new Account("rickrietveld", "password");
        accounts.add(account);
    }

    @Test
    public void testThatLoginRequestReturnsAValidToken() {
        Mockito.when(userDAOMock.getAllAccounts()).thenReturn(accounts);
        Mockito.when(tokenDAOMock.getNewToken(Mockito.any())).thenReturn(USER_TOKEN);
        assertEquals("rickrietveld", USER_TOKEN.getUser());
        assertEquals("9999-9999-9999", USER_TOKEN.getToken());
    }

    @Test
    public void testThatExceptionIsThrownWhenLoginIsInvalid() throws LoginException {
        thrown.expect(LoginException.class);
        thrown.expectMessage("Wrong credentials.");
        Account wrongCredentials = new Account("rietveldrick", "wordpass");
        Mockito.when(userDAOMock.getAllAccounts()).thenReturn(accounts);
        sut.login(wrongCredentials);
    }
}