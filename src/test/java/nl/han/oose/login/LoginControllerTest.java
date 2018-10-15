package nl.han.oose.login;

import nl.han.oose.controller.login.LoginController;
import nl.han.oose.service.login.LoginService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController sut;

//    @Test
//    public void testStatusOKOnSuccessfullLogin() throws LoginException {
//        UserToken userToken = new UserToken("", "");
//        Mockito.when(loginService.login(Mockito.any())).thenReturn(userToken);
//        Account account = new Account("", "");
//        Response loginResponse = sut.login(account);
//
//        assertEquals(Response.Status.OK.getStatusCode(), loginResponse.getStatus());
//        assertEquals(userToken, loginResponse.getEntity());
//    }
}