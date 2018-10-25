package nl.han.oose.login.controller;

import nl.han.oose.controller.login.LoginController;
import nl.han.oose.entity.account.Account;
import nl.han.oose.entity.account.UserToken;
import nl.han.oose.service.login.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.security.auth.login.LoginException;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    private final Account account = new Account("", "");
    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController sut;

    @Test
    public void testThatLoginRespondsOK() throws LoginException {
        UserToken userToken = new UserToken("", "");
        Mockito.when(loginService.login(Mockito.any())).thenReturn(userToken);
        Response loginResponse = sut.login(account);
        assertEquals(Response.Status.OK.getStatusCode(), loginResponse.getStatus());
        assertEquals(userToken, loginResponse.getEntity());
    }

    @Test
    public void testThatLoginRespondsUNAUTHORIZED() throws LoginException {
        Mockito.when(loginService.login(Mockito.any())).thenThrow(new LoginException("Incorrect credentials."));
        Response loginResponse = sut.login(account);
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), loginResponse.getStatus());
    }


}