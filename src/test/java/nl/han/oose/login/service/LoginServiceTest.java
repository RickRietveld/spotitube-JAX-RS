package nl.han.oose.login.service;

import nl.han.oose.entity.account.Account;
import nl.han.oose.entity.account.UserToken;
import nl.han.oose.persistence.account.TokenDAO;
import nl.han.oose.persistence.account.UserDAO;
import nl.han.oose.service.login.LoginServiceImpl;
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

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private UserDAO userDAOMock;

    @Mock
    private TokenDAO tokenDAOMock;

    @InjectMocks
    private LoginServiceImpl sut;

    @Test
    public void testThatLoginRequestReturnsAValidToken() {
        List<Account> accounts = new ArrayList<>();
        Account account = new Account("rickrietveld", "password");
        accounts.add(account);
        UserToken userToken = new UserToken("9999-9999-9999", "rickrietveld");

        Mockito.when(userDAOMock.getAllAccounts()).thenReturn(accounts);
        Mockito.when(tokenDAOMock.getNewToken(Mockito.any())).thenReturn(userToken);

        assertEquals("rickrietveld", userToken.getUser());
        assertEquals("9999-9999-9999", userToken.getToken());
    }

    @Test
    public void testThatExceptionIsThrownWhenLoginIsInvalid() throws LoginException {
        thrown.expect(LoginException.class);
        thrown.expectMessage("Wrong credentials.");

        List<Account> accounts = new ArrayList<>();
        Account account = new Account("rickrietveld", "password");
        accounts.add(account);
        Account wrongCredentials = new Account("rietveldrick", "wordpass");

        Mockito.when(userDAOMock.getAllAccounts()).thenReturn(accounts);
        sut.login(wrongCredentials);
    }
}